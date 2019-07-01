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

function getDjrList(){
		
	dwr.engine.setAsync(false);
	
	var tableName = "view_pxpj_djr"; 
	var dm = "xh"; 
	var mc = "xm";
	var msg = "";
	var pk = "bjdm";
	var pkValue = $("bj").value;
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

function getBjSzrs(bjdm){
		
	var tableName = "view_pypj_djrszrs"; 
	var colList = ["szrs"]; 
	var pk = "bjdm"; 
	var pkValue = bjdm; 
	var query = "";
	
	getTableInfo(tableName,colList,pk,pkValue,query);
}
	
function showMsg(){

	var bjdm = $("bj").value;
	
	if(bjdm != ""){
		$("nobjMsg").style.display= "none";
		$("bjMsg").style.display= "";
		getBjSzrs(bjdm);
	}else{
		$("nobjMsg").style.display= "";
		$("bjMsg").style.display= "none";
	}
}

function saveDjr(){

	var bjdm = $("bjdm").value;
	var szrs = $("szrs").value;
	var xzrs = $("xhR").options.length;
	
	if(bjdm == ""){
		alert("班级不能为空，请确认！！");
		return false;
	}
	
	if (xzrs == 0){
		alert("至少设置一个回答人，请确认！！");
		return false;
	}

	if(xzrs < szrs){
		alert("回答人未达到所设置的下限，请确认！！");
		return false;
	}
	
	for(var i = 0 ; i < $("xhR").options.length; i++){
		var tmp = document.createElement("input");
		tmp.type = "hidden";
		tmp.name = "djr";
		tmp.value = $("xhR").options[i].value;
		document.forms[0].appendChild(tmp);
	}
	
	var msg = "设置该批学生为此班级的答卷人，确认？";
	var url = "/xgxt/nbcsPxpj.do?method=djrUpdate&doType=save";
	
	if (confirm(msg)) {
		saveUpdate(url,'bjdm');
	}
}

	</script>
	<body onload="showMsg()">
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
						<td colspan="4" align="center">
							${xn }学年答卷人分配
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
						<html:select property="bjdm" style="" styleId="bj" onchange="getBjStuList();getDjrList();showMsg()">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
						</html:select>
					</td>
				</tr>
				<tr style="height: 23px">
					<td colspan="4" align="center">
						<div id="nobjMsg" style="display : none"><font color="blue">注：请确定班级，以便显示该班级的学生。</font></div>
						<div id="bjMsg" style="display : none"><font color="blue">注：所选班级最少需要设置<input type="text" name="szrs" id="zrss" style="width:5%" readonly="readonly">名答卷人。</font></div>
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
					<button type="button" class="button2" id="buttonSave" onclick="saveDjr()"
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
