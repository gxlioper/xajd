<%@ page language="java" pageEncoding="GBK"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/comm/commFunction.js"></script>
	<meta http-equiv="refresh"
			content="0;url=${forwardPath}">
  </head>
  <body onload="showWaitingDiv('100000');">
  <!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
  </body>
</html>
