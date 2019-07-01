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
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			var yjxf = document.getElementById('yjxf').value;
			var xfdk = document.getElementById('xfdk').value;
			var jtjjqk = document.getElementById('jtjjqk').value;
			if(xh == null){
				alert("学号不能为空!");
				return false;
			}
			if(yjxf == "" || yjxf == null){
				yjxf = "0";
			}
			if(xfdk == "" || xfdk == null){
				xfdk = "0";
			}
			if(Math.round(xfdk)>Math.round(yjxf)){
				alert("学费贷款数不能大于应缴学费数!");
				return false;
			}
			if(jtjjqk != null){
	         	if(jtjjqk.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("家庭经济情况不能大于1000个字符！");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/new_common_xszz.do?method=gjzxdksq&act=save";
			document.forms[0].submit();
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		
		function toPrintOut(){//输出相应的打印页面
			document.forms[0].action = "/xgxt/new_common_xszz.do?method=gjzxdksqb";
			document.forms[0].submit();
		}
		
		function je(){
			var yjxf = document.getElementById('yjxf').value;
			var xfdk = document.getElementById('xfdk').value;
			var shfdk = document.getElementById('shfdk').value;
			var hj = "0";
			
			if(yjxf == "" || yjxf == null){
				yjxf = "0";
			}
			if(xfdk == "" || xfdk == null){
				xfdk = "0";
			}
			if(shfdk == "" || shfdk == null){
				shfdk = "0";
			}
			if(Math.round(xfdk)>Math.round(yjxf)){
				alert("学费贷款数不能大于应缴学费数!");
			}
			hj = Math.round(xfdk) + Math.round(shfdk);
			document.getElementById('xfdk').value = xfdk;
			document.getElementById('shfdk').value = shfdk;
			document.getElementById('hj').value = hj;
		}
		
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 国家助学贷款申请
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				现在不在申请时间内,不能申请！！！
			</p>
		</center>
	</logic:equal>
		<html:form action="new_common_xszz.do?method=gjzxdksq" method="post">
			<input type="hidden" id="url" name="url"
				value="/new_common_xszz.do?method=gjzxdksq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="nd" name="nd"
				value="<bean:write name="rs" property="nd" />">
			<input type="hidden" id="sqsj" name="sqsj"
				value="<bean:write name="rs" property="sqsj" />">
			<input type="hidden" id="xysh" name="xysh"
				value="<bean:write name="rs" property="xysh" />">
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj" />">
			<input type="hidden" id="xyshsj" name="xyshsj"
				value="<bean:write name="rs" property="xyshsj" />">
			<input type="hidden" id="xxsh" name="xxsh"
				value="<bean:write name="rs" property="xxsh" />">
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj" />">
			<input type="hidden" id="xxshsj" name="xxshsj"
				value="<bean:write name="rs" property="xxshsj" />">


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
			<logic:present name="have">
				<logic:match value="have" name="have">
					<script language="javascript">
	         			alert("已通过审核，不能申请！");
	         		</script>
				</logic:match>
			</logic:present>

			<table class="tbstyle" width="90%">
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" width="16%">
							<font color="red">*</font>学号
						</td>
						<td align="left" width="34%">
							<html:text name='rs' property="xh" styleId="xh"
								onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true" />
							<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="center" width="16%">
							<font color="red">*</font>学号
						</td>
						<td align="left" width="34%">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xh" />" readonly="true">
						</td>
					</logic:equal>
					<td width="16%" scope="col">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="34%" scope="col">
						<input type="text" id="xm" name="xm" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xm" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							性别
						</div>
					</td>
					<td>
						<input type="text" id="xb" name="xb" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xb" />" readonly="true">
					</td>
					<td>
						<div align="center">
							身份证号
						</div>
					</td>
					<td>
						<input type="text" id="sfzh" name="sfzh" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="sfzh" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td>
						<input type="text" id="xymc" name="xymc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xymc" />" readonly="true">
					</td>
					<td>
						<div align="center">
							专业
						</div>
					</td>
					<td>
						<input type="text" id="zymc" name="zymc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="zymc" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							班级
						</div>
					</td>
					<td>
						<input type="text" id="bjmc" name="bjmc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="bjmc" />" readonly="true">
					</td>
					<td>
						<div align="center">
							本/专/专接本
						</div>
					</td>
					<td align="center">
						<logic:present name="rs" property="xsxz">
							<logic:match value="本科" name="rs" property="xsxz">
								<input type="radio" name="xsxz" value="本科" checked>&nbsp;&nbsp;本科
							    <input type="radio" name="xsxz" value="专科">&nbsp;&nbsp;专科
							    <input type="radio" name="xsxz" value="专接本">&nbsp;&nbsp;专接本
							</logic:match>
							<logic:match value="专科" name="rs" property="xsxz">
								<input type="radio" name="xsxz" value="本科">&nbsp;&nbsp;本科
							    <input type="radio" name="xsxz" value="专科" checked>&nbsp;&nbsp;专科
							    <input type="radio" name="xsxz" value="专接本">&nbsp;&nbsp;专接本
							</logic:match>
							<logic:match value="专接本" name="rs" property="xsxz">
								<input type="radio" name="xsxz" value="本科">&nbsp;&nbsp;本科
							    <input type="radio" name="xsxz" value="专科">&nbsp;&nbsp;专科
							    <input type="radio" name="xsxz" value="专接本" checked>&nbsp;&nbsp;专接本
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="xsxz">
							<input type="radio" name="xsxz" value="本科" checked>&nbsp;&nbsp;本科
							<input type="radio" name="xsxz" value="专科">&nbsp;&nbsp;专科
							<input type="radio" name="xsxz" value="专接本">&nbsp;&nbsp;专接本
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							学制
						</div>
					</td>
					<td>
						<input type="text" id="xz" name="xz"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xz" />" readonly="true">
					</td>
					<td>
						<div align="center">
							年度
						</div>
					</td>
					<td>
						<input type="text" id="nd" name="nd"
							style="width:100%;heigh:100%" readonly="readonly"
							value="<bean:write name='rs' property="nd" />">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							入学年月
						</div>
					</td>
					<td>
						<input type="text" id="rxny" name="rxny"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="rxny" />" readonly="true">
					</td>
					<td>
						<div align="center">
							出生日期
						</div>
					</td>
					<td>
						<input type="text" id="csrq" name="csrq"
							style="width:100%;heigh:100%" readonly="readonly"
							value="<bean:write name='rs' property="csrq" />">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							有效居住地址
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="yxjzdz" maxlength="200" name="yxjzdz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yxjzdz"/>">
					</td>
				</tr>
				<tr>
					<td scope="row">
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
					<td>
						<div align="center">
							邮政编码
						</div>
					</td>
					<td>
						<input type="text" id="yzbm" maxlength="6" name="yzbm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yzbm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							家庭经济情况
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="jtjjqk" rows='7' style='width:100%' onblur="yzdx(this,'jtjjqk')"/>
						<input type="hidden" id="jtjjqk" name="jtjjqk" value="">
						<div align="left">
							<font color="red">
								&nbsp;注："家庭经济情况"要注明家庭人口情况、收入来源、月人均收入。
							</font>
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							应缴学费
						</div>
					</td>
					<td>
						<input type="text" id="yjxf" maxlength="8" name="yjxf"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yjxf"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							学费贷款
						</div>
					</td>
					<td>
						<input type="text" id="xfdk" maxlength="8" name="xfdk"
							style="width:100%;heigh:100%" onblur="je();"
							value="<bean:write name="rs" property="xfdk"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							生活费贷款
						</div>
					</td>
					<td>
						<input type="text" id="shfdk" maxlength="8" name="shfdk"
							style="width:100%;heigh:100%" onblur="je();"
							value="<bean:write name="rs" property="shfdk"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							合计
						</div>
					</td>
					<td>
						<input type="text" id="hj" maxlength="10" name="hj"
							style="width:100%;heigh:100%" readonly="readonly"
							value="<bean:write name="rs" property="hj"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td scope="row" colspan="4">
						<div align="center">
						上学年成绩
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							课程名称
						</div>
					</td>
					<td>
						<div align="center">
							成绩
						</div>
					</td>
					<td scope="row">
						<div align="center">
							课程名称
						</div>
					</td>
					<td>
						<div align="center">
							成绩
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<input type="text" id="sxncj1_mc" maxlength="100" name="sxncj1_mc"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="sxncj1_mc"/>">
					</td>
					<td>
						<input type="text" id="sxncj1_cj" maxlength="20" name="sxncj1_cj"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="sxncj1_cj"/>">
					</td>
					<td>
						<input type="text" id="sxncj2_mc" maxlength="100" name="sxncj2_mc"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="sxncj2_mc"/>">
					</td>
					<td>
						<input type="text" id="sxncj2_cj" maxlength="20" name="sxncj2_cj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sxncj2_cj"/>">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<input type="text" id="sxncj3_mc" maxlength="100" name="sxncj3_mc"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="sxncj3_mc"/>">
					</td>
					<td>
						<input type="text" id="sxncj3_cj" maxlength="20" name="sxncj3_cj"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="sxncj3_cj"/>">
					</td>
					<td>
						<input type="text" id="sxncj4_mc" maxlength="100" name="sxncj4_mc"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="sxncj4_mc"/>">
					</td>
					<td>
						<input type="text" id="sxncj4_cj" maxlength="20" name="sxncj4_cj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sxncj4_cj"/>">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<input type="text" id="sxncj5_mc" maxlength="100" name="sxncj5_mc"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="sxncj5_mc"/>">
					</td>
					<td>
						<input type="text" id="sxncj5_cj" maxlength="20" name="sxncj5_cj"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="sxncj5_cj"/>">
					</td>
					<td>
						<input type="text" id="sxncj6_mc" maxlength="100" name="sxncj6_mc"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="sxncj6_mc"/>">
					</td>
					<td>
						<input type="text" id="sxncj6_cj" maxlength="20" name="sxncj6_cj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sxncj6_cj"/>">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<input type="text" id="sxncj7_mc" maxlength="100" name="sxncj7_mc"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="sxncj7_mc"/>">
					</td>
					<td>
						<input type="text" id="sxncj7_cj" maxlength="20" name="sxncj7_cj"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="sxncj7_cj"/>">
					</td>
					<td>
						<input type="text" id="sxncj8_mc" maxlength="100" name="sxncj8_mc"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="sxncj8_mc"/>">
					</td>
					<td>
						<input type="text" id="sxncj8_cj" maxlength="20" name="sxncj8_cj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sxncj8_cj"/>">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<input type="text" id="sxncj9_mc" maxlength="100" name="sxncj9_mc"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="sxncj9_mc"/>">
					</td>
					<td>
						<input type="text" id="sxncj9_cj" maxlength="20" name="sxncj9_cj"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="sxncj9_cj"/>">
					</td>
					<td>
						<input type="text" id="sxncj10_mc" maxlength="100" name="sxncj10_mc"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="sxncj10_mc"/>">
					</td>
					<td>
						<input type="text" id="sxncj10_cj" maxlength="20" name="sxncj10_cj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sxncj10_cj"/>">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							入学以来<br />不及格科目
						</div>
					</td>
					<td>
						<input type="text" id="rxlbjgkm" maxlength="8" name="rxlbjgkm"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="rxlbjgkm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							入学以来<br />补考通过科目
						</div>
					</td>
					<td>
						<input type="text" id="rxlbktgkm" maxlength="8" name="rxlbktgkm"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="rxlbktgkm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
			</table>
	<logic:equal name="sfksq" value="1">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button class="button2" onClick="yz();">
					提交申请
				</button>
				<button class="button2" onClick="toPrintOut();">
					打&nbsp;&nbsp;&nbsp;&nbsp;印
				</button>
			</div>
	</logic:equal>

		</html:form>
</body>
</html:html>
