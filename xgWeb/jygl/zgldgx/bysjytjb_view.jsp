<%@ page language="java" contentType="text/html; charset=GBK"%>

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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript"> 
	function expTabdy(the_table, tabTit, titSpan) {
		/*var HKEY_Root="HKEY_CURRENT_USER";
		var HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
		var Wsh=new ActiveXObject("WScript.Shell");
		var HKEY_Key="header";
		    Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
		    HKEY_Key="footer";
		    Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,""); */
		var table_title = (titSpan == null || titSpan == "") ? tabTit
				: document.getElementById(titSpan).outerHTML;
		var the_content = "<style media='print'>\n";
		the_content += ".noPrin{\n";
		the_content += "display:none;}\n";
		the_content += "</style>\n";
		the_content += "<link rel='stylesheet' rev='stylesheet' href='skin1/style/main.css' type='text/css' media='all' />\n";
		the_content += "<object id=\"WebBrowser\" width=0 height=0 classid=\"CLSID:8856F961-340A-11D0-A96B-00C04FD705A2\"></object>\n";
		the_content += "<center><h3><b>";
		the_content += table_title;
		the_content += "</b></h3>";
		the_content += document.all(the_table).outerHTML;
    	//the_content = the_content.replace(/ style=\"[^\"]*\"/g, "");
		the_content = the_content.replace(/ onclick=\"[^\"]*\"/g, "");
		the_content = the_content.replace(/ mode=\"(false|true)"/g, "");
		the_content = the_content.replace(/ oBgc=\"[\w#\d]*\"/g, "");
		the_content = the_content.replace(/ oFc=\"[\w#\d]*\"/g, "");
		the_content = the_content.replace(/<span>(5|6)<\/span>/gi, "");
		the_content = the_content.replace(
				/<DIV contentEditable=false>(.*)<\/DIV>/ig, "$1");
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
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
<html:form action="/yxjzyjs.do" method="post">
		
		<logic:iterate id="rs1" name="rs1">
			<table width="100%" class="tbstyle" id="grjl">
				<thead>
					<tr>
						<td colspan="8" align="center">
							2009�챱��������ͨ��У��ҵ����ҵ�Ƽ���
						</td>
					</tr>
				</thead>
				<tr>
					<td rowspan="7" align="center" >
						<b>��<br>��<br>��<br>Ϣ</b>
					</td>
					<td align="center" style="width: 85px" nowrap="nowrap">
						ѧ��
					</td>
					<td>
						<bean:write name="rs1" property="xsxh"/>
					</td>
					<td align="center" nowrap="nowrap" style="width: 150px;">
						����
					</td>
					<td style="width: 200px;">
						<bean:write name="rs1" property="name"/>
					</td>
					<td align="center" nowrap="nowrap">
						�Ա�
					</td>
					<td style="width: 150px;">
						<bean:write name="rs1" property="xb" />
					</td>
					<td rowspan="6" align="center">
						<img border="0" style="height:133px;width:100px;"
								src="/xgxt<bean:write name="rs1" property="sczp" />">
					</td>
				</tr>
				<tr>
					<td align="center" nowrap="nowrap">
						���֤��
					</td>
					<td>
						<bean:write name="rs1" property="id"/>
					</td>
					<td align="center" nowrap="nowrap">
						��������
					</td>
					<td nowrap="nowrap">
						<bean:write name="rs1" property="csrq"/>
					</td>
					<td align="center" nowrap="nowrap">
						������Ŀ
					</td>
					<td>
						<bean:write name="rs1" property="zzmm"/>
					</td>
				</tr>
				<tr>
					<td align="center" nowrap="nowrap">
						��Դ����
					</td>
					<td>
						<bean:write name="rs1" property="sydq"/>
					</td>
					<td align="center" nowrap="nowrap">
						�绰
					</td>
					<td>
						<bean:write name="rs1" property="lxdh"/>
					</td>
					<td align="center" nowrap="nowrap">
						�ֻ�
					</td>
					<td>
						<bean:write name="rs1" property="sjhm"/>
					</td>
				</tr>
				<tr>
					<td align="center" nowrap="nowrap">
						ͨѶ��ַ
					</td>
					<td colspan="3">
						<bean:write name="rs1" property="txdz"/>
					</td>
					<td align="center" nowrap="nowrap">
						�ʱ����
					</td>
					<td >
						<bean:write name="rs1" property="yzbm"/>
					</td>
					
				</tr>
				<tr>
					<td align="center" nowrap="nowrap">
						��ҵѧУ
					</td>
					<td colspan="3">
						<bean:write name="rs1" property="byxx"/>
					</td>
					<td align="center" nowrap="nowrap">
						ѧ��
					</td>
					<td>
						<bean:write name="rs1" property="xlmc"/>
					</td>
					
				</tr>
				<tr>
					<td align="center" >
						רҵ
					</td>
					<td colspan="3">
						<bean:write name="rs1" property="zymc"/>
					</td>
					<td align="center">
						ѧ��
					</td>
					<td >
						<bean:write name="rs1" property="xz"/>
					</td>
					
				</tr>
				<tr>
					<td align="center" >
						��ҵʱ��
					</td>
					<td>
						<bean:write name="rs1" property="bysj"/>
					</td>
					<td align="center">
						����<br>���
					</td>
					<td colspan="4">
						<bean:write name="rs1" property="jlqk"/>
					</td>
				</tr>
				<tr>
					<td rowspan="3" align="center">
						<b>��<br>��<br>ʵ<br>��</b>
					</td>
					<td align="right">
						1��
					</td>
					<td colspan="7">
						<bean:write name="rs1" property="shsj1"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						2��
					</td>
					<td colspan="7">
						<bean:write name="rs1" property="shsj2"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						3��
					</td>
					<td colspan="7">
						<bean:write name="rs1" property="shsj3"/>
					</td>
				</tr>
				<tr>
					<td rowspan="3" align="center">
						<b>��<br>��<br>��<br>��<br>��</b>
					</td>
					<td align="center" colspan="3">
						1��������������
					</td>
					<td >
						<bean:write name="rs1" property="wyyz"/>
					</td>
					<td align="center">
						����
					</td>
					<td colspan="3">
						<bean:write name="rs1" property="jb"/>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="3">
						2:�����ˮƽ
					</td>
					<td colspan="4">
						<bean:write name="rs1" property="jsjsp"/>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="3">
						3:�س�������(������У�ڼ䵣��ְ��)
					</td>
					<td colspan="4">
						<bean:write name="rs1" property="tcnl"/>
					</td>
				</tr>
				<tr>
					<td align="center" rowspan="5">
						<b>ѧ<br>У<br>��<br>��<br>��<br>��</b>
					</td>
					<td align="center" rowspan="3" colspan="2">
						ϵ��Ժ�������
					</td>
					<td align="center">
						��ҵ��<br>������ʽ
					</td>
					<td colspan="6">
						<bean:write name="rs1" property="pyfs"/>
					</td>
				</tr>
				<tr>
				<td align="center">
						��ҵ��Χ
					</td>
					<td colspan="6">
						<bean:write name="rs1" property="jyfw"/>
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<bean:write name="rs1" property="yxyj"/>
					</td>
				</tr>
				<tr>
					
					<td align="center" rowspan="1" colspan="2">
						ѧУ��ҵ<br>����ҵ��<br>�����
					</td>
					<td colspan="5">
						<bean:write name="rs1" property="jybmyj"/>
					</td>
				</tr>
				<tr>
					<td align="center">
						��ϵ����
					</td>
					<td >
						<bean:write name="rs1" property="lxbm"/>
					</td>
					<td align="center">
						��ϵ��
					</td>
					<td >
						<bean:write name="rs1" property="bmlxr"/>
					</td>
					<td align="center">
						��ϵ�绰
					</td>
					<td colspan="4">
						<bean:write name="rs1" property="bmlxdh"/>
					</td>
				</tr>
				<tr>
					<td align="center">
						<b>��<br>ע</b>
					</td>
					<td align="center" colspan="7">
					<bean:write name="rs1" property="bz"/>
					</td>
				</tr>
			</table>
			<div align="center">
				<button class="button2"  onclick="expTabdy('grjl','','');" style="width: 100px">
					��ӡ�б�
				</button>
				&nbsp;&nbsp;
				<button class="button2"  onclick="Close();return false;" style="width: 100px">
					�ر�
				</button>
			</div>
		</logic:iterate>
		</html:form>
	</body>
</html>
