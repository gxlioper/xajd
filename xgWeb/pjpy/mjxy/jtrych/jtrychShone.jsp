
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
       function modiJtrych(){
     	refreshForm("/xgxt/mjxyJtrych.do?method=jtrychShone&doType=modi");
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
		   <html:form action="/mjxyJtrych" method="post">
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
			<input type="hidden" name="save_sqrq" id="save_sqrq" value="${rs.sqrq }"/>
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
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
						<font color="red">*</font>院系：
					</td>
					<td align="left">
						${rs.zymc }
					</td>
					<td align="right" style="width: 10%">
						年级：
					</td>
					<td align="left" style="width: 40%">
						<bean:write name="xn" />	
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						专业：
					</td>
					<td align="left">
						${rs.zymc}
					</td>
					<td align="right">
						班级：
					</td>
					<td align="left">
						${rs.bjmc }
						<html:hidden property="save_bjdm" value="${rs.bjdm}"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						学年：
					</td>
					<td align="left">
						${xn }
						<html:hidden property="save_xn" value="${rs.xn}"/>
					</td>
					<td align="right">
						<font color="red"></font>班级人数：
					</td>
					<td align="left">
						${bjrs}人
					</td>		
				</tr>
				<tr style="height:22px">
					<td align="right">
						班长(团支书)：
					</td>
					<td align="left">
						${bjgb}
					</td>
					<td align="right">
						手机号码：
					</td>
					<td align="left">
					${rs.sjhm }
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						党员人数（比例）：
					</td>
					<td align="left">
						${dyrsbl}%
					</td>
					<td align="right"><font color=red>*</font>
						荣誉称号：
					</td>
					<td align="left">
						${rs.jtrychmc }
						<html:hidden property="save_jtrychdm" value="${rs.jtrychdm}"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						违纪处分人数（比例）：
					</td>
					<td align="left">
						${cfrsbl}%
					</td>
					<td align="right">
						申请日期：
					</td>
					<td align="left">
						${rs.sqrq}
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
						获奖情况：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs" property="hjqk" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						主要事迹：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" name="rs" style="width:98%" property="zysj" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						辅导员意见：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs" property="save_fdyshyj" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						院系意见：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs" property="save_xyshyj" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						备注：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs" property="bz" onblur="chLeng(this,400)"/>
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn"  width="100%">	
				<logic:equal name="writeAble" value="yes">
					<logic:equal name="write" value="yes">
						<button type="button" class="button2" id="buttonSave" onclick="modiJtrych()" style="width:80px">
						保  存 
						</button>
					</logic:equal>
				</logic:equal>			
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px" id="buttonClose">
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

