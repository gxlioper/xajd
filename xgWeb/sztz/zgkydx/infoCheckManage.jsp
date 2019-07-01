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
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		
    <html:form action="/zgkydx_sztz.do" method="post">
    <input type="hidden" id="pkVStr" name="pkVStr" value="" />	
					<input type="hidden" name="zyV" id="zyV" />
					<input type="hidden" name="bjV" id="bjV" />			
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：
					素质拓展 - 拓展信息审核 -审核
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
								   学年：
									<html:select property="xn" disabled="true" style="width:100px;background-color:#FFFFFF" 
									styleId="xn" >
										<html:options collection="xnList" property="xn" 
											labelProperty="xn" />
									</html:select>
										&nbsp;&nbsp;学期：
									<html:select property="xq" disabled="true" style="width:90px;background-color:#FFFFFF" 
									styleId="xq" >
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>										
										&nbsp;&nbsp;&nbsp;年级：
									<html:select property="nj" style="width:80px" onchange="initZyList();initBjList()" style="background-color:#FFFFFF">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
										&nbsp;&nbsp;学号：
									<html:text property="xh" style="width:85px"></html:text>
										&nbsp;&nbsp;姓名：
									<html:text property="xm" style="width:85px"></html:text>
									</td>
									<td width="10" rowspan="2" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button class="button2" style="height:40px" id="search_go"
											onclick="document.forms[0].go.value='go';refreshForm('/xgxt/sztzInfoCheck.do')">
											查询
										</button>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										<bean:message key="lable.xsgzyxpzxy" />：
									<html:select property="xydm" style="width:180px;background-color:#FFFFFF" styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc"/>
									</html:select>
									&nbsp;&nbsp;专业：
									<html:select property="zydm" style="width:180px;background-color:#FFFFFF" styleId="zy"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
									&nbsp;&nbsp;班级：
									<html:select property="bjdm" style="width:180px;background-color:#FFFFFF" styleId="bj">
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
							<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
								<td>
										<input type="checkbox" name="fyxx" value="all"
											onclick="chec()">
									</td>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" >
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowMoreClick(this,'',event);" 
								    style="cursor:hand;background-color:
                                  <logic:iterate id="v" name="s" offset="0" length="1">
                                     <bean:write name="v"/>
                                   </logic:iterate>
                                  "
									ondblclick="View()">
									<td align="center">
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="checkbox" name="pkV"
												value="<bean:write name="v"/>">
										</logic:iterate>
									</td>
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
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
				 <br><br> <br><br>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				  <center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
							<button class="button2" onclick="batchCheck('yes')"
								style="width:80px">
								通过
							</button>								
							&nbsp;&nbsp;&nbsp;&nbsp;							
										<button class="button2" onclick="batchCheck('no')"
								style="width:80px">
								不通过
							</button>													
					<br>
						&nbsp;&nbsp;
					</div>
				</center>
				
	</html:form>
	<script type="text/javascript" src="js/bottomButton.js"></script>		
    </body>
<script type="text/javascript">
function View(){	          
	var url = "/xgxt/zgkydx_sztz.do?method=infoChkView";
		url +="&pkValue=";
	    url +=(curr_row.cells[0].getElementsByTagName("input"))[0].value;	   	            	            
	showTopWin(url,"800","700");	          
}
function chec(){
       for(i=0;i<document.getElementsByName("pkV").length;i++){
      	  document.getElementsByName("pkV")[i].checked=document.getElementsByName("fyxx")[0].checked;
       }
}
 function batchCheck(str){
           var url = "/xgxt/zgkydx_sztz.do?method=infoChk&check="+str; 
		   var RowsStr="!!";		  	   
		   xyshDone = (str=="yes")?"通过":"不通过";
		   var pkVArray = "'";
		   for (i=0; i<document.getElementsByName("pkV").length; i++){
	    	  if(document.getElementsByName("pkV")[i].checked){
	    		 RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
	    		 pkVArray+=document.getElementsByName("pkV")[i].value+"','"
	    	  }	    	  
		   }		   
		   if (RowsStr=="!!"){
			   alert("请选择要操作的记录！\n(单击每条记录前复选框)");
			   return false;
		   }
		   document.forms[0].pkVStr.value = RowsStr;
		   pkVArray=pkVArray.substring(0,pkVArray.length-2);	
		   if (confirm("确定要\""+xyshDone+"\"所选记录？")){	   		      
		       refreshForm(url); 	
		   }             
  }

</script>
</html>	
