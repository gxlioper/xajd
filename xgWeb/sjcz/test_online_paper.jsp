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
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
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
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
function loadTestSelectItem(){
  var xxArray= new Array();
  var txt=document.forms[0].xxStr.value;
  var splitTxt=txt.split("!!SplitSignOne!!");
  var stlxtype;  
  if(splitTxt!=""){
	  for(i=0;i<splitTxt.length;i++)
	    xxArray[i]=splitTxt[i].split("!!SplitSignTwo!!");
	  for(j=0;j<xxArray.length;j++){	 
	    document.getElementById(xxArray[j][0]).innerHTML=document.getElementById(xxArray[j][0]).innerHTML+"<br/>";
	    if(xxArray[j][1]=="001")
	           stlxtype="radio";     
	    else if(xxArray[j][1]=="002"||xxArray[j][1]=="003")
	           stlxtype="checkbox";	          
	    document.getElementById(xxArray[j][0]).innerHTML=document.getElementById(xxArray[j][0]).innerHTML+"<input type='"+stlxtype+"' name='"+xxArray[j][0]+"' value='"+xxArray[j][2]+"'>&nbsp;"+xxArray[j][2]+"&nbsp;&nbsp;"+xxArray[j][3]+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+xxArray[j][4];
	  }
  }
}

function saveTestSelectItem(){
    var selectedStr="";
    var tmp;
    for(i=0;i<tp.rows.length;i++){
       if(i!=0)
         selectedStr += "!!SplitSignOne!!";
       selectedStr += tp.rows[i].cells[0].id;
       selectedStr += "!!SplitSignTwo!!";
       tmp=document.getElementsByName(tp.rows[i].cells[0].id);
       for(j=0;j<tmp.length;j++)
         selectedStr +=(tmp[j].checked)?tmp[j].value:"";
    }
    document.forms[0].xxStr.value = selectedStr;    
}
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="checkWinType();loadTestSelectItem();jsys()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					心理健康 - 心理测试 - 在线测试 - 答题
				</div>
			</div>
			<logic:empty name="sjinfo">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="sjinfo">
				<input type="hidden" id="sjlsh" name="sjlsh" value="<bean:write name="sjinfo" property="sjlsh"/>" />
				<input type="hidden" id="xxStr" name="xxStr" value="<bean:write name="xxStr"/>" />
				<input type="hidden" id="dtkssj" name="dtkssj" value="${dtkssj}" >
				<input type="hidden" id="lssj" name="lssj" value="${dtkssj}" >
				<table width="99%" class="tbstyle">
					<tr>
						<td align="center">
							<B><font size="5"><bean:write name="sjinfo"
										property="sjm" />
							</font></B>
						</td>
					</tr>
					<tr>
						<td align="left">
							考试用时:<p align="left" id="showdate"></p>
						</td>
					</tr>
					<tr>
						<td align="left">
							<fieldset>
								<legend>
									试卷说明：
								</legend>
								<bean:write name="sjinfo" property="sjsm" />
							</fieldset>
						</td>
					</tr>
					<tr>
						<td align="left">
							<fieldset>
								<legend>
									共
									<bean:write name="total" />
									题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:iterate id="sjinfoList" name="sjinfoList">
										<bean:write name="sjinfoList" property="stlxmc" />:<bean:write
											name="sjinfoList" property="flTotal" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:iterate>
									时间限定:
									<bean:write name="sjinfo" property="sjxd" />
									(分钟)
								</legend>
								<logic:empty name="stList">暂无试题!</logic:empty>
								<logic:notEmpty name="stList">
									<table width="100%" class="tbstyle" id="tp">
										<logic:iterate id="stList" name="stList">
											<tr>
												<td align="left"
													id="<bean:write name="stList" property="stlsh"/>"
													width="100%">
													<B><bean:write name="stList" property="stxh" />.</B>&nbsp;
													<bean:write name="stList" property="stnr" />
													&nbsp;&nbsp;[
													<bean:write name="stList" property="stlxmc" />
													&nbsp;&nbsp;
													<bean:write name="stList" property="stndjbmc" />
													]
													<logic:notEmpty name="stList" property="stfz">&nbsp;&nbsp;<bean:write
															name="stList" property="stfz" />分</logic:notEmpty>
												</td>
											</tr>
										</logic:iterate>
									</table>
								</logic:notEmpty>
							</fieldset>
						</td>
					</tr>
				</table>
				<br />
				<div class="buttontool">
					<button type="button" class="button2"
						onclick="saveTestSelectItem();refreshForm('/xgxt/submit_testPaper.do')"
						style="width:80px" id="buttonSave">
						&nbsp;&nbsp;提&nbsp;&nbsp;交&nbsp;&nbsp;
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						&nbsp;&nbsp;重&nbsp;&nbsp;置&nbsp;&nbsp;
					</button>
				</div>
			</logic:notEmpty>
		</html:form>
		
	</body>
</html>
