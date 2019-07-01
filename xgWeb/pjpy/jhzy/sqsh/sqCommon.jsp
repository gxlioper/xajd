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
	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
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
			��ǰ����λ�ã��������� - ������������ - <bean:write name ="jxjmc" />
		</div>
	</div>
	<%--	<logic:equal name="sfksq" value="-1">--%>
	<%--		<center>--%>
	<%--			<p>--%>
	<%--				���ڲ�������ʱ���ڣ�--%>
	<%--			</p>--%>
	<%--		</center>--%>
	<%--	</logic:equal>--%>
	<html:form action="/jhzy_pjpySqsh" method="post">
		<input type="hidden" id="url" name="url"
			value="/jhzy_pjpySqsh.do?method=jxjsq&jxjdm=<bean:write name = "jxjdm" />" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xh-xm-xb-xymc-bjmc" />
		<input type="hidden" id="jxjdm" name="jxjdm"
			value="<bean:write name = "jxjdm" />" />	
		<input type="hidden" id="jxjmc" name="jxjmc"
			value="<bean:write name = "jxjmc" />" />
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
				<logic:equal name="userOnLine" value="teacher" scope="session">
					<td align="center" width="16%">
						<font color="red">*</font>ѧ��
					</td>
					<td align="left" width="34%">
						<html:text name='rs' property="xh" styleId="xh" readonly="true"
							onkeypress="autoFillStuInfo(event.keyCode,this)" />
						<logic:empty name="act">
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</logic:empty>
					</td>
				</logic:equal>
				<logic:equal name="userOnLine" value="student" scope="session">
					<td align="center" width="16%">
						<font color="red">*</font>ѧ��
					</td>
					<td align="left" width="34%">
						<input type="text" id="xh" name="xh" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xh" />" readonly="true">
					</td>
				</logic:equal>
				<td width="16%">
					<div align="center">
						ѧ��
					</div>
				</td>
				<logic:notEmpty name="act">
					<td width="34%">
						<html:select property="xn" styleId="xn">
							<html:options collection="xnList" property="xn"
								labelProperty="xn" />
						</html:select>
					</td>
				</logic:notEmpty>
				<logic:empty name="act">
					<td width="34%">
						<bean:write name="xn"/>
					</td>
				</logic:empty>
			</tr>
			
			<logic:present name ="xqmc">
			<tr>
				<td width="16%">
					<div align="center">
						ѧ��
					</div>
				</td>
				<logic:notEmpty name="act">
					<td width="34%">
						<html:select property="xq" styleId="xq">
							<html:options collection="xqList" property="xqdm"
								labelProperty="xqmc" />
						</html:select>
					</td>
				</logic:notEmpty>
				<logic:empty name="act">
					<td width="34%">
						<bean:write name="xqmc"/>
					</td>
				</logic:empty>
				<td width="16%">
					<div align="center">
					</div>
				</td>
				<td width="34%">
				</td>
			</tr>
			</logic:present>
			<tr>
				<td width="16%">
					<div align="center">
						����
					</div>
				</td>
				<td width="34%">
					<bean:write name="rs" property="xm"/>
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
					<bean:write name="rs" property="zzmmmc"/>
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
						��ѧ����
					</div>
				</td>
				<td>
					<bean:write name="rs" property="rxny"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						����
					</div>
				</td>
				<td>
					<bean:write name="rs" property="jg"/>
				</td>
				<td>
					<div align="center">
						��ϵ�绰
					</div>
				</td>
				<td>
					<input type="text" id="sjhm"  name="sjhm"
						style="width:100%;heigh:100%" maxlength="20"
						value="<bean:write name="rsJxj" property="sjhm"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						�꼶
					</div>
				</td>
				<td>
					<bean:write name="rs" property="nj"/>
				</td>
				<td>
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />����
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xymc"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						רҵ����
					</div>
				</td>
				<td>
					<bean:write name="rs" property="zymc"/>
				</td>
				<td>
					<div align="center">
						�༶����
					</div>
				</td>
				<td>
					<bean:write name="rs" property="bjmc"/>
				</td>
			</tr>

			<tr>
				<td>
					<div align="center">
						��ͥ��ַ
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="jtdz"/>
				</td>
			</tr>
			<tr id= "jfqk">
				<td>
					<div align="center">
						������ֽ���
						<br>
						<font color="red"><��800��>
						</font>
					</div>
				</td>
				<td colspan="3">
					<html:textarea name="rsJxj" property="jfqk" rows='5'
						style="width:100%" onblur="chLeng(this,800)"/>
				</td>
			</tr>
			<tr id = "sqly">
				<td>
					<div align="center">
						��������
						<br>
						<font color="red"><��600��>
						</font>
					</div>
				</td>
				<td colspan="3">
					<html:textarea name="rsJxj" property="sqly" rows='5'
						style="width:100%" onblur="chLeng(this,600)"/>
				</td>
			</tr>
			<tr id = "bz">
				<td>
					<div align="center">
						��ע
						<br>
						<font color="red"><��300��> </font>
					</div>
				</td>
				<td colspan="3">
					<html:textarea name="rsJxj" property="bz" rows='3' style="width:100%" onblur="chLeng(this,300)"/>
				</td>
			</tr>
		</table>
		<%--			<logic:equal name="sfksq" value="1">--%>
		<%--				<logic:notPresent name="msg">--%>
		<div class="buttontool" id="btn" style="position: absolute;width:90%">
		<logic:notEqual value="view" name="act">
			<button type="button" class="button2" onClick=" dtjsCommonSave('jhzy_pjpySqsh.do?method=jxjsq&doType=save','sqly-bz-jfqk','600-300-600','xh-sqly');">
				�ύ����
			</button>
		</logic:notEqual>
			<button type="button" class="button2" onClick="expTabby('theTable','<bean:write name = "jxjmc" />'+'�����');">
				��&nbsp;&nbsp;&nbsp;ӡ
			</button>
			<button type="button" id="btn_cj" class="button2" onclick="showTopWin('ahjg_xscjb.do?xh='+document.getElementById('xh').value,'500','400')">
							ѧ �� �� ��
						</button>
						&nbsp;&nbsp;
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
			    alert('����ʧ�ܣ�,��ȷ�ϸ����Ƿ�������');
			    Close();
			    if(window.dialogArguments){
	         	window.dialogArguments.document.getElementById('search_go').click();
	         	}
			  </script>
			</logic:equal>
			<logic:equal value="tjbfh" name="done">
			  <script type="text/javascript">
			    alert('����ʧ�ܣ�,�����ϸý�ѧ�����������');
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
