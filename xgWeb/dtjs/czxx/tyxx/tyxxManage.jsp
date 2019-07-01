<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<title><bean:message key="lable.title" /></title>

		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getSjxyDtjsDAO.js'></script>
		<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/commanFunction.js"></script>

		<script language="javascript">
		function save(){
		if($("rsNum")){
			if($("rsNum").value == "0"){
				alert("请确定欲设置（取消）的学生！");
				return false;
			}else{
				if(confirm("确定进行该操作吗?")) {
				  saveUpdate('/xgxt/czxxDtjsTyxx.do?method=tyxxManage&doType=save','');
				}
			}
		}
		}
		
		function update(url,doType,w,h){
			if(curr_row == null){
				alert("请选择要修改的数据！");
			}else{
				var sfty = curr_row.getElementsByTagName('input')[2].value;
				if(sfty == "否"){
				  alert("该学生不是团员，不能修改团员信息！");
				}else{
				  var pk = curr_row.getElementsByTagName('input')[0].value;
				  url+="&doType="+doType;
				  url+="&pkValue="+pk;
                  showTopWin(url,w,h);
				}
			}
		}
		
	function chec(){
      for(i=0;i<document.getElementsByName("tyxh").length;i++){
    	if(document.getElementsByName("tyxh")[i].disabled == false){
    		document.getElementsByName("tyxh")[i].checked=document.getElementsByName("all")[0].checked;
    	}
      }
    }
		
		function dataExport(){
			var url = "czxxDtjsTyxx.do?method=tyxxExp";
			url += "&nj=" + val('nj');
			url += "&xh=" + val('xh');
			url += "&xm=" + val('xm');
			url += "&xydm=" + val('xy');
			url += "&zydm=" + val('zy');
			url += "&bjdm=" + val('bj');
			url += "&sfty=" + val('sfty');
			
			window.open(url);
		}
		
	function djzh(){
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
	dd_html += "---------------该批学生的入团时间----------------";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "</thead>";
	dd_html += "<tbody>";
	
	dd_html += "<tr height='30'>";
	dd_html += "<td align='right' width='40%'>";
	dd_html += "请设置时间:";
	dd_html += "</td>";
	dd_html += "<td align='left'>";
	dd_html += "<input type='text' name='zxrq' id ='rtrq' onclick=showCalendar('rtrq','y-mm-dd') onblur=dateFormatChg(this)>" 
	dd_html += "</td>";
	dd_html += "</tr>";
	
	dd_html += "<tr bgcolor='EEF4F9'>";
	dd_html += "<td colspan='2' align='center'>";
	dd_html += "<button type='button' class='button2' onclick=saveUpdate('/xgxt/czxxDtjsTyxx.do?method=tyxxManage&doType=save','');>确定</button>";
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
	</head>
	<body onload="xyDisabled('xy');">
		<html:form action="/czxxDtjsTyxx" method="post">
			<input type="hidden" id="realTable" name="realTable"
				value="${realTable }" />
			<input type="hidden" id="tableName" name="tableName"
				value="${tableName }" />
			<input type="hidden" id="title" name="title" value="${title }" />
			<input type="hidden" id="writeAble" name="writeAble"
				value="${writeAble }" />
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" id="rsNum" name="rsNum" value="${rsNum }" />
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>

			<logic:notEmpty name="msg">
				<div align="center">
					<font color="red" size="6"><bean:write name="msg" /> </font>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<div class="toolbox">
					<logic:equal value="yes" name="writeAble">
						<div class="buttonbox">
							<ul>
								<li>
									<a href="#" class="btn_zj" onclick="save();return false;">保存</a>
								</li>
								<li>
									<a href="#" class="btn_xg"
										onclick="update('/xgxt/czxxDtjsTyxx.do?method=tyxxUpdate','update','600','450')">修改</a>
								</li>
								<li>
									<a href="#" class="btn_dr" onclick="impAndChkData()">导入</a>
								</li>
								<li>
									<a href="#" class="btn_dc" onclick="dataExport()">导出</a>
								</li>
							</ul>
						</div>
					</logic:equal>



					<div class="searchtab">
						<table width="100%" border="0">
							<tfoot>
								<tr>
									<td colspan="6">
										<div class="btn">
											<input type="hidden" name="go" value="a" />
											<button type="button" class="btn_cx" id="search_go"
												onclick="allNotEmpThenGo('/xgxt/czxxDtjsTyxx.do?method=tyxxManage');">
												查 询
											</button>
											&nbsp;&nbsp;&nbsp;&nbsp;
											<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
												重 置
											</button>
										</div>
									</td>
								</tr>
							</tfoot>
							<tbody>
								<tr>
									<th>
										年级
									</th>
									<td>
										<html:select property="nj" style="width: 60px"
											onchange="initZyList();initBjList()" styleId="nj">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>
									</td>
									<th>
										学号
									</th>
									<td>
										<html:text property="xh" style="" maxlength="20" styleId="xh" />
									</td>
									<th>
										姓名
									</th>
									<td>
										<html:text property="xm" style="" maxlength="20" styleId="xm" />
									</td>

								</tr>
								<tr>
									<th>
										<bean:message key="lable.xsgzyxpzxy" />
									</th>
									<td>
										<html:select property="xydm" style="width: 150px" styleId="xy"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</td>
									<th>
										专业
									</th>
									<td>
										<html:select property="zydm" style="width: 150px" styleId="zy"
											onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
									</td>
									<th>
										班级
									</th>
									<td>
										<html:select property="bjdm" style="width: 150px" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>
										是否是团员
									</th>
									<td>
										<html:select property="sfty" style="width: 60px"
											styleId="sfty">
											<html:option value=""></html:option>
											<html:option value="是">是</html:option>
											<html:option value="否">否</html:option>
										</html:select>
									</td>
									<logic:present name="xntj">
										<th>
											年度
										</th>
										<td>
											<html:select property="nd" styleId="nd" style="width:150px">
												<html:options collection="ndList" property="nd"
													labelProperty="nd" />
											</html:select>
										</td>
										<th>
										</th>
										<td>
										</td>
									</logic:present>
									<logic:notPresent name="xntj">
										<td colspan="4">
									</logic:notPresent>
								</tr>
							</tbody>
						</table>
					</div>

				</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; 
							<logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty> </span>
					</h3>

					<logic:notEmpty name="rs">
						<table summary="" id="rsTable" class="dateline" width="100%">
							<thead>
								<tr>
									<td>
										<input type="checkbox" id="selall" name="selall"
											onclick="selAll()" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="1" length="8">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand">

										<logic:iterate id="v" name="s" offset="7" length="1">
											<td align="center">
												<logic:equal name="v" value="是">
													<input type="checkbox" id=tyxh name="tyxh"
														checked="checked"
														value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
												</logic:equal>
												<logic:equal name="v" value="否">
													<input type="checkbox" id=tyxh name="tyxh"
														value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
												</logic:equal>
											</td>
										</logic:iterate>

										<td>
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" name="checkVal" value="${v }" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="7" length="1">
												<input type="hidden" value="${v }" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="8" length="1">
												<input type="hidden" name="rtrq" value="${v }" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="9" length="1">
												<input type="hidden" name="rtdd" value="${v }" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="1" length="1">
												<bean:write name="v" />
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="2" length="7">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>

									</tr>
								</logic:iterate>
							</tbody>
						</table>
						<!--分页显示-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=czxxDtjsForm"></jsp:include>
						<!--分页显示-->
					</logic:notEmpty>
				</div>
			</logic:empty>
		</html:form>

		<div id="tmpdiv1"></div>
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("操作失败");
			</script>
		</logic:equal>
	</body>
</html>
