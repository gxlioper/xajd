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
			var isXX = document.getElementById('isXX').value;
			var xxsh = document.getElementById('xxsh').value;
			if(("通过" == xxsh) && (isXX != "is")){
				alert("学校审核已通过，不能再修改审核结果!");
	          	return false;
			}
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />审核意见不能大于200个字符");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("学校审核意见不能大于200个字符");
	          		 return false;
	       		 }
	       	}
			refreshForm('/xgxt/auditing_zzsf_sydxyzxdk_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 审核 - 生源地信用助学贷款审核 - 单个审核
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
						<td align="center" width="16%">
							学号
						</td>
						<td align="left" width="34%">
							<bean:write name="rs" property="xh" />
						</td>
					<td width="16%">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="34%">
						<bean:write name="rs" property="xm"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							性别
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xb"/>
					</td>
					<td>
						<div align="center">
							出生年月
						</div>
					</td>
					<td>
						<bean:write name="rs" property="csrq"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							身份证号
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sfzh"/>
					</td>
					<td>
						<div align="center">
							学制
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xz"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							年级
						</div>
					</td>
					<td>
						<bean:write name="rs" property="nj"/>
					</td>
					<td>
						<div align="center">
							系别
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xymc"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							专业
						</div>
					</td>
					<td>
						<bean:write name="rs" property="zymc"/>
					</td>
					<td>
						<div align="center">
							班级
						</div>
					</td>
					<td>
						<bean:write name="rs" property="bjmc"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							家庭详细地址
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtxxdz"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							家庭电话
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtdh"/>
					</td>
					<td>
						<div align="center">
							户籍所在地
						</div>
					</td>
					<td>
						<bean:write name="rs" property="hjszd"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							邮政编码
						</div>
					</td>
					<td>
						<bean:write name="rs" property="yzbm"/>
					</td>
					<td>
						<div align="center">
							移动电话
						</div>
					</td>
					<td>
						<bean:write name="rs" property="yddh"/>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<div align="center">
							共同借款人信息
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							姓名
						</div>
					</td>
					<td>
						<bean:write name="rs" property="gtjkr_xm"/>
					</td>
					<td>
						<div align="center">
							与借款人关系
						</div>
					</td>
					<td>
						<bean:write name="rs" property="gtjkr_gx"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							工作单位
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="gtjkr_gzdw"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							身份证号
						</div>
					</td>
					<td>
						<bean:write name="rs" property="gtjkr_sfzh"/>
					</td>
					<td>
						<div align="center">
							职务
						</div>
					</td>
					<td>
						<bean:write name="rs" property="gtjkr_zw"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							家庭详细地址
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="gtjkr_jtxxdz"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							家庭电话
						</div>
					</td>
					<td>
						<bean:write name="rs" property="gtjkr_jtdh"/>
					</td>
					<td>
						<div align="center">
							户籍所在地
						</div>
					</td>
					<td>
						<bean:write name="rs" property="gtjkr_hjszd"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							邮政编码
						</div>
					</td>
					<td>
						<bean:write name="rs" property="gtjkr_yzbm"/>
					</td>
					<td>
						<div align="center">
							移动电话
						</div>
					</td>
					<td>
						<bean:write name="rs" property="gtjkr_yddh"/>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<div align="center">
							申请贷款信息
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							困难类型
						</div>
					</td>
					<td colspan="3">
						<logic:equal name="rs" property="knlx_dsr" value="是">
							低收入&nbsp;&nbsp;&nbsp;
						</logic:equal>
						<logic:equal name="rs" property="knlx_cnh" value="是">
							纯农户&nbsp;&nbsp;&nbsp;
						</logic:equal>
						<logic:equal name="rs" property="knlx_sxg" value="是">
							双下岗&nbsp;&nbsp;&nbsp;
						</logic:equal>
						<logic:equal name="rs" property="knlx_dbh" value="是">
							低保户&nbsp;&nbsp;&nbsp;
						</logic:equal>
						<logic:equal name="rs" property="knlx_gr" value="是">
							孤儿&nbsp;&nbsp;&nbsp;
						</logic:equal>
						<logic:equal name="rs" property="knlx_zbh" value="是">
							重病户&nbsp;&nbsp;&nbsp;
						</logic:equal>
						<logic:equal name="rs" property="knlx_wsr" value="是">
							无收入&nbsp;&nbsp;&nbsp;
						</logic:equal>
						<logic:equal name="rs" property="knlx_lszn" value="是">
							烈士子女&nbsp;&nbsp;&nbsp;
						</logic:equal>
						<logic:equal name="rs" property="knlx_qt" value="是">
							其他&nbsp;&nbsp;&nbsp;
						</logic:equal>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							详细原因
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xxyy"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							贷款年限
						</div>
					</td>
					<td>
						<bean:write name="rs" property="dknx"/>
					</td>
					<td>
						<div align="center">
							贷款总金额
						</div>
					</td>
					<td>
						<bean:write name="rs" property="dkzje"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							分期发放次数/金额
						</div>
					</td>
					<td>
						<bean:write name="rs" property="fqffcsje"/>
					</td>
					<td>
						<div align="center">
							经办银行
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jbyh"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							生源地信用助学<br />贷款合同编号
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sydxyzxdkhtbh"/>
					</td>
					<td>
						<div align="center">
							学年
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xn"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							申请时间
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sqsj" />
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
				<tr>
					<td>
						<div align="center">
							系审核意见
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="xyshyj" rows='5' style="width:100%" onblur="yzdx(this,'xyshyj')"/>
						<input type="hidden" id="xyshyj" name="xyshyj" value="">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
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
				<button type="button" class="button2" onclick="yz()" style="width:80px"
					id="buttonSave">
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
