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
		function shenhe(){
		
		 document.forms[0].action = "zpshenhe.do?method=zpshenhe&jytype=jyweb&doType=view&act=shenhe";
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
		<html:form action="/zpshenhe" method="post">
			<html:hidden name="rs" property="pkValue" />
			<div class="mainframe">
				<div class="jy_midframe">
					<h1>
						招聘信息
					</h1>
					<table width="98%" align="center" class="tbborder">
						<tr height="25">
							<td align="right" width="15%">
								招聘职位：
							</td>
							<td width="35%">
								<font color="blue" size="2"><B><bean:write name="rs"
											property="zpzw" /> </B> </font>
							</td>
							<td align="right" width="15%">
								公司名称：
							</td>
							<td width="35%">
								&nbsp;
								<bean:write name="rs" property="gsmc" />
								&nbsp;
							</td>
						</tr>
						<tr height="25">
							<td align="right">
								电子邮箱：
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="email" />
								&nbsp;
							</td>
							<td align="right">
								联系电话：
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="lxdh" />
								&nbsp;
							</td>
						</tr>
						<tr height="25">
							<td align="right">
								联系人：
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="lxr" />
								&nbsp;
							</td>
							<td align="right">
								移动电话：
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="yddh" />
								&nbsp;
							</td>
						</tr>
						<tr height="25">
							<td align="right">
								网址：
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="gswz" />
								&nbsp;
							</td>
							<td align="right">
								传真：
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="cz" />
								&nbsp;
							</td>
						</tr>
						<tr height="25">
							<td align="right">
								工作地点：
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="gzdd" />
								&nbsp;
							</td>
							<td align="right">
								招聘人数：
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="zprs" />
								&nbsp;
							</td>
						</tr>
						<tr height="25">
							<td align="right">
								行业分类：
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="hyfl" />
								&nbsp;
							</td>
							<td align="right">
								外语要求：
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="wyyq" />
								&nbsp;
							</td>
						</tr>
						<tr height="25">
							<td align="right">
								试用期薪水：
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="syxs" />
								&nbsp;
							</td>
							<td align="right">
								转正后薪水：
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="zzxs" />
								&nbsp;
							</td>
						</tr>
						<tr height="25">
							<td align="right">
								性别要求：
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="xb" />
								&nbsp;
							</td>
							<td align="right">
								单位性质：
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="dwxz" />
								&nbsp;
							</td>
						</tr>
						<tr height="25">
							<td align="right">
								学历要求：
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="xlyq" />
								&nbsp;
							</td>
							<td align="right">
								面试时间：
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="mssj" />
								&nbsp;
							</td>
						</tr>
						<tr height="25">
							<td align="right">
								面试携带：
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="msxd" />
								&nbsp;
							</td>
							<td align="right">
								面试地点：
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="msdd" />
								&nbsp;
							</td>
						</tr>
						<tr>
							<td align="right">
								岗位职责：
							</td>
							<td colspan="3">
								<html:textarea name="rs" property="gwzz" rows="8" cols="85%"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<td align="right">
								职位要求：
							</td>
							<td colspan="3">
								<html:textarea name="rs" property="zwyq" rows="8" cols="85%"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<td align="right">
								公司简介：
							</td>
							<td colspan="3">
								<html:textarea name="rs" property="gsjj" rows="8" cols="85%"
									readonly="true" />
							</td>
						</tr>
						<tr height="25">
							<td>
								审核：
							</td>
							<td>
								<div align="left">
									<html:select name="rs" property="xxsh" style="width:120px">
										<html:option value="未审核">未审核</html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
									</html:select>
								</div>
							</td>
							<td align="right">
								发布时间：
							</td>
							<td>
								<bean:write name="rs" property="fbsj" />
							</td>

						</tr>
						<tr>

						</tr>
					</table>
					<div align="center">
						<button onclick="shenhe();">
							提交
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="reset">
							关闭
						</button>
					</div>
					<div>
						<h3>
						</h3>
					</div>
					<jsp:include flush="true" page="foot.jsp"></jsp:include>
		</html:form>
		<logic:notEmpty name="shenhe">
				<logic:equal name="shenhe" value="ok">
					<script>
                      alert("审核提交成功！");
                    </script>
				</logic:equal>
				<logic:equal name="shenhe" value="no">
					<script>
                      alert("审核提交失败！");
                    </script>
				</logic:equal>
		</logic:notEmpty>
	</body>
</html>
