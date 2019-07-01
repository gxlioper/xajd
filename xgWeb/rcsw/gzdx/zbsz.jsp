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
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="/xgxt/js/function.js"></script>
	<script language="javascript" src="/xgxt/js/jsFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="/xgxt/js/pjpy/pjpy_zjsyzy.js"></script>
	<script type="text/javascript">
		function queryData(){	
			refreshForm('/xgxt/zbrygl.do?method=zbsz&doType=query');
		}
		function setZbry(){
			var num = 0;
			var pk = '';
			var pks = document.getElementsByName('pkV');
			for(var i=0; i<pks.length; i++){
				if(pks[i].checked == true){
					num++;
					pk +=pks[i].value; 
				}
			}
			if(num == 0){
				alert("请勾选你要操作的记录！");
				return  false;
			}else if(num > 1){
				alert("一次只能操作一条记录！");
				return  false;
			}else{
				showTopWin('/xgxt/zbrygl.do?method=zbsz&doType=zbsz&pk='+pk,'850','600');
			}
		}
		
 		function getRqVal(name){
			var rq=document.getElementById(name).value;
			if (rq!=""){
				rqs=rq.split("-");
				rq="";
				for (var i=0;i<rqs.length;i++){
					rq+=rqs[i];
				}
				document.getElementById(name).value=rq;
			}
		}
	</script>
	<body>
		<center>
			<html:form action="/zbrygl" method="post">
				<logic:equal value="no" name="view">
					<br>
					<br>
					<br>
					<p align="center">
						<font color="red" size="2">该页面只允许教师和学生管理员访问！</font>
					</p>
				</logic:equal>
				<logic:equal value="wqy" name="view">
					<br>
					<br>
					<br>
					<p align="center">
						<font color="red" size="2">
						您的学生管理员权限没启用！
						</font>
					</p>
				</logic:equal>
				<logic:notEqual value="no" name="view">
				<logic:notEqual value="wqy" name="view">
					<div class="title">
						<div class="title_img" id="title_m">
							当前所在位置：日常事务 - 值班人员管理 - 值班设置
						</div>
					</div>
					<fieldset>
						<legend>
							查 询
						</legend>
						<table width="100%" class="tbstyle">
							<thead>
								<tr>
									<td align="left">
										使用部门：
										<html:select property="bm" style="width:200px">
											<html:option value="">--请选择--</html:option>
											<html:options property="bmdm" labelProperty="bmmc"
												collection="bmList" />
										</html:select>
										
										&nbsp;&nbsp;&nbsp; 预约场地：
										<html:select property="cddm" style="width:180px">
											<html:option value="">--请选择--</html:option>
											<html:options property="dm" labelProperty="mc"
												collection="cdList" />
										</html:select>
										&nbsp;&nbsp;&nbsp;预约日期：
										<html:text property="yyrq" styleId="yyrq" readonly="true" style="width:100px"
											onclick="this.value='';return showCalendar('yyrq','y-mm-dd');"
											onblur="getRqVal('yyrq')"></html:text>
									</td>
									<td width="10" rowspan="2" align="center" valign="middle" style="40px">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="button2"  id="search_go"
											onclick="queryData();">
											查询
										</button>
									</td>
								</tr>
								<tr>
									<td align="left">
										是否已设置值班人员：
										<html:select property="zbry">
											<html:option value="">--请选择--</html:option>
											<html:option value="wsz">未设置</html:option>
											<html:option value="ysz">已设置</html:option>
										</html:select>
										&nbsp;&nbsp;&nbsp;值班人姓名：
										<html:text property="zbryxm"></html:text>
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
								<font color="blue">提示：单击表头可以排序</font>
							</legend>
							<table width="100%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
										<td align="center">
											选择
										</td>
										<logic:iterate id="tit" name="topTr" offset="1">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" style="cursor:hand"
										ondblclick="chkView()">
										<td align="center">
											<input type="checkbox" name="pkV"
												value="<bean:write name="s" property="guid"/>">
										</td>
										<td align="center">
											<bean:write name="s" property="bmmc" />
										</td>
										<td align="center">
											<bean:write name="s" property="cdmc" />
										</td>
										<td align="center">
											<bean:write name="s" property="yyrq" />
										</td>
										<td align="center">
											<bean:write name="s" property="yysjd" />
										</td>
										<td align="center">
											<bean:write name="s" property="fzr" />
										</td>
										<td align="center">
											<bean:write name="s" property="lxdh" />
										</td>
										<td align="center">
											<bean:write name="s" property="zbry" />
										</td>
										<td align="center">
											<bean:write name="s" property="zbdh" />
										</td>
									</tr>
								</logic:iterate>
							</table>
						</fieldset>
						<TABLE width="99%" id="rsTable" class="tbstyle">
								<TR>
									<TD height=3></TD>
								</TR>
								<TR>
									<TD>
										<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=rcswgzdxForm"></jsp:include>
									</TD>
								</TR>
								<TR>
									<TD height=3></TD>
								</TR>
						</TABLE>
						<br />
						<br />
					</logic:notEmpty>
					<center>
						<logic:notEqual name="isFdy" value="true">
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<button type="button" class="button2" onclick="setZbry()" style="width:100px">
								设置值班人员
							</button>
						</div>
						<script language="javascript">
							document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
							document.getElementById("btn").style.width = "98%";
							window.setInterval("initBTNTool('btn')",1);
						</script>
						</logic:notEqual>
					</center>
				</logic:notEqual>
				</logic:notEqual>
			</html:form>
			<div id="tmpdiv"></div>
		</center>
	</body>
	
	<logic:equal value="true" name="result">
		<script language="javascript">
			alert('操作成功！');
		</script>
	</logic:equal>
	<logic:equal value="false" name="result">
		<script language="javascript">
			alert('操作失败！');
		</script>
	</logic:equal>
</html>
