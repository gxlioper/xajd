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
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript'
		src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script type="text/javascript">
	function getDw(){
		var pk = document.getElementById("xmdm").value;
		if(pk!=null && pk!=""){
		getOtherData.getDwmc(pk,function(data){
			document.getElementById("sqdw").value = data[0];
			document.getElementById("fzr").value = data[1];
			document.getElementById("gwlxdh").value = data[2];
		});
		}		
	}
	
	function chkisDataExist(obj){
		var value = obj.split("-");
		if($("stuPass")){
			if(document.getElementById("stuPass").value == "false"){
				alert("该学生没有通过<bean:message key="lable.xsgzyxpzxy" />推荐，暂时不能安排岗位！");
				return false;
			}
		}
		for(var i=0; i<value.length; i++){
			if(document.getElementById(value[i]).value==""){
				alert("请将带\*号的项目填写完整！");				
				return false;
			}
		}
		refreshForm('qgzxZgdzdx.do?method=saveGwfp');
	}
	</script>
	<body>
		<html:form action="/qgzxZgdzdx.co" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：勤工助学 - 申请 - 直接分配岗位
				</div>
			</div>
			<logic:equal value="student" name="userOnLine">
				<br />
				<br />
				<center>
					本页面只有学校用户才可以访问！
				</center>
			</logic:equal>
			<logic:equal value="xy" name="userType">
				<br />
				<br />
				<center>
					本页面只有学校用户才可以访问！
				</center>
			</logic:equal>

			<logic:notEqual value="xy" name="userType">
				<logic:notEqual value="stu" name="userType">
