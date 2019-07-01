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
								中国建设银行国家助学贷款申请表
						</strong>
							</h3> 
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td colspan="2" scope="col" width="4%">
								<div align="center">
									申请人姓名
								</div>
							</td>
							<td colspan="4" scope="col">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td colspan="3" scope="col">
								<div align="center">
									性别
								</div>
							</td>
							<td colspan="3" scope="col">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td colspan="3" scope="col">
								<div align="center">
									出生日期
								</div>
							</td>
							<td colspan="5" scope="col">
								<div align="center">
									<logic:empty name="rs" property="csrq">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
									</logic:empty>
									<logic:notEmpty name="rs" property="csrq">
										<bean:write name='rs' property="csrq" />
									</logic:notEmpty>
								</div>
							</td>
							<td width="12%" rowspan="5" scope="col">
								<div align="center">
									照片
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
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
							<td colspan="2" scope="row">
								<div align="center">
									学生证号码
								</div>
							</td>
							<td colspan="10">
								<div align="center">
									<bean:write name='rs' property="xh" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									民族
								</div>
							</td>
							<td colspan="5">
								<div align="center">
									<bean:write name='rs' property="mzmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									籍贯
								</div>
							</td>
							<td colspan="5">
								<div align="center">
									<bean:write name='rs' property="jg" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									户籍所在地
								</div>
							</td>
							<td colspan="9">
								<div align="center">
									<bean:write name='rs' property="hjszd" />
								</div>
							</td>
						</tr>
						<tr>
							<td width="4%" rowspan="8" scope="row">
								<div align="center">
									基本情况
								</div>
							</td>
							<td width="12%">
								<div align="center">
									健康状况
								</div>
							</td>
							<td colspan="5">
								<div align="center">
									<logic:equal name="jkzk" value="良好">
    								√
    								</logic:equal>
									<logic:notEqual name="jkzk" value="良好">
    								□
    								</logic:notEqual>
									良好&nbsp;
									<logic:equal name="jkzk" value="一般">
    								√
    								</logic:equal>
									<logic:notEqual name="jkzk" value="一般">
    								□
    								</logic:notEqual>
									一般&nbsp;
									<logic:equal name="jkzk" value="差">
    								√
    								</logic:equal>
									<logic:notEqual name="jkzk" value="差">
    								□
    								</logic:notEqual>
									差
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									婚姻状况
								</div>
							</td>
							<td colspan="9">
								<div align="center">
									<logic:equal name="hyzk" value="未婚">
    								√
    								</logic:equal>
									<logic:notEqual name="hyzk" value="未婚">
    								□
    								</logic:notEqual>
									未婚&nbsp;
									<logic:equal name="hyzk" value="已婚无子女">
    								√
    								</logic:equal>
									<logic:notEqual name="hyzk" value="已婚无子女">
    								□
    								</logic:notEqual>
									已婚无子女&nbsp;
									<logic:equal name="hyzk" value="已婚有子女">
    								√
    								</logic:equal>
									<logic:notEqual name="hyzk" value="已婚有子女">
    								□
    								</logic:notEqual>
									已婚有子女
									<br />
									<logic:equal name="hyzk" value="丧偶">
    								√
    								</logic:equal>
									<logic:notEqual name="hyzk" value="丧偶">
    								□
    								</logic:notEqual>
									丧偶&nbsp;
									<logic:equal name="hyzk" value="离婚">
    								√
    								</logic:equal>
									<logic:notEqual name="hyzk" value="离婚">
    								□
    								</logic:notEqual>
									离婚
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									最高学历
								</div>
							</td>
							<td colspan="19">
								<div align="left">
									<logic:equal name="zgxl" value="大学本科">
    								√
    								</logic:equal>
									<logic:notEqual name="zgxl" value="大学本科">
    								□
    								</logic:notEqual>
									大学本科&nbsp;
									<logic:equal name="zgxl" value="大专和专科学校">
    								√
    								</logic:equal>
									<logic:notEqual name="zgxl" value="大专和专科学校">
    								□
    								</logic:notEqual>
									大专和专科学校&nbsp;
									<logic:equal name="zgxl" value="中专学校">
    								√
    								</logic:equal>
									<logic:notEqual name="zgxl" value="中专学校">
    								□
    								</logic:notEqual>
									中专学校
									<br />
									<logic:equal name="zgxl" value="高中">
    								√
    								</logic:equal>
									<logic:notEqual name="zgxl" value="高中">
    								□
    								</logic:notEqual>
									高中&nbsp;
									<logic:equal name="zgxl" value="技工学校">
    								√
    								</logic:equal>
									<logic:notEqual name="zgxl" value="技工学校">
    								□
    								</logic:notEqual>
									技工学校&nbsp;
									<logic:equal name="zgxl" value="初中">
    								√
    								</logic:equal>
									<logic:notEqual name="zgxl" value="初中">
    								□
    								</logic:notEqual>
									初中&nbsp;
									<logic:equal name="zgxl" value="小学">
    								√
    								</logic:equal>
									<logic:notEqual name="zgxl" value="小学">
    								□
    								</logic:notEqual>
									小学&nbsp;
									<logic:equal name="zgxl" value="文盲或半文盲">
    								√
    								</logic:equal>
									<logic:notEqual name="zgxl" value="文盲或半文盲">
    								□
    								</logic:notEqual>
									文盲或半文盲
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家庭电话
								</div>
							</td>
							<td colspan="9">
								<div align="center">
									<bean:write name='rs' property="jtdh" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									移动电话
								</div>
							</td>
							<td colspan="6">
								<div align="center">
									<bean:write name='rs' property="yddh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家庭地址
								</div>
							</td>
							<td colspan="9">
								<div align="center">
									<bean:write name='rs' property="jtdz" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									邮政编码
								</div>
							</td>
							<td colspan="6">
								<div align="center">
									<bean:write name='rs' property="yzbm" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									E-Mail地址
								</div>
							</td>
							<td colspan="19">
								<div align="left">
									<bean:write name='rs' property="e_mail" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									入学时间
								</div>
							</td>
							<td colspan="9">
								<div align="center">
									<logic:empty name="rs" property="rxny">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月
									</logic:empty>
									<logic:notEmpty name="rs" property="rxny">
										<bean:write name='rs' property="rxny" />
									</logic:notEmpty>
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									毕业年月
								</div>
							</td>
							<td colspan="6">
								<div align="center">
									<logic:empty name="rs" property="byny">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月
									</logic:empty>
									<logic:notEmpty name="rs" property="byny">
										<bean:write name='rs' property="byny" />
									</logic:notEmpty>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									学制
								</div>
							</td>
							<td colspan="9">
								<div align="center">
									<bean:write name='rs' property="xz" />&nbsp;&nbsp;年
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									就读学生<br />所在年级
								</div>
							</td>
							<td colspan="6">
								<div align="center">
									<bean:write name='rs' property="nj" />&nbsp;&nbsp;年级
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									所在<bean:message key="lable.xsgzyxpzxy" />
								</div>
							</td>
							<td colspan="6">
								<div align="center">
									<bean:write name='rs' property="xymc" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									专业及班级
								</div>
							</td>
							<td colspan="9">
								<div align="center">
									<bean:write name='rs' property="zymc" />&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='rs' property="bjmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="4" scope="row">
								<div align="center">
									见证人情况
								</div>
							</td>
							<td>
								<div align="center">
									姓名
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jzr1_xm" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									性别
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jzr1_xb" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									证件类型
									<br />
									及号码
								</div>
							</td>
							<td colspan="6">
								<bean:write name='rs' property="jzr1_zjlxjhm" />
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									与借款人关系
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jzr1_yjkrgx" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									联系
									<br />
									电话
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jzr1_lxdh" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									通讯地址
								</div>
							</td>
							<td colspan="6">
								<bean:write name='rs' property="jzr1_txdz" />
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									姓名
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jzr2_xm" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									性别
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jzr2_xb" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									证件类型
									<br />
									及号码
								</div>
							</td>
							<td colspan="6">
								<bean:write name='rs' property="jzr2_zjlxjhm" />
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									与借款人关系
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jzr2_yjkrgx" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									联系
									<br />
									电话
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jzr2_lxdh" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									通讯地址
								</div>
							</td>
							<td colspan="6">
								<bean:write name='rs' property="jzr2_txdz" />
							</td>
						</tr>
						<tr>
							<td rowspan="3" scope="row">
								<div align="center">
									申请借款情况
								</div>
							</td>
							<td>
								<div align="center">
									借款总金额
									<br />
									(小写)
								</div>
							</td>
							<td colspan="19">
								<div align="center">
									<bean:write name='rs' property="jkzje" />
									&nbsp;元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;贷款期限&nbsp;&nbsp;
									<bean:write name='rs' property="dkqx" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									还款方式
								</div>
							</td>
							<td colspan="19">
								<logic:equal name="hkfs" value="等额本息还款法">
    							√
    							</logic:equal>
								<logic:notEqual name="hkfs" value="等额本息还款法">
    							□
    							</logic:notEqual>
								等额本息还款法
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<logic:equal name="hkfs" value="等额本金还款法">
    							√
    							</logic:equal>
								<logic:notEqual name="hkfs" value="等额本金还款法">
    							□
    							</logic:notEqual>
								等额本金还款法
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									贷款种类
								</div>
							</td>
							<td colspan="19">
								<logic:equal name="dkzl" value="中央财政贴息国家助学贷款">
    							√
    							</logic:equal>
								<logic:notEqual name="dkzl" value="中央财政贴息国家助学贷款">
    							□
    							</logic:notEqual>
								中央财政贴息国家助学贷款&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<logic:equal name="dkzl" value="地方财政贴息国家助学贷款">
    							√
    							</logic:equal>
								<logic:notEqual name="dkzl" value="地方财政贴息国家助学贷款">
    							□
    							</logic:notEqual>
								地方财政贴息国家助学贷款
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									学费单价(按年)
								</div>
							</td>
							<td colspan="8">
								<bean:write name='rs' property="xfdj" />
								&nbsp;元
							</td>
							<td colspan="4">
								<div align="center">
									住宿费单价(按年)
								</div>
							</td>
							<td colspan="7">
								<bean:write name='rs' property="zsfdj" />
								&nbsp;元
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									生活费单价
									<br />
									(按
									<logic:equal name="shffs" value="月">
    								√
    								</logic:equal>
									<logic:notEqual name="shffs" value="月">
    								□
    								</logic:notEqual>
									月/
									<logic:equal name="shffs" value="季">
    								√
    								</logic:equal>
									<logic:notEqual name="shffs" value="季">
    								□
    								</logic:notEqual>
									季)
								</div>
							</td>
							<td colspan="6">
								<bean:write name='rs' property="shfdj" />
								&nbsp;元
							</td>
							<td colspan="4">
								<div align="center">
									收髋人帐户
									<br />
									类型及账号
								</div>
							</td>
							<td colspan="9">
								<bean:write name='rs' property="skrzhlxjzh" />
							</td>
						</tr>
						<tr>
							<td colspan="21" scope="row">
								借款申请人声明：
								<br />
								&nbsp;&nbsp;&nbsp;&nbsp;1、以上申请书及其所附资料所填内容为本人所填，且完全属实。本人承担因填写不实所引致的一切法律责任；
								<br />
								&nbsp;&nbsp;&nbsp;&nbsp;2、本人承认以此申请书作为向贵行借款的依据。报送的资料复印件可保存贵行作备查凭证；
								<br />
								&nbsp;&nbsp;&nbsp;&nbsp;3、经贵行审查不符合规定的借款条件而未予受理时，本人无异议；
								<br />
								&nbsp;&nbsp;&nbsp;&nbsp;4、本人保证在取得银行贷款后，按时足额偿还贷款本息。
								<br />
								<br />
								<div align="center">
									申请人签字：
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									法定监护人签字（如果存在）：
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									见证人签字：
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</div>
								<div align="center">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>年
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>月
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>日
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>年
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>月
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>日
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>年
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>月
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>日
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
