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
	<style>
	 .szrs{}
	 .xymcI{}
	 .xyrs{}
	</style>
	
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/prototype-1.6.0.3.js"></script>
	<script language="javascript">
		function yz(){
			var szrsArr = $$('input.szrs');	
			var bjmcIArr = $$('input.bmcI');
			var bjrsArr = $$('input.bjrs');
			var zrsObj = $('zrs').value;
			var sumSzrs	= 0;
			for(var i=0;i<szrsArr.length;i++){
				if(toInt(szrsArr[i].value) > toInt(bjrsArr[i].value)){
					alert(zymcIArr[i].value+" �������������ƣ�");
					return false;
				} else {
				  	sumSzrs += toInt(szrsArr[i].value);
				}
			}
			if(sumSzrs > zrsObj){
			   alert("��������"+sumSzrs+"�ˣ��������ƣ�");
			   return false;
			}
			document.forms[0].action = "/xgxt/shgc_pjpy_new.do?method=shgc_pjpy_bjrsEdit&act=save";
			document.forms[0].submit();
		}
		
		function fp(){
			var bfb = $('bfb').value;
			var bjrsArr = $$('input.bjrs');
			var szrsArr = $$('input.szrs');
			var zrs = 0;
			
			if (bfb == null || bfb == ""){
				$('bfb').value = 0;
				bfb = 0;
			}
			
			if (toInt(bfb) > 100){
				$('bfb').value = 100;
				bfb = 100;
			}
			
			for(var i=0;i<szrsArr.length;i++){
				szrsArr[i].value = Math.round(toInt(bjrsArr[i].value)*toInt(bfb)/100);
				zrs += Math.round(toInt(bjrsArr[i].value)*toInt(bfb)/100);
			}
			document.getElementById('yfprs').value = zrs;
		}
		
		function jsrs(){
			var szrsArr = $$('input.szrs');	
			var sumSzrs	= 0;
			
			for(var i=0;i<szrsArr.length;i++){
				sumSzrs += toInt(szrsArr[i].value);
			}
			document.getElementById('yfprs').value = sumSzrs;
		}
	</script>
</head>

<body onload="jsrs();">
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã��������� - ��������ά�� - �༶��������ά��
		</div>
	</div>
	<html:form action="/shgc_pjpy_new.do?method=shgc_pjpy_bjrsEdit" method="post">

		<input type="hidden" id="bbdm" name="bbdm"
				value="<bean:write name="bbdm" />">
		<input type="hidden" id="zydm" name="zydm"
				value="<bean:write name="zydm" />">
		<logic:present name="end">
			<logic:match value="end" name="end">
				<script language="javascript">
	         	alert("������ɣ�");
	         	</script>
			</logic:match>
		</logic:present>
		<table class="tbstyle"  width="100%">
			<tr>
				<td width="50%">
					<div align="center">
						��Ŀ/רҵ
					</div>
				</td>
				<td colspan="2">
					<bean:write name="bbmc" />&nbsp;
					<bean:write name="zymc" />
				</td>
			</tr>
			<tr>
				<td width="50%">
					<div align="center">
						������
					</div>
				</td>
				<td colspan="2">
					<bean:write name="zrs" />
					<input type="hidden" id="zrs" name="zrs" value="<bean:write name="zrs" />" />
				</td>
			</tr>
			<tr>
				<td width="50%">
					<div align="center">
						�ѷ���������
					</div>
				</td>
				<td colspan="2">
					<input type="text" id="yfprs" name="yfprs" readonly="readonly"
						style="width:100%;heigh:100%"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<td width="50%">
					<div align="center">
						ȫ������
					</div>
				</td>
				<td width="20%">
					<input type="text" id="bfb" name="bfb" maxlength="3"
						style="width:50%;heigh:100%"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					%
				</td>
				<td width="30%">
					<button class="button2" onClick="fp();">
						�Զ�����
					</button>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td width="50%" align="center">
									�༶����
								</td>
								<td width="25%" align="center">
									�༶����
								</td>
								<td width="25%" align="center">
									��������
								</td>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr>
								<td align="center">
									<input type="hidden" name="pks" value="${s.pks}" />
									<input type="hidden" class="bjmcI" name="bjmcI" value="${s.bjmc}" />
									${s.bjmc}
								</td>
								<td align="center">
									<input type="hidden" class="bjrs" name="bjrs" value="${s.bjrs}" />
									${s.bjrs}
								</td>
								<td align="center">
									<input type="text" class="szrs" id="szrs" name="szrs" maxlength="5" onblur="jsrs();"
										style="width:100%;heigh:100%" value="${s.szrs}"
										onkeyup="value=value.replace(/[^\d]/g,'') "
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
								</td>
								<td align="center">
									${s.yfprs}
								</td>
							</tr>
						</logic:iterate>
					</table>
				</td>
			</tr>
		</table>
		<logic:equal name="writeAble" value="yes">
			<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<button class="button2" onClick="yz();" id="bt" name="bt">
					��&nbsp;&nbsp;&nbsp;&nbsp;��
				</button>
				<button class="button2"
					onClick="Close();window.dialogArguments.document.getElementById('search_go_zy').click();">
					��&nbsp;&nbsp;&nbsp;&nbsp;��
				</button>
			</div>
		</logic:equal>
	</html:form>
</body>
</html:html>
