<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
	</head>
	<body>
		
		<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��ҵ���� - ��ҵЭ�鷽�� - ��ҵЭ��</a>
				</p>
			</div>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
				<tbody>
				<tr style="height:22px">
					<td colspan="4" align="center" bgcolor="DOEOEE">
						<b>��һ����</b>
					</td>
				</tr>
				<tr bgcolor="DOEOEE">
					<td align="left" colspan="4">
						ѧ�����:
						<html:text property="xslb" name="rs" readonly="true"
							style="width:45px" />
						&nbsp;&nbsp;��ҵ���:
						<html:text property="bynd" name="rs" readonly="true"
							style="width:35px" />
						&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />:
						<html:text property="xymc" name="rs" readonly="true"
							style="width:120px" />
						&nbsp;&nbsp;�ύʱ��:
						<html:text name="rs" property="tjsj" readonly="true"
							style="width:140px" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right" width="17%">
						ѧ��
					</th>
					<td align="left" width="33%">
						<bean:write name="rs" property="xsxh" />
					</td>

					<th align="right" width="17%">
					</th>
					<td align="left" width="33%">
					</td>
				</tr>
				<tr style="height:22px">
						<th align="right">
							Э������
						</th>
						<td align="left">
							<bean:write name="rs" property="xysbh" />
						<th align="right" style="width=20%">
							����
						</th>
						<td align="left">
							<bean:write name="rs" property="name" />
						</td>
					</tr>
				<tr style="height:22px">
					<th align="right">
						�Ա����
					</th>
					<td align="left">
						<bean:write name="rs" property="xbdm" />
					<th align="right">
						���֤��
					</th>
					<td align="left">
						<bean:write name="rs" property="id" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						ѧУ����
					</th>
					<td align="left">
						<bean:write name="rs" property="xxdm" />
					</td>
					<th align="right">
						ѧУ����
					</th>
					<td align="left">
						<bean:write name="rs" property="xxmc" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						רҵ����
					</th>
					<td align="left">
						<bean:write name="rs" property="zydm" />
					</td>
					<th align="right">
						רҵ����
					</th>
					<td align="left">
						<bean:write name="rs" property="zymc" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						ѧ�ƴ���
					</th>
					<td align="left">
						<bean:write name="rs" property="xzdm" />
					</td>
					<th align="right">
						ѧ������
					</th>
					<td align="left">
						<bean:write name="rs" property="xldm" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						������ʽ����
					</th>
					<td align="left">
						<bean:write name="rs" property="pyfsdm" />
					</td>
					<th align="right">
						��Դ��������
					</th>
					<td align="left">
						<bean:write name="rs" property="sydqdm" />
					</td>
				</tr>

				<tr style="height:22px">
					<td colspan="4" align="center" bgcolor="DOEOEE">
						<b>�ڶ�����</b>
					</td>
				</tr>


				<tr style="height:22px">
					<th align="right">
						��ҵȥ�����
					</th>
					<td align="left">
						<bean:write name="rs" property="byqxdm" />
					</td>
					<th align="right">
						��Դ��������
					</th>
					<td align="left">
						<bean:write name="rs" property="sydq" />
					</td>

				</tr>
				<tr style="height:22px">
					<th align="right">
						��λ��������
					</th>
					<td align="left">
						<bean:write name="rs" property="dwdq" />
					</td>

					<th align="right">
						���ܲ�������
					</th>
					<td align="left">
						<bean:write name="rs" property="zgbm" />
					</td>

				</tr>
				<tr style="height:22px">
					<th align="right">
						��λ��������
					</th>
					<td align="left">
						<bean:write name="rs" property="dwdqdm" />
					</td>
					<th align="right">
						���ܲ��Ŵ���
					</th>
					<td align="left">
						<bean:write name="rs" property="zgbmdm" />
					</td>


				</tr>
				<tr style="height:22px">
					<th align="right">
						��Ϣ�ǼǺ�
					</th>
					<td align="left">
						<bean:write name="rs" property="xxdjh" />
					</td>
					<th align="right">
						��֯��������
					</th>
					<td align="left">
						<bean:write name="rs" property="zzjgdm" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						��λ����
					</th>
					<td align="left">
						<bean:write name="rs" property="dwmc" />
					</td>
					<th align="right">
						������ò����
					</th>
					<td align="left">
						<bean:write name="rs" property="zzmmdm" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						δ��ҵ��־
					</th>
					<td align="left">
						<bean:write name="rs" property="wjybz" />
					</td>
					<th align="right">
						�Զ���1
					</th>
					<td align="left">
						<bean:write name="rs" property="bz1" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						δ��ҵԭ��
					</th>
					<td align="left">
						<bean:write name="rs" property="wjyyy" />
					</td>
					<th align="right">
						�Զ���2
					</th>
					<td align="left">
						<bean:write name="rs" property="bz2" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						��ϵ��ַ
					</th>
					<td align="left">
						<bean:write name="rs" property="lxdz" />
					</td>
					<th align="right">
						�Զ���3
					</th>
					<td align="left">
						<bean:write name="rs" property="bz3" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						�ʱ�
					</th>
					<td align="left">
						<bean:write name="rs" property="yb" />
					</td>
					<th align="right">
						�Զ���4
					</th>
					<td align="left">
						<bean:write name="rs" property="bz4" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						�绰
					</th>
					<td align="left">
						<bean:write name="rs" property="dh" />
					</td>
					<th align="right">
						�Զ���5
					</th>
					<td align="left">
						<bean:write name="rs" property="bz5" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						
					</th>
					<td align="left">
						
					</td>
					<th align="right">
						��ס֤�������־λ
					</th>
					<td align="left">
						<bean:write name="rs" property="jzzhlbbzwdm" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						��Դ�����ܵ�λ
					</th>
					<td align="left">
						<bean:write name="rs" property="sydzgbm" />
					</td>
					<th align="right">
						��λ���ʴ���
					</th>
					<td align="left">
						<bean:write name="rs" property="dwxzdm" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						������׼�ĺ�
					</th>
					<td align="left">
						<bean:write name="rs" property="blueno" />
					</td>
					<th align="right">
						��ע
					</th>
					<td align="left">
						<bean:write name="rs" property="memo" />
					</td>
				</tr>

				<tr style="height:22px">
					<td colspan="4" align="center" bgcolor="DOEOEE">
						<b>��������</b>
					</td>
				</tr>


				<tr style="height:22px">
					<th align="right">
						��λ���ʴ���2
					</th>
					<td align="left">
						<bean:write name="rs" property="dwxzdm2" />
					</td>
					<th align="right">
							<logic:equal value="10856" name="xxdm">
								�������յ�λ����
							</logic:equal>
							<logic:notEqual value="10856" name="xxdm">
								�������յ�
							</logic:notEqual>
					</th>
					<td align="left">
						<bean:write name="rs" property="dajsd" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						��λ��ַ
					</th>
					<td align="left">
						<bean:write name="rs" property="dwdz" />
					</td>
					<th align="right">
						�������յص�ַ
					</th>
					<td align="left">
						<bean:write name="rs" property="dajsddz" />
					</td>
				</tr>
				<tr style="height:22px">
						<th align="right">
							��λ��ϵ��
						</th>
						<td align="left">
							<bean:write name="rs" property="dwlxr" />
						</td>
						<th align="right">
							�������յ�λ����
						</th>
						<td align="left">
							<bean:write name="rs" property="dajsddwmc" />
						</td>
					</tr>
				<tr style="height:22px">
					<th align="right">
						��λ�绰
					</th>
					<td align="left">
						<bean:write name="rs" property="dwdh" />
					</td>
					<th align="right">
						�������յ��ʱ�
					</th>
					<td align="left">
						<bean:write name="rs" property="dajsdyb" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						��λ�ʱ�
					</th>
					<td align="left">
						<bean:write name="rs" property="dwyb" />
					</td>
					<th align="right">
							<logic:equal value="10856" name="xxdm">
								��λ���ڵ���
							</logic:equal>
							<logic:notEqual value="10856" name="xxdm">
								��������
							</logic:notEqual>
					</th>
					<td align="left">
						<bean:write name="rs" property="dqlx" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						ΥԼ��
					</th>
					<td align="left">
						<bean:write name="rs" property="wyj" />
					</td>
					<th align="right">
						��ҵ����
					</th>
					<td align="left">
						<bean:write name="rs" property="hyfl" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						��һ����ƽ������
					</th>
					<td align="left">
						<bean:write name="rs" property="dynypjgz" />
					</td>
					<th align="right">
						רҵ�Կ�
					</th>
					<td align="left">
						<bean:write name="rs" property="zydk" />
					</td>
				</tr>
				<tr style="height:22px">
						<th align="right">
							��λ��ģ
						</th>
						<td align="left">
							<bean:write name="rs" property="dwgm" />
						</td>
						<th align="right">
							��λע���ʽ�
						</th>
						<td align="left">
							<bean:write name="rs" property="dwzczj" />
						</td>
					</tr>
						<tr style="height:22px">
						<th align="right">
							������ʼʱ��
						</th>
						<td align="left">
								<bean:write name="rs" property="bdkssj" />
						</td>
						<th align="right">
							��������ʱ��
						</th>
						<td align="left">
							<bean:write name="rs" property="bdjssj" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							����֤��
						</th>
						<td align="left">
							<bean:write name="rs" property="bdzh" />
						</td>
						<th align="right">
						</th>
						<td align="left">
						</td>
					</tr>
				<tr style="height:22px">
					<th align="right">
						ѧУ��˽��
					</th>
					<td align="left">
						<html:text name="rs" property="xxsh" readonly="true"
							style="width=100%" />
					</td>
					<th align="right">
						ѧУ�����
					</th>
					<td align="left">
						<html:text name="rs" property="xxshren" readonly="true"
							style="width=100%" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						ѧУ���ʱ��
					</th>
					<td align="left" colspan="3">
						<html:text name="rs" property="xxshsj" readonly="true"
							style="width=100%" />
					</td>
				</tr>

				<tr>
					<th align="right">
						��ͨ��ԭ���޸����
					</th>
					<td colspan="3">
						<html:textarea name="rs" property="btgyy" rows="4"
							style="word-break:break-all;width:99%" readonly="true" />
					</td>
				</tr>
				<!-- �ж��Ƿ��и����ϴ� -->
				<logic:present name="youFj">
					<th align="right">
						������ϻ򸽼�����
					</th>
					<td colspan="3">
						<a href="downloadfilewj.do?wjsclj=${rs.wjdz }" target="_self">����</a>
					</td>
				</logic:present>
			</table>
		</fieldset>
	</body>
</html>
