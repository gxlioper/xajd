<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>

	<script type="text/javascript" src="js/pjpy/pjpy_szyqxy.js"></script>
	<script language="javascript"  src="js/sztzFunction.js"></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script type="text/javascript">	
	function dyzz(){
		
		viewTempDiv('选择时间','tmpdiv1',280,160);
			
<!--		var d_width = document.body.clientWidth;-->
<!--		var d_height = document.body.clientHeight ;-->
<!--		var d_left = 0;-->
<!--		var d_top = 0;-->
<!--		var d_color = "#EEF4F9";-->
<!--		var d_width_top = 300;-->
<!--		var d_height_top = 120;-->
<!--		var d_left_top = (d_width - d_width_top) / 2;-->
<!--		var d_top_top = (d_height - d_height_top) / 2;-->
<!--		var d_color_top = "#EEF4F9";-->
<!--		dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";-->
<!--		dd_html += "</div>";-->
<!--		dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";-->
<!--		dd_html += "<div class='tab'><table width='100%' class='formlist'>";-->
<!--		dd_html += "<thead>";-->
<!--		dd_html += "<tr height='30'>";-->
<!--		dd_html += "<th colspan='2'>";-->
<!--		dd_html += "<span>培训时间</span>";-->
<!--		dd_html += "</th>";-->
<!--		dd_html += "</tr>";-->
<!--		dd_html += "</thead>";-->
<!--		dd_html += "<tbody>";-->
<!--		dd_html += "<tr height='30'>";-->
<!--		dd_html += "<th align='right' width='40%'>";-->
<!--		dd_html += "时间:";-->
<!--		dd_html += "</th>";-->
<!--		dd_html += "<td align='left'>";-->
<!--		dd_html += "<input type='text' name='gzkssj' id='gzkssj' onblur='dateFormatChg(this)' onclick='time(this.id)'  style='cursor:hand;' readonly='true'/>";-->
<!--		dd_html += "</td>";-->
<!--		dd_html += "</tr>";-->
<!--		dd_html += "<tr bgcolor='EEF4F9'>";-->
<!--		dd_html += "<td colspan='2' align='center'>";-->
<!--		dd_html += "<button type='button' class='button2' onclick='saveZsdy()';>确定</button>";-->
<!--		dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";-->
<!--		dd_html += "<button type='button' class='button2' onclick='closeAdd1()'>关闭</button>";-->
<!--		dd_html += "</td>";-->
<!--		dd_html += "</tr>";-->
<!--		dd_html += "<tbody>";-->
<!--		dd_html += "</table></div>";-->
<!--		dd_html += "</div>";-->
<!--		tmpdiv1.innerHTML = dd_html;-->
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
		var url = "/xgxt/czxxDtjsDyxx.do?method=pxxxUpdate&doType=add";
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
		saveUpdate('/xgxt/czxxDtjsDyxx.do?method=pxxxUpdate&doType=save','pxmc-pxsj')
	}
	function jd(){
		if($("jd")){
			$("jd").focus();
		}
	}
	</script>
	</head>
	<body onload="jd()">
		<html:form action="/czxxDtjsDyxx">
		<input type="hidden" id="doType" name="doType" value="${doType }"/>
		<input type="hidden" id="rsNum" name="rsNum" value="${rsNum }"/>
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<div class="tab">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>党课培训</span>
						</th>
					</tr>
				</thead>
				
				<tfoot>
			      <tr>
			        <td colspan="4">
			        
			          <logic:notEqual name="doType" value="view">
			        	<div class="bz">"<span class="red">*</span>"为必填项</div>
			          </logic:notEqual>
			          <div class="btn">
			          <logic:notEqual name="doType" value="view">
			          		<button type="button" name="提交" onclick="savePxxx()" id="buttonSave">提 交</button>
			          </logic:notEqual>
			            <button type="button" name="关闭" onclick="window.close();return false;" id="buttonClose">关 闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
				
				<tbody>
				<tr style="height: 23px">
					<th align="right">
					  <logic:notEqual name="doType" value="view">
						<font color="red">*</font>
					  </logic:notEqual>
					  培训名称
					</th>
					<td align="left">
					<logic:notEmpty name="rs">
						<html:text name='rs' property="pxmc" styleId="pxmc" maxlength="25"/>
					</logic:notEmpty>
					<logic:empty name="rs">
						<html:text  property="pxmc" styleId="pxmc" maxlength="25"/>
					</logic:empty>
					</td>
					<th align="right">
					<logic:notEqual name="doType" value="view">
						<font color="red">*</font>
					</logic:notEqual>	
					培训时间
					</th>
					<td align="left">
					<logic:notEmpty name="rs">
						<html:text name='rs' property="pxsj" styleId="pxsj" readonly="true"/>
					</logic:notEmpty>
					<logic:empty name="rs">
						<html:text  property="pxsj" styleId="pxsj" readonly="true"/>
					</logic:empty>
						<logic:equal name="doType" value="add">
						<button type="button" onclick="dyzz()"
						class="btn_01" id="buttonFindStu">
						选择
						</button>
						</logic:equal>
					</td>
				</tr>	
				<tr style="height: 23px">
					<th align="right">
						培训地点
					</th>
					<td align="left" colspan="3">
						<html:text name='rs' property="pxdd" styleId="pxdd" maxlength="50" style="width:100%"/>
					</td>
				</tr>	
				<tr style="height: 23px">
					<th align="right">
						培训内容<br/><font color="red">(限250字)</font>
					</th>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="pxnr" styleId="pxnr" rows="5" cols="80"  onblur="chLeng(this,250)"/>
					</td>
				</tr>	
				<tr style="height: 23px">
					<th align="right">
						备注<br/><font color="red">(限250字)</font>
					</th>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="bz" styleId="bz" rows="5" cols="80" onblur="chLeng(this,250)"/>
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
							<table width="100%" id="rsTable" class="formlist">
								<thead>	
									<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topTr" offset="0" length="7">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)">
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
										<td>
											成绩
										</td>
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
											<logic:iterate id="v" name="s" offset="7">
											<input type="text" name="dkcj" style="width:100%" maxlength="3" value="<bean:write name="v" />"/>
											</logic:iterate>
										</td>
									</tr>
								</logic:iterate>
							</table>
						</fieldset>
						</logic:present>
					</td>
				</tr>
				
				</tbody>
			</table>
			</div>
			<div id="tmpdiv1" style="display: none">
				<div class="tab">
					<table class="formlist">
						<tr>
							<th>培训时间</th>
							<td><input type='text' name='gzkssj' id='gzkssj' onblur='dateFormatChg(this)' onclick='time(this.id)' style='cursor:hand;' readonly='readonly'/></td>
						</tr>
								
						<tfoot>
					      <tr>
					        <td colspan="2">
					          <div class="btn">
					          <button type="button" onclick='saveZsdy()'>确定</button>
					          </div></td>
					      </tr>
					    </tfoot>
					</table>
				</div>
			</div>
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
