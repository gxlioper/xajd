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
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/xszzFunction.js"></script>
<script language="javascript">
function expTabT(the_table1, the_table2, the_table3, the_table4,tabTit, titSpan) {
	 
	var table_title = (titSpan == null || titSpan == "") ? tabTit : document.getElementById(titSpan).outerHTML;	
	var the_content = "<style media='print'>\n";
	the_content += ".noPrin{\n";
	the_content += "display:none;}\n";
	the_content += "</style>\n";
	the_content += "<link rel='stylesheet' rev='stylesheet' href='skin1/style/main.css' type='text/css' media='all' />\n";
	the_content += "<object id=\"WebBrowser\" width=0 height=0 classid=\"CLSID:8856F961-340A-11D0-A96B-00C04FD705A2\"></object>\n";
	the_content += "<script language='javascript' src='js/function.js'>";
	the_content += "</sc";
	the_content += "ript>\n";
	the_content += "<center><h3><b>";
	the_content += table_title;
	the_content += "</b></h3>";		
	the_content += document.all(the_table1).outerHTML;	
	the_content += "<div style='page-break-before:always;'>&nbsp;</div>";
	the_content += document.all(the_table2).outerHTML;	
	the_content += "<div style='page-break-before:always;'>&nbsp;</div>";
	the_content += document.all(the_table3).outerHTML;	
	the_content += "<div style='page-break-before:always;'>&nbsp;</div>";
	the_content += document.all(the_table4).outerHTML;	
	the_content = the_content.replace(/ style=\"[^\"]*\"/g, "");
	the_content = the_content.replace(/ onclick=\"[^\"]*\"/g, "");
	the_content = the_content.replace(/ mode=\"(false|true)"/g, "");
	the_content = the_content.replace(/ oBgc=\"[\w#\d]*\"/g, "");
	the_content = the_content.replace(/ oFc=\"[\w#\d]*\"/g, "");
	the_content = the_content.replace(/<span>(5|6)<\/span>/gi, "");
	the_content = the_content.replace(/<DIV contentEditable=false>(.*)<\/DIV>/ig, "$1");
	the_content += "\n<br><div class='noPrin'><input type='button' class='button2' value='ҳ������' onclick=\"WebBrowser.ExecWB(8,1)\">";
	the_content += "<input type='button' class='button2' value='��ӡԤ��' onclick=\"WebBrowser.ExecWB(7,1)\">";
	the_content += "<input type='button' class='button2' value='ֱ�Ӵ�ӡ' onclick=\"WebBrowser.ExecWB(6,6)\">";
	the_content += "<\/div>";
	//confirm(the_content);
	var newwin = window.open("about:blank", "_blank", "");
	newwin.document.open();
	newwin.document.write(the_content);
	newwin.document.close();
	newwin = null;
}

   function jd(){
		if($("jd")){
			$("jd").focus();
		}
	}
	</script>
	<body onload="jd()">
		<center>			
			<br><html:form action="/csmz_sztz.do?method=tzxm_tj" method="post">		
					<!-- ��ȡ���� Ϊ����ΰ����� -->		
					<input type="text" id="jd" name="jd"  
						style="text-align:left;ime-mode:disabled;width:100%;border:0;background-color: white" readonly="readonly"	/>								
					
					<table width="100%" border="0" id="theTable1" class="">
					
						<tr>
							<td>
								<div align="center">
									<h1>
										<strong> ${xxmc}
											<br /> <br /> ��ѧ��������չ�ƻ� <br /><br />  ��Ŀ������ <br /><br /></strong>
									</h1>
								</div>
							</td>
						<tr>
							<td>
								<table width="100%">
									<tr height="50px">
										<td align="right">

											<font size="5" > �� Ŀ �� �� �� �ţ�</font>
										</td>
										<td>
											<u>&nbsp;&nbsp;<font size="3"><bean:write
														name="sqbm" scope="request" />
											</font> &nbsp;&nbsp;</u>

										</td>
									</tr>
									<tr  height="50px">
										<td align="right">

											<font size="5">�� Ŀ �� �� ѧ �ڣ�</font>
										</td>
										<td>
											<u>&nbsp;&nbsp;<font size="3"><bean:write
														name="xn" scope="request" />ѧ��&nbsp;�� <bean:write
														name="xq" scope="request" /> ѧ�� &nbsp;&nbsp; </font>
											</u>

										</td>
									</tr>
									<tr  height="50px">
										<td align="right">
											<font size="5">�� Ŀ �� �� �� �ڣ�</font>
										</td>
										<td>
											<u>&nbsp;&nbsp;<font size="3"><bean:write
														name="sqrq" scope="request" />
											</font> &nbsp;&nbsp;</u>

										</td>
									</tr>
									<tr  height="50px">
										<td align="right">
											<font size="5">�� Ŀ �� �� �� ��</font>
										<td>
										<td>
										</td>
									</tr>
									<tr >
										<td align="right">
											<font size="3">˼���������������&nbsp;&nbsp;</font>
										</td>
										<td align="left">
											<font size="3">���ʵ����־Ը����</font>
										</td>
									</tr>
									<tr>
										<td align="right">
											<font size="3">�Ƽ�ѧ���봴�´�ҵ&nbsp;&nbsp;</font>
										</td>
										<td align="left">
											<font size="3">�Ļ����������ķ�չ</font>
										</td>
									</tr>
									<tr>
										<td align="right">
											<font size="3">���Ż����Ṥ��&nbsp;&nbsp;</font>
										</td>
										<td align="left">
											<font size="3">������ѵ������</font>
										</td>
									</tr>
								</table>
						</td>
						</tr>		
						<tr>
						<td align="center">
							<br>
							<br>
							<br>
							<br>
							<font size="3"><bean:write name="xxmc" scope="request" />
							��ѧ��������չ��֤������</font>
							<br>
							<br>
						<div align="center"><font size="3">��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</font></div>
						</td>
					</tr>
					</table>
														
					<table width="100%" border="0" id="theTable2">
							<tr>
								<td>
									
									<div align="center">
										<font size="3"><strong>�� �� ˵ ��</strong> </font>
									</div>
									<br>
									<br>
									<p align="left">
										<font size="3">һ�� ����������Ŀ���밴�涨��ʽ��ӡ��</font>
									</p>
									<p align="left">
										<font size="3">���� ��Ŀ���벿����дϵ��ȫ�ƣ�</font>
									</p>
									<p align="left">
										<font size="3">���� ��Ŀ���Ʋ�����14���֣����������ţ������ʱ�䡱��д����е�ʱ�䣻</font>
									</p>
									<p align="left">
										<font size="3">�ġ� �밴��Ŀ��չ�����ڽ���Ŀ˳�����У���ͳ��ÿ�����Ļ������</font>
									</p>
									<p align="left">
										<font size="3">�塢
											����һʽ���ݣ���˫���ӡ��ϵ������һ�ݣ��������ݱ���<bean:message key="lable.xsgzyxpzxy" />��ѧ��������չ��֤���İ칫�ң�</font>
									</p>
									<p align="left">
										<font size="3">����
											ϵ��������չ��Ŀ������ǩ�����뵳��֧�����ǩ�֣�ϵ��������չ�쵼С�鸺����ǩ������ϵ����ǩ�֣�</font>
									</p>
									<p align="left">
										<font size="3">�ߡ� ���������ʵ��д������Ū������ϵ�������Ը���</font>
									</p>
									<br>
									<br>
									<br>
									<br>
								</td>
							</tr>							
						</table>
														
								<table class="printstyle" width="100%" id="theTable3">
									<thead>
										<tr>
											<td>
												���
											</td>
											<td>
												��Ŀ���
											</td>
											<td>
												��Ŀ����
											</td>
											<td>
												�ʱ��
											</td>
											<td>
												��Ŀ����
											</td>
											<td>
												�����
											</td>
										</tr>
									</thead>
									<logic:notEmpty name="rs2">
										<logic:iterate name="rs2" id="s">
											<tr>
												<td>
													<logic:iterate id="v" name="s" offset="0" length="1">
														<bean:write name="v" />
													</logic:iterate>
												</td>
												<logic:iterate id="v" name="s" offset="1">
													<td>
														<bean:write name="v" />
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>										
									</logic:notEmpty>
								</table>
				
							<table width="100%" class="printstyle" id="theTable4">
								<tr>
									<td>
										&nbsp;ϵ��������չ��Ŀ���ܲ�����������
										<br />
										<br />
										<br />
										<br />
										<br />
										<div align="right">
											�����ˣ�ǩ�֣�:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</div>
										<br />
										<div align="right">
											��ϵ��������չ��֤���Ĺ��£�
										</div>
										<br />
										<div align="right">
											<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
											<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>��
											<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>�� &nbsp;&nbsp;
										</div>
									</td>
								</tr>
								<tr>
									<td>
										&nbsp;ϵ��������չ�쵼С����������
										<br />
										<br />
										<br />
										<br />
										<br />
										<div align="right">
											�����ˣ�ǩ�֣�:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</div>
										<br />
										<div align="right">
											��ϵ��������չ�쵼С�鹫�£�
										</div>
										<br />
										<div align="right">
											<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
											<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>��
											<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>�� &nbsp;&nbsp;
										</div>

									</td>
								</tr>
								<tr>
									<td>
										&nbsp;<bean:message key="lable.xsgzyxpzxy" />������չ��Ŀ���ܲ�����������
										<br />
										<br />
										<br />
										<br />
										<br />
										<div align="right">
											�����ˣ�ǩ�֣�:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</div>
										<br />
										<div align="right">
											��<bean:message key="lable.xsgzyxpzxy" />������չ��֤���Ĺ��£�
										</div>
										<br />
										<div align="right">
											<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
											<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>��
											<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>�� &nbsp;&nbsp;
										</div>

									</td>
								</tr>
								<tr>
									<td>
										&nbsp;<bean:message key="lable.xsgzyxpzxy" />������չ�쵼С��������:
										<br />
										<br />
										<br />
										<br />
										<br />
										<div align="right">
											�����ˣ�ǩ�֣�:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</div>
										<br />
										<div align="right">
											��<bean:message key="lable.xsgzyxpzxy" />������չ�쵼С�鹫�£�
										</div>
										<br />
										<div align="right">
											<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
											<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>��
											<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>�� &nbsp;&nbsp;
										</div>

									</td>
								</tr>
							</table>
												
				<div class="buttontool" align="center">
						<button class="btn_dy" name="button_print" style="width:80px"
				onClick="expTabT('theTable1','theTable2','theTable3','theTable4','')">
				�� ӡ
			</button>														
							
			</html:form>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>				
	</body>
</html>
