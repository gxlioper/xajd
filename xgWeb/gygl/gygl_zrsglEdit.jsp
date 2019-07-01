<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<script type="text/javascript">
function GetCwhList(){
	var lddm = document.getElementById("lddm").value;
	var qsh = document.getElementById("qsh").value;
	var ssbh=lddm+"-"+qsh;
	document.getElementById("ssbh").value=ssbh;
	chSs(ssbh);
}
		
function chSs(ssbh){
	GetListData.GetQsRzXsXxList(ssbh,function(data){
			if (data != null && typeof data == 'object') {
				var objId = "xh";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"xh","xm");			
					if(objId + "V"){
					if($(objId +"V").value != "" && $(objId + "V").value != null){
						for(var i = 0;i < $(objId).options.length; i++){
							if($(objId).options[i].value == $(objId +"V").value){
								$(objId).options[i].selected = true;
								return true;
                           }
						}
					}
					}
				}
			}
		});
}	
function addXh(){
	var fromIndx = $("xh").selectedIndex;
	if(fromIndx < 0){
		return false;
	}
	$("zrlist").options[$("zrlist").options.length] = new Option($("xh").options[fromIndx].text,$("xh").options[fromIndx].value);
	$("xh").options[fromIndx] = null;
	if($("xh").options.length > 0){
		$("xh").options[0].selected = true;
		$("delXhB").disabled = false;
	}else{		
		$("addXhB").disabled = true;
	}
	if($("zrlist").options.length > 0){
		$("zrlist").options[0].selected = true;
		$("qdbtn").disabled = false;
	}
}
function delXh(){
	var toIndx = $("zrlist").selectedIndex;
	var doType = document.getElementById("doType").value;
	
	if(toIndx < 0){
		return false;
	}
	$("xh").options[$("xh").options.length] = new Option($("zrlist").options[toIndx].text,$("zrlist").options[toIndx].value);
	$("zrlist").options[toIndx] = null;
	if($("zrlist").options.length > 0){
		$("zrlist").options[0].selected = true;
		$("addXhB").disabled = false;
		$("qdbtn").disabled = false;
	}else{		
		$("delXhB").disabled = true;
		$("qdbtn").disabled = true;
		if(doType == "modi"){
			$("qdbtn").disabled = false;
		}
	}	
	if($("xh").options.length > 0){
		$("xh").options[0].selected = true;
	}
}
function qd(){
	var toIndx = $("zrlist").length;
	var zs = document.getElementById("zs").value;
	var lddm = document.getElementById("lddm").value;
	var qsh = document.getElementById("qsh").value;
	var ssbh=lddm+"-"+qsh;
	var pk = new Array();
	var temppk="";
	var tempzs=""
	var doType = document.getElementById("doType").value;
	
	if(doType == "modi"){
	if(toIndx==0){
		if(confirm("值日生顺序列表为空，请确认？")){
			document.getElementById("buttonSave").style.display="";
			document.getElementById("rsxsxx").style.display="none";
			return;
		}else{
		return;
		}
	}
	}
	for(var i=0;i<toIndx;i++){
		pk[i]= $('zrlist').options[i].value+"  "+$('zrlist').options[i].text+splitSignOne;
		temppk = temppk+ pk[i];
	}
	GetListData.GetQsRzXsXxMsg(ssbh,toIndx,zs,temppk,TjRzSsXsXx);
	GetListData.GetQsRzXsXxMsg(ssbh,toIndx,zs,temppk,function(data){
		if (data != null){
			for(var i=0;i<data.length;i++){
				tempzs=tempzs+data[i]+splitSignOne;
			}
			document.getElementById("zsV").value=tempzs;
		}
	});
	document.getElementById("rsTable1").style.display="none";
	document.getElementById("buttonSave").style.display="";
	document.getElementById("buttonCan").style.display="";
	document.getElementById("rsxsxx").style.display="";
}

function qx(){
	document.getElementById("rsTable1").style.display="";
	document.getElementById("buttonSave").style.display="none";
	document.getElementById("buttonCan").style.display="none";
	document.getElementById("rsxsxx").style.display="none";
}

