package com.web.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import com.util.Constant;

/**
 * 代码压缩:
 * 1.创建和编辑类的操作不做压缩，因为访问量不大
 * 2.<pre></pre>内部的代码不做处理
 * 3.只对content type为html和json做处理
 * 
 */
public class HtmlCompressFilter implements Filter {

	private FilterConfig filterConfig = null;
	
	// pre标签正则
	public static final String reg_tag_pre = "(<|&lt;)pre[\\w\\W]*?[\\w\\W]*?/pre[\\w\\W]*?(>|&gt;)";
	public static final Pattern pattern_tag_pre = Pattern.compile(reg_tag_pre, Pattern.CASE_INSENSITIVE);
	
	// 页面内嵌js/css注释正则
	public static final String reg_embed_comment = "(/\\*([^*]|[\\r\\n]|(\\*+([^*/]|[\\r\\n])))*\\*+/)|(//.*)";
	public static final Pattern pattern_embed_comment = Pattern.compile(reg_embed_comment, Pattern.CASE_INSENSITIVE);
	
	// 页面内嵌html注释正则
	public static final String reg_html_comment = "(<!--.*?-->)";
	public static final Pattern pattern_html_comment = Pattern.compile(reg_html_comment, Pattern.CASE_INSENSITIVE);
	
	// contentType类型正则
	public static final String content_type_html_json = ".*?(html|json).*?";
	public static final String content_type_js = ".*?(javascript).*?";
	public static final String content_type_css = ".*?(css).*?";
	
	
	// 代码段类
	class CodeFragment {
		private String left;
		private String fragment;
		private String right;
		
		public CodeFragment(String left, String fragment, String right) {
			this.left = left;
			this.fragment = fragment;
			this.right = right;
		}

		public String getLeft() {
			return left;
		}

		public void setLeft(String left) {
			this.left = left;
		}

		public String getFragment() {
			return fragment;
		}

		public void setFragment(String fragment) {
			this.fragment = fragment;
		}

		public String getRight() {
			return right;
		}

		public void setRight(String right) {
			this.right = right;
		}
	}
	
	// 字节输出
	private static class ByteArrayServletStream extends ServletOutputStream {

		ByteArrayOutputStream baos;
		ByteArrayServletStream(ByteArrayOutputStream baos) {
			this.baos = baos;
		}
		
		@Override
		public void write(int b) throws IOException {
			baos.write(b);
		}
	}
	
	// 字符输出
	private static class ByteArrayPrintWriter {
		private ByteArrayOutputStream baos = new ByteArrayOutputStream();
		private PrintWriter pw = new PrintWriter(baos);
		private ServletOutputStream sos = new ByteArrayServletStream(baos);
		
		public PrintWriter getWriter() {
			return pw;
		}
		
		public ServletOutputStream getStream() {
			return sos;
		}
		
		byte[] toByte() {
			return baos.toByteArray();
		}

	}
	
	// 自定义HttpServletResponse包装类
	public class CharResponseWrapper extends HttpServletResponseWrapper {
		private ByteArrayPrintWriter output;
		private boolean usingWriter;
		
		public CharResponseWrapper(HttpServletResponse response) {
			super(response);
			usingWriter = false;
			output = new ByteArrayPrintWriter();
		}
		
		public byte[] getByteArray() {
			return output.toByte();
		}
		
		@Override
		public ServletOutputStream getOutputStream() throws IOException {
			// will error out, if in use
			if (usingWriter) {
				super.getOutputStream();
			}
			usingWriter = false;
			return output.getStream();
		}
		
		@Override
		public PrintWriter getWriter() throws IOException {
			// will error out, if in use
			if (usingWriter) {
				super.getWriter();
			}
			usingWriter = false;
			return output.getWriter();
		}
		
		public String toString() {
			return output.toString();
		}
	}
	
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if (httpRequest.getRequestURI().indexOf("update") != -1
				|| httpRequest.getRequestURI().indexOf("create") != -1
				|| httpRequest.getRequestURI().startsWith("/admin/")
				) {
			chain.doFilter(request, response);
			return;
		}
		CharResponseWrapper wrappedResponse = new CharResponseWrapper(
				(HttpServletResponse) response);
		chain.doFilter(request, wrappedResponse);
		byte[] bytes = wrappedResponse.getByteArray();
		
		String contentType = wrappedResponse.getContentType();
		if (contentType != null && contentType.matches(".*?(html|json).*?")) {
			String html = new String(bytes);
			
			// 过滤html注释
			html = this.filterHtmlComment(html);
			// 过滤页面内嵌js/css注释
			html = this.filterEmbedJsCssComment(html);
			// 处理pre标签
			html = this.filterPreTag(html);
			
			response.getOutputStream().write(html.getBytes(Constant.charset));
		} else if (contentType != null && contentType.matches(".*?(javascript).*?")) {
			// js文件暂不做处理
			String html = new String(bytes);
			response.getOutputStream().write(html.getBytes(Constant.charset));
		} else if (contentType != null && contentType.matches(".*?(css).*?")) {
			// css文件暂不做处理
			String html = new String(bytes);
			response.getOutputStream().write(html.getBytes(Constant.charset));
		} else {
			String html = new String(bytes);
			response.getOutputStream().write(html.getBytes(Constant.charset));
		}
	}
	
	private String filterHtmlComment(String html) {
		Matcher matcher = pattern_html_comment.matcher(html);
		while (matcher.find()) {
			String fragment = matcher.group(0);
			html = html.replace(fragment, "");
		}
		return html;
	}
	
	private String filterEmbedJsCssComment(String html) {
		return html;
	}
	
	private String filterPreTag(String html) {
		Matcher matcher = pattern_tag_pre.matcher(html);
		List<CodeFragment> codeFragments = new ArrayList<CodeFragment>();
		while (matcher.find()) {
			String fragment = matcher.group(0);
			String left = matcher.group(1);
			String right = matcher.group(2);
			CodeFragment codeFragment = new CodeFragment(left, fragment, right);
			codeFragments.add(codeFragment);
			// 占位符<pre>idx</pre>
			html = html.replace(fragment, left + "pre" + right
					+ codeFragments.size() + left + "/pre" + right);
		}
		html = html.replaceAll("[\r\n]", "").replaceAll(">\\s*?<", "><").trim();
		// 还原占位符
		for (int i = 0; i < codeFragments.size(); i++) {
			CodeFragment codeFragment = codeFragments.get(i);
			String fragment = codeFragment.getFragment();
			String left = codeFragment.getLeft();
			String right = codeFragment.getRight();
			html = html.replace(left + "pre" + right + (i + 1) + left
					+ "/pre" + right, fragment);
		}
		return html;
	}
	
	
	
	@Override
	public void destroy() {
		filterConfig = null;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

}
