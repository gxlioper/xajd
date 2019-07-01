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
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
		<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script language="javascript">	     
     function cqAddSave(){
        var xh = "";
        var sj = ""; 
        var bz = "";
        var cqqk="";      
        if($("xh")){
           xh = $("xh").value;
        }
        if($("jcsj")){
           sj = $("jcsj").value;
        }
        if($("cqqk")){
           cqqk = $("cqqk").value;
        }
        if($("bz")){
           bz = $("bz").value;
        }                
        if(xh==""){
          alert("学号不能为空！");
          return false;
        }
        if(sj==""){
          alert("检查时间不能为空！");
          return false;
        }  
        if(cqqk.length>300){
          alert("出勤情况字数过大，限300字内！");
          return false;
        }
        if(bz.length>300){
          alert("备注字数过大，限300字内！");
          return false;
        }
        
        var url= "/xgxt/zgdd_jxgl.do?method=gfscqModi&doType=save";       
        refreshForm(url);
		if($("buttonSave")){
           $("buttonSave").disabled=true;
        }
<%--        getPjpyDao.getInfoEx("zjlg_xsrychb","xh||xn||rychdm",tem," 1=1",function(tag){--%>
<%--		     if(tag){--%>
<%--		        if(confirm("该生该荣誉称号已存在,确定要保存数据！\n\n点击\'确定\'更新数据，点击\'取消\'放弃保存")){--%>
<%--		           refreshForm(url);--%>
<%--		           if($("buttonSave")){--%>
<%--                     $("buttonSave").disabled=true;--%>
<%--                   }		           --%>
<%--		        }		         			        --%>
<%--		     }else{--%>
<%--		        if(confirm("确定要保存数据！")){--%>
<%--		           refreshForm(url);--%>
<%--		           if($("buttonSave")){--%>
<%--                     $("buttonSave").disabled=true;--%>
<%--                   }--%>
<%--		        }              --%>
<%--		     }--%>
<%--    	});	                  --%>
     }
</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getStuDetails.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<html:form action="/zgdd_jxgl" method="post">
			<input type="hidden" id="pkValue" name="pkValue"
				value="${pkValue}" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：军训管理 - 国防生管理 - 出勤管理--修改
				</div>
			</div>

			<table width="99%" id="rsT" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							<b>填写信息表</b>
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right" style="width: 12%">
						<font color="red">*</font>学号：
					</td>
					<td align="left">
						<html:text  name='rs' property="xh"  styleId="xh" onblur="dctStuXh()"
							onkeypress="autoFillStuInfo(event.keyCode,this)" disabled="true" />						
					</td>
					<td align="right" style="width: 12%">
						<font color="red">*</font>检查时间：
					</td>
					<td align="left" style="width: 40%">
					
						<html:text  name='rs' property="jcsj" styleId="jcsj" onblur="dateFormatChg(this)"
							style="cursor:hand;" disabled="true"
							onclick="return showCalendar('jcsj','y-mm-dd','aa');" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						姓名：
					</td>
					<td align="left">
						<bean:write name='rs' property="xm" />
					</td>
					<td align="right">
						所在连队：
					</td>
					<td align="left">
						<html:select name='rs' property="lddm" styleId="lddm" style="width:150px">
							<html:option value="">--请选择--</html:option>
							<html:options collection="ldList" property="dm"
								labelProperty="mc" />
						</html:select>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						性别：
					</td>
					<td align="left">
						<bean:write name='rs' property="xb" />
					</td>
					<td align="right">
						早操情况：
					</td>
					<td align="left">
						<html:select name='rs' property="zcqk" styleId="zcqk" style="width:150px">
							<html:option value="">--请选择--</html:option>
							<html:options collection="zcPfList" property="dm"
								labelProperty="mc" />
						</html:select>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						年级：
					</td>
					<td align="left">
						<bean:write name='rs' property="nj" />
					</td>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						<bean:write name='rs' property="xymc" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						专业：
					</td>
					<td align="left">
						<bean:write name='rs' property="zymc" />
					</td>
					<td align="right">
						班级：
					</td>
					<td align="left">
						<bean:write name='rs' property="bjmc" />
					</td>
				</tr>
				<tr style="height:22px">

					<td align="right">
						出勤情况：
						<br />
						<span class="style1">(限300字)&nbsp;</span>
					</td>
					<td colspan="3">
						<html:textarea  name='rs' property='cqqk' style="width:99%" rows='8' />
					</td>
				</tr>
				<tr style="height:22px">

					<td align="right">
						备注：
						<br />
						<span class="style1">(限300字)&nbsp;</span>
					</td>
					<td colspan="3">
						<html:textarea name='rs' property='bz' style="width:99%" rows='5' />
					</td>
			</table>
			<div class="buttontool" align="center">
				<logic:notEqual value="view" name="act">
				<button type="button" class="button2" id="buttonSave" onclick="cqAddSave()">
					保 存
				</button>
				&nbsp;&nbsp;
				</logic:notEqual>
				<button type="button" class="button2" id="buttonClose" onclick="Close();return false;">
					关 闭
				</button>
			</div>
			<logic:equal name="done" value="true">
				<script>
			      alert("添加成功！");
                  if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
    	               window.dialogArguments.document.all("search_go").click();
    	               Close();
                  }			          
			   </script>
			</logic:equal>
			<logic:equal name="done" value="false">
				<script>
			        alert("添加失败！");
			    </script>
			</logic:equal>
		</html:form>
	</body>
</html>
