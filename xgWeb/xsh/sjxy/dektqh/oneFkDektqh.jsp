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
		<script type="text/javascript">
		 function sqPrint(){
        window.open('sjxyDektqh.do?method=printFkDektqh&pkValue=${pkValue}');
        }	
		</script>
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
	<script language="javascript">
	function dosubmit()
	{
		document.forms[0].submit();
	}
	</script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body>		
		  <html:form action="/sjxyDektqh.do?method=shOneDektqh&doType=modi" method="post">
		    <input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="url" name="url" value="/sjxyDektqh.do?method=shOneDektqh&doType=view" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx}" />
			<input type="hidden" id="pkValue" name="pkValue" value="${pkValue}" />
			<fieldset>
			<div class="title">
			<div class="title_img" id="title_m">
					当前位置：学生会 - 社团管理 - 第二课堂活动企划反馈
			</div>
			</div>

			<logic:present name="result">
			</logic:present>

			<table width="99%" id="rsT" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							<b>第二课堂活动企划反馈</b>
						</td>
					</tr>
				</thead>
				<tr>
				<td align="right" style="width: 15%">
						<font color="red">*</font>活动负责人：
					</td>
					<td align="left" width="35%">
							<bean:write name="rs" property="hdfzr" />
					</td>
					<td align="right" width="18%">
						宿舍号：
					</td>
					<td align="left" width="35%">
						<bean:write  name="rs" property="save_ssh"/>
					</td>
				</tr>
				<tr style="height:22px">
					
					<td align="right" style="width: 15%">
						举办部门：
					</td>
					<td align="left">
						<bean:write name="rs" property="jbbm"/>
						<html:hidden property="save_jbbm" value="${rs.jbbm}"/>
					</td>
					<td align="right">
						活动名称：
					</td>
					<td align="left">
						<bean:write  name="rs" property="hdmc" />
						<html:hidden property="save_hdmc" value="${rs.hdmc}"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						指导老师：
					</td>
					
					<td align="left">
						<bean:write name="rs" property="zdls"/>
					</td>
					<td align="right">
						邀请嘉宾：
					</td>
					<td align="left">
						<bean:write  name="rs" property="save_yqjb"/>
					</td>		
				</tr>
				<tr style="height:22px">
					<td align="right">
						拟举办时间：
					</td>
					<td align="left">
						<bean:write name="rs" property="jbsj"/>
						<html:hidden property="save_jbsj" value="${rs.jbsj}"/>
					</td>
					<td align="right">
						<font color="red">*</font>联系方式：
					</td>
					<td align="left">
						<bean:write  name="rs" property="save_lxfs"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						活动地点：
					</td>
					<td align="left">
						<bean:write  name="rs" property="save_cjdxrs" />
					</td>
					<td align="right">
						<font color="red">*</font>参加对象及人数：
					</td>
					<td align="left">
						<bean:write  name="rs" property="save_cjdxrs" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						分管老师审核：
					</td>
					<td align="left">
						<bean:write name="rs" property="save_xxsh"/>
					</td>
					<td align="right">
							实际出席人数：
					</td>
					<td align="left">
							<bean:write name="rs" property="sjcxrs" />
					</td>
				</tr>
				<tr>
					<td align="right">
							活动效果：
					</td>
					<td align="left">
							<bean:write name="rs" property="hdxg" />
					</td>
					<td align="right">
							是否有图片：
					</td>
					<td>
						<bean:write  name="rs" property="save_sfytp" />
					</td>
				</tr>
				<tr>	
					<td align="right">
						是否有通讯稿：
					</td>
					<td>
						<bean:write name="rs" property="save_sfytxg"/>
					</td>	
					<td align="right">
						是否报销：
					</td>
					<td>
						<bean:write name="rs" property="save_sfbx"/>
					</td>	
				</tr>
				<tr style="height:22px">
					<td align="right">
						活动的目的和意义：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs" property="hdmdyy" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						活动可行性分析：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs" property="hdkxfx" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						活动实施时间表：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs" property="hdsssjb" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						活动需要经费：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs"  property="hdxyjf" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						活动经费预算清单：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs" property="hdjfys" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<logic:equal name="userType"value="xx">
					<tr style="height:22px">
						<td align="right">
							学生会主席事后评价、意见：
							<br />
							<span class="style1">(限400字)&nbsp;</span>
						</td>
						<td colspan="3" align="left">
							<html:textarea rows="8" style="width:98%" name="rs" property="save_shpjyj" onblur="chLeng(this,400)"/>
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="userType"value="admin">
					<tr style="height:22px">
						<td align="right">
							学生会主席事后评价、意见：
							<br />
							<span class="style1">(限400字)&nbsp;</span>
						</td>
						<td colspan="3" align="left">
							<html:textarea rows="8" style="width:98%" name="rs" property="save_shpjyj" onblur="chLeng(this,400)"/>
						</td>
					</tr>
				</logic:equal>
			</table>
					<div class="buttontool" align="center">
						<button class="button2" onclick="Close();return false;" style="width:80px" id="buttonClose">
							关 闭
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="sqPrint()" style="width:80px">
						打  印
					</button>
					</div>
				</fieldset>
		</html:form>
		<logic:present name="result">
		<input type="hidden" id="message" value="${message}"/>
		<script>
				alert(document.getElementById('message').value);
				if(opener){
			 		opener.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:present>
	</body>
</html>