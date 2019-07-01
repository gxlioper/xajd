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
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="/xgxt/dwr/interface/getOtherData.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
function addYj(url){
	var njS = document.getElementById('nj').value;
	url += "&nj="+njS;
	return showTopWin(url,450,330);
}
function addQt(){
	var isLj = document.getElementById("islj").value;
	var cbVal = document.getElementById('cbVal').value;
	if (cbVal == null || cbVal == ""){
		alert("请点选要增加下属的建制。");
		return false;
	}
	var njS = document.getElementById('nj').value;
	var jxjzmcS = "";
	var jxjzdjS = "";
	var jxssjzS = "";
	
	dwr.engine.setAsync(false);
	getOtherData.getXbmzJxjzDate(cbVal,njS,isLj,function(data){
       if(data!=null){
       	jxjzmcS=data[1];
       	jxjzdjS=data[2];
       	jxssjzS=data[6];
       }
    });
    dwr.engine.setAsync(true);
    
    if (jxjzdjS == 4){
    	alert(jxjzmcS+"已经是最小单位，不能再增加。");
    	return false;
    }
    
    var url = "/xgxt/jxgljz_xbmz.do";
    if (jxjzdjS != 2){
    	url += "?method=addQtjz&sjdm="+cbVal;
    	url += "&ssjz="+jxssjzS+jxjzmcS;
    	url += "&sjdj="+jxjzdjS;
		url += "&nj="+njS;
		return showTopWin(url,450,350);
    } else {
    	url += "?method=xbmzJxbj&sjdm="+cbVal;
    	url += "&ssjz="+jxssjzS+jxjzmcS;
    	url += "&nj="+njS;
		return showTopWin(url,650,400);
    }
}

function mod(){
	var cbVal = document.getElementById('cbVal').value;
	if (cbVal == null || cbVal == ""){
		alert("请点选要修改的建制。");
		return false;
	}
	var njS = document.getElementById('nj').value;
	var jxjzmcS = "";
	var jxjzdjS = "";
	var jxssjzS = "";
	var isLj = document.getElementById("islj").value;
	
	dwr.engine.setAsync(false);
	getOtherData.getXbmzJxjzDate(cbVal,njS,isLj,function(data){
       if(data!=null){
       	jxjzmcS=data[1];
       	jxjzdjS=data[2];
       	jxssjzS=data[6];
       }
    });
    dwr.engine.setAsync(true);
    
    var url = "/xgxt/jxgljz_xbmz.do";
    if (jxjzdjS != 4){
    	url += "?method=modXbmzJxjz&bzdm="+cbVal;
		url += "&nj="+njS;
		return showTopWin(url,450,350);
    } else {
    	alert("请点选其连级编制，并点击增加下属建制，进入修改。");
		return false;
    }
	
}

function del(){
	var cbVal = document.getElementById('cbVal').value;
	if (cbVal == null || cbVal == ""){
		alert("请点选要修改的建制。");
		return false;
	}
	var njS = document.getElementById('nj').value;
	var isLj = document.getElementById("islj").value;
	var xb = document.getElementById("xbV").value;
	
	dwr.engine.setAsync(false);
	
	getOtherData.getXbmzJxjzDate(cbVal,njS,isLj,function(data){
       if(data!=null){
       	jxjzmcS=data[1];
       	jxjzdjS=data[2];
       	jxssjzS=data[6];
       }
    });
    dwr.engine.setAsync(true);
    
    if (jxjzdjS != 4){
    	if (!confirm("删除该建制会同时删除下属建制信息，确定要删除所选建制？")){
			return false;
		}
    } else {
    	alert("请点选其连级编制，并点击增加下属建制，进入修改。");
		return false;
    }
	
	refreshForm('/xgxt/jxgljz_xbmz.do?method=jxgljzwhXbmz&act=del&xb='+xb);
}

	function jxbzPrint(){
		var url = "/xgxt/jxgljz_xbmz.do?method=jxjzPrintAll";
		window.open(url);
	}
