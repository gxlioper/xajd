<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<title>�����������к�����ѧ����</title>
	<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
</head>
<body> 
<div style='layout-grid:15.6pt' align="center"> 
  <b><span style='font-size:20.0pt;font-family:����;"Times New Roman";"Times New Roman"'>�й����������ᡰ�к��ʹ�ѧ����ѧ����</span></b>
  <br/>
  <b><span style='font-size:20.0pt;font-family:����;"Times New Roman";"Times New Roman"'>����ѧ�������</span></b>
  <br/>
  <b>���������&nbsp;&nbsp;&nbsp; �ٴ������&nbsp;&nbsp;&nbsp;ѧУ<u>&nbsp;&nbsp;&nbsp;${xxmc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u>&nbsp;&nbsp;���<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u></b><u></u> 
  <table class="tbstyle" width="75%" height="333px"> 
    <tr> 
      <td colspan=10 align=center bgcolor="#E0E0E0"> <b>��������</b></td> 
    </tr> 
    <tr> 
      <td align=center width="16%"> ��&nbsp;&nbsp; ��</td> 
      <td colspan=2 align=center width="15%">${rs.xm } </td> 
      <td align=center width="10%"> ��&nbsp;&nbsp;��</td> 
      <td colspan=2 align=center width="15%">${rs.xb } </td> 
      <td align=center width="11%"> ��&nbsp;&nbsp;��</td> 
      <td colspan=2 align=center width="16%">${rs.mzmc } </td> 
      <td rowspan=4 align=center width="17%"> �� �� �� �� �� Ƭ</td> 
    </tr> 
    <tr> 
      <td align=center> ��������</td> 
      <td colspan=2 align=center>${rs.csrq } </td> 
      <td colspan=2 align=center> ������ò&nbsp;&nbsp;&nbsp;&nbsp;</td> 
      <td colspan=4 align=center>${rs.zzmmmc } </td> 
    </tr> 
    <tr> 
      <td align=center> ��&nbsp;&nbsp;&nbsp;&nbsp; ��</td> 
      <td colspan=8 align=center>${rs.tc } </td> 
    </tr> 
    <tr> 
      <td colspan=2 align=center> ����Ժϵ���༶&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td> 
      <td colspan=7 align=center>${rs.xymc }${rs.bjmc } </td> 
    </tr> 
    <tr> 
      <td align=center> ͨ�ŵ�ַ</td> 
      <td colspan=5 align=center>${rs.xxdz }</td> 
      <td colspan=2 align=center> ��ϵ�绰</td> 
      <td colspan=2>${rs.lxdh } </td> 
    </tr> 
    <tr> 
      <td align=center> ��������</td> 
      <td colspan=5 align=center>${rs.xxyb } </td> 
      <td colspan=2 align=center> �����ʼ�</td> 
      <td colspan=2>${rs.lxdzxx } </td> 
    </tr> 
    <tr> 
      <td align=center> ���֤��</td> 
      <td colspan=5 align=center>${rs.sfzh } </td> 
      <td colspan=2 align=center> �������</td> 
      <td colspan=2>${rs.shf } </td> 
    </tr> 
    <tr> 
      <td colspan=6>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; �Ƿ��ΰࡢУ��ѧ���ᡢ��(��)��֯�ɲ�</td> 
      <td colspan=4 align="center">
      		<logic:equal value="" name="rs" property="sfbgb">
      		���&nbsp;&nbsp;/&nbsp;&nbsp;
      		�ǡ� &nbsp;&nbsp;&nbsp;&nbsp;
      		��ע�� <u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
      		</logic:equal>
      		
      		<logic:equal value="��" name="rs" property="sfbgb">
      		���&nbsp;&nbsp;/&nbsp;&nbsp;
      		�ǡ� &nbsp;&nbsp;&nbsp;&nbsp;
      		��ע�� <u>${rs.zw }</u>
      		</logic:equal>
      		
      		<logic:equal value="��" name="rs" property="sfbgb">
      		���&nbsp;&nbsp;/&nbsp;&nbsp;
      		�ǡ� &nbsp;&nbsp;&nbsp;&nbsp;
      		��ע�� <u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
      		</logic:equal>
      </td> 
    		
    </tr> 
    <tr height=0> 
      <td></td> 
      <td></td> 
      <td></td> 
      <td></td> 
      <td width=35></td> 
      <td width=77></td> 
      <td></td> 
      <td width=42></td> 
      <td></td> 
      <td></td> 
    </tr> 
  </table> 
  
  <br/><br/>
  <table class="tbstyle" width="75%" height="296px"> 
    <tr> 
      <td colspan=8 align=center bgcolor="#E0E0E0"> <b>��ͥ����</b></td> 
    </tr> 
    <tr> 
      <td align="center"> ԭ��</td> 
      <td colspan=7 align=center> &nbsp;&nbsp;${szsheng } &nbsp;&nbsp;ʡ/��&nbsp;&nbsp;${szshi }&nbsp;&nbsp; ����/��&nbsp;&nbsp;${szxian }&nbsp;&nbsp;��/��&nbsp;&nbsp;${rs.szzhen }&nbsp;&nbsp; �ֵ�/��&nbsp;&nbsp;${rs.szcun }&nbsp;&nbsp; ��</td> 
    </tr> 
    <tr> 
      <td align="center"> ��ַ</td> 
      <td colspan=2 align=center>${rs.jtdz } </td> 
      <td align=center> �ʱ�</td> 
      <td colspan=2 align=center>${rs.jtyb } </td> 
      <td align=center> �绰</td> 
      <td align=center>${rs.jtdh } </td> 
    </tr> 
    <tr> 
      <td colspan=2 align="center"> �Ƿ�ƶ����</td> 
      <td colspan=6 align=center> 
      <logic:equal value="" name="rs" property="sfpkx">
      ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      ���ǣ������Ҽ�ƶ����&nbsp;&nbsp;&nbsp;&nbsp; ��ʡ��ƶ���أ�
      </logic:equal>
      
      <logic:equal value="��" name="rs" property="sfpkx">
      ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      ���ǣ������Ҽ�ƶ����&nbsp;&nbsp;&nbsp;&nbsp; ��ʡ��ƶ���أ�
      </logic:equal>
      
      <logic:equal value="��" name="rs" property="sfpkx">
      ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      ���ǣ�
      <logic:equal value="" name="rs" property="pkxjb">
      �����Ҽ�ƶ����&nbsp;&nbsp;&nbsp;&nbsp; ��ʡ��ƶ���أ�
      </logic:equal>
      
      <logic:equal value="���Ҽ�ƶ����" name="rs" property="pkxjb">
      �����Ҽ�ƶ����&nbsp;&nbsp;&nbsp;&nbsp; ��ʡ��ƶ���أ�
      </logic:equal>
      
       <logic:equal value="ʡ��ƶ����" name="rs" property="pkxjb">
      �����Ҽ�ƶ����&nbsp;&nbsp;&nbsp;&nbsp; ��ʡ��ƶ���أ�
      </logic:equal>
      
      </logic:equal>
      </td> 
    </tr> 
    <tr> 
      <td colspan=2 align=center> �����ͥ����</td> 
      <td align=center>${rs.jtnzsr } </td> 
      <td colspan=2 align=center> ��ͥ��Ա��</td> 
      <td colspan=3 align=center>${rs.jtrs } </td> 
    </tr> 
    <tr> 
      <td colspan=2 align=center> ����ְҵ</td> 
      <td align=center>
     	 	<logic:iterate name="cyList" id="cy">
					<logic:equal value="����" name="cy" property="mc">
						${cy.cyzy }
					</logic:equal>
			</logic:iterate>
      </td> 
      <td colspan=2 align=center> ĸ��ְҵ</td> 
      <td colspan=3 align=center>
      	<logic:iterate name="cyList" id="cy">
					<logic:equal value="ĸ��" name="cy" property="mc">
						${cy.cyzy }
					</logic:equal>
			</logic:iterate>
      </td> 
    </tr> 
    <tr> 
      <td colspan=2 align="center"> �����Ƿ���Ƿծ</td> 
      <td> 
      	<logic:equal value="" name="rs" property="sfqz">
      	�С� / �ޡ�
      	</logic:equal>
      	
      	<logic:equal value="��" name="rs" property="sfqz">
      	�С� / �ޡ�
      	</logic:equal>
      	
      	<logic:equal value="��" name="rs" property="sfqz">
      	�С� / �ޡ�
      	</logic:equal>
      </td> 
      <td colspan=2 align="center"> ��ĸ�Ƿ��в���м�</td> 
      <td colspan=3 align="center"> 
      	<logic:equal value="" name="rs" property="fmjk">
      	�����С� &nbsp;&nbsp;/&nbsp;&nbsp;ĸ���С�&nbsp;&nbsp; /&nbsp;&nbsp;�ޡ�
      	</logic:equal>
      	
      	<logic:equal value="������" name="rs" property="fmjk">
      	�����С� &nbsp;&nbsp;/&nbsp;&nbsp;ĸ���С�&nbsp;&nbsp; /&nbsp;&nbsp;�ޡ�
      	</logic:equal>
      	
      	<logic:equal value="ĸ����" name="rs" property="fmjk">
      	�����С� &nbsp;&nbsp;/&nbsp;&nbsp;ĸ���С�&nbsp;&nbsp; /&nbsp;&nbsp;�ޡ�
      	</logic:equal>
      	
      	<logic:equal value="��" name="rs" property="fmjk">
      	�����С� &nbsp;&nbsp;/&nbsp;&nbsp;ĸ���С�&nbsp;&nbsp; /&nbsp;&nbsp;�ޡ�
      	</logic:equal>
      	
      	<logic:equal value="��ĸ����" name="rs" property="fmjk">
      	�����С� &nbsp;&nbsp;/&nbsp;&nbsp;ĸ���С�&nbsp;&nbsp; /&nbsp;&nbsp;�ޡ�
      	</logic:equal>
      </td> 
    </tr> 
    <tr> 
      <td colspan=2 align="center"> ��ĸ�Ƿ���</td> 
      <td colspan=6 align="center">
      	<logic:equal value="" name="rs" property="fmjz">
      	 ��ĸ˫ȫ�� / ��ĸ˫���� / ����ĸ�ڡ� / ����ĸ����
      	</logic:equal>
      	
      	<logic:equal value="��ĸ˫ȫ" name="rs" property="fmjz">
      	 ��ĸ˫ȫ�� / ��ĸ˫���� / ����ĸ�ڡ� / ����ĸ����
      	</logic:equal>
      	
      	<logic:equal value="��ĸ˫��" name="rs" property="fmjz">
      	 ��ĸ˫ȫ�� / ��ĸ˫���� / ����ĸ�ڡ� / ����ĸ����
      	</logic:equal>
      	
      	<logic:equal value="����ĸ��" name="rs" property="fmjz">
      	 ��ĸ˫ȫ�� / ��ĸ˫���� / ����ĸ�ڡ� / ����ĸ����
      	</logic:equal>
      	
      	<logic:equal value="����ĸ��" name="rs" property="fmjz">
      	 ��ĸ˫ȫ�� / ��ĸ˫���� / ����ĸ�ڡ� / ����ĸ����
      	</logic:equal>
      </td> 
    </tr> 
    <tr height=0> 
      <td></td> 
      <td></td> 
      <td></td> 
      <td></td> 
      <td></td> 
      <td width=28></td> 
      <td></td> 
      <td></td> 
    </tr> 
  </table> 
  <br clear=all style='page-break-before:always'> 
  <table class="tbstyle" width="75%"> 
    <tr> 
      <td align=center bgcolor="#E0E0E0">
        <b>��������</b></td> 
    </tr> 
    <tr> 
      <td height="200px"> 
      	<p align="left"> &nbsp;&nbsp; ${rs.sqsm }</p>
  		<p align="right">    
      		����ǩ��<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/> 
        	����<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </p>
       </td> 
    </tr> 
  </table> 
  
  <br/><br/>
  <table class="tbstyle" width="75%"> 
    <tr> 
      <td align=center bgcolor="#E0E0E0"> <b>ѧУ���</b></td> 
    </tr>
				<tr height="200px">
					<td align=center>
						<p>
							&nbsp;&nbsp;
						</p>
						<p align="right">
							ѧУ�����£�
							<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<br />
							����
							<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;
								</u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						ע����ע�������Ƿ�������������ƶ������
					</td>
				</tr>
			</table> 
</div> 

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
</html>
