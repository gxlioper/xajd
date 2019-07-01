<%@ page language="java" contentType="text/html; charset=gb2312"%>

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
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript">
	function addDisDormList(){
		var oracleListIndex = document.forms[0].oracleList.selectedIndex;
	    var addOracleV = document.forms[0].oracleList.options[oracleListIndex].value;
		var addOracleT = document.forms[0].oracleList.options[oracleListIndex].text;
		document.forms[0].sql.options[document.forms[0].sql.options.length] = new Option(addOracleV, addOracleT);
		document.forms[0].oracleList.options[oracleListIndex]=null;
	}
	
	function delDisDormList(){
		 var sqlIndex = document.forms[0].sql.selectedIndex;
       	 var sqlV = document.forms[0].sql.options[sqlIndex].value;
	   	 var sqlT = document.forms[0].sql.options[sqlIndex].text;
	   	 document.forms[0].oracleList.options[document.forms[0].oracleList.options.length] = new Option(sqlV, sqlT);
		 //document.forms[0].oracleList.options[document.forms[0].oracleList.options.length] = new Option(sqlV.substring(sqlV.indexOf("/")+1,sqlV.length), sqlT);
		 //.substring(sqlT.indexOf("/")+1,sqlT.length));
		 document.forms[0].sql.options[sqlIndex]=null;
	}
	
	function saveConditionSql(){	
		document.forms[0].conditionSqlText.value = "";
		document.forms[0].conditionSqlValue.value = "";
		for (i = 0; i < document.forms[0].sql.options.length; i++) {
			document.forms[0].conditionSqlText.value = document.forms[0].conditionSqlText.value + document.forms[0].sql.options[i].text;
			document.forms[0].conditionSqlValue.value = document.forms[0].conditionSqlValue.value + document.forms[0].sql.options[i].value;			
			if (i != document.forms[0].sql.options.length - 1) {
				document.forms[0].conditionSqlText.value = document.forms[0].conditionSqlText.value + "!!SplitSignOne!!";
				document.forms[0].conditionSqlValue.value = document.forms[0].conditionSqlValue.value + "!!SplitSignOne!!";
			}
		}
	}
	
function addAllPower() {
	var sqlPower = new Array();
	var tmp;
	j = document.forms[0].oracleList.options.length;
	for (i = 0; i < document.forms[0].sql.options.length; i++) {
		sqlPower[sqlPower.length] = document.forms[0].sql.options[i].value.substring(1, document.forms[0].sql.options[i].value.length);
	}
	for (i = 0; i < j; i++) {
		tmp = document.forms[0].oracleList.options[i].value;
		if (!inArray(tmp, sqlPower)) {
			addById(tmp);
		}
	}
	var length = document.forms[0].oracleList.options.length;
	for (k = 0; k < length; k++) {
		document.forms[0].oracleList.options[0] = null;
	}
}

function addById(powerId){
	var tmp_txt;
	var tmp_value;
	for (k = 0; k < document.forms[0].oracleList.options.length; k++) {
		if (document.forms[0].oracleList.options[k].value == powerId) {
			tmp_txt = document.forms[0].oracleList.options[k].text;
			tmp_value = document.forms[0].oracleList.options[k].value;
			document.forms[0].sql.options[document.forms[0].sql.options.length] = new Option(tmp_txt, tmp_value);					
		}
	}
}

function delAllPower() {
	var sqlPower = new Array();
	var tmp;
	j = document.forms[0].sql.options.length;
	for (i = 0; i < document.forms[0].sql.options.length; i++) {
		sqlPower[sqlPower.length] = document.forms[0].sql.options[i].value.substring(1, document.forms[0].sql.options[i].value.length);
	}
	for (i = 0; i < j; i++) {
		tmp = document.forms[0].sql.options[i].value;
		if (!inArray(tmp, sqlPower)) {
			delById(tmp);
		}
	}
	
	document.forms[0].sql.options.length = 0;
}

