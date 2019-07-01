<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
			
		<script type="text/javascript">
			function sendZgh(){
				if(window.dialogArguments.document.getElementById('xh')){
					window.dialogArguments.document.getElementById('xh').value = curr_row.getElementsByTagName('input')[0].value;
					Close();
					window.dialogArguments.document.getElementById('disbutton').click();
				}
			}
		</script>
	</head>
	<body onload="xyDisabled('xy');">
		<center>
			<script language="javascript" src="js/function.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
			<script type="text/javascript" src="js/AjaxFunction.js"></script>
			
			<html:form action="/pjpy_comm_xmsq" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="fdyQx"/>" />
			<input type="hidden" id="isBzr" name="isBzr" value="<bean:write name="bzrQx"/>" />
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName"/>" />
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType"/>" />
			<input type="hidden" id="xxdm" name="xxdm"
				value="<bean:write name="xxdm" scope="session"/>" />	
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
				
				<div class="tab_cur">
					<p class="location">
						<em>您的当前位置:</em><a>评奖学生查询</a>
					</p>
				</div>
				<div class="searchtab">
					<table width="100%" class="" border="0">
						<tbody>
							<tr>
								<th align="left">学号</th>
								<td><html:text property="querylike_xh" style="width:100" onkeypress="if(event.keyCode==13){document.forms[0].go.value='go';}"/></td>
								<th>姓名</th>
								<td><html:text property="querylike_xm" onkeypress="if(event.keyCode==13){document.forms[0].go.value='go';}"/></td>
								<th>年级</th>
								<td>
									<html:select property="queryequals_nj">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<th align="left"><bean:message key="lable.xb" /></th>
								<td>
									<logic:equal name="userType" value="xy">
										<input type="hidden" name="queryequals_xydm" value="${userDep }"/>
									</logic:equal>
									<html:select property="queryequals_xydm" styleId="xy" style="width:150px" onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
									</html:select>
								</td>
								<th>专业</th>
								<td>
									<html:select property="queryequals_zydm" styleId="zy" style="width:150px" onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm" labelProperty="zymc"/>
									</html:select>
								</td>
								<th>班级</th>
								<td>
									<html:select property="queryequals_bjdm" styleId="bj" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm" labelProperty="bjmc"/>
									</html:select>
								</td>
							</tr>
							
						</tbody>
						
						<tfoot>
			        		<tr>
			          			<td colspan="6">
			            		<div class="btn">
			              		<input type="hidden" name="go" value="a" />
			              		<button type="button" id="search_go" 
			              		onclick="document.forms[0].go.value='go';refreshForm('/xgxt/pjpy_comm_xmsq.do?method=getStuInfo');this.disabled=true;">
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
									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="sendZgh()">
									<logic:iterate id="v" name="s" offset="0" length="1">
										<td align="center">
											<input type="hidden" value="${v }"/>
											<bean:write name="v" />
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1">
										<td align="center">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
						
						<!--分页显示-->
					     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpyXmsqForm"></jsp:include>
						<!--分页显示-->
				</logic:notEmpty>
				</div>
			</html:form>
		</center>
	</body>
</html>
