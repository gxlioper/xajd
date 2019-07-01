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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
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
	<body>	
		<html:form action="/csmz_gygl.do?method=fdy_xqxx_modi" method="post">	
		<input type="hidden" name="qshV" value=""/>		
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置： 公寓管理 - 辅导员下寝 - 信息修改
				</div>
			</div>			
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="4" align="center">
							信息修改
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right" width="15%">
						<font color="red">*</font>职工号:
					</td>
					<td align="left">
						<html:text name='rs' property="zgh" styleId="zgh" readonly="true"/>
					</td>
					<td align="right" width="10%">
						学年:
					</td>
					<td align="left">
						<html:text name='rs' property="xn" styleId="xn" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>辅导员姓名:
					</td>
					<td align="left">
						<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
					</td>
					<td align="right">
						学期:
					</td>
					<td align="left">
						<html:text name='rs' property="xq" styleId="xq" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>楼栋:
					</td>
					<td align="left">
						<html:select name="rs" property="lddm" style="width:150px" styleId="lddm" onchange="GetQshList()">
							<html:option value="">--请选择--</html:option>
							<html:options collection="ldList" property="lddm" labelProperty="ldmc" />
						</html:select>
					</td>
					<td align="right">
						<font color="red">*</font>寝室号:
					</td>
					<td align="left">
						<html:select name="rs" property="qsh" style="width:110px"
							onchange="GetCwhList()">
							<html:option value=""></html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>访问时间:
					</td>
					<td align="left">
						<html:text name="rs" property="sj" readonly="true"
								onblur="dateFormatChg(this)"
								onclick="return showCalendar('sj','y-mm-dd');"
								style="cursor:hand " />
					</td>
					<td align="left">
						&nbsp;
					</td>
					<td align="left">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>解决的主要问题:<br>限500字内
					</td>
					<td align="left" colspan="3">
					<html:textarea name="rs" property="zywt" cols="76" rows="5" ></html:textarea>	
					</td>	
				</tr>
				<tr>
					<td align="right">
						备注:<br>限200字内
					</td>
					<td align="left" colspan="3">
					<html:textarea  name="rs" property="bz" cols="76" rows="5" ></html:textarea>	
					</td>	
				</tr>
			</table>
				<div class="buttontool" align="center">	
					<logic:notPresent name="onlyView">			
					<button class="button2" onclick="tbDataSave()" style="width:80px" id="buttonSave">
					修改	
					</button>	
					</logic:notPresent>
					<button class="button2" onclick="Close();return false;" style="width:80px" id="buttonSave">
					关闭
					</button>					
				</div>
		</html:form>		
		<logic:equal value="true" name="done">
			<script type="text/javascript">
	           alert("保存成功！");
	           Close();
			   window.dialogArguments.document.getElementById('search_go').click();
	        </script>
		</logic:equal>
		<logic:equal value="false" name="done">
			<script type="text/javascript">
	          alert("保存失败！");
	          Close();
			  window.dialogArguments.document.getElementById('search_go').click();
	        </script>
		</logic:equal>
  </body>
  <script type="text/javascript">
  function tbDataSave(){
      if(IsNoEmpty('zgh-xm-lddm-qsh-sj-zywt'))
       {
          if($('zywt').value.length>500){
             alert('主要问题字数超出500字！');
             return false;
          }else if($('bz').value.length>200){
             alert('备注字数超出200字！');
             return false;
          }else{
             refreshForm('/xgxt/csmz_gygl.do?method=fdy_xqxx_modiToSave');
          }
       }    
  }
  </script>
</html>
