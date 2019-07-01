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
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			var xyshyj = document.getElementById('xyshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			var userT = document.getElementById('userT').value;
			var xxsh = document.getElementById('xxsh').value;
			
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 150){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />审核意见不能大于150个字符");
	          		 return false;
	       		 }
	       	}
			if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 150){	         
	          		alert("学校审核意见不能大于150个字符");
	          		return false;
	       		 }
	       	}
			if((("困难" == xxsh) || ("特别困难" == xxsh)) && (userT != "xx")){
				alert("学校审核已通过，不能再修改审核结果!");
	          	return false;
			}
		
			refreshForm('/xgxt/auditing_shgc_kns_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 审核 - 家庭经济困难学生认定审核 - 单个审核
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pk"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" name="tName" value="<bean:write name="tName" />" />
			<input type="hidden" name="xxsh" value="<bean:write name="rs" property="xxsh" />" />
			<input type="hidden" name="userT" value="<bean:write name="userT" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="8" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td align="center" colspan="2">
						学号
					</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="xh" />
					</td>
					<td colspan="2">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="34%">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							性别
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xb" />
					</td>
					<td colspan="2">
						<div align="center">
							身份证号
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sfzh" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							民族
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="mzmc" />
					</td>
					<td colspan="2">
						<div align="center">
							政治面貌
						</div>
					</td>
					<td>
						<bean:write name="rs" property="zzmmmc" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							系别
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xymc" />
					</td>
					<td colspan="2">
						<div align="center">
							专业
						</div>
					</td>
					<td>
						<bean:write name="rs" property="zymc" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							班级
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="bjmc" />
					</td>
					<td colspan="2">
						<div align="center">
							年级
						</div>
					</td>
					<td>
						<bean:write name="rs" property="nj" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							生源地
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="syd" />
					</td>
					<td colspan="2">
						<div align="center">
							家庭住址
						</div>
					</td>
					<td align="center">
						<bean:write name="rs" property="jtzz" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							联系电话
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="lxdh" />
					</td>
					<td colspan="2">
						<div align="center">
							入学前户口
						</div>
					</td>
					<td align="center">
						<bean:write name="rs" property="rxqhk" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							邮政编码
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="yzbm" />
					</td>
					<td colspan="2">
						<div align="center">
							联系电话
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtlxdh" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							是否愿意参加
							<br />
							慈善或志愿活动
						</div>
					</td>
					<td colspan="3" align="center">
						<bean:write name="rs" property="sfyycjcshzyhd" />
					</td>
					<td colspan="2">
						<div align="center">
							是否愿意申请国家
							<br />
							助学贷款或勤工助学
						</div>
					</td>
					<td align="center">
						<bean:write name="rs" property="sfyysqgjzxdkhqgzx" />
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<div align="center">
							家庭类型
						</div>
						<div align="left">
							<font color="#ff0000">
								注：1.单亲指一方去世；2.离异家庭注明对方抚养情况；3.孤儿写明监护人的情况及收入和民政补贴； <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.军烈属及优抚家庭需提供相应证明；5.残疾及重病家庭需提供县级以上医院证明
							</font>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							是否健全
						</div>
					</td>
					<td align="center" colspan="3">
						<bean:write name="rs" property="sfjq" />
					</td>
					<td colspan="2">
						<div align="center">
							是否孤儿
						</div>
					</td>
					<td align="center">
						<bean:write name="rs" property="sfge" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							是否单亲
						</div>
					</td>
					<td align="center" colspan="3">
						<bean:write name="rs" property="sfdq" />
					</td>
					<td colspan="2">
						<div align="center">
							是否残疾
						</div>
					</td>
					<td align="center">
						<bean:write name="rs" property="sfcj" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							是否军烈属
						</div>
					</td>
					<td align="center" colspan="3">
						<bean:write name="rs" property="sfjls" />
					</td>
					<td colspan="2">
						<div align="center">
							是否离异
						</div>
					</td>
					<td align="center">
						<bean:write name="rs" property="sfly" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							是否重病
						</div>
					</td>
					<td align="center" colspan="3">
						<bean:write name="rs" property="sfzb" />
					</td>
					<td colspan="2">
						<div align="center">
							家庭人均年收入(元)
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtrjnsr" />
					</td>
				</tr>
				<tr>
					<td rowspan="6" width="4%">
						<div align="center">
							家庭成员情况
						</div>
					</td>
					<td width="12%" align="center">
						姓名
					</td>
					<td width="10%" align="center">
						年龄
					</td>
					<td width="12%" align="center">
						与学生
						<br />
						关系
					</td>
					<td width="12%" align="center">
						职业
					</td>
					<td width="8%" align="center">
						年收入
						<br />
						(元)
					</td>
					<td width="8%" align="center">
						健康状况
					</td>
					<td align="center">
						工作(学习)单位
					</td>
				</tr>
				<tr>
					<td align="center">
						<bean:write name="rs" property="jtcy1_xm" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy1_nl" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy1_gx" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy1_zy" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy1_nsr" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy1_jkzk" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy1_gzdw" />
					</td>
				</tr>
				<tr>
					<td align="center">
						<bean:write name="rs" property="jtcy2_xm" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy2_nl" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy2_gx" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy2_zy" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy2_nsr" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy2_jkzk" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy2_gzdw" />
					</td>
				</tr>
				<tr>
					<td align="center">
						<bean:write name="rs" property="jtcy3_xm" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy3_nl" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy3_gx" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy3_zy" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy3_nsr" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy3_jkzk" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy3_gzdw" />
					</td>
				</tr>
				<tr>
					<td align="center">
						<bean:write name="rs" property="jtcy4_xm" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy4_nl" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy4_gx" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy4_zy" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy4_nsr" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy4_jkzk" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy4_gzdw" />
					</td>
				</tr>
				<tr>
					<td align="center">
						<bean:write name="rs" property="jtcy5_xm" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy5_nl" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy5_gx" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy5_zy" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy5_nsr" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy5_jkzk" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy5_gzdw" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							学生在本地受助情况
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="xszbdszqk" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭遭受自然灾害情况
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="jtzszrzhqk" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭遭受突发意外事件
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="jtzstfywsj" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							其他情况
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="qtqk" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							民政部门详细通讯地址
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="mzbm_txdz" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							民政部门邮政编码
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="mzbm_yzbm" />
					</td>
					<td colspan="2">
						<div align="center">
							民政部门联系电话
						</div>
					</td>
					<td>
						<bean:write name="rs" property="mzbm_lxdh" />
					</td>
				</tr>
				<logic:equal name="userT" value="xx">
					<tr>
						<td colspan="2">
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />审核
							</div>
						</td>
						<td colspan="3">
							<bean:write name="rs" property="xysh" />
						</td>
						<td colspan="2">
							<div align="center">
								认定时间
							</div>
						</td>
						<td>
							<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('rdsj','y-mm-dd');"
								value="<bean:write name='rs' property="rdsj" />" name="rdsj"
								id="rdsj" />
						</td>
					</tr>
				</logic:equal>
				<tr>
					<td colspan="2">
						<div align="center">
							申请时间
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sqsj" />
					</td>
					<td colspan="2">
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
