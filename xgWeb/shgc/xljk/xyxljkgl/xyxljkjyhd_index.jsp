<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/lrh_new_js.js"></script>
		<script language="javascript">
			function xyxljkjyhd_add(){
				showTopWin('/xgxt/xljk_xyxljkjyhd.do?act=xyxljkjyhd&doType=xyxljkjyhd_add',700,520);
			}
			
			function xyxljkjyhd_view(){
				var xn_id=curr_row.cells[0].getElementsByTagName("input")[0].value;
				showTopWin('/xgxt/xljk_xyxljkjyhd.do?act=xyxljkjyhd&doType=xyxljkjyhd_view&xn_id='+xn_id,700,520);
			}
			
			function xyxljkjyhd_modi(){
				if (curr_row == null) {
					alert("请选择要修改的数据！\n（单击相应的行）");
					return false;
				} 
				var xn_id=curr_row.cells[0].getElementsByTagName("input")[0].value;
			    showOpenWindow('/xgxt/xljk_xyxljkjyhd.do?act=xyxljkjyhd&doType=xyxljkjyhd_modi&xn_id='+xn_id,700,520);
			}
			
			function xyxljkjyhd_del(){
				if (curr_row == null) {
					alert("请选择要删除的数据！\n（单击相应的行）");
					return false;
				}else if (confirm("确定要删除该行数据吗？")) {
					var xn_id=curr_row.cells[0].getElementsByTagName("input")[0].value;
					refreshForm('/xgxt/xljk_xyxljkjyhd.do?act=xyxljkjyhd&doType=xyxljkjyhd_del&xn_id='+xn_id);
				}	
			}
		</script>
	<body onload="lrh_xyDisabled();">

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/xljk_xyxljkjyhd" method="post">
			<input type="hidden" id="userDep" name="userDep"
				value="<bean:write name="userDep" scope="request"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />

			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" onclick="xyxljkjyhd_add();" class="btn_zj"> 增加 </a>
							</li>
							<li>
								<a href="#" onclick="xyxljkjyhd_modi();" class="btn_xg"> 修改
								</a>
							</li>
							<li>
								<a href="#" onclick="xyxljkjyhd_del();" class="btn_sc"> 删除 </a>
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
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/xljk_xyxljkjyhd.do?act=xyxljkjyhd&doType=xyxljkjyhd_search')">
											查 询
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
								<th width="70px">
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td  width="180px">
									<html:select property="xydm" style="width:180px" styleId="xy"
										disabled="true"
										>
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th  width="70px">
									日期
								</th>
								<td>
									<html:text style="cursor:hand; width:120px;" styleId="dateF"
										property="rq"
										onclick="return showCalendar('dateF','y-mm-dd');"
										readonly="readonly" />
								</td>
								<th></th>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>


				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty> <logic:notEmpty name="rs">
								<font color="blue">提示：双击一行可以查询详细信息;单击表头可以排序</font>
							</logic:notEmpty> </span>
					</h3>

					<logic:notEmpty name="rs">
					<div class="con_overlfow">
						<table summary="" class="dateline tablenowrap" align="" width="100%">
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
									<tr onclick="rowOnClick(this)" ondblclick="xyxljkjyhd_view()"
										style="cursor:hand">
										<td>
											<input type="hidden" id="xn_id" name="xn_id"
												value="<bean:write name="s" property="xn_id"/>" />
											<bean:write name="s" property="rq" />
										</td>
										<td>
											<bean:write name="s" property="xymc" />
										</td>
										<td>
											<bean:write name="s" property="zt" />
										</td>
										<td>
											<bean:write name="s" property="cyxs" />
										</td>
										<td>
											<bean:write name="s" property="rs" />
										</td>
										<td>
											<bean:write name="s" property="zcr" />
										</td>
										<td>
											<bean:write name="s" property="xs" />
										</td>
										<td>
											<bean:write name="s" property="hdmc" />
										</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>
					</logic:notEmpty>
				</div>
			</div>

			<logic:notEmpty name="message">
				<logic:equal name="message" value="del_success">
					<script>
						alert("删除成功!");
						document.getElementById("search_go").click();
						</script>
				</logic:equal>
				<logic:equal name="message" value="del_fail">
					<script>
						alert("删除失败!");
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
