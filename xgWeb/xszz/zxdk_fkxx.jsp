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
	<script language="javascript" src="dwr/interface/GetListData.js"></script>
	<script language="javascript" src="dwr/engine.js"></script>
	<script language="javascript" src="js/BatAjax.js"></script>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function dataDoSave(mustFill,je,xszt) {
			var eles = mustFill.split("-");
			for (i = 0; i < eles.length; i++) {
				if (document.getElementById(eles[i]).value == "") {
					alert("�뽫��\"*\"�ŵ���Ŀ����������");
					return false;
				}
			}
			var htje = document.getElementById('htje').value;
			var zfkje = document.getElementById('zfkje').value;
			if((xszt != "�ڶ�") && (xszt != "��ѧ")){
				alert("ѧ��״̬������Ϊ��ѧ���ſ�!");
				return false;
			}
			if(!isNumber(je)){
				alert("�ſ������Ϊ��������");
				return false;
			}
			if((Math.round(je)+Math.round(zfkje)) > Math.round(htje)){
				alert("�ſ��ۼƽ���Ѵ��ں�ͬ��");
				return false;
			}
			var url = "/xgxt/zxdk_fkxx.do?doType=save&pk=";
			url += window.document.forms[0].pk.value;
			url += "&doType2=";
			url += window.document.forms[0].doType2.value;
			document.forms[0].action = url;
			document.forms[0].submit();
		}
		/**
		 * ��������ʱ����Ч��
		 */
		function validate() {
			var xszt = $('xszt').value.trim();
			var zje = $('zje').value.trim();
			var htje = $('htje').value.trim();
			
			if (xszt!='��ѧ' && xszt!='�ڶ�') {
				alert('ѧ����ǰ״̬���ܷſ�!');
				$('xszt').focus();
				return false;
			}
			if (new Number(zje) > new Number(htje)) {
				alert('�ſ��ܽ��ܴ��ں�ͬ���!');
				$('zje').focus();
				return false;
			} 
			if ($('hth').value.trim()=='') {
				alert('��ѡ���ͬ��!');
				$('hth').focus();
				return false;
			}
			return true;
		}
		
		function onc(){
			var vrl = "/zxdk_fkxx.do";
			document.forms[0].submit();
		}
		function elementValidate(element) {
			if (element.value.trim()!='' && !isNumber(element.value)) {
				alert('��������ȷ��'+element.sample);
				element.focus();
			} else {
				if (element.value.trim()=='') {
					element.value = '0';
				}
				$('zje').value = ''+zjeJs();
			}
		}
		function zjeJs() {
			if ($('fkje1').value.trim() == '') {
				$('fkje1').value = '0';
			} else if (!isNumber($('fkje1').value.trim())) {
				alert('��������ȷ��'+$('fkje1').sample);
				$('fkje1').focus();
				return 0;
			}
			if ($('fkje2').value.trim() == '') {
				$('fkje2').value = '0';
			} else if (!isNumber($('fkje2').value.trim())) {
				alert('��������ȷ��'+$('fkje2').sample);
				$('fkje2').focus();
				return 0;
			}
			if ($('fkje3').value.trim() == '') {
				$('fkje3').value = '0';
			} else if (!isNumber($('fkje3').value.trim())) {
				alert('��������ȷ��'+$('fkje3').sample);
				$('fkje3').focus();
				return 0;
			}
			if ($('fkje4').value.trim() == '') {
				$('fkje4').value = '0';
			} else if (!isNumber($('fkje4').value.trim())) {
				alert('��������ȷ��'+$('fkje4').sample);
				$('fkje4').focus();
				return 0;
			}
			if ($('fkje5').value.trim() == '') {
				$('fkje5').value = '0';
			} else if (!isNumber($('fkje5').value.trim())) {
				alert('��������ȷ��'+$('fkje5').sample);
				$('fkje5').focus();
				return 0;
			}
			return (new Number($('fkje1').value.trim())+new Number($('fkje2').value.trim())+new Number($('fkje3').value.trim())+new Number($('fkje4').value.trim())+new Number($('fkje4').value.trim())+new Number($('fkje5').value.trim()) );
		}
		
/**
 * @describe ������������ж��ַ����Ƿ�Ϊ����
 */
function isNumber(s){
	var p = /^(-|\+)?\d+$/;
	return p.test(s); 
}
		
	</script>
</head>

