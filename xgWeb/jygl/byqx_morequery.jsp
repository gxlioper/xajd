<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
	</head>
	<body>
		<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��ҵ���� - ѧ����Ϣ - ��ҵȥ����Ϣ</a>
				</p>
		</div>
		<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
					<tr>
						<td align="left" colspan="4">
							��ҵ���
							<html:text property="bynd" name="rs" style="width:35px"
								readonly="true" />
							<bean:message key="lable.xsgzyxpzxy" />
							<html:text property="xymc" name="rs" style="width:130px"
								readonly="true" />
							�ύʱ��
							<html:text name="rs" property="tjsj" style="width:140px" readonly="true" />
						</td>
					</tr>
				</thead>
				<tbody>
				<tr style="height:22px">
					<th width="15%">
						ѧ��
					</th>
					<td align="left" width="35%">
						<bean:write name="rs" property="xsxh"  />
					</td>
					<th width="15%">
						����
					</th>
					<td width="35%">
						<bean:write name="rs" property="name"  />
					</td>
				</tr>
				<tr style="height:22px">
					<th>
						�Ա�
					</th>
					<td align="left">
						<bean:write name="rs" property="xb"  />
					<th>
						���֤��
					</th>
					<td>
						<bean:write name="rs" property="id"  />
					</td>
				</tr>
				<tr style="height:22px">
					<th>
						��Դ����
					</th>
					<td >
						<bean:write name="rs" property="sydq"  />
					</td>
					<th>
						<bean:message key="lable.xb" />
					</th>
					<td >
						<bean:write name="rs" property="xymc"  />
					</td>
				</tr>
				<tr style="height:22px">
					<th>
						רҵ
					</th>
					<td align="left">
						<bean:write name="rs" property="zymc"  />
					<th>
						�༶
					</th>
					<td>
						<bean:write name="rs" property="bjmc"  />
					</td>
				</tr>
				<tr style="height:22px">
					<th>
						��ҵȥ��
					</th>
					<td>
					    <bean:write name="rs" property="byqx"  />
					</td>
					<th>
						��ϵ��ַ
					</th>
					<td align="left">
						<bean:write name="rs" property="lxdz" />
					</td>
				</tr>
				<tr style="height:22px">
					<th>
						��ϵ�绰
					</th>
					<td align="left">
						<bean:write name="rs" property="lxdh" />
					</td>
					<th>
						��������
					</th>
					<td align="left">
						<bean:write name="rs" property="yzbm" />
					</td>
				</tr>
				<tr style="height:22px">
					<th>
						�ƶ��绰
					</th>
					<td align="left">
						<bean:write name="rs" property="yddh" />
					</td>
					<th>
						��������
					</th>
					<td align="left">
						<bean:write name="rs" property="email" />
					</td>
				</tr>
				<tr style="height:22px">
					<th>
						ѧУ��˽��
					</th>
					<td align="left">
						<html:text name="rs" property="xxsh" style="width=100%" readonly="true"  />
					</td>
					<th>
						���ʱ��
					</th>
					<td align="left">
						<html:text name="rs" property="shsj" style="width=100%" readonly="true" />
					</td>
				</tr>
				<tr style="height:22px">
					<th>
						��ҵ��λ
					</th>
					<td align="left">
						${rs.jydw}
					</td>
					<th>
						
					</th>
					<td align="left">
						
					</td>
				</tr>
				<tr style="height:55px">
				    <th>
						�޸����
					</th>
					<td colspan="3">
						<html:textarea name="rs" property="xgyj" rows="3" 
							style="word-break:break-all;width:99%" readonly="true"/>
					</td>
				</tr>
				<tr style="height:22px">
					<th>
						�����
					</th>
					<td colspan="3">
						<html:text name="rs" property="shperson" style="width=100%" readonly="true" />
					</td>
				</tr>
				</tbody>
				<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<button name="�ر�" onclick="Close();return false;" id="buttonClose">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
			</table>
		</fieldset>
	</body>
</html>
