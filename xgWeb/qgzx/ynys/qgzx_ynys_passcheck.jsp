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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/BatAlert.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/xgutil.js"></script>	
	<script language="javascript" >		
		function check_user(){
			var userType1=document.all['userType'].value;
			if("xy"==userType1){
				document.getElementById('xy').disabled=true;
			}else{
				document.getElementById('xy').disabled=false;
			}
		}		
		
		function modify(){
			if(curr_row == null){
				alert("请单击您要修改的数据行！");
				return false;
			}
			var pkV = curr_row.cells[0].getElementsByTagName('input')[0].value;
			showTopWin("qgzxGwgl.do?method=zgxsgwxxxg&pkV="+pkV);
		}
		
		//导出数据
		function dataExport(){
			var url = "qgzxGwgl.do?method=exportZgxsxx";
			url += "&nj=" + val('nj');
			url += "&xn=" + val('xn');
			url += "&xq=" + val('xq');
			url += "&xh=" + val('xh');
			url += "&xm=" + val('xm');
			url += "&xydm=" + val('xy');
			url += "&zydm=" + val('zy');
			url += "&bjdm=" + val('bj');
			url += "&gwdm=" + val('gwdm');
			url += "&yrdwdm=" + val('yrdwdm');
			window.open(url);
		}
	</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	
	<body onload="check_user();xyDisabled('xy');">			
		<html:form action="/qgzxLogic" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置： 勤工助学 -  信息查询 - 审核通过学生查询
				</div>
			</div>
			<fieldset>
				<legend>
					查 询
				</legend>				
				<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="session"/>" />
				<input type="hidden" id="realTable" name="realTable" value="xsgwxxb" />
				<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
				<input type="hidden" id="pk" name="pk" value="<bean:write name="pk" scope="request"/>" />
				<input type="hidden" id="act" name="act" value="work" />
				<input type="hidden" name="xyV" value=""/>	
				<input type="hidden" name="zyV" value=""/>	
				<input type="hidden" name="bjV" value=""/>	
				<input type="hidden" id="type" name="type" value="tg"/>	
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								年级：
								<html:select property="nj" style="width:80px"
									onchange="initZyList();initBjList();" styleId="nj">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj" labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;学年：
								<html:select property="xn" style="width:100px" styleId="xn">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;学期：
								<html:select property="xq" style="width:90px" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
								&nbsp;&nbsp;学号：
								<html:text property="xh" style="width:85px" styleId="xh"></html:text>
								&nbsp;&nbsp;姓名：
								<html:text property="xm" style="width:85px" styleId="xm"></html:text>										
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="allNotEmpThenGo('qgzxLogic.do?method=stuPassCkeckInfo')">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" style="width:180px" styleId="xy" 
									onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm" labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;专业：
								<html:select property="zydm" style="width:180px" styleId="zy"
									onchange="initBjList();">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm" labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;班级：
								<html:select property="bjdm" style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td colspan="2">								
								岗位名称：
								<html:select property="gwdm" style="width:180px" styleId="gwdm">
									<html:option value=""></html:option>
									<html:options collection="gwList" property="gwdm" labelProperty="gwdm" />
								</html:select>
								&nbsp;&nbsp;用人单位：
								<html:select property="yrdwdm" style="width:90px" styleId="yrdwdm">
									<html:option value=""></html:option>
									<html:options collection="yrdwList" property="yrdwdm"
										labelProperty="yrdwmc" />
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
						&nbsp;&nbsp;
						<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序;按住Ctrl可以选择多条记录</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" id="all" name="all" onclick="chec()" />
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
							<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand" ondblclick="viewMore('view')">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="checkbox" name="pkV" value="<bean:write name="v"/>">
									</logic:iterate>
								</td>
								<!--非常州信息职业技术<bean:message key="lable.xsgzyxpzxy" />-->
								<logic:notEqual value="12317" name="xxdm">
									<logic:iterate id="v" name="s" offset="1">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</logic:notEqual>
								<!--end非常州信息职业技术<bean:message key="lable.xsgzyxpzxy" />-->

								
								<!--常州信息职业技术<bean:message key="lable.xsgzyxpzxy" />-->
								<logic:equal value="12317" name="xxdm">
									<logic:iterate id="v" name="s" offset="1" length="8">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
									<td>
										<logic:iterate id="v" name="s" offset="9" length="1">
											<a
											href="qgzxGwgl.do?method=viewFile&fileName=<bean:write name="v" />"
											target="_blank" style="color:blue"><bean:write name="v" />
										</a>
										</logic:iterate>
									</td>
								</logic:equal>
								<!--end常州信息职业技术<bean:message key="lable.xsgzyxpzxy" />-->
							</tr>
						</logic:iterate>
					</table>
					<TABLE width="99%" id="rsTable1" class="tbstyle">
						<TR>
							<TD height=3></TD>
						</TR>
						<TR>
							<TD>
								<jsp:include flush="true"
									page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
							</TD>
						</TR>
						<TR>
							<TD height=3></TD>
						</TR>
					</TABLE>
				</fieldset>
			</logic:notEmpty>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>			
			<logic:equal value="yes" name="writeAble" scope="request">
				<center>
					<div class="buttontool" id="btn" style="position: absolute;left:1%;top:100px">
						<!--常州信息职业技术<bean:message key="lable.xsgzyxpzxy" />-->
						<logic:equal value="12317" name="xxdm">
							<button type="button" class="button2" onclick="modify()" style="width:80px">
								修改
							</button>
							&nbsp;&nbsp;
						</logic:equal>
						<!--end常州信息职业技术<bean:message key="lable.xsgzyxpzxy" />-->
				        <button type="button" class="button2" onclick="dataExport()" style="width:80px">
							导出数据
						</button>		
						&nbsp;&nbsp;
						<button type="button" class="button2" onclick="expTab('rsTable','已通过审核学生信息表','')" style="width:80px">
							打印
						</button>
					</div>
				</center>
			</logic:equal>
			<div id="tmpdiv"></div>
		</html:form>
		<logic:equal name="result" value="ok">
			<script language="javascript">
      			alert("操作成功！");
	  		</script>
		</logic:equal>
		<logic:equal name="result" value="no">
			<script language="javascript">
	  			alert("操作失败! ");
	  		</script>
		</logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>

