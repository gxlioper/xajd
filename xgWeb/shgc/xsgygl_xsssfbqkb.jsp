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
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="js/xsgyglFunction.js"></script>
		<script type="text/javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/init_page_info.do?doType=ssfb" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：
					<bean:write name="tips" scope="request" />
				</div>
			</div>
			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<fieldset>
				<legend>
					基本参数
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
					<tr>
							<td align="left" nowrap>
							年级：
								<html:select  property="nj" style="width:90px" styleId="nj" onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj" labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" style="width:180px" styleId="xy"
									onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm" labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;专业：
								<html:select property="zydm" style="width:180px" styleId="zy"
									onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm" labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;学号：
									<html:text  property="xh" style="width: 80px" styleId="xh"/>
							</td>
							<td width="10" rowspan="3" align="center" valign="middle">
								<input type="hidden" name="go" value="go" />
								<button class="button2" style="height:40px" id="search_go" 
										onclick="allNotEmpThenGo('/xgxt/init_page_info.do?doType=ssfb')">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								班级：
								<html:select property="bjdm" style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
								</html:select>
								<logic:equal value="ff" name="aa">
								&nbsp;&nbsp;楼栋名称：
								<html:select property="lddm" style="width:90px" styleId="lddm">
									<html:option value=""></html:option>
									<html:options collection="ldList" property="lddm" labelProperty="ldmc" />
								</html:select>
								</logic:equal>
								<logic:present name="showXBEMY">
								&nbsp;&nbsp;政治面貌：
								<html:select property="zzmm" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="zzmmList" property="zzmmdm"
											labelProperty="zzmmmc" />
								</html:select>								
                                 &nbsp;&nbsp;民族：
                                 <html:select property="mz"
										style="width:80px">
										<html:option value=""></html:option>
										<html:options collection="mzList" property="mzdm"
											labelProperty="mzmc" />
								</html:select>
                                 &nbsp;&nbsp;来源地：
                                <html:text property="syd" style="width:80px"></html:text>
								</logic:present>
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
								<td align="center" onclick="tableSort(this)">学号</td>
								<td align="center" onclick="tableSort(this)">姓名</td>
								<td align="center" onclick="tableSort(this)"><bean:message key="lable.xsgzyxpzxy" /></td>
								<td align="center" onclick="tableSort(this)">专业</td>
								<td align="center" onclick="tableSort(this)">班级</td>
								<td align="center" onclick="tableSort(this)">宿舍号</td>								
								<td align="center" onclick="tableSort(this)">辅导员</td>
								<logic:notPresent name="showXBEMY">
								<td align="center" onclick="tableSort(this)">室长</td>
								<td align="center" onclick="tableSort(this)">层长</td>
								<td align="center" onclick="tableSort(this)">楼长</td>
								</logic:notPresent>
								<td align="center" onclick="tableSort(this)">床位号</td>
							</tr>
						</thead>
						 <tbody id="rsTable">
    						<logic:present name="rs">
      							<logic:iterate id="s" name="rs">
									<tr onclick="rowOnClick(this)" style="cursor:hand" ondblclick="viewInfo('modi','/xgxt/stu_dromInfo.do')">
         								 <td><input type="hidden" value="<bean:write name="s" property="xh"/>"/></td>
          								 <td><bean:write name="s" property="xh" /></td>
          								 <td><bean:write name="s" property="xm" /></td>
          								 <td><bean:write name="s" property="xymc" /></td>
          								 <td><bean:write name="s" property="zymc" /></td>
          								 <td><bean:write name="s" property="bjmc" /></td>
          								 <td><bean:write name="s" property="ssbh" /></td>
          								 <td><bean:write name="s" property="fdyxm" /></td>
          								 <logic:notPresent name="showXBEMY">
          								 <td><bean:write name="s" property="szxm" /></td>
          								 <td><bean:write name="s" property="czxm" /></td>
          								 <td><bean:write name="s" property="lzxm" /></td>
          								 </logic:notPresent>
          								 <td><bean:write name="s" property="cwh" /></td>
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
						<button class="button2" onclick="viewInfo('add','/xgxt/stu_dromInfo.do')"
							style="width:80px">
							增 加
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="viewInfo('modi','/xgxt/stu_dromInfo.do')"
							style="width:80px">
							修 改
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="viewInfo('del','/xgxt/del_stu_dromDistribut.do')"
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
