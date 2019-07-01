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


	<title><bean:message key="lable.title" /></title>
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
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function dataDoSave(mustFill,byyfbz,bybfbz,bysfbz) {
			var eles = mustFill.split("-");
			for (i = 0; i < eles.length; i++) {
				if (document.getElementById(eles[i]).value == "") {
					alert("请将带\"*\"号的项目输入完整！");
					return false;
				}
			}
			if(!isNumber(byyfbz)){
				alert("本月应发补助必须为正整数！");
				return false;
			}
			if(!isNumber(bybfbz)){
				alert("本月补发补助必须为正整数！");
				return false;
			}
			if(!isNumber(bysfbz)){
				alert("本月实发补助必须为正整数！");
				return false;
			}
			if(bysfbz != (Math.round(byyfbz) + Math.round(bybfbz))){
				alert("应发补助和补发补助之和与实发补助不相等！");
				return false;
			}
			var url = "/xgxt/fsbz.do?doType=save&pk=";
			url += window.document.forms[0].pk.value;
			url += "&doType2=";
			url += window.document.forms[0].doType2.value;
			document.forms[0].action = url;
			document.forms[0].submit();
		}
		
		function onc(){
			var vrl = "/zxdk_fkxx.do";
			document.forms[0].submit();
		}
		
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
		
		function xfhj(){
			var byyfbz = document.getElementById('byyfbz').value;
			var bybfbz = document.getElementById('bybfbz').value;
			if((byyfbz == null) || (byyfbz == "")){
				byyfbz = "0";
			}
			if((bybfbz == null) || (bybfbz == "")){
				bybfbz = "0";
			}
			var bysfbz = Math.round(byyfbz) + Math.round(bybfbz);
			document.getElementById('bysfbz').value=bysfbz;
		}
	</script>
</head>

