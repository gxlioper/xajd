<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>友情链接</a>
			</p>
		</div>
	
		<html:form action="/jyweb" method="post">
			<input type="hidden" name="realTable" id="realTable"
				value="${realTable }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<!-- 多功能操作区一 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a
								href="javascript:showTopWin('jywebUseCheckSession.do?method=linksUpdate','500','300');"
								class="btn_zj"> 增加 </a>
						</li>
						<li>
							<a  onclick="showUpdateWindow('primarykey_cbv','jywebUseCheckSession.do?method=linksUpdate&doType=update',500,300)"
								href="#"
								class="btn_xg"> 修改 </a>
						</li>
						<li>
							<a
								href="javascript:batchData('primarykey_cbv','jywebUseCheckSession.do?method=linksManage&doType=del','del')"
								class="btn_sc"> 删除 </a>
						</li>
					</ul>
				</div>
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('jywebUseCheckSession.do?method=linksManage&doType=query')">
											查 询
										</button>
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
									链接名称
								</th>
								<td>
									<html:text property="querylike_ljmc" maxlength="20"></html:text>
								</td>
								<th>
									链接网址
								</th>
								<td>
									<html:text property="querylike_ljwz" maxlength="50"></html:text>
								</td>
								<th>
									显示顺序
								</th>
								<td>
									<html:text property="queryequals_xssx" maxlength="20"
										onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<!-- From内容 start-->
			<!---------------------表格--有复选框的数据表单------------------->
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; 
						<logic:notEmpty name="rs">
							<font color="blue" >提示：单击表头可以排序</font>
						</logic:notEmpty>
					</span>
				</h3>

				
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr>
								<td>
									<input type="checkbox" disabled="true" style="height:17.5px"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" scope="request">
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
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
										</td>
										<td>
											<a
												onclick="showTopWin('jywebUseCheckSession.do?method=linksUpdate&doType=view&pkValue=<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>',500,300);"
												class="pointer" style="color:#0A63BF" href="#"> 
												<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>
											</a>
										</td>
										<logic:iterate id="v" name="s" offset="2">
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
									<logic:iterate id="t" name="topTr" offset="1" scope="request">
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
								<logic:iterate id="t" name="topTr" offset="1" scope="request">
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
					<!--分页显示-->
					<jsp:include flush="true"
						page="/jygl/jyweb/turnPage.jsp?form=jyglActionForm"></jsp:include>
					<!--分页显示-->
				</div>
			<logic:present name="message">
				<script defer="defer">
					alert("${message}");
				</script>
			</logic:present>

		</html:form>
	</body>
</html>
