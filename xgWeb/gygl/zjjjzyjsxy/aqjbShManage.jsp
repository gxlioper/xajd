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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<body>
		<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
		<script language="javascript" src="js/function.js"></script>	
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>	
		<html:form action="/zjjjzy_Gygl" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：公寓管理 - 审核 - 每周简报审核 
				</div>
			</div>
			<fieldset>
				<legend>
					基本参数
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
						<td >
						   审核状态
						   <html:select property="yesNo">
									<html:option value="未审核">未审核</html:option>
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
                            </html:select>
                           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							标题：<html:text property="title" styleId="title"/>
						   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							时间段：
							<html:text property="rzrq" styleId="rzrq"
								 style="cursor:hand;width: 80px"
								onclick="return showCalendar('rzrq','y-mm-dd');" />
							--
							<html:text property="lzrq" styleId="lzrq"
								style="cursor:hand;width: 80px"
								onclick="return showCalendar('lzrq','y-mm-dd');" />
							</td>
							<td width="10"  align="center" valign="middle">
								<input type="hidden" name="go" value="go" />
								<button class="button2" style="height:40px" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/zjjjzy_Gygl.do?method=aqjbShManage')">
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
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="mzjbView()">
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
			</logic:notEmpty>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<logic:equal value="yes" name="writeAble">
						<button class="button2" onclick="mzjbSh('yes')"
							style="width:80px">
							通 过
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="mzjbSh('no')"
							style="width:80px">
							不通过
						</button>						
						</logic:equal>
					</div>
				</center>
		</html:form>
		<script language="javascript">
document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
document.getElementById("btn").style.width = "96%";
window.setInterval("initBTNTool('btn')",1);
</script>
	</body>
	<script type="text/javascript">
	       function mzjbView(){	      
	            url="/xgxt/zjjjzy_Gygl.do?method=aqjbShow&documentId=";	           	
	            url+=(curr_row.cells[0].getElementsByTagName("input"))[0].value ;                
	            showTopWin(url,"800","600");	          
	       }
	       function mzjbSh(shType){
	           if (curr_row == null) {
		          alert("请选要审核的记录！\n单击一行记录即可");
		          return false;
	           } else {	           
	                url="/xgxt/zjjjzy_Gygl.do?method=aqjbSh&documentId=";	           	
	                url+=(curr_row.cells[0].getElementsByTagName("input"))[0].value ;
	                url+="&shType="+shType;
	                var clinText = "";
	                if(shType=="yes"){
	                   clinText="通过";
	                }else{
	                   clinText="不通过";
	                }
	                if(confirm("确定 "+clinText+" 该简报记录？" )){
	                   refreshForm(url);
	                }  
	          }              	            
	       }
	</script>
</html>
