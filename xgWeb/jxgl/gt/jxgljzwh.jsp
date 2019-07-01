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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="/xgxt/dwr/interface/getOtherData.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
function addMax(url){
	var njS = document.getElementById('nj').value;
	url += "&nj="+njS;
	return showTopWin(url,450,400);
}
function addQt(){

	var cbVal = document.getElementById('cbVal').value;
	var allJz = document.getElementById('allJz').value;
	var jz = new Array();
	jz = allJz.split("-");
	
	if (cbVal == null || cbVal == ""){
		alert("请点选要增加下属的建制。");
		return false;
	}
	var njS = document.getElementById('nj').value;
	var jxjzmcS = "";
	var jxjzdjS = "";
	var jxssjzS = "";
	
	dwr.engine.setAsync(false);
	getOtherData.getJxjzDate(cbVal,njS,function(data){
       if(data!=null){
       	jxjzmcS=data[1];
       	jxjzdjS=data[2];
       	jxssjzS=data[6].replace("-","");
       }
    });
    dwr.engine.setAsync(true);
    
    if (jxjzdjS == jz[jz.length-2]){
    	alert(jxjzmcS+"已经是最小单位，不能再增加。");
    	return false;
    }

    var url = "/xgxt/jxglgt.do";
    if (jxjzdjS != jz[jz.length-3]){
    	url += "?method=addQtjz&sjdm="+cbVal;
    	url += "&ssjz="+jxssjzS+jxjzmcS;
    	url += "&sjdj="+jxjzdjS;
		url += "&nj="+njS;
		return showTopWin(url,450,400);
    } else {
    	url += "?method=addBjjz&sjdm="+cbVal;
    	url += "&ssjz="+jxssjzS+jxjzmcS;
    	url += "&nj="+njS;
		return showTopWin(url,650,400);
    }
}

function mod(){
	var cbVal = document.getElementById('cbVal').value;
	var allJz = document.getElementById('allJz').value;
	if (cbVal == null || cbVal == ""){
		alert("请点选要修改的建制。");
		return false;
	}
	var njS = document.getElementById('nj').value;
	var jxjzmcS = "";
	var jxjzdjS = "";
	var jxssjzS = "";
	var jz = new Array();
	jz = allJz.split("-");
	
	dwr.engine.setAsync(false);
	getOtherData.getJxjzDate(cbVal,njS,function(data){
       if(data!=null){
       	jxjzmcS=data[1];
       	jxjzdjS=data[2];
       	jxssjzS=data[6];
       }
    });
    dwr.engine.setAsync(true);
    
    var url = "/xgxt/jxglgt.do";
    if (jxjzdjS != jz[jz.length-2]){
    	url += "?method=modJxjz&bzdm="+cbVal;
		url += "&nj="+njS;
		return showTopWin(url,450,350);
    } else {
    	alert(jxjzmcS+"已经是最小建制单位，若想修改，请点击其上级建制的'增加下属建制'");
    }
	
}

function del(){
	var cbVal = document.getElementById('cbVal').value;
	var allJz = document.getElementById('allJz').value;
	if (cbVal == null || cbVal == ""){
		alert("请点选要修改的建制。");
		return false;
	}
	var njS = document.getElementById('nj').value;
	var jxjzmcS = "";
	var jxjzdjS = "";
	var jxssjzS = "";
	var jz = new Array();
	jz = allJz.split("-");
	
	dwr.engine.setAsync(false);
	getOtherData.getJxjzDate(cbVal,njS,function(data){
       if(data!=null){
       	jxjzmcS=data[1];
       	jxjzdjS=data[2];
       	jxssjzS=data[6];
       }
    });
    dwr.engine.setAsync(true);
     if (jxjzdjS != jz[jz.length-2]){
    	if (!confirm("删除该建制会同时删除下属建制信息，确定要删除所选建制？")){
			return false;
		}
    } else {
    	alert(jxjzmcS+"已经是最小建制单位，若想修改，请点击其上级建制的'增加下属建制'");
    	return false;
    }
	
	refreshForm('/xgxt/jxglgt.do?method=jxbz&act=del');
}

function jxbzPrint(){
	var nd = $("nj").value;
	var url = "/xgxt/jxglgt.do?method=jxbzPrint&nd="+nd;
	window.open(url);
	
}

