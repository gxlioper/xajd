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

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<html:form action="/csmz_gygl.do?method=xqcx_Default" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置： 公寓管理 - 辅导员下寝 - 查询统计
				</div>
			</div>
			<div class="rightcontent">
				<fieldset>
					<legend>
						查 询
					</legend>
<%--					<input type="hidden" id="userType" name="userType"--%>
<%--						value="<bean:write name="userType" scope="request"/>" />--%>
					<input type="hidden" id="tableName" name="tableName"
						value="<bean:write name="tableName" scope="request"/>" />
<%--					<input type="hidden" id="act" name="act"--%>
<%--						value="<bean:write name="act" scope="request"/>" />--%>
					<input type="hidden" id="realTable" name="realTable"
						value="<bean:write name="realTable" scope="request"/>" />
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
										学年：
									<html:select property="xn" style="width:90px" >
										<html:option value=""></html:option>
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
									&nbsp;&nbsp;学期：
									<html:select property="xq" style="width:90px" >
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
										&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
									<html:select property="xy" style="width:180px" >
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
										&nbsp;&nbsp;辅导员：
									<html:select property="zgh" style="width:90px" >
										<html:option value=""></html:option>
										<html:options collection="fdyList" property="zgh"
											labelProperty="xm" />
									</html:select>
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button class="button2" style="height:60px" id="search_go"
										onclick="document.forms[0].go.value='go';refreshForm('/xgxt/fdy_xqtjcx.do')">
										查询
									</button>
								</td>
								<td width="10" rowspan="3" align="center" valign="middle">
									<button class="button2" style="height:60px;width:40px"
										onclick="">
										&nbsp;&gt;&nbsp;&gt;&nbsp;
									</button>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>									
									辅导员姓名：
									<html:text property="xm" styleId="xm" style="width:80px"></html:text>
									&nbsp;&nbsp;下寝时间：
									<html:text property="sj" readonly="true"
								onblur="dateFormatChg(this)"
								onclick="return showCalendar('sj','y-mm-dd');"
								style="cursor:hand " />								
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
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
									ondblclick="view()">									
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
<%--					<TABLE width="99%" id="rsTable" class="tbstyle">--%>
<%--								<TR>--%>
<%--									<TD height=3></TD>--%>
<%--								</TR>--%>
<%--								<TR>--%>
<%--									<TD>--%>
<%--										<jsp:include flush="true"--%>
<%--											page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>--%>
<%--									</TD>--%>
<%--								</TR>--%>
<%--								<TR>--%>
<%--									<TD height=3></TD>--%>
<%--								</TR>--%>
<%--					</TABLE>--%>
				</logic:notEmpty>
				<div id="tmpdiv"></div>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
						<logic:equal value="yes" name="writeAble" scope="request">
							<button class="button2" onclick="add()"
								style="width:80px">
								增 加
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="modi()"
								style="width:80px">
								修 改
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="del()"
								style="width:80px">
								删 除
							</button>							
							&nbsp;&nbsp;&nbsp;&nbsp;
							</logic:equal>							
							<button class="button2" onclick="dataExport()" style="width:80px">
								报表
							</button>							
						</div>
					</center>
					</div>
		</html:form>
			<script language="javascript">
				document.getElementById("btn").style.pixelTop = document.body.clientHeight - 55;
				document.getElementById("btn").style.width = "96%";
				window.setInterval("initBTNTool('btn')",1);
			</script>
			<logic:equal name="sfDone" value="true">
			<script language="javascript">
      				alert("操作成功！");
	  		</script>
		</logic:equal>
		<logic:equal name="sfDone" value="false">
			<script language="javascript">
	  				alert("操作失败! ");
	  		</script>
		</logic:equal>
	</body>
		<script language="javascript">
		      function  add(){ 
		         url = "/xgxt/csmz_gygl.do?method=fdy_xqxx_add";
		         showTopWin(url,'700','500');
		      }
		      function modi(){
		        if(curr_row == null) {
		            alert("请选择要修改的记录！\n（单击相应的行）");
		            return false;
	            }
	            if(confirm("确定要修改该条记录!")){
	             url = "/xgxt/csmz_gygl.do?method=fdy_xqxx_modi";
	             url +="&pkValue="
	             url += curr_row.cells[0].getElementsByTagName("input")[0].value;
	             showTopWin(url,'700','500');
	             }else{
	                  return false;             
	             }
		      }
		      function view(){
		         url = "/xgxt/csmz_gygl.do?method=fdy_xqxx_view";
	             url +="&pkValue="
	             url += curr_row.cells[0].getElementsByTagName("input")[0].value;
	             showTopWin(url,'700','500');
		      }
		      function del(){
		         if(curr_row == null) {
		            alert("请选择要修改的记录！\n（单击相应的行）");
		            return false;
	            }
	             if(confirm("确定要删除该条记录!")){
		         url = "/xgxt/csmz_gygl.do?method=fdy_xqxx_del";
	             url +="&pkValue="
	             url += curr_row.cells[0].getElementsByTagName("input")[0].value;
	             refreshForm(url);
	             }else{
	                return false;
	             }
		      }
		</script>
</html>
