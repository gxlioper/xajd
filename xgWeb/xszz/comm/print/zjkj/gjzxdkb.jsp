<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<meta name=ProgId content=Excel.Sheet>
	<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	<!-- end -->
</head>
<body> 

<div align="center"> 
	<div align="center" style="font-size:28px;font:'����' "><b>������ѧ��������������</b></div>
		<br>
		<div align="center" style="font-size:15px;">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;
		ԺУ���ƣ�&nbsp;&nbsp;${rs.xymc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  ��ţ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
  <table class="tbstyle" width="80%" height="850px"> 
    <tr align="center"> 
      <td width="6%" rowspan=6>��<br/>��<br/>��<br/>��<br/>��</td> 
      <td width="10%">����</td> 
      <td width="10%">${rs.xm }</td> 
      <td width="10%">�Ա�</td> 
      <td width="10%">${rs.xb }</td> 
      <td width="12%">��������</td> 
      <td width="15%">${rs.csrq }</td> 
      <td width="12%">���֤��</td> 
      <td width="15%">${rs.sfzh }</td> 
    </tr> 
    <tr align="center"> 
      <td rowspan=2>Ժϵ</td> 
      <td colspan=3 rowspan=2>${rs.xymc }</td> 
      <td rowspan=2>ѧ��</td> 
      <td rowspan=2>${rs.xz }</td> 
      <td>ѧ��</td> 
      <td>${rs.xh }</td> 
    </tr> 
    <tr align="center"> 
      <td>���ÿ���</td> 
      <td>${rs.yhkh }</td> 
    </tr> 
    <tr align="center"> 
      <td rowspan=2>��ͥסַ</td> 
      <td colspan=5 rowspan=2>��</td> 
      <td>��������</td> 
      <td>${rs.jtyb }</td> 
    </tr> 
    <tr align="center"> 
      <td>��ϵ�绰</td> 
      <td>${rs.jtdh }</td> 
    </tr> 
    <tr align="center"> 
      <td>��ע</td> 
      <td colspan=7></td> 
    </tr> 
    <tr align="center"> 
      <td rowspan="${cyNum+3 }">��<br/>ͥ<br/>��<br/>��</td> 
      <td>��ϵ</td> 
      <td>����</td> 
      <td colspan=4>������λ</td> 
      <td>ÿ������</td> 
      <td>��ϵ�绰</td> 
    </tr> 
    <logic:iterate name="cyList" id="cy">
							<tr height="20px" align="center">
								<td>
										${cy.mc }&nbsp;&nbsp;
										
								</td>
								<td>
										${cy.cyxm }&nbsp;&nbsp;
								</td>
								<td colspan=4>
										${cy.cygzdw }
								</td>
								<td>
										${cy.cyysr }&nbsp;&nbsp;
								</td>
								<td>
										${cy.cydh }&nbsp;&nbsp;
								</td>								
							</tr>
						</logic:iterate>
    <tr align="center"> 
      <td>��</td> 
      <td>��</td> 
      <td colspan=4>��</td> 
      <td>��</td> 
      <td>��</td> 
    </tr> 
    <tr align="center"> 
      <td>��</td> 
      <td>��</td> 
      <td colspan=4>��</td> 
      <td>��</td> 
      <td>��</td> 
    </tr> 
    <tr align="center"> 
      <td rowspan=4>��<br/>��<br/>��<br/>��</td> 
      <td rowspan=3>��������ܶ�</td> 
      <td rowspan=3>${rs.zje }��</td> 
      <td rowspan=3>��������</td> 
      <td rowspan=3>��</td> 
      <td rowspan=3>���ŷ�ʽ</td> 
      <td colspan=3>һ���Է���<font
  class="font68574">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </font><font class="font08574">Ԫ</font><font class="font68574">&nbsp;&nbsp;&nbsp;</font></td> 
    </tr> 
    <tr align="center"> 
      <td colspan=3>��ѧ�귢�ţ�ÿѧ��<font class="font68574">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </font><font class="font08574">Ԫ</font></td> 
    </tr> 
    <tr align="center"> 
      <td colspan=3>��</td> 
    </tr>
				<tr>
					<td colspan=8 rowspan="1" height="80px" valign="bottom" align="right">
						<p>
						�����ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						
						 ��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
						</p>
					</td>
				</tr>
				<tr align="center">
					<td rowspan=3>
						��
						<br />
						֤
						<br />
						��
					</td>
					<td>
						����
					</td>
					<td colspan=2>
						������λ
					</td>
					<td colspan=3>
						�����˹�ϵ
					</td>
					<td>
						��ϵ�绰
					</td>
					<td>
						ǩ��
					</td>
				</tr>
				<tr align="center"> 
      <td>��</td> 
      <td colspan=2>��</td> 
      <td colspan=3>��</td> 
      <td>��</td> 
      <td>��</td> 
    </tr> 
    <tr align="center"> 
      <td>��</td> 
      <td colspan=2>��</td> 
      <td colspan=3>��</td> 
      <td>��</td> 
      <td>��</td> 
    </tr> 
    <tr align="center"> 
      <td>��<br/>ϵ<br/>��</td> 
      <td>��С��</td> 
      <td colspan=2>�㽭�Ƽ�ѧԺ</td> 
      <td colspan=3>ʦ��</td> 
      <td>85070162</td> 
      <td>��</td> 
    </tr> 
    <tr align="center"> 
      <td>��<br/>��<br/>ѧ<br/>Ժ<br/>��<br/>��</td> 
      <td colspan="4"></td>
      <td>ѧ<br/>��<br/>�� <br/>��<br/>��</td> 
      <td colspan="3">
      
      </td> 
    </tr> 
    
    <tr align="center" height="120px"> 
      <td>��<br/>��<br/>��<br/>��<br/>��</td> 
      <td colspan=3 align="left">
      	�������
		<br/><br/><br/><br/>      
      	<p align="right">
      		�����ˣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      		<br/>
      		��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
      	</p>
      </td> 
      <td colspan=3 align="left">
      	��鱨��
		<br/><br/><br/><br/>      
      	<p align="right">
      		����ˣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      		<br/>
      		��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
      	</p>
      </td> 
       <td colspan=3 align="left">
      	�������
		<br/><br/><br/><br/>      
      	<p align="right">
      		�����ˣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      		<br/>
      		��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
      	</p>
      </td> 
    </tr> 
  </table> 
</div>
	<br/>
	<br/>
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
