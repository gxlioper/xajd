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
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/CzxxJxjDao.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script type="text/javascript">
    	
    </script>
	<body >
		<html:form action="/czxxPjpyJxj" method="post">
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<div class="title"> 
				<div class="title_img" id="title_m">
					当前所在位置：评奖评优 - 审核 - 奖学金审核
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<thead>
					<tr align="center">
						<td height="22" colspan="4">
							单个审核
						</td>
					</tr>
				</thead>
				<tr>
					<td height="22" align="right" style="width:25%">
						学号：
					</td>
					<td height="22" align="left" style="width:25%">
					${rs.xh }
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
						姓名：
					</td>
					<td height="22" align="left">
						${rs.xm }
					</td>
					<td height="22" align="right">
						专业：
					</td>
					<td height="22" align="left">
						${rs.zymc }
					</td>
					
				</tr>
				<tr>
				
					<td height="22" align="right">
						年级：
					</td>
					<td height="22" align="left">
						${rs.nj }
					</td>
				<td height="22" align="right" style="width:25%">
						学年：
					</td>
					<td height="22" align="left" style="width:25%">
						${rs.xn }
					</td>	
				</tr>
				<tr>
					
					<td height="22" align="right">
						班级：
					</td>
					<td height="22" align="left">
						${rs.bjmc }
					</td>
					<td height="22" align="right">
						学期：
					</td>
					<td height="22" align="left">
						${rs.xqmc }
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						德育成绩：
					</td>
					<td height="22" align="left">
						${rs.dcj }
					</td>
					<td height="22" align="right">
						智育成绩：
					</td>
					<td height="22" align="left">
						${rs.zcj}
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						体育成绩：
					</td>
					<td height="22" align="left">
						${rs.tcj }
					</td>
					<td height="22" align="right">
						综测总成绩：
					</td>
					<td height="22" align="left">
						${rs.zxf}
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						德育成绩班级排名：
					</td>
					<td height="22" align="left">
						${rs.dpm }
					</td>
					<td height="22" align="right">
						智育成绩班级排名：
					</td>
					<td height="22" align="left">
						${rs.zpm}
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						体育成绩班级排名：
					</td>
					<td height="22" align="left">
						${rs.tpm }
					</td>
					<td height="22" align="right">
						综测总成绩班级排名：
					</td>
					<td height="22" align="left">
						${rs.zfpm}
					</td>
				</tr>
				
				<tr>
					<td height="22" align="right">
					奖学金名称：
					</td>
					<td height="22" align="left">
						${rs.jxjmc }
					</td>
					<td height="22" align="right">
						奖励金额：
					</td>
					<td height="22" align="left">
						${rs.jlje }
					</td>
				</tr>
				
				<tr>
				<td align="right" colspan="4">
					<table width="100%" border="1" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main4" style="color:blue;cursor:hand"
									onclick="document.all.child4.style.display=(document.all.child4.style.display =='none')?'':'none';">
									<div align="center" class="style1 style3">
										<strong>违纪处分信息</strong>
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
								<td align="center" width="80px">
									学年
								</td>
								<td align="center" width="80px">
									学期
								</td>
								<td align="center" width="110px">
									处分类别
								</td>
								<td align="center" width="110px">
									处分原因
								</td>
								<td align="center" width="80px">
									处分时间
								</td>
								<td align="center" width="110px">
									处分文号
								</td>
							</tr>
							</thead>
							<logic:empty name="cfList">
								<tr>
								<td align="center" colspan="6">
									暂无记录！
								</td>
								</tr>
							</logic:empty>
							<logic:notEmpty name="cfList">
								<logic:iterate id="s" name="cfList" >
									<tr onclick="rowOnClick(this);" style="cursor:hand;" >
										<logic:iterate id="v" name="s" >
											<td align="center">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
						</table>
					</div>
				</td>
			</tr>
			
			<tr>
				<td align="right" colspan="4">
					<table width="100%" border="1" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main5" style="color:blue;cursor:hand"
									onclick="document.all.child5.style.display=(document.all.child5.style.display =='none')?'':'none';">
									<div align="center" class="style1 style3">
										<strong>学生课程成绩信息</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div id="child5" style="display:none">
						<table width="100%" border="1" align="center" class="tbstyle">
						<thead>
							<tr>
								<td align="center" width="80px">
									学年
								</td>
								<td align="center" width="80px">
									学期
								</td>
								<td align="center" width="110px">
									课程名称
								</td>
								<td align="center" width="110px">
									成绩
								</td>
								<td align="center" width="80px">
									课程性质
								</td>
								<td align="center" width="110px">
									补考成绩
								</td>
								<td align="center" width="110px">
									重修成绩
								</td>
							</tr>
							</thead>
							<logic:empty name="cjList">
								<tr>
								<td align="center" colspan="7">
									暂无记录！
								</td>
								</tr>
							</logic:empty>
							<logic:notEmpty name="cjList">
								<logic:iterate id="s" name="cjList" >
									<tr onclick="rowOnClick(this);" style="cursor:hand;" >
										<logic:iterate id="v" name="s" >
											<td align="center">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
						</table>
					</div>
				</td>
			</tr>
				<tr>
					<td height="22" align="right">
						主要事迹及获奖情况：
					</td>
					<td height="22" align="left" colspan="3">
						<html:textarea name="rs" property="zysj" rows="5" styleId="zysj" rows="6" style="width:540px;overflow:auto" readonly="true">
						</html:textarea>
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						<font color="red">*</font>审核状态：
						<br/>
					</td>
					<td height="22" align="left" colspan="3">
						<html:select property="sh" styleId="sh" >
									<html:option value="未审核">未审核</html:option>
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
								</html:select>
					</td>
				</tr>
				
				<logic:equal value="true" name="fdyQx">
					<tr>
						<td height="22" align="right">
							审核意见：
							<br/>
						</td>
						<td height="22" align="left" colspan="3">
							<html:textarea property="yj" styleId="yj" rows="3" style="width:540px"></html:textarea>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							<bean:message key="lable.xsgzyxpzxy" />审核意见：
							<br/>
						</td>
						<td height="22" align="left" colspan="3">
							<html:textarea name="rs" property="xyshyj" styleId="xyshyj" rows="3" readonly="true" style="width:540px;overflow:auto"></html:textarea>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							学校审核意见：
							<br/>
						</td>
						<td height="22" align="left" colspan="3">
							<html:textarea name="rs" property="xxshyj" styleId="xxshyj" rows="3" readonly="true" style="width:540px;overflow:auto"></html:textarea>
						</td>
					</tr>
				</logic:equal>
				<logic:notEqual value="true" name="fdyQx">
					<logic:equal value="xy" name="userType">
						<tr>
						<td height="22" align="right">
							辅导员审核意见：
							<br/>
						</td>
						<td height="22" align="left" colspan="3">
							<html:textarea name="rs" property="fdyyj" styleId="fdyyj" rows="3" readonly="true" style="width:540px;overflow:auto"></html:textarea>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							审核意见：
							<br/>
						</td>
						<td height="22" align="left" colspan="3">
							<html:textarea property="yj" styleId="yj" rows="3" style="width:540px;"></html:textarea>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							学校审核意见：
							<br/>
						</td>
						<td height="22" align="left" colspan="3">
							<html:textarea name="rs" property="xxshyj" styleId="xxshyj" rows="3" readonly="true" style="width:540px;overflow:auto"></html:textarea>
						</td>
					</tr>
					</logic:equal>
					<logic:notEqual value="xy" name="userType">
					<tr>
						<td height="22" align="right">
							辅导员审核意见：
							<br/>
						</td>
						<td height="22" align="left" colspan="3">
							<html:textarea name="rs" property="fdyyj" styleId="fdyyj" rows="3" readonly="true" style="width:540px;overflow:auto"></html:textarea>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							<bean:message key="lable.xsgzyxpzxy" />审核意见：
							<br/>
						</td>
						<td height="22" align="left" colspan="3">
							<html:textarea name="rs" property="xyshyj" styleId="xyshyj" rows="3" readonly="true" style="width:540px;overflow:auto"></html:textarea>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							审核意见：
							<br/>
						</td>
						<td height="22" align="left" colspan="3">
							<html:textarea property="yj" styleId="yj" rows="3" style="width:540px;"></html:textarea>
						</td>
					</tr>
					</logic:notEqual>
				</logic:notEqual>
			</table>
			<br />
			<div class="buttontool" id="button" align="center">
			<logic:notEqual value="view" name="writable">
			<logic:notEqual value="提交" name="tjzt">
			<button type="button" class="button2" onclick="saveinfo('pjpy_czxx_jxjDggrsh.do?act=save','')" style="width:80px"
					id="btn_save">
					保 存
				</button>
				&nbsp;&nbsp;
				</logic:notEqual>
				</logic:notEqual>
				<logic:equal value="提交" name="tjzt">
					<div align="center">
						<font color="red">相关部门审核中,不能再进行操作!</font>
					</div>
				</logic:equal>
				<button type="button" class="button2" onclick="window.close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
		<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert("操作成功!");
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert("操作失败!" + $('message').value);
				</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
