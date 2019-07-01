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
			var xyshyj = document.getElementById('xyshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			var spbbh = document.getElementById('spbbh').value;
			var isXX = document.getElementById('isXX').value;
			var xxsh = document.getElementById('xxsh').value;
			var spbbhList = document.getElementById('spbbhList').value;
			
			if(spbbh == null || spbbh.trim() == ""){
				alert("审批表编号不能为空！");
				return false;
			}
			if(isXX == "is"){
			msgArray = new Array();
			msgArray = spbbhList.split("!!OneSpilt!!");
			for(var i = 0; i < msgArray.length; i++){
				if(spbbh == msgArray[i]){
					alert("审批表编号已存在!");
					return false;
				}
			}
			}
			if((("通过" == xxsh)) && (isXX != "is")){
				alert("学校审核已通过，不能再修改审核结果!");
	          	return false;
			}
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />审核意见不能大于1000个字符");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("学校审核意见不能大于1000个字符");
	          		 return false;
	       		 }
	       	}
			
			refreshForm('/xgxt/gnnzzy_gjzxdk.do?method=zxdkshXxxx&actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
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
			<input type="hidden" name="tName" id="tName" value="<bean:write name="tName" />" />
			<input type="hidden" name="xxsh" id="xxsh" value="<bean:write name="rs" property="xxsh" />" />
			<input type="hidden" name="isXX" id="isXX" value="<bean:write name="isXX" />" />
			<input type="hidden" name="spbbhList" id="spbbhList" value="<bean:write name="spbbhList" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td width="16%" scope="row">
						<div align="center">
							年度
						</div>
					</td>
					<td width="34%">
						<bean:write name="rs" property="nd" />
					</td>
					<td width="16%">
						<div align="center">
							申请时间
						</div>
					</td>
					<td width="34%">
						<bean:write name="rs" property="sqsj" />
					</td>
				</tr>
				<tr>
				<td align="center">
					学号
				</td>
				<td align="left">
					<bean:write name="rs" property="xh" />
				</td>
				<td scope="col">
					<div align="center">
						姓名
					</div>
				</td>
				<td scope="col">
					<bean:write name="rs" property="xm" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						性别
					</div>
				</td>
				<td>
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
						年度
					</div>
				</td>
				<td>
					<bean:write name="rs" property="nd" />
				</td>
				<td>
					<div align="center">
						申请时间
					</div>
				</td>
				<td>
					<bean:write name="rs" property="sqsj" />
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
						家庭人口数
					</div>
				</td>
				<td>
					<bean:write name="rs" property="jtrk" />
				</td>
				<td>
					<div align="center">
						家庭人均月收入
					</div>
				</td>
				<td>
					<bean:write name="rs" property="jtrjysr" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						家庭年总收入
					</div>
				</td>
				<td>
					<bean:write name="rs" property="jtnzsr" />
				</td>
				<td>
					<div align="center">
						家庭所在街道\
						<br />
						村委会联系电话
					</div>
				</td>
				<td>
					<bean:write name="rs" property="jtszjwhdh" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						父亲姓名
					</div>
				</td>
				<td>
					<bean:write name="rs" property="fqxm" />
				</td>
				<td>
					<div align="center">
						母亲姓名
					</div>
				</td>
				<td>
					<bean:write name="rs" property="mqxm" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						父亲身份证号
					</div>
				</td>
				<td>
					<bean:write name="rs" property="fqsfzh" />
				</td>
				<td>
					<div align="center">
						母亲身份证号
					</div>
				</td>
				<td>
					<bean:write name="rs" property="mqsfzh" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						父亲工作单位
					</div>
				</td>
				<td>
					<bean:write name="rs" property="fqgzdw" />
				</td>
				<td>
					<div align="center">
						母亲工作单位
					</div>
				</td>
				<td>
					<bean:write name="rs" property="mqgzdw" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						父亲职业
					</div>
				</td>
				<td>
					<bean:write name="rs" property="fqzy" />
				</td>
				<td>
					<div align="center">
						母亲职业
					</div>
				</td>
				<td>
					<bean:write name="rs" property="mqzy" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						父亲联系电话
					</div>
				</td>
				<td>
					<bean:write name="rs" property="fqlxdh" />
				</td>
				<td>
					<div align="center">
						母亲联系电话
					</div>
				</td>
				<td>
					<bean:write name="rs" property="mqlxdh" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						贷款类型
					</div>
				</td>
				<td>
					<bean:write name="rs" property="dklxmc" />
				</td>
				<td>
					<div align="center">
						贷款金额
					</div>
				</td>
				<td>
					<bean:write name="rs" property="dkje" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						贷款期限
					</div>
				</td>
				<td>
					<bean:write name="rs" property="dkqx" />
				</td>
				<td>
					<div align="center">
						贷款年利率
					</div>
				</td>
				<td>
					<bean:write name="rs" property="nll" />
				</td>
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
						合同编号
					</div>
				</td>
				<td>
					<bean:write name="rs" property="htbh" />
				</td>
				<logic:equal name="isXX" value="is">
				<td>
					<div align="center">
						<font color="red">*</font>审批表编号
					</div>
				</td>
				<td>
					<input type="text" id="spbbh" name="spbbh" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="spbbh" />" maxlength="20">
				</td>
				</logic:equal>
				<logic:equal name="isXX" value="no">
				<td>
					<div align="center">
						审批表编号
					</div>
				</td>
				<td>
					<input type="text" id="spbbh" name="spbbh" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="spbbh" />" readonly="readonly">
				</td>
				</logic:equal>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						备注
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="bz" />
				</td>
			</tr>
				<tr>
					<logic:equal name="isXX" value="is">
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />审核结果
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xysh" />
					</td>
					</logic:equal>
					<logic:equal name="isXX" value="no">
					<td colspan="2">
					</td>
					</logic:equal>
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
				<tr>
					<td>
						<div align="left">
							<bean:message key="lable.xsgzyxpzxy" />审核意见
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="xyshyj" rows='5' style="width:100%" onblur="yzdx(this,'xyshyj')"/>
						<input type="hidden" id="xyshyj" name="xyshyj" value="">
					</td>
				</tr>
				<tr>
					<td>
						<div align="left">
							学校审核意见
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="xxshyj" rows='5' style="width:100%" onblur="yzdx(this,'xxshyj')"/>
						<input type="hidden" id="xxshyj" name="xxshyj" value="">
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
