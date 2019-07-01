<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script language="javascript" src="js/sztzFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript"  src="js/pjpy/pjpyFunction.js"></script>
	<script language="javascript"  src="js/pjpy/nbcs/pjpy_wjdc.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyNbcsDAO.js'></script>	
	<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyInfo.js'></script>
	<script type="text/javascript">	
function saveBl(){
	var xn = $("xn").value;
	var bl = $("bl").value;
	var url = "/xgxt/nbcsPxpj.do?method=djrBlsz&doType=save";
	
	if(bl == ""){
		alert("比例不能为空，请确认！！");
		return false;
	}
	
	if(bl >100){
		alert("比例不能大于100%，请确认！！");
		return false;
	}
	
	var msg = xn +"学年答卷人比例设置为"+bl+"%,请确认!";
	
	if (confirm(msg)) {
		saveUpdate(url,'');
	}
}
	</script>
	<body onload="">
		<html:form action="/nbcsPxpj">
			<!-- 隐藏域 -->
			<%@ include file="/pjpy/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：<bean:write name="title" />
				</div>
			</div>
			<table class="tbstyle" border="0" id="rsTable" align="center"
				style="width: 100%">
				<thead>
					<tr>
						<td colspan="2" align="center">
							答卷人比例设置
						</td>
					</tr>
				</thead>
				<tr style="height: 23px">
					<td align="right" width="30%">
						学年：
					</td>
					<td align="left" width="70%" colspan="">
						${xn }
						<html:hidden name="rs" property="xn"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right" width="30%">
						<font color="red">*</font>比例：
					</td>
					<td align="left" width="70%" colspan="">
						<html:text name="rs" property="bl" 
							onkeypress="return onlyNum(this,3)" 
							maxlength="3"
							style="width:15%;ime-mode:disabled"/>%
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right" width="30%">
						备注：
						<br>
						<font color="red">(限500字)</font>
					</td>
					<td align="left" width="70%" colspan="">
						<html:textarea name="rs" property="bz"style="width: 90%" rows="5" onblur="chLeng(this,500)"></html:textarea>
					</td>
				</tr>
				<tr bgcolor="EEF4F9" align="center">
					<td colspan="2">
					<button type="button" class="button2" id="buttonSave" onclick="saveBl()"
						style="width: 80px">
						保&nbsp;&nbsp;存
					</button>
					&nbsp;&nbsp;
					<button type="button" class="button2" id="buttonClose" onclick="Close();return false;"
						style="width: 80px">
						关&nbsp;&nbsp;闭
					</button>
				</tr>
			</table>
			<logic:notEmpty name="message">
				<script>
					alert($("message").value);
					$("message").value = "";
					$("doType").value = "";
					dialogArgumentsQueryChick();
					window.close();
				</script>
			</logic:notEmpty>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
