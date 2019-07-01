<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<base target="_self">
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/pjpyFunction.js"></script>

		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
	</head>

	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>

		<html:form action="/stu_info_add" method="post">
		<div align="center" style="font-size:28px;font:'����' "><b>��ͨ�ߵ�ѧУѧ����ͥ��������</b></div>
		<br>
		<div align="left" style="font-size:15px;">
		<b>
		ѧУ��<u>${rs.xxmc }</u>
		��ϵ����<u>${rs.xymc }</u>
		רҵ��<u>${rs.zymc }</u>
		�꼶��<u>${rs.nj }</u>
		</b>
		</div>
		<br>
		<table width="100%" id="rsT" class="printstyle">
			<!-- ѧ�����˻������ -->
			<!-- ��һ�� -->
			<tr>
				<th width="10%" rowspan="4">
					<div align="center">
						ѧ<br>
						��<br>
						��<br>
						��<br>
						��<br>
						��<br>
						��<br>
						��
					</div>
				</th>
				<th width="10%">
					<div align="center">
						�� ��
					</div>
				</th>
				<th width="15%">
					<div align="center">
						${rs.xm }
					</div>
				</th>
				<th width="10%">
					<div align="center">
						�Ա�
					</div>
				</th>
				<th width="10%">
					<div align="center">
						${rs.xb }
					</div>
				</th>
				<th width="12%">
					<div align="center">
						����<br/>����
					</div>
				</th>
				<th width="8%">
					<div align="center">
						${rs.csrq }
					</div>
				</th>
				<th width="10%">
					<div align="center">
						����
					</div>
				</th>
				<th width="15%">
					<div align="center">
						${rs.mzmc }
					</div>
				</th>
			</tr>
			<!-- �ڶ��� -->
			<tr style="height:22px">
				<th>
					<div align="center">
						���֤<br/>����
					</div>
				</th>
				<th colspan="2">
					<div align="center">
						${rs.sfzh }
					</div>
				</th>
				<th>
					<div align="center">
						����<br/>��ò
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
							������&nbsp;&nbsp;
							��ũ��
						</logic:empty>
						<logic:equal name="rs" property="jthk" value="����">
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							����&nbsp;&nbsp;
							��ũ��
						</logic:equal>
						<logic:equal name="rs" property="jthk" value="ũ��">
							������&nbsp;&nbsp;
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							ũ��
						</logic:equal>	
					</div>
				</th>
			</tr>
			<!-- ������ -->
			<tr style="height:22px">
				<th>
					<div align="center">
						��ͥ<br/>�˿���		
					</div>
				</th>
				<th colspan="2">
					<div align="center">
						${rs.jtrs }
					</div>
				</th>
				<th>
					<div align="center">
						��ҵ<br/>ѧУ					
					</div>
				</th>
				<th colspan="2">
					<div align="center">
						${rs.rxqdw }
					</div>
				</th>
				<th>
					<div align="center">
						����<br/>�س�
					</div>
				</th>
				<th>
					<div align="center">
						${rs.tc }
					</div>
				</th>
			</tr>
			<!-- ������ -->
			<tr style="height:22px">
				<th>
					<div align="center">
						�� ��
					</div>
				</th>
				<th>
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
			<tr style="height:22px">
				<th rowspan="2">
					<div align="center">
						��ͥ<br>
						ͨѶ<br>
						��Ϣ
					</div>
				</th>
				<th>
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
			<tr style="height:22px">
				<th>
					<div align="center">
						�� �� �� ��
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
			
			<logic:lessEqual value="3" name="${cyNum}">
			<tr style="height:22px">
				<th rowspan="${cyNum+4 }">
					<div align="center">
						��<br>
						ͥ<br>
						��<br>
						Ա<br>
						��<br>
						��
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
				<th>
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
					<th>
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
				<%	
					for(int i=0;i<3;i++){
						%>
						<tr>
					<th>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</th>
					<th>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</th>
					<th>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</th>
					<th colspan="2">
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</th>
					<th>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</th>
					<th>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</th>
					<th>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</th>
				</tr>
						
						<%} %>
			</logic:lessEqual>
			
			
			<!-- ��ͥ��Ա��� -->
			<logic:greaterThan value="3" name="cyNum">
			<tr style="height:22px">
				<th rowspan="${cyNum+1 }">
					<div align="center">
						��<br>
						ͥ<br>
						��<br>
						Ա<br>
						��<br>
						��
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
				<th>
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
					<th>
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
					Ӱ��<br>
					��ͥ<br>
					����<br>
					״��<br>
					�й�<br>
					��Ϣ
				</th>
				<th colspan="8" align="left">
				��ͥ�˾�������:<u>&nbsp;&nbsp;${rs.jtrjsr }&nbsp;&nbsp;<logic:equal value="" name="rs" property="jtrjsr">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:equal></u>��Ԫ����
				ѧ����ѧ���ѻ��������:<u>&nbsp;&nbsp;${rs.yhzzqk}&nbsp;&nbsp;<logic:equal value="" name="rs" property="yhzzqk">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:equal></u><br>
				��ͥ������Ȼ�ֺ������<u>&nbsp;&nbsp;${rs.jtszqk}&nbsp;&nbsp;<logic:equal value="" name="rs" property="jtszqk">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:equal></u>��
				��ͥ����ͻ�������¼���<u>&nbsp;&nbsp;${rs.tfsjqk}&nbsp;&nbsp;<logic:equal value="" name="rs" property="tfsjqk">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:equal></u> ��<br>
				��ͥ��Ա��м����������Ͷ������������<u>&nbsp;&nbsp;${rs.cjnmqk}&nbsp;&nbsp;<logic:equal value="" name="rs" property="cjnmqk">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:equal></u> ��<br>
				��ͥ��Աʧҵ�����<u>&nbsp;&nbsp;${rs.jtsyqk}&nbsp;&nbsp;<logic:equal value="" name="rs" property="jtsyqk">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:equal></u> ��
				��ͥǷծ�����<u>&nbsp;&nbsp;${rs.jtqzqk}&nbsp;&nbsp;<logic:equal value="" name="rs" property="jtqzqk">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:equal></u>��<br>
				���������<u>&nbsp;&nbsp;${rs.jtqtqk}&nbsp;&nbsp;<logic:equal value="" name="rs" property="jtqtqk">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:equal></u>��		
				</th>
			</tr>
			<!-- �����Ϣ -->
			<tr style="height:100px">
				<th width="5%">
					ǩ<br>
					��<br>
				</th>
				<th>
					ѧ��<br>
					����<br>
					ǩ��
				</th>
				<th>

				</th>
				<th>
					ѧ���ҳ�<br>
					��໤��<br>
					ǩ��
				</th>
				<th>

				</th>
				<th>
					ѧ����ͥ<br>���ڵ���<br>���ֵ�<br>��������<br>���
				</th>
				<th colspan="3">	
					<div align="left">		
						<br/>
						���صͱ���׼��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ԫ/��.��<br>
						������ǩ�֣�<br>
						��λ���ƣ�<br/>
						���Ӹǹ��£�
						<br>
					</div>
					<div align="right">
						  ��<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
					</div>
				</th>
			</tr>
			<!-- ����������Ϣ -->
			<tr style="height:22px">
				<th width="5%" rowspan="2">
					����<br>
					����<br>
					��Ϣ<br>
				</th>
				<th colspan="2">
					��ϸͨѶ��ַ
				</th>
				<th colspan="6">

				</th>
			</tr>
			<tr style="height:22px">
				<th colspan="2">
					��������
				</th>
				<th colspan="2">

				</th>
				<th colspan="2">
					��ϵ�绰
				</th>
				<th colspan="2">

				</th>
			</tr>
		</table>
		<div align="left">
		ע���ͱ�������ʿ��ͥ���屣�����м�ѧ���ȸ�֤���ļ���ӡ����
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
		</html:form>
	</body>
</html>
