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
<html:html>
<base target="_self" />
<head>


	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="������� zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/GetFdyList.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript">
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/jhzy_rych.do?method=bkzxjjsqb";
			document.forms[0].submit();
		}
		function expTabby(the_table, tabTit, titSpan) {
			/*var HKEY_Root="HKEY_CURRENT_USER";
			var HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
			var Wsh=new ActiveXObject("WScript.Shell");
			var HKEY_Key="header";
			    Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
			    HKEY_Key="footer";
			    Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,""); */ 
			var table_title = (titSpan == null || titSpan == "") ? tabTit : document.getElementById(titSpan).outerHTML;	
			var table_content=((the_table != null || the_table != "")&&$(the_table)) ? document.all(the_table).outerHTML:"δ�ҵ��κμ�¼��" ;
			var the_content = "<style media='print'>\n";
			the_content += ".noPrin{\n";
			the_content += "display:none;}\n";
			the_content += "</style>\n";
			the_content += "<link rel='stylesheet' rev='stylesheet' href='skin1/style/main.css' type='text/css' media='all' />\n";
			the_content += "<object id=\"WebBrowser\" width=0 height=0 classid=\"CLSID:8856F961-340A-11D0-A96B-00C04FD705A2\"></object>\n";
			//the_content += "<script language='javascript' src='js/function.js'>";
			the_content += "</sc";
			the_content += "ript>\n";
			the_content += "<center><h3><b>";
			the_content += table_title;
			the_content += "</b></h3>";	
			the_content += table_content;			
			the_content = the_content.replace(/ onclick=\"[^\"]*\"/g, "");
			the_content = the_content.replace(/ mode=\"(false|true)"/g, "");	
			the_content = the_content.replace(/ oBgc=\"[\w#\d]*\"/g, "");
			the_content = the_content.replace(/ oFc=\"[\w#\d]*\"/g, "");
			the_content = the_content.replace(/<span>(5|6)<\/span>/gi, "");
			the_content = the_content.replace("<BUTTON class=button2 id=buttonFindStu>ѡ��</BUTTON>", "");
			the_content = the_content.replace(/<DIV contentEditable=false>(.*)<\/DIV>/ig, "$1");
			the_content += "\n<br><div class='noPrin'><input type='button' class='button2' value='ҳ������' onclick=\"WebBrowser.ExecWB(8,1)\">";
			the_content += "<input type='button' class='button2' value='��ӡԤ��' onclick=\"WebBrowser.ExecWB(7,1)\">";
			the_content += "<input type='button' class='button2' value='ֱ�Ӵ�ӡ' onclick=\"WebBrowser.ExecWB(6,6)\">";
			the_content += "<\/div>";	
			var newwin = window.open("about:blank", "_blank", "");
			newwin.document.open();
			newwin.document.write(the_content);
			newwin.document.close();
			newwin = null;
		}
	</script>
</head>
<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�˼������ -  �Ƚ��ƺ� - ���������
		</div>
	</div>
	<%--	<logic:equal name="sfksq" value="-1">--%>
	<%--		<center>--%>
	<%--			<p>--%>
	<%--				���ڲ�������ʱ���ڣ�--%>
	<%--			</p>--%>
	<%--		</center>--%>
	<%--	</logic:equal>--%>
	<html:form action="/szdw_zjlg" method="post">
		<input type="hidden" id="xjchdm" name="xjchdm"
			value="<bean:write name = "xjchdm" />" />	
		<input type="hidden" id="act" name="act"
			value="${act}" />
		<input type="hidden" id="pk" name="pk"
			value="<bean:write name = "pk"/>" />
		<logic:present name="ok">
			<logic:match value="ok" name="ok">
				<script language="javascript">
	         	alert("����ɹ���");
	         	</script>
			</logic:match>
			<logic:match value="no" name="ok">
				<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
			</logic:match>
		</logic:present>
		<logic:present name="isPASS">
			<logic:match value="is" name="isPASS">
				<script language="javascript">
	         			alert("��ͨ��ѧУ��ˣ��������룡");
	         		</script>
			</logic:match>
		</logic:present>
		<table class="tbstyle" width="90%" id="theTable">
			<tr>
				<td align="center" width="16%">
						ѧ��
				</td>
				<td width="34%" >
				        <input type = "hidden" name="xn" value = "<bean:write name = "xn" />">
						<bean:write name = "xn" />
				</td>
				<td align="center" width="16%">
						��������:
				</td>
				<logic:notEqual name="userType" value="admin" scope="session">
					<td align="left" width="34%">
						<input type = "hidden" name = "bmdm" value ="<bean:write name = "bmdm" />" >
						<html:select name = "rs" property="bmdm" styleId="bmdm" disabled="true" style="width:50%" >
							<html:options collection="bmList" property="bmdm"
								labelProperty="bmmc" />
						</html:select>
					</td>
				</logic:notEqual>
				<logic:equal name="userType" value="admin" scope="session">
					<td align="left" width="34%">
						<html:select name = "rs" property="bmdm" styleId="bmdm" onchange="getFdyList()" style="width:100%">
							<html:option value=""></html:option>
							<html:options collection="bmList" property="bmdm"
								labelProperty="bmmc" />
						</html:select>
					</td>
				</logic:equal>
			<tr>
				<td align="center" width="16%">
					<font color="red">*</font>����Ա
				</td>
					<td width="34%">
						<html:select  property="zgh"  styleId="zgh" style="width:150px" onchange="refreshForm('szdw_zjlg.do?method=xjchSq')" styleId="zgh">
								<html:option value=""></html:option>
								<html:options collection="fdyList" property="zgh"
											labelProperty="xm" />
								</html:select>
						<input type="hidden" name="zghV" value="<bean:write name="rs" property="zgh" scope="request"/>"/>
					</td>
				<td>
					<div align="center">
						�Ա�
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xb"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						����
					</div>
				</td>
				<td>
					<bean:write name="rs" property="mzmc"/>
				</td>
				<td>
					<div align="center">
						������ò
					</div>
				</td>
				<td>
					<bean:write name="rs" property="zzmm"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						��������
					</div>
				</td>
				<td>
					<bean:write name="rs" property="csrq"/>
				</td>
				<td>
					<div align="center">
						����
					</div>
				</td>
				<td>
					<bean:write name="rs" property="sfmc"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						ְ��
					</div>
				</td>
				<td>
					<bean:write name="rs" property="zwmc"/>
				</td>
				<td>
					<div align="center">
						��ҵԺУ
					</div>
				</td>
				<td>
					<bean:write name="rs" property="byyx"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						ѧ��
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xl"/>
				</td>
				<td>
					<div align="center">
						��ѧרҵ
					</div>
				</td>
				<td>
					<bean:write name="rs" property="sxzy"/>
				</td>
			</tr>
			<tr>
				<td align="center">
						���¸���Ա����ʱ��
				</td>
				<td>
					<bean:write name="rs" property="szgzsj"/>
				</td>
				<td align="center">
						ְ��
				</td>
				<td>
					<bean:write name="rs" property="zc"/>
				</td>
			</tr>

			<tr>
				<td>
					<div align="center">
						�����༶��ѧ���������
					</div>
				</td>
				<td colspan="3">
					<html:textarea name="rsxjch" property="sdbjxshjqk" rows='5'
						style="width:100%" onblur="chLeng(this,300)"/>
				</td>
			</tr>
			<tr >
				<td>
					<div align="center">
						���˻������
						<br>
						<font color="red"><��200��>
						</font>
					</div>
				</td>
				<td colspan="3">
					<html:textarea name="rsxjch" property="zysj" rows='5' styleId="zysj" 
						style="width:100%" onblur="chLeng(this,200)"/>
				</td>
			</tr>
			<tr >
				<td>
					<div align="center">
						���˹����ܽ�
						<br>
						<font color="red">*<��300��> </font>
					</div>
				</td>
				<td colspan="3">
					<html:textarea name="rsxjch" property="gzzj" rows='5' style="width:100%" onblur="chLeng(this,200)"/>
				</td>
			</tr>
		</table>
		<%--			<logic:equal name="sfksq" value="1">--%>
		<%--				<logic:notPresent name="msg">--%>
		<div class="buttontool" id="btn" style="position: absolute;width:90%">
		<logic:notEqual value="view" name="act">
			<button type="button" class="button2" onClick="dtjsCommonSave('szdw_zjlg.do?method=xjchSq&doType=save','','','zgh-zysj');">
				�ύ����
			</button>
		</logic:notEqual>
		</div>
	
	</html:form>
				<logic:equal value="true" name="done">
			  <script type="text/javascript">
			    alert('����ɹ���');
			    Close();
			    if(window.dialogArguments){
	         	window.dialogArguments.document.getElementById('search_go').click();
	         	}
			  </script>
			</logic:equal>	
			<logic:equal value="false" name="done">
			  <script type="text/javascript">
			    alert('����ʧ�ܣ�,��ȷ�ϸø���Ա�Ƿ�������');
			    Close();
			    if(window.dialogArguments){
	         	window.dialogArguments.document.getElementById('search_go').click();
	         	}
			  </script>
			</logic:equal>
</body>
<logic:present name="msg">
	<script>
			alert(''+document.getElementById('msg').value);
		</script>
</logic:present>
</html:html>