function jxbzJhPrint(){
		var d_width = document.body.clientWidth;
		var d_height = document.body.clientHeight ;
		var d_left = 0;
		var d_top = 0;
		var d_color = "#EEF4F9";
		var d_width_top = 250;
		var d_height_top = 120;
		var d_left_top = (d_width - d_width_top) / 2;
		var d_top_top = (d_height - d_height_top)/ 2;
		var d_color_top = "#EEF4F9";
		dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
		dd_html += "</div>";
		dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
		dd_html += "<table width='300' class='tbstyle'>";
		dd_html += "<thead>";
		dd_html += "<tr height='30'>";
		dd_html += "<td colspan='2'>";
		dd_html += "----------------确认军训学年---------------";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "</thead>";
		dd_html += "<tbody>";
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "学年:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='xn' id ='xn' onchange='chXn(this.value)'>" 
		dd_html += $('xn').innerHTML;
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr bgcolor='EEF4F9'>";
		dd_html += "<td colspan='2' align='center'>";
		dd_html += "<button type='button' class='button2' onclick='printJhExcel()'>确定</button>";
		dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
		dd_html += "<button type='button' class='button2' onclick='closeAdd1()'>关闭</button>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tbody>";
		dd_html += "</table>";
		dd_html += "</div>";
		tmpdiv1.innerHTML = dd_html;
}

function jxbzGtPrint(){
		var d_width = document.body.clientWidth;
		var d_height = document.body.clientHeight ;
		var d_left = 0;
		var d_top = 0;
		var d_color = "#EEF4F9";
		var d_width_top = 250;
		var d_height_top = 120;
		var d_left_top = (d_width - d_width_top) / 2;
		var d_top_top = (d_height - d_height_top)/ 2;
		var d_color_top = "#EEF4F9";
		dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
		dd_html += "</div>";
		dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
		dd_html += "<table width='300' class='tbstyle'>";
		dd_html += "<thead>";
		dd_html += "<tr height='30'>";
		dd_html += "<td colspan='2'>";
		dd_html += "----------------确认军训学年---------------";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "</thead>";
		dd_html += "<tbody>";
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "学年:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='xn' id ='xn' onchange='chXn(this.value)'>" 
		dd_html += $('xn').innerHTML;
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr bgcolor='EEF4F9'>";
		dd_html += "<td colspan='2' align='center'>";
		dd_html += "<button type='button' class='button2' onclick='printExcel()'>确定</button>";
		dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
		dd_html += "<button type='button' class='button2' onclick='closeAdd1()'>关闭</button>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tbody>";
		dd_html += "</table>";
		dd_html += "</div>";
		tmpdiv1.innerHTML = dd_html;
}

function printExcel(){
	var xn = $("xnV").value;
	if(xn == ""){
		alert(" 请确认学年");
		return false;
	}
	var url = "/xgxt/jxglgt.do?method=jxbzGtPrint&xn="+xn;
	window.open(url);
}

