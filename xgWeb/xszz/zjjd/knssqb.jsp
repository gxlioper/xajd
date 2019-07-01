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
			document.forms[0].action = "/xgxt/zjjdzyjsxy_xszz.do?method=knssq";
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
								浙江机电职业技术<bean:message key="lable.xsgzyxpzxy" />困难生申请表
						</strong>
							</h2> 
					</div>
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;
					<br />
				</td>
			</tr>
			<tr>
				<td scope="col">
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
									<bean:write name="rs" property="xm" />
								</div>
							</td>
							<td scope="col" width="10%">
								<div align="center">
									性别
								</div>
							</td>
							<td scope="col" width="10%">
								<div align="center">
									<bean:write name="rs" property="xb" />
								</div>
							</td>
							<td scope="col" width="11%">
								<div align="center">
									出生年月
								</div>
							</td>
							<td colspan="2" scope="col">
								<div align="center">
									<bean:write name="rs" property="csrq" />
								</div>
							</td>
							<td scope="col" width="10%">
								<div align="center">
									民族
								</div>
							</td>
							<td scope="col" width="12%">
								<div align="center">
									<bean:write name="rs" property="mzmc" />
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
									<bean:write name="rs" property="sfzh" />
								</div>
							</td>
							<td>
								<div align="center">
									政治面貌
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="zzmmmc" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									学号
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="xh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="xymc" />
								</div>
							</td>
							<td>
								<div align="center">
									班级
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="bjmc" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									家庭
									<br />
									人口数
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtrks" />
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
									<logic:empty name="rs" property="xh">
										□&nbsp;是&nbsp;/&nbsp;□&nbsp;否
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<logic:equal name="rs" property="sfgc" value="是">
											√&nbsp;是&nbsp;/&nbsp;□&nbsp;否
										</logic:equal>
										<logic:equal name="rs" property="sfgc" value="否">
											□&nbsp;是&nbsp;/&nbsp;√&nbsp;否
										</logic:equal>
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
										□&nbsp;是&nbsp;/&nbsp;□&nbsp;否
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<logic:equal name="rs" property="sfdq" value="是">
											√&nbsp;是&nbsp;/&nbsp;□&nbsp;否
										</logic:equal>
										<logic:equal name="rs" property="sfdq" value="否">
											□&nbsp;是&nbsp;/&nbsp;√&nbsp;否
										</logic:equal>
									</logic:notEmpty>
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									烈士子女
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:empty name="rs" property="xh">
										□&nbsp;是&nbsp;/&nbsp;□&nbsp;否
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<logic:equal name="rs" property="sflszn" value="是">
											√&nbsp;是&nbsp;/&nbsp;□&nbsp;否
										</logic:equal>
										<logic:equal name="rs" property="sflszn" value="否">
											□&nbsp;是&nbsp;/&nbsp;√&nbsp;否
										</logic:equal>
									</logic:notEmpty>
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
								<bean:write name="rs" property="xxtxdz" />
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
									<bean:write name="rs" property="yzbm" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									联系电话
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name="rs" property="lxdh" />
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
									<bean:write name="rs" property="jtcy1_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name="rs" property="jtcy1_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy1_gx" />
								</div>
							</td>
							<td colspan="3">
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
									<bean:write name="rs" property="jtcy1_sr" />
								</div>
							</td>
							<td colspan="2">
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
									&nbsp;<bean:write name="rs" property="jtcy2_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy2_gx" />
								</div>
							</td>
							<td colspan="3">
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
									<bean:write name="rs" property="jtcy2_sr" />
								</div>
							</td>
							<td colspan="2">
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
									&nbsp;<bean:write name="rs" property="jtcy3_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy3_gx" />
								</div>
							</td>
							<td colspan="3">
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
									<bean:write name="rs" property="jtcy3_sr" />
								</div>
							</td>
							<td colspan="2">
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
									&nbsp;<bean:write name="rs" property="jtcy4_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy4_gx" />
								</div>
							</td>
							<td colspan="3">
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
									<bean:write name="rs" property="jtcy4_sr" />
								</div>
							</td>
							<td colspan="2">
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
									&nbsp;<bean:write name="rs" property="jtcy5_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy5_gx" />
								</div>
							</td>
							<td colspan="3">
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
									<bean:write name="rs" property="jtcy5_sr" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="jtcy5_jkzk" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="2">
								<div align="center">
									已获
									<br />
									资助
									<br />
									情况
								</div>
							</td>
							<td>
								<div align="center">
									国家助学<br />贷款
								</div>
							</td>
							<td>
								<div align="center">
									国家助学金
								</div>
							</td>
							<td>
								<div align="center">
									国家励志<br />奖学金
								</div>
							</td>
							<td>
								<div align="center">
									国家奖学金
								</div>
							</td>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" /><br />奖学金
								</div>
							</td>
							<td>
								<div align="center">
									勤工助学
								</div>
							</td>
							<td>
								<div align="center">
									学费减免
								</div>
							</td>
							<td>
								<div align="center">
									临时<br />补助
								</div>
							</td>
							<td>
								<div align="center">
									当地政府、<br />社会资助
								</div>
							</td>
							<td>
								<div align="center">
									合计
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name="rs" property="yhzzqk_gjzxdk" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="yhzzqk_gjzxj" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="yhzzqk_gjlzjxj" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="yhzzqk_gjjxj" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="yhzzqk_xyjxj" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="yhzzqk_qgzx" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="yhzzqk_xfjm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="yhzzqk_lxbz" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name="rs" property="yhzzqk_ddzfshjxj" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="yhzzqk_hj" />
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
								家庭遭受自然灾害情况：
								<logic:empty name="rs" property="jtzszrzhqk">
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:empty>
								<logic:notEmpty name="rs" property="jtzszrzhqk">
								<u>&nbsp;<bean:write name="rs" property="jtzszrzhqk" />&nbsp;</u>
								</logic:notEmpty>
								。家庭遭受突发意外事件：
								<logic:empty name="rs" property="jttfywsj">
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:empty>
								<logic:notEmpty name="rs" property="jttfywsj">
								<u>&nbsp;<bean:write name="rs" property="jttfywsj" />&nbsp;</u>
								</logic:notEmpty>
								。
								<br />
								家庭成员因残疾、年迈而劳动能力弱情况：
								<logic:empty name="rs" property="jtcyycjnmndlqk">
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:empty>
								<logic:notEmpty name="rs" property="jtcyycjnmndlqk">
								<u>&nbsp;<bean:write name="rs" property="jtcyycjnmndlqk" />&nbsp;</u>
								</logic:notEmpty>
								。
								<br />
								家庭成员失业情况：
								<logic:empty name="rs" property="jtcysyqk">
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:empty>
								<logic:notEmpty name="rs" property="jtcysyqk">
								<u>&nbsp;<bean:write name="rs" property="jtcysyqk" />&nbsp;</u>
								</logic:notEmpty>
								。家庭欠债情况：
								<logic:empty name="rs" property="jtqzqk">
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:empty>
								<logic:notEmpty name="rs" property="jtqzqk">
								<u>&nbsp;<bean:write name="rs" property="jtqzqk" />&nbsp;</u>
								</logic:notEmpty>
								。
								<br />
								其他情况：
								<logic:empty name="rs" property="qtqk">
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:empty>
								<logic:notEmpty name="rs" property="qtqk">
								<u>&nbsp;<bean:write name="rs" property="qtqk" />&nbsp;</u>
								</logic:notEmpty>
								。
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									家庭
									<br />
									详细
									<br />
									经济
									<br />
									情况
									<br />
									说明
								</div>
							</td>
							<td colspan="10">
								<logic:empty name="rs" property="jtjjqkxxsm">
								<br /><br /><br /><br /><br /><br />
								</logic:empty>
								<logic:notEmpty name="rs" property="jtjjqkxxsm">
								<br /><bean:write name="rs" property="jtjjqkxxsm" /><br />
								</logic:notEmpty>
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
								<bean:write name="rs" property="mzbm_xxtxdz" />
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
									<bean:write name="rs" property="mzbm_yzbm" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									联系电话
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="mzbm_lxdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									班
									<br />
									主
									<br />
									任
									<br />
									审
									<br />
									核
								</div>
							</td>
							<td colspan="10">
								<br />
								认定为：
								<logic:equal name="rs" property="fdysh" value="一般困难">
								√
								</logic:equal>
								<logic:notEqual name="rs" property="fdysh" value="一般困难">
								□
								</logic:notEqual>
								&nbsp;一般困难&nbsp;&nbsp;&nbsp;
								<logic:equal name="rs" property="fdysh" value="特殊困难">
								√
								</logic:equal>
								<logic:notEqual name="rs" property="fdysh" value="特殊困难">
								□
								</logic:notEqual>
								&nbsp;特殊困难&nbsp;&nbsp;&nbsp;
								<logic:equal name="rs" property="fdysh" value="困难">
								√
								</logic:equal>
								<logic:notEqual name="rs" property="fdysh" value="困难">
								□
								</logic:notEqual>
								&nbsp;困难&nbsp;&nbsp;&nbsp;
								<logic:equal name="rs" property="fdysh" value="不困难">
								√
								</logic:equal>
								<logic:notEqual name="rs" property="fdysh" value="不困难">
								□
								</logic:notEqual>
								&nbsp;不困难
								<br />
								<logic:empty name="rs" property="fdyshyj">
								<br />
								<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="fdyshyj">
									<bean:write name="rs" property="fdyshyj" />
								</logic:notEmpty>
								<br />
								<div align="right">
									签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									系
									<br />
									审
									<br />
									核
								</div>
							</td>
							<td colspan="10">
								<br />
								认定为：
								<logic:equal name="rs" property="xysh" value="一般困难">
								√
								</logic:equal>
								<logic:notEqual name="rs" property="xysh" value="一般困难">
								□
								</logic:notEqual>
								&nbsp;一般困难&nbsp;&nbsp;&nbsp;
								<logic:equal name="rs" property="xysh" value="特殊困难">
								√
								</logic:equal>
								<logic:notEqual name="rs" property="xysh" value="特殊困难">
								□
								</logic:notEqual>
								&nbsp;特殊困难&nbsp;&nbsp;&nbsp;
								<logic:equal name="rs" property="xysh" value="困难">
								√
								</logic:equal>
								<logic:notEqual name="rs" property="xysh" value="困难">
								□
								</logic:notEqual>
								&nbsp;困难&nbsp;&nbsp;&nbsp;
								<logic:equal name="rs" property="xysh" value="不困难">
								√
								</logic:equal>
								<logic:notEqual name="rs" property="xysh" value="不困难">
								□
								</logic:notEqual>
								&nbsp;不困难
								<br />
								<logic:empty name="rs" property="xyshyj">
								<br />
								<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="xyshyj">
									<bean:write name="rs" property="xyshyj" />
								</logic:notEmpty>
								<br />
								<div align="right">
									签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									学
									<br />
									院
									<br />
									审
									<br />
									核
								</div>
							</td>
							<td colspan="10">
								<br />
								认定为：
								<logic:equal name="rs" property="xxsh" value="一般困难">
								√
								</logic:equal>
								<logic:notEqual name="rs" property="xxsh" value="一般困难">
								□
								</logic:notEqual>
								&nbsp;一般困难&nbsp;&nbsp;&nbsp;
								<logic:equal name="rs" property="xxsh" value="特殊困难">
								√
								</logic:equal>
								<logic:notEqual name="rs" property="xxsh" value="特殊困难">
								□
								</logic:notEqual>
								&nbsp;特殊困难&nbsp;&nbsp;&nbsp;
								<logic:equal name="rs" property="xxsh" value="困难">
								√
								</logic:equal>
								<logic:notEqual name="rs" property="xxsh" value="困难">
								□
								</logic:notEqual>
								&nbsp;困难&nbsp;&nbsp;&nbsp;
								<logic:equal name="rs" property="xxsh" value="不困难">
								√
								</logic:equal>
								<logic:notEqual name="rs" property="xxsh" value="不困难">
								□
								</logic:notEqual>
								&nbsp;不困难
								<br />
								<logic:empty name="rs" property="xxshyj">
								<br />
								<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="xxshyj">
									<bean:write name="rs" property="xxshyj" />
								</logic:notEmpty>
								<br />
								<div align="right">
									(盖章)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;
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
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input  value="返回" onclick="back();" />
	</div>
</body>
</html:html>
