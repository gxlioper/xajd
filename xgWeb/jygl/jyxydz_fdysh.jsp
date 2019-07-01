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
	<script language="javascript">
    function shgo(){
    var fdysh= document.getElementById("fdysh").value;
    var xxsh= document.getElementById("xxsh").value;
    
    if(fdysh=="未审核"&&xxsh!="未审核"||fdysh=="未通过X"&&xxsh!="未审核"||fdysh=="已通过√"&&xxsh!="未审核"){
    alert("你无权进行该项操作！");
    return false;
    }
		 	document.forms[0].action = "/xgxt/jyglJyxyFdysh.do?act=shenhe&doType=shenghe";
		 	document.forms[0].submit();
    }
	</script>
	<body>
		<script language="javascript" src="js/function.js"></script>

		<fieldset>
			<legend>
				就业协议
			</legend>
			<html:form action="/data_search" method="post">
				<table width="100%" id="rsT" class="tbstyle">
					<tr style="height: 22px">
					<td colspan="4" align="center" bgcolor="DOEOEE">
						<b>就业协议</b>
					</td>
				</tr>
				<tr bgcolor="DOEOEE">
					<td align="left" colspan="4">
						学生类别:
						<html:text property="xslb" name="rs" readonly="true"
							style="width:45px" />
						&nbsp;&nbsp;毕业年度:
						<html:text property="bynd" name="rs" readonly="true"
							style="width:35px" />
						&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />:
						<html:text property="xymc" name="rs" readonly="true"
							style="width:120px" />
						&nbsp;&nbsp;提交时间:
						<html:text name="rs" property="tjsj" readonly="true"
							style="width:140px" />
					</td>
				</tr>
				<tr style="height: 22px">
					<td align="right" style="">
						学号：
					</td>
					<td align="left" style="">
						<bean:write name="rs" property="xsxh" />
						<html:hidden name="rs" property="xsxh" />
					<td align="right">
						协议书编号：
					</td>
					<td align="left">
							<bean:write name="rs" property="xysbh" />
					<td align="right">
				</tr>
				<tr style="height: 22px">
					<td align="right" style="">
						姓名：
					</td>
					<td align="left">
						<bean:write name="rs" property="name" />
					</td>
					<td align="right" style="">
						性别：
					</td>
					<td align="left">
						<bean:write name="rs" property="xb" />
				</tr>
				<tr style="height: 22px">
					<td align="right">
						身份证号：
					</td>
					<td align="left">
						<bean:write name="rs" property="id" />
					</td>
					<td align="right">
						QQ号：
					</td>
					<td align="left">
						<bean:write name="rs" property="qq" />
					</td>
				</tr>
				<tr style="height: 22px">
					<td align="right">
						专业名称：
					</td>
					<td align="left">
						<bean:write name="rs" property="zymc" />>
					</td>
					<td align="right">
						民族：
					</td>
					<td align="left">
						<bean:write name="rs" property="mz" />
					</td>
				</tr>
				<tr style="height: 22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						<bean:write name="rs" property="xymc" />
					</td>
					<td align="right">
						学历：
					</td>
					<td align="left">
						<bean:write name="rs" property="xldm" />
					</td>
				</tr>
				<tr style="height: 22px">
					<td align="right">
						政治面貌：
					</td>
					<td align="left">
						<bean:write name="rs" property="zzmm" />
					</td>
					<td align="right">
						学制：
					</td>
					<td align="left">
						<bean:write name="rs" property="xzdm" />
					</td>
				</tr>
				<tr style="height: 22px">

					<td align="right">
						生源地区代码：
					</td>
					<td align="left">
						<bean:write name="rs" property="sydqdm" />
						<bean:write name="rs" property="jgshi" />
					</td>
				</tr>
					<tr style="height: 22px">
						<td colspan="4" align="center">
							<b>学生就业信息</b>
						</td>
					</tr>
				<tr>
					<td width="20%" align="right">
						 培养方式：
					</td>
					<td align="left">
						<bean:write name="rs" property="pyfsdm" />
					</td>
					<td width="20%" align="right">
						 委培单位：
					</td>
					<td align="left">
						<bean:write name="rs" property="wpdw" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">
						招生名册：
					</td>
					<td align="left">
						<bean:write name="rs" property="zsmc" />
					</td>
					<td width="20%" align="right">
						自动处理：
					</td>
					<td align="left">
						<bean:write name="rs" property="zdcl" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">
						毕业去向：
					</td>
					<td align="left">
						<bean:write name="rs" property="byqxdm" />
					</td>
					<td width="20%" align="right">
						就业状况：
					</td>
					<td align="left">
						<bean:write name="rs" property="jyzk" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">
						 未就业标志：
					</td>
					<td align="left">
						<bean:write name="rs" property="wjybz" />
					</td>
					<td width="20%" align="right">
						为就业原因：
					</td>
					<td align="left">
						<bean:write name="rs" property="wjyyy" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">
						报到证备注：
					</td>
					<td align="left">
						<bean:write name="rs" property="bdzbz" />
					</td>
					<td width="20%" align="right">
						实际单位：
					</td>
					<td align="left">
						<bean:write name="rs" property="sjdw" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">
						<font color="red">*</font> 派遣单位ID：
					</td>
					<td align="left">
						<bean:write name="rs" property="pqdwid" />
					</td>
					<td width="20%" align="right">
						<font color="red">*</font> 派遣单位：
					</td>
					<td align="left">
						<bean:write name="rs" property="pqdw" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">
						<font color="red">*</font> 单位性质：
					</td>
					<td align="left">
						<bean:write name="rs" property="dwxz1" />
						&nbsp
						<bean:write name="rs" property="dwxz2" />

					</td>
					<td width="20%" align="right">
						<font color="red">*</font> 性质选择：
					</td>
					<td align="left">
							<bean:write name="rs" property="dwxzdm2" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">
						<font color="red">*</font> 主管部门：
					</td>
					<td align="left">
						<bean:write name="rs" property="zgbm1" />
						<bean:write name="rs" property="zgbm1" />
					</td>
					<td width="20%" align="right">
						<font color="red">*</font> 主管选择：
					</td>
					<td align="left">
						<bean:write name="rs" property="zgbmmc" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">
						<font color="red">*</font> 单位所在地：
					</td>
					<td align="left">
						<bean:write name="rs" property="dwszd1" />
						<bean:write name="rs" property="jgshi2" />
					</td>
					<td width="20%" align="right">
						<font color="red">*</font> 所在地：
					</td>
					<td align="left">
						<bean:write name="rs" property="dwszd2" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">
						<font color="red">*</font> 实际所在地：
					</td>
					<td align="left">
						<bean:write name="rs" property="dwszd3" />
						<bean:write name="rs" property="jgshi3" />
					</td>
					<td width="20%" align="right">
						<font color="red">*</font> 所在地：
					</td>
					<td align="left">
						<bean:write name="rs" property="dwdq" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">
						<font color="red">*</font> 档案地址：
					</td>
					<td align="left">
						<bean:write name="rs" property="dajsd" />
					</td>
					<td width="20%" align="right">
						<font color="red">*</font> 档案邮编：
					</td>
					<td align="left">
						<bean:write name="rs" property="dajsdyb" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">
						<font color="red">*</font> 户籍地址：
					</td>
					<td align="left" colspan="10">
						<bean:write name="rs" property="hkdz" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">
						<font color="red">*</font> 改派日期：
					</td>
					<td align="left">
						<bean:write name="rs" property="gprq" />
					</td>
					<td width="20%" align="right">
						<font color="red">*</font> 改派次数：
					</td>
					<td align="left">
						<bean:write name="rs" property="gpcs" />
					</td>
				</tr>
				<tr>
				 	<td width="20%" align="right">
						<font color="red">*</font> 改派原因：
					</td>
					<td align="left">
						<bean:write name="rs" property="gpyy" />
					</td>
					<td width="20%" align="right">
						<font color="red">*</font> 原单位名称：
					</td>
					<td align="left">
						<bean:write name="rs" property="ydwmc" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">
						<font color="red">*</font> 报道证编号：
					</td>
					<td align="left">
						<bean:write name="rs" property="bdzbh" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">
						<font color="red">*</font> 备注：
					</td>
					<td align="left">
						<bean:write name="rs" property="memo" />
					</td>
				</tr>

					<tr style="height:22px">
						<td align="right">
							辅导员审核：
						</td>
						<td align="left" >
							<html:select name="rs" property="fdysh">
								<html:option value="未审核">未审核</html:option>
								<html:option value="未通过X">未通过X</html:option>
								<html:option value="已通过√">已通过√</html:option>
							</html:select>
						</td>
						<td align="right">
							学校审核：
						</td>
						<td align="left">
							<html:text name="rs" property="xxsh" style="width=100%" readonly="true"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							辅导员审核人：
						</td>
						<td align="left">
							<html:text name="rs" property="fdyshren" style="width=100%" readonly="true"/>
						</td>
						<td align="right">
							学校审核人：
						</td>
						<td align="left">
							<html:text name="rs" property="xxshren" style="width=100%" readonly="true" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							辅导员审核时间：
						</td>
						<td align="left">
							<html:text name="rs" property="fdyshsj" style="width=100%" readonly="true"/>
						</td>
						<td align="right">
							学校审核时间：
						</td>
						<td align="left">
							<html:text name="rs" property="xxshsj" style="width=100%" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="center">
							不通过原因<br>及修改意见
						</td>
						<td colspan="3">
							<html:textarea name="rs" property="btgyy" rows="4"
								style="width:99%" />
						</td>
					</tr>
				</table>
				<table width="100%">
					<tr>
						<td>
							<div class="buttontool" align="center">
								<button class="button2" onclick="shgo()" style="width:80px">
									提 交
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button class="button2"
									onclick="Close();window.dialogArguments.document.getElementById('query_go').click();" type="reset"
									style="width:80px">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</table>
			</html:form>
		</fieldset>
		<logic:notEmpty name="shenhe">
			<logic:equal name="shenhe" value="ok">
				<script>
                     alert("操作成功！");
                     close();
                     window.dialogArguments.document.getElementById('query_go').click();
               </script>
			</logic:equal>
			<logic:equal name="shenhe" value="no">
				<script>
                    alert("操作失败！");
                </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