<body>
		<html:form action="/zxdk_fkxx.do" method="post">
			<input type="hidden" id="url" name="url"
				value="/shgc_xszz.do?method=getFkxxInfo" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="htje" name="htje" value="<bean:write name="map" property="htje"/>">
			<input type="hidden" id="hthV" value="<bean:write name="map" property="hth"/>">
			
			<table width="100%" border="1" class="tbstyle">
				<thead>
					<tr>
						<td colspan="6" align="center">
							�ſ���Ϣ
						</td>
					</tr>
				</thead>
				<tr>
					<td align="center" width="10%" scope="col">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<td align="left" width="20%" scope="col">
						<html:text name='map' property="xh" styleId="xh"
							readonly="readonly"
							onkeypress="autoFillStuInfo(event.keyCode,this)" onchange="refreshForm('shgc_xszz.do?method=getFkxxInfo');" />
						<button onclick="showTopWin('stu_info.do',750,550);"
							class="btn_01" id="buttonFindStu">
							ѡ��
						</button>
					</td>
					<td width="10%" scope="col">
						<div align="center">
							����
						</div>
					</td>
					<td width="20%" scope="col">
						<div align="left">
							<input type="text" id="xm" name="xm"
								style="width:100%;heigh:100%"
								value="<bean:write name='map' property="xm" />" readonly="true">
						</div>
					</td>
					<td scope="row" width="10%">
						<div align="center">
							ѧ��״̬
						</div>
					</td>
					<td width="20%">
						<div align="left">
							<input type="text" id="xszt" readonly="readonly" name="xszt"
								style="width:100%;heigh:100%"
								value="<bean:write name='map' property="xszt" />" readonly="true">
						</div>
					</td>
				</tr>
				<tr>
					
					<td>
						<div align="center">
							<font color="red">*</font>���֤��
						</div>
					</td>
					<td>
						<div align="left">							
                          <input type="text" id="sfzh" readonly="readonly" name="sfzh"
								style="width:100%;heigh:100%"
								value="<bean:write name='map' property="sfzh" />">
