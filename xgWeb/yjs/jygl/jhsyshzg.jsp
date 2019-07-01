<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
<head>
	<base target="_self">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link rel="stylesheet" type="text/css" media="all" href="style/calendar.css" title="win2k-cold-1" />
    <script type="text/javascript" src="style/calendar.js"></script>
    <script type="text/javascript" src="style/calendar-zh.js"></script>
    <script type="text/javascript" src="style/calendar-setup.js"></script>
	<script language="JavaScript" src="style/dmwh.js"></script>
	<script language="JavaScript" src="style/xjgl.js"></script>
	<script language="JavaScript" src="style/jhgl.js"></script>
	<script language="javascript">
	function checkModify(){
			changTab('/yjsjwgl/jhsysh.do?act=dosh');
		}
	</script>
</head>
 <%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<body>
	<html:form method="POST" action="jytjgl"
		enctype="multipart/form-data">		
		
		<div id="title">
			<div class="titiel_img"></div>
			��ǰ����λ�ã��ƻ���������-->���ҳ��
		</div>
		<div>
			<fieldset>
				<legend>
					<font color="blue">�ƻ������������</font>
				</legend>
				<input type="hidden" name="dm" value="${dm}">
				<input type="hidden" name="page" value="${page}">
				<table width="99%">
					<tr>
						<td align="center">
							ѧ��
						</td>
						<td><input type="text" value="${map.xh}"/></td>
						<td align="center">
							����
						</td>
						<td><input type="text" value="${map.xm}"/></td>
					</tr>
					<tr>
						<td align="center">
							�꼶
						</td>
						<td><input type="text" value="${map.nk}"/> </td>
						<td align="center">
							��������
						</td>
						<td><input type="text" value="${map.csrq}"/></td>	
					</tr>
					<tr>
						<td align="center">
							���֤��
						</td>
						<td><input type="text" value="${map.sfzh}"/></td>
						<td align="center">
							��������
						</td>
						<td><input type="text" value="${map.pyccmc}"/></td>	
					</tr>
					<tr>
						<td align="center">
							����״��
						</td>
						<td><input type="text" value="${map.hyzk}"/></td>
						<td align="center">
							����
						</td>
						<td><input type="text" value="${map.jgmc}"/></td>	
					</tr>
					<tr>
						<td align="center">
							����ʱ��
						</td>
						<td><input type="text" value="${map.sqsj}"/></td>
						<td align="center">
							����״̬
						</td>
						<td>
							<logic:equal value="bm" name="page">
								<html:select property="bmshzt" style="width:150px">
								<html:option value="${map.bmshztdm}">--${map.bmshzt}--</html:option>
								<html:option value="0">δ���</html:option>
								<html:option value="1">ͨ��</html:option>
								<html:option value="2">δͨ��</html:option>
								</html:select>
							</logic:equal>
							<logic:equal value="xy" name="page">
								<html:select property="xyshzt" style="width:150px">
								<html:option value="${map.xyshztdm}">--${map.xyshzt}--</html:option>
								<html:option value="0">δ���</html:option>
								<html:option value="1">ͨ��</html:option>
								<html:option value="2">δͨ��</html:option>
								</html:select>
							</logic:equal>
							<logic:equal value="ds" name="page">
								<html:select property="dsshzt" style="width:150px">
								<html:option value="${map.dsshztdm}">--${map.dsshzt}--</html:option>
								<html:option value="0">δ���</html:option>
								<html:option value="1">ͨ��</html:option>
								<html:option value="2">δͨ��</html:option>
								</html:select>
							</logic:equal>
							<logic:equal value="yws" name="page">
								<html:select property="ywsshzt" style="width:150px">
								<html:option value="${map.ywsshztdm}">--${map.ywsshzt}--</html:option>
								<html:option value="0">δ���</html:option>
								<html:option value="1">ͨ��</html:option>
								<html:option value="2">δͨ��</html:option>
								</html:select>
							</logic:equal>
						</td>
					</tr>
					<tr height="100px">
						<td align="center">
							������Ϣ
						</td>
						<td colspan="3">
							<html:textarea property="" value="${map.sqxx}" style="width:350px;height:95px"></html:textarea> 
						</td>
					</tr>
				</table>
			</fieldset>
			<div id="button">
				<logic:notPresent name="ck">
				<button class="button2" onclick="checkModify();">
					�� ��
				</button>
				</logic:notPresent>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="window.close();return false;">
					�� ��
				</button>
			</div>
	   </div>
			<logic:equal name="result" value="view">
				<script>
			    alert("<bean:write name='jyglForm' property='message'/>");
			    var page = window.dialogArguments.document.forms[0].page.value;
			    window.dialogArguments.document.forms[0].action='/yjsjwgl/jhsysh.do?act=find&page'+page;
			   	window.dialogArguments.document.forms[0].submit();
			   	window.close();
			  	</script>
			</logic:equal>
	</html:form>
</body>

</html:html>