</script>
	<body onload="check_user()">
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>
			<html:form action="jxgljz_nblg.do?method=jxgljzwhNblg" method="post">
				<div class="title">
					<div class="title_img" id="title_m">
						当前所在位置：
						<bean:write name="title" />
					</div>
				</div>
				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType" scope="request"/>" />
				<input type="hidden" id="cbVal" name="cbVal" value="" />
				<input type="hidden" id="xbV" name="xbV" value="" />
				<input type="hidden" id="islj" name="islj" value="1" />
				<logic:present name="isDel">
					<logic:match value="is" name="isDel">
						<script language="javascript">
	         				alert("删除成功！");
	         			</script>
					</logic:match>
					<logic:match value="no" name="isDel">
						<script language="javascript">
	         				alert("删除失败！");
	         			</script>
					</logic:match>
				</logic:present>
				<table width="100%" id="rsTable" class="tbstyle">
					<tr>
						<td width="50%" bgcolor="#D0E0EE">
							年级：
							<html:select name="rsT" property="nj"
								onchange="refreshForm('/xgxt/jxgljz_xbmz.do?method=jxgljzwhXbmz')"
								style="width:60px;padding-left:80px">
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
							<button type="button" onclick="refreshForm('/xgxt/jxgljz_xbmz.do?method=jxgljzwhXbmz')" id="search_go"
								style="display: none" ></button>
							&nbsp;
							<button type="button" class="button2" onclick="addYj('/xgxt/jxgljz_xbmz.do?method=addYjjz');"
								style="width:90px">
								增加营级建制
							</button>
							&nbsp;
							<button type="button" class="button2" onclick="addQt();"
								style="width:90px">
								增加下属建制
							</button>
							&nbsp;
							<button type="button" class="button2" onclick="mod();">
								修改
							</button>
							&nbsp;
							<button type="button" class="button2" onclick="del();">
								删除
							</button>
						</td>
						<td align="center" width="50%" bgcolor="#D0E0EE">
							建制详细信息
						</td>
					</tr>
					<tr>
						<td rowspan="2" height="450px">
							<div style="height:100%; width:100%;overflow-x:hidden;overflow-y:auto">
								<div style="padding:4px;height:100%">
									<logic:notEqual name="menuListTop" value="]">
										<script language="javascript">var TREE_ITEMS = <%=request.getAttribute("menuListTop")%>;</script>
										<script language="JavaScript" src="js/tree_xbmz_jx.js"></script>
										<script language="JavaScript" src="js/tree_tpl_jx.js"></script>
										<script language="JavaScript">new tree(TREE_ITEMS, tree_tpl);</script>
									</logic:notEqual>
									<logic:equal name="menuListTop" value="]">
										<div align="center">
											<br />
											<br />
											无建制数据!
										</div>
									</logic:equal>
								</div>
							</div>
						</td>
						<td>
							<table width="100%" id="rsTable" class="tbstyle" bgcolor="#D0E0EE">
								<tr>
									<td width="50%">
										<div align="center">
											建制代码
										</div>
									</td>
									<td width="50%">
										<div align="center">
											<span id="jxjzdm"><bean:write name="bzdm"scope="request" /></span>
											<input type="hidden" name="jxjzdmV" styleId="jxjzdmV" value="<bean:write name="bzdm"scope="request" />"/>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<div align="center">
											建制名称
										</div>
									</td>
									<td>
										<div align="center">
											<span id="jxjzmc"><bean:write name="bzmc"scope="request" /></span>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<div align="center">
											建制级别
										</div>
									</td>
									<td>
										<div align="center">
											<span id="jxjzdjmc"><bean:write name="bzdjmc"scope="request" /></span>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<div align="center">
											指导员
										</div>
									</td>
									<td>
										<div align="center">
											<span id="jxzdy"><bean:write name="zdy"scope="request" /></span>
											<input type="hidden" name="jxzdyV" styleId="jxzdyV" value="<bean:write name="zdy"scope="request" />"/>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<div align="center">
											教官
										</div>
									</td>
									<td>
										<div align="center">
											<span name="jxjgmc" id="jxjgmc"><bean:write name="jgmc"scope="request" /></span>
											<input type="hidden" name="jxjgmcV" styleId="jxjgmcV" value="<bean:write name="jgmc"scope="request" />"/>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<div align="center">
											所属
										</div>
									</td>
									<td>
										<div align="center">
											<span id="jxssjz"><bean:write name="ssjz"scope="request" /></span>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<div align="center">
											性别
										</div>
									</td>
									<td>
										<div align="center">
											<span id="xb"><bean:write name="xb"scope="request" /></span>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<div align="center">
											备注
										</div>
									</td>
									<td>
										<div align="center">
											<span id="jxbz"><bean:write name="bz"scope="request" /></span>
										</div>
									</td>
								</tr>
							</table>
							<div align="center">
								<button type="button" class="button2" onclick="jxbzPrint();">
									军训学生营连编配表
								</button>
							</div>
						</td>
					</tr>
					<tr>
						<td height="300px">
							<div align="center">
								&nbsp;
							</div>
						</td>
					</tr>
				</table>
			</html:form>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
