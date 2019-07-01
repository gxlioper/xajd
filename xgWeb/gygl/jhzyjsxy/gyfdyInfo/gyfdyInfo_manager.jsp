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
	<script type="text/javascript">
		function xqzr(){
			var url = "/xgxt/jhzy_gygl.do?method=selectIntoFdy";
			 showOpenWindow(url,'600','400');
		}
		</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/jhzy_gygl" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置： 公寓管理 - 信息维护 - 公寓辅导员信息
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
								&nbsp;&nbsp;学年：
								<html:select property="xn" style="width:100px" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;学期：
								<html:select property="xq" style="width:100px" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								&nbsp;&nbsp;公寓辅导员：
								<html:select property="yhm" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="yhList" property="yhm"
										labelProperty="xm" />
								</html:select>
							</td>
							<td width="10" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button class="button2" id="search_go"
									onclick="document.forms[0].go.value='go';refreshForm('/xgxt/jhzy_gygl.do?method=gyfdyInfoManage&go=go');">
									查 询
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
							<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
								ondblclick="myViewMore('modi')">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="8" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2" length="6">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
				<TABLE width="99%" id="rsTable" class="tbstyle">
					<TR>
						<TD height=3></TD>
					</TR>
					<TR>
						<TD>
							<jsp:include flush="true"
								page="/sjcz/turnpage.jsp?form=jhzy_gyglForm"></jsp:include>
						</TD>
					</TR>
					<TR>
						<TD height=3></TD>
					</TR>
				</TABLE>
			</logic:notEmpty>
			<br />
			<br />
			<br />
			<br />
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
			<center>
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
					<logic:equal value="yes" name="writeAble" scope="request">
						<button class="button2" onclick="myViewMore('add')"
							style="width:80px">
							增 加
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="myViewMore('modi')"
							style="width:80px">
							修 改
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="myViewMore('del')"
							style="width:80px">
							删 除
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						
						<button class="button2" onclick="impAndChkData();"
							style="width:80px">
							导入数据
						</button>
					</logic:equal>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="dataExport()" style="width:80px">
						导出数据
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="xqzr()" style="width:80px">
						同步
					</button>
				</div>
			</center>
		</html:form>
		<script language="javascript">
document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
document.getElementById("btn").style.width = "96%";
window.setInterval("initBTNTool('btn')",1);
</script>
		<script type="text/javascript">
function myViewMore(doType){
   var pkValue = "";
   var url = "/xgxt/jhzy_gygl.do?method=gyfdyInfoModify&doType=";
   url += doType;
   url += "&pkValue=";
   if(doType=="add"){
      url +=pkValue;
      showOpenWindow(url,'800','600');
   }else if(doType=="modi"){
	   if(curr_row == null){
	        alert("请选择要操作的数据！\n（单击相应的行）");
			return false;
	      }
      pkValue = curr_row.getElementsByTagName("input")[0].value;
      var xn = curr_row.getElementsByTagName("input")[1].value;
      var xq = curr_row.getElementsByTagName("input")[2].value;
      url +=pkValue;
      url +="&isView=modify";
      url +="&xn="+xn;
      url +="&xq="+xq;
      showOpenWindow(url,'800','600');
   }else {
      if(curr_row == null){
        alert("请选择要操作的数据！\n（单击相应的行）");
		return false;
      }
      pkValue = curr_row.getElementsByTagName("input")[0].value;  
      var xn = curr_row.getElementsByTagName("input")[1].value;
      var xq = curr_row.getElementsByTagName("input")[2].value;   
      if(doType=="del"){
    	   url = "/xgxt/jhzy_gygl.do?method=gyfdyInfoManage&doType=";
    	   url += doType;
    	   url += "&pkValue=";
    	   url +=pkValue;  
    	   url +="&xn="+xn;
    	   url +="&xq="+xq;
         if(confirm("确定要修删除该用户的所有数据？")){
             refreshForm(url);
         }else{
             return false;
         }
      }
   }
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


