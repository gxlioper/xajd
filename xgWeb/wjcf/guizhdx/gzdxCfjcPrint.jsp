<%@ page language="java" contentType="text/html;charset=GBK"%>

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
<html:html>
<base target="_self">
<head>
	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="Pragma" http-equiv="no-cache" />
	<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
	<meta http-equiv="Expires" http-equiv="0" />
	<meta name="Copyright" content="������� zfsoft" />
</head>
<%
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/xszzFunction.js"></script>
<script language="javascript">
</script>
<body>
	<html:form action="/wjcfnblgwh" method="post">
		<table width="90%" border="0" id="theTable" align="center">
			<tr>
				<td scope="col">
					<div align="center">
						<h1>
							<strong> ���ݴ�ѧѧ��Υ�ʹ��ֽ������</strong>
						</h1>
					</div>
				</td>
			</tr>
			<tr>
				<td scope="col" align="center">
					<div align="left">У������(��ѧ����)</div>
					<table width="100%" border="0"  cellpadding="0" cellspacing="0">
						<tr>
							<td width="50%">
								<table class="tbstyle" style="border:0px" width="100%" cellpadding="0" cellspacing="0">
									<tr height="61px">
										<td>
											����<u>&nbsp;&nbsp;${rs.xm }&nbsp;&nbsp;</u>ѧ��<u>&nbsp;&nbsp;${rs.xh }&nbsp;&nbsp;</u> �Ա�<u>&nbsp;&nbsp;${rs.xb }</u> 
										</td>
									</tr>
									<tr height="60px">
										<td>
											<u>${rs.xymc }&nbsp;&nbsp; </u><bean:message key="lable.xsgzyxpzxy" /><u>&nbsp;&nbsp;${rs.zymc }&nbsp;&nbsp;  </u>רҵ<u>&nbsp;&nbsp;${rs.nj }&nbsp;&nbsp; </u> ��
										</td>
									</tr>
									<tr>
										<td width="100%">
											Υ�����ɣ�
											<p style="height:310px">&nbsp;&nbsp;&nbsp;&nbsp;${rs.JTWJSY }</p>
											<div align="right">
											ǩ����&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;
											����&nbsp;&nbsp;&nbsp;&nbsp;<br/>
											��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<bean:message key="lable.xsgzyxpzxy" />�����
											<p style=" height=130px">&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyyj }</p>
											<div align="right">
											ǩ����&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;
											����&nbsp;&nbsp;&nbsp;&nbsp;<br/>
											��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											</div>
										</td>
									</tr>
									<tr>
										<td >
											�������ϣ�
											<p style="height:122px"></p>
											<div align="center">
											<bean:message key="lable.xsgzyxpzxy" />����ʱ��
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											</div>
										</td>
									</tr>
								</table>
							</td>
							<td>
								<table class="tbstyle" style="border:0px" width="100%"  cellpadding="0" cellspacing="0">
									<tr>
										<td>
											ѧ�������������
											<p style="height:170px">&nbsp; </p>
											<div align="right">
											ǩ����&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;
											����&nbsp;&nbsp;&nbsp;&nbsp;<br/>
											��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											</div>
										</td>
									</tr>
									<tr>
										<td>
											�йز��Ż�ǩ�����
											<p style="height:170px"></p>
											<div align="right">
											ǩ����&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;
											����&nbsp;&nbsp;&nbsp;&nbsp;<br/>
											��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											</div>
										</td>
									</tr>
									
									<tr>
										<td>
											ѧУ�����
											<p style="height:130px">&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxyj }</p>
											<div align="right">
											ǩ����&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;
											����&nbsp;&nbsp;&nbsp;&nbsp;<br/>
											��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											</div>
										</td>
									</tr>
									<tr>
										<td>
											��ע��
											<p style="height:122px">&nbsp;</p>
											<div align="right">
												&nbsp;&nbsp;&nbsp;&nbsp;
												��
												&nbsp;&nbsp;&nbsp;&nbsp;
												��&nbsp;&nbsp;&nbsp;&nbsp;
												��
											</div>
											
										</td>
									</tr>
								</table>
							</td>
						</tr>
						
					</table>
					<center>
						<div style="width:90%" align="left">�����У�쿴���ñ�</div>
						<div style="width:90%" align="right">���ݴ�ѧѧ������</div>
					</center>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
