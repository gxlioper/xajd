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
		<table><tr><td><span style="font-size:24px;font-family:����">����ʡ�Ͼ���ʦѧԺ</span></td><td rowspan="2" >
		<span style="font-size:26px;font-family:����">ѧ����</span></td></tr>
                          <tr><td><span style="font-size:24px;font-family:����">�Ͼ��и߼�����ѧУ</span> </td></tr> </table>

	</center>
	<br />
	<br />
	<table width="100%" style="font-size:12px;">
	<tr>
	<td width="80%">
	<u>${rs.xymc }</u>ϵ <u>${rs.nj }</u>�� <u>${rs.zymc }</u> רҵ<u>${rs.bjmc }</u>  �� 
	</td>
	<td width="20%">
	ѧ�ţ� <u>  ${rs.xh }</u>
	</td>
	</tr>
	</table>
	<table class="printtab" width="100%" style="font-size:13px;">
		<tr style="height:35px">
			<td align="center" width="15%" colspan="2">
				����
			</td>
			<td  align="center" width="10%">
			${rs.xm }
			</td>
			<td  align="center" width="10%">
				�� ��
			</td>
			<td align="center" width="12%">
			${rs.xb }
			</td>
			<td  align="center" width="12%">
				����
			</td>
			<td  align="center" width="10%">
			${rs.mzmc }
			</td>
			<td  align="center" width="10%">
				����
			</td>
			<td  align="center" width="15%">
			${rs.sydmc }
			</td>
			<td rowspan="6" width="16%">
			<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}" width="96" height="128"/>
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center" colspan="2">
				���֤����
			</td>
			<td colspan="4" align="center">
			${rs.sfzh }
			</td>
			<td align="center" colspan="2">
				������ò
			</td>
			<td align="center" >
			${rs.zzmmmc}
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center" colspan="2">
				��ͥסַ
			</td>
			<td colspan="4" align="center">
			${jtcy.jtszd }
			</td>
			<td colspan="2" align="center">
				�ʱ�
			</td>
			<td align="center" colspan="1">
			${jtcy.jtyb }
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center" colspan="2">
				��ϵ�绰
			</td>
			<td colspan="2" align="center">
			${rs.save_lxdh }
			</td>
			<td  align="center" colspan="2">
				��ѧǰ��ҵѧУ
			</td>
			<td align="center" colspan="3">
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center" colspan="2">
				ѧ��
			</td>
			<td colspan="2" align="center">
			${rs.xz }
			</td>
			<td  align="center" colspan="2">
				��(��)ҵ֤���
			</td>
			<td colspan="3" align="center">
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center" colspan="3">
				ȡ�ú��ּ����ȼ���֤��ȼ�
			</td>
			<td colspan="3"  align="center">
			</td>
			<td align="center" colspan="2" >
				�ȼ�֤����
			</td>
			<td  align="center">
			</td>
		</tr>
		<tr style="height:35px">
			<td rowspan="4" align="center">
				��ͥ
				<br />
				��Ҫ
				<br />
				��Ա
			</td>
			<td align="center" width="9%">
				��ν
			</td>
			<td colspan="1" align="center" width="10%">
				����
			</td>
			<td colspan="4" align="center">
				������λ
			</td>
			<td colspan="4" align="center">
				��ϵ�绰
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center">
			${jtcy.jtcy1_gx }
			</td>
			<td colspan="1" align="center">
			${jtcy.jtcy1_xm }
			</td>
			<td colspan="4" align="center">
			</td>
			<td colspan="3" align="center">
			${jtcy.jtcy1_lxdh1 }
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center">
			${jtcy.jtcy2_gx }
			</td>
			<td  colspan="1" align="center">
			${jtcy.jtcy2_xm }
			</td>
			<td colspan="4" align="center">
			</td>
			<td colspan="3" align="center">
			${jtcy.jtcy2_lxdh1 }
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center">
			${jtcy.jtcy3_gx }
			</td>
			<td colspan="1" align="center">
			${jtcy.jtcy3_xm }
			</td>
			<td colspan="4" align="center">
			</td>
			<td colspan="3" align="center">
			${jtcy.jtcy3_lxdh1 }
			</td>
		</tr>
			<tr style="height:35px">
			<td rowspan="3" align="center">
				ѧ��
				<br />
				���
				<br />
				����
			</td>
			<td colspan="6" align="center">
				�� �� �� ԭ ��
			</td>
			<td colspan="5" align="center">
				��׼����
			</td>
		</tr>
		<tr style="height:35px">
			<td colspan="6" align="center">
			</td>
			<td colspan="5" align="center">
			</td>
		</tr>
		<tr style="height:35px">
			<td colspan="6" align="center">
			</td>
			<td colspan="5" align="center">
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
			</td>
			<td colspan="6" align="center">
				�� �� �� �� �� �� �� �� �� ��
			</td>
			<td colspan="5" align="center">
				��׼����
			</td>
		</tr>
		<tr style="height:35px">
			<td colspan="6" align="center">
			</td>
			<td colspan="5" align="center">
			</td>
		</tr>
		<tr style="height:35px">
			<td colspan="6" align="center">
			</td>
			<td colspan="5" align="center">
			</td>
		</tr>
		<tr style="height:35px">
			<td colspan="6" align="center">
			</td>
			<td colspan="5" align="center">
			</td>
		</tr>
		<tr style="height:35px">
			<td colspan="6" align="center">
			</td>
			<td colspan="5" align="center">
			</td>
		</tr>
	</table>
	<table height='300px'><tr><td></td></tr></table>
	<table class="printtab" width="100%" style="font-size:13px;">
	<tr>
	<td rowspan="6"  width="5%" align="center" >
	ѧ
	<br/>
	��
	<br/>
	��
	<br/>
	��
	</td>
	</tr>
	<tr>
	<td width="5%">
	��<br/>
	һ<br/>
	ѧ<br/>
	��<br/>
	</td>
	<td> <br/><br/><br/>
	&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
     &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
   &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
          ���У�&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;������: &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
      &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;       
          ��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</td></tr>
	<tr>
	<td width="5%">
	��<br/>
	��<br/>
	ѧ<br/>
	��<br/>
	</td>
	<td> <br/><br/><br/>
	&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
     &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
   &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
          ���У�&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;������: &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
      &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;       
          ��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</td></tr>
	<tr>
	<td width="5%">
	��<br/>
	��<br/>
	ѧ<br/>
	��<br/>
	</td>
	<td> <br/><br/><br/>
	&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
     &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
   &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
          ���У�&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;������: &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
      &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;       
          ��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</td></tr>
	<tr>
	<td width="5%">
	��<br/>
	��<br/>
	ѧ<br/>
	��<br/>
	</td>
	<td> <br/><br/><br/>
	&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
     &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
   &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
          ���У�&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;������: &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
      &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;       
          ��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</td></tr>
	<tr>
	<td width="5%">
	��<br/>
	��<br/>
	ѧ<br/>
	��<br/>
	</td>
	<td> <br/><br/><br/>
	&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
     &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
   &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
          ���У�&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;������: &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
      &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;       
          ��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</td></tr>
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
