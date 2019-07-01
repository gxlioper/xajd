<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/zjlgZbglDAO.js'></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script language="javascript">	
	function delJjfz(){
		var checkBoxArr = document.getElementsByName("checkVal");
		var selall = document.getElementById('selall');
		var flag = false;
		
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				flag = true;
			}
		}
		if(flag){
			if (confirm("确定要删除所勾选的数据?")) {
				showTips('处理数据中，请等待......');
				var url = "/xgxt/zjlgDtjsJjfz.do?method=jjfzManage&doType=del";
				refreshForm(url);
			}
		}else{
			alert("请勾选要处理的数据");
			return false;
		}
	}
	
	function getZbList(){
		var xydm = $("xydm").value;
		dwr.engine.setAsync(false);
		zjlgZbglDAO.getZbWssList(xydm,function(data){
			if (data != null && typeof data == 'object') {
				var objId = "zbdm";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);			
						DWRUtil.addOptions(objId,data,"zbdm","zbmc");			
				}
				$(objId).options[0].value = "";
			}
		});
		dwr.engine.setAsync(true);
	}
	
	function dyzz(){
		var checkBoxArr = document.getElementsByName("checkVal");
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
		dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
		dd_html += "<div class='tab'>"
		dd_html += "<table width='300' class='formlist'>";
		dd_html += "<thead>";
		dd_html += "<tr height='30'>";
		dd_html += "<td colspan='2'>";
		dd_html += "<span>转为预备党员时间</span>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "</thead>";
		dd_html += "<tbody>";
		dd_html += "<tr height='30'>";
		dd_html += "<th align='right' width='30%'>";
		dd_html += "时间";
		dd_html += "</th>";
		dd_html += "<td align='left'>";
		dd_html += "<input type='text' name='gzkssj' id='gzkssj' onblur='dateFormatChg(this)' onclick='time(this.id)'  style='cursor:hand;' readonly='true'/>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tbody>";
		dd_html += "<tfoot><tr><td colspan='2' align='right'>";
		dd_html += "<button type='button' onclick='saveYbdy()'>确定</button>";
		dd_html += "<button type='button' onclick='closeAdd1()'>关闭</button>";
		dd_html += "</td></tr></tfoot>";
		dd_html += "</table>";
		dd_html += "</div>";
		tmpdiv1.innerHTML = dd_html;
	}
	
	function time(id){
		return showCalendar(id,'y-mm-dd');
	}
	
	function saveYbdy() {
		if($("gzkssj")){
			if($("gzkssj").value == ""){
				alert("请确定转为预备党员时间");
				return false;
			}
		}
		var url = "/xgxt/zjlgDtjsJjfz.do?method=jjfzManage&doType=zz";
		showTips('处理数据中，请等待......');
		refreshForm(url);
	}
	</script>
	</head>
	<body onload="xyDisabled('xy');">
		<html:form action="/zjlgDtjsJjfz" method="post">
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			<input type="hidden" id="isFdy" name="isFdy"
				value="<bean:write name="fdyQx" scope="session"/>" />
			<input type="hidden" id="userName" name="userName"
				value="<bean:write name="userName" scope="session"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="userName" name="userName"
				value="<bean:write name="userName" scope="session"/>" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a><bean:write name="title" /></a>
				</p>
			</div>
			<logic:notEmpty name="msg">
				<div align="center">
					<font color="red"><bean:write name="msg" />
					</font>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<logic:equal value="yes" name="writeAble">
					<div class="toolbox">
						<div class="buttonbox">	
							<ul>
								<li><a href="#" class="btn_zj" onclick="showTopWin('/xgxt/zjlgDtjsJjfz.do?method=jjfzUpdate&doType=add',800,600);">增加</a></li>
								<li><a href="#" class="btn_xg" onclick="if(curr_row == null){alert('请选择要修改的数据！');return false;}showTopWin('/xgxt/zjlgDtjsJjfz.do?method=jjfzUpdate&doType=update&pk='+curr_row.getElementsByTagName('input')[0].value,800,600);">修改</a></li>
								<li><a href="#" class="btn_sc" onclick="delJjfz()">删除</a></li>
								<li><a href="#" class="btn_up" onclick="dyzz()">批量转入预备党员</a></li>
								<li><a href="#" class="btn_dr" onclick="impAndChkData()">导入</a></li>
								<li><a href="#" class="btn_dc" onclick="dataExport()">导出</a></li>								
							</ul>
						</div>
					</div>
				</logic:equal>		
				<div class="searchtab">
						<table width="100%" class="" border="0">
							<tbody>
								<tr>
									<th>年级</th>
									<td><html:select property="nj" style=""
											onchange="setDzbZyList();setDzbBjList();">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select></td>
									<th>学号</th>
									<td><html:text property="xh" style="width:85px" maxlength="20"></html:text></td>
									<th>姓名</th>
									<td><html:text property="xm" style="width:85px" maxlength="20"></html:text></td>
								</tr>
								<tr>
									<th><bean:message key="lable.xsgzyxpzxy" /></th>
									<td><html:select property="xydm" style="width: 150px" styleId="xy"
											onchange="setDzbZyList();setDzbBjList();getZbList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select></td>
									<th>专业</th>
									<td><html:select property="zydm" style="width: 150px" styleId="zy"
											onchange="setDzbBjList();">
											<logic:equal name="isZbfzr" value="false">
												<html:option value=""></html:option>
											</logic:equal>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select></td>
									<th>班级</th>
									<td><html:select property="bjdm" style="width: 150px" styleId="bj">
											<logic:equal name="isZbfzr" value="false">
												<html:option value=""></html:option>
											</logic:equal>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>所在支部</th>
									<td><html:select property="zbdm" onchange=""
										styleClass="select" style="" styleId="zbdm">
											<html:options collection="zbList" property="zbdm"
												labelProperty="zbmc" />
											<html:option value="无所属">无所属</html:option>
										</html:select></td>
<%--									<th>在职状态</th>--%>
<%--									<td><html:select property="zzzt" styleClass="select" style="" styleId="zzzt">--%>
<%--											<html:option value="">&nbsp;</html:option>--%>
<%--											<html:option value="yes">是</html:option>--%>
<%--											<html:option value="no">否</html:option>--%>
<%--										</html:select>--%>
<%--									</td>--%>
									<th colspan="4"></th>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="6">
									<div class="btn">
									<input type="hidden" name="go" value="a" />
									<button type="button" id="search_go" onclick="allNotEmpThenGo('/xgxt/zjlgDtjsJjfz.do?method=jjfzManage');">
										查询
									</button>
									 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
										重置
									 </button>
									</div>
									</td>
								</tr>
							</tfoot>
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
							<table width="100%" id="rsTable" class="dateline">
								<thead>
									<tr align="center" style="cursor:hand">
										<td>
											<input type="checkbox" id="selall" name="selall"
												onclick="selAll()"/>
										</td>
										<logic:iterate id="tit" name="topTr" offset="1">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);"
										ondblclick="showTopWin('/xgxt/zjlgDtjsJjfz.do?method=jjfzUpdate&doType=view&pk='+curr_row.getElementsByTagName('input')[0].value,800,600);"
										style="cursor:hand">
										<td align="center">
											<input type="checkbox" id="checkVal" name="checkVal"
												value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
										</td>
										<td>
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v" />" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="1" length="1">
												<bean:write name="v" />
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="2">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
							<!--分页显示-->
						     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=zjlgDtjsForm"></jsp:include>
							<!--分页显示-->
					</logic:notEmpty>
					<div id="tmpdiv1"></div>
				</div>
			</logic:empty>
		</html:form>
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
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
