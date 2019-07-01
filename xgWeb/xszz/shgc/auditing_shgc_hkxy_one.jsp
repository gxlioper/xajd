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
		<title><bean:message key="lable.title" /></title>
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
			
			refreshForm('/xgxt/auditing_shgc_hkxy_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 审核 - 还款协议审核 - 单个审核
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
					<td colspan="2">
						<input type="hidden" name="xh" value="<bean:write name="rs" property="xh" />" />
						<bean:write name="rs" property="xh" />
					</td>
					<td width="16%">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="34%">
						<input type="hidden" name="xm" value="<bean:write name="rs" property="xm" />" />
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							出生年月
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="csrq"
							value="<bean:write name="rs" property="csrq" />" />
						<bean:write name="rs" property="csrq" />
					</td>
					<td>
						<div align="center">
							性别
						</div>
					</td>
					<td>
						<input type="hidden" name="xb" value="<bean:write name="rs" property="xb" />" />
						<bean:write name="rs" property="xb" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							身份证号
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="sfzh"
							value="<bean:write name="rs" property="sfzh" />" />
						<bean:write name="rs" property="sfzh" />
					</td>
					<td>
						<div align="center">
							学校名称
						</div>
					</td>
					<td>
						<input type="hidden" name="xxmc"
							value="<bean:write name="rs" property="xxmc" />" />
						<bean:write name="rs" property="xxmc" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="xymc" value="<bean:write name="rs" property="xymc" />" />
						<bean:write name="rs" property="xymc" />
					</td>
					<td>
						<div align="center">
							系
						</div>
					</td>
					<td>
						<input type="hidden" name="xmc" value="<bean:write name="rs" property="xmc" />" />
						<bean:write name="rs" property="xmc" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							学历
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="xl" value="<bean:write name="rs" property="xl" />" />
						<bean:write name="rs" property="xl" />
					</td>
					<td>
						<div align="center">
							联系电话
						</div>
					</td>
					<td>
						<input type="hidden" name="lxdh"
							value="<bean:write name="rs" property="lxdh" />" />
						<bean:write name="rs" property="lxdh" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							婚姻状况
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="hyzk"
							value="<bean:write name="rs" property="hyzk" />" />
						<bean:write name="rs" property="hyzk" />
					</td>
					<td>
						<div align="center">
							配偶姓名
						</div>
					</td>
					<td>
						<input type="hidden" name="poxm"
							value="<bean:write name="rs" property="poxm" />" />
						<bean:write name="rs" property="poxm" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							工作单位
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="gzdw"
							value="<bean:write name="rs" property="gzdw" />" />
						<bean:write name="rs" property="gzdw" />
					</td>
					<td>
						<div align="center">
							工作单位电话
						</div>
					</td>
					<td>
						<input type="hidden" name="dwdh"
							value="<bean:write name="rs" property="dwdh" />" />
						<bean:write name="rs" property="dwdh" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							工作单位地址
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="dwdz"
							value="<bean:write name="rs" property="dwdz" />" />
						<bean:write name="rs" property="dwdz" />
					</td>
					<td>
						<div align="center">
							工作单位邮政编码
						</div>
					</td>
					<td>
						<input type="hidden" name="dwyzbm"
							value="<bean:write name="rs" property="dwyzbm" />" />
						<bean:write name="rs" property="dwyzbm" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							移动电话
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="yddh"
							value="<bean:write name="rs" property="yddh" />" />
						<bean:write name="rs" property="yddh" />
					</td>
					<td>
						<div align="center">
							E—MAIL
						</div>
					</td>
					<td>
						<input type="hidden" name="email"
							value="<bean:write name="rs" property="email" />" />
						<bean:write name="rs" property="email" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							联系人姓名
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="lxrxm"
							value="<bean:write name="rs" property="lxrxm" />" />
						<bean:write name="rs" property="lxrxm" />
					</td>
					<td>
						<div align="center">
							联系人性别
						</div>
					</td>
					<td>
						<input type="hidden" name="lxrxb"
							value="<bean:write name="rs" property="lxrxb" />" />
						<bean:write name="rs" property="lxrxb" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							联系人出生日期
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="lxrcsrq"
							value="<bean:write name="rs" property="lxrcsrq" />" />
						<bean:write name="rs" property="lxrcsrq" />
					</td>
					<td>
						<div align="center">
							与本人关系
						</div>
					</td>
					<td>
						<input type="hidden" name="lxrgx"
							value="<bean:write name="rs" property="lxrgx" />" />
						<bean:write name="rs" property="lxrgx" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							联系人电话
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="lxrdh"
							value="<bean:write name="rs" property="lxrdh" />" />
						<bean:write name="rs" property="lxrdh" />
					</td>
					<td>
						<div align="center">
							联系人地址/单位
						</div>
					</td>
					<td>
						<input type="hidden" name="lxrdwdz"
							value="<bean:write name="rs" property="lxrdwdz" />" />
						<bean:write name="rs" property="lxrdwdz" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭详细地址
						</div>
					</td>
					<td colspan="4">
						<input type="hidden" name="jtxxzz"
							value="<bean:write name="rs" property="jtxxzz" />" />
						<bean:write name="rs" property="jtxxzz" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭邮政编码
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="jtyzbm"
							value="<bean:write name="rs" property="jtyzbm" />" />
						<bean:write name="rs" property="jtyzbm" />
					</td>
					<td>
						<div align="center">
							家庭联系电话
						</div>
					</td>
					<td>
						<input type="hidden" name="jtlxdh"
							value="<bean:write name="rs" property="jtlxdh" />" />
						<bean:write name="rs" property="jtlxdh" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							父亲姓名
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="fqxm"
							value="<bean:write name="rs" property="fqxm" />" />
						<bean:write name="rs" property="fqxm" />
					</td>
					<td>
						<div align="center">
							母亲姓名
						</div>
					</td>
					<td>
						<input type="hidden" name="mqxm"
							value="<bean:write name="rs" property="mqxm" />" />
						<bean:write name="rs" property="mqxm" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							父亲职业
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="fqzy"
							value="<bean:write name="rs" property="fqzy" />" />
						<bean:write name="rs" property="fqzy" />
					</td>
					<td>
						<div align="center">
							母亲职业
						</div>
					</td>
					<td>
						<input type="hidden" name="mqzy"
							value="<bean:write name="rs" property="mqzy" />" />
						<bean:write name="rs" property="mqzy" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							父亲身份证号
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="fqsfzh"
							value="<bean:write name="rs" property="fqsfzh" />" />
						<bean:write name="rs" property="fqsfzh" />
					</td>
					<td>
						<div align="center">
							母亲身份证号
						</div>
					</td>
					<td>
						<input type="hidden" name="mqsfzh"
							value="<bean:write name="rs" property="mqsfzh" />" />
						<bean:write name="rs" property="mqsfzh" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							申请时间
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="sqsj"
							value="<bean:write name="rs" property="sqsj" />" />
						<bean:write name="rs" property="sqsj" />
					</td>
					<td>
						<div align="center">
							合同号
						</div>
					</td>
					<td>
						<input type="hidden" name="hth" value="<bean:write name="rs" property="hth" />" />
						<bean:write name="rs" property="hth" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							离校时间
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="lxsj"
							value="<bean:write name="rs" property="lxsj" />" />
						<bean:write name="rs" property="lxsj" />
					</td>
					<td>
						<div align="center">
							离校原因
						</div>
					</td>
					<td>
						<input type="hidden" name="lxyy"
							value="<bean:write name="rs" property="lxyy" />" />
						<bean:write name="rs" property="lxyy" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							最后放款时间
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="zhfkrq"
							value="<bean:write name="rs" property="zhfkrq" />" />
						<bean:write name="rs" property="zhfkrq" />
					</td>
					<td>
						<div align="center">
							放款总金额
						</div>
					</td>
					<td>
						<input type="hidden" name="fkzje"
							value="<bean:write name="rs" property="fkzje" />" />
						<bean:write name="rs" property="fkzje" />
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<div align="center">
							采用
							<font color="red"><input type="hidden" name="hkfs1"
									value="<bean:write name="rs" property="hkfs1" />" />
								<bean:write name="rs" property="hkfs1" />
							</font>的方式,按
							<font color="red"><input type="hidden" name="hkfs2"
									value="<bean:write name="rs" property="hkfs2" />" />
								<bean:write name="rs" property="hkfs2" />
							</font> 分
							<font color="red"><input type="hidden" name="hkcs"
									value="<bean:write name="rs" property="hkcs" />" />
								<bean:write name="rs" property="hkcs" />
							</font>期归还贷款本息，还款期限共
							<font color="red"><input type="hidden" name="hkqx"
									value="<bean:write name="rs" property="hkqx" />" />
								<bean:write name="rs" property="hkqx" />
							</font>月
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							还款开始时间
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="hkkssj"
							value="<bean:write name="rs" property="hkkssj" />" />
						<bean:write name="rs" property="hkkssj" />
					</td>
					<td>
						<div align="center">
							还款结束时间
						</div>
					</td>
					<td>
						<input type="hidden" name="hkjssj"
							value="<bean:write name="rs" property="hkjssj" />" />
						<bean:write name="rs" property="hkjssj" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							帐户户名
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="zffm"
							value="<bean:write name="rs" property="zffm" />" />
						<bean:write name="rs" property="zffm" />
					</td>
					<td>
						<div align="center">
							帐户号
						</div>
					</td>
					<td>
						<input type="hidden" name="zfh" value="<bean:write name="rs" property="zfh" />" />
						<bean:write name="rs" property="zfh" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center"></div>
					</td>
					<td colspan="2"></td>
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
					<td colspan="2">
						<div align="left">
							<bean:message key="lable.xsgzyxpzxy" />审核意见
						</div>
					</td>
					<td colspan="4">
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
					<td colspan="4">
						<html:textarea name="rs" property="xxshyj" rows='5' style="width:100%" onblur="yzdx(this,'xxshyj')"/>
						<input type="hidden" id="xxshyj" name="xxshyj" value="">
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2" onclick="yz();" style="width:80px"
					id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onClick="toPrintOut('hkxy');">
					打印还款协议
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onClick="toPrintOut('zlqrs');">
					打印资料确认书
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
