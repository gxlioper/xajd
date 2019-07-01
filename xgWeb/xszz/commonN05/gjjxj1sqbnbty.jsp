<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

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
			document.forms[0].action = "/xgxt/n05_xszz.do?method=gjjxj1sq";
			document.forms[0].submit();
		}
	</script>
<body>
	<html:form action="zxdksq.do" method="post">
		<input type="hidden" name="pkVal" value="${rs.xn }${rs.xh }"/>
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
						<h2>
							<strong>
							<logic:empty name="rs" property="xn">
							（&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;—&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学年）国家奖学金申请审批表
							</logic:empty>
							<logic:notEmpty name="rs" property="xn">
								（<bean:write name='rs' property="xn" />）国家奖学金申请审批表
							</logic:notEmpty>
							</strong>
						</h2>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<br />
					<br />
					<div align="left">
						<b>学校：&nbsp;
						<bean:write name='rs' property="xxmc" />
						&nbsp;&nbsp; 院系：&nbsp;
						<logic:empty name='rs' property="xymc">
							&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						<logic:notEmpty name='rs' property="xymc">
							<bean:write name='rs' property="xymc" />
						</logic:notEmpty>
						&nbsp;&nbsp; 学号：&nbsp;
						<logic:empty name='rs' property="xh">
							&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						<logic:notEmpty name='rs' property="xh">
							<bean:write name='rs' property="xh" />
						</logic:notEmpty>
						</b>
					</div>
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle" style="font-size: 50px">
						<tr>
							<td rowspan="4" width="12%" height="180px">
								<div align="center">
									<b>基本
									<br />
									情况</b>
								</div>
							</td>
							<td width="16%">
								<div align="center">
									姓名
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									性别
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									出生年月
								</div>
							</td>
							<td colspan="5">
								<div align="center">
									<bean:write name='rs' property="csrq" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									政治面貌
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="zzmmmc" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									民族
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="mzmc" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									入学时间
								</div>
							</td>
							<td colspan="5">
								<div align="center">
									<bean:write name='rs' property="rxrq" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									专业
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="zymc" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									学制
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="xz" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									联系电话
								</div>
							</td>
							<td colspan="5">
								<div align="center">
									<bean:write name='rs' property="lxdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									身份证号
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh1" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh2" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh3" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh4" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh5" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh6" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh7" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh8" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh9" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh10" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh11" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh12" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh13" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh14" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh15" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh16" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh17" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh18" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="2" height="80px">
								<div align="center">
									<b>学习
									<br />
									情况</b>
									<br clear=all style='page-break-before:always'> 
								</div>
							</td>
							<td colspan="9" align="center" height="40px">
								&nbsp;&nbsp;成绩排名：
								<u>&nbsp;&nbsp;
								<logic:empty name='rs' property="cjpm">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name='rs' property="cjpm">
									<bean:write name='rs' property="cjpm" />
								</logic:notEmpty></u>
								（名次/总人数）
							</td>
							<td colspan="10" align="center">
								实行综合考评排名：是□；否□
							</td>
						</tr>
						<tr>
							<td colspan="9" align="center" height="40px">
							    必修课<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>门，其中及格以上<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>门
							</td>
							<td colspan="10" align="center">
								如是，排名：<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>（名次/总人数）
						</tr>
						<tr >
							<td rowspan="5" height="150px">
								<div align="center" >
									<b>大学<br>
									期间<br>
									主要<br>
									获奖<br>
									情况</b>
								</div>
							</td>
							<td colspan="6" align="center" height="30px">
								日期
							</td>
							<td colspan="6" align="center">
								奖项名称
							</td>
							<td colspan="7" align="center">
								颁奖单位
							</td>
						</tr>
						<tr>
							<td colspan="6" align="center" height="30px">
								&nbsp;&nbsp;${rs.hjsj1 }
							</td>
							<td colspan="6" align="center">
								&nbsp;&nbsp;${rs.hjmc1 }
							</td>
							<td colspan="7" align="center">
								&nbsp;&nbsp;${rs.bjdw1 }
							</td>
						</tr>
						<tr>
							<td colspan="6" align="center" height="30px">
								&nbsp;&nbsp;${rs.hjsj2 }
							</td>
							<td colspan="6" align="center">
								&nbsp;&nbsp;${rs.hjmc2 }
							</td>
							<td colspan="7" align="center">
								&nbsp;&nbsp;${rs.bjdw2 }
							</td>
						</tr>
						<tr>
							<td colspan="6" align="center" height="30px">
								&nbsp;&nbsp;${rs.hjsj3 }
							</td>
							<td colspan="6" align="center">
								&nbsp;&nbsp;${rs.hjmc3 }
							</td>
							<td colspan="7" align="center">
								&nbsp;&nbsp;${rs.bjdw3 }
							</td>
						</tr>
						<tr>
							<td colspan="6" align="center" height="30px">
								&nbsp;&nbsp;${rs.hjsj4 }
							</td>
							<td colspan="6" align="center">
								&nbsp;&nbsp;${rs.hjmc4 }
							</td>
							<td colspan="7" align="center">
								&nbsp;&nbsp;${rs.bjdw4 }
							</td>
						</tr>
						<tr>
							<td  height="200px" valign="center">
								<div align="center">
									<b>申请<br>
									理由<br></b>
									(200字)
								</div>
							</td>
							<td colspan="19" valign="top">
								<p align="left" >
								<logic:empty name='rs' property="sqly">
									<br />
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name='rs' property="sqly">
									<br/>
									<br/>
									&nbsp;&nbsp;<bean:write name='rs' property="sqly" />
								</logic:notEmpty>
								</p>
								<br />
							
								<p>&nbsp;</p>
								<p>&nbsp;</p>
								<p>&nbsp;</p>
								<p>&nbsp;</p>
								<p>&nbsp;</p>
								<br/><br/><br/>
								<p align="right">
									申请人签名(手签)：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</p>
								<br />
								<p align="right">
									年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</p>
							</td>
						</tr>
						<tr height="190px">
							<td>
								<div align="center">
									<b>推荐<br>
									理由<br></b>
									(100字)
								</div>
							</td>
							<td colspan="19" align="center">
								<p align="left">
								<br />
								<br />
								<br />
								<logic:empty name='rs' property="fdyshyj">
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name='rs' property="fdyshyj">
									<br/>
									&nbsp;&nbsp;<bean:write name='rs' property="fdyshyj" />
								</logic:notEmpty>
								</p>
								<br /><br/>
								<p align="right">
									&nbsp;&nbsp;推荐人（辅导员或班主任）签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</p>
								<br />
								<p align="right">
									年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</p>
							</td>
						</tr>
						<tr height="190px">
							<td>
								<div align="center">
									<b>院
									<br />
									（系）
									<br />
									意
									<br />
									见</b>
								</div>
							</td>
							<td colspan="19" align="center">
								<p align="left">
								<br />
								<br />
								<br />
								<logic:empty name='rs' property="xyshyj">
									<br />
									<br />
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name='rs' property="xyshyj">
									<br/>
									&nbsp;&nbsp;<bean:write name='rs' property="xyshyj" />
								</logic:notEmpty>
								</p>
								<br />
								<div align="right">
									 院系主管学生工作领导签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br/>
								<div align="right">
									（院系公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr height="190px">
							<td>
								<div align="center">
									<b>学
									<br />
									校
									<br />
									意
									<br />
									见</b>
								</div>
							</td>
							<td colspan="19" align="center">
								<p align="left">
								<br/>
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<div align="center" valign="center">
									&nbsp;&nbsp;经评审，并在校内公示<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>个工作日，无异议，现报请批准该同学获得国家奖学金。
								</div>
								<br />
								<br />
								<br />
								</p>
								<p align="right">
									（学校公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>
									年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</p>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td align="right">制表：全国学生资助管理中心　2010年版</td>
			</tr>
		</table>
	</html:form>
	<div align="center">
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','')" />
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input  value="返回" onclick="back();" />
	</div>
</body>
</html:html>
