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
<html:html>
<base target="_self" />
<head>


	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function dataDoSave(mustFill) {
			var eles = mustFill.split("-");
			for (i = 0; i < eles.length; i++) {
				if (document.getElementById(eles[i]).value == "") {
					alert("请将带\"*\"号的项目输入完整！");
					return false;
				}
			}
			
			var yxlxdh = document.getElementById('yxlxdh').value;
			var yzbm = document.getElementById('yzbm').value;
			var fqlxdh = document.getElementById('fqlxdh').value;
			var mqlxdh = document.getElementById('mqlxdh').value;
			var dkze = document.getElementById('dkze').value;
			var dknx = document.getElementById('dknx').value;
			var ht1_zje = document.getElementById('ht1_zje').value;
			var ht1_hkjzzh = document.getElementById('ht1_hkjzzh').value;
			var ht2_zje = document.getElementById('ht2_zje').value;
			var ht2_hkjzzh = document.getElementById('ht2_hkjzzh').value;
			var ht3_zje = document.getElementById('ht3_zje').value;
			var ht3_hkjzzh = document.getElementById('ht3_hkjzzh').value;
			var ht4_zje = document.getElementById('ht4_zje').value;
			var ht4_hkjzzh = document.getElementById('ht4_hkjzzh').value;
			
			if((yxlxdh != null) && (yxlxdh != "") && (!isNumber(yxlxdh))){
				alert("有效联系电话必须为整数！");
				return false;
			}
			if((yzbm != null) && (yzbm != "") && (!isNumber(yzbm))){
				alert("邮政编码必须为整数！");
				return false;
			}
			if((fqlxdh != null) && (fqlxdh != "") && (!isNumber(fqlxdh))){
				alert("父亲联系电话必须为整数！");
				return false;
			}
			if((mqlxdh != null) && (mqlxdh != "") && (!isNumber(mqlxdh))){
				alert("母亲联系电话必须为整数！");
				return false;
			}
			if((dkze != null) && (dkze != "") && (!isNumber(dkze))){
				alert("贷款总额必须为整数！");
				return false;
			}
			if((dknx != null) && (dknx != "") && (!isNumber(dknx))){
				alert("贷款年限必须为整数！");
				return false;
			}
			if((ht1_zje != null) && (ht1_zje != "") && (!isNumber(ht1_zje))){
				alert("合同1总金额必须为整数！");
				return false;
			}
			if((ht1_hkjzzh != null) && (ht1_hkjzzh != "") && (!isNumber(ht1_hkjzzh))){
				alert("合同1还款介质帐号必须为整数！");
				return false;
			}
			if((ht2_zje != null) && (ht2_zje != "") && (!isNumber(ht2_zje))){
				alert("合同2总金额必须为整数！");
				return false;
			}
			if((ht2_hkjzzh != null) && (ht2_hkjzzh != "") && (!isNumber(ht2_hkjzzh))){
				alert("合同2还款介质帐号必须为整数！");
				return false;
			}
			if((ht3_zje != null) && (ht3_zje != "") && (!isNumber(ht3_zje))){
				alert("合同3总金额必须为整数！");
				return false;
			}
			if((ht3_hkjzzh != null) && (ht3_hkjzzh != "") && (!isNumber(ht3_hkjzzh))){
				alert("合同3还款介质帐号必须为整数！");
				return false;
			}
			if((ht4_zje != null) && (ht4_zje != "") && (!isNumber(ht4_zje))){
				alert("合同4总金额必须为整数！");
				return false;
			}
			if((ht4_hkjzzh != null) && (ht4_hkjzzh != "") && (!isNumber(ht4_hkjzzh))){
				alert("合同4还款介质帐号必须为整数！");
				return false;
			}
			var url = "/xgxt/zjys_dkxx_info.do?doType=save&pk=";
			url += window.document.forms[0].pk.value;
			document.forms[0].action = url;
			document.forms[0].submit();
		}
		
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
		
		function checkSfzh(sfzh) {
   			sfzh=sfzh.toLowerCase()
			var OldID;
			if(sfzh.length == 15){
				OldID = sfzh;
				return true;
			}else if(sfzh.length == 18){
				OldID = sfzh.substring(0, 6) + sfzh.substring(8,sfzh.length-1);
			}else{
				return false;
			}
			var W = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
			var A = new Array("1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2");
			var i, j, S;
			var NewID, ID, strNF;
			NewID = OldID.substring(0, 6) + "19" + OldID.substring(6,OldID.length);
			S = 0;
			for( i = 0; i <= 16; i++){
				j = NewID.substring(i, i+1) * W[i];
				S = S + j;
			}
			S = S % 11;
			if(sfzh != NewID + A[S]){
				return false;
			}
			return true;
		}
	</script>
