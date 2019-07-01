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
		<title>˼����������</title>
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
	function changePage(title){
		var titleName,anotherName; 
		if(title == "001"||title == "����Ա����"){
			titleName = "001";				
			document.getElementById(titleName+"l").className = "xxk_on_l";
			document.getElementById(titleName+"m").className = "xxk_on_m";
			document.getElementById(titleName+"r").className = "xxk_on_r";
			anotherName = "002";
			document.getElementById(anotherName+"l").className = "xxk_off_l";
			document.getElementById(anotherName+"m").className = "xxk_off_m";
			document.getElementById(anotherName+"r").className = "xxk_off_r";
			document.getElementById("title").value = "����Ա����";		
		}else{
			titleName = "002";				
			document.getElementById(titleName+"l").className = "xxk_on_l";
			document.getElementById(titleName+"m").className = "xxk_on_m";
			document.getElementById(titleName+"r").className = "xxk_on_r";
			anotherName = "001";
			document.getElementById(anotherName+"l").className = "xxk_off_l";
			document.getElementById(anotherName+"m").className = "xxk_off_m";
			document.getElementById(anotherName+"r").className = "xxk_off_r";
			document.getElementById("title").value = "����������";		
		}
		if(title == "001"|| title == "002"){
			refreshForm('/xgxt/fdypyforShgc.do?method=fdypysq');
		}
	}
	function check_user()
	{
		var user=document.all['userType'].value;
		if("xy"==user)
		{
			document.getElementById('bmdm').disabled=true;
		}
		else if("xx"==user)
		{
			document.getElementById('bmdm').disabled=false;
		}
	}
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="check_user();changePage('<bean:write name = "title"/>');getFdyList();">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/GetFdyList.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/data_search" method="post">
		<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope = "session"/>" />
			<div class="xxk">
				<ul>
					<li id="001l"
						class="xxk_off_l"></li>
					<li id="001m"
						onclick="changePage('001')" class="xxk_off_m">
						&nbsp;
						����Ա����
						&nbsp;
					</li>
					<li id="001r"
						class="xxk_off_r"></li>
				</ul>
				<ul>
					<li id="002l"
						class="xxk_off_l"></li>
					<li id="002m"
						onclick="changePage('002')" class="xxk_off_m">
						&nbsp;
						����������
					</li>
					<li id="002r"
						class="xxk_off_r"></li>
				</ul>
  			</div>
			<logic:notEmpty name="rs">
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name='rs' property="pk" scope="request"/>" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="title" name="title" value="<bean:write name = "title"/>" />
				<fieldset>
					<legend>
						<title><bean:write name = "title"/></title>
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
							</td>
							<td align="right">
								<font color="red">*</font>����Ա��
							</td>
							<td align="left">
								<html:select name = "rs" property="zgh"  styleId="zgh" style="width:150px" onchange="refreshForm('/xgxt/fdypyforShgc.do?method=fdypysq');">
										<html:option value=""></html:option>
								</html:select>
							<input type="hidden" name="zghV" value="<bean:write name="rs" property="zgh" scope="request"/>"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								�Ա�
							</td>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb"  readonly="true" />
							</td>
							<td align="right">
								��ϵ�绰��
							</td>
							<td align="left">
								<html:text name='rs' property="lxdh" styleId="lxdh"  readonly="true" />
							</td>
						</tr>
						<tr>
							<td align="right">
								�ƶ��绰��
							</td>
							<td align="left">
								<html:text name='rs' property="yddh" styleId="yddh"  readonly="true" />
							</td>
							<td align="right">
								ְ�����ƣ�
							</td>
							<td align="left">
								<html:text name='rs' property="zwmc" styleId="zw"  readonly="true" />
							</td>
						</tr>
						<tr>
							<logic:equal name = "title" value="����Ա����">
							<td align="right">
								�����꼶��
							</td>
							<td align="left">
								<html:text name='rs' property="sdnj" styleId="sdnj" />
							</td>
							</logic:equal>
							<logic:equal name = "title" value="����������">
							<td align="right">
								�����༶��
							</td>
							<td align="left">
								<html:text name='rs' property="sdbj" styleId="sdbj" />
							</td>
							</logic:equal>
							<td align="right">
								ѧ��������
							</td>
							<td align="left">
								<html:text name='rs' property="xsrs" styleId="xsrs" />
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								��Ҫ�¼���
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='zysj' styleId="zysj" style="width:99%"
									rows='7' />
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />�����
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='xyyj' styleId="xyyj" style="width:99%"
									rows='5' />
							</td>
						</tr>
					</table>
				</fieldset>
				<div class="buttontool">
					<button type="button" class="button2"
						onclick="szsxDataDoSave('fdypyforShgc.do?method=saveFdypysq','zgh');"
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
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("�ύ�ɹ���");
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("�ύʧ�ܣ�");
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
