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
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td>
					<div align="center">
							<h3>
						<strong>
								上海市高等学校家庭经济困难学生认定申请表
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
						&nbsp;&nbsp;班级：
						<logic:equal name="bjmc" value="isnull">
							<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						</logic:equal>
						<logic:notEqual name="bjmc" value="isnull">
							<u>&nbsp;&nbsp;<bean:write name='rs' property="bjmc" />&nbsp;&nbsp;</u>
						</logic:notEqual>
						&nbsp;&nbsp;学号：
						<logic:equal name="xh" value="isnull">
							<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						</logic:equal>
						<logic:notEqual name="xh" value="isnull">
							<u>&nbsp;&nbsp;<bean:write name='rs' property="xh" />&nbsp;&nbsp;</u>
						</logic:notEqual>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td width="4%" rowspan="4" scope="col">
								<div align="center">
									学生基本情况
								</div>
							</td>
							<td scope="col" width="12%">
								<div align="center">
									姓名
								</div>
							</td>
							<td scope="col" width="18%">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td scope="col" width="12%">
								<div align="center">
									性别
								</div>
							</td>
							<td scope="col" width="10%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td scope="col" width="10%">
								<div align="center">
									联系电话
								</div>
							</td>
							<td scope="col" width="10%">
								<div align="center">
									<bean:write name='rs' property="lxdh" />
								</div>
							</td>
							<td scope="col" width="10%">
								<div align="center">
									民族
								</div>
							</td>
							<td scope="col" width="10%">
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
							<td>
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
							<td colspan="2">
								<div align="center">
									入学前户口
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
									家庭住址
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtzz" />
								</div>
							</td>
							<td>
								<div align="center">
									邮政编码
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="yzbm" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									家庭联系电话
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:equal name="jtlxdh" value="-">
   										（区号）-&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   									</logic:equal>
									<logic:notEqual name="jtlxdh" value="-">
										<bean:write name='rs' property="jtlxdh" />
									</logic:notEqual>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="left">
									是否愿意参加慈善或志愿活动
								</div>
							</td>
							<td>
								<div align="center">
									<logic:equal name="xh" value="isnull">
   										&nbsp;
   									</logic:equal>
									<logic:notEqual name="xh" value="isnull">
										<bean:write name='rs' property="sfyycjcshzyhd" />
									</logic:notEqual>
								</div>
							</td>
							<td colspan="4">
								<div align="left">
									是否愿意申请国家助学贷款或勤工助学
								</div>
							</td>
							<td>
								<div align="center">
									<logic:equal name="xh" value="isnull">
   										&nbsp;
   									</logic:equal>
									<logic:notEqual name="xh" value="isnull">
										<bean:write name='rs' property="sfyysqgjzxdkhqgzx" />
									</logic:notEqual>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="4" scope="row">
								<div align="left">
									学生家长或监护人申请签名：
								</div>
							</td>
							<td colspan="5">
								<div align="left">
									学生本人申请签名：
								</div>
							</td>
						</tr>
					</table>
					<table width="100%" class="tbstyle">
						<tr>
							<td colspan="9" scope="col">
								<div align="center">
									<strong>家庭经济困难情况调查</strong>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" rowspan="2" scope="row">
								<div align="center">
									家庭类型
								</div>
							</td>
							<td colspan="7">
								<logic:equal name="xh" value="isnull">
   									&nbsp;□
   								</logic:equal>
								<logic:notEqual name="xh" value="isnull">
									<logic:equal name="sfjq" value="是">
										&nbsp;√
									</logic:equal>
									<logic:equal name="sfjq" value="否">
										&nbsp;□
									</logic:equal>
								</logic:notEqual>
								&nbsp;健全&nbsp;&nbsp;
								<logic:equal name="xh" value="isnull">
   									&nbsp;□
   								</logic:equal>
								<logic:notEqual name="xh" value="isnull">
									<logic:equal name="sfge" value="是">
										&nbsp;√
									</logic:equal>
									<logic:equal name="sfge" value="否">
										&nbsp;□
									</logic:equal>
								</logic:notEqual>
								&nbsp;孤儿&nbsp;&nbsp;
								<logic:equal name="xh" value="isnull">
   									&nbsp;□
   								</logic:equal>
								<logic:notEqual name="xh" value="isnull">
									<logic:equal name="sfdq" value="是">
										&nbsp;√
									</logic:equal>
									<logic:equal name="sfdq" value="否">
										&nbsp;□
									</logic:equal>
								</logic:notEqual>
								&nbsp;单亲&nbsp;&nbsp;
								<logic:equal name="xh" value="isnull">
   									&nbsp;□
   								</logic:equal>
								<logic:notEqual name="xh" value="isnull">
									<logic:equal name="sfcj" value="是">
										&nbsp;√
									</logic:equal>
									<logic:equal name="sfcj" value="否">
										&nbsp;□
									</logic:equal>
								</logic:notEqual>
								&nbsp;残疾&nbsp;&nbsp;
								<logic:equal name="xh" value="isnull">
   									&nbsp;□
   								</logic:equal>
								<logic:notEqual name="xh" value="isnull">
									<logic:equal name="sfjls" value="是">
										&nbsp;√
									</logic:equal>
									<logic:equal name="sfjls" value="否">
										&nbsp;□
									</logic:equal>
								</logic:notEqual>
								&nbsp;军烈属&nbsp;&nbsp;
								<logic:equal name="xh" value="isnull">
   									&nbsp;□
   								</logic:equal>
								<logic:notEqual name="xh" value="isnull">
									<logic:equal name="sfly" value="是">
										&nbsp;√
									</logic:equal>
									<logic:equal name="sfly" value="否">
										&nbsp;□
									</logic:equal>
								</logic:notEqual>
								&nbsp;离异&nbsp;&nbsp;
								<logic:equal name="xh" value="isnull">
   									&nbsp;□
   								</logic:equal>
								<logic:notEqual name="xh" value="isnull">
									<logic:equal name="sfzb" value="是">
										&nbsp;√
									</logic:equal>
									<logic:equal name="sfzb" value="否">
										&nbsp;□
									</logic:equal>
								</logic:notEqual>
								&nbsp;重病
							</td>
						</tr>
						<tr>
							<td colspan="7">
								<div align="left">
									注：1.单亲指一方去世;2.离异家庭注明对方抚养情况;3.孤儿写明监护人的情况及收入和民政补贴;
									<br />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.军烈属及优抚家庭需提供相应证明;5.残疾及重病家庭需提供县级以上医院证明
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="6" scope="row" width="4%">
								<div align="center">
									家庭人口
								</div>
							</td>
							<td width="12%">
								<div align="center">
									姓名
								</div>
							</td>
							<td width="8%">
								<div align="center">
									年龄
								</div>
							</td>
							<td width="10%">
								<div align="center">
									与学生<br />关系
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									工作(学习)单位
								</div>
							</td>
							<td width="12%">
								<div align="center">
									职业
								</div>
							</td>
							<td width="12%">
								<div align="center">
									年收入(元)
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
								<div align="center">
									<bean:write name='rs' property="jtcy1_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;
									<bean:write name='rs' property="jtcy1_nl" />
									&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_gx" />
								</div>
							</td>
							<td colspan="2">
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
									&nbsp;
									<bean:write name='rs' property="jtcy2_nl" />
									&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_gx" />
								</div>
							</td>
							<td colspan="2">
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
									&nbsp;
									<bean:write name='rs' property="jtcy3_nl" />
									&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_gx" />
								</div>
							</td>
							<td colspan="2">
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
									&nbsp;
									<bean:write name='rs' property="jtcy4_nl" />
									&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_gx" />
								</div>
							</td>
							<td colspan="2">
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
									&nbsp;
									<bean:write name='rs' property="jtcy5_nl" />
									&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_gx" />
								</div>
							</td>
							<td colspan="2">
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
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_jkzk" />
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									家庭经济状况
								</div>
							</td>
							<td colspan="8">
								家庭人均年收入
								<logic:equal name="jtrjnsr" value="isnull">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:equal>
								<logic:notEqual name="jtrjnsr" value="isnull">
									<u>&nbsp;<bean:write name='rs' property="jtrjnsr" />&nbsp;</u>
								</logic:notEqual>
								(元)。学生在本地受助情况：
								<logic:equal name="xszbdszqk" value="isnull">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>。
								</logic:equal>
								<logic:notEqual name="xszbdszqk" value="isnull">
									<u>&nbsp;<bean:write name='rs' property="xszbdszqk" />&nbsp;</u>。
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
								<logic:equal name="jtzstfywsj" value="isnull">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:equal>
								<logic:notEqual name="jtzstfywsj" value="isnull">
									<u>&nbsp;<bean:write name='rs' property="jtzstfywsj" />&nbsp;</u>
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
								<br />
								<br />
								<div align="right">
									家庭所在地民政部门名称：
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									经过人签名：
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<div align="right">
									（加盖公章）
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									民政部门<br />通讯地址
								</div>
							</td>
							<td colspan="3">
								<bean:write name='rs' property="mzbm_txdz" />
							</td>
							<td width="10%">
								<div align="center">
									邮政编码
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="mzbm_yzbm" />
								</div>
							</td>
							<td>
								<div align="center">
									联系电话
								</div>
							</td>
							<td>
								<div align="center">
									<logic:equal name="mzbm_lxdh" value="-">
    									(区号)-&nbsp;&nbsp;
    								</logic:equal>
									<logic:notEqual name="mzbm_lxdh" value="-">
										<bean:write name='rs' property="mzbm_lxdh" />
									</logic:notEqual>
								</div>
							</td>
						</tr>
					</table>
					<table width="100%" class="tbstyle">
						<tr>
							<td colspan="5" scope="col">
								<div align="center">
									<strong>家庭经济困难认定</strong>
								</div>
							</td>
						</tr>
						<tr>
							<td width="4%" rowspan="3" scope="row">
								<div align="center">
									民主评议
								</div>
							</td>
							<td width="4%" rowspan="3">
								<div align="center">
									推荐档次
								</div>
							</td>
							<td width="42%">
								&nbsp;A.家庭经济特别困难&nbsp;□
							</td>
							<td width="4%" rowspan="3">
								<div align="center">
									陈述理由
								</div>
							</td>
							<td width="46%" rowspan="3">
								院(系)评议小组组长签字：
								<div align="right">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>年
									<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>月
									<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>日 &nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								&nbsp;B.家庭经济困难&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;□
							</td>
						</tr>
						<tr>
							<td>
								&nbsp;C.家庭经济不困难&nbsp;&nbsp;&nbsp;□
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									认定意见
								</div>
							</td>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />意见
								</div>
							</td>
							<td>
								<br />
								&nbsp;□&nbsp;&nbsp;同意评议小组意见。
								<br />
								&nbsp;□&nbsp;&nbsp;不同意评议小组意见。
								<br />
								<br />
								&nbsp;调整为
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>。
								<br />
								<br />
								&nbsp;院(系)工作组组长签字：
								<div align="right">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>年
									<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>月
									<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>日 &nbsp;&nbsp;
								</div>
								<div align="right">
									（加盖部门公章） &nbsp;&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									学校意见
								</div>
							</td>
							<td>
								<br />
								&nbsp;□&nbsp;&nbsp;同意工作组和评议小组意见。
								<br />
								&nbsp;□&nbsp;&nbsp;不同意工作组和评议小组意见。
								<br />
								<br />
								&nbsp;调整为
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>。
								<br />
								<br />
								&nbsp;学校学生资助管理机构负责人签字：
								<div align="right">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>年
									<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>月
									<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>日 &nbsp;&nbsp;
								</div>
								<div align="right">
									（加盖部门公章） &nbsp;&nbsp;
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
