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
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/sjcz/xszxbzb.jsp" />
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4" align="center">
								专项补助
							</td>
						</tr>
					</thead>
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
							姓名：
						</td>
						<td align="left">
							<html:text name='rs' property="xm" styleId="xm" />
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
							年级：
						</td>
						<td align="left">
							<html:text name='rs' property="nj" styleId="nj" />
						</td>
						<td align="right">
							<font color="red">*</font>学期：
						</td>
						<td align="left">
							<html:select name="rs" property="xq" style="width:90px"
								styleId="xq">
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
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
							性别：
						</td>
						<td align="left">
							<html:text name='rs' property="xb" styleId="xb" />
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
							毕业时间：
						</td>
						<td align="left">
							<input type="text" width="100%" id="bysj" name="bysj" value="${rs.bysj}"/>
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
							邮编：
						</td>
						<td align="left">
							<input type="text" width="100%" id="yzbm" name="yzbm" value="${rs.yzbm}"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							家庭地址：
						</td>
						<td align="left" colspan="3">
							<input type="text" size="65" id="jtdz" name="jtdz" value="${rs.jtdz}"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							家庭成员1姓名：
						</td>
						<td align="left">
							<input type="text" width="100%" id="jtcy1_xm" name="jtcy1_xm" value="${rs.jtcy1_xm}"/>
						</td>
						<td align="right">
							与家庭成员1关系：
						</td>
						<td align="left">
							<input type="text" width="100%" name="jtcy1_gx" id="jtcy1_gx" value="${rs.jtcy1_gx}"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							家庭成员1工作单位：
						</td>
						<td align="left">
							<input type="text" width="100%" name="jtcy1_gzdw" id="jtcy1_gzdw" value="${rs.jtcy1_gzdw}"/>
						</td>
						<td align="right">
							家庭成员1月收入：
						</td>
						<td align="left">
							<input type="text" width="100%" name="jtcy1_ysr" id="jtcy1_ysr" value="${rs.jtcy1_ysr}"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							家庭成员2姓名：
						</td>
						<td align="left">
							<input type="text" width="100%" id="jtcy2_xm" name="jtcy2_xm" value="${rs.jtcy2_xm}"/>
						</td>
						<td align="right">
							与家庭成员2关系：
						</td>
						<td align="left">
							<input type="text" width="100%" name="jtcy2_gx" id="jtcy2_gx" value="${rs.jtcy2_gx}"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							家庭成员2工作单位：
						</td>
						<td align="left">
							<input type="text" width="100%" name="jtcy2_gzdw" id="jtcy2_gzdw" value="${rs.jtcy2_gzdw}"/>
						</td>
						<td align="right">
							家庭成员2月收入：
						</td>
						<td align="left">
							<input type="text" width="100%" name="jtcy2_ysr" id="jtcy2_ysr" value="${rs.jtcy2_ysr}"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							家庭成员3姓名：
						</td>
						<td align="left">
							<input type="text" width="100%" id="jtcy3_xm" name="jtcy3_xm" value="${rs.jtcy3_xm}"/>
						</td>
						<td align="right">
							与家庭成员3关系：
						</td>
						<td align="left">
							<input type="text" width="100%" name="jtcy3_gx" id="jtcy3_gx" value="${rs.jtcy3_gx}"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							家庭成员3工作单位：
						</td>
						<td align="left">
							<input type="text" width="100%" name="jtcy3_gzdw" id="jtcy3_gzdw" value="${rs.jtcy3_gzdw}"/>
						</td>
						<td align="right">
							家庭成员3月收入：
						</td>
						<td align="left">
							<input type="text" width="100%" name="jtcy3_ysr" id="jtcy3_ysr" value="${rs.jtcy3_ysr}"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							家庭成员4姓名：
						</td>
						<td align="left">
							<input type="text" width="100%" id="jtcy4_xm" name="jtcy4_xm" value="${rs.jtcy4_xm}"/>
						</td>
						<td align="right">
							与家庭成员4关系：
						</td>
						<td align="left">
							<input type="text" width="100%" name="jtcy4_gx" id="jtcy4_gx" value="${rs.jtcy4_gx}"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							家庭成员4工作单位：
						</td>
						<td align="left">
							<input type="text" width="100%" name="jtcy4_gzdw" id="jtcy4_gzdw" value="${rs.jtcy4_gzdw}"/>
						</td>
						<td align="right">
							家庭成员4月收入：
						</td>
						<td align="left">
							<input type="text" width="100%" name="jtcy4_ysr" id="jtcy4_ysr" value="${rs.jtcy4_ysr}"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							家庭成员5姓名：
						</td>
						<td align="left">
							<input type="text" width="100%" id="jtcy5_xm" name="jtcy5_xm" value="${rs.jtcy5_xm}"/>
						</td>
						<td align="right">
							与家庭成员5关系：
						</td>
						<td align="left">
							<input type="text" width="100%" name="jtcy5_gx" id="jtcy5_gx" value="${rs.jtcy5_gx}"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							家庭成员5工作单位：
						</td>
						<td align="left">
							<input type="text" width="100%" name="jtcy5_gzdw" id="jtcy5_gzdw" value="${rs.jtcy5_gzdw}"/>
						</td>
						<td align="right">
							家庭成员5月收入：
						</td>
						<td align="left">
							<input type="text" width="100%" name="jtcy5_ysr" id="jtcy5_ysr" value="${rs.jtcy5_ysr}"/>
						</td>
					</tr>
					<tr>
						<td align="right">本学年家庭提供：</td>
						<td align="left"><input type="text" width="100%" name="jttgje" id="jttgje" value="${rs.jttgje}">元 /月</td>
						<td align="right">本学年助学金：</td>
						<td align="left"><input type="text" width="100%" name="zxje" id="zxje" value="${rs.zxje}">元</td>
					</tr>
					<tr>
						<td align="right">本学年奖学金：</td>
						<td align="left"><input type="text" width="100%" name="jxje" id="jxje" value="${rs.jxje}">元</td>
						<td align="right">本学年勤工助学收入：</td>
						<td align="left"><input type="text" width="100%" name="qgzxje" id="qgzxje" value="${rs.qgzxje}">元</td>
					</tr>
					<tr>
						<td align="right">本学年校内无息贷学金：</td>
						<td align="left"><input type="text" width="100%" name="xnwxdkje" id="xnwxdkje" value="${rs.xnwxdkje}">元</td>
						<td align="right">本学年其他收入：</td>
						<td align="left"><input type="text" width="100%" name="qtsrje" id="qtsrje" value="${rs.qtsrje}">元</td>
					</tr>
					<tr>
						<td align="center" colspan="4">助学贷款</td>
					</tr>
					<tr>
						<td align="right">申请金额：</td>
						<td align="left"><input type="text" width="100%" name="zxdkje" id="zxdkje" value="${rs.zxdkje}">元</td>
						<td align="right">申请时间：</td>
						<td align="left"><input type="text" width="100%" name="zxdksj" id="zxdksj" value="${rs.zxdksj}"></td>
					</tr>
					<tr>
						<td align="right">已发放金额：</td>
						<td align="left"><input type="text" width="100%" name="yffzxdkje" id="yffzxdkje" value="${rs.yffzxdkje}">元</td>
						<td align="right">发放时间：</td>
						<td align="left"><input type="text" width="100%" name="yffzxdksj" id="yffzxdksj" value="${rs.yffzxdksj}"></td>
					</tr>
					<tr>
						<td align="right">申请资助理由：</td>
						<td align="left" colspan="3"><textarea name="sqzzly" id="sqzzly" rows="5" style="width:100%" type="_moz">${rs.sqzzly}</textarea></td>
					</tr>
					<tr>
						<td align="right"><input type="checkbox" name="zzff1" id="zzff1" checked readonly=true>专项补助</td>
						<td align="left" colspan="3"></td>
					</tr>
					<tr>
						<td align="center" colspan="4">第一志愿</td>						
					</tr>
					<tr>
						<td align="right">起始金额：</td>
						<td align="left"><input type="text" width="100%" name="zzff1qsje" id="zzff1qsje" value="${rs.zzff1qsje}">元</td>
						<td align="right">结束金额：</td>
						<td align="left"><input type="text" width="100%" name="zzff1jsje" id="zzff1jsje" value="${rs.zzff1jsje}">元</td>
					</tr>
					<logic:present name="userType" scope="request">
						<logic:match value="admin" name="userType" scope="request">
						<tr>
							<td align="right">
								学校审核:
							</td>
							<td align="left">
								<html:select property="yesNo">
									<html:options collection="chkList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
							<td align="right"></td>
							<td align="left"></td>
						</tr>	 
						</logic:match>
					</logic:present>
				</table>
				<div class="buttontool" align="center">
					<button type="button" class="button2" onclick="dataCanModi(true)"
						style="width:80px" id="buttonModi">
						修 改
					</button>
					<button type="button" class="button2"
						onclick="if(checkXnNd('xn','nd'))dataDoSave('xn-xh-nd');"
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