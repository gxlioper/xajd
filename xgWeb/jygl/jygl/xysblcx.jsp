<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript" src="js/jygl/jygl.js"></script>
	</head>
	<body onload="xyDisabled('xy');hiddenTr($('moreTerm'));purviewControl();">

		<html:form action="/jygl" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>

			<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
			<input type="hidden" name="userName" value="${userName }" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable"
				value="${realTable }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />

			<logic:equal value="stu" name="userType">
				<input type="hidden" name="queryequals_xh" value="${userName }" />
			</logic:equal>

			<input type="hidden" name="isComm" value="true"/> 
			<!-- ��ʦ��Ȩ���õ� -->
			<logic:present name="purview">
				<input type="hidden" id="purview" name="purview" value="${ purview }" /> 
				<input type="hidden" id=" operateBound " name="operateBound" value="${ operateBound }" />
			</logic:present>


			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									id="btn_zj"
									onclick="showTopWin('/xgxt/jygl.do?method=xysblsq',600,400)"
									class="btn_zj"> ���� </a>
							</li>
							<li>
								<a href="#"
									id="btn_xg"
									onclick="showUpdateWindow('primarykey_cbv','/xgxt/jygl.do?method=xysblsq&doType=update','600','400')"
									class="btn_xg"> �޸� </a>
							</li>
							<li>
								<a href="#"
									id="btn_sc"
									onclick="deletedata('/xgxt/jygl.do?method=xysblcx&doType=del')"
									class="btn_sc"> ɾ�� </a>
							</li>
							<li>
								<a href="#" onclick="impAndChkData()" class="btn_dr" id="btn_dr"> ���� </a>
							</li>
							<!--<li>
								<a href="#"
									onclick="expData('/xgxt/jygl.do?method=xysblcx&doType=expData')"
									class="btn_dc"> ���� </a>
							</li>
						-->	
							<li>
								<a href="#" class="btn_dc" onclick="configureExportData();return false;" id="btn_dc">��������</a>
							</li>
							<li>
								<a href="#" class="btn_qx" onclick="choiceFields();return false;" id="btn_qx">�����ֶ�ȷ��</a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="bz">
										<label>
											<html:checkbox onclick="hiddenTr(this)" property="moreTerm"
												styleId="moreTerm" />
											<strong>��������</strong>
										</label>
									</div>

									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/jygl.do?method=xysblcx&doType=query')">
											�� ѯ
										</button>
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
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
										onchange="initZyList();initBjList()" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<logic:equal value="stu" name="userType" scope="session">
										<html:text property="querylike_xh" maxlength="20"
											style="width:155px" value="${userName }" readonly="true"></html:text>
									</logic:equal>
									<logic:notEqual value="stu" name="userType" scope="session">
										<html:text property="querylike_xh" maxlength="20"
											style="width:155px"></html:text>
									</logic:notEqual>

								</td>
								<th>
									����
								</th>
								<td>
									<html:text property="querylike_xm" maxlength="20"
										style="width:155px"></html:text>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="queryequals_xydm"
										onchange="initZyList();initBjList()" styleId="xy"
										style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="queryequals_zydm"
										onchange="initBjList()" styleId="zy" style="width:160px">
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
										style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr style="display:none">
								<th>
									ԭ��ҵ
									<br />
									Э����
								</th>
								<td>
									<html:text property="querylike_jyxybh" style="width:155px"></html:text>
								</td>
								<th>
									�¾�ҵ
									<br />
									Э����
								</th>
								<td>
									<html:text property="querylike_newjyxybh" style="width:155px"></html:text>
								</td>
								<th>
									�������
								</th>
								<td>
									<html:select property="queryequals_bblbdm" style="width:160px">
										<html:options collection="bblbList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							<tr style="display:none">
								<logic:equal value="2" name="cssz" property="xysbbshjb">
									<th>
										<bean:message key="lable.xsgzyxpzxy" />���
									</th>
									<td>
										<html:select property="queryequals_xysh" style="width:160px">
											<html:option value=""></html:option>
											<html:options collection="shztList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
									<th>
										<bean:message key="lable.xsgzyxpzxy" />�����
									</th>
									<td>
										<html:text property="querylike_xyshr" style="width:155px"></html:text>
									</td>
								</logic:equal>
								<th>
									ѧУ���
								</th>
								<td>
									<html:select property="queryequals_xxsh" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="shztList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<logic:equal value="1" name="cssz" property="xysbbshjb">
									<th>
										ѧУ�����
									</th>
									<td>
										<html:text property="querylike_xxshr" style="width:155px"></html:text>
									</td>
									<th>
										��
									</th>
									<td>
										<html:select property="queryequals_je" styleId="je"
											style="width:180px">
											<html:option value=""></html:option>
											<html:options collection="nfList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
								</logic:equal>
							</tr>
							<logic:equal value="2" name="cssz" property="xysbbshjb">
								<tr style="display:none">
									<th>
										ѧУ�����
									</th>
									<td>
										<html:text property="querylike_xxshr" style="width:155px"></html:text>
									</td>
									<th>
										��
									</th>
									<td>
										<html:select property="queryequals_je" styleId="je"
											style="width:180px">
											<html:option value=""></html:option>
											<html:options collection="nfList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
									<th></th>
									<td></td>
								</tr>
							</logic:equal>
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
					<table summary="" class="dateline tablenowrap" align=""
						width="100%">
						<thead>
							<tr>
								<td>
									<input type="checkbox" disabled="true" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="2" scope="request">
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
												value="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>"
												<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate> />
										</td>

										<td>
											<a
												href="javascript:showTopWin('/xgxt/jygl.do?method=xysblsq&doType=view&pkValue=<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>','700','500');"
												class="pointer" style="color:#0A63BF"> <logic:iterate
													id="v" name="s" offset="2" length="1">${v }</logic:iterate>
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
									<logic:iterate id="t" name="topTr" offset="2" scope="request">
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
									<logic:iterate id="t" name="topTr" offset="2" scope="request">
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
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=jyglActionForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</div>

		</html:form>
		<logic:present name="message">
			<script language="javascript" defer="defer">
	         	alert("${message}");
	         </script>
		</logic:present>
	</body>
</html>
