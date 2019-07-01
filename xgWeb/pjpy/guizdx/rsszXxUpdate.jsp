<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>

	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript"  src="js/pjpy/pjpyFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyInfo.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript">	

//重置<bean:message key="lable.xsgzyxpzxy" />列表
function czXyList(){
	var objId = "xyRight";
	DWRUtil.removeAllOptions(objId);			
}

//录入比例的DIV
function showBlDiv(){
	var d_width = document.body.clientWidth;
	var d_height = document.body.clientHeight ;
	var d_left = 0;
	var d_top = 0;
	var d_color = "#EEF4F9";
	var d_width_top = 200;
	var d_height_top = 120;
	var d_left_top = (d_width - d_width_top) /1.8;
	var d_top_top = (d_height - d_height_top) / 2;
	var d_color_top = "#EEF4F9";
	dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
	dd_html += "</div>";
	dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
	dd_html += "<table width='50' class='tbstyle'>";
	dd_html += "<thead>";
	dd_html += "<tr height='30'>";
	dd_html += "<td>";
	dd_html += "请设置比例";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "</thead>";
	dd_html += "<tbody>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='left'>";
	dd_html += "<input type='text' name='bl' id='bl' onblur='chMax(this,100);setBlValue(this.value)' onkeypress='return onlyNum(this,3)' style='width:30%;ime-mode:disabled'/>%";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr bgcolor='EEF4F9'>";
	dd_html += "<td align='center'>";
	dd_html += "<button class='button2' onclick='saveXybl()';>确定</button>";
	dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
	dd_html += "<button class='button2' onclick='closeAdd1()'>关闭</button>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tbody>";
	dd_html += "</table>";
	dd_html += "</div>";
	tmpdiv1.innerHTML = dd_html;
}

function setBlValue(value){
	$("szbl").value=value;
}

function saveXybl(){
	var nj = $("nj").value;
	var bl = $("bl").value;
	var jxjdm = $("jxjdm").value;
	var jxjmc = "";
	var mc = "";
	var msg = "";
	var url = "/xgxt/guizdxPjpy.do?method=rsszXxUpdate&doType=save";
	
	if(bl == ""){
		alert("比例不能不能为空，请确认！！");
		return false;
	}
	
	if(jxjdm == ""){
		alert("奖学金(荣誉称号)类别不能为空，请确认！！");
		return false;
	}
	
	for(var i=0;i<$("jxjdm").options.length;i++){
		if($("jxjdm").options[i].selected){
			jxjmc=$("jxjdm").options[i].text;
		}
	}

	for(var i = 0 ; i < $("xyRight").options.length; i++){
		var tmp = document.createElement("input");
		tmp.type = "hidden";
		tmp.name = "szbm";
		tmp.value = $("xyRight").options[i].value;
		document.forms[0].appendChild(tmp);
	}
	
	if(nj =="" &&  $("xyRight").options.length == "0"){
		msg = "将全体<bean:message key="lable.xsgzyxpzxy" />的"+jxjmc+"人数比例设置为" + bl + "%,请确认";
	}else if(nj !="" && $("xyRight").options.length == "0"){
		msg = "将" + nj + "年级全体<bean:message key="lable.xsgzyxpzxy" />的"+jxjmc+"人数比例设置为" + bl + "%,请确认";
	}else if($("xyRight").options.length != "0"){
		msg = "将所选<bean:message key="lable.xsgzyxpzxy" />的"+jxjmc+"人数比例设置为" + bl + "%,请确认";
	}
	
	if (confirm(msg)) {
		saveUpdate(url,'');
	}
}

	</script>
	</head>
	<body onload="">
		<html:form action="/guizdxPjpy">
			<!-- 隐藏域 -->
			<%@ include file="/pjpy/hiddenValue.jsp"%>
			<input type="hidden" name="szbl"id="szbl" />
			<input type="hidden" name="xn" id="xn" value="${xn }"/>
			<input type="hidden" name="xq" id="xq" value="无"/>
			<input type="hidden" name="bmlx" id="bmlx" value="xy"/>
			<html:hidden property="szlx" styleId="szlx"/>
			<!-- 隐藏域 end-->
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<div class="tab">
				<table width="100%"  border="0" class="formlist">
				 <thead>
    				<tr>
        				<th colspan="6"><span>${xn}学年奖学金人数设置</span></th>
        			</tr>
   				 </thead>
				<tbody>
				<tr style="height: 23px">
					<th width="20%">
						年级
					</th>
					<td  width="30%">
						<html:select property="nj" style="" onchange="setXylist();czXyList();">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj" labelProperty="nj" />
						</html:select>
					</td>
					<!-- 奖学金 -->
					<logic:equal name="mklx" value="jxj">
						<th width="20%">
							奖学金类别
						</th>
						<td width="30%">
							<html:select property="jxjdm" style="" styleId="jxjdm">
								<html:options collection="jxjList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
					</logic:equal>
					<!-- 奖学金 end-->
					<!-- 荣誉称号 -->
					<logic:equal name="mklx" value="rych">
						<th  width="20%">
							荣誉称号类别
						</th>
						<td width="30%">
							<html:select property="jxjdm" style="" styleId="jxjdm">
								<html:options collection="rychList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
					</logic:equal>
					<!-- 荣誉称号 end-->
				</tr>
				<tr>
					<td colspan="4">
						<table width="100%">
							<tr>
								<th width="10%" >
									学<br><br><br>院
								</th>
								<td width="30%">
									<html:select property="xydm" style="width:100% " styleId="xyLeft" size="12" onchange="">
									</html:select>
								</td>
								<td width="10%">
									<button  style="width:100%" id="addBtn" onclick="addRightFrame('xyLeft','xyRight')">
										&gt;&nbsp;&gt;
									</button>
									<br />
									<br />
									<br />
									<button  style="width:100%" id="delBtn" onclick="addLeftFrame('xyLeft','xyRight')">
										&lt;&nbsp;&lt;
									</button>
								</td>
								<th width="10%">
									设<br>置<br>学<br>院
								</th>
								<td width="30%">
									<html:select property="xh" style="width:100%" styleId="xyRight"  size="12">
									</html:select>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr bgcolor="EEF4F9" align="center">
					<td colspan="4">
					<button id="buttonSave" onclick="showBlDiv()"
						style="width: 80px">
						保&nbsp;&nbsp;存
					</button>
					&nbsp;&nbsp;
					<button   id="buttonClose" onclick="Close();return false;"
						style="width: 80px">
						关&nbsp;&nbsp;闭
					</button>
				</tr>
			</table>
			<div id="tmpdiv1"></div>
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
