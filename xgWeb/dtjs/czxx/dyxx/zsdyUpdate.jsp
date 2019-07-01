<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>

	<script type='text/javascript' src='/xgxt/dwr/interface/getDtjsInfo.js'></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script type="text/javascript">	
	function jd(){
		if($("jd")){
			$("jd").focus();
		}
	}
	function zzgx(){
		var d_width = document.body.clientWidth;
		var d_height = document.body.clientHeight ;
		var d_left = 0;
		var d_top = 0;
		var d_color = "#EEF4F9";
		var d_width_top = 250;
		var d_height_top = 120;
		var d_left_top = (d_width - d_width_top) / 2;
		var d_top_top = (d_height - d_height_top);
		var d_color_top = "#EEF4F9";
		dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
		dd_html += "</div>";
		dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
		dd_html += "<table width='300' class='tbstyle'>";
		dd_html += "<thead>";
		dd_html += "<tr height='30'>";
		dd_html += "<td colspan='2'>";
		dd_html += "----------------组织关系转接---------------";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "</thead>";
		dd_html += "<tbody>";
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "时间:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<input type='text' name='zjsj' id='zjsj' onblur='dateFormatChg(this)' onclick='time(this.id)'  style='cursor:hand;' readonly='true'/>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "转接类型:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='zjlx' id='zjlx' onchange='chZjlx(this.value)'><option value=''><option value='out'>转出</option></select>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "转出地址:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<input type='text' name='zjdz' id='zjdz' maxlength='50'/>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "备注:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<textarea name='zjbz' id='zjbz' rows='3' onblur='chLeng(this,60)'></textarea>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr bgcolor='EEF4F9'>";
		dd_html += "<td colspan='2' align='center'>";
		dd_html += "<button type='button' class='button2' onclick='saveZzgx()';>确定</button>";
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
	
	function saveZzgx(){
		if($("zjsj")){
			if($("zjsj").value == ""){
				alert("请确定转接时间");
				return false;
			}
		}
		if($("zjlx") && $("zjdz")){
			if($("zjlx").value == ""){
				alert("请确定转接类型");
				return false;
			}
			if($("zjlx").value == "out" && $("zjdz").value == ""){
				alert("请确定转出地址");
				return false;
			}
		}

		var url = "/xgxt/zjlgDtjsZsdy.do?method=zsdyUpdate&doType=zz";
		showTips('处理数据中，请等待......');
		$("buttonSave").disabled=true;
		$("buttonZz").disabled=true;
		$("buttonClose").disabled=true;
		refreshForm(url);
	}
	
	function chZjlx(zjlx){
		if(zjlx == "out"){
			$("zjdz").disabled=false;;
		}else{
			$("zjdz").value="";
			$("zjdz").disabled=true;
		}
	}
	function saveZsdy(){
		var xh = $("xh").value;
		var xn = $("xn").value;
		var xq = $("xq").value;
		var nd = $("nd").value;
		var xsccdm = $("xsccdm").value;
		var kssj = $("ybdykssj").value;
		var jssj = $("ybdyjssj").value;
		if(xh==""){
			alert("学号为空，请确认！");
			return false;
		}
		if(xn=="" || xq == ""|| nd == ""){
			alert("年度，学年,学期不能为空！");
			return false;
		}
		if(kssj !="" && jssj != ""){
			if(kssj>jssj){
				alert("预备开始时间大于结束时间，请确认！");
				return false;
			}
		}
		var url = "/xgxt/zjlgDtjsZsdy.do?method=zsdyUpdate&doType=save";
		showTips('处理数据中，请等待......');
		$("buttonSave").disabled=true;
		$("buttonClose").disabled=true;
		refreshForm(url);
	}
	
	function getZbmc(){
		var xh = $("xh").value;
		var zbmc = $("zbmc").value;
		dwr.engine.setAsync(false);
		if(xh !="" && zbmc == ""){
			zjlgZbglDAO.getZbmc(xh,function(data){
				$("zbmc").value=data;		
			});
		}
		dwr.engine.setAsync(true);
	}
	
	function addLxr(){
		var xh = $("xh").value;
		if(xh == ""){
			alert("请确定党员学号");
			return false;
		}
		showTopWin('/xgxt/zjlgDtjsZsdy.do?method=lxrxxManage&blxr='+xh,750,550);
	}
	
	function getZsdyxx(){
		var xh=$("xh").value;
		var xn=$("xn").value;
		var xq=$("xq").value;
		if(xh !="" && xn!="" && xq!=""){
			var tableName="view_sjxy_dyxx";
			var colList =["rdsj","zzsj","ybdykssj","ybdyjssj","bz","zzzt"];
			var pk = "xn||xq||xh";
			var pkValue = xn + xq + xh;
			getDtjsInfo.getDyxxInfo(tableName, pk, pkValue,colList,function(data){
				if(data!=null){
					if(data.bz !=""){
						$("bz").value = data.bz;
					}else{
						$("bz").value = "";
					}
					if(data.rdsj !=""){
						$("rdsj").value = data.rdsj;
					}else{
						$("rdsj").value = "";
					}
					if(data.zzsj !=""){
						$("zzsj").value = data.zzsj;
					}else{
						$("zzsj").value = "";
					}
					if(data.zzzt !=""){
						$("zzzt").value = data.zzzt;
					}else{
						$("zzzt").value = "";
					}
					if(data.ybdykssj !=""){
						$("ybdykssj").value = data.ybdykssj;
					}else{
						$("ybdykssj").value = "";
					}
					if(data.ybdyjssj !=""){
						$("ybdyjssj").value = data.ybdyjssj;
					}else{
						$("ybdyjssj").value = "";
					}
				}
			});
		}
	}
	</script>
	</head>
	
	<body onload="jd()">
		<html:form action="/czxxDtjsDyxx">
		<input type="hidden" id="doType" name="doType" value="${doType }"/>
		<input type="hidden" id="url" name="url" value="/xgxt/czxxDtjsDyxx.do?method=zsdyUpdate&doType=add"/>
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm-xb-xymc-zymc-bjmc-nj-mzmc"/>
		<!-- 常州信息 -->
		<logic:equal name="xxdm" value="12317">
			<input type="hidden" id="tableName" name="tableName" value="view_ybdyxx"/>
			<input type="hidden" id="lx" name="lx" value="预备党员"/>
		</logic:equal>
		<!-- 通用 -->
		<logic:notEqual name="xxdm" value="12317">
			<input type="hidden" id="tableName" name="tableName" value="view_xsjbxx"/>
			<input type="hidden" id="lx" name="lx" value="学生"/>
		</logic:notEqual>
		<html:hidden name='rs' property="zzzt" styleId="zzzt"/>
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
							<span>正式党员</span>
						</th>
					</tr>
				</thead>
				
				<tfoot>
			      <tr>
			        <td colspan="4">
			        	<logic:equal name="doType" value="add">
			          <div class="bz">"<span class="red">*</span>"为必填项</div>
			          </logic:equal>
			          <div class="btn">
			          <logic:notEqual name="doType" value="view">
			          		<button type="button" name="提交" 
			          		onclick="saveUpdate('/xgxt/czxxDtjsDyxx.do?method=zsdyUpdate&doType=save','xh-xn-xq-nd-xsccdm')">提 交</button>
			          </logic:notEqual>
			            <button type="button" name="关闭" onclick="window.close();return false;">关 闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
				
				<tbody>
				<tr >
					<th align="right">
						<logic:equal name="doType" value="add">
						<font color="red">*</font>
						</logic:equal>
						学号
					</th>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:text name='rs' property="xh" styleId="xh" readonly="true"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="sendXx();return false;"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:text name='rs' property="xh" styleId="xh" readonly="true" />
						</logic:notEqual>
					</td>
					<th>
						<logic:equal name="doType" value="add">
						<font color="red">*</font>
						</logic:equal>
						年度
					</th>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:select name="rs" property="nd" style="" styleId="nd">
								<html:options collection="ndList" property="nd" labelProperty="nd" />
							</html:select>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:hidden name='rs' property="nd" styleId="nd"/>
							<html:select name="rs" property="nd" style="" styleId="nd" disabled="true">
								<html:options collection="ndList" property="nd" labelProperty="nd" />
							</html:select>
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<th align="right">
						姓名
					</th>
					<td align="left">
						<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
					</td>
					<th align="right">
						<logic:equal name="doType" value="add">
						<font color="red">*</font>
						</logic:equal>
						学年
					</th>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:select name="rs" property="xn" style="" styleId="xn" onchange="getZsdyxx()">
								<html:options collection="xnList" property="xn" labelProperty="xn" />
							</html:select>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:hidden name='rs' property="xn" styleId="xn"/>
							<html:select name="rs" property="xn" style="" styleId="xn" disabled="true">
								<html:options collection="xnList" property="xn" labelProperty="xn" />
							</html:select>
						</logic:notEqual>
					</td>
				</tr>
				<tr >
					<th align="right">
						性别
					</th>
					<td align="left">
						<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
					</td>
					<th align="right">
						<logic:equal name="doType" value="add">
						<font color="red">*</font>
						</logic:equal>
						学期
					</th>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:select name="rs" property="xq" style="" styleId="xq" onchange="getZsdyxx()">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
							</html:select>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:hidden name='rs' property="xq" styleId="xq"/>
							<html:select name="rs" property="xq" style="" styleId="xq" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
							</html:select>
						</logic:notEqual>
					</td>
				</tr>
				<tr >
					<th align="right">
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left">
						<html:hidden name='rs' property="xydm" styleId="xydm"/>
						<html:text name='rs' property="xymc" styleId="xymc" readonly="true"/>
					</td>
					<th align="right">
						入党时间
					</th>
					<td align="left">
						<html:text name="rs" property="rdsj" styleId="rdsj"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('rdsj','yyyyMMdd');"/>	
					</td>
				</tr>
				<tr >
					<th align="right">
						专业
					</th>
					<td align="left">
						<html:text name='rs' property="zymc" styleId="zymc" readonly="true"/>
					</td>
					<th align="right">
						转正时间
					</th>
					<td align="left">
						<html:text name="rs" property="zzsj" styleId="zzsj" 
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('zzsj','yyyyMMdd');"/>	
					</td>
				</tr>
				<tr >
					<th align="right">
						班级
					</th>
					<td align="left">
						<html:text name='rs' property="bjmc" styleId="bjmc" readonly="true"/>
					</td>
					<th align="right">
						预备党员开始时间
					</th>
					<td align="left">
						<html:text name="rs" property="ybdykssj" styleId="ybdykssj" readonly="true"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('ybdykssj','y-mm-dd');"/>	
					</td>
				</tr>
				<tr >
					<th align="right">
						年级
					</th>
					<td align="left">
						<html:text name='rs' property="nj" styleId="nj" readonly="true"/>
					</td>
					<th align="right">
						预备党员结束时间
					</th>
					<td align="left">
						<html:text name="rs" property="ybdyjssj" styleId="ybdyjssj" readonly="true"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('ybdyjssj','y-mm-dd');"/>	
					</td>
				</tr>		
				
				<tr>
					<th>党内职务</th>
					<td>
						<html:text property="dnzw" maxlength="20" value="${rs.dnzw }"></html:text>
					</td>
					<th></th>
					<td></td>
				</tr>
				
				<%@ include file="dtxxInfo.jsp"%>
				<tr >
					<th align="right">
						备注<br/><font color="red">(限60字)</font>
					</th>
					<td align="left" colspan="3"><br /><html:textarea name='rs' property="bz" styleId="bz" rows="5" style="width: 100%" onblur="chLeng(this,60)"/>
					<br /></td>
				</tr>
			</tbody>
			</table>
			</div>
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
