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
		<table width="90%" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
				<br/><br/><br/><br/><br/>
					<b>
					<span style='font-size:16.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.xxmc }<logic:equal value="����ѧԺ" property="xymc" name="rs">${rs.xymc }</logic:equal>��ͥ�����������϶������
					</span>
					</b>
					<br/><br/><br/>
				</td>
			</tr>
			<tr>
				<td align="center">
		<table width="100%" id="rsT" class="printstyle">
			<!-- ѧ�����˻������ -->
			<tr>
				<td width="5%"></td>
				<td width="7%"></td>
				<td width="5%"></td>
				<td width="6%"></td>
				<td width="7%"></td>
				<td width="5%"></td>
				<td width="6%"></td>
				<td width="7%"></td>
				<td width="3%"></td>
				<td width="3%"></td>
				<td width="6%"></td>
				<td width="8%"></td>
				<td width="3%"></td>
				<td width="8%"></td>
				<td width="7%"></td>
			</tr>
			<!-- ��һ�� -->
			<tr style="height:20px">
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
				<td align="center"  colspan="2">
						��&nbsp;&nbsp;��
				</td>
				<td  align="center" colspan="3">
						${rs.xm }
				</td>
				<td align="center">
						��&nbsp;&nbsp;��
				</td>
				<td align="center" colspan="2">
						${rs.xb }
				</td>
				<td align="center" colspan="2">
						��������
				</td>
				<td colspan="2" align="center" >
						${rs.csrq }
				</td>
				<td align="center" >
						��&nbsp;&nbsp;��
				</td>
				<td align="center" >
						${rs.mzmc }
				</td>
			</tr>
			<!-- �ڶ��� -->
			<tr style="height:20px">
				<td align="center" colspan="2">
						���֤����
				</td>
				<td colspan="4" align="center">
						${rs.sfzh }
				</td>
				<td align="center"colspan="2">
						������ò
				</td>
				<td colspan="2" align="center">
						${rs.zzmmmc }
				</td>
				<td align="center" colspan="2">
						��ͥ�˾�<br/>������	
				</td>
				<td align="center" colspan="2">
					${rs.jtrjsr }Ԫ
				</td>
			</tr>
			<!-- ������ -->
			<tr style="height:20px">
				<td align="center" colspan="2">
						ѧ&nbsp;&nbsp;Ժ	
				</td>
				<td colspan="3" align="center">
						${rs.xymc }
				</td>
				<td align="center">
						ϵ				
				</td>
				<td colspan="4" align="center">
						${rs.xymc }
				</td>
				<td align="center">
						ר&nbsp;&nbsp;ҵ
				</td>
				<td align="center" colspan="3">
						${rs.zymc }
				</td>
			</tr>
			<!-- ������ -->
			<tr style="height:20px">
				<td align="center" colspan="2">
						��&nbsp;&nbsp;��
				</td>
				<td align="center">
						${rs.nj }		
				</td>
				<td align="center">
						��
				</td>
				<td align="center" colspan="2">
						${rs.bjmc }
				</td>
				<td align="center" colspan="3">
						��У��ϵ�绰
				</td>
				<td align="center" colspan="5">
						${rs.sjhm }
				</td>
			</tr>
			<!-- ѧ�����������϶����� -->
			<!-- ��һ�� -->
			<tr style="height:130px">
				<td align="center">
					<b>
						
						ѧ<br/>
						��<br/>
						��<br/>
						��<br/>
						��<br/>
						��<br/>
						��<br/>
						��<br/>
						��<br/>
						��
					</b>
				</td>
				<td colspan="14" align="left">
					<p style="height:120px">
						<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
						</span>
					</p>
					<div align="left">
						<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							&nbsp;���˱�֤�������������ʵ��Ч��
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							ѧ��ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
							��<u>&nbsp;&nbsp;&nbsp;</u>
							��<u>&nbsp;&nbsp;&nbsp;</u>
							��&nbsp;&nbsp;
						</span>
					</div>
					<div align="left">
						<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							<b>ע��������ϸ���˵����</b>
						</span>
					</div>
				</td>
			</tr>
			<tr style="height:40px">
				<td align="center" rowspan="3">
					<b>
						��<br/>
						��<br/>
						��<br/>
						��
					</b>
				</td>
				<td align="center" rowspan="3">
						��<br/>
						��<br/>
						��<br/>
						��
				</td>
				<td align="left" colspan="4">
						A. ��ͥ�����������ѡ�
						<%--  
						<logic:present name="rs" property="knjb">
							<logic:equal value="һ������" property="knjb" name="rs">
								��
							</logic:equal>
							<logic:equal value="��������" property="knjb" name="rs">
								<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							</logic:equal>
							<logic:equal value="" property="knjb" name="rs">
								��
							</logic:equal>
						</logic:present>
						<logic:notPresent name="rs" property="knjb">
							��
						</logic:notPresent>
						 --%>
				</td>
				<td align="center" rowspan="3">
						��<br/>
						��<br/>
						��<br/>
						��
				</td>
				<td align="left" rowspan="3" colspan="8">
					<p style="height:110px">
						<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						&nbsp;&nbsp;&nbsp;&nbsp;
						</span>
					</p>
					<div align="left">
						<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							����С���鳤ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
							��<u>&nbsp;&nbsp;&nbsp;</u>
							��<u>&nbsp;&nbsp;&nbsp;</u>
							��&nbsp;&nbsp;
						</span>
					</div>
					<br/><br/>
				</td>
			</tr>
			<tr style="height:40px">
				<td align="left" colspan="4">
						B. ��ͥ����һ�����ѡ�
						<%--  
						<logic:present name="rs" property="knjb">
							<logic:equal value="һ������" property="knjb" name="rs">
								<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							</logic:equal>
							<logic:equal value="��������" property="knjb" name="rs">
								��
							</logic:equal>
							<logic:equal value="" property="knjb" name="rs">
								��
							</logic:equal>
						</logic:present>
						<logic:notPresent name="rs" property="knjb">
							��
						</logic:notPresent>
						 --%>
				</td>
			</tr>
			<tr style="height:40px">
				<td align="left" colspan="4">
						C.��ͥ���ò����ѡ�
						<%--  
						<logic:present name="rs" property="knjb">
							<logic:equal value="һ������" property="knjb" name="rs">
								��
							</logic:equal>
							<logic:equal value="��������" property="knjb" name="rs">
								��
							</logic:equal>
							<logic:equal value="" property="knjb" name="rs">
								<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							</logic:equal>
						</logic:present>
						<logic:notPresent name="rs" property="knjb">
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
						</logic:notPresent>
						 --%>
				</td>
			</tr>
			<tr style="height:120px">
				<td align="center">
					<b>
						��<br/>
						��<br/>
						��<br/>
						��
					</b>
				</td>
				<td align="center">
						<logic:equal value="����ѧԺ" property="xymc" name="rs">ϵ������</logic:equal>
						<logic:notEqual value="����ѧԺ" property="xymc" name="rs">Ժ��ϵ��</logic:notEqual>
						<br/>
						���
				</td>
				<td align="left" colspan="5">
					<div align="left">
						<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							������С���Ƽ�����<logic:equal value="����ѧԺ" property="xymc" name="rs">ϵ������</logic:equal>
							<logic:notEqual value="����ѧԺ" property="xymc" name="rs">Ժ��ϵ��</logic:notEqual>������˺�<br/>
							��&nbsp;&nbsp;ͬ������С�������<br/>
							��&nbsp;&nbsp;��ͬ������С�����������Ϊ<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��<br/><br/>
							�������鳤ǩ�֣�
						</span>
					</div>
					<br/><br/><br/>
					<div align="right">
						<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
							��<u>&nbsp;&nbsp;&nbsp;</u>
							��<u>&nbsp;&nbsp;&nbsp;</u>
							��&nbsp;&nbsp;
						</span>
					</div>
					<br/><br/><br/><br/>
				</td>
				<td align="center">
						ѧУѧ<br/>
						������<br/>
						�����<br/>
						�����
				</td>
				<td align="left" colspan="7">
					<div align="left">
						<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						��ѧ������Ժ��ϵ�����룬�����������ʵ��<br/>
						��  ͬ�⹤���������С�������<br/>
						��  ��ͬ�⹤���������С�����������Ϊ��<br/><br/>
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
						<br/><br/>
						������ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>
					</div>
					<br/><br/>
					<div align="right">
						<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
							��<u>&nbsp;&nbsp;&nbsp;</u>
							��<u>&nbsp;&nbsp;&nbsp;</u>
							��&nbsp;&nbsp;
						</span>
					</div>
					<br/><br/>
					<div align="right">
						<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							���Ӹǲ��Ź��£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>
					</div>
				</td>
			</tr>
		</table>
		<br/>
		<div align="left">
			<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
			��ע���˱�һʽ2�ݣ�һ��<logic:equal value="����ѧԺ" property="xymc" name="rs">ϵ������</logic:equal><logic:notEqual value="����ѧԺ" property="xymc" name="rs"><bean:message key="lable.xb" /></logic:notEqual>���棬һ��ѧУ���棨�ɸ�ӡ����
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