<body>
	<html:form action="/fsbz.do" method="post">
		<input type="hidden" id="url" name="url" value="/fsbz.do" />
		<input type="hidden" id="doType2" name="doType2"
			value="<bean:write name='doType2' scope="request" />" />
		<input type="hidden" id="pk" name="pk"
			value="<bean:write name='pk' scope="request" />" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-bjmc" />


		<logic:present name="ok">
			<logic:match value="ok" name="ok">
				<script language="javascript">
	         	alert("保存成功！");
	         	window.close();
	         	dialogArgumentsQueryChick();
	         	</script>
			</logic:match>
			<logic:match value="isSH" name="ok">
				<script language="javascript">
	         	alert("已通过学校审核，不能修改！");
	         	window.close();
	         	dialogArgumentsQueryChick();
	         	</script>
			</logic:match>
			<logic:match value="no" name="ok">
				<script language="javascript">
	         	alert("保存失败！");
	         	</script>
			</logic:match>
		</logic:present>
		<table width="100%" border="1" class="tbstyle">
			<thead>
				<tr>
					<td colspan="4" align="center">
						副食补助
					</td>
				</tr>
			</thead>
			<tr>
				<td align="center" width="16%" scope="col">
					<font color="red">*</font>学号：
				</td>
				<td align="left" width="34%" scope="col">
					<html:text name='rs' property="xh" styleId="xh" 
						onkeypress="autoFillStuInfo(event.keyCode,this)" />
					<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
						class="btn_01" id="buttonFindStu">
						选择
					</button>
				</td>
				<td width="16%" scope="col">
					<div align="center">
						姓名
					</div>
				</td>
				<td width="34%" scope="col">
					<div align="left">
						<input type="text" id="xm" name="xm" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xm" />" readonly="true">
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />
					</div>
				</td>
				<td>
					<div align="left">
						<input type="text" id="xymc" readonly="readonly" name="xymc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xymc" />" readonly="true">
					</div>
				</td>
				<td>
					<div align="center">
						班级
					</div>
				</td>
				<td>
					<div align="left">
						<input type="text" id="bjmc" readonly="readonly" name="bjmc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="bjmc" />">
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						卡号
					</div>
				</td>
				<td>
					<div align="left">
						<input type="text" id="kh" readonly="readonly" name="kh"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="kh" />" readonly="true">
					</div>
				</td>
				<td>
					<div align="center">
						<font color="red">*</font>本月应发补助
					</div>
				</td>
				<td>
					<div align="left">
						<input type="text" id="byyfbz" name="byyfbz"
							style="width:100%;heigh:100%" maxlength="5"  onblur="xfhj();"
							value="<bean:write name='rs' property="byyfbz" />"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						<font color="red">*</font>本月补发补助
					</div>
				</td>
				<td>
					<div align="left">
						<input type="text" id="bybfbz" name="bybfbz"
							style="width:100%;heigh:100%" maxlength="5"  onblur="xfhj();"
							value="<bean:write name='rs' property="bybfbz" />"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</div>
				</td>
				<td scope="row">
					<div align="center">
						<font color="red">*</font>本月实发补助
					</div>
				</td>
				<td>
					<div align="left">
						<input type="text" id="bysfbz" name="bysfbz"
							style="width:100%;heigh:100%" readonly="readonly"
							value="<bean:write name='rs' property="bysfbz" />"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						<font color="red">*</font>发放补助年月
					</div>
				</td>
				<td>
					<div align="left">
						<input type="text" readonly style="cursor:hand;width:100%"
							onclick="return showCalendar('bzffny','y-mm');"
							value="<bean:write name='rs' property="bzffny" />" name="bzffny"
							id="bzffny" />
					</div>
				</td>
				<td scope="row">
					<div align="center">
						学籍状态
					</div>
				</td>
				<td>
					<div align="left">
						<input type="text" id="xjzt" readonly="readonly" name="xjzt"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xjzt" />" readonly="true">
					</div>
				</td>
			</tr>
			<tr>
				<logic:equal name="isXX" value="is">
					<td scope="row">
						<div align="center">
							审核结果
						</div>
					</td>
					<td>
						<div align="left">
							<logic:present name="rs" property="xxsh">
								<logic:match value="未审核" name="rs" property="xxsh">
									<html:select name="rs" property="xxsh" style="width:100%">
										<html:option value="未审核">未审核</html:option>
										<html:option value="已通过">已通过</html:option>
										<html:option value="不通过">不通过</html:option>
									</html:select>
								</logic:match>
								<logic:match value="已通过" name="rs" property="xxsh">
									<html:select name="rs" property="xxsh" style="width:100%">
										<html:option value="已通过">已通过</html:option>
										<html:option value="不通过">不通过</html:option>
										<html:option value="未审核">未审核</html:option>
									</html:select>
								</logic:match>
								<logic:match value="不通过" name="rs" property="xxsh">
									<html:select name="rs" property="xxsh" style="width:100%">
										<html:option value="不通过">不通过</html:option>
										<html:option value="已通过">已通过</html:option>
										<html:option value="未审核">未审核</html:option>
									</html:select>
								</logic:match>
							</logic:present>
						</div>
					</td>
				</logic:equal>
				<logic:equal name="isXX" value="no">
					<td scope="row">
						<div align="center">
						</div>
					</td>
					<td>
						<div align="left">
						</div>
					</td>
				</logic:equal>
			<td>
			</td>
			<td>
			</td>
			</tr>
			<logic:equal name="isXX" value="is">
				<tr>
					<td scope="row">
						<div align="center">
							学校审核意见
						</div>
					</td>
					<td colspan="3">
						<div align="left">
							<input type="text" id="xxshyj" name="xxshyj"
								style="width:100%;heigh:100%" maxlength="200"
								value="<bean:write name='rs' property="xxshyj" />">
						</div>
					</td>
				</tr>
			</logic:equal>
		</table>

		<div class="buttontool" align="center">
			<button class="button2"
				onclick="dataDoSave('xh-byyfbz-bybfbz-bysfbz-bzffny',document.getElementById('byyfbz').value,document.getElementById('bybfbz').value,document.getElementById('bysfbz').value);"
				style="width:80px" id="buttonSave">
				保 存
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="button2"
				onclick="window.dialogArguments.document.getElementById('search_go').click();Close();"
				style="width:80px" id="buttonClose">
				关 闭
			</button>
		</div>

	</html:form>
</body>
</html:html>