function printJhExcel(){
	var xn = $("xnV").value;
	if(xn == ""){
		alert(" 请确认学年");
		return false;
	}
	var url = "/xgxt/jxglgt.do?method=jxbzJhPrint&xn="+xn;
	window.open(url);
}
function chXn(xn){
	$("xnV").value = xn;
}
</script>
	<body>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>
			<html:form action="jxglgt.do?method=jxbz" method="post">
				<div class="title">
					<div class="title_img" id="title_m">
						当前所在位置：
						<bean:write name="title" />
					</div>
				</div>
				<logic:present name="jz">
					<p align="center">
						已配置的建制数量小于2，请在代码维护处进行维护
					</p>
				</logic:present>
				<logic:notPresent name="jz">
				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType" scope="request"/>" />
				<input type="hidden" id="cbVal" name="cbVal" value="" />
				<input type="hidden" id="xnV" name="xnV" value="" />
				<input type="hidden" id="allJz" name="allJz"
					value="<bean:write name="allJz" scope="request"/>" />
				<logic:present name="isDel">
					<logic:match value="is" name="isDel">
						<script language="javascript">
	         				alert("删除成功！");
	         			</script>
					</logic:match>
					<logic:match value="no" name="isDel">
						<script language="javascript">
	         				alert("删除失败！");
	         			</script>
					</logic:match>
				</logic:present>
				<table width="100%" id="rsTable" class="tbstyle">
					<tr>
						<td width="55%" bgcolor="#D0E0EE">
							年级：
							<html:select name="rsT" property="nj"
								onchange="refreshForm('/xgxt/jxglgt.do?method=jxbz')"
								style="width:60px;padding-left:80px">
								<html:options collection="njList" property="nd"
									labelProperty="nd" />
							</html:select>
							<html:select property="xn" style="display: none">
								<html:option value=""></html:option>
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
							<button type="button" onclick="refreshForm('/xgxt/jxglgt.do?method=jxbz')" id="search_go"
								style="display: none" ></button>
							&nbsp;
							<button type="button" class="button2" onclick="addMax('/xgxt/jxglgt.do?method=addMaxjz');"
								style="width:90px">
								增加<bean:write name="maxJz"/>建制
							</button>
							&nbsp;
							<button type="button" class="button2" onclick="addQt();"
								style="width:90px">
								增加下属建制
							</button>
							&nbsp;
							<button type="button" class="button2" onclick="mod();">
								修改
							</button>
							&nbsp;
							<button type="button" class="button2" onclick="del();">
								删除
							</button>
						</td>
						<td align="center" width="50%" bgcolor="#D0E0EE">
							建制详细信息
						</td>
					</tr>
					<tr>
						<td rowspan="2" height="450px">
							<div style="height:100%; width:100%;overflow-x:hidden;overflow-y:auto">
								<div style="padding:4px;height:100%">
									<logic:notEqual name="menuListTop" value="]">
										<script language="javascript" >var TREE_ITEMS = <%=request.getAttribute("menuListTop")%>;</script>
										<script language="JavaScript" src="js/tree_jx_gt.js" ></script>
										<script language="JavaScript" src="js/tree_tpl_jx.js" ></script>
										<script language="JavaScript" >new tree(TREE_ITEMS, tree_tpl);</script>
										<iframe   style="display:none"   name=if0   ></iframe>   
									</logic:notEqual>
									<logic:equal name="menuListTop" value="]">
										<div align="center">
											<br />
											<br />
											无建制数据!
										</div>
									</logic:equal>
								</div>
							</div>
						</td>
						<td>
							<table width="100%" id="rsTable" class="tbstyle" bgcolor="#D0E0EE">
								<tr>
									<td>
										<div align="center">
											军训学年
										</div>
									</td>
									<td>
										<div align="center">
											<span id="xn"><bean:write name="xn"scope="request" /></span>
										</div>
									</td>
								</tr>
								<tr>
									<td width="50%">
										<div align="center">
											建制代码
										</div>
									</td>
									<td width="50%">
										<div align="center">
											<span id="jxjzdm"><bean:write name="bzdm"scope="request" /></span>
											<input type="hidden" name="jxjzdmV" styleId="jxjzdmV" value="<bean:write name="bzdm"scope="request" />"/>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<div align="center">
											建制名称
										</div>
									</td>
									<td>
										<div align="center">
											<span id="jxjzmc"><bean:write name="bzmc"scope="request" /></span>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<div align="center">
											建制级别
										</div>
									</td>
									<td>
										<div align="center">
											<span id="jxjzdjmc"><bean:write name="bzdjmc"scope="request" /></span>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<div align="center">
											指导员
										</div>
									</td>
									<td>
										<div align="center">
											<span id="jxzdy"><bean:write name="zdy"scope="request" /></span>
											<input type="hidden" name="jxzdyV" styleId="jxzdyV" value="<bean:write name="zdy"scope="request" />"/>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<div align="center">
											教官
										</div>
									</td>
									<td>
										<div align="center">
											<span name="jxjgmc" id="jxjgmc"><bean:write name="jgmc"scope="request" /></span>
											<input type="hidden" name="jxjgmcV" styleId="jxjgmcV" value="<bean:write name="jgmc"scope="request" />"/>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<div align="center">
											所属
										</div>
									</td>
									<td>
										<div align="center">
											<span id="jxssjz"><bean:write name="ssjz"scope="request" /></span>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<div align="center">
											性别
										</div>
									</td>
									<td>
										<div align="center">
											<span id="xb"><bean:write name="xb"scope="request" /></span>
											<input type="hidden" name="xbV" styleId="xbV" value="<bean:write name="xb"scope="request" />"/>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<div align="center">
											备注
										</div>
									</td>
									<td>
										<div align="center">
											<span id="jxbz"><bean:write name="bz"scope="request" /></span>
										</div>
									</td>
								</tr>
							</table>
							<logic:present name="printdm">
							<logic:equal name="printdm" value="13022">
							<div align="center">
								<button type="button" class="button2" onclick="jxbzPrint();">
									学生军训编制分配表
								</button>
							</div>
							</logic:equal>
							<logic:equal name="printdm" value="12061">
								<div align="center">
									<button type="button" class="button2" onclick="jxbzJhPrint();">
										军训编制分配表
									</button>
								</div>
							</logic:equal>
							</logic:present>
							<logic:notPresent name="printdm">
								<div align="center">
									<button type="button" class="button2" onclick="jxbzGtPrint();">
										共通学生军训编制分配表
									</button>
								</div>
							</logic:notPresent>
						</td>
					</tr>
					<tr>
						<td height="300px">
							<div align="center">
								&nbsp;
							</div>
						</td>
					</tr>
				</table>
				</logic:notPresent>
				<div id="tmpdiv1"></div>
			</html:form>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
