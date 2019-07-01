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
		<script language="javascript" src="pjpy/hbsf/hbsfjs.js"></script>
		<script type="text/javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
		<html:form action="/pjpyhbsfwh" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置: 评奖评优 - 信息维护 - 综合素质测评
				</div>
			</div>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("您输入的学号无效!");
    </script>
				</logic:equal>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/zhszcpadd.do" />
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
						</td>
						<td align="right">
							<font color="red">*</font>年度：
						</td>
						<td align="left">
							<html:select property="nd" style="width:90px"
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
							<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
						</td>
						<td align="right">
							<font color="red">*</font>学年：
						</td>
						<td align="left">
							<html:select property="xn" style="width:90px"
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
							<html:select property="xq" style="width:90px"
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
							思想道德素质总分：
						</td>
						<td align="left">
							<html:text property="dcj" styleId="dcj" maxlength="4" onblur="chkData6(this);chksszf()"></html:text>
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
							科学文化素质总分：
						</td>
						<td align="left">
							<html:text property="zcj" styleId="zcj" maxlength="4" onblur="chkData6(this);countkxzf()"></html:text>
						</td>
					</tr><tr>
						<td align="right">
							专业：
						</td>
						<td align="left">
							<html:text property="zymc" name="rs" readonly="true"></html:text>
						</td>
						<td align="right">
							身心健康素质总分：
						</td>
						<td align="left">
							<html:text property="tcj" styleId="tcj" maxlength="4" onblur="chkData6(this);countsxzf()"></html:text>
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
							综合测评总分：
						</td>
						<td align="left">
							<html:text property="zhszcpzf" styleId="zhszcpzf" maxlength="4" readonly="true"></html:text>
						</td>
					</tr>
					<tr style="">
						<td align="right">
							综合测评排名：
						</td>
						<td align="left">
							<html:text property="zhcppm" styleId="zhcppm" onkeypress="chkonlynum(this,event);" maxlength="4"></html:text>
						</td>
						<td align="right">
							不及格缓考门数：
						</td>
						<td align="left">
							<html:text property="bjghkms" styleId="bjghkms" onkeypress="chkonlynum(this,event);" maxlength="2"></html:text>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<table width="100%" border="1" class="tbstyle">
								<tr>
									<td bgcolor="#CCCCCC">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child2.style.display=(document.all.child2.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>思想道德素质总分明细</strong>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<div id="child2" style="display:none" class="style2">
								<table width="100%" border="1" align="center" class="tbstyle">
									<tr style="width:20px">
										<td align="right">
											思想道德表现：
										</td>
										<td align="left">
											<html:text property="sxddbx" styleId="sxddbx" maxlength="4" onblur="chkData6(this);countsxddzf()"></html:text>
										</td><td align="right">
											政治理论学习：
										</td>
										<td align="left">
											<html:text property="zzllxx" style="zzllxx" maxlength="4" onblur="chkData6(this);countsxddzf()"></html:text>
										</td>
									</tr>
									<tr style="width:20px">
										<td align="right">
											宿舍建设情况：
										</td>
										<td align="left">
											<html:text property="ssjsqk" style="ssjsqk" maxlength="4" onblur="chkData6(this);countsxddzf()"></html:text>
										</td><td align="right">
											社会实践活动：
										</td>
										<td align="left">
											<html:text property="shsjhd" style="shsjhd" maxlength="4" onblur="chkData6(this);countsxddzf()"></html:text>
										</td>
									</tr>
									<tr style="width:20px">
										<td align="right">
											干部任职表现：
										</td>
										<td align="left">
											<html:text property="gbrzbx" style="gbrzbx" maxlength="4" onblur="chkData6(this);countsxddzf()"></html:text>
										</td><td align="right">
											其它突出事迹：
										</td>
										<td align="left">
											<html:text property="qttcsj" style="qttcsj" maxlength="4" onblur="chkData6(this);countsxddzf()"></html:text>
										</td>
									</tr>
									<tr style="width:20px">
										<td align="right">
											自评分：
										</td>
										<td align="left">
											<html:text property="zpf" styleId="zpf" maxlength="4" onblur="chkData6(this);countsxddzf()"></html:text>
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<table width="100%" border="1" class="tbstyle">
								<tr>
									<td bgcolor="#CCCCCC">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child3.style.display=(document.all.child3.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>科学文化素质总分明细</strong>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<div id="child3" style="display:none" class="style2">
								<table width="100%" border="1" align="center" class="tbstyle">
									<tr style="height:20px">
										<td align="right">
											专业拓展：
										</td>
										<td align="left">
											<html:text property="zytz" styleId="zytz" maxlength="4"  onblur="chkData6(this);countkxwhszzf()"></html:text>
										</td>
										<td align="right">
											语言技能：
										</td>
										<td align="left">
											<html:text property="yyjn" styleId="yyjn" maxlength="4" onblur="chkData6(this);countkxwhszzf()"></html:text>
										</td>
									</tr>
									<tr style="height:20px">
										<td align="right">
											计算机技能：
										</td>
										<td align="left">
											<html:text property="jsjjn" styleId="jsjjn" maxlength="4" onblur="chkData6(this);countkxwhszzf()"></html:text>
										</td>
										<td align="right">
											职业技能：
										</td>
										<td align="left">
											<html:text property="zyjn" styleId="zyjn" maxlength="4" onblur="chkData6(this);countkxwhszzf()"></html:text>
										</td>
									</tr>
									<tr style="height:20px">
										<td align="right">
											学科竞赛：
										</td>
										<td align="left">
											<html:text property="kxjs" styleId="kxjs" maxlength="4" onblur="chkData6(this);countkxwhszzf()"></html:text>
										</td>
										<td align="right">
											创新能力：
										</td>
										<td align="left">
											<html:text property="cxnl" styleId="cxnl" maxlength="4" onblur="chkData6(this);countkxwhszzf()"></html:text>
										</td>
									</tr>
									<tr style="height:20px">
										<td align="right">
											课程加权平均分：
										</td>
										<td align="left">
											<html:text property="kcjqpfj" styleId="kcjqpfj" maxlength="4" onblur="chkData6(this);countkxwhszzf()"></html:text>
										</td>
										<td align="right">
											课程加权平均分排名：
										</td>
										<td align="left">
											<html:text property="kcjqpfjpm" styleId="kcjqpfjpm" onkeypress="chkonlynum(this,event);" maxlength="4"></html:text>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<table width="100%" border="1" class="tbstyle">
								<tr>
									<td bgcolor="#CCCCCC">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child4.style.display=(document.all.child4.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>身心健康素质总分明细</strong>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<div id="child4" style="display:none" class="style2">
								<table width="100%" border="1" align="center" class="tbstyle">
									<tr style="width:20px">
										<td align="right">
											体育达标：
										</td>
										<td align="left">
											<html:text property="tydb" styleId="tydb" maxlength="4" onblur="chkData6(this);countsxjkszzf()"></html:text>
										</td>
										<td align="right">
											体育活动：
										</td>
										<td align="left">
											<html:text property="tyhd" styleId="tyhd" maxlength="4" onblur="chkData6(this);countsxjkszzf()"></html:text>
										</td>
									</tr>
									<tr style="width:20px">
										<td align="right">
											特殊情况：
										</td>
										<td align="left">
											<html:text property="tsqk" styleId="tsqk" maxlength="4" onblur="chkData6(this);countsxjkszzf()"></html:text>
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr style="width:20px">
										<td align="right">
											心理健康活动：
										</td>
										<td align="left">
											<html:text property="xljkhd" styleId="xljkhd" maxlength="4" onblur="chkData6(this);countsxjkszzf()"></html:text>
										</td>
										<td align="right">
											心理素质状况：
										</td>
										<td align="left">
											<html:text property="xlszzk" styleId="xlszzk" maxlength="4" onblur="chkData6(this);countsxjkszzf()"></html:text>
										</td>
									</tr>
									<tr style="width:20px">
										<td align="right">
											身体素质总分：
										</td>
										<td align="left">
											<html:text property="stszzf" styleId="stszzf" readonly="true" ></html:text>
										</td>
										<td align="right">
											心理素质总分：
										</td>
										<td align="left">
											<html:text property="xlszzf" styleId="xlszzf" readonly="true"></html:text>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr>
						<td align="right">
							处分记载：
						</td>
						<td align="left" colspan="3">
							<html:textarea property="cfjz" styleId="cfjz" rows="2" style="width:98%"></html:textarea>
						</td>
					</tr>
					<tr>
						<td align="right">
							测评结果：
						</td>
						<td align="left" colspan="3">
							<html:textarea property="cpjg" styleId="cpjg" rows="2" style="width:98%"></html:textarea>
						</td>
					</tr>
					<tr>
						<td align="right">
							备注：
						</td>
						<td colspan="3" align="left">
							<html:textarea property="bz" styleId="bz" rows="3" style="width:98%"></html:textarea>
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
						<logic:equal value="yes" name="updated">
							<button class="button2"
							onclick="if(checkXnNd('xn','nd'))zhszcpsave('xn-xq-xh','hbsf_zhszcpsave.do');"
							style="width:80px" id="buttonSave">
							保 存
						</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:equal>
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
