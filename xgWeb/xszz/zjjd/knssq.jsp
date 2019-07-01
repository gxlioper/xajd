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


	<title><bean:message key="lable.title" /></title>
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
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			var jtjjqkxxsm = document.getElementById('jtjjqkxxsm').value;
			
			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
			if(jtjjqkxxsm != null){
	         	if(jtjjqkxxsm.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("家庭详细经济情况说明不能大于1000个字符");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/zjjdzyjsxy_xszz.do?method=knssqSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//输出相应的打印页面
			document.forms[0].action = "/xgxt/zjjdzyjsxy_xszz.do?method=knssqb";
			document.forms[0].submit();
		}
		
		function je(){
			var yhzzqk_gjzxdk = document.getElementById('yhzzqk_gjzxdk').value;
			var yhzzqk_gjzxj = document.getElementById('yhzzqk_gjzxj').value;
			var yhzzqk_gjjxj = document.getElementById('yhzzqk_gjjxj').value;
			var yhzzqk_gjlzjxj = document.getElementById('yhzzqk_gjlzjxj').value;
			var yhzzqk_xyjxj = document.getElementById('yhzzqk_xyjxj').value;
			var yhzzqk_qgzx = document.getElementById('yhzzqk_qgzx').value;
			var yhzzqk_xfjm = document.getElementById('yhzzqk_xfjm').value;
			var yhzzqk_lxbz = document.getElementById('yhzzqk_lxbz').value;
			var yhzzqk_ddzfshjxj = document.getElementById('yhzzqk_ddzfshjxj').value;
			var yhzzqk_hj = "0";
			
			if((yhzzqk_gjzxdk == null) || (yhzzqk_gjzxdk == "")){
				yhzzqk_gjzxdk = "0";
				document.getElementById('yhzzqk_gjzxdk').value = yhzzqk_gjzxdk;
			}
			if((yhzzqk_gjzxj == null) || (yhzzqk_gjzxj == "")){
				yhzzqk_gjzxj = "0";
				document.getElementById('yhzzqk_gjzxj').value = yhzzqk_gjzxj;
			}
			if((yhzzqk_gjjxj == null) || (yhzzqk_gjjxj == "")){
				yhzzqk_gjjxj = "0";
				document.getElementById('yhzzqk_gjjxj').value = yhzzqk_gjjxj;
			}
			if((yhzzqk_gjlzjxj == null) || (yhzzqk_gjlzjxj == "")){
				yhzzqk_gjlzjxj = "0";
				document.getElementById('yhzzqk_gjlzjxj').value = yhzzqk_gjlzjxj;
			}
			if((yhzzqk_xyjxj == null) || (yhzzqk_xyjxj == "")){
				yhzzqk_xyjxj = "0";
				document.getElementById('yhzzqk_xyjxj').value = yhzzqk_xyjxj;
			}
			if((yhzzqk_qgzx == null) || (yhzzqk_qgzx == "")){
				yhzzqk_qgzx = "0";
				document.getElementById('yhzzqk_qgzx').value = yhzzqk_qgzx;
			}
			if((yhzzqk_xfjm == null) || (yhzzqk_xfjm == "")){
				yhzzqk_xfjm = "0";
				document.getElementById('yhzzqk_xfjm').value = yhzzqk_xfjm;
			}
			if((yhzzqk_lxbz == null) || (yhzzqk_lxbz == "")){
				yhzzqk_lxbz = "0";
				document.getElementById('yhzzqk_lxbz').value = yhzzqk_lxbz;
			}
			if((yhzzqk_ddzfshjxj == null) || (yhzzqk_ddzfshjxj == "")){
				yhzzqk_ddzfshjxj = "0";
				document.getElementById('yhzzqk_ddzfshjxj').value = yhzzqk_ddzfshjxj;
			}
			
			yhzzqk_hj = Math.round(yhzzqk_gjzxdk) + Math.round(yhzzqk_gjzxj) + Math.round(yhzzqk_gjjxj) + Math.round(yhzzqk_gjlzjxj) + Math.round(yhzzqk_xyjxj) + Math.round(yhzzqk_qgzx) + Math.round(yhzzqk_xfjm) + Math.round(yhzzqk_lxbz) + Math.round(yhzzqk_ddzfshjxj);
			document.getElementById('yhzzqk_hj').value = yhzzqk_hj;
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 困难生
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				现在不在申请时间内或困难生人数已满,不能申请！！
			</p>
		</center>
	</logic:equal>
		<html:form action="zjjdzyjsxy_xszz.do?method=knssq" method="post">
			<input type="hidden" id="url" name="url"
				value="/zjjdzyjsxy_xszz.do?method=knssq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="rs" property="pkVal" />">
			<input type="hidden" id="fdysh" name="fdysh"
				value="<bean:write name="rs" property="fdysh" />">
			<input type="hidden" id="fdyshyj" name="fdyshyj"
				value="<bean:write name="rs" property="fdyshyj" />">
			<input type="hidden" id="xysh" name="xysh"
				value="<bean:write name="rs" property="xysh" />">
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj" />">
			<input type="hidden" id="xxsh" name="xxsh"
				value="<bean:write name="rs" property="xxsh" />">
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj" />">

			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("保存成功！");
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("保存失败！");
	         	</script>
				</logic:match>
			</logic:present>
			<logic:present name="isPASS">
				<logic:match value="is" name="isPASS">
					<script language="javascript">
	         			alert("已通过审核，不能申请！");
	         		</script>
				</logic:match>
			</logic:present>
			<table class="tbstyle" width="90%">
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" colspan="3">
							<font color="red">*</font>学号：
						</td>
						<td align="left" colspan="2">
							<html:text name='rs' property="xh" styleId="xh"
								readonly="readonly"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="center" colspan="3">
							<font color="red">*</font>学号：
						</td>
						<td align="left" colspan="2">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xh" />" readonly="true">
						</td>
					</logic:equal>
					<td width="16%">
						<div align="center">
							姓名
						</div>
					</td>
					<td colspan="3">
						<input type="text" readonly="readonly" id="xm" name="xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xm"/>" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							性别
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="xb" readonly="readonly" name="xb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xb"/>">
					</td>
					<td>
						<div align="center">
							年度
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="nd" name="nd" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="nd"/>">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							民族
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="mzmc" readonly="readonly" name="mzmc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzmc"/>">
					</td>
					<td>
						<div align="center">
							政治面貌
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="zzmmmc" name="zzmmmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zzmmmc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							年级
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="nj" readonly="readonly" name="nj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="nj"/>">
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />名称
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="xymc" name="xymc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xymc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							专业名称
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="zymc" readonly="readonly" name="zymc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zymc"/>">
					</td>
					<td>
						<div align="center">
							班级名称
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="bjmc" name="bjmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bjmc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							身份证号
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="sfzh" readonly="readonly" name="sfzh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfzh"/>">
					</td>
					<td>
						<div align="center">
							出生日期
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="csrq" name="csrq" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="csrq"/>">
					</td>
				</tr>
				<tr>
					<td colspan="9" style="padding:0;">
						<%@ include file="yhkh.jsp"%>
					</td>
				</tr>			
				<tr>
					<td colspan="3">
						<div align="center">
							家庭人口数
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="jtrks" name="jtrks" maxlength="5"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtrks"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							是否孤残
						</div>
					</td>
					<td colspan="3" align="center">
						<logic:present name="rs" property="sfgc">
							<logic:match value="是" name="rs" property="sfgc">
								<input type="radio" name="sfgc" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="sfgc" value="否">&nbsp;&nbsp;否
							</logic:match>
							<logic:match value="否" name="rs" property="sfgc">
								<input type="radio" name="sfgc" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="sfgc" value="否" checked>&nbsp;&nbsp;否
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfgc">
							<input type="radio" name="sfgc" value="是">&nbsp;&nbsp;是
							<input type="radio" name="sfgc" value="否" checked>&nbsp;&nbsp;否
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							是否单亲
						</div>
					</td>
					<td colspan="2" align="center">
						<logic:present name="rs" property="sfdq">
							<logic:match value="是" name="rs" property="sfdq">
								<input type="radio" name="sfdq" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="sfdq" value="否">&nbsp;&nbsp;否
							</logic:match>
							<logic:match value="否" name="rs" property="sfdq">
								<input type="radio" name="sfdq" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="sfdq" value="否" checked>&nbsp;&nbsp;否
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfdq">
							<input type="radio" name="sfdq" value="是">&nbsp;&nbsp;是
							<input type="radio" name="sfdq" value="否" checked>&nbsp;&nbsp;否
						</logic:notPresent>
					</td>
					<td>
						<div align="center">
							是否烈士子女
						</div>
					</td>
					<td colspan="3" align="center">
						<logic:present name="rs" property="sflszn">
							<logic:match value="是" name="rs" property="sflszn">
								<input type="radio" name="sflszn" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="sflszn" value="否">&nbsp;&nbsp;否
							</logic:match>
							<logic:match value="否" name="rs" property="sflszn">
								<input type="radio" name="sflszn" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="sflszn" value="否" checked>&nbsp;&nbsp;否
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sflszn">
							<input type="radio" name="sflszn" value="是">&nbsp;&nbsp;是
							<input type="radio" name="sflszn" value="否" checked>&nbsp;&nbsp;否
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							家庭邮政编码
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="yzbm" name="yzbm" maxlength="6"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yzbm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							家庭联系电话
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="lxdh" name="lxdh" maxlength="15"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							家庭详细通讯地址
						</div>
					</td>
					<td colspan="6">
						<input type="text" id="xxtxdz" name="xxtxdz" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xxtxdz"/>">
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
					<td width="8%">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="8%">
						<div align="center">
							年龄
						</div>
					</td>
					<td width="9%">
						<div align="center">
							与学生关系
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							工作或学习单位
						</div>
					</td>
					<td width="12%">
						<div align="center">
							职业
						</div>
					</td>
					<td width="11%">
						<div align="center">
							年收入(元)
						</div>
					</td>
					<td width="11%">
						<div align="center">
							健康状况
						</div>
					</td>
				</tr>
				<tr>
					<td width="6%">
						<div align="center">
							<input type="text" id="jtcy1_xm" name="jtcy1_xm" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy1_xm"/>">
						</div>
					</td>
					<td width="6%">
						<div align="center">
							<input type="text" id="jtcy1_nl" name="jtcy1_nl" maxlength="5"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy1_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_gx" name="jtcy1_gx" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy1_gx"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy1_gzdw" name="jtcy1_gzdw"
								maxlength="200" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy1_gzdw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_zy" name="jtcy1_zy" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy1_zy"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_sr" name="jtcy1_sr" maxlength="10"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy1_sr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_jkzk" name="jtcy1_jkzk"
								maxlength="20" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy1_jkzk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td width="6%">
						<div align="center">
							<input type="text" id="jtcy2_xm" name="jtcy2_xm" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy2_xm"/>">
						</div>
					</td>
					<td width="6%">
						<div align="center">
							<input type="text" id="jtcy2_nl" name="jtcy2_nl" maxlength="5"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy2_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_gx" name="jtcy2_gx" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy2_gx"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy2_gzdw" name="jtcy2_gzdw"
								maxlength="200" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy2_gzdw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_zy" name="jtcy2_zy" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy2_zy"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_sr" name="jtcy2_sr" maxlength="10"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy2_sr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_jkzk" name="jtcy2_jkzk"
								maxlength="20" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy2_jkzk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td width="6%">
						<div align="center">
							<input type="text" id="jtcy3_xm" name="jtcy3_xm" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy3_xm"/>">
						</div>
					</td>
					<td width="6%">
						<div align="center">
							<input type="text" id="jtcy3_nl" name="jtcy3_nl" maxlength="5"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy3_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_gx" name="jtcy3_gx" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy3_gx"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy3_gzdw" name="jtcy3_gzdw"
								maxlength="200" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy3_gzdw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_zy" name="jtcy3_zy" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy3_zy"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_sr" name="jtcy3_sr" maxlength="10"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy3_sr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_jkzk" name="jtcy3_jkzk"
								maxlength="20" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy3_jkzk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td width="6%">
						<div align="center">
							<input type="text" id="jtcy4_xm" name="jtcy4_xm" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy4_xm"/>">
						</div>
					</td>
					<td width="6%">
						<div align="center">
							<input type="text" id="jtcy4_nl" name="jtcy4_nl" maxlength="5"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy4_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_gx" name="jtcy4_gx" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy4_gx"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy4_gzdw" name="jtcy4_gzdw"
								maxlength="200" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy4_gzdw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_zy" name="jtcy4_zy" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy4_zy"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_sr" name="jtcy4_sr" maxlength="10"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy4_sr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_jkzk" name="jtcy4_jkzk"
								maxlength="20" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy4_jkzk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td width="6%">
						<div align="center">
							<input type="text" id="jtcy5_xm" name="jtcy5_xm" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy5_xm"/>">
						</div>
					</td>
					<td width="6%">
						<div align="center">
							<input type="text" id="jtcy5_nl" name="jtcy5_nl" maxlength="5"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy5_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_gx" name="jtcy5_gx" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy5_gx"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy5_gzdw" name="jtcy5_gzdw"
								maxlength="200" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy5_gzdw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_zy" name="jtcy5_zy" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy5_zy"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_sr" name="jtcy5_sr" maxlength="10"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy5_sr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_jkzk" name="jtcy5_jkzk"
								maxlength="20" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy5_jkzk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="9">
						<div align="center">
							已获资助情况
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							国家助学贷款
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="yhzzqk_gjzxdk" name="yhzzqk_gjzxdk" maxlength="6"
							style="width:100%;heigh:100%" onblur="je();" readonly="readonly"
							value="<bean:write name="rs" property="yhzzqk_gjzxdk"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							国家助学金
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="yhzzqk_gjzxj" name="yhzzqk_gjzxj" maxlength="6"
							style="width:100%;heigh:100%" onblur="je();" readonly="readonly"
							value="<bean:write name="rs" property="yhzzqk_gjzxj"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							国家励志奖学金
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="yhzzqk_gjlzjxj" name="yhzzqk_gjlzjxj" maxlength="6"
							style="width:100%;heigh:100%" onblur="je();" readonly="readonly"
							value="<bean:write name="rs" property="yhzzqk_gjlzjxj"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							国家奖学金
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="yhzzqk_gjjxj" name="yhzzqk_gjjxj" maxlength="6"
							style="width:100%;heigh:100%" onblur="je();" readonly="readonly"
							value="<bean:write name="rs" property="yhzzqk_gjjxj"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />奖学金
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="yhzzqk_xyjxj" name="yhzzqk_xyjxj" maxlength="6"
							style="width:100%;heigh:100%" onblur="je();" readonly="readonly"
							value="<bean:write name="rs" property="yhzzqk_xyjxj"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							勤工助学
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="yhzzqk_qgzx" name="yhzzqk_qgzx" maxlength="6"
							style="width:100%;heigh:100%" onblur="je();" readonly="readonly"
							value="<bean:write name="rs" property="yhzzqk_qgzx"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							学费减免
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="yhzzqk_xfjm" name="yhzzqk_xfjm" maxlength="6"
							style="width:100%;heigh:100%" onblur="je();" readonly="readonly"
							value="<bean:write name="rs" property="yhzzqk_xfjm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							临时补助
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="yhzzqk_lxbz" name="yhzzqk_lxbz" maxlength="6"
							style="width:100%;heigh:100%" onblur="je();" readonly="readonly"
							value="<bean:write name="rs" property="yhzzqk_lxbz"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							当地政府、社会资助
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="yhzzqk_ddzfshjxj" name="yhzzqk_ddzfshjxj" maxlength="6"
							style="width:100%;heigh:100%" onblur="je();"
							value="<bean:write name="rs" property="yhzzqk_ddzfshjxj"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							合计
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="yhzzqk_hj" name="yhzzqk_hj" maxlength="10"
							style="width:100%;heigh:100%" readonly="readonly"
							value="<bean:write name="rs" property="yhzzqk_hj"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							家庭遭受自然灾害情况
						</div>
					</td>
					<td colspan="6">
						<input type="text" id="jtzszrzhqk" name="jtzszrzhqk"
							maxlength="200" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtzszrzhqk"/>">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							家庭遭受突发意外事件
						</div>
					</td>
					<td colspan="6">
						<input type="text" id="jttfywsj" name="jttfywsj"
							maxlength="200" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jttfywsj"/>">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							家庭成员因残疾、年迈而劳动能力弱情况
						</div>
					</td>
					<td colspan="6">
						<input type="text" id="jtcyycjnmndlqk" name="jtcyycjnmndlqk"
							maxlength="200" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcyycjnmndlqk"/>">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							家庭成员失业情况
						</div>
					</td>
					<td colspan="6">
						<input type="text" id="jtcysyqk" name="jtcysyqk"
							maxlength="200" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcysyqk"/>">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							家庭欠债情况
						</div>
					</td>
					<td colspan="6">
						<input type="text" id="jtqzqk" name="jtqzqk"
							maxlength="200" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtqzqk"/>">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							其他情况
						</div>
					</td>
					<td colspan="6">
						<input type="text" id="qtqk" name="qtqk"
							maxlength="200" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="qtqk"/>">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							家庭详细经济情况说明
						</div>
					</td>
					<td colspan="6">
						<html:textarea name="rs" property="jtjjqkxxsm" rows='5'
							style="width:100%" onblur="yzdx(this,'jtjjqkxxsm')" />
						<input type="hidden" id="jtjjqkxxsm" name="jtjjqkxxsm" value="">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							民政部门邮政编码
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="mzbm_yzbm" name="mzbm_yzbm" maxlength="6"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzbm_yzbm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							民政部门联系电话
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="mzbm_lxdh" name="mzbm_lxdh" maxlength="15"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzbm_lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							民政部门详细通讯地址
						</div>
					</td>
					<td colspan="6">
						<input type="text" id="mzbm_xxtxdz" name="mzbm_xxtxdz" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzbm_xxtxdz"/>">
					</td>
				</tr>
			</table>
	<logic:equal name="sfksq" value="1">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button type="button" class="button2" onClick="yz();">
					提交申请
				</button>
				<button type="button" class="button2" onClick="toPrintOut();">
					打&nbsp;&nbsp;&nbsp;&nbsp;印
				</button>
			</div>
	</logic:equal>
		</html:form>
</body>
	<script language="javascript">
		var xh = document.getElementById('xh').value;
			
		if((xh != null) && (xh != "")){
			var yhzzqk_gjzxdk = document.getElementById('yhzzqk_gjzxdk').value;
			var yhzzqk_gjzxj = document.getElementById('yhzzqk_gjzxj').value;
			var yhzzqk_gjjxj = document.getElementById('yhzzqk_gjjxj').value;
			var yhzzqk_gjlzjxj = document.getElementById('yhzzqk_gjlzjxj').value;
			var yhzzqk_xyjxj = document.getElementById('yhzzqk_xyjxj').value;
			var yhzzqk_qgzx = document.getElementById('yhzzqk_qgzx').value;
			var yhzzqk_xfjm = document.getElementById('yhzzqk_xfjm').value;
			var yhzzqk_lxbz = document.getElementById('yhzzqk_lxbz').value;
			var yhzzqk_ddzfshjxj = document.getElementById('yhzzqk_ddzfshjxj').value;
			var yhzzqk_hj = "0";
			
			if((yhzzqk_gjzxdk == null) || (yhzzqk_gjzxdk == "")){
				yhzzqk_gjzxdk = "0";
				document.getElementById('yhzzqk_gjzxdk').value = yhzzqk_gjzxdk;
			}
			if((yhzzqk_gjzxj == null) || (yhzzqk_gjzxj == "")){
				yhzzqk_gjzxj = "0";
				document.getElementById('yhzzqk_gjzxj').value = yhzzqk_gjzxj;
			}
			if((yhzzqk_gjjxj == null) || (yhzzqk_gjjxj == "")){
				yhzzqk_gjjxj = "0";
				document.getElementById('yhzzqk_gjjxj').value = yhzzqk_gjjxj;
			}
			if((yhzzqk_gjlzjxj == null) || (yhzzqk_gjlzjxj == "")){
				yhzzqk_gjlzjxj = "0";
				document.getElementById('yhzzqk_gjlzjxj').value = yhzzqk_gjlzjxj;
			}
			if((yhzzqk_xyjxj == null) || (yhzzqk_xyjxj == "")){
				yhzzqk_xyjxj = "0";
				document.getElementById('yhzzqk_xyjxj').value = yhzzqk_xyjxj;
			}
			if((yhzzqk_qgzx == null) || (yhzzqk_qgzx == "")){
				yhzzqk_qgzx = "0";
				document.getElementById('yhzzqk_qgzx').value = yhzzqk_qgzx;
			}
			if((yhzzqk_xfjm == null) || (yhzzqk_xfjm == "")){
				yhzzqk_xfjm = "0";
				document.getElementById('yhzzqk_xfjm').value = yhzzqk_xfjm;
			}
			if((yhzzqk_lxbz == null) || (yhzzqk_lxbz == "")){
				yhzzqk_lxbz = "0";
				document.getElementById('yhzzqk_lxbz').value = yhzzqk_lxbz;
			}
			if((yhzzqk_ddzfshjxj == null) || (yhzzqk_ddzfshjxj == "")){
				yhzzqk_ddzfshjxj = "0";
			}
			
			yhzzqk_hj = Math.round(yhzzqk_gjzxdk) + Math.round(yhzzqk_gjzxj) + Math.round(yhzzqk_gjjxj) + Math.round(yhzzqk_gjlzjxj) + Math.round(yhzzqk_xyjxj) + Math.round(yhzzqk_qgzx) + Math.round(yhzzqk_xfjm) + Math.round(yhzzqk_lxbz) + Math.round(yhzzqk_ddzfshjxj);
			document.getElementById('yhzzqk_hj').value = yhzzqk_hj;
		}
	</script>
</html:html>
