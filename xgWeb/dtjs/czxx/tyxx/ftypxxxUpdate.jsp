<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSjxyDtjsDAO.js'></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script type="text/javascript">	

	</script>
	
	<body onload="">
		<html:form action="/czxxDtjsTyxx">
		<input type="hidden" id="doType" name="doType" value="${doType }"/>
		<input type="hidden" id="tableName" name="tableName" value="view_czxx_tyxx"/>
		<input type="hidden" id="zd" name="zd" value="sfty"/>
		<input type="hidden" id="va" name="va" value="��"/>
		<input type="hidden" id="lx" name="lx" value="����Ա"/>
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm-xb-xydm-xymc-zymc-bjmc"/>
		<input type="hidden" id="url" name="url" value="/xgxt/czxxDtjsTyxx.do?method=ftypxxxUpdate&doType=add"/>
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
							����Ա��ѵ��Ϣ
						</td>
					</tr>
				</thead>
				<tr style="height: 23px">
					<td align="right" width="10%">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<td align="left" width="40%">
						<logic:equal name="doType" value="add">
							<html:text name='rs' property="xh" styleId="xh" readonly="true"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="sendXx();return false;" class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:text name='rs' property="xh" styleId="xh" readonly="true" />
						</logic:notEqual>
					</td>
					<td align="right">
						<font color="red">*</font>��ȣ�
					</td>
					<td align="left">
						<logic:notEqual name="doType" value="add">
							<html:hidden name="rs" property="nd" styleId="nd"/>
							<bean:write name="rs" property="nd"/>	
						</logic:notEqual>
						<logic:equal value="add" name="doType">
							<html:select name="rs" property="nd" styleId="nd">
								<html:options collection="ndList" property="nd" labelProperty="nd"/>
							</html:select>
						</logic:equal>						
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
						<font color="red">*</font>ѧ�꣺
					</td>
					<td align="left">
						<logic:notEqual name="doType" value="add">
							<html:hidden name="rs" property="xn" styleId="xn"/>
							<bean:write name="rs" property="xn"/>	
						</logic:notEqual>
						<logic:equal value="add" name="doType">
							<html:select name="rs" property="xn" styleId="xn">
							<html:options collection="xnList" property="xn" labelProperty="xn"/>
						</html:select>
						</logic:equal>	
						
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
						<font color="red">*</font>ѧ�ڣ�
					</td>
					<td align="left">
						<logic:notEqual name="doType" value="add">
							<html:hidden name="rs" property="xq" styleId="xq"/>
							<bean:write name="rs" property="xqmc"/>	
						</logic:notEqual>
						<logic:equal value="add" name="doType">
							<html:select name="rs" property="xq" styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
							</html:select>
						</logic:equal>
						
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
						<html:hidden name='rs' property="xydm" styleId="xydm"/>
						<html:text name='rs' property="xymc" styleId="xymc" readonly="true"/>
					</td>
					<td align="right">
						<font color="red">*</font>��ѵ��Ŀ��
					</td>
					<td align="left">
						<logic:notEqual name="doType" value="add">
							<html:hidden name="rs" property="pxxmdm" styleId="pxxmdm"/>
							<bean:write name="rs" property="pxxmmc"/>	
						</logic:notEqual>
						<logic:equal value="add" name="doType">
							<html:select property="pxxmdm" styleId="pxxmdm" name="rs">
								<html:option value=""></html:option>
								<html:options collection="pxxmList" property="dm" labelProperty="mc"/>
							</html:select>
						</logic:equal>
						
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
						<font color="red">*</font>��ѵʱ�䣺
					</td>
					<td align="left">
						<logic:notEqual name="doType" value="add">
							<html:hidden name="rs" property="pxsj" styleId="pxsj"/>
							<bean:write name="rs" property="pxsj"/>	
						</logic:notEqual>
						<logic:equal value="add" name="doType">
							<html:text name="rs" property="pxsj" styleId="pxsj" readonly="true"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('pxsj','y-mm-dd');"/>	
						</logic:equal>
						
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
						��ѵ�ɼ���
					</td>
					<td align="left">
						<html:text name="rs" property="pxcj" styleId="pxcj" onkeyup="value=value.replace(/[^\d|.]/g,'') " maxlength="8"/>	
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						��ע��
					</td>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="bz" styleId="bz" rows="5" style="width: 100%" onblur="chLeng(this,60)"/>
					</td>
				</tr>
				<tr bgcolor="EEF4F9" align="center">
					<td colspan="4">
					<logic:notEqual name="doType" value="view">
					<button type="button" class="button2" 
							id="buttonSave" 
							onclick="if(checkXnNd('xn','nd')){saveUpdate('/xgxt/czxxDtjsTyxx.do?method=ftypxxxUpdate&doType=save','xh-pxxmdm-pxsj-xn-nd-xq')}"
							style="width: 80px">
							��	��
					</button>
					&nbsp;&nbsp;
					</logic:notEqual>
					<button type="button" class="button2" id="buttonClose" onclick="Close();return false;"
						style="width: 80px">
						��	��
					</button>
					</td>
				</tr>
			</table>
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