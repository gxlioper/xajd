<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	 <script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzDao.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getXszzInfo.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/jxgl/jxglFunction.js"></script>
	<script language="javascript" src="js/yjs/yjs.js"></script>
	<script type="text/javascript">	
	</script>
	<body onload="">
		<html:form action="/yjs_jyjhsy">
		<!-- 隐藏域 -->
		<%@ include file="/jxgl/hiddenValue.jsp"%>
		<!-- 隐藏域 end-->
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：<bean:write name="title" />
				</div>
			</div>
			<fieldset>
				<legend>
					学生基本信息
				</legend>
				<table class="tbstyle" border="0" id="rsTable" align="center"
					style="width: 100%">
					<tr style="height: 23px">
						<td align="right" width="15%">
							<font color="red">*</font>学号:
						</td>
						<td align="left" width="35%">
							<html:text name="rs" property="xh" maxlength="20" styleId="xh"/>
						</td>
						<td align="right" width="15%">
							<font color="red">*</font>姓名：
						</td>
						<td align="left">
							<html:text name="rs" property="xm" maxlength="50" styleId="xm"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<font color="red">*</font>性别：
						</td>
						<td align="left">
							<html:select name="rs" property="xb" style="" styleId="xb">
								<html:option value=""></html:option>
								<html:option value="男">男</html:option>
								<html:option value="女">女</html:option>
							</html:select>
						</td>
						<td align="right">
							年级：
						</td>
						<td align="left">
							<html:select name="rs" property="nj" style="" onchange="initZylist()" styleId="nj">
											<html:option value=""></html:option>
											<html:options collection="njList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xb" />：
						</td>
						<td align="left">
							<html:select name="rs" property="xydm" style="" styleId="xy" onchange="initZylist()">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
						<td align="right">
							<font color="red">*</font>专业：
						</td>
						<td align="left">
							<html:select name="rs" property="zydm" style="" styleId="zy">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					<tr>
						
						<td align="right">
							学历：
						</td>
						<td align="left">
							<html:text name="rs" property="xm" maxlength="50"/>
						</td>
						<td align="right">
							培养方式：
						</td>
						<td align="left">
							<html:text name="rs" property="pufs" maxlength="50"/>
						</td>
					</tr>
					<tr>
						
						<td align="right">
							生源地：
						</td>
						<td align="left" colspan="3">
							<html:text name="rs" property="syd" maxlength="100" style="width: 90%"/>
						</td>
					</tr>	
				</table>
			</fieldset>
			<div class="buttontool" align="center">
				<span class="openbutton"> 	
					<!-- 非查看 -->
					<logic:notEqual name="doType"value="view">
						<button class="button2" 
							id="buttonSave" 
							onclick="saveUpdate('/xgxt/yjs_jyjhsy.do?method=yjsjbxxwhOne&doType=save','xh-xm-xb-zy');"
							style="width: 80px">
								保 存
						</button> 
					</logic:notEqual>
					&nbsp;&nbsp;
					<button class="button2" id="buttonClose" onclick="window.close();return false;"
						style="width: 80px" id="buttonClose">
							关 闭
					</button>
				</span>
			</div>
			<div id="tmpdiv1"></div>
			<logic:present name="message">
				<script>
					if($("message")	&& $("message").value != ""){
						alert($("message").value);
						dialogArgumentsQueryChick();
						window.close();
					}	
				</script>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
