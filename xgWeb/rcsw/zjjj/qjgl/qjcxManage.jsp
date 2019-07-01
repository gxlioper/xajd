<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		
		<script type="text/javascript">
			function xyDisabled(ele) {
				if ($("userStatus")&&$("userStatus").value == "xy") {
					var tmp = ele.split("-");
					for (i = 0; i < tmp.length; i++) {
						document.getElementById(tmp[i]).disabled = true;
					}
				}
			}
			
			function stuDisabled(ele) {
				if ($("userStatus")&&$("userStatus").value == "stu") {
					var tmp = ele.split("-");
					for (i = 0; i < tmp.length; i++) {
						document.getElementById(tmp[i]).disabled = true;
					}
				}
			}
			
			function checkAll(){
				var chec = document.getElementById('chec');
				var checkBoxArr = document.getElementsByName("pkValues");

				for(var i=0;i<checkBoxArr.length;i++){
					if(!checkBoxArr[i].disabled){
						checkBoxArr[i].checked = chec.checked;
					}
				}
			}
			
			function batchSave(url,names){
				var checkbox = document.getElementsByName(names);
				
				var flag = false;
				
				for(var i=0;i<checkbox.length;i++){
					if(checkbox[i].checked == true){
						flag = true;
						break;
					}
				}
				
				if(flag){
					refreshForm(url);
				}else{
					alertInfo('请选择需要操作的数据！');
				}
			}
			
			function showMoreInfo(url, w, h,opt) {
				if (w == null) {
					w = 700;
				}
				if (h == null) {
					h = 400;
				}
				if (curr_row == null) {
					alertInfo("请选择要操作的行！");
					return false;
				} else {
					var sfsh = false;
					var shs = curr_row.cells[0].getElementsByTagName("input");
					
					for (var i=1; i<shs.length; i++){
						var sh = shs[i].value;
						if("通过" == sh || "不通过" == sh){
							sfsh = true;
							break;
						}
					}
					
					if(!sfsh || 'view' == opt){
						var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
						url += "&pkValue=";
						url += pk;
						
						showTopWin(url, w, h);
					}else {
						alertInfo('该记录已在审核中，您不能对此进行修改！');
					}
				}	
			}
			
			function print(url,w,h){
				if (curr_row == null) {
					alertInfo("请选择要操作的行！");
					return false;
				} else {
					var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
					url += "&pkValue=";
					url += pk;
					window.open(url);
				}	
			}
		</script>
	
	</head>
	<body onload="xyDisabled('xy');stuDisabled('xhsel');">
    <html:form action="/zjjj_rcsw_qjgl" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="xyV" id="xyV" />
			<input type="hidden" name="tableName" value="${tableName }"/>
			<input type="hidden" name="realTable" value="${realTable }"/>
			<input type="hidden" id="userStatus" name="userStatus" value="${userStatus }"/>
			<input type="hidden" name="userName" value="${userName }"/>
			<logic:equal value="xy" name="userStatus">
				<input type="hidden" name="xydm" value="${userDep }"/>
			</logic:equal>
			<logic:equal value="stu" name="userStatus">
				<input type="hidden" name="xh" value="${userName }"/>
			</logic:equal>
			
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<!-- 多功能操作区 -->
			
			<logic:equal value="yes" name="writeAble">
				<div class="toolbox">
				<div class="buttonbox">	
					<ul>
						<li><a href="#" class="btn_xg" onclick="showMoreInfo('/xgxt/zjjj_rcsw_qjgl.do?method=qjxgUpdate&doType=view',800,600);return false;">修改</a></li>
						<li><a href="#" class="btn_sc" onclick="batchData('pkValues','/xgxt/zjjj_rcsw_qjgl.do?method=qjcxManage&doType=del','del');return false;">删除</a></li>
						<li><a href="#" class="btn_dy" onclick="print('/xgxt/zjjj_rcsw_qjgl.do?method=qjxgUpdate&doType=print');return false;">打印报表</a></li>	
						<li><a href="#" class="btn_dr" onclick="impAndChkData();return false;">导入</a></li>
						<li><a href="#" class="btn_dc" onclick="dataExport();return false;">导出</a></li>								
					</ul>
				</div>
				</div>
			</logic:equal>
			
			<div class="searchtab">
					<table>
						<tr>
							<th>学年</th>
							<td>
								<html:select property="xn" style="width:100px" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
							<th>学期</th>
							<td>
								<html:select property="xq" style="width:90px" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
							<th>学号</th>
							<td><html:text property="xh" styleId="xhsel" style="width:85px"></html:text></td>
						</tr>
						<tr>
							<th>姓名</th>
							<td><html:text property="xm" style="width:85px"></html:text></td>
							<th>请假天数</th>
							<td><html:text property="qjts" style="width:85px"></html:text></td>
							<th>
								院系
							</th>
							<td>
								<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>专业</th>
							<td>
								<html:select property="zydm" style="width:180px" styleId="zy"
									onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<th>班级</th>
							<td>
								<html:select property="bjdm" style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
							<th>幢号</th>
							<td>
								<html:select property="lddm" style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="ldList" property="lddm"
										labelProperty="ldmc" />
								</html:select>
							</td>
						</tr>
						
						<tfoot>
							<tr>
								<td colspan="6">
								<div class="btn">
								<input type="hidden" id="go" name="go" value="a" />
								<button type="button" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/zjjj_rcsw_qjgl.do?method=qjcxManage')">
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
								查询结果&nbsp;&nbsp;<font color="blue">单击表头可以排序;双击一行可以查看详细信息;</font> 
							</span>
						</h3>
						<div class="con_overlfow">
							<table width="99%" id="rsTable tablenowrap" class="dateline">
								<thead>
									<tr align="center" style="cursor:hand">
										<td>
											<input type="checkbox" id="chec" onclick="checkAll();"/>
										</td>
										<logic:iterate id="tit" name="topTr" offset="2">
											<td onclick="tableSort(this)">
												${tit }
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this);"
										style="cursor:hand;background-color:"
										ondblclick="showMoreInfo('/xgxt/zjjj_rcsw_qjgl.do?method=qjshUpdate&doType=view',800,600,'view')">
										<td>
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="checkbox" ${v } value="<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>" name="pkValues" />
											</logic:iterate>
											
											<logic:iterate id="v" name="s" offset="11" length="4">
												<input type="hidden" value="${v }" />
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="2">
											<td nowrap="nowrap">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
					</div>			
					<!--分页显示-->
				     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=qjglForm"></jsp:include>
					<!--分页显示-->
				</logic:notEmpty>
				
			</div>	
		</html:form>	
		
		<logic:present name="message">
			<input type="hidden" id="message" value="${message }"/>
			<script type="text/javascript" defer="defer">
				alertInfo($('message').value);
			</script>
		</logic:present>
    </body>
</html>	