function hh_zrssave(){
	var xn = $("xn").value;
	var xq = $("xq").value;
	var toIndx = $("zrlist").length;
	var ssbh = $("ssbh").value;              
	var doType = $("doType").value;	
	var xh = new Array();
	var temppk="";
	var tempzs=document.getElementById("zsV").value;
	var doType = document.getElementById("doType").value;
	
	for(var i=0;i<toIndx;i++){
		xh[i]= $('zrlist').options[i].value+splitSignOne;
		temppk = temppk+ xh[i];
	}
	if(xh!=""){
		if(confirm("确定要保存？\n\n点击\"确定\"，保存信息；\n点击\"取消\"，将放弃保存！")){
			saveZrs("XsgyglHhDispatch.do?method=zrsglSave&pk="+temppk+"&zsV="+tempzs);
		}
		}
	if(doType == "modi"){
		if(xh==""){
			if(confirm("确定要删除该寝室的所有值日生信息？")){
			saveZrs("XsgyglHhDispatch.do?method=zrsglSave&pk="+temppk+"&zsV="+tempzs);
		}
		}
	}
}

function saveZrs(url){
	showTips('处理数据中，请等待......');
	refreshForm(url);
}		
		
function showSs(){
	var doType = document.getElementById("doType").value;
	if(doType == "add"){
		document.getElementById("add1").style.display="";
		document.getElementById("add2").style.display="";
		document.getElementById("modi").style.display="none";
	}
	if(doType == "modi"){
		var ssbh = $("ssbhV").value;
		var toIndx = $("zrlist").length;
		var zs = document.getElementById("zs").value;
		var pk = new Array();
		var temppk="";
		for(var i=0;i<toIndx;i++){
			pk[i]= $('zrlist').options[i].value+"  "+$('zrlist').options[i].text+splitSignOne;
			temppk = temppk+ pk[i];
		}
		GetListData.GetQsRzXsXxMsg(ssbh,toIndx,zs,temppk,TjRzSsXsXx1);
		var pk1 = new Array();
			pk1 = ssbh.split("-");  
		$("ssbh").value=ssbh;
		$("ssbhV").value=pk1[0]+"号楼" + pk1[1]+"寝室";
		document.getElementById("add1").style.display="none";
		document.getElementById("add2").style.display="none";
		document.getElementById("modi").style.display="";
		document.getElementById("rsxsxx").style.display="";
	}
}

