<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
	</head>
	<body>
		<div class="yl_win">
			<h3></h3>
			<div class="yl_con">
				<div class="module">
					<h5>
						������Ϣ
					</h5>
					<table width="100%" border="0" class="tab_02">
						<tr>
							<td>
								���֤�ţ�
								<logic:equal value="��" property="sfzhbm" name="rs">
									����
								</logic:equal>
								<logic:notEqual value="��" property="sfzhbm" name="rs">
									${rs.sfzh }
								</logic:notEqual>
							</td>
							<td>
								��&nbsp;&nbsp;&nbsp;&nbsp;����${rs.xm }
							</td>
							<td rowspan="4">
								<div class="photo">
									<img
										src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
										style="width:95px;height:130px" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								��&nbsp;&nbsp;&nbsp;&nbsp;��${rs.xb }
							</td>
							<td>
								��&nbsp;&nbsp;&nbsp;&nbsp;�壺${rs.mzmc }
							</td>
						</tr>
						<tr>
							<td>
								�������£�${rs.csrq }
							</td>
							<td>
								��Դ������${rs.sydq }
							</td>
						</tr>
						<tr>
							<td>
								������ò��${rs.zzmm }
							</td>
							<td>
								����״����${rs.jkzk }
							</td>
						</tr>
					</table>
				</div>
				<div class="module">
					<h5>
						��������
					</h5>
					<table width="100%" border="0" class="tab_02">
						<tr>
							<td>
								��ҵ<bean:message key="lable.xb" />��${rs.xymc }
							</td>
							<td>
								��ѧ���£�${rs.rxnd }
							</td>
						</tr>
						<tr>
							<td>
								����רҵ��${rs.zymc }
							</td>
							<td>
								ѧ&nbsp;&nbsp;&nbsp;&nbsp;�ƣ�${rs.xz }
							</td>
						</tr>
						<tr>
							<td>
								����רҵ��${rs.fxzy }
							</td>
							<td>
								ѧ&nbsp;&nbsp;&nbsp;&nbsp;����${rs.xl }
							</td>
						</tr>
						<tr>
							<td>
								�����ˮƽ��${rs.jsjsp }
							</td>
							<td>
								����ˮƽ��${rs.wysp }
							</td>
						</tr>
					</table>
				</div>
				<div class="module">
					<h5>
						��ϵ��ʽ
					</h5>
					<table width="100%" border="0" class="tab_02">
						<tr>
							<td>
								��ϵ��ַ��${rs.lxdz }
							</td>
							<td>
								�������룺${rs.yzbh }
							</td>
						</tr>
						<tr>
							<td>
								�������䣺${rs.dzyx }
							</td>
							<td>
								��ϵ�绰��${rs.lxdh }
							</td>
						</tr>
						<tr>
							<td></td>
							<td></td>
						</tr>
					</table>
				</div>
				<div class="module">
					<h5>
						��ְ����
					</h5>
					<table width="100%" border="0" class="tab_02">
						<tr>
							<td width="70px">
								�������ʣ�
							</td>
							<td>
								${rs.gzxz }
							</td>
						</tr>
						<tr>
							<td>
								Ŀ���λ��
							</td>
							<td>
								${rs.mbgw }
							</td>
						</tr>
						<tr>
							<td>
								Ŀ��ص㣺
							</td>
							<td>
								${rs.gzdd }
							</td>
						</tr>
						<tr>
							<td>
								����нˮ��
							</td>
							<td>
								${rs.qwxs }
							</td>
						</tr>
						<tr>
							<td valign="top">
								Ŀ��ְ�ܣ�
							</td>
							<td>
								${rs.mbzn }
							</td>
						</tr>
					</table>
				</div>
				<div class="module">
					<h5>
						��Ṥ�����
					</h5>
					<table width="100%" border="0" class="tab_02">
						<tr>
							<td width="20%" align="center">
								��ֹʱ��
							</td>
							<td width="30%" align="center">
								������λ����������֯��
							</td>
							<td width="30%" align="center">
								����ְ��
							</td>
						</tr>
						<tr>
							<td align="center">
								${rs.shgzkssj1 }
								<logic:present name="rs" property="shgzjssj1"> - </logic:present>
								${rs.shgzjssj1 }
							</td>
							<td align="center">
								${rs.gzdw1 }
							</td>
							<td align="center">
								${rs.srzw1 }
							</td>
						</tr>
						<tr>
							<td align="center">
								${rs.shgzkssj2 }
								<logic:present name="rs" property="shgzjssj2"> - </logic:present>
								${rs.shgzjssj2 }
							</td>
							<td align="center">
								${rs.gzdw2 }
							</td>
							<td align="center">
								${rs.srzw2 }
							</td>
						</tr>
						<tr>
							<td align="center">
								${rs.shgzkssj3 }
								<logic:present name="rs" property="shgzjssj3"> - </logic:present>
								${rs.shgzjssj3 }
							</td>
							<td align="center">
								${rs.gzdw3 }
							</td>
							<td align="center">
								${rs.srzw3 }
							</td>
						</tr>
					</table>
				</div>
				<div class="module">
					<h5>
						���ʵ�����
					</h5>
					<table width="100%" border="0" class="tab_02">
						<tr>
							<td width="20%" align="center">
								��ֹʱ��
							</td>
							<td width="30%" align="center">
								������λ��ʵ������
							</td>
							<td width="30%" align="center">
								ʵ�����Ч
							</td>
						</tr>
						<tr>
							<td align="center">
								${rs.shsjkssj1 }
								<logic:present name="rs" property="shsjjssj1"> - </logic:present>
								${rs.shsjjssj1 }
							</td>
							<td align="center">
								${rs.shsjdw1 }
							</td>
							<td align="center">
								${rs.shsjcx1 }
							</td>
						</tr>
						<tr>
							<td align="center">
								${rs.shsjkssj2 }
								<logic:present name="rs" property="shsjjssj2"> - </logic:present>
								${rs.shsjjssj2 }
							</td>
							<td align="center">
								${rs.shsjdw2 }
							</td>
							<td align="center">
								${rs.shsjcx2 }
							</td>
						</tr>
						<tr>
							<td align="center">
								${rs.shsjkssj3 }
								<logic:present name="rs" property="shsjjssj3"> - </logic:present>
								${rs.shsjjssj3 }
							</td>
							<td align="center">
								${rs.shsjdw3 }
							</td>
							<td align="center">
								${rs.shsjcx3 }
							</td>
						</tr>
					</table>
				</div>

				<div class="module">
					<h5>
						�����
					</h5>
					<table width="100%" border="0" class="tab_02">
						<tr>
							<td width="20%" align="center">
								ʱ��
							</td>
							<td width="30%" align="center">
								�����ƺ�
							</td>
							<td width="30%" align="center">
								��������
							</td>
						</tr>
						<tr>
							<td align="center">
								${rs.hjsj1 }
							</td>
							<td align="center">
								${rs.rych1 }
							</td>
							<td align="center">
								${rs.bzjg1 }
							</td>
						</tr>
						<tr>
							<td align="center">
								${rs.hjsj2 }
							</td>
							<td align="center">
								${rs.rych2 }
							</td>
							<td align="center">
								${rs.bzjg2 }
							</td>
						</tr>
						<tr>
							<td align="center">
								${rs.hjsj3 }
							</td>
							<td align="center">
								${rs.rych3 }
							</td>
							<td align="center">
								${rs.bzjg3 }
							</td>
						</tr>
					</table>
				</div>

				<div class="module">
					<h5>
						����֤��
					</h5>
					<table width="100%" border="0" class="tab_02">
						<tr>
							<td width="20%" align="center">
								ʱ��
							</td>
							<td width="30%" align="center">
								֤������
							</td>
							<td width="30%" align="center">
								��֤����
							</td>
						</tr>
						<tr>
							<td align="center">
								${rs.jnzssj1 }
							</td>
							<td align="center">
								${rs.jnzsmc1 }
							</td>
							<td align="center">
								${rs.jnzsmc1 }
							</td>
						</tr>
						<tr>
							<td align="center">
								${rs.jnzssj2 }
							</td>
							<td align="center">
								${rs.jnzsmc2 }
							</td>
							<td align="center">
								${rs.jnzsmc2 }
							</td>
						</tr>
						<tr>
							<td align="center">
								${rs.jnzssj3 }
							</td>
							<td align="center">
								${rs.jnzsmc3 }
							</td>
							<td align="center">
								${rs.jnzsmc3 }
							</td>
						</tr>
					</table>
				</div>
				<div class="module">
					<h5>
						�����Ƽ�
					</h5>
					<table width="100%" border="0" class="tab_02">
						<tr>
							<td>
								${rs.zwtj }
							</td>
						</tr>
					</table>
				</div>

				<div class="btn">
					<button onclick="window.close();return false;">
						�� ��
					</button>
				</div>
			</div>
		</div>
	</body>
</html>
