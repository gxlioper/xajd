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
		<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body >	
		<html:form action="/nblgxy_gygl" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：
                     公寓管理 - 辅导员月报 - 月报查询
				</div>
			</div>
			
			<fieldset>
				<legend>
					基本参数
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								年份：
								<html:select  property="nf" style="width:90px" styleId="nf" >
									<html:options collection="nfList" property="nd" labelProperty="nd" />
								</html:select>								
							&nbsp;&nbsp;&nbsp;&nbsp;
								月份：
								<html:select property="yf" style="width:90px" styleId="yf">
									<html:option value=""></html:option>
									<html:option value="1">1</html:option>
									<html:option value="2">2</html:option>
									<html:option value="3">3</html:option>
									<html:option value="4">4</html:option>
									<html:option value="5">5</html:option>
									<html:option value="6">6</html:option>
									<html:option value="7">7</html:option>
									<html:option value="8">8</html:option>
									<html:option value="9">9</html:option>
									<html:option value="10">10</html:option>
									<html:option value="11">11</html:option>
									<html:option value="12">12</html:option>
								</html:select>
							</td>							
							<td width="10" rowspan="1" align="center" valign="middle">
								<input type="hidden" name="go" value="go" />
								<button class="button2" style="height:40px" id="search_go" 
										onclick="allNotEmpThenGo('/xgxt/nblgxy_gygl.do?method=ybManage');">
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
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								style="cursor: hand" ondblclick="ybView()">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td>
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
						
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick=" ybModi()"
							style="width:80px">
							修 改
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="ybDel()"
							style="width:80px">
							删 除
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:equal>
					</div>
				</center>
			
		</html:form>
		<logic:equal value="yes" name="writeAble" scope="request">
		<script language="javascript">
			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
			document.getElementById("btn").style.width = "96%";
			window.setInterval("initBTNTool('btn')",1);
		</script>
		</logic:equal>
  </body>   
<script type="text/javascript">
	       function ybModi(){
	           if (curr_row == null) {
		          alert("请选要修改的记录！\n单击一行记录即可");
		          return false;
	           } else {
	            var url = "/xgxt/nblgxy_gygl.do?method=ybModi";
	            url +="&pkValue=";
	            url +=(curr_row.cells[0].getElementsByTagName("input"))[0].value;
	            showTopWin(url,"700","800");
	           }
	       }
	       function ybView(){	          
	            var url = "/xgxt/nblgxy_gygl.do?method=ybView";
	            url +="&pkValue=";
	            url +=(curr_row.cells[0].getElementsByTagName("input"))[0].value;
	            window.open(url,"","");	          
	       }
	       function ybDel(){	          
	           if (curr_row == null) {
		          alert("请选要删除的记录！\n单击一行记录即可");
		          return false;
	           } else {
	              if(confirm("确定要删除该记录？")){	           
	                  var url = "/xgxt/nblgxy_gygl.do?method=ybDel";
	                  url +="&pkValue=";
	                  url +=(curr_row.cells[0].getElementsByTagName("input"))[0].value;
	                  refreshForm(url);   
	              }else{
	                  return false;
	              }
	           }              
	       }

</script>  
</html>
