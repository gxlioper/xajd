<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/commit.js"></script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/sztz" method="post">
		
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href='sztz.do?method=xmsq'
								   class="btn_zj"
								   id="btn_zj">申请</a>
							</li>
							<li>
								<a href="#" 
								   class="btn_xg"
								    onclick="showUpdateWindow('primarykey_cbv','sztz.do?method=sztzUpdate',800,600)"
								   id="btn_xg">修改</a>
							</li>
							<li>
								<a href="#" 
								   class="btn_sc"
								   onclick="doDel('primarykey_cbv','sztz.do?method=sztzDel')"
								   id="btn_sc">删除</a>
							</li>
							<li>
								<a href="#"
									id="btn_cs"
									onclick="showViewWindow('primarykey_cbv','sztz.do?method=sztzLcgz','550','350',false,'请勾选一条记录!')"
									class="btn_cs">流程跟踪 </a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<!-- 过滤条件 -->
				<%--<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tbody>
							<tr>
								<th>科目代码</th>
								<td>
									<html:text property="querylike_kmdm"></html:text>
								</td>
								<th>科目名称</th>
								<td>
									<html:text property="querylike_kmmc"></html:text>
								</td>
								<td colspan="2">
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('sztz.do?method=xssqQuery')">
											查 询
										</button>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			--%></div>
			
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; 
						<logic:notEmpty name="rs">
							<font color="blue">提示：单击表头可以排序</font>
						</logic:notEmpty>
					</span>
				</h3>
				<div class="con_overlfow">
					<table class="dateline tablenowrap" width="100%">
						<thead>
							<tr>
								<td width="17px">
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
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr>
										<td>
											<input type="checkbox" id="cbv" name="primarykey_cbv"
												alt="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"
												value="<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>" />
										</td>
											<td>
												<a
												href="javascript:showTopWin('sztz.do?method=sztzUpdate&doType=view&pkValue=<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>','750','550');"
												class="pointer" style="color:#0A63BF"> 
												<logic:iterate id="v" name="s" offset="2" length="1">
													${v }
												</logic:iterate>
												</a>
											</td>
										
										<logic:iterate id="v" name="s" offset="3">
											<td>
												${v }
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>

								<%
											for (int i = 0; i < (Integer) request
											.getAttribute("maxNum")
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
							<logic:empty name="rs">
								<%
											for (int i = 0; i < (Integer) request
											.getAttribute("maxNum"); i++) {
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

				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=sztzActionForm"></jsp:include>
			</div>
			
		</html:form>
	</body>
</html>
