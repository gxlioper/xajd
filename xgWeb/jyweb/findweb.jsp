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

		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript">
		function findtswt(){
		 
		 document.forms[0].action = "findweb.do?method=dwyhmmzh&doType1=pro&jytype=jyweb";
		 document.forms[0].submit();
		}
		
		function yztswt(){
		
		  document.forms[0].action = "findweb.do?method=dwyhmmzh&doType2=find&jytype=jyweb";
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
		<logic:equal name="xxdm" value="10856" scope="session">
			<logic:equal name="yhm" value="" scope="session">
				<jsp:include flush="true" page="head_false.jsp"></jsp:include>
				<input type="hidden" name="lock" value="closed" />
			</logic:equal>
			<logic:notEqual name="yhm" value="" scope="session">
				<jsp:include flush="true" page="head.jsp"></jsp:include>
				<input type="hidden" name="lock" value="open" />
			</logic:notEqual>
		</logic:equal>
		<logic:notEqual name="xxdm" value="10856" scope="session">
			<jsp:include flush="true" page="head.jsp"></jsp:include>
			<input type="hidden" name="lock" value="open" />
		</logic:notEqual>
		<div class="mimax">
			<html:form action="/findweb" method="post">
				<input type="hidden" name="timesago"
					value="<bean:write name="timesago"/>" />

				<h1>
					��λ�����һ�
				</h1>
				<h4>
					<br>
					<br>

					�û�����
					<html:text name="rs" property="yhm"
						onkeypress="if(window.event.keyCode==13) findtswt();" />
					&nbsp;&nbsp;
					<button onclick="findtswt();">
						�ύ
					</button>

					<br>
					<br>
					<logic:empty name="rs">
                        &nbsp;
					</logic:empty>
					<logic:notEmpty name="rs">
						<table width="45%" cellspacing="0" cellpadding="0" height="150">
							<tr>
								<td align="right">
									��ʾ����1��
								</td>
								<td>
									<html:text name="rs" property="tswt1" readonly="true" />
								</td>

							</tr>
							<tr>
								<td align="right">
									�ش�1��
								</td>
								<td>
									<html:text name="rs" property="da1" />
								</td>
							</tr>
							<tr>
								<td align="right">
									��ʾ����2��
								</td>
								<td>
									<html:text name="rs" property="tswt2" readonly="true" />
								</td>
							</tr>
							<tr>
								<td align="right">
									�ش�2��
								</td>
								<td>
									<html:text name="rs" property="da2"
										onkeypress="if(window.event.keyCode==13) yztswt();" />
								</td>
							</tr>
							<tr align="center">
								<td colspan="2">
									<button onclick="yztswt();">
										�һ�����
									</button>
									&nbsp;
									<button type="reset">
										ȡ������
									</button>
								</td>
							</tr>
						</table>

					</logic:notEmpty>
				</h4>
			</html:form>
			<h3>

			</h3>
		</div>
		<jsp:include flush="true" page="foot.jsp"></jsp:include>


		<logic:notEmpty name="nothisyhm">
			<logic:equal name="nothisyhm" value="nothisyhm">
				<script>
                      alert("���û������ڣ�");
                </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="find">
			<logic:equal name="find" value="no">
				<script>
                      alert("�ش���������ԣ�");
                </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="find">
			<logic:equal name="find" value="most">
				<script>
                      alert("���ѳ�������һش��������Ժ����ԣ�");
                </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
