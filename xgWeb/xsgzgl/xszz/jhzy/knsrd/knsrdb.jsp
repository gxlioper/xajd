<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/syscommon/pagehead_V4.ini"%>
<html>

	<head>
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	</head>
	<body>
		
			<p style="font-size:22px;font-family:����;padding-right: 80px" align="right" >���:</p><br/>
		<center>	
			<span style="font-size:25px;font-family:����">��ְҵ����ѧԺ��ͥ��������ѧ���϶������</span>
		</center>
		<table class="printtab" style="font-size:14px;width:100%">
			<tr height="40px" align="center">
			<td rowspan="4" width="7%"><B>ѧ<br/>��<br/>��<br/>��<br/>��<br/>��<br>��<br/>��</B></td>
				<td  width="12%" colspan="2">
					<p align="center">
						��&nbsp;&nbsp;��
					</p>
				</td>
				<td  width="9%">
					<p align="center">
						${rs.xm }
					</p>
				</td>
				<td  width="7%">
					<p align="center">
						��&nbsp;&nbsp;��
					</p>
				</td>
				<td  width="8%">
					<p align="center">
						${rs.xb }
					</p>
				</td>
				<td  width="8%">
					<p align="center">
						����<br/>����
					</p>
				</td>
				<td  width="12%">
					<p align="center">
						${rs.csrq }
					</p>
				</td>
				<td  width="10%">
				    <p align="center">
				        ��&nbsp;&nbsp;��
				    </p>
				</td>
				<td  width="10%">
				      <p align="center">
				      ${rs.mzmc }
				      </p>
				</td>
			</tr>
			<tr height="40px">
				<td colspan="2">
					<p align="center">
						���֤����
					</p>
				</td>
				<td  colspan="2">
					<p align="center">
						${rs.sfzh }
					</p>
				</td>
				<td  >
					<p align="center">
						����<br/>��ò
					</p>
				</td>
				<td >
					<p align="center">
						${rs.zzmmmc }
					</p>
				</td>
				<td>
					<p align="center">
						��ͥ�˾�<br/>������
					</p>
				</td>
				<td  colspan="2">
					<p align="center">
						${rs.jtrjsr }<span  style="padding-left: 50px">Ԫ</span>
					</p>
				</td>
			</tr>
			<tr height="40px">
				<td colspan="2">
					<p align="center">
						ѧ&nbsp;&nbsp;Ժ
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						${rs.xymc }
					</p>
				</td>
				<td  colspan="2">
					<p align="center">
						ר&nbsp;&nbsp;ҵ
					</p>
				</td>
				<td  colspan="3">
					<p align="center">
						${rs.zymc }
					</p>
				</td>
			</tr>
			<tr height="40px">
				<td colspan="2">
					<p align="center">
						�༶
					</p>
				</td>
				<td  colspan="2">
					<p align="center">
						${rs.bjmc }
					</p>
				</td>
				<td >
					<p align="center">
						��У��<br/>ϵ�绰
					</p>
				</td>
				<td colspan="4">
					<p align="center">
						${rs.sjhm }
					</p>
				</td>
			</tr>
			<tr >
				<td  align="center" rowspan="2" >
					<B>ѧ<br>
					��<br/>
					��<br/>
					��<br/>
					��<br/>
					��<br/>
					��<br/>
					��<br/>
					��<br/>
					��<br></B>
				</td>
				<td align="center" colspan="2" height="45px">�������</td>
				<td  colspan="10"  height="45px">
					<logic:present name="knslbList"><logic:iterate name="knslbList" id="knslb">${knslb.num}��${knslb.mc };</logic:iterate></logic:present>
				</td>
			</tr>
			<tr  height="150px">
				<td  colspan="12" valign="top" colspan="2">
					<p style="height: 50px">
						�������ɣ�${rs.sqly }
					</p>
					<p align="right" >
					         
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
					<p>
						 (ע��������ϸ���˵����)
					          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr height="200px">
				<td   align="center">
					<B><br/>
					��<br>
					��<br>
					��<br>
					��<br></B>
				</td>
				<td  align="center" width="7%">
				    <br/>
					��<br>
					��<br>
					��<br>
					��<br>
				</td>
				<td width="25%" colspan="2">
					<p style="letter-spacing:6px">
						A.��ͥ��������<logic:equal name="rs" property="xxtjdc" value="һ������"><img src="/xgxt/pictures/xszz/gou.jpg"></img></logic:equal>
						<logic:notEqual name="rs" property="xxtjdc" value="һ������"><font style="font-size: 24px">��</font></logic:notEqual>
					</p>
					<p  style="letter-spacing:6px">	
						B.��ͥ������������<br/><logic:equal name="rs" property="xxtjdc" value="�ر�����"><img src="/xgxt/pictures/xszz/gou.jpg"></img></logic:equal>
						<logic:notEqual name="rs" property="xxtjdc" value="�ر�����"><font style="font-size: 24px">��</font></logic:notEqual>
					</p>
					<p  style="letter-spacing:6px">
						C.��ͥ���ò�����<br/><font style="font-size: 24px">��</font>
					</p>
					
				</td>
				<td align="center">
				<br/>
				��<br>
				��<br>
				��<br>
				��<br>
				</td>
				<td colspan="5">
				<p style="height:160px"></p>
				<span  style="align:left">
				����С���鳤ǩ�֣�
				</span>
				<span  style="padding-left: 40px">	
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						 ��
						<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
						 ��
						<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
						 ��
					</span>
				</td>
			</tr>
			<tr height="250px">
				<td   align="center">
					<B><br/>
					��<br>
					��<br>
					��<br>
					��<br></B>
				</td>
				<td align="center" valign="center">
				 <br/>
				   <bean:message key="lable.xb" /><br>
				   ���<br>    
				</td>
				<td colspan="3" valign="top">
					������С���Ƽ�����Ժ<br/>
					��ϵ��������˺�<br/>
					<font style="font-size: 24px">��</font>ͬ������С�������<br/>
					<font style="font-size: 24px">��</font>��ͬ������С��<br/>���������Ϊ<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>��<br/><br/>
					�������鳤ǩ�֣�<br/>
					<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U> ��<U>&nbsp;&nbsp;&nbsp;&nbsp;</U>��<U>&nbsp;&nbsp;&nbsp;&nbsp;</U>��

				</td>
				<td width="5%" align="center" valign="center">
					ѧ<br>У<br>ѧ<br>��<br>��<br>��<br>��<br>��<br>��<br>��<br>��<br>��<br>
				</td>
				<td width="440" colspan="10" valign="top">
					<br/>
					��ѧ������Ժ��ϵ�����룬�����������ʵ��<br/>
					<font style="font-size: 24px">��</font>  ͬ�⹤���������С�������<br/>
					<font style="font-size: 24px">��</font> 	��ͬ�⹤���������С�����������Ϊ<br/>
					<font style="font-size: 24px">��</font> 	
					<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U> ��
					<br>
					<br>
					<br>
					������ǩ�֣�        
					<br>
					<br>
					<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U> ��<U>&nbsp;&nbsp;&nbsp;&nbsp;</U>��<U>&nbsp;&nbsp;&nbsp;&nbsp;</U>��<br/>
					���Ӹǲ��Ź��£�

				</td>
			</tr>
		</table>
		<p style="padding-left: 60px;font-size: 14px">
			�����һʽ���ݣ�ѧ���졢ѧ����������һ��
		</p>
		<p>
			&nbsp;
		</p>



		<div align="center" class='noPrin'>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				ҳ������
			</button>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				��ӡԤ��
			</button>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;"
				id="printButton">
				ֱ�Ӵ�ӡ
			</button>
		</div>
	</body>
</html>
