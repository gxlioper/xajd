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
		<title><%=session.getAttribute("xxmc")%>��ҵ��</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="zh-CN" />
		<meta content="all" name="robots" />
		<meta name="author" content="�����������ӹ������޹�˾ hzzfsoft@126.com" />
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
			<span>�û����ͣ�����Ա</span>
			<h4>
				<a href="changepassword.do?method=changepassword&jytype=jyweb">�޸�����</a>
				<a href="index.do?method=jyindex&doType=left&jytype=jyweb">ע��</a>
			</h4>
			<ul>

				<li id="zxdt">
					<a
						href="adminoperation.do?method=adminoperation&act=zxdt&jytype=jyweb">���¶�̬</a>
				</li>
				<li id="lmgx">
					<a
						href="adminoperation.do?method=adminoperation&act=addmessage&jytype=jyweb">��Ŀ����</a>
				</li>
				<li id="tpxx">
					<a
						href="adminoperation.do?method=adminoperation&act=tpxx&jytype=jyweb">ͼƬ��Ϣ</a>
				</li>
				<li id="jlgl">
					<a
						href="adminoperation.do?method=adminoperation&act=jlgl&jytype=jyweb">��������</a>
				</li>
				<li id="qzgl">
					<a
						href="adminoperation.do?method=adminoperation&act=qzxxgl&jytype=jyweb">��ְ����</a>
				</li>
				<li id="fbzp">
					<a
						href="adminoperation.do?method=adminoperation&act=fbzp&jytype=jyweb">������Ƹ</a>
				</li>
				<li id="zpsh">
					<a
						href="adminoperation.do?method=adminoperation&act=fbshenhe&jytype=jyweb">��Ƹ���</a>
				</li>
<%--				<li id="jyzd">--%>
<%--					<a--%>
<%--						href="adminoperation.do?method=adminoperation&act=jyzd&jytype=jyweb">��ҵָ��</a>--%>
<%--				</li>--%>
				<li id="xzzx">
					<a
						href="adminoperation.do?method=adminoperation&act=xzzx&jytype=jyweb">��������</a>
				</li>
				<li id="yhgl">
					<a
						href="adminoperation.do?method=adminoperation&act=yhgl&jytype=jyweb">�û�����</a>
				</li>
				<li id="wjdc">
					<a
						href="adminoperation.do?method=adminoperation&act=wjdcgl&jytype=jyweb">�ʾ����</a>
				</li>
				<li id="mmzh">
					<a
						href="adminoperation.do?method=adminoperation&act=findpassword&jytype=jyweb">�����һ�</a>
				</li>
				<li id="sstj">
					<a
						href="adminoperation.do?method=adminoperation&act=sstj&jytype=jyweb">ʵʱͳ��</a>
				</li>
				<li id="yqlj">
					<a
						href="adminoperation.do?method=adminoperation&act=yqlj&jytype=jyweb">��������</a>
			</ul>
			<div>
				<button onclick="rebacktopweb()">
					������ҳ
				</button>
			</div>
		</div>
	</body>
</html>
