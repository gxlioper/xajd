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
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			var yqyy = document.getElementById('yqyy').value;
			var bz = document.getElementById('bz').value;
			
			if(yqyy != null){
	         	if(yqyy.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("逾期原因不能大于200个字符");
	          		 return false;
	       		 }
	       	}
	       	if(bz != null){
	         	if(bz.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("备注不能大于200个字符");
	          		 return false;
	       		 }
	       	}
		
			refreshForm('/xgxt/gnnzzy_gjzxdk.do?method=hdxxshXxxx&actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 审核 -  还贷基本信息 - 单个信息
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pk"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" name="tName" id="tName" value="<bean:write name="tName" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
						</td>
					</tr>
				</thead>
				<tr>
				<td align="center" width="16%">
					学号
				</td>
				<td align="left" width="34%">
					<bean:write name="rs" property="xh" />
				</td>
				<td width="16%" scope="col">
					<div align="center">
						姓名
					</div>
				</td>
				<td width="34%" scope="col">
					<bean:write name="rs" property="xm" />
				</td>
			</tr>
			<tr>
				<td width="16%" scope="row">
					<div align="center">
						性别
					</div>
				</td>
				<td width="34%">
					<bean:write name="rs" property="xb" />
				</td>
				<td>
					<div align="center">
						年级
					</div>
				</td>
				<td>
					<bean:write name="rs" property="nj" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						身份证号
					</div>
				</td>
				<td>
					<bean:write name="rs" property="sfzh" />
				</td>
				<td>
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xymc" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						专业
					</div>
				</td>
				<td>
					<bean:write name="rs" property="zymc" />
				</td>
				<td>
					<div align="center">
						班级
					</div>
				</td>
				<td>
					<bean:write name="rs" property="bjmc" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						学制
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xz" />
				</td>
				<td>
					<div align="center">
						个人联系电话
					</div>
				</td>
				<td>
					<bean:write name="rs" property="grlxdh" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						邮箱地址
					</div>
				</td>
				<td>
					<bean:write name="rs" property="yxdz" />
				</td>
				<td>
					<div align="center">
						户籍所在地
					</div>
				</td>
				<td>
					<bean:write name="rs" property="hjszd" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						家庭居住地址
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="jtjzdz" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						邮政编码
					</div>
				</td>
				<td>
					<bean:write name="rs" property="yzbm" />
				</td>
				<td>
					<div align="center">
						家庭联系电话
					</div>
				</td>
				<td>
					<bean:write name="rs" property="jtlxdh" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						工作单位名称
					</div>
				</td>
				<td>
					<bean:write name="rs" property="gzdwmc" />
				</td>
				<td>
					<div align="center">
						工作单位邮编
					</div>
				</td>
				<td>
					<bean:write name="rs" property="gzdwyb" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						工作单位地址
					</div>
				</td>
				<td>
					<bean:write name="rs" property="gzdwdz" />
				</td>
				<td>
					<div align="center">
						工作单位电话
					</div>
				</td>
				<td>
					<bean:write name="rs" property="dwdh" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						合同号
					</div>
				</td>
				<td>
					<bean:write name="rs" property="hth" />
				</td>
				<td>
					<div align="center">
						合同金额
					</div>
				</td>
				<td>
					<bean:write name="rs" property="htje" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						合同总金额
					</div>
				</td>
				<td>
					<bean:write name="rs" property="htzje" />
				</td>
				<td>
					<div align="center">
						贷款期限
					</div>
				</td>
				<td>
					<bean:write name="rs" property="dkqx" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						自付息具体日期
					</div>
				</td>
				<td>
					<bean:write name="rs" property="zfxjtrq" />
				</td>
				<td>
					<div align="center">
						贷款年利率
					</div>
				</td>
				<td>
					<bean:write name="rs" property="nll" />
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						还款帐户类型
					</div>
				</td>
				<td>
					<bean:write name="rs" property="hkzhlx" />
				</td>
				<td>
					<div align="center">
						还款帐户号码
					</div>
				</td>
				<td>
					<bean:write name="rs" property="hkzhhm" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						还款时间
					</div>
				</td>
				<td>
					<bean:write name="rs" property="hksj" />
				</td>
				<td>
					<div align="center">
						已还款金额
					</div>
				</td>
				<td>
					<input type="text" id="yhkje" name="yhkje"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yhkje" />" maxlength="6"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						逾期期数
					</div>
				</td>
				<td>
					<input type="text" id="yqqs" name="yqqs"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yqqs" />" maxlength="6"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td>
					<div align="center">
						逾期本金
					</div>
				</td>
				<td>
					<input type="text" id="yqbj" name="yqbj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yqbj" />" maxlength="6"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						逾期罚息
					</div>
				</td>
				<td>
					<input type="text" id="yqfx" name="yqfx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yqfx" />" maxlength="10">
				</td>
				<td>
					<div align="center">
						逾期原因
					</div>
				</td>
				<td>
					<input type="text" id="yqyy" name="yqyy"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yqyy" />" maxlength="100">
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						备注
					</div>
				</td>
				<td colspan="3">
					<html:textarea name="rs" property="bz" rows='3' style="width:100%" onblur="yzdx(this,'bz')"/>
					<input type="hidden" id="bz" name="bz" value="">
				</td>
			</tr>
			</table>
			<div class="buttontool" align="center">
				<button class="button2" onclick="yz()" style="width:80px"
					id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
	</body>
</html>
