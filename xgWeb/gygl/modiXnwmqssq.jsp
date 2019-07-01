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
	
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>	
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/systemFunction.js'></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script>
		function sumbitForm(){
			//提交
			refreshForm("XsgyglDispatch.do?method=modiXnwmqssq&doType=save");	
		}
	</script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body >	
		<html:form action="/XsgyglDispatch" method="post">			
			<div class="title">
				<div class="title_img" id="title_m">
						当前所在位置：公寓管理 - 申请 - 学年文明寝室申请修改
				</div>
			</div>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4" align="center">
								学年文明寝室申请信息
							</td>
						</tr>
					</thead>					
					<tr>
				    <td align="right">
						<font color="red">*</font>楼栋名称：
					</td>
					<td align="left">
						${rs.ldmc}
						<html:hidden property="lddm" name="rs"/>
					</td>
				</tr>					
				<tr>
				<td align="right">
						<font color="red">*</font>寝室号：
					</td>					
					<td align="left">
						${rs.qsh}
						<html:hidden property="qsh" name="rs"/>
						<html:hidden property="ssbh" name="rs"/>
					</td>											
				</tr>
				<tr>
					<td align="right" >
						<font color="red">*</font>学年：
					  </td>
					<td align="left">
						${rs.xn}
						<html:hidden property="xn" name="rs"/>
					</td>
				</tr>
				<tr>	
					<td align="right">
					    申请人：
					</td>
					<td align="left">
						<html:text property="sqr" name="rs" readonly="true"></html:text>		
					</td>
				</tr>
				<tr>
					<td align="right" width="50%">
						备注：
					</td>
					<td align="left">
						<html:textarea property="bz" name="rs" cols="50" rows="4" onblur="chLeng(this,300)"></html:textarea>
					</td>
				</tr>	
				</table>
				<div class="buttontool" align="center">											
					<button class="button2" onclick="sumbitForm();" style="width:80px" id="buttonSave">
						确 定
					</button>
				</div>		
		</html:form>
		<logic:equal value="true" name="result">
			<script type="text/javascript">
			    alert('操作成功！');
			    if(window.dialogArguments){
				    Close();
					dialogArgumentsQueryChick();
				}
			 </script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script type="text/javascript">
			    alert('操作失败！');
			  </script>
		</logic:equal>
  </body>
</html>
