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
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script type="text/javascript">
	function chBl(value){
		if(value == "yes"){
			$("zp").style.display="";
			$("bj").style.display="";
		}else{
			$("zp").style.display="none";
			$("bj").style.display="none";
			$("zpfbl").value="";
			$("bjfbl").value="";
		}
	}
	function saveBl(){
		var zpfbl = $("zpfbl").value;
		var bjfbl = $("bjfbl").value;
		var isBl = $("isBl").value;
		if(isBl == "yes"){
			if(zpfbl == "" || bjfbl == ""){
				alert("��������ֻ��߰༶����ֱ����Բ���Ϊ�գ���ȷ�ϣ���");
				return false;
			}
		}
		if((parseInt(zpfbl) + parseInt(bjfbl)) > 100){
			alert("������Ӳ��ܳ���100%����ȷ�ϣ�����");
			return false;
		}
		var url='/xgxt/zjlgPjpy.do?method=dycpfPsfSz&type=save&doType='+$('type').value;
		refreshForm('/xgxt/zjlgPjpy.do?method=dycpfPsfSz&type=save&doType='+$('type').value);
	}
	function onShow(){
		var isBl = $("isBl").value;
		if( isBl == "yes"){
			$("zp").style.display="";
			$("bj").style.display="";
		}
		if($('type').value == "edit"){
			$("isBl").disabled=true;
		}
	}
	</script>
	<body onload="onShow()">
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/chgPass" method="post">
			<div class="title">
				<div class="title_img">
					��ǰ����λ�ã�<bean:write name="title" />
				</div>
			</div>
			<fieldset>
				<legend>
					��������
				</legend>
				<input type="hidden" name="type" id="type" value="<bean:write name="type" />"/>
				<table width="80%" class="tbstyle" align="center">
					<thead>
						<tr align="center">
							<td colspan="2">
								��������
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right" width="40%">
							��Ҫ����ƽʱ�ֱ�����
						</td>
						<td align="left" width="40%">
							<html:select name="rs" property="isBl" style="width:150px" onchange="chBl(this.value)">
								<html:option value="no">��</html:option>
								<html:option value="yes">��</html:option>
							</html:select>
						</td>
					</tr>
					<tr id="zp" style="display: none">
						<td align="right" width="40%">
							��������ֱ�����
						</td>
						<td align="left" width="40%">
							<html:text name="rs" property="zpfbl" style="width:30px" 
								onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="3"/>%
						</td>
					</tr>
					<tr id="bj" style="display: none">
						<td align="right" width="40%">
							�༶����ֱ�����
						</td>
						<td align="left" width="40%">
							<html:text name="rs" property="bjfbl" style="width:30px"
								onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="3"/>%
						</td>
					</tr>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="2">
						<button type="button" class="button2"
							onclick="saveBl();"
							style="width:80px">
							�� ��
						</button>
						</td>
					</tr>
				</table>
			</fieldset>
		<logic:equal value="yes" name="result">
			<script>
				alert("�����ɹ�!");
				Close();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("����ʧ��");
			</script>
		</logic:equal>		
		</html:form>
	</body>
</html>
