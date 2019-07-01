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
		function yz(){
			document.forms[0].action = "/xgxt/ycws_xszz.do?method=knsrdEditSave";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 信息维护 - 困难认定详细信息
		</div>
	</div>
		<html:form action="ycws_xszz.do?method=knsrdEdit" method="post">
			<input type="hidden" id="url" name="url"
				value="/ycws_xszz.do?method=knsrdEdit" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="pkVal" />">
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" />">

			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("保存成功！");
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("保存失败！");
	         	</script>
				</logic:match>
			</logic:present>
			<logic:present name="isPASS">
				<logic:match value="is" name="isPASS">
					<script language="javascript">
	         			alert("已存在数据，不能增加！");
	         		</script>
				</logic:match>
			</logic:present>
			<table class="tbstyle" width="100%">
				<tr>
					<td align="center" width="16%">
						<font color="red">*</font>学号
					</td>
					<td align="left" width="34%">
						<logic:equal name="act" value="add">
						<html:text name='rs' property="xh" styleId="xh"
							onkeypress="autoFillStuInfo(event.keyCode,this)" />
						<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
							class="btn_01" id="buttonFindStu">
							选择
						</button>
						</logic:equal>
						<logic:notEqual name="act" value="add">
							<bean:write name="rs" property="xh"/>
							<input type="hidden" id="xh" name="xh"
								value="<bean:write name="rs" property="xh" />">
						</logic:notEqual>
					</td>
					<td width="16%">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="34%">
						<bean:write name="rs" property="xm"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							性别
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xb"/>
					</td>
					<td>
						<div align="center">
							身份证号
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sfzh"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							民族
						</div>
					</td>
					<td>
						<bean:write name="rs" property="mzmc"/>
					</td>
					<td>
						<div align="center">
							政治面貌
						</div>
					</td>
					<td>
						<bean:write name="rs" property="zzmmmc"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							年级
						</div>
					</td>
					<td>
						<bean:write name="rs" property="nj"/>
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />名称
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xymc"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							专业名称
						</div>
					</td>
					<td>
						<bean:write name="rs" property="zymc"/>
					</td>
					<td>
						<div align="center">
							班级名称
						</div>
					</td>
					<td>
						<bean:write name="rs" property="bjmc"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							学制
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xz"/>
					</td>
					<td>
						<div align="center">
							联系电话
						</div>
					</td>
					<td>
						<input type="text" id="lxdh" name="lxdh" maxlength="15"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							家庭类型
						</div>
					</td>
					<td>
						<input type="text" id="jtlx" name="jtlx" maxlength="30"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="jtlx"/>">
					</td>
					<td>
						<div align="center">
							是否低保
						</div>
					</td>
					<td>
						<div align="center">
							<logic:present name="rs" property="sfdb">
								<logic:match value="是" name="rs" property="sfdb">
									<input type="radio" name="sfdb" value="是" checked>&nbsp;是
								    <input type="radio" name="sfdb" value="否">&nbsp;否
								</logic:match>
								<logic:match value="否" name="rs" property="sfdb">
									<input type="radio" name="sfdb" value="是">&nbsp;是
							    	<input type="radio" name="sfdb" value="否" checked>&nbsp;否
								</logic:match>
							</logic:present>
							<logic:notPresent name="rs" property="sfdb">
								<input type="radio" name="sfdb" value="是" checked>&nbsp;是
								<input type="radio" name="sfdb" value="否">&nbsp;否
							</logic:notPresent>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							家庭年收入
						</div>
					</td>
					<td>
						<input type="text" id="jtnsr" name="jtnsr" maxlength="6"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="jtnsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							家庭人口
						</div>
					</td>
					<td>
						<input type="text" id="jtrk" name="jtrk" maxlength="6"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="jtrk"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							是否有证明材料
						</div>
					</td>
					<td>
						<div align="center">
							<logic:present name="rs" property="sfyzmcl">
								<logic:match value="是" name="rs" property="sfyzmcl">
									<input type="radio" name="sfyzmcl" value="是" checked>&nbsp;是
								    <input type="radio" name="sfyzmcl" value="否">&nbsp;否
								</logic:match>
								<logic:match value="否" name="rs" property="sfyzmcl">
									<input type="radio" name="sfyzmcl" value="是">&nbsp;是
							    	<input type="radio" name="sfyzmcl" value="否" checked>&nbsp;否
								</logic:match>
							</logic:present>
							<logic:notPresent name="rs" property="sfyzmcl">
								<input type="radio" name="sfyzmcl" value="是" checked>&nbsp;是
								<input type="radio" name="sfyzmcl" value="否">&nbsp;否
							</logic:notPresent>
						</div>
					</td>
					<td>
						<div align="center">
							困难认定
						</div>
					</td>
					<td>
						<html:select name="rs" property="knrd" style="width:80px">
							<html:option value=""></html:option>
							<html:option value="一般困难">一般困难</html:option>
							<html:option value="困难">困难</html:option>
							<html:option value="特殊困难">特殊困难</html:option>
							<html:option value="不困难">不困难</html:option>
						</html:select>
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<button type="button" class="button2" onClick="yz();">
					保&nbsp;&nbsp;&nbsp;&nbsp;存
				</button>
				<button type="button" class="button2" onClick="Close();window.dialogArguments.document.getElementById('search_go').click();">
					关&nbsp;&nbsp;&nbsp;&nbsp;闭
				</button>
			</div>

		</html:form>
</body>
</html:html>
