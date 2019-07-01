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
		    function buttonstylechange(){
           
               var webtype = $("webtype").value;
               if(webtype=="fbzp"){
                  document.getElementById("fbzp").className="current";
               }
               if(webtype=="ckjl"){
                  document.getElementById("ckjl").className="current";
               }
               if(webtype=="mrhf"){
                  document.getElementById("mrhf").className="current";
               }
            }
		
		    function rebacktopweb(){
               document.forms[0].action = "index.do?jytype=jyweb";
		 	   document.forms[0].submit();
           
           }
		</script>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="buttonstylechange();">
	
	   <div class="htgl">
			<h3></h3>
			  <span>用户类型：＜单位＞</span>
			  <h4><a href="changepassword.do?method=changepassword&jytype=jyweb">修改密码</a>
              <a href="index.do?method=jyindex&doType=left&jytype=jyweb">注销</a> 
			  </h4>
			  <ul>
				<li id="fbzp"> <a href="adminoperation.do?method=adminoperation&act=zpxx&jytype=jyweb" ><font color="black">发布招聘</font></a></li>
				<li id="ckjl"><a href="adminoperation.do?method=adminoperation&act=ckjl&jytype=jyweb" ><font color="black">学生简历</font></a></li>
				<li id="mrhf"><a href="adminoperation.do?method=adminoperation&act=mrhf&jytype=jyweb" ><font color="black">默认回复</font></a></li>
			  </ul>			
			</div>
			<div>
				<button onclick="rebacktopweb()">
					返回首页
				</button>
			</div>
	</body>
</html>
