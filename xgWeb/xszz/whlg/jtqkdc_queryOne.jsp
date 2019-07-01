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
			
			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
			document.forms[0].action = "/xgxt/whlg_xszz.do?method=jtqkdc_save";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 数据维护 - 家庭情况调查详细信息
		</div>
	</div>
		<html:form action="hzzy_gajjsq.do" method="post">
			<input type="hidden" id="url" name="url" value="/whlg_xszz.do?method=jtqkdc_queryOne" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />

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
			<table class="tbstyle" width="100%">
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" colspan="2">
							<font color="red">*</font>学号：
						</td>
						<td align="left" colspan="3">
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
						<td align="center" colspan="2">
							<font color="red">*</font>学号：
						</td>
						<td align="left" colspan="3">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xh" />" readonly="true">
						</td>
					</logic:equal>
					<td width="11%">
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
					<td colspan="2">
						<div align="center">
							性别
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="xb" readonly="readonly" name="xb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xb"/>">
					</td>
					<td>
						<div align="center">
							身份证号
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="sfzh" name="sfzh" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfzh"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							年级
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="nj" readonly="readonly" name="nj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="nj"/>">
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="xymc" name="xymc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xymc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							专业
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="zymc" readonly="readonly" name="zymc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zymc"/>">
					</td>
					<td>
						<div align="center">
							班级
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="bjmc" name="bjmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bjmc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							民族
						</div>
					</td>
					<td colspan="3">
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
					<td colspan="2">
						<div align="center">
							出生日期
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="csrq" readonly="readonly" name="csrq"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="csrq"/>">
					</td>
					<td>
						<div align="center">
							入学前户口
						</div>
					</td>
					<td colspan="3" align="center">
						<logic:present name="rs" property="rxqhk">
							<logic:match value="城镇" name="rs" property="rxqhk">
								<input type="radio" name="rxqhk" value="城镇" checked>&nbsp;&nbsp;城镇
							    <input type="radio" name="rxqhk" value="农村">&nbsp;&nbsp;农村
							</logic:match>
							<logic:match value="农村" name="rs" property="rxqhk">
								<input type="radio" name="rxqhk" value="城镇">&nbsp;&nbsp;城镇
							    <input type="radio" name="rxqhk" value="农村" checked>&nbsp;&nbsp;农村
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="rxqhk">
							<input type="radio" name="rxqhk" value="城镇" checked>&nbsp;&nbsp;城镇
							<input type="radio" name="rxqhk" value="农村">&nbsp;&nbsp;农村
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭人口数
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jtrks" maxlength="3" name="jtrks"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtrks"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							毕业学校
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="byxx" name="byxx" maxlength="100"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="byxx"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							个人特长
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="grtc" maxlength="100" name="grtc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="grtc"/>">
					</td>
					<td>
						<div align="center">
							是否孤残
						</div>
					</td>
					<td colspan="3" align="center">
						<logic:present name="rs" property="sfgc">
							<logic:match value="是" name="rs" property="sfgc">
								<input type="radio" name="sfgc" value="是" checked>是
							    <input type="radio" name="sfgc" value="否">否
							</logic:match>
							<logic:match value="否" name="rs" property="sfgc">
								<input type="radio" name="sfgc" value="是">是
							    <input type="radio" name="sfgc" value="否" checked>否
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfgc">
							<input type="radio" name="sfgc" value="是">是
							<input type="radio" name="sfgc" value="否" checked>否
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							是否单亲
						</div>
					</td>
					<td colspan="3" align="center">
						<logic:present name="rs" property="sfdq">
							<logic:match value="是" name="rs" property="sfdq">
								<input type="radio" name="sfdq" value="是" checked>是
							    <input type="radio" name="sfdq" value="否">否
							</logic:match>
							<logic:match value="否" name="rs" property="sfdq">
								<input type="radio" name="sfdq" value="是">是
							    <input type="radio" name="sfdq" value="否" checked>否
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfdq">
							<input type="radio" name="sfdq" value="是">是
							<input type="radio" name="sfdq" value="否" checked>否
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
								<input type="radio" name="sflszn" value="是" checked>是
							    <input type="radio" name="sflszn" value="否">否
							</logic:match>
							<logic:match value="否" name="rs" property="sflszn">
								<input type="radio" name="sflszn" value="是">是
							    <input type="radio" name="sflszn" value="否" checked>否
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sflszn">
							<input type="radio" name="sflszn" value="是">是
							<input type="radio" name="sflszn" value="否" checked>否
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							家庭详细通讯地址
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jtxxtxdz" maxlength="200" name="jtxxtxdz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtxxtxdz"/>">
					</td>
					<td>
						<div align="center">
							学年
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="xn" readonly="readonly" name="xn"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xn"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							邮政编码
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jtyzbm" maxlength="6" name="jtyzbm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtyzbm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							联系电话
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jtlxdh" maxlength="15" name="jtlxdh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtlxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td width="4%" rowspan="7" scope="row">
						<div align="center">
							家
							<br />
							庭
							<br />
							成
							<br />
							员
							<br />
							情
							<br />
							况
						</div>
					</td>
					<td width="12%">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="12%">
						<div align="center">
							年龄
						</div>
					</td>
					<td width="10%">
						<div align="center">
							与学生
							<br />
							关系
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							工作(学习)单位
						</div>
					</td>
					<td width="12%">
						<div align="center">
							职业
						</div>
					</td>
					<td width="10%">
						<div align="center">
							年收入
							<br />
							(元)
						</div>
					</td>
					<td width="12%">
						<div align="center">
							健康状况
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_xm" maxlength="40" name="jtcy1_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_nl" maxlength="3" name="jtcy1_nl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_gx" maxlength="20" name="jtcy1_gx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_gx"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy1_gzdw" maxlength="200" name="jtcy1_gzdw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_gzdw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_zy" maxlength="20" name="jtcy1_zy"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_zy"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_sr" maxlength="8" name="jtcy1_sr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_sr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_jkzk" maxlength="20" name="jtcy1_jkzk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_jkzk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_xm" maxlength="40" name="jtcy2_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_nl" maxlength="3" name="jtcy2_nl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_gx" maxlength="20" name="jtcy2_gx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_gx"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy2_gzdw" maxlength="200" name="jtcy2_gzdw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_gzdw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_zy" maxlength="20" name="jtcy2_zy"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_zy"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_sr" maxlength="8" name="jtcy2_sr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_sr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_jkzk" maxlength="20" name="jtcy2_jkzk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_jkzk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_xm" maxlength="40" name="jtcy3_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_nl" maxlength="3" name="jtcy3_nl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_gx" maxlength="20" name="jtcy3_gx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_gx"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy3_gzdw" maxlength="200" name="jtcy3_gzdw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_gzdw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_zy" maxlength="20" name="jtcy3_zy"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_zy"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_sr" maxlength="8" name="jtcy3_sr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_sr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_jkzk" maxlength="20" name="jtcy3_jkzk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_jkzk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_xm" maxlength="40" name="jtcy4_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_nl" maxlength="3" name="jtcy4_nl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_gx" maxlength="20" name="jtcy4_gx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_gx"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy4_gzdw" maxlength="200" name="jtcy4_gzdw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_gzdw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_zy" maxlength="20" name="jtcy4_zy"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_zy"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_sr" maxlength="8" name="jtcy4_sr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_sr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_jkzk" maxlength="20" name="jtcy4_jkzk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_jkzk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_xm" maxlength="40" name="jtcy5_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_nl" maxlength="3" name="jtcy5_nl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_gx" maxlength="20" name="jtcy5_gx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_gx"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy5_gzdw" maxlength="200" name="jtcy5_gzdw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_gzdw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_zy" maxlength="20" name="jtcy5_zy"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_zy"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_sr" maxlength="8" name="jtcy5_sr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_sr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_jkzk" maxlength="20" name="jtcy5_jkzk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_jkzk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy6_xm" maxlength="40" name="jtcy6_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy6_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy6_nl" maxlength="3" name="jtcy6_nl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy6_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy6_gx" maxlength="20" name="jtcy6_gx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy6_gx"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy6_gzdw" maxlength="200" name="jtcy6_gzdw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy6_gzdw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy6_zy" maxlength="20" name="jtcy6_zy"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy6_zy"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy6_sr" maxlength="8" name="jtcy6_sr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy6_sr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy6_jkzk" maxlength="20" name="jtcy6_jkzk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy6_jkzk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							家庭人均年收入
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jtrjnsr" maxlength="10" name="jtrjnsr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtrjnsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							家庭每月提供生活费
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jtmytgshf" maxlength="6" name="jtmytgshf"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtmytgshf"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							学生本学年已获资助情况
						</div>
					</td>
					<td colspan="7">
						<input type="text" id="xsbxnyhzzqk" maxlength="200" name="xsbxnyhzzqk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xsbxnyhzzqk"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							家庭遭受自然灾害情况
						</div>
					</td>
					<td colspan="7">
						<input type="text" id="jtzszrzh" maxlength="200" name="jtzszrzh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtzszrzh"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							家庭突发意外事件
						</div>
					</td>
					<td colspan="7">
						<input type="text" id="jttfywsj" maxlength="200" name="jttfywsj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jttfywsj"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							家庭成员因残疾、年迈而劳动能力弱情况
						</div>
					</td>
					<td colspan="7">
						<input type="text" id="jtcyycjnmrndnlr" maxlength="200" name="jtcyycjnmrndnlr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcyycjnmrndnlr"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							家庭成员失业情况
						</div>
					</td>
					<td colspan="7">
						<input type="text" id="jtcysyqk" maxlength="200" name="jtcysyqk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcysyqk"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							其他情况
						</div>
					</td>
					<td colspan="7">
						<input type="text" id="qtqk" maxlength="200" name="qtqk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="qtqk"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							民政部门详细通讯地址
						</div>
					</td>
					<td colspan="7">
						<input type="text" id="mzbm_xxtxdz" maxlength="200" name="mzbm_xxtxdz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzbm_xxtxdz"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							邮政编码
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="mzbm_yzbm" maxlength="6" name="mzbm_yzbm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzbm_yzbm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							联系电话
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="mzbm_lxdh" maxlength="15" name="mzbm_lxdh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzbm_lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<button type="button" class="button2" onclick="yz()" style="width:80px"
					id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
</body>
</html:html>
