<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/bottomButton.js"></script>
		<html:form action="/prise_conf_rs" method="post">
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${tips }</a>
				</p>
			</div>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="bmlb" name="bmlb" value="xy" />

			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="teacher" name="userOnLine">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									onclick="showTopWin('ticket_book_appsucess.do?method=delCpydInfo',400,300)"
									class="btn_sc"> ɾ�� </a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go"
											onclick="refreshForm('/xgxt/ticket_book_search.do?go=go')">
											�� ѯ
										</button>

										&nbsp;&nbsp;&nbsp;&nbsp;
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
									ѧ��
								</th>
								<td>
									<html:select property="xn" style="width:120px" disabled="true"
										styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									���
								</th>
								<td>
									<html:select property="nd" style="width:90px" disabled="true"
										styleId="nd">
										<html:options collection="xnList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<html:select property="xq" style="width:90px" disabled="true"
										styleId="xq">
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
							</tr>

							<tr>
								<th>
									����
								</th>
								<td>
									<html:select property="cc" style="width:100px">
										<html:option value=""></html:option>
										<html:options collection="ccList" property="cc"
											labelProperty="cc" />
									</html:select>
								</td>
								<th>
									��Ʊ��
								</th>
								<td>
									<html:text property="dph" />
								</td>
								<td colspan="2"></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> <logic:notEmpty name="rs">
							<font color="blue">��ʾ��˫��һ�п��Բ鿴������Ϣ�������Ըı�Ԥ���������ȡ״̬��������ͷ��������</font>
						</logic:notEmpty> </span>
				</h3>
				<logic:notEmpty name="rs">
					<div class="con_overlfow">
						<table summary="" id="rsTable" class="dateline" width="100%">

							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="2">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;background-color:
									    <logic:iterate id="v" name="s" offset="0" length="1">
									    <bean:write name="v"/>
									    </logic:iterate>
									    "ondblclick="chkPriseOne('/xgxt/ticket_book_edit.do?act=view&pkVal=',700,620)">
										<td>
											<logic:iterate id="v" name="s" offset="1" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="2" length="1">
												<bean:write name="v" />
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="3">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
				</logic:notEmpty>
			</div>
		</html:form>
	</body>
</html>
