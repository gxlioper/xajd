<%@ page language="java" contentType="text/html;charset=GBK"%>

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
<base target="_self">
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="Pragma" http-equiv="no-cache" />
	<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
	<meta http-equiv="Expires" http-equiv="0" />
	<meta name="Copyright" content="正方软件 zfsoft" />
	<style type="text/css">
	<!--
	.style1 {font-family: "华文行楷"}
	-->
	</style>
</head>
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
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
							<h1 class="style1">
						<strong>
								江苏信息职业技术<bean:message key="lable.xsgzyxpzxy" />
						</strong>
							</h1> 
					</div>
					<div align="center">
							<h2>
						<strong>
								校内勤工助学申报表
						</strong>
							</h2>
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						系(部)&nbsp;
						<bean:write name='rs' property="zymc" />
						&nbsp;班级&nbsp;
						<bean:write name='rs' property="bjmc" />
						&nbsp;姓名&nbsp;
						<bean:write name='rs' property="xm" />
						&nbsp;性别&nbsp;
						<bean:write name='rs' property="xb" />
						&nbsp;&nbsp;申请日期:&nbsp;
						<bean:write name='rs' property="sqsj_year" />
						&nbsp;年&nbsp;
						<bean:write name='rs' property="sqsj_mon" />
						&nbsp;月&nbsp;
						<bean:write name='rs' property="sqsj_day" />
						&nbsp;日
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<table width="100%" class="tbstyle">
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									籍贯
								</div>
							</td>
							<td width="10%">
								<div align="center">
									<bean:write name='rs' property="jg" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									政治面貌
								</div>
							</td>
							<td width="12%">
								<div align="center">
									<bean:write name='rs' property="zzmm" />
								</div>
							</td>
							<td width="12%">
								<div align="center">
									宿舍号
								</div>
							</td>
							<td width="12%">
								<div align="center">
									<bean:write name='rs' property="ssbh" />
								</div>
							</td>
							<td>
								<div align="center">
									学生电话
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="lxdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="6" scope="row" width="4%">
								<div align="center">
									家
									<br />
									庭
									<br />
									成
									<br />
									员
								</div>
							</td>
							<td scope="row" width="12%">
								<div align="center">
									姓名
								</div>
							</td>
							<td>
								<div align="center">
									称谓
								</div>
							</td>
							<td>
								<div align="center">
									年龄
								</div>
							</td>
							<td>
								<div align="center">
									身体状况
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									工作(学习)单位及职务
								</div>
							</td>
							<td>
								<div align="center">
									年收入(元)
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row" width="10%">
								<div align="center">
									<bean:write name='rs' property="jtcy1_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_cw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_jkzk" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy1_gzdwjzw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_nsr" />
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row" width="10%">
								<div align="center">
									<bean:write name='rs' property="jtcy2_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_cw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_jkzk" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy2_gzdwjzw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_nsr" />
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row" width="10%">
								<div align="center">
									<bean:write name='rs' property="jtcy3_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_cw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_jkzk" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy3_gzdwjzw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_nsr" />
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row" width="10%">
								<div align="center">
									<bean:write name='rs' property="jtcy4_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_cw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_jkzk" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy4_gzdwjzw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_nsr" />
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row" width="10%">
								<div align="center">
									<bean:write name='rs' property="jtcy5_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_cw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_jkzk" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy5_gzdwjzw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_nsr" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									学习情况
								</div>
							</td>
							<td colspan="7">
								<bean:write name='rs' property="xxqk" />
								<div align="right">
									(补考后有无不及格课程&nbsp;
									<bean:write name='rs' property="bkhywbjgkc" />
									)&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="4" scope="row">
								每年应缴各种费用&nbsp;
								<bean:write name='rs' property="mnyjngzfy" />
								&nbsp;元
							</td>
							<td colspan="3">
								家庭每年提供&nbsp;
								<bean:write name='rs' property="jtmntg" />
								&nbsp;元
							</td>
							<td colspan="2">
								合计每年尚缺费用&nbsp;
								<bean:write name='rs' property="hjmnsqfy" />
								&nbsp;元
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									家庭经济具体情况
								</div>
							</td>
							<td colspan="7">
								<bean:write name='rs' property="jtjjknqk" />
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									欠缴学费数
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtjjknqk" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									已贷款种类及金额
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtjjknqk" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="4" scope="row">
								<div align="center">
									有何技能特长(书法、电脑等)
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="yhtc" />
								</div>
							</td>
							<td>
								<div align="center">
									班团职务
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="btzw" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									申请参加勤工助学的理由
								</div>
							</td>
							<td colspan="7">
								<bean:write name='rs' property="xssq" />
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									家庭经济
									<br />
									困难类别
								</div>
							</td>
							<td colspan="7">
								<div align="center">
									孤儿：
									<bean:write name='rs' property="sfgr" />
									， 烈士子女：
									<bean:write name='rs' property="sflszn" />
									， 无收入户：
									<bean:write name='rs' property="sfwsrh" />
									， 重病户：
									<bean:write name='rs' property="sfzbh" />
									， 低保户：
									<bean:write name='rs' property="sfdbh" />
									<br />
									父母双下岗：
									<bean:write name='rs' property="sffmsxg" />
									， 纯农户：
									<bean:write name='rs' property="sfcnh" />
									， 低收入(家庭收入不足以支付就学基本费用)：
									<bean:write name='rs' property="sfdsr" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									目前勤工助学情况
								</div>
							</td>
							<td colspan="7">
								<bean:write name='rs' property="mqqgzxqk" />
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									应聘情况
								</div>
							</td>
							<td colspan="4">
								应聘单位、部门、岗位:
								<bean:write name='rs' property="gwdm" />
							</td>
							<td colspan="3">
								是否愿意统一调配:
								<bean:write name='rs' property="sfyytytp" />
							</td>
						</tr>
						<tr>
							<td colspan="2" rowspan="5" scope="row">
								<div align="center">
									班主任意见
								</div>
							</td>
							<td colspan="2">
								操行等第:
								<bean:write name='rs' property="cxdd" />
							</td>
							<td>
								平时表现
							</td>
							<td colspan="4">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan="7">
								奖惩情况：
							</td>
						</tr>
						<tr>
							<td colspan="7">
								有无家庭所在地乡镇(街办)民政部门出具的家庭经济困难证明：有□&nbsp;无□
							</td>
						</tr>
						<tr>
							<td colspan="7">
								已认定&nbsp;困难类别、程度：&nbsp;贫困生&nbsp;是□&nbsp;否□&nbsp;&nbsp;特困生&nbsp;是□&nbsp;否□&nbsp;&nbsp;困难类别:
							</td>
						</tr>
						<tr>
							<td colspan="7">
								<div align="right">
									签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									<bean:message key="lable.xb" />意见
								</div>
							</td>
							<td colspan="7">
								<div align="right">
									签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									学工处意见
								</div>
							</td>
							<td colspan="7">
								<div align="right">
									签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									用工单位意见
								</div>
							</td>
							<td colspan="7">
								<div align="right">
									签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									岗位安排
								</div>
							</td>
							<td colspan="7">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									备注
								</div>
							</td>
							<td colspan="7">
								&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td scope="row">
					&nbsp;&nbsp;&nbsp;&nbsp;应附困难证明(复印件)
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center">
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','')" />
	</div>
</body>
</html:html>
