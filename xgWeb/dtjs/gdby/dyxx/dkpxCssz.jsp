<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script type="text/javascript" src="js/pjpy/pjpy_szyqxy.js"></script>
	<script language="javascript"  src="js/sztzFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="js/commanFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script type="text/javascript">
		function saveCssz(){
			var xxtd = eval($('xxtdbl').value);
			var xxjl = eval($('xxjlbl').value);
			var xxxg = eval($('xxxgbl').value);
			
			var zbl = xxtd + xxjl + xxxg;
			
			if(zbl>100){
				alert('���б��ʺͲ��ܳ���100%');
				return false;
			}else{
				document.forms[0].action='gdby_dtjs.do?method=pxxxCssz&doType=save';
				document.forms[0].submit();
			}
		}
	</script>
	<body onload="">
		<html:form action="/gdby_dtjs.do">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã����β�������
				</div>
			</div>
			<table class="tbstyle" border="0" id="rsTable" align="center"
				style="width: 100%">
				<thead>
					<tr>
						<td colspan="2" align="center">
							������ѵ��������
						</td>
					</tr>
				</thead>
				<tr style="height: 23px">
					<td align="right" width="70%">
						ѧ�꣺
					</td>
					<td align="left" width="30%">
						<html:select property="xn" value="${xn }" onchange="refreshForm('gdby_dtjs.do?method=pxxxCssz');">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
					</td>
				</tr>	
				<tr style="height: 23px">
					<td align="right">
						ѧϰ̬�ȣ�
					</td>
					<td align="left">
						<html:text property="xxtdbl" styleId="xxtdbl" onkeyup="value=value.replace(/[^\d]/g,'') "
						 value="${rs.xxtdbl }" maxlength="10"/><font color="red">%</font>
					</td>
				</tr>	
				<tr style="height: 23px">
					<td align="right">
						ѧϰ���ɣ�
					</td>
					<td align="left">
						<html:text property="xxjlbl" styleId="xxjlbl" onkeyup="value=value.replace(/[^\d]/g,'') "
						value="${rs.xxjlbl }" maxlength="10"/><font color="red">%</font>
					</td>
				</tr>	
				<tr style="height: 23px">
					<td align="right">
						ѧϰЧ����
					</td>
					<td align="left">
						<html:text property="xxxgbl" styleId="xxxgbl" onkeyup="value=value.replace(/[^\d]/g,'') "
						value="${rs.xxxgbl }" maxlength="10" /><font color="red">%</font>
					</td>
				</tr>	
				<tr bgcolor="EEF4F9" align="center">
					<td colspan="2">
					<logic:notEqual name="doType" value="view">
					<button type="button" class="button2" id="buttonSave" onclick="saveCssz()"
						style="width: 80px">
						��	��
					</button>
					</logic:notEqual>
					&nbsp;&nbsp;
					<button type="button" class="button2" id="buttonClose" onclick="Close();return false;"
						style="width: 80px">
						��	��
					</button>
					</td>
				</tr>
			</table>
			<div id="tmpdiv1"></div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('�����ɹ���');
<%--						window.dialogArguments.document.getElementById("search_go").click();--%>
						window.close();
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('����ʧ�ܣ�');
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
