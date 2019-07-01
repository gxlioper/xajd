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
	<script language="javascript">
		function hdqk_add(){
			showTopWin('/xgxt/jyglInfo.do?method=add_hdqk',650,550);
		}
		
		function hdqk_view(){
			var pk=curr_row.getElementsByTagName("input")[0].value;
			showTopWin('/xgxt/jyglInfo.do?method=view_hdqk&pk='+pk,650,550);
		}
		
		function hdqk_modi(){
			if (curr_row == null) {
				alert("请选择要修改的数据！\n（单击相应的行）");
				return false;
			}else if (confirm("确定要修改该行数据吗？")) {
				var pk=curr_row.getElementsByTagName("input")[0].value;
				showTopWin('/xgxt/jyglInfo.do?method=modi_hdqk&pk='+pk,650,550);
			}
		}
		
		function hdqk_del(){
			if (curr_row == null) {
				alert("请选择要修改的数据！\n（单击相应的行）");
				return false;
			}else if (confirm("确定要修改该行数据吗？")) {
				var pk=curr_row.getElementsByTagName("input")[0].value;
				refreshForm('/xgxt/jyglInfo.do?method=delete_hdqk&pk='+pk);
			}
		}
	</script>
	<body>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<script language="javascript" src="js/lrh_new_js.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/jyglInfo" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：就业管理 - 就业促进会 - 活动情况记录
				</div>
			</div>
			<fieldset>
				<legend>
					查询条件
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="right">
								日期：
								<html:text style="cursor:hand; width:75px;" styleId="dateF"
									property="rq" onclick="return showCalendar('dateF','y-mm-dd');"
									readonly="readonly" />
							</td>
							<td width="10" align="center" valign="middle" rowspan="3">
								<input type="hidden" name="go" value="a" />
								<button class="button2" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/jyglInfo.do?method=searchHdqk&doType=search')">
									查 询
								</button>
								<button class="button2" id="search_go1" style="display:none;"
									onclick="refreshForm('/xgxt/jyglInfo.do?method=searchHdqk&doType=search')">
								</button>
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					未找到任何记录！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						当前记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" ondblclick="hdqk_view()"
								style="cursor:hand">
								<logic:iterate id="v" name="s" offset="0">
									<input type="hidden" value="${v}"/>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="1">
									<td>
										${v}
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
			<logic:equal value="yes" name="writeAble" scope="request">
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:485px" width="100%">
					<button class="button2" onclick="hdqk_add()" style="width:80px">
						增 加
					</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="hdqk_modi()" style="width:80px">
						修 改
					</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="hdqk_del()" style="width:80px">
						删 除
					</button>
				</div>
			</logic:equal>
			<logic:notEmpty name="message">
				<logic:equal name="message" value="del_success">
					<script>alert("删除成功!")</script>
				</logic:equal>
				<logic:equal name="message" value="del_fail">
					<script>alert("删除失败!")</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
		<script language="javascript">
			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
			document.getElementById("btn").style.width = "98%";
			window.setInterval("initBTNTool('btn')",1);
		</script>
	</body>
</html>
