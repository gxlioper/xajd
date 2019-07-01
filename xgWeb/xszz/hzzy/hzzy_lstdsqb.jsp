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
				<td colspan="2">
					<br />
				</td>
			</tr>
			<tr>
				<td colspan="2" scope="col">
					<div align="center">
							<h3>
						<strong>
								����ְҵ����<bean:message key="lable.xsgzyxpzxy" />��ɫͨ��֤��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No.
						</strong>
							 </h3>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2" scope="col">
					<div align="center">
						<bean:write name='rs' property="sqsj_year" />
						&nbsp;��&nbsp;
						<bean:write name='rs' property="sqsj_mon" />
						&nbsp;��&nbsp;
						<bean:write name='rs' property="sqsj_day" />
						&nbsp;��
					</div>
				</td>
			</tr>
			<tr>
				<td width="95%" scope="col">
					<logic:equal name="isNULL" value="no">
						<table width="100%" class="tbstyle">
							<tr>
								<td width="12%" scope="col">
									<div align="center">
										ѧ������
									</div>
								</td>
								<td width="20%" scope="col">
									<div align="center">
										<bean:write name='rs' property="xm" />
									</div>
								</td>
								<td width="12%" scope="col">
									<div align="center">
										����ϵ(Ժ)
									</div>
								</td>
								<td width="20%" scope="col">
									<div align="center">
										<bean:write name='rs' property="xymc" />
									</div>
								</td>
								<td width="12%" scope="col">
									<div align="center">
										�༶
									</div>
								</td>
								<td width="24%" scope="col">
									<div align="center">
										<bean:write name='rs' property="bjmc" />
									</div>
								</td>
							</tr>
							<tr>
								<td scope="row">
									<div align="center">
										Ƿ��ԭ��
									</div>
								</td>
								<td colspan="5">
									<bean:write name='rs' property="qfyy" />
								</td>
							</tr>
							<tr>
								<td scope="row">
									<div align="center">
										�Ƚ���ѧ��
									</div>
								</td>
								<td colspan="3">
									<logic:equal name='rs' property="xjnxf_dx" value=" ">
										<div align="left">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(��д)&nbsp;
										</div>
									</logic:equal>
									<logic:notEqual name='rs' property="xjnxf_dx" value=" ">
										<div align="center">
											(��д)&nbsp;
											<bean:write name='rs' property="xjnxf_dx" />
										</div>
									</logic:notEqual>
								</td>
								<td colspan="2">
									<div align="right">
										��
										<bean:write name='rs' property="xjnxf" />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</div>
								</td>
							</tr>
							<tr>
								<td scope="row">
									<div align="center">
										�Ƚ��ɴ��ܷ�
									</div>
								</td>
								<td colspan="3">
									<logic:equal name='rs' property="xjndgf_dx" value=" ">
										<div align="left">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(��д)&nbsp;
										</div>
									</logic:equal>
									<logic:notEqual name='rs' property="xjndgf_dx" value=" ">
										<div align="center">
											(��д)&nbsp;
											<bean:write name='rs' property="xjndgf_dx" />
										</div>
									</logic:notEqual>
								</td>
								<td colspan="2">
									<div align="right">
										��
										<bean:write name='rs' property="xjndgf" />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</div>
								</td>
							</tr>
							<tr>
								<td scope="row">
									<div align="center">
										�ϼ�
									</div>
								</td>
								<td colspan="3">
									<logic:equal name='rs' property="hj_dx" value=" ">
										<div align="left">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(��д)&nbsp;
										</div>
									</logic:equal>
									<logic:notEqual name='rs' property="hj_dx" value=" ">
										<div align="center">
											(��д)&nbsp;
											<bean:write name='rs' property="hj_dx" />
										</div>
									</logic:notEqual>
								</td>
								<td colspan="2">
									<div align="right">
										��
										<bean:write name='rs' property="hj" />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</div>
								</td>
							</tr>
							<tr>
								<td scope="row">
									<div align="center">
										���ಿ�ֻ��ʽ�ͻ���ʱ��
									</div>
								</td>
								<td colspan="5">
									<div align="center">
										<bean:write name='rs' property="qybfhkfshsj" />
									</div>
								</td>
							</tr>
						</table>
					</logic:equal>
					<logic:equal name="isNULL" value="is">
						<table width="100%" class="tbstyle">
							<tr>
								<td width="14%" scope="col">
									<div align="center">
										ѧ������
									</div>
								</td>
								<td width="18%" scope="col">
									<div align="center">
										<bean:write name='rs' property="xm" />
									</div>
								</td>
								<td width="12%" scope="col">
									<div align="center">
										����ϵ(Ժ)
									</div>
								</td>
								<td width="20%" scope="col">
									<div align="center">
										<bean:write name='rs' property="xymc" />
									</div>
								</td>
								<td colspan="3" scope="col">
									<div align="center">
										�༶
									</div>
								</td>
								<td colspan="6" scope="col">
									<div align="center">
										<bean:write name='rs' property="bjmc" />
									</div>
								</td>
							</tr>
							<tr>
								<td scope="row">
									<div align="center">
										Ƿ��ԭ��
									</div>
								</td>
								<td colspan="12">
									<bean:write name='rs' property="qfyy" />
								</td>
							</tr>
							<tr>
								<td scope="row" rowspan="2">
									<div align="center">
										�Ƚ���ѧ��
									</div>
								</td>
								<td colspan="3" rowspan="2">
									<logic:equal name='rs' property="xjnxf_dx" value=" ">
										<div align="left">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(��д)&nbsp;
										</div>
									</logic:equal>
									<logic:notEqual name='rs' property="xjnxf_dx" value=" ">
										<div align="center">
											(��д)&nbsp;
											<bean:write name='rs' property="xjnxf_dx" />
										</div>
									</logic:notEqual>
								</td>
								<td width="4%">
									<div align="center">
										��
									</div>
								</td>
								<td width="4%">
									<div align="center">
										ʮ
									</div>
								</td>
								<td width="4%">
									<div align="center">
										��
									</div>
								</td>
								<td width="4%">
									<div align="center">
										ǧ
									</div>
								</td>
								<td width="4%">
									<div align="center">
										��
									</div>
								</td>
								<td width="4%">
									<div align="center">
										ʮ
									</div>
								</td>
								<td width="4%">
									<div align="center">
										Ԫ
									</div>
								</td>
								<td width="4%">
									<div align="center">
										��
									</div>
								</td>
								<td width="4%">
									<div align="center">
										��
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
							</tr>
							<tr>
								<td scope="row">
									<div align="center">
										�Ƚ��ɴ��ܷ�
									</div>
								</td>
								<td colspan="3">
									<logic:equal name='rs' property="xjndgf_dx" value=" ">
										<div align="left">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(��д)&nbsp;
										</div>
									</logic:equal>
									<logic:notEqual name='rs' property="xjndgf_dx" value=" ">
										<div align="center">
											(��д)&nbsp;
											<bean:write name='rs' property="xjndgf_dx" />
										</div>
									</logic:notEqual>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
							</tr>
							<tr>
								<td scope="row">
									<div align="center">
										�ϼ�
									</div>
								</td>
								<td colspan="3">
									<logic:equal name='rs' property="hj_dx" value=" ">
										<div align="left">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(��д)&nbsp;
										</div>
									</logic:equal>
									<logic:notEqual name='rs' property="hj_dx" value=" ">
										<div align="center">
											(��д)&nbsp;
											<bean:write name='rs' property="hj_dx" />
										</div>
									</logic:notEqual>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
							</tr>
							<tr>
								<td scope="row">
									<div align="center">
										���ಿ�ֻ��ʽ�ͻ���ʱ��
									</div>
								</td>
								<td colspan="12">
									<div align="center">
										<bean:write name='rs' property="qybfhkfshsj" />
									</div>
								</td>
							</tr>
						</table>
					</logic:equal>
				</td>
				<td width="5%" scope="col">
					<div align="center">
						��
						<br />
						һ
						<br />
						��
						<br />
						&nbsp;
						<br />
						&nbsp;
						<br />
						��
						<br />
						��
						<br />
						��
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="left">
						&nbsp;ѧ��ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ϵ(Ժ)����֧���ǩ��:
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<br />
					<br />
					<br />
				</td>
			</tr>
			<tr>
				<td colspan="2" scope="col">
					<div align="center">
							<h3>
						<strong>
								����ְҵ����<bean:message key="lable.xsgzyxpzxy" />��ɫͨ��֤��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No.
						</strong>
							</h3>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2" scope="col">
					<div align="center">
						<bean:write name='rs' property="sqsj_year" />
						&nbsp;��&nbsp;
						<bean:write name='rs' property="sqsj_mon" />
						&nbsp;��&nbsp;
						<bean:write name='rs' property="sqsj_day" />
						&nbsp;��
					</div>
				</td>
			</tr>
			<tr>
				<td width="95%" scope="col">
					<logic:equal name="isNULL" value="no">
						<table width="100%" class="tbstyle">
							<tr>
								<td width="14%" scope="col">
									<div align="center">
										ѧ������
									</div>
								</td>
								<td width="18%" scope="col">
									<div align="center">
										<bean:write name='rs' property="xm" />
									</div>
								</td>
								<td width="12%" scope="col">
									<div align="center">
										����ϵ(Ժ)
									</div>
								</td>
								<td width="20%" scope="col">
									<div align="center">
										<bean:write name='rs' property="xymc" />
									</div>
								</td>
								<td width="12%" scope="col">
									<div align="center">
										�༶
									</div>
								</td>
								<td width="24%" scope="col">
									<div align="center">
										<bean:write name='rs' property="bjmc" />
									</div>
								</td>
							</tr>
							<tr>
								<td scope="row">
									<div align="center">
										Ƿ��ԭ��
									</div>
								</td>
								<td colspan="5">
									<bean:write name='rs' property="qfyy" />
								</td>
							</tr>
							<tr>
								<td scope="row">
									<div align="center">
										�Ƚ���ѧ��
									</div>
								</td>
								<td colspan="3">
									<logic:equal name='rs' property="xjnxf_dx" value=" ">
										<div align="left">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(��д)&nbsp;
										</div>
									</logic:equal>
									<logic:notEqual name='rs' property="xjnxf_dx" value=" ">
										<div align="center">
											(��д)&nbsp;
											<bean:write name='rs' property="xjnxf_dx" />
										</div>
									</logic:notEqual>
								</td>
								<td colspan="2">
									<div align="right">
										��
										<bean:write name='rs' property="xjnxf" />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</div>
								</td>
							</tr>
							<tr>
								<td scope="row">
									<div align="center">
										�Ƚ��ɴ��ܷ�
									</div>
								</td>
								<td colspan="3">
									<logic:equal name='rs' property="xjndgf_dx" value=" ">
										<div align="left">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(��д)&nbsp;
										</div>
									</logic:equal>
									<logic:notEqual name='rs' property="xjndgf_dx" value=" ">
										<div align="center">
											(��д)&nbsp;
											<bean:write name='rs' property="xjndgf_dx" />
										</div>
									</logic:notEqual>
								</td>
								<td colspan="2">
									<div align="right">
										��
										<bean:write name='rs' property="xjndgf" />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</div>
								</td>
							</tr>
							<tr>
								<td scope="row">
									<div align="center">
										�ϼ�
									</div>
								</td>
								<td colspan="3">
									<logic:equal name='rs' property="hj_dx" value=" ">
										<div align="left">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(��д)&nbsp;
										</div>
									</logic:equal>
									<logic:notEqual name='rs' property="hj_dx" value=" ">
										<div align="center">
											(��д)&nbsp;
											<bean:write name='rs' property="hj_dx" />
										</div>
									</logic:notEqual>
								</td>
								<td colspan="2">
									<div align="right">
										��
										<bean:write name='rs' property="hj" />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</div>
								</td>
							</tr>
							<tr>
								<td scope="row">
									<div align="center">
										���ಿ�ֻ��ʽ�ͻ���ʱ��
									</div>
								</td>
								<td colspan="5">
									<div align="center">
										<bean:write name='rs' property="qybfhkfshsj" />
									</div>
								</td>
							</tr>
						</table>
					</logic:equal>
					<logic:equal name="isNULL" value="is">
						<table width="100%" class="tbstyle">
							<tr>
								<td width="12%" scope="col">
									<div align="center">
										ѧ������
									</div>
								</td>
								<td width="20%" scope="col">
									<div align="center">
										<bean:write name='rs' property="xm" />
									</div>
								</td>
								<td width="12%" scope="col">
									<div align="center">
										����ϵ(Ժ)
									</div>
								</td>
								<td width="20%" scope="col">
									<div align="center">
										<bean:write name='rs' property="xymc" />
									</div>
								</td>
								<td colspan="3" scope="col">
									<div align="center">
										�༶
									</div>
								</td>
								<td colspan="6" scope="col">
									<div align="center">
										<bean:write name='rs' property="bjmc" />
									</div>
								</td>
							</tr>
							<tr>
								<td scope="row">
									<div align="center">
										Ƿ��ԭ��
									</div>
								</td>
								<td colspan="12">
									<bean:write name='rs' property="qfyy" />
								</td>
							</tr>
							<tr>
								<td scope="row" rowspan="2">
									<div align="center">
										�Ƚ���ѧ��
									</div>
								</td>
								<td colspan="3" rowspan="2">
									<logic:equal name='rs' property="xjnxf_dx" value=" ">
										<div align="left">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(��д)&nbsp;
										</div>
									</logic:equal>
									<logic:notEqual name='rs' property="xjnxf_dx" value=" ">
										<div align="center">
											(��д)&nbsp;
											<bean:write name='rs' property="xjnxf_dx" />
										</div>
									</logic:notEqual>
								</td>
								<td width="4%">
									<div align="center">
										��
									</div>
								</td>
								<td width="4%">
									<div align="center">
										ʮ
									</div>
								</td>
								<td width="4%">
									<div align="center">
										��
									</div>
								</td>
								<td width="4%">
									<div align="center">
										ǧ
									</div>
								</td>
								<td width="4%">
									<div align="center">
										��
									</div>
								</td>
								<td width="4%">
									<div align="center">
										ʮ
									</div>
								</td>
								<td width="4%">
									<div align="center">
										Ԫ
									</div>
								</td>
								<td width="4%">
									<div align="center">
										��
									</div>
								</td>
								<td width="4%">
									<div align="center">
										��
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
							</tr>
							<tr>
								<td scope="row">
									<div align="center">
										�Ƚ��ɴ��ܷ�
									</div>
								</td>
								<td colspan="3">
									<logic:equal name='rs' property="xjndgf_dx" value=" ">
										<div align="left">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(��д)&nbsp;
										</div>
									</logic:equal>
									<logic:notEqual name='rs' property="xjndgf_dx" value=" ">
										<div align="center">
											(��д)&nbsp;
											<bean:write name='rs' property="xjndgf_dx" />
										</div>
									</logic:notEqual>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
							</tr>
							<tr>
								<td scope="row">
									<div align="center">
										�ϼ�
									</div>
								</td>
								<td colspan="3">
									<logic:equal name='rs' property="hj_dx" value=" ">
										<div align="left">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(��д)&nbsp;
										</div>
									</logic:equal>
									<logic:notEqual name='rs' property="hj_dx" value=" ">
										<div align="center">
											(��д)&nbsp;
											<bean:write name='rs' property="hj_dx" />
										</div>
									</logic:notEqual>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										&nbsp;
									</div>
								</td>
							</tr>
							<tr>
								<td scope="row">
									<div align="center">
										���ಿ�ֻ��ʽ�ͻ���ʱ��
									</div>
								</td>
								<td colspan="12">
									<div align="center">
										<bean:write name='rs' property="qybfhkfshsj" />
									</div>
								</td>
							</tr>
						</table>
					</logic:equal>
				</td>
				<td width="5%" scope="col">
					<div align="center">
						��
						<br />
						��
						<br />
						��
						<br />
						&nbsp;
						<br />
						&nbsp;
						<br />
						��
						<br />
						��
						<br />
						��
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="left">
						&nbsp;ѧ��ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ϵ(Ժ)����֧���ǩ��:
					</div>
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
