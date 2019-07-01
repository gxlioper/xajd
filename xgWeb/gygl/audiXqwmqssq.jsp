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
			if(val('disabled') == 'disabled'){
				alert('上级已经审核，暂时不能修改审核结果！');
				return false;
			}
			//提交
			refreshForm("XsgyglDispatch.do?method=audiXqwmqssq&doType=save");			
		}
	</script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body >	
		<html:form action="/XsgyglDispatch" method="post">	
			<input id="pk" name="pk" value="${rs.pk}" type="hidden"/>
			<input id="disabled" name="disabled" value="${rs.disabled}" type="hidden"/>		
			<div class="title">
				<div class="title_img" id="title_m">
						当前所在位置：公寓管理 - 审核 - 学期文明寝室申请审核
				</div>
			</div>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4" align="center">
								学期文明寝室申请信息
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
							<font color="red">*</font>寝室号：
						</td>					
						<td align="left">
							${rs.qsh}
							<html:hidden property="qsh" name="rs"/>
							<html:hidden property="ssbh" name="rs"/>
						</td>	
						<td align="right">
							<font color="red">*</font>学期：
						</td>
						<td align="left">
							${rs.xqmc}
							<html:hidden property="xq" name="rs"/>
						</td>					
					</tr>
					<tr>	
						<td align="right">
						    申请人：
						</td>
						<td align="left">
							${rs.sqr }		
						</td>
						<td align="right">
							
						</td>
						<td align="left">
							
						</td>
					</tr>
					<tr>
						<td align="right" >
							备注：
						</td>
						<td align="left" colspan="3">
							${rs.bz}
						</td>
					</tr>
					<!--公寓辅导员	-->
					<logic:equal value="fdy" name="yhType">
					<tr>	
						<td align="right">
							辅导员审核：
						</td>
						<td align="left">
							<html:select property="fdysh" name="rs">
								<html:option value=""></html:option>
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>							
						<td align="right">
						   审核时间：
						</td>
						<td align="left">
							${rs.fdyshsj}
						</td>
					</tr>
					<tr>
						<td align="right" >
							辅导员意见：
						</td>
						<td align="left" colspan="3">
							<html:textarea property="fdyshyj" name="rs" cols="80" rows="4" onblur="chLeng(this,100)"></html:textarea>
						</td>
					</tr>
					<tr>	
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />审核：
						</td>
						<td align="left">
							${rs.xysh}
						</td>							
						<td align="right">
						   <bean:message key="lable.xsgzyxpzxy" />审核时间：
						</td>
						<td align="left">
							${rs.xyshsj}
						</td>
					</tr>
					<tr>
						<td align="right" >
							<bean:message key="lable.xsgzyxpzxy" />意见：
						</td>
						<td align="left" colspan="3">
							${rs.xyshyj}
						</td>
					</tr>
					<tr>	
						<td align="right">
							学校审核：
						</td>
						<td align="left">
							${rs.xxsh}
						</td>							
						<td align="right">
						   学校审核时间：
						</td>
						<td align="left">
							${rs.xxshsj}
						</td>
					</tr>
					<tr>
						<td align="right" >
							学校意见：
						</td>
						<td align="left" colspan="3">
							${rs.xxshyj}
						</td>
					</tr>
					</logic:equal>
					<!--end公寓辅导员	-->
					<!--<bean:message key="lable.xsgzyxpzxy" />	-->
					<logic:equal value="xy" name="yhType">
					<tr>	
						<td align="right">
							辅导员审核：
						</td>
						<td align="left">
							${rs.fdysh}
						</td>							
						<td align="right">
						   审核时间：
						</td>
						<td align="left">
							${rs.fdyshsj}
						</td>
					</tr>
					<tr>
						<td align="right" >
							辅导员意见：
						</td>
						<td align="left" colspan="3">
							${rs.fdyshyj}
						</td>
					</tr>
					<tr>	
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />审核：
						</td>
						<td align="left">
							<html:select property="xysh" name="rs">
								<html:option value=""></html:option>
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>							
						<td align="right">
						   <bean:message key="lable.xsgzyxpzxy" />审核时间：
						</td>
						<td align="left">
							${rs.xyshsj}
						</td>
					</tr>
					<tr>
						<td align="right" >
							<bean:message key="lable.xsgzyxpzxy" />意见：
						</td>
						<td align="left" colspan="3">
							<html:textarea property="xyshyj" name="rs" cols="80" rows="4" onblur="chLeng(this,100)"></html:textarea>
						</td>
					</tr>
					<tr>	
						<td align="right">
							学校审核：
						</td>
						<td align="left">
							${rs.xxsh}
						</td>							
						<td align="right">
						   学校审核时间：
						</td>
						<td align="left">
							${rs.xxshsj}
						</td>
					</tr>
					<tr>
						<td align="right" >
							学校意见：
						</td>
						<td align="left" colspan="3">
							${rs.xxshyj}
						</td>
					</tr>
					</logic:equal>
					<!--end<bean:message key="lable.xsgzyxpzxy" />	-->
					<!--学校	-->
					<logic:equal value="xx" name="yhType">
					<tr>	
						<td align="right">
							辅导员审核：
						</td>
						<td align="left">
							${rs.fdysh}
						</td>							
						<td align="right">
						   	审核时间：
						</td>
						<td align="left">
							${rs.fdyshsj}
						</td>
					</tr>
					<tr>
						<td align="right" >
							辅导员意见：
						</td>
						<td align="left" colspan="3">
							${rs.fdyshyj}
						</td>
					</tr>
					<tr>	
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />审核：
						</td>
						<td align="left">
							${rs.xysh}
						</td>							
						<td align="right">
						   <bean:message key="lable.xsgzyxpzxy" />审核时间：
						</td>
						<td align="left">
							${rs.xyshsj}
						</td>
					</tr>
					<tr>
						<td align="right" >
							<bean:message key="lable.xsgzyxpzxy" />意见：
						</td>
						<td align="left" colspan="3">
							${rs.xyshyj}
						</td>
					</tr>
					<tr>	
						<td align="right">
							学校审核：
						</td>
						<td align="left">
							<html:select property="xxsh" name="rs">
								<html:option value=""></html:option>
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>							
						<td align="right">
						   学校审核时间：
						</td>
						<td align="left">
							${rs.xxshsj}
						</td>
					</tr>
					<tr>
						<td align="right" >
							学校意见：
						</td>
						<td align="left" colspan="3">
							<html:textarea property="xxshyj" name="rs" cols="80" rows="4" onblur="chLeng(this,100)"></html:textarea>
						</td>
					</tr>
					</logic:equal>
					<!--end学校	-->
				</table>
				<div class="buttontool" align="center">											
					<button class="button2" onclick="sumbitForm();" style="width:80px" id="buttonSave">
						保 存
					</button>
					&nbsp;&nbsp;
					<button class="button2" onclick="Close();return false;" style="width:80px" id="buttonSave">
						关 闭
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
