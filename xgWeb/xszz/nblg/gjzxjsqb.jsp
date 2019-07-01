<%@ page language="java" contentType="text/html;charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<base target="_self">
<head>
	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="Pragma" http-equiv="no-cache" />
	<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
	<meta http-equiv="Expires" http-equiv="0" />
	<meta name="Copyright" content="������� zfsoft" />
</head>
<%
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/xszzFunction.js"></script>
<script language="javascript">
		function back(){
			document.forms[0].action = "/xgxt/nblg_xszz.do?method=gjzxjsq";
			document.forms[0].submit();
		}
		
function expTabT(the_table, the_table2, tabTit, titSpan) {	
	/*var HKEY_Root="HKEY_CURRENT_USER";
	var HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
	var Wsh=new ActiveXObject("WScript.Shell");
	var HKEY_Key="header";
	    Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
	    HKEY_Key="footer";
	    Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,""); */  
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
	the_content += document.all(the_table).outerHTML;	
	the_content += "<div style='page-break-before:always;'>&nbsp;</div>";
	the_content += document.all(the_table2).outerHTML;	
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
	</script>
<body>
	<html:form action="nblg_xszz.do?method=gjzxjsqb" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
							<h3>
						<strong>
								������ѧ�������
						</strong>
							</h3>
					</div>
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="12%">
								<div align="center">
									����
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name="rs" property="xm" />
								</div>
							</td>
							<td width="12%">
								<div align="center">
									ѧ��
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name="rs" property="xh" />
								</div>
							</td>
							<td width="12%">
								<div align="center">
									�Ա�
								</div>
							</td>
							<td width="8%">
								<div align="center">
									<bean:write name="rs" property="xb" />
								</div>
							</td>
							<td width="12%">
								<div align="center">
									����
								</div>
							</td>
							<td width="14%">
								<div align="center">
									<bean:write name="rs" property="mzmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��������
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="csny" />
								</div>
							</td>
							<td>
								<div align="center">
									������ò
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="zzmmmc" />
								</div>
							</td>
							<td>
								<div align="center">
									���֤����
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="sfzh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��Ժ
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="xymc" />
								</div>
							</td>
							<td>
								<div align="center">
									רҵ�༶
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name="rs" property="zymc" />
									&nbsp;
									<bean:write name="rs" property="bjmc" />
								</div>
							</td>
						</tr>
					</table>
					<table width="100%" class="tbstyle">
						<tr>
							<td colspan="2">
								<div align="center">
									��ѧ������
									<br />
									Υ�ʹ���
								</div>
							</td>
							<td width="12%">
								<div align="center">
									<bean:write name="rs" property="sxnywwjcf" />
								</div>
							</td>
							<td width="14%">
								<div align="center">
									ѧϰ���(ѧϰ
									<br />
									̬�ȡ��ɼ�)
								</div>
							</td>
							<td colspan="3">
								<bean:write name="rs" property="xxqk" />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									������ֽ���
									<br />
									���������
								</div>
							</td>
							<td colspan="5">
								<bean:write name="rs" property="chhzjlhzzqk" />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									���������ѧ��
									<br />
									��𣨴򡰡̡���
								</div>
							</td>
							<td colspan="5">
								<div align="center">
									<logic:empty name="rs" property="xh">
										��&nbsp;A ����һ����ͥ�����ر����ѵ�&nbsp;&nbsp;��&nbsp;B ���ڶ�����ͥ�������ѵ�
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<logic:equal name="rs" property="sqdj" value="һ��">
											��&nbsp;A ����һ����ͥ�����ر����ѵ�&nbsp;&nbsp;��&nbsp;B ���ڶ�����ͥ�������ѵ�
										</logic:equal>
										<logic:equal name="rs" property="sqdj" value="����">
											��&nbsp;A ����һ����ͥ�����ر����ѵ�&nbsp;&nbsp;��&nbsp;B ���ڶ�����ͥ�������ѵ�
										</logic:equal>
									</logic:notEmpty>
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="3" width="4%">
								<div align="center">
									��
									<br />
									ͥ
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									��
								</div>
							</td>
							<td width="20%">
								<div align="center">
									��ͥ���ں�
									<br />
									��ͥ����
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<logic:empty name="rs" property="xh">
										����&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;ũ��&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;˫��&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;����&nbsp;��
										<br />
										�¶�&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;����&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;����&nbsp;��&nbsp;&nbsp;&nbsp;(��"��")
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<logic:equal name="rs" property="jthk" value="����">
											����&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;ũ��&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:equal>
										<logic:equal name="rs" property="jthk" value="ũ��">
											����&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;ũ��&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:equal>
										<logic:equal name="rs" property="jtlx" value="˫��">
											˫��&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;����&nbsp;��
											<br />
											�¶�&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:equal>
										<logic:equal name="rs" property="jtlx" value="����">
											˫��&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;����&nbsp;��
											<br />
											�¶�&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:equal>
										<logic:equal name="rs" property="jtlx" value="�¶�">
											˫��&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;����&nbsp;��
											<br />
											�¶�&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:equal>
										<logic:equal name="rs" property="sfly" value="��">
											����&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:equal>
										<logic:equal name="rs" property="sfly" value="��">
											����&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:equal>
										<logic:equal name="rs" property="sfls" value="��">
											����&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:equal>
										<logic:equal name="rs" property="sfls" value="��">
											����&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:equal>
										(��"��")
									</logic:notEmpty>
								</div>
							</td>
							<td width="14%">
								<div align="center">
									��ͥ���˿���
								</div>
							</td>
							<td width="14%">
								<div align="center">
									<bean:write name="rs" property="jtrkzs" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��ͥ����
									<br />
									��������
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtyzsr" />
								</div>
							</td>
							<td>
								<div align="center">
									��ͥ�˾�
									<br />
									������
								</div>
							</td>
							<td width="22%">
								<div align="center">
									<bean:write name="rs" property="jtrjysr" />
								</div>
							</td>
							<td>
								<div align="center">
									������Դ
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="srly" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��ͥסַ
								</div>
							</td>
							<td colspan="3">
								<bean:write name="rs" property="jtzz" />
							</td>
							<td>
								<div align="center">
									��������
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="yzbm" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="6">
								<div align="center">
									��
									<br />
									ͥ
									<br />
									��
									<br />
									Ա
									<br />
									��
									<br />
									��
								</div>
							</td>
							<td>
								<div align="center">
									����
								</div>
							</td>
							<td>
								<div align="center">
									��ν
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									ְҵ�͹�����ѧϰ����λ
								</div>
							</td>
							<td>
								<div align="center">
									������
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy1_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;
									<bean:write name="rs" property="jtcy1_cw" />
									&nbsp;
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jtcy1_gz" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy1_sr" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy2_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;
									<bean:write name="rs" property="jtcy2_cw" />
									&nbsp;
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jtcy2_gz" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy2_sr" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy3_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;
									<bean:write name="rs" property="jtcy3_cw" />
									&nbsp;
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jtcy3_gz" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy3_sr" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy4_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;
									<bean:write name="rs" property="jtcy4_cw" />
									&nbsp;
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jtcy4_gz" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy4_sr" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy5_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;
									<bean:write name="rs" property="jtcy5_cw" />
									&nbsp;
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jtcy5_gz" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy5_sr" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="7">
								&nbsp;��������(��ҳ)��
								<br />
								&nbsp;
								<div align="right">
									������ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="7">
								&nbsp;&nbsp;&nbsp;&nbsp;������ѧ����������͵ط�������ͬ���������ģ�����������ͥ�������ѵ�ѧ�������˳�ŵ���������ѧ��ֻ����ѧϰ���������֧�����棬������������;����֤����������ǿ���ڷ�ѧϰ��������ӡ�
								<br />
								&nbsp;
								<div align="right">
									��ŵ��ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									�ർʦ��
									<br />
									�����
								</div>
							</td>
							<td colspan="5">
								&nbsp;ѧ����ͥ���á�ѧ��ѧϰ���ճ����ֵ������
								<br />
								<logic:empty name="rs" property="bdsyj">
									<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="bdsyj">
									<bean:write name="rs" property="bdsyj" />
								</logic:notEmpty>
								<br />
								&nbsp;
								<div align="right">
									�ർʦǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									��Ժ��
									<br />
									�����
								</div>
							</td>
							<td colspan="5">
								<br />
								<logic:empty name="rs" property="xyshyj">
									<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="xyshyj">
									<bean:write name="rs" property="xyshyj" />
								</logic:notEmpty>
								<br />
								&nbsp;
								<div align="right">
									������ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									ѧУ��
									<br />
									�����
								</div>
							</td>
							<td colspan="5">
								<br />
								<logic:empty name="rs" property="xxshyj">
									<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="xxshyj">
									<bean:write name="rs" property="xxshyj" />
								</logic:notEmpty>
								<br />
								&nbsp;
								<div align="right">
									������ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div style='page-break-before:always;'>&nbsp;</div>
		<table width="100%" border="0" id="theTable2">
			<tr>
				<td>
					&nbsp;�������ɣ���ҳ����<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="sqly" />
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center">
		<input  value="��ӡ" name="button_print"
			onclick="expTabT('theTable','theTable2','')" />
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input  value="����" onclick="back();" />
	</div>
</body>
</html:html>
