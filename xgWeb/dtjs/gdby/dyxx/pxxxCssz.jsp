<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script type="text/javascript" src="js/pjpy/pjpy_szyqxy.js"></script>
	<script language="javascript"  src="js/sztzFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script type="text/javascript">	
	function time(id){
		return showCalendar(id,'y-mm-dd');
	}
	
	function saveZsdy(){
		if($("gzkssj")){
			if($("gzkssj").value == ""){
				alert("��ȷ����ѵʱ��");
				return false;
			}
		}
		var url = "/xgxt/czxxDtjsDyxx.do?method=pxxxUpdate&doType=add";
		$("buttonSave").disabled=true;
		$("buttonClose").disabled=true;
		refreshForm(url);
	}
	
	function savePxxx(){
	
		if($("pxsj").value == ""){
			alert("��ȷ����ѵʱ��");
			return false;
		}
		
		if($("pxmc").value == ""){
			alert("��ȷ����ѵ����");
			return false;
		}
		if($("rsNum").value == "" || $("rsNum").value == "0"){
			alert("����ѵʱ����δȷ��������������ȷ�Ϻ�����!");
			return false;
		}
		saveUpdate('/xgxt/czxxDtjsDyxx.do?method=pxxxUpdate&doType=save','pxmc-pxsj')
	}
	</script>
	<body onload="">
		<html:form action="/czxxDtjsDyxx">
		<input type="hidden" id="doType" name="doType" value="${doType }"/>
		<input type="hidden" id="rsNum" name="rsNum" value="${rsNum }"/>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã�<bean:write name="title" />
				</div>
			</div>
			<table class="tbstyle" border="0" id="rsTable" align="center"
				style="width: 100%">
				<thead>
					<tr>
						<td colspan="4" align="center">
							������ѵ��������
						</td>
					</tr>
				</thead>
				<tr style="height: 23px">
					<td align="right">
						<font color="red">*</font>��ѵ���ƣ�
					</td>
					<td align="left">
						<html:text name='rs' property="pxmc" styleId="pxmc" maxlength="25"/>
					</td>
					<td align="right">
						<font color="red">*</font>��ѵʱ�䣺
					</td>
					<td align="left">
						<html:text name='rs' property="pxsj" styleId="pxsj" readonly="true"/>
						<logic:equal name="doType" value="add">
						<button type="button" onclick="dyzz()"
						class="btn_01" id="buttonFindStu">
						ѡ��
						</button>
						</logic:equal>
					</td>
				</tr>	
				<tr style="height: 23px">
					<td align="right">
						��ѵ�ص㣺
					</td>
					<td align="left" colspan="3">
						<html:text name='rs' property="pxdd" styleId="pxdd" maxlength="50" style="width:100%"/>
					</td>
				</tr>	
				<tr style="height: 23px">
					<td align="right">
						��ѵ���ݣ�<br><font color="red">(��250��)</font>
					</td>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="pxnr" styleId="pxnr" rows="5" style="width: 100%" onblur="chLeng(this,250)"/>
					</td>
				</tr>	
				<tr style="height: 23px">
					<td align="right">
						��ע��<br><font color="red">(��250��)</font>
					</td>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="bz" styleId="bz" rows="5" style="width: 100%" onblur="chLeng(this,250)"/>
					</td>
				</tr>
				<tr bgcolor="EEF4F9" align="center">
					<td colspan="4">
					<logic:notEqual name="doType" value="view">
					<button type="button" class="button2" id="buttonSave" onclick="savePxxx()"
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
						window.dialogArguments.document.getElementById("search_go").click();
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
