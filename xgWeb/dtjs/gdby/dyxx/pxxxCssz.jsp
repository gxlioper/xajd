<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script type="text/javascript" src="js/pjpy/pjpy_szyqxy.js"></script>
	<script language="javascript"  src="js/sztzFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script type="text/javascript">	
	function time(id){
		return showCalendar(id,'y-mm-dd');
	}
	
	function saveZsdy(){
		if($("gzkssj")){
			if($("gzkssj").value == ""){
				alert("请确定培训时间");
				return false;
			}
		}
		var url = "/xgxt/czxxDtjsDyxx.do?method=pxxxUpdate&doType=add";
		$("buttonSave").disabled=true;
		$("buttonClose").disabled=true;
		refreshForm(url);
	}
	
	function savePxxx(){
	
		if($("pxsj").value == ""){
			alert("请确认培训时间");
			return false;
		}
		
		if($("pxmc").value == ""){
			alert("请确认培训名称");
			return false;
		}
		if($("rsNum").value == "" || $("rsNum").value == "0"){
			alert("该培训时间尚未确认名单，请名单确认后再试!");
			return false;
		}
		saveUpdate('/xgxt/czxxDtjsDyxx.do?method=pxxxUpdate&doType=save','pxmc-pxsj')
	}
	</script>
	<body onload="">
		<html:form action="/czxxDtjsDyxx">
		<input type="hidden" id="doType" name="doType" value="${doType }"/>
		<input type="hidden" id="rsNum" name="rsNum" value="${rsNum }"/>
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
							党课培训参数设置
						</td>
					</tr>
				</thead>
				<tr style="height: 23px">
					<td align="right">
						<font color="red">*</font>培训名称：
					</td>
					<td align="left">
						<html:text name='rs' property="pxmc" styleId="pxmc" maxlength="25"/>
					</td>
					<td align="right">
						<font color="red">*</font>培训时间：
					</td>
					<td align="left">
						<html:text name='rs' property="pxsj" styleId="pxsj" readonly="true"/>
						<logic:equal name="doType" value="add">
						<button type="button" onclick="dyzz()"
						class="btn_01" id="buttonFindStu">
						选择
						</button>
						</logic:equal>
					</td>
				</tr>	
				<tr style="height: 23px">
					<td align="right">
						培训地点：
					</td>
					<td align="left" colspan="3">
						<html:text name='rs' property="pxdd" styleId="pxdd" maxlength="50" style="width:100%"/>
					</td>
				</tr>	
				<tr style="height: 23px">
					<td align="right">
						培训内容：<br><font color="red">(限250字)</font>
					</td>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="pxnr" styleId="pxnr" rows="5" style="width: 100%" onblur="chLeng(this,250)"/>
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
					<button type="button" class="button2" id="buttonSave" onclick="savePxxx()"
						style="width: 80px">
						保	存
					</button>
					</logic:notEqual>
					&nbsp;&nbsp;
					<button type="button" class="button2" id="buttonClose" onclick="Close();return false;"
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
						window.dialogArguments.document.getElementById("search_go").click();
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
