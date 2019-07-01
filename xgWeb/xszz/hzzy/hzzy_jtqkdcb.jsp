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
<script language="javascript">
		function back(){
			document.forms[0].action = "/xgxt/hzzy_jtqkdc.do";
			document.forms[0].submit();
		}
	</script>
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
							<h2>
						<strong>
								杭州职业技术<bean:message key="lable.xsgzyxpzxy" />学生及家庭情况调查表
						</strong>
							</h2>
					</div>
				</td>
			</tr>
			<tr>
				<td scope="col">
					<div align="center">
						<strong>
							系(院):<u>&nbsp;
							<logic:empty name="rs" property="xymc">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</logic:empty>
							<logic:notEmpty name="rs" property="xymc">
							<bean:write name='rs' property="xymc" />
							</logic:notEmpty>
							&nbsp;&nbsp;</u>
							专业:<u>&nbsp;
							<logic:empty name="rs" property="zymc">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</logic:empty>
							<logic:notEmpty name="rs" property="zymc">
							<bean:write name='rs' property="zymc" />
							</logic:notEmpty>
							&nbsp;&nbsp;</u>
							班级:<u>&nbsp;
							<logic:empty name="rs" property="bjmc">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</logic:empty>
							<logic:notEmpty name="rs" property="bjmc">
							<bean:write name='rs' property="bjmc" />
							</logic:notEmpty>
							&nbsp;&nbsp;</u>
						</strong>
					</div>
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td rowspan="4" scope="col" width="4%">
								<div align="center">
									学生本人基本情况
								</div>
							</td>
							<td scope="col" width="10%">
								<div align="center">
									姓名
								</div>
							</td>
							<td colspan="2" scope="col" width="20%">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td scope="col" width="10%">
								<div align="center">
									性别
								</div>
							</td>
							<td scope="col" width="12%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td scope="col" width="10%">
								<div align="center">
									出生年月
								</div>
							</td>
							<td scope="col" width="12%">
								<div align="center">
									<bean:write name='rs' property="csny" />
								</div>
							</td>
							<td scope="col" width="10%">
								<div align="center">
									民族
								</div>
							</td>
							<td scope="col" width="12%">
								<div align="center">
									<bean:write name='rs' property="mzmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									身份证号
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sfzh" />
								</div>
							</td>
							<td>
								<div align="center">
									政治面貌
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zzmmmc" />
								</div>
							</td>
							<td>
								<div align="center">
									入学前户口
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:empty name="rs" property="xh">
									□&nbsp;城镇&nbsp;&nbsp;□&nbsp;农村
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
									<bean:write name='rs' property="rxqhk" />
									</logic:notEmpty>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家庭人口数
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtrks" />
								</div>
							</td>
							<td>
								<div align="center">
									毕业学校
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="byxx" />
								</div>
							</td>
							<td>
								<div align="center">
									个人特长
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="grtc" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									孤残
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:empty name="rs" property="xh">
									□&nbsp;是&nbsp;&nbsp;□&nbsp;否
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
									<bean:write name='rs' property="sfgc" />
									</logic:notEmpty>
								</div>
							</td>
							<td>
								<div align="center">
									单亲
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:empty name="rs" property="xh">
									□&nbsp;是&nbsp;&nbsp;□&nbsp;否
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
									<bean:write name='rs' property="sfdq" />
									</logic:notEmpty>
								</div>
							</td>
							<td>
								<div align="center">
									烈士子女
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:empty name="rs" property="xh">
									□&nbsp;是&nbsp;&nbsp;□&nbsp;否
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
									<bean:write name='rs' property="sflszn" />
									</logic:notEmpty>
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="2" scope="row">
								<div align="center">
									家庭通讯信息
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									详细通讯地址
								</div>
							</td>
							<td colspan="7">
								<bean:write name='rs' property="xxtxdz" />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									邮政编码
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="yzbm" />
								</div>
							</td>
							<td>
								<div align="center">
									联系电话
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<logic:equal name="rs" property="lxdh" value="-">
									(区号)&nbsp;-
									</logic:equal>
									<logic:notEqual name="rs" property="lxdh" value="-">
									<bean:write name='rs' property="lxdh" />
									</logic:notEqual>
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="6" scope="row">
								<div align="center">
									家庭成员情况
								</div>
							</td>
							<td>
								<div align="center">
									姓名
								</div>
							</td>
							<td width="10%">
								<div align="center">
									年龄
								</div>
							</td>
							<td width="10%">
								<div align="center">
									与学生关系
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									工作(学习)单位
								</div>
							</td>
							<td>
								<div align="center">
									年收入(元)
								</div>
							</td>
							<td>
								<div align="center">
									职业
								</div>
							</td>
							<td>
								<div align="center">
									健康状况
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy1_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy1_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_nsr" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_jkzk" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy2_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy2_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_nsr" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_jkzk" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy3_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy3_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_nsr" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_jkzk" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy4_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy4_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_nsr" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_jkzk" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy5_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy5_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_nsr" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_jkzk" />
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									影响家庭经济状况有关信息
								</div>
							</td>
							<td colspan="9">
								家庭人均年收入<u>&nbsp;
								<logic:empty name='rs' property="jtrjnsr">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name='rs' property="jtrjnsr">
								<bean:write name='rs' property="jtrjnsr" />
								</logic:notEmpty>
								&nbsp;</u>
								(元)。学生本学年已获资助情况<u>&nbsp;
								<logic:empty name='rs' property="xsbxnyhzzqk">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name='rs' property="xsbxnyhzzqk">
								<bean:write name='rs' property="xsbxnyhzzqk" />
								</logic:notEmpty>
								</u>
								。
								<br />
								<br />
								家庭遭受自然灾害情况:<u>&nbsp;
								<logic:empty name='rs' property="jtzszrzhqk">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name='rs' property="jtzszrzhqk">
								<bean:write name='rs' property="jtzszrzhqk" />
								</logic:notEmpty>
								</u>
								。家庭遭受突发意外情况:<u>&nbsp;
								<logic:empty name='rs' property="jtzstfywsj">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name='rs' property="jtzstfywsj">
								<bean:write name='rs' property="jtzstfywsj" />
								</logic:notEmpty>
								</u>
								。
								<br />
								<br />
								家庭成员因残疾、年迈而劳动力弱情况:<u>&nbsp;
								<logic:empty name='rs' property="jtcyycjnmndlrqk">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name='rs' property="jtcyycjnmndlrqk">
								<bean:write name='rs' property="jtcyycjnmndlrqk" />
								</logic:notEmpty>
								</u>
								。
								<br />
								<br />
								家庭成员失业情况:<u>&nbsp;
								<logic:empty name='rs' property="jtcysyqk">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name='rs' property="jtcysyqk">
								<bean:write name='rs' property="jtcysyqk" />
								</logic:notEmpty>
								</u>
								。家庭欠债情况:<u>&nbsp;
								<logic:empty name='rs' property="jtqzqk">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;
								</logic:empty>
								<logic:notEmpty name='rs' property="jtqzqk">
								<bean:write name='rs' property="jtqzqk" />
								</logic:notEmpty>
								</u>
								。
								<br />
								<br />
								其他情况:<u>&nbsp;
								<logic:empty name='rs' property="jtqzqk">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name='rs' property="jtqzqk">
								<bean:write name='rs' property="jtqzqk" />
								</logic:notEmpty>
								</u>
								。
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									签章
								</div>
							</td>
							<td>
								<div align="center">
									学
									<br />
									生
									<br />
									本
									<br />
									人
								</div>
							</td>
							<td>
								&nbsp;
							</td>
							<td>
								<div align="center">
									学生
									<br />
									家长
									<br />
									或监
									<br />
									护人
								</div>
							</td>
							<td>
								&nbsp;
							</td>
							<td>
								<div align="center">
									学生家庭
									<br />
									所在地乡
									<br />
									镇或街道
									<br />
									民政部门
								</div>
							</td>
							<td colspan="4">
								<div align="left">
									当地低保标准:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;元/人·月
								</div>
								<br />
								<div align="left">
									经办人签字:
								</div>
								<br />
								<div align="left">
									单位名称:
								</div>
								<div align="center">
									(加盖公章)
								</div>
								<div align="right">
									年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="2" scope="row">
								<div align="center">
									民政部门信息
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									详细通讯地址
								</div>
							</td>
							<td colspan="7">
								<bean:write name='rs' property="mzbm_xxtxdz" />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									邮政编码
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="mzbm_yzbm" />
								</div>
							</td>
							<td>
								<div align="center">
									联系电话
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<logic:equal name="rs" property="mzbm_lxdh" value="-">
									(区号)&nbsp;-
									</logic:equal>
									<logic:notEqual name="rs" property="mzbm_lxdh" value="-">
									<bean:write name='rs' property="mzbm_lxdh" />
									</logic:notEqual>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td align="left">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					注：低保户、烈士家庭、五保户、残疾学生等附证明文件复印件。
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center">
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','')" />
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input  value="返回"
			onclick="back();" />
	</div>
</body>
</html:html>
