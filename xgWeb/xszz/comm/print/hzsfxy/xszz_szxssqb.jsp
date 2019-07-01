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
			.noPrin{display:none;};
			.PageNext{
			page-break-after: always;
		}
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
		<table width="83%" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
				<br/><br/><br/><br/><br/><br/>
						<logic:equal value="��ᰮ����ѧ" property="xmmc" name="rs"><b><span style='font-size:20pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>�� �� ʦ �� ѧ Ժ �� �� �� �� �� �� ѧ ��<br/>�� �� ѧ �� �� �� ��</span></b></logic:equal>
						<logic:equal value="�����ظ���ѧ��" property="xmmc" name="rs"><b><span style='font-size:16pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>���� �� �� �� �� ѧ �� ���� �� ѧ �� �� �� ��</span></b></logic:equal>
					<br/><br/><br/>
				</td>
			</tr>
			<tr>
				<td align="center">
				<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
					<b>��������<logic:equal value="��������" property="sfzcsq" name="rs"><img src="/xgxt/pictures/xszz/gou.jpg"></img></logic:equal><logic:notEqual value="��������" property="sfzcsq" name="rs">��</logic:notEqual></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<b>�ٴ�����<logic:equal value="�ٴ�����" property="sfzcsq" name="rs"><img src="/xgxt/pictures/xszz/gou.jpg"></img></logic:equal><logic:notEqual value="�ٴ�����" property="sfzcsq" name="rs">��</logic:notEqual></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<b>���<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></b><br/>
				</span>
		<!-- �������� -->
		<table width="100%" id="rsT" class="printstyle">
			<tr>
				<td width="8%"></td>
				<td width="16%"></td>
				<td width="6%"></td>
				<td width="4%"></td>
				<td width="8%"></td>
				<td width="7%"></td>
				<td width="2%"></td>
				<td width="5%"></td>
				<td width="16%"></td>
			</tr>
			<!-- ��һ�� -->
			<tr style="height:21px">
				<td align="center" colspan="9">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							<b>��&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;��</b>
					</span>
				</td>
			</tr>
			<!-- �ڶ��� -->
			<tr style="height:21px">
				<td align="center">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						��&nbsp;&nbsp;&nbsp;&nbsp;��
					</span>
				</td>
				<td  align="center">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.xm }
					</span>
				</td>
				<td align="center">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						�Ա�
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.xb }
					</span>
				</td>
				<td align="center">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<bean:message key="lable.xb" />
					</span>
				</td>
				<td align="center" colspan="3">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.xymc }
					</span>
				</td>
			</tr>
			<!-- ������ -->
			<tr style="height:21px">
				<td align="center">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						�༶ѧ��
					</span>
				</td>
				<td  align="center">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.xh }
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						�������
					</span>
				</td>
				<td align="center" colspan="5">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.yhzzqk }
					</span>
				</td>
			</tr>
			<!-- ������ -->
			<tr style="height:21px">
				<td align="center">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						ƶ���̶�
					</span>
				</td>
				<td  align="center">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.knjb }
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						��ϵ�绰
					</span>
				</td>
				<td align="center" colspan="3">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.sjhm }
					</span>
				</td>
				<td align="center">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						����
					</span>
				</td>
				<td  align="center">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.mzmc }
					</span>
				</td>
			</tr>
		</table>
				</td>
			</tr>
			<tr style="height:21px">
				<td align="center">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td align="center">
		<!-- ��ͥ���� -->
		<table width="100%" id="rsT" class="printstyle">
			<tr>
				<td width="8%"></td>
				<td width="6%"></td>
				<td width="20%"></td>
				<td width="8%"></td>
				<td width="8%"></td>
				<td width="3%"></td>
				<td width="8%"></td>
				<td width="15%"></td>
			</tr>
			<!-- ��ͥ���ϵ�һ�� -->
			<tr style="height:21px">
				<td align="center" colspan="8">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							<b>��&nbsp;&nbsp;ͥ&nbsp;&nbsp;��&nbsp;&nbsp;��</b>
					</span>
				</td>
			</tr>
			<!-- ��ͥ���ϵڶ��� -->
			<tr style="height:21px">
				<td align="center">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							ԭ&nbsp;��
					</span>
				</td>
				<td align="left" colspan="7">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							${rs.jg }
					</span>
				</td>
			</tr>
			<!-- ��ͥ���ϵ����� -->
			<tr style="height:21px">
				<td align="center">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<logic:equal value="��ᰮ����ѧ" property="xmmc" name="rs">��&nbsp;ַ</logic:equal>
						<logic:equal value="�����ظ���ѧ��" property="xmmc" name="rs">ͨѶ��ַ</logic:equal>
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							${rs.jtdz }
					</span>
				</td>
				<td align="center">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							�ʱ�
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							${rs.jtyb }
					</span>
				</td>
				<td align="center">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							�绰
					</span>
				</td>
				<td align="center">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							${rs.jtdh }
					</span>
				</td>
			</tr>
			<!-- ��ͥ���ϵ����� -->
			<tr style="height:21px">
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							�Ƿ�ƶ����
					</span>
				</td>
				<td align="center" colspan="6">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<logic:present name="rs" property="sfpkx">
							<logic:equal value="��" property="sfpkx" name="rs">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									<img src="/xgxt/pictures/xszz/gou.jpg"></img>��&nbsp;&nbsp;&nbsp;����&nbsp;&nbsp;&nbsp;�������Ҽ�ƶ����&nbsp;&nbsp;&nbsp;��ʡ��ƶ���أ�
								</span>
							</logic:equal>
							<logic:equal value="��" property="sfpkx" name="rs">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									����&nbsp;&nbsp;&nbsp;<img src="/xgxt/pictures/xszz/gou.jpg"></img>��&nbsp;&nbsp;&nbsp;
									
										<logic:present name="rs" property="pkxjb">
										<logic:equal value="���Ҽ�ƶ����" property="pkxjb" name="rs">
											<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
												��<img src="/xgxt/pictures/xszz/gou.jpg"></img>���Ҽ�ƶ����&nbsp;&nbsp;&nbsp;��ʡ��ƶ���أ�
											</span>
										</logic:equal>
										<logic:equal value="ʡ��ƶ����" property="pkxjb" name="rs">
											<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
												�������Ҽ�ƶ����&nbsp;&nbsp;&nbsp;<img src="/xgxt/pictures/xszz/gou.jpg"></img>ʡ��ƶ���أ�
											</span>
										</logic:equal>
										<logic:equal value="" property="pkxjb" name="rs">
											<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
												�������Ҽ�ƶ����&nbsp;&nbsp;&nbsp;��ʡ��ƶ���أ�
											</span>
										</logic:equal>
									</logic:present>
									<logic:notPresent name="rs" property="pkxjb">
										<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
											�������Ҽ�ƶ����&nbsp;&nbsp;&nbsp;��ʡ��ƶ���أ�
										</span>
									</logic:notPresent>
									
									
								</span>
							</logic:equal>
							<logic:equal value="" property="sfpkx" name="rs">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									����&nbsp;&nbsp;&nbsp;����&nbsp;&nbsp;&nbsp;�������Ҽ�ƶ����&nbsp;&nbsp;&nbsp;��ʡ��ƶ���أ�
								</span>
							</logic:equal>
						</logic:present>
						<logic:notPresent name="rs" property="sfpkx">
							<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								����&nbsp;&nbsp;&nbsp;����&nbsp;&nbsp;&nbsp;�������Ҽ�ƶ����&nbsp;&nbsp;&nbsp;��ʡ��ƶ���أ�
							</span>
						</logic:notPresent>
						
					</span>
				</td>
			</tr>
			<!-- ��ͥ���ϵ����� -->
			<tr style="height:21px">
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							�����ͥ����
					</span>
				</td>
				<td align="center">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							${rs.snjtsr }
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							��ͥ��Ա��
					</span>
				</td>
				<td align="center" colspan="3">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							${rs.jtrs }
					</span>
				</td>
			</tr>
			<!-- ��ͥ���ϵ����� -->
			<tr style="height:21px">
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							��&nbsp;��&nbsp;ְ&nbsp;ҵ
					</span>
				</td>
				<td align="center">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<logic:iterate name="cyList" id="cy">
							<logic:equal value="����" name="cy" property="mc">
								${cy.cyzy }
							</logic:equal>
						</logic:iterate>
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							ĸ��ְҵ
					</span>
				</td>
				<td align="center" colspan="3">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<logic:iterate name="cyList" id="cy">
								<logic:equal value="ĸ��" name="cy" property="mc">
									${cy.cyzy }
								</logic:equal>
						</logic:iterate>
					</span>
				</td>
			</tr>
			<!-- ��ͥ���ϵ����� -->
			<tr style="height:45px">
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							�����Ƿ���Ƿծ
					</span>
				</td>
				<td align="center">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							<logic:present name="rs" property="sfqz">
								<logic:equal value="��" property="sfqz" name="rs">
									<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										��<img src="/xgxt/pictures/xszz/gou.jpg"></img>&nbsp;&nbsp;/&nbsp;&nbsp;�ޡ�
									</span>
								</logic:equal>
								<logic:equal value="��" property="sfqz" name="rs">
									<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										�С�&nbsp;&nbsp;/&nbsp;&nbsp;��<img src="/xgxt/pictures/xszz/gou.jpg"></img>
									</span>
								</logic:equal>
								<logic:equal value="" property="sfqz" name="rs">
									<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										�С�&nbsp;&nbsp;/&nbsp;&nbsp;�ޡ�
									</span>
								</logic:equal>
							</logic:present>
							<logic:notPresent name="rs" property="sfqz">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									�С�&nbsp;&nbsp;/&nbsp;&nbsp;�ޡ�
								</span>
							</logic:notPresent>
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							��ĸ�Ƿ��в�<br/>��м�
					</span>
				</td>
				<td align="center" colspan="3">
					<logic:present name="rs" property="fmjk">
						<logic:equal value="������" property="fmjk" name="rs">
							<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								������<img src="/xgxt/pictures/xszz/gou.jpg"></img>&nbsp;&nbsp;/ĸ���С�<br/>/�ޡ�
							</span>
						</logic:equal>
						<logic:equal value="ĸ����" property="fmjk" name="rs">
							<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								�����С�&nbsp;&nbsp;/ĸ����<img src="/xgxt/pictures/xszz/gou.jpg"></img><br/>/�ޡ�
							</span>
						</logic:equal>
						<logic:equal value="��ĸ����" property="fmjk" name="rs">
							<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								������<img src="/xgxt/pictures/xszz/gou.jpg"></img>&nbsp;&nbsp;/ĸ����<img src="/xgxt/pictures/xszz/gou.jpg"></img><br/>/�ޡ�
							</span>
						</logic:equal>
						<logic:equal value="��" property="fmjk" name="rs">
							<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								�����С�&nbsp;&nbsp;/ĸ���С�<br/>/��<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							</span>
						</logic:equal>
						<logic:equal value="" property="fmjk" name="rs">
							<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								�����С�&nbsp;&nbsp;/ĸ���С�<br/>/�ޡ�
							</span>
						</logic:equal>
					</logic:present>
					<logic:notPresent name="rs" property="fmjk">
						<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							�����С�&nbsp;&nbsp;/ĸ���С�<br/>/�ޡ�
						</span>
					</logic:notPresent>
					
				</td>
			</tr>
			<!-- ��ͥ���ϵڰ��� -->
			<tr style="height:21px">
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							��ĸ�Ƿ���
					</span>
				</td>
				<td align="left" colspan="6">
					<logic:present name="rs" property="fmjz">
						<logic:equal value="��ĸ˫ȫ" property="fmjz" name="rs">
							<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ĸ˫ȫ<img src="/xgxt/pictures/xszz/gou.jpg"></img>&nbsp;&nbsp;/&nbsp;&nbsp;��ĸ˫����&nbsp;&nbsp;/&nbsp;&nbsp;����ĸ�ڡ�&nbsp;&nbsp;/&nbsp;&nbsp;����ĸ����
							</span>
						</logic:equal>
						<logic:equal value="��ĸ˫��" property="fmjz" name="rs">
							<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ĸ˫ȫ��&nbsp;&nbsp;/&nbsp;&nbsp;��ĸ˫��<img src="/xgxt/pictures/xszz/gou.jpg"></img>&nbsp;&nbsp;/&nbsp;&nbsp;����ĸ�ڡ�&nbsp;&nbsp;/&nbsp;&nbsp;����ĸ����
							</span>
						</logic:equal>
						<logic:equal value="��ĸ˫��" property="fmjz" name="rs">
							<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ĸ˫ȫ��&nbsp;&nbsp;/&nbsp;&nbsp;��ĸ˫����&nbsp;&nbsp;/&nbsp;&nbsp;����ĸ��<img src="/xgxt/pictures/xszz/gou.jpg"></img>&nbsp;&nbsp;/&nbsp;&nbsp;����ĸ����
							</span>
						</logic:equal>
						<logic:equal value="����ĸ��" property="fmjz" name="rs">
							<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ĸ˫ȫ��&nbsp;&nbsp;/&nbsp;&nbsp;��ĸ˫����&nbsp;&nbsp;/&nbsp;&nbsp;����ĸ�ڡ�&nbsp;&nbsp;/&nbsp;&nbsp;����ĸ��<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							</span>
						</logic:equal>
						<logic:equal value="" property="fmjz" name="rs">
							<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ĸ˫ȫ��&nbsp;&nbsp;/&nbsp;&nbsp;��ĸ˫����&nbsp;&nbsp;/&nbsp;&nbsp;����ĸ�ڡ�&nbsp;&nbsp;/&nbsp;&nbsp;����ĸ����
							</span>
						</logic:equal>
					</logic:present>
					<logic:notPresent name="rs" property="fmjz">
						<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							��ĸ˫ȫ��&nbsp;&nbsp;/&nbsp;&nbsp;��ĸ˫����&nbsp;&nbsp;/&nbsp;&nbsp;����ĸ�ڡ�&nbsp;&nbsp;/&nbsp;&nbsp;����ĸ����
						</span>
					</logic:notPresent>
				</td>
			</tr>
		</table>
				</td>
			</tr>
			<tr style="height:10px">
				<td align="center">
				</td>
			</tr>
			<tr>
				<td align="center">
				<logic:equal value="��ᰮ����ѧ" property="xmmc" name="rs">
		<table width="100%" class="printstyle">
						<tr height="21px" align="center">
							<td>
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>��&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;��</b>
								</span>
							</td>
						</tr>
						<tr height="280px" align="center">
							<td>
								<p style="height:270px" align="left">
									<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
									</span>
								</p>
								<div align="right">
									<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									����ǩ��<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u><br/><br/>
									��&nbsp;&nbsp;&nbsp;&nbsp;��<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
									</span>
								</div>
							</td>
						</tr>
					</table>
					</logic:equal>
					<logic:equal value="�����ظ���ѧ��" property="xmmc" name="rs">
					<table width="100%" class="printstyle">
						<tr height="21px" align="center">
							<td>
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>��&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;��</b>
								</span>
							</td>
						</tr>
						<tr height="200px" align="center">
							<td>
								<p style="height:200px" align="left">
									<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
									</span>
								</p>
							</td>
						</tr>
						<tr height="80px" align="left">
							<td>
								<p style="height:70px">
									<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									�����˳�ŵ��<br/>
									1.�����ṩ������׼ȷ����ʵ��<br/>
									2.���������󽫿̿����С�Ŭ��ѧϰ��<br/>
									3.����Ը����밮�ķ����Ŷӣ��������μӹ�������ʵ�����<br/>
									</span>
								</p>
								<div align="right">
									<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									����ǩ��<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u><br/><br/>
									��&nbsp;&nbsp;&nbsp;&nbsp;��<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
									</span>
								</div>
							</td>
						</tr>
					</table>
					</logic:equal>
				</td>
			</tr>
	</table>
		
		
		<!--��ҳ-->
		<div class="PageNext"><br/></div>
		<br/>
		<table width="83%" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
				<br/><br/><br/><br/><br/>
					<table width="100%" class="printstyle">
						<tr height="21px" align="center">
							<td>
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>ѧ&nbsp;&nbsp;Ժ&nbsp;&nbsp;��&nbsp;&nbsp;��</b>
								</span>
							</td>
						</tr>
						<tr height="220px" align="center">
							<td>
								<p style="height:210px" align="left">
									<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyshyj }
									</span>
								</p>
								<div align="right">
									<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									<bean:message key="lable.xb" />�����£�<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
									<br/><br/>
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
									</span>
								</div>
								<div align="left">
									<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;ע����ע�������ļ�ͥ�������������༰��У��Ʒѧ����
									</span>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr style="height:21px">
				<td align="center">
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="printstyle">
						<tr height="21px" align="center">
							<td>
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>ѧ&nbsp;&nbsp;У&nbsp;&nbsp;��&nbsp;&nbsp;��</b>
								</span>
							</td>
						</tr>
						<tr height="160px" align="center">
							<td>
								<p style="height:150px" align="left">
									<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxshyj }
									</span>
								</p>
								<div align="right">
									<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									ѧУ�����£�<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
									<br/><br/>
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
									</span>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr style="height:35px">
				<td align="center">
				</td>
			</tr>
			<tr>
				<td align="center">
					<div align="left">
						<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						&nbsp;&nbsp;&nbsp;&nbsp;��������������д
						</span>
					</div>
					<table width="100%" class="printstyle">
						<tr height="21px" align="center">
							<td>
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>��&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;��</b>
								</span>
							</td>
						</tr>
						<tr height="170px" align="center">
							<td>
								<p style="height:160px">
									<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									
									</span>
								</p>
								<div align="right">
									<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									�����ǩ��<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
									<br/><br/>
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
									</span>
								</div>
							</td>
						</tr>
					</table>
					<div align="left">
						<br/>
						<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<b>��ע��</b>�˱�һʽ���ݣ�һ��ѧУ���棬һ�����������棨���������ӡ����
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
