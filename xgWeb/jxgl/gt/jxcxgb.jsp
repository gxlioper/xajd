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
	<body onload="xyDisabled('xy');">
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script language="javascript" src="/xgxt/js/AjaxFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getJxglDAO.js'></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<script language="javascript">
		function delGbxx(){
		if(curr_row == null){
			alert('请选择要删除的信息!');
			return false;
		}
		refreshForm('/xgxt/jxglgt.do?method=jxcxgbOne&type=del&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value);
		}
		
		function chBzList(jxnd){
		getJxglDAO.getLdList(jxnd,function(data){
		if (data != null && typeof data == 'object') {
			var objId = "lddm";
			if($(objId) && $(objId).tagName.toLowerCase() == "select"){
				DWRUtil.removeAllOptions(objId);			
				DWRUtil.addOptions(objId,data,"bzdm","bzmc");		
				$(objId).options[0].value = "";
				if(objId + "V"){
					if($(objId +"V").value != "" && $(objId + "V").value!= null){
						for(var i = 0;i < $(objId).options.length; i++){
							if($(objId).options[i].value == $(objId +"V").value){
								$(objId).options[i].selected = true;
								return true;
							}
						}
					}
					}
				}
			}else{
				showMsgWin("有错误出现：远程数据读取失败！");
			}
		});
		}
		
	function chDwList(dwdm){
		var jxnd = $("jxnd").value;
		if($("xxdm") == "13022"){
		var gblx = $("gblx").value;
			if(gblx == "jg"){
				getJxglDAO.getzwList(dwdm,function(data){
					if (data != null && typeof data == 'object') {
						var objId = "jxzw";
						if($(objId) && $(objId).tagName.toLowerCase() == "select"){
							DWRUtil.removeAllOptions(objId);			
							DWRUtil.addOptions(objId,data,"zwdm","zwmc");		
						}else{
							showMsgWin("有错误出现：远程数据读取失败！");
						}
					}
				});
			}else if(gblx == "cx"){
				getJxglDAO.getBzzwList(dwdm,function(data){
				if (data != null && typeof data == 'object') {
					var objId = "jxzw";
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);			
						DWRUtil.addOptions(objId,data,"zwdm","zwmc");		
						}
					}else{
						showMsgWin("有错误出现：远程数据读取失败！");
					}
				});
			}
		}else{
			getJxglDAO.getBzzwList(dwdm,function(data){
				if (data != null && typeof data == 'object') {
					var objId = "jxzw";
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);			
						DWRUtil.addOptions(objId,data,"zwdm","zwmc");		
						}
					}else{
						showMsgWin("有错误出现：远程数据读取失败！");
					}
				});
		}
	}
	function chgb(){
		var jxnd = $("jxnd").value;
		if($("xxdm") == "13022"){
			var gblx = $("gblx").value;
			if(gblx == "jg"){
				DWRUtil.removeAllOptions("jxzw");
				getJxglDAO.getdwList(function(data){
				if (data != null && typeof data == 'object') {
					var objId = "lddm";
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);			
						DWRUtil.addOptions(objId,data,"dwdm","dwmc");		
					}else{
						showMsgWin("有错误出现：远程数据读取失败！");
					}
				}
			});
			}else if(gblx == "cx"){
				DWRUtil.removeAllOptions("jxzw");
				getJxglDAO.getLdList(jxnd,function(data){
				if (data != null && typeof data == 'object') {
					var objId = "lddm";
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);			
						DWRUtil.addOptions(objId,data,"bzdm","bzmc");		
					}else{
						showMsgWin("有错误出现：远程数据读取失败！");
					}
				}
			});
			}
		}else{
			DWRUtil.removeAllOptions("jxzw");
			getJxglDAO.getLdList(jxnd,function(data){
			if (data != null && typeof data == 'object') {
				var objId = "lddm";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"bzdm","bzmc");		
				}else{
					showMsgWin("有错误出现：远程数据读取失败！");
				}
			}
			});
		}
	}
		
	function jxgbPrint(){
		var url = "/xgxt/jxglgt.do?method=jxgbPrint&nd="+$("jxnd").value;
		window.open(url);
	}
		</script>
		<html:form action="/jxglgt?method=jgxx" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：<bean:write name = "title" />
				</div>
			</div>
			<div class="rightcontent">

				<fieldset>
					<legend>
						查 询
					</legend>
					<input type="hidden" name="xxdm" id="xxdm" value="<bean:write name="xxdm"/>"/>
					<input type="hidden" name="xyV" id="xyV" />
					<input type="hidden" name="zyV" id="zyV" />
					<input type="hidden" name="bjV" id="bjV" />
					<input type="hidden" name="lddmV" id="lddmV" />
					<input type="hidden" name="ljdmV" id="ljdmV" />
					<input type="hidden" name="pjdmV" id="pjdmV" />
					<input type="hidden" id="userType" name="userType"
						value="<bean:write name="userType" scope="request"/>" />
					<input type="hidden" id="tableName" name="tableName"
						value="<bean:write name="tableName" scope="request"/>" />
					<input type="hidden" id="realTable" name="realTable"
						value="<bean:write name="realTable" scope="request"/>" />
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									&nbsp;职工编号：
									<html:text property="zgh" style="width:85px"></html:text>
									&nbsp;&nbsp;&nbsp;姓名：
									<html:text property="xm" style="width:85px"></html:text>
									&nbsp;负责年级：
									<html:select property="jxnd" styleId="jxnd" onchange="chgb()">
									<html:options collection="njList" labelProperty="njmc" property="njdm"/>
									</html:select>
									<logic:equal value="13022" name="xxdm">
									&nbsp;干部类型：
									<html:select property="gblx" style="width:100px"
										onchange="chgb()">
										<html:option value="jg">机关人员</html:option>
										<html:option value="cx">参训人员</html:option>
									</html:select>
									</logic:equal>
								</td>
								<td width="10" align="center" rowspan="2" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2" style="height:40px" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/jxglgt.do?method=jxcxgb')">
										查询
									</button>
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;军训单位：
									<html:select property="lddm" style="width:180px" styleId="lddm" onchange="chDwList(this.value)">
										<html:options collection="dwList" labelProperty="bzmc" property="bzdm" />
									</html:select>
									&nbsp;军训职位：
									<html:select property="jxzw" styleId="jxzw">
										<html:options collection="zwList" labelProperty="zwmc" property="zwdm" />
									</html:select>
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
							记录数：
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<logic:equal value="13022" name="xxdm">
									<tr onclick="rowOnClick(this);" style="cursor:hand"								
										ondblclick="showTopWin('/xgxt/jxglgt.do?method=jxcxgbOne&type=view&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value+'&lx='+$('gblx').value,550,450)">
										<td>
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="1" length="1">
												<bean:write name="v" />
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="2">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:equal>
								<logic:notEqual value="13022" name="xxdm">
									<tr onclick="rowOnClick(this);" style="cursor:hand"								
										ondblclick="showTopWin('/xgxt/jxglgt.do?method=jxcxgbOne&type=view&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value,550,450)">
										<td>
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="1" length="1">
												<bean:write name="v" />
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="2">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:notEqual>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<logic:equal value="yes" name="writeAble" scope="request">
							<button type="button" class="button2"
								onclick="showTopWin('/xgxt/jxglgt.do?method=jxcxgbOne&type=add',550,450)"
								style="width:80px">
								增 加
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<logic:equal value="13022" name="xxdm">
								<button type="button" class="button2" 
									onclick="if(curr_row == null){alert('请选择要修改的信息!');return false;}showTopWin('/xgxt/jxglgt.do?method=jxcxgbOne&type=edit&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value+'&lx='+$('gblx').value,550,450)"
									style="width:80px">
									修 改
								</button>
							</logic:equal>
							<logic:notEqual value="13022" name="xxdm">
								<button type="button" class="button2" 
									onclick="if(curr_row == null){alert('请选择要修改的信息!');return false;}showTopWin('/xgxt/jxglgt.do?method=jxcxgbOne&type=edit&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value,550,450)"
									style="width:80px">
									修 改
								</button>
							</logic:notEqual>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="delGbxx();"
								style="width:80px">
								删 除
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<logic:equal value="13022" name="xxdm">
								<button type="button" class="button2" onclick="jxgbPrint();"
									style="width:80px">
									打印通讯录
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
							</logic:equal>
							<button type="button" class="button2" onclick="impAndChkData();"
								style="width:80px">
								导入数据
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="dataExport()" style="width:80px">
								导出数据
							</button>
						</logic:equal>
					</div>
				</center>
				<div id="tmpdiv"></div>
			</div>
		</html:form>
		<logic:equal value="yes" name="result">
			<script>
				window.document.getElementById("search_go").click();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				window.document.getElementById("search_go").click();
			</script>
		</logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
