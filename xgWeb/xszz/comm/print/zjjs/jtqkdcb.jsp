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
<body style="padding-right: 0mm">	
		<br/><br/><br/>
		<div align="center" style="font-size:22px;font:'����' "><b>�ߵ�ѧУѧ������ͥ��������</b></div>
		<br/><br/>
		<p align="center" style="font-size:15px;">
		<b>
		ѧУ��<u>${rs.xxmc }</u>
		Ժ��ϵ����<u>${rs.xymc }</u>
		רҵ��<u>${rs.zymc }</u>
		�꼶��<u>${rs.nj }</u>
		</b>
		</p>
		<table class="printtab" style="font-family:����_GB2312;font-size:15px;" width="100%">

			<!-- ѧ�����˻������ -->
			<!-- ��һ�� -->
			<tr height="22px">
				<th width="10%" rowspan="4" >
					<div align="center">
					<font style="font-size:15px">
						ѧ<br>
						��<br>
						��<br>
						��<br>
						��<br>
						��<br>
						��<br>
						��
					</font>
					</div>
				</th>
				<th width="10%">
					�� ��
				</th>
				<th width="20%" colspan="2">
					${rs.xm }
				</th>
				<th width="10%">
					�� ��
				</th>
				<th width="12%">
					${rs.xb }
				</th>
				<th width="10%">
					������<br/>��
				</th>
				<th width="10%">
					${rs.csrq }
				</th>
				<th width="10%">
					�� ��
				</th>
				<th width="10%">
					${rs.mzmc }
				</th>
			</tr>
			<!-- �ڶ��� -->
			<tr height="22px">
				<th>
					<div align="center">
						���֤<br/>����
					</div>
				</th>
				<th colspan="3">
					<div align="center">
						${rs.sfzh }
					</div>
				</th>
				<th>
					<div align="center">
						������<br/>ò
					</div>
				</th>
				<th>
					<div align="center">
						${rs.zzmmmc }
					</div>
				</th>
				<th>
					<div align="center">
						��ѧǰ<br/>����	
					</div>
				</th>
				<th colspan="2">
					<div align="center">
						<logic:empty name="rs" property="jthk">
							������&nbsp;
							��ũ��
						</logic:empty>
						<logic:equal name="rs" property="jthk" value="����">
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							����&nbsp;
							��ũ��
						</logic:equal>
						<logic:equal name="rs" property="jthk" value="ũ��">
							������&nbsp;
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							ũ��
						</logic:equal>	
					</div>
				</th>
			</tr>
			<!-- ������ -->
			<tr height="22px">
				<th>
					<div align="center">
						��ͥ��<br/>����		
					</div>
				</th>
				<th colspan="3">
					<div align="center">
						${rs.jtrs }
					</div>
				</th>
				<th>
					<div align="center">
						��ҵ<br/>ѧУ					
					</div>
				</th>
				<th >
					<div align="center">
						${rs.rxqdw }
					</div>
				</th>
				<th >
					<div align="center">
						����<br/>�س�
					</div>
				</th>
				<th colspan="2">
					<div align="center">
						${rs.tc }
					</div>
				</th>
			</tr>
			<!-- ������ -->
			<tr height="22px">
				<th>
					<div align="center">
						�� ��
					</div>
				</th>
				<th colspan="2">
					<div align="center">					
						<logic:empty name="rs" property="sfgc">
							����&nbsp;&nbsp;
							����
						</logic:empty>
						<logic:equal name="rs" property="sfgc" value="��">
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							��&nbsp;&nbsp;
							����
						</logic:equal>
						<logic:equal name="rs" property="sfgc" value="��">
							����&nbsp;&nbsp;
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							��
						</logic:equal>				
					</div>
				</th>
				<th >
					<div align="center">
						�� ��
					</div>
				</th>
				<th colspan="2">
					<div align="center">
						<logic:empty name="rs" property="sfdq">
							����&nbsp;&nbsp;
							����
						</logic:empty>
						<logic:equal name="rs" property="sfdq" value="��">
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							��&nbsp;&nbsp;
							����
						</logic:equal>
						<logic:equal name="rs" property="sfdq" value="��">
							����&nbsp;&nbsp;
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							��
						</logic:equal>		
					</div>
				</th>
				<th>
					<div align="center">
						��ʿ��Ů
					</div>
				</th>
				<th colspan="2">
					<div align="center">
						<logic:empty name="rs" property="lszn">
							����&nbsp;&nbsp;
							����
						</logic:empty>
						<logic:equal name="rs" property="lszn" value="��">
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							��&nbsp;&nbsp;
							����
						</logic:equal>
						<logic:equal name="rs" property="lszn" value="��">
							����&nbsp;&nbsp;
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							��
						</logic:equal>			
					</div>
				</th>
			</tr>
			<!-- ��ͥͨѶ��Ϣ -->
			<!-- ��һ�� -->
			<tr height="22px">
				<th rowspan="2">
					<div align="center">
					<font style="font-size:15px">
						��ͥ<br>
						ͨѶ<br>
						��Ϣ
					</font>
					</div>
				</th>
				<th colspan="2">
					<div align="center"  >
						��ϸͨѶ��ַ
					</div>
				</th>
				<th colspan="7">
					<div align="center">
						${rs.jtdz }
					</div>
				</th>
			</tr>
			<!-- �ڶ��� -->
			<tr height="22px">
				<th colspan="2">
					<div align="center">
						��������
					</div>
				</th>
				<th colspan="2">
					<div align="center">
						${rs.jtyb }
					</div>
				</th>
				<th>
					<div align="center">
						��ϵ�绰
					</div>
				</th>
				<th colspan="4">
					<div align="center">
						${rs.jtdh }
					</div>
				</th>
			</tr>
			
			<logic:lessEqual value="6" name="${cyNum}">
			<tr style="height:22px">
				<th rowspan="7">
					<div align="center">
					<font style="font-size:15px">
						��<br>
						ͥ<br>
						��<br>
						Ա<br>
						��<br>
						��
					</font>
					</div>
				</th>
				<th width="10%">
					<div align="center">
						����
					</div>
				</th>
				<th width="10%">
					<div align="center">
						����
					</div>
				</th>
				<th width="10%">
					<div align="center">
						��ѧ��<br/>��ϵ
					</div>
				</th>
				<th colspan="2">
					<div align="center">
						������ѧϰ����λ
					</div>
				</th>
				<th>
					<div align="center">
						ְҵ
					</div>
				</th>
				<th colspan="2">
					<div align="center">
						�����루Ԫ��
					</div>
				</th>
				<th>
					<div align="center">
						����<br/>״��
					</div>
				</th>
				</tr>
				
				<logic:iterate name="cyList" id="cy">
				<tr style="height:22px">
					<th>
						<div align="center">
							${cy.cyxm }
						</div>
					</th>
					<th>
						<div align="center">
							${cy.cynl }
						</div>
					</th>
					<th>
						<div align="center">
							${cy.mc }
						</div>
					</th>
					<th colspan="2">
						<div align="center">
							${cy.cygzdw }
						</div>
					</th>
					<th>
						<div align="center">
							${cy.cyzy }
						</div>
					</th>
					<th colspan="2">
						<div align="center">
							${cy.cynsr }
						</div>
					</th>
					<th>
						<div align="center">
							${cy.cyjkzk }
						</div>
					</th>
				</tr>
			</logic:iterate>
			<logic:iterate name="rs" id="cy" length="${6-cyNum}">
					<tr height="28px">
					<th>
						
					</th>
					<th>
						
					</th>
					<th>
						
					</th>
					<th colspan="2">
						
					</th>
					<th>
						
					</th>
					<th colspan="2">
						
					</th>
					<th>
						
					</th>
				</tr>
					</logic:iterate>
			</logic:lessEqual>
			
			
			<!-- ��ͥ��Ա��� -->
			<logic:greaterThan value="6" name="cyNum">
			<tr style="height:22px">
				<th rowspan="${cyNum+7 }">
					<div align="center">
					<font style="font-size:15px">
						��<br>
						ͥ<br>
						��<br>
						Ա<br>
						��<br>
						��
					</font>
					</div>
				</th>
				<th>
					<div align="center">
						����
					</div>
				</th>
				<th>
					<div align="center">
						����
					</div>
				</th>
				<th>
					<div align="center">
						��ѧ����ϵ
					</div>
				</th>
				<th colspan="2">
					<div align="center">
						������ѧϰ����λ
					</div>
				</th>
				<th>
					<div align="center">
						ְҵ
					</div>
				</th>
				<th colspan="2">
					<div align="center">
						�����루Ԫ��
					</div>
				</th>
				<th>
					<div align="center">
						����״��
					</div>
				</th>
				</tr>
				<logic:iterate name="cyList" id="cy">
				<tr>
					<th>
						<div align="center">
							${cy.cyxm }&nbsp;&nbsp;
						</div>
					</th>
					<th>
						<div align="center">
							${cy.cynl }&nbsp;&nbsp;
						</div>
					</th>
					<th>
						<div align="center">
							${cy.mc }&nbsp;&nbsp;
						</div>
					</th>
					<th colspan="2">
						<div align="center">
							${cy.cygzdw }&nbsp;&nbsp;
						</div>
					</th>
					<th>
						<div align="center">
							${cy.cyzy }&nbsp;&nbsp;
						</div>
					</th>
					<th colspan="2">
						<div align="center">
							${cy.cynsr }&nbsp;&nbsp;
						</div>
					</th>
					<th>
						<div align="center">
							${cy.cyjkzk }&nbsp;&nbsp;
						</div>
					</th>
				</tr>
			</logic:iterate>
			</logic:greaterThan>
			<!-- �����Ϣ -->
			<tr style="height:100px">
				<th width="5%">
					<font style="font-size:15px">
					Ӱ��<br>
					��ͥ<br>
					����<br>
					״��<br>
					�й�<br>
					��Ϣ
					</font>
				</th>
				<th colspan="9" align="left">
				��ͥ�˾�������<u>&nbsp;&nbsp;${rs.jtrjsr }&nbsp;&nbsp;<logic:equal value="" name="rs" property="jtrjsr">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:equal></u>��Ԫ����
				ѧ����ѧ���ѻ��������<u>&nbsp;&nbsp;${rs.yhzzqk}&nbsp;&nbsp;<logic:equal value="" name="rs" property="yhzzqk">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:equal></u>��<br/>
				��ͥ������Ȼ�ֺ������<u>&nbsp;&nbsp;${rs.jtszqk}&nbsp;&nbsp;<logic:equal value="" name="rs" property="jtszqk">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:equal></u>��
				��ͥ����ͻ�������¼���<u>&nbsp;&nbsp;${rs.tfsjqk}&nbsp;&nbsp;<logic:equal value="" name="rs" property="tfsjqk">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:equal></u> ��<br>
				��ͥ��Ա��м����������Ͷ������������<u>&nbsp;&nbsp;${rs.cjnmqk}&nbsp;&nbsp;<logic:equal value="" name="rs" property="cjnmqk">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:equal></u> ��<br>
				��ͥ��Աʧҵ�����<u>&nbsp;&nbsp;${rs.jtsyqk}&nbsp;&nbsp;<logic:equal value="" name="rs" property="jtsyqk">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:equal></u> ��
				��ͥǷծ�����<u>&nbsp;&nbsp;${rs.jtqzqk}&nbsp;&nbsp;<logic:equal value="" name="rs" property="jtqzqk">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:equal></u>��<br>
				���������<u>&nbsp;&nbsp;${rs.jtqtqk}&nbsp;&nbsp;<logic:equal value="" name="rs" property="jtqtqk">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:equal></u>��		
				</th>
			</tr>
			<!-- �����Ϣ -->
			<tr style="">
				<th width="5%">
					<font style="font-size:15px">
					ǩ<br>
					��<br>
					</font>
				</th>
				<th>
					ѧ����<br>
					��ǩ��
				</th>
				<th>

				</th>
				<th>
					ѧ����<br>
					�����<br>
					����ǩ<br>
					��
				</th>
				<th>

				</th>
				<th width="10%">
					ѧ����ͥ<br>���ڵ���<br>���ֵ�<br>��������<br>���
				</th>
				<th colspan="4">	
					<div align="left">		
						<br/>
						���صͱ���׼��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ԫ/��.��<br>
						������ǩ�֣�<br>
						��λ���ƣ�<br/>
						���Ӹǹ��£�
						<br>
					</div>
					<div align="left">
						 <u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
					</div>
				</th>
			</tr>
			<!-- ����������Ϣ -->
			<tr height="22px">
				<th width="5%" rowspan="2">
					<font style="font-size:15px">
					����<br>
					����<br>
					��Ϣ<br>
					</font>
				</th>
				<th colspan="2" >
					��ϸͨѶ��ַ
				</th>
				<th colspan="7">

				</th>
			</tr>
			<tr height="22px">
				<th colspan="2">
					��������
				</th>
				<th colspan="2">

				</th>
				<th>
					��ϵ�绰
				</th>
				<th colspan="4" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�����ţ���</th>
			</tr>
		</table>
		<div align="left"  style="font-family:����_GB2312;">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<B>ע��</B>�ͱ�������ʿ��ͥ���屣�����м�ѧ���ȸ�֤���ļ���ӡ����
		</div>
		<br>
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
