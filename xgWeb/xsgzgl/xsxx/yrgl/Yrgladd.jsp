<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type='text/javascript' src='js/calendar/calendar-setup.js'></script>
		<script type="text/javascript" src="xsgzgl/xsxx/yrgl/js/yrgl.js"></script>
	</head>
	<body>
	<html:form action="/xsxx_yrgl.do" styleId="yrglForm" method="post">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tr>
							<th style="width:15%"><font color="red">*</font>ѧ��</th>
							<td style="width:35%"><html:text property="xh" readonly="true" styleId="xh" style="width:60%" />
							<button class="btn_01" type="button"  
										onclick="showDialog('��ѡ��һ��ѧ��',680,480,'xsxx_xsgl.do?method=showStudents&goto=${path}');return false;">ѡ��</button>
							</td>
							<th style="width:15%">����</th>
							<td style="width:35%">
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="xm"/>
								</logic:present>
							</td>
					</tr>
						<tr>
							<th>�꼶</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="nj"/>
								</logic:present>
							</td>
							<th>ѧԺ</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="xymc"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th>רҵ</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="zymc"/>
								</logic:present>
							</td>
							<th>�༶</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="bjmc"/>
								</logic:present>
							</td>
						</tr>
						<thead>
							<tr>
								<th colspan="4">
									<span>�����Ա����</span>
								</th>
							</tr>
						</thead>
					<tbody>
					<tr>
							<th><span class="red">*</span>����ѧ��</th>
							<td>
								<html:select  property="xn" styleId="xn" style="width:130px">
									<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
						<tr>
							<th>
								��������
								<br /><font color="red">&lt;��400��&gt;</font>
							</th>
							<td width="34%" colspan="3" rowspan="4">
								<html:textarea property="sqly" onblur="chLengs(this,400);" styleId="sqly" rows="4" style="width:99%" ></html:textarea>
							</td>
						</tr>
				</tbody>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="sqly">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="save();">
										�� ��
									</button>
									<button type="button" onclick="refreshParent2();">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</table>	
		</html:form>
	</body>
</html>