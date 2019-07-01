<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script type="text/javascript" src="js/pjpy/pjpy_szyqxy.js"></script>
	<script language="javascript" src="js/sztzFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSzPjpyDAO.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/cpzZjcmDAO.js'></script>
	<script type="text/javascript">	

function addBjP(){
	var fromIndx = $("bj").selectedIndex;
	var toIndx = $("bjP").options.length;
	if(fromIndx < 0){
		return false;
	}

	for(var i=0;i<toIndx;i++){
		if($("bj").options[fromIndx].value == $("bjP").options[i].value){
			return false;
		}
	}
	$("bjP").options[$("bjP").options.length] = new Option($("bj").options[fromIndx].text,$("bj").options[fromIndx].value);
	$("bj").options[fromIndx] = null;
	if($("bj").options.length > 0){
		$("bj").options[0].selected = true;
		//$("delBj").disabled = false;
	}else{		
		//$("addBj").disabled = true;
	}
	if($("bjP").options.length > 0){
		$("bjP").options[0].selected = true;
	}
}

function delBjP(){
	var toIndx = $("bjP").selectedIndex;
	var fromIndx = $("bj").options.length;
	if(toIndx < 0){
		return false;
	}
	for(var i=0;i<fromIndx;i++){
		if($("bjP").options[toIndx].value == $("bj").options[i].value){
			$("bjP").options[toIndx] = null;
			return false;
		}
	}
	$("bj").options[$("bj").options.length] = new Option($("bjP").options[toIndx].text,$("bjP").options[toIndx].value);
	$("bjP").options[toIndx] = null;
	if($("bjTlist").options.length > 0){
		$("bjP").options[0].selected = true;
		//$("addBj").disabled = false;
	}else{		
		//$("delBj").disabled = true;
	}	
	if($("bj").options.length > 0){
		$("bj").options[0].selected = true;
	}
	
}
function saveZw(){
	var xn = $("xn").value;
	var xq = $("xq").value;
	var xydm = $("xydm").value;
	var lrkssj=$("lrkssj").value;
	var lrjssj=$("lrjssj").value;
	var sbkssj=$("sbkssj").value;
	var sbjssj=$("sbjssj").value;
	
	if(xn == "" || xq == "" ){
		alert("学年学期不能为空，请确认！！");
		return false;
	}
	
	if(lrkssj == "" || lrjssj == ""|| sbkssj == "" || sbjssj == "" ){
		alert("录入和申报时间不能为空，请确认！！");
		return false;
	}
	
	if(lrkssj > lrjssj || sbkssj > sbjssj){
		alert("开始时间不能大于结束时间，请确认！！");
		return false;
	}
	
	if(xydm == "" ){
		alert("<bean:message key="lable.xsgzyxpzxy" />不能为空，请确认！！");
		return false;
	}
	
	if($("bjP").options.length == 0){
		alert("测评组不能为空，请确定测评组职务！！");
		return false;
	}
	for(var i = 0 ; i < $("bjP").options.length; i++){
		var tmp = document.createElement("input");
		tmp.type = "hidden";
		tmp.name = "zw";
		tmp.value = $("bjP").options[i].value;
		document.forms[0].appendChild(tmp);
	}
	refreshForm("/xgxt/zjcm_cpz.do?method=cpzManage&doType=save");
}

function getZwList(){
	var xn = $("xn").value;
	var xq = $("xq").value;
	var xydm = $("xydm").value;
	dwr.engine.setAsync(false);
	if(xn != "" && xq !="" && xydm!= ""){
		cpzZjcmDAO.getZwList(xn,xq,xydm,function(data){
			if (data != null && typeof data == 'object') {
				var objId = "bj";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);			
						DWRUtil.addOptions(objId,data,"bjgbdm","bjgbmc");			
				}
			}		
		});
		cpzZjcmDAO.getCpZwList(xn,xq,xydm,function(data){
			if (data != null && typeof data == 'object') {
				var objId = "bjP";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);			
						DWRUtil.addOptions(objId,data,"bjgbdm","bjgbmc");			
				}
			}		
		});
		cpzZjcmDAO.getCpzSj(xn,xq,xydm,function(data){
			if (data != null && data !="") {
				$("lrkssj").value=data.lrkssj;
				$("lrjssj").value=data.lrjssj;
				$("sbkssj").value=data.sbkssj;
				$("sbjssj").value=data.sbjssj;
			}else{
				$("lrkssj").value="";
				$("lrjssj").value="";
				$("sbkssj").value="";
				$("sbjssj").value="";
			}
		});
	}
	dwr.engine.setAsync(true);

}
	</script>
	<body onload="">
		<html:form action="/zjcm_cpz">
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
				style="width: 80%">
				<thead>
					<tr>
						<td colspan="4" align="center">
							测评小组设置
						</td>
					</tr>
				</thead>
				<tr style="height: 23px">
					<td align="right" width="25%">
						评奖学年：
					</td>
					<td align="left" width="25%">
						<html:select property="xn" style="" styleClass="select"styleId="xn" onchange="getZwList()">
							<html:options collection="xnList" property="xn" labelProperty="xn" />
						</html:select>
					</td>
					<td align="right" width="25%">
						评奖学期：
					</td>
					<td align="left" width="25%">
						<html:select property="xq" style="" styleClass="select"styleId="xq" onchange="getZwList()">
							<html:option value=""></html:option>
							<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right" width="25%">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						<html:select property="xydm" onchange="getZwList()"
						styleClass="select" style="" styleId="xy">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
					</td>
					<td>
					</td>
					<td>
					</td>
				</tr>
				<tr>
					<td align="right" width="">
						录入分值开始时间：
					</td>
					<td align="left">
						<html:text  property="lrkssj" styleId="lrkssj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('lrkssj','y-mm-dd');"/>
					</td>
					<td align="right">
						录入分值结束时间：
					</td>
					<td align="left">
						<html:text  property="lrjssj" styleId="lrjssj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('lrjssj','y-mm-dd');"/>
					</td>
				</tr>
				<tr>
					<td align="right" width="">
						奖学金申报开始时间：
					</td>
					<td align="left">
						<html:text  property="sbkssj" styleId="sbkssj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('sbkssj','y-mm-dd');"/>
					</td>
					<td align="right">
						奖学金申报结束时间：
					</td>
					<td align="left">
						<html:text  property="sbjssj" styleId="sbjssj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('sbjssj','y-mm-dd');"/>
					</td>
				</tr>
				<tr>
					<td align="center" valign="middle">
						<p>
							学
						</p>
						<p>
							生
						</p>
						<p>
							职
						</p>
						<p>
							务
						</p>
					</td>
					<td width="40%">
						<html:select property="bjdm" styleId="bj"  size="12" style="width:100% ">
							<html:options collection="zwList" property="bjgbdm"
								labelProperty="bjgbmc" />
						</html:select>
					</td>
					<td nowrap width="10%">
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
					<td width="">
						<html:select property="bjdm" styleId="bjP" size="12" style="width:100% ">
							<html:options collection="cpZwList" property="bjgbdm"
								labelProperty="bjgbmc" />
						</html:select>
					</td>
					
				</tr>
				<tr bgcolor="EEF4F9" align="center">
					<td colspan="4">
					<button class="button2" id="buttonSave" onclick="saveZw()"
						style="width: 80px">
						保存
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
			<logic:present name="msg">
				<script>
					alert($("msg").value);
				</script>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
