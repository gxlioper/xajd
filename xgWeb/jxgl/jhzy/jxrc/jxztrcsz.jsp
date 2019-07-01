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
	<base target="_self">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript">
     function dataSave(){
        var url = "/xgxt/jxgl_jhzy.do?method=jxztrcsz&doType=save";
        var ksrq="";
        var jsrq="";
        if($("xn")){
           if($("xn").value==""){
              alert("学年不能为空！");
              return false;
           }
        }
        if($("xq")){
           if($("xq").value==""){
              alert("学期不能为空！");
              return false;
           }
        }
        if($("nd")){
           if($("nd").value==""){
              alert("年度不能为空！");
              return false;
           }
        }
        if($("ksrq")){
           ksrq=$("ksrq").value
           if(ksrq==""){
              alert("开始日期不能为空！");
              return false;
           }
        }
        if($("jsrq")){
            jsrq=$("jsrq").value
           if(jsrq==""){
              alert("结束日期不能为空！");
              return false;
           }
        }
        if(ksrq>=jsrq){
           alert("开始日期大于等于结束日期!");
           return false;
        }
        refreshForm(url);
     }

	</script>
	<body >
		<html:form action="/jxgl_jhzy" method="post">			
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：军训管理 - 军训日程管理 - 整体安排
				</div>
			</div>			
			<table class="tbstyle"
				style="width: 100%">
				<thead >
				<tr style="height: 23px" align="center">
				<td  colspan="2" > 
						军训日期范围设置
					</td>
				</tr>	
				</thead>
				
				<tr style="height: 23px">
					<td align="right">
						年度：
					</td>
					<td align="left"  style="width: 60%">
						<html:select  property="nd" onchange="refreshForm('/xgxt/jxgl_jhzy.do?method=jxztrcsz')">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
					</td>
					
				</tr>
				<tr style="height: 23px">
					<td align="right">
						学年：
					</td>
					<td align="left"  style="width: 60%">
						<html:select  property="xn" onchange="refreshForm('/xgxt/jxgl_jhzy.do?method=jxztrcsz')">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
					</td>
					
				</tr>
				<tr style="height: 23px">
					<td align="right">
						学期：
					</td>
					<td align="left"  style="width: 60%">
						<html:select  property="xq" onchange="refreshForm('/xgxt/jxgl_jhzy.do?method=jxztrcsz')">
						<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
					</td>
					
				</tr>
				<tr style="height: 23px">
					<td align="right">
						军训开始日期：
					</td>
					<td align="left"  style="width: 60%">
						<html:text name="rs"  property="ksrq"
							onclick="return showCalendar('ksrq','y-mm-dd');"
							onblur="dateFormatChg(this)" readonly="true" size="10"
							styleId="ksrq"></html:text>
					</td>
					
				</tr>
				<tr style="height: 23px" >
					<td align="right">
						军训结束日期：
					</td>
					<td align="left">
						<html:text name="rs"  property="jsrq"
							onclick="return showCalendar('jsrq','y-mm-dd');"
							onblur="dateFormatChg(this)" readonly="true" size="10"
							styleId="jsrq"></html:text>
					</td>
					
				</tr>
			</table>			
			<div class="buttontool" align="center">
				<span class="openbutton">					
					<button type="button" class="button2" id="buttonSave" onclick="dataSave()" style="width: 80px">
						保 存
					</button> &nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="buttonClose"
						 onclick="window.close();return false;" 
						 style="width: 80px" id="buttonClose">
						关 闭
					</button> </span>
			</div>
			<logic:present name="done">
				<logic:equal value="true" name="done">
					<script>
						alert('操作成功！');
					</script>
				</logic:equal>
				<logic:equal value="false" name="done">
					<script>
						alert('操作失败！');
					</script>
				</logic:equal>
			</logic:present>			
		</html:form>
		
	</body>
</html>
