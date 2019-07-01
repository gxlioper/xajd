<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<html>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/commit.js"></script>
	
	<script language="javascript">
		function modi(url){
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value
				+'&xtwsh='+curr_row.getElementsByTagName('input')[2].value,700,500);
				return true;
			}else{
				alert('请选择要修改的数据行！');
				return false;
			}
		}
		
		function choiceDisabled(usertype,ele) {
   			if($("userType")){
				if ($("userType").value == usertype) {
					var tmp = ele.split("-");
					for (i = 0; i < tmp.length; i++) {
						if(document.getElementById(tmp[i])){
			  		 		document.getElementById(tmp[i]).disabled = true;
						}
					}
  		 		}  
			}
		}
		
		function print(url){
			if(curr_row != null){
				showOpenWindow(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
				return true;
			}else{
				alert('请选择要修改的数据行！');
				return false;
			}
		}
		
		function changeTab(sec){
			$('realTable').value = sec.value;
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
	dd_html += "----------------请选择要导入的表---------------";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "</thead>";
	dd_html += "<tbody>";
	
	dd_html += "<tr height='30'>";
	dd_html += "<td align='right' width='30%'>";
	dd_html += "请选择表:";
	dd_html += "</td>";
	dd_html += "<td align='left'>";
	dd_html += "<select name='table' id ='table' onchange='changeTab(this)'>" 
	dd_html += "<option value='sjxy_shgzb'>履历审核表</option>"
	dd_html += 	"<option value='sjxy_rzqkb'>任职情况表</option>"
	dd_html += "<option value='sjxy_hjqkb'>获奖情况表</option>"
	dd_html += "</select>"
	dd_html += "</td>";
	dd_html += "</tr>";
	
	dd_html += "<tr bgcolor='EEF4F9'>";
	dd_html += "<td colspan='2' align='center'>";
	dd_html += "<button type='button' class='button2' onclick='impAndChkData();'>确定</button>";
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
	<body onload="choiceDisabled('xy','xy');">
		<html:form action="/sjxy_shgzwh.do?method=shgzsh" method="post">
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" name="userType" id="userType" value="${user}" />
			<input type="hidden" name="userName" value="${userName }"/>
			
			<input type="hidden" name="tableName" value="${tableName }"/>
			<input type="hidden" name="realTable" value="${realTable }"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置： ${title }
				</div>
			</div>
			<fieldset>
				<legend>
					查询条件
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td>
								&nbsp;&nbsp;学号：
								<html:text property="querylike_xh"></html:text>
								&nbsp;&nbsp;姓名：
								<html:text property="querylike_xm"></html:text>
								<logic:equal value="xy" name="user">
									&nbsp;&nbsp;分团委审核
									<html:select property="queryequals_ftwsh">
									<html:option value="">-----</html:option>
									<html:option value="未审核">未审核</html:option>
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
									</html:select>
								</logic:equal>
								
								<logic:equal value="xx" name="user">
									&nbsp;&nbsp;校团委审核
									<html:select property="queryequals_xtwsh">
									<html:option value="">-----</html:option>
									<html:option value="未审核">未审核</html:option>
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
									</html:select>
								</logic:equal>
								

							</td>
							<td width="10" align="center" valign="middle" rowspan="3">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" id="search_go" style="height:40px"
									onclick="allNotEmpThenGo('/xgxt/sjxy_shgzwh.do?method=shgzcx&go=go')">
									查 询
								</button>
							</td>
						</tr>
				
						<tr>
							<td>
								<logic:equal value="xy" name="user">
									<input type="hidden" name="queryequals_xydm" value="${userDep}">
								</logic:equal>
								
								<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="queryequals_xydm" style="width:180px"
									styleId="xy" onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;专业：
								<html:select property="queryequals_zydm" style="width:180px"
									styleId="zy" onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;班级：
								<html:select property="queryequals_bjdm" style="width:180px"
									styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
								
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
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
						记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()">
								</td>
								<logic:iterate id="tit" name="topTr" offset="2">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								ondblclick="modi('sjxy_shgzwh.do?method=shgzViewAndModi&doType=view')"
								align="center"
								style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="1" length="1">								
										<input type="checkbox" name="pkValues" id="pkV"
											${v } value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" >
									</logic:iterate>
								</td>
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="${v }" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length="1">
										${v }
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="3" length="6">
									<td>
										${v }
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="9" length="1">
										<td>
											<input type="hidden" value="${v }" />
											${v }
										</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
				<TABLE width="99%" id="Table" class="tbstyle">
					<TR>
						<TD height=3></TD>
					</TR>
					<TR>
						<TD>
							<jsp:include flush="true"
								page="/sjcz/turnpage.jsp?form=shgzForm"></jsp:include>
						</TD>
					</TR>
					<TR>
						<TD height=3></TD>
					</TR>
					</TABLE>
			</logic:notEmpty>
			<div id="tmpdiv1"></div>
			<div class="buttontool" id="btn"
				style="position: absolute;left:1%;top:100px" width="100%">
				<logic:equal value="yes" name="writeAble" scope="request">
					<button type="button" class="button2"
						onclick="location='sjxy_shgzwh.do?method=shgzadd';"
						style="width:80px">
						增加
					</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2"
						onclick="modi('sjxy_shgzwh.do?method=shgzViewAndModi&doType=modi')"
						style="width:80px">
						修改
					</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2"
						onclick="dataBatch('sjxy_shgzwh.do?method=shgzcx&doType=del&go=go')"
						style="width:80px">
						删 除
					</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="djzh();"
						style="width:80px">
						导入数据
					</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:equal>
				<button type="button" class="button2"
					onclick="wjcfDataExport('sjxy_shgzwh.do?method=shgzExp',600,400)"
					style="width:80px">
					导出数据
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2"
					onclick="print('sjxy_shgzwh.do?method=shgzprint')"
					style="width:80px">
					打 印
				</button>
			</div>

			<script language="javascript">
						document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
						document.getElementById("btn").style.width = "96%";
						window.setInterval("initBTNTool('btn')",1);
			</script>
		</html:form>
		<logic:present name="msg">
			<input type="hidden" id="msg" value="${msg }" />
			<script type="text/javascript">
				alert(document.getElementById('msg').value);
			</script>
		</logic:present>
	</body>
</html>
