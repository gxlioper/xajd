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
			var isXX = document.getElementById('isXX').value;
			var xxsh = document.getElementById('xxsh').value;
			if(("通过" == xxsh) && (isXX != "is")){
				alert("学校审核已通过，不能再修改审核结果!");
	          	return false;
			}
			 refreshForm('/xgxt/auditing_hzzy_jtqkdc_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 审核 - 学生及家庭情况调查审核 - 单个审核
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
						<td colspan="9" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right" colspan="3">
						学号
					</td>
					<td align="left" colspan="2">
						<bean:write name="rs" property="xh" />
					</td>
					<td width="16%">
						<div align="right">
							姓名
						</div>
					</td>
					<td colspan="3" align="left">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							<bean:message key="lable.xsgzyxpzxy" />名称
						</div>
					</td>
					<td colspan="2" align="left">
						<bean:write name="rs" property="xymc" />
					</td>
					<td>
						<div align="right">
							专业名称
						</div>
					</td>
					<td colspan="3" align="left">
						<bean:write name="rs" property="zymc" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							班级名称
						</div>
					</td>
					<td colspan="2" align="left">
						<bean:write name="rs" property="bjmc" />
					</td>
					<td>
						<div align="right">
							性别
						</div>
					</td>
					<td colspan="3" align="left">
						<bean:write name="rs" property="xb" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							出生年月
						</div>
					</td>
					<td colspan="2" align="left">
						<bean:write name="rs" property="csny" />
					</td>
					<td>
						<div align="right">
							身份证号
						</div>
					</td>
					<td colspan="3" align="left">
						<bean:write name="rs" property="sfzh" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							民族名称
						</div>
					</td>
					<td colspan="2" align="left">
						<bean:write name="rs" property="mzmc" />
					</td>
					<td>
						<div align="right">
							政治面貌
						</div>
					</td>
					<td colspan="3" align="left">
						<bean:write name="rs" property="zzmmmc" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							毕业学校
						</div>
					</td>
					<td colspan="2" align="left">
						<bean:write name="rs" property="byxx" />
					</td>
					<td>
						<div align="right">
							入学前户口
						</div>
					</td>
					<td colspan="3" align="left">
						<bean:write name="rs" property="rxqhk" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							个人特长
						</div>
					</td>
					<td colspan="6" align="left">
						<bean:write name="rs" property="grtc" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							是否孤残
						</div>
					</td>
					<td colspan="2" align="left">
						<bean:write name="rs" property="sfgc" />
					</td>
					<td>
						<div align="right">
							是否单亲
						</div>
					</td>
					<td colspan="3" align="left">
						<bean:write name="rs" property="sfdq" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							是否烈士子女
						</div>
					</td>
					<td colspan="2" align="left">
						<bean:write name="rs" property="sflszn" />
					</td>
					<td>
						<div align="right">
							家庭人口数
						</div>
					</td>
					<td colspan="3" align="left">
						<bean:write name="rs" property="jtrks" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							家庭邮政编码
						</div>
					</td>
					<td colspan="2" align="left">
						<bean:write name="rs" property="yzbm" />
					</td>
					<td>
						<div align="right">
							家庭联系电话
						</div>
					</td>
					<td colspan="3" align="left">
						<bean:write name="rs" property="lxdh" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							家庭详细通讯地址
						</div>
					</td>
					<td colspan="6" align="left">
						<bean:write name="rs" property="xxtxdz" />
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
							<bean:write name="rs" property="jtcy1_xm" />
						</div>
					</td>
					<td width="6%">
						<div align="center">
							<bean:write name="rs" property="jtcy1_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_gx" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy1_gzdw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_zy" />
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
					<td width="6%">
						<div align="center">
							<bean:write name="rs" property="jtcy2_xm" />
						</div>
					</td>
					<td width="6%">
						<div align="center">
							<bean:write name="rs" property="jtcy2_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_gx" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy2_gzdw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_zy" />
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
					<td width="6%">
						<div align="center">
							<bean:write name="rs" property="jtcy3_xm" />
						</div>
					</td>
					<td width="6%">
						<div align="center">
							<bean:write name="rs" property="jtcy3_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_gx" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy3_gzdw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_zy" />
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
					<td width="6%">
						<div align="center">
							<bean:write name="rs" property="jtcy4_xm" />
						</div>
					</td>
					<td width="6%">
						<div align="center">
							<bean:write name="rs" property="jtcy4_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_gx" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy4_gzdw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_zy" />
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
					<td width="6%">
						<div align="center">
							<bean:write name="rs" property="jtcy5_xm" />
						</div>
					</td>
					<td width="6%">
						<div align="center">
							<bean:write name="rs" property="jtcy5_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_gx" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy5_gzdw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_zy" />
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
					<td colspan="3">
						<div align="right">
							家庭人均年收入
						</div>
					</td>
					<td colspan="2" align="left">
						<bean:write name="rs" property="jtrjnsr" />
					</td>
					<td>
						<div align="right">
							家庭欠债情况
						</div>
					</td>
					<td colspan="3" align="left">
						<bean:write name="rs" property="jtqzqk" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							学生已获资助情况
						</div>
					</td>
					<td colspan="6" align="left">
						<bean:write name="rs" property="xsbxnyhzzqk" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							家庭遭受自然灾害情况
						</div>
					</td>
					<td colspan="6" align="left">
						<bean:write name="rs" property="jtzszrzhqk" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							家庭遭受突发以外事件
						</div>
					</td>
					<td colspan="6" align="left">
						<bean:write name="rs" property="jtzstfywsj" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							家庭成员因残疾、年迈而劳动力弱情况
						</div>
					</td>
					<td colspan="6" align="left">
						<bean:write name="rs" property="jtcyycjnmndlrqk" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							家庭成员失业情况
						</div>
					</td>
					<td colspan="6" align="left">
						<bean:write name="rs" property="jtcysyqk" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							其他情况
						</div>
					</td>
					<td colspan="6" align="left">
						<bean:write name="rs" property="qtqk" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							民政部门邮政编码
						</div>
					</td>
					<td colspan="2" align="left">
						<bean:write name="rs" property="mzbm_yzbm" />
					</td>
					<td>
						<div align="right">
							民政部门联系电话
						</div>
					</td>
					<td colspan="3" align="left">
						<bean:write name="rs" property="mzbm_lxdh" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							民政部门详细通讯地址
						</div>
					</td>
					<td colspan="6" align="left">
						<bean:write name="rs" property="mzbm_xxtxdz" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							申请时间
						</div>
					</td>
					<td colspan="2" align="left">
						<bean:write name="rs" property="sqsj" />
					</td>
					<td>
						<div align="right">
							审核结果
						</div>
					</td>
					<td colspan="3">
						<html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
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
