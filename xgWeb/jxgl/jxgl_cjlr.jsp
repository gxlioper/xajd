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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
		//begin 骆嘉伟 2009/3/30
	function chkInputJx(obj){
		var num = obj.value;
		if(num != ""){
		if(num.match(/^\d+\.{0,1}\d{0,3}$/)==null){
				alert("必需为数字！");
				document.getElementById("isErr").value = "1";
				return false;
			}
		if(num>100){
				alert("军训成绩不能大于100！");
				document.getElementById("isErr").value = "1";
				return false;
			}
		}
		document.getElementById("isErr").value = "0";	
		return true;
	}
	function saveCj(){
		showTips('处理数据中，请等待......');
		if(document.getElementById("isErr").value == "1"){
			alert("请输入正确的成绩！");
		}else{
		cjSave('ArmyAchievementSave.do');
		}
	}
</script>
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/jxgl.js"></script>
	<script type="text/javascript" src="js/jxglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getJxLdjzList.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
	<body  <logic:notPresent name="cjlrType2"> onload="getJxcjbl();"</logic:notPresent> >
		<html:form action="/ArmyIntoAchievement" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：军训管理 - 军训考核 - 军训成绩录入
				</div>
			</div>
			<input type="hidden" id="pk" name="pk" value="" />
			<input type="hidden" name="ldbhV" id="ldbhV" />
			<input type="hidden" id="isErr" name="isErr" value="0" />
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" id="tableName" name="tableName" value="${tableName }" />
			<input type="hidden" id="title" name="title" value="${title }" />
			<input type="hidden" id="writeAble" name="writeAble" value="${writeAble }" />
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="isFdy" scope="session"/>"/>
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>"/>
			<input type="hidden" id="isBzr" name="isBzr" value="<bean:write name="bzrQx" scope="session"/>" />
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<fieldset>
				<legend>
					查询条件
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								年级：
								<html:select property="nj" style="width:170px" onchange="initZyList();initBjList()">
									<html:option value="" />
									<html:options collection="njList" property="nj" labelProperty="nj" />
								</html:select>
								&nbsp;年度：
								<html:select property="nd" style="width:90px" onchange="">
									<html:option value=""></html:option>
									<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select>
								&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" style="width:170px" styleId="xy" onchange="initZyList();initBjList()">
									<html:option value="" />
									<html:options collection="xyList" property="xydm" labelProperty="xymc" />
								</html:select>
							</td>
							<td rowspan="4" width="10" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/ArmyIntoAchievement.do')">
									查 询
								</button>
							</td>
						</tr>
						<tr>
							<td>
								专业：
								<html:select property="zydm" style="width:170px" styleId="zy" onchange="initBjList()">
									<html:option value="" />
									<html:options collection="zyList" property="zydm" labelProperty="zymc" />
								</html:select>
								&nbsp;班级：
								<html:select property="bjdm" style="width:170px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
								</html:select>
								<logic:notPresent name="cjlrType2">
								&nbsp;所属连队：
								<html:select property="ldbh"  onchange="">
									<html:option value=""></html:option>
									<html:options collection="ldList" property="lddm" labelProperty="ldmc" />
								</html:select>
								</logic:notPresent>
								<logic:present name="cjlrType2">
								&nbsp;成绩比例:<font color="red">训练成绩
								<html:text property="xlcjbl" styleId="xlcjbl" style="width:35px" maxlength="3" value="60" onkeyup="chkInput(this,event)"/>%
								&nbsp;考试成绩
								<html:text property="kscjbl" styleId="kscjbl" style="width:35px" maxlength="3" value="40" onkeyup="chkInput(this,event)"/>%</font>
								</logic:present>
							</td>
						</tr>
						<logic:notPresent name="cjlrType2">
						<tr>
							<td>
								学号：
								<html:text property="xh" style="width:120px" />
								&nbsp;&nbsp;&nbsp;&nbsp;姓名：
								<html:text property="xm" style="width:100px" />
								&nbsp;&nbsp;性别：
								<html:select property="xb" style="width:90px"
									styleId="xb" onchange="">
									<html:option value=""></html:option>
									<html:option value="男">男</html:option>
									<html:option value="女">女</html:option>
								</html:select>
								<!-- begin 骆嘉伟 2009/3/30 -->
								<logic:present name="jxgl_zjcm">
								&nbsp;成绩比例:<font color="red">军训实践
								<html:text property="xlcjbl" styleId="xlcjbl" style="width:35px" maxlength="3" value="" onkeyup="chkInput(this,event)" disabled="true"/>%
								&nbsp;军训理论
								<html:text property="kscjbl" styleId="kscjbl" style="width:35px" maxlength="3" value="" onkeyup="chkInput(this,event)" disabled="true"/>%</font>
								</logic:present>
								<!-- end 骆嘉伟 2009/3/30 -->
							</td>
						</tr>
						</logic:notPresent>
						<logic:present name="cjlrType2">
						<tr>
							<td>
								&nbsp;查询成绩总值为:
								<input type = "test" name = "cj" maxlength="3" style="width:35px"/>
								&nbsp;&nbsp;&nbsp;&nbsp;查询成绩总值在
								<input type = "test" maxlength="3" name = "qszz" style="width:35px" onkeyup="chkInput(this,event)"/>&nbsp;到&nbsp;<input type = "test" maxlength="3" name = "jszz" style="width:35px" onkeyup="chkInput(this,event)"/>之间
							</td>
						</tr>
						</logic:present>
					</thead>
				</table>
			</fieldset>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					未找到任何记录！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<%-- <font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font> --%>
					</legend>
					<table width="99%" id="rsTable" class="tbstyle" align="center">
						<thead>
							<tr align="center" style="cursor:hand">
								<td align="center">学号</td>
								<td align="center">姓名</td>
								<td align="center">年度</td>
								<td align="center">性别</td>
								<td align="center">年级</td>
								<td align="center">班级</td>
								<logic:present name="cjlrType2">
								<td align="center" style="width: 3%">训练成绩</td>
								<td align="center" style="width: 3%">考试成绩</td>
								</logic:present>
								<!-- begin 骆嘉伟 2009/3/30 -->
								<logic:present name="jxgl_zjcm">
								<td align="center" style="width: 3%">军训实践</td>
								<td align="center" style="width: 3%">军训理论</td>
								</logic:present>
								<!-- end 骆嘉伟 2009/3/30 -->
								<td align="center" style="width: 3%">成绩</td>
							</tr>
						</thead>
						<tbody id="rsTables">
						<logic:iterate name="rs" id="s">
						     <tr>
								<td align="left"><bean:write name="s" property="xhArray"/><html:hidden name="s" property="xhArray"/></td>
								<td align="left"><bean:write name="s" property="xmArray"/><html:hidden name="s" property="xmArray"/></td>
								<td align="left"><bean:write name="s" property="ndArray"/><html:hidden name="s" property="ndArray"/></td>
								<td align="left"><bean:write name="s" property="xb"/></td>
								<td align="left"><bean:write name="s" property="nj"/></td>
								<td align="left"><bean:write name="s" property="bjmc"/></td>
								<logic:present name="cjlrType2">
								<td align="left"><html:text styleId="xlcjtext" name="s" property="xlcjArray" style="width: 50px" size="8" maxlength="5" onblur="ResultCount()"/></td>
								<td align="left"><html:text styleId="kscjtext" name="s" property="kscjArray" style="width: 50px" size="8" maxlength="5" onblur="ResultCount()"/></td>
								</logic:present>
								<!-- begin 骆嘉伟 2009/3/30 -->
								<logic:present name="jxgl_zjcm">
								<td align="left"><html:text styleId="xlcjtext" name="s" property="xlcjArray" style="width: 50px" size="8" maxlength="5" onblur="if(chkInputJx(this)){ResultCount();}"/></td>
								<td align="left"><html:text styleId="kscjtext" name="s" property="kscjArray" style="width: 50px" size="8" maxlength="5" onblur="if(chkInputJx(this)){ResultCount();}"/></td>
								</logic:present>
								<!-- end 骆嘉伟 2009/3/30 -->
								<logic:equal value="10690" name="xxdm" scope = "session">
								<td align="left"><html:select name="s" property="cjArray"  style="width: 50px">
									<html:option value=""></html:option>
									<html:option value="优秀">优秀</html:option>
									<html:option value="合格">合格</html:option>
									<html:option value="不合格">不合格</html:option>
									</html:select>
								</td>
								</logic:equal>
								<logic:notEqual value="10690" name="xxdm" scope = "session">
								<td align="left"><html:text styleId="text" name="s" property="cjArray" style="width: 50px" size="8" maxlength="5"/></td>
								</logic:notEqual>
							 </tr>
						</logic:iterate>
						</tbody>
					</table>
					<p>&nbsp;</p>
				</fieldset>
			</logic:notEmpty>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" />
			</div>
			<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
					<logic:equal value="yes" name="writeAble">
					<logic:equal value="10690" name="xxdm" scope = "session">
					<button type="button" class="button2"
						onclick="refreshForm('ArmyAchievementSave.do');"
						style="width:80px">
						保 存
					</button>
					</logic:equal>
					<!-- begin 骆嘉伟 2009/3/30 -->
					<logic:equal value="11647" name="xxdm" scope = "session">
					<button type="button" class="button2"
						onclick="saveCj();"
						style="width:80px">
						保 存
					</button>
					</logic:equal>
					<!-- end 骆嘉伟 2009/3/30 -->
					<logic:notEqual value="11647" name="xxdm" scope = "session">
					<logic:notEqual value="10690" name="xxdm" scope = "session">
					<button type="button" class="button2"
						onclick="cjSave('ArmyAchievementSave.do');"
						style="width:80px">
						保 存
					</button>
					</logic:notEqual>
					</logic:notEqual>
					&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:equal>
					<button type="button" class="button2"
						onclick="impAndChkData();"
						style="width:80px">
						导入数据
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="dataExport()" style="width:80px">
						导出数据
					</button>
			</div>
		</html:form>
		<logic:equal value="ok" name="result">
			<script>
				alert("保存成功！");
				document.getElementById("search_go").click();
			</script>
		</logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