<%--				<input type="hidden" name="stuPass" id="stuPass" value="${stuPass}"/><!-- 判断学生是否通过<bean:message key="lable.xsgzyxpzxy" />的推荐 -->--%>
					<logic:equal value="true" name="fpFlag">
						<logic:empty name="rs">
							<br />
							<br />
							<p align="center">
								有错误发生！
							</p>
						</logic:empty>
						<logic:notEmpty name="rs">
							<input type="hidden" id="xxdm" value="${xxdm }"/>
							<input type="hidden" id="getStuInfo" name="getStuInfo"
								value="xm-xb-xymc-nj-zymc-bjmc" />
							<input type="hidden" id="url" name="url"
								value="/qgzx/zgdzdx/gwfp.jsp" />
							<input type="hidden" name="xh" id="xh"
								value="<bean:write name='rs' property="xh" />">
							<input type="hidden" name="mes" id="mes" value="${hmdMember}">
							<table width="100%" id="rsT" class="tbstyle">
								<thead>
									<tr style="height:22px">
										<td height="18" colspan="4" align="center">
											<b>填写申请表</b>
										</td>
									</tr>
								</thead>
								<tr style="height:22px">
									<logic:equal name="userOnLine" value="teacher" scope="session">
										<td align="right">
											<font color="red">*</font>学号：
										</td>
										<td align="left">
											<html:text name='rs' property="xh" styleId="xh"
												onkeypress="autoFillStuInfo(event.keyCode,this)" />
											<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
												class="btn_01" id="buttonFindStu">
												选择
											</button>
										</td>
									</logic:equal>
									<logic:equal name="userOnLine" value="student" scope="session">
										<td align="right">
											<font color="red">*</font>学号：
										</td>
										<td align="left">
											<bean:write name='rs' property="xh" />
										</td>
									</logic:equal>
									<td align="right">
										<font color="red">*</font>岗位名称：
									</td>
									<logic:equal value="modi" name="doType">
										<td align="left">
											<input type="hidden" id="isModi" name="isModi"
												value="<bean:write name="doType" scope="request"/>" />
											<input type="hidden" name="gwmc" id="gwmc"
												value="<bean:write name='rs' property='gwmc'/>">
											<html:select name="rs" property="gwdm" styleId="gwmc"
												style="width:150px" disabled="true" onchange="">
												<html:option value=""></html:option>
												<html:options collection="gwList" property="gwdmgwsbsj"
													labelProperty="gwdm" />
											</html:select>
										</td>
									</logic:equal>
									<logic:notEqual value="modi" name="doType">
										<td align="left">
											<html:select name="rs" property="gwmc" styleId="gwmc"
												style="width:150px" onchange="">
												<html:option value=""></html:option>
												<html:options collection="gwList" property="gwdmgwsbsj"
													labelProperty="gwdm" />
											</html:select>
										</td>
									</logic:notEqual>
								</tr>
								<tr style="height:22px">
									<td align="right">
										姓名：
									</td>
									<td align="left">
										<bean:write name='rs' property="xm" />
									</td>
									<td align="right">
										年度：
									</td>
									<td align="left">
										<html:text name="rs" property="nd" readonly="true" />
									</td>
								</tr>
								<tr style="height:22px">
									<td align="right">
										性别：
									</td>
									<td align="left">
										<bean:write name='rs' property="xb" />
									</td>
									<td align="right">
										学年：
									</td>
									<td align="left">
										<html:text name="rs" property="xn" readonly="true" />
									</td>
								</tr>
								<tr style="height:22px">
									<td align="right">
										年级：
									</td>
									<td align="left">
										<bean:write name='rs' property="nj" />
									</td>
									<td align="right">
										学期：
									</td>
									<td align="left">
										<html:text name="rs" property="xq" readonly="true" />
									</td>
								</tr>
								<tr style="height:22px">
									<td align="right">
										<bean:message key="lable.xsgzyxpzxy" />：
									</td>
									<td align="left">
										<bean:write name='rs' property="xymc" />
									</td>
									<td align="right">
										联系电话：
									</td>
									<td align="left">
										<html:text name='rs' property="lxdh" />
									</td>
								</tr>
								<tr style="height:22px">
									<td align="right">
										专业：
									</td>
									<td align="left">
										<bean:write name='rs' property="zymc" />
									</td>
									<td align="right">
										可参加勤工助学时间：
									</td>

									<td align="left">
										<html:text name='rs' property="kcjqgzxsj" />
									</td>
								</tr>

								<tr style="height:22px">
									<td align="right">
										班级：
									</td>
									<td align="left">
										<bean:write name='rs' property="bjmc" />
									</td>
									<td align="right">
										是否贫困生：
									</td>
									<td align="left">
										<bean:write name='rs' property="sfpks" />
										<input type="hidden" name="sfpks" id="sfpks" />
									</td>
								</tr>
								<tr align="left" style="height:22px">
									<td align="right">
										申请理由：
									</td>
									<td colspan="3">
										<html:textarea name='rs' property='sqly' styleId="sqly"
											style="width:99%" rows='5' />
									</td>
								</tr>
								<tr align="left" style="height:22px">
									<td align="right">
										备注：
									</td>
									<td colspan="3">
										<html:textarea name='rs' property='bz' styleId="bz"
											style="width:99%" rows='5' />
									</td>
								</tr>
							</table>
							<div class="buttontool" align="center">
								<button type="button" class="button2" onclick="chkisDataExist('xh-gwmc')">
									保 存 申 请
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" class="button2"
									onclick="expAppTab('rsT','勤工助学岗位分配表','')">
									打 印 预 览
								</button>
							</div>
<%--							<logic:present name="stuPass">--%>
<%--							<logic:equal value="false" name="stuPass">--%>
<%--								<script>--%>
<%--									alert("该学生没有通过<bean:message key="lable.xsgzyxpzxy" />推荐，暂时不能安排岗位！");--%>
<%--								</script>--%>
<%--							</logic:equal>--%>
<%--							</logic:present>--%>
						</logic:notEmpty>
					</logic:equal>
					<!--在申请时间外  -->
				</logic:notEqual>
				<!--非学生用户-->
			</logic:notEqual>
			<logic:equal value="false" name="fpFlag">
								<script>
									alert("现在不在学校直接分配岗位的时间内，无法直接分配！");
								</script>
							</logic:equal>
			<!--非<bean:message key="lable.xsgzyxpzxy" />用户-->
			<logic:notEmpty name="result">
				<logic:equal name="result" value="true">
					<script>
				    	alert("操作成功！");
				    </script>
				</logic:equal>
				<logic:equal name="result" value="false">
					<logic:present name="hmdMember">
						<script>
				    	alert("该学生已经被列人勤工助学黑名单，无法申请岗位！");
				    </script>
					</logic:present>
					<logic:notPresent name="hmdMember">
						<script>
				    	alert("操作失败！");
				    </script>
					</logic:notPresent>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
