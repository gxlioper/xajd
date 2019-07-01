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
	<script language="javascript">
		function yz(titName){
			var xh = document.getElementById('xh').value;
			var hkrs = document.getElementById('hkrs').value;
			var jtyzsr = document.getElementById('jtyzsr').value;
			var jtrjsr = document.getElementById('jtrjsr').value;
			var yzbm = document.getElementById('yzbm').value;
			var jlxx = document.getElementById('jlxx').value;
			var jtzz = document.getElementById('jtzz').value;
			var sqly = document.getElementById('sqly').value;
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
			
			document.forms[0].action = "/xgxt/szxx_gjjxjsq.do?doType=add&titName=" + titName;
			document.forms[0].submit();
		}
		
		function toPrintOut(titName){//输出相应的打印页面
			document.forms[0].action = "/xgxt/szxx_gjjxjsqb.do";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 国家奖学金申请
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				现在不在申请时间内！！！
			</p>
		</center>
	</logic:equal>
		<html:form action="szxx_gjjxjsq.do" method="post">
			<input type="hidden" id="titName" name="titName"
				value="<bean:write name="titName" scope="request" />">
			<input type="hidden" id="url" name="url"
				value="/szxx_gjjxjsq.do" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj"/>">
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj"/>">
			<input type="hidden" id="jxjdjmc" name="jxjdjmc"
				value="<bean:write name="rs" property="jxjdjmc"/>">
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
									<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
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
								<input type="text" id="xymc" name="xymc" readonly="readonly"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="xymc"/>">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									系
								</div>
							</td>
							<td colspan="2">
								<input type="text" readonly="readonly" id="zymc" name="zymc"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="zymc"/>">
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
									奖学金等级
								</div>
							</td>
							<td colspan="2">
								<html:select name="rs" property="jxjdjdm" style="width:80%">
										<html:option value="">
										-------请选择--------
										</html:option>
										<html:options collection="jxjdjList" property="jxjdjdm"
											labelProperty="jxjdjmc" />
									</html:select>
							</td>
							<td>
								<div align="center">
									奖学金金额
								</div>
							</td>
							<td>
								<input type="text" id="jjje" maxlength="5" name="jjje"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jjje"/>"
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
							<td>
								<div align="center"></div>
							</td>
							<td></td>
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
								<html:textarea name="rs" property="jlxx" rows='3' style="width:100%" onblur="yzdx(this,'jlxx')"/>
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
								<logic:present name="rs" property="radjthk">
									<logic:match value="城镇" name="rs" property="radjthk">
										<input type="radio" name="radjthk" value="城镇" checked>&nbsp;&nbsp;A  城镇
							         	<input type="radio" name="radjthk" value="农村">&nbsp;&nbsp;B 农村
							         </logic:match>
									<logic:match value="农村" name="rs" property="radjthk">
										<input type="radio" name="radjthk" value="城镇">&nbsp;&nbsp;A  城镇
							         	<input type="radio" name="radjthk" value="农村" checked>&nbsp;&nbsp;B 农村
							         </logic:match>
								</logic:present>
								<logic:notPresent name="rs" property="radjthk">
									<input type="radio" name="radjthk" value="城镇" checked>&nbsp;&nbsp;A  城镇 
							         <input type="radio" name="radjthk" value="农村">&nbsp;&nbsp;B 农村
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
								<input type="text" name="jtsrly" maxlength="10" name="jtsrly"
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
								<input type="text" id="jtcy1_xm" maxlength="40" name="jtcy1_xm"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy1_xm"/>">
							</td>
							<td>
								<input type="text" id="jtcy1_nl" name="jtcy1_nl" maxlength="3"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy1_nl" />"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
							<td>
								<input type="text" id="jtcy1_gx" name="jtcy1_gx" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy1_gx" />">
							</td>
							<td colspan="2">
								<input type="text" id="jtcy1_gzdz" maxlength="50"
									name="jtcy1_gzdz" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy1_gzdz" />">
							</td>
						</tr>
						<tr>
							<td width="12%">
								<input type="text" id="jtcy2_xm" name="jtcy2_xm" maxlength="40"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy2_xm"/>">
							</td>
							<td>
								<input type="text" id="jtcy2_nl" name="jtcy2_nl" maxlength="3"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy2_nl" />"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
							<td>
								<input type="text" id="jtcy2_gx" name="jtcy2_gx" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy2_gx" />">
							</td>
							<td colspan="2">
								<input type="text" id="jtcy2_gzdz" maxlength="50"
									name="jtcy2_gzdz" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy2_gzdz" />">
							</td>
						</tr>
						<tr>
							<td width="12%">
								<input type="text" id="jtcy3_xm" name="jtcy3_xm" maxlength="40"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy3_xm"/>">
							</td>
							<td>
								<input type="text" id="jtcy3_nl" name="jtcy3_nl" maxlength="3"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy3_nl" />"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
							<td>
								<input type="text" id="jtcy3_gx" name="jtcy3_gx" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy3_gx" />">
							</td>
							<td colspan="2">
								<input type="text" id="jtcy3_gzdz" maxlength="50"
									name="jtcy3_gzdz" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy3_gzdz" />">
							</td>
						</tr>
						<tr>
							<td width="12%">
								<input type="text" id="jtcy4_xm" name="jtcy4_xm" maxlength="40"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy4_xm"/>">
							</td>
							<td>
								<input type="text" id="jtcy4_nl" name="jtcy4_nl" maxlength="3"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy4_nl" />"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
							<td>
								<input type="text" id="jtcy4_gx" name="jtcy4_gx" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy4_gx" />">
							</td>
							<td colspan="2">
								<input type="text" id="jtcy4_gzdz" maxlength="50"
									name="jtcy4_gzdz" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy4_gzdz" />">
							</td>
						</tr>
						<tr>
							<td width="12%">
								<input type="text" id="jtcy5_xm" name="jtcy5_xm" maxlength="40"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy5_xm"/>">
							</td>
							<td>
								<input type="text" id="jtcy5_nl" name="jtcy5_nl" maxlength="3"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy5_nl" />"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
							<td>
								<input type="text" id="jtcy5_gx" name="jtcy5_gx" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy5_gx" />">
							</td>
							<td colspan="2">
								<input type="text" id="jtcy5_gzdz" maxlength="50"
									name="jtcy5_gzdz" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy5_gzdz" />">
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
				<button class="button2"
					onClick="yz(document.getElementById('titName').value);">
					提交申请
				</button>
				<button class="button2"
					onClick="toPrintOut(document.getElementById('titName').value);">
					打&nbsp;&nbsp;&nbsp;&nbsp;印
				</button>
			</div>
	</logic:equal>

		</html:form>
</body>
</html:html>
