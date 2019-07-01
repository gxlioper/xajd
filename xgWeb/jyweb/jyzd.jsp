<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<title><%=session.getAttribute("xxmc")%>就业网</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="zh-CN" />
		<meta content="all" name="robots" />
		<meta name="author" content="杭州正方电子工程有限公司 hzzfsoft@126.com" />
		<meta name="Copyright" content="Copyright" />
		<meta name="description" content="description" />
		<meta name="keywords" content="description" />

		<link id="csss" rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link href="jyweb/style/module.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/base.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/forms.css" rel="stylesheet" type="text/css">

		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript">
		function findtswt(){
		 
		 document.forms[0].action = "findweb.do?doType1=pro&jytype=jyweb";
		 document.forms[0].submit();
		
		}
		
		
		</script>

	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body>
		<jsp:include flush="true" page="head.jsp"></jsp:include>
        <input type="hidden" name="webtype" value="jyzd" />
		<br>


		<div class="mainframe">
			<div class="ny_midframe">
				<div class="leftframe">
					<jsp:include flush="true" page="contrl.jsp"></jsp:include>
					<div class="rdxw">
						<h1></h1>
					</div>
					<div class="yqlj">
						<h1></h1>
						<span></span>
					</div>
				</div>
				<div class="ny_rightframe">
					<div class="ny_con">
							<h3>
								当前位置：
								<a href="index.do">首页</a>选择 就业指导
							</h3>
						<div align="center">
							<font size="5" color="red">对不起！该功能暂未开通</font>
							<br>
							<br>
							<br>
						</div>


					</div>
					<h2></h2>
				</div>
			</div>
		</div>


		<jsp:include flush="true" page="foot.jsp"></jsp:include>


		<logic:notEmpty name="nothisyhm">
			<logic:equal name="nothisyhm" value="nothisyhm">
				<script>
                      alert("该用户不存在！");
                </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
