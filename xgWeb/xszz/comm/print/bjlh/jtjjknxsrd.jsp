<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<object id=eprint classid="clsid:CA03A5A8-9890-49BE-BA4A-8C524EB06441"
			codebase="images/webprint.cab" viewasext>
		</object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	</head>
	<body>
		<center>
			<span style="font-size:22px;font-family:����">�������ϴ�ѧ��ͥ��������ѧ���϶������</span>
			<br />
			<br />
			<br />
		</center>
		<table class="printtab">
			<tr height="40px">
			<td rowspan="4">   ѧ��<br/>����<br/>����<br>���  </td>
				<td width="90">
					<p align="center">
						�� ��
					</p>
				</td>
				<td width="73">
					<p align="center">
						${rs.xm }
					</p>
				</td>
				<td>
					<p align="center">
						�� ��
					</p>
				</td>
				<td>
					<p align="center">
						${rs.xb }
					</p>
				</td>
				<td>
					<p align="center">
						��������
					</p>
				</td>
				<td>
					<p align="center">
						${rs.csrq }
					</p>
				</td>
				<td width="87px">
				    <p align="center">
				                        ����
				    </p>
				</td>
				<td width="130">
				      <p align="center">
				      ${rs.mzmc }
				      </p>
				</td>
			</tr>
			<tr height="40px">
				<td width="85">
					<p align="center">
						���֤��
					</p>
				</td>
				<td  colspan="2">
					<p align="center">
						${rs.sfzh }
					</p>
				</td>
				<td width="87" >
					<p align="center">
						������ò
					</p>
				</td>
				<td width="113">
					<p align="center">
						${rs.zzmmmc }
					</p>
				</td>
				<td width="100">
					<p align="center">
						��ͥ�˾�������
					</p>
				</td>
				<td  colspan="2">
					<p align="center">
						${rs.jtrjysr }
					</p>
				</td>
			</tr>
			<tr height="40px">
				<td width="85">
					<p align="center">
						<bean:message key="lable.xb" />
					</p>
				</td>
				<td>
					<p align="center">
						${rs.xymc }
					</p>
				</td>
				<td>
					<p align="center">
						ѧ��
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						${rs.xh }
					</p>
				</td>
				<td width="100">
					<p align="center">
						רҵ
					</p>
				</td>
				<td  colspan="2">
					<p align="center">
						${rs.zymc }
					</p>
				</td>
			</tr>
			<tr height="40px">
				<td width="87">
					<p align="center">
						�༶
					</p>
				</td>
				<td  colspan="2">
					<p align="center">
						${rs.bjmc }
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						��У��ϵ�绰
					</p>
				</td>
				<td colspan="3">
					<p align="center">
						${rs.sjhm }
					</p>
				</td>
			</tr>
			<tr height="300px">
				<td width="38" align="center">
					ѧ<br>
					��<br>
					��<br>
					��<br>
					��<br>
					��<br>
					��<br>
					��<br>
					��<br>
					��<br>
				</td>
				<td width="840" colspan="11" valign="top">
					<p style="height:290px">
						<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqsm }
					</p>
					<p align="center">
					          ע��������ϸ���˵����
					          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						ѧ��ǩ�֣�
						 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						 ��
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						 ��
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						 ��
					</p>
				</td>
			</tr>
			<tr height="200">
				<td width="38" align="center">
					<br/>
					��<br>
					��<br>
					��<br>
					��<br>
				</td>
				<td  align="center">
				    <br/>
					��<br>
					��<br>
					��<br>
					��<br>
				</td>
				<td width="300" colspan="2">
					<p>
						A.��ͥ����һ������     <input type="checkbox" />
					</p>
					<p>	<br/>
						B.��ͥ������������  <input type="checkbox"/>
					</p>
					<p><br/>
						C.��ͥ���ò�����&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"/>
					</p>
				</td>
				<td align="center">
				<br/>
				��<br>
				��<br>
				��<br>
				��<br>
				</td>
				<td colspan="4">
				<p style="height:160px"></p>
				<p  align="left">
				����С���鳤ǩ�֣�
				</p>
				<p align="right">	
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						 ��
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						 ��
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						 ��
					</p>
				</td>
			</tr>
			<tr height="200">
				<td width="38" align="center">
					<br/>
					��<br>
					��<br>
					��<br>
					��<br>
				</td>
				<td align="center">
				 <br/>
				   ѧ<br>
				   Ժ<br>
				   ��<br>
				   ��<br>    
				</td>
				<td colspan="3">
				������С���Ƽ�����<bean:message key="lable.xb" />������˺�<br/>                 
				 <input type="checkbox"/>   ͬ������С�������   <br/>
				 <input type="checkbox"/>  ��ͬ������С�����������Ϊ 
				 <u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 </u>��
				
				</td>
				<td width="440" colspan="11" valign="top">
					<p style="height:160px" align="left">
					<br/>
						�������鳤ǩ�֣�
					</p>
					<p align="right">	
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						 ��
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						 ��
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						 ��
					</p>
					<p align="center">
					���Ӹ�<bean:message key="lable.xb" />���£�
					</p>
				</td>
			</tr>
		</table>
		<p>
			&nbsp;
		</p>
		<p>
			&nbsp;
		</p>



		<div align="center" class='noPrin'>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				ҳ������
			</button>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				��ӡԤ��
			</button>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6,6);return false;"
				id="printButton">
				ֱ�Ӵ�ӡ
			</button>
		</div>
	</body>
</html>
