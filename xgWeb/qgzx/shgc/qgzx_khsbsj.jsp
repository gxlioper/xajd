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
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/sharedFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	
	<script language="javascript">	
	function chec(){
      for(i=0;i<document.getElementsByName("pk").length;i++){
      	document.getElementsByName("pk")[i].checked=document.getElementsByName("gwmc")[0].checked;
      }
    }
    
    function batch(url){
		var RowsStr="!!";		
		for (i=1; i<document.getElementsByName("pk").length; i++){
	    	if(document.getElementsByName("pk")[i].checked){
	    		RowsStr+=document.getElementsByName("pk")[i].value+"!!";
	    	}
		}
		
		if (RowsStr=="!!"){
			alert("请选择要批量设置的记录！");
			return false;
		}
		
		if (!confirm("确定要设置所选记录？")){
			return false;
		}
		url += "&pkString="+RowsStr;
		showTopWin(url,400,300);
}
	function single(url){
		if (curr_row==null){
			alert("请选择要设置的行！");
			return false;
		}
		var RowsStr = "!!";
		RowsStr += curr_row.cells[0].getElementsByTagName("input")[0].value + "!!";					
		url += "&pkString="+RowsStr;
		showTopWin(url,400,300);
	}
</script>
	<body onload="">
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>

			<html:form action="/qgzxShgc.do" method="post">	
			<input type="hidden" id="pk"  name="pk" value="pk"/>	
			<input type="hidden" id="doType"  name="doType" value="single"/>
				<div class="title">
					<div class="title_img" id="title_m">
						当前位置：勤工助学 - 参数设置 - 考核上报时间设置
					</div>
				</div>
				<table class="tbstyle" width="100%">
				<thead>
					<tr><td width="100%">
					<center><b>用人单位考核上报时间设置</b></center>
					</td></tr>
				</thead>
				</table>	
				<fieldset>
				<legend>
					查询条件
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								用人单位：
								<html:select property="yrdwdm" styleId="yrdwdm" style="width:80px">
									<html:option value=""></html:option>
									<html:options collection="yrdwList" property="yrdwdm" labelProperty="yrdwmc"/>
								</html:select>
								&nbsp;&nbsp;开始时间：
								<html:text property="kssj" onclick="return showCalendar('kssj','y-mm-dd');" styleId="kssj"/>
								&nbsp;&nbsp;结束时间：
								<html:text property="jssj" onclick="return showCalendar('jssj','y-mm-dd');" styleId="jssj"/>
							</td>
							<td width="10" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" id="search_go"
									onclick="allNotEmpThenGo('qgzxShgc.do?method=kssbsj');">
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
							记录数：
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">提示：双击一行可以选定；单击表头可以排序</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" name="gwmc" value="all"
											onclick="chec()">
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
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand" ondblclick="single('qgzxShgc.do?method=khsbsjModi')">		
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="checkbox" name="pk" value="<bean:write name="v"/>">
										</logic:iterate>
									</td>							
									<logic:iterate id="v" name="s" offset="1">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>	
					</fieldset>					
				</logic:notEmpty>

					<logic:equal value="yes" name="writeAble">
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<button type="button" class="button2"
								onclick="batch('qgzxShgc.do?method=khsbsjModi')"
								style="width:80px">
								设置时间
							</button>							
						</div>
					</logic:equal>

				<logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
						alert("操作成功！");
						Close();
										
					</script>
				</logic:equal>
				<logic:equal name="result" value="false">
					<script>
						alert("操作失败!");
					</script>
				</logic:equal>
			</logic:notEmpty>
			</html:form>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
									