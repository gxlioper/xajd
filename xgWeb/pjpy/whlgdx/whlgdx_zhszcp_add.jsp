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
	<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/pjpyFunction.js"></script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="checkWinType();">
		
		<script language="javascript" src="js/sharedFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
		<script language="javascript" src="pjpy/whlgdx/whlgdxjs.js"></script>
		<script type="text/javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
		<html:form action="/pjpy_whlgdx.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow">
					 当前位置：评奖评优 - 信息维护 - 综合素质测评信息
					</span>
				</div>
			</div>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    					alert("您输入的学号无效!");
   					</script>
				</logic:equal>
				<input type="hidden" id="disableEle" name="disableEle" value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/pjpy_whlgdx.do?method=zhszcpAdd" />
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4" align="center">
								综合素质测评信息维护
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
							<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu" style="">
								选择
							</button>
						</td>
						<td align="right">
							<font color="red">*</font>年度：
						</td>
						<td align="left">
							<html:select name='rs' property="nd" style="width:90px"	styleId="nd">
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
							<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
						</td>
						<td align="right">
							<font color="red">*</font>学年：
						</td>
						<td align="left">
							<html:select name='rs' property="xn" style="width:90px"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							性别：
						</td>
						<td align="left">
							<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
						</td>
						<td align="right">
							<font color="red">*</font>学期：
						</td>
						<td align="left">
							<html:select name='rs' property="xq" style="width:90px"
								styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							年级：
						</td>
						<td align="left">
							<html:text property="nj" name="rs" readonly="true"></html:text>
						</td>
						<td align="right">
 							思想道德素质分数：
						</td>
						<td align="left">
							<html:text name='rs' property="dcj" styleId="dcj" maxlength="4" onblur="chkData6(this);"></html:text>
						</td>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							<html:text property="xymc" name="rs" readonly="true"></html:text>
						</td>
						<td align="right">
 							学习平均成绩：
						</td>
						<td align="left">
							<html:text name='rs' property="xxpjcj" styleId="xxpjcj" maxlength="4" onblur="chkData6(this);"></html:text>
						</td>
					</tr><tr>
						<td align="right">
							专业：
						</td>
						<td align="left">
							<html:text property="zymc" name="rs" readonly="true"></html:text>
						</td>
						<td align="right">
 							学习平均成绩排名：
						</td>
						<td align="left">
							<html:text name='rs' property="xxpjcjpm" styleId="xxpjcjpm" maxlength="15"></html:text>
						</td>
					</tr>
					<tr>
						<td align="right">
							班级：
						</td>
						<td align="left">
							<html:text property="bjmc" name="rs" readonly="true"></html:text>
						</td>
						<td align="right">
							学习平均成绩排名比例：
						</td>
						<td align="left">
							<html:text name='rs' property="xxpjcjpmbl" styleId="xxpjcjpmbl" maxlength="10"></html:text>
						</td>
					</tr>
					<tr style="">
					  <td align="right"> 身体素质分数： </td>
					  <td align="left">
					  	<html:text name='rs' property="stszzf" styleId="stszzf" maxlength="10"/>
					  </td>
					  <td align="right"> 拓展素质分数：</td>
					  <td align="left">
					  	<html:text name='rs' property="sztzzf" styleId="sztzzf" maxlength="10"/>
					  </td>
				  </tr>
					<tr style="">
					  <td align="right"><p>综合测评总分：</p></td>
					  <td align="left">
					  	<html:text name='rs' property="zhszcpzf" styleId="zhszcpzf" maxlength="10"/>
					  </td>
					  <td align="right">综合测评排名比例：</td>
					  <td align="left">
					  	<html:text name='rs' property="zhszcpcjpmbl" styleId="zhszcpcjpmbl" maxlength="10"/>
					  </td>
				  </tr>
					<tr style="">
						<td align="right">
							综合测评排名：
						</td>
						<td align="left">
							<html:text name='rs' property="zhszcpcjpm" styleId="zhszcpcjpm" maxlength="15"></html:text>
						</td>
						<td align="right">
 							单科最低分数：
 						</td>
						<td align="left">
							<html:text name='rs' property="dkzdfs" styleId="dkzdfs" onkeypress="" maxlength="10"></html:text>
						</td>
					</tr>
					<tr>
						<td align="right">
 							外语过级情况：
						</td>
						<td align="left" colspan="3">
							<html:textarea name='rs' property="wygjqk" styleId="wygjqk" rows="2" style="width:98%"></html:textarea>
						</td>
					</tr>
					<tr>
						<td align="right">
							备注：
						</td>
						<td colspan="3" align="left">
							<html:textarea name='rs' property="bz" styleId="bz" rows="3" style="width:98%"></html:textarea>
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
						<button class="button2"
							onclick="if(checkXnNd('xn','nd'))zhszcpsave('xn-xq-xh-nd','pjpy_whlgdx.do?method=zhszcpSave');"
							style="width:80px" id="buttonSave">
							保 存
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							关 闭
						</button>
				</div>
			</logic:notEmpty>
		</html:form>
	</body>
	<logic:present name="inserted">
		<logic:equal value="yes" name="inserted">
			<script>
				alert("操作成功！");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
		<logic:equal value="no" name="inserted">
			<script>
				alert("操作失败！");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
	</logic:present>
</html>
