<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
		<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>������ - <bean:message key="lable.xsgzyxpzxy" />���������� - 
					<logic:equal value="10827" name="xxdm">
						����ίԱ
					</logic:equal>
					<logic:notEqual value="10827" name="xxdm">
						ѧ������۲�Ա
					</logic:notEqual>
					- �鿴
					</a>
				</p>
			</div>
	
	
		<html:form action="/xljk_xsxlgcy" method="post">
		<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button  onclick="Close();return false;" >
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
				<tr>
					<th colspan="2">
						<font color="red">*</font>ѧ��
					</th>
					<td align="left">
						<html:text property="xh" styleId="xh" readonly="true" />
					</td>
					<th>
						����
					</th>
					<td align="left">
						<html:text property="xm" styleId="xm" readonly="true" />
					</td>
				</tr>
				<tr>
					<th colspan="2">
						�Ա�
					</th>
					<td align="left">
						<html:text property="xb" styleId="xb" readonly="true" />
					</td>
					<th>
						��������
					</th>
					<td align="left">
						<html:text property="csny" styleId="csrq" readonly="true" />
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left">
						<html:text property="xymc" styleId="xymc" readonly="true" />
					</td>
					<th>
						�༶
					</th>
					<td align="left">
						<html:text property="bjmc" styleId="bjmc" readonly="true" />
					</td>
				</tr>
				<tr>
				<logic:equal value="10856" name="xxdm">
					<th colspan="2">
						�꼶
					</th>
				<td align="left">
						<html:text property="nj" styleId="zy" readonly="true" />
					</td>
					</logic:equal>
					<logic:notEqual value="10856" name="xxdm">
					<th colspan="2">
						�ֻ�����
					</th>
				<td align="left">
						<html:text property="sjhm" styleId="zy" readonly="true" />
					</td>
					</logic:notEqual>
					<th>
						���ҵ绰
					</th>
					<td align="left">
						<html:text property="qsdh" readonly="true" />
					</td>
				</tr>
				<tr>
				<logic:notEqual value="10856" name="xxdm">
					<th colspan="2">
						<logic:equal value="10827" name="xxdm">
							����ίԱ���
						</logic:equal>
						<logic:notEqual value="10827" name="xxdm">
							�۲�Ա���
						</logic:notEqual>
					</th>
					<td align="left">
						<html:text property="gcybh" readonly="true" />
					</td>
					<th></th>
					<td></td>
					</logic:notEqual>
					<logic:equal value="10856" name="xxdm">
					<th colspan="2">
						�����Գɼ�
					</th>
					<td align="left">
						<html:text property="xlkscj" styleId="xlkscj" readonly="true"/>
					</td>
					<th>
						�ֻ�����
					</th>
					<td align="left">
						<html:text property="sjhm" styleId="zy" readonly="true" />
					</td>
					</logic:equal>
				</tr>
				<tr>
					<th colspan="2">
						��ע
					</th>
					<td colspan="4" style="word-break:break-all;">
						<html:textarea rows="5" style="width:98%" property="bz"
							styleId="a" readonly="true" />
					</td>
				</tr>
				</tbody>
			</table>
			</div>
		</html:form>
	</body>
</html>
