<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
</head>
	<body>
		<center>
			<html:form action="/chgPriseBat" method="post">
				<input type="hidden" name="xxdm" id="xxdm" value="<bean:write name="xxdm"/>"/>
				<html:hidden name="rs" property="yrdwdm"/>
				<div class="tab_cur">
					<p class="location">
						<em>���ĵ�ǰλ��:</em>
						<a>
							<%--��ɳ����--%>
							<logic:equal value="10827" name="xxdm">
								ѧ���幤 - ���ѹ��� - ���ѻ���
							</logic:equal>
							<logic:notEqual value="10827" name="xxdm">
								�ڹ���ѧ - ���ѹ��� - ���ѻ���
							</logic:notEqual>
						</a>
					</p>
				</div>
				<div class="tab">
		  		
				<fieldset>
					<legend>
						<span class="blue"><b>ѧ��:<bean:write name="rs" property="xn"/>&nbsp;&nbsp;��ȣ�<bean:write name="rs" property="nd"/>&nbsp;&nbsp;ѧ�ڣ�<bean:write name="rs" property="xqmc"/>&nbsp;&nbsp;������𣺳���</b></span>
					</legend>
					<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<logic:present name="showbjlh">
								<th colspan="6">
									<span>��<bean:message key="lable.xsgzyxpzxy" />����λ�ڹ������趨</span>
								</th>
							</logic:present>
							<logic:notPresent name="showbjlh">
								<th colspan="5">
									<span>��<bean:message key="lable.xsgzyxpzxy" />����λ�ڹ������趨</span>
								</th>
							</logic:notPresent>
						</tr>
						<logic:present name="showbjlh">
							<tr>
								<th>������λ</th>
								<th>��������</th>
								<th>��λ��</th>
								<th>������</th>
								<th>��������</th>
								<th>��λ����</th>
							</tr>
						</logic:present>
						</thead>
						<tbody>
						<logic:iterate name="yrdwList" id="s" indexId="index">
						<tr>
							<th><bean:write name="s" property="yrdwmc"/></th>
							<logic:present name="showbjlh">
								<th><bean:write name="s" property="xydm"/></th>
								<th><bean:write name="s" property="gws"/></th>
								<th><bean:write name="s" property="syrs"/></th>
							</logic:present>
							<td>
								<input type="text" name="<bean:write name="s" property="yrdwdm"/>" maxlength="8" onkeyup="value=value.replace(/[^\d]/g,'') "/>(Ԫ)
							</td>
							<logic:notPresent name="showbjlh">
								<th>��λ����</th>
								<td>
									<select name="gwxz${index}" style="width:180px">
										<option value=""></option>
										<logic:iterate id="gwxz" name="gwxzList">
										<option value="<bean:write name="gwxz" property="gwxzdm"/>"><bean:write name="gwxz" property="gwxzmc"/> </option>
										</logic:iterate>
									</select>
								</td>
							</logic:notPresent>
							
							<logic:present name="showbjlh">
							<td>
								<select name="gwxz${index}" style="width:180px">
									<option value=""></option>
									<logic:iterate id="gwxz" name="gwxzList">
									<option value="<bean:write name="gwxz" property="gwxzdm"/>"><bean:write name="gwxz" property="gwxzmc"/> </option>
									</logic:iterate>
								</select>
							</td>
							</logic:present>
						</tr>
						</logic:iterate>
						</tbody>
						<tfoot>
						  <tr>
							<logic:present name="showbjlh">
								<td colspan="6">
									<div class="btn">
										<button type="button" onclick="if(identifyInt()){refreshForm('/xgxt/work_outlay_transfer.do?dotype=save')}">
											����
										</button>
									</div>
								</td>
							</logic:present>
							<logic:notPresent name="showbjlh">
								<td colspan="5">
									<div class="btn">
										<button type="button"
											onclick="if(identifyInt()){if (confirm('ȷ��Ҫ�Ծ��ѻ������ݽ��б�����')) {refreshForm('/xgxt/work_outlay_transfer.do?dotype=save')}}">
											����
										</button>
									</div>
								</td>
							</logic:notPresent>								
							</tr>
					    </tfoot>
					</table>
			</fieldset>
			</div>
			<logic:notEmpty name="ok">
				<logic:equal name="ok" value="ok">
					<script>alert("����ɹ�!")</script>
				</logic:equal>
				<logic:equal name="ok" value="no">
					<script>alert("����ʧ��!")</script>
				</logic:equal>
			</logic:notEmpty>
	</html:form>
</body>
</html>


