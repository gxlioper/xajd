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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body onload="xyDisabled('xy');removeXnXq();bzrLoad();">
		<script language="javascript">
		function queryOne() {
	if((curr_row == null) || (curr_row == "")){
		return false;
	}
	var xh = curr_row.getElementsByTagName("input")[2].value;
	var url = "/xgxt/stu_info_details.do?xh="+xh;
	showTopWin(url, 800, 600);
}
		var Rows=new Array();	//所有选中的行对象
		var ShiftStartRow="";		//Shift多选时存储开始行对象
		var cur_bgc = "#ffdead";//选中行背景（字符串）
		
function rowOver(objTr) {//
	curr_row = objTr;
}

function rowOut(objTr) {//
	curr_row = null;
}
			function xz_viewMore(curr_row)
			{
				var xxdm=document.all['xxdm2'].value;
				var xg_xxdm = document.getElementById("xxdm").value;
				var tn = '';
				if ($('tableName')) {
					tn = document.getElementById('tableName').value;
				}
				if("no"==xxdm)
				{	
						viewMore('view');
			}
			}
			function getRqVal(name)
			{
			var rq=document.getElementById(name).value;
			if (rq!="")
			{
				rqs=rq.split("-");
				rq="";
				for (var i=0;i<rqs.length;i++)
				{
					rq+=rqs[i];
				}
				document.getElementById(name).value=rq;
			}
			}
			function chgrychlists(xydms) {
				var xydm = document.getElementById(xydms).value;
				chgRychlist.xyRychList(xydm,function(data) {
							DWRUtil.removeAllOptions('rychdm');			
							var o = [{id:'',labelText:''}];
							DWRUtil.addOptions('rychdm',o,'id','labelText');
							for(var i=0;i<data.length;i++){
								o = [{id:data[i].rychdm,labelText:data[i].rychmc}];
							DWRUtil.addOptions('rychdm',o,'id','labelText');
							}
				});
			}  		
		function chgJxjlists(xydms) {
			var xydm = document.getElementById(xydms).value;
			chgJxjlist.xyJxjList(xydm,function(data) {
						DWRUtil.removeAllOptions('jxjdm');			
						var o = [{id:'',labelText:''}];
						DWRUtil.addOptions('jxjdm',o,'id','labelText');
						for(var i=0;i<data.length;i++){
							o = [{id:data[i].jxjdm,labelText:data[i].jxjmc}];
						DWRUtil.addOptions('jxjdm',o,'id','labelText');
						}
			});
		}
		
	function dyzz(tab){
		var checkBoxArr = document.getElementsByName("pks");
		var yzchk=true;
		for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked){
					yzchk =false;
					break;
				}
		}
		if(yzchk==true){
			alert("请勾选需批量转入的学生");
			return;
		}
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
		if(tab=="rdjjfzxxb"){
		dd_html += "-----------------转为预备党员时间----------------";
		}else{
		dd_html += "-------------------转为党员时间------------------";
		}
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "</thead>";
		dd_html += "<tbody>";
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "时间:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<input type='text' name='zhsj' id='zhsj' onblur='dateFormatChg(this)' onclick='time(this.id)'  style='cursor:hand;' readonly='true'/>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr bgcolor='EEF4F9'>";
		dd_html += "<td colspan='2' align='center'>";
		dd_html += "<button type='button' class='button2' onclick=saveDjzh('";
		dd_html += tab;
		dd_html += "');>确定</button>";
		dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
		dd_html += "<button type='button' class='button2' onclick='closeAdd2()'>关闭</button>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tbody>";
		dd_html += "</table>";
		dd_html += "</div>";
		tmpdiv.innerHTML = dd_html;
	}
	
	function saveDjzh(tab) {
		refreshForm("/xgxt/jhzyYydx.do?method=djxxPlzh");
	}
	
	function closeAdd2() {
	i = document.getElementsByTagName("select").length;
	for (j = 0; j < i; j++) {
		document.getElementsByTagName("select")[j].style.visibility = "";
	}
	dd_html = "";
	tmpdiv.innerHTML = dd_html;
	}
	
	function time(id){
	return showCalendar(id,'y-mm-dd');
	}
		
		function chSj(){
			if($("gzkssj") && $("gzjssj")){
				var kssj = $("gzkssj").value;
				var jssj = $("gzjssj").value;
				if(kssj !="" && jssj !=""){
					if(kssj > jssj){
						alert("开始时间大于结束时间，请确认");
						return false；
					}
				}
			}
			return true;		
		}
		
		/**全选*/
		function selectAlldt(){
				var checkBoxArr = document.getElementsByName("pks");
				var selall = document.getElementById('cb');
				if(selall.checked==true){
				for(var i=0;i<checkBoxArr.length;i++){
					checkBoxArr[i].checked = true;
				}
			} else {
				for(var i=0;i<checkBoxArr.length;i++){
					checkBoxArr[i].checked = false;
				}
			}
		}
		</script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/chgRychlist.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/chgJxjlist.js'></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>

		<html:form action="/data_search" method="post">
		<input type="hidden" id="tzurl" name="tzurl" onclick="refreshForm('wjcf_BgdGl.do?go=go');"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置:
					<bean:write name="tips" scope="request" />
				</div>
			</div>
			<logic:equal value="view_xslxfszsxx" name="tableName">
				<logic:equal value="stu" name="userType">
					此页面只有学校和<bean:message key="lable.xsgzyxpzxy" />用户可以访问
				</logic:equal>
			</logic:equal>
			<logic:notEqual value="stu" name="userType">
				<div class="rightcontent">

					<fieldset>
						<legend>
							查 询
						</legend>
						<input type="hidden" name="zyV" id="zyV" />
						<input type="hidden" name="bjV" id="bjV" />
						<input type="hidden" id="userType" name="userType"
							value="<bean:write name="userType" scope="request"/>" />
						<input type="hidden" id="tableName" name="tableName"
							value="<bean:write name="tableName" scope="request"/>" />
						<input type="hidden" id="act" name="act"
							value="<bean:write name="act" scope="request"/>" />
						<input type="hidden" id="realTable" name="realTable"
							value="<bean:write name="realTable" scope="request"/>" />
						<input type="hidden" id="pk" name="pk"
							value="<bean:write name="pk" scope="request"/>" />
						<input type="hidden" id="xxdm" name="xxdm"
							value="<bean:write name="xxdm" scope="session"/>" />
						<input type="hidden" id="dxq" name="dxq"
							value="<bean:write name="writeAble" scope="request"/>" />
						<input type="hidden" id="isBzr" name="isBzr"
							value="<bean:write name="isBzr" scope="request"/>" />
						<input type="hidden" id="stab" name="stab" value="stab" />
						<input type="hidden" id="userName" name="userName"
							value="<bean:write name="userName" scope="session"/>" />
							<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }"/>
						<table width="100%" class="tbstyle">
							<logic:present name="showzbdx_xx">
								<input type="hidden" id="xxdm2" name="xxdm2"
									value="<bean:write name="xxdm" scope="request"/>" />
							</logic:present>
							<logic:notPresent name="showzbdx_xx">
								<input type="hidden" id="xxdm2" name="xxdm2" value="no" />
							</logic:notPresent>
							<logic:present name="sfxfzrx">
								<input type="hidden" id="sfxfzrx" name="sfxfzrx"
									value="<bean:write name="sfxfzrx" scope="request"/>" />
							</logic:present>
							<thead>
								<tr>
									<td align="left">
										年级：
										<html:select property="nj" style="width:70px"
											onchange="initZyList();initBjList()" styleId="nj">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>
										<logic:equal value="xshsxfb" name="realTable">
											&nbsp;&nbsp;年份：
											<html:select property="nf" style="width:100px" styleId="nf">
												<html:option value=""></html:option>
												<html:options collection="njList" property="nj"
													labelProperty="nj" />
											</html:select>
										</logic:equal>

										<logic:notEqual value="view_xslxfszsxx" name="tableName">
											<logic:notEqual value="xshsxfb" name="realTable">
												<!--非投保信息查询-->
												<logic:notEqual value="xsbxb" name="realTable">											
												&nbsp;&nbsp;学年：											
												<html:select property="xn" style="width:90px" styleId="xn"
													onchange="">
													<html:options collection="xnList" property="xn"
														labelProperty="xn" />
												</html:select>
												</logic:notEqual>
												<!--end非投保信息查询-->
											</logic:notEqual>
										</logic:notEqual>
										
										<%--学生信息维护中无年度--%>
										<logic:notEqual value="view_xslxfszsxx" name="tableName">
										<logic:notEqual value="13108" name="xxdm">
											&nbsp;&nbsp;年度：
											<html:select property="nd" styleId="nd">
											<html:options collection="ndList" property="nd"
												labelProperty="nd" />
											</html:select>
										</logic:notEqual>
										</logic:notEqual>
										<logic:notPresent name="zjujxjrych">
											<logic:notEqual value="view_xslxfszsxx" name="tableName">
												<logic:notEqual value="xshsxfb" name="realTable">
												    <!--非投保信息查询-->
													<logic:notEqual value="xsbxb" name="realTable">	
														&nbsp;&nbsp;学期：
														<html:select property="xq" style="width:60px" styleId="xq">
															<html:option value=""></html:option>
															<html:options collection="xqList" property="xqdm"
																labelProperty="xqmc" />
														</html:select>										
													</logic:notEqual>
													<!--end非投保信息查询-->
												</logic:notEqual>
											</logic:notEqual>
										</logic:notPresent>
										<logic:notEqual value="bjhj" name="bjhjType">
										&nbsp;&nbsp;&nbsp;&nbsp;
										学号：
										<html:text property="xh" style="width:120px"></html:text>
										&nbsp;&nbsp;姓名：
										<html:text property="xm" style="width:85px"></html:text>
										</logic:notEqual>
										<logic:present name="xsccList">
											</br>
											学生层次：
											<html:select property="xsccdm" style="width:90px">
												<html:option value=""></html:option>
												<html:options collection="xsccList" property="xsccdm"
													labelProperty="xsccmc" />
											</html:select>
										</logic:present>
										<logic:equal name="tableName" value="view_rdjjfzxx">
											<logic:equal name="xxdm"value="12061">
											&nbsp;&nbsp;获得时间段:
											<html:text  property="gzkssj" styleId="gzkssj"
												onblur="dateFormatChg(this);" style="cursor:hand;width:10%"
												onclick="return showCalendar('gzkssj','y-mm-dd');"/>--	
											<html:text  property="gzjssj" styleId="gzjssj"
												onblur="dateFormatChg(this);" style="cursor:hand;width:10%"
												onclick="return showCalendar('gzjssj','y-mm-dd');"/>	
											</logic:equal>
										</logic:equal>
									</td>
									<td width="10" rowspan="2" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<input type="hidden" name="tab" id="tab" value="qtjxj" />
										<button type="button" class="button2" style="height: 40px" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/data_search.do')">
											查询
										</button>
									</td>
									<logic:present name="showblsz">
										<td width="10" rowspan="2" align="center" valign="middle">
											<button type="button" class="button2" style="height: 40px; width: 40px"
												onclick="showTopWin('/xgxt/zhszcpblsz.do',750,250)">
												比例
												<br>
												设置
											</button>
										</td>
									</logic:present>


								</tr>
								<tr>
									<td align="left" nowrap>
										<bean:message key="lable.xsgzyxpzxy" />：
										<logic:equal value="10827" name="xxdm">
											<logic:equal value="xsjxjb" name="realTable">
												<html:select property="xydm" style="width:180px"
													styleId="xy"
													onchange="initZyList();initBjList();chgJxjlists('xy');">
													<html:option value=""></html:option>
													<html:options collection="xyList" property="xydm"
														labelProperty="xymc" />
												</html:select>
											</logic:equal>
											<logic:notEqual value="xsjxjb" name="realTable">
												<logic:equal value="xsrychb" name="realTable">
													<html:select property="xydm" style="width:180px"
														styleId="xy"
														onchange="initZyList();initBjList();chgrychlists('xy');">
														<html:option value=""></html:option>
														<html:options collection="xyList" property="xydm"
															labelProperty="xymc" />
													</html:select>
												</logic:equal>
												<logic:notEqual value="xsrychb" name="realTable">
													<html:select property="xydm" style="width:180px"
														styleId="xy" onchange="initZyList();initBjList();">
														<html:option value=""></html:option>
														<html:options collection="xyList" property="xydm"
															labelProperty="xymc" />
													</html:select>
												</logic:notEqual>
											</logic:notEqual>
										</logic:equal>
										<logic:notEqual value="10827" name="xxdm">
											<html:select property="xydm" style="width:180px" styleId="xy"
												onchange="initZyList();initBjList()">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
										</logic:notEqual>
										<logic:notEqual value="xyhj" name="xyhjType">
										&nbsp;&nbsp;专业：
										<html:select property="zydm" style="width:180px" styleId="zy"
												onchange="initBjList()">
												<html:option value=""></html:option>
												<html:options collection="zyList" property="zydm"
													labelProperty="zymc" />
											</html:select>
										&nbsp;&nbsp;班级：
										<html:select property="bjdm" style="width:180px" styleId="bj">
												<logic:notEqual value="yes" name="isBzr">
													<html:option value=""></html:option>
												</logic:notEqual>
												<html:options collection="bjList" property="bjdm"
													labelProperty="bjmc" />
											</html:select>
										</logic:notEqual>
									</td>
								</tr>
							</thead>
						</table>
					</fieldset>
					<logic:empty name="rs">
						<p align="center">
							未找到任何记录！
						</p>
					</logic:empty>
					<logic:notEmpty name="rs">
						<fieldset>
							<legend>
								显示记录数：
								<bean:write name="rsNum" />
								&nbsp;
								<font color="blue"><logic:present name="qssj">(<bean:write
											name="qssj" />--</logic:present> <logic:present name="zzsj">
										<bean:write name="zzsj" /> 违纪名单)</logic:present>
									提示：双击一行可以查看详细信息；单击表头可以排序;按住Ctrl可以多选</font>
							</legend>

							<table width="100%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor: hand">
											<td nowrap>
												<input type="checkbox" id="cb" name="cb" onclick="selectAlldt()" />
											</td>
											<logic:iterate id="tit" name="topTr" offset="1">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)" nowrap>
													<bean:write name="tit" property="cn" />
												</td>									
											</logic:iterate>
											<logic:equal value="10290" name="xxdm">
											<logic:iterate id="tit" name="topTr" offset="1" length="2">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)" nowrap>
													<bean:write name="tit" property="cn" />
												</td>									
											</logic:iterate>
											<logic:iterate id="tit" name="topTr" offset="3" length="4">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)" nowrap>
													<bean:write name="tit" property="cn" />
												</td>									
											</logic:iterate>
											
											<logic:iterate id="tit" name="topTr" offset="8" length="1">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)" nowrap>
													<bean:write name="tit" property="cn" />
												</td>									
											</logic:iterate>
											
											</logic:equal>
										<logic:present name="xsjxjb">
											<logic:iterate id="tit" name="topTr" offset="2">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)" nowrap>
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
										</logic:present>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
												<tr onclick="rowMoreClick(this,'',event);" style="cursor: hand"
													ondblclick="xz_viewMore(this)">
													<td align=center>
													<logic:iterate id="v" name="s" offset="0" length="1">
															<input type="hidden" value="<bean:write name="v"/>" />
														</logic:iterate>
															<input type="checkbox" id="pks" name="pks"
																value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
													</td>
													<td>
														
														<logic:iterate id="v" name="s" offset="1" length="1">
															<bean:write name="v" />
														</logic:iterate>
													</td>
													<logic:iterate id="v" name="s" offset="2">
														<td nowrap>
															<bean:write name="v" />
														</td>
													</logic:iterate>
												</tr>
								</logic:iterate>
							</table>
							
							<TABLE width="99%" id="rsTable1" class="tbstyle">
									<TR>
										<TD height=3></TD>
									</TR>
									<TR>
										<TD>
											<jsp:include flush="true"
												page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
										</TD>
									</TR>
									<TR>
										<TD height=3></TD>
									</TR>
								</TABLE>
								<br />
								<br />
						</fieldset>
					</logic:notEmpty>
					<br />
					<br />
					<div id="toolTipLayer"
						style="position: absolute; visibility: hidden" /></div>
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute; left: 1%; top: 100px" width="100%">
							<logic:equal value="yes" name="writeAble" scope="request">
								<logic:notPresent name="showzdjs">
									<logic:equal name="act" value="party">
										<%--end浙江传媒<bean:message key="lable.xsgzyxpzxy" />--%>
										<button type="button" class="button2"
											onclick="showTips('处理数据中，请等待......');refreshForm('/xgxt/party_stuinfo.do');"
											style="width: 90px">
											更新到学生库
										</button>
										&nbsp;
									</logic:equal>
									<logic:equal name="act" value="prepare">
										<button type="button" class="button2"
											onclick="showTips('处理数据中，请等待......');refreshForm('/xgxt/party_stuinfo.do');"
											style="width: 90px">
											更新到学生库
										</button>
										&nbsp;
									</logic:equal>
									<%--begin浙江传媒<bean:message key="lable.xsgzyxpzxy" />--%>
									<logic:equal name="act" value="active">
										<logic:equal name="xxdm" value="11647">
											<button type="button" class="button2"
												onclick="showTopWin('/xgxt/dtjs_zjcm.do?method=fzdxAll&go=go',800,600);" style="width:90px"> 
												发展对象设置
											</button>
										</logic:equal>
									</logic:equal>
									
										
											<button type="button" class="button2" onclick="viewMore('add')"
												style="width: 80px">
												增 加
											</button>
										&nbsp;
										<button type="button" class="button2"
												onclick="<logic:equal value="10827" name="xxdm"><logic:equal value="view_xsrychb" name="tableName">updaterychxx('modi')</logic:equal><logic:notEqual value="view_xsrychb" name="tableName">viewMore('modi')</logic:notEqual></logic:equal><logic:notEqual value="10827" name="xxdm">viewMore('modi')</logic:notEqual>"
												style="width: 80px">
												修 改
										</button>
									&nbsp;
										<button type="button" class="button2" onclick="viewMore('del')"
											style="width: 80px">
											删 除
										</button>
									&nbsp;
									<logic:equal value="rdjjfzxxb" name = "realTable">
										<button type="button" class="button2" onclick="dyzz('rdjjfzxxb')"
											style="width: 120px">
											批量转入预备党员
										</button>	
									</logic:equal>	
									&nbsp;			
									<logic:equal value="ybdyxxb" name = "realTable">
										<button type="button" class="button2" onclick="dyzz('ybdyxxb')"
											style="width: 120px">
											批量转入党员
										</button>	
									</logic:equal>
									&nbsp;			
							</logic:notPresent>
								<logic:notEqual value="no" name="xydel">
