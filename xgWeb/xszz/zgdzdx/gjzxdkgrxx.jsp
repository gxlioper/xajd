<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<base target="_self" />
	</head>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		</script>
		<html:form action="/zgdzdx_xszz.do?method=gjzxdkshSave" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<div class="tab">
				<logic:equal name="userType" value="stu">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>������ѧ���������ϸ��Ϣ</span></th>
			        </tr>
			    </thead>

				<tr>
					<th align="center" width="16%">
						ѧ��
					</th>
					<td align="left" width="34%">
						<bean:write name='rs' property="xh" />
					</td>
					<th width="16%">
							����
					</th>
					<td width="34%">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<th>
							�Ա�
					</th>
					<td>
						<bean:write name="rs" property="xb" />
					</td>
					<th>
							��������
					</th>
					<td>
						<bean:write name="rs" property="csrq" />
					</td>
				</tr>
				<tr>
					<th>
							���֤��
					</th>
					<td>
						<bean:write name="rs" property="sfzh"/>
					</td>
					<th>
							����绰
					</th>
					<td>
						<bean:write name="rs" property="ssdh"/>
					</td>
				</tr>
				<tr>
					<th>
							�꼶
					</th>
					<td>
						<bean:write name="rs" property="nj"/>
					</td>
					<th>
							<bean:message key="lable.xsgzyxpzxy" />����
					</th>
					<td>
						<bean:write name="rs" property="xymc"/>
					</td>
				</tr>
				<tr>
					<th>
							רҵ����
					</th>
					<td>
						<bean:write name="rs" property="zymc"/>
					</td>
					<th>
							�������
					</th>
					<td>
						<bean:write name="rs" property="bjmc"/>
					</td>
				</tr>
				<tr>
					<th>
							ѧ��
					</th>
					<td>
						<bean:write name="rs" property="xz"/>
					</td>
					<th>
							E-Mail��QQ
					</th>
					<td>
						<bean:write name="rs" property="emailqq"/>
					</td>
				</tr>
				<tr>
					<th>
							��ҵʱ��
					</th>
					<td>
						<bean:write name="rs" property="byny"/>
					</td>
					<th>
							�Ƿ��ҵ
					</th>
					<td>
						<bean:write name="rs" property="sfby"/>
					</td>
				</tr>
				<tr>
					<th>
							���򾭼�״��
					</th>
					<td>
						<bean:write name="rs" property="qyjjzk"/>
					</td>
					<th>
							�Ƿ�Ϊ�ſ����е��ؾ���
					</th>
					<td>
						<bean:write name="rs" property="sfwfkyhddjm"/>
					</td>
				</tr>
				<tr>
					<th>
							��ͥ�¾�����
					</th>
					<td>
						<bean:write name="rs" property="jtysr"/>
					</td>
					<th>
							��ͥ��ϸ��ַ
					</th>
					<td>
						<bean:write name="rs" property="jtxxzz"/>
					</td>
				</tr>
				<tr>
					<th>
							��ͥ�ʱ�
					</th>
					<td>
						<bean:write name="rs" property="jtyb"/>
					</td>
					<th>
							��ͥ�绰
					</th>
					<td>
						<bean:write name="rs" property="jtdh"/>
					</td>
				</tr>
				<tr>
					<th>
							��������
					</th>
					<td>
						<bean:write name="rs" property="fqxm"/>
					</td>
					<th>
							ĸ������
					</th>
					<td>
						<bean:write name="rs" property="mqxm"/>
					</td>
				</tr>
				<tr>
					<th>
							����ְҵ
					</th>
					<td>
						<bean:write name="rs" property="fqzy"/>
					</td>
					<th>
							ĸ��ְҵ
					</th>
					<td>
						<bean:write name="rs" property="mqzy"/>
					</td>
				</tr>
				<tr>
					<th>
							�������֤��
					</th>
					<td>
						<bean:write name="rs" property="fqsfzh"/>
					</td>
					<th>
							ĸ�����֤��
					</th>
					<td>
						<bean:write name="rs" property="mqsfzh"/>
					</td>
				</tr>
				<tr>
					<th>
							����״��
					</th>
					<td>
						<bean:write name="rs" property="hyzk"/>
					</td>
					<th>
							��ż����
					</th>
					<td>
						<bean:write name="rs" property="pomc"/>
					</td>
				</tr>
				<tr>
					<th>
							��ż��ϵ�绰
					</th>
					<td>
						<bean:write name="rs" property="polxdh"/>
					</td>
					<th>
							������λ
					</th>
					<td>
						<bean:write name="rs" property="gzdw"/>
					</td>
				</tr>
				<tr>
					<th>
							��λ�绰
					</th>
					<td>
						<bean:write name="rs" property="dwdh"/>
					</td>
					<th>
							�ƶ��绰
					</th>
					<td>
						<bean:write name="rs" property="yddh"/>
					</td>
				</tr>
				<tr>
					<th>
							��λ��ַ
					</th>
					<td colspan="3">
						<bean:write name="rs" property="dwdz"/>
					</td>
				</tr>
				<tr>
					<th>
							��λ�ʱ�
					</th>
					<td>
						<bean:write name="rs" property="dwyb"/>
					</td>
					<th>
							��ϵ������
					</th>
					<td>
						<bean:write name="rs" property="lxrxm"/>
					</td>
				</tr>
				<tr>
					<th>
							��ϵ�˳�������
					</th>
					<td>
						<bean:write name="rs" property="lxrcsrq"/>
					</td>
					<th>
							��ϵ���Ա�
					</th>
					<td>
						<bean:write name="rs" property="lxrxb"/>
					</td>
				</tr>
				<tr>
					<th>
							��ϵ����ϵ�绰
					</th>
					<td>
						<bean:write name="rs" property="lxrlxdh"/>
					</td>
					<th>
							��ϵ�������˹�ϵ
					</th>
					<td>
						<bean:write name="rs" property="lxrgx"/>
					</td>
				</tr>
				<tr>
					<th>
							��ϵ�˵�λ/��ַ
					</th>
					<td colspan="3">
						<bean:write name="rs" property="lxrdwdz"/>
					</td>
				</tr>
				<tr>
					<th>
							��ע
					</th>
					<td colspan="3">
						<bean:write name="rs" property="bz"/>
					</td>
				</tr>
				<tr>
					<th>
							��ͬ���
					</th>
					<td>
						<bean:write name="rs" property="htbh" />
					</td>
					<th>
							������
					</th>
					<td>
						<bean:write name="rs" property="sqdkje"/>
					</td>
				</tr>
				<tr>
					<th>
							��������(��)
					</th>
					<td>
						<bean:write name="rs" property="dkqxy"/>
					</td>
					<th>
							��������
					</th>
					<td>
						<bean:write name="rs" property="dkqx"/>
					</td>
				</tr>
				<tr>
					<th>
							��������
					</th>
					<td>
						<bean:write name="rs" property="dkll"/>
					</td>
					<th>
							�ͻ���
					</th>
					<td>
						<bean:write name="rs" property="khh"/>
					</td>
				</tr>
				<tr>
					<th rowspan="2">
							��һѧ��
					</th>
					<th>
							�ſ���
					</th>
					<th>
							�Ƿ�ſ�
					</th>
					<th>
							���ʱ��
					</th>
				</tr>
				<tr>
					<td>
							<bean:write name="rs" property="fk_xn1_je"/>
					</td>
					<td>
							&nbsp;<bean:write name="rs" property="fk_xn1_sffk"/>&nbsp;
					</td>
					<td>
							<bean:write name="rs" property="fk_xn1_tksj"/>
					</td>
				</tr>
				<tr>
					<th rowspan="2">
							�ڶ�ѧ��
					</th>
					<th>
							�ſ���
					</th>
					<th>
							�Ƿ�ſ�
					</th>
					<th>
							���ʱ��
					</th>
				</tr>
				<tr>
					<td>
							<bean:write name="rs" property="fk_xn2_je"/>
					</td>
					<td>
							&nbsp;<bean:write name="rs" property="fk_xn2_sffk"/>&nbsp;
					</td>
					<td>
							<bean:write name="rs" property="fk_xn2_tksj"/>
					</td>
				</tr>
				<tr>
					<th rowspan="2">
							����ѧ��
					</th>
					<th>
							�ſ���
					</th>
					<th>
							�Ƿ�ſ�
					</th>
					<th>
							���ʱ��
					</th>
				</tr>
				<tr>
					<td>
							<bean:write name="rs" property="fk_xn3_je"/>
					</td>
					<td>
							&nbsp;<bean:write name="rs" property="fk_xn3_sffk"/>&nbsp;
					</td>
					<td>
							<bean:write name="rs" property="fk_xn3_tksj"/>
					</td>
				</tr>
				<tr>
					<th rowspan="2">
							����ѧ��
					</th>
					<th>
							�ſ���
					</th>
					<th>
							�Ƿ�ſ�
					</th>
					<th>
							���ʱ��
					</th>
				</tr>
				<tr>
					<td>
							<bean:write name="rs" property="fk_xn4_je"/>
					</td>
					<td>
							&nbsp;<bean:write name="rs" property="fk_xn4_sffk"/>&nbsp;
					</td>
					<td>
							<bean:write name="rs" property="fk_xn4_tksj"/>
					</td>
				</tr>
				<logic:equal value="5" property="xz" name="rs">
				<tr>
					<th rowspan="2">
							����ѧ��
					</th>
					<th>
							�ſ���
					</th>
					<th>
							�Ƿ�ſ�
					</th>
					<th>
							���ʱ��
					</th>
				</tr>
				<tr>
					<td>
							<bean:write name="rs" property="fk_xn5_je"/>
					</td>
					<td>
							&nbsp;<bean:write name="rs" property="fk_xn5_sffk"/>&nbsp;
					</td>
					<td>
							<bean:write name="rs" property="fk_xn5_tksj"/>
					</td>
				</tr>
				</logic:equal>
				<tr>
					<th>
							�ſ��ܽ��
					</th>
					<td>
						<bean:write name="rs" property="fkzje"/>
					</td>
					<th>
							�������
					</th>
					<td>
						<bean:write name="rs" property="dkye"/>
					</td>
				</tr>
				<tr>
					<th>
							��ѧ��������ʱ��
					</th>
					<td>
						<bean:write name="rs" property="sqsj"/>
					</td>
					<th>
							����������������ϵʱ��
					</th>
					<td>
						<bean:write name="rs" property="jkrzhyyhlxsj"/>
					</td>
				</tr>
				<tr>
					<th>
							����˻������
					</th>
					<td colspan="3">
						<bean:write name="rs" property="jkrhkqk"/>
					</td>
				</tr>
				<tr>
					<th>
							�Ƿ�ǩ������Э��
					</th>
					<td>
						<bean:write name="rs" property="sfqdhkxy"/>
					</td>
					<th>
							����Э��ǩ��ʱ��
					</th>
					<td>
						<bean:write name="rs" property="hkxyqssj"/>
					</td>
				</tr>
				<tr>
					<th>
							������ۺ�
					</th>
					<td>
						<bean:write name="rs" property="hkczh"/>
					</td>
					<th>
							�����ʻ����
					</th>
					<td>
						<bean:write name="rs" property="hkzhye"/>
					</td>
				</tr>
				<tr>
					<th>
							չ��ʱ��
					</th>
					<td>
						<bean:write name="rs" property="zqsj"/>
					</td>
					<th>
							չ������
					</th>
					<td>
						<bean:write name="rs" property="zqqx"/>
					</td>
				</tr>
				<tr>
					<th>
							չ������
					</th>
					<td>
						<bean:write name="rs" property="zqlv"/>
					</td>
					<th>
							չ�ڽ��
					</th>
					<td>
						<bean:write name="rs" property="zqje"/>
					</td>
				</tr>
				<tr>
					<th>
							չ�����ڵ�λ
					</th>
					<td colspan="3">
						<bean:write name="rs" property="zqszdw"/>
					</td>
				</tr>
				<tr>
					<th>
							�������Ƿ��Ϣ�ܶ�
					</th>
					<td>
						<bean:write name="rs" property="jkrtqlxze"/>
					</td>
					<th>
							�������Ƿ��Ϣʱ��
					</th>
					<td>
						<bean:write name="rs" property="jkrtqlxsj"/>
					</td>
				</tr>
				<tr>
					<th>
							�������Ƿ�����ܶ�
					</th>
					<td>
						<bean:write name="rs" property="jkrtqbjze"/>
					</td>
					<th>
							�������Ƿ����ʱ��
					</th>
					<td>
						<bean:write name="rs" property="jkrtqbjsj"/>
					</td>
				</tr>
				<tr>
					<th>
							�Ƿ���ǰ����
					</th>
					<td>
						<bean:write name="rs" property="sftqhk"/>
					</td>
					<th>
							��ǰ������
					</th>
					<td>
						<bean:write name="rs" property="tqhkje"/>
					</td>
				</tr>
				<tr>
					<th>
							���ѷ�ʽ
					</th>
					<td>
						<bean:write name="rs" property="txfs"/>
					</td>
					<th>
							�����Ѵ���
					</th>
					<td>
						<bean:write name="rs" property="ytxcs"/>
					</td>
				</tr>
				<tr>
					<th>
							���һ������ʱ��
					</th>
					<td>
						<bean:write name="rs" property="zjyctxsj"/>
					</td>
					<th>
							�Ƿ���д����ȷ����
					</th>
					<td>
						<bean:write name="rs" property="sftxzlqrs"/>
					</td>
				</tr>
			</table>
			</logic:equal>
			<logic:notEqual name="userType" value="stu">
				<div align="center">
					<br />
					<h3>�ù���ֻ���Ÿ�ѧ����</h3>
				</div>
			</logic:notEqual>
		</html:form>
	</body>
</html>
