<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/head.ini"%>
<title>�ߵ�ѧУѧ�������ͥ��������</title>
<style>
.font_style td{font-size:14px;font-family:����; }
</style>

</head>

<body lang=ZH-CN style='text-justify-trim:punctuation'>

<div align=center>
<html:form action="/xszz_jtqkdc" method="post" styleId="jtqkdcForm">
<table width="652px" align="center" border="0">
	<tr>
		<td align="center">
		<br/><br/><br/><br/>
			<b>
			<span style='font-size:16.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
				�ߵ�ѧУѧ������ͥ��������
			</span>
			</b>
			<br/><br/><br/>
			<div align="left">
				<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
				<b>ѧУ��</b><u>${xxmc}&nbsp;&nbsp;</u><br/><br/>
				<b>Ժ��ϵ����</b><u>${jbxx.xymc }&nbsp;&nbsp;</u>
				<b>רҵ��</b><u>${jbxx.zymc }&nbsp;&nbsp;</u>
				<b>�꼶��</b><u>${jbxx.nj }</u>
				</span>
			</div>
			<br/>
		</td>
	</tr>
	<tr>
		<td align="center">
		<table width="100%" class="font_style" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" style='border-collapse:collapse;border:none;'>
			<!-- ѧ�����˻������ -->
			<tr>
				<td width="7%"></td>
				<td width="7%"></td>
				<td width="5%"></td>
				<td width="4%"></td>
				<td width="5%"></td>
				<td width="7%"></td>
				<td width="5%"></td>
				<td width="5%"></td>
				<td width="6%"></td>
				<td width="8%"></td>
				<td width="7%"></td>
				<td width="5%"></td>
				<td width="5%"></td>
				<td width="7%"></td>
			</tr>
			<!-- ��һ�� -->
			<tr style="height:30px">
				<td rowspan="4" align="center">
						<b>
						ѧ<br>
						��<br>
						��<br>
						��<br>
						��<br>
						��<br>
						��<br>
						��
						</b>
				</td>
				<td align="center">
						�� ��
				</td>
				<td  align="center" colspan="3">
						${jbxx.xm }
				</td>
				<td align="center">
						�Ա�
				</td>
				<td align="center">
						${jbxx.xb }
				</td>
				<td align="center" colspan="2">
						��������
				</td>
				<td colspan="2" align="center" >
						${jbxx.csrq }
				</td>
				<td colspan="2" align="center" >
						����
				</td>
				<td align="center">
						${jbxx.mzmc }
				</td>
			</tr>
			<!-- �ڶ��� -->
			<tr style="height:30px">
				<td width="" align="center">
						���֤<br/>����
				</td>
				<td colspan="4" align="center">
						${jbxx.sfzh }
				</td>
				<td align="center">
						����<br/>��ò
				</td>
				<td colspan="2" align="center">
						${jbxx.zzmmmc }
				</td>
				<td align="center" colspan="2">
						��ѧǰ<br/>����	
				</td>
				<td align="left" colspan="3">
					&nbsp;<logic:empty name="jtqk" property="jthk">
							������&nbsp;
							��ũ��
					</logic:empty>
					<logic:equal name="jtqk" property="jthk" value="����">
						<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							����&nbsp;
							��ũ��
					</logic:equal>
					<logic:equal name="jtqk" property="jthk" value="ũ��">
						������&nbsp;
						<img src="/xgxt/pictures/xszz/gou.jpg"></img>
						ũ��
					</logic:equal>
				</td>
			</tr>
			<!-- ������ -->
			<tr style="height:30px">
				<td align="center">
						��ͥ��<br/>����		
				</td>
				<td colspan="4" align="center">
						${jtqk.jtrs}
				</td>
				<td align="center">
						��ҵ<br/>ѧУ					
				</td>
				<td colspan="2" align="center">
						&nbsp;
				</td>
				<td align="center" colspan="2">
						����<br/>�س�
				</td>
				<td align="center" colspan="3">
						${jbxx.tc }
				</td>
			</tr>
			<!-- ������ -->
			<tr style="height:30px">
				<td align="center">
						�� ��
				</td>
				<td align="center" colspan="3">
					<logic:empty name="jtqk" property="sfgc">
						����&nbsp;&nbsp;
						����
					</logic:empty>
					<logic:equal name="jtqk" property="sfgc" value="1">
						<img src="/xgxt/pictures/xszz/gou.jpg"></img>
						��&nbsp;&nbsp;
						����
					</logic:equal>
					<logic:equal name="jtqk" property="sfgc" value="0">
						����&nbsp;&nbsp;
						<img src="/xgxt/pictures/xszz/gou.jpg"></img>
						��
					</logic:equal>
				</td>
				<td align="center" colspan="1">
						�� ��
				</td>
				<td align="center" colspan="3">
					<logic:empty name="jtqk" property="sfdq">
						����&nbsp;&nbsp;
						����
					</logic:empty>
					<logic:equal name="jtqk" property="sfdq" value="1">
						<img src="/xgxt/pictures/xszz/gou.jpg"></img>
						��&nbsp;&nbsp;
						����
					</logic:equal>
					<logic:equal name="jtqk" property="sfdq" value="0">
						����&nbsp;&nbsp;
						<img src="/xgxt/pictures/xszz/gou.jpg"></img>
						��
					</logic:equal>
				</td>
				<td align="center" colspan="2">
						��ʿ��Ů
				</td>
				<td align="left" colspan="3">
					&nbsp;<logic:empty name="jtqk" property="lszn">
						����&nbsp;&nbsp;&nbsp;&nbsp;
						����
					</logic:empty>
					<logic:equal name="jtqk" property="lszn" value="1">
						<img src="/xgxt/pictures/xszz/gou.jpg"></img>
						��&nbsp;&nbsp;&nbsp;&nbsp;
						����
					</logic:equal>
					<logic:equal name="jtqk" property="lszn" value="0">
						����&nbsp;&nbsp;&nbsp;&nbsp;
						<img src="/xgxt/pictures/xszz/gou.jpg"></img>
						��
					</logic:equal>
				</td>
			</tr>
			<!-- ��ͥͨѶ��Ϣ -->
			<!-- ��һ�� -->
			<tr style="height:30px">
				<td rowspan="2" align="center">
					<b>
						��ͥ<br>
						ͨѶ<br>
						��Ϣ
					</b>
				</td>
				<td align="center" colspan="2">
						��ϸͨѶ��ַ
				</td>
				<td colspan="11" align="left">
						&nbsp;${jtqk.jtdz}
				</td>
			</tr>
			<!-- �ڶ��� -->
			<tr style="height:30px">
				<td align="center" colspan="2">
						��������
				</td>
				<td align="center" colspan="3">
						${jtqk.jtyb}
				</td>
				<td align="center" colspan="2">
						��ϵ�绰
				</td>
				<td colspan="6" align="center" >
						${jtqk.jtdh}
				</td>
			</tr>
			<!-- ��ͥ��Ա��� -->
			<tr style="height:30px">
				<td rowspan="${cysize+1 }"  align="center">
					<b>
						��<br>
						ͥ<br>
						��<br>
						Ա<br>
						��<br>
						��
					</b>
				</td>
				<td align="center">
						����
				</td>
				<td align="center">
						����
				</td>
				<td align="center" colspan="2">
						��ѧ��<br>��ϵ
				</td>
				<td align="center" colspan="4">
						������ѧϰ����λ
				</td>
				<td align="center">
						ְҵ
				</td>
				<td align="center" colspan="2">
						������<br>��Ԫ��
				</td>
				<td align="center" colspan="2">
						����״��
				</td>
				</tr>
				<logic:iterate name="cyList" id="cy">
				<tr style="height:30px">
					<td align="center" >
							${cy.cyxm }&nbsp;&nbsp;
					</td>
					<td align="center">
							${cy.cynl }&nbsp;&nbsp;
					</td>
					<td align="center" colspan="2">
							${cy.cygxmc }&nbsp;&nbsp;
					</td>
					<td align="center"colspan="4">
							${cy.cyxxdw }&nbsp;&nbsp;
					</td>
					<td align="center">
							${cy.cyzy }&nbsp;&nbsp;
					</td>
					<td align="center" colspan="2">
							${cy.cynsr }&nbsp;&nbsp;
					</td>
					<td align="center" colspan="2">
							${cy.cyjkzk }&nbsp;&nbsp;
					</td>
				</tr>
			</logic:iterate>
			<logic:iterate name="blankList" id="blank">
				<tr style="height:30px">
					<td align="center" >
							&nbsp;&nbsp;
					</td>
					<td align="center">
							&nbsp;&nbsp;
					</td>
					<td align="center" colspan="2">
							&nbsp;&nbsp;
					</td>
					<td align="center"colspan="4">
							&nbsp;&nbsp;
					</td>
					<td align="center">
							&nbsp;&nbsp;
					</td>
					<td align="center" colspan="2">
							&nbsp;&nbsp;
					</td>
					<td align="center" colspan="2">
							&nbsp;&nbsp;
					</td>
				</tr>
			</logic:iterate>
			<!-- �����Ϣ -->
			<tr style="height:150px">
				<td align="center">
					<b>
					Ӱ��<br>
					��ͥ<br>
					����<br>
					״��<br>
					�й�<br>
					��Ϣ
					</b>
				</td>
				<td colspan="13" align="left">
					<br/>
					&nbsp;��ͥ�˾�������:<logic:notEqual value="" property="jtrjsr" name="jtqk"><u>&nbsp;${jtqk.jtrjsr }&nbsp;</u></logic:notEqual><logic:equal value="" property="jtrjsr" name="jtqk"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></logic:equal>��ѧ����ѧ���ѻ��������:
					<logic:notEqual value="" property="yhzzqk" name="jtqk"><u>&nbsp;${jtqk.yhzzqk }&nbsp;</u></logic:notEqual><logic:equal value="" property="yhzzqk" name="jtqk"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></logic:equal>��<br><br>
					&nbsp;��ͥ������Ȼ�ֺ������<logic:notEqual value="" property="jtszqk" name="jtqk"><u>&nbsp;${jtqk.jtszqk }&nbsp;</u></logic:notEqual><logic:equal value="" property="jtszqk" name="jtqk"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></logic:equal>����ͥ����ͻ�������¼���<logic:notEqual value="" property="tfsjqk" name="jtqk"><u>&nbsp;${jtqk.tfsjqk }&nbsp;</u></logic:notEqual><logic:equal value="" property="tfsjqk" name="jtqk"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></logic:equal>��<br><br>
					&nbsp;��ͥ��Ա��м����������Ͷ������������<logic:notEqual value="" property="cjnmqk" name="jtqk"><u>&nbsp;${jtqk.cjnmqk }&nbsp;</u></logic:notEqual><logic:equal value="" property="cjnmqk" name="jtqk"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></logic:equal>��<br><br>
					&nbsp;��ͥ��Աʧҵ�����<logic:notEqual value="" property="jtsyqk" name="jtqk"><u>&nbsp;${jtqk.jtsyqk }&nbsp;</u></logic:notEqual><logic:equal value="" property="jtsyqk" name="jtqk"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></logic:equal>����ͥǷծ�����<logic:notEqual value="" property="jtqzqk" name="jtqk"><u>&nbsp;${jtqk.jtqzqk }&nbsp;</u></logic:notEqual><logic:equal value="" property="jtqzqk" name="jtqk"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></logic:equal>��<br><br>
					&nbsp;���������<logic:notEqual value="" property="jtqtqk" name="jtqk"><u>&nbsp;${jtqk.jtqtqk }&nbsp;</u></logic:notEqual><logic:equal value="" property="jtqtqk" name="jtqk"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></logic:equal>��
					<br/><br/>
				</td>
			</tr>
			<!-- �����Ϣ -->
			<tr style="height:120px">
				<td align="center">
					<b>
					ǩ<br>
					��<br>
					</b>
				</td>
				<td align="center">
					ѧ<br>
					��<br>
					��<br>
					��<br>
				</td>
				<td colspan="2">

				</td>
				<td align="center">
					ѧ��<br>
					�ҳ�<br>
					���<br>
					����
				</td>
				<td colspan="2">

				</td>
				<td align="center" colspan="2">
					ѧ����ͥ<br>
					���ڵ���<br>
					���ֵ�<br>
					��������
				</td>
				<td colspan="5">	
					<div align="left">
						<br>
						&nbsp;������ǩ�֣�
						<br>
						<br>
						&nbsp;��λ���ƣ�
						<br>
					</div>
					<div align="right">
						���Ӹǹ��£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br><br>
						         <u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<!-- ����������Ϣ -->
			<tr style="height:30px">
				<td rowspan="2" align="center">
					<b>
					����<br>
					����<br>
					��Ϣ<br>
					</b>
				</td>
				<td colspan="2" align="center">
					��ϸͨѶ��ַ
				</td>
				<td colspan="11" align="left">
					&nbsp;${jtqk.mzbmtxdz}
				</td>
			</tr>
			<tr style="height:30px" align="center">
				<td colspan="2" align="center">
					��������
				</td>
				<td colspan="3" align="center">
					${jtqk.mzbmyzbm}
				</td>
				<td colspan="2" align="center">
					��ϵ�绰
				</td>
				<td colspan="6" align="center">
					${jtqk.mzbmlxdh}
				</td>
			</tr>
		</table>
		</td>
		</tr>
		</table>
	</html:form>
	</div>
</body>

</html>