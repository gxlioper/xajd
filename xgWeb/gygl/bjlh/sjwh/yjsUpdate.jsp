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
							�о�����Ϣ
						</td>
					</tr>
				</thead>
				<tr style="height: 23px">
					<td align="right">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:text name='rs' property="fqrzxh" styleId="fqrzxh" maxlength="20" onchange="isCz('fqrzxh||lx',this.value+'�о���')"/>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:text name='rs' property="fqrzxh" styleId="fqrzxh" maxlength="20" readonly="true"/>
						</logic:notEqual>
					</td>
					<td align="right">
						<font color="red">*</font>�꼶��
					</td>
					<td align="left">
						<html:select name="rs" property="nj" style="" styleId="nj" onchange="setZyList('�о���');setBjList('�о���')">
							<html:options collection="njList" property="dm" labelProperty="mc" />
						</html:select>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						<font color="red">*</font>������
					</td>
					<td align="left">
						<html:text name='rs' property="xm" styleId="xm" maxlength="20"/>
					</td>
					<td align="right">
						<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
						<html:select name="rs" property="xydm" style="" styleId="xydm" onchange="setZyList('�о���');setBjList('�о���')">
							<html:options collection="xyList" property="dm" labelProperty="mc" />
						</html:select>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						<font color="red">*</font>�Ա�
					</td>
					<td align="left">
						<html:select name="rs" property="xb" style="" styleId="xb">
							<html:option value=""></html:option>
							<html:options collection="xbList" property="en" labelProperty="cn" />
						</html:select>
					</td>
					<td align="right">
						<font color="red">*</font>רҵ��
					</td>
					<td align="left">
						<html:select name="rs" property="zydm" style="" styleId="zydm" onchange="setBjList('�о���')">
							<html:options collection="zyList" property="dm" labelProperty="mc" />
						</html:select>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						���壺
					</td>
					<td align="left">
						<html:select name="rs" property="mz" style="" styleId="mz">
							<html:options collection="mzList" property="mzdm" labelProperty="mzmc" />
						</html:select>
					</td>
					<td align="right">
						<font color="red">*</font>�༶��
					</td>
					<td align="left">
						<html:select name="rs" property="bjdm" style="" styleId="bjdm">
							<html:options collection="bjList" property="dm" labelProperty="mc" />
						</html:select>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						������ò��
					</td>
					<td align="left">
						<html:select name="rs" property="zzmm" style="" styleId="zzmm">
							<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc" />
						</html:select>
					</td>
					<td align="right">
						�������ڣ�
					</td>
					<td align="left">
						<html:text name="rs" property="csrq" styleId="csrq"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('csrq','y-mm-dd');"/>	
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						��ߣ�
					</td>
					<td align="left">
						<html:text name='rs' property="sg" styleId="sg" 
						onkeypress="return onlyNum(this,3)" maxlength="3" 
						style="width:10%;ime-mode:disabled"/>CM
					</td>
					<td align="right">
						���أ�
					</td>
					<td align="left">
						<html:text name='rs' property="tz" styleId="tz" 
						onkeypress="return onlyNum(this,3)" maxlength="3" 
						style="width:10%;ime-mode:disabled"/>KG
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						<font color="red">*</font>���֤�ţ�
					</td>
					<td align="left">
						<html:text name='rs' property="sfzh" styleId="sfzh" maxlength="20"/>
					</td>
					<td align="right">
						��ϵ�绰��
					</td>
					<td align="left">
						<html:text name='rs' property="lxdh" styleId="lxdh" maxlength="20"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						���᣺
					</td>
					<td align="left">
						<html:text name='rs' property="jg" styleId="jg" maxlength="20"/>
					</td>
					<td align="right">
						��Դ������
					</td>
					<td align="left">
						<html:text name='rs' property="lydq" styleId="lydq" maxlength="50"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						<font color="red">*</font>��ѧ���ڣ�
					</td>
					<td align="left">
						<html:text name="rs" property="rxrq" styleId="rxrq"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('rxrq','y-mm-dd');"/>	
					</td>
					<td align="right">
						<font color="red">*</font>ѧ�ƣ�
					</td>
					<td align="left">
						<html:text name='rs' property="xz" styleId="xz" 
						onkeypress="return onlyNum(this,1)" maxlength="1" 
						style="width:10%;ime-mode:disabled"/>��
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
						onclick="saveYjs();"
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
