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
							<strong> ���ݴ�ѧѧ��Υ�ʹ�������</strong>
						</h1>
					</div>
				</td>
			</tr>
			<tr>
				<td scope="col" align="center">
					<table width="100%" border="0"  cellpadding="0" cellspacing="0">
						<tr height="600px">
							<td width="50%">
								<table class="tbstyle" style="border:0px" width="100%" cellpadding="0" cellspacing="0">
									<tr height="60px">
										<td>
											����<u>&nbsp;&nbsp;${rs.xm }&nbsp;&nbsp;</u>ѧ��<u>&nbsp;&nbsp;${rs.xh }&nbsp;&nbsp; </u>�Ա�<u>&nbsp;&nbsp;${rs.xb }&nbsp;&nbsp;</u>
										</td>
									</tr>
									<tr height="60px">
										<td>
											<u>${rs.xymc }&nbsp;&nbsp;</u> <bean:message key="lable.xsgzyxpzxy" /><u>&nbsp;&nbsp;${rs.zymc }&nbsp;&nbsp;  </u>רҵ<u>&nbsp;&nbsp;${rs.nj }&nbsp;&nbsp; </u>��
										</td>
									</tr>
									<tr>
										<td width="100%">
											Υ�����ɣ�
											<p style="height:265px">&nbsp;&nbsp;&nbsp;&nbsp;${rs.bz }</p>
											��д��ǩ���� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											��&nbsp;&nbsp;&nbsp;&nbsp;    ��&nbsp;&nbsp;&nbsp;&nbsp;    ��
										</td>
									</tr>
									<tr>
										<td>&nbsp;
											<h2>���������ʵ</h2>
										<p style="height:70px"/>
											<div align="right">
											ѧ������ǩ��:
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>
												 &nbsp;&nbsp;&nbsp;&nbsp;��
												 &nbsp;&nbsp;&nbsp;&nbsp;��
												 &nbsp;&nbsp;&nbsp;&nbsp;��
												 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											</div>
										</td>
									</tr>
									<tr>
										<td >
											�������ϣ�֤��Ŀ¼����
											<p style="height:134px"/>
										</td>
									</tr>
									<tr>
										<td>
											<strong>�������ݣ�</strong><br/>
											1������ͨ�ߵ�ѧУѧ������涨����&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��<br/>
											2�������ҽ�������Υ�洦��취����&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��<br/>
											3�������ݴ�ѧѧ��Υ�ʹ�����������&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��<br/>
										</td>
									</tr>
								</table>
							</td>
							<td>
								<table class="tbstyle" style="border:0px" width="100%"  cellpadding="0" cellspacing="0">
									<tr>
										<td>
												Ժ�������о������
												<p style="height:120px">&nbsp;</p>
												<div align="center">(<bean:message key="lable.xsgzyxpzxy" />���£�������ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</div>
												<br/>
												<br/>
										</td>
									</tr>
									<tr>
										<td>
											������ز����϶������
											<p style="height:132px"></p>
											<div align="right">
												ǩ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>
											&nbsp;&nbsp;&nbsp;&nbsp;��
											&nbsp;&nbsp;&nbsp;&nbsp;��
											&nbsp;&nbsp;&nbsp;&nbsp;��
											&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;
											</div>
										</td>
									</tr>
									<tr>
										<td>
											ѧ�������������
											<p style="height:130px">&nbsp; </p>
											<div align="right">
											ǩ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<p/>
											&nbsp;&nbsp;&nbsp;&nbsp;��
											&nbsp;&nbsp;&nbsp;&nbsp;��
											&nbsp;&nbsp;&nbsp;&nbsp;��
											&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;
											</div>
										</td>
									</tr>
									<tr>
										<td>
											�ֹ�У�쵼���
											<p style="height:115 px">&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxclyj }</p>
											<div align="right">
												�ֹ�У�쵼�֣�&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;<br/>
												&nbsp;&nbsp;&nbsp;&nbsp;��
												&nbsp;&nbsp;&nbsp;&nbsp;��
												&nbsp;&nbsp;&nbsp;&nbsp;��
												&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;
											</div>
											
										</td>
									</tr>
								</table>
							</td>
						</tr>
						
					</table>
					<center>
						<div style="width:90%">
						<div align="left">������һʽ���� һ��װ��ѧ��������һ��ѧУ���棬���������ֽ</div>
						<div align="right">���ݴ�ѧѧ������</div>
						</div>
					</center>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
