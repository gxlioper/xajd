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
	<script type='text/javascript' src='/xgxt/dwr/interface/getSjxyDtjsDAO.js'></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script type="text/javascript">	
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
	</script>
	<body onload="getSszb()">
		<html:form action="/sjxyDtjsDyxx">
		<input type="hidden" id="doType" name="doType" value="${doType }"/>
		<input type="hidden" id="url" name="url" value="/sjxyDtjsDyxx.do?method=zsdyUpdate&doType=add"/>
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm-xb-xymc-zymc-bjmc-nj-mzmc"/>
		<html:hidden name='rs' property="zzzt" styleId="zzzt"/>
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
							正式党员
						</td>
					</tr>
				</thead>
				<tr style="height: 23px">
					<td align="right">
						<font color="red">*</font>学号：
					</td>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:text name='rs' property="xh" styleId="xh" readonly="true"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:text name='rs' property="xh" styleId="xh" readonly="true" />
						</logic:notEqual>
					</td>
					<td align="right">
						<font color="red">*</font>年度：
					</td>
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
				<tr style="height: 23px">
					<td align="right">
						姓名：
					</td>
					<td align="left">
						<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
					</td>
					<td align="right">
						<font color="red">*</font>学年：
					</td>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:select name="rs" property="xn" style="" styleId="xn">
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
				<tr style="height: 23px">
					<td align="right">
						性别：
					</td>
					<td align="left">
						<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
					</td>
					<td align="right">
						<font color="red">*</font>学期：
					</td>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:select name="rs" property="xq" style="" styleId="xq">
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
				<tr style="height: 23px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						<html:hidden name='rs' property="xydm" styleId="xydm"/>
						<html:text name='rs' property="xymc" styleId="xymc" readonly="true"/>
					</td>
					<td align="right">
						入党时间：
					</td>
					<td align="left">
						<html:text name="rs" property="rdsj" styleId="rdsj" readonly="true"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('rdsj','y-mm-dd');"/>	
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						专业：
					</td>
					<td align="left">
						<html:text name='rs' property="zymc" styleId="zymc" readonly="true"/>
					</td>
					<td align="right">
						转正时间：
					</td>
					<td align="left">
						<html:text name="rs" property="zzsj" styleId="zzsj" readonly="true"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('zzsj','y-mm-dd');"/>	
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						班级：
					</td>
					<td align="left">
						<html:text name='rs' property="bjmc" styleId="bjmc" readonly="true"/>
					</td>
					<td align="right">
						预备党员开始时间：
					</td>
					<td align="left">
						<html:text name="rs" property="ybdykssj" styleId="ybdykssj" readonly="true"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('ybdykssj','y-mm-dd');"/>	
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						民族：
					</td>
					<td align="left">
						<html:text name='rs' property="mzmc" styleId="mzmc" readonly="true"/>
					</td>
					<td align="right">
						预备党员结束时间：
					</td>
					<td align="left">
						<html:text name="rs" property="ybdyjssj" styleId="ybdyjssj" readonly="true"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('ybdyjssj','y-mm-dd');"/>	
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						<font color="red">*</font>学生层次：
					</td>
					<td align="left">
						<html:select name="rs" property="xsccdm" style="" styleId="xsccdm">
							<html:options collection="xsccList" property="dm" labelProperty="mc" />
						</html:select>
					</td>
					<td align="right">
						所属支部：
					</td>
					<td align="left">
						<html:select name="rs" property="zbmc" style="" styleId="zbmc">
							<html:options collection="zbmcList" property="dm" labelProperty="mc" />
						</html:select>
					</td>
				</tr>	
				<tr style="height: 23px">
					<td align="right">
						转正情况：
					</td>
					<td align="left">
						<html:select  name='rs' property="zzlx" style="" styleId="zzlx">
							<html:option value=""></html:option>
							<html:options collection="dyzzlxList" property="en" labelProperty="cn" />
						</html:select>
					</td>
					<td align="right">
						组织所在单位：
					</td>
					<td align="left">
						<html:text name='rs' property="zzdw" styleId="zzdw" maxlength="50"/>
					</td>
				</tr>				
				<tr style="height: 23px">
					<td align="right">
						备注：
					</td>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="bz" styleId="bz" rows="5" style="width: 100%" onblur="chLeng(this,60)"/>
					</td>
				</tr>
				<tr bgcolor="EEF4F9" align="center">
					<td colspan="4">
					<logic:notEqual name="doType" value="view">
					<button type="button" class="button2" id="buttonSave" onclick="saveUpdate('/xgxt/sjxyDtjsDyxx.do?method=zsdyUpdate&doType=save','xh-xn-xq-nd-xsccdm')"
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
