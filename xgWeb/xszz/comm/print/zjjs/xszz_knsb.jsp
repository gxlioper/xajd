<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!-- ͷ�ļ� -->
<html:html>
<head>
	<!-- ��ӡ�ؼ�begin -->
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media='print'>
			.noPrin{display:none;}
		</style>
	<!-- end -->
	<style>
		.radic {
		position:relative;
		}
		.radic em {
		position:absolute;
		top:3px; 
		left:-4px;
		font-family:Arial;
		font-size:22px;
		}
		</style>
</head>
<body>
	<br/><br /><br />
	<p align=center>
		<b><span
			style='font-size:18.0pt;letter-spacing:1.0pt'>�ߵ�ѧУ��ͥ��������ѧ���϶������</span>
		</b>
	</p>
	<br />
	<br />
	<p style='font-size:16px'><B>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ѧУ��<U>&nbsp;&nbsp;${xxmc}&nbsp;&nbsp;</U></B></p>
	<table class="printtab"
		style="font-family:����_GB2312;font-size:15px;width:100%" >
		<!-- ѧ�����˻������ -->
		<!-- ��һ�� -->
		<tr style="height:45px">
			<td width="7%" rowspan="4" align="center">
					<B>
					ѧ<br>
					��<br>
					��<br>
					��<br>
					��<br>
					��<br>
					��<br>
					��
					</B>
			</td>
			<td colspan="2" width="14%"  align="center" style="vertical-align: center">
				�� ��
			</td>
			<td colspan="2" width="14%"  align="center" style="vertical-align: center">
				${rs.xm }
			</td>
			<td colspan="2" width="11%"  align="center" style="vertical-align: center">
				�Ա�
			</td>
			<td  width="8%"  align="center" style="vertical-align: center">
				${rs.xb }
			</td>
			<td colspan="2" width="16%"  align="center" style="vertical-align: center">
				����<br/>����
			</td>
			<td colspan="2" width="12%"  align="center" style="vertical-align: center">
				${rs.csrq }
			</td>
			<td width="7%"  align="center" style="vertical-align: center">
				����
			</td>
			<td width="7%"  align="center" style="vertical-align: center">
				${rs.mzmc }
			</td>
		</tr>
		<!-- �ڶ��� -->
		<tr style="height:45px">
			<td width="" colspan="2"  align="center" style="vertical-align: center">
				���֤����
			</td>
			<td width="" colspan="4"  align="center" style="vertical-align: center">
				${rs.sfzh }
			</td>
			<td  align="center" style="vertical-align: center">
				����<br/>��ò
			</td>
			<td colspan="2"  align="center" style="vertical-align: center">
				${rs.zzmmmc }
			</td>
			<td colspan="2" align="center" style="vertical-align: center">
				��ͥ�˾�<br/>������
			</td>
			<td colspan="2" align="center" style="vertical-align: center">
				${rs.jtrjsr }Ԫ
			</td>
		</tr>
		<!-- ������ -->
		<tr style="height:45px">
			<td colspan="2" align="center" style="vertical-align: center">
				<bean:message key="lable.xb" />
			</td>
			<td colspan="3" align="center" style="vertical-align: center">
				${xxmc }
			</td>

			<td width="" align="center" style="vertical-align: center">
				ϵ
			</td>
			<td colspan="3" align="center" style="vertical-align: center">
				${rs.xymc }
			</td>

			<td width="9%" align="center" style="vertical-align: center">
				רҵ
			</td>
			<td width="21%" colspan="3" align="center" style="vertical-align: center">
				${rs.zymc }
			</td>
		</tr>
		<!-- ������ -->
		<tr>
			<td width="" colspan="2" align="center" style="vertical-align: center">
				�꼶
			</td>
			<td width=""  align="center" style="vertical-align: center">
				${rs.nj}
			</td>
			<td width="" align="center" style="vertical-align: center">
				��
			</td>
			<td width="" colspan="2" align="center" style="vertical-align: center">
				${rs.bjmc}
			</td>
			<td width="18%" colspan="2" align="center" style="vertical-align: center">
				��У��<br/>ϵ�绰
			</td>
			<td width="" colspan="5" align="center" style="vertical-align: center">
			</td>
		</tr>
		<!-- ѧ�����������϶����� -->
		<tr style="height:150px">
			<td width="5%" align="center" style=";vertical-align: center">
				<B>
				ѧ<br>
				��<br>
				��<br>
				��<br>
				��<br>
				��<br>
				��<br>
				��<br>
				��<br>
				��
				</B>
			</td>
			<td colspan="13" align="left">
				<p style="height: 120px">
					<br>
					${rs.rdly }
				</p>
				<div align="right">
					ѧ��ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<u>&nbsp;&nbsp;&nbsp;</u> ��
					<u>&nbsp;&nbsp;&nbsp;</u> ��
					<u>&nbsp;&nbsp;&nbsp;</u> ��
				</div>
				<div align="left">
					<font style="font-size:15px">
					(ע��������ϸ���˵����)
					</font>
				</div>
			</td>
		</tr>
		<!-- ѧ�����������϶����� -->
		<tr height="150px">
			<td width="5%" rowspan="${fjNum }">
				<div align="center">
					<B>
					��<br>
					��<br>
					��<br>
					��
					</B>
				</div>
			</td>
			<td width="" rowspan="${fjNum }">
				<div align="center">
					��<br>
					��<br>
					��<br>
					��
				</div>
			</td>
			<td width="10%" height="50px" colspan="4">
				<div align="center">
					<logic:iterate name="xmfjqkList" id="fj" indexId="num">
						<logic:equal name="num" value="0">
								${fj.fjmc }��
							</logic:equal>
					</logic:iterate>
				</div>
			</td>
			<td width="10%" rowspan="${fjNum }">
				<div align="center">
					��
					<br>
					��
					<br>
					��
					<br>
					��
				</div>
			</td>
			<td width="" colspan="7" rowspan="${fjNum }">
				<p  style="height: 120px"></p>
				<div align="right">
					����С���鳤ǩ�֣�&nbsp;&nbsp;
					<u>&nbsp;&nbsp;&nbsp;</u> ��
					<u>&nbsp;&nbsp;&nbsp;</u> ��
					<u>&nbsp;&nbsp;&nbsp;</u> ��
				</div>
			</td>
		</tr>
		<logic:iterate name="xmfjqkList" id="fj" indexId="num">
			<logic:notEqual name="num" value="0">
				<tr>
					<td width="10%" colspan="4"  height="50px" >
						<div align="center">
							${fj.fjmc }
							<logic:notEqual name="fj" property="fjmc" value="${rs.xmzzjb }">
									��
								</logic:notEqual>
							<logic:equal name="fj" property="fjmc" value="${rs.xmzzjb }">
								<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							</logic:equal>
						</div>
					</td>
				</tr>
			</logic:notEqual>
		</logic:iterate>
		<!-- �϶����� -->
		<tr height="150px" >
			<td width="5%" align="center" valign="middle">
					<B>
					��<br>
					��<br>
					��<br>
					��
					</B>
			</td>
			<td width="11%" align="center" valign="middle" >
					Ժ(ϵ)
					<br>
					���
			</td>
			<td width="" colspan="5">
				<p style="height: 140px;" align="left">
					������С���Ƽ�����Ժ<br/>
					(ϵ)������˺�
					<br/>
					�� ͬ������С����<br/>����
					<br/>
					�� ��ͬ������С��<br/>
					���������Ϊ<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
				</p>
				<div align="left">
					�������鳤ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;
				</div><br/>
				<div align="left">
					<u>&nbsp;&nbsp;&nbsp;&nbsp;</u> ��
					<u>&nbsp;&nbsp;&nbsp;</u> ��
					<u>&nbsp;&nbsp;&nbsp;</u> ��
				</div>
				<br/><br/>
			</td>
			<td width="8%">
				<div align="center">
					ѧ<br>
					У<br>
					ѧ<br>
					��<br>
					��<br>
					��<br>
					��<br>
					��<br>
					��<br>
					��<br>
					��<br>
					��
				</div>
			</td>
			<td width="" colspan="6">
				<p style="height: 120px;" align="left">
					��ѧ������Ժ(ϵ)���룬�����������ʵ��<br>
					�� ͬ�⹤���������С�������
					<br>
					�� ��ͬ�⹤���������С�����������Ϊ<br/>
					<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
				</p>
				<br/>
				<div align="left">
					������ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
				<br/>
				<div align="left">
					<u>&nbsp;&nbsp;&nbsp;&nbsp;</u> ��
					<u>&nbsp;&nbsp;&nbsp;</u> ��
					<u>&nbsp;&nbsp;&nbsp;</u> ��
				</div><br/>
				<div align="left">
					���Ӹǲ��Ź��£�
				</div>
				<br/>
				<br/>
			</td>
		</tr>
	</table>
	<!-- ƶ�����Ǽ� -->
	<br /><br />
	<p align=center>
		<b><span
			style='font-size:14.0pt;letter-spacing:1.0pt'>�㽭����ְҵ����ѧԺƶ�����ǼǱ�</span>
		</b>
	</p>
	<br />
	<table class="printtab"
		style="font-family:����_GB2312;font-size:15px;width:100%" >
		<!-- ѧ�����˻������ -->
		<!-- ��һ�� -->
		<tr  style="height: 22px"> 
        <td width="5%" rowspan=6 align="center" valign="middle">
        	��<br/>
        	��<br/>
        	��<br/>
        	��</td> 
        <td width="20%" align="center" valign="middle">����</td> 
        <td width="15%" align="center" valign="middle">${rs.xm } </td> 
        <td width="15%" align="center" valign="middle">�Ա�</td> 
        <td width="10%" align="center" valign="middle">${rs.xb }</td> 
        <td width="15%" align="center" valign="middle">��������</td> 
        <td width="15%" align="center" valign="middle">${rs.csrq }</td> 
        <td width="15%" rowspan=4 ><img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}" width="96px" height="128px"/></td> 
      </tr> 
      <tr style="height: 22px"> 
        <td width="20%" align="center" valign="middle">����</td> 
        <td width="15%" align="center" valign="middle">${rs.mzmc }</td> 
        <td width="10%"  align="center" valign="middle">������ò</td> 
        <td width="10%"  align="center" valign="middle"> ${rs.zzmmmc }</td> 
        <td width="15%" align="center" valign="middle">��ѧʱ��</td> 
        <td width="15%"  align="center" valign="middle">${rs.rxrq }</td> 
      </tr> 
      <tr style="height: 22px"> 
        <td width="20%" align="center" valign="middle"> ���֤����</td> 
        <td width="75%" colspan=5 align="center" valign="middle" >&nbsp; ${rs.sfzh }</td> 
      </tr> 
      <tr style="height: 22px"> 
        <td width="20%" align="center" valign="middle">�ѻ��������</td> 
        <td width="20%" colspan=2 align="center" valign="middle">&nbsp; </td> 
        <td width="35%" colspan=3 >
        <logic:empty name="rs" property="xymc">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </logic:empty>
        <logic:notEmpty name="rs" property="xymc">
        &nbsp;${rs.xymc}
        </logic:notEmpty>
        ϵ
        <logic:empty name="rs" property="bjmc">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </logic:empty>
        <logic:notEmpty name="rs" property="bjmc">
        ${rs.bjmc}
        </logic:notEmpty>
        �༶</td> 
      </tr> 
      <tr style="height: 22px"> 
        <td width=20% align="center" valign="middle">���ҵ绰</td> 
        <td width=20% colspan=2 align="center" valign="middle">&nbsp; </td> 
        <td width=15% align="center" valign="middle" >�ֻ�</td> 
        <td width=45% colspan=3 >&nbsp; </td> 
      </tr> 
      <tr style="height: 22px"> 
        <td width=20%  align="center" valign="middle">�κ�ѧ���ɲ�</td> 
        <td width=20% colspan=2 align="center" valign="middle">&nbsp; </td> 
        <td width=15% align="center" valign="middle">�����</td> 
        <td width=45% colspan=3 align="center" valign="middle">&nbsp; </td> 
      </tr> 
      <tr > 
        <td width=5% rowspan=4  align="center" valign="middle" style="">
        	��<br/>
        	ͥ<br/>
        	��<br/>
        	��<br/>
        	��<br/>
        	��</td> 
        <td width=20%  align="center" valign="middle" style="height: 22px">
       		 ��ͥ����
       	</td> 
        <td width=50% colspan=4  align="center" valign="middle" style="height: 22px">
        	1������&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 2��ũ��
        </td> 
        <td width=15%  align="center" valign="middle" style="height: 22px">��ͥ����</td> 
        <td width=15%  style="">&nbsp; </td> 
      </tr> 
      
      <tr style="height: 22px"> 
        <td width=20% style="">��ͥ��������</td> 
        <td width=15% style="">&nbsp; </td> 
        <td width=20% colspan=2 style="">�˾�������</td> 
        <td width=15% style="">&nbsp; </td> 
        <td width=15% style="">������Դ</td> 
        <td width=15% style="">&nbsp; </td> 
      </tr> 
      <tr style="height: 22px"> 
        <td width=20% rowspan=2  align="center" valign="middle" style="">��ͥ��ϵ��ʽ</td> 
        <td width=15%  align="center" valign="middle" style="">�� ��</td> 
        <td width=35% colspan=3 style=""  align="center" valign="middle">&nbsp; </td> 
        <td width=15% style="" align="center" valign="middle">��ͥ�绰</td> 
        <td width=15% style="" align="center" valign="middle">&nbsp; </td> 
      </tr> 
      <tr style="height: 22px"> 
        <td  align="center" valign="middle">�� ַ</td> 
        <td  colspan=3 align="center" valign="middle">&nbsp; </td> 
        <td align="center" valign="middle">�� ��</td> 
        <td  align="center" valign="middle">&nbsp; </td> 
      </tr> 
      <tr style="height: 22px"> 
        <td  rowspan=5 align="center" valign="middle">��ͥ��Ա</td> 
        <td  align="center" valign="middle">�� ��</td> 
        <td  align="center" valign="middle">�� ��</td> 
        <td  colspan=2 align="center" valign="middle">�뱾�˹�ϵ</td> 
        <td  colspan=3 align="center" valign="middle">������λ�����ι���</td> 
      </tr> 
      <tr style="height: 22px"> 
        <td width=108 align="center" valign="middle">&nbsp; </td> 
        <td width=72 align="center" valign="middle">&nbsp; </td> 
        <td width=144 colspan=2 align="center" valign="middle">&nbsp; </td> 
        <td width=261 colspan=3 align="center" valign="middle">&nbsp; </td> 
      </tr> 
      <tr style="height: 22px"> 
        <td width=108 align="center" valign="middle">&nbsp; </td> 
        <td width=72 align="center" valign="middle">&nbsp; </td> 
        <td width=144 colspan=2 align="center" valign="middle">&nbsp; </td> 
        <td width=261 colspan=3 align="center" valign="middle">&nbsp; </td> 
      </tr> 
      <tr style="height: 22px"> 
        <td width=108 align="center" valign="middle">&nbsp; </td> 
        <td width=72 align="center" valign="middle">&nbsp; </td> 
        <td width=144 colspan=2 align="center" valign="middle">&nbsp; </td> 
        <td width=261 colspan=3 align="center" valign="middle">&nbsp; </td> 
      </tr> 
      <tr style="height: 22px"> 
        <td width=108 align="center" valign="middle">&nbsp; </td> 
        <td width=72 align="center" valign="middle">&nbsp; </td> 
        <td width=144 colspan=2 align="center" valign="middle">&nbsp; </td> 
        <td width=261 colspan=3 align="center" valign="middle">&nbsp; </td> 
      </tr> 
      <tr> 
        <td width=620 colspan=8 height="90px" >
        <p style="height:80px" >�����������(�ɸ�ҳ��ȷ�������ʵ��һ������鱨���󣬸���ǹ�����)��</p>
  <p  align="right">����ǩ����&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p></td> 
      </tr> 
      <tr> 
        <td width=620 colspan=8  height="90px" >
        <p style="height:80px">ϵ��������</p>
        <p align="right">������ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p></td> 
      </tr> 
      <tr> 
        <td width=620 colspan=8 height="100px" >
        <p style="height:90px">ѧ���������������</p>
        <p align="right">
        ������ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
        &nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p></td> 
      </tr> 
	</table>
	<div align="center" class='noPrin'>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
			ҳ������
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
			��ӡԤ��
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			ֱ�Ӵ�ӡ
		</button>
	</div>
</body>
</html:html>
