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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="checkWinType();dataManLoad();">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("您输入的学号无效!");
    </script>
				</logic:equal>
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="readonlyEle" name="readonlyEle"
					value="xysh-xxsh-yjfsbj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/sjcz/gjzxdksqb.jsp" />
				<table width="100%" class="tbstyle">
					<tr>
						<td colspan="4" align="center">
							国家助学贷款信息维护
						</td>
					</tr>
					<tr>
						<td align="right">
							<font color="red">*</font>学号：
						</td>
						<td align="left">
							<html:text name='rs' property="xh" readonly="readonly"
								styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu" style="display:none">
								选择
							</button>
						</td>
						<td align="right">
							姓名：
						</td>
						<td align="left">
							<html:text name='rs' property="xm" styleId="xm" />
						</td>
					</tr>
					<tr>
						<td align="right">
							性别：
						</td>
						<td align="left">
							<html:text name='rs' property="xb" styleId="xb" />
						</td>
						<td align="right">
							年级：
						</td>
						<td align="left">
							<html:text name='rs' property="nj" styleId="nj" />
						</td>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							<html:text name='rs' property="xymc" styleId="xy" />
						</td>
						<td align="right">
							<font color="red">*</font>年度：
						</td>
						<td align="left">
							<html:select name="rs" property="nd" style="width:90px"
								styleId="nd">
								<html:options collection="xnList" property="nd"
									labelProperty="nd" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							专业：
						</td>
						<td align="left">
							<html:text name='rs' property="zymc" styleId="zy" />
						</td>
						<td align="right">
							<font color="red">*</font>学年：
						</td>
						<td align="left">
							<html:select name="rs" property="xn" style="width:90px"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							班级：
						</td>
						<td align="left">
							<html:text name='rs' property="bjmc" styleId="bj" />
						</td>
						<td align="right">
							<font color="red">*</font>学期：
						</td>
						<td align="left">
							<html:select name="rs" property="xq" style="width:90px"
								styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							申请贷款总额:
						</td>
						<td>
							<html:text name="rs" property="sqdkze" />
							(元)
						</td>
						<td align="right">
							亲友与本人关系:
						</td>
						<td>
							<html:text name="rs" property="qygx" />
						</td>
					</tr>
					<tr>
						<td align="right">
							申请贷款学费
						</td>
						<td>
							<html:text name="rs" property="sqdkxf" />
							(元)
						</td>
						<td align="right">
							亲友联系电话:
						</td>
						<td>
							<html:text name="rs" property="qylxdh" />
						</td>
					</tr>
					<tr>
						<td align="right">
							申请贷款生活费
						</td>
						<td>
							<html:text name="rs" property="sqdkshf" />
							(元)
						</td>
						<td align="right">
							亲友身份证号码:
						</td>
						<td>
							<html:text name="rs" property="qysfzh" style="width:100% " />
						</td>
					</tr>
					<tr>
						<td align="right">
							申请贷款住宿费
						</td>
						<td>
							<html:text name="rs" property="sqdkzsf" />
							(元)
						</td>
						<td align="right">
							亲友工作单位:
						</td>
						<td>
							<html:text name="rs" property="qygzdw" style="width:100% " />
						</td>
					</tr>
					<tr>
						<td align="right">
							有效联系方式:
						</td>
						<td>
							<html:text name="rs" property="lxdh" />
						</td>
						<td align="right">
							亲友住址:
						</td>
						<td>
							<html:text name="rs" property="qyzz" style="width:100% " />
						</td>
					</tr>
					<tr>
						<td align="right">
							父亲姓名:
						</td>
						<td>
							<html:text name="rs" property="fqxm" />
						</td>
						<td align="right">
							母亲姓名:
						</td>
						<td>
							<html:text name="rs" property="mqxm" />
						</td>
					</tr>
					<tr>
						<td align="right">
							父亲身份证号码:
						</td>
						<td>
							<html:text name="rs" property="fqsfzh" style="width:100% " />
						</td>
						<td align="right">
							母亲身份证号码:
						</td>
						<td>
							<html:text name="rs" property="mqsfzh" style="width:100% " />
						</td>
					</tr>
					<tr>
						<td align="right">
							父亲工作单位:
						</td>
						<td>
							<html:text name="rs" property="fqgzdw" style="width:100% " />
						</td>
						<td align="right">
							母亲工作单位:
						</td>
						<td>
							<html:text name="rs" property="mqgzdw" style="width:100% " />
						</td>
					</tr>
					<tr>
						<td align="right">
							父亲联系方式:
						</td>
						<td>
							<html:text name="rs" property="fqlxfs" style="width:100% " />
						</td>
						<td align="right">
							母亲联系方式:
						</td>
						<td>
							<html:text name="rs" property="mqlxfs" style="width:100% " />
						</td>
					</tr>
					<tr>
						<td align="right">
							首次毕业去向:
						</td>
						<td>
							<html:text name="rs" property="scbyqx" style="width:100% " />
						</td>
						<td align="right">
							发放时间：
						</td>
						<td align="left">
							<html:text name='rs' property="ffsj" styleId="ffsj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('ffsj','y-mm-dd');" />
						</td>
					</tr>
					<tr>
						<td align="right">
							合同号1:
						</td>
						<td>
							<html:text name="rs" property="hth1" style="width:100% " />
						</td>
						<td align="right">
							合同号2:
						</td>
						<td>
							<html:text name="rs" property="hth2" style="width:100% " />
						</td>
					</tr>
					<tr>
						<td align="right">
							合同经办金融机构1:
						</td>
						<td>
							<html:text name="rs" property="htjbjrjg1" style="width:100% " />
						</td>
						<td align="right">
							合同经办金融机构2:
						</td>
						<td>
							<html:text name="rs" property="htjbjrjg2" style="width:100% " />
						</td>
					</tr>
					<tr>
						<td align="right">
							分支机构名称1:
						</td>
						<td>
							<html:text name="rs" property="fzjgmc1" style="width:100% " />
						</td>
						<td align="right">
							分支机构名称2:
						</td>
						<td>
							<html:text name="rs" property="fzjgmc2" style="width:100% " />
						</td>
					</tr>
					<tr>
						<td align="right">
							贷款金额1:
						</td>
						<td>
							<html:text name="rs" property="dkje1" />
							(元)
						</td>
						<td align="right">
							贷款金额2:
						</td>
						<td>
							<html:text name="rs" property="dkje2" />
							(元)
						</td>
					</tr>
					<tr>
						<td align="right">
							还款年份1:
						</td>
						<td align="left">
							<html:text name='rs' property="hknf1" styleId="hknf1"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('hknf1','y-mm-dd');" />
						</td>
						<td align="right">
							还款年份2:
						</td>
						<td align="left">
							<html:text name='rs' property="hknf2" styleId="hknf2"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('hknf2','y-mm-dd');" />
						</td>
					</tr>
					<tr>
						<td align="right">
							合同号3:
						</td>
						<td>
							<html:text name="rs" property="hth3" style="width:100% " />
						</td>
						<td align="right">
							当前工作单位:
						</td>
						<td>
							<html:text name="rs" property="dqgzdw" style="width:100% " />
						</td>
					</tr>
					<tr>
						<td align="right">
							合同经办金融机构3:
						</td>
						<td>
							<html:text name="rs" property="htjbjrjg3" style="width:100% " />
						</td>
						<td align="right">
							当前单位地址:
						</td>
						<td>
							<html:text name="rs" property="dqdwdz" style="width:100% " />
						</td>
					</tr>
					<tr>
						<td align="right">
							分支机构名称3:
						</td>
						<td>
							<html:text name="rs" property="fzjgmc3" style="width:100% " />
						</td>
						<td align="right">
							当前单位邮编:
						</td>
						<td>
							<html:text name="rs" property="dqdwyb" style="width:100% " />
						</td>
					</tr>
					<tr>
						<td align="right">
							贷款金额3:
						</td>
						<td>
							<html:text name="rs" property="dkje3" />
							(元)
						</td>
						<td align="right">
							当前单位联系方式
						</td>
						<td>
							<html:text name="rs" property="dqdwlxfs" style="width:100% " />
						</td>
					</tr>
					<tr>
						<td align="right">
							还款年份3:
						</td>
						<td align="left">
							<html:text name='rs' property="hknf3" styleId="hknf3"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('hknf3','y-mm-dd');" />
						</td>
						<td align="right">
							邮件发送标记:
						</td>
						<td>
							<html:text name="rs" property="yjfsbj" />
						</td>
					</tr>

					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />审核:
						</td>
						<td>
							<html:text name="rs" property="xysh" />
						</td>
						<td align="right">
							学校审核:
						</td>
						<td>
							<html:text name="rs" property="xxsh" />
						</td>
					</tr>
					<tr>
						<td align="right">
							发放贷款总额:
						</td>
						<td>
							<html:text name="rs" property="ffdkze" />
							(元)
						</td>
						<td align="right">
							发放贷款学费:
						</td>
						<td>
							<html:text name="rs" property="ffdkxf" />
							(元)
						</td>
					</tr>
					<tr>
						<td align="right">
							发放贷款生活费:
						</td>
						<td>
							<html:text name="rs" property="ffdkshf" />
							(元)
						</td>
						<td align="right">
							发放贷款住宿费
						</td>
						<td>
							<html:text name="rs" property="ffdkzsf" />
							(元)
						</td>
					</tr>
					<tr>
						<td align="right">
							申请理由:
						</td>
						<td colspan="3">
							<html:textarea name="rs" property="sqyy" rows="4"
								style="width:100%" />
						</td>
					</tr>
					<tr>
						<td align="right">
							备注:
						</td>
						<td colspan="3">
							<html:textarea name="rs" property="bz" rows="4"
								style="width:100%" />
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button type="button" class="button2" onclick="dataCanModi(true)"
						style="width:80px" id="buttonModi">
						修 改
					</button>
					<button type="button" class="button2"
						onclick="if(checkXnNd('xn','nd'))dataDoSave('xn-xq-xh');"
						style="width:80px" id="buttonSave">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
			</logic:notEmpty>
			 <jsp:include flush="true" page="/sjcz/include/modiPageJudge.jsp"></jsp:include>
		</html:form>
	</body>
</html>
