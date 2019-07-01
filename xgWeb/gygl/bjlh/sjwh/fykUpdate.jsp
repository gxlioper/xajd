<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script language="javascript"  src="js/sztzFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/gygl/bjlh/bjlhFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getBjlhGyglDAO.js'></script>
	<script type="text/javascript">	
	function saveCjs(){
		var doType=$("doType").value;
		if(doType == "add"){
			if (confirm("�ɹ������Ϣ��\n¥�������������ҺŲ��ɸ���,\n��ȷ��¼��������")) {
			
			}else{
				return false;
			}
		}
		saveUpdate('/xgxt/bjlh_sjwh.do?method=fykUpdate&doType=save','lddm-cs-cws-qsh');
	}
	
	function chPk(){
		var lddm=$("lddm").value;
		var cs =$("cs").value;
		var qsh=$("qsh").value;
		if(lddm != "" && cs!="" && qsh!=""){
			isCz("lddm||cs||qsh",lddm+cs+qsh);
		}
	}
	</script>
	<body onload="">
		<html:form action="/bjlh_sjwh">
			<input type="hidden" id="doType" name="doType" value="${ doType}"/>
			<input type="hidden" id="realTable" name="realTable" value="${ realTable}"/>
			<input type="hidden" id="url" name="url" value="${ url}"/>
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
							��Դ����Ϣ
						</td>
					</tr>
				</thead>
				<tr style="height: 23px">
					<td align="right" width="20%">
						У����
					</td>
					<td align="left" width="30%">
						<logic:equal name="doType" value="add">
						<html:select name="rs" property="xqdm" style="" styleId="xqdm" onchange="setLdList()">
							<html:options collection="xqList" property="dm" labelProperty="mc" />
						</html:select>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:hidden name="rs" property="xqdm" styleId="xqdm"/>
							<bean:write name="rs" property="xqmc"/>
						</logic:notEqual>
					</td>
					<td align="right" width="20%">
						�����ǣ�
					</td>
					<td align="left">
						<html:select name="rs" property="fbbj" style="" styleId="fbbj">
							<html:options collection="blList" property="en" labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						<font color="red">*</font>¥����
					</td>
					<td align="left">
						<logic:equal name="doType" value="add">
						<html:select name="rs" property="lddm" style="" styleId="lddm" onchange="setXqList();setCsList();chPk();">
							<html:options collection="ldList" property="dm" labelProperty="mc" />
						</html:select>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:hidden name="rs" property="lddm" styleId="lddm"/>
							<bean:write name="rs" property="ldmc"/>
						</logic:notEqual>
					</td>
					<td align="right">
						<font color="red">*</font>��λ����
					</td>
					<td align="left">
						<html:text name="rs" property="cws" styleId="cws"
						onkeypress="return onlyNum(this,10)" 
						maxlength="10" style="ime-mode:disabled"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						<font color="red">*</font>����������
					</td>
					<td align="left">
						<logic:equal name="doType" value="add">
						<html:select name="rs" property="cs" style="" styleId="cs" onchange="chPk()">
							<html:options collection="csList" property="dm" labelProperty="mc" />
						</html:select>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:hidden name="rs" property="cs" styleId="cs"/>
							��<bean:write name="rs" property="cs"/>��
						</logic:notEqual>
					<td align="right">
						���ҵ绰��
					</td>
					<td align="left">
						<html:text name="rs" property="qsdh" styleId="qsdh" style="" maxlength="20"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						<font color="red">*</font>���Һţ�
					</td>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:text name="rs" property="qsh" styleId="qsh" style=""  maxlength="15" onchange="chPk()"/>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:hidden name="rs" property="qsh" styleId="qsh"/>
							<bean:write name="rs" property="qsh"/>
						</logic:notEqual>
					</td>
					<td align="right">
						�շѱ�׼��
					</td>
					<td align="left">
						<html:text name="rs" property="sfbz" styleId="sfbz" style="" maxlength="10"/>
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
					<button class="button2" id="buttonSave"
						onclick="saveCjs();"
						style="width: 80px">
						��	��
					</button>
					</logic:notEqual>
					&nbsp;&nbsp;
					<button class="button2" id="buttonClose" onclick="Close();return false;"
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
						dialogArgumentsQueryChick();
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
