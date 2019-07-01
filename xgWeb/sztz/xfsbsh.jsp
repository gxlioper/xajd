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
		type="text/css" media="all"/>
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body onload="xyDisabled('xy');removeXnXq()">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>		
        <script language="javascript" src="js/AjaxFunction.js"></script>
		
		<html:form action="/sztz_data_search" method="post">
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：
					<bean:write name="tips" scope="request" />
				</div>
			</div>
			<div class="rightcontent">

				<fieldset>
					<legend>
						查 询
					</legend>
					<input type="hidden" name="zyV" id="zyV" />
			        <input type="hidden" name="bjV" id="bjV" />					
					<input type="hidden" id="userType" name="userType"
						value="<bean:write name="userType" scope="request"/>" />
					<input type="hidden" id="tableName" name="tableName"
						value="<bean:write name="tableName" scope="request"/>" />
					<input type="hidden" id="act" name="act"
						value="<bean:write name="act" scope="request"/>" />
					<input type="hidden" id="realTable" name="realTable"
						value="<bean:write name="realTable" scope="request"/>" />
					<input type="hidden" id="pk" name="pk"
						value="<bean:write name="pk" scope="request"/>" />
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									年级：
									<html:select property="nj" style="background-color:#FFFFFF" onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学年：
									<html:select property="xn" style="background-color:#FFFFFF" styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学期：
									<html:select property="xq" style="background-color:#FFFFFF" styleId="xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;学号：
									<html:text property="xh" style="width:85px"></html:text>
									&nbsp;&nbsp;&nbsp;拓展活动(项目)：
									<html:text property="xmmc" ></html:text>
								</td>
								<td width="10" rowspan="4" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button class="button2" style="height:40px" id="search_go"
										onclick="sztzAllNotEmpThenGo('/xgxt/sztz_xfsb_sh.do')">
										查询
									</button>
								</td>
									
							</tr>
							<tr>
								<td align="left" nowrap>
									<bean:message key="lable.xsgzyxpzxy" />：
									<html:select property="xydm" style="background-color:#FFFFFF" styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc"/>
									</html:select>
									&nbsp;&nbsp;专业：
									<html:select property="zydm" style="background-color:#FFFFFF" styleId="zy"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
									&nbsp;&nbsp;班级：
									<html:select property="bjdm" style="background-color:#FFFFFF" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
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
						<font color="blue">提示：双击一行可以查看相信信息，并可以改变审核状态；单击表头可以排序</font>
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand" >
								<logic:iterate id="tit" name="topTr" offset="2">
									<td  id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
						 <tr onclick="rowOnClick(this)"
								style="cursor:hand;background-color:
                          <logic:iterate id="v" name="s" offset="0" length="1">
                           <bean:write name="v"/>
                            </logic:iterate>
                             "ondblclick="sztzChkPriseOne('/xgxt/sztz_xfsb_sh.do?act=viewOne&pkValue=')" >
								<td  >
									<logic:iterate id="v" name="s" offset="1" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="3">
									<td >
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>				
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>			
				<div id="tmpdiv"></div>
			  <logic:equal value="yes" name="writeAble" scope="request">			  				
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">							
						<logic:equal name="realTable" value="sztz_xfsbb">	
						<logic:present name="isXCXY">
						<button class="button2"
								onclick="xcxyXfSbAutoChk('/xgxt/sztz_xfsb_autochk.do')"
								style="width:80px">
								整批审核
						</button>
						</logic:present>
						<logic:notPresent name="isXCXY">
						<button class="button2"
								onclick="sztzXfSbAutoChk('/xgxt/sztz_xfsb_autochk.do')"
								style="width:80px">
								整批审核
						</button>
						</logic:notPresent>
						</logic:equal>	
						<logic:equal name="realTable" value="sztz_xscjxxb">	
						<button class="button2"
								onclick="impAndChkData();"
								style="width:80px">
								导入数据
						</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="sztzDataExport()" style="width:80px">
								导出数据
						</button>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="xlsDown/<bean:write name="realTable" scope="request"/>.xls" target="_blank">模板下载</a>
				        </logic:equal>	
				        </div>					
				   </center>								
			</logic:equal>
			<div id="tmpdiv"></div>
			<logic:present name="autoChk">
				<logic:equal name="autoChk" value="ok" scope="request">
				<script language="javascript">
                   alert("自动审核完成！");
                   allNotEmpThenGo('/xgxt/sztz_xfsb_sh.do');
	             </script>
				</logic:equal>
				<logic:equal name="autoChk" value="no" scope="request">
				<script language="javascript">
                 alert("自动审核失败！");
 	              </script>
				</logic:equal>				
			</logic:present>
			</div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>

