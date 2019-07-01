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
function dataAdd() {
    var url = "/xgxt/jxgl_jhzy.do?method=jxrcJtAdd";		    	
	showTopWin(url, "800","700");			
}
function chec(){
    for(i=0;i<document.getElementsByName("pkV").length;i++){
      	document.getElementsByName("pkV")[i].checked=document.getElementsByName("pk")[0].checked;
    }
}
 function dataViewModi(str) {
	var pkValue = "";
	var xh = "" ;
	if (curr_row == null) {
		alert("请选择要修改的数据！\n（单击相应的行）");
		return false;
	} else {
		var url = "/xgxt/jxgl_jhzy.do?method=jxrcJtUpdate&pkValue=";		    			    
		url+= curr_row.getElementsByTagName("input")[0].value;
		url+="&doType="+str;
		showTopWin(url, "800","700");		
	}
}
function dataDel(){
           var url = "/xgxt/jxgl_jhzy.do?method=jxrcJtDel&go=go"; 
		   var RowsStr="";		  
		   for (i=0; i<document.getElementsByName("pkV").length; i++){
	    	  if(document.getElementsByName("pkV")[i].checked){
	    		 RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
	    	  }
		   }
		   document.forms[0].delPk.value = RowsStr;		   
		   if (RowsStr==""){
			   alert("请选择要删除的记录！\n(单击每条记录前复选框)");
			   return false;
		   }		
		   if (!confirm("确定要删除所选记录？")){
			  return false;
		   }
	       refreshForm(url);    

}
</script>
	<body onload="xyDisabled('xy');">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jxglFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/jxgl_jhzy" method="post">
		<input type="hidden"  name="delPk" id="delPk"/>
		<input type="hidden" id="userType" name="userType" value="${userType}"/>	
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：军训管理 - 军训日程 - 具体安排
				</div>
			</div>
			<div class="rightcontent">

				<fieldset>
					<legend>
						查 询
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
								    <bean:message key="lable.xsgzyxpzxy" />：
									<html:select property="xydm" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									&nbsp;&nbsp;年度：
									<html:select property="nd">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
									&nbsp;&nbsp;学年：
									<html:select property="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
									&nbsp;&nbsp;学期：
									<html:select property="xq">
									    <html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>									
								</td>
								<td width="10" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2"  id="search_go"
										onclick="refreshForm('/xgxt/jxrcJtManage.do?&go=go');this.disabled=true;">
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
									<td>
										<input type="checkbox" name="pk" value="all" onclick="chec()">
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
								<tr onclick="rowOnClick(this);" style="cursor:hand"
									ondblclick="dataViewModi('view')">
									<td align="center">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="checkbox" name="pkV"
												value="<bean:write name="v"/>">
										</logic:iterate>
									</td>									
									<logic:iterate id="v" name="s" offset="1">
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
					<logic:equal value="yes" name="writeAble" scope="request">
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">							
							<button type="button" class="button2" onclick="dataAdd()"
								style="width:80px">
								增 加
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="dataViewModi('modi')"
								style="width:80px">
								修 改
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="dataDel()"
								style="width:80px">
								删 除
							</button>																																	
						</div>
						</logic:equal>		
					</center>
				<div id="tmpdiv"></div>
				<script type="text/javascript" src="js/bottomButton.js"></script>
			</div>
		</html:form>
		<logic:equal value="yes" name="result">
			<script>
				alert("操作成功!");
				document.getElementById("search_go").click();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("操作失败!");
				document.getElementById("search_go").click();
			</script>
		</logic:equal>
		
	</body>
</html>
