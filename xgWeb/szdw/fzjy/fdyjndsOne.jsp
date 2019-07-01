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
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="checkWinType();getFdyList();">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/GetFdyList.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/szdwfzjy" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			<logic:notEmpty name="rs">
				<input type="hidden" id="sxsfdm" name="sxsfdm" value=""/>
				<input type="hidden" id="tableName" name="tableName" value="<bean:write name = "tableName" />"/>
				<input type="hidden" id="pk" name="pk"
					value="<bean:write  name="pk"  scope="request"/>" />
				<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope = "session"/>" />
				<fieldset>
					<legend>
						����Ա���ܴ�����Ϣ�����޸�
					</legend>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								�������ƣ�
							</td>
							<td align="left">
								<html:select name = "rs" property="bmdm" style="width:200px" styleId="bmdm" 
									onchange="getFdyList();">
										<html:option value=""></html:option>
										<html:options collection="bmList" property="bmdm"
											labelProperty="bmmc" />
									</html:select>
								<input type="hidden" name="bmdmV" value="<bean:write name="rs" property="bmdm" scope="request"/>"/>
							</td>
							<td align="right">
								<font color="red">*</font>����Ա��
							</td>
							<td align="left">
								<html:select name = "rs" property="zgh"  styleId="zgh" style="width:150px">
										<html:option value=""></html:option>
								</html:select>
							<input type="hidden" name="zghV" value="<bean:write name="rs" property="zgh" scope="request"/>"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								�ڽ�:
							</td>
							<td align="left">
								<html:text name = "rs" property="qj" />
							</td>
							<td align="right">
								�μ��˷�Χ
							</td>
							<td align="left">
								<html:text name = "rs" property="cjr" />
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								����
							</td>
							<td colspan="3">
								<html:text name = "rs" property="zt" style="width:99%" />
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								����:
							</td>
							<td align="left">
								<html:text name = "rs" property="mc" />
							</td>
							<td align="right">
								�ٰ�ʱ��
							</td>
							<td align="left">
								<html:text name= "rs"  property="fssj" styleId="fssj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('fssj','y-mm-dd');" />
							</td>
						</tr>
					</table>
				</fieldset>
				<div class="buttontool">
					<button type="button" class="button2"
						onclick="szsxDataDoSave('szdwfzjy.do?method=saveFdyxgxxOne','zgh');"
						style="width:80px" id="buttonSave">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
			</logic:notEmpty>
			<logic:present name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("�ύ�ɹ���");
    dialogArgumentsQueryChick();
	Close()
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("�ύʧ�ܣ�");
    </script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
