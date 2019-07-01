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
	<html:form action="/zgdzdx_xszz" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
						<h3>
							<strong>中国银行股份有限公司国家助学贷款还款协议</strong>
						</h3>
					</div>
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;借款人(甲方)：
					<logic:empty name="rs" property="xm">
					__________________________________；
					</logic:empty>
					<logic:notEmpty name="rs" property="xm">
					<u>&nbsp;&nbsp;<bean:write name="rs" property="xm" />&nbsp;&nbsp;<bean:write name="rs" property="bjmc" />&nbsp;&nbsp;<bean:write name="rs" property="xh" />&nbsp;&nbsp;&nbsp;&nbsp;</u>
					</logic:notEmpty>
					&nbsp;贷款帐号：
					<logic:empty name="rs" property="khh">
					__________________
					</logic:empty>
					<logic:notEmpty name="rs" property="khh">
					<u>&nbsp;&nbsp;<bean:write name="rs" property="khh" />&nbsp;&nbsp;&nbsp;&nbsp;</u>
					</logic:notEmpty>
					<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;有效证件号码：
					<logic:empty name="rs" property="sfzh">
					_________________________________________________________
					</logic:empty>
					<logic:notEmpty name="rs" property="sfzh">
					<u>&nbsp;&nbsp;<bean:write name="rs" property="sfzh" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
					</logic:notEmpty>
					<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;家庭住址及邮政编码：
					<logic:empty name="rs" property="jtxxzz">
					____________________________________________________
					</logic:empty>
					<logic:notEmpty name="rs" property="jtxxzz">
					<u>&nbsp;&nbsp;<bean:write name="rs" property="jtxxzz" />&nbsp;&nbsp;<bean:write name="rs" property="jtyb" />&nbsp;&nbsp;</u>
					</logic:notEmpty>
					<br /><br />
					<logic:notEmpty name="rs" property="fqxm">
						&nbsp;&nbsp;&nbsp;&nbsp;家庭有效联系人姓名：
						<u>&nbsp;&nbsp;<bean:write name="rs" property="fqxm" />&nbsp;&nbsp;&nbsp;&nbsp;</u>
						&nbsp;&nbsp;与借款人关系：
						<u>&nbsp;&nbsp;父亲&nbsp;&nbsp;&nbsp;&nbsp;</u>
					</logic:notEmpty>
					<logic:empty name="rs" property="fqxm">
						<logic:notEmpty name="rs" property="mqxm">
							&nbsp;&nbsp;&nbsp;&nbsp;家庭有效联系人姓名：
							<u>&nbsp;&nbsp;<bean:write name="rs" property="mqxm" />&nbsp;&nbsp;&nbsp;&nbsp;</u>
							&nbsp;&nbsp;与借款人关系：
							<u>&nbsp;&nbsp;母亲&nbsp;&nbsp;&nbsp;&nbsp;</u>
						</logic:notEmpty>
						<logic:empty name="rs" property="mqxm">
							&nbsp;&nbsp;&nbsp;&nbsp;家庭有效联系人姓名：
							_______________________
							&nbsp;&nbsp;与借款人关系：
							________________
						</logic:empty>
					</logic:empty>
					<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;联系电话：
					<logic:empty name="rs" property="jtdh">
					____________________________________________________
					</logic:empty>
					<logic:notEmpty name="rs" property="jtdh">
					<u>&nbsp;&nbsp;<bean:write name="rs" property="jtdh" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
					</logic:notEmpty>
					<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;工作单位：
					<logic:empty name="rs" property="gzdw">
					_____________________________________________________________
					</logic:empty>
					<logic:notEmpty name="rs" property="gzdw">
					<u>&nbsp;&nbsp;<bean:write name="rs" property="gzdw" />&nbsp;&nbsp;<bean:write name="rs" property="gzdz" /></u>
					</logic:notEmpty>
					<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;邮政编码：
					<logic:empty name="rs" property="dwyb">
					__________________________
					</logic:empty>
					<logic:notEmpty name="rs" property="dwyb">
					<u>&nbsp;&nbsp;<bean:write name="rs" property="dwyb" />&nbsp;&nbsp;</u>
					</logic:notEmpty>
					联系电话：
					<logic:empty name="rs" property="dwdh">
						<logic:empty name="rs" property="yddh">
							_________________________
						</logic:empty>
						<logic:notEmpty name="rs" property="yddh">
							<u>&nbsp;&nbsp;<bean:write name="rs" property="yddh" />&nbsp;&nbsp;</u>
						</logic:notEmpty>
					</logic:empty>
					<logic:notEmpty name="rs" property="dwdh">
						<logic:empty name="rs" property="yddh">
							<u>&nbsp;&nbsp;<bean:write name="rs" property="dwdh" />&nbsp;&nbsp;</u>
						</logic:empty>
						<logic:notEmpty name="rs" property="yddh">
							<u>&nbsp;&nbsp;<bean:write name="rs" property="dwdh" />&nbsp;&nbsp;<bean:write name="rs" property="yddh" />&nbsp;&nbsp;</u>
						</logic:notEmpty>
					</logic:notEmpty>
					<br />
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;借款人(乙方)：
					<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;中国银行股份有限公司武汉东湖开发区支行&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
					<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;地址：
					<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;武汉市洪山区卓刀泉南路39号&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
					<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;邮政编码及邮箱：
					<u>&nbsp;&nbsp;430079&nbsp;&nbsp;&nbsp;&nbsp;donghuboc@sina.com&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
					<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;联系电话：
					<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;027-87521649、87521897&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
					<br />
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;本协议为甲方和乙方签订的《中国银行股份有限公司国家助学贷款借款合同》（合同编号：
					<logic:empty name="rs" property="htbh">
					7245-___________
					</logic:empty>
					<logic:notEmpty name="rs" property="htbh">
					<u>&nbsp;<bean:write name="rs" property="htbh" />&nbsp;</u>
					</logic:notEmpty>
					号）约定的从属协议，用以明确甲方和乙方归还国家助学贷款计划。经甲方、乙方协商同意后，订立如下还款协议：
					<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;一、截止
					<u>
					<logic:empty name="rs" property="bynf">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:empty>
					<logic:notEmpty name="rs" property="bynf">
					<bean:write name="rs" property="bynf" />
					</logic:notEmpty>
					</u>年
					<u>&nbsp;${rs.byny_mon }&nbsp;</u>月
					<u>&nbsp;${rs.byny_day }&nbsp;</u>日，甲方从乙方获得国家助学贷款共计人民币（大写）
					<logic:equal name="rs" property="sjffje" value="0">
					__________________
					</logic:equal>
					<logic:notEqual name="rs" property="sjffje" value="0">
					<u>&nbsp;<bean:write name="rs" property="sjffje_dx" />&nbsp;</u>
					</logic:notEqual>
					元；
					<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;二、甲方于
					<u>
					<logic:empty name="rs" property="bynf">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:empty>
					<logic:notEmpty name="rs" property="bynf">
					<bean:write name="rs" property="bynf" />
					</logic:notEmpty>
					</u>年
					<u>&nbsp;${rs.byny_mon }&nbsp;</u>月
					<u>&nbsp;${rs.byny_day }&nbsp;</u>日因
					<u>&nbsp;毕业&nbsp;</u>原因，正式离开
					<u>中国地质大学（武汉）</u>（所在学校）。
					<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;三、甲方采用以下第
					<u>&nbsp;（二）&nbsp;</u>方式按
					<u>&nbsp;月&nbsp;</u>（月/季）分
					<u>&nbsp;/&nbsp;</u>期归还贷款本金及利息，从
					
					<u>
						<logic:empty name="rs" property="bynf">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						
						<logic:notEmpty name="rs" property="bynf">
							${rs.bynf }
						</logic:notEmpty>
					</u>年
					<u>&nbsp;${rs.bynyn_mon }&nbsp;</u>月
					<u>&nbsp;${rs.bynyn_day }&nbsp;</u>日
					
					至
					<logic:empty name="rs" property="dkjssj_year">
						____
					</logic:empty>
					<logic:notEmpty name="rs" property="dkjssj_year">
						<u>${rs.dkjssj_year }</u>
					</logic:notEmpty>
					年
					<logic:empty name="rs" property="dkjssj_mon">
						____
					</logic:empty>
					<logic:notEmpty name="rs" property="dkjssj_mon">
						<u>&nbsp;${rs.dkjssj_mon }&nbsp;</u>
					</logic:notEmpty>
					月
					<u>&nbsp;${rs.dkjssj_day }&nbsp;</u>日
					共
					<logic:empty name="rs" property="month1">
						____
					</logic:empty>
					<logic:notEmpty name="rs" property="month1">
						<u>&nbsp;${rs.month1 }&nbsp;</u>
					</logic:notEmpty>
					月归还贷款利息；
					从
					<logic:empty name="rs" property="rq2_year">
						____
					</logic:empty>
					<logic:notEmpty name="rs" property="rq2_year">
						<u>${rs.rq2_year }</u>
					</logic:notEmpty>
					年
					<u>&nbsp;${rs.bynyn_mon }&nbsp;</u>月
					<u>&nbsp;${rs.bynyn_day }&nbsp;</u>日至
					<logic:empty name="rs" property="dkjssj_year">
						____
					</logic:empty>
					<logic:notEmpty name="rs" property="dkjssj_year">
						<u>${rs.dkjssj_year }</u>
					</logic:notEmpty>
					年
					<logic:empty name="rs" property="dkjssj_mon">
						____
					</logic:empty>
					<logic:notEmpty name="rs" property="dkjssj_mon">
						<u>&nbsp;${rs.dkjssj_mon }&nbsp;</u>
					</logic:notEmpty>
					月
					<u>&nbsp;${rs.dkjssj_day }&nbsp;</u>日共
					<logic:empty name="rs" property="month2">
					____
						</logic:empty>
					<logic:notEmpty name="rs" property="month2">
					<u>&nbsp;${rs.month2 }&nbsp;</u>
					</logic:notEmpty>
					月归还贷款本金及利息：
					<br />
					<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（一）等额本息还款法
					<br />
					<br /><br />
					<div align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;还款月数
						<br />
						月利率*（1+月利率）&nbsp;&nbsp;&nbsp;&nbsp;
						<br />
						每期还款额&nbsp;=&nbsp;————————————————&nbsp;*&nbsp;借款本金
						<br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;还款月数
						<br />
						（1+月利率）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;1
						<br />
						<br />
						每月贷款利息额=贷款本金余额*年利率/360*每月天数
						<br />
						<br />
					</div>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（二）等额本金还款法
					<br />
					<br /><br />
					<div align="center">
						借款本金&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<br />
						每期还款额&nbsp;=&nbsp;——————&nbsp;+&nbsp;（借款本金&nbsp;-&nbsp;累计已归还本金额）&nbsp;*&nbsp;月利率
						<br />
						还款期数&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<br />
						<br />
						每月贷款利息额=贷款本金余额*年利率/360*每月天数
						<br /><br />
					</div>
					&nbsp;&nbsp;&nbsp;&nbsp;四、经双方确认的借款，在本还款协议履行期间，按贷款期限执行中国人民银行同档次法定利率。如果法定利率调整，乙方将执行调整后的利率，无须另行通知甲方；
					<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;五、甲方授权乙方直接从甲方在乙方开立的帐户中扣款，用于归还借款本息，帐户户名为：
					<logic:empty name="rs" property="xm">
					___________________
					</logic:empty>
					<logic:notEmpty name="rs" property="xm">
					<u>&nbsp;<bean:write name="rs" property="xm" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
					</logic:notEmpty>
					，帐户号为：
					<logic:empty name="rs" property="hkczh">
					_____________________________
					</logic:empty>
					<logic:notEmpty name="rs" property="hkczh">
					<u>&nbsp;<bean:write name="rs" property="hkczh" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
					</logic:notEmpty>
					；
					<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;六、甲方承诺在离校手续办妥后一个月内将《中国银行武汉东湖开发区（分）支行国家助学贷款毕业生资料确认书》寄送回乙方；如甲方工作单位、联系方式有变动，必须及时通知乙方；
					<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;七、本协议所有条款甲方已经与乙方进行了充分的协商；
					<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;八、本协议作为《中国银行股份有限公司国家助学贷款借款合同》的组成部分，与《中国银行股份有限公司国家助学贷款借款合同》具有同等法律效力。
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
					&nbsp;&nbsp;甲方：（签字或盖章）
					<br />
					<br />
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
					<br />
					<br />
					<br />
					<br />
					<br />
					&nbsp;&nbsp;乙方：（公章）
					<br />
					<br />
					&nbsp;&nbsp;授权签字人：（签字）
					<br />
					<br />
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
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
