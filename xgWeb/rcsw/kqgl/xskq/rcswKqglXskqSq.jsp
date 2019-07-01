
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
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type="text/javascript" src="dwr/interface/rcswKqglXskqService.js"></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/Function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">	
	var checks="";
	     
     function getData(data){
		 if(data=="false"){
        	alert("请假天数输入不正确！");
         }else{
			showTips("保存中，请等待...");
     		refreshForm("/xgxt/rcswKqglXskq.do?method=rcswKqglXskqSq&doType=save");     
     		if($("buttonSave")){
         		 $("buttonSave").disabled=true;
       		}   
         }
     }
	
     function rychSqSave(){
        var qjsj = "";
        var qjqssj="";
        var qjjssj="";
        var qjlx="";
        var xh="";
        if($("xh")){
        	xh=$("xh").value;
        }
        if($("qjlx")){
           qjlx = $("qjlx").value;
        }
        if($("qjsj")){
          qjsj=$("qjsj").value;
        }
        if(xh==""){
        	alert("学号必须填写！");
        	return false;
        }
        if(qjlx==""){
        	alert("请假类型必须选择！");
        	return false;
        }
        if($("qjsj")){
        	qjsj=$("qjsj").value;
        }
        if(qjsj==""){
       		alert("请假天数必须填写！");
       		return false;
        }
        if(qjsj=="0"){
        	alert("请假天数不能为零!");
        	return false;
        }
        if(chkNumInput($("qjsj"))){
        	alert("您输入的时间格式有误！");
        	return false;
        }
        if($("qjqssj") && $("qjjssj")){
        	qjqssj=$("qjqssj").value;
        	qjjssj=$("qjjssj").value;
        }
        if(qjqssj!="" && qjjssj!=""){
        	if(qjqssj>qjjssj){
        		alert("请假开始时间早于结束时间！");
        		return false;
        	}
        }else{
        	alert("请把请假时间填写完整！");
        	return false;
        }
        qjsj=eval(qjsj);
        qjqssj=eval(qjqssj);
        qjjssj=eval(qjjssj);
        rcswKqglXskqService.checkTimes(qjqssj,qjjssj,qjsj,getData);
        
       
     }

   
</script>
	<%--		<input type="hidden" id="printpk" value="${printpk }"/>--%>
	<body>
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
		<script language="javascript" src="js/commanFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
		   <script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>
		   <html:form action="/rcswKqglXskq" method="post">
		   	<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xh-xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url" value="/rcswKqglXskq.do?method=rcswKqglXskqSq" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" name="pkValue" id="pkValue" value="${pk}"/>
			<input type="hidden" name="save_sqsj" id="save_sqsj" value="${nowTime}"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：日常事物 - 考勤管理 - 学生请假申请
				</div>
			</div>

			<logic:present name="result">
			<script>
				alert(''+$('message').value);
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
			</logic:present>

			<table width="99%" id="rsT" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							<b>学生请假申请</b>
						</td>
					</tr>
				</thead>
				<tr>
				<td align="right" style="width: 10%">
						<font color="red">*</font>学号：
					</td>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="left">
						    <html:text  property="xh" styleId="xh"
								onblur="dctStuXh()" 
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<html:hidden name='rs' property="save_xh" value="${rs.xh}" />
						    <button type="button" onclick="checkXhExists('qjlx-qjqssj-qjjssj-qjsj-qjly-bz');showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="left">
						<html:text name="rs" property="xh" readonly="true" styleId="xh"/>
						<html:hidden property="save_xh" value="${rs.xh}"/>
						</td>
					</logic:equal>
					<td align="right">
						姓名：
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="xm" />
						</logic:notEmpty>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						性别：
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="xb" />
					</logic:notEmpty>
					<td align="right">
						年级：
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="nj" />
					</logic:notEmpty>	
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="xymc" />
						<html:hidden property="save_xydm" value="${rs.xydm}"/>
					</logic:notEmpty>
					</td>
					<td align="right">
						专业：
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="zymc" />
						<html:hidden property="save_zydm" value="${rs.zydm}"/>
					</logic:notEmpty>
					</td>	
				</tr>
				<tr style="height:22px">
					<td align="right">
						班级：
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="bjmc" />
						<html:hidden property="save_bjdm" value="${rs.bjdm}"/>
					</logic:notEmpty>
					</td>
					<td align="right">
						<font color="red">*</font>请假类型：
					</td>
					<td align="left">
						<html:select property="save_qjlx" styleId="qjlx">
							<html:option value=""></html:option>
							<html:options collection="qjlxList" property="dm"
								labelProperty="mc" />
								
						</html:select>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<font color="red"></font>年度：
					</td>
					<td align="left">
						<bean:write name="nd" />
						<html:hidden property="save_nd" value="${nd}"/>
					</td>	
					<td align="right">
						<font color="red">*</font>请假时间：
					</td>
					<td align="left">
						<html:text property="save_qjqssj" styleId="qjqssj" style="width:80px"
							onclick="return showCalendar('qjqssj','y-mm-dd');" 
							onblur="dateFormatChg(this)" readonly="true" />至
						 <html:text property="save_qjjssj" styleId="qjjssj" style="width:80px"
							onclick="return showCalendar('qjjssj','y-mm-dd');" 
							onblur="dateFormatChg(this)" readonly="true" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right" style="width: 10%">
						学年：
					</td>
					<td align="left" style="width: 40%">
						<bean:write name="xn" />	
						<html:hidden property="save_xn"  value="${xn}"/>
					</td>
					<td align="right">
						<font color="red">*</font>请假天数：
					</td>
					<td align="left">
						<html:text property="save_qjsj" styleId="qjsj"	onblur="chLeng(this,20)" style="width:80px" />天
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						学期：
					</td>
					<td align="left">
						<bean:write name="xqmc"/>
						<html:hidden property="save_xq" value="${xq}"/>
					</td>
					<td align="right">
						公寓辅导员：
						</td>
					<td align="left">
					  	<logic:notEmpty name="gygly">
							<bean:write name="gygly" />
						</logic:notEmpty>
					</td>
					</tr>
				<tr style="height:22px">
					<td align="right">
						申请理由：
						<br />
						<span class="style1">(限200字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" styleId="qjly" style="width:98%" property="save_qjly" onblur="chLeng(this,200)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						备注：
						<br />
						<span class="style1">(限200字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" styleId="bz" property="save_bz" onblur="chLeng(this,200)"/>
					</td>
				</tr>
			</table>
			<logic:equal name="writeAbled" value="yes">
			<div class="buttontool" id="btn"  width="100%">	
					<button type="button" class="button2" id="buttonSave" onclick="rychSqSave();" style="width:80px">
						保  存 
					</button>
			</div>
			</logic:equal>
			<logic:equal name="done" value="true">
				<script>
			          alert("申请成功！");
			        </script>
			</logic:equal>
			<logic:equal name="done" value="false">
				<script>
			          alert("申请失败！");
			    </script>
			</logic:equal>
			<html:hidden property="noSq"  value="${notSqsj}" styleId="notSqsj"/>
			<logic:equal name="notKns" value="yes">
				<script>
			        alert("对不起，只有困难生才能申请家庭经济困难生困难补助！");			    
			        </script>
			</logic:equal>
		</html:form>
	</body>


</html>

