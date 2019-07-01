<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/js/jsFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
		<script language="javascript" src="/xgxt/js/pjpy/pjpy_zjsyzy.js"></script>
		<script type="text/javascript">
			function chkView(){
				var xxfkdm = $("xxfkdm").value; 	
			    var url = "/xgxt/szdw_zjlg.do?method=xxFkChek&doType=view&xxfkdm="+xxfkdm+"&pkValue=";
			    var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
				url += pk;
			    showTopWin(url,"850","600");              		                  
			 }
		</script>
	</head>
	<body>
		<center>
			<html:form action="/szdw_zjlg" method="post">
<%--				<input type="hidden" id="realTable" name="realTable" value="${realTable}" />--%>
<%--				<input type="hidden" id="tableName" name="tableName" value="${tableName}" />--%>
				<input type="hidden" id="userType" name="userType"
					value="${userType}" />
				<input type="hidden" id="userName" name=" userName "
					value="${userName}" />
				<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="fdyQx" scope="session"/>"/>		
				<input type="hidden" id="xyV" name="xyV"  value="" />
				<input type="hidden" id="zyV" name="zyV"  value="" />
				<input type="hidden" id="bjV" name="bjV"  value="" />
				<input type="hidden" id="pkVStr" name="pkVStr"  value="" />
				<input type="hidden" id="xxfkdm" name="xxfkdm"  value="<bean:write name="xxfkdm"/>" />
				
				<div class="tab_cur">
					<p class="location">
						<em>您的当前位置:</em><a>思政队伍 - 信息反馈 - 信息反馈结果 - <bean:write name = "xxfkmc"/></a>
					</p>
				</div>
				<div class="searchtab">		
					<table width="100%" class="" border="0">
						<tbody>
							<tr>
								<th>部门</th>
								<td><logic:equal value="admin" name="userType" scope="session">
									<html:select property="bmdm" styleId="bmdm" style="width: 150px">
										<html:option value=""></html:option>
										<html:options collection="bmList" property="bmdm"
											labelProperty="bmmc" />
									</html:select>	
									</logic:equal>
									<logic:notEqual value="admin" name = "userType" scope="session">
										<html:select property="bmdm" styleId="bmdm" disabled="true" style="width: 150px">
											<html:option value=""></html:option>
											<html:options collection="bmList" property="bmdm"
												labelProperty="bmmc" />
										</html:select>	
									</logic:notEqual>
								</td>
								<th>姓名</th>
								<td><html:text property="xm" /></td>
								<th>审核状态</th>
								<td><html:select property="shzt" styleId="shzt">
										<html:option value="">全部</html:option>
										<html:options collection="chkList" property="en"
											labelProperty="cn" />
									</html:select>															
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="8">
								<div class="btn">
								<input type="hidden" name="go" value="a" />
								<button type="button" id="search_go" onclick="refreshForm('/xgxt/szdw_zjlg.do?method=xxFkQuery&go=go');">
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
							查询结果&nbsp;&nbsp;<font color="blue">双击一行可以查看详细信息；单击表头可以排序</font> 
						</span>
					</h3>
						<table width="100%" id="rsTable" class="dateline">
							<thead>
								<tr align="center" style="cursor:hand">
									<td align="center">
										<input type="checkbox" name="all" value="all" onclick="chec()"/>
									</td>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" style="cursor:hand"
										ondblclick="chkView()">
										<td align="center">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v" />"/>
											<input type="checkbox" name="pkV" value="<bean:write name="v"/>" />
										</logic:iterate>										
										</td>
										<logic:iterate id="v" name="s" offset="1"> 
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
						</table>
						
						<!--分页显示-->
					     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xjchForm"></jsp:include>
						<!--分页显示-->
					<br />
					<br />
				</logic:notEmpty>
				</div>
			</html:form>
			<div id="tmpdiv"></div>
		</center>
	</body>
</html>
