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
        <base target="_self">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript">
	          function sfyz(){
	             var pkValue= $("yhm").value;
	             document.forms[0].action = "viewyhxxinfo.do?method=yhxxinfo&jytype=jyweb&act=update&doType=view&pkValue="+pkValue;
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
		<html:form action="/viewyhxxinfo.do">
		<html:hidden name="rs" property="yhm"  />
           <div class="mainframe">
				<div class="jy_midframe">
					<h1>
						单位注册信息
					</h1>
					<table width="97%" border="0" cellpadding="0" cellspacing="0" align="center">
						<tr align="right">
							<td colspan="2" >
							是否通过身份验证：
							<html:radio name="rs" property="sftgsfyz" value="是">是</html:radio>
							&nbsp;&nbsp;&nbsp;
							<html:radio name="rs" property="sftgsfyz" value="否">否</html:radio>
							&nbsp;&nbsp;&nbsp;
								<button onClick="sfyz();">
									提交验证
								</button>
							</td>
						</tr>
						<tr>
						<td>
						&nbsp;
						</td>
						</tr>
						<tr height="25">
							<td width="11%">
								<div align="right">
									用户名：
								</div>
							</td>
							<td width="35%">
								<div align="left">
									&nbsp;&nbsp;&nbsp;
									<bean:write name="rs" property="yhm" />
								</div>
							</td>
						</tr>
						<tr height="25">
							<td width="11%">
								<div align="right">
									用户密码：
								</div>
							</td>
							<td width="35%">
								<div align="left">
									&nbsp;&nbsp;&nbsp;
									<bean:write name="rs" property="mm" />
								</div>
							</td>
						</tr>

						<tr>
							<td colspan="2" height="25px" bgcolor="#F4F4F4">
								密码找回信息
							</td>
						</tr>

						<tr height="25">
							<td>
								<div align="right">
									提示问题1：
								</div>
							</td>
							<td>
								<div align="left">
									&nbsp;&nbsp;&nbsp;
									<bean:write name="rs" property="tswt1" />
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div align="right">
									答案1：
								</div>
							</td>
							<td>
								<div align="left">
									&nbsp;&nbsp;&nbsp;
									<bean:write name="rs" property="da1" />
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div align="right">
									提示问题2：
								</div>
							</td>
							<td>
								<div align="left">
									&nbsp;&nbsp;&nbsp;
									<bean:write name="rs" property="tswt2" />
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div align="right">
									答案2：
								</div>
							</td>
							<td>
								<div align="left">
									&nbsp;&nbsp;&nbsp;
									<bean:write name="rs" property="da2" />
								</div>
							</td>
						</tr>
						<tr height="25">
							<td colspan="2" bgcolor="#F4F4F4">
								单位基本信息
							</td>
						</tr>
						<tr height="25">
							<td>
								<div align="right">
									单位名称：
								</div>
							</td>
							<td width="35%">
								<div align="left">
									&nbsp;&nbsp;&nbsp;
									<bean:write name="rs" property="dwmc" />
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div align="right">
									企业法人：
								</div>
							</td>
							<td width="35%">
								<div align="left">
									&nbsp;&nbsp;&nbsp;
									<bean:write name="rs" property="qyfr" />
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div align="right">
									机构代码证号：
								</div>
							</td>
							<td width="35%">
								<div align="left">
									&nbsp;&nbsp;&nbsp;
									<bean:write name="rs" property="jgdmzh" />
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div align="right">
									单位性质：
								</div>
							</td>
							<td width="35%">
								<div align="left">
									&nbsp;&nbsp;&nbsp;
									<bean:write name="rs" property="dwxz" />
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div align="right">
									行业分类：
								</div>
							</td>
							<td width="35%">
								<div align="left">
									&nbsp;&nbsp;&nbsp;
									<bean:write name="rs" property="hyfl" />
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div align="right">
									联系人：
								</div>
							</td>
							<td width="35%">
								<div align="left">
									&nbsp;&nbsp;&nbsp;
									<bean:write name="rs" property="lxr" />
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div align="right">
									联系电话：
								</div>
							</td>
							<td width="35%">
								<div align="left">
									&nbsp;&nbsp;&nbsp;
									<bean:write name="rs" property="lxdh" />
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div align="right">
									传真：
								</div>
							</td>
							<td width="35%">
								<div align="left">
									&nbsp;&nbsp;&nbsp;
									<bean:write name="rs" property="cz" />
									&nbsp;
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div align="right">
									电子邮箱：
								</div>
							</td>
							<td width="35%">
								<div align="left">
									&nbsp;&nbsp;&nbsp;
									<bean:write name="rs" property="email" />
									&nbsp;
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div align="right">
									地址：
								</div>
							</td>
							<td width="35%">
								<div align="left">
									&nbsp;&nbsp;&nbsp;
									<bean:write name="rs" property="dz" />
									&nbsp;
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div align="right">
									邮编：
								</div>
							</td>
							<td width="35%">
								<div align="left">
									&nbsp;&nbsp;&nbsp;
									<bean:write name="rs" property="yb" />
									&nbsp;
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								单位简介：
							</td>
							<td >
								&nbsp;
								<html:textarea name="rs" property="dwjj" rows="15" cols="100%"
									readonly="true" />
							</td>
						</tr>
						<tr height="25">
							<td>
								注册时间：
							</td>
							<td>
								&nbsp;&nbsp;&nbsp;
								<bean:write name="rs" property="zcsj" />
							</td>
						</tr>
					</table>

					<div>
						<h3>

						</h3>
					</div>
				</div>
		</html:form>
		<jsp:include flush="true" page="foot.jsp"></jsp:include>
		<logic:notEmpty name="update">
				<logic:equal name="update" value="ok">
					<script>
                      alert("身份验证提交成功！");
                    </script>
				</logic:equal>
				<logic:equal name="update" value="no">
					<script>
                      alert("身份验证提交成功！");
                    </script>
				</logic:equal>
		</logic:notEmpty>
	</body>
</html>
