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
	<script language="javascript">	
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
	function onShow(){
		var xydm = $("xyV").value;
		if($("userType").value == "xy"){
			for(var i=0;i<$("xy").options.length;i++){
				if($("xy").options[i].value == xydm){
					$("xy").options[i].selected = true;
				}
			}
			$("xy").disabled = true;
		}
	}
	
	jQuery(function(){
		onShow();
	})
	
	</script>
	<body >
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getJxLdjzList.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getJxglDAO.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/ArmyStuInfo" method="post">
			<input type="hidden" name="njV" id="njV" />
			<input type="hidden" name="ndV" id="ndV" />
			<input type="hidden" name="xyV" id="xyV" value="<bean:write name="xydm" scope="request"/>"/>
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="lddmV" id="lddmV" />
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：<bean:write name="title" />
				</div>
			</div>
			<div class="rightcontent">
				<logic:present name="isBz">
					<p align="center">
						你不是班长，本模块只能由班长访问！！
					</p>
				</logic:present>
				<logic:present name="isBzr">
					<logic:equal name="isBzr"value="no">
						<p align="center">
							你不是班主任，本模块只能由班主任访问！！
						</p>
					</logic:equal>
				</logic:present>
				<logic:notPresent name="isBz">
				<logic:equal name="isBzr"value="yes">
				<fieldset>
					<legend>
						查 询
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									项目申请年度：
									<html:select property="nd" style="width:80px">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
								<td width="10" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/bjtsxm.do?method=bjtsxmJs')">
										查询
									</button>
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
								<tr onclick="rowOnClick(this);" style="cursor:hand"
									ondblclick="showOpenWindow('/xgxt/bjtsxm.do?method=bjtsxmJsjd&type=view&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value,680,600)">
									<td align="center">
										<bean:write name="s" property="xmmc" />
										<input type="hidden" name="xmdm"
											value="<bean:write name="s" property="xmdm"/>" />
									</td>
									<td align="center">
										<bean:write name="s" property="bzxm" />
									</td>
									<td align="center">
										<bean:write name="s" property="nj" />
									</td>
									<td align="center">
										<bean:write name="s" property="xymc" />
									</td>
									<td align="center">
										<bean:write name="s" property="bjmc" />
									</td>
									<td align="center">
										<bean:write name="s" property="xmsqsj" />
									</td>
									<td align="center">
										<bean:write name="s" property="bzrsh" filter="false" />
									</td>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<logic:equal name="userType" value="teacher">
							<button type="button" class="button2"
								onclick="if(curr_row == null){alert('请选择要修改的信息!');return false;}showOpenWindow('/xgxt/bjtsxm.do?method=bjtsxmJsjd&type=edit&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value,680,600)"
								style="width:80px">
								审 核
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:equal>
						<logic:equal name="userType" value="stu">
							<button type="button" class="button2"
								onclick="if(curr_row == null){alert('请选择要填写的信息!');return false;}showOpenWindow('/xgxt/bjtsxm.do?method=bjtsxmJsjd&type=edit&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value,680,600)"
								style="width:80px">
								填写进度
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
								onclick="if(curr_row == null){alert('请选择要删除的信息!');return false;}refreshForm('/xgxt/bjtsxm.do?method=bjtsxmJsjd&type=del&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value)"
								style="width:80px">
								删 除
							</button>
						</logic:equal>
					</div>
				</center>
				<div id="tmpdiv"></div>
				</logic:equal>
				</logic:notPresent>
			</div>
		</html:form>
		<logic:equal value="yes" name="result">
			<script>
				alert("操作成功!");
				window.document.getElementById("search_go").click();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("该项目已经通过班主任审核，无法删除");
				window.document.getElementById("search_go").click();
			</script>
		</logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
