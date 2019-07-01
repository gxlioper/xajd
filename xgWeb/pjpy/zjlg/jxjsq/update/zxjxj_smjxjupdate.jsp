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
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="������� zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript">
		function jxjSqSavett(){
			var xmdm = null;
			var xh = null;
			var sqly = null;
			var jfqk = null;
			var xyshyj = null;
			var fdyshyj = null;
			var lshshyj = null;
			var bz = null;



			if(document.getElementById('jxjdm')){
			 xmdm = document.getElementById('jxjdm').value;
			}
			if(document.getElementById('xh')){
			 xh = document.getElementById('xh').value;
			}
			if(document.getElementById('sqly')){
			 sqly = document.getElementById('sqly').value;
			}
			if(document.getElementById('jfqk')){
			 jfqk = document.getElementById('jfqk').value;
			}
			if(document.getElementById('xyshyj')){
			 xyshyj = document.getElementById('xyshyj').value;
			}
			if(document.getElementById('fdyyj')){
			 fdyshyj = document.getElementById('fdyyj').value;
			}
			if(document.getElementById('lshshyj')){
			 lshshyj = document.getElementById('lshshyj').value;
			}
			if(document.getElementById('bz')){
			 bz = document.getElementById('bz').value;
			}
			
			if(xmdm == null || xmdm == ""){
				alert("��ѡ��Ҫ����Ľ���ѧ��!");
				return false;
			}
			if(xh == null || xh == ""){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if(jfqk != null){
	         	if(jfqk.length > 400){	         
	          		 alert("��������ܴ���200���ַ���");
	          		 return false;
	       		 }
	       	}
			if(sqly != null){
	         	if(sqly.length > 400){	         
	          		 alert("�������벻�ܴ���800���ַ���");
	          		 return false;
	       		 }
	       	}
	       	if(xyshyj != null){
	         	if(xyshyj.rlength > 200){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />������ܴ���100���ַ���");
	          		 return false;
	       		 }
	       	}
	    	if(lshshyj != null){
	         	if(lshshyj.length > 200){	         
	          		 alert("ɣ�齱ѧ�������������ίԱ�᲻�ܴ���100���ַ���");
	          		 return false;
	       		 }
	       	}
			if(bz != null){
	         	if(bz.length > 200){	         
	          		 alert("��ע���ܴ���100���ַ���");
	          		 return false;
	       		 }
	       	}
			if(fdyshyj != null){
	         	if(fdyshyj.length > 200){	         
	          		 alert("����Ա���ܴ���100���ַ���");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&doType=save&jxjtype=smjxj&update=update";
			document.forms[0].submit();
		}
		
		function chang(){
			
			alert('tt');
			return false;
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=jzxj_xmsq";
			document.forms[0].submit();
		}
		
		function toPrintOut(){
			var xmdm = document.getElementById('xmdm').value;
			if(xmdm == null || xmdm == ""){
				alert("��ѡ��Ҫ���صĽ���ѧ��!");
				return false;
			}
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=jzxj_xmmbxz&xmdm="+xmdm;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		function initjxjList(){
			var jxjdm = document.getElementById("jxjdm").value;
			GetListData.getJxjdm(jxjdm,function initTjList(data){
					if (data != null) {
						alert(data);
						if(document.getElementById("jxjlb").value!=data  || document.getElementById("jxjlb").value=="ר�ѧ��"){
							document.getElementById("jxjlb").value=data;
							if(data=="ͻ�����׽�ѧ��"){
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype=tcgxjxj";
								document.forms[0].submit();
							}else if(data=="����ѧ����ѧ��"){
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype=yxxsjxj";
								document.forms[0].submit();
							}else if(data=="��Ṥ����ѧ��"){
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype=shgzjxj";
								document.forms[0].submit();
							}else if(data=="���д��½�ѧ��"){
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype=kycxjxj";
								document.forms[0].submit();
							}else if(data=="���ѧ��"){
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype=dxjxj";
								document.forms[0].submit();
							}else if(data=="�����ҵ����ѧ��"){
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype=yxbysjxj";
								document.forms[0].submit();
							}else if(data=="ר�ѧ��"){
								var jxjlb;
								if(jxjdm=="00071"){
									jxjlb="fzzgjxj";
								}else if(jxjdm=="00072"){
									jxjlb="gjjxj";
								}else if(jxjdm=="00073"){
									jxjlb="hdjxj";
								}else if(jxjdm=="00074"){
									jxjlb="smjxj";
								}else{
										alert("ѡ�����������ѡ��");
								}
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype="+jxjlb;
								document.forms[0].submit();
							}
						}else{
						}
					}else{
						showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
					}
				});
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã��������� - ��ѧ�� - ɣ�齱ѧ��
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ���ڻ򲻷�����������������
			</p>
		</center>
	</logic:equal>
		<html:form action="/zjlgPjpy" method="post">
			
			<input type="hidden" id="url" name="url"
				value="/zjlgPjpy.do?method=yxxsjxjsq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="xn" name="xn"
				value="<bean:write name="rs" property="xn" />">
			<input type="hidden" id="xq" name="xq"
				value="<bean:write name="rs" property="xq" />">
			<input type="hidden" id="jxjmc" name="jxjmc"
				value="smjxj">
				
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
			<logic:present name="have">
				<logic:match value="have" name="have">
					<script language="javascript">
	         			alert("��ͨ����ˣ��������룡");
	         		</script>
				</logic:match>
			</logic:present>

			<logic:equal value="update" name="forwardtype">
			<table class="tbstyle" width="90%">
				<tr>
					<td align="center" colspan="1" style="width: 120px">
						<font color="red">*</font>��ѧ������
					</td>
					<td colspan="2">
						<html:select name="rs" property="jxjdm" style="width:180px;display:none"
							onchange="initjxjList();">
							<html:option value=""></html:option>
							<html:options collection="jzxjxmList" property="jxjdm"
								labelProperty="jxjmc" />
						</html:select>
						<html:text name="rs" property="jxjmc" readonly="true"></html:text>
					</td>
					<td colspan="1" scope="row">
						<div align="center">
							��ѧ�����
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="jxjlbmc" readonly="true"></html:text>
						<html:text name="rs" property="jxjlb" readonly="true" style="display: none"></html:text>
					</td>
				</tr>
				<tr>
						<td align="center" colspan="1">
							<font color="red">*</font>ѧ��
						</td>
						<td align="left" colspan="2">
							<html:text name='rs' property="xh" styleId="xh" readonly="true" />
						</td>
					<td width="11%" colspan="1">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="4" scope="col">
						<html:text name="rs" property="xm" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							���п���
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="yhkh"></html:text>
					</td>
					<td colspan="1">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="yhlx"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							ѧ��
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="xn" readonly="true"></html:text>
					</td>
					<td colspan="1">
						<div align="center">
							ѧ��
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="xq" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="xb" readonly="true"></html:text>
					</td>
					<td colspan="1">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="csrq" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							������ò
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="zzmm" readonly="true"></html:text>
					</td>
					<td>
						<div align="center">
							����ְ��
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="drzw" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							��Դ��
						</div>
					</td>
					<td colspan="2"><bean:write name="rs" property="jgs"/>
<%--						<html:text name="rs" property="jgs" readonly="true"></html:text>--%>
						<input id="jgs" name="jgs" type="text"" value="<bean:write name="rs" property="jg"/>" readonly="readonly"/>
					</td>
					<td>
						<div align="center">
							�س�
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="tc" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							����ũ��
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="nccz" readonly="true"></html:text>
					</td>
					<td>
						<div align="center">
							
						</div>
					</td>
					<td colspan="4">
					
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							�ۺϲ�������
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="zhszcpcjpm" readonly="true"></html:text>
					</td>
					<td>
						<div align="center">
						רҵ����
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="zhuanypm" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							�� �� �� ��
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="sbdj" readonly="true"></html:text>
					</td>
					<td>
						<div align="center">
						�ڼ��λ��ɣ�齱ѧ��
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="djchdjxj" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>��<br>��<br>��<br>��<br>
						</div>
					</td>
					<td colspan="8">
						<html:textarea name="rs" property="jfqk" rows='6' style="width:100%" />
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							���<br>ɣ��<br>����<br>���<br>��
						</div>
					</td>
					<td colspan="8">
						���ɣ������������ʵҵ�ҡ�������ʿ������ر����������Ͼ�ѫ�»��
						<br>�߲����������������������������ҹ���֯�����ޣ�����ʮ���������
						<br>Ϊ��չ��֯��ҵ��ע�˴����������ɼ�׿��������һλ�ɹ�ʵҵ�ҵľ����
						<br>�ǻۣ������Ƽ������������˲��Ǵٽ��ҹ���֯��ҵ���غͷ�չ�ĸ�����
						<br>�⣬��ˣ�����һ�žŶ������ȳ��ʳ�����ɣ�����ᣬ�����Ե��λ����
						<br>��ϯ��ּ���廳�ҹ���֯��ҵ��Դ��ɣ�飬���Լ������ڷ�֯���ҵ��Ա��
						<br>����ѧ�ӷ��ﰮ�����徫�����ӿƼ���Ŭ�����У������л���֯��ҵ����
						<br>����Ⱥ�������֯��<bean:message key="lable.xsgzyxpzxy" />��������ѧ��ԭ�й���֯��ѧ�����㽭����ѧ
						<br>��ԭ�㽭˿��<bean:message key="lable.xsgzyxpzxy" />���ͱ�����װ<bean:message key="lable.xsgzyxpzxy" />������ɣ�齱ѧ�𣬶Խ����Ƚ�����
						<br>���˲��ṩ�������������ٽ��˷�֯������ҵ�ķ�չ��
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="2">
						<div align="center">
							��<br>��<br>��<br>��
						</div>
					</td>
					<td colspan="8">
					����������Ӧ���������˵��������ı��֡�������Ṥ�����μ������������뽱ѧ��Ķ�����Ŭ��Ŀ�꣬����Ҫ˵����ͥ�������
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<html:textarea name="rs" property="sqly" rows='6' style="width:100%"/>
					</td>
				</tr>
				
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>��<br>ע<br>
						</div>
					</td>
					<td colspan="8">
					<html:textarea property="bz" rows='6' style="width:100%;display: none"  />
					1.����һʽ���ݣ�������д����Ҫ����Դ�ӡ��<br>
					2.ѧУ��Ժ��ϵ�༶������д��
					
					</td>
				</tr>
			</table>
	<div class="buttontool" align="center">
				<button type="button" class="button2" id="buttonSave" onclick="jxjSqSavett();">
					�� �� �� ��
				</button>
				&nbsp;&nbsp;
			</div>
			</logic:equal>
			<logic:equal value="view" name="forwardtype">
			<table class="tbstyle" width="90%">
				<tr>
					<td align="center" colspan="1" style="width: 120px">
						<font color="red">*</font>��ѧ������
					</td>
					<td colspan="2">
						<html:select name="rs" property="jxjdm" style="width:180px;display:none"
							onchange="initjxjList();">
							<html:option value=""></html:option>
							<html:options collection="jzxjxmList" property="jxjdm"
								labelProperty="jxjmc" />
						</html:select>
						<html:text name="rs" property="jxjmc" readonly="true"></html:text>
					</td>
					<td colspan="1" scope="row">
						<div align="center">
							��ѧ�����
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="jxjlbmc" readonly="true"></html:text>
						<html:text name="rs" property="jxjlb" readonly="true" style="display: none"></html:text>
					</td>
				</tr>
				<tr>
						<td align="center" colspan="1">
							<font color="red">*</font>ѧ��
						</td>
						<td align="left" colspan="2">
							<html:text name='rs' property="xh" styleId="xh" readonly="true" />
						</td>
					<td width="11%" colspan="1">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="4" scope="col">
						<html:text name="rs" property="xm" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							���п���
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="yhkh" readonly="true"></html:text>
					</td>
					<td colspan="1">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="yhlx" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							ѧ��
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="xn" readonly="true"></html:text>
					</td>
					<td colspan="1">
						<div align="center">
							ѧ��
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="xq" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="xb" readonly="true"></html:text>
					</td>
					<td colspan="1">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="csrq" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							������ò
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="zzmm" readonly="true"></html:text>
					</td>
					<td>
						<div align="center">
							����ְ��
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="drzw" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							��Դ��
						</div>
					</td>
					<td colspan="2"><bean:write name="rs" property="jgs"/>
<%--						<html:text name="rs" property="jgs" readonly="true"></html:text>--%>
						<input id="jgs" name="jgs" type="text"" value="<bean:write name="rs" property="jg"/>" readonly="readonly"/>
					</td>
					<td>
						<div align="center">
							�س�
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="tc" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							����ũ��
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="nccz" readonly="true"></html:text>
					</td>
					<td>
						<div align="center">
						</div>
					</td>
					<td colspan="4">
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							�ۺϲ�������
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="zhszcpcjpm" readonly="true"></html:text>
					</td>
					<td>
						<div align="center">
						רҵ����
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="zhuanypm" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							�� �� �� ��
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="sbdj" readonly="true"></html:text>
					</td>
					<td>
						<div align="center">
						�ڼ��λ��ɣ�齱ѧ��
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="djchdjxj" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>��<br>��<br>��<br>��<br>
						</div>
					</td>
					<td colspan="8">
						<html:textarea name="rs" property="jfqk" rows='6' style="width:100%" />
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							���<br>ɣ��<br>����<br>���<br>��
						</div>
					</td>
					<td colspan="8">
						���ɣ������������ʵҵ�ҡ�������ʿ������ر����������Ͼ�ѫ�»��
						<br>�߲����������������������������ҹ���֯�����ޣ�����ʮ���������
						<br>Ϊ��չ��֯��ҵ��ע�˴����������ɼ�׿��������һλ�ɹ�ʵҵ�ҵľ����
						<br>�ǻۣ������Ƽ������������˲��Ǵٽ��ҹ���֯��ҵ���غͷ�չ�ĸ�����
						<br>�⣬��ˣ�����һ�žŶ������ȳ��ʳ�����ɣ�����ᣬ�����Ե��λ����
						<br>��ϯ��ּ���廳�ҹ���֯��ҵ��Դ��ɣ�飬���Լ������ڷ�֯���ҵ��Ա��
						<br>����ѧ�ӷ��ﰮ�����徫�����ӿƼ���Ŭ�����У������л���֯��ҵ����
						<br>����Ⱥ�������֯��<bean:message key="lable.xsgzyxpzxy" />��������ѧ��ԭ�й���֯��ѧ�����㽭����ѧ
						<br>��ԭ�㽭˿��<bean:message key="lable.xsgzyxpzxy" />���ͱ�����װ<bean:message key="lable.xsgzyxpzxy" />������ɣ�齱ѧ�𣬶Խ����Ƚ�����
						<br>���˲��ṩ�������������ٽ��˷�֯������ҵ�ķ�չ��
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="2">
						<div align="center">
							��<br>��<br>��<br>��
						</div>
					</td>
					<td colspan="8">
					����������Ӧ���������˵��������ı��֡�������Ṥ�����μ������������뽱ѧ��Ķ�����Ŭ��Ŀ�꣬����Ҫ˵����ͥ�������
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<html:textarea name="rs" property="sqly" rows='6' style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>��<br>��<br>��<br>��<br>Ա<br>��<br>��<br>
						</div>
					</td>
					<td colspan="8">
						<html:textarea name="rs" property="fdyyj" rows='6' style="width:100%" readonly="true"/>
					</td>
				</tr>
				
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br><bean:message key="lable.xsgzyxpzxy" /><br>���<br>���<br>
						</div>
					</td>
					<td colspan="8">
						<html:textarea name="rs" property="xyshyj" rows='6' style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>ɣ��<br>��ѧ<br>����<br>����<br>����<br>��ί<br>Ա��<br>
						</div>
					</td>
					<td colspan="8">
						<html:textarea name="rs" property="lshshyj" rows='6' style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>��<br>ע<br>
						</div>
					</td>
					<td colspan="8">
					<html:textarea property="bz" rows='6' style="width:100%;display: none"  readonly="true"/>
					1.����һʽ���ݣ�������д����Ҫ����Դ�ӡ��<br>
					2.ѧУ��Ժ��ϵ�༶������д��
					
					</td>
				</tr>
			</table>
			</logic:equal>
			<logic:equal name="done" value="true">
				<script>
			          alert("�޸ĳɹ���");
			        </script>
			</logic:equal>
			<logic:equal name="done" value="false">
				<script>
			          alert("�޸�ʧ�ܣ�");
			        </script>
			</logic:equal>
			<logic:equal name="isExist" value="no">
				<script>
			        alert("�������ƺ�������,����ͨ����ز������\n�����������,�����ٴ����룡");			    
			        </script>
			</logic:equal>

		</html:form>
</body>
</html:html>
