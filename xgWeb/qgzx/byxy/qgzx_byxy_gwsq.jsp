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
	<script type='text/javascript'src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<body>
		<!-- 井冈山大学 -->
		<logic:equal value="10419" name="xxdm">
			<logic:present name="tsxx">
				<input type="hidden" name="tsxx" id="tsxx" value="<bean:write name="tsxx" property="tsxx" />"/>
				<script>
					alert(document.getElementById("tsxx").value);
				</script>
			</logic:present>
		</logic:equal>
		
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
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="6" align="center">
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
							<td colspan="3" align="right">
								<font color="red">*</font>学号：
							</td>
							<td width="182" align="left">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onClick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td width="88" align="right">
								<font color="red">*</font>学号：
							</td>
							<td width="64" align="left">
								<input type="hidden" name="xh" id="xh"
									value="<bean:write name='rs' property="xh" />                ">
								<bean:write name='rs' property="xh" />
							</td>
						</logic:equal>
						<td align="right">
							<font color="red">*</font>岗位名称：
						</td>
						<logic:equal value="modi" name="doType">
							<td width="117" align="left">
								<input type="hidden" id="isModi" name="isModi"
									value="<bean:write name="dotype" scope="request"/>
                " />
								<input type="hidden" name="xmdmmodi" id="xmdmmodi"
									value="<bean:write name='rs' property='xmdm'/>">
								<html:select name="rs" property="xmdm" styleId="xmdm"
									style="width:150px" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="gwList" property="gwdmgwsbsj"
										labelProperty="gwdm" />
								</html:select>
							</td>
						</logic:equal>
						<logic:notEqual value="modi" name="doType">
							<td width="11" align="left">
								<html:select name="rs" property="xmdm" styleId="xmdm"
									style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="gwList" property="gwdmgwsbsj"
										labelProperty="gwdm" />
								</html:select>
							</td>
						</logic:notEqual>
					</tr>
					<tr style="height:22px">
						<td colspan="3" align="right">
							姓名：
						</td>
						<td align="left">
							<bean:write name='rs' property="xm" />
						</td>
						<td align="right">
							年度：
						</td>
						<td align="left">
							<html:text name="rs" property="nd" readonly="true"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td colspan="3" align="right">
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
						<td colspan="3" align="right">
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
						<td colspan="3" align="right">
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
						<td colspan="3" align="right">
							专业：
						</td>
						<td align="left">
							<bean:write name='rs' property="zymc" />
						</td>
						<td align="right">
							可参加勤工助学时间：
						</td>
						<td align="left">
							<html:text name='rs' property="kcjqgzxsj" styleId="kcjqgzxsj"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td colspan="3" align="right">
							班级：
						</td>
						<td align="left">
							<bean:write name='rs' property="bjmc" />
						</td>
						<td align="right">
							个人特长 ：
						</td>
						<td align="left">
							<html:text name='rs' property="yhtc" />
						</td>
					</tr>
					<tr>
						<td  colspan="6">
							<table class="tbstyle" width="100%">
							
					<tr align="left" style="height:22px">
						<td rowspan="5" align="right" width="18">
							家
							<br />
							庭
							<br />
							成
							<br />
							员
							<br />
							信
							<br />
							息
							<br />
						</td>
						<td align="center">
							家庭成员姓名
						</td>
						<td align="center">
							称谓
						</td>
						<td align="center">
							工作单位及职务
						</td>
						<td colspan="2" align="center">
							年收入
						</td>
					</tr>
					<tr align="center" style="height:22px">
						<td align="center">
							<html:text name="rs" property="jtcy1_xm" />
						</td>
						<td align="center">
							<html:text name="rs" property="jtcy1_cw"/>
						</td>
						<td align="center">
							<html:text name="rs" property="jtcy1_gzdwjzw" />
						</td>
						<td colspan="2" align="center" />
							<html:text name="rs" property="jtcy1_nsr"/>
						</td>
					</tr>
					<tr align="center" style="height:22px">
						<td align="center">
							<html:text name="rs" property="jtcy2_xm"/>
						</td>
						<td align="center">
							<html:text name="rs" property="jtcy2_cw"/>
						</td>
						<td align="center">
							<html:text name="rs" property="jtcy2_gzdwjzw"/>
						</td>
						<td colspan="2" align="center">
							<html:text name="rs" property="jtcy2_nsr"/>
						</td>
					</tr>
					<tr align="center" style="height:22px">
						<td align="center">
							<html:text name="rs" property="jtcy3_xm"/>
						</td>
						<td align="center">
							<html:text name="rs" property="jtcy3_cw"/>
						</td>
						<td align="center">
							<html:text name="rs" property="jtcy3_gzdwjzw"/>
						</td>
						<td colspan="2" align="center">
							<html:text name="rs" property="jtcy3_nsr"/>
						</td>
					</tr>
					<tr align="center" style="height:22px">
						<td align="center">
							<html:text name="rs" property="jtcy4_xm"/>
						</td>
						<td align="center">
							<html:text name="rs" property="jtcy4_cw"/>
						</td>
						<td align="center">
							<html:text name="rs" property="jtcy4_gzdwjzw"/>
						</td>
						<td colspan="2" align="center">
							<html:text name="rs" property="jtcy4_nsr"/>
						</td>
					</tr>
					</table>
					</td>
					</tr>
					<tr align="left" style="height:22px">
						<td colspan="3" align="right">
							家庭主要经济来源：
						</td>
						<td colspan="3">
							<html:textarea  name="rs" property="jtzyjjly" styleId="jtzyjjly"
								style="width:99%" rows='5'></html:textarea>
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<td colspan="3" align="right">
							申请理由：
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='xssq' styleId="xssq"
								style="width:99%" rows='5' />
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<td colspan="3" align="right">
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
						<button type="button" class="button2" onclick="commitApply('qgzxLogic.do?method=saveStationOfStu','xh-xmdm')">
							提 交 申 请
						</button>
					</logic:equal>
					<logic:equal name="do" value="yes" scope="request">
						<button type="button" class="button2" onclick="commitApply('qgzxLogic.do?method=saveStationOfStu','xh-xmdm')">
							保 存 申 请
						</button>
					</logic:equal>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="javascript: var xh = document.getElementById('xh').value;window.open('qgzxLogic.do?method=printStationOfStu&xh='+xh)">
						打 印 预 览
					</button>
				</div>
			<logic:notEmpty name="result">
				<logic:equal name="result" value="true">
					<script>
    alert("申请成功！");
    </script>
				</logic:equal>
				<logic:equal name="result" value="false">
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
	</body>
</html>
