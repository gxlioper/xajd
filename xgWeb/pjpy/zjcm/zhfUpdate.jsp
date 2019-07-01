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
	<script language="javascript"  src="js/pjpy/pjpyFunction.js"></script>
	<script type="text/javascript">	
	function saveBl(){
		var dyfbl = $("dyfbl").value;
		var zyfbl = $("zyfbl").value;
		var tyfbl = $("tyfbl").value;
		var nlfbl = $("nlfbl").value;

		if((parseInt(dyfbl) + parseInt(zyfbl) + parseInt(tyfbl) + parseInt(nlfbl)) > 100){
			alert("各项合计不能超过100，请确认！！！");
			return false;
		}
		saveUpdate('/xgxt/zjcmPjpy.do?method=zhfUpdate&doType=save','dyfbl-zyfbl-tyfbl-nlfbl')
	}
	</script>
	
	<body onload="">
		<html:form action="/zjcmDtjs">
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
							各分值上限
						</td>
					</tr>
				</thead>
					<tr>
						<td align="right" width="40%">
							评奖学年：
						</td>
						<td align="left" width="40%">
							<html:hidden name="rs" property="xn" />
							<html:select name="rs" property="xn" style="" onchange="" disabled="true">
								<html:options collection="xnList" property="xn" labelProperty="xn" />
							</html:select>			
						</td>
					</tr>
					<tr>
						<td align="right" width="40%">
							评奖学期：
						</td>
						<td align="left" width="40%">
							<html:hidden name="rs" property="xq" />
							<html:select name="rs" property="xq" style="" onchange="" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right" width="40%">
							<font color="red">*</font>德育分比例：
						</td>
						<td align="left" width="40%">
							<html:text name="rs" property="dyfbl" style="width:30px"
								onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="3"/>%
						</td>
					</tr>
					<tr>
						<td align="right" width="40%">
							<font color="red">*</font>智育分比例：
						</td>
						<td align="left" width="40%">
							<html:text name="rs" property="zyfbl" style="width:30px"
								onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="3"/>%
						</td>
					</tr>
					<tr>
						<td align="right" width="40%">
							<font color="red">*</font>体育分比例：
						</td>
						<td align="left" width="40%">
							<html:text name="rs" property="tyfbl" style="width:30px"
								onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="3"/>%
						</td>
					</tr>
					<tr>
						<td align="right" width="40%">
							<font color="red">*</font>能力分比例：
						</td>
						<td align="left" width="40%">
							<html:text name="rs" property="nlfbl" style="width:30px"
								onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="3"/>%
						</td>
					</tr>
				<tr bgcolor="EEF4F9" align="center">
					<td colspan="4">
					<logic:notEqual name="doType" value="view">
					<button class="button2" id="buttonSave" 
						onclick="saveBl()"
						style="width: 80px">
						保	存
					</button>
					&nbsp;&nbsp;
					</logic:notEqual>
					<button class="button2" id="buttonClose" onclick="Close();return false;"
						style="width: 80px">
						关	闭
					</button>
					</td>
				</tr>
			</table>
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
