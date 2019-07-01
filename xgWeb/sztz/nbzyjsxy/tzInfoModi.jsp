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
		<base target="_self" />
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
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
	     function hiddenKm(){
	        var sfjl = "";
	        if($("sfjlxm"))sfjl= $("sfjlxm").value;	        
	        if(sfjl=="是"){
	          if ($("kmdm")) $("kmdm").disabled="true";
	          if($("kmdm"))$("kmdm").value= "";
	        }else{
	          if ($("kmdm")) $("kmdm").disabled=false;
	        }
	     }
	    function tzInfoAddSave(){	          
	       if($("sfjlxm")){
	         if($("sfjlxm").value=="否"){
	            if ($("kmdm")&&$("kmdm").value==""){
	                alert("所属科目不能为空！");
	                return false;
	            }
	         }
	       } 
	       if($("xn")&&$("xn").value==""){
	         alert("学年不能为空！");
	         return false;
	       }
	       if($("xq")&&$("xq").value==""){
	         alert("学期不能为空！");
	         return false;
	       }                
	       if($("xmmc")&&$("xmmc").value==""){
	         alert("项目名称不能为空！");
	         return false;
	       } 
	       if($("sj")&&$("sj").value==""){
	         alert("参与时间不能为空！");
	         return false;
	       } 	       
	       if($("bz")&&$("bz").value.length>300){
	         alert("备注字数不能超过300字！");
	         return false;
	       } 	               	                       
           refreshForm("/xgxt/nbzy_sztz.do?method=tzInfoModi&doType=Save");
           $('buttonSave').disabled=true;
	   }      
	</script>
	<body onload="hiddenKm()">
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getStuDetails.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/nbzy_sztz.do" method="post">	
		   <input type="hidden" id="pkValue" name="pkValue"
				value="<bean:write name="pkValue" />" />	
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：素质拓展 - 素质拓展信息 - 信息维护 
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<thead>
					<tr align="center">
						<td height="22" colspan="4">
							个人素质拓展信息
						</td>
					</tr>
				</thead>
				<tr>
					<td height="22" align="right" style="width: 10%">
						<font color="red">*</font>学号：
					</td>
					<td height="22" align="left" style="width: 30%">
						<html:text name="rs" property="xh" styleId="xh" maxlength="15" disabled="true"/>
						
					</td>
					<td height="22" align="right" style="width: 18%">
						是否奖励项目：
					</td>
					<td height="22" align="left">
						<html:select name="rs" styleId="sfjlxm" property="sfjlxm"
							onchange="hiddenKm()" style="background-color:#FFFFFF;">
							<html:option value="否">否</html:option>
							<html:option value="是">是</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						姓名：
					</td>
					<td height="22" align="left">
						<bean:write name="rs" property="xm" />
					</td>
					<td height="22" align="right">
						<font color="red">*</font>所属科目：
					</td>
					<td height="22" align="left">
						<html:select name="rs" styleId="kmdm" property="kmdm"
							style="background-color:#FFFFFF;">
							<html:option value=""></html:option>
							<html:options collection="kmdmList" property="kmdm"
								labelProperty="kmm"></html:options>
						</html:select>
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						性别：
					</td>
					<td height="22" align="left">
						<bean:write name="rs" property="xb" />
					</td>
					<td height="22" align="right">
						<font color="red">*</font>学年：
					</td>
					<td height="22" align="left">
						<html:select name="rs" property="xn" style="background-color:#FFFFFF"
							styleId="xn">
							<html:options collection="xnList" property="xn"
								labelProperty="xn" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						年级：
					</td>
					<td height="22" align="left">
						<bean:write name="rs" property="nj" />
					</td>
					<td height="22" align="right">
						<font color="red">*</font>学期：
					</td>
					<td height="22" align="left">
						<html:select name="rs" property="xq" style="background-color:#FFFFFF"
							styleId="xq">
							<html:option value=""></html:option>
							<html:options collection="xqList" property="xqdm"
								labelProperty="xqmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td height="22" align="left">
						<bean:write name="rs" property="xymc" />
					</td>
					<td height="22" align="right">
						<font color="red">*</font>项目名称：
					</td>
					<td height="22" align="left">
						<html:text name="rs" property="xmmc" styleId="xmmc" style="width:250px;"
							maxlength="50" />
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						专业：
					</td>
					<td height="22" align="left">
						<bean:write name="rs" property="zymc" />
					</td>
					<td height="22" align="right" style="width: 15%">
						所获成绩：
					</td>
					<td height="22" align="left">
						<html:text name="rs" property="cj" styleId="cj" style="width:250px;"
							maxlength="50" />
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						班级：
					</td>
					<td height="22" align="left">
						<bean:write name="rs" property="bjmc" />
					</td>
					<td height="22" align="right" style="width: 15%">
						<font color="red">*</font>参与(获奖)时间：
					</td>
					<td height="22" align="left">
						<html:text name="rs" property="sj" styleId="sj" onblur="dateFormatChg(this)"
							style="cursor:hand;width:80px;"
							onclick="return showCalendar('sj','y-mm-dd');" readonly="true" />
					</td>
				</tr>
				<tr align="left">
					<td align="right">
						备注:
						<br />
						(限300字)

					</td>
					<td colspan="4">
						<html:textarea name="rs" property="bz" style="width:570px" rows="4" />
					</td>
				</tr>
			</table>
			<div class="buttontool" id="button" align="center">
			 <logic:notEqual value="view" name="act">
				<button class="button2" onclick="tzInfoAddSave()" style="width:80px"
					id="buttonSave">
					保 存
				</button>
				</logic:notEqual>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonSave">
					关 闭
				</button>
			</div>
			<logic:equal value="true" name="done">
				<script type="text/javascript">
				alert("操作成功！");
				Close();
				dialogArgumentsQueryChick();
			</script>
			</logic:equal>
			<logic:equal value="false" name="done">
				<script type="text/javascript">
				alert("操作失败,或系统中已存在与带\"*\"号项目相同的记录，请检查输入的数据后再提交！");
			</script>
			</logic:equal>
		</html:form>

	</body>

</html>
