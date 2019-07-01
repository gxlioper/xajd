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
		<base target="_self">
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
	<body onload="xnxqNosee()">
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		
    <html:form action="/sztz_xm_query" method="post">
			<input type="hidden" id="url" name="url" value="<bean:write name="url" scope="request"/>" />
			<input type="hidden" id="xh" name="xh" value="<bean:write name="xh" scope="request"/>" />
			<input type="hidden" id="xnxqNos" name="xnxqNos" value="<bean:write name="xnxqNos" scope="request"/>" />
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
					<table width="100%" class="tbstyle">
						<thead>							
								<tr>
									<td align="left">
										学年：
										<html:select property="xn" style="width:100px" styleId="xn">
										<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学期：
										<html:select property="xq" style="width:90px" styleId="xq">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;所属科目：
										<html:select property="kmdm" style="width:150px"
											styleId="kmdm">
											<html:option value=""></html:option>
											<html:options collection="kmList" property="kmdm"
												labelProperty="kmm" />
										</html:select>										
									</td>
									<td width="10" rowspan="2" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button class="button2" style="height:40px" id="search_go"
											onclick="document.forms[0].go.value='go';refreshForm('/xgxt/sztz_xm_query.do');">
											查询
										</button>
									</td>
								</tr>
								<tr>
									<td align="left">																				
										&nbsp;&nbsp;项目名称：
										<html:text property="xmmc">
										</html:text>
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
							<font color="blue">提示：单击表头可以排序;</font>
						</legend>						
							<table width="100%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topTr" offset="2">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>			
									<logic:iterate name="rs" id="s">
										<tr onclick="rowOnClick(this);"
											style="cursor:hand;"
											ondblclick="sendTzxmInfo();">
											<td align="center">
												<logic:iterate id="v" name="s" offset="1" length="1">
													<input type="hidden" value="<bean:write name="v"/>" />
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="2" length="1">
													<bean:write name="v" />
												</logic:iterate>
											</td>
											<logic:iterate id="v" name="s" offset="3">
												<td align="center">
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
									page="/sjcz/turnpage.jsp?form=sztzForm"></jsp:include>
							</TD>
						</TR>
						<TR>
							<TD height=3></TD>
						</TR>
					</TABLE>
				</logic:notEmpty>
			</div>
	</html:form>	
    </body>
<script type="text/javascript">
function sendTzxmInfo() {	
	if(confirm("确定选择该项目吗?")){
		var xmdm = replaceChar(curr_row.getElementsByTagName('input')[0].value," ","");
		var xh = window.dialogArguments.document.forms[0].xh.value;
		var pk = xmdm;
		var url = document.forms[0].url.value ;
		var str = "";
   	 	if(url.search('sztz_modi_data')==1){
             var vel = window.dialogArguments.document.forms[0].xmmc;
		     vel.focus();
			 vel.value = replaceChar(curr_row.cells[2].innerText," ","");				
    		 str = "/xgxt/"+url+"?from="+url+"&xh="+xh+"&xmdm="+xmdm;
		     window.dialogArguments.document.forms[0].action = str;
	         window.dialogArguments.document.forms[0].submit(); 	
	         window.close();
         }else{
   	 	   getSztzData.getInfoEx("sztz_xmdmb","xmdm",pk," ((select to_char(sysdate,'yyyymmdd')sj from dual ) >=sqkssj or sqkssj='暂无') and ((select to_char(sysdate,'yyyymmdd')sj from dual )<= sqjssj or sqjssj='暂无' ) ",function(data){
		         if(data<1 || data==null){
		            alert("不在申请范围内，暂不能进行该项目的申请！");
			        return false;
		         }else{
		         	var vel = window.dialogArguments.document.forms[0].xmmc;
					vel.focus();
					vel.value = replaceChar(curr_row.cells[2].innerText," ","");				
    				str = "/xgxt/"+url+"?from="+url+"&xh="+xh+"&xmdm="+xmdm;
		            window.dialogArguments.document.forms[0].action = str;
	                window.dialogArguments.document.forms[0].submit(); 	
	                window.close();
	             }   	        		            	
            });          
         }
         
	}else{
		return false;
	}
}
function xnxqNosee(){
     var xnxqNos = $("xnxqNos").value;
     if(xnxqNos=="true"){
         $("xn").disabled=true;
         $("xq").disabled=true;
     }
}
</script>

</html>	