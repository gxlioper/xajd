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
		<script type="text/javascript">
		function rychSqPrint(){
        	window.open('nbty_rych.do?method=rychDjb&pkValue=${pkValue}');
        }
		</script>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>	
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript">
	function dosubmit()
	{
		document.forms[0].submit();
	}
	</script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body onload="checkWinType();">		
		<html:form action="nbty_rych.do?method=oneRych&doType=modi" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��������� - �����ƺ����� - ��������ѯ	
				</div>
			</div>
				<input type ="hidden" id="save_xq" name="save_xq" value=${rs.xq }>
				<input type ="hidden" id="save_xh" name="save_xh" value=${rs.xh }>
				<input type ="hidden" id="save_xn" name="save_xn" value=${rs.xn }>
				<input type ="hidden" id="save_nd" name="save_nd" value=${rs.nd}>
				<input type="hidden" id="pkValue" name="pkValue" value="${pkValue}" />
				<fieldset>
					<legend>
						У��ס��ά��
					</legend>
					<table width="99%" id="rsT" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							<b>��д�����</b>
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right" style="width: 10%">
						ѧ�ţ�
					</td>
					<td>
						<bean:write name="rs" property="xh"/>
					</td>
					<td align="right" style="width: 10%">
						ѧ�꣺
					</td>
					<td align="left" style="width: 40%">
						<bean:write name="rs" property="xn"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						������
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="xm" />
						</logic:notEmpty>
					</td>
					<td align="right">
						ѧ�ڣ�
					</td>
					<td align="left">
						<bean:write name='rs' property="xqmc" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�Ա�
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="xb" />
					</logic:notEmpty>
					</td>
					<td align="right">
						<font color="red">*</font>�����ƺţ�
					</td>
					<logic:equal name="doType" value="save">
					<td align="left">
							<html:select property="save_rychdm" >
							<html:options collection="rychList" property="dm"
								labelProperty="mc" />
					</html:select>
					</td>
					</logic:equal>
					<logic:notEqual name="doType" value="save">
					<td align="left">
							<bean:write name="rs" property="rychmc"/>
					</td>		
					</logic:notEqual>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="xymc" />
					</logic:notEmpty>
					</td>
					<td align="right">
						����ְ��
					</td>
					<td align="left">
						<html:text name="rs" property="save_xrzw"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						רҵ��
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="zymc" />
					</logic:notEmpty>
					</td>
					<td align="right">
						ѧ��ƽ���ɼ�
					</td>
					<td align="left">
						<html:text name="rs" property="save_xnpjcj" onkeyup="value=value.replace(/[^\d]/g,'')"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�༶��
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="bjmc" />
					</logic:notEmpty>
					</td>
					<td align="right">
						����ְʱ�䣺
					</td>
					<td align="left">
						 <html:text name="rs"  property="save_rxzsj" 
							onclick="return showCalendar('save_rxzsj','ymmdd');" 
							onblur="dateFormatChg(this)" readonly="true" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						��ʱ�估���ƣ�
						<br />
						<span class="style1">(��400��)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" name="rs" style="width:98%"  property="save_hjsjmc" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						��Ҫ�¼���
						<br />
						<span class="style1">(��400��)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs" property="save_zysj" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						��ע��
						<br />
						<span class="style1">(��400��)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs" property="save_bz" onblur="chLeng(this,400)"/>
					</td>
				</tr>
			</table>
					<div class="buttontool" align="center">
						<logic:equal name="doType" value="save">
							<button type="button" class="button2"	onclick="dosubmit()" style="width:80px" id="buttonSave">
								�� ��
							</button>
						</logic:equal>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="Close();return false;" style="width:80px" id="buttonClose">
							�� ��
						</button>
						<logic:notEqual name="doType" value="save">
							<button type="button" class="button2" onclick="rychSqPrint()" style="width:80px" >
								��  ӡ
							</button>
						</logic:notEqual>
					</div>
				</fieldset>
		</html:form>
		<logic:present name="result">
		<intput type="hidden" id="message" value="${message}"/>
			<script>
				alert(document.getElementById('message').value);
				if(window.dialogArguments){
			 		window.dialogArguments.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:present>
	</body>
</html>