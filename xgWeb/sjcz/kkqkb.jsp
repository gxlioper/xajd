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

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
	function kkgb(){
		var kkkc = document.getElementById("kkkc").value;
		if(kkkc == 2){
		document.getElementById("wzx").style.display = "inline";
		document.getElementById("skqk").style.display = "none";
		document.getElementById("zdl").style.display = "none";
		}else if(kkkc == 1){
		document.getElementById("wzx").style.display = "none";
		document.getElementById("skqk").style.display = "inline";
		document.getElementById("zdl").style.display = "none";
		}else if(kkkc == 3){
		document.getElementById("wzx").style.display = "none";
		document.getElementById("skqk").style.display = "none";
		document.getElementById("zdl").style.display = "inline";
		}else{
		document.getElementById("wzx").style.display = "none";
		document.getElementById("skqk").style.display = "none";
		document.getElementById("zdl").style.display = "none";
		}
	}
</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="checkWinType();">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			<logic:notEmpty name="rs">

				<logic:equal name="rs" property="stuExists" value="no">
					<script>
   					 alert("您输入的学号无效!");
    				</script>
				</logic:equal>
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-nj-xymc-zymc-bjmc" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-nj-xymc-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/sjcz/kkqkb.jsp" />
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4" align="center">
								旷课情况
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right">
							<font color="red">*</font>学号：
						</td>
						<td align="left">
							<html:text name='rs' property="xh" readonly="readonly"
								styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<logic:equal name="doType" value="add">
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu" style="">
								选择
							</button>
							</logic:equal>
						</td>
						<td align="right">
							<font color="red">*</font>年度：
						</td>
						<td align="left">
							<html:select name="rs" property="nd" style="width:90px"
								styleId="nd">
								<html:options collection="xnList" property="nd"
									labelProperty="nd" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							姓名：
						</td>
						<td align="left">
							<html:text name="rs" property="xm" styleId="xm" />
						</td>
						<td align="right">
							<font color="red">*</font>学年：
						</td>
						<logic:present name="isGZCJXY">
							<logic:equal value="yes" name="isGZCJXY">
								<td align="left">
									<html:select name="rs" property="xn" style="width:90px"
										styleId="xn"
										onchange="refreshForm('/xgxt/modiData.do?realTable=kkqkb&chag=true&tableName=view_kkqkxx&doType='+document.forms[0].doType.value+'&xh='+document.forms[0].xh.value+'&pk=xh||kkrq||kkkc&pkValue='+document.forms[0].pkValue.value)">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
							</logic:equal>
						</logic:present>
						<logic:notPresent name="isGZCJXY">
							<td align="left">
								<html:select name="rs" property="xn" style="width:90px"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
						</logic:notPresent>
					</tr>
					<tr>
						<td align="right">
							年级：
						</td>
						<td align="left">
							<html:text name="rs" property="nj" styleId="nj" />
						</td>
						<td align="right">
							学期：
						</td>
						<logic:present name="isGZCJXY">
							<logic:equal value="yes" name="isGZCJXY">
								<td align="left">
									<html:select name="rs" property="xq" style="width:90px"
										styleId="xq"
										onchange="refreshForm('/xgxt/modiData.do?realTable=kkqkb&chag=true&tableName=view_kkqkxx&doType='+document.forms[0].doType.value+'&xh='+document.forms[0].xh.value+'&pk=xh||kkrq||kkkc&pkValue='+document.forms[0].pkValue.value)">
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
							</logic:equal>
						</logic:present>
						<logic:notPresent name="isGZCJXY">
							<td align="left">
								<html:select name="rs" property="xq" style="width:90px"
									styleId="xq">
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
						</logic:notPresent>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							<html:text name="rs" property="xymc" styleId="xymc" />
						</td>
						<td align="right">
							<font color="red">*</font>旷课日期：
						</td>
						<td align="left">
							<html:text name="rs" property="kkrq" readonly="true"
								onclick="return showCalendar('kkrq','y-mm-dd');"
								onblur="dateFormatChg(this)" style="cursor:hand " />
						</td>
					</tr>
					<tr>
						<td align="right">
							专业：
						</td>
						<td align="left">
							<html:text name="rs" property="zymc" styleId="zymc" />
						</td>
						<td align="right">
							<font color="red">*</font>旷课课程：
						</td>
						<td align="left">
							
							<logic:notEqual value="1049701" name="xxdm" scope="session" >
								<html:select  name="rs" property="kkkc" style="width:180px"
									styleId="kkkc" onchange="kkgb();">
									<html:option value=""></html:option>
									<html:options collection="kcList" property="kcdm"
										labelProperty="kcmc" />
								</html:select>
							</logic:notEqual>
							
							<logic:equal value="1049701" name="xxdm" scope="session" >
								<html:text name="rs" property="kkkc" maxlength="15" styleId="kkkc"></html:text>
							</logic:equal>
						</td>
					</tr>
					<tr>
						<td align="right">
							班级：
						</td>
						<td align="left">
							<html:text name="rs" property="bjmc" styleId="bjmc" />
						</td>
						<td align="right">
							旷课节数：
						</td>
						<td align="left">
							<input type="text" width="100%" id="kkjs" name="kkjs" onkeyup="value=value.replace(/[^\d]/g,'')"
								value="${rs.kkjs}" maxlength="2"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							处理结果：
						</td>
						<td align="left" colspan="3">
							<input type="text" id="cljg" name="cljg" value="${rs.cljg}"
								size="50" />
						</td>
					</tr>
					<tr id="wzx" style="display: none;">
						<td align="right">
							晚自修：
						</td>
						<td align="left" colspan="3">
							<input type="text" id="wzx" name="wzx" value="${rs.wzx}" size="50" />
						</td>
					</tr>
					<tr id="skqk" style="display: none;">
						<td align="right">
							上课情况：
						</td>
						<td align="left" colspan="3">
							<input type="text" id="skqk" name="skqk" value="${rs.skqk}"
								size="50" />
						</td>
					</tr>
					<tr id="zdl" style="display: none">
						<td align="right">
							早锻炼：
						</td>
						<td align="left" colspan="3">
							<input type="text" id="zdl" name="zdl" value="${rs.zdl}"
								size="50" />
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button type="button" class="button2"
						onclick="if(checkXnNd('xn','nd'))dataDoSave('xh-kkrq-kkkc');"
						style="width: 80px" id="buttonSave">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width: 80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
			</logic:notEmpty>
		</html:form>
	</body>
	<logic:equal value="true" name="isSuccess">
			<script>
				alert('操作成功');
				if (window.dialogArguments) {
					close();
					if(window.dialogArguments.document.getElementById("isPage")){
						window.dialogArguments.document.getElementById("isPage").value="yes";
					}
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
		</logic:equal>
</html>