<%--									<button type="button" class="button2" onclick="Alldel()" style="width: 80px">--%>
<%--										全部删除--%>
<%--									</button>--%>
						
							</logic:notEqual>
							</logic:equal>
							<logic:notEqual value="view_xslxfszsxx" name="tableName">
								<logic:equal value="yes" name="writeAble" scope="request">
									<button type="button" class="button2" onclick="impAndChkData()"
												style="width: 80px">
											导入数据
									</button>
									&nbsp;
									<button type="button" class="button2" onclick="dataExport()"
										style="width: 80px">
										导出数据
									</button>
									
								</logic:equal>
							</logic:notEqual>
						</div>
					</center>
				</div>
			</logic:notEqual>

			<div id="tmpdiv"></div>
			<%--浙江传媒<bean:message key="lable.xsgzyxpzxy" />--%>
			<logic:present name="dydate">
				<logic:equal name="dydate" value="no">
					<script language="javascript">
      					alert("该学生未过预备期");
	  				</script>
				</logic:equal>
				<logic:equal name="dydate" value="yes">
					<script language="javascript">
      					alert("转正成功");
	  				</script>
				</logic:equal>
			</logic:present>
			<%--end浙江传媒<bean:message key="lable.xsgzyxpzxy" />--%>
		</html:form>
		<logic:equal name="result" value="ok">
			<script language="javascript">
      				alert("操作成功！");
	  		</script>
		</logic:equal>
		<logic:equal name="result" value="no">
			<script language="javascript">
	  				alert("操作失败! ");
	  		</script>
		</logic:equal>
		<!-- 	<script type="text/javascript" src="js/bottomButton.js"></script>  -->
		<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "96%";
					window.setInterval("initBTNTool('btn')",1);

					function hzyrychprint(){
						if (curr_row==null || curr_row=='') {
							alert('请选择要打印的数据行！');
							return;
						} else 
							window.open('hzyrychprint.do?pkValue='+curr_row.cells[0].getElementsByTagName("input")[0].value);
					}
					function hzyprint() {
				     	if (curr_row==null || curr_row=='') 
				     	{
				     		alert('请选择要打印的行数据，单击一行即可!');
				     		return;
				     	}
				     	 else {
				     	 	var url = 'dxjxjsp.do?method=dxjxjsp&pk=';
				     	 	url += curr_row.cells[0].getElementsByTagName("input")[0].value;
				     	 	window.open(url);
				     	 }
				     }
				     function hzyjxjmodi(){
				     	if (curr_row==null || curr_row=='') {
				     		alert('请选择要操作的数据行.');
				     		return;
				     	} else {
				     		var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
				     		
				     		showTopWin('hzzyjxjmodi.do?pkValue='+pkValue,'670','550');
				     	}
				     } 
				     function hzzyrychmodi(){
				     	if (curr_row==null || curr_row=='') {
				     		alert('请选择要操作的数据行.');
				     		return;
				     	} else {
				     		var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
				     		
				     		showTopWin('hzzyrychmodi.do?pkValue='+pkValue,'610','510');
				     	}
				     }
		</script>
	</body>
</html>
