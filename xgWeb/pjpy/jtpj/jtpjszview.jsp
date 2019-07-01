<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="pjpy/jtpj/js/jtpjsz.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				autoSetPjdwMc();
				var kqqk=jQuery("#sfkqkg").val();
				if(kqqk=="1"){
					jQuery("#kqxx").show();
				}else{
					jQuery("#kqxx").hide();
				}
			});
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/jtpjsz">
			<input type="hidden" id="jtpjdw" value="${data.jtpjdw}" />
			<input type="hidden" id="sfkqkg" value="${data.sfkfsq}" />
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<div>
				<table width="" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>���影����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								��������
							</th>
							<td colspan="3">
								${data.jxmc}
							</td>
						</tr>
						<tr>
							<th width="">
								����˵��
							</th>
							<td colspan="3">
								${data.jxsm}
							</td>
						</tr>
						<tr>
							<th width="">
								����������λ
								<br />
							</th>
							<td colspan="3">
								<logic:equal name="data" property="jtpjdw" value="xy">��<font color='blue'><bean:message key="lable.xb" /></font>Ϊ��λ</logic:equal>
								<logic:equal name="data" property="jtpjdw" value="bj">��<font color='blue'>�༶</font>Ϊ��λ</logic:equal>
								<logic:equal name="data" property="jtpjdw" value="qs">��<font color='blue'>����</font>Ϊ��λ</logic:equal>
								<logic:equal name="data" property="jtpjdw" value="qt">����</logic:equal>
								
							</td>
						</tr>
						<thead>
							<tr>
								<th colspan="4">
									<span>�����������</span>
								</th>
							</tr>
						</thead>
						<tr>
							<th width="">
								��������
							</th>
							<td>
								${data.sqxn} ${sqxqmc}
							</td>
							<th width="20%">
								������������
							</th>
							<td>
								${data.pdxn} ${pdxqmc}
							</td>
						</tr>
						<logic:equal name="iswzdx" value="1">
							<tr>
								<th width="">
									ѧ����������Ա�趨
								</th>
								<td colspan="3">
									${zwmc}
								</td>
							</tr>
						</logic:equal>
						<tr>
							<th width="">
								���뿪��
							</th>
							<td colspan="3">
								${sfkfsqmc}
							</td>
						</tr>
						<tbody id="kqxx">
							<tr>
								<th width="">
									���뿪��ʱ��
								</th>
								<td colspan="3">
									${data.sqkfkssj} 
									<logic:notEmpty name="data" property="sqkfjssj">
										��
									</logic:notEmpty>
									<logic:empty name="data" property="sqkfjssj">
										<logic:notEmpty name="data" property="sqkfkssj">
											��
										</logic:notEmpty>
									</logic:empty>
									 ${data.sqkfjssj}
								</td>
							</tr>
							<tr>
								<th>
									�������
								</th>
								<td colspan="3">
									${splcmc}
								</td>
							</tr>
						</tbody>
					</tbody>
				</table>
			</div>
			<div style="hight: 15px"></div>
			<div>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="iFClose();" id="buttonClose">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>
