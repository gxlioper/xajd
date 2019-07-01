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
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript">
		function yz(){
			var xyshyj = document.getElementById('xyshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			var isXX = document.getElementById('isXX').value;
			var xxsh = document.getElementById('xxsh').value;
			if((("一般困难" == xxsh) || ("比较困难" == xxsh) || ("特别困难" == xxsh)) && (isXX != "is")){
				alert("学校审核已通过，不能再修改审核结果!");
	          	return false;
			}
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />审核意见不能大于100个字符");
	          		 return false;
	       		 }
	       	}else if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("学校审核意见不能大于100个字符");
	          		 return false;
	       		 }
			 }
			 refreshForm('/xgxt/auditing_jsxx_knsxx_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 审核 - 经济困难学生及家庭情况调查 - 单个审核
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
					<td colspan="2" scope="col">
						<div align="center">
							学号
						</div>
					</td>
					<td colspan="3" scope="col">
						<bean:write name="rs" property="xh" />
					</td>
					<td scope="col" width="16%">
						<div align="center">
							姓名
						</div>
					</td>
					<td colspan="3" scope="col">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							身份证号
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sfzh" />
					</td>
					<td>
						<div align="center">
							性别
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xb" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							出生日期
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="csrq" />
					</td>
					<td>
						<div align="center">
							政治面貌
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="zzmm" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xymc" />
					</td>
					<td>
						<div align="center">
							系
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="zymc" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							班级
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="bjmc" />
					</td>
					<td>
						<div align="center">
							年度
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="nd" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							学生电话
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xsdh" />
					</td>
					<td>
						<div align="center">
							家庭电话
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtdh" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							家庭地址
						</div>
					</td>
					<td colspan="7">
						<bean:write name="rs" property="jtdz" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							入学前户口
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="rxqhk" />
					</td>
					<td>
						<div align="center">
							孤儿
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sfgr" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							残疾
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sfcj" />
					</td>
					<td>
						<div align="center">
							单亲
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sfdq" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							烈士子女
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sfjszn" />
					</td>
					<td>
						<div align="center">
							少数民族
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sfssmz" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							无收入户
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sfwsrh" />
					</td>
					<td>
						<div align="center">
							重病户
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sfzbh" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							低保户
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sfdbh" />
					</td>
					<td>
						<div align="center">
							父母双下岗
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sffmsxg" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							纯农户
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sfcnh" />
					</td>
					<td>
						<div align="center">
							低收入
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sfdsr" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							入学成绩
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="rxcj" />
					</td>
					<td>
						<div align="center">
							上学期班内排名
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sxqpm" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							各课程成绩
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="pjcj" />
					</td>
					<td>
						<div align="center">
							操行等第
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="cxdd" />
					</td>
				</tr>
				<tr>
					<td rowspan="6" scope="row" width="8%">
						<div align="center">
							家庭人员
						</div>
					</td>
					<td width="8%">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="8%">
						<div align="center">
							称谓
						</div>
					</td>
					<td width="8%">
						<div align="center">
							年龄
						</div>
					</td>
					<td colspan="3" width="52%">
						<div align="center">
							工作单位及职务
						</div>
					</td>
					<td width="8%">
						<div align="center">
							年收入
						</div>
					</td>
					<td width="8%">
						<div align="center">
							健康状况
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_cw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_nl" />
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							<bean:write name="rs" property="jtcy1_gzdwjzw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_nsr" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_jkzk" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_cw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_nl" />
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							<bean:write name="rs" property="jtcy2_gzdwjzw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_nsr" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_jkzk" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_cw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_nl" />
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							<bean:write name="rs" property="jtcy3_gzdwjzw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_nsr" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_jkzk" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_cw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_nl" />
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							<bean:write name="rs" property="jtcy4_gzdwjzw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_nsr" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_jkzk" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_cw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_nl" />
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							<bean:write name="rs" property="jtcy5_gzdwjzw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_nsr" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_jkzk" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							家庭经济困难状况(糟灾、意外事故，父母病残、欠债、失业下岗，低保)
						</div>
					</td>
					<td colspan="7">
						<bean:write name="rs" property="jtjjknqk" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							有关民政部门信息
						</div>
					</td>
					<td colspan="7">
						<div align="left">
							邮政编码：
							<bean:write name="rs" property="mzbm_yzbm" />
							，&nbsp;&nbsp;&nbsp;&nbsp; 联系电话：
							<bean:write name="rs" property="mzbm_lxdh" />
							，&nbsp;&nbsp;&nbsp;&nbsp; 联系人：
							<bean:write name="rs" property="mzbm_lxr" />
						</div>
					</td>
				</tr>
				<tr>
					<td rowspan="3" scope="row">
						<div align="center">
							缴费情况
						</div>
					</td>
					<td colspan="8">
						<div align="left">
							每年本人和家庭提供费用
							<bean:write name="rs" property="jfqk_jttg" />
							元，&nbsp; 其他亲友提供
							<bean:write name="rs" property="jfqk_qtqytg" />
							元，&nbsp; 合计
							<bean:write name="rs" property="jfqk_hjtg" />
							元
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<div align="left">
							每年应缴各种费用
							<bean:write name="rs" property="jfqk_yjfy" />
							元，&nbsp; 没月生活费
							<bean:write name="rs" property="jfqk_mysffy" />
							元，&nbsp; 全年合计费用：
							<bean:write name="rs" property="jfqk_qnhjfy" />
							元
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<div align="left">
							本学年家庭提供费用不足数：
							<bean:write name="rs" property="jfqk_bxnjttgfybzs" />
							元，&nbsp; 累计欠费：
							<bean:write name="rs" property="jfqk_ljqf" />
							元，&nbsp; 有无缓缴计划：
							<bean:write name="rs" property="jfqk_ywhjjh" />
						</div>
					</td>
				</tr>
				<tr>
					<td rowspan="5" scope="row">
						<div align="center">
							资助情况
						</div>
					</td>
					<td colspan="8">
						<div align="left">
							校内勤工助学：
							<bean:write name="rs" property="zzqk_sfsqxnqgzx" />
							，&nbsp; 岗位：
							<bean:write name="rs" property="zzqk_xnqgzxyapgw" />
							。&nbsp; 校外勤工助学：
							<bean:write name="rs" property="zzqk_sfsqxwqgzx" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<div align="left">
							本学年农村助学贷款：
							<bean:write name="rs" property="zzqk_sfsbnczxdk" />
							，&nbsp; 历年已获农村助学贷款累计
							<bean:write name="rs" property="zzqk_lnyhnczxdhje" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<div align="left">
							本学年高校助学贷款：
							<bean:write name="rs" property="zzqk_sfsbgxzxdk" />
							，&nbsp; 历年已获高校助学贷款累计
							<bean:write name="rs" property="zzqk_lnyhgxzxdhje" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<div align="left">
							本学年保险助学贷款：
							<bean:write name="rs" property="zzqk_sfsbbxzxdk" />
							，&nbsp; 历年已获保险助学贷款累计
							<bean:write name="rs" property="zzqk_lnyhbxzxdhje" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<div align="left">
							历年已获奖、助学金等资助情况：
						</div>
						<logic:equal name="notJzxj" value="is">
							<div align="center">
							无
							</div>
							</logic:equal>
							<logic:equal name="notJzxj" value="no">
								<%ArrayList list = (ArrayList) request
									.getAttribute("xszzList"); 
								  for(Iterator it = list.iterator();it.hasNext();){
								  	String temp = (String)it.next();
								%>
								&nbsp;&nbsp;&nbsp;&nbsp;<%=temp %><br />
								<%} %>
							</logic:equal>
					</td>
				</tr>
				<logic:equal name="isXX" value="no">
				<tr>
					<td colspan="2">
						<div align="center">
						</div>
					</td>
					<td colspan="3">
					</td>
					<td>
						<div align="center">
							审核结果
						</div>
					</td>
					<td colspan="2">
						<html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				</logic:equal>
				<logic:equal name="isXX" value="is">
				<tr>
					<td colspan="2">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />审核结果
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xysh" />
					</td>
					<td>
						<div align="center">
							审核结果
						</div>
					</td>
					<td colspan="2">
						<html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				</logic:equal>
				<tr>
					<td colspan="2">
						<div align="left">
							<bean:message key="lable.xsgzyxpzxy" />审核意见
						</div>
					</td>
					<td colspan="6">
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
					<td colspan="6">
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
