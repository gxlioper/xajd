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
			<p align=center style='text-align:center'>
				<b><span style='font-size:20.0pt;font-family:����'>�������ϴ�ѧ${rs.xmmc }�ǼǱ�</span></b>
			</p>
			<table class="printtab" width="90%">
				<tr style="height:45px" >
					<td width="16%" align="center">
						<p align=center style='text-align:center'>
							<span style='font-family:����;"Times New Roman"'>����</span>
						</p>
					</td>
					<td  width="16%" align="center">
						&nbsp;${rs.xm }
					</td>
					<td  width="16%" align="center">
						<p align=center style='text-align:center'>
							<span style='font-family:����;"Times New Roman"'>��</span>
							<span style='font-family:����;"Times New Roman";"Times New Roman"'>��</span>
						</p>
					</td>
					<td  width="16%" align="center">
						&nbsp;${rs.xb }
					</td>
					<td   width="16%" align="center">
						<p align=center style='text-align:center;'>
							<span style='font-family:����;"Times New Roman"'>רҵ</span>
						</p>
					</td>
					<td  width="16%" align="center">
						&nbsp;${rs.zymc }
					</td>
					
				</tr>
				<tr style="height:45px">
					<td align="center">
						<p align=center style='text-align:center'>
							<span style='font-family:����;"Times New Roman"'>����</span>
						</p>
					</td>
					<td  align="center">
						&nbsp;${rs.mzmc }
					</td>
					<td  align="center">
						<p align=center style='text-align:center'>
							<span style='font-family:����;"Times New Roman"'>������ò</span>
						</p>
					</td>
					<td align="center">
						&nbsp;${rs.zzmmmc }
					</td>
					<td  align="center">
						<p align=center style='text-align:center'>
							<span style='font-family:����;"Times New Roman"'>ѧ��</span>
						</p>
					</td>
					<td  align="center">
						&nbsp;${rs.xh}
					</td>
					
				</tr>
				<tr style="height:45px">
					<td align="center">
						<p align=center style='text-align:center'>
							<span style='font-family:����;"Times New Roman"'>�������</span>
						</p>
					</td>
					<td align="center" >
						&nbsp;${rs.pjnd }
					</td>
					<td align="center">
						<p align=center style='text-align:center'>
							<span style='font-family:����;"Times New Roman"'>��ϵ��ʽ</span>
						</p>
					</td>
					<td align="center">
						&nbsp;${rs.lxdh }
					</td>
					<td  align="center">
						<p align=center style='text-align:center'>
							<span style='font-family:����;"Times New Roman"'>�༶</span>
						</p>
					</td>
					<td  align="center">
						&nbsp;${rs.bjmc }
					</td>
					
				</tr>
				<tr style="height:45px">
					<td align="center">
						<p align=center style='text-align:center'>
							<span style='font-family:����;"Times New Roman"'>����<bean:message key="lable.xb" /></span>
						</p>
					</td>
					<td  align="center" colspan=3  >
						&nbsp;${rs.xymc}
					</td>
					<td  align="center">
						<p align=center style='text-align:center'>
							<span style='font-family:����;"Times New Roman"'>��ҵ��Ƴɼ�</span>
						</p>
					</td>
					<td align="center" colspan=1>
						&nbsp;
					</td>
				</tr>
				<tr style="height:45px">
					<td  align="center">
						<p align=center style='text-align:center'>
							<span style='font-family:����;"Times New Roman"'>��ҵȥ��</span>
						</p>
					</td>
					<td  align="center" colspan=5 valign=top >
						&nbsp;
					</td>
					
				</tr>
				<tr style="height:70px">
					<td >
						<p align=center style='text-align:center'>
							<span style='font-family:����;"Times New Roman"'>�ٻ�ƺ�</span>
						</p>
					</td>
					<td colspan=5 valign=top >
						<p>
							<span style='font-family:����;
 							 &quot;Times New Roman&quot;'>
 							 
 							 <logic:iterate name="pjinfo" id="pjpy">
 							 	${pjpy.pjinfo }
 							 </logic:iterate>
 							 
 							 </span>
						</p>
					</td>
					
				</tr>
				<tr style="height:530px">
					<td  align="center">
						<p align=center style='text-align:center'>
							<span style='font-family:����;"Times New Roman"'>��</span>
						</p>
						<p align=center style='text-align:center'>
							<span style='font-family:����;"Times New Roman"'>��</span>
						</p>
						<p align=center style='text-align:center'>
							<span style='font-family:����;"Times New Roman"'>��</span>
						</p>
						<p align=center style='text-align:center'>
							<span style='font-family:����;"Times New Roman"'>��</span>
						</p>
					</td>
					<td  align="left" colspan=5 valign=top>
						<p>
							<span style='font-family:����;
  							&quot;Times New Roman&quot;'>������Ҫ�¼���<br/>
  							&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }</span>
						</p>
					</td>
					
				</tr>
				 <tr style="height:150px"> 
			      <td colspan=3   align="center" > <p align="left" style="vertical-align: top;height: 80px" >
			      	<span ><bean:message key="lable.xb" />���<br/>
			      	&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyyj }
			      	</span>
			      </p> 
			      
			      <p align="right" style="vertical-align: bottom;">
			      	<span style='font-family:����'>��&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
			      	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p>
			      </td> 
			      
			     <td colspan=3  align="center"> <p align="left" style="vertical-align: top;height: 80px" ><span >ѧУ���<br/>
			     	&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxyj }
			     </span>
			      </p> 
			       <p align="right" style="vertical-align: bottom;">
			      	<span style='font-family:����'>��&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
			      	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p>
			      </td>  
			    </tr> 
			</table>
			<p>
			<p style="text-align: left;"><span style='font-family:����'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ע���˱�һʽ���ݣ��ɸ��ơ���ӡ��</span>
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
