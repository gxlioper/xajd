<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script language="javascript" src="js/sztzFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript"  src="js/pjpy/pjpyFunction.js"></script>
	<script language="javascript"  src="js/pjpy/nbcs/pjpy_wjdc.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyNbcsDAO.js'></script>	
	<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyInfo.js'></script>
	<script type="text/javascript">	
function saveBl(){
	var xn = $("xn").value;
	var bl = $("bl").value;
	var url = "/xgxt/nbcsPxpj.do?method=djrBlsz&doType=save";
	
	if(bl == ""){
		alert("��������Ϊ�գ���ȷ�ϣ���");
		return false;
	}
	
	if(bl >100){
		alert("�������ܴ���100%����ȷ�ϣ���");
		return false;
	}
	
	var msg = xn +"ѧ�����˱�������Ϊ"+bl+"%,��ȷ��!";
	
	if (confirm(msg)) {
		saveUpdate(url,'');
	}
}
	</script>
	<body onload="">
		<html:form action="/nbcsPxpj">
			<!-- ������ -->
			<%@ include file="/pjpy/hiddenValue.jsp"%>
			<!-- ������ end-->
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã�<bean:write name="title" />
				</div>
			</div>
			<table class="tbstyle" border="0" id="rsTable" align="center"
				style="width: 100%">
				<thead>
					<tr>
						<td colspan="2" align="center">
							����˱�������
						</td>
					</tr>
				</thead>
				<tr style="height: 23px">
					<td align="right" width="30%">
						ѧ�꣺
					</td>
					<td align="left" width="70%" colspan="">
						${xn }
						<html:hidden name="rs" property="xn"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right" width="30%">
						<font color="red">*</font>������
					</td>
					<td align="left" width="70%" colspan="">
						<html:text name="rs" property="bl" 
							onkeypress="return onlyNum(this,3)" 
							maxlength="3"
							style="width:15%;ime-mode:disabled"/>%
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right" width="30%">
						��ע��
						<br>
						<font color="red">(��500��)</font>
					</td>
					<td align="left" width="70%" colspan="">
						<html:textarea name="rs" property="bz"style="width: 90%" rows="5" onblur="chLeng(this,500)"></html:textarea>
					</td>
				</tr>
				<tr bgcolor="EEF4F9" align="center">
					<td colspan="2">
					<button type="button" class="button2" id="buttonSave" onclick="saveBl()"
						style="width: 80px">
						��&nbsp;&nbsp;��
					</button>
					&nbsp;&nbsp;
					<button type="button" class="button2" id="buttonClose" onclick="Close();return false;"
						style="width: 80px">
						��&nbsp;&nbsp;��
					</button>
				</tr>
			</table>
			<logic:notEmpty name="message">
				<script>
					alert($("message").value);
					$("message").value = "";
					$("doType").value = "";
					dialogArgumentsQueryChick();
					window.close();
				</script>
			</logic:notEmpty>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
