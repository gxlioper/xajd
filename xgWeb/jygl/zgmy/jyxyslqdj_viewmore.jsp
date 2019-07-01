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
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
	function saveJyxy(){
		var url = "viewJyxyLqdjInfo.do?act=save&lqqk=";
		var lqqk = document.getElementById("lqqk").value;
		if(document.getElementById("xh").value == ""){
			alert("��ȷ��ѧ�ŷǿգ�");
			return false;
		}
		if(document.getElementById("jyxybh").value == ""){
			alert("��ȷ�Ͼ�ҵЭ�����ŷǿգ�");
			return false;
		}
		showTips('���������У���ȴ�......');
		refreshForm(url+lqqk);
	}
	</script>
	<body>
		<html:form action="/data_search" method="post">
		<input type="hidden" id="url" name="url" value="/jygl/zgmy/jyxyslqdj_viewmore.jsp" />
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-nj-zymc-bjmc" />
		<fieldset>
			<legend>
				��ҵЭ������ȡ�Ǽ���ϸ��Ϣ
			</legend>
			<table width="100%" id="rsT" class="tbstyle">
				<logic:notPresent name="type">
				<tr style="height:22px">
					<td align="right" width="20%">
						ѧ��:
					</td>
					<td align="left" width="30%">
						<bean:write name="rs" property="xh" />
					</td>
					<td align="right" width="20%">
						������
					</td>
					<td align="left" width="30%">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />:
					</td>
					<td align="left">
						<bean:write name="rs" property="xymc" />
					</td>
					<td align="right">
						רҵ��
					</td>
					<td align="left">
						<bean:write name="rs" property="zymc" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�༶:
					</td>
					<td align="left">
						<bean:write name="rs" property="bjmc" />
					</td>
					<td align="right">
						�꼶��
					</td>
					<td align="left">
						<bean:write name="rs" property="nj" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						��ҵЭ������:
					</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="jyxybh" />
					</td>
				</tr>
				<logic:equal name="xxdm" value="10355">
					<tr style="height:22px">
					<td align="right">
						��ע��
					</td>
					<td align="left" colspan="3">
						<html:textarea name="rs" property="newremark" rows="15"
							style="width:100%" readonly="true" />
					</td>
					</tr>
				</logic:equal>				
				<logic:notEqual name="xxdm" value="10355">
					<tr style="height:22px">
					<td align="right">
						��ע��
					</td>
					<td align="left" colspan="3">
						<html:textarea name="rs" property="remark" rows="15"
							style="width:100%" readonly="true" />
					</td>
					</tr>
				</logic:notEqual>
				</logic:notPresent>
				<logic:present name="type">
					<logic:equal name="type" value="add">
					<tr style="height:22px">
					<td align="right" width="20%">
						ѧ��:
					</td>
					<td align="left" width="30%">
						<html:text name='rs' property="xh" styleId="xh" />
						<logic:notEqual value="stu" name="userType" scope="session">
						<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
							class="btn_01" id="buttonFindStu">
							ѡ��
						</button>
						</logic:notEqual>
					</td>
					<td align="right" width="20%">
						������
					</td>
					<td align="left" width="30%">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />:
					</td>
					<td align="left">
						<bean:write name="rs" property="xymc" />
					</td>
					<td align="right">
						רҵ��
					</td>
					<td align="left">
						<bean:write name="rs" property="zymc" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�༶:
					</td>
					<td align="left">
						<bean:write name="rs" property="bjmc" />
					</td>
					<td align="right">
						�꼶��
					</td>
					<td align="left">
						<bean:write name="rs" property="nj" />
					</td>
				</tr>
				<logic:equal name="update" value="no">
				<tr style="height:22px">
					<td align="right">
						��ҵЭ������:
					</td>
					<td align="left">
						<html:text name='rs' property="jyxybh" styleId="jyxybh" 
						onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="20"/>
					</td>
					<td align="right">
						��ȡ���:
					</td>
					<td align="left">
						<input type="text" id="lqqk" value="δ��ȡ" readOnly="true">
					</td>
				</tr>
				</logic:equal>
				<logic:equal name="update" value="yes">
				<tr style="height:22px">
					<td align="right">
						��ҵЭ������:
					</td>
					<td align="left">
						<html:text name='rs' property="jyxybh" styleId="jyxybh" 
						onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="20"/>
					</td>
					<td align="right">
						��ȡ���:
					</td>
					<td align="left">
						<html:select name='rs' property="lqqk" style="width:150px">
							<html:option value=""></html:option>
							<html:option value="δ��ȡ">δ��ȡ</html:option>
							<html:option value="����ȡ">����ȡ</html:option>
						</html:select>
					</td>
				</tr>
				</logic:equal>
				<tr style="height:22px">
					<td align="right">
						��ע��
					</td>
					<td align="left" colspan="3">
						<html:textarea name="rs" property="remark" rows="15"
							style="width:100%"/>
					</td>
				</tr>
				</logic:equal>
				</logic:present>
			</table>
			<logic:present name="type">
				<logic:equal name="type" value="add">
					<div class="buttontool">
						<button class="button2" style="width:80px" id="buttonSave"
							onclick="saveJyxy()">
							�� ��
						</button>	
						&nbsp;&nbsp;
						<button class="button2" onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							�� ��
						</button>
					</div>
				</logic:equal>
			</logic:present>
		</fieldset>
		</html:form>
	</body>
</html>
<logic:present name="inserted">
	<logic:equal name="inserted" value="ok">
		<script>
    		alert("��Ϣ��ӳɹ���");
    		dialogArgumentsQueryChick();
			Close();
   		</script>
	</logic:equal>
	<logic:equal name="inserted" value="no">
		<script>
    		alert("��Ϣ���ʧ�ܣ�");
   		</script>
	</logic:equal>
</logic:present>
