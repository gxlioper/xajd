<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead.ini"%>
	</head>
<body>
	<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	<!-- end -->
	<html:form action="/typj" method="post">
		<table width="95%" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
					<h2>
						${xxmc }${nd }���ҵ����ҵ�Ƽ���
					</h2>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
						<tr>
							<td align="center" width="10%"></td>
							<td align="center" width="16%"></td>
							<td align="center" width="12%"></td>
							<td align="center" width="12%"></td>
							<td align="center" width="12%"></td>
							<td align="center" width="10%"></td>
							<td align="center" width="14%"></td>
							<td align="center" width="14%"></td>
						</tr>
						<tr height="25px">
							<td align="center" rowspan="3">
								����<br/>���
							</td>
							<td align="center">
								����
							</td>
							<td align="center">
								${rs.xm }
							</td>
							<td align="center">
								�Ա�
							</td>
							<td align="center">
								${rs.xb }
							</td>
							<td align="center">
								����
							</td>
							<td align="center">
									${rs.mzmc }
							</td>
							<td align="center" rowspan="5">
								<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh }"
									border="0" align="absmiddle"  style="width:80px;height:100px" />
							</td>
						</tr>
						<tr  height="25px">
							<td align="center">
								��������
							</td>
							<td align="center">
								${rs.csrq }
							</td>
							<td align="center">
								��Դ����
							</td>
							<td align="center">
								${rs.sydq }
							</td>
							<td align="center">
								������ò
							</td>
							<td align="center">
								${rs.zzmmmc }
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
								��ͥ��ַ
							</td>
							<td align="center" colspan="3">
								${rs.jtdz }
							</td>
							<td align="center">
								����״��
							</td>
							<td align="center">
								${rs.jkzk }
							</td>
						</tr>
						<tr height="25px">
							<td align="center" rowspan="2">
								��ϵ<br/>��ʽ
							</td>
							<td align="center">
								��ϵ��ַ
							</td>
							<td align="center" colspan="3">
								${rs.lxdz }
							</td>
							<td align="center">
								��������
							</td>
							<td align="center">
								${rs.yzbh }
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
								��������
							</td>
							<td align="center" colspan="3">
								${rs.dzyx }
							</td>
							<td align="center">
								��ϵ�绰
							</td>
							<td align="center">
								${rs.lxdh }
							</td>
						</tr>
						<tr height="25px">
							<td align="center" rowspan="4">
								����<br/><br/>����
							</td>
							<td align="center">
								����<bean:message key="lable.xb" />
							</td>
							<td align="center" colspan="3">
								${rs.xymc }
							</td>
							<td align="center">
								��ѧ����
							</td>
							<td align="center" colspan="2">
								${rs.rxnd }
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
								����רҵ
							</td>
							<td align="center" colspan="3">
								${rs.zymc }
							</td>
							<td align="center">
								ѧ ��
							</td>
							<td align="center" colspan="2">
								${rs.xz }
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
								����רҵ
							</td>
							<td align="center" colspan="3">
								${rs.fxzy }
							</td>
							<td align="center">
								ѧ��
							</td>
							<td align="center" colspan="2">
								${rs.xl }
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
								�����ˮƽ
							</td>
							<td align="center" colspan="3">
								${rs.jsjsp }
							</td>
							<td align="center">
								����ˮƽ
							</td>
							<td align="center" colspan="2">
								${rs.wysp }
							</td>
						</tr>
						<tr height="25px">
							<td align="center" rowspan="4">
								��<br/>��<br/>��<br/>��
							</td>
							<td align="center">
								��ֹʱ��
							</td>
							<td align="center" colspan="4">
								������λ����������֯��
							</td>
							<td align="center" colspan="2">
								����ְ��
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
									${rs.shgzkssj1 }<br/>${rs.shgzjssj1 }
							</td>
							<td align="center" colspan="4">
									${rs.gzdw1 }
							</td>
							<td align="center" colspan="2">
									${rs.srzw1 }
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
									${rs.shgzkssj2 }<br/>${rs.shgzjssj2 }
							</td>
							<td align="center" colspan="4">
									${rs.gzdw2 }
							</td>
							<td align="center" colspan="2">
								${rs.srzw2 }
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
								${rs.shgzkssj3 }<br/>${rs.shgzjssj3 }
							</td>
							<td align="center" colspan="4">
								${rs.gzdw3 }
							</td>
							<td align="center" colspan="2">
								${rs.srzw3 }
							</td>
						</tr>
						<tr height="25px">
							<td align="center" rowspan="4">
								��<br/>��<br/>��<br/>��
							</td>
							<td align="center">
								ʱ��
							</td>
							<td align="center" colspan="4">
								�����ƺŻ�֤������
							</td>
							<td align="center" colspan="2">
								�������ţ���֤������
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
								${rs.hjsj1 }
							</td>
							<td align="center" colspan="4">
								${rs.rych1 }
							</td>
							<td align="center" colspan="2">
								${rs.bzjg1 }
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
								${rs.hjsj2 }
							</td>
							<td align="center" colspan="4">
								${rs.rych2 }
							</td>
							<td align="center" colspan="2">
								${rs.bzjg2 }
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
								${rs.hjsj3 }
							</td>
							<td align="center" colspan="4">
								${rs.rych3 }
							</td>
							<td align="center" colspan="2">
								${rs.bzjg3 }
							</td>
						</tr>
						<tr height="25px">
							<td align="center" rowspan="4">
								��<br/>��<br/>֤<br/>��
							</td>
							<td align="center">
								ʱ��
							</td>
							<td align="center" colspan="4">
								�����ƺŻ�֤������
							</td>
							<td align="center" colspan="2">
								�������ţ���֤������
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
								${rs.jnzssj1 }
							</td>
							<td align="center" colspan="4">
								${rs.jnzsmc1 }
							</td>
							<td align="center" colspan="2">
								${rs.jnzsbzjg1 }
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
								${rs.jnzssj2 }
							</td>
							<td align="center" colspan="4">
								${rs.jnzsmc2 }
							</td>
							<td align="center" colspan="2">
								${rs.jnzsbzjg2 }
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
								${rs.jnzssj3 }
							</td>
							<td align="center" colspan="4">
								${rs.jnzsmc3 }
							</td>
							<td align="center" colspan="2">
								${rs.jnzsbzjg3 }
							</td>
						</tr>
						<tr height="25px">
							<td align="center" rowspan="4">
								��<br/>��<br/>ʵ<br/>��
							</td>
							<td align="center">
								��ֹʱ��
							</td>
							<td align="center" colspan="4">
								������λ��ʵ������
							</td>
							<td align="center" colspan="2">
								ʵ�����Ч
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
								${rs.shsjkssj1 }<br/>${rs.shsjjssj1 }
							</td>
							<td align="center" colspan="4">
								${rs.shsjdw1 }
							</td>
							<td align="center" colspan="2">
								${rs.shsjcx1 }
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
								${rs.shsjkssj2 }<br/>${rs.shsjjssj2 }
							</td>
							<td align="center" colspan="4">
								${rs.shsjdw2 }
							</td>
							<td align="center" colspan="2">
								${rs.shsjcx2 }
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
								${rs.shsjkssj3 }<br/>${rs.shsjjssj3 }
							</td>
							<td align="center" colspan="4">
								${rs.shsjdw3 }
							</td>
							<td align="center" colspan="2">
								${rs.shsjcx3 }
							</td>
						</tr>
					</table>
					
					<br/><br/>
					<br/><br/>
					<br/><br/>
					<br/><br/>
					
					<table width="100%" class="tbstyle">
						<tr>
							<td align="center" width="10%">
								��<br/><br/>ҵ<br/><br/>��<br/><br/>��<br/><br/>��<br/><br/>��<br/><br/>��
							</td>
							<td colspan="7">
								<p style="height:420px">
									<u>���Ը񡢰��á��س�����ҵ�ʹ��¾����Ŷ�Э������֯���������ȣ�</u><br/>
									${rs.zwtj }
								</p>
								<div align="right" >
									����ǩ��:
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/>
								</div>
							</td>
						</tr>
						<tr>
							<td align="center">
								<br/>��<br/><br/>ҵ<br/><br/>��<br/><br/>��<br/><br/>��<br/><br/>ѧ<br/><br/>Ժ<br/><br/>��<br/><br/>��<br/><br/>��<br/><br/>��<br/>
							</td>
							<td colspan="5">
								<p style="height:220px">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyshyj }
								</p>
								<div align="right" >
									�����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
							<td colspan="2" valign="bottom" align="center" style="width:20%">
								<strong> ${xxmc }</strong><br/>
								��ҵָ����������<br/>
								�����£�<br/>
								��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center" class='noPrin'>
		<button class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
			ҳ������
		</button>
		<button class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
			��ӡԤ��
		</button>
		<button class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			ֱ�Ӵ�ӡ
		</button>
	</div>
</body>
</html>
