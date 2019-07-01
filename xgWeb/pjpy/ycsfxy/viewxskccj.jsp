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


	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<style media='print'>
	.noPrin{
	display:none;}
</style>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script>
			function checkData(){
				var userType = document.getElementById('userType').value;
				var yj = '';	
				if("xy"==userType){	
					yj = document.getElementById('xyshyj').value;
				}else{
					yj = document.getElementById('xxshyj').value;
				}
				if(yj.length > 1000){
					alert('学校审核意见不能大于1000字！');
					return false;
				}
				refreshForm('/xgxt/pjpy_ycsf_viewxskccj.do');
			}
	</script>

</head>
<body>
	<html:form action="pjpyycsfwh.do" method="post">
		<input type="hidden" name="act" value="save" />
		<logic:present name="lb">
			<input type="hidden" name="lb" value="<bean:write name="lb"/>" />
			<input type="hidden" name="pk" value="<bean:write name="pk"/>" />
			<input type="hidden" name="userType"
				value="<bean:write name="userType"/>" />
		</logic:present>
		<input type="hidden" name="act" value="" />
		<div class="title">
			<div class="title_img" id="title_m">
				当前位置：评奖评优 - 信息维护 - 综合素质测评维护 - 查看学生成绩
			</div>
		</div>
		<fieldset>
			<table width="100%" class="tbstyle" id="theTable">
				<tr>
					<td align="right" width="20%">
						学号：
					</td>
					<td colspan="3" width="30%">
						<bean:write name="xsxxmap" property="xh" />
					</td>
					<td align="right" width="20%">
						姓名：
					</td>
					<td colspan="3" width="30%">
						<bean:write name="xsxxmap" property="xm" />
					</td>
				</tr>
				<tr>
					<td align="right">
						性别：
					</td>
					<td colspan="3">
						<bean:write name="xsxxmap" property="xb" />
					</td>
					<td align="right">
						年级：
					</td>
					<td colspan="3">
						<bean:write name="xsxxmap" property="nj" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />名称：
					</td>
					<td colspan="3">
						<bean:write name="xsxxmap" property="xymc" />
					</td>
					<td align="right">
						专业名称：
					</td>
					<td colspan="3">
						<bean:write name="xsxxmap" property="zymc" />
					</td>
				</tr>
				<logic:notEqual value="yes" name="ahzyjsxy">
					<tr>
						<td align="right">
							班级名称：
						</td>
						<td colspan="3">
							<bean:write name="xsxxmap" property="bjmc" />
						</td>
						<td align="right">
							平时考核成绩：
						</td>
						<td colspan="3">
							<bean:write name="xsxxmap" property="pjkhcj" />
						</td>
					</tr>
					<tr>
						<td align="right">
							阶段考核成绩：
						</td>
						<td colspan="3">
							<bean:write name="xsxxmap" property="jdkhcj" />
						</td>
						<td align="right">
							学业考核成绩：
						</td>
						<td colspan="3">
							<bean:write name="xsxxmap" property="xykhcj" />
						</td>
					</tr>
					<tr>
						<td align="right">
							综合测评成绩：
						</td>
						<td colspan="3">
							<bean:write name="xsxxmap" property="zhszcpzf" />
						</td>
						<td align="right">
							综合测评排名：
						</td>
						<td colspan="3">
							<bean:write name="xsxxmap" property="pm" />
						</td>
					</tr>
				</logic:notEqual>
				<logic:equal value="yes" name="ahzyjsxy">
					<tr>
						<td align="right">
							班级名称：
						</td>
						<td colspan="3">
							<bean:write name="xsxxmap" property="bjmc" />
						</td>
						<td align="right">
							总科平均分：
						</td>
						<td>
							<bean:write name="xsxxmap" property="pjf" />
						</td>
						<td align="right">
							排名：
						</td>
						<td>
							<bean:write name="xsxxmap" property="pm" />
						</td>
					</tr>
				</logic:equal>
				<logic:present name="shMap">
					<tr>
						<td align="right">
							<font color="red">奖项：</font>
						</td>
						<td colspan="3">
							<logic:equal value="jxj" name="lb">
								<html:select name="shMap" property="dm" styleId="dm">
									<html:options collection="jxjList" property="jxjdm"
										labelProperty="jxjmc" />
								</html:select>
							</logic:equal>
							<logic:equal value="rych" name="lb">
								<html:select name="shMap" property="dm" styleId="dm">
									<html:options collection="rychList" property="rychdm"
										labelProperty="rychmc" />
								</html:select>
							</logic:equal>
						</td>
						<logic:notPresent name="view">
							<logic:equal value="yes" name="ahzyjsxy">
								<logic:equal value="xy" name="userType">
									<td align="right">
										<font color="red"><bean:message key="lable.xsgzyxpzxy" />审核状态：</font>
									</td>
									<td colspan="3">
										<html:select name="shMap" property="xysh" styleId="xysh">
											<html:option value="未审核">未审核</html:option>
											<html:option value="通过">通过</html:option>
											<html:option value="不通过">不通过</html:option>
										</html:select>
									</td>
								</logic:equal>
							</logic:equal>
							<logic:notEqual value="xy" name="userType">
								<td align="right">
									<font color="red">学校审核状态：</font>
								</td>
								<td colspan="3">
									<html:select name="shMap" property="xxsh" styleId="xxsh">
										<html:option value="未审核">未审核</html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
									</html:select>
								</td>
							</logic:notEqual>
						</logic:notPresent>
						<logic:present name="view">
							<td align="right">
								<font color="red">学校审核状态：</font>
							</td>
							<td colspan="3">
								<html:select name="shMap" property="xxsh" styleId="xxsh">
									<html:option value="未审核">未审核</html:option>
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
								</html:select>
							</td>
						</logic:present>
					</tr>
					<logic:equal value="yes" name="ahzyjsxy">
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />审核意见：
							</td>
							<td colspan="7">
								<html:textarea name="shMap" property="xyshyj" cols="60" rows="6"></html:textarea>
							</td>
						</tr>
					</logic:equal>
					<logic:present name="view">
						<logic:equal value="xy" name="userType">
							<tr>
								<td align="right">
									学校审核意见：
								</td>
								<td colspan="7">
									<html:textarea name="shMap" property="xxshyj" cols="60"
										rows="6"></html:textarea>
								</td>
							</tr>
						</logic:equal>
					</logic:present>
					<logic:notEqual value="xy" name="userType">
						<tr>
							<td align="right">
								学校审核意见：
							</td>
							<td colspan="7">
								<html:textarea name="shMap" property="xxshyj" cols="60" rows="6"></html:textarea>
							</td>
						</tr>
					</logic:notEqual>
				</logic:present>
				<tr align="center">
					<td colspan="8">
						具体课程成绩如下
					</td>
				</tr>
				<logic:notEqual value="yes" name="ahzyjsxy">
					<tr align="center">
						<td>
							学年
						</td>
						<td>
							学期
						</td>
						<td>
							课程名称
						</td>
						<td>
							课程性质
						</td>
						<td>
							考试类型
						</td>
						<td>
							成绩
						</td>
						<td>
							学分
						</td>
						<td>
							补考成绩
						</td>
					</tr>
					<logic:present name="rs">
						<logic:iterate id="s" name="rs">
							<tr align="center">
								<logic:iterate id="v" name="s">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</logic:present>
				</logic:notEqual>
				<logic:equal value="yes" name="ahzyjsxy">
					<tr align="center">
						<td>
							学年
						</td>
						<td>
							学期
						</td>
						<td colspan="3">
							课程名称
						</td>
						<td>
							课程性质
						</td>
						<td>
							考试类型
						</td>
						<td>
							成绩
						</td>
					</tr>
					<logic:present name="rs">
						<logic:iterate id="s" name="rs">
							<tr align="center">
								<logic:iterate id="v" name="s" offset="0" length="2">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="2" length="1">
									<td colspan="3">
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="3" length="3">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</logic:present>
				</logic:equal>
				<logic:present name="shMap">
					<logic:notPresent name="view">
						<logic:notEqual value="stu" name="userType">
							<tr>
								<td align="center" colspan="8">
									<button class="button2" onclick="checkData();"
										style="width: 60px">
										保 存
									</button>
									&nbsp;&nbsp;
									<button class="button2" onclick="reset();" style="width: 60px">
										关 闭
									</button>
								</td>
							</tr>
						</logic:notEqual>
					</logic:notPresent>
				</logic:present>

			</table>
		</fieldset>
		<br>
		<logic:present name="psjd">
			<div align="center" class="noPrin">
				<input type='button' class='button2' value='页面设置'
					onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='打印预览'
					onclick="WebBrowser.ExecWB(7,1);">
				<input type='button' class='button2' value='直接打印'
					onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>
		</logic:present>
		<logic:equal value="yes" name="result">
			<script>
				alert('操作成功!');
				close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert('操作失败!');
			</script>
		</logic:equal>
	</html:form>
</body>
</html:html>
