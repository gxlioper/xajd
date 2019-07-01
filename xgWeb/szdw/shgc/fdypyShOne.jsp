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
		<title>思政队伍评优</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
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
		if(title == "001"||title == "辅导员评优"){
			titleName = "001";				
			document.getElementById(titleName+"l").className = "xxk_on_l";
			document.getElementById(titleName+"m").className = "xxk_on_m";
			document.getElementById(titleName+"r").className = "xxk_on_r";
			anotherName = "002";
			document.getElementById(anotherName+"l").className = "xxk_off_l";
			document.getElementById(anotherName+"m").className = "xxk_off_m";
			document.getElementById(anotherName+"r").className = "xxk_off_r";
			document.getElementById("title").value = "辅导员评优";		
		}else{
			titleName = "002";				
			document.getElementById(titleName+"l").className = "xxk_on_l";
			document.getElementById(titleName+"m").className = "xxk_on_m";
			document.getElementById(titleName+"r").className = "xxk_on_r";
			anotherName = "001";
			document.getElementById(anotherName+"l").className = "xxk_off_l";
			document.getElementById(anotherName+"m").className = "xxk_off_m";
			document.getElementById(anotherName+"r").className = "xxk_off_r";
			document.getElementById("title").value = "班主任评优";		
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
	<body onload="check_user();">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/GetFdyList.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/fdypyforShgc" method="post">
		<logic:notEmpty name="rs">
		<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope = "session"/>" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：思想教育-学生工作队伍建设-评优审核-<bean:write name = "title"/>
				</div>
			</div>
			
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name='rs' property="pk" scope="request"/>" />
				<input type="hidden" id="zgh" name="zgh"
					value="<bean:write name='rs' property="zgh" scope="request"/>" />
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
								部门名称：
							</td>
							<td align="left">
								<html:text name='rs' property="bmmc" styleId="bmmc"  readonly="true" />
							</td>
							<td align="right">
								<font color="red">*</font>辅导员：
							</td>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm"  readonly="true" />
							</td>
						</tr>
						<tr>
							<td align="right">
								性别：
							</td>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb"  readonly="true" />
							</td>
							<td align="right">
								联系电话：
							</td>
							<td align="left">
								<html:text name='rs' property="lxdh" styleId="lxdh"  readonly="true" />
							</td>
						</tr>
						<tr>
							<td align="right">
								移动电话：
							</td>
							<td align="left">
								<html:text name='rs' property="yddh" styleId="yddh"  readonly="true" />
							</td>
							<td align="right">
								职务名称：
							</td>
							<td align="left">
								<html:text name='rs' property="zwmc" styleId="zw"  readonly="true" />
							</td>
						</tr>
						<tr>
							<logic:equal name = "title" value="辅导员评优">
							<td align="right">
								所带年级：
							</td>
							<td align="left">
								<html:text name='rs' property="sdnj" styleId="sdnj" readonly="true"/>
							</td>
							</logic:equal>
							<logic:equal name = "title" value="班主任评优">
							<td align="right">
								所带班级：
							</td>
							<td align="left">
								<html:text name='rs' property="sdbj" styleId="sdbj" readonly="true"/>
							</td>
							</logic:equal>
							<td align="right">
								学生人数：
							</td>
							<td align="left">
								<html:text name='rs' property="xsrs" styleId="xsrs" readonly="true"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								申请日期：
							</td>
							<td align="left">
								<html:text name='rs' property="lrrq" styleId="lrrq"  readonly="true" />
							</td>
							<td align="right">
								审核状态：
							</td>
							<td align="left">
									<html:select name='rs' property="xxsh" style="width:90px"
											styleId="xxsh">
											<html:options collection="shztList" property="en"
												labelProperty="cn" />
							</html:select>
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								主要事迹：
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='zysj' styleId="zysj" style="width:99%"
									rows='7' readonly="true"/>
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />意见：
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='xyyj' styleId="xyyj" style="width:99%"
									rows='5' readonly="true"/>
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								学校意见：
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='xxyj' styleId="xxyj" style="width:99%"
									rows='5'/>
							</td>
						</tr>
					</table>
				</fieldset>
				<div class="buttontool">
					<button type="button" class="button2"
						onclick="szsxDataDoSave('fdypyforShgc.do?method=saveFdypySh','zgh');"
						style="width:80px" id="buttonSave">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="window.open('fdypyforShgc.do?method=fdypyReport&pk='+document.getElementById('pk').value)" style="width:80px"
						id="buttonClose">
						打 印 报 表
					</button>
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("提交成功！");
    dialogArgumentsQueryChick();
	Close()
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("提交失败！");
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
