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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
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
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：勤工助学 - 审核 - 学生申请审核 - 单个审核
				</div>
			</div>
			<input type="hidden" name="pkVal"
				value="<bean:write name="rs" property="xh||gwdm||sqsj"/>" />
			<input type="hidden" name="gwdm"
				value="<bean:write name="rs" property="gwdm"/>" />
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="8" align="center">
							单个学生申请审核
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" colspan="2" width="16%">
							学号
						</td>
						<td align="left" colspan="3" width="34%">
							<bean:write name='rs' property="xh" />
						</td>
					</logic:equal>
					<td align="center" width="16%">
						岗位名称
					</td>
					<td align="left" colspan="2">
						<bean:write name='rs' property="gwdm" />
					</td>
				</tr>

				<tr style="height:22px">
					<td align="center" colspan="2">
						姓名
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xm" />
					</td>
					<td align="center">
						性别
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xb" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="center" colspan="2">
						政治面貌
					</td>
					<td colspan="3">
						<bean:write name="rs" property="zzmm" />
					</td>
					<td align="center">
						宿舍号
					</td>
					<td colspan="2">
						<bean:write name="rs" property="ssbh" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="center" colspan="2">
						<bean:message key="lable.xsgzyxpzxy" />名称
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xymc" />
					</td>
					<td align="center">
						专业名称
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zymc" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="center" colspan="2">
						班级名称
					</td>
					<td colspan="3">
						<bean:write name="rs" property="bjmc" />
					</td>
					<td align="center">
						申请时间
					</td>
					<td colspan="2">
						<bean:write name="rs" property="sqsj" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="center" colspan="2">
						班团职务
					</td>
					<td colspan="3">
						<bean:write name="rs" property="btzw" />
					</td>
					<td align="center">
						有何技能特长
					</td>
					<td colspan="2">
						<bean:write name="rs" property="yhtc" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="center" colspan="2">
						籍贯
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jg" />
					</td>
					<td align="center">
						学生电话
					</td>

					<td colspan="2">
						<bean:write name="rs" property="lxdh" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="center" colspan="2">
						补考后有无不及格课程
					</td>
					<td colspan="3">
						<bean:write name="rs" property="bkhywbjgkc" />
					</td>
					<td align="center">
						是否愿意统一调配
					</td>
					<td colspan="2" align="center">
						<bean:write name="rs" property="sfyytytp" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							学习情况
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="xxqk" />
					</td>
				</tr>
				<tr>
					<td rowspan="6" scope="row" width="4%">
						<div align="center">
							家
							<br />
							庭
							<br />
							主
							<br />
							要
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
					<td width="10%">
						<div align="center">
							称谓
						</div>
					</td>
					<td width="10%">
						<div align="center">
							年龄
						</div>
					</td>
					<td width="14%">
						<div align="center">
							身体状况
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							工作(学习)单位及职务
						</div>
					</td>
					<td width="12%">
						<div align="center">
							年收入(元)
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
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_jkzk" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy1_gzdwjzw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_nsr" />
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
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_jkzk" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy2_gzdwjzw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_nsr" />
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
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_jkzk" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy3_gzdwjzw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_nsr" />
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
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_jkzk" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy4_gzdwjzw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_nsr" />
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
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_jkzk" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy5_gzdwjzw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_nsr" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							孤儿
						</div>
					</td>
					<td colspan="3" align="center">
						<bean:write name="rs" property="sfgr" />
					</td>
					<td>
						<div align="center">
							烈士子女
						</div>
					</td>
					<td colspan="2" align="center">
						<bean:write name="rs" property="sflszn" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							无收入户
						</div>
					</td>
					<td colspan="3" align="center">
						<bean:write name="rs" property="sfwsrh" />
					</td>
					<td>
						<div align="center">
							重病户
						</div>
					</td>
					<td colspan="2" align="center">
						<bean:write name="rs" property="sfzbh" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							低保户
						</div>
					</td>
					<td colspan="3" align="center">
						<bean:write name="rs" property="sfdbh" />
					</td>
					<td>
						<div align="center">
							父母双下岗
						</div>
					</td>
					<td colspan="2" align="center">
						<bean:write name="rs" property="sffmsxg" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							纯农户
						</div>
					</td>
					<td colspan="3" align="center">
						<bean:write name="rs" property="sfcnh" />
					</td>
					<td>
						<div align="center">
							低收入(家庭收入不足以支付就学基本费用)
						</div>
					</td>
					<td colspan="2" align="center">
						<bean:write name="rs" property="sfdsr" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭经济困难具体情况
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="jtjjknqk" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							每年应缴纳各种费用
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="mnyjngzfy" />
					</td>
					<td>
						<div align="center">
							家庭每年提供
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtmntg" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							合计每年尚缺费用
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="hjmnsqfy" />
					</td>
					<td>
						<div align="center">
							欠缴学费数
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="qjxfs" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							已贷款种类及金额
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="yhdkzljje" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							目前勤工助学情况
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="mqqgzxqk" />
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						申请理由
					</td>
					<td colspan="6">
						<bean:write name="rs" property="xssq" />
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						备注
					</td>
					<td colspan="6">
						<bean:write name="rs" property="bz" />
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						是否困难生
					</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="sfpks" />
					</td>
					<td align="center">
						审核
					</td>
					<td align="left" colspan="2">
						<html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2"
					onclick="refreshForm('/xgxt/postStuChkOne.do?act=save');"
					style="width:80px" id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
		<logic:equal value="view" name="result">
			<script>
				alert("操作成功！");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
		<logic:equal value="full" name="result">
			<script>
				alert("人数已满！");
			</script>
		</logic:equal>
		<logic:equal value="knsfull" name="result">
			<script>
				alert("困难生人数已满！");
			</script>
		</logic:equal>
	</body>
</html>
