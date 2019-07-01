<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script language="javascript"  src="js/sztzFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/gygl/bjlh/bjlhFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getBjlhGyglDAO.js'></script>
	<script type="text/javascript">	
	function saveCjs(){
		var doType=$("doType").value;
		if(doType == "add"){
			if (confirm("成功添加信息后，\n楼栋，层数，寝室号不可更改,\n请确认录入项无误。")) {
			
			}else{
				return false;
			}
		}
		saveUpdate('/xgxt/bjlh_sjwh.do?method=fykUpdate&doType=save','lddm-cs-cws-qsh');
	}
	
	function chPk(){
		var lddm=$("lddm").value;
		var cs =$("cs").value;
		var qsh=$("qsh").value;
		if(lddm != "" && cs!="" && qsh!=""){
			isCz("lddm||cs||qsh",lddm+cs+qsh);
		}
	}
	</script>
	<body onload="">
		<html:form action="/bjlh_sjwh">
			<input type="hidden" id="doType" name="doType" value="${ doType}"/>
			<input type="hidden" id="realTable" name="realTable" value="${ realTable}"/>
			<input type="hidden" id="url" name="url" value="${ url}"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：<bean:write name="title" />
				</div>
			</div>
			<table class="tbstyle" border="0" id="rsTable" align="center"
				style="width: 100%">
				<thead>
					<tr>
						<td colspan="4" align="center">
							房源库信息
						</td>
					</tr>
				</thead>
				<tr style="height: 23px">
					<td align="right" width="20%">
						校区：
					</td>
					<td align="left" width="30%">
						<logic:equal name="doType" value="add">
						<html:select name="rs" property="xqdm" style="" styleId="xqdm" onchange="setLdList()">
							<html:options collection="xqList" property="dm" labelProperty="mc" />
						</html:select>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:hidden name="rs" property="xqdm" styleId="xqdm"/>
							<bean:write name="rs" property="xqmc"/>
						</logic:notEqual>
					</td>
					<td align="right" width="20%">
						分配标记：
					</td>
					<td align="left">
						<html:select name="rs" property="fbbj" style="" styleId="fbbj">
							<html:options collection="blList" property="en" labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						<font color="red">*</font>楼栋：
					</td>
					<td align="left">
						<logic:equal name="doType" value="add">
						<html:select name="rs" property="lddm" style="" styleId="lddm" onchange="setXqList();setCsList();chPk();">
							<html:options collection="ldList" property="dm" labelProperty="mc" />
						</html:select>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:hidden name="rs" property="lddm" styleId="lddm"/>
							<bean:write name="rs" property="ldmc"/>
						</logic:notEqual>
					</td>
					<td align="right">
						<font color="red">*</font>床位数：
					</td>
					<td align="left">
						<html:text name="rs" property="cws" styleId="cws"
						onkeypress="return onlyNum(this,10)" 
						maxlength="10" style="ime-mode:disabled"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						<font color="red">*</font>所属层数：
					</td>
					<td align="left">
						<logic:equal name="doType" value="add">
						<html:select name="rs" property="cs" style="" styleId="cs" onchange="chPk()">
							<html:options collection="csList" property="dm" labelProperty="mc" />
						</html:select>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:hidden name="rs" property="cs" styleId="cs"/>
							第<bean:write name="rs" property="cs"/>层
						</logic:notEqual>
					<td align="right">
						寝室电话：
					</td>
					<td align="left">
						<html:text name="rs" property="qsdh" styleId="qsdh" style="" maxlength="20"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						<font color="red">*</font>寝室号：
					</td>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:text name="rs" property="qsh" styleId="qsh" style=""  maxlength="15" onchange="chPk()"/>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:hidden name="rs" property="qsh" styleId="qsh"/>
							<bean:write name="rs" property="qsh"/>
						</logic:notEqual>
					</td>
					<td align="right">
						收费标准：
					</td>
					<td align="left">
						<html:text name="rs" property="sfbz" styleId="sfbz" style="" maxlength="10"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						备注：<br><font color="red">(限250字)</font>
					</td>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="bz" styleId="bz" rows="5" style="width: 100%" onblur="chLeng(this,250)"/>
					</td>
				</tr>
				<tr bgcolor="EEF4F9" align="center">
					<td colspan="4">
					<logic:notEqual name="doType" value="view">
					<button class="button2" id="buttonSave"
						onclick="saveCjs();"
						style="width: 80px">
						保	存
					</button>
					</logic:notEqual>
					&nbsp;&nbsp;
					<button class="button2" id="buttonClose" onclick="Close();return false;"
						style="width: 80px">
						关	闭
					</button>
					</td>
				</tr>
			</table>
			<div id="tmpdiv1"></div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
						dialogArgumentsQueryChick();
						window.close();
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('操作失败！');
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
