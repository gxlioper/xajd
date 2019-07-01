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
	<%
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			var hkrs = document.getElementById('hkrs').value;
			var jtyzsr = document.getElementById('jtyzsr').value;
			var jtrjsr = document.getElementById('jtrjsr').value;
			var yzbm = document.getElementById('yzbm').value;
			var JTCY1_nl = document.getElementById('JTCY1_nl').value;
			var JTCY2_nl = document.getElementById('JTCY2_nl').value;
			var JTCY3_nl = document.getElementById('JTCY3_nl').value;
			var JTCY4_nl = document.getElementById('JTCY4_nl').value;
			var JTCY5_nl = document.getElementById('JTCY5_nl').value;
			var jlxx = document.getElementById('jlxx').value;
			var jtzz = document.getElementById('jtzz').value;
			var sqly = document.getElementById('sqly').value;
			var titName = $("titName").value;
			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
			if((hkrs != null) && (hkrs != "") && (!isNumber(hkrs))){
				alert("户口人数必须为整数!");
				return false;
			}
			if((jtyzsr != null) && (jtyzsr != "") && (!isNumber(jtyzsr))){
				alert("家庭月总收入必须为整数!");
				return false;
			}
			if((jtrjsr != null) && (jtrjsr != "") && (!isNumber(jtrjsr))){
				alert("家庭人均收入必须为整数!");
				return false;
			}
			if((yzbm != null) && (yzbm != "") && (!isNumber(yzbm))){
				alert("邮政编码必须为整数!");
				return false;
			}
			if((JTCY1_nl != null) && (JTCY1_nl != "") && (!isNumber(JTCY1_nl))){
				alert("家庭成员1年龄必须为整数!");
				return false;
			}
			if((JTCY2_nl != null) && (JTCY2_nl != "") && (!isNumber(JTCY2_nl))){
				alert("家庭成员2年龄必须为整数!");
				return false;
			}
			if((JTCY3_nl != null) && (JTCY3_nl != "") && (!isNumber(JTCY3_nl))){
				alert("家庭成员3年龄必须为整数!");
				return false;
			}
			if((JTCY4_nl != null) && (JTCY4_nl != "") && (!isNumber(JTCY4_nl))){
				alert("家庭成员4年龄必须为整数!");
				return false;
			}
			if((JTCY5_nl != null) && (JTCY5_nl != "") && (!isNumber(JTCY5_nl))){
				alert("家庭成员5年龄必须为整数!");
				return false;
			}
			if(jlxx != null){
	         	if(jlxx.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("奖励信息不能大于100个字符");
	          		 return false;
	       		 }
	       	}
	       	if(jtzz != null){
	         	if(jtzz.replace(/[^\u0000-\u00ff]/g, "**").length > 50){	         
	          		 alert("家庭住址不能大于50个字符");
	          		 return false;
	       		 }
	       	}
	       	if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("申请理由不能大于1000个字符");
	          		 return false;
	       		 }
			}
			BatAlert.showTips('正在操作，请稍等...');
			document.forms[0].action = "/xgxt/xszz_ynys.do?method=jzxjsq&doType=add&lx=" + titName;
			document.forms[0].submit();
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
		
		function toPrintOut(){//输出相应的打印页面
			var titName = $("titName").value;	
			document.forms[0].action = "/xgxt/xszz_ynys.do?method=jzxjprint";
			document.forms[0].submit();
		}
	</script>
</head>

