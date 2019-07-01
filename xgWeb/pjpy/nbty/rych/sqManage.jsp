
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
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript" src="js/Function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	
	<script language="javascript">	     
     function rychSqSave(){
        var xh = "";
        var rychdm = "";
        var bz  = "";
        if($("xh")){
           xh = $("xh").value;
        }
        if($("rychdm")){
           rychdm = $("rychdm").value;
        }
        if($("bz")){
          bz = $("bz").value;
        }
        if(xh==""){
          alert("学号不能为空！");
          return false;
        }
        if(rychdm==""){
          alert("荣誉称号不能为空！");
          return false;
        }  
        if(bz.length>1000){
          alert("备注字数过大，限1000字内！");
          return false;
        }      
        refreshForm("/xgxt/nbty_rych.do?method=rychSq&doType=save");
        if($("buttonSave")){
          $("buttonSave").disabled=true;
        }
     }
     function rychSqPrint(){
        window.open('nbty_rych.do?method=rychDjb&pkValue=${pkValue}');
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
		   <html:form action="/nbty_rych" method="post">
			<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj-jg-mzmc" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xh-xm-xb-xymc-nj-zymc-bjmc-jg-mzmc" />
			<input type="hidden" id="url" name="url" value="/nbty_rych.do?method=rychSq" />
			<input type="hidden" id="viewName" name="viewName" value="view_nbty_xsrychb" />
			<input type="hidden" id="tabName" name="tabName" value="nbty_xsrychb" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" name="save_xn"  value="${xn}"/>
            <input type="hidden" name="save_xq"  value="${xq}"/>
            <input type="hidden" name="save_nd"  value="${nd}"/>
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" name="save_mzmc" id="save_mzmc" value="${rs.mzmc }"/>
			<input type="hidden" name="save_jg" id="save_jg" value="${rs.jg}"/>
			<input type="hidden" name="save_sbsj" id="save_sbsj" value="${nowTime}"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：评奖评优 - 荣誉称号申请 - 填写申请表
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
							<b>填写申请表</b>
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right" style="width: 10%">
						<font color="red">*</font>学号：
					</td>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="left">
						<logic:notEmpty name="rs" scope="request">
							<html:text  property="xh" styleId="xh"
								onblur="dctStuXh()" name="rs"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<html:hidden name='rs' property="save_xh" value="${rs.xh}" />
						</logic:notEmpty>
						<logic:empty name="rs" scope="request">
							<html:text  property="xh" styleId="xh" 
								onblur="dctStuXh()" 
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
						</logic:empty >
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="left">
						<logic:notEmpty name="rs"  scope="request">
							<html:text name='rs' property="xh" readonly="true"/>
							<html:hidden name='rs' property="save_xh" value="${rs.xh}" />
						</logic:notEmpty>
						</td>
					</logic:equal>

					<td align="right" style="width: 10%">
						学年：
					</td>
					<td align="left" style="width: 40%">
						<bean:write name="xn" />	
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
						<logic:equal name="xq" value="01">
							春
						</logic:equal>
						<logic:equal name="xq" value="02">
							秋
						</logic:equal>
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
						担任职务：
					</td>
					<td align="left">
						<html:text property="save_xrzw"/>
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
						学年平均成绩
					</td>
					<td align="left">
						<html:text property="save_xnpjcj" onkeyup="value=value.replace(/[^\d]/g,'')"/>
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
						任现职时间：
					</td>
					<td align="left">
					 <html:text property="save_rxzsj" styleId="rxzsj" 
					onclick="return showCalendar('rxzsj','y-mm-dd');" 
					onblur="dateFormatChg(this)" readonly="true" />

					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
					
					</td>
					<td align="left">
					
					</td>
					<td align="right"><font color=red>*</font>
						荣誉称号：
					</td>
					<td align="left">
					<html:select property="save_rychdm" styleId="rychdm">
							<option value=""></option>
							<html:options collection="rychList" property="dm"
								labelProperty="mc" />
					</html:select>
					</td>
				</tr>
				
				
				
				
				<tr style="height:22px">
					<td align="right">
						获奖时间及名称：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" property="save_hjsjmc" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						主要事迹：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" property="save_zysj" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						备注：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" property="save_bz" onblur="chLeng(this,400)"/>
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn"  width="100%">	
				<logic:equal name="writeAbled" value="yes">
					<button type="button" class="button2" id="buttonSave" onclick="rychSqSave();" style="width:80px">
						保  存 
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:equal>
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
			<logic:equal name="isExist" value="no">
				<script>
			        alert("该荣誉称号已申请,且已通过相关部门审核\n或正在审核中,不能再次申请！");			    
			        </script>
			</logic:equal>
			<logic:equal name="pass" value="no">
				<script>
			        alert("该生不满足该项荣誉称号申请条件！");			    
			        </script>
			</logic:equal>
		</html:form>
	</body>


</html>

