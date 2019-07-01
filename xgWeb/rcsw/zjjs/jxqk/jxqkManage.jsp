<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script src="js/commit.js"></script>
	</head>

	<body>
		<html:form action="/zjjsRcswJxqk" method="post">
			<input type="hidden" name="userName" value="${userName }" />
			<input type="hidden" name="userType" value="${userType }" />
			<input type="hidden" name="userDep" value="${userDep }" />

			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<input type="hidden" id="realTable" name="realTable" value="xg_zjjs_rcsw_fdyjxqkb" />
			<input type="hidden" id="tableName" name="tableName" value="xg_zjjs_rcsw_fdyjxqkb" />

			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>

			</div>

			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" class="btn_zj" onclick="showTopWin('zjjsRcswJxqk.do?method=jxqkAdd','700','500');return false;"
								id="btn_qx">增加</a>
						</li>
						<li>
							<a href="#" class="btn_xg" onclick="showUpdateWindow('primarykey_cbv','zjjsRcswJxqk.do?method=jxqkView','700','500');return false;"
								id="btn_qx">修改</a>
						</li>
						<li>
							<a href="#" class="btn_sc" onclick="batchData('primarykey_cbv','zjjsRcswJxqk.do?method=jxqkDel','del');return false;"
								id="btn_qx">删除</a>
						</li>
						
						<li>
							<a href="#" class="btn_ck" onclick="showViewWindow('primarykey_cbv','zjjsRcswJxqk.do?method=jxqkView&doType=view','700','500');return false;"
								id="btn_qx">查看</a>
						</li>
						<li>
							<a href="#" class="btn_dr" onclick="impAndChkData()" id="btn_dr">导入</a>
						</li>

						<li>
							<a href="#" id="btn_dc"
								onclick="expData('zjjsRcswJxqk.do?method=jxqkExp');return false;" class="btn_dc">
								导出 </a>
						</li>
					</ul>
				</div>
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('zjjsRcswJxqk.do?method=jxqkManage')">
											查 询
										</button>
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>

							<tr>
								<th>
									系部
								</th>
								<td>
									<html:text property="querylike_bmmc"></html:text>
								</td>
								<th>姓名</th>
								<td>
								<html:text property="querylike_xm"></html:text>
								</td>
								<th>性别</th>
								<td>
									<html:select property="queryequals_xb">
										<html:option value=""></html:option>
										<html:option value="男">男</html:option>
										<html:option value="女">女</html:option>
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:notEmpty name="rs">
							<font color="blue">提示：单击表头可以排序</font>
						</logic:notEmpty> </span>
				</h3>


				<div class="con_overlfow">
					<table class="dateline tablenowrap" width="100%">
						<thead>
							<tr>
								<td width="17px">
									<input type="checkbox" disabled="disabled" />

								</td>
								<logic:iterate id="t" name="topTr" offset="1" scope="request">
									<td onclick="tableSort(this)">
										${t.cn}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)">
										<td>
											<input type="checkbox" id="cbv" name="primarykey_cbv"
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
										</td>
										<logic:iterate id="v" name="s" offset="1">
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
				</div>

				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=zjjsJxqkForm"></jsp:include>
			</div>
			<logic:present name="message">
				<script>
					alertInfo("${message}");
				</script>
			</logic:present>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
