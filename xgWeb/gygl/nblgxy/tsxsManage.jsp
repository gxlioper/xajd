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
		<script type='text/javascript' src='/xgxt/dwr/interface/gyglShareData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>		
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/gygl/gyglFunction.js"></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>		
		<html:form action="/nblgxy_gygl" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />		
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：公寓管理 - 信息维护 - 特殊学生信息
				</div>
			</div>
			<fieldset>
				<legend>
					基本参数
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left" nowrap >								
								年级：
								<html:select property="nj" style="width:90px"
										 styleId="nj" onchange="initZyList();initBjList()">										
										<html:option value="">--请选择--</html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
								</html:select>
								 <bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xy" style="width:170px" styleId="xy" onchange="initZyList();initBjList()">									
									<html:option value="">--请选择--</html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								 专业：
								<html:select property="zy" style="width:150px" styleId="zy" onchange="initBjList()">
									<html:option value="">--请选择--</html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								班级：
								<html:select property="bj" style="width:160px" styleId="bj">
									<html:option value="">--请选择--</html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>	
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="go" />
								<button class="button2" style="height:40px" id="search_go" 
										onclick="allNotEmpThenGo('/xgxt/nblgxy_gygl.do?method=tsxsManage')">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap >							
								 楼栋：
								<html:select property="lddm" style="width:120px"
										 styleId="lddm" onchange="getSsbhLb()">										
										<html:option value="">--请选择--</html:option>
										<html:options collection="ldList" property="dm"
											labelProperty="mc" />
								</html:select>
								 寝室号：
								<html:select property="ssbh" style="width:120px" styleId="qsh">									
									<html:options collection="ssbhList" property="dm"
										labelProperty="mc" />
								</html:select>
								学号：
								<html:text property="xh" style="width: 120px" styleId="xh" />
								姓名：
								<html:text property="xm" style="width: 120px" styleId="xm" />
																			
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
								ondblclick="tsxsManageView()">
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
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<logic:equal value="yes" name="writeAble">
						<button class="button2" onclick="tsxsManageAdd()"
							style="width:80px">
							增 加
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="tsxsManageModi()"
							style="width:80px">
							修 改
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="tsxsDel()"
							style="width:80px">
							删 除
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:equal>
					</div>
				</center>
		</html:form>
		<script language="javascript">
document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
document.getElementById("btn").style.width = "96%";
window.setInterval("initBTNTool('btn')",1);
</script>
	</body>
	<script type="text/javascript">
	       function tsxsManageAdd(){
	            var url = "/xgxt/nblgxy_gygl.do?method=tsxsAdd";
	            showTopWin(url,"700","550");
	       }
	       function tsxsManageModi(){
	           if (curr_row == null) {
		          alert("请选要修改的记录！\n单击一行记录即可");
		          return false;
	           } else {
	            var url = "/xgxt/nblgxy_gygl.do?method=tsxsModi";
	            url +="&pkValue=";
	            url +=(curr_row.cells[0].getElementsByTagName("input"))[0].value;
	            showTopWin(url,"700","550");
	           }
	       }
	       function tsxsManageView(){       
	            var url = "/xgxt/nblgxy_gygl.do?method=tsxsView";
	            url +="&pkValue=";
	            url +=(curr_row.cells[0].getElementsByTagName("input"))[0].value;	            
	            showTopWin(url,"700","550");	          
	       }
	       function tsxsDel(){
	           if (curr_row == null) {
		          alert("请选要删除的记录！\n单击一行记录即可");
		          return false;
	           } else {
	              if(confirm("确定要删除该记录？")){
	                 var url = "/xgxt/nblgxy_gygl.do?method=tsxsDel";
	                 url +="&pkValue=";
	                 url +=(curr_row.cells[0].getElementsByTagName("input"))[0].value;
	                 refreshForm(url);
	              }else{	 
	                 return false;           	            
	              }
	          }
	       }

	</script>
</html>
