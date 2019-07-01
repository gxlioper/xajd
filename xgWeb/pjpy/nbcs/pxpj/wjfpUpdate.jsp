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
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>	
	<script language="javascript"  src="js/pjpy/nbcs/pjpy_wjdc.js"></script>
	<script type="text/javascript">	
	
function getBjStuList(){
		
	dwr.engine.setAsync(false);
	
	var tableName = "view_xsjbxx"; 
	var dm = "xh"; 
	var mc = "xm";
	var msg = "";
	var pk = "bjdm";
	var pkValue = $("bj").value;
	var obId = "xhL";
	
	if(pkValue !=""){		
		getPjpyInfo.getPjpyList(tableName, dm, mc, msg, pk, pkValue,function(data){
			if(data!=null){
				DWRUtil.removeAllOptions(obId);
				DWRUtil.addOptions(obId,data,"dm","mc");
				$(obId).options[0] = null;
			}
		});
	}else{
		DWRUtil.removeAllOptions(obId);
	}
	
	dwr.engine.setAsync(true);
}

function getFpList(){
		
	dwr.engine.setAsync(false);
	
	var tableName = "view_pxpj_wjfp"; 
	var dm = "xh"; 
	var mc = "xm";
	var msg = "";
	var pk = "bjdm||id";
	var pkValue = $("bj").value+$("id").value;
	var obId = "xhR";
	
	if(pkValue !=""){		
		getPjpyInfo.getPjpyList(tableName, dm, mc, msg, pk, pkValue,function(data){
			if(data!=null){
				DWRUtil.removeAllOptions(obId);
				DWRUtil.addOptions(obId,data,"dm","mc");
				$(obId).options[0] = null;
			}
		});
	}else{
		DWRUtil.removeAllOptions(obId);
	}
	
	var leftList = $("xhL");
	var rightList = $("xhR");

	for(i=0;i<leftList.options.length;i++){
	
		for(j=0;j<rightList.options.length;j++){
		
			if(leftList.options[i].value == rightList.options[j].value){
				leftList.options[i] = null;
			}
		}
	}
	
	dwr.engine.setAsync(true);
}

function saveWjfp(){

	var nj = $("nj").value;
	var xydm = $("xy").value;
	var zydm = $("zy").value;
	var bjdm = $("bj").value;
	var id = $("id").value;
	
	var xymc = "";
	var zymc = "";
	var bjmc = "";
	var wjmc = "";
	
	var msg = "";
	var url = "/xgxt/nbcsPxpj.do?method=wjfpUpdate&doType=save";
	
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
	
	for(var i=0;i<$("bj").options.length;i++){
		if($("bj").options[i].selected){
			bjmc=$("bj").options[i].text;
		}
	}
	
	for(var i=0;i<$("id").options.length;i++){
		if($("id").options[i].selected){
			wjmc=$("id").options[i].text;
		}
	}	

	for(var i = 0 ; i < $("xhR").options.length; i++){
		var tmp = document.createElement("input");
		tmp.type = "hidden";
		tmp.name = "fpxh";
		tmp.value = $("xhR").options[i].value;
		document.forms[0].appendChild(tmp);
	}
	
	if(nj =="" && xydm == "" && zydm == "" && bjdm == "" && $("xhR").options.length == "0"){
		msg = "将问卷'" + wjmc + "'分配给全校全体学生？";
	}else if(nj !="" && xydm == "" && zydm == "" && bjdm == "" && $("xhR").options.length == "0"){
		msg = "将问卷'" + wjmc + "'分配给" + nj + "全体学生？";
	}else if(xydm != "" && zydm == "" && bjdm == "" && $("xhR").options.length == "0"){
		msg = "将问卷'" + wjmc + "'分配给";
		if(nj != ""){
			msg += nj + "年级";
		}
		msg += xymc + "全体学生?";
	}else if(zydm != "" && $("xhR").options.length == "0"){
		msg = "将问卷'" + wjmc + "'分配给";
		if(nj != ""){
			msg += nj + "年级";
		}
		if(xymc != ""){
			msg += xymc;
		}
		msg += zymc + "全体学生?";
	}else if(bjdm != "" && $("xhR").options.length == "0"){
		msg = "将问卷'" + wjmc + "'分配给";
		if(nj != ""){
			msg += nj + "年级";
		}
		if(xymc != ""){
			msg += xymc;
		}
		if(zymc != ""){
			msg += zymc;
		}
		msg += bjmc + "全体学生?";
	}else if($("xhR").options.length != "0"){
		msg = "将问卷'" + wjmc + "'分配给所设置学生？";
	}
	
	if (confirm(msg)) {
		saveUpdate(url,'lx');
	}
}

	</script>
	<body onload="getWjList()">
		<html:form action="/nbcsPxpj">
			<!-- 隐藏域 -->
			<%@ include file="/pjpy/hiddenValue.jsp"%>
			<input type="hidden" name="xn" id="xn" value="${xn }"/>
			<input type="hidden" name="xq" id="xq" value=""/>
			<input type="hidden" name="nd" id="nd" value=""/>
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
							${xn }学年问卷分配
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
						<!-- <bean:message key="lable.xsgzyxpzxy" /> -->
						<logic:equal name="isxy" value="true">
						<html:select property="xydm" style="" styleId="xy" disabled="true">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm" labelProperty="xymc" />
						</html:select>	
						</logic:equal>
						<!-- <bean:message key="lable.xsgzyxpzxy" /> -->
						<logic:equal name="isxy" value="false">
						<html:select property="xydm" style="" styleId="xy" onchange="initZyList();initBjList();">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm" labelProperty="xymc" />
						</html:select>	
						</logic:equal>
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
						<font color="red">*</font>班级：
					</td>
					<td align="left" width="" colspan="">
						<html:select property="bjdm" style="" styleId="bj" onchange="getBjStuList();getFpList();">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
						</html:select>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right" width="">
						问卷：
					</td>
					<td align="left" width="" colspan="">
						<html:select property="id" style="" styleId="id" onchange="getBjStuList();getFpList()">
							<html:options collection="wjList" property="dm" labelProperty="mc" />
						</html:select>
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
									学<br><br>生
								</td>
								<td width="30%">
									<html:select property="xh" style="width:100% " multiple="multiple"
										styleId="xhL" size="12" ondblclick="">
									</html:select>
								</td>
								<td width="10%">
									<button type="button" class="button2" style="width:100%" id="addBj" onclick="addAllRightFrame('xhL','xhR')">
										&gt;&nbsp;&gt;
									</button>
									<br />
									<br />
									<br />
									<button type="button" class="button2" style="width:100%" id="delBj" onclick="addAllLeftFrame('xhL','xhR')">
										&lt;&nbsp;&lt;
									</button>
								</td>
								<td width="10%" align="right">
									回<br>答<br>人
								</td>
								<td width="30%">
									<html:select property="xm" style="width:100%" styleId="xhR" multiple="multiple"
										size="12" ondblclick="">
									</html:select>		
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr bgcolor="EEF4F9" align="center">
					<td colspan="4">
					<button type="button" class="button2" id="buttonSave" onclick="saveWjfp()"
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
