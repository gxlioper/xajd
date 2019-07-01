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
		<base target="_self" />
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<%--	<script type='text/javascript' src='/xgxt/dwr/interface/getStuDyInfo.js'></script>--%>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/gzdxpjpy.js'></script>
	<script type='text/javascript'
		src='/xgxt/dwr/interface/getStuDtiaInfo.js'></script>
	<script type="text/javascript" src="pjpy/pjpyjs/pjpy.js"></script>
	<script type="text/javascript">
      function dataSave(){
         var userType=$("userType").value;
         var pkValue=$("pkValue").value;
         var flag=true;
         if(userType=='xy'){
            dwr.engine.setAsync(false);
            getSztzData.getInfoEx("view_xsrychb","xh||xn||rychdm",pkValue,"xxsh='通过'",function(str){
		         if(str){		         	
			        flag = false;
		         }
    	    });
    	    dwr.engine.setAsync(true);	
         }
         if(!flag){
            alert("该荣誉称号记录已通过审核，\n不能再操作！");
            return false;
         }
         refreshForm('/xgxt/gzdxPjpy.do?method=rychjgModi&doType=save')
      }
    </script>
	<body>
		<html:form action="/gzdxPjpy" method="post">
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act"/>" />
			<input type="hidden" id="pkValue" name="pkValue"
				value="<bean:write name="pkValue"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType"/>" />	
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：评奖评优 - 获奖信息维护 - 荣誉称号结果修改
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<thead>
					<tr align="center">
						<td height="22" colspan="4">
							荣誉称号结果
						</td>
					</tr>
				</thead>
				<tr>
					<td height="22" align="right">
						学号：
					</td>
					<td height="22" align="left">
						<html:text name="rs" property="xh" styleId="xh" readonly="true"
							onkeypress="autoFillStuInfo(event.keyCode,this)" />
					</td>
					<td height="22" align="right">
						学年：
					</td>
					<td height="22" align="left">
						<html:select name="rs" property="xn" styleId="xndm">
							<html:options collection="xnList" property="xn"
								labelProperty="xn" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						姓名：
					</td>
					<td height="22" align="left">
						${rs.xm }
					</td>
					<td height="22" align="right">
						性别：
					</td>
					<td height="22" align="left">
						${rs.xb }
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						年级：
					</td>
					<td height="22" align="left">
						${rs.nj }
					</td>
					<td height="22" align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td height="22" align="left">
						${rs.xymc }
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						专业：
					</td>
					<td height="22" align="left">
						${rs.zymc }
					</td>
					<td height="22" align="right">
						班级：
					</td>
					<td height="22" align="left">
						${rs.bjmc}
					</td>
				</tr>
				<tr>
					<td height="22" align="right">

					</td>
					<td height="22" align="left">

					</td>
					<td height="22" align="right">
						奖学金
					</td>
					<td height="22" align="left">
						<html:select name="rs" property="rychdm" styleId="rychdm">
							<html:option value=""></html:option>
							<html:options collection="dmList" property="dm"
								labelProperty="mc" />
						</html:select>
					</td>
				</tr>
			</table>
			<fieldset>
				<legend>
					<font color="blue">相关成绩信息</font>
				</legend>
				<table width="100%" class="tbstyle">
					<tr align="left">
						<td align="center" colspan="4">
							<table width="100%" border="1" class="tbstyle">
								<tr>
									<td bgcolor="#CCCCCC">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child2.style.display=(document.all.child2.style.display =='none')?'':'none';getStucjList();">
											<div align="center" class="style1 style3">
												<strong>${rs.xn }学年课程成绩信息</strong>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<div id="child2" style="display:none">
								<table width="100%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td align="center">
												学期
											</td>
											<td align="center">
												课程性质
											</td>
											<td align="center">
												课程
											</td>
											<td align="center">
												成绩
											</td>
											<td align="center">
												补考成绩
											</td>
											<td align="center">
												重修成绩
											</td>
										</tr>
									</thead>
									<!-- 这里是通过DWR进行调用的 -->
									<tbody width="100%" class="tbstyle" id="cjxx" align="center"></tbody>
								</table>
							</div>
						</td>
					</tr>
					<!-- 处分信息 -->
					<tr>
						<td align="right" colspan="4">
							<table width="100%" border="1" class="tbstyle">
								<tr>
									<td bgcolor="#CCCCCC">
										<div id="main4" style="color:blue;cursor:hand"
											onclick="document.all.child4.style.display=(document.all.child4.style.display =='none')?'':'none';getStucfList();">
											<div align="center" class="style1 style3">
												<strong>${rs.xn }学年违纪处分信息</strong>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<div id="child4" style="display:none">
								<table width="100%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td align="center">
												学期
											</td>
											<td align="center">
												处分类别
											</td>
											<td align="center">
												处分原因
											</td>
											<td align="center">
												处分时间
											</td>
											<td align="center">
												处分文号
											</td>
										</tr>
									</thead>
									<!-- 这里是通过DWR进行调用的 -->
									<tbody width="100%" class="tbstyle" id="cfxx" align="center"></tbody>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</fieldset>
			<br />
			<logic:notEqual value="view" name="act">
				<div class="buttontool" id="button" align="center">
					<button class="button2" onclick="dataSave()" style="width:80px"
						id="buttonSave">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
			</logic:notEqual>
		</html:form>
		<logic:equal value="true" name="done">
			<script type="text/javascript">
				alert("操作成功！");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
		<logic:equal value="false" name="done">
			<script type="text/javascript">
				alert("操作失败!");
			</script>
		</logic:equal>
	</body>

</html>
