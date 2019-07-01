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
		<title><bean:message key="lable.title" />
		</title>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/commanFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/xszzFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			var isXX = document.getElementById('isXX').value;
			var xxsh = document.getElementById('xxsh').value;
			var xyshyj = document.getElementById('xyshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />审核意见不能大于60个字符");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          		 alert("学校审核意见不能大于60个字符");
	          		 return false;
	       		 }
	       	}
			if(("通过" == xxsh) && (isXX != "is")){
				alert("学校审核已通过，不能再修改审核结果!");
	          	return false;
			}
			 refreshForm('/xgxt/auditing_shgc_xsxfjmsq_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 审核 - 助学贷款审核 - 单个审核
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pk"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" name="tName" value="<bean:write name="tName" />" />
			<input type="hidden" id="xxsh" name="xxsh" value="<bean:write name="rs" property="xxsh" />" />
			<input type="hidden" id="isXX" name="isXX" value="<bean:write name="isXX" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="8" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td colspan="2">
						<div align="center">
							学号
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xh" />
					</td>
					<td width="16%">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="34%">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							民族
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="mzmc" />
					</td>
					<td>
						<div align="center">
							性别
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xb" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							大学
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xxmc" />
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xy" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							系
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xmc" />
					</td>
					<td>
						<div align="center">
							班
						</div>
					</td>
					<td>
						<bean:write name="rs" property="bjmc" />
					</td>
				</tr>
				<logic:notEmpty name="isSHGC">
					<logic:equal name="isSHGC" value="is">
						<tr>
							<td colspan="2">
								<div align="center">
									卡号
								</div>
							</td>
							<td colspan="3">
								<bean:write name="rs" property="kh" />
							</td>
							<td>
								<div align="center"></div>
							</td>
							<td></td>
						</tr>
					</logic:equal>
					<logic:equal name="isSHGC" value="no">
					</logic:equal>
				</logic:notEmpty>
				<tr>
					<td colspan="2">
						<div align="center">
							以享受困难补助，减免学费及贷款情况
						</div>
					</td>
					<td colspan="5">
						<bean:write name="rs" property="yxsbzdkqk" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							在校参加勤工助学情况
						</div>
					</td>
					<td colspan="5">
						<bean:write name="rs" property="zxcjqgzxqk" />
					</td>
				</tr>
				<tr>
					<td width="4%" rowspan="6">
						<div align="center">
							家
							<br>
							庭
							<br>
							成
							<br>
							员
							<br>
							情
							<br>
							况
						</div>
					</td>
					<td width="12%">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="9%">
						<div align="center">
							与本人关系
						</div>
					</td>
					<td width="13%">
						<div align="center">
							年龄
						</div>
					</td>
					<td width="12%">
						<div align="center">
							收入(元/月)
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							工作或学习单位
						</div>
					</td>
				</tr>
				<tr>
					<td width="12%">
						<div align="center">
							<bean:write name="rs" property="jtcy1_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_gx" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_ysr" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy1_gzdz" />
						</div>
					</td>
				</tr>
				<tr>
					<td width="12%">
						<div align="center">
							<bean:write name="rs" property="jtcy2_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_gx" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_ysr" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy2_gzdz" />
						</div>
					</td>
				</tr>
				<tr>
					<td width="12%">
						<div align="center">
							<bean:write name="rs" property="jtcy3_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_gx" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_ysr" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy3_gzdz" />
						</div>
					</td>
				</tr>
				<tr>
					<td width="12%">
						<div align="center">
							<bean:write name="rs" property="jtcy4_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_gx" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_ysr" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy4_gzdz" />
						</div>
					</td>
				</tr>
				<tr>
					<td width="12%">
						<div align="center">
							<bean:write name="rs" property="jtcy5_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_gx" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_ysr" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy5_gzdz" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							申请理由
						</div>
					</td>
					<td colspan="5">
						<bean:write name="rs" property="sqly" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							联系电话
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="lxdh" />
					</td>
					<td>
						<div align="center">
							审核结果
						</div>
					</td>
					<td>
						<html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<logic:equal name="isXX" value="no">
				<tr>
					<td colspan="2">
						<div align="center">
							拟减免金额
						</div>
					</td>
					<td colspan="3">
						<input name="njmje" id="njmje" type="text" maxlength="10"
							style="width:100%;heigh:100%" value="<bean:write name="rs" property="njmje"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							减免金额
						</div>
					</td>
					<td>
						<input name="jmje" readonly="readonly" id="jmje" type="text"
							style="width:100%;heigh:100%" value="<bean:write name="rs" property="jmje"/>">
					</td>
				</tr>
				</logic:equal>
				<logic:equal name="isXX" value="is">
				<tr>
					<td colspan="2">
						<div align="center">
							拟减免金额
						</div>
					</td>
					<td colspan="3">
						<input name="njmje" id="njmje" type="text" readonly="readonly"
							style="width:100%;heigh:100%" value="<bean:write name="rs" property="njmje"/>">
					</td>
					<td>
						<div align="center">
							减免金额
						</div>
					</td>
					<td>
						<input name="jmje" id="jmje" type="text" maxlength="10"
							style="width:100%;heigh:100%" value="<bean:write name="rs" property="jmje"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				</logic:equal>
				<tr>
					<td colspan="2">
						<div align="center">
							拟减免金额(大写)
						</div>
					</td>
					<td colspan="3">
						<input name="njmjedx" id="njmjedx" type="text"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="njmjedx" />" readonly="readonly">
					</td>
					<td>
						<div align="center">
							减免金额(大写)
						</div>
					</td>
					<td>
						<input name="jmjedx" id="jmjedx" type="text"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jmjedx" />" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="left">
							<bean:message key="lable.xsgzyxpzxy" />审核意见
						</div>
					</td>
					<td colspan="5">
						<html:textarea name="rs" property="xyshyj" rows='5' style="width:100%" onblur="yzdx(this,'xyshyj')"/>
						<input type="hidden" id="xyshyj" name="xyshyj" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="left">
							学校审核意见
						</div>
					</td>
					<td colspan="5">
						<html:textarea name="rs" property="xxshyj" rows='5' style="width:100%" onblur="yzdx(this,'xxshyj')"/>
						<input type="hidden" id="xxshyj" name="xxshyj" value="">
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2"
					onclick="yz()"
					style="width:80px" id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
	</body>
</html>
