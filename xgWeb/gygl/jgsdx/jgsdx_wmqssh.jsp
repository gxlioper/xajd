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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="rwDefualt()">	
		<html:form action="/ahjg_gygl" method="post">
			<input type="hidden" name="qshV" id="qshV" />
			<input type="hidden" name="pkValue" id="pkValue"
			value="<bean:write name="pkValue" scope="request"/>"/>
			<input type="hidden" name="writeAble" id="writeAble" value="<bean:write name="writeAble"/>"/>
			<input type="hidden" name="isUserReq" id="isUserReq" value="<bean:write name="isUserReq"/>"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置： 公寓管理 - 审核 - 文明寝室审核
				</div>
			</div>
			<logic:equal value="true" name="done">
				<script type="text/javascript">
	             alert("保存成功！");
	             Close();
				window.dialogArguments.document.getElementById('search_go').click();
	            </script>
			</logic:equal>
			<logic:equal value="false" name="done">
				<script type="text/javascript">
	            alert("保存失败！");
	            Close();
				window.dialogArguments.document.getElementById('search_go').click();
	           </script>
			</logic:equal>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
  				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4" align="center">
								单个审核
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right">
							楼栋名称：
						</td>
						<td>						 
						<bean:write name="rs"  property="ldmc" />
						</td>
						<td align="right">
							学年：
						</td>
						<td align="left">
							<bean:write name="rs" property="xn" />
						</td>
					</tr>
					<tr>
						<td align="right">
							寝室号：
						</td>
						<td align="left">
							<bean:write name="rs" property="qsh" />
						</td>
						<td align="right">
							学期：
						</td>
						<td align="left">
							<bean:write name="rs" property="xq" />
						</td>
					</tr>
					<tr>
						<td align="right">
							评优时间：
						</td>
						<td align="left">
							<bean:write name="rs" property="pysj" />
						</td>
						<td align="right">
							申请人：
						</td>
						<td align="left">
							<bean:write name="rs" property="xm" />
						</td>
					</tr>
					<tr>
						<td align="right">

						</td>
						<td align="left">

						</td>
						<td align="right">
							<FONT color="red"><bean:write name="rs" property="shType" />审核：</FONT>
						</td>
						<td align="left">
							<html:select name="rs" property="yesNo" style="width:80px" styleId="yesNo">								
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							备注：
						</td>
						<td align="left" colspan="3">
							<bean:write name="rs" property="bz"/>
						</td>
					</tr>
				</table>
			</logic:notEmpty>
			<div class="buttontool" align="center">
					<button class="button2"
						onclick="shDataSave(this)"
						style="width:80px" id="buttonSave">
						保 存
					</button>	
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2"
						onclick="Close();return false;"
						style="width:80px" id="buttonClose">
						关 闭
					</button>				
			</div>
		</html:form>	
	</body>
   <script type="text/javascript">
     function rwDefualt(){
         if($("writeAble").value=="no"){
              document.getElementById("buttonSave").disabled="true";
              alert("你只有读权限！");
         }else{
              if($("isUserReq").value=="yes"){
              document.getElementById("buttonSave").disabled="true";
              alert("该文明寝室由当前用户申报，申报者不能审核自己所申报的内容！");
              }
         }
     }
     function shDataSave(obj){     
          refreshForm('/xgxt/jgsdx_gygl.do?method=wmqsSbShSave');  
          obj.disabled="true";
     }
</script>
</html>
