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
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>

			</div>

			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" class="btn_zj" onclick="showTopWin('zjjsRcswJxqk.do?method=jxqkAdd','700','500');return false;"
								id="btn_qx">����</a>
						</li>
						<li>
							<a href="#" class="btn_xg" onclick="showUpdateWindow('primarykey_cbv','zjjsRcswJxqk.do?method=jxqkView','700','500');return false;"
								id="btn_qx">�޸�</a>
						</li>
						<li>
							<a href="#" class="btn_sc" onclick="batchData('primarykey_cbv','zjjsRcswJxqk.do?method=jxqkDel','del');return false;"
								id="btn_qx">ɾ��</a>
						</li>
						
						<li>
							<a href="#" class="btn_ck" onclick="showViewWindow('primarykey_cbv','zjjsRcswJxqk.do?method=jxqkView&doType=view','700','500');return false;"
								id="btn_qx">�鿴</a>
						</li>
						<li>
							<a href="#" class="btn_dr" onclick="impAndChkData()" id="btn_dr">����</a>
						</li>

						<li>
							<a href="#" id="btn_dc"
								onclick="expData('zjjsRcswJxqk.do?method=jxqkExp');return false;" class="btn_dc">
								���� </a>
						</li>
					</ul>
				</div>
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('zjjsRcswJxqk.do?method=jxqkManage')">
											�� ѯ
										</button>
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>

							<tr>
								<th>
									ϵ��
								</th>
								<td>
									<html:text property="querylike_bmmc"></html:text>
								</td>
								<th>����</th>
								<td>
								<html:text property="querylike_xm"></html:text>
								</td>
								<th>�Ա�</th>
								<td>
									<html:select property="queryequals_xb">
										<html:option value=""></html:option>
										<html:option value="��">��</html:option>
										<html:option value="Ů">Ů</html:option>
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:notEmpty name="rs">
							<font color="blue">��ʾ��������ͷ��������</font>
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
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
