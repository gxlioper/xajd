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
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body>
		<script language="javascript">
			function modify(){
				var xh = "";
				var nd = "";
				var url = "shgc_tbxxwh.do?method=modTbxx";
				if(curr_row==null){
					alert('请选择要修改的记录！');
					return;
				}
				xh = curr_row.cells[1].innerText;
				nd = curr_row.cells[3].innerText;
				url += "&xh=";
				url += xh;
				url += "&nd=";
				url += nd;
				showTopWin(url,800,550);
			}
			
			function del(){
				var xh = "";
				var nd = "";
				var url = "shgc_tbxxwh.do?method=delTbxx";
				if(curr_row==null){
					alert('请选择要删除的记录！');
					return;
				}				
				xh = curr_row.cells[0].innerText;
				nd = curr_row.cells[2].innerText;
				url += "&xh=";
				url += xh;
				url += "&nd=";
				url += nd;
				if(confirm('您确定删除所选信息吗？')){
					refreshForm(url);
				}
			}
			
	function jiaofei(){
	var checkBoxArr = document.getElementsByName("checkVal");
	var selall = document.getElementById('selall');
	var flag = false;
	var jfbj = document.getElementById('jfbj').value;
	var temppk="";
	
	if(jfbj == ""){
		alert('请确认是否缴费!');
		return false;
	}
	
	if (confirm("确认要将这些学生的缴费标记设置为"+jfbj+"吗?")) {
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				temppk = temppk + checkBoxArr[i].value + "!!SplitSignOne!!";
				flag = true;
			}
		}
	
		document.getElementById("pkV").value = temppk;
		if(selall.checked){
			showTips('处理数据中，请等待......');
			refreshForm('/xgxt/shgc_tbxxwh.do?method=jfbjAll&go=go');
		
		} else {
			if(flag){
				showTips('处理数据中，请等待......');
				refreshForm('/xgxt/shgc_tbxxwh.do?method=jfbjAll&go=go');
			}
			else{
				alert('请选择要缴费标记的学生!');
			}
		}
	}
	}
		</script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>

		<html:form action="/shgc_tbxxwh.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置:保险管理 - 保险信息 - 投保信息维护
				</div>
			</div>
			<logic:notEqual value="stu" name="userType">
				<div class="rightcontent">
					<fieldset>
						<legend>
							查 询
						</legend>
						<input type="hidden" name="zyV" id="zyV" />
						<input type="hidden" name="bjV" id="bjV" />
						<input type="hidden" name="pkV" id="pkV" />
						<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="session"/>" />
						<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm" scope="session"/>" />
						<input type="hidden" id="tableName" name="tableName" value="view_xsbxxx"/>
						<input type="hidden" id="realTable" name="realTable" value="xsbxb"/>
						<input type="hidden" id="updateJfbj" name="updateJfbj" value="${updateJfbj}"/>
							<input type="hidden" id="xxdm2" name="xxdm2" value="no" />
						<table width="100%" class="tbstyle">
							<thead>
								<tr>
									<td align="left">
										年级：
										<html:select property="nj" style="width:80px"
											onchange="initZyList();initBjList()" styleId="nj">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>
										&nbsp;&nbsp;年度：
										<html:select property="nd" style="width:100px" styleId="nd">
											<html:options collection="xnList" property="nd"
												labelProperty="nd" />
										</html:select>							
										&nbsp;&nbsp;学号：
										<html:text property="xh" style="width:85px"></html:text>
										<font color="red">*</font>&nbsp;&nbsp;确认缴费：
										<html:select property="jfbj" style="width:150px">
											<html:option value=""></html:option>
											<html:option value="否">否</html:option>
											<html:option value="是">是</html:option>
										</html:select>
									</td>
									<td width="10" rowspan="2" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<input type="hidden" name="tab" id="tab" value="qtjxj" />
										<button class="button2" style="height:40px" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/shgc_tbxxwh.do?method=tbxxwhQue')">
											查询
										</button>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										<bean:message key="lable.xsgzyxpzxy" />：
										<html:select property="xydm" style="width:180px" styleId="xy"
											onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										<logic:notEqual value="xyhj" name="xyhjType">
										&nbsp;&nbsp;专业：
										<html:select property="zydm" style="width:180px" styleId="zy"
												onchange="initBjList()">
												<html:option value=""></html:option>
												<html:options collection="zyList" property="zydm"
													labelProperty="zymc" />
											</html:select>
										&nbsp;&nbsp;班级：
										<html:select property="bjdm" style="width:180px" styleId="bj">
												<logic:notEqual value="yes" name="isBzr">
													<html:option value=""></html:option>
												</logic:notEqual>
												<html:options collection="bjList" property="bjdm"
													labelProperty="bjmc" />
											</html:select>
										</logic:notEqual>
									</td>
								</tr>
							</thead>
						</table>
					</fieldset>
					<logic:empty name="rs">
						<p align="center">
							未找到任何记录！
						</p>
					</logic:empty>
					<logic:notEmpty name="rs">
						<fieldset>
							<legend>
								显示记录数：
								<bean:write name="rsNum" />
								&nbsp;
								<font color="blue"> 提示：双击一行可以查看详细信息；单击表头可以排序;按住Ctrl可以多选 </font>
							</legend>

							<table width="100%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
										<td>
											<input type="checkbox" id="selall" name="selall"
												onclick="selAll()">
										</td>
										<logic:iterate id="top" name="topTr" offset="0" length="1">
										<td id="<bean:write name="top" property="en"/>"
												onclick="tableSort(this)" nowrap>
											<bean:write name="top" property="cn" />		
										</td>
										</logic:iterate>
										<logic:iterate id="tit" name="topTr" offset="1">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>

									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this);" style="cursor:hand" ondblclick="modify()">	
									<td>
										<input type="checkbox" id="checkVal" name="checkVal" 
								 		value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>">
									</td>
										<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v" />" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<bean:write name="v" />
										</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
						</fieldset>
					</logic:notEmpty>
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<logic:equal value="yes" name="writeAble" scope="request">	
								&nbsp;&nbsp;
								<button class="button2" onclick="jiaofei();">
									缴费确认
								</button>							
								&nbsp;&nbsp;
								<button class="button2" onclick="showTopWin('shgc_tbxxwh.do?method=addTbxx',800,550)" style="width:80px">
									增 加
								</button>
								&nbsp;&nbsp;
								<button class="button2" onclick="modify()"
									style="width:80px">
									修 改
								</button>
								&nbsp;&nbsp;
								<button class="button2" onclick="del()"
									style="width:80px">
									删 除
								</button>								
							</logic:equal>
							<logic:equal value="yes" name="writeAble" scope="request">
								&nbsp;&nbsp;
								<button class="button2"
									onclick="impAndChkData();"
									style="width:80px">
									导入数据
								</button>
							</logic:equal>
							&nbsp;&nbsp;
							<button class="button2" onclick="document.forms[0].target = '_blank';refreshForm('shgc_tbxxwh.do?method=expData');document.forms[0].target = '_self';" style="width:80px">
								导出数据
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="xlsDown/.xls" target="_blank">模板下载</a>
						</div>
					</center>
				</div>
			</logic:notEqual>
		</html:form>
		<logic:equal name="result" value="true">
			<script language="javascript">
      				alert("操作成功！");
      				document.getElementById('search_go').click();
	  		</script>
		</logic:equal>
		<logic:equal name="result" value="false">
			<script language="javascript">
	  				alert("操作失败! ");
	  		</script>
		</logic:equal>
		<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "96%";
					window.setInterval("initBTNTool('btn')",1);
		</script>
		<logic:present name="updateShh">
				<script>
					alert(''+document.getElementById('updateJfbj').value);
				</script>
		</logic:present>
	</body>
</html>

