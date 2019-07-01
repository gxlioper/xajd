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
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
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
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			var user = document.getElementById('user').value;
			var xxsh = document.getElementById('xxsh').value;
			var xypdyj = document.getElementById('xypdyj').value;
			
			if(xypdyj != null){
	         	if(xypdyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />评定组意见不能大于200个字符");
	          		 return false;
	       		 }
	       	}
			if((("一般困难" == xxsh)||("特殊困难" == xxsh)||("其他困难" == xxsh)) && (user == "xy")){
				alert("学校审核已通过，不能再修改审核结果!");
	          	return false;
			}
		
			refreshForm('/xgxt/bjlhdx_xszz.do?method=knsshXxxx&actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 审核 -困难生审核 - 单个审核
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pk"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" name="tName" value="<bean:write name="tName" />" />
			<input type="hidden" name="xxsh" id="xxsh"
				value="<bean:write name='rs' property='xxsh' />" />
			<input type="hidden" name="xypdjg" id="xypdjg"
				value="<bean:write name='rs' property='xypdjg' />" />
			<input type="hidden" name="xypdyj" id="xypdyj"
				value="<bean:write name='rs' property='xypdyj' />" />
			<input type="hidden" name="user" value="<bean:write name="user" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="9" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td align="center" width="16%">
						学号
					</td>
					<td align="left">
						<bean:write name='rs' property="xh" />
					</td>
					<td width="16%" scope="col">
						<div align="center">
							姓名
						</div>
					</td>
					<td scope="col">
						<bean:write name='rs' property="xm" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							性别
						</div>
					</td>
					<td>
						<bean:write name='rs' property="xb" />
					</td>
					<td>
						<div align="center">
							身份证号
						</div>
					</td>
					<td>
						<bean:write name='rs' property="sfzh" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							民族
						</div>
					</td>
					<td>
						<bean:write name='rs' property="mzmc" />
					</td>
					<td>
						<div align="center">
							政治面貌
						</div>
					</td>
					<td>
						<bean:write name='rs' property="zzmmmc" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							出生日期
						</div>
					</td>
					<td>
						<bean:write name='rs' property="csrq" />
					</td>
					<td>
						<div align="center">
							年级
						</div>
					</td>
					<td>
						<bean:write name='rs' property="nj" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td>
						<bean:write name='rs' property="xymc" />
					</td>
					<td>
						<div align="center">
							专业
						</div>
					</td>
					<td>
						<bean:write name='rs' property="zymc" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							班级
						</div>
					</td>
					<td>
						<bean:write name='rs' property="bjmc" />
					</td>
					<td>
						<div align="center">
							入学前户口
						</div>
					</td>
					<td>
						<bean:write name='rs' property="rxqhk" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							入学年月
						</div>
					</td>
					<td>
						<bean:write name='rs' property="rxny" />
					</td>
					<td>
						<div align="center">
							毕业年月
						</div>
					</td>
					<td>
						<bean:write name='rs' property="byny" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							学历
						</div>
					</td>
					<td>
						<bean:write name='rs' property="xl" />
					</td>
					<td>
						<div align="center">
							在校联系电话
						</div>
					</td>
					<td>
						<bean:write name='rs' property="zxlxdh" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							父亲姓名
						</div>
					</td>
					<td>
						<bean:write name='rs' property="fqxm" />
					</td>
					<td>
						<div align="center">
							母亲姓名
						</div>
					</td>
					<td>
						<bean:write name='rs' property="mqxm" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							父亲年龄
						</div>
					</td>
					<td>
						<bean:write name='rs' property="fqnl" />
					</td>
					<td>
						<div align="center">
							母亲年龄
						</div>
					</td>
					<td>
						<bean:write name='rs' property="mqnl" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							父亲年收入
						</div>
					</td>
					<td>
						<bean:write name='rs' property="fqnsr" />
					</td>
					<td>
						<div align="center">
							母亲年收入
						</div>
					</td>
					<td>
						<bean:write name='rs' property="mqnsr" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							家庭人口数
						</div>
					</td>
					<td>
						<bean:write name='rs' property="jtrks" />
					</td>
					<td>
						<div align="center">
							家庭人均月收入
						</div>
					</td>
					<td>
						<bean:write name='rs' property="jtrjysr" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							提供证明类型
						</div>
					</td>
					<td>
						<bean:write name='rs' property="tgzmlx" />
					</td>
					<td>
						<div align="center">
							开据证明时间
						</div>
					</td>
					<td>
						<bean:write name='rs' property="kjzmsj" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							影响家庭经济
							<br />
							的重要因素
						</div>
					</td>
					<td colspan="3">
						<bean:write name='rs' property="yxjtjjzyys" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							影响家庭经济
							<br />
							状况其他原因
						</div>
					</td>
					<td colspan="3">
						<bean:write name='rs' property="yxjtjjzkqtxx" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							备注
						</div>
					</td>
					<td colspan="3">
						<bean:write name='rs' property="bz" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							其他字段
						</div>
					</td>
					<td>
						<bean:write name='rs' property="qtzd" />
					</td>
					<td scope="row">
						<div align="center">
							申请时间
						</div>
					</td>
					<td>
						<bean:write name='rs' property="sqsj" />
					</td>
				</tr>
				<logic:equal name="user" value="xy">
					<tr>
						<td>
							<div align="center">
								评定结果
							</div>
						</td>
						<td>
							<html:select name="rs" property="yesNo">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<td>
							<div align="center">
								评定时间
							</div>
						</td>
						<td>
							<bean:write name='rs' property="now" />
						</td>
					</tr>
					<tr>
						<td>
							<div align="left">
								<bean:message key="lable.xsgzyxpzxy" />评定组意见
							</div>
						</td>
						<td colspan="3">
							<html:textarea name="rs" property="xypdyj" rows='3'
								style="width:100%" onblur="yzdx(this,'xypdyj')" />
							<input type="hidden" id="xypdyj" name="xypdyj" value="">
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="user" value="xx">
					<tr>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />评定组评定结果
							</div>
						</td>
						<td>
							<bean:write name='rs' property="xypdjg" />
						</td>
						<td>
							<div align="center">
								评定时间
							</div>
						</td>
						<td>
							<bean:write name='rs' property="now" />
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								审核结果
							</div>
						</td>
						<td>
							<html:select name="rs" property="yesNo">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<td colspan="2">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							<div align="left">
								<bean:message key="lable.xsgzyxpzxy" />评定组意见
							</div>
						</td>
						<td colspan="3">
							<bean:write name='rs' property="xypdyj" />
						</td>
					</tr>
				</logic:equal>
			</table>
			<div class="buttontool" align="center">
				<button class="button2" onclick="yz()" style="width:80px"
					id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
	</body>
</html>
