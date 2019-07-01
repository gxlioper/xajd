
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
	<script type="text/javascript" src="dwr/interface/nbtyxszz.js"></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript" src="js/Function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	
	<script language="javascript">	
	 function getJtjjknbz(){
	 	var bzlx="";
	 	if($("bzlx").value!=null && $("bzlx").value!=""){
	 	bzlx=$("bzlx").value;
	 	nbtyxszz.getJtjjknbz(bzlx,getData);
	 	}
	 	else
	 	{
	 	$("bzje").value="";
	 	}
	 } 
	 function getData(data)
	 {
	 	$("bzje").value=data[0].je;
	 }    
     function rychSqSave(){
        var xh = "";
        var bzlx = "";
        var xnzz="";
        var sqly="";
        var bz  = "";
        var cjqgdg ="";
        if($("notSqsj").value=="yes"){
        	alert("对不起，不在申请时间内！");
        	return false;
        }
        if($("xnzz")){
        	xnzz=$("xnzz").value
        }
        if($("xh")){
           xh = $("xh").value;
        }
        if($("bz")){
          bz = $("bz").value;
        }
        if($("bzlx")){
          bzlx=$("bzlx").value
        }
        if($("sqly")){
          sqly=$("sqly").value
        }
        if(sqly.length>800){
          alert("申请理由字数过大，限400字内！");
          return false;
        }
        if($("cjqgdg")){
        	cjqgdg=$("cjqgdg").value
        }
        if(xnzz.length>60){
        	alert("何学年获得何资助字数过大，限60字");
        	return false;
        }
        if(cjqgdg.length>60){
        	alert("是否参加勤工助学和助学贷款字数过大，限60字");
        	return false;
        }
        if(bzlx==""){
          alert("补助类型必须选择！");
          return false;
        }
        if(xh==""){
          alert("学号不能为空！");
          return false;
        }
        if(bz.length>800){
          alert("备注字数过大，限400字内！");
          return false;
        }      
        refreshForm("/xgxt/nbtyJtjjkns.do?method=jtjjknsSq&doType=save");
        if($("buttonSave")){
          $("buttonSave").disabled=true;
        }
     }
    function rychSqPrint(){
        window.open('nbtyJtjjkns.do?method=jtjjknsPrint&pkValue=${pkValue}');
        }	
</script>
	<%--		<input type="hidden" id="printpk" value="${printpk }"/>--%>
	<body>
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
		   <script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>
		   <html:form action="/nbtyJtjjkns" method="post">
		   	<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xh-xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url" value="/nbtyJtjjkns.do?method=jtjjknsSq&doType=view" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" name="pkValue" id="pkValue" value="${pk}"/>
			<input type="hidden" name="save_csrq" id="save_csrq" value="${rs.csrq}"/>
			<input type="hidden" name="save_mzmc" id="save_mzmc" value="${rs.mzmc}"/>
			<input type="hidden" name="save_sqsj" id="save_sqsj" value="${nowTime}"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：学生资助 - 申请 - 填写申请表
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
						<logic:equal name="notSqsj" value="no">
							<b>家庭经济困难学生补助申请</b>
						</logic:equal>
						<logic:equal name="notSqsj" value="yes">
							<font color="red">目前不在申请时间范围内，暂不开放申请！</font>
						</logic:equal>
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
						    <button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
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

					<td align="right" style="width: 10%">
						学年：
					</td>
					<td align="left" style="width: 40%">
						<bean:write name="xn" />	
						<html:hidden property="save_xn"  value="${xn}"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						姓名：
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="xm" />
						</logic:notEmpty>
					</td>
					<td align="right">
						学期：
					</td>
					<td align="left">
						<bean:write name="xqmc"/>
						<html:hidden property="save_xq" value="${xq}"/>
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
					</td>
					<td align="right">
						<font color="red"></font>年度：
					</td>
					<td align="left">
						<bean:write name="nd" />
						<html:hidden property="save_nd" value="${nd}"/>
					</td>		
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="xymc" />
					</logic:notEmpty>
					</td>
					<td align="right">
						申请补助：
					</td>
					<td align="left">
						<html:select property="save_bzlx" styleId="bzlx" onchange="getJtjjknbz()">
							<html:option value=""></html:option>
							<html:options collection="xszzList" property="dm"
								labelProperty="mc" />
								
						</html:select>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						专业：
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="zymc" />
					</logic:notEmpty>
					</td>
					<td align="right">
						补助金额：
					</td>
					<td align="left">
						<html:text   property="save_bzje" styleId="bzje" onkeyup="value=value.replace(/[^\d]/g,'')" onblur="chLeng(this,10)" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						班级：
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="bjmc" />
					</logic:notEmpty>
					</td>
					<td align="right">
						品德等第：
					</td>
					<td align="left">
						<html:text property="save_pddd" onblur="chLeng(this,10)" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						年级：
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="nj" />
					</logic:notEmpty>	
					</td>
					<td>
						
					</td>
					<td>
					  
					</td>
					</tr>
				<tr style="height:22px">
					<td align="right">
						何学年获何资助：(写明受助金额)
						<br />
						<span class="style1">(限30字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="2" styleId="xnzz" style="width:98%" property="save_xnzz" onblur="chLeng(this,30)"/>
					</td>
				</tr>	
				<tr style="height:22px">
					<td align="right">
						是否参加勤工助学和申请助学贷款:
						<br />
						<span class="style1">(限30字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="2" styleId="cjqgdg" style="width:98%" property="save_cjqgdg" onblur="chLeng(this,30)"/>
					</td>
				</tr>	
				<tr style="height:22px">
					<td align="right">
						申请理由：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" styleId="sqly" style="width:98%" property="save_sqly" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						备注：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" styleId="bz" property="save_bz" onblur="chLeng(this,400)"/>
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn"  width="100%">	
					<button type="button" class="button2" id="buttonSave" onclick="rychSqSave();" style="width:80px">
						保  存 
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="rychSqPrint()" style="width:80px">
						打  印
					</button>
			</div>
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

