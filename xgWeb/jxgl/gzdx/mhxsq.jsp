<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/jxgl/jxglFunction.js"></script>
	<script type="text/javascript">	

	</script>
	<body onload="">
		<html:form action="/gzdxJxgl">
		<%@ include file="/jxgl/hiddenValue.jsp"%>
		<input type="hidden" id="url" name="url" value="/xgxt/gzdxJxgl.do?method=mhxsq"/>
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm-xb-xymc-zymc-bjmc-nj"/>
		<input type="hidden" id="lx" name="lx" value="${rs.xn }学年军训名单"/>
		<input type="hidden" id="zd" name="zd" value="xn"/>
		<input type="hidden" id="va" name="va" value="${rs.xn }"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：<bean:write name="title" />
				</div>
			</div>
			<table class="tbstyle" border="0" id="rsTable" align="center"
				style="width: 100%">
				<thead>
					<tr>
						<td colspan="4" align="center">
							免缓训申请(当前学年${rs.xn })
							<html:hidden name="rs" property="save_xn" styleId="xn"/>
						</td>
					</tr>
				</thead>
				<tr style="height: 23px">
					<td align="right" width="20%">
						<font color="red">*</font>学号：
					</td>
					<td align="left" width="30%">
						<logic:equal name="userType" value="stu">
							${rs.xh }
							<html:hidden name="rs" property="save_xh" styleId="xh"/>
						</logic:equal>
						<logic:notEqual name="userType" value="stu">
							<html:text name='rs' property="save_xh" styleId="xh" readonly="true" />
							<button type="button" onclick="sendXx();return false;" class="btn_01" id="buttonFindStu">
								选择
							</button>
						</logic:notEqual>
					</td>
					<td align="right">
						年级：
					</td>
					<td align="left">
						${rs.nj }
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						姓名：
					</td>
					<td align="left">
						${rs.xm }
					</td>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						${rs.xymc }
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						性别：
					</td>
					<td align="left">
						${rs.xb }
					</td>
					<td align="right">
						专业：
					</td>
					<td align="left">
						${rs.zymc }
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						身份证号：
					</td>
					<td align="left">
						${rs.sfzh }
					</td>
					<td align="right">
						班级：
					</td>
					<td align="left">
						${rs.bjmc }
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right" width="20%">
						籍贯：
					</td>
					<td align="left" colspan="3">
						<html:select name="rs" property="jgshen" styleId="jgshen" onchange="loadShi('jgshen','jgshi','jgxian');" disabled="true">
							<html:option value="">--请选择--</html:option>
							<html:options collection="ssList" property="ssdm"
								labelProperty="ssmc" />
						</html:select>
						<html:select name="rs" property="jgshi" styleId="jgshi"
							onchange="loadXian('jgshi','jgxian')" disabled="true">
							<html:options collection="jgshiList" property="shidm"
								labelProperty="shimc" />
						</html:select>
						<html:select name="rs" property="jgxian" styleId="jgxian" disabled="true">
							<html:options collection="jgxianList" property="xiandm"
								labelProperty="xianmc" />
						</html:select>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						<font color="red">*</font>申请类别：
					</td>
					<td align="left">
						<html:select name="rs" property="save_sqlx" style="" styleId="sqlx">
							<html:options collection="mhlxList" property="dm" labelProperty="mc" />
						</html:select>
					</td>
					<td align="right">
						<font color="red">*</font>申请时间：
					</td>
					<td align="left">
						<html:text name="rs" property="save_sqsj" styleId="sqsj" readonly="true"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('sqsj','y-mm-dd');"/>	
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						所在连队：
					</td>
					<td align="left">
						${rs.ldmc }
					</td>
					<td align="right">
						带队辅导员：
					</td>
					<td align="left">
						${rs.jsxm }
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						申请理由：<br><font color="red">(限250字)</font>
					</td>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="save_sqly" styleId="sqly" rows="5" style="width: 100%" onblur="chLeng(this,250)"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						备注：<br><font color="red">(限250字)</font>
					</td>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="save_bz" styleId="bz" rows="5" style="width: 100%" onblur="chLeng(this,250)"/>
					</td>
				</tr>
				<tr bgcolor="EEF4F9" align="center">
					<td colspan="4">
					<button type="button" class="button2" id="buttonSave" onclick="saveUpdate('/xgxt/gzdxJxgl.do?method=mhxsq&doType=save','sqlx-sqsj')"
						style="width: 80px">
						保	存
					</button>
					</td>
				</tr>
			</table>
			<div id="tmpdiv1"></div>
			<logic:present name="message">
				<script>
					if($("message")){
						alert($("message").value);
					}
				</script>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
