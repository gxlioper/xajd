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
						��Ƹ��Ϣ
					</h1>
					<table width="98%" align="center" class="tbborder">
						<tr height="25">
							<td align="right" width="15%">
								��Ƹְλ��
							</td>
							<td width="35%">
								<font color="blue" size="2"><B><bean:write name="rs"
											property="zpzw" /> </B> </font>
							</td>
							<td align="right" width="15%">
								��˾���ƣ�
							</td>
							<td width="35%">
								&nbsp;
								<bean:write name="rs" property="gsmc" />
								&nbsp;
							</td>
						</tr>
						<tr height="25">
							<td align="right">
								�������䣺
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="email" />
								&nbsp;
							</td>
							<td align="right">
								��ϵ�绰��
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="lxdh" />
								&nbsp;
							</td>
						</tr>
						<tr height="25">
							<td align="right">
								��ϵ�ˣ�
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="lxr" />
								&nbsp;
							</td>
							<td align="right">
								�ƶ��绰��
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="yddh" />
								&nbsp;
							</td>
						</tr>
						<tr height="25">
							<td align="right">
								��ַ��
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="gswz" />
								&nbsp;
							</td>
							<td align="right">
								���棺
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="cz" />
								&nbsp;
							</td>
						</tr>
						<tr height="25">
							<td align="right">
								�����ص㣺
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="gzdd" />
								&nbsp;
							</td>
							<td align="right">
								��Ƹ������
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="zprs" />
								&nbsp;
							</td>
						</tr>
						<tr height="25">
							<td align="right">
								��ҵ���ࣺ
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="hyfl" />
								&nbsp;
							</td>
							<td align="right">
								����Ҫ��
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="wyyq" />
								&nbsp;
							</td>
						</tr>
						<tr height="25">
							<td align="right">
								������нˮ��
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="syxs" />
								&nbsp;
							</td>
							<td align="right">
								ת����нˮ��
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="zzxs" />
								&nbsp;
							</td>
						</tr>
						<tr height="25">
							<td align="right">
								�Ա�Ҫ��
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="xb" />
								&nbsp;
							</td>
							<td align="right">
								��λ���ʣ�
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="dwxz" />
								&nbsp;
							</td>
						</tr>
						<tr height="25">
							<td align="right">
								ѧ��Ҫ��
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="xlyq" />
								&nbsp;
							</td>
							<td align="right">
								����ʱ�䣺
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="mssj" />
								&nbsp;
							</td>
						</tr>
						<tr height="25">
							<td align="right">
								����Я����
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="msxd" />
								&nbsp;
							</td>
							<td align="right">
								���Եص㣺
							</td>
							<td>
								&nbsp;
								<bean:write name="rs" property="msdd" />
								&nbsp;
							</td>
						</tr>
						<tr>
							<td align="right">
								��λְ��
							</td>
							<td colspan="3">
								<html:textarea name="rs" property="gwzz" rows="8" cols="85%"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<td align="right">
								ְλҪ��
							</td>
							<td colspan="3">
								<html:textarea name="rs" property="zwyq" rows="8" cols="85%"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<td align="right">
								��˾��飺
							</td>
							<td colspan="3">
								<html:textarea name="rs" property="gsjj" rows="8" cols="85%"
									readonly="true" />
							</td>
						</tr>
						<tr height="25">
							<td>
								��ˣ�
							</td>
							<td>
								<div align="left">
									<html:select name="rs" property="xxsh" style="width:120px">
										<html:option value="δ���">δ���</html:option>
										<html:option value="ͨ��">ͨ��</html:option>
										<html:option value="��ͨ��">��ͨ��</html:option>
									</html:select>
								</div>
							</td>
							<td align="right">
								����ʱ�䣺
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
							�ύ
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="reset">
							�ر�
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
                      alert("����ύ�ɹ���");
                    </script>
				</logic:equal>
				<logic:equal name="shenhe" value="no">
					<script>
                      alert("����ύʧ�ܣ�");
                    </script>
				</logic:equal>
		</logic:notEmpty>
	</body>
</html>
