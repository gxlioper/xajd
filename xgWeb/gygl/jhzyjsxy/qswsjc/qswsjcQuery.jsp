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
	<body onload="xyDisabled('xy');bjLimit('bj')">
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/jhzy_gygl" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：公寓管理 - 信息维护 - 寝室卫生检查查询
				</div>
			</div>
			<div class="rightcontent">

				<fieldset>
					<legend>
						查 询
					</legend>
					<input type="hidden" id="checkedValue" name="checkedValue" value="<bean:write name = "checkedValue" />" />
					<input type="hidden" id="userType" name="userType"
						value="<bean:write name="userType" scope="request"/>" />
					<input type="hidden" id="isFdy" name="isFdy"
						value="<bean:write name="isFdy" scope="session"/>" />
					<input type="hidden" id="userName" name="userName"
						value="<bean:write name="userName" scope="session"/>"/>
					<input type="hidden" id="xxdm" name="xxdm"
						value="<bean:write name="xxdm" scope="session"/>"/>
						<input type="hidden" name="zyV" id="zyV" />
						<input type="hidden" name="bjV" id="bjV" />
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr >
									<td align="left">
									楼栋
									<html:select property="lddm" styleId="lddm"
										onchange="getLcList()" >
										<html:options collection="ldList" property="dm"
											labelProperty="mc" />
									</html:select>
									&nbsp;&nbsp;楼层
									<html:select property="lcdm" styleId="lc"
										onchange="getQshList2();" >
										<html:options collection="lcList" property="dm"
											labelProperty="mc" />
									</html:select>
									&nbsp;&nbsp;寝室
									<html:select property="qsh" styleId="qsh" >
										<html:options collection="qshList" property="dm"
											labelProperty="mc" />
									</html:select>	
									<td width="10" rowspan="2" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button class="button2" style="height:60px" id="search_go"
											onclick="to_search()">
											查询
										</button>
									</td>
								</tr>								
							<tr>
									<td align="left">
										卫生等级:
										<html:select property="guid" onchange="">
											<html:option value="">--请选择--</html:option>
											<html:option value="A">A</html:option>
											<html:option value="B">B</html:option>
											<html:option value="C">C</html:option>
											<html:option value="D">D</html:option>
										</html:select>
										检查时间:
										<html:text property="jcsj"  onblur="dateFormatChg(this)" style="cursor:hand;" onclick="return showCalendar('jcsj','y-mm-dd');"  readonly="true"></html:text>																					
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
							<font color="blue">提示：单击表头可以排序</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">									
									<logic:iterate name="topTr" id="tit">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" >
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr  style="cursor:hand">									
										<logic:iterate id="v" name="s" offset="0" length="1">
										<td  width="5%" nowrap="nowrap">
											<bean:write name = "v"/>	
										</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
										<td nowrap="nowrap" width="5%">
											<bean:write name = "v"/>											
										</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="2" length="1">
										<td nowrap="nowrap"  width="3%">
											<bean:write name="v" />
										</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="3" length="1">
										<td nowrap="nowrap"  width="5%">
											<bean:write name="v" />
										</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="4" length="1">
										<td nowrap="nowrap"  width="6%">
											<bean:write name="v" />
										</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="6" length="1">
										<td nowrap="nowrap"  width="6%">
											<bean:write name="v" />
										</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="7" length="1">
										<td nowrap="nowrap"  width="6%">
											<bean:write name="v" />
										</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="5" length="1">
										<td width="60%">
											<bean:write name="v" />
										</td>
										</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
                    <br>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
			</div>					
				</logic:notEmpty>	
					<center>
<%--					<logic:equal value="yes" name="writeAble" scope="request">--%>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<button class="button2" onclick="refreshForm('/xgxt/dormCheckManage.do?jgwh=yes');this.disabled=true"	>
								       结果维护
							 </button>
							 &nbsp;&nbsp;
<%--							 <button class="button2" onclick="refreshForm('/xgxt/jhzy_gygl.do?method=resultQuManage&go=go');this.disabled=true"	>--%>
<%--								       结果查询--%>
<%--							 </button>--%>
						</div>
<%--					</logic:equal>--%>
				</center>			
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
		<script>
		var lxids = document.getElementById("checkedValue").value.split('!!');
		    for(i = 0; i < lxids.length; i++){
			   if($(lxids[i])){
				  document.getElementById(lxids[i]).checked=true;
			   }
		    }
		function to_search(){
			 //var lddm= $("lddm").value;
		     //if(lddm==""){
		    //    alert("请选择楼栋！");
		    //    return false;
		    // }
		   refreshForm('/xgxt/jhzy_gygl.do?method=resultQuManage&go=go');
		   $("search_go").disabled=true
		}
		</script>
		<logic:equal value="ok" name="done">
<script type="text/javascript">
alert("操作成功！");
document.getElementById('search_go').click();   
</script>
</logic:equal>
<logic:equal value="no" name="done">
<script type="text/javascript">
alert("操作失败！");
document.getElementById('search_go').click();   
</script>
</logic:equal>
	</body>
</html>
