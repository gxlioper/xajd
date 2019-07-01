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
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript'src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript'src='/xgxt/dwr/interface/gyglShareData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/gygl/gyglFunction.js"></script>
		<html:form action="/zjgszy_gygl" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置： 公寓管理 - 信息维护 - 公寓团总支
				</div>
			</div>
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<fieldset>
				<legend>
					查询条件
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								校区名：
								<html:select property="xqdm" styleId="xqdm" style="width:150px"
									onchange="getLdLb()">
									<html:option value=""></html:option>
									<html:options collection="xiaoquList" property="dm"
										labelProperty="xqmc" />
								</html:select>
								&nbsp;&nbsp;&nbsp; 楼栋名:
								<html:select property="lddm" style="width:120px">
									<html:option value=""></html:option>
									<html:options collection="ldList" property="lddm"
										labelProperty="ldmc" />
								</html:select>
								&nbsp;&nbsp;&nbsp; 学号:
								<html:text property="xh" style="width:80px"></html:text>
								&nbsp;&nbsp;&nbsp; 姓名:
								<html:text property="xm" style="width:80px"></html:text>
							</td>
							<td width="10" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button class="button2" id="search_go" style="height:40px"
									onclick="allNotEmpThenGo('/xgxt/zjgszy_gygl.do?method=gyTzzQuery');">
									查询
								</button>
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
						<font color="blue">提示：单击表头可以排序</font>
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
							<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
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
				<%--				<TABLE width="99%" id="rsTable" class="tbstyle">--%>
				<%--					<TR>--%>
				<%--						<TD height=3></TD>--%>
				<%--					</TR>--%>
				<%--					<TR>--%>
				<%--						<TD>--%>
				<%--							<jsp:include flush="true"--%>
				<%--								page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>--%>
				<%--						</TD>--%>
				<%--					</TR>--%>
				<%--					<TR>--%>
				<%--						<TD height=3></TD>--%>
				<%--					</TR>--%>
				<%--				</TABLE>--%>
			</logic:notEmpty>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
			<center>
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
					<logic:equal value="yes" name="writeAble" scope="request">
						<button class="button2" onclick="tzzAdd()" style="width:80px">
							增 加
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="tzzModi()" style="width:80px">
							修 改
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="tzzDel()" style="width:80px">
							删 除
						</button>
					</logic:equal>
					<%--						&nbsp;&nbsp;&nbsp;&nbsp;--%>
					<%--						<button class="button2" onclick="dataExport()" style="width:80px">--%>
					<%--							导出数据--%>
					<%--						</button>--%>
				</div>
			</center>
		</html:form>
		<script language="javascript">
document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
document.getElementById("btn").style.width = "96%";
window.setInterval("initBTNTool('btn')",1);
</script>
		<script type="text/javascript">
function tzzAdd(){
    var url = "/xgxt/zjgszy_gygl.do?method=gyTzzAdd";
    showTopWin(url,'650','400');
}
function tzzModi(){
    var url = "/xgxt/zjgszy_gygl.do?method=gyTzzModi&pkValue=";
    if(curr_row == null){
        alert("请选择要操作的数据！\n（单击相应的行）");
		return false;
    }
    pkValue = curr_row.getElementsByTagName("input")[0].value;     
    url +=pkValue;                             
    if(confirm("确定要修改该数据？")){
         showTopWin(url,'650','400');
    }else{
        return false;
    }    
}
function tzzDel(){
    var url = "/xgxt/zjgszy_gygl.do?method=gyTzzDel&pkValue=";
    if(curr_row == null){
        alert("请选择要操作的数据！\n（单击相应的行）");
		return false;
    }
    pkValue = curr_row.getElementsByTagName("input")[0].value;     
    url +=pkValue;                             
    if(confirm("确定要修删除该行数据？")){
        refreshForm(url);
    }else{
        return false;
    }    
}
</script>

		<logic:equal value="true" name="done">
			<script type="text/javascript">
alert("操作成功！");   
</script>
		</logic:equal>
		<logic:equal value="false" name="done">
			<script type="text/javascript">
alert("操作失败！");
</script>
		</logic:equal>

	</body>
</html>


