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
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<base target="_self"/>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body>	
		<html:form action="/stu_gydykp_info" method="post">
		<input type="hidden" id="pk" name="pk" value="xh||xn||xq||sj" />
		<input type="hidden" id="Pkxh" name="Pkxh" value="<bean:write name="xh" scope="request"/>" />
		<input type="hidden" id="search_go" onclick="refreshForm('/xgxt/stu_gydykp_info.do')"/>
		   <div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow">当前所在位置：公寓管理 - 公寓德育考评 - 详细信息查看</span>
				</div>
			</div>
			<fieldset>
					<legend>
						查 询
					</legend>
			<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="2">
								<input type="hidden" name="xh" id="xh" value="<bean:write name="xh" scope="request" />">
								学号:<bean:write name="xh" scope="request" />
								&nbsp;&nbsp;
								姓名：<bean:write name="xm" scope="request" />
								&nbsp;&nbsp;学年：
								<html:select property="xn" style="width:100px" styleId="xn" onchange="refreshForm('/xgxt/stu_gydykp_info.do?Pkxh='+$('xh').value)">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;学期：
								<html:select property="xq" style="width:80px" styleId="xq" onchange="refreshForm('/xgxt/stu_gydykp_info.do?Pkxh='+$('xh').value)">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
			</thead>
			</table>
			</fieldset>		
			<br/>
				<br/>
				<fieldset>
					<legend>
						奖励	&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
						
					</legend>
			    <logic:empty name="rsa">
					<p align="center">
						未找到任何记录！
					</p>
				</logic:empty>
                <logic:notEmpty name="rsa">   
				<table width="100%" class="tbstyle">
					<thead>						
						<tr>
						    <td align="center" onclick="tableSort(this)" >行号</td>
						    <td align="center" onclick="tableSort(this)" >学年</td>
							<td align="center" onclick="tableSort(this)" >学期</td>	
						    <td align="center" onclick="tableSort(this)" >奖励时间</td>
							<td align="center" onclick="tableSort(this)" >宿舍编号</td>								
							<td align="center" onclick="tableSort(this)" >考评情况</td>
							<td align="center" onclick="tableSort(this)" >加分情况</td>
						</tr>
					</thead>
					<tbody id="rsTables">
					<logic:iterate name="rsa" id="s">
					<tr onclick="rowOnClick(this);" style="cursor:hand" ondblclick="gykpViewData('view')">
						<td>
						<input type="hidden" value="<bean:write name="s" property="pk"/>" />
						<input type="hidden" value="<bean:write name="s" property="l"/>" />
						<bean:write name="s" property="r"/>
						</td>
						<td><bean:write name="s" property="xn"/></td>
						<td><bean:write name="s" property="xq"/></td>
						<td><bean:write name="s" property="sj"/></td>
						<td><bean:write name="s" property="ssbh"/></td>
						<td><bean:write name="s" property="jlnr"/></td>
						<td><bean:write name="s" property="ryjf"/></td>
					</tr>
					</logic:iterate>
					</tbody>
				</table>
				</logic:notEmpty>
			</fieldset>	
				<br/>
				<br/>
				<fieldset>
					<legend>
						处罚	&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
					</legend>
					 <logic:empty name="rsb">
					<p align="center">
						未找到任何记录！
					</p>
				</logic:empty>
                <logic:notEmpty name="rsb">   
				<table width="100%" class="tbstyle">
					<thead>						
						<tr>
						    <td align="center" onclick="tableSort(this)" >行号</td>
						    <td align="center" onclick="tableSort(this)" >处罚时间</td>
							<td align="center" onclick="tableSort(this)" >宿舍编号</td>								
								<td align="center" onclick="tableSort(this)" >考评情况</td>
								<td align="center" onclick="tableSort(this)" >减分情况</td>
						</tr>
					</thead>
					<tbody id="rsTables">
					<logic:iterate name="rsb" id="s">
					<tr onclick="rowOnClick(this);" style="cursor:hand" ondblclick="gykpViewData('view')">
						<td>
						<input type="hidden" value="<bean:write name="s" property="pk"/>" />
						<input type="hidden" value="<bean:write name="s" property="l"/>" />
						<bean:write name="s" property="r"/>
						</td>
						<td><bean:write name="s" property="sj"/></td>
						<td><bean:write name="s" property="ssbh"/></td>
						<td><bean:write name="s" property="cfnr"/></td>
						<td><bean:write name="s" property="rykf"/></td>
					</tr>
					</logic:iterate>
					</tbody>
				</table>
				</logic:notEmpty>
			</fieldset>	
			<logic:equal value="yes" name="writeAble" scope="request">
			<div class="buttontool" id="btn" align="center" >			
			<button class="button2" onclick="gykpViewData('add')" style="width:80px">
				增 加
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="button2" onclick="gykpViewData('modi')" style="width:80px">
				修 改
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="button2" onclick="gykpViewData('del')" style="width:80px">
				删 除
			</button>
		</div>	
		</logic:equal>	
		</html:form>	
  </body>
 <script type="text/javascript">
 function gykpViewData(doType){
	var xh = document.forms[0].xh.value;
	var pkValueA = "";
	var pkValueB = "";
	var pk = document.forms[0].pk.value;
	var url = "/xgxt/detailData.do?act=";
	url += doType;
	url += "&xh=";
	url += xh;
	url += "&pk=";
	url += pk;
	url += "&pkValueA=";
    
	if (doType == "modi" && curr_row == null) {
		alert("请选择要修改的数据！\n（单击相应的行）");
		return false;
	}
	
	if (doType == "del"){
		if(curr_row == null){
			alert("请选择要修改的数据！\n（单击相应的行）");
			return false;
		}else{	
		    pkValueA = curr_row.getElementsByTagName("input")[0].value;
		    pkValueB = curr_row.getElementsByTagName("input")[1].value;		
			url += pkValueA;
			url += "&pkValueB=";
			url += pkValueB;
			if(confirm("确定要删选中记录吗？")){
				refreshForm(url);
				return true;
			}else{
				return false;
			}
		}
	}
	if(doType != "add"){	     
		pkValueA = curr_row.getElementsByTagName("input")[0].value;
		pkValueB = curr_row.getElementsByTagName("input")[1].value;
	}
	url += pkValueA;
	url += "&pkValueB=";
	url += pkValueB;
	if(doType=="add"){
	     getSztzData.getInfoEx("xszsxxb","xh",xh," 1=1 ",function(data){
	        if(data){	        
	          showTopWin(url, 700, 500);
	        }else{
	          alert("该生目前未入住寝室，不能进行德育评比");
	          return false;	          
	        }
	    });	    	    	  
	}else{
	     showTopWin(url, 700, 500);
	}		
}
 </script>
</html>
