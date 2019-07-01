<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=gbk">
		<title>湖州师范<bean:message key="lable.xsgzyxpzxy" />求真<bean:message key="lable.xsgzyxpzxy" />星级寝室评比办法</title>
	</head>
	<script type="text/javascript">
	 function dataSave(){
		  var url = "/xgxt/jhzy_gygl.do?method=wmgyldgSh&chk=chk";
		   document.forms[0].action = url;
	       document.forms[0].submit();
		 }
	</script>
	<body bgcolor="#FFFFFF" class="Normal" lang=ZH-CN>
		<html:form action="/jhzy_gygl" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置： 公寓管理 - 文明公寓楼申请 - 文明公寓楼申请表
				</div>
			</div>
			<div align=center>
				<table border=1 cellspacing=0 cellpadding=0 width="100%"
					class="tbstyle">
					<tr>
						<td class="Normal" align="right">
							<font color="red">*</font>楼幢名
						</td>
						<td colspan=2 class="Normal">
							<bean:write name="rs" property="lzm" />
							<input id="pkValue" name="pkValue"
								value="<bean:write name="rs" property="pk"/>" type="hidden" />
							<html:hidden name="rs" property="lzm"></html:hidden>
						</td>
						<td class="Normal" align="right">
							<font color="red">*</font>所属<bean:message key="lable.xsgzyxpzxy" />
						</td>
						<td colspan=2 class="Normal">
							<bean:write name="rs" property="xydm" />
						</td>
					</tr>
					<tr>
						<td class="Normal" align="right">
							<font color="red">*</font>申请学年
						</td>
						<td colspan=2 class="Normal">
							<bean:write name="rs" property="xn" />
						</td>
						<td class="Normal" align="right">
							年级
						</td>
						<td colspan=2 class="Normal">
							<bean:write name="rs" property="nj" />
						</td>
					</tr>
					<tr>
						<td class="Normal" align="right">
							寝室数
						</td>
						<td colspan=2 class="Normal">
							<bean:write name="rs" property="qss" />
						</td>
						<td class="Normal" align="right">
							学生数
						</td>
						<td colspan=2 class="Normal">
							<bean:write name="rs" property="xss" />
						</td>
					</tr>
					<tr>
						<td class="Normal" align="right">
							卫生合格率
						</td>
						<td colspan=2 class="Normal">
							<bean:write name="rs" property="wshgl" />
							%
						</td>
						<td class="Normal" align="right">
							卫生优秀率
						</td>
						<td colspan=2 class="Normal">
							<bean:write name="rs" property="wsyxl" />
							%
						</td>
					</tr>
					<tr>
						<td class="Normal" align="right">
							星级寝室率
						</td>
						<td colspan=2 class="Normal">
							<bean:write name="rs" property="xjqsl" />
							%
						</td>
					</tr>
					<tr id="gywmgstr">
						<td class="Normal" align="right">
							公寓文明建
							<br>
							设情况概述
						</td>
						<td colspan=5 valign=bottom class="Normal">
							<%--							楼长签名：&nbsp; 年&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日--%>
							<bean:write name="rs" property="gywmgs" />
						</td>
					</tr>
					<tr id="fdyyjtr">
						<td class="Normal" align="right">
							楼幢辅导员意见
						</td>
						<td colspan=5 valign=bottom class="Normal">
							<%--							辅导员签名： &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
							<%--							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
							<%--							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;--%>
							<%--							月&nbsp;&nbsp;&nbsp;&nbsp; 日--%>
							<bean:write name="rs" property="fdyyj" />
						</td>
					</tr>
					<tr id="xyyjtr">
						<td class="Normal" align="right">
							所属<bean:message key="lable.xsgzyxpzxy" />意见
						</td>
						<td colspan=5 valign=bottom class="Normal">
							<%--							（盖 章）--%>
							<%--							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
							<%--							年&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp; 日--%>
							<bean:write name="rs" property="xyyj" />
						</td>
					</tr>
					<tr id="xxyjtr">
						<td class="Normal" align="right">
							学生处意见
						</td>
						<td colspan=5 valign=bottom class="Normal">
							<%--							（盖 章）--%>
							<%--							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
							<%--							年&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp; 日--%>
							<bean:write name="rs" property="xxyj" />
						</td>
					</tr>
					<tr id="xxyjtr">
						<td class="Normal" align="right">
							学校审核
						</td>
						<td colspan=5 valign=bottom class="Normal">
							<html:select name="rs" property="xxsh">
								<html:option value="未审核"></html:option>
								<html:option value="通过"></html:option>
								<html:option value="不通过"></html:option>
							</html:select>
						</td>
					</tr>
					<tr height=0>
						<td width=96 class="Normal"></td>
						<td width=96 class="Normal"></td>
						<td width=108 class="Normal"></td>
						<td width=120 class="Normal"></td>
						<td width=96 class="Normal"></td>
						<td width=84 class="Normal"></td>
					</tr>
				</table>
			</div>
			<div class="buttontool" id="btn" align="center">
				<button class="button2" onclick="dataSave();" style="width: 80px"
					id="buttonSave">
					审核
				</button>
				&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width: 80px"
					id="buttonClose">
					关闭
				</button>
			</div>
		</html:form>
		<logic:equal value="ok" name="done">
			<script language="javascript">
			alert("审核成功！");
			Close();
			window.dialogArguments.document.getElementById('search_go').click();
	</script>
		</logic:equal>
		<logic:equal value="no" name="done">
			<script language="javascript">
			alert("审核失败！");
			//Close();
			//window.dialogArguments.document.getElementById('search_go').click();
	</script>
		</logic:equal>
	</body>
</html>