</div>
					</td>
					<td scope="row">
						<div align="center">
							ѧУ����
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="xxmc" readonly="readonly" name="xxmc"
								style="width:100%;heigh:100%"
								value="<bean:write name='map' property="xxmc" />" readonly="true">
						</div>
					</td>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="kh" readonly="readonly" name="kh"
								style="width:100%;heigh:100%"
								value="<bean:write name='map' property="kh" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<font color="red">*</font>��ͬ��
						</div>
					</td>
					<td>
						<select name="hth" id="hth" style="width:100%" onchange="refreshForm('shgc_xszz.do?method=getFkxxInfo');"></select>
					</td>
					<td scope="row">
						<div align="center">
							��ͬ���
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="htje" readonly="readonly" name="htje"
								style="width:100%;heigh:100%"
								value="<bean:write name='map' property="htje" />">
						</div>
					</td>
					<td>
						<div align="center">
							��ͬ����
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="htqx" name="htqx"
								style="width:100%;heigh:100%"
								value="<bean:write name='map' property="htqx" />" readonly="true">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��������
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="dkyh" readonly="readonly" name="dkyh"
								style="width:100%;heigh:100%"
								value="<bean:write name='map' property="dkyh" />" readonly="true">
						</div>
					</td>
					<td scope="row">
						<div align="center">
							�ſ��ܽ��
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="zje" name="zje"
								style="width:100%;heigh:100%"
								value="<bean:write name='map' property="zje" />" readonly="true">
						</div>
					</td>
					
					<td scope="row">
						<div align="center">
							��������
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="dkll" readonly="readonly" name="dkll"
								style="width:100%;heigh:100%"
								value="<bean:write name='map' property="dkll" />" readonly="true">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<font color="red">*</font>�ſ�����1						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('fkrq1','y-mm-dd');"
								value="<bean:write name='map' property="fkrq1" />"
								name="fkrq1" id="fkrq1" />
						</div>
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>�ſ���1
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="fkje1" name="fkje1" onBlur="elementValidate(this)" sample="�ſ���1"
								style="width:100%;heigh:100%" maxlength="5"
								value="<bean:write name='map' property="fkje1" />"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							���1</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="nf1" name="nf1"
								style="width:100%;heigh:100%" onclick="return showCalendar('nf1','y');"
								value="<bean:write name='map' property="nf1" />"  readonly="true" >
						</div>
					</td>
				</tr>
				<tr>
					
					<td scope="row">
						<div align="center">
							�ſ�����2						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="fkrq2" readonly="readonly" name="fkrq2"
								style="width:100%;heigh:100%" onclick="return showCalendar('fkrq2','y-mm-dd');"
								value="<bean:write name='map' property="fkrq2" />" readonly="true">
						</div>
					</td>
					<td>
						<div align="center">�ſ���2						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="fkje2" name="fkje2" onBlur="elementValidate(this)" sample="�ſ���2"
								style="width:100%;heigh:100%" maxlength="5"
								value="<bean:write name='map' property="fkje2" />" 
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td scope="row">
						<div align="center">
							���2
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="nf2" readonly="readonly" name="nf2"
								style="width:100%;heigh:100%" onclick="return showCalendar('nf2','y');"
								value="<bean:write name='map' property="nf2" />" readonly="true">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							�ſ�����3</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="fkrq3" name="fkrq3"
								style="width:100%;heigh:100%" onclick="return showCalendar('fkrq3','y-mm-dd');"
								value="<bean:write name='map' property="fkrq3" />"  readonly="true" >
						</div>
					</td>
					<td scope="row">
						<div align="center">
							�ſ���3
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="fkje3" name="fkje3" maxlength="5" onBlur="elementValidate(this)" sample="�ſ���3"
								style="width:100%;heigh:100%"
								value="<bean:write name='map' property="fkje3" />"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							���3</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="nf3" name="nf3"
								style="width:100%;heigh:100%" onclick="return showCalendar('nf3','y');"
								value="<bean:write name='map' property="nf3" />"  readonly="true" >
						</div>
					</td>
				</tr>
				<tr>
					
					<td scope="row">
						<div align="center">
							�ſ�����4
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="fkrq4" readonly="readonly" name="fkrq4"
								style="width:100%;heigh:100%" onclick="return showCalendar('fkrq4','y-mm-dd');"
								value="<bean:write name='map' property="fkrq4" />" readonly="true">
						</div>
					</td>
					<td>
						<div align="center">
							�ſ���4</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="fkje4" name="fkje4" onBlur="elementValidate(this)" sample="�ſ���4"
								style="width:100%;heigh:100%" maxlength="5"
								value="<bean:write name='map' property="fkje4" />"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td scope="row">
						<div align="center">
							���4
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="nf4" readonly="readonly" name="nf4"
								style="width:100%;heigh:100%"  onclick="return showCalendar('nf4','y');"
								value="<bean:write name='map' property="nf4" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							�ſ�����5
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="fkrq5" readonly="readonly" name="fkrq5"
								style="width:100%;heigh:100%" onclick="return showCalendar('fkrq5','y-mm-dd');"
								value="<bean:write name='map' property="fkrq5" />">
						</div>
					</td>
					<td>
						<div align="center">
							�ſ���5</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="fkje5" name="fkje5" onBlur="elementValidate(this)" sample="�ſ���5"
								style="width:100%;heigh:100%" maxlength="5"
								value="<bean:write name='map' property="fkje5" />"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					
					<td scope="row">
						<div align="center">
							���5
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="nf5" readonly="readonly" name="nf5"
								style="width:100%;heigh:100%"  onclick="return showCalendar('nf5','y');"
								value="<bean:write name='map' property="nf5" />" readonly="true">
						</div>
					</td>
				</tr>
								
				<tr>
					<td scope="row">
						<div align="center">
							��ע
						</div>
					</td>
					<td colspan="5">
						<div align="left">
							<input type="text" id="bz" name="bz"
								style="width:100%;heigh:100%" maxlength="100"
								value="<bean:write name='map' property="bz" />">
						</div>
					</td>
				</tr>
			</table>
			
			<div class="buttontool" align="center">
					<button class="button2"
						onclick="if(validate()) {refreshForm('shgc_xszz.do?method=fkxxSave');}"
						style="width:80px" id="buttonSave">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="window.dialogArguments.document.getElementById('search_go').click();Close();" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>

		</html:form>
</body>
</html:html>
<script type="text/javascript">
<logic:equal name="xskmydr" value="YES">
if ($('xh').value.trim() == '') {
	alert("��ѡ���ѧ����û�е�����ѧ����ѧ���⣡");
}
</logic:equal>
if ($('xh').value.trim() != '') {
	GetListData.getHthList($('xh').value.trim(),{callback:function(data){initTjList(data, 'hth');}});
}
</script>