<body onload="loadPage()">
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：<bean:write name="title"/>
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				现在不在申请时间内！！！
			</p>
		</center>
	</logic:equal>
		<html:form action="/xszz_ynys" method="post">
			<input type="hidden" id="titName" name="titName"
				value="<bean:write name="titName" scope="request" />">
			<logic:equal name="titName"value="jbgl">
			<input type="hidden" id="url" name="url"
				value="/xszz_ynys.do?method=jzxjsq&lx=jbgl" />
			</logic:equal>
			<logic:equal name="titName"value="xy">
			<input type="hidden" id="url" name="url"
				value="/xszz_ynys.do?method=jzxjsq&lx=xy" />
			</logic:equal>
			<logic:equal name="titName"value="jj">
			<input type="hidden" id="url" name="url"
				value="/xszz_ynys.do?method=jzxjsq&lx=jj" />
			</logic:equal>
			<logic:equal name="titName"value="gjzxdkjbzx">
			<input type="hidden" id="url" name="url"
				value="/xszz_ynys.do?method=jzxjsq&lx=gjzxdkjbzx" />
			</logic:equal>
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj"/>">
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj"/>">
			<logic:present name="isPASS">
				<logic:match value="is" name="isPASS">
					<script language="javascript">
	         			alert("已通过审核，不能申请！");
	         		</script>
				</logic:match>
			</logic:present>
			<logic:present name="kns">
				<logic:match value="no" name="kns">
					<script language="javascript">
	         			alert("必须是困难生才能申请！");
	         		</script>
				</logic:match>
			</logic:present>
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
			<logic:notEqual name="isCSMZ" value="is">
			<div class="xxk">
				<logic:notEmpty name="pages">
					<logic:iterate id="card" name="pages" scope="request">
						<ul>
							<li id="<bean:write name='card' property='en'/>l"
								class="xxk_off_l"></li>
							<li id="<bean:write name='card' property='en'/>m"
								onclick="changePage(this)" class="xxk_off_m">
								&nbsp;
								<bean:write name='card' property='cn' />
								&nbsp;
							</li>
							<li id="<bean:write name='card' property='en'/>r"
								class="xxk_off_r"></li>
						</ul>
					</logic:iterate>
				</logic:notEmpty>
			</div>
			</logic:notEqual>

					<table class="tbstyle">
						<tr>
							<logic:equal name="userOnLine" value="teacher" scope="session">
								<td align="right" colspan="2">
									<font color="red">*</font>学号：
								</td>
								<td align="left" colspan="2">
									<html:text name="rs" property="xh" styleId="xh"
										onkeypress="autoFillStuInfo(event.keyCode,this)"
										readonly="true" />
									<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										选择
									</button>
								</td>
							</logic:equal>
							<logic:equal name="userOnLine" value="student" scope="session">
								<td align="right" colspan="2">
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
							<td width="34%">
								<input type="text" readonly="readonly" id="xm" name="xm"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="xm"/>"
									readonly="readonly">
								<input type="hidden" id="nj" name="nj" value="${rs.nj}">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									出生年月
								</div>
							</td>
							<td colspan="2">
								<input type="text" id="csny" readonly="readonly" name="csny"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="csny"/>">
							</td>
							<td>
								<div align="center">
									性别
								</div>
							</td>
							<td>
								<input type="text" id="xb" name="xb" readonly="readonly"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="xb"/>">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									入学时间
								</div>
							</td>
							<td colspan="2">
								<input type="text" id="rxny" readonly="readonly" name="rxny"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="rxny"/>">
							</td>
							<td>
								<div align="center">
									民族
								</div>
							</td>
							<td>
								<input type="text" id="mzmc" name="mzmc" readonly="readonly"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="mzmc"/>">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									大学
								</div>
							</td>
							<td colspan="2">
								<input type="text" id="xxmc" readonly="readonly" name="xxmc"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="xxmc"/>">
							</td>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />
								</div>
							</td>
							<td>
								<input type="text" id="xy" name="xy" readonly="readonly"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="xy"/>">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									系
								</div>
							</td>
							<td colspan="2">
								<input type="text" readonly="readonly" id="xmc" name="xmc"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="xmc"/>">
							</td>
							<td>
								<div align="center">
									班
								</div>
							</td>
							<td>
								<input type="text" id="bjmc" readonly="readonly" name="bjmc"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="bjmc"/>">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									政治面貌
								</div>
							</td>
							<td colspan="2">
								<input type="text" id="zzmmmc" readonly="readonly" name="zzmmmc"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="zzmmmc"/>">
							</td>
							<td>
								<div align="center">
									身份证号
								</div>
							</td>
							<td>
								<input type="text" id="sfzh" readonly="readonly" name="sfzh"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="sfzh"/>">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									上学年综合<br />测评成绩
								</div>
							</td>
							<td colspan="2">
								<input type="text" id="sxncj" maxlength="20" name="sxncj"
									style="width:100%;heigh:100%" onkeyup="value=value.replace(/[^\d|.]/g,'') "
									value="<bean:write name="rs" property="sxncj"/>">
							</td>
							<td>
								<div align="center">
									联系电话
								</div>
							</td>
							<td>
								<input type="text" id="lxdh" maxlength="15" name="lxdh"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="lxdh"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									卡号
								</div>
							</td>
							<td colspan="2">
								<input type="text" readonly="readonly" id="kh" name="kh"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="kh"/>">
							</td>
							<logic:equal name="titName"value="xy">
							<td>
								<div align="center">
									级别
								</div>
							</td>
							<td>
								<html:select name="rs" property="jb" styleId="jb">
									<html:options collection="jbList" labelProperty="mc" property="dm"/>
								</html:select>
							</td>
							</logic:equal>
							<logic:notEqual name="titName"value="xy">
							<td></td>
							<td></td>
							</logic:notEqual>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									曾获何种
								</div>
								<p align="center">
									奖励
									<br>
									(50个字以内)
							</td>
							<td colspan="4">
								<html:textarea name="rs" property="jlxx" rows='5' style="width:100%" onblur="yzdx(this,'jlxx')"/>
								<input type="hidden" id="jlxx" name="jlxx" value="">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									家庭户口
								</div>
							</td>
							<td colspan="2" align="center">

								<logic:present name="rs" property="radJthk">
									<logic:match value="城镇" name="rs" property="radJthk">
										<input type="radio" name="radJthk" value="城镇" checked>&nbsp;&nbsp;A  城镇
							         	<input type="radio" name="radJthk" value="农村">&nbsp;&nbsp;B 农村
							         </logic:match>
									<logic:match value="农村" name="rs" property="radJthk">
										<input type="radio" name="radJthk" value="城镇">&nbsp;&nbsp;A  城镇
							         	<input type="radio" name="radJthk" value="农村" checked>&nbsp;&nbsp;B 农村
							         </logic:match>
								</logic:present>
								<logic:notPresent name="rs" property="radJthk">
									<input type="radio" name="radJthk" value="城镇" checked>&nbsp;&nbsp;A  城镇 
							         <input type="radio" name="radJthk" value="农村">&nbsp;&nbsp;B 农村
						         </logic:notPresent>
							</td>
							<td>
								<div align="center">
									家庭户口人数
								</div>
							</td>
							<td>
								<input type="text" id="hkrs" name="hkrs" maxlength="10"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="hkrs"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									家庭月总收入
								</div>
							</td>
							<td colspan="2">
								<input name="jtyzsr" id="jtyzsr" maxlength="10" type="text"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtyzsr"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
							<td>
								<div align="center">
									家庭人均收入
								</div>
							</td>
							<td>
								<input type="text" id="jtrjsr" name="jtrjsr" maxlength="10"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtrjsr"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									家庭收入来源
								</div>
							</td>
							<td colspan="2">
								<input type="text" name="jtsrly" maxlength="50" name="jtsrly"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtsrly"/>">
							</td>
							<td>
								<div align="center">
									邮政编码
								</div>
							</td>
							<td>
								<input name="yzbm" id="yzbm" maxlength="6" type="text"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="yzbm"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									家庭住址
								</div>
							</td>
							<td colspan="4">
								<html:textarea name="rs" property="jtzz" rows='3' style="width:100%" onblur="yzdx(this,'jtzz')"/>
								<input type="hidden" id="jtzz" name="jtzz" value="">
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
									年龄
								</div>
							</td>
							<td width="25%">
								<div align="center">
									与本人关系
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
								<input type="text" id="JTCY1_XM" maxlength="40" name="JTCY1_XM"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY1_XM"/>">
							</td>
							<td>
								<input type="text" id="JTCY1_nl" name="JTCY1_nl" maxlength="3"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY1_nl" />"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
							<td>
								<input type="text" id="JTCY1_GX" name="JTCY1_GX" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY1_GX" />">
							</td>
							<td colspan="2">
								<input type="text" id="JTCY1_GZDZ" maxlength="50"
									name="JTCY1_GZDZ" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY1_GZDZ" />">
							</td>
						</tr>
						<tr>
							<td width="12%">
								<input type="text" id="JTCY2_XM" name="JTCY2_XM" maxlength="40"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY2_XM"/>">
							</td>
							<td>
								<input type="text" id="JTCY2_nl" name="JTCY2_nl" maxlength="3"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY2_nl" />"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
							<td>
								<input type="text" id="JTCY2_GX" name="JTCY2_GX" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY2_GX" />">
							</td>
							<td colspan="2">
								<input type="text" id="JTCY2_GZDZ" maxlength="50"
									name="JTCY2_GZDZ" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY2_GZDZ" />">
							</td>
						</tr>
						<tr>
							<td width="12%">
								<input type="text" id="JTCY3_XM" name="JTCY3_XM" maxlength="40"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY3_XM"/>">
							</td>
							<td>
								<input type="text" id="JTCY3_nl" name="JTCY3_nl" maxlength="3"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY3_nl" />"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
							<td>
								<input type="text" id="JTCY3_GX" name="JTCY3_GX" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY3_GX" />">
							</td>
							<td colspan="2">
								<input type="text" id="JTCY3_GZDZ" maxlength="50"
									name="JTCY3_GZDZ" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY3_GZDZ" />">
							</td>
						</tr>
						<tr>
							<td width="12%">
								<input type="text" id="JTCY4_XM" name="JTCY4_XM" maxlength="40"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY4_XM"/>">
							</td>
							<td>
								<input type="text" id="JTCY4_nl" name="JTCY4_nl" maxlength="3"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY4_nl" />"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
							<td>
								<input type="text" id="JTCY4_GX" name="JTCY4_GX" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY4_GX" />">
							</td>
							<td colspan="2">
								<input type="text" id="JTCY4_GZDZ" maxlength="50"
									name="JTCY4_GZDZ" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY4_GZDZ" />">
							</td>
						</tr>
						<tr>
							<td width="12%">
								<input type="text" id="JTCY5_XM" name="JTCY5_XM" maxlength="40"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY5_XM"/>">
							</td>
							<td>
								<input type="text" id="JTCY5_nl" name="JTCY5_nl" maxlength="3"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY5_nl" />"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
							<td>
								<input type="text" id="JTCY5_GX" name="JTCY5_GX" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY5_GX" />">
							</td>
							<td colspan="2">
								<input type="text" id="JTCY5_GZDZ" maxlength="50"
									name="JTCY5_GZDZ" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY5_GZDZ" />">
							</td>
						</tr>
						<tr>
							<td>
								<br>
								申请
								<br>
								理由
							</td>
							<td colspan="5">
								<html:textarea name="rs" property="sqly" rows='5' style="width:100%" onblur="yzdx(this,'sqly')"/>
								<input type="hidden" id="sqly" name="sqly" value="">
							</td>
						</tr>
					</table>
	<logic:equal name="sfksq" value="1">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button type="button" class="button2"
					onClick="yz();">
					提交申请
				</button>
				<button type="button" class="button2"
					onClick="toPrintOut();">
					打&nbsp;&nbsp;&nbsp;&nbsp;印
				</button>
			</div>
	</logic:equal>

	</html:form>
</body>
<logic:notEmpty name="IsKns">
	<logic:equal name="IsKns" value="no">
		<script>
						alert("不是困难生不能申请!");
						</script>
	</logic:equal>
</logic:notEmpty>
</html:html>
