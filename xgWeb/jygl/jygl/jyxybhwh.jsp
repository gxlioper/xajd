<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List"/>
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
		<script type="text/javascript" src="/xgxt/dwr/interface/jyglDAO.js"></script>
		<script type="text/javascript">
			function scxysbh(){
				var count = $('count').value;
				jyglDAO.saveXybh(count,function(data){
					hiddenMessage(true,true);
					if (data){
						alert('�����ɹ�!');
						$('search_go').click();
					}else {
						alert('��������Ч����!');
					}
				});
			}
		</script>
	</head>
	<body onload="xyDisabled('xy');purviewControl();">

		<html:form action="/jygl" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
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
									onclick="viewTempDiv('Э�������Զ�����','inputDiv',300,140);"
									class="btn_zj"> ����Э������ </a>
							</li>
							<li>
								<a href="#"
									id="btn_sc"
									onclick="deletedata('/xgxt/jygl.do?method=jyxybhwh&doType=del')"
									class="btn_sc"> ɾ�� </a>
							</li>
							<li>
								<a href="#" onclick="impAndChkData()" class="btn_dr" id="btn_dr"> ���� </a>
							</li>
							<li>
								<a href="#"
									id="btn_dc"
									onclick="expData('/xgxt/jygl.do?method=jyxybhwh&doType=expData')"
									class="btn_dc"> ���� </a>
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
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/jygl.do?method=jyxybhwh&doType=query')">
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
								<th>��ҵЭ����</th>
								<td>
									<html:text property="querylike_jyxybh" style="width:180px"/>
								</td>
								<th>�Ƿ���ʹ��</th>
								<td>
									<html:select property="queryequals_sfsy" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="isNot" property="en" labelProperty="cn"/>
									</html:select>
								</td>
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
						<table summary="" class="dateline tablenowrap" align=""
							width="100%">
							<thead>
								<tr>
									<td>
										<input type="checkbox" disabled="true"/>
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
										<tr onclick="rowOnClick(this)">
											<td>
												<input type="checkbox" id="cbv" name="primarykey_cbv"
													value="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>"
													<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate> />
												<input type="hidden" value="<bean:write name="v" />" />
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

			<!-- �����㣬�������ɱ�ŵĸ��� -->
			<div class="open_win01" style="display:none;" id="inputDiv">
				<table width='300' class='formlist'>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>���ɸ���
							</th>
							<td>
								<input type="text" maxlength="3" name="count" id="count"onkeyup="value=value.replace(/[^\d]/g,'')" />
							</td>
						</tr>
					<tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class='btn'>
									<button onclick='scxysbh();'>
										����
									</button>
									<button onclick="hiddenMessage(true,true);return false;">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>


		</html:form>
		<logic:present name="message">
			<script language="javascript" defer="defer">
	         	alert("${message}");
	         </script>
		</logic:present>
	</body>
</html>
