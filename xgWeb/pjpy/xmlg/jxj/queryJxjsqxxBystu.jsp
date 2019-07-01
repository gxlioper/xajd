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
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript">
</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/hbsf/hbsfjs.js"></script>
		<html:form action="/xmlgpjpy" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：评奖评优 - 奖学金申请 - 申请结果查询
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="left" nowrap="nowrap">
							学号：
							<bean:write name="userName" scope="session" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							姓名：
							<bean:write name="userNameReal" scope="session" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							奖学金申请次数：
							${num }
							<input  id="search_go" onclick="refreshForm('pjpy_xmlg_queryJxjsqxxBystu.do')" style="display: none;"/>
						</td>
					</tr>
				</thead>
			</table>
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
						<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
<%--								<td nowrap>--%>
<%--									<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />--%>
<%--								</td>--%>
								<logic:iterate id="tit" name="topTr" offset="2">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand;"
								align="center" ondblclick="modiAndDel('pjpy_xmlg_modiJxjsqxx.do?operType=view&pkValue=','modi',780,650)">
<%--								<td>--%>
<%--									<input type="checkbox" id="cbv" name="cbv"--%>
<%--										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"--%>
<%--										<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>/>--%>
<%--								</td>--%>
								<logic:iterate id="v" name="s" offset="2">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>			

<%--				<div class="buttontool" id="btn"--%>
<%--							style="position: absolute;left:1%;top:100px" width="100%">--%>
<%--					<button type="button" class="button2" onclick="refreshForm('pjpy_xmlg_jxjsqDefault.do')"--%>
<%--						style="width:80px">--%>
<%--						增 加--%>
<%--					</button>--%>
<%--					&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--					<button type="button" class="button2" onclick="modiAndDel('pjpy_xmlg_modiJxjsqxx.do?pkValue=','modi',780,650)"--%>
<%--						style="width:80px">--%>
<%--						修 改--%>
<%--					</button>--%>
<%--					&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--					<button type="button" class="button2" onclick="deldata('pjpy_xmlg_stuJxjsqDelete.do')"--%>
<%--						style="width:80px">--%>
<%--						删 除--%>
<%--					</button>--%>
<%--				</div>				--%>
		</html:form>
<%--		<script language="javascript">--%>
<%--			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;--%>
<%--			document.getElementById("btn").style.width = "96%";--%>
<%--			window.setInterval("initBTNTool('btn')",1);--%>
<%--		</script>--%>
		<logic:present name="deleted">
			<logic:equal value="yes" name="deleted">
				<script>
					alert('操作成功!');
					document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
			<logic:equal value="no" name="deleted">
				<script>
					alert('操作失败!');
					document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
