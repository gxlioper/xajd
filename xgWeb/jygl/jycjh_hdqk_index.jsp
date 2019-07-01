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
		<meta name="Copyright" content="������� zfsoft" />
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
				alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
				return false;
			}else if (confirm("ȷ��Ҫ�޸ĸ���������")) {
				var pk=curr_row.getElementsByTagName("input")[0].value;
				showTopWin('/xgxt/jyglInfo.do?method=modi_hdqk&pk='+pk,650,550);
			}
		}
		
		function hdqk_del(){
			if (curr_row == null) {
				alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
				return false;
			}else if (confirm("ȷ��Ҫ�޸ĸ���������")) {
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
					��ǰ����λ�ã���ҵ���� - ��ҵ�ٽ��� - ������¼
				</div>
			</div>
			<fieldset>
				<legend>
					��ѯ����
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="right">
								���ڣ�
								<html:text style="cursor:hand; width:75px;" styleId="dateF"
									property="rq" onclick="return showCalendar('dateF','y-mm-dd');"
									readonly="readonly" />
							</td>
							<td width="10" align="center" valign="middle" rowspan="3">
								<input type="hidden" name="go" value="a" />
								<button class="button2" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/jyglInfo.do?method=searchHdqk&doType=search')">
									�� ѯ
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
					δ�ҵ��κμ�¼��
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						��ǰ��¼����
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
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
						�� ��
					</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="hdqk_modi()" style="width:80px">
						�� ��
					</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="hdqk_del()" style="width:80px">
						ɾ ��
					</button>
				</div>
			</logic:equal>
			<logic:notEmpty name="message">
				<logic:equal name="message" value="del_success">
					<script>alert("ɾ���ɹ�!")</script>
				</logic:equal>
				<logic:equal name="message" value="del_fail">
					<script>alert("ɾ��ʧ��!")</script>
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
