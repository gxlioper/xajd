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
	function sendXx(){
		var lx = $("lx").value;
		if(lx == ""){
			alert("��ȷ�����ѧ��������");
			return false;
		}
		showTopWin('/xgxt/bjlh_sjwh.do?method=xsxxManage&lx='+lx,800,600);
	}
	
	function saveYjs(){
		var doType=$("doType").value;
		if(doType == "add"){
			if (confirm("ѧ�ű���󲻿��޸ģ���ȷ��")) {
			
			}else{
				return false;
			}
		}
		saveUpdate('/xgxt/bjlh_sjwh.do?method=yjsUpdate&doType=save','xh-xb-nj-xm-xydm-zydm-bjdm-sfzh-rxrq-xz');
	}
	
	</script>
	<body onload="">
		<html:form action="/bjlh_sjwh">
			<input type="hidden" id="doType" name="doType" value="${ doType}"/>
			<input type="hidden" id="realTable" name="realTable" value="${ realTable}"/>
			<input type="hidden" id="tableName" name="tableName" value="isFullQs"/>
			<input type="hidden" id="url" name="url" value="/xgxt/bjlh_sjwh.do?method=zsxxUpdate&doType=update"/>
			<html:hidden name='rs' property="zzbj" styleId="zzbj"/>
			<html:hidden name='rs' property="xydm" styleId="xydm"/>
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
							ѧ��ס����Ϣ
						</td>
					</tr>
				</thead>
				<tr style="height: 23px">
					<td align="right" width="20%">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<td align="left" width="30%">
						<logic:equal name="doType" value="add">
							<html:text name='rs' property="xh" styleId="xh" readonly="true"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button onclick="sendXx();return false;" class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:text name='rs' property="xh" styleId="xh" maxlength="20" readonly="true"/>
						</logic:notEqual>
					</td>
					<td align="right" width="20%">
						<font color="red">*</font>���ͣ�
					</td>
					<td align="left">
						<html:select name="rs" property="lx" style="" styleId="lx" onchange="">
							<html:option value=""></html:option>
							<html:options collection="xsList" property="en" labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						������
					</td>
					<td align="left">
						<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
					</td>
					<td align="right">
						<font color="red">*</font>�������ͣ�
					</td>
					<td align="left">
						<html:select property="fplx" style="" styleId="fplx" onchange="setQsList();">
							<html:options collection="fpList" property="en" labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						�Ա�
					</td>
					<td align="left">
						<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
					</td>
					<td align="right">
						<font color="red">*</font>У����
					</td>
					<td align="left">
						<html:select name="rs" property="xqdm" style="" styleId="xqdm" onchange="setLdList()">
							<html:options collection="xqList" property="dm" labelProperty="mc" />
						</html:select>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						�꼶��
					</td>
					<td align="left">
						<html:text name='rs' property="nj" styleId="nj" readonly="true"/>
					</td>
					<td align="right">
						<font color="red">*</font>¥����
					</td>
					<td align="left">
						<html:select name="rs" property="lddm" style="" styleId="lddm" onchange="setXqList();setCsList();setQsList();">
							<html:options collection="ldList" property="dm" labelProperty="mc" />
						</html:select>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
						<html:text name='rs' property="xymc" styleId="xymc" readonly="true"/>
					</td>
					<td align="right">
						<font color="red">*</font>����¥�㣺
					</td>
					<td align="left">
						<html:select name="rs" property="cs" style="" styleId="cs" onchange="setQsList();">
							<html:options collection="csList" property="dm" labelProperty="mc" />
						</html:select>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						רҵ��
					</td>
					<td align="left">
						<html:text name='rs' property="zymc" styleId="zymc" readonly="true"/>
					</td>
					<td align="right">
						<font color="red">*</font>���Һţ�
					</td>
					<td align="left">
						<html:select  name='rs' property="qsh" style="" styleId="qsh" onchange="setCwList()">
							<html:options collection="qsList" property="dm" labelProperty="mc" />
						</html:select>	
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						�༶��
					</td>
					<td align="left">
						<html:text name='rs' property="bjmc" styleId="bjmc" readonly="true"/>
					</td>
					<td align="right">
						<font color="red">*</font>��λ�ţ�
					</td>
					<td align="left">
						<html:select  name='rs' property="cwh" style="" styleId="cwh" onchange="">
							<html:options collection="cwList" property="dm" labelProperty="mc" />
						</html:select>
						<logic:equal name="rs" property="zzbj" value="yes">
						
						</logic:equal>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						<font color="red">*</font>��ס���ڣ�
					</td>
					<td align="left">
						<logic:equal name="doType" value="add">
						<html:text name="rs" property="rzrq" styleId="rzrq"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('rzrq','y-mm-dd');"/>	
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:text name='rs' property="rzrq" styleId="rzrq" readonly="true"/>
						</logic:notEqual>
					</td>
					<td align="right">
						�˷����ڣ�
					</td>
					<td align="left">
						<html:text name='rs' property="tfrq" styleId="tfrq" readonly="true"/>
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
					<logic:notEqual name="rs" property="zzbj" value="no">
					<button class="button2" id="buttonSave"
						onclick="saveUpdate('/xgxt/bjlh_sjwh.do?method=zsxxUpdate&doType=save','xh-lx-fplx-lddm-xqdm-cs-qsh-rzrq-cwh');"
						style="width: 80px">
						��	��
					</button>
					</logic:notEqual>
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
