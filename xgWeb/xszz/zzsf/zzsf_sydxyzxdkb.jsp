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
							<strong> 漳州师范<bean:message key="lable.xsgzyxpzxy" />生源地信用助学贷款信息采集表 </strong>
						</h3>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="tbstyle">
						<tr bgcolor="#9F9F9F">
							<td colspan="8">
								&nbsp;
								<strong>借款人基本情况：</strong>
							</td>
						</tr>
						<tr>
							<td width="15%">
								<div align="center">
									姓名
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									性别
								</div>
							</td>
							<td width="10%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td width="12%">
								<div align="center">
									出生年月
								</div>
							</td>
							<td width="13%">
								<div align="center">
									<bean:write name='rs' property="csrq" />
								</div>
							</td>
							<td width="12%">
								<div align="center">
									学号
								</div>
							</td>
							<td width="13%">
								<div align="center">
									<bean:write name='rs' property="xh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									身份证号
								</div>
							</td>
							<td colspan="7">
								<div align="center">
									<bean:write name='rs' property="sfzh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家庭详细地址
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jtxxdz" />
								</div>
							</td>
							<td>
								<div align="center">
									家庭电话
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									户籍所在地
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="hjszd" />
								</div>
							</td>
							<td>
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
									移动电话
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="yddh" />
								</div>
							</td>
						</tr>
					</table>
					<table width="100%" class="tbstyle">
						<tr bgcolor="#9F9F9F">
							<td colspan="6">
								&nbsp;
								<strong>共同借款人信息：</strong>
							</td>
						</tr>
						<tr>
							<td width="15%" rowspan="4">
								<div align="center">
									姓名
								</div>
							</td>
							<td width="20%" rowspan="4">
								<div align="center">
									<bean:write name='rs' property="gtjkr_xm" />
								</div>
							</td>
							<td width="10%" rowspan="4">
								<div align="center">
									与借
									<br />
									款人
									<br />
									关系
								</div>
							</td>
							<td width="15%">
								<logic:empty name='rs' property="xh">
									&nbsp;□
								</logic:empty>
								<logic:notEmpty name='rs' property="xh">
									<logic:equal name='rs' property="gtjkr_gx" value="父">
										&nbsp;√
									</logic:equal>
									<logic:notEqual name='rs' property="gtjkr_gx" value="父">
										&nbsp;□
									</logic:notEqual>
								</logic:notEmpty>
								&nbsp;父
							</td>
							<td width="15%" rowspan="4">
								<div align="center">
									身份证号码
								</div>
							</td>
							<td width="25%" rowspan="4">
								<div align="center">
									<bean:write name='rs' property="gtjkr_sfzh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<logic:empty name='rs' property="xh">
									&nbsp;□
								</logic:empty>
								<logic:notEmpty name='rs' property="xh">
									<logic:equal name='rs' property="gtjkr_gx" value="母">
										&nbsp;√
									</logic:equal>
									<logic:notEqual name='rs' property="gtjkr_gx" value="母">
										&nbsp;□
									</logic:notEqual>
								</logic:notEmpty>
								&nbsp;母
							</td>
						</tr>
						<tr>
							<td>	
								<logic:empty name='rs' property="xh">
									&nbsp;□
								</logic:empty>
								<logic:notEmpty name='rs' property="xh">
									<logic:equal name='rs' property="gtjkr_gx" value="近亲属">
										&nbsp;√
									</logic:equal>
									<logic:notEqual name='rs' property="gtjkr_gx" value="近亲属">
										&nbsp;□
									</logic:notEqual>
								</logic:notEmpty>
								&nbsp;近亲属
							</td>
						</tr>
						<tr>
							<td>
								<logic:empty name='rs' property="xh">
									&nbsp;□
								</logic:empty>
								<logic:notEmpty name='rs' property="xh">
									<logic:equal name='rs' property="gtjkr_gx" value="其他">
										&nbsp;√
									</logic:equal>
									<logic:notEqual name='rs' property="gtjkr_gx" value="其他">
										&nbsp;□
									</logic:notEqual>
								</logic:notEmpty>
								&nbsp;其他
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									工作单位
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="gtjkr_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									职务
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="gtjkr_zw" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家庭详细地址
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="gtjkr_jtxxdz" />
								</div>
							</td>
							<td>
								<div align="center">
									家庭电话
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="gtjkr_jtdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									户籍所在地
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="gtjkr_hjszd" />
								</div>
							</td>
							<td>
								<div align="center">
									邮政编码
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="gtjkr_yzbm" />
								</div>
							</td>
							<td>
								<div align="center">
									移动电话
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="gtjkr_yddh" />
								</div>
							</td>
						</tr>
					</table>
					<table width="100%" class="tbstyle">
						<tr bgcolor="#9F9F9F">
							<td colspan="10">
								&nbsp;
								<strong>就&nbsp;学&nbsp;信&nbsp;息：</strong>
							</td>
						</tr>
						<tr>
							<td width="7%">
								<div align="center">
									系别
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name='rs' property="xymc" />
								</div>
							</td>
							<td width="7%">
								<div align="center">
									专业
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name='rs' property="zymc" />
								</div>
							</td>
							<td width="7%">
								<div align="center">
									年级
								</div>
							</td>
							<td width="7%">
								<div align="center">
									<bean:write name='rs' property="nj" />
								</div>
							</td>
							<td width="7%">
								<div align="center">
									班级
								</div>
							</td>
							<td width="19%">
								<div align="center">
									<bean:write name='rs' property="bjmc" />
								</div>
							</td>
							<td width="7%">
								<div align="center">
									学制
								</div>
							</td>
							<td width="7%">
								<div align="center">
									<bean:write name='rs' property="xz" />
								</div>
							</td>
						</tr>
					</table>
					<table width="100%" class="tbstyle">
						<tr bgcolor="#9F9F9F">
							<td colspan="6">
								&nbsp;
								<strong>申请贷款信息：</strong>
							</td>
						</tr>
						<tr>
							<td rowspan="9" width="4%">
								<div align="center">
									困
									<br />
									&nbsp;
									<br />
									难
									<br />
									&nbsp;
									<br />
									类
									<br />
									&nbsp;
									<br />
									型
								</div>
							</td>
							<td width="15%">
								<logic:equal name='rs' property="knlx_dsr" value="是">
									&nbsp;√
								</logic:equal>
								<logic:notEqual name='rs' property="knlx_dsr" value="是">
									&nbsp;□
								</logic:notEqual>
								&nbsp;低收入
							</td>
							<td colspan="4" rowspan="5">
								详细原因：
								<br />
								<logic:empty name='rs' property="xxyy">
								<br />
								<br />
								<br />
								</logic:empty>
								<logic:notEmpty name='rs' property="xxyy">
									<bean:write name='rs' property="xxyy" />
								</logic:notEmpty>
								<br />
							</td>
						</tr>
						<tr>
							<td>
								<logic:equal name='rs' property="knlx_cnh" value="是">
									&nbsp;√
								</logic:equal>
								<logic:notEqual name='rs' property="knlx_cnh" value="是">
									&nbsp;□
								</logic:notEqual>
								&nbsp;纯农户
							</td>
						</tr>
						<tr>
							<td>
								<logic:equal name='rs' property="knlx_sxg" value="是">
									&nbsp;√
								</logic:equal>
								<logic:notEqual name='rs' property="knlx_sxg" value="是">
									&nbsp;□
								</logic:notEqual>
								&nbsp;双下岗
							</td>
						</tr>
						<tr>
							<td>
								<logic:equal name='rs' property="knlx_dbh" value="是">
									&nbsp;√
								</logic:equal>
								<logic:notEqual name='rs' property="knlx_dbh" value="是">
									&nbsp;□
								</logic:notEqual>
								&nbsp;低保户
							</td>
						</tr>
						<tr>
							<td>
								<logic:equal name='rs' property="knlx_zbh" value="是">
									&nbsp;√
								</logic:equal>
								<logic:notEqual name='rs' property="knlx_zbh" value="是">
									&nbsp;□
								</logic:notEqual>
								&nbsp;重病户
							</td>
						</tr>
						<tr>
							<td>
								<logic:equal name='rs' property="knlx_wsr" value="是">
									&nbsp;√
								</logic:equal>
								<logic:notEqual name='rs' property="knlx_wsr" value="是">
									&nbsp;□
								</logic:notEqual>
								&nbsp;无收入
							</td>
							<td width="13%" rowspan="2">
								<div align="center">
									贷款年限
								</div>
							</td>
							<td colspan="3" rowspan="2">
								<div align="center">
									<bean:write name='rs' property="dknx" />&nbsp;&nbsp;年
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<logic:equal name='rs' property="knlx_lszn" value="是">
									&nbsp;√
								</logic:equal>
								<logic:notEqual name='rs' property="knlx_lszn" value="是">
									&nbsp;□
								</logic:notEqual>
								&nbsp;烈士子女
							</td>
						</tr>
						<tr>
							<td>
								<logic:equal name='rs' property="knlx_gr" value="是">
									&nbsp;√
								</logic:equal>
								<logic:notEqual name='rs' property="knlx_gr" value="是">
									&nbsp;□
								</logic:notEqual>
								&nbsp;孤儿
							</td>
							<td rowspan="2">
								<div align="center">
									贷款总金额
								</div>
							</td>
							<td rowspan="2" width="20%">
								<div align="center">
									<bean:write name='rs' property="dkzje" />&nbsp;&nbsp;元
								</div>
							</td>
							<td rowspan="2" width="20%">
								<div align="center">
									分期发放
									<br />
									次数/金额
								</div>
							</td>
							<td rowspan="2" width="28%">
								<div align="center">
									<bean:write name='rs' property="fqffcsje" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<logic:equal name='rs' property="knlx_qt" value="是">
									&nbsp;√
								</logic:equal>
								<logic:notEqual name='rs' property="knlx_qt" value="是">
									&nbsp;□
								</logic:notEqual>
								&nbsp;其他
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									经办银行
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jbyh" />
								</div>
							</td>
							<td>
								<div align="center">
									生源地贷款助学
									<br />
									贷款合同编号
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="sydxyzxdkhtbh" />
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						辅导员( 签字):&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						日期：<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>年<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>月<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>日
					</div>
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
