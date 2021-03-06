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
	<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script>
		function sumbitForm(){
			//非空判断
			var column = ["xn","lddm","qsh"];
		  	for(var i=0; i<column.length; i++){
		  		if(document.getElementById(column[i]).value==""){
		  			alert('请将带\*号的项目填写完整!');
		  			return false;
		  		}
		  	}
		  	var xn = val('xn');
		  	var ssbh = val('lddm') + '-' + val('qsh');
			//记录是否存在判断
			var pkValue = xn + ssbh;
			systemFunction.checkExists("gygl_xnwmqssqb","xn||ssbh",pkValue,
				function(data){
					if(data == true){
						alert("您选择的寝室在该学年已经申请过学年文明寝室！");
						return false;
					}else{
						//判断两学期都达到学期文明寝室要求
						getGyglDAO.xnxqwmqs({xn:xn,ssbh:ssbh},function(data){
							if(data <2){
								alert('两学期都获得学期文明寝室才可申请学年文明寝室！');
								return false;
							}else{
								//提交
								refreshForm("XsgyglDispatch.do?method=xnwmqssq&doType=save");
							}
						});
					}
				}
			);			
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
						当前所在位置：公寓管理 - 申请 -学年文明寝室申请
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
							<html:select property="lddm" style="width:80px" styleId="lddm"
								onchange="GetQshList()">
								<html:option value=""></html:option>
								<html:options collection="ldList" property="lddm"
									labelProperty="ldmc" />
							</html:select>
					</td>
				</tr>					
				<tr>
				<td align="right">
						<font color="red">*</font>寝室号：
					</td>					
					<td align="left">
					<html:select property="qsh" styleId="qsh">
						<html:option value=""></html:option>
						<html:options collection="qshList" property="dm"
							  labelProperty="mc" />
					</html:select>
					</td>											
				</tr>
				<tr>
					<td align="right" >
						<font color="red">*</font>学年：
					  </td>
					<td align="left">
						<html:select property="xn" style="width:90px" styleId="xn">
							<html:options collection="xnList" property="xn"
								labelProperty="xn" />
						</html:select>
					</td>
				</tr>
				<tr>	
					<td align="right">
					    申请人：
					</td>
					<td align="left">
						<html:text property="sqr" readonly="true"></html:text>		
					</td>
				</tr>
				<tr>
					<td align="right" width="50%">
						备注：
					</td>
					<td align="left">
						<html:textarea property="bz" cols="50" rows="4" onblur="chLeng(this,300)"></html:textarea>
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
