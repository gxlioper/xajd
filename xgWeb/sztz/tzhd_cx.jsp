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
	<body onload="xyDisabled('xy')">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<html:form action="/sztz_hd_cx" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：
					<bean:write name="tips" scope="request" />
				</div>
			</div>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType1" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
				
			<fieldset>
				<legend>
					查询条件
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left" nowrap>
								学年：
								<html:select property="xn" style="width:100px" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;学期：
								<html:select property="xq" style="width:90px" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm"
									style="width:230px;background-color:#FFFFFF" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input id="chgMode" type="checkbox"
									style="border:0px;display:none" />
							</td>
								
							<td width="10" align="center" valign="middle" rowspan="2">
								<input type="hidden" name="go" value="a" />
									<button class="button2" id="search_go" style="height: 40px"
										onclick="allNotEmpThenGo('/xgxt/sztz_hd_cx.do')">
										查询
									</button>							
							</td>
						</tr>
						<tr>
						<td align="left" nowrap>
								科目类别：
								<html:select property="kmdm"
									style="width:230px;background-color:#FFFFFF" styleId="kmdm">
									<html:option value=""></html:option>
									<html:options collection="kmdmList" property="kmdm"
										labelProperty="kmm" />
								</html:select>
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
			<logic:present name="check">
			
				<fieldset>
					<legend>
						记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：双击一行可以查看相信信息，并可以改变审核状态；单击表头可以排序</font>
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
						 <tr onclick="rowOnClick(this)"  
                             style="cursor:hand;background-color:
                             <logic:iterate id="v" name="s" offset="0" length="1">
                             <bean:write name="v"/>
                             </logic:iterate>
						    "ondblclick="checkTzhd('/xgxt/sztz_hd_check.do?doType=check&xmdm=')">
								<td>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
				</logic:present>
				<logic:notPresent name="check">				
				<fieldset>
					<legend>
						记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：双击一行可以查看相信信息；单击表头可以排序</font>						
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
						 <tr onclick="rowOnClick(this)" style="cursor:hand" ondblclick="checkTzhd('/xgxt/sztz_hd_view.do?doType=check&xmdm=')">
						  <td>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
				</logic:notPresent>
			</logic:notEmpty>
			<br/><br/><br/>
			<logic:notPresent name="check">
				<div class="buttontool" id="btn"
								style="position: absolute;left:1%;top:100px" width="100%">					
					<logic:equal value="yes" name="writeAble" scope="request">					
					<button class="button2"
						onclick="showTopWin('/xgxt/sztz_hd_sb.do','800','600')">
						增 加
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="modiTzhd('/xgxt/sztz_hd_sb.do?doType=modi&xmdm=');">
						修 改
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="modiTzhd('/xgxt/sztz_hd_sb.do?doType=del&xmdm=')">
						删 除
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2"
							onclick="impAndChkData();"
						style="width:80px">
						导入数据
					</button>
					</logic:equal>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="dataExport()" style="width:80px">
						导出数据
					</button>
					
				</div>	
				</logic:notPresent>		
			<div id="tmpdiv"></div>
		</html:form>
		<logic:equal value="ok" name="isEXIST">
         		<script language="javascript">
	         		alert("操作成功！");
	         	</script>
	    </logic:equal>
	        <logic:equal value="no" name="isEXIST">
         		<script language="javascript">
	         		alert("操作失败！");
	         	</script>
	        </logic:equal>
	        <logic:equal value="ischeck" name="isEXIST">
         		<script language="javascript">
	         		alert("该项目已通过审核,不能再进行修改、删除操作！");
	         	</script>
	        </logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
