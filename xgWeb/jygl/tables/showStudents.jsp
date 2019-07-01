<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>	
	<script type="text/javascript">
		function searchRs(){
			allNotEmpThenGo('jyglTables.do?method=showStudents&doType=query');
		}

		function plszDy(){
			showTopWin('jyglTables.do?method=plszDykc','500','400')

			}
	</script>
</head>
<body>
	<html:form action="/jyglTables" method="post">
		<input type="hidden" id="userName" name="userName" value="${userName}" />
		<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx}" />
		<input type="hidden" name="xyV" value="" />
		<input type="hidden" name="zyV" value="" />
		<input type="hidden" name="bjV" value="" />
		
		<input type="hidden" name="userDep" id="userDep" value="${userDep }"/>
		<input type="hidden" id="path" name="searchModel.path" value="njjs_jytjb.do"/>
		
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title}</a>
			</p>
		</div>

		<div class="toolbox">	
			  <!--按钮	-->
			  <div class="buttonbox">
				<logic:equal value="yes" name="writeAble">
				    <ul>
						<li> 
							<a href="#" class="btn_ck" onclick="showUpdateWindow('primarykey_cbv','jyglTables.do?method=njjsJytjbUpdate','','',true,'请先选择一个学生')">查看</a>
						</li>
						<li>
							<a href="#" class="btn_sz" onclick="plszDy()">批量设置打印课程</a>
						</li>
						<li>
							<a href="#" class="btn_sz" onclick="showUpdateWindow('primarykey_cbv','jyglTables.do?method=dgszDykc','500','400',false,'请先选择一个学生')">单个学生设置打印课程</a>
						</li>					
					</ul>
				</logic:equal>
			  </div>
			  <%@ include file="/comm/search/superSearchArea.jsp"%>
		</div>
		<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; 
						<logic:notEmpty name="rs">
							<font color="blue">提示：单击表头可以排序</font>
						</logic:notEmpty> 
					</span>
				</h3>

				<div class="con_overlfow">
					<table summary="" class="dateline" width="100%">
						<thead>
							<tr>
								<td style="width:30px">
									<input type="checkbox" disabled="disabled" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="0" scope="request">
									<td onclick="tableSort(this)">
										${tit}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:present name="rs">
								<logic:notEmpty name="rs">
									<logic:iterate name="rs" id="s">
										<tr>
											<td>
												<input type="checkbox" id="cbv" name="primarykey_cbv"
													value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"/>
											</td>
											<logic:iterate id="v" name="s" offset="0">
												<td>
													${v }
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
	
									<%
												for (int i = 0; i < (Integer) request.getAttribute("maxNum")
														- ((List) request.getAttribute("rs")).size(); i++) {
									%>
									<tr>
										<td>
											<input type="checkbox" disabled="disabled" />
										</td>
										<logic:iterate id="t" name="topTr" offset="0" scope="request">
											<td>
												&nbsp;
											</td>
										</logic:iterate>
									</tr>
									<%
									}
									%>
	
								</logic:notEmpty>
							</logic:present>
							<logic:empty name="rs">
								<%
											for (int i = 0; i < (Integer) request.getAttribute("maxNum"); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<logic:iterate id="t" name="topTr" offset="0" scope="request">
										<td>
											&nbsp;
										</td>
									</logic:iterate>
								</tr>
								<%
								}
								%>
							</logic:empty>
						</tbody>
					</table>
				</div>
				
				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=jyglTablesForm"></jsp:include>
				<!--分页显示-->
			</div>
	</html:form>
</body>
</html>
