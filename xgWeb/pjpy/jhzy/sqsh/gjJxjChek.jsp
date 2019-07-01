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
<html:html>
<base target="_self" />
<head>


	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
<script type='text/javascript' >
window.onload = function(){
    userType = $("userType").value;
    if(userType=="xx"||userType=="admin"){
        $("fdyshyj").readOnly=true;
        $("xyshyj").readOnly=true;
    }else  if(userType=="xy"){
        $("fdyshyj").readOnly=true;
        $("xxshyj").readOnly=true;
    }else {
        $("xxshyj").readOnly=true;
        $("xyshyj").readOnly=true;
    }
}
function dataSave(){
   var fdyshyj="";
   var xyshyj="";
   var xxshyj="";
   if($("fdyshyj")){
      fdyshyj=$("fdyshyj").value;
   }
   if($("xyshyj")){
      xyshyj=$("xyshyj").value;
   }
   if($("xxshyj")){
      xxshyj=$("xxshyj").value;
   }
   if(fdyshyj.length>300){
	   alert("辅导员审核意见字数过大，限300字内!");
	   return false;
   } 
   if(xyshyj.length>300){
	   alert("<bean:message key="lable.xsgzyxpzxy" />审核意见字数过大，限300字内!");
	   return false;
	}
   if(xxshyj.length>300){
	   alert("学校审核意见字数过大，限300字内!");
	   return false;
	} 
	refreshForm("/xgxt/jhzy_pjpySqsh.do?method=jxjChek&doType=save");	         
}
</script>
</head>
<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：评价评优 - 审核 - 奖学金审核 - <bean:write name = "jxjmc"/>
		</div>
	</div>
	
	<html:form action="/jhzy_pjpySqsh" method="post">
	<input type="hidden" name="userType" value="${userType}">	
	<input type="hidden" name="jxjdm" value="${jxjdm}">	
	<input type="hidden" name="pkValue" value="${pkValue}">				
		<table class="tbstyle" width="100%">
			<tr>
				<td width="16%">
					<div align="center">
						学年
					</div>
					</td>
					<td width="34%">
                	  <bean:write name='rsJxj' property="xn" />
					</td>	
					<td>
					</td>
					<td>
					</td>			
			</tr>
			<tr>
                    <td align="center" width="16%">
						<font color="red">*</font>学号
					</td>
					<td align="left" width="34%">
                     <bean:write name='rs' property="xh" />
					</td>	
					<td width="16%" scope="col">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="34%" scope="col">
						<input type="text" id="xm" name="xm" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xm" />" readonly="true">
					</td>	
				
			</tr>
			<tr>
					<td scope="row">
						<div align="center">
							性别
						</div>
					</td>
					<td>
						<input type="text" id="xb" name="xb" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xb" />" readonly="true">
					</td>
					<td>
						<div align="center">
							身份证号
						</div>
					</td>
					<td>
						<input type="text" id="sfzh" name="sfzh"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="sfzh" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td>
						<input type="text" id="xymc" name="xymc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xymc" />" readonly="true">
					</td>
					<td>
						<div align="center">
							专业
						</div>
					</td>
					<td>
						<input type="text" id="zymc" name="zymc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="zymc" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							班级
						</div>
					</td>
					<td>
						<input type="text" id="bjmc" name="bjmc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="bjmc" />" readonly="true">
					</td>
					<td>
						<div align="center">
							年级
						</div>
					</td>
					<td>
						<input type="text" id="nj" name="nj" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="nj" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							入学年月
						</div>
					</td>
					<td>
						<input type="text" id="rxny" name="rxny"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="rxny" />" readonly="true">
					</td>
					<td>
						<div align="center">
							出生年月
						</div>
					</td>
					<td>
						<input type="text" id="csny" name="csny"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="csny" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							民族
						</div>
					</td>
					<td>
						<input type="text" id="mzmc" name="mzmc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="mzmc" />" readonly="true">
					</td>
					<td>
						<div align="center">
							政治面貌
						</div>
					</td>
					<td>
						<input type="text" id="zzmmmc" name="zzmmmc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="zzmmmc" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							联系电话
						</div>
					</td>
					<td>
						<input type="text" id="lxdh" name="lxdh" 
							style="width:100%;heigh:100%" maxlength="20" readonly="true"
							value="<bean:write name='rsJxj' property="sjhm" />" 
							>
					</td>
					<td>
					</td>
					<td>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							申请理由<br />
							(全面反映德智体美情况)
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rsJxj" property="sqly" rows='10' style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
				<td colspan="4">
					<fieldset>
						<legend>
							所获相关等级列表
						</legend>
					<table width="100%" class="tbstyle">
							<thead>
								<tr align="center">
									<td width="30%">
										学年/学期
									</td>
									<td >
										所获等级
									</td>
									<td >
										成绩
									</td>
								</tr>
							</thead>
							<logic:iterate id="s" name="shDjList">
								<tr align="center">
									<td>
										<bean:write name="s" property="xn" />/<bean:write name="s" property="xqmc" />										
									</td>
									<td>										
										<bean:write name="s" property="djksmc" />
									</td>
									<td>										
										<bean:write name="s" property="cj" />
									</td>									
								</tr>
							</logic:iterate>
					</table>
					</fieldset>
				</td>				
			</tr>			
			
			<tr>
				<td colspan="4">
					<fieldset>
						<legend>
							曾获奖学金
						</legend>
					<table width="100%" class="tbstyle">
							<thead>
								<tr align="center">
									<td width="30%">
										学年
									</td>
									<td >
										所获奖学金
									</td>									
								</tr>
							</thead>
							<logic:iterate id="s" name="shJxjList">
								<tr align="center">
									<td>
										<bean:write name="s" property="xn" />
										
									</td>
									<td>										
										<bean:write name="s" property="jxjmc" />
									</td>
								</tr>
							</logic:iterate>
					</table>
					</fieldset>
				</td>				
			</tr>
			<tr>
				<td colspan="4">
					<fieldset>
						<legend>
							综合排名
						</legend>
						<table width="100%" class="tbstyle">
							<thead>
								<tr align="center">
									<td width="10%">
										学年/学期
									</td>
									<td width="10%">
										德育素质分/名次
									</td>
									<td width="10%">
										学习成绩/名次
									</td>
									<td width="10%">
										体育成绩/名次
									</td>
									<td width="15%">
										综合测评分/名次
									</td>
								</tr>
							</thead>
							<logic:iterate id="s" name="cjList">
								<tr align="center">
									<td>
										<bean:write name="s" property="xn" />
										/
										<bean:write name="s" property="xqmc" />
									</td>
									<td>
										<bean:write name="s" property="dyf" />
										/
										<bean:write name="s" property="dypm" />
									</td>
									<td>
										<bean:write name="s" property="zyf" />
										/
										<bean:write name="s" property="zypm" />
									</td>
									<td>
										<bean:write name="s" property="tyf" />
										/
										<bean:write name="s" property="typm" />
									</td>
									<td>
										<bean:write name="s" property="zhf" />
										/
										<bean:write name="s" property="zhpm" />
									</td>

								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</td>
			</tr>		
			<tr>
			
				<td>
					<div align="center">
						
					</div>
				</td>
				<td>
					
				</td>
				<td>
					<div align="center">
						审核
					</div>
				</td>
				<td>
					<html:select name="rsJxj" property="yesNo" styleId="yesNo">
					   <html:options collection="chkList" property="en"
								labelProperty="cn" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						辅导员意见：<br>
						<font color="red"><限300字>
						</font>					
					</div>
				</td>
				<td colspan="3">
					<html:textarea name="rsJxj" property="fdyyj" rows='5'  styleId="fdyshyj"									
						style="width:100%" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />意见：<br>
						<font color="red"><限300字>
						</font>					
					</div>
				</td>
				<td colspan="3">
					<html:textarea name="rsJxj" property="xyshyj" rows='5' styleId="xyshyj"	
						style="width:100%" />
				</td>
			</tr>
				<tr>
				<td>
					<div align="center">
						学校意见：<br>
						<font color="red"><限300字>
						</font>					
					</div>
				</td>
				<td colspan="3">
					<html:textarea name="rsJxj" property="xxshyj" rows='5' styleId="xxshyj"	
						style="width:100%" />
				</td>
			</tr>
		</table>
		<div class="buttontool" id="btn" style="position: absolute;width:100%">

			<button type="button" class="button2" onClick="dataSave()" id="buttonSave">
				保存
			</button>
			&nbsp;&nbsp;
			<button type="button" class="button2" onClick="Close()">
				关闭
			</button>
		</div>
	</html:form>
				<logic:equal value="true" name="done">
			  <script type="text/javascript">
			    alert('操作成功！');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
			</logic:equal>	
			<logic:equal value="false" name="done">
			  <script type="text/javascript">
			    alert('操作失败！');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
			</logic:equal>
</body>
<logic:present name="msg">
	<script>
			alert(''+document.getElementById('msg').value);
		</script>
</logic:present>
</html:html>
