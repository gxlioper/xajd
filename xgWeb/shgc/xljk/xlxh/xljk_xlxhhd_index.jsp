<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/lrh_new_js.js"></script>
	<script language="javascript">
		function xlxhhd_add(){
			showTopWin('/xgxt/xljk_xlxhhd.do?act=xljk_xlxhhd&doType=xlxhhd_add',650,550);
		}
		
		function xlxhhd_view(){
			var xlxhhd_xnid=curr_row.cells[0].getElementsByTagName("input")[0].value;
			showTopWin('/xgxt/xljk_xlxhhd.do?act=xljk_xlxhhd&doType=xlxhhd_view&xlxhhd_xnid='+xlxhhd_xnid,650,550);
		}
		
		function xlxhhd_modi(){
			if (curr_row == null) {
				alert("请选择要修改的数据！\n（单击相应的行）");
				return false;
			}else if (confirm("确定要修改该行数据吗？")) {
				var xlxhhd_xnid=curr_row.cells[0].getElementsByTagName("input")[0].value;
				showTopWin('/xgxt/xljk_xlxhhd.do?act=xljk_xlxhhd&doType=xlxhhd_modi&xlxhhd_xnid='+xlxhhd_xnid,650,550);
			}
		}
		
		function xlxhhd_del(){
			if (curr_row == null) {
				alert("请选择要修改的数据！\n（单击相应的行）");
				return false;
			}else if (confirm("确定要修改该行数据吗？")) {
				var xlxhhd_xnid=curr_row.cells[0].getElementsByTagName("input")[0].value;
				refreshForm('/xgxt/xljk_xlxhhd.do?act=xljk_xlxhhd&doType=xlxhhd_del&xlxhhd_xnid='+xlxhhd_xnid);
			}
		}
	</script>
	</head>
	<body>
		
		<html:form action="/xljk_xlxhhd" method="post">
			<div class="tab_cur">
				<p class="location">
				<em>您的当前位置：</em><a>${title}</a>
				</p>
			</div>
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<div class="toolbox">
			 <logic:equal value="yes" name="writeAble" scope="request">
			 <div class="buttonbox">
			    <ul>
				<li> <a href="#" onclick="xlxhhd_add()"  class="btn_zj"> 增加 </a> </li>
			     <li> <a href="#"onclick="xlxhhd_modi()"  class="btn_xg"> 修改 </a> </li>
				<li> <a href="#" onclick="xlxhhd_del()" class="btn_sc"> 删除 </a> </li>
				<li> <a href="#" onclick="impAndChkData();" class="btn_dr"> 导入 </a> </li>
				<li> <a href="#" onclick="dataExport()" class="btn_dc"> 导出 </a> </li>
			    </ul>
			 </div>
			 </logic:equal>
			
			<div class="searchtab">
			<table width="100%" border="0">
				 <tfoot>
				 	<tr>
				 		<td colspan="6" >
							<input type="hidden" name="go" value="a" />
							<div class="btn">
		             			 <button class="btn_cx" id="search_go" 
									onclick="allNotEmpThenGo('/xgxt/xljk_xlxhhd.do?act=xljk_xlxhhd&doType=xlxhhd_search')">
									查 询
								 </button>
								 <button  id="search_go1" style="display:none;"
									onclick="refreshForm('/xgxt/xljk_xlxhhd.do?act=xljk_xlxhhd&doType=xlxhhd_search')">
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
								日期
							</th>
							<td>
								<html:text style="cursor:hand; width:75px;" styleId="dateF"
									property="rq" onclick="return showCalendar('dateF','y-mm-dd');"
									readonly="readonly" />
							</td>
							<th></th>
							<td></td>
							<th></th>
							<td></td>
						</tr>
					</tbody>
				</table>
				</div>
			</div>
			<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs" >
								<font color="blue"> 
									提示：单击表头可以排序</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
				<logic:notEmpty name="rs">
					<div class="con_overlfow">
				 	<table summary="" id="rsTable" class="dateline" width="100%">
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
							<tr onclick="rowOnClick(this)" ondblclick="xlxhhd_view()"
								style="cursor:hand">
								<td>
									<input type="hidden" id="xn_id" name="xn_id"
										value="<bean:write name="s" property="xn_id"/>" />
									<bean:write name="s" property="rq" />
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
									<bean:write name="s" property="hdxs" />
								</td>
								<td>
									<bean:write name="s" property="zcr" />
								</td>
								<td>
									<bean:write name="s" property="xs" />
								</td>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
				</div>
			</logic:notEmpty>
			</div>
			<logic:notEmpty name="message">
				<logic:equal name="message" value="del_success">
					<script>alert("删除成功!")</script>
				</logic:equal>
				<logic:equal name="message" value="del_fail">
					<script>alert("删除失败!")</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
