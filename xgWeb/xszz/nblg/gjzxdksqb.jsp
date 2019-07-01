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
<script language="javascript">
		function back(){
			document.forms[0].action = "/xgxt/nblg_xszz.do?method=gjzxdksq";
			document.forms[0].submit();
		}
	</script>
<body>
	<html:form action="nblg_xszz.do?method=gjzxdksqb" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
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
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="12%">
								<div align="center">
									申请人姓名
								</div>
							</td>
							<td width="22%">
								<div align="center">
									<bean:write name="rs" property="xm" />
								</div>
							</td>
							<td width="12%">
								<div align="center">
									性别
								</div>
							</td>
							<td width="12%">
								<div align="center">
									<bean:write name="rs" property="xb" />
								</div>
							</td>
							<td width="12%">
								<div align="center">
									出生日期
								</div>
							</td>
							<td width="30%">
								<div align="center">
									<bean:write name="rs" property="csny" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									身份证号码
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="sfzh" />
								</div>
							</td>
							<td>
								<div align="center">
									学生证号码
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
									籍贯
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jg" />
								</div>
							</td>
							<td>
								<div align="center">
									户籍所在地
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="hjszd" />
								</div>
							</td>
						</tr>
					</table>
					<table width="100%" class="tbstyle">
						<tr>
							<td rowspan="6" width="6%">
								<div align="center">
									基本
									<br />
									情况
								</div>
							</td>
							<td width="18%">
								<div align="center">
									健康状况
								</div>
							</td>
							<td colspan="5">
								&nbsp;
								<logic:empty name="rs" property="xh">
								□&nbsp;&nbsp;良好&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;□&nbsp;&nbsp;一般
								</logic:empty>
								<logic:notEmpty name="rs" property="xh">
									<logic:equal name="rs" property="jkzk" value="良好">
										√
									</logic:equal>
									<logic:notEqual name="rs" property="jkzk" value="良好">
										□
									</logic:notEqual>
									&nbsp;&nbsp;良好&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:equal name="rs" property="jkzk" value="一般">
										√
									</logic:equal>
									<logic:notEqual name="rs" property="jkzk" value="一般">
										□
									</logic:notEqual>
									&nbsp;&nbsp;一般
								</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家庭电话
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="jtdh" />
								</div>
							</td>
							<td width="16%">
								<div align="center">
									移动电话
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="yddh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家庭通讯地址
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jttxdz" />
								</div>
							</td>
							<td width="14%">
								<div align="center">
									邮政编码
								</div>
							</td>
							<td width="16%">
								<div align="center">
									<bean:write name="rs" property="yzbm" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									E-Mail地址
								</div>
							</td>
							<td colspan="5">
								<div align="center">
									<bean:write name="rs" property="email" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									入学时间
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<bean:write name="rs" property="rxny" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									学制
								</div>
							</td>
							<td>
								<div align="right">
									<bean:write name="rs" property="xz" />&nbsp;&nbsp;&nbsp;年
								</div>
							</td>
							<td>
								<div align="center">
									专业
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="zymc" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									就读学生所在年级
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="nj" />
								</div>
							</td>
							<td>
								<div align="center">
									所在<bean:message key="lable.xsgzyxpzxy" />班级
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="xymc" />
									<br />
									<bean:write name="rs" property="bjmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="2">
								<div align="center">
									申请
									<br />
									借款
									<br />
									情况
								</div>
							</td>
							<td colspan="6">
								&nbsp;&nbsp;&nbsp;&nbsp;借款总金额（小写）：&nbsp;&nbsp;
								<logic:empty name="rs" property="sqje">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="sqje">
									<bean:write name="rs" property="sqje" />
								</logic:notEmpty>
								&nbsp;&nbsp;元
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									还款方法
								</div>
							</td>
							<td colspan="5">
								&nbsp;&nbsp;□&nbsp;等额本息还款法&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;□&nbsp;等额本金还款法
							</td>
						</tr>
					</table>
					<table width="100%" class="tbstyle">
						<tr>
							<td width="15%">
								<div align="center">
									学费
								</div>
							</td>
							<td width="35%">
								<div align="center">
									&nbsp;&nbsp;&nbsp;&nbsp;0&nbsp;&nbsp;元/年
								</div>
							</td>
							<td width="15%">
								<div align="center">
									住宿费
								</div>
							</td>
							<td width="35%">
								<div align="center">
									&nbsp;&nbsp;&nbsp;&nbsp;0&nbsp;&nbsp;元/年
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									生活费
								</div>
							</td>
							<td colspan="3">
								&nbsp;&nbsp;&nbsp;&nbsp;每&nbsp;&nbsp;□&nbsp;月/□&nbsp;季&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0&nbsp;&nbsp;
								元
							</td>
						</tr>
						<tr>
							<td colspan="4">
								见证人情况
								<br />
								姓名
								<u>&nbsp;
								<logic:empty name="rs" property="jzr1_xm">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="jzr1_xm">
									<bean:write name="rs" property="jzr1_xm" />
								</logic:notEmpty>
								&nbsp;</u>性别
								<u>&nbsp;
								<logic:empty name="rs" property="jzr1_xm">
								&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="jzr1_xm">
									<bean:write name="rs" property="jzr1_xb" />
								</logic:notEmpty>
								&nbsp;</u>身份证号码
								<u>
								<logic:empty name="rs" property="jzr1_sfzh">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="jzr1_sfzh">
									<bean:write name="rs" property="jzr1_sfzh" />
								</logic:notEmpty>
								</u>与借款人关系
								<u>&nbsp;
								<logic:empty name="rs" property="jzr1_gx">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="jzr1_gx">
									<bean:write name="rs" property="jzr1_gx" />
								</logic:notEmpty>
								&nbsp;</u>
								<br />
								现住址
								<u>&nbsp;
								<logic:empty name="rs" property="jzr1_xzz">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="jzr1_xzz">
									<bean:write name="rs" property="jzr1_xzz" />
								</logic:notEmpty>
								&nbsp;</u>联系电话
								<u>&nbsp;
								<logic:empty name="rs" property="jzr1_lxdh">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="jzr1_lxdh">
									<bean:write name="rs" property="jzr1_lxdh" />
								</logic:notEmpty>
								&nbsp;</u>
								<br />
								姓名
								<u>&nbsp;
								<logic:empty name="rs" property="jzr2_xm">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="jzr2_xm">
									<bean:write name="rs" property="jzr2_xm" />
								</logic:notEmpty>
								&nbsp;</u>性别
								<u>&nbsp;
								<logic:empty name="rs" property="jzr2_xm">
								&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="jzr2_xm">
									<bean:write name="rs" property="jzr2_xb" />
								</logic:notEmpty>
								&nbsp;</u>身份证号码
								<u>
								<logic:empty name="rs" property="jzr2_sfzh">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="jzr2_sfzh">
									<bean:write name="rs" property="jzr2_sfzh" />
								</logic:notEmpty>
								</u>与借款人关系
								<u>&nbsp;
								<logic:empty name="rs" property="jzr2_gx">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="jzr2_gx">
									<bean:write name="rs" property="jzr2_gx" />
								</logic:notEmpty>
								&nbsp;</u>
								<br />
								现住址
								<u>&nbsp;
								<logic:empty name="rs" property="jzr2_xzz">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="jzr2_xzz">
									<bean:write name="rs" property="jzr2_xzz" />
								</logic:notEmpty>
								&nbsp;</u>联系电话
								<u>&nbsp;
								<logic:empty name="rs" property="jzr2_lxdh">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="jzr2_lxdh">
									<bean:write name="rs" property="jzr2_lxdh" />
								</logic:notEmpty>
								&nbsp;</u>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								学校意见
								<br />
								<br />
								<div align="right">
									经办人签名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学校盖章&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<div align="right">
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
					</table>
					<br />
					<br />
					<table width="100%" class="tbstyle">
						<tr>
							<td>
								<p>
									借款申请人声明：
								</p>
								<p>
									&nbsp;&nbsp;&nbsp;&nbsp;1
									、以上申请书及其所附资料所填内容为本人所填，且完全属实。本人承担因填写不实所引致的一律责任；
								</p>
								<p>
									&nbsp;&nbsp;&nbsp;&nbsp;2
									、本人承认以此申请书作为向贵银行借款的依据。保送的资料复印件可留存贵银行作备查凭证；
								</p>
								<p>
									&nbsp;&nbsp;&nbsp;&nbsp;3 、经贵银行审查不符合规定的借款条件而未予受理时，本人无异议；
								</p>
								<p>
									&nbsp;&nbsp;&nbsp;&nbsp;4 、本人保证在取得银行贷款后，按时足额偿还贷款本息；
								</p>
								<p>
									&nbsp;&nbsp;&nbsp;&nbsp;5
									、本人同意建设银行将本人信用信息提供给中国人民银行个人信用信息基础数据库及信贷征信主管部门批准建立的其他个人信用数据库。并同意建设银行向上述个人信用数据库或有关单位、部门及个人查询本人的信用状况，查询获得的信用报告限用于中国人民银行颁布的《个人信用信息基础数据库管理暂行办法》规定用途范围内。
								</p>
								<div align="center">
									申请人签字：____________&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;法定监护人签字(如果存在)：______________
								</div>
								<div align="center">
									______年____月____日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;______年____月____日
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
