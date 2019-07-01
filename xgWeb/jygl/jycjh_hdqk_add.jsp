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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<script language="javascript">
	function hdqk_save(){
			var zt=document.all["zt"].value;
			if ( zt==""){
				alert ("请填写主题！");
				document.all["zt"].focus();
				return false;
			}
			var hdxs=document.all["hdxs"].value;
			if ( hdxs==""){
				alert ("请选择活动形式!");
				document.all["hdxs"].focus();
				return false;
			}else if(hdxs=="013"){
				var qthdxs=document.all["qthdxs"].value;
				if(qthdxs==""){
					alert ("请填写其他活动形式!");
					document.all["qthdxs"].focus();
					return false;
				}
			}
			
			var dd=document.all["dd"].value;
			if ( dd==""){
				alert ("请填写活动地点！");
				document.all["dd"].focus();
				return false;
			}
			
			var rq=document.all["rq"].value;
			if ( rq==""){
				alert ("请选择活动日期！");
				document.all["rq"].focus();
				return false;
			}
			
			var zcr=document.all["zcr"].value;
			if ( zcr==""){
				alert ("请填写主持人！");
				document.all["zcr"].focus();
				return false;
			}
			
			var xs=document.all["xs"].value;
			if ( xs==""){
				alert ("请填写学生！");
				document.all["xs"].focus();
				return false;
			}
			
			var cyxs=document.all["cyxs"].value;
			if ( cyxs==""){
				alert ("请填写参与学生！");
				document.all["cyxs"].focus();
				return false;
			}
			
			var rs=document.all["rs"].value;
			if ( rs==""){
				alert ("请填写参与学生人数！");
				document.all["rs"].focus();
				return false;
			}
			
			var hdjl=document.all["hdjl"].value;
			if ( hdjl==""){
				alert ("请填写活动记录！");
				document.all["hdjl"].focus();
				return false;
			}
			
			var hdxg=document.all["hdxg"].value;
			if ( hdxg==""){
				alert ("请填写活动效果！");
				document.all["hdxg"].focus();
				return false;
			}
			underDealWith();
			refreshForm('/xgxt/jyglInfo.do?method=add_hdqk&doType=add');
	}
	
	
	function check_qthdxs(){
		var hdxs=$('hdxs').options[$('hdxs').selectedIndex].text;
		if(hdxs.indexOf('其他')>-1 ||hdxs.indexOf('其它')>-1){
			document.getElementById('qthdxs').readOnly=false;
		}else{
			document.getElementById('qthdxs').readOnly=true;
		}
	}
	</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/jyglInfo" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：就业管理 - 就业促进会 - 活动情况记录 - 增加活动情况记录
				</div>
			</div>
			<table class="tbstyle" align="center" style="width:100%">
				<tr style="height:22px">
					<td align="right" colspan="2">
						<font color="red">*</font>主 题：
					</td>
					<td colspan="6" align="left">
						<html:text style="width:98%" property="zt" styleId="zt" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right" colspan="2">
						<font color="red">*</font>活 动 形 式：
					</td>
					<td align="left" colspan="2">
						<html:select property="hdxs" style="width:145px" styleId="hdxs"
							onchange="check_qthdxs()">
							<html:option value=""></html:option>
							<html:options collection="hdxsList" property="dm"
								labelProperty="mc" />
						</html:select>
					</td>
					<td align="right" colspan="2">
						其 他 形 式：
					</td>
					<td align="left" colspan="2">
						<html:text property="qthdxs" styleId="qthdxs" readonly="true" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right" colspan="2">
						<font color="red">*</font>地 点：
					</td>
					<td align="left" colspan="2">
						<html:text property="dd" styleId="dd" />
					</td>
					<td align="right" colspan="2">
						<font color="red">*</font>活 动 日 期：
					</td>
					<td colspan="2">
						<html:text style="cursor:hand;" styleId="dateF" property="rq"
							onclick="return showCalendar('dateF','y-mm-dd');" readonly="true" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right" colspan="2">
						开 始 时 间：
					</td>
					<td align="left" colspan="2">
						<html:text property="kssj" styleId="kssj" />
					</td>
					<td align="right" colspan="2">
						结 束 时 间：
					</td>
					<td align="left" colspan="2">
						<html:text property="jssj" styleId="jssj" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right" colspan="2">
						<font color="red">*</font>主 持 人：
					</td>
					<td align="left" colspan="2">
						<html:text property="zcr" styleId="zy" />
					</td>
					<td align="right" colspan="2">
						<font color="red">*</font>学 生：
					</td>
					<td align="left" colspan="2">
						<html:text property="xs" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right" colspan="2">
						<font color="red">*</font>参 与 学 生：
					</td>
					<td colspan="6" align="left">
						<html:text style="width:98%" property="cyxs" styleId="cyxs" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right" colspan="2">
						<font color="red">*</font>参 与 学 生 人 数：
					</td>
					<td align="left" colspan="6">
						<html:text property="rs" styleId="rs" />
					</td>
				</tr>
				<tr>
					<td align="right" colspan="2">
						<font color="red">*</font> 活 动 记 录：
					</td>
					<td colspan="6" align="left">
						<html:textarea rows="5" style="width:98%" property="hdjl"
							styleId="a" />
					</td>
				</tr>
				<tr>
					<td align="right" colspan="2">
						<font color="red">*</font> 活 动 效 果：
					</td>
					<td colspan="6" align="left">
						<html:textarea rows="5" style="width:98%" property="hdxg"
							styleId="a" />
					</td>
				</tr>
			</table>
			
			<div id="tmpdiv"></div>
			<div class="buttontool" align="center">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="hdqk_save()"
					style="width:80px" id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px" id="buttonClose">
					关 闭
				</button>
			</div>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
				</logic:equal>
			</logic:notEmpty>
			<logic:notEmpty name="flag">
				<logic:equal name="flag" value="true">
					<script>
						alert("保存成功!");
						window.dialogArguments.document.getElementById("search_go1").click();
						Close();
						</script>
				</logic:equal>
				<logic:equal name="flag" value="false">
					<script>
						alert("保存失败!");
						document.getElementById("tmpdiv").innerHTML = "";
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
