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
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
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
	</script>
	<body>
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
					value="xm-xb-xymc-nj-zymc-bjmc-kh" />
				<input type="hidden" id="url" name="url" value="/post_stu_apply.do" />
				<input type="hidden" id="dyxmdm" name="dyxmdm" value="002" />
				<input type="hidden" id="tabName" name="tabName" value="view_xsgwxx" />
				<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}" />
				<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td height="18" colspan="4" align="center">
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
								<input type="hidden" name="xh" id="xh"
									value="<bean:write name='rs' property="xh" />">
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
								<input type="hidden" name="xmdmmodi" id="xmdmmodi"
									value="<bean:write name='rs' property='xmdm'/>">
								<html:select name="rs" property="xmdm" styleId="xmdm"
									style="width:150px" disabled="true" onchange="">
									<html:option value=""></html:option>
									<html:options collection="gwList" property="gwdmgwsbsj"
										labelProperty="gwdm" />
								</html:select>
							</td>
						</logic:equal>
						<logic:notEqual value="modi" name="doType">
							<td align="left">
								<html:select name="rs" property="xmdm" styleId="xmdm"
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
					<tr style="height:22px">
						<td align="right">
							银行卡号：
						</td>
						<td align="left" colspan="3">
							<html:text property="kh" name="rs" styleId="kh" style="width:100%"/>
						</td>						
					</tr>	
					<tr style="height:22px">
						<td align="right">
							特长：
						</td>
						<td align="left" colspan="3">
							<html:text property="yhtc" name="rs" styleId="yhtc" style="width:100%"/>
						</td>						
					</tr>	
					<tr align="left" style="height:22px">
						<td align="right">
							家庭情况：
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='jtjjknqk' styleId="jtjjknqk"
								style="width:99%" rows='5' />
						</td>
					</tr>			
					<tr align="left" style="height:22px">
						<td align="right">
							申请理由：
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='xssq' styleId="sqly"
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
					<logic:equal name="do" value="no" scope="request">
						<button type="button" class="button2" onclick="alert('ok');chkisDataExist('xh-xmdm');">
							提 交 申 请
						</button>
					</logic:equal>
					<logic:equal name="do" value="yes" scope="request">
						<button type="button" class="button2" onclick="chkisDataExist('xh-xmdm')">
							保 存 申 请
						</button>
					</logic:equal>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="print('qgzxZjjjzy.do?method=printZjjjzyGwsqb','xh-yhtc-jtjjknqk-xssq-kh','xmdm')">
						打 印 预 览
					</button>
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
			</logic:notEmpty>
		</html:form>
	</body>
</html>
