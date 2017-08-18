<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>网页对视频的录制 -- 通过navigator.getUserMedia()开启视频</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    1. 网页对视频的录制 -- 通过navigator.getUserMedia()开启视频. <br>
    <style>
	.grayscale{
	  -webkit-filter:grayscale(1);
	  -moz-filter:grayscale(1);
	  -o-filter:grayscale(1);
	  -ms-filter:grayscale(1);
	  filter:grayscale(1);
	}
	.sepia{
	  -webkit-filter:sepia(0.8);
	  -moz-filter:sepia(0.8);
	  -o-filter:sepia(0.8);
	  -ms-filter:sepia(0.8);
	  filter:sepia(0.8);
	}
	.blur{
	  -webkit-filter:blur(3px);
	  -moz-filter:blur(3px);
	  -o-filter:blur(3px);
	  -ms-filter:blur(3px);
	  filter:blur(3px);
	}
	.brightness{
	  -webkit-filter:brightness(0.3);
	  -moz-filter:brightness(0.3);
	  -o-filter:brightness(0.3);
	  -ms-filter:brightness(0.3);
	  filter:brightness(0.3);
	}
	.contrast{
	  -webkit-filter:contrast(0.5);
	  -moz-filter:contrast(0.5);
	  -o-filter:contrast(0.5);
	  -ms-filter:contrast(0.5);
	  filter:contrast(0.5);
	}
	.hue-rotate{
	  -webkit-filter:hue-rotate(90deg);
	  -moz-filter:hue-rotate(90deg);
	  -o-filter:hue-rotate(90deg);
	  -ms-filter:hue-rotate(90deg);
	  filter:hue-rotate(90deg);
	}
	.hue-rotate2{
	  -webkit-filter:hue-rotate(180deg);
	  -moz-filter:hue-rotate(180deg);
	  -o-filter:hue-rotate(180deg);
	  -ms-filter:hue-rotate(180deg);
	  filter:hue-rotate(180deg);
	}
	.hue-rotate3{
	  -webkit-filter:hue-rotate(270deg);
	  -moz-filter:hue-rotate(270deg);
	  -o-filter:hue-rotate(270deg);
	  -ms-filter:hue-rotate(270deg);
	  filter:hue-rotate(270deg);
	}
	.saturate{
	  -webkit-filter:saturate(10);
	  -moz-filter:saturate(10);
	  -o-filter:saturate(10);
	  -ms-filter:saturate(10);
	  filter:saturate(10);
	}
	.invert{
	  -webkit-filter:invert(1);
	  -moz-filter:invert(1);
	  -o-filter: invert(1);
	  -ms-filter: invert(1);
	  filter: invert(1);
	}
	</style>
	
	
	
	<div class="warning">
	<h2>Native web camera streaming (getUserMedia) is not supported in this browser.</h2>
	</div>
	<div class="container">
	  <h3>Current filter is: None</h3>
	  <button>Click here to change video filter</button>
	  <div style="clear:both"></div>
	  <div class="col">
	    <h2>HTML5 video object</h2>
	    <video></video>
	  </div>
	  <div class="col">
	    <h2>HTML5 canvas object</h2>
	    <canvas width="600" height="450"></canvas>
	  </div>
	</div>
	
	
	<script type="text/javascript">
	// Main initialization
	document.addEventListener('DOMContentLoaded', function() {
	  // Global variables
	  var video = document.querySelector('video');
	  var audio, audioType;
	  var canvas = document.querySelector('canvas');
	  var context = canvas.getContext('2d');
	  // Custom video filters
	  var iFilter = 0;
	  var filters = [
	    'grayscale',
	    'sepia',
	    'blur',
	    'brightness',
	    'contrast',
	    'hue-rotate',
	    'hue-rotate2',
	    'hue-rotate3',
	    'saturate',
	    'invert',
	    'none'
	  ];
	  // Get the video stream from the camera with getUserMedia
	  navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia ||
	    navigator.mozGetUserMedia || navigator.msGetUserMedia;
	  window.URL = window.URL || window.webkitURL || window.mozURL || window.msURL;
	  if (navigator.getUserMedia) {
	    // Evoke getUserMedia function
	    navigator.getUserMedia({video: true, audio: true}, onSuccessCallback, onErrorCallback);
	    function onSuccessCallback(stream) {
	      // Use the stream from the camera as the source of the video element
	      video.src = window.URL.createObjectURL(stream) || stream;
	      console.log(stream);
	      // Autoplay
	      video.play();
	      // HTML5 Audio
	      audio = new Audio();
	      audioType = getAudioType(audio);
	      if (audioType) {
	        audio.src = 'polaroid.' + audioType;
	        audio.play();
	      }
	    }
	    // Display an error
	    function onErrorCallback(e) {
	      var expl = 'An error occurred: [Reason: ' + e.code + ']';
	      console.error(expl);
	      alert(expl);
	      return;
	    }
	  } else {
	    document.querySelector('.container').style.visibility = 'hidden';
	    document.querySelector('.warning').style.visibility = 'visible';
	    return;
	  }
	  // Draw the video stream at the canvas object
	  function drawVideoAtCanvas(obj, context) {
	    window.setInterval(function() {
	      context.drawImage(obj, 0, 0);
	    }, 60);
	  }
	  // The canPlayType() function doesn't return true or false. In recognition of how complex
	  // formats are, the function returns a string: 'probably', 'maybe' or an empty string.
	  function getAudioType(element) {
	    if (element.canPlayType) {
	      if (element.canPlayType('audio/mp4; codecs="mp4a.40.5"') !== '') {
	        return('aac');
	      } else if (element.canPlayType('audio/ogg; codecs="vorbis"') !== '') {
	        return("ogg");
	      }
	    }
	    return false;
	  }
	  // Add event listener for our video's Play function in order to produce video at the canvas
	  video.addEventListener('play', function() {
	    drawVideoAtCanvas(this, context);
	  }, false);
	  // Add event listener for our Button (to switch video filters)
	  document.querySelector('button').addEventListener('click', function() {
	    video.className = '';
	    canvas.className = '';
	    var effect = filters[iFilter++ % filters.length]; // Loop through the filters.
	    if (effect) {
	      video.classList.add(effect);
	      canvas.classList.add(effect);
	      document.querySelector('.container h3').innerHTML = 'Current filter is: ' + effect;
	    }
	  }, false);
	}, false);
	</script>
  </body>
</html>
