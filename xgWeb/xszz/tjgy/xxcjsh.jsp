<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getDtjsInfo.js'></script>
		<script type="text/javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript">
			function plsh(){
				tipsWindown("ϵͳ��ʾ","id:shdiv","350","140","true","","true","id");
			}
		</script>
	</head>

	<body onload="xyDisabled('xy');">
		<html:form action="/xxcj" method="post">
			<input type="hidden" name="isComm" value="true"/>
			<input type="hidden" name="tableName" id="tableName" value="${tableName }"/>
			<input type="hidden" name="realTable" id="realTable" value="${realTable }"/>
			
			<input type="hidden" name="isFdy"    id="isFdy"    value="${isFdy }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<input type="hidden" name="userName" id="userName" value="${userName }" />
			
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
		
			<logic:notEqual value="xy" name="userType">
				<html:hidden property="queryequals_xysh" value="ͨ��"/>
			</logic:notEqual>
		
		
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
								<a  href="#"
									id="btn_sh"
									onclick="showAuditingWindow('primarykey_cbv','xxcj.do?method=xxcjDgsh',800,600);"
									class="btn_sh">��� </a>
							</li>
							<li>
								<a  href="#"
									id="btn_qxsh"
									onclick="if(isChecked('primarykey_cbv') && confirm('��ȷ��Ҫȡ���������ѡ�ļ�¼��')){refreshForm('xxcj.do?method=xxcjsh&doType=qxsh')}"
									class="btn_qxsh">ȡ����� </a>
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
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('xxcj.do?method=xxcjshcx&doType=query')">
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
								<th>ѧ��</th>
								<td>
									<html:text property="querylike_xh" style="width:175px"></html:text>
								</td>
								<th>����</th>
								<td>
									<html:text property="querylike_xm" style="width:175px"></html:text>
								</td>
								<th></th>
								<td></td>
							</tr>
							<tr>
								<th>ѧ��</th>
								<td>
									<html:select property="queryequals_xn" style="width:180px">
										<html:options collection="xnList" property="xn" labelProperty="xn"/>
									</html:select>
								</td>
								<th>ѧ��</th>
								<td>
									<html:select property="queryequals_xq" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
									</html:select>
								</td>
								<th>�꼶</th>
								<td>
									<html:select property="queryequals_nj" styleId="nj"
										onchange="initZyList();initBjList()" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th><bean:message key="lable.xsgzyxpzxy" /></th>
								<td>
									<html:select property="queryequals_xydm"
										onchange="initZyList();initBjList()" styleId="xy"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>רҵ</th>
								<td>
									<html:select property="queryequals_zydm"
										onchange="initBjList()" styleId="zy" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>�༶</th>
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
								<logic:equal value="xy" name="userType">
									<th><bean:message key="lable.xsgzyxpzxy" />���</th>
									<td>
										<html:select property="queryequals_xysh" style="width:180px">
											<html:option value=""></html:option>
											<html:options collection="shjgList" property="en" labelProperty="cn"/>
										</html:select>
									</td>
								</logic:equal>
								<th>ѧУ���</th>
								<td>
									<html:select property="queryequals_xxsh" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="shjgList" property="en" labelProperty="cn"/>
									</html:select>
								</td>
								<logic:notEqual value="xy" name="userType">
									<th></th>
									<td></td>
								</logic:notEqual>
								
								<th></th>
								<td></td>
							</tr>
							
						</tbody>
					</table>
				</div>
			</div>
			
			
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; 
						<logic:notEmpty name="rs">
							<font color="blue">��ʾ��������ͷ��������</font>
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
												<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>
												alt="<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>"
												value="<logic:iterate id="v" name="s" offset="2" length="1">${v }</logic:iterate>"
												/>
										</td>
										
										<td>
											<a
											href="javascript:showTopWin('xxcj.do?method=xxcjUpdate&doType=view&pkValue=<logic:iterate id="v" name="s" offset="2" length="1">${v }</logic:iterate>','900','600');"
											class="pointer" style="color:#0A63BF"> <logic:iterate
												id="v" name="s" offset="3" length="1">${v }</logic:iterate>
											</a>
										</td>
										
										<logic:iterate id="v" name="s" offset="4">
											<td>
												${v }
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>

								<%
									for (int i = 0; i < (Integer) request.getAttribute("maxNum")- ((List) request.getAttribute("rs")).size(); i++) {
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
				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=xxcjForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</div>
			
			
			
			
			
			<div class="open_win01" style="display:none;" id="shdiv">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>����д�����Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									���״̬
								</th>
								<td>
									<html:select property="shzt" style="width:120px" styleId="shzt">
										<html:options collection="shztList" property="en" labelProperty="cn"/>
									</html:select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button type="button" name="����" onclick="refreshForm('xxcj.do?method=xxcjsh&doType=sh&shzt='+$('shzt').value)">
											�� ��
										</button>
										<button type="button" name="ȡ��" onclick="closeWindown();return false;">
											ȡ ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			<logic:present name="message">
				<script defer>
					alert('${message}');
				</script>
			</logic:present>
			
			
		</html:form>
	</body>
</html>
