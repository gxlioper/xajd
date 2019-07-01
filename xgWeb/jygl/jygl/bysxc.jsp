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
		<script type="text/javascript">
			jQuery(function(){
				var syshen = jQuery('#syshen').val();
				var syshi = jQuery('#syshen').val();
				dwr.engine.setAsync(false);
				if (syshen != ''){
					loadShi('syshen','syshi','syxian');
					jQuery('#syshi').val('${jyglActionForm.queryequals_sydshi}');
				}
				if (syshi != ''){
					loadXian('syshi','syxian');
					jQuery('#syxian').val('${jyglActionForm.queryequals_sydxian}');
				}
				dwr.engine.setAsync(true);
			})
		</script>
	</head>
	<body onload="xyDisabled('xy');hiddenTr($('moreTerm'));purviewControl()">

		<html:form action="/jygl" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
			<input type="hidden" name="userName" value="${userName }" />
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<input type="hidden" id="tableName" name="tableName" value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			
			<logic:equal value="xy" name="userType">
				<logic:equal value="false" name="isFdy">
					<input type="hidden" name="queryequals_xydm" value="${userDep }" />
				</logic:equal>
			</logic:equal>
			
			<!-- ͨ�õ����õ� -->
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
									id = "btn_xg"
									onclick="showUpdateWindow('primarykey_cbv','/xgxt/jygl.do?method=bysUpdate&doType=update','900','580')"
									class="btn_xg"> ��Ϣȷ�� </a>
							</li>
							<li>
								<a href="#"
									id="btn_sc"
									onclick="deletedata('/xgxt/jygl.do?method=bysxc&doType=del')"
									class="btn_sc"> ɾ�� </a>
							</li>
							<li>
								<a href="#" onclick="impAndChkData()" class="btn_dr" id="btn_dr"> ���� </a>
							</li>
							<li>
								<a href="#"
									id="btn_dc"
									onclick="configureExportData();return false;"
									class="btn_dc"> ���� </a>
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
											onclick="allNotEmpThenGo('/xgxt/jygl.do?method=bysxc&doType=query')">
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
									��
								</th>
								<td>
									<html:select property="je" styleId="je"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="nfList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<logic:equal value="stu" name="userType" scope="session">
										<html:text property="xh" maxlength="20"
											style="width:175px" value="${userName }" readonly="true"></html:text>
									</logic:equal>
									<logic:notEqual value="stu" name="userType" scope="session">
										<html:text property="xh" maxlength="20"
											style="width:175px"></html:text>
									</logic:notEqual>

								</td>
								<th>
									����
								</th>
								<td>
									<html:text property="xm" maxlength="20"
										style="width:175px"></html:text>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm"
										onchange="initZyList();initBjList()" styleId="xy"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="zydm"
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
									<html:select property="bjdm" styleId="bj"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<logic:notEqual value="10388" name="xxdm" scope="session">
								<tr style="display:none">
									<th>
										ѧ�����
									</th>
									<td>
										<html:select property="xlccdm" style="width:180px">
											<html:options collection="xlList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
									<th>
										������ʽ
									</th>
									<td>
										<html:select property="pyfsdm" style="width:180px">
											<html:options collection="pyfsList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
									<th>
										�������
									</th>
									<td>
										<html:select property="zslbdm" style="width:180px">
											<html:options collection="zsList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
								</tr>
								<tr style="display:none">
									<th>
										��Դ��(ʡ)
									</th>
									<td>
										<html:select property="sydshen" styleId="syshen" 
											onchange="loadShi('syshen','syshi','syxian')" style="width:180px">
											<html:options collection="sydqList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
									<th>
										��Դ��(��)
									</th>
									<td>
										<html:select property="sydshi" styleId="syshi"  
											onchange="loadXian('syshi','syxian')" style="width:180px">
											<html:option value="">----��ѡ��-----</html:option>
										</html:select>
									</td>
									<th>
										��Դ��(��)
									</th>
									<td>
										<html:select property="sydxian" styleId="syxian" style="width:180px">
											<html:option value="">----��ѡ��-----</html:option>
										</html:select>
									</td>
								</tr>
								<tr style="display:none">
									<th>
										�Ƿ���ְ
									</th>
									<td>
										<html:select property="sfzz" style="width:180px">
											<html:option value=""></html:option>
											<html:options collection="isNot" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
									<th>
										�Ƿ�ʦ��
									</th>
									<td>
										<html:select property="sfsf" style="width:180px">
											<html:option value=""></html:option>
											<html:options collection="isNot" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
									<th>
										�Ƿ����
									</th>
									<td>
										<html:select property="sfdl" style="width:180px">
											<html:option value=""></html:option>
											<html:options collection="isNot" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
								</tr>
								<tr style="display:none">
									<th>
										�Ƿ�ȷ��
									</th>
									<td>
										<html:select property="sfqr" style="width:180px">
											<html:option value=""></html:option>
											<html:options collection="isNot" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
									<th>
										�Ƿ��޸�
									</th>
									<td>
										<html:select property="sfxg" style="width:180px">
											<html:option value=""></html:option>
											<html:options collection="isNot" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
									<th>
										ѧУ���
									</th>
									<td>
										<html:select property="shzt" style="width:180px">
											<html:option value=""></html:option>
											<html:options collection="shztList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
								</tr>
							</logic:notEqual>
							<!-- �������� -->
							<logic:equal value="10388" name="xxdm" scope="session">
								<tr style="display:none">
									<th>
										��У����
									</th>
									<td>
										<html:text property="lxdk" maxlength="25"
											style="width:175px"></html:text>
									</td>
									<th>
										�Ƿ�ѧ���ɲ�
									</th>
									<td>
										<html:text property="xsgb" maxlength="25"
											style="width:175px"></html:text>
									</td>
									<th>
										ѧ�����
									</th>
									<td>
										<html:select property="xlccdm" style="width:180px">
											<html:options collection="xlList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
								</tr>
								<tr style="display:none">
									<th>
										��ҵ���
									</th>
									<td>
										<html:select property="bynf" styleId="bynf"
											style="width:180px">
											<html:option value=""></html:option>
											<html:option value="δ��ҵ">δ��ҵ</html:option>
											<html:options collection="nfList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
									<th>
										�Ƿ��ҵ��
									</th>
									<td>
										<html:select property="isjys" style="width:180px">
											<html:option value=""></html:option>
											<html:options collection="isNot" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
									<th>
										���״̬
									</th>
									<td>
										<html:select property="shzt" style="width:180px">
											<html:option value=""></html:option>
											<html:options collection="shztList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
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
									<input type="checkbox" disabled="disabled" />
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
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"
												<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate> />
										</td>
										<td>
											<a
												href="javascript:showTopWin('/xgxt/jygl.do?method=bysUpdate&doType=view&pk=<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>','900','600');"
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
			<script language="javascript">
         		alert("${message}");
         	</script>
		</logic:present>
	</body>
</html>
