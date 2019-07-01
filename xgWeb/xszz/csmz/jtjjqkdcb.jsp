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
			document.forms[0].action = "/xgxt/csmz_xszz.do?method=knssq";
			document.forms[0].submit();
		}
	</script>
<body>
	<html:form action="csmz_xszz.do?method=jtjjqkdcb" method="post">
	<input type="hidden" id="nd" name="nd"
			value="<bean:write name="rs" property="nd" />">
	<input type="hidden" id="xh" name="xh"
			value="<bean:write name="rs" property="xh" />">
		<table width="100%" id="theTable" border="0">
			<tr>
				<td>
					<div align="center">
						<h3>
							<strong>长沙民政职业技术<bean:message key="lable.xsgzyxpzxy" />学生家庭经济情况调查表</strong>
						</h3>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<strong>院(系): </strong>
						<bean:write name='rs' property="xymc" />
						&nbsp;&nbsp;
						<strong>班级: </strong>
						<bean:write name='rs' property="bjmc" />
						&nbsp;&nbsp;
						<strong>入学时间： </strong>
						<bean:write name='rs' property="rxny" />
						&nbsp;&nbsp;
						<strong>填表时间： </strong>
						<bean:write name='rs' property="sqsj" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="tbstyle" height="1600px">
						<tr>
							<td width="6%">
								<div align="center">
									姓名
								</div>
							</td>
							<td width="14%">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td width="8%">
								<div align="center">
									性别
								</div>
							</td>
							<td width="8%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td width="12%">
								<div align="center">
									出生日期
								</div>
							</td>
							<td width="10%">
								<div align="center">
									<bean:write name='rs' property="csrq" />
								</div>
							</td>
							<td width="8%">
								<div align="center">
									民族
								</div>
							</td>
							<td width="9%">
								<div align="center">
									<bean:write name='rs' property="mzmc" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									政治面貌
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name='rs' property="zzmmmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									现住家庭详细地址
								</div>
							</td>
							<td colspan="6">
								<bean:write name='rs' property="xzjtxxdz" />
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
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									原学习学校
								</div>
							</td>
							<td colspan="3">
								<bean:write name='rs' property="yxxxx" />
							</td>
							<td>
								<div align="center">
									籍贯
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jg" />
								</div>
							</td>
							<td>
								<div align="center">
									家庭电话
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="6">
								<div align="center">
									直
									<br />
									系
									<br />
									家
									<br />
									庭
									<br />
									成
									<br />
									员
								</div>
							</td>
							<td>
								<div align="center">
									姓名
								</div>
							</td>
							<td>
								<div align="center">
									年龄
								</div>
							</td>
							<td>
								<div align="center">
									关系
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									现在何处工作及职务
								</div>
							</td>
							<td>
								<div align="center">
									联系电话
								</div>
							</td>
							<td>
								<div align="center">
									每月工作
									<br />
									收入(元)
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy1_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy1_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy1_gx" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="zsjtcy1_gzdwjzw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy1_lxdh" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="zsjtcy1_ysr" />&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy2_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy2_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy2_gx" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="zsjtcy2_gzdwjzw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy2_lxdh" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="zsjtcy2_ysr" />&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy3_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy3_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy3_gx" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="zsjtcy3_gzdwjzw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy3_lxdh" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="zsjtcy3_ysr" />&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy4_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy4_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy4_gx" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="zsjtcy4_gzdwjzw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy4_lxdh" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="zsjtcy4_ysr" />&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy5_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy5_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy5_gx" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="zsjtcy5_gzdwjzw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zsjtcy5_lxdh" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="zsjtcy5_ysr" />&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="2">
								<div align="center">
									家庭经
									<br />
									济收入
								</div>
							</td>
							<td>
								<div align="center">
									城镇
								</div>
							</td>
							<td colspan="8">
								家庭人口共
								<logic:equal name="fkqk" value="cz">
									<u> &nbsp; <bean:write name='rs' property="jtrks" />
										&nbsp; </u>
								</logic:equal>
								<logic:equal name="fkqk" value="nc">
									<u> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u>
								</logic:equal>
								人，全家年收入
								<logic:equal name="fkqk" value="cz">
									<u> &nbsp; <bean:write name='rs' property="jtjj_cz_qjnsr" />
										&nbsp; </u>
								</logic:equal>
								<logic:equal name="fkqk" value="nc">
									<u> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u>
								</logic:equal>
								元，人均月收入
								<logic:equal name="fkqk" value="cz">
									<u> &nbsp; <bean:write name='rs' property="jtjj_cz_rjysr" />
										&nbsp; </u>
								</logic:equal>
								<logic:equal name="fkqk" value="nc">
									<u> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u>
								</logic:equal>
								元
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									农村
								</div>
							</td>
							<td colspan="8">
								家庭人口共
								<logic:equal name="fkqk" value="nc">
									<u> &nbsp; <bean:write name='rs' property="jtrks" />
										&nbsp; </u>
								</logic:equal>
								<logic:equal name="fkqk" value="cz">
									<u> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u>
								</logic:equal>
								人，当年总收入总计
								<logic:equal name="fkqk" value="nc">
									<u> &nbsp; <bean:write name='rs' property="jtjj_nc_dnzsr" />
										&nbsp; </u>
								</logic:equal>
								<logic:equal name="fkqk" value="cz">
									<u> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u>
								</logic:equal>
								元，人均年收入
								<logic:equal name="fkqk" value="nc">
									<u> &nbsp; <bean:write name='rs' property="jtjj_nc_rjnsr" />
										&nbsp; </u>
								</logic:equal>
								<logic:equal name="fkqk" value="cz">
									<u> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u>
								</logic:equal>
								元
								<br />
								（注：务农人员必须把农作物和其他收入转换为货币收入，收入不能为零。）
							</td>
						</tr>
						<tr>
							<td colspan="10">
								<div align="center">
									当地最低社会生活保障为每年
									<logic:empty name="rs" property="ddzdshshbz">
										<u> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u>
									</logic:empty>
									<logic:notEmpty name="rs" property="ddzdshshbz">
										<u> &nbsp;&nbsp;<bean:write name='rs'
												property="ddzdshshbz" />&nbsp;&nbsp; </u>
									</logic:notEmpty>
									元
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="5">
								<div align="center">
									主
									<br />
									要
									<br />
									社
									<br />
									会
									<br />
									关
									<br />
									系
								</div>
							</td>
							<td>
								<div align="center">
									姓名
								</div>
							</td>
							<td>
								<div align="center">
									年龄
								</div>
							</td>
							<td>
								<div align="center">
									关系
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									现在何处工作及职务
								</div>
							</td>
							<td>
								<div align="center">
									每月工作
									<br />
									收入(元)
								</div>
							</td>
							<td>
								<div align="center">
									与你家经济
									<br />
									联系或供养
									<br />
									情况
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="zyshgx1_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zyshgx1_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zyshgx1_gx" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="zyshgx1_gzdwjzw" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="zyshgx1_ysr" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zyshgx1_ynjtjjlxhgyqk" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="zyshgx2_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zyshgx2_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zyshgx2_gx" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="zyshgx2_gzdwjzw" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="zyshgx2_ysr" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zyshgx2_ynjtjjlxhgyqk" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="zyshgx3_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zyshgx3_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zyshgx3_gx" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="zyshgx3_gzdwjzw" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="zyshgx3_ysr" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zyshgx3_ynjtjjlxhgyqk" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="zyshgx4_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zyshgx4_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zyshgx4_gx" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="zyshgx4_gzdwjzw" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="zyshgx4_ysr" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zyshgx4_ynjtjjlxhgyqk" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家
									<br />
									庭
									<br />
									经
									<br />
									济
									<br />
									困
									<br />
									难
									<br />
									情
									<br />
									况
									<br />
									及
									<br />
									原
									<br />
									因
								</div>
							</td>
							<td colspan="9">
								<br />
								<bean:write name='rs' property="jtjjknqkjyy" />
								<br />
								<br />
								本人保证以上填写内容真实无误，并予以认可。
								<div align="right">
									学生(签名)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="3">
								<div align="center">
									证
									<br />
									明
									<br />
									单
									<br />
									位
									<br />
									审
									<br />
									查
									<br />
									意
									<br />
									见
								</div>
							</td>
							<td>
								<div align="center">
									父亲单位
								</div>
							</td>
							<td colspan="3">
								<bean:write name='rs' property="fqdw" />
							</td>
							<td rowspan="3">
								<div align="center">
									证
									<br />
									明
									<br />
									单
									<br />
									位
									<br />
									审
									<br />
									查
									<br />
									意
									<br />
									见
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									母亲单位
								</div>
							</td>
							<td colspan="2">
								<bean:write name='rs' property="mqdw" />
							</td>
						</tr>
						<tr>
							<td colspan="4">
								审查人签名：
								<br />
								联系电话：
								<br />
								村委会盖章：
								<br />
								<br />
								<div align="right">
									年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
								</div>
							</td>
							<td colspan="4">
								审查人签名：
								<br />
								联系电话：
								<br />
								村委会盖章：
								<br />
								<br />
								<div align="right">
									年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								审查人签名：
								<br />
								联系电话：
								<br />
								乡镇人民政府，街道或县、区民政局公章：
								<br />
								<br />
								<div align="right">
									年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
								</div>
							</td>
							<td colspan="4">
								审查人签名：
								<br />
								联系电话：
								<br />
								乡镇人民政府，街道或县、区民政局公章：
								<br />
								<br />
								<div align="right">
									年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<p align="left">
						<strong>注：无论父母有无具体工作单位，审查意见及证明单位须为村委会、乡镇人民政府（街道或县、区民政局）以上单位填写和盖章。此表必须用黑色笔填写。</strong>
					</p>
				</td>
			</tr>
		</table>
	</html:form>
	<div align=center>
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','')" />
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input  value="返回"
			onclick="back();" />
	</div>
</body>
</html:html>
