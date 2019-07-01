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
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
			<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<body>

		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="wjcf/shgc/shgcjs/shgcjs.js"></script>
		<script language="javascript" src="js/qgzxFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/shgcwjcfwh.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：违纪处分 - <bean:message key="lable.xb" />申报 - 学生处分申报
				</div>
			</div>
			<%--<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			--%><logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("您输入的学号无效!");
    return;
    </script>
				</logic:equal>
				
				<input type="hidden" name="sbsj" id="sbsj" value="${sbsj }"/>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/shgcxysbqrystu.do" />
				<table width="99%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<b>填写申请表</b>
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td align="right" style="width:15%">
								<font color="red">*</font>学号：
							</td>
							<td align="left">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="checkXhExists('cflb-cfyy-xq-wjjtsy-zacfqk-qtcfqk-xyyj-xxyj-bzryj-xgcyj-bz');showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="right">
								<font color="red">*</font>学号：
							</td>
							<td align="left"><html:hidden name='rs' property="xh" styleId="xh" />
								<bean:write name='rs' property="xh" />
							</td>
						</logic:equal>
						<td align="right">
							年度：
						</td>
						<td align="left">
							<bean:write name='rs' property="nd" />
							<input type="hidden" name="nd" id="nd" value="<bean:write name='rs' property="nd" />" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							姓名：
						</td>
						<td align="left">
							<bean:write name='rs' property="xm" />
						</td>
						<td align="right">
							学年：
						</td>
						<td align="left">
							<bean:write name='rs' property="xn" />
							<input type="hidden" name="xn" id="xn" value="<bean:write name='rs' property="xn" />" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							性别：
						</td>
						<td align="left">
							<bean:write name='rs' property="xb" />
						</td>
						<td align="right">
							<font color="red">*</font>处分类别：
						</td>
						<td align="left">
							<html:select property="cflb" style="width:150px"
								styleId="cflb">
								<html:option value=""></html:option>
								<html:options collection="cflbList" property="cflbdm"
									labelProperty="cflbmc" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							年级：
						</td>
						<td align="left">
							<bean:write name='rs' property="nj" />
						</td>
						<td align="right">
						<font color="red">*</font>处分事由：
						</td>
						<td align="left">
						<html:select property="cfyy" style="width:150px"
								styleId="cfyy">
								<html:option value=""></html:option>
								<html:options collection="cfyyList" property="cfyydm"
									labelProperty="cfyymc" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							院系：
						</td>
						<td align="left">
							<bean:write name='rs' property="xymc" />
						</td>
						<td align="right"><font color="red">*</font>学期:</td>
						<td align="left">
							<html:select property="xq" styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							专业：
						</td>
						<td align="left">
							<bean:write name='rs' property="zymc" />
						</td>
						<td align="right">&nbsp;	
						</td>
						<td align="left">&nbsp;
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							班级：
						</td>
						<td align="left">
							<bean:write name='rs' property="bjmc" />
						</td>
						<td align="right">&nbsp;</td><td align="left">&nbsp;</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
						<font color="red">*</font>具体违纪事由：<br><font color="red">(字数在1000字以内)</font>
						</td>
						<td colspan="3" align="left"><html:textarea rows="3" name="rs" style="width:98%;inputtext;" property="jtwjsy" styleId="wjjtsy" onblur="chLeng(this,1000)" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
						<font color="red">*</font>治安处罚情况：<br><font color="red">(字数在1000字以内)</font>
						</td>
						<td colspan="3" align="left"><html:textarea rows="3" name="rs" style="width:98%;inputtext;" property="zacfqk" styleId="zacfqk" onblur="chLeng(this,1000)" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
						<font color="red">*</font>其它处罚情况：<br><font color="red">(字数在1000字以内)</font>
						</td>
						<td colspan="3" align="left"><html:textarea rows="3"  style="width:98%;inputtext;" property="qtcfqk" styleId="qtcfqk"  onblur="chLeng(this,1000)"/>
						</td>
					</tr>
					<logic:equal name="xxdm" value="11355">
					<tr style="height:22px">
						<td align="right">
							系意见：<br/><font color="red">(字数在100字以内)</font>
						</td>
						<td colspan="3" align="left"><html:textarea rows="5"  style="width:98%;inputtext" property="xyyj" styleId="xyyj"  onblur="chLeng(this,100)"/>
						</td>
					<tr>
						<td align="right">辅导员班主任意见：<br/><font color="red">(字数在100字以内)</font></td>
						<td align="left" colspan="3">
							<html:textarea name="rs" property="bzryj" styleId="bzryj" rows="5" style="width:98%;inputtext"  onblur="chLeng(this,100)">
							</html:textarea>
						</td>
					</tr>
					<tr>
						<td align="right">学工处意见：<br/><font color="red">(字数在100字以内)</font></td>
						<td align="left" colspan="3">
							<html:textarea  name="rs" property="xgcyj" styleId="xgcyj" rows="5" style="width:98%;inputtext"  onblur="chLeng(this,100)">
							</html:textarea>
						</td>
					</tr>
					<tr>
						<td align="right"><bean:message key="lable.xb" />意见：<br/><font color="red">(字数在100字以内)</font></td>
						<td align="left" colspan="3">
							<html:textarea  name="rs" property="xxyj" styleId="xxyj" rows="5" style="width:98%;inputtext" onblur="chLeng(this,100)">
							</html:textarea>
						</td>
					</tr>
					<tr>
						<td align="right">备注：<br/><font color="red">(字数在100字以内)</font></td>
						<td align="left" colspan="3">
							<html:textarea name="rs"  property="bz" styleId="bz" rows="5" style="width:98%;inputtext" onblur="chLeng(this,100)">
							</html:textarea>
						</td>
					</tr>
					</logic:equal>
				</table>
				<div class="buttontool" align="center">
					<button type="button" class="button2"
						onclick="dataSave_shgc('/xgxt/shgcxysbdatasub.do')">
						保     存
					</button>
						&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="wjcfysbprint('wjcfysbprint.do?xh=')" style="width:90px">
								 打 印/预 览 
							</button>
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("申请成功！");
    </script>
	</logic:equal>
	<logic:equal name="inserted" value="no">
	<script>
    alert("申请失败！");
    </script>
	</logic:equal>
			</logic:notEmpty>
			<script>
				function wjcfysbprint(url){
					var xh = document.getElementById('xh').value;
					var cflb = document.getElementById('cflb').value;
					var cfyy = document.getElementById('cfyy').value;
					var sbsj = document.getElementById('sbsj').value;
					var xn = document.getElementById('xn').value;
					var nd = document.getElementById('nd').value;
					var pkVal = xh+xn+nd+sbsj;
					url += xh;
					url += '&cflb=';
					url += cflb;
					url += '&cfyy=';
					url += cfyy;
					url += '&pkVal=';
					url += pkVal;
					window.open(url);
			}
			</script>
		</html:form>
		<logic:equal value="view" name="result">
			<script>
				alert("您有未通过审核的申报!\n请在未通过审核信息中查看，并重新申报");
			</script>
		</logic:equal>
	</body>
</html>
