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
		<table width="87%" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
				<br/><br/><br/><br/>
					<b>
					<span style='font-size:16.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.xxmc }<logic:equal value="����ѧԺ" property="xymc" name="rs">${rs.xymc }</logic:equal>ѧ������ͥ��������
					</span>
					</b>
					<br/><br/><br/>
					<div align="left">
						<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<b>ѧУ��</b><u>${rs.xxmc }</u>
						<b><logic:equal value="����ѧԺ" property="xymc" name="rs">ϵ</logic:equal>
						<logic:notEqual value="����ѧԺ" property="xymc" name="rs">Ժ��ϵ��</logic:notEqual>
						��</b><u>${rs.xymc }</u>
						<b>רҵ��</b><u>${rs.zymc }</u>
						<b>�꼶��</b><u>${rs.nj }</u>
						</span>
					</div>
				</td>
			</tr>
			<tr>
				<td align="center">
		<table width="100%" id="rsT" class="printstyle">
			<!-- ѧ�����˻������ -->
			<tr>
				<td width="7%"></td>
				<td width="7"></td>
				<td width="5"></td>
				<td width="2"></td>
				<td width="3"></td>
				<td width="7"></td>
				<td width="5"></td>
				<td width="5"></td>
				<td width="6"></td>
				<td width="5"></td>
				<td width="5"></td>
				<td width=""></td>
				<td width="5"></td>
				<td width=""></td>
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
						${rs.xm }
				</td>
				<td align="center">
						�Ա�
				</td>
				<td align="center">
						${rs.xb }
				</td>
				<td align="center" colspan="2">
						��������
				</td>
				<td colspan="2" align="center" >
						${rs.csrq }
				</td>
				<td colspan="2" align="center" >
						����
				</td>
				<td align="center">
						${rs.mzmc }
				</td>
			</tr>
			<!-- �ڶ��� -->
			<tr style="height:30px">
				<td width="" align="center">
						���֤<br/>����
				</td>
				<td colspan="4" align="center">
						${rs.sfzh }
				</td>
				<td align="center">
						����<br/>��ò
				</td>
				<td colspan="2" align="center">
						${rs.zzmmmc }
				</td>
				<td align="center" colspan="2">
						��ѧǰ<br/>����	
				</td>
				<td align="center" colspan="3">
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
				</td>
			</tr>
			<!-- ������ -->
			<tr style="height:30px">
				<td align="center">
						��ͥ��<br/>����		
				</td>
				<td colspan="4" align="center">
						${rs.jtrs }
				</td>
				<td align="center">
						��ҵ<br/>ѧУ					
				</td>
				<td colspan="2" align="center">
						${rs.rxqdw }
				</td>
				<td align="center" colspan="2">
						����<br/>�س�
				</td>
				<td align="center" colspan="3">
						${rs.tc }
				</td>
			</tr>
			<!-- ������ -->
			<tr style="height:30px">
				<td align="center">
						�� ��
				</td>
				<td align="center" colspan="2">
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
				</td>
				<td align="center" colspan="2">
						�� ��
				</td>
				<td align="center" colspan="3">
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
				</td>
				<td align="center" colspan="2">
						��ʿ��Ů
				</td>
				<td align="center" colspan="3">
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
				<td colspan="11" align="center">
						${rs.jtdz }
				</td>
			</tr>
			<!-- �ڶ��� -->
			<tr style="height:30px">
				<td align="center" colspan="2">
						��������
				</td>
				<td align="center" colspan="3">
						${rs.jtyb }
				</td>
				<td align="center" colspan="2">
						��ϵ�绰
				</td>
				<td colspan="6" align="center" >
						${rs.jtdh }
				</td>
			</tr>
			<!-- ��ͥ��Ա��� -->
			<tr style="height:30px">
				<td rowspan="${cyNum+1 }"  align="center">
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
							${cy.mc }&nbsp;&nbsp;
					</td>
					<td align="center"colspan="4">
							${cy.cygzdw }&nbsp;&nbsp;
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
					��ͥ�˾�������:<logic:notEqual value="" property="jtrjsr" name="rs"><u>&nbsp;${rs.jtrjsr }&nbsp;</u></logic:notEqual><logic:equal value="" property="jtrjsr" name="rs"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></logic:equal>��Ԫ����ѧ����ѧ���ѻ��������:
					<logic:notEqual value="" property="yhzzqk" name="rs"><u>&nbsp;${rs.yhzzqk }&nbsp;</u></logic:notEqual><logic:equal value="" property="yhzzqk" name="rs"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></logic:equal>��<br><br>
					��ͥ������Ȼ�ֺ������<logic:notEqual value="" property="jtszqk" name="rs"><u>&nbsp;${rs.jtszqk }&nbsp;</u></logic:notEqual><logic:equal value="" property="jtszqk" name="rs"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></logic:equal>����ͥ����ͻ�������¼���<logic:notEqual value="" property="tfsjqk" name="rs"><u>&nbsp;${rs.tfsjqk }&nbsp;</u></logic:notEqual><logic:equal value="" property="tfsjqk" name="rs"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></logic:equal>��<br><br>
					��ͥ��Ա��м����������Ͷ������������<logic:notEqual value="" property="cjnmqk" name="rs"><u>&nbsp;${rs.cjnmqk }&nbsp;</u></logic:notEqual><logic:equal value="" property="cjnmqk" name="rs"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></logic:equal>��<br><br>
					��ͥ��Աʧҵ�����<logic:notEqual value="" property="jtsyqk" name="rs"><u>&nbsp;${rs.jtsyqk }&nbsp;</u></logic:notEqual><logic:equal value="" property="jtsyqk" name="rs"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></logic:equal>����ͥǷծ�����<logic:notEqual value="" property="jtqzqk" name="rs"><u>&nbsp;${rs.jtqzqk }&nbsp;</u></logic:notEqual><logic:equal value="" property="jtqzqk" name="rs"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></logic:equal>��<br><br>
					���������<logic:notEqual value="" property="jtqtqk" name="rs"><u>&nbsp;${rs.jtqtqk }&nbsp;</u></logic:notEqual><logic:equal value="" property="jtqtqk" name="rs"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></logic:equal>��
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
				<td >

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
				<td colspan="6">	
					<div align="left">
						<br>
						������ǩ�֣�
						<br>
						<br>
						��λ���ƣ�
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
				<td colspan="11">
					${rs.mzbmtxdz }
				</td>
			</tr>
			<tr style="height:30px" align="center">
				<td colspan="2" align="center">
					��������
				</td>
				<td colspan="3" align="center">
					${rs.mzbmyzbm }
				</td>
				<td colspan="2" align="center">
					��ϵ�绰
				</td>
				<td colspan="6" align="center">
					${rs.mzbmlxdh }
				</td>
			</tr>
		</table>
		<br/>
		<div align="left">
			<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
			��ע���˱�һʽ2�ݣ�һ��<logic:equal value="����ѧԺ" property="xymc" name="rs">��ϵ������</logic:equal><logic:notEqual value="����ѧԺ" property="xymc" name="rs"><bean:message key="lable.xb" /></logic:notEqual>���棬һ��ѧУ���棨�ɸ�ӡ����
			</span>
		</div>
		</td>
		</tr>
		</table>
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
