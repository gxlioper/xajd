<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!-- ͷ�ļ� -->
<html>
	<head>
		<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	</head>

	<body>
		<html:form action="zxdksq.do" method="post">
			<div align="right">
				<h4>
					��ͬ���&nbsp;
					<logic:empty name="rs" property="htbh">
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
					</logic:empty>
					<logic:notEmpty name="rs" property="htbh">
						<u>&nbsp;<bean:write name="rs" property="htbh" />&nbsp;</u>
					</logic:notEmpty>
				</h4>
			</div>
			<div align="center" style="font-size:21px;font-family:����;">
				
					�й����й�����ѧ��������������
				
			</div>
				<br/>
					����֧�У�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					�����ţ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				
						<table width="100%" class="printtab" style="font-family:����;font-size:14px;">
							<tr>
								<td width="7%"></td>
								<td width="5%"></td>
								<td width="4%"></td>
								<td width="5%"></td>
								<td width="5%"></td>
								<td width="5%"></td>
								<td width="5%"></td>
								<td width="5%"></td>
								<td width="5%"></td>
								<td width="5%"></td>
								<td width="5%"></td>
								<td width="7%"></td>
								<td width="5%"></td>
								<td width="5%"></td>
								<td width="7%"></td>
								<td width="5%"></td>
							</tr>
							<tr class="nowrap">
								<td width="10%" colspan="2">
									����������
								</td>
								<td colspan="3" width="20%">
									&nbsp;
									<bean:write name="rs" property="xm" />
								</td>
								<td width="6%">
									�Ա�
								</td>
								<td align="10%" colspan="4">
									&nbsp;&nbsp;&nbsp;
									<logic:equal name="rs" property="xb" value="��">
								��
								</logic:equal>
									<logic:notEqual name="rs" property="xb" value="��">
								��
								</logic:notEqual>
									&nbsp;��&nbsp;&nbsp;
									<logic:equal name="rs" property="xb" value="Ů">
								��
								</logic:equal>
									<logic:notEqual name="rs" property="xb" value="Ů">
								��
								</logic:notEqual>
									&nbsp;Ů
								</td>
								<td width="10%" colspan="2">
									��������
								</td>
								<td colspan="4">
									&nbsp;
									<logic:equal name="rs" property="csrq_year" value="">
								&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:equal>
									<logic:notEqual name="rs" property="csrq_year" value="">
										<bean:write name="rs" property="csrq_year" />
									</logic:notEqual>
									&nbsp;��&nbsp;
									<logic:equal name="rs" property="csrq_month" value="">
								&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:equal>
									<logic:notEqual name="rs" property="csrq_month" value="">
										<bean:write name="rs" property="csrq_month" />
									</logic:notEqual>
									&nbsp;��
								</td>
								<td rowspan="4" width="15%">
									��������Ƭ
								</td>
							</tr>
							<tr class="nowrap">
								<td colspan="2">
									���֤����
								</td>
								<td colspan="7">
									&nbsp;
									<bean:write name="rs" property="sfzh" />
								</td>
								<td colspan="2">
									����
								</td>
								<td colspan="5">
									&nbsp;
									<bean:write name="rs" property="mzmc" />
								</td>
							</tr>
							<tr class="nowrap">
								<td colspan="2">
									ѧУ
								</td>
								<td colspan="3">
									�й����ʴ�ѧ(�人)
								</td>
								<td>
									Ժϵ
								</td>
								<td colspan="3">
									<bean:write name="rs" property="xymc" />
								</td>
								<td colspan="2">
									רҵ
								</td>
								<td colspan="5">
									<bean:write name="rs" property="zymc" />
								</td>
							</tr>
							<tr>
								<td colspan="2">
									�༶
								</td>
								<td colspan="3">
									<bean:write name="rs" property="bjmc" />
								</td>
								<td>
									ѧ��
								</td>
								<td colspan="3">
									<bean:write name="rs" property="xh" />
								</td>
								<td colspan="2">
									������ò
								</td>
								<td colspan="5">
									<bean:write name="rs" property="zzmmmc" />
								</td>
							</tr>

							<tr>
								<td colspan="2">
									ѧ��
								</td>
								<td colspan="3">
									<bean:write name="rs" property="xz" />
									��
								</td>
								<td>
									ѧ��
								</td>
								<td colspan="11">
									<logic:equal name="rs" property="xl" value="ר��">
								&nbsp;&nbsp;&nbsp;&nbsp;��
								</logic:equal>
									<logic:notEqual name="rs" property="xl" value="ר��">
								��
								</logic:notEqual>
									&nbsp;ר�� &nbsp;&nbsp;
									<logic:equal name="rs" property="xl" value="����">
								��
								</logic:equal>
									<logic:notEqual name="rs" property="xl" value="����">
								��
								</logic:notEqual>
									&nbsp;����&nbsp;&nbsp; ��&nbsp;˶ʿ��&nbsp;&nbsp;��&nbsp;��ʿ��
								</td>
							</tr>
							<tr>
								<td colspan="2">
									����״��
								</td>
								<td colspan="3">
									&nbsp;
								</td>
								<td colspan="2">
									����״��
								</td>
								<td colspan="2">
									&nbsp;
								</td>
								<td colspan="2">
									����绰
								</td>
								<td colspan="3">
									&nbsp;
									<bean:write name="rs" property="ssdh" />
								</td>
								<td colspan="2">
									�ƶ��绰
								</td>
								<td>
									&nbsp;
									<bean:write name="rs" property="sjhm" />
								</td>
							</tr>
							<tr>
								<td rowspan="5" colspan="2">
									��ͥ���
								</td>
								<td colspan="3">
									��ͥ��ַ
								</td>
								<td colspan="4">
									<bean:write name="rs" property="jtxxzz" />
								</td>
								<td colspan="2">
									��������
								</td>
								<td colspan="3">
									<bean:write name="rs" property="jtyb" />
								</td>
								<td colspan="2">
									��ͥ������
								</td>
								<td>
									${nsr }Ԫ
								</td>
							</tr>
							<tr>
								<td colspan="3">
									��ͥ��Ա
								</td>
								<td colspan="3">
									����
								</td>
								<td colspan="3">
									������λ��ַ
								</td>
								<td colspan="4">
									֤�����ͼ�����
								</td>
								<td colspan="2">
									��ϵ�绰
								</td>
							</tr>
							<tr>
								<td colspan="3">
									����
								</td>
								<td colspan="3">
									<logic:notEmpty name="rs" property="fqxm">
										<bean:write name="rs" property="fqxm" />
									</logic:notEmpty>
									<logic:empty name="rs" property="fqxm">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								</td>
								<td colspan="3">
								</td>
								<td colspan="4">
									<logic:notEmpty name="rs" property="fqsfzh">
										<bean:write name="rs" property="fqsfzh" />
									</logic:notEmpty>
									<logic:empty name="rs" property="fqsfzh">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								</td>
								<td colspan="2">
								</td>
							</tr>
							<tr>
								<td colspan="3">
									ĸ��
								</td>
								<td colspan="3">
									<logic:notEmpty name="rs" property="mqxm">
										<bean:write name="rs" property="mqxm" />
									</logic:notEmpty>
									<logic:empty name="rs" property="mqxm">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								</td>
								<td colspan="3">
								</td>
								<td colspan="4">
									&nbsp;&nbsp;
									<logic:notEmpty name="rs" property="mqsfzh">
										<bean:write name="rs" property="mqsfzh" />
									</logic:notEmpty>
									<logic:empty name="rs" property="mqsfzh">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								</td>
								<td colspan="2">
								</td>
							</tr>
							<tr>
								<td colspan="3">
									��������
								</td>
								<td colspan="3">
								</td>
								<td colspan="3">
								</td>
								<td colspan="4">
								</td>
								<td colspan="2">
								</td>
							</tr>
							<tr>
								<td colspan="2" rowspan="5">
									��֤�����
								</td>
								<td colspan="3">
									����
								</td>

								<td colspan="2">
									���� 
								</td>
								<td colspan="2">
									�Ա�
								</td>
								<td>
									��&nbsp;��&nbsp;&nbsp;��&nbsp;Ů
								</td>
								<td colspan="4">
									֤�����ͼ�����
								</td>
								<td colspan="3">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td colspan="3">
									�����˹�ϵ
								</td>
								<td colspan="2">
									ʦ��
								</td>
								<td colspan="2">
									��ϵ�绰
								</td>
								<td colspan="3">
									027-67883443
								</td>
								<td colspan="2">
									ͨѶ��ַ
								</td>
								<td colspan="3">
									�й����ʴ�ѧ���人��ѧ������������
								</td>
							</tr>
							<tr>
								<td colspan="3">
									����
								</td>

								<td colspan="2">
									&nbsp;
								</td>
								<td colspan="2">
									�Ա�
								</td>
								<td>
									��&nbsp;��&nbsp;&nbsp;��&nbsp;Ů
								</td>
								<td colspan="4">
									֤�����ͼ�����
								</td>
								<td colspan="3">
								</td>
							</tr>
							<tr>
								<td colspan="3">
									�����˹�ϵ
								</td>
								<td colspan="2">
									&nbsp;
								</td>
								<td colspan="2">
									��ϵ�绰
								</td>
								<td colspan="3">

								</td>
								<td colspan="2">
									ͨѶ��ַ
								</td>
								<td colspan="3">

								</td>
							</tr>
							<tr style="height:10px">
								<td colspan="4">
									��֤��һ
									<br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���˼�֤����������ʵ�� ��Ը������֤����
								</td>
								<td colspan="4">
									ǩ��:
									<br>
									<br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
								</td>
								<td colspan="4">
									��֤�˶�
									<br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���˼�֤����������ʵ�� ��Ը������֤����
									<br>
								</td>
								<td colspan="3">
									ǩ��:
									<br>
									<br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
								</td>
							</tr>
							<tr>
								<td rowspan="3" colspan="2">
									����
									<br>
									���
									<br>
									���
								</td>
								<td colspan="3">
									��������
								</td>
								<td colspan="12">
									&nbsp;&nbsp;&nbsp;&nbsp;�����������Ϣ������ѧ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���ط�������Ϣ������ѧ����
								</td>
							</tr>
							<tr>
								<td colspan="3">
									�����ܽ��
								</td>
								<td colspan="4">
									<logic:notEmpty name="rs" property="sqdkje">
										<bean:write name="rs" property="sqdkje" />
									</logic:notEmpty>
									<logic:empty name="rs" property="sqdkje">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
									&nbsp;&nbsp;Ԫ
								</td>
								<td colspan="8">
									���У�ѧ��&nbsp;&nbsp;&nbsp;&nbsp;Ԫ��ס�޷�&nbsp;&nbsp;&nbsp;&nbsp;Ԫ�������&nbsp;&nbsp;&nbsp;&nbsp;Ԫ
								</td>
							</tr>
							<tr>
								<td colspan="3">
									��������
								</td>
								<td colspan="4">
									<logic:notEqual name="rs" property="dkqxy" value="">
										<bean:write name="rs" property="dkqxy" />
									</logic:notEqual>
									<logic:equal name="rs" property="dkqxy" value="">
								&nbsp;&nbsp;
								</logic:equal>
									&nbsp;&nbsp;��
								</td>
								<td colspan="8" align="center">
									��

									<logic:notEqual name="rs" property="dkqx1_year" value="">
										<bean:write name="rs" property="dkqx1_year" />
									</logic:notEqual>
									<logic:equal name="rs" property="dkqx1_year" value="">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:equal>
									��

									<logic:notEqual name="rs" property="dkqx1_mon" value="">
										<bean:write name="rs" property="dkqx1_mon" />
									</logic:notEqual>
									<logic:equal name="rs" property="dkqx1_mon" value="">
								&nbsp;&nbsp;
								</logic:equal>
									��

									<logic:notEqual name="rs" property="dkqx1_day" value="">
										<bean:write name="rs" property="dkqx1_day" />
									</logic:notEqual>
									<logic:equal name="rs" property="dkqx1_day" value="">
								&nbsp;&nbsp;
								</logic:equal>
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
									<logic:notEqual name="rs" property="dkqx2_year" value="">
										<bean:write name="rs" property="dkqx2_year" />
									</logic:notEqual>
									<logic:equal name="rs" property="dkqx2_year" value="">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:equal>
									��
									<logic:notEqual name="rs" property="dkqx2_mon" value="">
										<bean:write name="rs" property="dkqx2_mon" />
									</logic:notEqual>
									<logic:equal name="rs" property="dkqx2_mon" value="">
								&nbsp;&nbsp;
								</logic:equal>
									��
									<logic:notEqual name="rs" property="dkqx2_day" value="">
										<bean:write name="rs" property="dkqx2_day" />
									</logic:notEqual>
									<logic:equal name="rs" property="dkqx2_day" value="">
								&nbsp;&nbsp;
								</logic:equal>
									��
								</td>
							</tr>

							<tr>
								<td colspan="17">
									�����������
									<br>
									1�����������鼰������������������Ϊ�����������ȫ��ʵ������Ը��е��ɴ˲�����һ�з������Σ�
									<br>
									2�����˳����Դ���������Ϊ����н������ݣ����͵����ϸ�ӡ�����������������ƾ֤��
									<br>
									3����������鲻���Ϲ涨�Ľ��������δ������ʱ�����������飻
									<br>
									4�����˱�֤��ȡ�����д���󣬰�ʱ�������Ϣ��
									<br>
									5��δ�������д���ǰ�����������Ϸ����仯�����˱�֤�ڱ仯��һ�����������ṩ���º�����ϡ�
									<br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;������ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;�����໤��ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
								</td>
							</tr>
							<tr>
								<td colspan="17">
									ѧУ�����
									<br>
									1�������ϵ��У�Ͷ�ѧ����
									<br>
									2������˻�����Ϣ�ͼ�ͥ�������������ʵ��ͬ�������������ѧ���
									<br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���������/ѧУ��ǩ�£���&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
								</td>
							</tr>
							<tr>
								<td colspan="17">
									�ͻ����������
									<br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��������������������ѧ������ݹ���������߹涨����ί�н�������ڸ�У������������ȡ�����������ϣ������ֳ���֤��������˱���ǩ�֣���������ˣ���ͬ���������룬�����벿�����ܼ���Ȩ��������ˡ�
									<br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ͻ�����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
								</td>
							</tr>
							<tr>
								<td colspan="17">
									�������������
									<br>
									<div align="center">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ͬ��ͻ��������&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����ͬ��ͻ��������
										<br />
									</div>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�������ܣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
								</td>
							</tr>
							<tr>
								<td colspan="17">
									��Ȩ����������
									<br>
									<div align="center">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ͬ�ⷢ�Ŵ���&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����ͬ�ⷢ�Ŵ���
										<br />
									</div>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��Ȩ�����ˣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
								</td>
							</tr>
						</table>
						
						</html:form>
						<div align="center" class='noPrin'>
							<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
								ҳ������
							</button>
							<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
								��ӡԤ��
							</button>
							<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
								ֱ�Ӵ�ӡ
							</button>
						</div>
	</body>
</html>
