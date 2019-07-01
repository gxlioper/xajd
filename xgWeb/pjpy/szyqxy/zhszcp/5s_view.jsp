<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type="text/javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
	<script type="text/javascript" src="js/pjpy/pjpy_szyqxy.js"></script>
	<script language="javascript" src="js/comm/commFunction.js"></script>
	<script type="text/javascript">
	function selectAllss(){
	var checkBoxArr = document.getElementsByName("cbv");
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
	function yd() {
		var d_width = document.body.clientWidth;
		var d_height = document.body.clientHeight ;
		var d_left = 0;
		var d_top = 0;
		var d_color = "#EEF4F9";
		var d_width_top = 350;
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
		dd_html += "------------------------选择班级------------------------";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "</thead>";
		dd_html += "<tbody>";
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "年级:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='printnj' id ='printnj' onchange='initPrintZyList();initPrintBjList()'>" 
		dd_html += $('nj').innerHTML;
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right'>";
		dd_html += "<bean:message key="lable.xsgzyxpzxy" />:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='printxy' id ='printxy' onchange='initPrintZyList();initPrintBjList()'>" 
		dd_html += $('xy').innerHTML;
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right'>";
		dd_html += "专业:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='printzy' id ='printzy'onchange='initPrintBjList()'>" 
		dd_html += $('zy').innerHTML;
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right'>";
		dd_html += "班级:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='printbj' id ='printbj'>" 
		dd_html += $('bj').innerHTML;
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right'>";
		dd_html += "汇总月度:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='yf' id ='yf'>" 
		dd_html += $('yf').innerHTML;
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr bgcolor='EEF4F9'>";
		dd_html += "<td colspan='2' align='center'>";
		dd_html += "<button type='button' class='button2' onclick='printPy()';>确定</button>";
		dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
		dd_html += "<button type='button' class='button2' onclick='closeAdd1()'>关闭</button>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tbody>";
		dd_html += "</table>";
		dd_html += "</div>";
		tmpdiv1.innerHTML = dd_html;
		//document.forms[0].action = "/xgxt/pjpyszyqwh.do?method=szyc_5sPrint";
		//document.forms[0].target = "_blank";
		//document.forms[0].submit();
		//document.forms[0].target = "_self";
	}
	
	function bbhz(){
		var url = "pjpyszyqwh.do?method=szyc_5sbb";
		showOpenWindow(url, '600','450');
	}
</script>
</head>
<body onload="xyDisabled('xy');">
	<html:form action="/pjpyszyqwh" method="post">
    	<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>综合素质-学生综合素质养成课-5S分审核</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>			
		<!-- 标题 end-->
		<!-- 提示信息 START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				鼠标移动到右上角<font color="blue">帮助中心</font>，可查看本模块的相关说明。</br>
				<span id="div_help" style="display: none">
				1.以下所有操作都是基于<font color="blue">${xn }</font>学年，<font color="blue">${xqmc }</font>学期 展开的。</br>
				2.点击<font color="blue">审核通过</font>和<font color="blue">审核不通过</font>，对学生进行审核。</br>
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
    	<input type="hidden" id="zyV" name="zyV" value=""/>
    	<input type="hidden" id="bjV" name="bjV" value=""/>
    	<input type="hidden" id="userType" name="userType" value="${userType}"/>
    	<input type="hidden" id="tableName" name="tableName" value="${tableName}"/>
    	<input type="hidden" id="realTable" name="realTable" value="${realTable}"/>
    	<input type="hidden" id="tmp" name="tmp" value="${tmp }"/>
    	<input type="hidden" name="pkstring" value="" />
    	<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="isFdy" scope="session"/>"/>
    	<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>" />
    	<!-- 批量删除信息提示 -->
    	<input type="hidden" id="failInfo" name="failInfo" value="${failinfo}"/>
    	
    	<logic:equal value="yes" name="writeAble">
			<div class="toolbox">
			<div class="buttonbox">	
				<ul>
					<li><a href="#" class="btn_shtg" onclick="sh5s('pjpyszyqwh.do?method=szyc_5sView&doType=save','tg');return false;">审核通过</a></li>
					<li><a href="#" class="btn_xg" onclick="sh5s('pjpyszyqwh.do?method=szyc_5sView&doType=save','btg');return false;">审核不通过</a></li>
					<li><a href="#" class="btn_sc" onclick="del5s('pjpyszyqwh.do?method=szyc_5sDel&doType=del');return false;">删除</a></li>
					<li><a href="#" class="btn_tj" onclick="bbhz();return false;">报表汇总</a></li>
				</ul>
			</div>
			</div>
		</logic:equal>
    	
		<div class="searchtab">
				<table width="100%">
					<tfoot>
						<tr>
							<td colspan="6">
							<div class="btn">
							<input type="hidden" name="go" value="a" />
							<button type="button" id="search_go" onclick="refreshForm('pjpyszyqwh.do?method=szyc_5sView&go=go');">
								查询
							</button>
							 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
								重置
							 </button>
							</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th>
								年级
							</th>
							<td>
							<html:select property="nj" styleId="nj" style=""
							onchange="initZyList();initBjList()" styleClass="select">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj"/>
							</html:select>
							</td>
							<th>
								学年
							</th>
							<td>
								<html:select property="xn" style="" styleClass="select"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
							<th>
								学期
							</th>
							<td>
								<html:select property="xq" style="" styleClass="select"
									styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>学号</th>
							<td>
								<logic:equal name = "userType" value="stu" scope="session">
									<input type="text" name = "xh" value="<bean:write name="userName" scope="session"/>" readonly="readonly" style="width:80px" class="inputtext"/>
								</logic:equal>
								<logic:notEqual name = "userType" value="stu" scope="session">
									<html:text property="xh" styleId="xh" styleClass="inputtext"
								 	style="width:80px"></html:text>
								 </logic:notEqual>
							</td>
							<th>							
								姓名
							</th>
							<td>
								<html:text property="xm" styleId="xm" styleClass="inputtext"
								 style="width:80px"></html:text>
							</td>
							<th>审核状态</th>
							<td><html:select property="shzt" styleClass="select" styleId="shzt">
									<html:option value=""></html:option>
									<html:option value="未审核">未审核</html:option>
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
									<html:option value="部分通过">部分通过</html:option>
									<html:option value="部分未审核">部分未审核</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<html:select property="xydm" onchange="initZyList();initBjList()"
									style="width:180px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
							<th>
								专业
							</th>
							<td><html:select property="zydm" onchange="initBjList()" style="" 
								style="width:180px" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<th>班级</th>
							<td><html:select property="bjdm" style="" 
								style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
					</thead>
				</table>
			</div>
			
			<div class="formbox">
				<logic:empty name="rs">
				    <h3 class="datetitle_01">
				    <span>
				    	查询结果&nbsp;&nbsp;
							<font color="red">未找到任何记录！</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="rs">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;<font color="blue">双击一行可以查看详细信息；单击表头可以排序</font> 
						</span>
					</h3>		
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
							    	<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" scope="request">
									<td id="${tit.en}"
										onclick="tableSort(this)">
										${tit.cn}
									</td>
								</logic:iterate>
								<td>
									审核状态
								</td>
							</tr>
						</thead>
					<logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this)" style="cursor: hand;"
							ondblclick="modiTab('pjpyszyqwh.do?method=szyc_5sSh&pkValue=', '650', '500')">
							<td align=center>
								<input type = "hidden" id="pk" name="pk" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
								<input type="checkbox" id="checkVal" name="checkVal" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
							</td>
							<logic:iterate id="v" name="s" offset="1">
								<td align=center>
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
				</table>
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpySzyqxyZhszcpActionForm"></jsp:include>
			</logic:notEmpty>
		</div>
		<div id="tmpdiv1"></div>
		<div id="tmpdiv"></div>			
	</html:form>
	 <script type="text/javascript" src="js/bottomButton.js"></script>
	 <!-- 操作提示 -->
	 <jsp:include flush="true" page="/syscommon/deleteprompt.jsp"></jsp:include>
</body>
</html>