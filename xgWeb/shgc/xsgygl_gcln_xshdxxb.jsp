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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<body>
		<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/xsgyglFunction.js"></script>
		<html:form action="/stu_active_InfoManage" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：
					<bean:write name="tips" scope="request" />
				</div>
			</div>
			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
			<fieldset>
				<legend>
					基本参数
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4" align="left" width="600">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							活动时间：从&nbsp;&nbsp;<input type="text" name="KShdsj" id="KShdsj" style="width:80px" value="" onclick="return showCalendar('KShdsj','y-mm-dd');" readonly="readonly"> 
        												 到 <input type="text" name="JShdsj" id="JShdsj" style="width:80px" value="" onclick="return showCalendar('JShdsj','y-mm-dd');" readonly="readonly"></td>
							<td width="10" align="center" valign="middle">
								<input type="hidden" name="go" value="go" />
								<button class="button2" style="height:40px" id="search_go" 
										onclick="allNotEmpThenGo('/xgxt/stu_active_InfoManage.do')">
									查询
								</button>
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
				<fieldset>
					<legend>
						当前记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：单击表头可以排序</font>
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td></td>
								<td align="center" onclick="tableSort(this)">序号</td>
								<td align="center" onclick="tableSort(this)">活动主题</td>
								<td align="center" onclick="tableSort(this)">活动性质</td>
								<td align="center" onclick="tableSort(this)">活动内容</td>
								<td align="center" onclick="tableSort(this)">活动时间</td>
								<td align="center" onclick="tableSort(this)">活动地点</td>
								<td align="center" onclick="tableSort(this)">活动对象</td>
							</tr>
						</thead>
						 <tbody id="rsTable">
    						<logic:present name="rs">
      							<logic:iterate id="s" name="rs">
									<tr onclick="rowOnClick(this)" style="cursor:hand" ondblclick="viewInfo('modi','/xgxt/stu_ActiveInfo.do')">
          								 <td><input type="hidden" value="<bean:write name="s" property="pkValue" />"/></td>
          								 <td><bean:write name="s" property="xh" /></td>
          								 <td><bean:write name="s" property="hd_zt" /></td>
          								 <td><bean:write name="s" property="hd_xz" /></td>
          								 <td><bean:write name="s" property="hd_nr" /></td>
          								 <td><bean:write name="s" property="hd_sj" /></td>
          								 <td><bean:write name="s" property="hd_dd" /></td>
          								 <td><bean:write name="s" property="hd_dx" /></td>
        						    </tr>
      						</logic:iterate>
    							</logic:present>
    					</tbody>
					</table>
				</fieldset>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<logic:equal value="yes" name="writeAble">
						<button class="button2" onclick="viewInfo('add','/xgxt/stu_ActiveInfo.do')"
							style="width:80px">
							增 加
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="viewInfo('modi','/xgxt/stu_ActiveInfo.do')"
							style="width:80px">
							修 改
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="viewInfo('del','/xgxt/del_stu_ActiveInfo.do')"
							style="width:80px">
							删 除
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:equal>
						<button class="button2"
							onclick="impAndChkData();"
							style="width:80px">
							导入数据
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="dataExport()" style="width:80px">
							导出数据
						</button>
					</div>
				</center>
		</html:form>
		<script language="javascript">
document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
document.getElementById("btn").style.width = "96%";
window.setInterval("initBTNTool('btn')",1);
</script>
	</body>
</html>