</head>

<body>
		<html:form action="/zjys_dkxx_info.do" method="post">
			<input type="hidden" id="url" name="url"
				value="/zjys_dkxx_info.do" />
			<input type="hidden" id="doType" name="doType"
				value="<bean:write name='doType' scope="request" />" />
			<input type="hidden" id="pk" name="pk"
				value="xh||bzffny" />
			<input type="hidden" id="pkValue" name="pkValue"
				value="<bean:write name='pkValue' scope="request" />" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />


			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("保存成功！");
	         	window.close();
	         	dialogArgumentsQueryChick();
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("保存失败！");
	         	window.close();
	         	</script>
				</logic:match>
			</logic:present>
			<table width="100%" border="1" class="tbstyle">
				<thead>
					<tr>
						<td colspan="4" align="center">
							贷款信息
						</td>
					</tr>
				</thead>
				<tr>
					<td align="center" scope="col">
						<font color="red">*</font>学号：
					</td>
					<td align="left" scope="col">
						<html:text name='rs' property="xh" styleId="xh"
								onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true"/>
							<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
					</td>
					<td scope="col">
						<div align="center">
							姓名：
						</div>
					</td>
					<td scope="col">
						<div align="left">
							<input type="text" id="xm" name="xm" value="<bean:write name='rs' property="xm" />" readonly="true">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							性别：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="xb" readonly="readonly" name="xb" value="<bean:write name='rs' property="xb" />" readonly="true">
						</div>
					</td>
					<td>
						<div align="center">
							身份证号：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="sfzh" readonly="readonly" name="sfzh" value="<bean:write name='rs' property="sfzh" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="xymc" readonly="readonly" name="xymc" value="<bean:write name='rs' property="xymc" />" readonly="true">
						</div>
					</td>
					<td>
						<div align="center">
							专业：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="zymc" readonly="readonly" name="zymc" value="<bean:write name='rs' property="zymc" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							班级名称：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="bjmc" readonly="readonly" name="bjmc" value="<bean:write name='rs' property="bjmc" />" readonly="true">
						</div>
					</td>
					<td>
						<div align="center">
							民族名称：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="mzmc" name="mzmc" maxlength="60" value="<bean:write name='rs' property="mzmc" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							入学年份：
						</div>
					</td>
					<td>
						<div align="left">
						<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('rxnf','y-mm');"
								value="<bean:write name='rs' property="rxnf" />"
								name="rxnf" id="rxnf" />
						</div>
					</td>
					<td scope="row">
						<div align="center">
							毕业时间：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('bynf','y-mm');"
								value="<bean:write name='rs' property="bynf" />"
								name="bynf" id="bynf" />
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							有效联系电话：
						</div>
					</td>
					<td>
						<div align="left">
						<input type="text" id="yxlxdh" name="yxlxdh" maxlength="14" value="<bean:write name='rs' property="yxlxdh" />"
						onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td scope="row">
						<div align="center">
							邮政编码：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="yzbm" name="yzbm" maxlength="6" value="<bean:write name='rs' property="yzbm" />"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							家庭住址：
						</div>
					</td>
					<td colspan="3">
						<div align="left">
						<input type="text" id="jtzz" name="jtzz" maxlength="100" value="<bean:write name='rs' property="jtzz" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							父亲姓名：
						</div>
					</td>
					<td>
						<div align="left">
						<input type="text" id="fqxm" name="fqxm" maxlength="40" value="<bean:write name='rs' property="fqxm" />">
						</div>
					</td>
					<td scope="row">
						<div align="center">
							母亲姓名：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="mqxm" name="mqxm" maxlength="40" value="<bean:write name='rs' property="mqxm" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							父亲工作单位：
						</div>
					</td>
					<td>
						<div align="left">
						<input type="text" id="fqgzdw" name="fqgzdw" maxlength="100" value="<bean:write name='rs' property="fqgzdw" />">
						</div>
					</td>
					<td scope="row">
						<div align="center">
							母亲工作单位：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="mqgzdw" name="mqgzdw" maxlength="100" value="<bean:write name='rs' property="mqgzdw" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							父亲联系电话：
						</div>
					</td>
					<td>
						<div align="left">
						<input type="text" id="fqlxdh" name="fqlxdh" maxlength="14" value="<bean:write name='rs' property="fqlxdh" />"
						onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td scope="row">
						<div align="center">
							母亲联系电话：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="mqlxdh" name="mqlxdh" maxlength="14" value="<bean:write name='rs' property="mqlxdh" />"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							见证人(1)：
						</div>
					</td>
					<td>
						<div align="left">
						<input type="text" id="jzr1" name="jzr1" maxlength="40" value="<bean:write name='rs' property="jzr1" />">
						</div>
					</td>
					<td scope="row">
						<div align="center">
							见证人(2)：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="jzr2" name="jzr2" maxlength="40" value="<bean:write name='rs' property="jzr2" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							贷款总额：
						</div>
					</td>
					<td>
						<div align="left">
						<input type="text" id="dkze" name="dkze" maxlength="10" value="<bean:write name='rs' property="dkze" />"
						onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td scope="row">
						<div align="center">
							贷款年限(年)：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="dknx" name="dknx" maxlength="10" value="<bean:write name='rs' property="dknx" />"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row" colspan="4">
						<div align="center" class="style1 style3">
							<strong>合同1</strong>
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							合同号：
						</div>
					</td>
					<td>
						<div align="left">
						<input type="text" id="ht1_hth" name="ht1_hth" maxlength="50" value="<bean:write name='rs' property="ht1_hth" />">
						</div>
					</td>
					<td scope="row">
						<div align="center">
							经办金融机构：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="ht1_jbjrjg" name="ht1_jbjrjg" maxlength="50" value="<bean:write name='rs' property="ht1_jbjrjg" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							批准日期：
						</div>
					</td>
					<td>
						<div align="left">
						<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('ht1_pzrq','y-mm-dd');"
								value="<bean:write name='rs' property="ht1_pzrq" />"
								name="ht1_pzrq" id="ht1_pzrq" />
						</div>
					</td>
					<td scope="row">
						<div align="center">
							总金额：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="ht1_zje" name="ht1_zje" maxlength="10" value="<bean:write name='rs' property="ht1_zje" />"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							经办员：
						</div>
					</td>
					<td>
						<div align="left">
						<input type="text" id="ht1_jby" name="ht1_jby" maxlength="40" value="<bean:write name='rs' property="ht1_jby" />">
						</div>
					</td>
					<td scope="row">
						<div align="center">
							批准行长：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="ht1_pzhz" name="ht1_pzhz" maxlength="40" value="<bean:write name='rs' property="ht1_pzhz" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							还款开始日期：
						</div>
					</td>
					<td>
						<div align="left">
						<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('ht1_hkkssj','y-mm-dd');"
								value="<bean:write name='rs' property="ht1_hkkssj" />"
								name="ht1_hkkssj" id="ht1_hkkssj" />
						</div>
					</td>
					<td scope="row">
						<div align="center">
							还款截止日期：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('ht1_hkjzsj','y-mm-dd');"
								value="<bean:write name='rs' property="ht1_hkjzsj" />"
								name="ht1_hkjzsj" id="ht1_hkjzsj" />
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							还款介质名称：
						</div>
					</td>
					<td>
						<div align="left">
						<input type="text" id="ht1_hkjzmc" name="ht1_hkjzmc" maxlength="50" value="<bean:write name='rs' property="ht1_hkjzmc" />">
						</div>
					</td>
					<td scope="row">
						<div align="center">
							还款介质帐号：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="ht1_hkjzzh" name="ht1_hkjzzh" maxlength="25" value="<bean:write name='rs' property="ht1_hkjzzh" />"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row" colspan="4">
						<div align="center" class="style1 style3">
							<strong>合同2</strong>
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							合同号：
						</div>
					</td>
					<td>
						<div align="left">
						<input type="text" id="ht2_hth" name="ht2_hth" maxlength="50" value="<bean:write name='rs' property="ht2_hth" />">
						</div>
					</td>
					<td scope="row">
						<div align="center">
							经办金融机构：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="ht2_jbjrjg" name="ht2_jbjrjg" maxlength="50" value="<bean:write name='rs' property="ht2_jbjrjg" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							批准日期：
						</div>
					</td>
					<td>
						<div align="left">
						<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('ht2_pzrq','y-mm-dd');"
								value="<bean:write name='rs' property="ht2_pzrq" />"
								name="ht2_pzrq" id="ht2_pzrq" />
						</div>
					</td>
					<td scope="row">
						<div align="center">
							总金额：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="ht2_zje" name="ht2_zje" maxlength="10" value="<bean:write name='rs' property="ht2_zje" />"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							经办员：
						</div>
					</td>
					<td>
						<div align="left">
						<input type="text" id="ht2_jby" name="ht2_jby" maxlength="40" value="<bean:write name='rs' property="ht2_jby" />">
						</div>
					</td>
					<td scope="row">
						<div align="center">
							批准行长：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="ht2_pzhz" name="ht2_pzhz" maxlength="40" value="<bean:write name='rs' property="ht2_pzhz" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							还款开始日期：
						</div>
					</td>
					<td>
						<div align="left">
						<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('ht2_hkkssj','y-mm-dd');"
								value="<bean:write name='rs' property="ht2_hkkssj" />"
								name="ht2_hkkssj" id="ht2_hkkssj" />
						</div>
					</td>
					<td scope="row">
						<div align="center">
							还款截止日期：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('ht2_hkjzsj','y-mm-dd');"
								value="<bean:write name='rs' property="ht2_hkjzsj" />"
								name="ht2_hkjzsj" id="ht2_hkjzsj" />
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							还款介质名称：
						</div>
					</td>
					<td>
						<div align="left">
						<input type="text" id="ht2_hkjzmc" name="ht2_hkjzmc" maxlength="50" value="<bean:write name='rs' property="ht2_hkjzmc" />">
						</div>
					</td>
					<td scope="row">
						<div align="center">
							还款介质帐号：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="ht2_hkjzzh" name="ht2_hkjzzh" maxlength="25" value="<bean:write name='rs' property="ht2_hkjzzh" />"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row" colspan="4">
						<div align="center" class="style1 style3">
							<strong>合同3</strong>
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							合同号：
						</div>
					</td>
					<td>
						<div align="left">
						<input type="text" id="ht3_hth" name="ht3_hth" maxlength="50" value="<bean:write name='rs' property="ht3_hth" />">
						</div>
					</td>
					<td scope="row">
						<div align="center">
							经办金融机构：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="ht3_jbjrjg" name="ht3_jbjrjg" maxlength="50" value="<bean:write name='rs' property="ht3_jbjrjg" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							批准日期：
						</div>
					</td>
					<td>
						<div align="left">
						<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('ht3_pzrq','y-mm-dd');"
								value="<bean:write name='rs' property="ht3_pzrq" />"
								name="ht3_pzrq" id="ht3_pzrq" />
						</div>
					</td>
					<td scope="row">
						<div align="center">
							总金额：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="ht3_zje" name="ht3_zje" maxlength="10" value="<bean:write name='rs' property="ht3_zje" />"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							经办员：
						</div>
					</td>
					<td>
						<div align="left">
						<input type="text" id="ht3_jby" name="ht3_jby" maxlength="40" value="<bean:write name='rs' property="ht3_jby" />">
						</div>
					</td>
					<td scope="row">
						<div align="center">
							批准行长：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="ht3_pzhz" name="ht3_pzhz" maxlength="40" value="<bean:write name='rs' property="ht3_pzhz" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							还款开始日期：
						</div>
					</td>
					<td>
						<div align="left">
						<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('ht3_hkkssj','y-mm-dd');"
								value="<bean:write name='rs' property="ht3_hkkssj" />"
								name="ht3_hkkssj" id="ht3_hkkssj" />
						</div>
					</td>
					<td scope="row">
						<div align="center">
							还款截止日期：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('ht3_hkjzsj','y-mm-dd');"
								value="<bean:write name='rs' property="ht3_hkjzsj" />"
								name="ht3_hkjzsj" id="ht3_hkjzsj" />
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							还款介质名称：
						</div>
					</td>
					<td>
						<div align="left">
						<input type="text" id="ht3_hkjzmc" name="ht3_hkjzmc" maxlength="50" value="<bean:write name='rs' property="ht3_hkjzmc" />">
						</div>
					</td>
					<td scope="row">
						<div align="center">
							还款介质帐号：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="ht3_hkjzzh" name="ht3_hkjzzh" maxlength="25" value="<bean:write name='rs' property="ht3_hkjzzh" />"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row" colspan="4">
						<div align="center" class="style1 style3">
							<strong>合同4</strong>
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							合同号：
						</div>
					</td>
					<td>
						<div align="left">
						<input type="text" id="ht4_hth" name="ht4_hth" maxlength="50" value="<bean:write name='rs' property="ht4_hth" />">
						</div>
					</td>
					<td scope="row">
						<div align="center">
							经办金融机构：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="ht4_jbjrjg" name="ht4_jbjrjg" maxlength="50" value="<bean:write name='rs' property="ht4_jbjrjg" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							批准日期：
						</div>
					</td>
					<td>
						<div align="left">
						<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('ht4_pzrq','y-mm-dd');"
								value="<bean:write name='rs' property="ht4_pzrq" />"
								name="ht4_pzrq" id="ht4_pzrq" />
						</div>
					</td>
					<td scope="row">
						<div align="center">
							总金额：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="ht4_zje" name="ht4_zje" maxlength="10" value="<bean:write name='rs' property="ht4_zje" />"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							经办员：
						</div>
					</td>
					<td>
						<div align="left">
						<input type="text" id="ht4_jby" name="ht4_jby" maxlength="40" value="<bean:write name='rs' property="ht4_jby" />">
						</div>
					</td>
					<td scope="row">
						<div align="center">
							批准行长：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="ht4_pzhz" name="ht4_pzhz" maxlength="40" value="<bean:write name='rs' property="ht4_pzhz" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							还款开始日期：
						</div>
					</td>
					<td>
						<div align="left">
						<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('ht4_hkkssj','y-mm-dd');"
								value="<bean:write name='rs' property="ht4_hkkssj" />"
								name="ht4_hkkssj" id="ht4_hkkssj" />
						</div>
					</td>
					<td scope="row">
						<div align="center">
							还款截止日期：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('ht4_hkjzsj','y-mm-dd');"
								value="<bean:write name='rs' property="ht4_hkjzsj" />"
								name="ht4_hkjzsj" id="ht4_hkjzsj" />
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							还款介质名称：
						</div>
					</td>
					<td>
						<div align="left">
						<input type="text" id="ht4_hkjzmc" name="ht4_hkjzmc" maxlength="50" value="<bean:write name='rs' property="ht4_hkjzmc" />">
						</div>
					</td>
					<td scope="row">
						<div align="center">
							还款介质帐号：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="ht4_hkjzzh" name="ht4_hkjzzh" maxlength="25" value="<bean:write name='rs' property="ht4_hkjzzh" />"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
			</table>
			
			<div class="buttontool" align="center">
					<button class="button2"
						onclick="dataDoSave('xh');"
						style="width:80px" id="buttonSave">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="window.dialogArguments.document.getElementById('search_go').click();Close();" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>

		</html:form>
</body>
</html:html>
