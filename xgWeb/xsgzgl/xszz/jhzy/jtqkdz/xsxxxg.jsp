<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>	
	</head>

	<body>
		<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>ѧ����Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>ѧ��
						</th>
						<td align="left" width="30%">
							${rs.xh }
							<input type="hidden" name="xh" value="${rs.xh }"/>
						</td>
						<th align="right" width="20%">
							����
						</th>
						<td align="left" width="30%">
							${rs.xm}
						</td>
					</tr>
					<tr>
						<th align="right">
							�Ա�
						</th>
							<td align="left">
							${rs.xb}
						</td>
						<th align="right">
							�꼶
						</th>
							<td align="left">
							${rs.nj}
						</td>
					</tr>
					<tr>
						<th align="right">
							<bean:message key="lable.xb" />
						</th>
							<td align="left">
							${rs.xymc}
						</td>
						<th align="right">
							רҵ
						</th>
							<td align="left">
							${rs.zymc}
						</td>
					</tr>
					<tr>
						<th align="right">
							�༶
						</th>
						<td align="left">
							${rs.bjmc}
						</td>
						<th align="right">
							ѧ��
						</th>
							<td align="left">
							${rs.xz}
						</td>
						
					</tr>
					<tr>
						<th align="right">
							���֤��
						</th>
						<td align="left">
							${rs.sfzh}
						</td>
						<th align="right">
							��������
						</th>
							<td align="left">
							${rs.csrq}
						</td>
						
					</tr>
					<tr>
						<th align="right">
							����
						</th>
						<td align="left">
							${rs.mzmc}
						</td>
						<th align="right">
							��ѧ����
						</th>
							<td align="left">
							${rs.rxrq}
						</td>
						
					</tr>
					<tr>
						<th align="right">
							����
						</th>
						<td align="left">
							${rs.jgmc}
						</td>
						<th align="right">
							������ò
						</th>
							<td align="left">
							${rs.zzmmmc}
						</td>
						
					</tr>
					<tr>
						<th align="right">
							<font color="red">*</font>��ҵѧУ
						</th>
						<td align="left">
							<html:text property="byxx" styleClass="bcClass" styleId="byxx" maxlength="30" style="width:230px" value="${rs.byxx}" ></html:text>
						</td>
						<th align="right">
							<font color="red">*</font>�ֻ�����
						</th>
							<td align="left">
							<html:text property="sjhm" styleClass="bcClass" styleId="sjhm" maxlength="20" value="${rs.sjhm}"></html:text>
						</td>
						
					</tr>
					<tr>
						<th align="right">
							<font color="red">*</font>�س�
						</th>
						<td align="left" colspan="3">
							<html:text property="tc" styleClass="bcClass" styleId="tc" maxlength="50" value="${rs.tc}"  style="width:230px"></html:text>
						</td>
						
					</tr>
					</tbody>
					</table>
					
	</body>

	</html>