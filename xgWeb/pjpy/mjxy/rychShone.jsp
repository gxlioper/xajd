
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
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	
	<script language="javascript">	 
       function shoneRych(){
     	refreshForm("/xgxt/mjxyRych.do?method=rychShone&doType=modi");
     	showTips("保存中，请等待...")
        if($("buttonSave")){
          $("buttonSave").disabled=true;
        }
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
		   <html:form action="/mjxyRych" method="post">
			<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj-jg-mzmc" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xh-xm-xb-xymc-nj-zymc-bjmc-jg-mzmc" />
			<input type="hidden" id="url" name="url" value="/nbty_rych.do?method=rychSq" />
			<input type="hidden" id="viewName" name="viewName" value="view_nbty_xsrychb" />
			<input type="hidden" id="tabName" name="tabName" value="nbty_xsrychb" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
			<input type="hidden" name="save_xq" id="save_xq" value="${rs.xq }"/>
			<input type="hidden" name="save_xn" id="save_xn" value="${rs.xn }"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置:${title }
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

			<table class="tbstyle" width="100%">
				<tr style="height:22px">
						<td colspan="4" align="center">
							<b>填写申请表</b>
						</td>
					</tr>
				<tr>
					<td align="right" width="16%">
						<font color="red">*</font>学号：
					</td>
					<td align="left" width="34%">
							<html:text name="rs" property="save_xh" readonly="true" styleId="xh" value="${rs.xh}" />
					</td>
					<td width="16%">
						<div align="right">
							姓名：
						</div>
					</td>
					<td width="34%">
						${rs.xm }
					</td>
				</tr>
				<tr>
					<td align="right">
						性别：
					</td>
					<td>
						${rs.xb }
					</td>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td>
						${rs.xymc }
					</td>
				</tr>
				<tr>
					<td align="right">
						专业：
					</td>
					<td>
						${rs.zymc }
					</td>
					<td align="right">
						班级：
					</td>
					<td>
						${rs.bjmc }
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>学年：
					</td>
					<td>
						${rs.xn}
					</td>
					<td align="right">
						荣誉称号名称：
					</td>
					<td>
						${rs.rychmc}
						<html:hidden property="save_rychdm" value="${rs.rychdm}" styleId="rychdm"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						出生年月：
					</td>
					<td>
						<bean:write name="rs" property="csrq"/>
					</td>
					<td align="right">
						民族：
					</td>
					<td>
						<bean:write name="rs" property="mzmc"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						政治面貌：
					</td>
					<td>
						<bean:write name="rs" property="zzmmmc"/>
					</td>
					<td align="right">
						专业人数：
					</td>
					<td>
						${zyrs}人
					</td>
				</tr>
				<tr>
					<td align="right">
						年级：
					</td>
					<td>
						${rs.nj}
					</td>
					<td align="right">
						手机号码：
					</td>
					<td>
						<bean:write name="rs" property="sjhm"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						工行卡号：
					</td>
					<td>
						<bean:write name="rs" property="yhkh" />
					</td>
					<td align="right">
						当学年任职情况：
					</td>
					<td>
						<html:text name="rs" property="drzw"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						综合测评成绩排名：
					</td>
					<td>
						<html:text name="rs" property="zhpfmc"/>
					</td>
					<td align="right">
						当学年补考门数：
					</td>
					<td>
						${bkms }门
					</td>
				</tr>
				<tr>
					<td align="right">
						当学年综考门数：
					</td>
					<td>
					   ${zkms}门
					</td>
					<td align="right">
						申请日期：
					</td>
					<td>
						${sqsj}
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						审核：
					</td>
					<td align="left">
					<logic:equal name="userType" value="xy">
						<html:select property="save_xysh">
							<html:option value="未审核">未审核</html:option>
							<html:option value="通过">通过</html:option>
							<html:option value="不通过">不通过</html:option>
						</html:select>
					</logic:equal>
					<logic:equal name="userType" value="admin">
						<html:select property="save_xxsh">
							<html:option value="未审核">未审核</html:option>
							<html:option value="通过">通过</html:option>
							<html:option value="不通过">不通过</html:option>
						</html:select>
					</logic:equal>
					<logic:equal name="userType" value="xx">
						<html:select property="save_xxsh">
							<html:option value="未审核">未审核</html:option>
							<html:option value="通过">通过</html:option>
							<html:option value="不通过">不通过</html:option>
						</html:select>
					</logic:equal>
					</td>
					<td align="right">
						
					</td>
					<td align="left">
						
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						个人简历：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs" property="brjl" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						获奖情况：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" name="rs" style="width:98%" property="jcqk" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						主要事迹：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs" property="zysj" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						辅导员意见：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs" property="save_fdyyj" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						院系意见：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs" property="save_xyyj" onblur="chLeng(this,400)"/>
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn"  width="100%">	
				<logic:equal name="writeAble" value="yes">
					<logic:equal name="write" value="yes">
						<button class="button2" id="buttonSave" onclick="shoneRych()" style="width:80px">
						保  存 
						</button>
					</logic:equal>
				</logic:equal>			
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="Close();return false;" style="width:80px" id="buttonClose">
							关 闭
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

