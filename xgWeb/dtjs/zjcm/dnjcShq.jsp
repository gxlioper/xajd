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
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/sxjyFunction.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
	function saveDnjc(url,pkFields){
	var eles = pkFields.split("-");
	for (i = 0; i < eles.length; i++) {
		if (document.getElementById(eles[i])){
			if(document.getElementById(eles[i]).value == "") {
				alert("����ȷ����������ݣ�");
				return false;
			}
		}
	}

	if($("bz").value.length>200){
		alert("��ע�������ܴ���200�֣�");
		return false;
	}
	showTips('���������У���ȴ�......');
	document.forms[0].action = url;
	document.forms[0].submit();
	
	}
	</script>
	<body onload="checkWinType();">
		<html:form action="/dtjs_zjcm" method="post">
			<input type="hidden" id="method" name="method" value="swclShq" />
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope = "session"/>" />
			<input type="hidden" id="url" name="url" value="/dtjs/zjcm/dnjcShq.jsp" />
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-nj-zymc-bjmc" />
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�<bean:write name = "title" />
				</div>
			</div>
			<fieldset>
				<legend>
					��������
				</legend>
				<table width="100%" class="tbstyle">
					<tr>
						<td>
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left">
							<html:text property="xh" styleId="xh" maxlength="10" name="rs"/>
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</td>
						<td>
							&nbsp;&nbsp;������
						</td>
						<td align="left">
							<html:text property="xm" styleId="xm" name="rs" readonly="true" />
						</td>

					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;�Ա�:
						</td>
						<td>
							<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
						</td>
						<td>
							&nbsp;&nbsp;�꼶��
						</td>
						<td align="left">
							<html:text name='rs' property="nj" styleId="nj" readonly="true"/>
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left">
							<html:text name='rs' property="xymc" styleId="xy" readonly="true"/>
						</td>
						<td>
							&nbsp;&nbsp;רҵ��
						</td>
						<td align="left">
							<html:text name='rs' property="zymc" styleId="zy" readonly="true"/>
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;�༶��
						</td>
						<td align="left">
							<html:text name='rs' property="bjmc" styleId="bj" readonly="true"/>
						</td>
						<td>
							&nbsp;&nbsp;����ʱ�䣺
						</td>
						<td align="left">
							<html:text name='rs' property="jcsj" styleId="jcsj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('jcsj','y-mm-dd');" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;�������ͣ�
						</td>
						<td>
							<html:select property="jclx"  styleId="lx" name="rs">
								<html:option value=""></html:option>
								<html:options collection="lxList" property="lxdm"
									labelProperty="lxmc" />
							</html:select>
						</td>
						<td>
							&nbsp;&nbsp;�������ɣ�
						</td>
						<td>
							<html:select property="jcly"  styleId="ly" name="rs">
								<html:option value=""></html:option>
								<html:options collection="lyList" property="lydm"
									labelProperty="lymc" />
							</html:select>	
						</td>
						
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;��ע��<br>
							<font color="blue">��200���ַ�</font>
						</td>
						<td colspan="4">
							<html:textarea property="bz" styleId="bz" style="width:430px" rows='10'/>
						</td>
					</tr>
				</table>
			</fieldset>
				<div class="buttontool">
					<button type="button" class="button2"
						onclick="saveDnjc('dtjs_zjcm.do?method=saveDnjcshq','xh-xm');"
						style="width:80px" id="buttonSave">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
			<logic:present name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    					alert("�ύ�ɹ���");
    					dialogArgumentsQueryChick();
						Close();
   					 </script>
				</logic:equal>
			<logic:equal name="inserted" value="no">
					<script>
    					alert("�ύʧ�ܣ�");
   					 </script>
				</logic:equal>
			</logic:present>
			<logic:present name="member">
			<logic:equal name="member" value="no">
					<script>
    					alert("��ѧ�������ڣ���ȷ�������ѧ������");
   					 </script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
