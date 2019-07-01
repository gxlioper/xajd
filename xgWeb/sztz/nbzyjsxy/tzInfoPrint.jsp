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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<style media="print" type="text/css">
   .brk{
	page-break-after:always;
    }
   .noPrin{display:none}
</style>
	<body onload="colorOn()">
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/nbzy_sztz" method="post">
			<div align="center" style="font: bold;font-size: 18px;">
				<bean:write name="xxmc" />
				��ѧ��������չ֤��
			</div>
			<br>
			<div>
				ѧ�ţ�
				<u>&nbsp;<bean:write name="rsStu" property="xh" />&nbsp;</u>&nbsp;&nbsp;
				�༶��
				<u>&nbsp;<bean:write name="rsStu" property="bjmc" />&nbsp;</u>&nbsp;&nbsp;
				<bean:message key="lable.xsgzyxpzxy" />��
				<u>&nbsp;<bean:write name="rsStu" property="xymc" />&nbsp;</u>&nbsp;&nbsp;
				רҵ��
				<u>&nbsp;<bean:write name="rsStu" property="zymc" />&nbsp;</u>&nbsp;&nbsp;
				��ѧʱ�䣺
				<u>&nbsp;<bean:write name="rsStu" property="rxrq" />&nbsp;</u>
			</div>
			<table width="100%" class="tbstyle">
				<tr>
					<td width="15%" align="center">
						����
					</td>
					<td>
						<bean:write name="rsStu" property="xm" />
					</td>
					<td align="center">
						�Ա�
					</td>
					<td>
						<bean:write name="rsStu" property="xb" />
					</td>
					<td rowspan="5" width="20%">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td align="center">
						����
					</td>
					<td >
						<bean:write name="rsStu" property="mzmc" />
					</td>
					<td align="center">
						����
					</td>
					<td>
						<bean:write name="rsStu" property="jg" />
					</td>
				</tr>
				<tr>
					<td align="center">
						��������
					</td>
					<td >
						<bean:write name="rsStu" property="csrq" />
					</td>
					<td align="center">
						������ò
					</td>
					<td>
						<bean:write name="rsStu" property="zzmmmc" />
					</td>
				</tr>
				<tr>
					<td align="center">
						���֤����
					</td>
					<td colspan="3">
						<bean:write name="rsStu" property="sfzh" />
					</td>
				</tr>
				<tr>
					<td align="center">
						������չ֤���
					</td>
					<td colspan="3">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="5" align="center">
						��У�ڼ佱�����
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<table width="100%" class="tbstyle">
							<tr>
								<td align="center">
									��������
								</td>
								<td align="center">
									ʱ��
								</td>
								<td align="center">
									��ע
								</td>
							</tr>
							<logic:iterate name="rsJlxm" id="s">
								<tr>
									<logic:iterate id="v" name="s" offset="0">
										<td>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<tr>
								<td>
									&nbsp;
								</td>
								<td>
									&nbsp;
								</td>
								<td>
									&nbsp;
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="5" align="center">
						������չ��¼����������ɼ���
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<table width="100%" class="tbstyle">
							<logic:iterate name="rsTzXm" id="s">
								<bean:size id="tSize" name="s" property="xmList" />
								<logic:iterate id="v" name="s" property="kmList">
									<tr style="cursor:hand">
										<td rowspan="<%=tSize%>" >
											<bean:write name="v" property="kmm" />
										</td>
										<logic:notEqual name="tSize" value="0">
											<logic:iterate id="b" name="s" property="xmList">
												<td>
													&nbsp;
													<bean:write name="b" property="xmjl" />
												</td>												
												<%
												out.print("</tr>");
												%>
											</logic:iterate>
										</logic:notEqual>
										<logic:equal name="tSize" value="0">
											<td>
												&nbsp;
											</td>											
										</logic:equal>
									</tr>
								</logic:iterate>
							</logic:iterate>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="5" height="40" align="center">
						ѧ��������չ������֤
					</td>
				</tr>
				<tr>
					<td colspan="5">
						&nbsp;&nbsp;&nbsp;&nbsp;����ʵ����֤�������У <u>&nbsp;<bean:write name="rsStu" property="xymc" />&nbsp;</u> Ժ��ϵ��<u>&nbsp;<bean:write name="rsStu" property="nj" />&nbsp;</u> ��ѧ�� <u> &nbsp;<bean:write name="rsStu" property="xm" />&nbsp;</u>  ��������չ���ݿ͹���ʵ��<br>
						&nbsp;&nbsp;&nbsp;&nbsp;����֤ʵ��
					<br><br><br><br>
					<div align="right">
					ѧ��������չ����
					</div>
					<div align="right">
					(������Ч)
					</div>
					<div align="right">
					<bean:write name="fzrq"/>
					</div>	
					</td>
				</tr>
			</table>
			<div class='noPrin' align="center">
				<input type='button' class='button2' value='ҳ������'
					onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='��ӡԤ��'
					onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='ֱ�Ӵ�ӡ'
					onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>
		</html:form>

	</body>
</html>
