<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/head.ini"%>
<title>�ɶ�����ѧԺ������������ǼǱ�</title>
<style>
.font_style td{font-size:14px;font-family:����; }
</style>

</head>

<body lang=ZH-CN style='text-justify-trim:punctuation'>

<div align=center>
<html:form action="/xszz_sqsh" method="post" styleId="xszzSqshForm">
<table width="90%" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
				<br/><br/>
					<b>
					<span style='font-size:16.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						�ɶ�����ѧԺ������������ǼǱ�
					</span>
					<br/>
					</b>
					<br/><br/>
					<div align="center">
						<span style='font-size:12.0pt;font-family:������κ;mso-ascii-font-family:"Times New Roman";'>
						ϵ(��):${jbxx.xymc }&nbsp;&nbsp;&nbsp;
						רҵ:${jbxx.zymc }&nbsp;&nbsp;&nbsp;
						�༶:${jbxx.bjmc }&nbsp;&nbsp;&nbsp;
						ѧ��:${jbxx.xh }&nbsp;&nbsp;&nbsp;
						��Դ��:${jbxx.sydqmc }
						</span>
					</div>
					<br/>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="font_style" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" style='border-collapse:collapse;border:none;'>
						<tr>
							<td width="10%"></td>
							<td width="10%"></td>
							<td width="7%"></td>
							<td width="7%"></td>
							<td width="3%"></td>
							<td width="4%"></td>
							<td width="7%"></td>
							<td width="7%"></td>
							<td width="6%"></td>
							<td width="6%"></td>
							<td width="6%"></td>
							<td width="6%"></td>
							<td width="6%"></td>
							<td width="15%"></td>
						</tr>
						<tr height="28px">
					        <td align="center">����</td>
					        <td align="center">${jbxx.xm }</td>
					        <td align="center">�Ա�</td>
					        <td align="center">${jbxx.xb }</td>
					        <td align="center">��<br/>��</td>
					        <td align="center" colspan="2">${jbxx.jgmc }</td>
					        <td align="center">����</td>
					        <td align="center">${jbxx.mzmc }</td>
					        <td align="center">����<br/>����</td>
					        <td align="center" colspan="3">${jbxx.csrq }</td>
					        <td align="center" rowspan="4">
								<img style="width:100px;height:130px;" src="xsxx_xsgl.do?method=showPhoto&xh=${jbxx.xh}" border="0"/>
							</td>
					      </tr>
						<tr height="28px">
					        <td align="center" rowspan="2">��ѧǰѧϰ<br/>(����)��λ</td>
					        <td align="center" colspan="2" rowspan="2">${jbxx.rxqxxdw }</td>
					        <td align="center">���</td>
					        <td align="center" colspan="2">${jbxx.sg }&nbsp;CM</td>
					        <td align="center">Ӧ��</td>
					        <td align="center">${jbxx.yj }</td>
					        <td align="center">����</td>
					        <td align="center">${jbxx.cz }</td>
					        <td align="center" >�Ƿ�Ϊ�ͱ���ͥ</td>
					        <td align="center" colspan="2"></td>
					      </tr>
					      <tr height="28px">
					     	<td align="center">����</td>
					        <td align="center" colspan="2">${jbxx.tz }&nbsp;KG</td>
					        <td align="center">����</td>
					        <td align="center">${jbxx.wj }</td>
					        <td align="center">ũ��</td>
					        <td align="center">${jbxx.nc }</td>
					        <td align="center" >�Ƿ�Ϊ�ͱ���ͥ</td>
					        <td align="center" colspan="2"></td>
					      </tr>
					      <tr height="39px">
					        <td align="center">��ѧǰ<br/>�������ڵ�</td>
					        <td align="center" colspan="2">${jbxx.rxqhkszd }</td>
					        <td align="center">��ͥ��ϸ<br/>ͨѶ��ַ</td>
					        <td align="center" colspan="6">${jbxx.jtszd }</td>
					        <td align="center">����<br/>����</td>
					        <td align="center" colspan="2">${jbxx.yb }</td>
					      </tr>
					      <tr height="39px">
					        <td align="center" rowspan="2">����<br/>��ϵ��ʽ</td>
					        <td align="left" colspan="3">
					        	�̶��绰��${jbxx.jtdh }</td>
					        <td align="left" colspan="6">�ƶ��绰��${jbxx.sjhm }</td>
					        <td align="center" colspan="3" rowspan="2">�뵳(��)<br/>ʱ��</td>
					        <td align="center" rowspan="2">
					        	<logic:notEmpty name="jbxx" property="rdsj"><bean:write name="jbxx" property="rdsj"/><br/>�뵳</logic:notEmpty>
					        	<logic:empty name="jbxx" property="rdsj"><bean:write name="jbxx" property="rtsj"/><br/>����</logic:empty>
					        	</td>
					      </tr>
					      <tr height="39px">
					        <td align="left" colspan="3">QQ�ţ�${jbxx.qqhm }</td>
					        <td align="left" colspan="6">�������䣺${jbxx.dzyx }</td>
					      </tr>
					      <tr height="28px">
					        <td align="center">�س�</td>
					        <td align="center" colspan="5">${jbxx.tc }</td>
					        <td align="center" colspan="2">ѧУ���Һ���</td>
					        <td align="center" colspan="2">${jbxx.ssbh }</td>
					        <td align="center" colspan="2">���֤����</td>
					        <td align="center" colspan="2">${jbxx.sfzh }</td>
					      </tr>
					      <tr height="40px">
					        <td align="center" colspan="3">��ѧǰ��ȡ�õ��˶��ɼ����ȼ�<br/>�ƺ��Լ��������</td>
					        <td align="center" colspan="11">${jbxx.qk }</td>
					      </tr>
					      <tr height="28px">
					        <td align="center" rowspan="3">���˼���</td>
					        <td align="center" rowspan="3" colspan="4">${jbxx.grjl }</td>
					        <td align="center" rowspan="3">�߿�<br/>�ɼ�</td>
					        <td align="center" rowspan="2">�Ŀ�</td>
					        <td align="center">�ܷ�</td>
					        <td align="center">����</td>
					        <td align="center">����</td>
					        <td align="center">��ѧ</td>
					        <td align="center">����</td>
					        <td align="center" colspan="2">�ۺ�</td>
					      </tr>
					      <tr height="28px">
					        <td align="center">&nbsp;</td>
					        <td align="center">&nbsp;</td>
					        <td align="center">&nbsp;</td>
					        <td align="center">&nbsp;</td>
					        <td align="center">&nbsp;</td>
					        <td align="center" colspan="2">&nbsp;</td>
					      </tr>
					      <tr height="39px">
					        <td align="center">�忼</td>
					        <td align="center">�ܷ�</td>
					        <td align="center"></td>
					        <td align="center">ר<br/>��</td>
					        <td align="center" colspan="4"></td>
					      </tr>
					      <tr height="39px">
					        <td align="center" rowspan="5">��ͥ��Ҫ��<br/>Ա������<br/>ϵ</td>
					        <td align="center">����</td>
					        <td align="center">����</td>
					        <td align="center">�뱾��<br/>��ϵ</td>
					        <td align="center" colspan="2">������<br/>(Ԫ)</td>
					        <td align="center">����<br/>��ò</td>
					        <td align="center" colspan="5">���ںεغ͵�λ�κ�ְ��</td>
					        <td align="center" colspan="2">��ϵ�绰</td>
					      </tr>
					      <tr height="28px">
					        <td align="center">${jbxx.jtcy1_xm }</td>
					        <td align="center">${jbxx.jtcy1_nlxx }</td>
					        <td align="center">${jbxx.jtcy1_gx}</td>
					        <td align="center" colspan="2">&nbsp;</td>
					        <td align="center">${jbxx.jtcy1_zzmmmc}</td>
					        <td align="center" colspan="5">${jbxx.jtcy1_gzdz}${jbxx.jtcy1_zw}</td>
					        <td align="center" colspan="2">${jbxx.jtcy1_lxdh1}</td>
					      </tr>
					      <tr height="28px">
					        <td align="center">${jbxx.jtcy2_xm }</td>
					        <td align="center">${jbxx.jtcy2_nlxx }</td>
					        <td align="center">${jbxx.jtcy2_gx}</td>
					        <td align="center" colspan="2">&nbsp;</td>
					        <td align="center">${jbxx.jtcy2_zzmmmc}</td>
					        <td align="center" colspan="5">${jbxx.jtcy2_gzdz}${jbxx.jtcy2_zw}</td>
					        <td align="center" colspan="2">${jbxx.jtcy2_lxdh1}</td>
					      </tr>
					      <tr height="28px">
					        <td align="center">${jbxx.jtcy3_xm }</td>
					        <td align="center">${jbxx.jtcy3_nlxx }</td>
					        <td align="center">${jbxx.jtcy3_gx}</td>
					        <td align="center" colspan="2">&nbsp;</td>
					        <td align="center">${jbxx.jtcy3_zzmmmc}</td>
					        <td align="center" colspan="5">${jbxx.jtcy3_gzdz}${jbxx.jtcy3_zw}</td>
					        <td align="center" colspan="2">${jbxx.jtcy3_lxdh1}</td>
					      </tr>
					      <tr height="28px">
					        <td align="center">&nbsp;</td>
					        <td align="center">&nbsp;</td>
					        <td align="center">&nbsp;</td>
					        <td align="center" colspan="2">&nbsp;</td>
					        <td align="center">&nbsp;</td>
					        <td align="center" colspan="5">&nbsp;</td>
					        <td align="center" colspan="2">&nbsp;</td>
					      </tr>
					</table>
				</td>
			</tr>
			<tr>
				<td align="left">
				<br/>
					<span style='font-size:12.0pt;font-family:������κ;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
					ע��1��������ѧ�������ú�ɫ�ֱ���ʵ��д���ҿ���У԰��ѧ������ҳ�����ء�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  				������ڣ�&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��<br/>
	  				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2������һʽ���ݣ�ϵѧ������칫�ҡ�ѧУѧ��������һ�ݡ�
					</span>
				</td>
			</tr>
		</table>
	</html:form>
	</div>
</body>
</html>