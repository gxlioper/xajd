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

	</script>
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
						<h2>
						<strong>中国银行股份有限公司国家助学贷款展期协议</strong>
						</h2>
					</div>
					<br />
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;借款人(以下称甲方)：
					<logic:empty name="rs" property="xm">
					_________________________________________
					</logic:empty>
					<logic:notEmpty name="rs" property="xm">
						<u>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="xm" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
					</logic:notEmpty>
					(借款学生)
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;贷款人(以下称乙方)：
					<u>中国银行股份有限公司武汉东湖开发区支行&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>(经办银行)
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;介绍人(以下称丙方)：
					<u>中国地质大学(武汉)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>(学校机构)
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;甲方经丙方申请国家助学贷款，甲、乙、丙各方于
					<logic:empty name="rs" property="dkqx1_year">
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
					</logic:empty>
					<logic:notEmpty name="rs" property="dkqx1_year">
						<u>&nbsp;<bean:write name="rs" property="dkqx1_year" />&nbsp;</u>
					</logic:notEmpty>
					年
					<logic:empty name="rs" property="dkqx1_mon">
						<u>&nbsp;&nbsp;&nbsp;</u>
					</logic:empty>
					<logic:notEmpty name="rs" property="dkqx1_mon">
						<u>&nbsp;<bean:write name="rs" property="dkqx1_mon" />&nbsp;</u>
					</logic:notEmpty>
					月
					<logic:empty name="rs" property="dkqx1_day">
						<u>&nbsp;&nbsp;&nbsp;</u>
					</logic:empty>
					<logic:notEmpty name="rs" property="dkqx1_day">
						<u>&nbsp;<bean:write name="rs" property="dkqx1_day" />&nbsp;</u>
					</logic:notEmpty>
					日签订《中国银行股份有限公司国家助学贷款借款合同》，合同号为：
					<u>&nbsp;<bean:write name="rs" property="htbh" />&nbsp;</u>
					号，贷款金额为(大写)
					<u>&nbsp;<bean:write name="rs" property="dkye_dx" />&nbsp;</u>
					元(小写
					<u>&nbsp;<bean:write name="rs" property="dkye" />&nbsp;</u>
					元)，期限为
					<u>&nbsp;<bean:write name="rs" property="dkqxy" />&nbsp;</u>
					个月。依据合同约定，该笔贷款应于
					<u>
					<logic:empty name="rs" property="byny_year">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:empty>
					<logic:notEmpty name="rs" property="byny_year">
					&nbsp;<bean:write name="rs" property="byny_year" />&nbsp;
					</logic:notEmpty>
					</u>年
					<u>&nbsp;${rs.bynyn_mon }&nbsp;</u>月
					<u>&nbsp;${rs.bynyn_day }&nbsp;</u>日进入还款期，现因
					<u>&nbsp;继续攻读学位&nbsp;</u>(继续攻读学位、保留学籍参军、支援西部建设、其他原因)，甲方经丙方同意向乙方提出贷款展期申请，乙方经审查同意为甲方办理展期贷款。为维护各方利益，明确责任，格守信用，根据国家法律、法规、国家助学贷款政策和乙方或其上级机构与全国学生贷款管理中心签订的《国家助学贷款业务合作协议书》，经协商一致，达成如下各项：
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一、甲方根据
					<u>&nbsp;<bean:write name="rs" property="htbh" />&nbsp;</u>
					号合同向乙方借用人民币贷款（大写）
					<u>&nbsp;<bean:write name="rs" property="sqdkje_dx" />&nbsp;</u>
					元整，共分
					<u>&nbsp;<bean:write name="rs" property="gfcs" />&nbsp;</u>
					次发放。截止
					<u>&nbsp;<bean:write name="rs" property="byny_year" />&nbsp;</u>年
					<u>&nbsp;${rs.byny_mon }&nbsp;</u>月
					<u>&nbsp;${rs.byny_day }&nbsp;</u>日，已发放
					<u>&nbsp;<bean:write name="rs" property="yfcs" />&nbsp;</u>
					次，上述贷款的余额（未偿还金额）为（大写）
					<u>&nbsp;<bean:write name="rs" property="dkye_dx" />&nbsp;</u>
					元整，现约定其中的
					<u>&nbsp;<bean:write name="rs" property="dkye" />&nbsp;</u> 元展期到
					<u>&nbsp;<bean:write name="rs" property="zqhbyny_year" />&nbsp;</u> 年
					<u>&nbsp;<bean:write name="rs" property="zqhbyny_mon" />&nbsp;</u> 月
					<u>&nbsp;${rs.byny_day }&nbsp;</u> 日。
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;因保留学籍参军、支援西部建设等办理贷款展期，甲方约定于
					<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>年
					<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>月
					<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>日之前恢复学业，丙方应及时通知乙方，乙方方可根据
					<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>号合同为甲方办理续放手续。
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;二、展期后的利率从展期之日起按________%执行。
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;三、展期后甲、乙、丙方的其它权利、义务、声明及承诺以及有关事项，仍按
					<u>&nbsp;<bean:write name="rs" property="htbh" />&nbsp;</u>号借款合同约定的条款执行。
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;四、甲、乙、丙各方商定的其它事项：
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1、甲方应向乙方提供甲方保证真实无误的联系方式、联系地址、联系电话等，并应于前述有关资料发生变动后即时以挂号信、邮件（邮件发出是应要求乙方签收回执）等形式书面或网络通知乙方（乙方接受后将与甲方确认信息已收，否则通知无效），否则乙方有权终止本协议并宣布贷款提前到期；
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、甲方应主动保持与乙方的经常联系（联系方式可为：挂号信、邮件（邮件发出应要求乙方签收回执）等形式，且该联系方式应具有可证明性，乙方对甲方定期反馈的信息进行登记），若甲方在六个月内不与乙方主动联系，则乙方有权终止本协议，并宣布贷款提前到期。
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3、甲方在
					<u>&nbsp;<bean:write name="rs" property="htbh" />&nbsp;</u>号《中国银行股份有限公司国家助学贷款借款合同》项下贷款展期期间的贴息，按丙方所在学校对该笔贷款继续承担《国家助学贷款业务合作协议书》规定的相关责任和义务。
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4、甲方因保留学籍参军、支援西部建设办理展期，应在协议约定时间内恢复学业，否则乙方有权终止本协议，停止发放尚未使用的贷款并宣布贷款提前到期。
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;五、本协议自双方签字并加盖公章（或按手印）之日起生效。
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;六、本协议构成
					<u>&nbsp;<bean:write name="rs" property="htbh" />&nbsp;</u>号《中国银行股份有限公司国家助学贷款借款合同》的组成部分，与之具有同等的法律效力。
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;七、本协议一式三份，甲、乙、丙三方各执一份，均具同等效力。
					<br />
					<br />
					<br />
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;甲方：
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（签字捺印）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
					<br />
					<br />
					<br />
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;乙方：
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;法定代表人（或授权代理人）：签章
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;公章
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
					<br />
					<br />
					<br />
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;丙方：签章
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;公章
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签定合同地点：中国地质大学（武汉）
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
