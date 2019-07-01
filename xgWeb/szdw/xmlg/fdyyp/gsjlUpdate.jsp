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
	<script type='text/javascript' src='/xgxt/dwr/interface/zjlgZbglDAO.js'></script>
	<script type="text/javascript">	
	function jsghSearch(){
		var url = "/xgxt/xmlgfdyyp.do?method=fdyxxSearch";
		showTopWin(url,600,500);
	}
	
	function saveGsxx(){
		var zgh = $("zgh").value;
		var xn = $("xn").value;
		var xq = $("xq").value;
		if(zgh==" "){
			alert("ְ����Ϊ�գ���ȷ�ϣ�");
			return false;
		}
		if(xn=="" || xq == ""){
			alert("ѧ��,ѧ�ڲ���Ϊ�գ�");
			return false;
		}
		var url = "/xgxt/xmlgfdyyp.do?method=gsjlUpdate&doType=save";
		showTips('���������У���ȴ�......');
		$("buttonSave").disabled=true;
		$("buttonClose").disabled=true;
		refreshForm(url);
	}
	

	</script>
	
	<body onload="">
		<html:form action="/xmlgfdyyp">
		<input type="hidden" id="url" name="url" value="/xgxt/xmlgfdyyp.do?method=gsjlUpdate"/>
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm-xb-xymc-zymc-bjmc"/>
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
							�ش��ʧ��¼
						</td>
					</tr>
				</thead>
				<tr style="height: 23px">
					<td align="right">
						ְ���ţ�
					</td>
					<td align="left">
						<html:text name="rs" property="zgh" styleId="zgh" readonly="true"/>
						<logic:empty name="doType">
						<button type="button" onclick="jsghSearch()"class="button2" id="buttonFindJsgh">
							ѡ��
						</button>
						</logic:empty>
					</td>
					<td align="right">
						ѧ�꣺
					</td>
					<td align="left">
						<logic:empty name="doType">
							<html:select name="rs" property="xn" style="" styleId="xn">
								<html:options collection="xnList" property="xn" labelProperty="xn" />
							</html:select>
						</logic:empty>
						<logic:notEmpty name="doType">
							<html:text name="rs" property="xn" styleId="xn" readonly="true"/>
						</logic:notEmpty>
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
						ѧ�ڣ�
					</td>
					<td align="left">
						<logic:empty name="doType">
							<html:select name="rs" property="xq" style="" styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
							</html:select>
						</logic:empty>
						<logic:notEmpty name="doType">
							<html:text name="rs" property="xq" styleId="xq" readonly="true"/>
						</logic:notEmpty>
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
						�������ƣ�
					</td>
					<td align="left">
						<html:text name='rs' property="bmmc" styleId="bmmc" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						��ʧʱ�䣺
					</td>
					<td align="left">
						<html:text name="rs" property="gssj" styleId="gssj"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('gssj','y-mm-dd');"/>
					</td>
					<td align="right">
						
					</td>
					<td align="left">
						
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						��ʧ���ݣ�
					</td>
					<td align="right" colspan="3">
						<html:textarea name='rs' property="gsnr" styleId="gsnr" rows="5" style="width: 100%" onblur="chLeng(this,250)"/>
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
					<button type="button" class="button2" id="buttonSave" onclick="saveGsxx()"
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
</html>
