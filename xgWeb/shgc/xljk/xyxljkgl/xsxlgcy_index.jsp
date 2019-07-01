<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/lrh_new_js.js"></script>
	<script language="JavaScript">
		function gcy_add(){
			showTopWin("/xgxt/xljk_xsxlgcy.do?act=xsxlgcy&doType=gcy_add",600,500);
		}
		
		function xsxlgcy_view(){
			var xn_id=curr_row.cells[0].getElementsByTagName("input")[0].value;
			showTopWin("/xgxt/xljk_xsxlgcy.do?act=xsxlgcy&doType=gcy_view&xn_id="+xn_id,525,400);
		}
		
		function gcy_modi(){
			if (curr_row == null) {
				alert("请选择要删除的数据！\n（单击相应的行）");
				return false;
			}
			var xn_id=curr_row.cells[0].getElementsByTagName("input")[0].value;
			showTopWin("/xgxt/xljk_xsxlgcy.do?act=xsxlgcy&doType=gcy_modi&xn_id="+xn_id,525,400);
		}
		
		function gcy_del(){
			if (curr_row == null) {
				alert("请选择要删除的数据！\n（单击相应的行）");
				return false;
			} else if (confirm("确定要删除该行数据吗？")) {
				var xn_id=curr_row.cells[0].getElementsByTagName("input")[0].value;
				refreshForm("/xgxt/xljk_xsxlgcy.do?act=xsxlgcy&doType=gcy_del&xn_id="+xn_id);
			}
		}
	</script>
	</head>
	<body onload="usercheck()">
		<html:form action="/xljk_xsxlgcy" method="post">
			<div class="title">
				<div class="tab_cur">
					<p class="location">
						<em>您的当前位置:</em><a>${title }</a>
					</p>
				</div>
				
			</div>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" id="userDep" name="userDep"
				value="<bean:write name="userDep" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName" value="xljk_xsxlgcyb"/>
			<input type="hidden" id="realTable" name="realTable"value="xljk_xsxlgcyb"/>
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			
			
			<div class="toolbox">
			 <!-- 按钮 -->
			 <div class="buttonbox">
			    <ul>
				<li> <a href="#" onclick="gcy_add()" class="btn_zj"> 增加 </a> </li>
			    <li> <a href="#" onclick="gcy_modi()" class="btn_xg"> 修改 </a> </li>
				<li> <a href="#" onclick="gcy_del()" class="btn_sc"> 删除 </a> </li>
				<li> <a href="#" onclick="dataExport()" class="btn_dc"> 导出 </a> </li>
			    </ul>
			 </div>
			<div class="searchtab">
				<table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="6">
			            <div class="btn">
			              <input type="hidden" name="go" value="a" />
			              <button class="btn_cx" id="search_go" 
			              	onclick="allNotEmpThenGo('/xgxt/xljk_xsxlgcy.do?act=xsxlgcy&doType=gcy_search')">
			              	查 询
			              </button>
			              <button  style="display:none"
									id="search_go1"
									onclick="refreshForm('/xgxt/xljk_xsxlgcy.do?act=xsxlgcy&doType=gcy_search')">
						  </button>
			              <button class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
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
								<html:select property="nj" style="width:70px" styleId="nj"
											 onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>
							</td>
						
							
							<th>
								学号
							</th>
							<td>
								<html:text property="xh" styleId="xh" />
							</td>
							<th>
							姓名
							</th>
							<td>
								<html:text property="xm" styleId="xm" />
							</td>
						</tr>
						<tr>
							
							<th align="left" nowrap>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<html:select property="xydm" style="width:180px" styleId="xy"
									disabled="true" onchange="initZyList();initBjList()"
									>
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
							<th>
								班级
							</th>
							<td>
								<html:select property="bjdm" style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
								<th align="left">
								<logic:equal value="10827" name="xxdm">
									心理委员编号
								</logic:equal>
								<logic:notEqual value="10827" name="xxdm">
									心理观察员编号
								</logic:notEqual>
							</th>
							<td>
								<html:text property="gcybh" styleId="gcybh" />
							</td>
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
			 		 <logic:notEmpty name="rs">
			 		 	记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
			 		 </logic:notEmpty>
			    </span>
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
								style="cursor:hand;background-color:"
								ondblclick="xsxlgcy_view()">
								<td>
									<input type="hidden" id="xn_id" name="xn_id"
										value="<bean:write name="s" property="xn_id"/>" />
									<bean:write name="s" property="gcybh" />
								</td>
								<td>
									<bean:write name="s" property="xh" />
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
									<bean:write name="s" property="bjmc" />
								</td>
								<td>
									<bean:write name="s" property="csny" />
								</td>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
			</logic:notEmpty>
			</div>
			<logic:notEmpty name="message">
				<logic:equal name="message" value="del_success">
					<script>
						alert("删除成功!");
						document.getElementById("search_go1").click();
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
