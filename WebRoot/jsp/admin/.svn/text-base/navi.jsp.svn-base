<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.model.admin.CategoryBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="navi-container">
	<c:forEach items="${sessionScope.adminCateList }" var="cate" varStatus="status">
	<ul id="ul_cate_${cate.id}" class="ul_cate">
		<li class="li_cate" id="li_cate_${cate.id}">
			<!-- 目录 -->
			<a class="black bold" href="javascript:void(0);" onclick="toggleCate(${cate.id})">${cate.description}</a>
			<ul id="ul_file_${cate.id}" class="ul_file">
				<%-- <li id="li_file_${cate.id}" class="li_file">
					<a class="blue" href="${cate.href}" target="_self">${cate.description}</a>
				</li>
				<li id="li_file_${cate.id}" class="li_file">
					<a class="blue" href="${cate.href}" target="_self">${cate.description}</a>
				</li> --%>
			</ul>
		</li>
	</ul>
	</c:forEach>
</div>
<input type="hidden" name="_dir_file_" id="_dir_file_" value='${sessionScope.jsonCategoryCurrent }'/>
