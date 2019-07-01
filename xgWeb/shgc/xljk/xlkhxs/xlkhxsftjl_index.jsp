<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script language="javascript" src="js/lrh_new_js.js"></script>
		<script language="JavaScript">
		function xlkhxsftjl_add(){
			showTopWin("/xgxt/xljk_xlkhxsftjl.do?act=xljk_xlkhxsftjl&doType=xlkhxsftjl_add",700,500);
		}
		
		function xlkhxsftjl_view(){
			var xn_id=curr_row.cells[0].getElementsByTagName("input")[0].value;
			showTopWin("/xgxt/xljk_xlkhxsftjl.do?act=xljk_xlkhxsftjl&doType=xlkhxsftjl_view&xn_id="+xn_id,700,500);
		}
		
		function xlkhxsftjl_modi(){
			if (curr_row == null) {
				alert("请选择要修改的数据！\n（单击相应的行）");
				return false;
			}else {
				var xn_id=curr_row.cells[0].getElementsByTagName("input")[0].value;
				showTopWin("/xgxt/xljk_xlkhxsftjl.do?act=xljk_xlkhxsftjl&doType=xlkhxsftjl_modi&xn_id="+xn_id,700,500);
				return true;
			}
		}
		
		function xlkhxsftjl_del(){
			if (curr_row == null) {
				alert("请选择要删除的数据！\n（单击相应的行）");
				return false;
			}else if (confirm("确定要删除该行数据吗？")){
				var xn_id=curr_row.cells[0].getElementsByTagName("input")[0].value;
				refreshForm("/xgxt/xljk_xlkhxsftjl.do?act=xljk_xlkhxsftjl&doType=xlkhxsftjl_del&xn_id="+xn_id);
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
	</head>
	<body onload="usercheck()">
	
		<html:form action="/xljk_xlkhxsftjl" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="session"/>" />
			<html:text property="ftjl_view_flag" styleId="ftjl_view_flag"
				style="display:none;" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<div class="toolbox">
			
			<logic:equal value="yes" name="writeAble" scope="request">
				<logic:notEqual value="yes" name="nobutton" scope="request">
					 <div class="buttonbox">
					    <ul>
						<li> <a href="#" onclick="xlkhxsftjl_add()" class="btn_zj"> 增加 </a> </li>
					    <li> <a href="#" onclick="xlkhxsftjl_modi()" class="btn_xg"> 修改 </a> </li>
						<li> <a href="#" onclick="xlkhxsftjl_del()" class="btn_sc"> 删除 </a> </li>
					    </ul>
					 </div>
				</logic:notEqual>
			 </logic:equal>
			<div class="searchtab">
				<table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="6">
			          	 <input type="hidden" name="go" value="a" />
			            <div class="btn">
			             	<button  id="search_go"
								onclick="allNotEmpThenGo('/xgxt/xljk_xlkhxsftjl.do?act=xljk_xlkhxsftjl&doType=xlkhxsftjl_search&nobutton=${nobutton }')">
								查询
							</button>
							<button  style="display:none;"
									id="search_go1"
									onclick="refreshForm('/xgxt/xljk_xlkhxsftjl.do?act=xljk_xlkhxsftjl&doType=xlkhxsftjl_search&nobutton=${nobutton }')">
							</button>
							<logic:notEqual value="yes" name="nobutton" scope="request">
				              <button class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
				              	重 置
				              </button>
				            </logic:notEqual>
			            </div>
			          </td>
			        </tr>
			      </tfoot>
			
				 <tbody>
						<tr>
							<th align="left" nowrap>
								年级
							</th>
							<td>
								<html:select property="nj" onchange="initZyList();initBjList()"
								styleClass="select" styleId="nj">
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
							<th>
							 <bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<html:select property="xydm" onchange="initZyList();initBjList()"style="width:150px"
								styleClass="select" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								<logic:equal name="userType" value="xy">
								<input type="hidden" name="xydm" value="${userDep }"/>
								</logic:equal>
							</td>
							<th>
								专业
							</th>
							<td>
								<html:select property="zydm" onchange="initBjList()" style="width:150px"
								styleClass="select" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<th>
								班级
							</th>
							<td>
								<html:select property="bjdm" style="width:150px"
								styleClass="select" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>		
							</td>						
						</tr>
						<tr>
							<th>
								访谈日期
							</th>
							<td colspan="4">
								<html:text style="cursor:hand; width:85px;" styleId="dateF"
									property="ftsjks"
									onclick="return showCalendar('dateF','y-mm-dd');"
									onblur="dateFormatChg(this)"
									readonly="readonly" />
								-
								<html:text style="cursor:hand; width:85px;" styleId="dateFt"
									property="ftsjjs"
									onclick="return showCalendar('dateFt','y-mm-dd');"
									onblur="dateFormatChg(this)"
									readonly="readonly" />
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
						&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：双击一行可以查看详细信息，单击表头可以排序</font>
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
								ondblclick="xlkhxsftjl_view();">
								<td align=center>
									
										    <input type="hidden" id="xn_id" name="xn_id" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
										<logic:iterate id="v" name="s" offset="1" length="1">
												<bean:write name="v" />
										</logic:iterate>
									    </td>
										<logic:iterate id="v" name="s" offset="2">
											<td align=center nowrap="nowrap">
												<bean:write name="v" />
											</td>
										</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
					<jsp:include flush="true"
										page="/sjcz/turnpage.jsp?form=xljk_xlkhxs_form"></jsp:include>
					<script type="text/javascript">
							$('choose').className="hide";
					</script>
			</logic:notEmpty>
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
				<logic:equal name="message" value="update_success">
					<script>
							alert("更新成功!");
							document.getElementById("search_go1").click();
						</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