function TjRzSsXsXx1(data){
	var cellfu =[
	    function(data) {return  data;}					
	 ];
	if (data != null && typeof data == 'object') {
		if ($("rsTables").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("rsTables");		
			DWRUtil.addRows("rsTables",data,cellfu,{escapeHtml:false});			
			if($("rsNum")){
			   $("rsNum").innerText = data.length;
			}
			if($("ldqs")){
			  $("ldqs").innerText = $("ssbhV").value;
			}			
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}
	</script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body onload="showSs()">	
		<html:form action="/holiday_lsxx" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：<bean:write name = "title" />
				</div>
			</div>
			<logic:notEmpty name="rs">
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" 
					value="lxdh-lxyy-zskssj-zsjssj-xn-xq-yssbh" />
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-nj-xymc-zymc-bjmc" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-nj-xymc-zymc-bjmc" />
				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType" scope="request"/>" />
				<input type="hidden" id="xxdm" name="xxdm"
					value="<bean:write name="xxdm" scope="request"/>" />
				<input type="hidden" id="url" name="url" value="/holiday_lsxx.do?doType=add&pkValue=" />
				<input type="hidden" name="lcV" id="lcV" value=""/>
				<input type="hidden" name="cwhV" id="cwhV" value=""/>
				<input type="hidden" name="qshV" id="qshV" />
				<input type="hidden" name="ssbh" id="ssbh" />
				<input type="hidden" name="xhV" value=""/>
				<input type="hidden" name="zsV" id="zsV" value=""/>
				
				<table width="100%" class="tbstyle" id="rsTable">
					<thead>
						<tr>
							<td colspan="4" align="center">
								假期留校学生信息
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right">
							<font color="red">*</font>学年：
						</td>
						<td align="left">
							<logic:notEqual value="add" name="doType">
								<html:text name="rs" property="xn" value="${rs.xn}" readonly="true"></html:text>
							</logic:notEqual>
							<logic:equal value="add" name="doType">
								<html:select name="rs" property="xn" styleId="xn" >
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</logic:equal>
						</td>			
						<td align="right">
							<font color="red">*</font>学期：
						</td>
						<td align="left">
							<logic:notEqual value="add" name="doType">
								<html:hidden name="rs" property="xq" value="${rs.xq}"/>
								<input type="text" value="${rs.xqmc}" readonly="true"/>
							</logic:notEqual>							
							<logic:equal value="add" name="doType">
								<html:select name="rs" property="xq" styleId="xq">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
							</logic:equal>
						</td>				
					</tr>
					<tr id="add1">
						<td align="right">
							&nbsp;&nbsp;楼栋名:
						</td>
						<td align="left">
							<html:select property="lddm" style="width:120px"  styleId="lddm"
								onchange="getLcList()">
								<html:option value="">--请选择--</html:option>
								<html:options collection="ldList" property="lddm"
								labelProperty="ldmc" />
							</html:select>
						</td>
						<td align="right">
							&nbsp;&nbsp;楼层:
						</td>
						<td align="left">
							<html:select property="lc" style="width:80px" styleId="lc"
								onchange="getQshList2()">										
								<html:options collection="lcList" property="dm"
								labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					<tr id="add2">
						<td align="right">
							&nbsp;&nbsp;寝室号:
						</td>
						<td align="left">
							<html:select property="qsh" style="width:80px" styleId="qsh"
							onchange="GetCwhList()">	
								<html:options collection="qshList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
						<td align="right">
							&nbsp;&nbsp;本学期周时:
						</td>
						<td align="left">
							<input type="text" id="zs" value="<bean:write name="zs"/>"/>
						</td>
					</tr>
					<tr id="modi">
						<td align="right">
							&nbsp;&nbsp;宿舍编号:
						</td>
						<td align="left">
							<html:text name="rs" property="ssbh" styleId="ssbhV" value="${rs.ssbh}" readonly="true"></html:text>
						</td>
						<td align="right">
							&nbsp;&nbsp;本学期周时:
						</td>
						<td align="left">
							<html:text name="rs" property="zs" value="${rs.zs}" readonly="true"></html:text>
						</td>
					</tr>	
					<tr id="rsxsxx" style="display:none">
						<td colspan="4">
						    <fieldset>
									<legend>
									    <span id="ldqs"></span>寝室当前入住人数：共<span id="rsNum"></span>人
									</legend>
							<table width="100%" class="tbstyle">
								
								<tbody id="rsTables">
								  
								</tbody>
								
							</table>
							</fieldset>
						</td>
					</tr>				
				</table>
				<br>
				<table width="100%" class="tbstyle" id="rsTable1">
					<tr>
						<td align="center" width="6%" valign="middle">
							<p>
								学
							</p>
							<p>
								生
							</p>
						</td>
						<td width="35%">
							<html:select name="xhInfo" property="xm" styleId="xh"
								ondblclick="addLdBj()" size="12" style="width:100% ">
								<html:options collection="xhList" property="xh"
									labelProperty="xm" />
							</html:select>
						</td>
						<td nowrap width="18%">
							<button class="button2" style="width:100%" id="addXhB"
								onclick="addXh()">
								&gt;&nbsp;&gt;
							</button>
							<br />
							<br />
							<br />
							<button class="button2" style="width:100%" id="delXhB"
								onclick="delXh()">
								&lt;&nbsp;&lt;
							</button>
							<br />
							<br />
							<br />
							<button class="button2" style="width:100%;" disabled="true" id="qdbtn"
								onclick="qd()">确定</button>
						</td>
						<td align="center" width="6%" valign="middle">
							<p><font color="red">值</font></p>
							<p><font color="red">日</font></p>
							<p><font color="red">生</font></p>
							<p><font color="red">顺</font></p>
							<p><font color="red">序</font></p>
						</td>
						<td width="35%">
							<html:select name="zrInfo" property="zrlist" size="12"
								ondblclick="delLdBj()" styleId="zrlist" style="width:100% ">
								<html:options collection="zrList" property="xh"
									labelProperty="xm" />
							</html:select>
						</td>
					</tr>
				</table>
				
				<div class="buttontool" align="center">

					<button class="button2" onclick="" style="width:0px" id="buttonModi">
						修 改
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="hh_zrssave();return false;" style="width:80px" id="buttonSave" style="display:none">
						保 存
					</button>
					<button class="button2" onclick="hh_zrssave();return false;" style="width:80px" id="buttonCan" style="display:none">
						取 消
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="Close();return false;" style="width:80px" id="buttonClose">
						关 闭
					</button>
				</div>
			</logic:notEmpty>		
		</html:form>
		<logic:equal value="ok" name="result">
			<script>
				alert("操作成功！");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("操作失败！");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
  </body>
</html>
