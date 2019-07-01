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
	<title><bean:message key="lable.title" /></title>
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
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td>
					<div align="center">
							<h3>
						<strong>
								高等学校学生及家庭情况调查表
						</strong>
							</h3> 
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						学校：
						<logic:equal name="xxmc" value="isnull">
							<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						</logic:equal>
						<logic:notEqual name="xxmc" value="isnull">
							<u>&nbsp;&nbsp;<bean:write name='rs' property="xxmc" />&nbsp;&nbsp;</u>
						</logic:notEqual>
						&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
						<logic:equal name="xymc" value="isnull">
							<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						</logic:equal>
						<logic:notEqual name="xymc" value="isnull">
							<u>&nbsp;&nbsp;<bean:write name='rs' property="xymc" />&nbsp;&nbsp;</u>
						</logic:notEqual>
						&nbsp;&nbsp;专业：
						<logic:equal name="zymc" value="isnull">
							<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						</logic:equal>
						<logic:notEqual name="zymc" value="isnull">
							<u>&nbsp;&nbsp;<bean:write name='rs' property="zymc" />&nbsp;&nbsp;</u>
						</logic:notEqual>
						<br />
						年级：
						<logic:equal name="nj" value="isnull">
							<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						</logic:equal>
						<logic:notEqual name="nj" value="isnull">
							<u>&nbsp;&nbsp;<bean:write name='rs' property="nj" />&nbsp;&nbsp;</u>
						</logic:notEqual>
						&nbsp;&nbsp;&nbsp;&nbsp;班级：
						<logic:equal name="bjmc" value="isnull">
							<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						</logic:equal>
						<logic:notEqual name="bjmc" value="isnull">
							<u>&nbsp;&nbsp;<bean:write name='rs' property="bjmc" />&nbsp;&nbsp;</u>
						</logic:notEqual>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td rowspan="4" scope="col" width="6%">
								<div align="center">
									学生本人基本情况
								</div>
							</td>
							<td scope="col" width="9%">
								<div align="center">
									姓名
								</div>
							</td>
							<td colspan="2" scope="col">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td scope="col" width="10%">
								<div align="center">
									性别
								</div>
							</td>
							<td scope="col" width="10%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td scope="col" width="11%">
								<div align="center">
									出生年月
								</div>
							</td>
							<td colspan="2" scope="col">
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
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="zzmmmc" />
								</div>
							</td>
							<td>
								<div align="center">
									入学前<br />户口
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:equal name="xh" value="isnull">
    									□&nbsp;城镇
    									&nbsp;&nbsp;
    									□&nbsp;农村
    								</logic:equal>
    								<logic:notEqual name="xh" value="isnull">
									<logic:equal name="rxqhk" value="城镇">
    									√&nbsp;城镇
    									&nbsp;&nbsp;
    									□&nbsp;农村
    								</logic:equal>
									<logic:equal name="rxqhk" value="农村">
    									□&nbsp;城镇
    									&nbsp;&nbsp;
    									√&nbsp;农村
    								</logic:equal>
    								</logic:notEqual>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家庭
									<br />
									人口数
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
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="byxx" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									个人特长
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="grtc" />
								</div>
							</td>
						</tr>
						<tr>
							<td height="52">
								<div align="center">
									孤残
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:equal name="xh" value="isnull">
    									□&nbsp;是
    									&nbsp;&nbsp;
    									□&nbsp;否
    								</logic:equal>
    								<logic:notEqual name="xh" value="isnull">
									<logic:equal name="sfgc" value="是">
    									√&nbsp;是
    									&nbsp;&nbsp;
    									□&nbsp;否
    								</logic:equal>
									<logic:equal name="sfgc" value="否">
    									□&nbsp;是
    									&nbsp;&nbsp;
    									√&nbsp;否
    								</logic:equal>
    								</logic:notEqual>
								</div>
							</td>
							<td>
								<div align="center">
									单亲
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:equal name="xh" value="isnull">
    									□&nbsp;是
    									&nbsp;&nbsp;
    									□&nbsp;否
    								</logic:equal>
    								<logic:notEqual name="xh" value="isnull">
									<logic:equal name="sfdq" value="是">
    									√&nbsp;是
    									&nbsp;&nbsp;
    									□&nbsp;否
   								 	</logic:equal>
									<logic:equal name="sfdq" value="否">
    									□&nbsp;是
    									&nbsp;&nbsp;
    									√&nbsp;否
    								</logic:equal>
    								</logic:notEqual>
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									烈士子女
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:equal name="xh" value="isnull">
    									□&nbsp;是
    									&nbsp;&nbsp;
   								 		□&nbsp;否
 								   	</logic:equal>
 								   	<logic:notEqual name="xh" value="isnull">
									<logic:equal name="sflszn" value="是">
    									√&nbsp;是
    									&nbsp;&nbsp;
    									□&nbsp;否
   									</logic:equal>
									<logic:equal name="sflszn" value="否">
    									□&nbsp;是
    									&nbsp;&nbsp;
    									√&nbsp;否
    								</logic:equal>
    								</logic:notEqual>
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="2" scope="row">
								<div align="center">
									家庭
									<br />
									通讯
									<br />
									信息
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									详细通讯地址
								</div>
							</td>
							<td colspan="8">
								<bean:write name='rs' property="jtxxtxdz" />
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
									<bean:write name='rs' property="jtyzbm" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									联系电话
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<logic:equal name="jtlxdh" value="-">
   										（区号）-&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   									</logic:equal>
									<logic:notEqual name="jtlxdh" value="-">
										<bean:write name='rs' property="jtlxdh" />
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
							<td width="8%">
								<div align="center">
									年龄
								</div>
							</td>
							<td width="8%">
								<div align="center">
									与学生关系
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									工作(学习)单位
								</div>
							</td>
							<td width="8%">
								<div align="center">
									职业
								</div>
							</td>
							<td width="8%">
								<div align="center">
									年收入(元)
								</div>
							</td>
							<td colspan="2">
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
									<bean:write name='rs' property="jtcy1_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_nsr" />
								</div>
							</td>
							<td colspan="2">
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
									<bean:write name='rs' property="jtcy2_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_nsr" />
								</div>
							</td>
							<td colspan="2">
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
									<bean:write name='rs' property="jtcy3_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_nsr" />
								</div>
							</td>
							<td colspan="2">
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
									<bean:write name='rs' property="jtcy4_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_nsr" />
								</div>
							</td>
							<td colspan="2">
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
									<bean:write name='rs' property="jtcy5_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_nsr" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtcy5_jkzk" />
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									影响
									<br />
									家庭
									<br />
									经济
									<br />
									状况
									<br />
									有关
									<br />
									信息
								</div>
							</td>
							<td colspan="10">
								家庭人均年收入
								<logic:equal name="jtrjnsr" value="isnull">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:equal>
								<logic:notEqual name="jtrjnsr" value="isnull">
									<u>&nbsp;<bean:write name='rs' property="jtrjnsr" />&nbsp;</u>
								</logic:notEqual>
								(元)。学生本学年已获资助情况
								<logic:equal name="xsbxnyhzzqk" value="isnull">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
									<br />
									&nbsp;<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>。
								</logic:equal>
								<logic:notEqual name="xsbxnyhzzqk" value="isnull">
									<u>&nbsp;<bean:write name='rs' property="xsbxnyhzzqk" />&nbsp;</u>。
								</logic:notEqual>
								<br />
								家庭遭受自然灾害情况：
								<logic:equal name="jtzszrzhqk" value="isnull">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:equal>
								<logic:notEqual name="jtzszrzhqk" value="isnull">
									<u>&nbsp;<bean:write name='rs' property="jtzszrzhqk" />&nbsp;</u>
								</logic:notEqual>
								。家庭遭受突发意外事件：
								<logic:equal name="jtzstfsjqk" value="isnull">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:equal>
								<logic:notEqual name="jtzstfsjqk" value="isnull">
									<u>&nbsp;<bean:write name='rs' property="jtzstfsjqk" />&nbsp;</u>
								</logic:notEqual>
								。
								<br />
								家庭成员因残疾、年迈而劳动能力弱情况：
								<logic:equal name="jtcyycjnmrldnlrqk" value="isnull">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:equal>
								<logic:notEqual name="jtcyycjnmrldnlrqk" value="isnull">
									<u>&nbsp;<bean:write name='rs' property="jtcyycjnmrldnlrqk" />&nbsp;</u>
								</logic:notEqual>
								。
								<br />
								家庭成员失业情况：
								<logic:equal name="jtcysyqk" value="isnull">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:equal>
								<logic:notEqual name="jtcysyqk" value="isnull">
									<u>&nbsp;<bean:write name='rs' property="jtcysyqk" />&nbsp;</u>
								</logic:notEqual>
								。家庭欠债情况：
								<logic:equal name="jtqzqk" value="isnull">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:equal>
								<logic:notEqual name="jtqzqk" value="isnull">
									<u>&nbsp;<bean:write name='rs' property="jtqzqk" />&nbsp;</u>
								</logic:notEqual>
								。
								<br />
								其他情况：
								<logic:equal name="qtqk" value="isnull">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:equal>
								<logic:notEqual name="qtqk" value="isnull">
									<u>&nbsp;<bean:write name='rs' property="qtqk" />&nbsp;</u>
								</logic:notEqual>
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
							<td colspan="2">
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
							<td colspan="2">
								&nbsp;
							</td>
							<td colspan="2">
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
							<td colspan="2">
								经办人签字：
								<br />
								<br />
								单位名称：
								<div align="center">
									(加盖公章)
								</div>
								<div align="right">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>年
									<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>月
									<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>日 &nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="2" scope="row">
								<div align="center">
									民政
									<br />
									部门
									<br />
									信息
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									详细通讯地址
								</div>
							</td>
							<td colspan="8">
								<bean:write name='rs' property="mzbm_xxtxdz" />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									邮政编码
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="mzbm_yzbm" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									联系电话
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<logic:equal name="mzbm_lxdh" value="-">
    									（区号）-&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    								</logic:equal>
									<logic:notEqual name="mzbm_lxdh" value="-">
										<bean:write name='rs' property="mzbm_lxdh" />
									</logic:notEqual>
								</div>
							</td>
						</tr>
					</table>
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
