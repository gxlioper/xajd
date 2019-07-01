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
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
	function cgxyupdate(){
		var rid = document.getElementById("pk").value;
		document.forms[0].action = "/xgxt/cgjyxys.do?act=saveupdate&rid="+rid;
		document.forms[0].submit();
    }
	</script>
	<body>
		
		<html:form action="/jyxxtjwh.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：就业管理 - 就业信息 - 就业信息 统计维护
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("您输入的学号无效!");
    </script>
				</logic:equal>
				<html:hidden name="rs" property="nd" />
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
					</thead>
					<tr style="height:22px">
							<td align="right" style="width=18%">
							<input id="pk" name="pk" value="${rs.rid}" type="hidden"/>
								<font color="red">*</font>学号：
							</td>
							<td align="left" style="width=32%">
								<html:text name='rs' property="xh" styleId="xsxh"
									style="width:210px"/>
								<button onclick="showTopWin('/xgxt/jyxyTurnInfo.do',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
							</td>
						<td align="right" style="width:20%">
							<font color="red">*</font>姓名：
						</td>
						<td align="left">
							<html:text name="rs" property="xm"  style="width: 210px"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							专业名称：
						</td>
						<td align="left">
							<html:text name="rs" property="zymc" 
								style="width:210px" />
						<td align="right">
							家庭地址：
						</td>
						<td align="left">
							<html:text name="rs" property="jtdz" 
								style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							中够协议书原因：
						</td>
						<td align="left">
							<html:select name="rs" property="zgxysyy" style="width:210px">
								<html:option value=""></html:option>
								<html:option value="协议解除">协议解除</html:option>
								<html:option value="协议遗失">协议遗失</html:option>
								<html:option value="信息错误">信息错误</html:option>
								<html:option value="新协议书编号">新协议书编号</html:option>
							</html:select>
						</td>
						<td align="right">
							新协议书编号：
						</td>
						<td align="left">
							<html:text name="rs" property="xxysbh" style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							原签约单位：
						</td>
						<td align="left">
							<html:text name="rs" property="yqydw" style="width:210px" />
						</td>
						<td align="right">
							单位性质：
						</td>
						<td align="left">
							<html:text name="rs" property="dwxz" style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							就业岗位：
						</td>
						<td align="left">
							<html:text name="rs" property="jygw" style="width:210px" />
						</td>
						<td align="right">
								协议解除原因：
						</td>
						<td align="left">
							<html:select name="rs" property="xyjcyy" style="width:210px" >
								<html:option value=""></html:option>
								<html:option value="档案材料不齐">档案材料不齐</html:option>
								<html:option value="档案材料错档">档案材料错档</html:option>
								<html:option value="档案信息错误">档案信息错误</html:option>
								<html:option value="用人单位信息错误">用人单位信息错误</html:option>
								<html:option value="用人单位主管部门信息错误">用人单位主管部门信息错误</html:option>
							</html:select>
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button class="button2" onclick="cgxyupdate()">
						提 交
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();">
						关闭
					</button>
					
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="saveupdate">
				<logic:equal name="saveupdate" value="ok">
					<script>
					    alert("提交成功！");
					    </script>
				</logic:equal>
				<logic:equal name="saveupdate" value="no">
					<script>
    				alert("提交失败！");
   				 </script>
				</logic:equal>
			</logic:notEmpty>
			<logic:notEmpty name="iszc">
				<logic:equal name="iszc" value="iszc">
					<script>
					    alert("该用户已经存在了，不要重复提交同一个学生");
					    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
