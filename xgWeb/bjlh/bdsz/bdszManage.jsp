<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/bdsz.js'></script>
		<script type="text/javascript" src="js/xtwh/bdsz.js"></script>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	</head>
	<body>
		<html:form action="/bdsz" method="post">
			<input type="hidden" id="tableName" name="tableName"
				value="${tableName }" />
			<input type="hidden" id="realTable" name="realTable"
				value="${realTable }" />
			<input type="hidden" id="zdyTable" name="zdyTable"
				value="${zdyTable }" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>


			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									onclick="showTopWin('/xgxt/bdsz.do?method=bdszUpdate','500','450')"
									class="btn_zj"> ����</a>
							</li>
							<li>
								<a href="#"
									onclick="showUpdateWindow('primarykey_cbv','/xgxt/bdsz.do?method=bdszUpdate&doType=edit','500','450')"
									class="btn_xg">�޸�</a>
							</li>
							<li>
								<a href="#"
									onclick="deletedata('/xgxt/bdsz.do?method=bdszManage&doType=del')"
									class="btn_sc"> ɾ��</a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0">
						<tbody>
							<tr>
								<th>
									ģ������
								</th>
								<td>
									<html:select property="mkdm" onchange="setGnbList(this)"
										style="width:170px">
										<html:option value=""></html:option>
										<html:options collection="gnmkList" property="mkdm"
											labelProperty="mkmc" />
									</html:select>
								</td>
								<th>
									��������
								</th>
								<td>
									<html:select property="tabname" styleId="tabname"
										style="width:170px">
										<html:option value=""></html:option>
										<html:options collection="gnmcList" property="gnb"
											labelProperty="gnmc" />
									</html:select>
								</td>
								<th>
									�ֶ�ID
								</th>
								<td>
									<html:text property="zdid" style="width:155px"></html:text>
								</td>
								<td width="10%">
				               	 <div class="btn">
				                    <button class="btn_cx" id="search_go" onclick="allNotEmpThenGo('/xgxt/bdsz.do?method=bdszManage')" >�� ѯ</button>
				                  </div>
				                </td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; <logic:notEmpty name="rs">
								<font color="blue"> ��ʾ��������ͷ�������� </font>
							</logic:notEmpty> </span>
					</h3>


					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr>
								<td>
									<input type="checkbox" disabled="disabled" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" scope="request">
									<td id="${tit.en}" onclick="tableSort(this)">
										${tit.cn}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr>
										<td>
											<input type="checkbox" name="primarykey_cbv"
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
										</td>

										<td>
											<a
												href="javascript:showTopWin('/xgxt/bdsz.do?method=bdszUpdate&doType=view&pkValue=<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>','450','450');"
												class="pointer" style="color:#0A63BF"> <logic:iterate
													id="v" name="s" offset="1" length="1">${v }</logic:iterate>
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
					<!--��ҳ��ʾ-->
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=bdszForm"></jsp:include>
					<!--��ҳ��ʾ-->
				</div>
		</html:form>

		<logic:present name="wscList">
			<div id="temp" style="display:none" class="open_prompt">
				<table class='formlist'>
					<thead>
						<tr>
							<td>
								��������
							</td>
							<td>
								�ֶ�ID
							</td>
						</tr>
					</thead>
					<tbody>
						<logic:iterate id="t" name="wscList">
							<tr>
								<td>
									${t.gnmc}
								</td>
								<td>
									${t.zdid}
								</td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
			</div>
		</logic:present>
		
		<logic:present name="wscList">
			<logic:notEmpty name="wscList">
				<script defer="defer">
					viewTempDiv("�����ֶ�","temp",300,120,false);
				</script>
			</logic:notEmpty>
		</logic:present>
		<logic:empty name="wscList">
			<logic:equal value="true" name="result">
				<script defer="defer">
					alert("ɾ���ɹ�");
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script defer="defer">
					alert("ɾ��ʧ��");
				</script>
			</logic:equal>
		</logic:empty>
	</body>
</html>
