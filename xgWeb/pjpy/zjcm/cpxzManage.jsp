<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyInfo.js'></script>
	<script language="javascript"  src="js/pjpy/pjpyFunction.js"></script>
	<script type="text/javascript">	
function saveCpxz(){
	var xydm = $("xydm").value;
	var msg = "";
	var xymc = "";
	
	for(var i=0;i<$("xy").options.length;i++){
		if($("xy").options[i].selected){
			xymc=$("xy").options[i].text;
		}
	}
	
	if(xydm == ""){
		msg = "院系未选择,将设置全体院系,请确认";
	}else{
		msg = "设置"+xymc+"参评组，请确认";
	}
	
	if($("bjP").options.length == 0){
		if(xydm == ""){
			msg = "未设置参评小组信息，且院系未选择,\n将撤销全体院系的参评组信息,请确认";
		}else{
			msg = "未设置参评小组信息，撤销"+xymc+"参评组信息，请确认";
		}
	}
		
	for(var i = 0 ; i < $("bjP").options.length; i++){
		var tmp = document.createElement("input");
		tmp.type = "hidden";
		tmp.name = "zwdm";
		tmp.value = $("bjP").options[i].value;
		document.forms[0].appendChild(tmp);
	}
	
	if (confirm(msg)) {
		saveUpdate("/xgxt/zjcmPjpy.do?method=cpxzManage&doType=save","xn-xq");
	}
}

function getCpzInfo(){

	var objId = "bj";
	
	var xydm = $("xy").value;
	var xn = $("xn").value;
	var xq = $("xq").value;

	var tableName = "sxjy_bjgbzlb"; 
	var dm = "bjgbdm"; 
	var mc = "bjgbmc";
	var msg = "";
	var pk = "";
	var pkValue = "";
	
	dwr.engine.setAsync(false);
	getPjpyInfo.getPjpyList(tableName, dm, mc, msg, pk, pkValue,function(data){
		if(data!=null){
			DWRUtil.removeAllOptions(objId);
			DWRUtil.addOptions(objId,data,"dm","mc");
		}
	});
	DWRUtil.removeAllOptions("bjP");
	
	if(xydm != "" && xn != "" && xq != ""){
		getPjpyInfo.getCpzInfoList(xn,xq,xydm,function(data){
			if(data!=null){
				for(var i=0;i<data.length;i++){
					if(i == 0){
						$("zhfkg").value = data[i].zhfkg;
						$("jxjkg").value = data[i].jxjkg;
					}
					for(var j=0;j<$("bj").options.length;j++){
						if($("bj").options[j].value == data[i].zwdm){
							$("bj").options[j] = null;
						}
					}	
					$("bjP").options[$("bjP").options.length] = new Option(data[i].zwmc,data[i].zwdm);
				}
			}
		});
	}
	dwr.engine.setAsync(true);
}
	</script>
	<body onload="getCpzInfo()">
		<html:form action="/zjcmPjpy">
			<input type="hidden" name="njV" id="njV" />
			<input type="hidden" name="ndV" id="ndV" />
			<input type="hidden" name="xyV" id="xyV" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
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
							测评小组设置
						</td>
					</tr>
				</thead>
				<tr style="height: 23px">
					<td align="right" width="10%">
						<font color="red">*</font>学年：
					</td>
					<td align="left" width="30%" colspan="">
						<html:select property="xn" style="" onchange="getCpzInfo()">
							<html:options collection="xnList" property="xn" labelProperty="xn" />
						</html:select>
					</td>
					<td align="right" width="10%">
						<font color="red">*</font>学期：
					</td>
					<td align="left" width="30%" colspan="">
						<html:select property="xq" style="" styleId="xq" onchange="getCpzInfo()">
							<html:option value=""></html:option>
							<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
						</html:select>		
					</td>
				</tr>
				<tr>
					<td align="right">
						综合分录入：
					</td>
					<td>
						<html:select property="zhfkg" style="" styleId="zhfkg" onchange="">
							<html:options collection="kgList" property="en" labelProperty="cn" />
						</html:select>	
					</td>
					<td align="right">
						奖学金申报：
					</td>
					<td>
						<html:select property="jxjkg" style="" styleId="jxjkg" onchange="">
							<html:options collection="kgList" property="en" labelProperty="cn" />
						</html:select>	
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right" width="">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left" width="" colspan="">
						<logic:equal name="userType" value="xy">
							<html:hidden property="xydm"/>
							<html:select property="xydm" style="" styleId="xy" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm" labelProperty="xymc" />
							</html:select>
						</logic:equal>
						<logic:notEqual name="userType" value="xy">
							<html:select property="xydm" style="" styleId="xy" onchange="getCpzInfo()">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm" labelProperty="xymc" />
							</html:select>
						</logic:notEqual>
					</td>
					<td align="right" width="">

					</td>
					<td align="left" width="" colspan="">
							
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<table width="100%">
							<tr>
								<td width="10%" align="right">
									职<br><br><br>务
								</td>
								<td width="30%">
									<html:select property="bjdm" style="width:100% " styleId="bj" size="12" onchange="">
										<html:options collection="zwList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								<td width="10%">
									<button class="button2" style="width:100%" id="addBj" onclick="addBjP()">
										&gt;&nbsp;&gt;
									</button>
									<br />
									<br />
									<br />
									<button class="button2" style="width:100%" id="delBj"
										onclick="delBjP();">
										&lt;&nbsp;&lt;
									</button>
								</td>
								<td width="10%" align="right">
									参<br>评<br>小<br>组
								</td>
								<td width="30%">
									<html:select property="xh" style="width:100%" styleId="bjP"  size="12">
									</html:select>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr bgcolor="EEF4F9" align="center">
					<td colspan="4">
					<button class="button2" id="buttonSave" onclick="saveCpxz()"
						style="width: 80px">
						保&nbsp;&nbsp;存
					</button>
				</tr>
			</table>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
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
