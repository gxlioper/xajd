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

	<%@ include file="/syscommon/pagehead_V4.ini"%>
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
	<style>
	 .sfyxI{}
	 .szrs{}
	 .xymcI{}
	 .xyrs{}
	</style>

	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/prototype-1.6.0.3.js"></script>
	<script language="javascript">
		function yz(){
			var szrsArr = $$('input.szrs');	
			var xymcIArr = $$('input.xymcI');
			var xyrsArr = $$('input.xyrs');
			var sfyxArr = $$('select.sfyxI');
			var zrsObj = $('zrs').value;
			var sumSzrs	= 0;
			for(var i=0;i<szrsArr.length;i++){
				if(toInt(szrsArr[i].value) > toInt(xyrsArr[i].value)){
					alert(xymcIArr[i].value+" �������������ƣ�");
					return false;
				} else {
				  if(sfyxArr[i].value == "��"){
				  	sumSzrs += toInt(szrsArr[i].value);
				  }
				}
			}
			if(sumSzrs > zrsObj){
			   alert("��������"+sumSzrs+"�ˣ��������ƣ�");
			   return false;
			}
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=jzxj_xmrsEdit&act=save";
			document.forms[0].submit();
		}
		
		function fp(){
			var bfb = $('bfb').value;
			var xyrsArr = $$('input.xyrs');
			var szrsArr = $$('input.szrs');
			var sfyxArr = $$('select.sfyxI');
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
				szrsArr[i].value = Math.round(toInt(xyrsArr[i].value)*toInt(bfb)/100);
				zrs += Math.round(toInt(xyrsArr[i].value)*toInt(bfb)/100);
				sfyxArr[i].value = "��";
			}
			document.getElementById('yfprs').value = zrs;
		}
		
		function jsrs(){
			var szrsArr = $$('input.szrs');	
			var sfyxArr = $$('select.sfyxI');
			var sumSzrs	= 0;
			
			for(var i=0;i<szrsArr.length;i++){
				if(sfyxArr[i].value == "��"){
					sumSzrs += toInt(szrsArr[i].value);
				}
			}
			document.getElementById('yfprs').value = sumSzrs;
		}
	</script>
</head>

<body onload="jsrs();">

	<html:form action="/zgdzdx_xszz.do?method=jzxj_xmrsEdit" method="post">

		<input type="hidden" id="pkVal" name="pkVal"
			value="<bean:write name="pkVal" />">
		<logic:present name="end">
			<logic:match value="end" name="end">
				<script language="javascript">
	         	alert("������ɣ�");
	         	</script>
			</logic:match>
		</logic:present>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>����ѧ�� - ��������ά�� - ��Ŀ��������ά��</a>
			</p>
		</div>
		<div class="tab">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="3">
							<span>��Ŀ����ά��</span>
						</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<td colspan="3">
							<div class="bz">
								"
								<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">

								<logic:equal name="sfyxT" value="��">
									<button type="button" class="" onClick="yz();" id="bt" name="bt">
										����
									</button>
								</logic:equal>
								<button type="button" class=""
									onClick="Close();window.dialogArguments.document.getElementById('search_go').click();">
									�ر�
								</button>
								</div>
						</td>
					</tr>
				</tfoot>

				<tbody>
					<tr>
						<th width="50%">
							��Ŀ
						</th>
						<td colspan="2">
							<bean:write name="xmmc" />
						</td>
					</tr>
					<tr>
						<th width="50%">
							������
						</th>
						<td colspan="2">
							<bean:write name="zrs" />
							<input type="hidden" id="zrs" name="zrs"
								value="<bean:write name="zrs" />" />
						</td>
					</tr>
					<tr>
						<th width="50%">
							�ѷ�������
						</th>
						<td colspan="2">
							<input type="text" id="yfprs" name="yfprs" readonly="readonly"
								style="width:100%;heigh:100%"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<th width="50%">
							ȫ������
						</th>
						<td width="20%">
							<input type="text" id="bfb" name="bfb" maxlength="3"
								style="width:50%;heigh:100%"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							%
						</td>
						<th width="30%">
							<button type="button" class="button2" onClick="fp();">
								�Զ�����
							</button>
						</th>
					</tr>
					<tr>
						<td colspan="3">
							<table width="100%" id="rsTable" class="formlist">
								<thead>
									<tr align="center" style="cursor:hand">
										<td width="40%" align="center">
											<b><bean:message key="lable.xsgzyxpzxy" />����</b>
										</td>
										<td width="20%" align="center">
											<b><bean:message key="lable.xsgzyxpzxy" />����</b>
										</td>
										<td width="20%" align="center">
											<b>��������</b>
										</td>
										<td width="20%" align="center">
											<b>�Ƿ���Ч</b>
										</td>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr>
										<td align="center">
											<input type="hidden" name="pks" value="${s.pks}" />
											<input type="hidden" class="xymcI" name="xymcI"
												value="${s.xymc}" />
											${s.xymc}
										</td>
										<td align="center">
											<input type="hidden" class="xyrs" name="xyrs"
												value="${s.xyrs}" />
											${s.xyrs}
										</td>
										<td align="center">
											<input type="text" class="szrs" id="szrs" name="szrs"
												maxlength="5" onblur="jsrs();" style="width:100%;heigh:100%"
												value="${s.szrs}"
												onkeyup="value=value.replace(/[^\d]/g,'') "
												onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
										</td>
										<td align="center">
											<html:select name="s" property="sfyx" styleClass="sfyxI"
												onchange="jsrs();">
												<html:option value="��">��</html:option>
												<html:option value="��">��</html:option>
											</html:select>
										</td>
									</tr>
								</logic:iterate>
							</table>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</html:form>
</body>
</html:html>
