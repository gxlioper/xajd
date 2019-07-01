<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.ArrayList"/>
<jsp:directive.page import="java.util.Iterator"/>
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
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function yz(titName){
			var xh = document.getElementById('xh').value;
			var rxcj = document.getElementById('rxcj').value;
			var sxqpm = document.getElementById('rxcj').value;
			var xsdh = document.getElementById('rxcj').value;
			var jtdh = document.getElementById('jtdh').value;
			var jtdz = document.getElementById('jtdz').value;
			var jtcy1_nl = document.getElementById('jtcy1_nl').value;
			var jtcy1_nsr = document.getElementById('jtcy1_nsr').value;
			var jtcy2_nl = document.getElementById('jtcy2_nl').value;
			var jtcy2_nsr = document.getElementById('jtcy2_nsr').value;
			var jtcy3_nl = document.getElementById('jtcy3_nl').value;
			var jtcy3_nsr = document.getElementById('jtcy3_nsr').value;
			var jtcy4_nl = document.getElementById('jtcy4_nl').value;
			var jtcy4_nsr = document.getElementById('jtcy4_nsr').value;
			var jtcy5_nl = document.getElementById('jtcy5_nl').value;
			var jtcy5_nsr = document.getElementById('jtcy5_nsr').value;
			var jtjjknqk = document.getElementById('jtjjknqk').value;
			var mzbm_yzbm = document.getElementById('mzbm_yzbm').value;
			var mzbm_lxdh = document.getElementById('mzbm_lxdh').value;
			var jfqk_jttg = document.getElementById('jfqk_jttg').value;
			var jfqk_qtqytg = document.getElementById('jfqk_qtqytg').value;
			var jfqk_hjtg = document.getElementById('jfqk_hjtg').value;
			var jfqk_yjfy = document.getElementById('jfqk_yjfy').value;
			var jfqk_mysffy = document.getElementById('jfqk_mysffy').value;
			var jfqk_qnhjfy = document.getElementById('jfqk_qnhjfy').value;
			var jfqk_bxnjttgfybzs = document.getElementById('jfqk_bxnjttgfybzs').value;
			var jfqk_ljqf = document.getElementById('jfqk_ljqf').value;
			var zzqk_lnyhnczxdhje = document.getElementById('zzqk_lnyhnczxdhje').value;
			var zzqk_lnyhgxzxdhje = document.getElementById('zzqk_lnyhgxzxdhje').value;
			var zzqk_lnyhbxzxdhje = document.getElementById('zzqk_lnyhbxzxdhje').value;
			
			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
			if((rxcj != null) && (rxcj != "") && (!isNumber(rxcj))){
				alert("入学成绩必须为整数!");
				return false;
			}
			if((sxqpm != null) && (sxqpm != "") && (!isNumber(sxqpm))){
				alert("上学期班内排名必须为整数!");
				return false;
			}
			if((xsdh != null) && (xsdh != "") && (!isNumber(xsdh))){
				alert("学生电话必须为整数!");
				return false;
			}
			if((jtdh != null) && (jtdh != "") && (!isNumber(jtdh))){
				alert("家庭电话必须为整数!");
				return false;
			}
			if(jtdz != null){
	         	if(jtdz.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("家庭地址不能大于100个字符");
	          		 return false;
	       		 }
			}
			if((jtcy1_nl != null) && (jtcy1_nl != "") && (!isNumber(jtcy1_nl))){
				alert("家庭成员1年龄必须为整数!");
				return false;
			}
			if((jtcy1_nsr != null) && (jtcy1_nsr != "") && (!isNumber(jtcy1_nsr))){
				alert("家庭成员1年收入必须为整数!");
				return false;
			}
			if((jtcy2_nl != null) && (jtcy2_nl != "") && (!isNumber(jtcy2_nl))){
				alert("家庭成员2年龄必须为整数!");
				return false;
			}
			if((jtcy2_nsr != null) && (jtcy2_nsr != "") && (!isNumber(jtcy2_nsr))){
				alert("家庭成员2年收入必须为整数!");
				return false;
			}
			if((jtcy3_nl != null) && (jtcy3_nl != "") && (!isNumber(jtcy3_nl))){
				alert("家庭成员3年龄必须为整数!");
				return false;
			}
			if((jtcy3_nsr != null) && (jtcy3_nsr != "") && (!isNumber(jtcy3_nsr))){
				alert("家庭成员3年收入必须为整数!");
				return false;
			}
			if((jtcy4_nl != null) && (jtcy4_nl != "") && (!isNumber(jtcy4_nl))){
				alert("家庭成员4年龄必须为整数!");
				return false;
			}
			if((jtcy4_nsr != null) && (jtcy4_nsr != "") && (!isNumber(jtcy4_nsr))){
				alert("家庭成员4年收入必须为整数!");
				return false;
			}
			if((jtcy5_nl != null) && (jtcy5_nl != "") && (!isNumber(jtcy5_nl))){
				alert("家庭成员5年龄必须为整数!");
				return false;
			}
			if((jtcy5_nsr != null) && (jtcy5_nsr != "") && (!isNumber(jtcy5_nsr))){
				alert("家庭成员5年收入必须为整数!");
				return false;
	       	}
	       	if((mzbm_yzbm != null) && (mzbm_yzbm != "") && (!isNumber(mzbm_yzbm))){
				alert("民政部门邮政编码必须为整数!");
				return false;
			}
			if((mzbm_lxdh != null) && (mzbm_lxdh != "") && (!isNumber(mzbm_lxdh))){
				alert("民政部门联系电话必须为整数!");
				return false;
			}
			if((jfqk_jttg != null) && (jfqk_jttg != "") && (!isNumber(jfqk_jttg))){
				alert("每年本人和家庭提供费用必须为整数!");
				return false;
			}
			if((jfqk_qtqytg != null) && (jfqk_qtqytg != "") && (!isNumber(jfqk_qtqytg))){
				alert("每年其他亲友提供费用必须为整数!");
				return false;
			}
			if((jfqk_hjtg != null) && (jfqk_hjtg != "") && (!isNumber(jfqk_hjtg))){
				alert("每年提供费用合计必须为整数!");
				return false;
			}
			if((jfqk_yjfy != null) && (jfqk_yjfy != "") && (!isNumber(jfqk_yjfy))){
				alert("每年应缴各种费用必须为整数!");
				return false;
			}
			if((jfqk_mysffy != null) && (jfqk_mysffy != "") && (!isNumber(jfqk_mysffy))){
				alert("每月生活费用必须为整数!");
				return false;
			}
			if((jfqk_qnhjfy != null) && (jfqk_qnhjfy != "") && (!isNumber(jfqk_qnhjfy))){
				alert("全年合计费用必须为整数!");
				return false;
			}
			if((jfqk_bxnjttgfybzs != null) && (jfqk_bxnjttgfybzs != "") && (!isNumber(jfqk_bxnjttgfybzs))){
				alert("本学年家庭提供费用不足数必须为整数!");
				return false;
			}
			if((jfqk_ljqf != null) && (jfqk_ljqf != "") && (!isNumber(jfqk_ljqf))){
				alert("累计欠费必须为整数!");
				return false;
			}
			if((zzqk_lnyhnczxdhje != null) && (zzqk_lnyhnczxdhje != "") && (!isNumber(zzqk_lnyhnczxdhje))){
				alert("历年已获农村助学贷款累计金额必须为整数!");
				return false;
			}
			if((zzqk_lnyhgxzxdhje != null) && (zzqk_lnyhgxzxdhje != "") && (!isNumber(zzqk_lnyhgxzxdhje))){
				alert("历年已获高校助学贷款累计金额必须为整数!");
				return false;
			}
			if((zzqk_lnyhbxzxdhje != null) && (zzqk_lnyhbxzxdhje != "") && (!isNumber(zzqk_lnyhbxzxdhje))){
				alert("历年已获保险助学贷款累计金额必须为整数!");
				return false;
	       	}
			
			document.forms[0].action = "/xgxt/jsxx_knsxx.do?doType=add&titName=" + titName;
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(titName){//输出相应的打印页面
			document.forms[0].action = "/xgxt/jsxx_knssqb.do";
			document.forms[0].submit();
		}
		
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
		
		function checkSfzh(sfzh) {
   			sfzh=sfzh.toLowerCase()
			var OldID;
			if(sfzh.length == 15){
				OldID = sfzh;
				return true;
			}else if(sfzh.length == 18){
				OldID = sfzh.substring(0, 6) + sfzh.substring(8,sfzh.length-1);
			}else{
				return false;
			}
			var W = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
			var A = new Array("1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2");
			var i, j, S;
			var NewID, ID, strNF;
			NewID = OldID.substring(0, 6) + "19" + OldID.substring(6,OldID.length);
			S = 0;
			for( i = 0; i <= 16; i++){
				j = NewID.substring(i, i+1) * W[i];
				S = S + j;
			}
			S = S % 11;
			if(sfzh != NewID + A[S]){
				return false;
			}
			return true;
		}
		
		function xfhj1(){
			var jfqk_jttg = document.getElementById('jfqk_jttg').value;
			var jfqk_qtqytg = document.getElementById('jfqk_qtqytg').value;
			if((jfqk_jttg == null) || (jfqk_jttg == "")){
				jfqk_jttg = "0";
			}
			if((jfqk_qtqytg == null) || (jfqk_qtqytg == "")){
				jfqk_qtqytg = "0";
			}
			var jfqk_hjtg = Math.round(jfqk_jttg) + Math.round(jfqk_qtqytg);
			document.getElementById('jfqk_hjtg').value=jfqk_hjtg;
		}
		
		function xfhj2(){
			var jfqk_yjfy = document.getElementById('jfqk_yjfy').value;
			var jfqk_mysffy = document.getElementById('jfqk_mysffy').value;
			if((jfqk_yjfy == null) || (jfqk_yjfy == "")){
				jfqk_yjfy = "0";
			}
			if((jfqk_mysffy == null) || (jfqk_mysffy == "")){
				jfqk_mysffy = "0";
			}
			var jfqk_qnhjfy = Math.round(jfqk_yjfy) + Math.round(jfqk_mysffy) * 12;
			document.getElementById('jfqk_qnhjfy').value=jfqk_qnhjfy;
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 经济困难学生及家庭情况调查审定表
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				现在不在申请时间内或困难生人数已满！！！
			</p>
		</center>
	</logic:equal>
	<logic:equal name="sfksq" value="-2">
		<center>
			<p>
				困难生人数已满！！！
			</p>
		</center>
	</logic:equal>
		<html:form action="jsxx_knsxx.do" method="post">
			<input type="hidden" id="titName" name="titName"
				value="<bean:write name="titName" scope="request" />">
			<input type="hidden" id="url" name="url"
				value="/jsxx_knsxx.do?jxjlbType=jsxx_knsxx" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj"/>">
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj"/>">
			<input type="hidden" id="nd" name="nd"
				value="<bean:write name="rs" property="nd"/>">
			<input type="hidden" id="sqsj" name="sqsj"
				value="<bean:write name="rs" property="sqsj"/>">
			<input type="hidden" id="xysh" name="xysh"
				value="<bean:write name="rs" property="xysh"/>">
			<input type="hidden" id="xxsh" name="xxsh"
				value="<bean:write name="rs" property="xxsh"/>">
			<input type="hidden" id="zzqk_lnjzxjqk" name="zzqk_lnjzxjqk"
				value="<bean:write name="rs" property="zzqk_lnjzxjqk"/>">

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
			<table class="tbstyle" width="100%">
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" colspan="2">
							<font color="red">*</font>学号：
						</td>
						<td align="left" colspan="2">
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
						<td align="center" colspan="2">
							<font color="red">*</font>学号：
						</td>
						<td align="left" colspan="2">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xh" />" readonly="true">
						</td>
					</logic:equal>
					<td>
						<div align="center">
							姓名
						</div>
					</td>
					<td colspan="2">
						<input type="text" readonly="readonly" id="xm" name="xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xm"/>" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							出生年月
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="csrq" readonly="readonly" name="csrq"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="csrq"/>">
					</td>
					<td>
						<div align="center">
							性别
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="xb" name="xb" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xb"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
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
							政治面貌
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="zzmm" name="zzmm" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zzmm"/>">
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
							班级
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="bjmc" readonly="readonly" name="bjmc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bjmc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							入学成绩
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="rxcj" name="rxcj" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="rxcj"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							上学期班内排名
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="sxqpm" maxlength="5" name="sxqpm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sxqpm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							课程成绩平均分
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="pjcj" name="pjcj" maxlength="5"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="pjcj"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							操行等第
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="cxdd" name="cxdd" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="cxdd"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="col">
						<div align="center">
							学生电话
						</div>
					</td>
					<td colspan="2" scope="col">
						<input type="text" id="xsdh" name="xsdh" maxlength="15"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xsdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td scope="col">
						<div align="center">
							家庭电话
						</div>
					</td>
					<td colspan="2" scope="col">
						<input type="text" id="jtdh" name="jtdh" maxlength="15"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtdh"/>"
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
					<td colspan="5">
						<html:textarea name="rs" property="jtdz" rows='2' style="width:100%" onblur="yzdx(this,'jtdz')"/>
						<input type="hidden" id="jtdz" name="jtdz" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="col">
						<div align="center">
							入学前户口
						</div>
					</td>
					<td colspan="2" scope="col" align="center">
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
					<td scope="col">
						<div align="center">
							是否孤儿
						</div>
					</td>
					<td colspan="2" scope="col" align="center">
						<logic:present name="rs" property="sfgr">
							<logic:match value="是" name="rs" property="sfgr">
								<input type="radio" name="sfgr" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="sfgr" value="否">&nbsp;&nbsp;否
							</logic:match>
							<logic:match value="否" name="rs" property="sfgr">
								<input type="radio" name="sfgr" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="sfgr" value="否" checked>&nbsp;&nbsp;否
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfgr">
							<input type="radio" name="sfgr" value="是">&nbsp;&nbsp;是 
							<input type="radio" name="sfgr" value="否" checked>&nbsp;&nbsp;否
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="col">
						<div align="center">
							是否残疾
						</div>
					</td>
					<td colspan="2" scope="col" align="center">
						<logic:present name="rs" property="sfcj">
							<logic:match value="是" name="rs" property="sfcj">
								<input type="radio" name="sfcj" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="sfcj" value="否">&nbsp;&nbsp;否
							</logic:match>
							<logic:match value="否" name="rs" property="sfcj">
								<input type="radio" name="sfcj" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="sfcj" value="否" checked>&nbsp;&nbsp;否
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfcj">
							<input type="radio" name="sfcj" value="是">&nbsp;&nbsp;是 
							<input type="radio" name="sfcj" value="否" checked>&nbsp;&nbsp;否
						</logic:notPresent>
					</td>
					<td scope="col">
						<div align="center">
							是否单亲
						</div>
					</td>
					<td colspan="2" scope="col" align="center">
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
				</tr>
				<tr>
					<td colspan="2" scope="col">
						<div align="center">
							是否烈士子女
						</div>
					</td>
					<td colspan="2" scope="col" align="center">
						<logic:present name="rs" property="sfjszn">
							<logic:match value="是" name="rs" property="sfjszn">
								<input type="radio" name="sfjszn" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="sfjszn" value="否">&nbsp;&nbsp;否
							</logic:match>
							<logic:match value="否" name="rs" property="sfjszn">
								<input type="radio" name="sfjszn" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="sfjszn" value="否" checked>&nbsp;&nbsp;否
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfjszn">
							<input type="radio" name="sfjszn" value="是">&nbsp;&nbsp;是 
							<input type="radio" name="sfjszn" value="否" checked>&nbsp;&nbsp;否
						</logic:notPresent>
					</td>
					<td scope="col">
						<div align="center">
							是否少数民族
						</div>
					</td>
					<td colspan="2" scope="col" align="center">
						<logic:present name="rs" property="sfssmz">
							<logic:match value="是" name="rs" property="sfssmz">
								<input type="radio" name="sfssmz" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="sfssmz" value="否">&nbsp;&nbsp;否
							</logic:match>
							<logic:match value="否" name="rs" property="sfssmz">
								<input type="radio" name="sfssmz" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="sfssmz" value="否" checked>&nbsp;&nbsp;否
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfssmz">
							<input type="radio" name="sfssmz" value="是">&nbsp;&nbsp;是 
							<input type="radio" name="sfssmz" value="否" checked>&nbsp;&nbsp;否
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="col">
						<div align="center">
							是否无收入户
						</div>
					</td>
					<td colspan="2" scope="col" align="center">
						<logic:present name="rs" property="sfwsrh">
							<logic:match value="是" name="rs" property="sfwsrh">
								<input type="radio" name="sfwsrh" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="sfwsrh" value="否">&nbsp;&nbsp;否
							</logic:match>
							<logic:match value="否" name="rs" property="sfwsrh">
								<input type="radio" name="sfwsrh" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="sfwsrh" value="否" checked>&nbsp;&nbsp;否
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfwsrh">
							<input type="radio" name="sfwsrh" value="是">&nbsp;&nbsp;是 
							<input type="radio" name="sfwsrh" value="否" checked>&nbsp;&nbsp;否
						</logic:notPresent>
					</td>
					<td scope="col">
						<div align="center">
							是否重病户
						</div>
					</td>
					<td colspan="2" scope="col" align="center">
						<logic:present name="rs" property="sfzbh">
							<logic:match value="是" name="rs" property="sfzbh">
								<input type="radio" name="sfzbh" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="sfzbh" value="否">&nbsp;&nbsp;否
							</logic:match>
							<logic:match value="否" name="rs" property="sfzbh">
								<input type="radio" name="sfzbh" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="sfzbh" value="否" checked>&nbsp;&nbsp;否
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfzbh">
							<input type="radio" name="sfzbh" value="是">&nbsp;&nbsp;是 
							<input type="radio" name="sfzbh" value="否" checked>&nbsp;&nbsp;否
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="col">
						<div align="center">
							是否低保户
						</div>
					</td>
					<td colspan="2" scope="col" align="center">
						<logic:present name="rs" property="sfdbh">
							<logic:match value="是" name="rs" property="sfdbh">
								<input type="radio" name="sfdbh" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="sfdbh" value="否">&nbsp;&nbsp;否
							</logic:match>
							<logic:match value="否" name="rs" property="sfdbh">
								<input type="radio" name="sfdbh" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="sfdbh" value="否" checked>&nbsp;&nbsp;否
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfdbh">
							<input type="radio" name="sfdbh" value="是">&nbsp;&nbsp;是 
							<input type="radio" name="sfdbh" value="否" checked>&nbsp;&nbsp;否
						</logic:notPresent>
					</td>
					<td scope="col">
						<div align="center">
							是否父母双下岗
						</div>
					</td>
					<td colspan="2" scope="col" align="center">
						<logic:present name="rs" property="sffmsxg">
							<logic:match value="是" name="rs" property="sffmsxg">
								<input type="radio" name="sffmsxg" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="sffmsxg" value="否">&nbsp;&nbsp;否
							</logic:match>
							<logic:match value="否" name="rs" property="sffmsxg">
								<input type="radio" name="sffmsxg" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="sffmsxg" value="否" checked>&nbsp;&nbsp;否
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sffmsxg">
							<input type="radio" name="sffmsxg" value="是">&nbsp;&nbsp;是 
							<input type="radio" name="sffmsxg" value="否" checked>&nbsp;&nbsp;否
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="col">
						<div align="center">
							是否纯农户
						</div>
					</td>
					<td colspan="2" scope="col" align="center">
						<logic:present name="rs" property="sfcnh">
							<logic:match value="是" name="rs" property="sfcnh">
								<input type="radio" name="sfcnh" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="sfcnh" value="否">&nbsp;&nbsp;否
							</logic:match>
							<logic:match value="否" name="rs" property="sfcnh">
								<input type="radio" name="sfcnh" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="sfcnh" value="否" checked>&nbsp;&nbsp;否
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfcnh">
							<input type="radio" name="sfcnh" value="是">&nbsp;&nbsp;是 
							<input type="radio" name="sfcnh" value="否" checked>&nbsp;&nbsp;否
						</logic:notPresent>
					</td>
					<td scope="col">
						<div align="center">
							是否低收入(家庭收入不足以支付就学基本费用)
						</div>
					</td>
					<td colspan="2" scope="col" align="center">
						<logic:present name="rs" property="sfdsr">
							<logic:match value="是" name="rs" property="sfdsr">
								<input type="radio" name="sfdsr" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="sfdsr" value="否">&nbsp;&nbsp;否
							</logic:match>
							<logic:match value="否" name="rs" property="sfdsr">
								<input type="radio" name="sfdsr" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="sfdsr" value="否" checked>&nbsp;&nbsp;否
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfdsr">
							<input type="radio" name="sfdsr" value="是">&nbsp;&nbsp;是 
							<input type="radio" name="sfdsr" value="否" checked>&nbsp;&nbsp;否
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<td rowspan="6" scope="row" width="4%">
						<div align="center">
							家
							<br>
							庭
							<br>
							人
							<br>
							员
						</div>
					</td>
					<td width="20%">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="12%">
						<div align="center">
							称谓
						</div>
					</td>
					<td width="12%">
						<div align="center">
							年龄
						</div>
					</td>
					<td width="26%">
						<div align="center">
							工作单位及职务
						</div>
					</td>
					<td width="12%">
						<div align="center">
							年收入
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
						<input type="text" id="jtcy1_xm" maxlength="40" name="jtcy1_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_xm"/>">
					</td>
					<td>
						<input type="text" id="jtcy1_cw" name="jtcy1_cw" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_cw" />">
					</td>
					<td>
						<input type="text" id="jtcy1_nl" name="jtcy1_nl" maxlength="3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_nl" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy1_gzdwjzw" maxlength="100"
							name="jtcy1_gzdwjzw" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_gzdwjzw" />">
					</td>
					<td>
						<input type="text" id="jtcy1_nsr" maxlength="10" name="jtcy1_nsr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_nsr" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy1_jkzk" maxlength="40"
							name="jtcy1_jkzk" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_jkzk" />">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy2_xm" maxlength="40" name="jtcy2_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_xm"/>">
					</td>
					<td>
						<input type="text" id="jtcy2_cw" name="jtcy2_cw" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_cw" />">
					</td>
					<td>
						<input type="text" id="jtcy2_nl" name="jtcy2_nl" maxlength="3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_nl" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy2_gzdwjzw" maxlength="100"
							name="jtcy2_gzdwjzw" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_gzdwjzw" />">
					</td>
					<td>
						<input type="text" id="jtcy2_nsr" maxlength="10" name="jtcy2_nsr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_nsr" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy2_jkzk" maxlength="40"
							name="jtcy2_jkzk" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_jkzk" />">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy3_xm" maxlength="40" name="jtcy3_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_xm"/>">
					</td>
					<td>
						<input type="text" id="jtcy3_cw" name="jtcy3_cw" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_cw" />">
					</td>
					<td>
						<input type="text" id="jtcy3_nl" name="jtcy3_nl" maxlength="3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_nl" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy3_gzdwjzw" maxlength="100"
							name="jtcy3_gzdwjzw" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_gzdwjzw" />">
					</td>
					<td>
						<input type="text" id="jtcy3_nsr" maxlength="10" name="jtcy3_nsr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_nsr" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy3_jkzk" maxlength="40"
							name="jtcy3_jkzk" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_jkzk" />">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy4_xm" maxlength="40" name="jtcy4_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_xm"/>">
					</td>
					<td>
						<input type="text" id="jtcy4_cw" name="jtcy4_cw" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_cw" />">
					</td>
					<td>
						<input type="text" id="jtcy4_nl" name="jtcy4_nl" maxlength="3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_nl" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy4_gzdwjzw" maxlength="100"
							name="jtcy4_gzdwjzw" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_gzdwjzw" />">
					</td>
					<td>
						<input type="text" id="jtcy4_nsr" maxlength="10" name="jtcy4_nsr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_nsr" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy4_jkzk" maxlength="40"
							name="jtcy4_jkzk" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_jkzk" />">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy5_xm" maxlength="40" name="jtcy5_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_xm"/>">
					</td>
					<td>
						<input type="text" id="jtcy5_cw" name="jtcy5_cw" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_cw" />">
					</td>
					<td>
						<input type="text" id="jtcy5_nl" name="jtcy5_nl" maxlength="3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_nl" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy5_gzdwjzw" maxlength="100"
							name="jtcy5_gzdwjzw" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_gzdwjzw" />">
					</td>
					<td>
						<input type="text" id="jtcy5_nsr" maxlength="10" name="jtcy5_nsr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_nsr" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy5_jkzk" maxlength="40"
							name="jtcy5_jkzk" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_jkzk" />">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭经济困难状况(遭灾、意外事故，父母病残、欠债、失业下岗，低保)
						</div>
					</td>
					<td colspan="6">
						<html:textarea name="rs" property="jtjjknqk" rows='5' style="width:100%" onblur="yzdx(this,'jtjjknqk')"/>
						<input type="hidden" id="jtjjknqk" name="jtjjknqk" value="">
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="col">
						<div align="center">
							民政部门信息
						</div>
					</td>
					<td colspan="6" scope="col">
						<div align="left">
							邮政编码：
							<input type="text" id="mzbm_yzbm" name="mzbm_yzbm" maxlength="6"
								style="width:10%;heigh:100%"
								value="<bean:write name="rs" property="mzbm_yzbm"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							&nbsp;&nbsp;&nbsp;&nbsp; 联系电话：
							<input type="text" id="mzbm_lxdh" name="mzbm_lxdh" maxlength="15"
								style="width:20%;heigh:100%"
								value="<bean:write name="rs" property="mzbm_lxdh"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							&nbsp;&nbsp;&nbsp;&nbsp; 联系人：
							<input type="text" id="mzbm_lxr" name="mzbm_lxr" maxlength="40"
								style="width:20%;heigh:100%"
								value="<bean:write name="rs" property="mzbm_lxr"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="col" rowspan="3">
						<div align="center">
							缴费情况
						</div>
					</td>
					<td colspan="6" scope="col">
						<div align="left">
							每年本人和家庭提供费用
							<input type="text" id="jfqk_jttg" name="jfqk_jttg" maxlength="10"
								style="width:10%;heigh:100%" onblur="xfhj1();"
								value="<bean:write name="rs" property="jfqk_jttg"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							元， 其他亲友提供
							<input type="text" id="jfqk_qtqytg" name="jfqk_qtqytg"
								maxlength="10" style="width:10%;heigh:100%" onblur="xfhj1();"
								value="<bean:write name="rs" property="jfqk_qtqytg"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							元， 合计
							<input type="text" id="jfqk_hjtg" name="jfqk_hjtg"
								readonly="readonly" style="width:10%;heigh:100%"
								value="<bean:write name="rs" property="jfqk_hjtg"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							元
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<div align="left">
							每年应缴各种费用
							<input type="text" id="jfqk_yjfy" name="jfqk_yjfy" maxlength="10"
								style="width:10%;heigh:100%" onblur="xfhj2();"
								value="<bean:write name="rs" property="jfqk_yjfy"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							元， 每月生活费
							<input type="text" id="jfqk_mysffy" name="jfqk_mysffy"
								maxlength="10" style="width:10%;heigh:100%" onblur="xfhj2();"
								value="<bean:write name="rs" property="jfqk_mysffy"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							元， 全年合计费用
							<input type="text" id="jfqk_qnhjfy" name="jfqk_qnhjfy"
								readonly="readonly" style="width:10%;heigh:100%"
								value="<bean:write name="rs" property="jfqk_qnhjfy"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							元
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<div align="left">
							本学年家庭提供费用不足数：
							<input type="text" id="jfqk_bxnjttgfybzs"
								name="jfqk_bxnjttgfybzs" maxlength="10"
								style="width:10%;heigh:100%"
								value="<bean:write name="rs" property="jfqk_bxnjttgfybzs"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							元， 累计欠费：
							<input type="text" id="jfqk_ljqf" name="jfqk_ljqf" maxlength="10"
								style="width:10%;heigh:100%"
								value="<bean:write name="rs" property="jfqk_ljqf"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							元， 有无缓缴计划：&nbsp;&nbsp;
							<logic:present name="rs" property="jfqk_ywhjjh">
								<logic:match value="有" name="rs" property="jfqk_ywhjjh">
									<input type="radio" name="jfqk_ywhjjh" value="有" checked>&nbsp;&nbsp;有
							         	<input type="radio" name="jfqk_ywhjjh" value="无">&nbsp;&nbsp;无
							         </logic:match>
								<logic:match value="无" name="rs" property="jfqk_ywhjjh">
									<input type="radio" name="jfqk_ywhjjh" value="有">&nbsp;&nbsp;有
							         	<input type="radio" name="jfqk_ywhjjh" value="无"
										checked>&nbsp;&nbsp;无
							         </logic:match>
							</logic:present>
							<logic:notPresent name="rs" property="jfqk_ywhjjh">
								<input type="radio" name="jfqk_ywhjjh" value="有">&nbsp;&nbsp;有 
							         <input type="radio" name="jfqk_ywhjjh" value="无" checked>&nbsp;&nbsp;无
						         </logic:notPresent>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="col" rowspan="5">
						<div align="center">
							资助情况
						</div>
					</td>
					<td colspan="6" scope="col">
						<div align="left">
							校内勤工助学：&nbsp;&nbsp;
							<logic:present name="rs" property="zzqk_sfsqxnqgzx">
								<logic:match value="已申请" name="rs" property="zzqk_sfsqxnqgzx">
									<input type="radio" name="zzqk_sfsqxnqgzx" value="已申请" checked>&nbsp;&nbsp;已申请
							         	<input type="radio" name="zzqk_sfsqxnqgzx" value="未申请">&nbsp;&nbsp;未申请
							         </logic:match>
								<logic:match value="未申请" name="rs" property="zzqk_sfsqxnqgzx">
									<input type="radio" name="zzqk_sfsqxnqgzx" value="已申请">&nbsp;&nbsp;已申请
							         	<input type="radio" name="zzqk_sfsqxnqgzx" value="未申请"
										checked>&nbsp;&nbsp;未申请
							         </logic:match>
							</logic:present>
							<logic:notPresent name="rs" property="zzqk_sfsqxnqgzx">
								<input type="radio" name="zzqk_sfsqxnqgzx" value="已申请">&nbsp;&nbsp;已申请 
							         <input type="radio" name="zzqk_sfsqxnqgzx" value="未申请"
									checked>&nbsp;&nbsp;未申请
						         </logic:notPresent>
							&nbsp;&nbsp;，岗位&nbsp;&nbsp;
							<logic:present name="rs" property="zzqk_xnqgzxyapgw">
								<logic:match value="已安排" name="rs" property="zzqk_xnqgzxyapgw">
									<input type="radio" name="zzqk_xnqgzxyapgw" value="已安排" checked>&nbsp;&nbsp;已安排
							         	<input type="radio" name="zzqk_xnqgzxyapgw" value="未安排">&nbsp;&nbsp;未安排
							         </logic:match>
								<logic:match value="未安排" name="rs" property="zzqk_xnqgzxyapgw">
									<input type="radio" name="zzqk_xnqgzxyapgw" value="已安排">&nbsp;&nbsp;已安排
							         	<input type="radio" name="zzqk_xnqgzxyapgw" value="未安排"
										checked>&nbsp;&nbsp;未安排
							         </logic:match>
							</logic:present>
							<logic:notPresent name="rs" property="zzqk_xnqgzxyapgw">
								<input type="radio" name="zzqk_xnqgzxyapgw" value="已安排">&nbsp;&nbsp;已安排 
							         <input type="radio" name="zzqk_xnqgzxyapgw" value="未安排"
									checked>&nbsp;&nbsp;未安排
						         </logic:notPresent>
							&nbsp;&nbsp;。校外勤工助学：&nbsp;&nbsp;
							<logic:present name="rs" property="zzqk_sfsqxwqgzx">
								<logic:match value="有" name="rs" property="zzqk_sfsqxwqgzx">
									<input type="radio" name="zzqk_sfsqxwqgzx" value="有" checked>&nbsp;&nbsp;有
							         	<input type="radio" name="zzqk_sfsqxwqgzx" value="无">&nbsp;&nbsp;无
							         </logic:match>
								<logic:match value="无" name="rs" property="zzqk_sfsqxwqgzx">
									<input type="radio" name="zzqk_sfsqxwqgzx" value="有">&nbsp;&nbsp;有
							         	<input type="radio" name="zzqk_sfsqxwqgzx" value="无"
										checked>&nbsp;&nbsp;无
							         </logic:match>
							</logic:present>
							<logic:notPresent name="rs" property="zzqk_sfsqxwqgzx">
								<input type="radio" name="zzqk_sfsqxwqgzx" value="有">&nbsp;&nbsp;有 
							         <input type="radio" name="zzqk_sfsqxwqgzx" value="无"
									checked>&nbsp;&nbsp;无
						         </logic:notPresent>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<div align="left">
							本学年农村助学贷款：&nbsp;&nbsp;
							<logic:present name="rs" property="zzqk_sfsbnczxdk">
								<logic:match value="已申办" name="rs" property="zzqk_sfsbnczxdk">
									<input type="radio" name="zzqk_sfsbnczxdk" value="已申办" checked>&nbsp;&nbsp;已申办
							         	<input type="radio" name="zzqk_sfsbnczxdk" value="未申办">&nbsp;&nbsp;未申办
							         </logic:match>
								<logic:match value="未申办" name="rs" property="zzqk_sfsbnczxdk">
									<input type="radio" name="zzqk_sfsbnczxdk" value="已申办">&nbsp;&nbsp;已申办
							         	<input type="radio" name="zzqk_sfsbnczxdk" value="未申办"
										checked>&nbsp;&nbsp;未申办
							         </logic:match>
							</logic:present>
							<logic:notPresent name="rs" property="zzqk_sfsbnczxdk">
								<input type="radio" name="zzqk_sfsbnczxdk" value="已申办">&nbsp;&nbsp;已申办 
							         <input type="radio" name="zzqk_sfsbnczxdk" value="未申办"
									checked>&nbsp;&nbsp;未申办
						         </logic:notPresent>
							&nbsp;&nbsp;，历年已获农村助学贷款累计
							<input type="text" id="zzqk_lnyhnczxdhje"
								name="zzqk_lnyhnczxdhje" maxlength="10"
								style="width:10%;heigh:100%"
								value="<bean:write name="rs" property="zzqk_lnyhnczxdhje"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							元
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<div align="left">
							本学年高校助学贷款：&nbsp;&nbsp;
							<logic:present name="rs" property="zzqk_sfsbgxzxdk">
								<logic:match value="拟申办" name="rs" property="zzqk_sfsbgxzxdk">
									<input type="radio" name="zzqk_sfsbgxzxdk" value="拟申办" checked>&nbsp;&nbsp;拟申办
							         	<input type="radio" name="zzqk_sfsbgxzxdk" value="不申办">&nbsp;&nbsp;不申办
							         </logic:match>
								<logic:match value="不申办" name="rs" property="zzqk_sfsbgxzxdk">
									<input type="radio" name="zzqk_sfsbgxzxdk" value="拟申办">&nbsp;&nbsp;拟申办
							         	<input type="radio" name="zzqk_sfsbgxzxdk" value="不申办"
										checked>&nbsp;&nbsp;不申办
							         </logic:match>
							</logic:present>
							<logic:notPresent name="rs" property="zzqk_sfsbgxzxdk">
								<input type="radio" name="zzqk_sfsbgxzxdk" value="拟申办">&nbsp;&nbsp;拟申办 
							         <input type="radio" name="zzqk_sfsbgxzxdk" value="不申办"
									checked>&nbsp;&nbsp;不申办
						         </logic:notPresent>
							&nbsp;&nbsp;，历年已获高校助学贷款累计
							<input type="text" id="zzqk_lnyhgxzxdhje"
								name="zzqk_lnyhgxzxdhje" maxlength="10"
								style="width:10%;heigh:100%"
								value="<bean:write name="rs" property="zzqk_lnyhgxzxdhje"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							元
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<div align="left">
							本学年保险助学贷款：&nbsp;&nbsp;
							<logic:present name="rs" property="zzqk_sfsbbxzxdk">
								<logic:match value="拟申办" name="rs" property="zzqk_sfsbbxzxdk">
									<input type="radio" name="zzqk_sfsbbxzxdk" value="拟申办" checked>&nbsp;&nbsp;拟申办
							         	<input type="radio" name="zzqk_sfsbbxzxdk" value="不申办">&nbsp;&nbsp;不申办
							         </logic:match>
								<logic:match value="不申办" name="rs" property="zzqk_sfsbbxzxdk">
									<input type="radio" name="zzqk_sfsbbxzxdk" value="拟申办">&nbsp;&nbsp;拟申办
							         	<input type="radio" name="zzqk_sfsbbxzxdk" value="不申办"
										checked>&nbsp;&nbsp;不申办
							         </logic:match>
							</logic:present>
							<logic:notPresent name="rs" property="zzqk_sfsbbxzxdk">
								<input type="radio" name="zzqk_sfsbbxzxdk" value="拟申办">&nbsp;&nbsp;拟申办 
							         <input type="radio" name="zzqk_sfsbbxzxdk" value="不申办"
									checked>&nbsp;&nbsp;不申办
						         </logic:notPresent>
							&nbsp;&nbsp;，历年已获保险助学贷款累计
							<input type="text" id="zzqk_lnyhbxzxdhje"
								name="zzqk_lnyhbxzxdhje" maxlength="10"
								style="width:10%;heigh:100%"
								value="<bean:write name="rs" property="zzqk_lnyhbxzxdhje"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							元
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<div align="left">
							历年已获各种奖、助学金等资助情况：
							<logic:equal name="notJzxj" value="is">
							<div align="center">
							无
							</div>
							</logic:equal>
							<logic:equal name="notJzxj" value="no">
								<div align="left">
								<%ArrayList list = (ArrayList) request
									.getAttribute("xszzList"); 
								  for(Iterator it = list.iterator();it.hasNext();){
								  	String temp = (String)it.next();
								%>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=temp %><br />
								<%} %>
								</div>
							</logic:equal>
						</div>
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
