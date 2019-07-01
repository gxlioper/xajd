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
	</head>
	<script type="text/javascript">
   
      function buttonstylechange(){
           
               var webtype = $("webtype").value;
               if(webtype=="zxdt"){
                  document.getElementById("zxdt").className="current";
               }
               if(webtype=="lmgx"){
              
                  document.getElementById("lmgx").className="current";
               }
               if(webtype=="tpxx"){
                  document.getElementById("tpxx").className="current";
               }
               if(webtype=="jlgl"){
                  document.getElementById("jlgl").className="current";
               }
               if(webtype=="qzgl"){
                  document.getElementById("qzgl").className="current";
               }
               if(webtype=="fbzp"){
                  document.getElementById("fbzp").className="current";
               }
               if(webtype=="zpsh"){
                  document.getElementById("zpsh").className="current";
               }
               if(webtype=="jyzd"){
                  document.getElementById("jyzd").className="current";
               }
               if(webtype=="zxdt"){
                  document.getElementById("zxdt").className="current";
               }
               if(webtype=="xzzx"){
                  document.getElementById("xzzx").className="current";
               }
               if(webtype=="yhgl"){
                  document.getElementById("yhgl").className="current";
               }
               if(webtype=="wjdc"){
                  document.getElementById("wjdc").className="current";
               }
               if(webtype=="mmzh"){
                  document.getElementById("mmzh").className="current";
               }
               if(webtype=="sstj"){
                  document.getElementById("sstj").className="current";
               }
               if(webtype=="yqlj"){
                  document.getElementById("yqlj").className="current";
               }
           
           }
           function rebacktopweb(){
               document.forms[0].action = "index.do?jytype=jyweb";
		 	   document.forms[0].submit();
           
           }
	</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="buttonstylechange();">
		<div class="htgl">
			<h3></h3>
			<span>用户类型：管理员</span>
			<h4>
				<a href="changepassword.do?method=changepassword&jytype=jyweb">修改密码</a>
				<a href="index.do?method=jyindex&doType=left&jytype=jyweb">注销</a>
			</h4>
			<ul>

				<li id="zxdt">
					<a
						href="adminoperation.do?method=adminoperation&act=zxdt&jytype=jyweb">最新动态</a>
				</li>
				<li id="lmgx">
					<a
						href="adminoperation.do?method=adminoperation&act=addmessage&jytype=jyweb">栏目更新</a>
				</li>
				<li id="tpxx">
					<a
						href="adminoperation.do?method=adminoperation&act=tpxx&jytype=jyweb">图片信息</a>
				</li>
				<li id="jlgl">
					<a
						href="adminoperation.do?method=adminoperation&act=jlgl&jytype=jyweb">简历管理</a>
				</li>
				<li id="qzgl">
					<a
						href="adminoperation.do?method=adminoperation&act=qzxxgl&jytype=jyweb">求职管理</a>
				</li>
				<li id="fbzp">
					<a
						href="adminoperation.do?method=adminoperation&act=fbzp&jytype=jyweb">发布招聘</a>
				</li>
				<li id="zpsh">
					<a
						href="adminoperation.do?method=adminoperation&act=fbshenhe&jytype=jyweb">招聘审核</a>
				</li>
<%--				<li id="jyzd">--%>
<%--					<a--%>
<%--						href="adminoperation.do?method=adminoperation&act=jyzd&jytype=jyweb">就业指导</a>--%>
<%--				</li>--%>
				<li id="xzzx">
					<a
						href="adminoperation.do?method=adminoperation&act=xzzx&jytype=jyweb">下载中心</a>
				</li>
				<li id="yhgl">
					<a
						href="adminoperation.do?method=adminoperation&act=yhgl&jytype=jyweb">用户管理</a>
				</li>
				<li id="wjdc">
					<a
						href="adminoperation.do?method=adminoperation&act=wjdcgl&jytype=jyweb">问卷调查</a>
				</li>
				<li id="mmzh">
					<a
						href="adminoperation.do?method=adminoperation&act=findpassword&jytype=jyweb">密码找回</a>
				</li>
				<li id="sstj">
					<a
						href="adminoperation.do?method=adminoperation&act=sstj&jytype=jyweb">实时统计</a>
				</li>
				<li id="yqlj">
					<a
						href="adminoperation.do?method=adminoperation&act=yqlj&jytype=jyweb">友情链接</a>
			</ul>
			<div>
				<button onclick="rebacktopweb()">
					返回首页
				</button>
			</div>
		</div>
	</body>
</html>
