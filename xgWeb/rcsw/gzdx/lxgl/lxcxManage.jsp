<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript">
			jQuery(function(){
				//查询
				jQuery("#search_go").click(function(){
					var kssj = eval($('kssj').value);
					var jssj = eval($('jssj').value);
					
					if(""!=kssj && ""!= jssj && kssj>jssj){
						alertInfo("开始时间不能大于结束时间！");
						return false;
					}
					allNotEmpThenGo('/xgxt/rcsw_gzdx_lxgl.do?method=lxcxManage')});
				
				//重置
				jQuery("#btn_cz").click(function(){searchReset();});
				
				//删除
				jQuery(".btn_sc").bind("click",function(event){
					event.preventDefault();
					batchData('pkValues','/xgxt/rcsw_gzdx_lxgl.do?method=lxcxManage&doType=del','del');
				});
				
				//打印
				jQuery(".btn_dy").bind("click",function(event){
					event.preventDefault();
					print('/xgxt/rcsw_gzdx_lxgl.do?method=lxglUpdate&doType=print');
				});
				
				jQuery(".btn_dc").bind("click",function(event){
					event.preventDefault();
					document.forms[0].action = "rcsw_gzdx_lxgl.do?method=lxcxManage&doType=export";
					document.forms[0].target = "_blank";
					document.forms[0].submit();
					document.forms[0].target = "_self";
				});
				
				//修改
				jQuery(".btn_xg").bind("click",function(event){
					event.preventDefault();
					var dis = curr_row.cells[0].getElementsByTagName("input")[1].value;
					
					if("" == dis || "xx" == jQuery("#userType").val()){
						modi('/xgxt/rcsw_gzdx_lxgl.do?method=lxglUpdate',800,600);
					}else{
						alertInfo("该记录已在审核中，请联系管理员修改！");
					}
				});
				
								
				//学院
				jQuery("#xy").change(function(){
					initZyList();
					initBjList();
				});
				
				//专业
				jQuery("#zy").change(function(){
					initBjList();
				});
				
				disXy();
			});
			
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
	<body>
		<html:form action="/rcsw_gzdx_lxgl" method="post">
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			<input type="hidden" name="qx" value="${qx }"/>
			
			<input type="hidden" id="userType" value="${user.userStatus }" />
			<input type="hidden" name="isFdy" value="${isFdy }"/>
			<input type="hidden" name="isBzr" value="${isBzr }" />
			<input type="hidden" name="userName" value="${user.userName }"/>
			
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>			
			
			<logic:equal value="yes" name="writeAble">
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_xg">修改</a></li>
						<li><a href="#" class="btn_sc">删除</a></li>
						<li><a href="#" class="btn_dy">打印报表</a></li>	
						<li><a href="#" class="btn_dc">留校名单导出</a></li>		
					</ul>
				</div>
			</div>
			</logic:equal>
			<div class="searchtab">
				<table>
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="btn_cx" id="search_go">
										查询
									</button>
									<button type="button" class="btn_cz" id="btn_cz">
										重置
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>学号</th>
							<td><html:text property="xh" style="width:120px"></html:text></td>
							<th>姓名</th>
							<td><html:text property="xm" style="width:120px"></html:text></td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th>
								院系
							</th>
							<td>
								<html:select property="xydm" style="width:120px" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
								</html:select>
							</td>
							<th>专业</th>
							<td>
								<html:select property="zydm" styleId="zy" style="width:120px" >
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
						</tr>
						<tr>
							<th>
								申请开始时间
							</th>
							<td>
								<html:text property="kssj" styleId="kssj"  style="width:120px"
									onclick="return showCalendar('kssj','y-mm-dd');" 
									onblur="dateFormatChg(this)" readonly="true" />
							</td>
							<th>
								 申请结束时间
							</th>
							<td>
								<html:text property="jssj" styleId="jssj"  style="width:120px"
									onclick="return showCalendar('jssj','y-mm-dd');" 
									onblur="dateFormatChg(this)" readonly="true" />
							</td>
							<th>
								<bean:message key="lable.xb" />审核
							</th>
							<td>
								<html:select property="sh1" style="width:120px" styleId="sh1">
									<html:option value=""></html:option>
									<html:option value="未审核">未审核</html:option>
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								学校审核
							</th>
							<td>
								<html:select property="sh2" style="width:120px" styleId="sh2">
									<html:option value=""></html:option>
									<html:option value="未审核">未审核</html:option>
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
								</html:select>
							</td>
							<th>
							</th>
							<td>
							</td>
							<th>
							</th>
							<td>
							</td>
						</tr>
					</tbody>
				</table>
			</div>

			<div class="formbox">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;<font color="blue">单击表头可以排序;双击记录查看详细信息</font>
						</span>
					</h3>
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<td><input type="checkbox" name="checAll" disabled="disabled"/></td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td onclick="tableSort(this)">
										${tit }
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:empty name="rs">
						  <%
							for(int i=0; i<11; i++){
							%>
							<tr>
								<td>
									<input type="checkbox" disabled="disabled"></input>
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td>
										&nbsp;
									</td>
								</logic:iterate>	
						 	</tr>
							<%}%>
						 </logic:empty>
						<logic:notEmpty name="rs">
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this);" style="cursor:hand" 
									ondblclick="modi('/xgxt/rcsw_gzdx_lxgl.do?method=lxglView&doType=view',800,600)">
									<td> 
										<logic:notEqual value="xx" name="user" property="userStatus">
											<logic:iterate id="sjsh" name="s" offset="7" length="1"></logic:iterate>
										</logic:notEqual>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="checkbox" name="pkValues" value="${v }"
												<logic:equal value="通过" name="sjsh">
													disabled=disabled
												</logic:equal>
											/>
										</logic:iterate>
										
										<logic:iterate id="v" name="s" offset="6" length="1">
											<input type="hidden" id="xysh" value="${v }" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="1">
										<td>${v }</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<%
							int rsNum = ((List)request.getAttribute("rs")).size();
							int pageSize = (Integer)request.getAttribute("pageSize");
							if(rsNum < pageSize){
								for(int i=0; i<(pageSize-rsNum); i++){
							%>
							<tr>
								<td><input type="checkbox" disabled="disabled"/></td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td>
										&nbsp;
									</td>
								</logic:iterate>
						 	</tr>
							<%}}%>
					</logic:notEmpty>
				</table>
				<!--分页显示-->
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=lxglForm"></jsp:include>
				<!--分页显示-->
			</div>
			
		</html:form>
		<logic:present name="message">
			<input type="hidden" id="message" name="message" value="${message }"/>
			<script type="text/javascript">
				alertInfo($('message').value);
			</script>
		</logic:present>
	</body>
</html>
