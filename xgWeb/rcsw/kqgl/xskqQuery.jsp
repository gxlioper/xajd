<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/commit.js"></script>
	</head>
	<body  onload="xyDisabled('xy');">

		<html:form action="/kqglManage" method="post">
			<input type="hidden" value="${realTable }" id="realTable" />
			<input type="hidden" value="${tableName }" id="tableName" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
			<input type="hidden" name="userName" value="${userName }" />
			<input type="hidden" id="userType" name="userType" value="${userType }" />
		
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<!-- �๦�ܲ�����һ -->
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a
									onclick="showTopWin('/xgxt/kqglManage.do?method=xskqAdd','600','400');"
									href="#" class="btn_zj"> ���� </a>
							</li>
							<li>
								<a
									onclick="showUpdateWindow('primarykey_cbv','/xgxt/kqglManage.do?method=xskqUpdate&doType=update','600','400')"
									href="#" class="btn_xg"> �޸� </a>
							</li>
							<li>
								<a
									onclick="showUpdateWindow('primarykey_cbv','/xgxt/kqglManage.do?method=xskqUpdate&doType=view','600','400')"
									href="#" class="btn_ck"> �鿴 </a>
							</li>
							<li>
								<a
									onclick="batchData('primarykey_cbv','/xgxt/kqglManage.do?method=xskqDel&doType=del','del');"
									href="#" class="btn_sc"> ɾ�� </a>
							</li>
							<li>
								<a onclick="impAndChkData()" href="#" class="btn_dr"> ���� </a>
							</li>
							<li>
								<a
									onclick="expData('/xgxt/kqglManage.do?method=xskqQuery&doType=expData')"
									href="#" class="btn_dc"> ���� </a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/kqglManage.do?method=xskqQuery&doType=query')">
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
									�꼶
								</th>
								<td>
									<html:select property="queryequals_nj" styleId="nj"
										onchange="initZyList();initBjList()" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<html:text property="querylike_xh" maxlength="20"
										style="width:175px"></html:text>

								</td>
								<th>
									����
								</th>
								<td>
									<html:text property="querylike_xm" maxlength="20"
										style="width:175px"></html:text>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
								<!-- ��ѧԺ�û� -->
									<logic:equal name="isxy" value="true">
										<html:select property="queryequals_xydm"
										onchange="initZyList();initBjList()" styleId="xy"
										style="width:180px" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
										</html:select>
									</logic:equal>
									<!-- ��ѧԺ�û� end -->
									<!-- ��ѧԺ�û� -->
									<logic:equal name="isxy" value="false">
										<html:select property="queryequals_xydm"
											onchange="initZyList();initBjList()" styleId="xy"
											style="width:180px">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										<logic:equal value="xy" name="userType">
										<logic:notEqual value="true" name="isFdy">
											<html:hidden property="queryequals_xydm" value="${userDep }"/>
										</logic:notEqual>
									</logic:equal>
									</logic:equal>
									
								</td>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="queryequals_zydm"
										onchange="initBjList()" styleId="zy" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									�༶
								</th>
								<td>
									<html:select property="queryequals_bjdm" styleId="bj"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									ѧ��
								</th>
								<td>
									<html:select property="queryequals_xn" style="width:180px">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<html:select property="queryequals_xq" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
								<th>
									��������
								</th>
								<td>
									<html:select property="queryequals_kqlx" style="width:180px">
										<html:options collection="kqlxdmList" property="dm" labelProperty="mc"/>
										<html:options collection="kqlxList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; </span>
				</h3>

				<div class="con_overlfow">
					<table class="dateline" width="100%" id="rsTable">
						<thead>
							<tr>
								<td width="17px">
									<input type="checkbox" disabled="true" />
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
								<logic:iterate name="rs" id="s" indexId="n">
									<tr onclick="rowOnClick(this)">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<td>
												<input type="checkbox" value="${v }" name="primarykey_cbv" />
												<input type="hidden" value="${v }" name="pkValue" />
											</td>
										</logic:iterate>
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
				<!--��ҳ��ʾ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=kqglForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</div>
		</html:form>
		<logic:present name="message">
			<script>
				alert("${message}");
				$('search_go').click();
			</script>
		</logic:present>

	</body>
</html>
