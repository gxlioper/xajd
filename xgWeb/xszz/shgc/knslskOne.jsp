<%@ page language="java" contentType="text/html; charset=GBK"%>
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
<base target="_self" />
<head>


	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 信息维护 - 困难生历史库详细信息
		</div>
	</div>
		<html:form action="shgc_xszz_new.do?method=knslskOne" method="post">
			<input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="pkVal" />">
			<input type="hidden" id="xh" name="xh"
				value="<bean:write name="rs" property="xh" />">
			<input type="hidden" id="nd" name="nd"
				value="<bean:write name="rs" property="nd" />">

			<table class="tbstyle" width="100%">
				<tr>
				<td align="center" colspan="2">
					学号
				</td>
				<td align="left" colspan="3">
					<bean:write name="rs" property="xh" />
				</td>
				<td colspan="2">
					<div align="center">
						姓名
					</div>
				</td>
				<td width="34%">
					<bean:write name="rs" property="xm"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						性别
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="xb"/>
				</td>
				<td colspan="2">
					<div align="center">
						身份证号
					</div>
				</td>
				<td>
					<bean:write name="rs" property="sfzh"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						民族
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="mzmc"/>
				</td>
				<td colspan="2">
					<div align="center">
						政治面貌
					</div>
				</td>
				<td>
					<bean:write name="rs" property="zzmmmc"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="xymc"/>
				</td>
				<td colspan="2">
					<div align="center">
						专业
					</div>
				</td>
				<td>
					<bean:write name="rs" property="zymc"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						班级
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="bjmc"/>
				</td>
				<td colspan="2">
					<div align="center">
						年级
					</div>
				</td>
				<td>
					<bean:write name="rs" property="nj"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						生源地
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="syd"/>
				</td>
				<td colspan="3">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						联系电话
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="lxdh"/>
				</td>
				<td colspan="2">
					<div align="center">
						入学前户口
					</div>
				</td>
				<td>
					<bean:write name="rs" property="rxqhk"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						家庭住址
					</div>
				</td>
				<td colspan="6">
					<bean:write name="rs" property="jtzz"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						邮政编码
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="yzbm"/>
				</td>
				<td colspan="2">
					<div align="center">
						联系电话
					</div>
				</td>
				<td>
					<bean:write name="rs" property="jtlxdh"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						是否愿意参加
						<br />
						慈善或志愿活动
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="sfyycjcshzyhd"/>
				</td>
				<td colspan="2">
					<div align="center">
						是否愿意申请国家
						<br />
						助学贷款或勤工助学
					</div>
				</td>
				<td>
					<bean:write name="rs" property="sfyysqgjzxdkhqgzx"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						家庭类型
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="jtlx"/>
				</td>
				<td colspan="2">
					<div align="center">
						家庭人均年收入(元)
					</div>
				</td>
				<td>
					<bean:write name="rs" property="jtrjnsr"/>
				</td>
			</tr>
			<tr>
				<td rowspan="6" width="4%">
					<div align="center">
						家庭成员情况
					</div>
				</td>
				<td width="12%" align="center">
					姓名
				</td>
				<td width="10%" align="center">
					年龄
				</td>
				<td width="12%" align="center">
					与学生
					<br />
					关系
				</td>
				<td width="12%" align="center">
					职业
				</td>
				<td width="8%" align="center">
					年收入
					<br />
					(元)
				</td>
				<td width="8%" align="center">
					健康状况
				</td>
				<td align="center">
					工作(学习)单位
				</td>
			</tr>
			<tr>
				<td align="center">
					<bean:write name="rs" property="jtcy1_xm"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy1_nl"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy1_gx"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy1_zy"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy1_nsr"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy1_jkzk"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy1_gzdw"/>
				</td>
			</tr>
			<tr>
				<td align="center">
					<bean:write name="rs" property="jtcy2_xm"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy2_nl"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy2_gx"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy2_zy"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy2_nsr"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy2_jkzk"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy2_gzdw"/>
				</td>
			</tr>
			<tr>
				<td align="center">
					<bean:write name="rs" property="jtcy3_xm"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy3_nl"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy3_gx"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy3_zy"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy3_nsr"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy3_jkzk"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy3_gzdw"/>
				</td>
			</tr>
			<tr>
				<td align="center">
					<bean:write name="rs" property="jtcy4_xm"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy4_nl"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy4_gx"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy4_zy"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy4_nsr"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy4_jkzk"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy4_gzdw"/>
				</td>
			</tr>
			<tr>
				<td align="center">
					<bean:write name="rs" property="jtcy5_xm"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy5_nl"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy5_gx"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy5_zy"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy5_nsr"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy5_jkzk"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy5_gzdw"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						学生在本地
						<br />
						受助情况
					</div>
				</td>
				<td colspan="6">
					<bean:write name="rs" property="xszbdszqk"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						家庭遭受
						<br />
						自然灾害情况
					</div>
				</td>
				<td colspan="6">
					<bean:write name="rs" property="jtzszrzhqk"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						家庭遭受
						<br />
						突发意外事件
					</div>
				</td>
				<td colspan="6">
					<bean:write name="rs" property="jtzstfywsj"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						其他情况
					</div>
				</td>
				<td colspan="6">
					<bean:write name="rs" property="qtqk"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						民政部门
						<br />
						详细通讯地址
					</div>
				</td>
				<td colspan="6">
					<bean:write name="rs" property="mzbm_txdz"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						民政部门邮政编码
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="mzbm_yzbm"/>
				</td>
				<td colspan="2">
					<div align="center">
						民政部门联系电话
					</div>
				</td>
				<td>
					<bean:write name="rs" property="mzbm_lxdh"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						年度
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="nd"/>
				</td>
				<td colspan="2">
					<div align="center">
						认定时间
					</div>
				</td>
				<td>
					<bean:write name='rs' property="rdsj" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						困难认定结果
					</div>
				</td>
				<td colspan="3">
					<bean:write name='rs' property="knrdjg" />
				</td>
				<td colspan="3">
					&nbsp;
				</td>
			</tr>
			</table>
			<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<button type="button" class="button2" onClick="Close();window.dialogArguments.document.getElementById('search_go').click();">
					关&nbsp;&nbsp;&nbsp;&nbsp;闭
				</button>
			</div>

		</html:form>
</body>
</html:html>
