
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
	<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/Function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	
	<script language="javascript">	
     function rychSqSave(){
     	showTips("保存中，请稍后...");
        refreshForm("/xgxt/sjxyDektqh.do?method=sqDektqh&doType=save");
     }
    function sqPrint(){
        window.open('sjxyDektqh.do?method=printSqDektqh&pkValue=${pkValue}');
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
		   <html:form action="/sjxyDektqh" method="post">
		   	<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xh-xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url" value="/sjxyDektqh.do?method=sqDektqh" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" name="pkValue" id="pkValue" value="${pk}"/>
			<input type="hidden" name="save_sqsj" id="save_sqsj" value="${nowTime}"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：学生会 - 社团管理 - 第二课堂活动企划申请
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
							<b>第二课堂活动企划申请</b>
						</td>
					</tr>
				</thead>
				<tr>
				<td align="right" style="width: 15%">
						<font color="red">*</font>活动负责人：
					</td>
					<td align="left">
						<logic:notEmpty name="rs">
							<bean:write name="rs" property="hdfzr" />	  
							<html:hidden name="rs" property="save_hdfzr" value="${rs.hdfzr}"/>
						</logic:notEmpty>
						<logic:empty name="rs">
						<logic:equal name="userType" value="xy">
							<html:text property="save_hdfzr" onblur="chLeng(this,25)"/>
						</logic:equal>
						</logic:empty>
					</td>
					<td align="right">
						宿舍号：
					</td>
					<td align="left">
						<html:text property="save_ssh"/>
					</td>
				</tr>
				<tr style="height:22px">
					
					<td align="right" style="width: 10%">
						举办部门：
					</td>
					<td align="left">
						<logic:notEmpty name="rs">
							<bean:write name="rs" property="jbbm" />	
							<html:hidden name="rs" property="save_jbbm" value="${rs.jbbm}"  />
						</logic:notEmpty>
						<logic:empty name="rs">
						<logic:equal name="userType" value="xy">
						<bean:write name="jbbm"  />
						<html:hidden property="save_jbbm" value="${jbbm}"/>
						</logic:equal>
						</logic:empty>
					</td>
					<td align="right">
						活动名称：
					</td>
					<td align="left">
						<html:text property="save_hdmc" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						指导老师：
					</td>
					
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="zdls" />
						<html:hidden property="save_zdls" value="${rs.zdls}"/>
					</logic:notEmpty>
					<logic:empty name="rs">
					<logic:equal name="userType" value="xy">
						<html:text property="save_zdls" onblur="chLeng(this,25)"/>
					</logic:equal>
					</logic:empty>
					</td>
					<td align="right">
						邀请嘉宾：
					</td>
					<td align="left">
						<html:text property="save_yqjb" onblur="chLeng(this,100)"/>
					</td>		
				</tr>
				<tr style="height:22px">
					<td align="right">
						拟举办时间：
					</td>
					<td align="left" style="width: 40%">
						 <html:text property="save_jbsj" styleId="jbsj" 
						onclick="return showCalendar('jbsj','y-mm-dd');" 
						onblur="dateFormatChg(this)" readonly="true" />
						
					</td>
					<td align="right">
						<font color="red">*</font>联系方式：
					</td>
					<td align="left">
						<html:text property="save_lxfs" onblur="chLeng(this,50)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						活动地点：
					</td>
					<td align="left">
					<html:text property="save_hddd" />
					</td>
					<td align="right">
						<font color="red">*</font>参加对象及人数：
					</td>
					<td align="left">
						<html:text property="save_cjdxrs"	onblur="chLeng(this,100)" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						活动的目的和意义：
						<br />
						<span class="style1">(限200字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" property="save_hdmdyy" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						活动可行性分析：
						<br />
						<span class="style1">(限200字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" property="save_hdkxfx" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						活动实施时间表：
						<br />
						<span class="style1">(限200字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" property="save_hdsssjb" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						活动需要经费：
						<br />
						<span class="style1">(限200字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%"  property="save_hdxyjf" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						活动经费预算清单：
						<br />
						<span class="style1">(限200字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" property="save_hdjfys" onblur="chLeng(this,400)"/>
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn"  width="100%">	
				<logic:equal name="writeAbled" value="yes">
					<logic:notEqual name="write" value="no">
					<button class="button2" id="buttonSave" onclick="rychSqSave();" style="width:80px">
						保  存 
					</button>
					</logic:notEqual>
				</logic:equal>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="sqPrint()" style="width:80px">
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
			<logic:equal name="write" value="no">
				<script>
			        alert("对不起，您目前还没有申请该活动的权限！");			    
			     </script>
			</logic:equal>
		</html:form>
	</body>


</html>

