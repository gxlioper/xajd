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
	</head>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<body>
		<logic:equal value="yes" name="IsKns">
		<html:form action="/data_search" method="post">
			<div class="title">
			   <logic:equal name="do" value="no">
				<div class="title_img" id="title_m">
					当前位置：勤工助学 - 岗位申请 - 填写申请表
				</div>
				</logic:equal>
				<logic:equal name="do" value="yes">
				<div class="title_img" id="title_m">
					当前位置：勤工助学 - 岗位申请 - 修改申请表
				</div>
				</logic:equal>
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
				<logic:equal name="sqsjFlag" value="1">
					<script>
    				alert("不在设定时间范围内,暂不开放申请!");
    				location.href="about:blank";
    				</script>
				</logic:equal>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/post_stu_apply.do" />
				<input type="hidden" id="gwsbsj" name="gwsbsj" />
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
							<logic:equal name="do" value="no">
								<b>填写申请表</b>
							</logic:equal>
							<logic:equal name="do" value="yes">
							   <b>修改申请表</b>
							</logic:equal>
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
						<td align="left">
							<html:select name="rs" property="xmdm" styleId="xmdm" style="width:150px"
								>
								<html:option value=""></html:option>
								<html:options collection="gwList" property="gwdmgwsbsj"
									labelProperty="gwdm" />
							</html:select>
						</td>
					</tr>
					
					<tr style="height:22px">
						<td align="right">
							姓名：
						</td>
						<td align="left">
							<bean:write name='rs' property="xm" />
						</td>
						<td align="right">
							年度
						</td>
						<td align="left">
							<html:text name="rs" property="nd" readonly="true"></html:text>
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
							<html:text name="rs" property="xn" readonly="true"></html:text>
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
							<html:text name="rs" property="xq" readonly="true"></html:text>
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
					<logic:notEqual value="11417" name="xxdm">
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
						    <logic:present name="showshgc">
						    	<html:select name="rs" property="xskysj" styleId="xskysj" style="width:150px" onchange="selIndex(this,'kcjqgzxsj')">
								<html:option value=""></html:option>
								<html:options collection="kysjList" property="kcjsjdm"
									labelProperty="kcjsjmc" />
							</html:select>
						    </logic:present>
						    <logic:notPresent name="showshgc">
								<html:text name='rs' property="kcjqgzxsj" />
							</logic:notPresent>
						</td>
					</tr>
					</logic:notEqual>
					<logic:equal value="11417" name="xxdm">
						<tr style="height:22px">
						<td align="right">
							专业：
						</td>
						<td align="left" colspan="3">
							<bean:write name='rs' property="zymc" />
						</td>						
					</tr>
					</logic:equal>
					<tr style="height:22px">
						<td align="right">
							班级：
						</td>
						<td align="left">
							<bean:write name='rs' property="bjmc" />
						</td>
						<td align="right">
						</td>
						<td align="left">
						    <bean:write name='rs' property="sfpks"/>
						</td>
					</tr>
					<logic:present name="showshgc">
					<tr>
						<td align="right">
							卡号：
						</td>
						<td>
							<html:text name='rs' property="kh" />
						</td>
						<td align="right">
							学生空余时间：
						</td>
						<td>
							<html:text name="rs" property="kcjqgzxsj" styleId="kcjqgzxsj" readonly="true"/>
						</td>
					</tr>
					</logic:present>
					<logic:present name="showshgc">
					<tr>
						<td align="right">
							政治面貌：
						</td>
						<td>
							<bean:write name='rs' property="zzmm"/>
						</td>
						<td align="right">
						</td>
						<td>
						</td>
					</tr>
					<tr>
						<td align="right">
							工作经历：
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='gzjl' styleId="gzjl"
								style="width:99%" rows='5' />
						</td>
					</tr>
					</logic:present>
					<tr align="left" style="height:22px">
						<td align="right">
							申请理由：
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='xssq' styleId="xssq"
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
					<logic:equal value="浙江大学" name="xxmc">
					<logic:present name="kxList">
					<tr align="left" style="height:22px">
						<td colspan="4">
							<table border="0" cellpadding="0" cellspacing="0" align="center">
								<tr>
									<td align="center">时间</td>
									<td>星期一</td>
									<td>星期二</td>
									<td>星期三</td>
									<td>星期四</td>
									<td>星期五</td>
									<td>星期六</td>
									<td>星期日</td>
								</tr>								
								<logic:iterate id="kxsj" name="kxList">
								<tr>
									<td>${kxsj.sj}</td>
									<td align="center">${kxsj.mon}</td>
									<td align="center">${kxsj.tue}</td>
									<td align="center">${kxsj.wed}</td>
									<td align="center">${kxsj.thu}</td>
									<td align="center">${kxsj.fri}</td>
									<td align="center">${kxsj.sat}</td>
									<td align="center">${kxsj.sun}</td>
								</tr>
								</logic:iterate>								
							</table>
						</td>
					</tr>
					</logic:present>
					<logic:present name="kxbz">
					<tr align="left" style="height:22px">
						<td colspan="4" align="center">
						请维护空闲时间
						</td>
					</tr>
					</logic:present>
					</logic:equal>
				</table>
				<div class="buttontool" align="center">
				<logic:equal name="do" value="no" scope="request">
					<button type="button" class="button2"
						onclick="dataDoSaveApply3('/xgxt/applySave.do','xmdm','gwsq')">
						提 交 申 请
					</button>
				</logic:equal>
				<logic:equal name="do" value="yes" scope="request">
					<button type="button" class="button2"
						onclick="dataDoSaveApply3('/xgxt/applySave.do','xmdm','gwsq')">
						保 存 申 请
					</button>
				</logic:equal>
					<logic:notPresent name="zdy">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="expAppTab('rsT','勤工助学岗位申请表','')">
						打 印 预 览
					</button>
					</logic:notPresent>
					<logic:present name="zdy">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="printReport('qgzx_bb_gwsqb.do')">
						打 印 预 览
					</button>
					</logic:present>
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("申请成功！");
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("申请失败！");
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="nopks">
					<script>
    alert("申请失败！必须是贫困生才能申请");
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
		</logic:equal>
		<logic:equal value="no" name="IsKns">
			<div align="center" ><font color="red" size="5">只有困难生才可以申请</font></div>
		</logic:equal>
	</body>
</html>
