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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="/xgxt/js/function.js"></script>
	<script language="javascript" src="/xgxt/js/jsFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="/xgxt/js/pjpy/pjpy_zjsyzy.js"></script>
	<script type="text/javascript">
		function chkView(){
   			var url = "/xgxt/zysyyygl.do?method=viewYyxx&pkVStr=";
   			var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
			url += pk;
    		showTopWin(url,"700","580");              		                  
 		}
		function queryData(){
			refreshForm('/xgxt/zysyyygl.do?method=zyyysh&doType=query');
		}
		function sqsh(value){
			if(value.length>10){
				alert("审核批复最长不能超过10个字!");
				return false;
			}
			var num = 0;
			var pkVStr = '!@!';	 
				var pks = document.getElementsByName('pkV');
				for(var i=0; i<pks.length; i++){
					if(pks[i].checked == true){
						num++;
						pkVStr +=pks[i].value+'!@!'; 
					}
				}
				if(num == 0){
					alert("请选择你要审核的记录！");
					return  false;
				}else{
					$('pkVStr').value=pkVStr;
				}
			$('pkVStr').value=pkVStr;
   			var url = "/xgxt/zysyyygl.do?method=zyyysh&doType=sqsh&flag="+value;
   			refreshForm(url);              		                  
 		}
 		
 		function checkXX(value){
 			if("11078"==$("xxdm").value){
				shpf(value);
			}else{
				sqsh(value)
			}
 		}
 		//审核批复
		function shpf(value){
		var num = 0;
		var pks = document.getElementsByName('pkV');
		for(var i=0; i<pks.length; i++){
			if(pks[i].checked == true){
				num++;
			}
		}
		if(num == 0){
			alert("请选择你要审核的记录！");
			return  false;
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
		dd_html += "----------------请选填写审核批复---------------";
		dd_html += "</td>";
			dd_html += "</tr>";
		dd_html += "</thead>";
		dd_html += "<tbody>";
	
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "审核批复:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<input type='text' name='shpf' id='shpf' maxlength='10'/>";
		dd_html += "</td>";
		dd_html += "</tr>";
	
		dd_html += "<tr bgcolor='EEF4F9'>";
		dd_html += "<td colspan='2' align='center'>";
		dd_html += "<button type='button' class='button2' onclick=\"sqsh('"+value+"')\">确定</button>";
		dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
		dd_html += "<button type='button' class='button2' onclick='closeAdd1()'>关闭</button>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tbody>";
		dd_html += "</table>";
		dd_html += "</div>";
		document.getElementById('tmpdiv1').innerHTML = dd_html;
	}
	</script>
	<body>
		<center>
			<html:form action="/zysyyygl" method="post">
				<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }"/>
				<logic:equal value="no" name="view">
					<br>
					<br>
					<br>
					<p align="center">
						<font color="red" size="2">该页面只允许学校级别用户访问！</font>
					</p>
				</logic:equal>
				<logic:notEqual value="no" name="view">
					<input type="hidden" name="pkVStr" id="pkVStr" value="" />
					<div class="title">
						<div class="title_img" id="title_m">
							当前所在位置：日常事务 - 资源使用预约管理 - 资源预约审核
						</div>
					</div>
					<fieldset>
						<legend>
							查 询
						</legend>
						<table width="100%" class="tbstyle">
							<thead>
								<tr>
									<td align="left">
										使用部门：
										<html:select property="bm" style="width:200px">
											<html:option value="">--请选择--</html:option>
											<html:options property="bmdm" labelProperty="bmmc"
												collection="bmList" />
										</html:select>
										&nbsp;&nbsp;&nbsp; 预约设备:
										<html:select property="yysb">
											<html:option value="">--请选择--</html:option>
											<html:options property="dm" labelProperty="mc"
												collection="sbList" />
										</html:select>
										&nbsp;&nbsp;&nbsp; 预约场地：
										<html:select property="cddm" style="width:200px">
											<html:option value="">--请选择--</html:option>
											<html:options property="dm" labelProperty="mc"
												collection="cdList" />
										</html:select>

									</td>
									<td width="10" rowspan="2" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="button2" style="height: 40px" id="search_go"
											onclick="queryData();">
											查询
										</button>
									</td>
								</tr>
								<tr>
									<td align="left">
										预约日期：
										<html:text property="yyrq" styleId="yyrq" readonly="true"
											onclick="this.value='';return showCalendar('yyrq','y-mm-dd');"
											onblur="getRqVal('yyrq')"></html:text>
										&nbsp;&nbsp;&nbsp;审核状态：
										<html:select property="xxsh" styleId="xxsh" style="width:80px">
											<html:option value=""></html:option>
											<html:option value="未审核">未审核</html:option>
											<html:option value="通过">通过</html:option>
											<html:option value="不通过">不通过</html:option>
										</html:select>
									</td>
								</tr>
							</thead>
						</table>
					</fieldset>
					<div id="tmpdiv1">
				
					</div>
					<logic:empty name="rs">
						<br />
						<br />
						<p align="center">
							未找到任何记录！
						</p>
					</logic:empty>
					<logic:notEmpty name="rs">
						<fieldset>
							<legend>
								<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
							</legend>
							<table width="100%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand" >
										<td align="center">
											<input type="checkbox" name="all" value="all"
												onclick="chec()">
										</td>
										<logic:iterate id="tit" name="topTr" offset="1">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" style="cursor:hand"
										ondblclick="chkView()"
										bgcolor="<bean:write name="s" property="bgcolor" />">
										<td align="center">
											<input type="checkbox" name="pkV"
												value="<bean:write name="s" property="guid"/>">
										</td>
										<td align="center">
											<bean:write name="s" property="bmmc" />
										</td>
										<logic:equal name="xxdm" value="11078">
										<td align="center">
											<bean:write name="s" property="sqsj" />
										</td>
										</logic:equal>
										<td align="center">
											<bean:write name="s" property="yyrq" />
										</td>
										<td align="center">
											<bean:write name="s" property="yysjd" />
										</td>
										<td align="center">
											<bean:write name="s" property="fzr" />
										</td>
										<td align="center">
											<bean:write name="s" property="lxdh" />
										</td>
										<td align="center">
											<bean:write name="s" property="sqr" />
										</td>
										<logic:equal name="xxdm" value="11078">
										<td align="center">
											<bean:write name="s" property="shpf" />
										</td>
										</logic:equal>
										<td align="center">
											<bean:write name="s" property="xxsh" />
										</td>
									</tr>
								</logic:iterate>
							</table>
						</fieldset>
						<TABLE width="99%" id="rsTable" class="tbstyle">
								<TR>
									<TD height=3></TD>
								</TR>
								<TR>
									<TD>
										<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=rcswgzdxForm"></jsp:include>
									</TD>
								</TR>
								<TR>
									<TD height=3></TD>
								</TR>
						</TABLE>
						<br />
						<br />
					</logic:notEmpty>
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							&nbsp;&nbsp;
							<button type="button" class="button2" onclick="checkXX('tg')">
								审核通过
							</button>
							&nbsp;&nbsp;
							<button type="button" class="button2" onclick="checkXX('btg')">
								审核不通过
							</button>
						</div>
					</center>
				</logic:notEqual>
			</html:form>
			<div id="tmpdiv"></div>
		</center>
	</body>
	<logic:notEqual name="view" value="no">
	<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "98%";
					window.setInterval("initBTNTool('btn')",1);
	</script>
	</logic:notEqual>
	<logic:equal value="true" name="result">
		<script language="javascript">
			alert('操作成功！');
			document.getElementById('search_go').click();
		</script>
	</logic:equal>
	<logic:equal value="false" name="result">
		<script language="javascript">
			alert('操作失败！');
		</script>
	</logic:equal>
</html>
