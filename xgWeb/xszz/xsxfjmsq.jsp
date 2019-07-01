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
			var xh  = document.getElementById('xh').value;
			var yxsbzdkqk  = document.getElementById('yxsbzdkqk').value;
			var zxcjqgzxqk  = document.getElementById('zxcjqgzxqk').value;
			var sqly  = document.getElementById('sqly').value;
			if(xh == null){
				alert("学号不能为空!");
				return false;
			}
			if(yxsbzdkqk != null){
	         	if(yxsbzdkqk.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("已享受困难补助、减免学费及贷款情况不能大于200个字符");
	          		 return false;
	       		 }
	       	}
	       	if(zxcjqgzxqk != null){
	         	if(zxcjqgzxqk.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("在校参加勤工助学情况不能大于200个字符");
	          		 return false;
	       		 }
	       	}
	       	if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("申请理由不能大于1000个字符");
	          		 return false;
	       		 }
			}
			
			document.forms[0].action = "/xgxt/xsxfjmsq.do?jxjlbType=xsxfjmsq&doType=add&titName=" + titName;
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(titName){//输出相应的打印页面
			document.forms[0].action = "/xgxt/xsxfjmsqb.do";
			document.forms[0].submit();
		}
		
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
	</script>

</head>

<body onload="loadPage()">
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 学生学费减免申请
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				现在不在申请时间内！！！
			</p>
		</center>
	</logic:equal>
		<html:form action="xsxfjmsq.do" method="post">
			<input type="hidden" id="titName" name="titName"
				value="<bean:write name="titName" scope="request" />">
			<input type="hidden" id="url" name="url"
				value="/xsxfjmsq.do?jxjlbType=xsxfjmsq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<logic:notEmpty name="isSHGC">
				<logic:equal name="isSHGC" value="is">
					<input type="hidden" id="xyshyj" name="xyshyj"
						value="<bean:write name="rs" property="xyshyj"/>">
					<input type="hidden" id="xxshyj" name="xxshyj"
						value="<bean:write name="rs" property="xxshyj"/>">
					<input type="hidden" id="njmjedx" name="njmjedx"
						value="<bean:write name="rs" property="njmjedx"/>">
					<input type="hidden" id="jmjedx" name="jmjedx"
						value="<bean:write name="rs" property="jmjedx"/>">
				</logic:equal>
			</logic:notEmpty>

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
			<logic:notEmpty name="isSHGCKNS">
				<logic:equal name="isSHGCKNS" value="no">
					<table class="tbstyle">
						<tr>
							<logic:equal name="userOnLine" value="teacher" scope="session">
								<td align="right" colspan="2">
									<font color="red">*</font>学号：
								</td>
								<td align="left" colspan="3">
									<html:text name='rs' property="xh" styleId="xh"
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
								<td align="left" colspan="3">
									<input type="text" id="xh" name="xh" readonly="readonly"
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
								<input type="text" id="xm" name="xm"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="xm"/>"
									readonly="readonly">
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
									政治面貌
								</div>
							</td>
							<td colspan="3">
								<input type="text" id="zzmmmc" readonly="readonly" name="zzmmmc"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="zzmmmc"/>">
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
							<td colspan="3">
								<input type="text" id="xmc" name="xmc" readonly="readonly"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="xmc"/>">
							</td>
							<td>
								<div align="center">
									班
								</div>
							</td>
							<td>
								<input type="text" id="bjmc" name="bjmc" readonly="readonly"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="bjmc"/>">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									卡号
								</div>
							</td>
							<td colspan="3">
								<input type="text" id="kh" name="kh" readonly="readonly"
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
									出生年月
								</div>
							</td>
							<td colspan="3">
								<input type="text" id="csny" readonly="readonly" name="csny"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="csny"/>">
							</td>
							<td>
								<div align="center">
									联系电话
								</div>
							</td>
							<td>
								<input type="text" id="lxdh" name="lxdh" maxlength="12"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="lxdh"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									现任职务
								</div>
							</td>
							<td colspan="3">
								<input type="text" id="xrzw" maxlength="30" name="xrzw"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="xrzw"/>">
							</td>
							<td>
								<div align="center">
									寝室
								</div>
							</td>
							<td>
								<input type="text" id="qs" name="qs" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="qs"/>">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									已享受困难补助、减免学费及贷款情况
								</div>
							</td>
							<td colspan="5">
								<html:textarea name="rs" property="yxsbzdkqk" rows='5' style="width:100%" onblur="yzdx(this,'yxsbzdkqk')"/>
								<input type="hidden" id="yxsbzdkqk" name="yxsbzdkqk" value="">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									在校参加勤工助学情况
								</div>
							</td>
							<td colspan="5">
								<html:textarea name="rs" property="sqly" rows='5' style="width:100%" onblur="yzdx(this,'zxcjqgzxqk')"/>
								<input type="hidden" id="zxcjqgzxqk" name="zxcjqgzxqk" value="">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									家庭住址
								</div>
							</td>
							<td colspan="3">
								<input type="text" name="jtzz" maxlength="60" name="jtzz"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtzz"/>">
							</td>
							<td>
								<div align="center">
									邮政编码
								</div>
							</td>
							<td>
								<input name="yzbm" id="yzbm" type="text" maxlength="6"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="yzbm"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
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
							<td width="15%">
								<div align="center">
									与本人关系
								</div>
							</td>
							<td width="10%">
								<div align="center">
									月收入
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
								<input type="text" id="JTCY1_XM" name="JTCY1_XM" maxlength="50"
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
								<input type="text" id="JTCY1_GX" name="JTCY1_GX" maxlength="10"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY1_GX" />">
							</td>
							<td>
								<input type="text" id="JTCY1_YSR" name="JTCY1_YSR"
									maxlength="10" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY1_YSR" />"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
							<td colspan="2">
								<input type="text" id="JTCY1_GZDZ" maxlength="50"
									name="JTCY1_GZDZ" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY1_GZDZ" />">
							</td>
						</tr>
						<tr>
							<td width="12%">
								<input type="text" id="JTCY2_XM" name="JTCY2_XM" maxlength="50"
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
								<input type="text" id="JTCY2_GX" name="JTCY2_GX" maxlength="10"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY2_GX" />">
							</td>
							<td>
								<input type="text" id="JTCY2_YSR" name="JTCY2_YSR"
									maxlength="10" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY2_YSR" />"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
							<td colspan="2">
								<input type="text" id="JTCY2_GZDZ" maxlength="50"
									name="JTCY2_GZDZ" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY2_GZDZ" />">
							</td>
						</tr>
						<tr>
							<td width="12%">
								<input type="text" id="JTCY3_XM" name="JTCY3_XM" maxlength="50"
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
								<input type="text" id="JTCY3_GX" name="JTCY3_GX" maxlength="10"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY3_GX" />">
							</td>
							<td>
								<input type="text" id="JTCY3_YSR" name="JTCY3_YSR"
									maxlength="10" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY3_YSR" />"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
							<td colspan="2">
								<input type="text" id="JTCY3_GZDZ" maxlength="50"
									name="JTCY3_GZDZ" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY3_GZDZ" />">
							</td>
						</tr>
						<tr>
							<td width="12%">
								<input type="text" id="JTCY4_XM" name="JTCY4_XM" maxlength="50"
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
								<input type="text" id="JTCY4_GX" name="JTCY4_GX" maxlength="10"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY4_GX" />">
							</td>
							<td>
								<input type="text" id="JTCY4_YSR" name="JTCY4_YSR"
									maxlength="10" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY4_YSR" />"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
							<td colspan="2">
								<input type="text" id="JTCY4_GZDZ" maxlength="50"
									name="JTCY4_GZDZ" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY4_GZDZ" />">
							</td>
						</tr>
						<tr>
							<td width="12%">
								<input type="text" id="JTCY5_XM" name="JTCY5_XM" maxlength="50"
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
								<input type="text" id="JTCY5_GX" name="JTCY5_GX" maxlength="10"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY5_GX" />">
							</td>
							<td>
								<input type="text" id="JTCY5_YSR" name="JTCY5_YSR"
									maxlength="10" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY5_YSR" />"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
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
							<td colspan="6">
								<html:textarea name="rs" property="sqly" rows='5' style="width:100%" onblur="yzdx(this,'sqly')"/>
								<input type="hidden" id="sqly" name="sqly" value="">
							</td>
						</tr>
					</table>
				</logic:equal>
				<logic:equal name="isSHGCKNS" value="is">
					<table class="tbstyle">
						<tr>
							<logic:equal name="userOnLine" value="teacher" scope="session">
								<td align="right" colspan="2">
									<font color="red">*</font>学号：
								</td>
								<td align="left" colspan="3">
									<html:text name='rs' property="xh" styleId="xh"
										readonly="readonly"
										onkeypress="autoFillStuInfo(event.keyCode,this)" />
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
								<td align="left" colspan="3">
									<input type="text" id="xh" name="xh" readonly="readonly"
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
								<input type="text" id="xm" readonly="readonly" name="xm"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="xm"/>"
									readonly="readonly">
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
									政治面貌
								</div>
							</td>
							<td colspan="3">
								<input type="text" id="zzmmmc" readonly="readonly" name="zzmmmc"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="zzmmmc"/>">
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
							<td colspan="3">
								<input type="text" id="xmc" readonly="readonly" name="xmc"
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
									卡号
								</div>
							</td>
							<td colspan="3">
								<input type="text" id="kh" name="kh" readonly="readonly"
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
									出生年月
								</div>
							</td>
							<td colspan="3">
								<input type="text" id="csny" readonly="readonly" name="csny"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="csny"/>">
							</td>
							<td>
								<div align="center">
									联系电话
								</div>
							</td>
							<td>
								<input type="text" id="lxdh" name="lxdh" maxlength="12"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="lxdh"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									现任职务
								</div>
							</td>
							<td colspan="3">
								<input type="text" id="xrzw" maxlength="30" name="xrzw"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="xrzw"/>">
							</td>
							<td>
								<div align="center">
									寝室
								</div>
							</td>
							<td>
								<input type="text" id="qs" name="qs" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="qs"/>">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									已享受困难补助、减免学费及贷款情况
								</div>
							</td>
							<td colspan="5">
								<html:textarea name="rs" property="yxsbzdkqk" rows='5' style="width:100%" onblur="yzdx(this,'yxsbzdkqk')"/>
								<input type="hidden" id="yxsbzdkqk" name="yxsbzdkqk" value="">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									在校参加勤工助学情况
								</div>
							</td>
							<td colspan="5">
								<html:textarea name="rs" property="sqly" rows='5' style="width:100%" onblur="yzdx(this,'zxcjqgzxqk')"/>
								<input type="hidden" id="zxcjqgzxqk" name="zxcjqgzxqk" value="">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									家庭住址
								</div>
							</td>
							<td colspan="3">
								<input type="text" name="jtzz" readonly="readonly" name="jtzz"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtzz"/>">
							</td>
							<td>
								<div align="center">
									邮政编码
								</div>
							</td>
							<td>
								<input name="yzbm" id="yzbm" type="text" readonly="readonly"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="yzbm"/>">
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
							<td width="15%">
								<div align="center">
									与本人关系
								</div>
							</td>
							<td width="10%">
								<div align="center">
									月收入
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
								<input type="text" id="JTCY1_XM" readonly="readonly"
									name="JTCY1_XM" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY1_XM"/>">
							</td>
							<td>
								<input type="text" id="JTCY1_nl" readonly="readonly"
									name="JTCY1_nl" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY1_nl" />">
							</td>
							<td>
								<input type="text" id="JTCY1_GX" readonly="readonly"
									name="JTCY1_GX" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY1_GX" />">
							</td>
							<td>
								<input type="text" id="JTCY1_YSR" readonly="readonly"
									name="JTCY1_YSR" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY1_YSR" />">
							</td>
							<td colspan="2">
								<input type="text" id="JTCY1_GZDZ" readonly="readonly"
									name="JTCY1_GZDZ" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY1_GZDZ" />">
							</td>
						</tr>
						<tr>
							<td width="12%">
								<input type="text" id="JTCY2_XM" readonly="readonly"
									name="JTCY2_XM" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY2_XM"/>">
							</td>
							<td>
								<input type="text" id="JTCY2_nl" readonly="readonly"
									name="JTCY2_nl" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY2_nl" />">
							</td>
							<td>
								<input type="text" id="JTCY2_GX" readonly="readonly"
									name="JTCY2_GX" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY2_GX" />">
							</td>
							<td>
								<input type="text" id="JTCY2_YSR" readonly="readonly"
									name="JTCY2_YSR" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY2_YSR" />">
							</td>
							<td colspan="2">
								<input type="text" id="JTCY2_GZDZ" readonly="readonly"
									name="JTCY2_GZDZ" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY2_GZDZ" />">
							</td>
						</tr>
						<tr>
							<td width="12%">
								<input type="text" id="JTCY3_XM" readonly="readonly"
									name="JTCY3_XM" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY3_XM"/>">
							</td>
							<td>
								<input type="text" id="JTCY3_nl" readonly="readonly"
									name="JTCY3_nl" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY3_nl" />">
							</td>
							<td>
								<input type="text" id="JTCY3_GX" readonly="readonly"
									name="JTCY3_GX" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY3_GX" />">
							</td>
							<td>
								<input type="text" id="JTCY3_YSR" readonly="readonly"
									name="JTCY3_YSR" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY3_YSR" />">
							</td>
							<td colspan="2">
								<input type="text" id="JTCY3_GZDZ" readonly="readonly"
									name="JTCY3_GZDZ" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY3_GZDZ" />">
							</td>
						</tr>
						<tr>
							<td width="12%">
								<input type="text" id="JTCY4_XM" readonly="readonly"
									name="JTCY4_XM" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY4_XM"/>">
							</td>
							<td>
								<input type="text" id="JTCY4_nl" readonly="readonly"
									name="JTCY4_nl" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY4_nl" />">
							</td>
							<td>
								<input type="text" id="JTCY4_GX" readonly="readonly"
									name="JTCY4_GX" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY4_GX" />">
							</td>
							<td>
								<input type="text" id="JTCY4_YSR" readonly="readonly"
									name="JTCY4_YSR" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY4_YSR" />">
							</td>
							<td colspan="2">
								<input type="text" id="JTCY4_GZDZ" readonly="readonly"
									name="JTCY4_GZDZ" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY4_GZDZ" />">
							</td>
						</tr>
						<tr>
							<td width="12%">
								<input type="text" id="JTCY5_XM" readonly="readonly"
									name="JTCY5_XM" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY5_XM"/>">
							</td>
							<td>
								<input type="text" id="JTCY5_nl" readonly="readonly"
									name="JTCY5_nl" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY5_nl" />">
							</td>
							<td>
								<input type="text" id="JTCY5_GX" readonly="readonly"
									name="JTCY5_GX" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY5_GX" />">
							</td>
							<td>
								<input type="text" id="JTCY5_YSR" readonly="readonly"
									name="JTCY5_YSR" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="JTCY5_YSR" />">
							</td>
							<td colspan="2">
								<input type="text" id="JTCY5_GZDZ" readonly="readonly"
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
							<td colspan="6">
								<html:textarea name="rs" property="sqly" rows='5' style="width:100%" onblur="yzdx(this,'sqly')"/>
								<input type="hidden" id="sqly" name="sqly" value="">
							</td>
						</tr>
					</table>
				</logic:equal>
			</logic:notEmpty>
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
