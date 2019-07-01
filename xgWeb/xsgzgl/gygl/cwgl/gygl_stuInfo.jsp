<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
		
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="dwr/interface/GetListData.js"></script>
		<script type="text/javascript" src="dwr/engine.js"></script>
		<script type="text/javascript" src="dwr/util.js"></script>
		<script type="text/javascript" src="js/AjaxFunction.js"></script>
		<script type="text/javascript">
		function sendXhxx(){	
			var api = frameElement.api,W = api.get('parentDialog');
			if(W == undefined){
				if(W.document.getElementById('xh')){
					W.document.getElementById('xh').value = curr_row.getElementsByTagName('input')[0].value;
					//Close();
					W.document.getElementById('disbutton').click();
					api.close();
				}
			}else{				
				if(W.document.getElementById('xh')){
					W.document.getElementById('xh').value = curr_row.getElementsByTagName('input')[0].value;
					//Close();
					W.document.getElementById('disbutton').click();
					api.close();
				}
			}
		}

		function sendXh(){
			setTimeout(sendXhxx, 20);
		}
		
			jQuery(function(){
//				var userType=jQuery("#userType").val();
//				if(userType!='xx'&&jQuery("#userDep").val()!=""){
//					jQuery("#xy").val(jQuery("#userDep").val());
//				}
//				xyDisabled('xy');
			});
		</script>
	</head>
	<body>
			<html:form action="/gyglnew_cwgl" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="fdyQx"/>" />
			<input type="hidden" id="isBzr" name="isBzr" value="<bean:write name="bzrQx"/>" />
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName"/>" />
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType"/>" />
			<input type="hidden" id="userDep" name="userDep" value="<bean:write name="userDep"/>" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
				
				<div class="tab_cur">
					<p class="location">
						<em>您的当前位置:</em><a>学生查询</a>
					</p>
				</div>
				<div class="searchtab">
					<table width="100%" class="" border="0">
						<tbody>
							<tr>
								<th align="left">学号</th>
								<td><html:text property="xh" style="width:100" onkeypress="if(event.keyCode==13){document.forms[0].go.value='go';}"/></td>
								<th>姓名</th>
								<td><html:text property="xm" onkeypress="if(event.keyCode==13){document.forms[0].go.value='go';}"/></td>
								<th>年级</th>
								<td>
									<html:select property="nj">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<th align="left"><bean:message key="lable.xsgzyxpzxy" /></th>
								<td>
									<html:select property="xydm" styleId="xy" style="width:150px" onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
									</html:select>
								</td>
								<th>专业</th>
								<td>
									<html:select property="zydm" styleId="zy" style="width:150px" onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm" labelProperty="zymc"/>
									</html:select>
								</td>
								<th>班级</th>
								<td>
									<html:select property="bjdm" styleId="bj" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm" labelProperty="bjmc"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>性别</th>
								<td>
									<input type="hidden" name="xb" value="${xb }"/>
									<html:select property="xb" styleId="xb">
										<html:option value=""></html:option>
										<html:option value="男"></html:option>
										<html:option value="女"></html:option>
									</html:select>
									<script type="text/javascript">
										jQuery('#xb').attr({disabled:"disabled"});
									</script>
								</td>
								<th colspan="4"></th>
							</tr>
						</tbody>
						
						<tfoot>
			        		<tr>
			          			<td colspan="6">
			            		<div class="btn">
			              		<input type="hidden" name="go" value="a" />
			              		<button type="button" id="search_go" onclick="allNotEmpThenGo('gyglnew_cwgl.do?method=getStuInfo');">
			              			查 询
			              		</button>
			             		 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
			              			重 置
			             		 </button>
			            		</div>
			          		</td>
			       			</tr>
			     		</tfoot>
					</table>
					</div>
					
					<div class="formbox">
						<h3 class="datetitle_01">
							<span>
								查询结果&nbsp;&nbsp;
							</span>
						</h3>
						<div style="width:100%;height:270px;overflow-x:hidden;overflow-y:auto;">
						<table width="99%" id="rsTable" class="dateline">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="${tit }" onclick="tableSort(this)">
											${tit }
										</td>
									</logic:iterate>
									<td>操作</td>
								</tr>
							</thead>
							<logic:empty name="rs">
							  <%
								for(int i=0; i<11; i++){
								%>
								<tr>
									<logic:iterate id="tit" name="topTr" offset="0">
										<td>
											&nbsp;
										</td>
									</logic:iterate>	
										<td>
											&nbsp;
										</td>
							 	</tr>
								<%}%>
							 </logic:empty>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this);" style="cursor:hand" 
										ondblclick="sendXh();">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<td>
												<input type="hidden" value="${v }"/>
												${v }
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1">
											<td>${v }</td>
										</logic:iterate>
										<td>
											<button type="button" id="select" onclick="sendXh();" style="cursor:hand;width:55px;"  class="btn_01" >选择</button>
										</td>
									</tr>
								</logic:iterate>
								<%
								int rsNum = ((List)request.getAttribute("rs")).size();
								int pageSize = (Integer)(request.getAttribute("pageSize"));
								if(rsNum < pageSize){
									for(int i=0; i<(pageSize-rsNum); i++){
								%>
								<tr>
									<logic:iterate id="tit" name="topTr" offset="0">
										<td>
											&nbsp;
										</td>
									</logic:iterate>
									<td>
											&nbsp;
									</td>
							 	</tr>
								<%}}%>
						</logic:notEmpty>
					</table>
					</div>
					<!--分页显示-->
			   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglnewCwglForm"></jsp:include>
					<!--分页显示-->
				</div>
			</html:form>
	</body>
</html>
