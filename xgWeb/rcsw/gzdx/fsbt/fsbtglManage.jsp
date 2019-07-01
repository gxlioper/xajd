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
				jQuery("#search_go").click(function(){allNotEmpThenGo('/xgxt/rcsw_gzdx_fsbtgl.do?method=fsbtglManage')});
				
				//重置
				jQuery("#btn_cz").click(function(){searchReset();});
				
				//删除
				jQuery(".btn_sc").bind("click",function(event){
					event.preventDefault();
					batchData('pkValues','/xgxt/rcsw_gzdx_fsbtgl.do?method=fsbtglManage&doType=del','del');
				});

				jQuery(".btn_dr").bind("click",function(event){
					event.preventDefault();
					impAndChkData();
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
					showTopWin("rcsw_gzdx_fsbtgl.do?method=fsbtglUpdate", 800, 600);
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
		<html:form action="/rcsw_gzdx_fsbtgl" method="post">
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			<input type="hidden" name="qx" value="${qx }"/>
			<input type="hidden" id="realTable" value="${realTable }" />
			<input type="hidden" id="tableName" value="${tableName }" />
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
				<!-- 非学生控制 begin -->
				<logic:notEqual name="userType" value="stu">
				<div class="toolbox" id="dgncz">
					<!-- 按钮 -->
					<div class="buttonbox">
						<ul>
							
							<li><a href="#" class="btn_xg">补贴发放</a></li>
							<li><a href="#" class="btn_sc">删除</a></li>
							<li><a href="#" class="btn_dr">导入</a></li>	
							
						</ul>
					</div>
				</div>
				</logic:notEqual>
				<!-- 非学生控制 end -->
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
							<th>年度</th>
							<td>
								<html:select property="nd" style="nd" styleId="nd">
									<html:options collection="ndList" property="nd" labelProperty="nd"/>
								</html:select>
							</td>
							<th>月份</th>
							<td>
								<html:select property="yf">
									<html:option value=""></html:option>
									<html:options collection="yfList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
							<th>补贴项目</th>
							<td>
								<html:select property="btdm">
									<html:option value=""></html:option>
									<html:options collection="fsbtList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
						</tr>
						<logic:notEqual name="userType" value="stu">
						<tr>
							<th>
								院系
							</th>
							<td>
								<html:select property="xydm" style="width:180px" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
								</html:select>
							</td>
							<th>专业</th>
							<td>
								<html:select property="zydm" styleId="zy" style="width:180px" >
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
						</logic:notEqual>
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
								<logic:notEqual name="userType" value="stu">
								<td><input type="checkbox" name="checAll" disabled="disabled"/></td>
								</logic:notEqual>
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
								<logic:notEqual name="userType" value="stu">
								<td>
									<input type="checkbox" disabled="disabled"></input>
								</td>
								</logic:notEqual>
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
									ondblclick="modi('/xgxt/rcsw_gzdx_fsbtgl.do?method=fsbtglView&doType=view',800,600)">
									<!-- 学生不需要多选按钮 -->
									<logic:notEqual name="userType" value="stu">
									<td> 
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="checkbox" name="pkValues" value="${v }"/>
										</logic:iterate>
									</td>
									</logic:notEqual>
									
									<logic:equal name="userType" value="stu">
									<td  style="display:none">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="checkbox" name="pkValues" value="${v }" style="display:none" />
										</logic:iterate>
									</td>
									</logic:equal>
									
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
								<logic:notEqual name="userType" value="stu">
								<td><input type="checkbox" disabled="disabled"/></td>
								</logic:notEqual>
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
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=fsbtglForm"></jsp:include>
				<!--分页显示-->
				<logic:equal name="userType" value="stu">
				<script type="text/javascript">
						$('choose').className="hide";
				</script>
				</logic:equal>
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
