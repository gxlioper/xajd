<%@ page language="java" contentType="text/html; charset=GBK"%>
<!-- ͷ�ļ� -->
<html>
	<body>
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
				.noPrin{display:none;}
			</style>
		<!-- end -->
	</head>
	<center>
		<span style="font-size:22px;font-family:����">ѧ �� �� �� ��</span>
	</center>
	<br />
	<table class="printtab" width="100%" style="font-size:14px">
		<tr style="height:35px">
			<td align="center" width="15%">
				����
			</td>
			<td colspan="2" align="center" width="14%">
				${rs.xm }
			</td>
			<td colspan="2" align="center" width="14%">
				������
			</td>
			<td align="center" width="14%">
				${rs.cym }
			</td>
			<td colspan="2" align="center" width="14%">
				�� ��
			</td>
			<td colspan="2" align="center" width="12%">
				${rs.xb }
			</td>
			<td rowspan="5" width="18%">
				<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}" width="96" height="128"/>
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center">
				ϵ ��
			</td>
			<td colspan="2" align="center">
				${rs.xymc }
			</td>
			<td colspan="2" align="center">
				�� ��
			</td>
			<td align="center">
				${rs.bjmc }	
			</td>
			<td colspan="2" align="center">
				�� ��
			</td>
			<td colspan="2" align="center">
				${rs.mzmc }
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center">
				ѧ ��
			</td>
			<td colspan="2" align="center">
				${rs.xh }
			</td>
			<td colspan="2" align="center">
				��������
			</td>
			<td align="center">
				${rs.csrq }
			</td>
			<td colspan="2" align="center">
				�� ��
			</td>
			<td colspan="2" align="center">
				${rs.sydmc }
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center">
				����״��
			</td>
			<td colspan="2" align="center">
				${rs.jkzk }
			</td>
			<td colspan="2" align="center">
				��ҵѧУ
			</td>
			<td align="center">
				${rs.xxmc }
			</td>
			<td colspan="2" align="center">
				��������
			</td>
			<td colspan="2" align="center">
				${rs.hkszd }
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center">
				���֤����
			</td>
			<td colspan="5" align="center">
				${rs.sfzh }
			</td>
			<td colspan="2" align="center">
				�Ƿ���Ա
			</td>
			<td colspan="2" align="center">
				<logic:present name="rs" property="zzmmmc">
					<logic:match value="��Ա" name="rs" property="zzmmmc">
						��
					</logic:match>
					<logic:notMatch value="��Ա" name="rs" property="zzmmmc">
						��
					</logic:notMatch>
				</logic:present>
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center" rowspan="2">
				��ͥ��
				<br />
				��סַ
			</td>
			<td colspan="5" rowspan="2" align="center">
				${jtcy.jtszd }
			</td>
			<td colspan="2" align="center">
				�� ��
			</td>
			<td colspan="3" align="center">
				${jtcy.jtyb }
			</td>
		</tr>
		<tr style="height:35px">
			<td colspan="2" align="center">
				�� ��
			</td>
			<td colspan="3" align="center">
				${rs.lxdh }
			</td>
		</tr>

		<tr style="height:35px">
			<td rowspan="5" align="center">
				ѧ
				<br />
				��
				<br />
				��
				<br />
				��
				<br />
				��
			</td>
			<td colspan="4" align="center">
				����������������
			</td>
			<td colspan="2" align="center">
				�� �� �� ѧ
			</td>
			<td colspan="4" align="center">
				֤����
			</td>
		</tr>
		<tr style="height:35px">
			<td colspan="4" align="center">
				&nbsp;
			</td>
			<td colspan="2" align="center">
				&nbsp;
			</td>
			<td colspan="4" align="center">
				&nbsp;
			</td>
		</tr>
		<tr style="height:35px">
			<td colspan="4" align="center">
				&nbsp;
			</td>
			<td colspan="2" align="center">
				&nbsp;
			</td>
			<td colspan="4" align="center">
				&nbsp;
			</td>
		</tr>
		<tr style="height:35px">
			<td colspan="4" align="center">
				&nbsp;
			</td>
			<td colspan="2" align="center">
				&nbsp;
			</td>
			<td colspan="4" align="center">
				&nbsp;
			</td>
		</tr>
		<tr style="height:35px">
			<td colspan="4" align="center">
				&nbsp;
			</td>
			<td colspan="2" align="center">
				&nbsp;
			</td>
			<td colspan="4" align="center">
				&nbsp;
			</td>
		</tr>
		<tr style="height:35px">
			<td rowspan="4" align="center">
				��
				<br />
				ͥ
				<br />
				��
				<br />
				Ա
				<br />
				��
				<br />
				��
			</td>
			<td align="center" width="9%">
				��ν
			</td>
			<td colspan="2" align="center" width="10%">
				����
			</td>
			<td align="center" width="9%">
				����
			</td>
			<td colspan="2" align="center">
				����ѧϰ��λ
			</td>
			<td colspan="2" align="center">
				������
			</td>
			<td colspan="2" align="center">
				��ϵ�绰
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center">
				${jtcy.jtcy1_gx }
			</td>
			<td colspan="2" align="center">
				${jtcy.jtcy1_xm }
			</td>
			<td align="center">
			</td>
			<td colspan="2" align="center">
			</td>
			<td colspan="2" align="center">
			</td>
			<td colspan="2" align="center">
				${jtcy.jtcy1_lxdh1 }
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center">
				${jtcy.jtcy2_gx }
			</td>
			<td colspan="2" align="center">
				${jtcy.jtcy2_xm }
			</td>
			<td align="center">
			</td>
			<td colspan="2" align="center">
			</td>
			<td colspan="2" align="center">
			</td>
			<td colspan="2" align="center">
				${jtcy.jtcy2_lxdh1 }
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center">
				${jtcy.jtcy3_gx }
			</td>
			<td colspan="2" align="center">
				${jtcy.jtcy3_xm }
			</td>
			<td align="center">
			</td>
			<td colspan="2" align="center">
			</td>
			<td colspan="2" align="center">
			</td>
			<td colspan="2" align="center">
				${jtcy.jtcy3_lxdh1 }
			</td>
		</tr>
		<tr style="height:35px">
			<td rowspan="5" align="center">
				��
				<br />
				��
				<br />
				��
				<br />
				��
				<br />
				��
				<br />
				��
			</td>
			<td colspan="4" align="center">
				����������������
			</td>
			<td colspan="2" align="center">
				�� �� �� ��
			</td>
			<td colspan="4" align="center">
				֤����
			</td>
		</tr>
		<tr style="height:35px">
			<td colspan="4" align="center">
			</td>
			<td colspan="2" align="center">
			</td>
			<td colspan="4" align="center">
			</td>
		</tr>
		<tr style="height:35px">
			<td colspan="4" align="center">
			</td>
			<td colspan="2" align="center">
			</td>
			<td colspan="4" align="center">
			</td>
		</tr>
		<tr style="height:35px">
			<td colspan="4" align="center">
			</td>
			<td colspan="2" align="center">
			</td>
			<td colspan="4" align="center">
			</td>
		</tr>
		<tr style="height:35px">
			<td colspan="4" align="center">
			</td>
			<td colspan="2" align="center">
			</td>
			<td colspan="4" align="center">
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center">
				��
				<br />
				��
			</td>
			<td colspan="10" align="center">
				${rs.tc }
			</td>
		</tr>
	</table>
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
