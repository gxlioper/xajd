<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/lrh_new_js.js"></script>
		<script language="JavaScript">
		function fdy_add(){
			showTopWin("/xgxt/xljk_xljkfdy.do?act=xljk_xljkfdy&doType=fdy_add",750,600);
		}
		
		function fdy_view(){
			if (curr_row == null) {
				alert("请选择要修改的数据！\n（单击相应的行）");
				return ;
			}	
			var xn_id=curr_row.cells[0].getElementsByTagName("input")[0].value;
			showTopWin("/xgxt/xljk_xljkfdy.do?act=xljk_xljkfdy&doType=fdy_view&xn_id="+xn_id,750,600);
		}
		
		function fdy_modi(){
			if (curr_row == null) {
				alert("请选择要修改的数据！\n（单击相应的行）");
				return ;
			}	
			var xn_id=curr_row.cells[0].getElementsByTagName("input")[0].value;
			showTopWin("/xgxt/xljk_xljkfdy.do?act=xljk_xljkfdy&doType=fdy_modi&xn_id="+xn_id,750,600);
		}
		
		function fdy_del(){
			if (curr_row == null) {
				alert("请选择要删除的数据！\n（单击相应的行）");
				return false;
			} else if (confirm("确定要删除该行数据吗？")) {
				var xn_id=curr_row.cells[0].getElementsByTagName("input")[0].value;
				refreshForm("/xgxt/xljk_xljkfdy.do?act=xljk_xljkfdy&doType=fdy_del&xn_id="+xn_id);
			}
		}
		
		function usercheck(){
			var userType=document.all['userType'].value;
			if("xx"==userType||"admin"==userType){
				document.getElementById('xy').disabled=false;
			}else{
				document.getElementById('xy').disabled=true;
			}
		}
	</script>
	<body onload="usercheck()">

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title}</a>
			</p>
		</div>


		<html:form action="/xljk_xljkfdy" method="post">
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" id="userDep" name="userDep"
				value="<bean:write name="userDep" scope="request"/>" />

			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" onclick="fdy_add();" class="btn_zj"> 增加 </a>
							</li>
							<li>
								<a href="#" onclick="fdy_modi();" class="btn_xg"> 修改 </a>
							</li>
							<li>
								<a href="#" onclick="fdy_del();" class="btn_sc"> 删除 </a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="8">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/xljk_xljkfdy.do?act=xljk_xljkfdy&doType=fdy_search')">
											查询
										</button>
										<button class="btn_cx" style="display:none" id="search_go1"
											onclick="refreshForm('/xgxt/xljk_xljkfdy.do?act=xljk_xljkfdy&doType=fdy_search')">
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									<logic:equal value="10827" name="xxdm">
										心理专
										<br/>
										干编号
									</logic:equal>
									<logic:notEqual value="10827" name="xxdm">
										心理健康
										<br/>辅导员编号
									</logic:notEqual>
								</th>
								<td>
									<html:text property="fdybh" styleId="gcybh" style="width:80px"/>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" styleId="xm" style="width:80px"/>
								</td>
								<th>
									性别
								</th>
								<td>
									<html:select property="xb" styleId="xb" onchange="">
										<html:option value=""></html:option>
										<html:options collection="sexList" property="DMH"
											labelProperty="DMMC" />
									</html:select>
								</td>

								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td align="left" nowrap>
									<html:select property="xydm" style="width:180px" styleId="xy"
										disabled="true"
										>
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>


				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty> <logic:notEmpty name="rs">
								<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
							</logic:notEmpty> </span>
					</h3>

					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;background-color:" ondblclick="fdy_view()">
										<td>
											<input type="hidden" id="xn_id" name="xn_id"
												value="<bean:write name="s" property="xn_id"/>" />
											<bean:write name="s" property="fdybh" />
										</td>
										<td>
											<bean:write name="s" property="xm" />
										</td>
										<td>
											<bean:write name="s" property="xb" />
										</td>
										<td>
											<bean:write name="s" property="xymc" />
										</td>
										<td>
											<bean:write name="s" property="rzsj" />
										</td>
										<td>
											<bean:write name="s" property="csny" />
										</td>
										<td>
											<bean:write name="s" property="sjhm" />
										</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</logic:notEmpty>
				</div>
			</div>
			<logic:notEmpty name="message">
				<logic:equal name="message" value="del_success">
					<script>
						alert("删除成功!");
						document.getElementById("search_go1").click();
						</script>
				</logic:equal>
				<logic:equal name="message" value="no">
					<script>alert("保存失败!咨询师编号已经存在！")</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
