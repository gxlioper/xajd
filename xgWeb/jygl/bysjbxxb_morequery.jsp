<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
	</head>

	<body>
		<div class="tab">
			<table width="80%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="6">
							<span>ѧ����ϸ��Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>
							ѧ�����
						</th>
						<td align="left" width="25%">
							<bean:write name="rs" property="xslb" />
						</td>
						<th>
							����
							<bean:message key="lable.xsgzyxpzxy" />
						</th>
						<td>
							<bean:write name="rs" property="xymc" />
						</td>
					</tr>
					<tr>
						<th>
							ѧ��
						</th>
						<td>
							<bean:write name="rs" property="xsxh" />
						</td>
						<th>
							���֤��
						</th>
						<td>
							<bean:write name="rs" property="id" />
						</td>
					</tr>
					<tr>
						<th>
							����
						</th>
						<td>
							<bean:write name="rs" property="name" />
						</td>
						<th>
							�Ա�
						</th>
						<td>
							<bean:write name="rs" property="xbdm" />
						</td>
					</tr>
					<logic:equal value="10491" name="xxdm">
						<tr>
							<th>
								����
							</th>
							<td align="left">
								<bean:write name="rs" property="mz" />
							</td>
							<th>
								������ò
							</th>
							<td>
								<bean:write name="rs" property="zzmm" />
							</td>
						</tr>
					</logic:equal>
					<tr>
						<th>
							ѧУ����
						</th>
						<td>
							<bean:write name="rs" property="xxmc" />
						</td>
						<th>
							ѧУ����
						</th>
						<td>
							<bean:write name="rs" property="xxdm" />
						</td>
					</tr>
					<logic:equal value="10491" name="xxdm">
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
								����
							</th>
							<td align="left">
								<bean:write name="rs" property="xymc" />
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
								����
							</th>
							<td align="left" width="25%">
								<bean:write name="rs" property="xydm" />
							</td>
						</tr>
					</logic:equal>
					<tr>
						<th>
							רҵ����
						</th>
						<td align="left">
							<bean:write name="rs" property="zymc" />
						</td>
						<th align="right" width="25%" bgcolor="DOEOEE">
							רҵ����
						</th>
						<td align="left">
							<bean:write name="rs" property="zydm" />
						</td>
					</tr>
					<logic:equal value="10491" name="xxdm">
						<tr>
							<th>
								�༶����
							</th>
							<td align="left">
								<bean:write name="rs" property="bjdm" />
							</td>
							<th>
								��ϵ��ʽ
							</th>
							<td align="left">
								<bean:write name="rs" property="lxfs" />
							</td>
						</tr>
					</logic:equal>
					<tr>
						<th>
							ѧ��
						</th>
						<td align="left">
							<bean:write name="rs" property="xl" />
						</td>
						<th>
							ѧ��
						</th>
						<td align="left">
							<bean:write name="rs" property="xzdm" />
						</td>
					</tr>
					<tr>
						<th>
							��Դ����
						</th>
						<td align="left">
							<bean:write name="rs" property="sydq" />
						</td>
						<th>
							������ʽ
						</th>
						<td align="left">
							<bean:write name="rs" property="pyfs" />
						</td>
					</tr>
					<tr>
						<th>
							��ѧ���
						</th>
						<td align="left">
							<bean:write name="rs" property="nd" />
						</td>
						<th>
							�ϱ�ʱ��
						</th>
						<td align="left">
							<bean:write name="rs" property="sbsj" />
						</td>
					</tr>
					<tr>
						<th>
							��ҵ���
						</th>
						<td align="left">
							<bean:write name="rs" property="bynd" />
						</td>
						<logic:equal value="10491" name="xxdm">
							<th>
								Э������
							</th>
							<td align="left">
								<bean:write name="rs" property="xysbh" />
							</td>
						</logic:equal>

						<logic:notEqual value="10491" name="xxdm">
							<th>
								��ע
							</th>
							<td align="left">
								<bean:write name="rs" property="memo" />
							</td>
						</logic:notEqual>

					</tr>
					<logic:equal value="10491" name="xxdm">
						<tr>
							<th>
								����
							</th>
							<td align="left">
								<bean:write name="rs" property="lxdzxx" />
							</td>
							<th>
								QQ
							</th>
							<td align="left">
								<bean:write name="rs" property="qq" />
							</td>
						</tr>
					</logic:equal>
					<logic:equal value="10491" name="xxdm">
						<tr>
							<th>
								��ע
							</th>
							<td align="left" colspan="4" width="75%">
								<html:textarea name="rs" property="memo" readonly="true"
									style="word-break:break-all;width:99%" cols="45"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								ѧУ���
							</th>
							<td>
								<html:text name="rs" property="sfsh" readonly="true"
									style="width=100%" />
							</td>
						</tr>
						<tr>
							<th>
								�����
							</th>
							<td>
								<html:text name="rs" property="shperson" readonly="true"
									style="width=100%" />
							</td>
						</tr>
						<tr>
							<th>
								���ʱ��
							</th>
							<th>
								<html:text name="rs" property="shsj" readonly="true"
									style="word-break:break-all;width:99%" />
							</th>
						</tr>
						<tr>
							<th>
								��ͨ��ԭ���޸����
							</th>
							<td colspan="6" rowspan="2">
								<html:textarea name="rs" property="btgyy" rows="4"
									style="word-break:break-all;width:99%" readonly="true" />
							</td>
						</tr>
					</logic:equal>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4">
							<logic:notEqual name="doType" value="view">
							<div class="bz">
								"
								<span class="red">*</span>"Ϊ������
							</div>
							</logic:notEqual>
							<div class="btn">
								<button name="�ر�" onclick="window.close();return false;">
									�ر�
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</body>
</html>
