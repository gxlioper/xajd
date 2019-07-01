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
	function saveYjs(){
		var doType=$("doType").value;
		if(doType == "add"){
			if (confirm("学号保存后不可修改，请确认")) {
			
			}else{
				return false;
			}
		}
		saveUpdate('/xgxt/bjlh_sjwh.do?method=yjsUpdate&doType=save','xh-xb-nj-xm-xydm-zydm-bjdm-sfzh-rxrq-xz');
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
							研究生信息
						</td>
					</tr>
				</thead>
				<tr style="height: 23px">
					<td align="right">
						<font color="red">*</font>学号：
					</td>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:text name='rs' property="fqrzxh" styleId="fqrzxh" maxlength="20" onchange="isCz('fqrzxh||lx',this.value+'研究生')"/>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:text name='rs' property="fqrzxh" styleId="fqrzxh" maxlength="20" readonly="true"/>
						</logic:notEqual>
					</td>
					<td align="right">
						<font color="red">*</font>年级：
					</td>
					<td align="left">
						<html:select name="rs" property="nj" style="" styleId="nj" onchange="setZyList('研究生');setBjList('研究生')">
							<html:options collection="njList" property="dm" labelProperty="mc" />
						</html:select>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						<font color="red">*</font>姓名：
					</td>
					<td align="left">
						<html:text name='rs' property="xm" styleId="xm" maxlength="20"/>
					</td>
					<td align="right">
						<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						<html:select name="rs" property="xydm" style="" styleId="xydm" onchange="setZyList('研究生');setBjList('研究生')">
							<html:options collection="xyList" property="dm" labelProperty="mc" />
						</html:select>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						<font color="red">*</font>性别：
					</td>
					<td align="left">
						<html:select name="rs" property="xb" style="" styleId="xb">
							<html:option value=""></html:option>
							<html:options collection="xbList" property="en" labelProperty="cn" />
						</html:select>
					</td>
					<td align="right">
						<font color="red">*</font>专业：
					</td>
					<td align="left">
						<html:select name="rs" property="zydm" style="" styleId="zydm" onchange="setBjList('研究生')">
							<html:options collection="zyList" property="dm" labelProperty="mc" />
						</html:select>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						民族：
					</td>
					<td align="left">
						<html:select name="rs" property="mz" style="" styleId="mz">
							<html:options collection="mzList" property="mzdm" labelProperty="mzmc" />
						</html:select>
					</td>
					<td align="right">
						<font color="red">*</font>班级：
					</td>
					<td align="left">
						<html:select name="rs" property="bjdm" style="" styleId="bjdm">
							<html:options collection="bjList" property="dm" labelProperty="mc" />
						</html:select>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						政治面貌：
					</td>
					<td align="left">
						<html:select name="rs" property="zzmm" style="" styleId="zzmm">
							<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc" />
						</html:select>
					</td>
					<td align="right">
						出生日期：
					</td>
					<td align="left">
						<html:text name="rs" property="csrq" styleId="csrq"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('csrq','y-mm-dd');"/>	
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						身高：
					</td>
					<td align="left">
						<html:text name='rs' property="sg" styleId="sg" 
						onkeypress="return onlyNum(this,3)" maxlength="3" 
						style="width:10%;ime-mode:disabled"/>CM
					</td>
					<td align="right">
						体重：
					</td>
					<td align="left">
						<html:text name='rs' property="tz" styleId="tz" 
						onkeypress="return onlyNum(this,3)" maxlength="3" 
						style="width:10%;ime-mode:disabled"/>KG
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						<font color="red">*</font>身份证号：
					</td>
					<td align="left">
						<html:text name='rs' property="sfzh" styleId="sfzh" maxlength="20"/>
					</td>
					<td align="right">
						联系电话：
					</td>
					<td align="left">
						<html:text name='rs' property="lxdh" styleId="lxdh" maxlength="20"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						籍贯：
					</td>
					<td align="left">
						<html:text name='rs' property="jg" styleId="jg" maxlength="20"/>
					</td>
					<td align="right">
						来源地区：
					</td>
					<td align="left">
						<html:text name='rs' property="lydq" styleId="lydq" maxlength="50"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						<font color="red">*</font>入学日期：
					</td>
					<td align="left">
						<html:text name="rs" property="rxrq" styleId="rxrq"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('rxrq','y-mm-dd');"/>	
					</td>
					<td align="right">
						<font color="red">*</font>学制：
					</td>
					<td align="left">
						<html:text name='rs' property="xz" styleId="xz" 
						onkeypress="return onlyNum(this,1)" maxlength="1" 
						style="width:10%;ime-mode:disabled"/>年
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
						onclick="saveYjs();"
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