function delById(powerId){
	var tmp_txt;
	var tmp_value;
	for (k = 0; k < document.forms[0].sql.options.length; k++) {
		//alert(powerId);
		//alert(document.forms[0].sql.options[k].value);
		if (document.forms[0].sql.options[k].value == powerId) {
			tmp_txt = document.forms[0].sql.options[k].text;
			tmp_value = document.forms[0].sql.options[k].value;
			document.forms[0].oracleList.options[document.forms[0].oracleList.options.length] = new Option(tmp_txt, tmp_value);					
		}
	}
}
    </script>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<base target="_self" />
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="check_user();">
		<html:form action="/leave_check" method="post">	
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType"/>"/>		
			<input type="hidden" id="conditionSqlText" name="conditionSqlText">
			<input type="hidden" id="conditionSqlValue" name="conditionSqlValue">
			
			<input type="hidden" id="conditionOrclText" name="conditionOrclText">
			<input type="hidden" id="conditionOrclValue" name="conditionOrclValue">
			
           <input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生离校 - 离校审核 - 审核
				</div>
			</div>
			<input type="hidden" name="mappingItems" value="" />
			<fieldset>
				<legend>
					离校审核
				</legend>
				<table width="100%" align="center" class="tbstyle">
					<tr align="center">
						<td width="" align="left" colspan="3">
							<bean:message key="lable.xsgzyxpzxy" />：
							<logic:notPresent name="xyList">
							<html:select property="xydm" style="width:150px" styleId="xy" disabled="disabled"
								onchange="refreshForm('/xgxt/leave_check.do?doType=search')">
								<html:option value=""></html:option>								
							</html:select>
							</logic:notPresent>
							<logic:present name="xyList">
							<html:select property="xydm" style="width:150px" styleId="xy"
								onchange="refreshForm('/xgxt/leave_check.do?doType=search')">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							</logic:present>
							&nbsp;&nbsp;&nbsp;年级：
							<logic:notPresent name="njList">
							<html:select property="nj" style="width:60px" disabled="disabled">
								<html:option value=""></html:option>
								
							</html:select>
							</logic:notPresent>
							<logic:present name="njList">
							<html:select property="nj" style="width:60px"
								onchange="saveConditionSql();refreshForm('/xgxt/leave_check.do?doType=search')">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
							</logic:present>
							&nbsp;&nbsp;&nbsp;班级：
							<html:select property="bjdm" style="width:180px" styleId="bj"
								onchange="saveConditionSql();refreshForm('/xgxt/leave_check.do?doType=search')">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
							&nbsp;&nbsp;&nbsp;学号：
							<html:text property="xh" style="width:120px"/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" style="" id="search_go"
								onclick="refreshForm('/xgxt/leave_check.do?doType=search')">
								查询
							</button>
						</td>
					</tr>

					<tr align="center" bgcolor="#D0E0EE">
						<td align="center" width="300px">
							在校学生
						</td>
						<td>
						</td>
						<td align="center" width="300px">
							批准离校学生
						</td>
					</tr>
					<tr align="center">
						<td valign="top">
							<bean:message key="lable.xsgzyxpzxy" />/学号/姓名/年级/班级
							<br/>
							<html:select property="oracleItem" style="width:100%;" size="27"
								styleId="oracleList" ondblclick="addDisDormList()">
								<html:options collection="zxList" labelProperty="jbxx"
									property="jbxx" />
							</html:select>
						</td>
						<td width="60px" valign="middle" align="center">
							<span id="btn1">
								<button class="button2" onclick="addAllPower()"
									style="width:60px">
									选择
								</button>
								&nbsp;&nbsp;
								<br />
								<br />
								<br />
								<br />
								<br />
								<button class="button2" onclick="delAllPower()"
									style="width:60px;">
									&lt;&lt;
								</button>
							</span>
						</td>
						<td valign="top">
							<bean:message key="lable.xsgzyxpzxy" />/学号/姓名/年级/班级
							<br/>
							<html:select property="sql" style="width:100%;" size="26"
								styleId="sql" ondblclick="delDisDormList()">
								<html:options collection="lxList" labelProperty="jbxx"
									property="jbxx" />
							</html:select>
						</td>
					</tr>
					<logic:equal value="yes" name="writeAble" scope="request">
						<tr>
							<td align="center" colspan="3">
								<input class="button2" type="button" name="button1"
									style="width:100px" value="确 定"
									onclick="if(document.forms[0].sql.options.length>0){saveConditionSql();refreshForm('/xgxt/leave_check.do?doType=save')}else{alert('批准离校列表不能为空!');return false;}" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" class="button2" name="button2"
									style="width:100px" value="重 置"
									onclick="refreshForm('/xgxt/leave_check.do?doType=reset')" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" class="button2" name="button2"
									style="width:100px" value="重 新 审 批"
									onclick="if(confirm('确定要重新审批吗?')){saveConditionSql();refreshForm('/xgxt/leave_check.do?doType=delete');}else return false;" />
							</td>
						</tr>
					</logic:equal>
				</table>
			</fieldset>
			<div id="tmpdiv"></div>
		</html:form>
		<logic:notEmpty name="doFlag">
			<logic:equal name="doFlag" value="ok">
				<script>
					alert("操作成功!");
				</script> 
			</logic:equal>
			<logic:equal name="doFlag" value="no">
				<script>
					alert("操作失败!");
				</script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
