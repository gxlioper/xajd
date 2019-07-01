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
function getFpbj(){
		
	var tableName = "view_wjdc_wjfp"; 
	var dm = "bjdm"; 
	var mc = "bjmc";
	var msg = "";
	var pk = "id";
	var pkValue = $("id").value;
	var obId = "bjR";
	if(pkValue == ""){
		pk = "";
	}
		
	getPjpyInfo.getPjpyList(tableName, dm, mc, msg, pk, pkValue,function(data){
		if(data!=null){
			DWRUtil.removeAllOptions(obId);
			DWRUtil.addOptions(obId,data,"dm","mc");
			$(obId).options[0] = null;
		}
	});
}
	
function saveWjfp(){

	var nj = $("nj").value;
	var xydm = $("xydm").value;
	var zydm = $("zydm").value;
	var id = $("id").value;
	
	var xymc = "";
	var zymc = "";
	var wjmc = "";
	
	var msg = "";
	var url = "/xgxt/nbcsPjpy.do?method=wjfpUpdate&doType=save";
	
	if(id == ""){
		alert("问卷不能为空，请确认！！");
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
	
	for(var i=0;i<$("id").options.length;i++){
		if($("id").options[i].selected){
			wjmc=$("id").options[i].text;
		}
	}	

	for(var i = 0 ; i < $("bjR").options.length; i++){
		var tmp = document.createElement("input");
		tmp.type = "hidden";
		tmp.name = "fpbj";
		tmp.value = $("bjR").options[i].value;
		document.forms[0].appendChild(tmp);
	}
	
	if(nj =="" && xydm == "" && zydm == "" && $("bjR").options.length == "0"){
		msg = "将问卷'" + wjmc + "'分配给全校班级？";
	}else if(nj !="" && xydm == "" && zydm == "" && $("bjR").options.length == "0"){
		msg = "将问卷'" + wjmc + "'分配给" + nj + "全体班级？";
	}else if(xydm != "" && zydm == "" && $("bjR").options.length == "0"){
		msg = "将问卷'" + wjmc + "'分配给";
		if(nj != ""){
			msg += nj + "年级";
		}
		msg += xymc + "全体班级?";
	}else if(zydm != "" && $("bjR").options.length == "0"){
		msg = "将问卷'" + wjmc + "'分配给";
		if(nj != ""){
			msg += nj + "年级";
		}
		if(xymc != ""){
			msg += xymc;
		}
		msg += zymc + "全体班级?";
	}else if($("bjR").options.length != "0"){
		msg = "将问卷'" + wjmc + "'分配给所设置班级？";
	}
	
	if (confirm(msg)) {
		saveUpdate(url,'lx');
	}
}

	</script>
	<body onload="getWjList()">
		<html:form action="/nbcsPjpy">
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
						<td colspan="4" align="center">
							问卷分配
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
						学年：
					</td>
					<td align="left" width="30%" colspan="">
						<html:hidden property="xn"/>
						<html:select property="xn" style="width:120px" styleId="xn" disabled="true">
							<html:options collection="xnList" property="xn" labelProperty="xn" />
						</html:select>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right" width="">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left" width="" colspan="">
						<html:select property="xydm" style="" styleId="xy" onchange="initZyList();initBjList();">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm" labelProperty="xymc" />
						</html:select>		
					</td>
					<td align="right" width="">
						年度：
					</td>
					<td align="left" width="" colspan="">
						<html:hidden property="nd"/>
						<html:select property="nd" style="" styleId="nd" disabled="true">
							<html:options collection="ndList" property="nd" labelProperty="nd" />
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
						学期：
					</td>
					<td align="left" width="" colspan="">
						<html:hidden property="xq"/>
						<html:select property="xq" style="" styleId="xq" disabled="true">
							<html:option value=""></html:option>
							<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
						</html:select>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right" width="">
						<font color="red">*</font>问卷：
					</td>
					<td align="left" width="" colspan="">
						<html:select property="id" style="" styleId="id" onchange="getFpbj()">
							<html:options collection="wjList" property="dm" labelProperty="mc" />
						</html:select>
					</td>
					<td align="right" width="">
						
					</td>
					<td align="left" width="" colspan="">
						
					</td>
				</tr>
				<tr>
					<td colspan="5" align="center">
						<font color="blue">提示：不选择班级的话，可以根据所选条件进行批量设置;按Shift或者Ctrl可以进行批量选择班级.</font>
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
									<html:select property="bjdm" style="width:100% " multiple="multiple"
										styleId="bj" size="12" ondblclick="">
									</html:select>
								</td>
								<td width="10%">
									<button class="button2" style="width:100%" id="addBj" onclick="addAllRightFrame('bj','bjR')">
										&gt;&nbsp;&gt;
									</button>
									<br />
									<br />
									<br />
									<button class="button2" style="width:100%" id="delBj" onclick="addAllLeftFrame('bj','bjR')">
										&lt;&nbsp;&lt;
									</button>
								</td>
								<td width="10%" align="right">
									设<br>置<br>班<br>级
								</td>
								<td width="30%">
									<html:select property="fpbj" style="width:100%" styleId="bjR" multiple="multiple"
										size="12" ondblclick="">
									</html:select>		
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr bgcolor="EEF4F9" align="center">
					<td colspan="4">
					<button class="button2" id="buttonSave" onclick="saveWjfp()"
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
