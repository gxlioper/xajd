<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script type="text/javascript" src="js/pjpy/pjpy_szyqxy.js"></script>
	<script language="javascript"  src="js/sztzFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script type="text/javascript">	
	function dyzz(){

		var d_width = document.body.clientWidth;
		var d_height = document.body.clientHeight ;
		var d_left = 0;
		var d_top = 0;
		var d_color = "#EEF4F9";
		var d_width_top = 200;
		var d_height_top = 120;
		var d_left_top = (d_width - d_width_top) / 2;
		var d_top_top = (d_height - d_height_top) / 2;
		var d_color_top = "#EEF4F9";
		dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
		dd_html += "</div>";
		dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
		dd_html += "<table width='300' class='tbstyle'>";
		dd_html += "<thead>";
		dd_html += "<tr height='30'>";
		dd_html += "<td colspan='2'>";
		dd_html += "-----------------------培训时间-----------------------";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "</thead>";
		dd_html += "<tbody>";
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "时间:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<input type='text' name='gzkssj' id='gzkssj' onblur='dateFormatChg(this)' onclick='time(this.id)'  style='cursor:hand;' readonly='true'/>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr bgcolor='EEF4F9'>";
		dd_html += "<td colspan='2' align='center'>";
		dd_html += "<button type='button' class='button2' onclick='saveZsdy()';>确定</button>";
		dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
		dd_html += "<button type='button' class='button2' onclick='closeAdd1()'>关闭</button>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tbody>";
		dd_html += "</table>";
		dd_html += "</div>";
		tmpdiv1.innerHTML = dd_html;
	}
	
		
	function time(id){
		return showCalendar(id,'y-mm-dd');
	}
	
	
	function saveZsdy(){
		if($("gzkssj")){
			if($("gzkssj").value == ""){
				alert("请确定培训时间");
				return false;
			}
		}
		var url = "/xgxt/gdby_dtjs.do?method=pxxxUpdate&doType=add";
		$("buttonSave").disabled=true;
		$("buttonClose").disabled=true;
		refreshForm(url);
	}
	
	function savePxxx(){
	
		if($("pxsj").value == ""){
			alert("请确认培训时间");
			return false;
		}
		
		if($("pxmc").value == ""){
			alert("请确认培训名称");
			return false;
		}
		if($("rsNum").value == "" || $("rsNum").value == "0"){
			alert("该培训时间尚未确认名单，请名单确认后再试!");
			return false;
		}
		saveUpdate('/xgxt/gdby_dtjs.do?method=pxxxUpdate&doType=save','pxmc-pxsj')
	}
	</script>
	<body onload="">
		<html:form action="/gdby_dtjs.do">
		<input type="hidden" id="doType" name="doType" value="${doType }"/>
		<input type="hidden" id="rsNum" name="rsNum" value="${rsNum }"/>
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
							党课培训
						</td>
					</tr>
				</thead>
				<tr style="height: 23px">
					<td align="right">
						<font color="red">*</font>培训名称：
					</td>
					<td align="left">
						<html:text name='rs' property="pxmc" styleId="pxmc" maxlength="25"/>
					</td>
					<td align="right">
						<font color="red">*</font>培训时间：
					</td>
					<td align="left">
						<html:text name='rs' property="pxsj" styleId="pxsj" readonly="true"/>
						<logic:equal name="doType" value="add">
						<button type="button" onclick="dyzz()"
						class="btn_01" id="buttonFindStu">
						选择
						</button>
						</logic:equal>
					</td>
				</tr>	
				<tr style="height: 23px">
					<td align="right">
						学年：
					</td>
					<td align="left">
						<html:text name='rs' property="xn" styleId="xn" maxlength="25" value="${xn }"/>
					</td>
					<td align="right">
						培训地点：
					</td>
					<td align="left">
						<html:text name='rs' property="pxdd" styleId="pxdd" maxlength="50"/>
					</td>
				</tr>	
				<tr style="height: 23px">
					<td align="right">
						培训内容：<br><font color="red">(限250字)</font>
					</td>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="pxnr" styleId="pxnr" rows="5" style="width: 100%" onblur="chLeng(this,250)"/>
					</td>
				</tr>	
				<tr style="height: 23px">
					<td align="right">
						备注：<br><font color="red">(限250字)</font>
					</td>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="bz" styleId="bz" rows="5" style="width: 100%" onblur="chLeng(this,250)"/>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<logic:notPresent name="rsList">
							无培训名单
						</logic:notPresent>
						<logic:present name="rsList">
						<fieldset>
							<legend>
								
							</legend>
							<table width="100%" id="rsTable" class="tbstyle">
								<thead>	
									<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topTr" offset="0">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rsList" id="s" indexId="index">
									<tr>
										<logic:iterate id="v" name="s" offset="0" length="1">
										<td align="center">
											<input type="hidden" name="pxmdxh" value="<bean:write name="v"/>"/>
											<bean:write name="v"/>
										</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="6">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<td>
											<logic:iterate id="v" name="s" offset="7" length="1">
											<input type="text" name="xxtd" style="width:100%" maxlength="3" value="<bean:write name="v" />"/>
											</logic:iterate>
										</td>
										<td>
											<logic:iterate id="v" name="s" offset="8" length="1">
											<input type="text" name="xxjl" style="width:100%" maxlength="3" value="<bean:write name="v" />"/>
											</logic:iterate>
										</td>
										<td>
											<logic:iterate id="v" name="s" offset="9" length="1">
											<input type="text" name="xxxg" style="width:100%" maxlength="3" value="<bean:write name="v" />"/>
											</logic:iterate>
										</td>
									</tr>
								</logic:iterate>
							</table>
						</fieldset>
						</logic:present>
					</td>
				</tr>
				<tr bgcolor="EEF4F9" align="center">
					<td colspan="4">
					<logic:notEqual name="doType" value="view">
					<button type="button" class="button2" id="buttonSave" onclick="savePxxx()"
						style="width: 80px">
						保	存
					</button>
					</logic:notEqual>
					&nbsp;&nbsp;
					<button type="button" class="button2" id="buttonClose" onclick="Close();return false;"
						style="width: 80px">
						关	闭
					</button>
					</td>
				</tr>
			</table>
			<div id="tmpdiv1"></div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
						window.dialogArguments.document.getElementById("search_go").click();
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
