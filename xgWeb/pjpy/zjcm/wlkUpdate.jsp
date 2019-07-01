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
	<script type="text/javascript">	
function saveWlk(){
	var nj = $("nj").value;
	var xydm = $("xydm").value;
	var zydm = $("zydm").value;
	var lx = $("lx").value;
	var xymc = "";
	var zymc = "";
	var mc = "";
	var msg = "";
	var url = "/xgxt/zjcmPjpy.do?method=wlkUpdate&doType=save";
	
	if(lx == ""){
		alert("类型不能为空，请确认！！");
		return false;
	}
	
	for(var i=0;i<$("xy").options.length;i++){
		if($("xy").options[i].selected){
			xymc=$("xy").options[i].text;
		}
	}
	
	for(var i=0;i<$("zy").options.length;i++){
		if($("zy").options[i].selected){
			zymc=$("zy").options[i].text;
		}
	}
	
	for(var i=0;i<$("lx").options.length;i++){
		if($("lx").options[i].selected){
			mc=$("lx").options[i].text;
		}
	}	

	for(var i = 0 ; i < $("bjP").options.length; i++){
		var tmp = document.createElement("input");
		tmp.type = "hidden";
		tmp.name = "wlkbjdm";
		tmp.value = $("bjP").options[i].value;
		document.forms[0].appendChild(tmp);
	}
	
	if(nj =="" && xydm == "" && zydm == "" && $("bjP").options.length == "0"){
		alert("请确认设置条件!!");
		return false;
	}else if(nj !="" && xydm == "" && zydm == "" && $("bjP").options.length == "0"){
		msg = "将" + nj + "年级全体班级设置为" + mc + ",请确认";
		url+="&fs=nj"
	}else if(xydm != "" && zydm == "" && $("bjP").options.length == "0"){
		msg = "将";
		if(nj != ""){
			msg += nj + "年级";
		}
		msg += xymc + "全体班级设置为" + mc + ",请确认";
		url+="&fs=xy"
	}else if(zydm != "" && $("bjP").options.length == "0"){
		msg = "将";
		if(nj != ""){
			msg += nj + "年级";
		}
		if(xymc != ""){
			msg += xymc;
		}
		msg += zymc + "全体班级设置为" + mc + ",请确认";
		url+="&fs=zy"
	}else if($("bjP").options.length != "0"){
		msg = "将所选班级设置为" + mc + ",请确认";
		url+="&fs=bj"
	}
	
	if (confirm(msg)) {
		saveUpdate(url,'lx');
	}
}

	</script>
	<body onload="">
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
							班级大类设置
						</td>
					</tr>
				</thead>
				<tr style="height: 23px">
					<td align="right" width="10%">
						年级：
					</td>
					<td align="left" width="30%" colspan="">
						<html:select property="nj" style="" onchange="initZyList();initBjList();">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj" labelProperty="nj" />
						</html:select>
					</td>
					<td align="right" width="10%">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left" width="30%" colspan="">
						<html:select property="xydm" style="" styleId="xy" onchange="initZyList();initBjList();">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm" labelProperty="xymc" />
						</html:select>		
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right" width="">
						专业：
					</td>
					<td align="left" width="" colspan="">
						<html:select property="zydm" style="" styleId="zy" onchange="initBjList();">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm" labelProperty="zymc" />
						</html:select>
					</td>
					<td align="right" width="">
						<font color="red">*</font>类型：
					</td>
					<td align="left" width="" colspan="">
						<html:select property="lx" style="" styleId="lx" onchange="">
							<html:options collection="wlkList" property="dm" labelProperty="mc" />
						</html:select>		
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<table width="100%">
							<tr>
								<td width="10%" align="right">
									班<br><br><br>级
								</td>
								<td width="30%">
									<html:select property="bjdm" style="width:100% " styleId="bj" size="12" onchange="">
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
									设<br>置<br>班<br>级
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
					<button class="button2" id="buttonSave" onclick="saveWlk()"
						style="width: 80px">
						保&nbsp;&nbsp;存
					</button>
					&nbsp;&nbsp;
					<button class="button2" id="buttonClose" onclick="Close();return false;"
						style="width: 80px">
						关&nbsp;&nbsp;闭
					</button>
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
