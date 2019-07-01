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
	<title><bean:message key="lable.title" />
	</title>
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
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td>
					<div align="center">
							<h2>
						<strong>
								����Ƽ�<bean:message key="lable.xsgzyxpzxy" />��ɫͨ��
						</strong>
							</h2> 
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td scope="col" width="14%">
								<div align="center">
									Ժ&nbsp;&nbsp;ϵ
								</div>
							</td>
							<td scope="col" width="20%">
								<div align="center">
									<bean:write name='rs' property="xymc" />
								</div>
							</td>
							<td scope="col" width="13%">
								<div align="center">
									��&nbsp;&nbsp;��
								</div>
							</td>
							<td scope="col" width="20%">
								<div align="center">
									<bean:write name='rs' property="bjmc" />
								</div>
							</td>
							<td scope="col" width="13%">
								<div align="center">
									ѧ&nbsp;&nbsp;��
								</div>
							</td>
							<td scope="col" width="20%">
								<div align="center">
									<bean:write name='rs' property="xh" />
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									��&nbsp;&nbsp;��
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td>
								<div align="center">
									��&nbsp;&nbsp;��
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td>
								<div align="center">
									��&nbsp;&nbsp;��
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="mzmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									��&nbsp;&nbsp;��
									<br />
									<br />
									ԭ&nbsp;&nbsp;��
								</div>
							</td>
							<td colspan="5">
								<br />
								<bean:write name='rs' property="hjyy" />
								<br />
								<p align="right">
									����ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</p>
								<p align="right">
									<bean:write name='rs' property="sqsj_year" />
									&nbsp;��&nbsp;
									<bean:write name='rs' property="sqsj_mon" />
									&nbsp;��&nbsp;
									<bean:write name='rs' property="sqsj_day" />
									&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</p>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									����Ա
									<br />
									<br />
									��&nbsp;&nbsp;��
								</div>
							</td>
							<td colspan="5">
								<br />
								<bean:write name='rs' property="fdyshyj" />
								<br />
								<p align="right">
									ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</p>
								<p align="right">
									<logic:equal name="fdyspsj_year" value="empty">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="fdyspsj_year" value="empty">
									<bean:write name='rs' property="fdyspsj_year" />
									</logic:notEqual>
									&nbsp;��&nbsp;
									<logic:equal name="fdyspsj_mon" value="empty">
									&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="fdyspsj_mon" value="empty">
									<bean:write name='rs' property="fdyspsj_mon" />
									</logic:notEqual>
									&nbsp;��&nbsp;
									<logic:equal name="fdyspsj_day" value="empty">
									&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="fdyspsj_day" value="empty">
									<bean:write name='rs' property="fdyspsj_day" />
									</logic:notEqual>
									&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</p>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									Ժ&nbsp;&nbsp;ϵ
									<br />
									<br />
									��&nbsp;&nbsp;��
								</div>
							</td>
							<td colspan="5">
								<br />
								<bean:write name='rs' property="xyshyj" />
								<br />
								<p align="right">
									���£�ǩ�֣�:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</p>
								<p align="right">
									<logic:equal name="xyspsj_year" value="empty">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="xyspsj_year" value="empty">
									<bean:write name='rs' property="xyspsj_year" />
									</logic:notEqual>
									&nbsp;��&nbsp;
									<logic:equal name="xyspsj_mon" value="empty">
									&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="xyspsj_mon" value="empty">
									<bean:write name='rs' property="xyspsj_mon" />
									</logic:notEqual>
									&nbsp;��&nbsp;
									<logic:equal name="xyspsj_day" value="empty">
									&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="xyspsj_day" value="empty">
									<bean:write name='rs' property="xyspsj_day" />
									</logic:notEqual>
									&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</p>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									ѧ����
									<br />
									<br />
									��&nbsp;&nbsp;��
								</div>
							</td>
							<td colspan="5">
								<p>
									<br />
									&nbsp;&nbsp;&nbsp;&nbsp;������о���ͬ�⻺��ѧ��
									<u>&nbsp;
									<logic:equal name="tyhjxf" value="empty">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="tyhjxf" value="empty">
									<bean:write name='rs' property="tyhjxf" />
									</logic:notEqual>
									&nbsp;</u>Ԫ(��д
									<u>&nbsp;
									<logic:equal name="tyhjxfdx" value="empty">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="tyhjxfdx" value="empty">
									<bean:write name='rs' property="tyhjxfdx" />
									</logic:notEqual>
									&nbsp;</u>),
								
								<p>
									��
									<u>&nbsp;
									<logic:equal name="jzrq_year" value="empty">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="jzrq_year" value="empty">
									<bean:write name='rs' property="jzrq_year" />
									</logic:notEqual>
									&nbsp;</u>��
									<u>&nbsp;
									<logic:equal name="jzrq_mon" value="empty">
									&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="jzrq_mon" value="empty">
									<bean:write name='rs' property="jzrq_mon" />
									</logic:notEqual>
									&nbsp;</u>��
									<u>&nbsp;
									<logic:equal name="jzrq_day" value="empty">
									&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="jzrq_day" value="empty">
									<bean:write name='rs' property="jzrq_day" />
									</logic:notEqual>
									&nbsp;</u>��ֹ��
								</p>
								<p align="right">
									���£�ǩ�֣�:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</p>
								<p align="right">
									<logic:equal name="xxspsj_year" value="empty">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="xxspsj_year" value="empty">
									<bean:write name='rs' property="xxspsj_year" />
									</logic:notEqual>
									&nbsp;��&nbsp;
									<logic:equal name="xxspsj_mon" value="empty">
									&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="xxspsj_mon" value="empty">
									<bean:write name='rs' property="xxspsj_mon" />
									</logic:notEqual>
									&nbsp;��&nbsp;
									<logic:equal name="xxspsj_day" value="empty">
									&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="xxspsj_day" value="empty">
									<bean:write name='rs' property="xxspsj_day" />
									</logic:notEqual>
									&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</p>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									��&nbsp;&nbsp;ע
								</div>
							</td>
							<td colspan="5">
								<br />
								<bean:write name='rs' property="bz" />
								<br />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center">
		<input  value="��ӡ" name="button_print"
			onclick="expTab('theTable','')" />
	</div>
</body>
</html:html>
