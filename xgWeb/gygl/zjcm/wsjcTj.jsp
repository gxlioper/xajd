<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script type="text/javascript" src="js/pjpy/pjpy_szyqxy.js"></script>
	<script language="javascript" src="js/sztzFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSzPjpyDAO.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/cpzZjcmDAO.js'></script>
	<script type="text/javascript">	
		function printBb(){
			var xn = $("xn").value;
			var xq = $("xq").value;
			var bblx = $("bblx").value;
			
			if(bblx == ""){
				alert("��ȷ���������ͣ���");
				return false;
			}
			
			if(xn == "" || xq == ""){
				alert("ѧ�����ѧ�ڲ���Ϊ�գ���");
				return false;
			}
			
			var url = "/xgxt/zjcmGygl.do?method=wsjcTj&doType=print";
			
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
			//window.open(url);
		}
	</script>
	<body onload="">
		<html:form action="/zjcmGygl">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã�<bean:write name="title" />
				</div>
			</div>
			<table class="tbstyle" border="0" id="rsTable" align="center"
				style="width: 50%">
				<thead>
					<tr>
						<td colspan="2" align="center">
							ͳ�Ʊ���
						</td>
					</tr>
				</thead>
				<tr style="height: 23px">
					<td align="right" width="25%">
						<font color="red">*</font>ѧ�꣺
					</td>
					<td align="left" width="">
						<html:select property="xn" style="" styleClass="select"styleId="xn" onchange="">
							<html:options collection="xnList" property="xn" labelProperty="xn" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right" width="">
						<font color="red">*</font>ѧ�ڣ�
					</td>
					<td align="left" width="">
						<html:select property="xq" style="" styleClass="select"styleId="xq" onchange="">
							<html:option value=""></html:option>
							<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right" width="">
						�꼶��
					</td>
					<td align="left">
						<html:select property="nj" onchange="" styleClass="select" style="" styleId="nj">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj" labelProperty="nj" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right" width="">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
						<html:select property="xydm" onchange="" styleClass="select" style="" styleId="xy">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm" labelProperty="xymc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
					 	<font color="red">*</font>�������ͣ�
					</td>
					<td>
						<html:select property="bblx" style="">
							<html:option value=""></html:option>
							<html:options collection="bblxList" property="en" labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
					 	������Ҫ�꼶��
					</td>
					<td>
						<input type="radio" name="needNj" value="yes"  checked="checked"/>��Ҫ
						<input type="radio" name="needNj" value="no"/>����Ҫ
					</td>
				</tr>
				<tr bgcolor="EEF4F9" align="center">
					<td colspan="2">
					<button class="button2" id="buttonSave" onclick="printBb()"
						style="width: 80px">
						ͳ��
					</button>
				</tr>
			</table>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('�����ɹ���');
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('����ʧ�ܣ�');
					</script>
				</logic:equal>
			</logic:present>
			<logic:present name="msg">
				<script>
					alert($("msg").value);
				</script>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
