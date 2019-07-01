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
    function chekhid(){
		var fdyval = document.getElementById("fdysh").value;
		var hidval = document.getElementById("btgtr");
		alert(fdyval);
		if(fdyval == "未通过X"){
			hidval.style.display = "inline";
		}else{
			hidval.style.display = "none";
		}
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

					<tr style="height:22px">
						<td colspan="4" align="center" bgcolor="DOEOEE">
							<b>第一部分</b>
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
						        <html:text name="rs" property="tjsj" readonly="true" style="width:140px" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" width="18%">
							学号：
						</td>
						<td align="left" width="32%">
							<html:text name="rs" property="xsxh" readonly="true" />
						</td>

						<td align="right" width="18%">
							姓名：
						</td>
						<td align="left" width="32%">
							<bean:write name="rs" property="name" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							性别代码：
						</td>
						<td align="left">
							<bean:write name="rs" property="xbdm" />
						<td align="right">
							身份证号：
						</td>
						<td align="left">
							<bean:write name="rs" property="id" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							学校代码：
						</td>
						<td align="left">
							<bean:write name="rs" property="xxdm" />
						</td>
						<td align="right">
							学校名称：
						</td>
						<td align="left">
							<bean:write name="rs" property="xxmc" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							专业代码：
						</td>
						<td align="left">
							<bean:write name="rs" property="zydm" />
						</td>
						<td align="right">
							专业名称：
						</td>
						<td align="left">
							<bean:write name="rs" property="zymc" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							学制代码：
						</td>
						<td align="left">
							<bean:write name="rs" property="xzdm" />
						</td>
						<td align="right">
							学历代码：
						</td>
						<td align="left">
							<bean:write name="rs" property="xldm" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							培养方式代码：
						</td>
						<td align="left">
							<bean:write name="rs" property="pyfsdm" />
						</td>
						<td align="right">
							生源地区代码：
						</td>
						<td align="left">
							<bean:write name="rs" property="sydqdm" />
						</td>
					</tr>

					<tr style="height:22px">
						<td colspan="4" align="center" bgcolor="DOEOEE">
							<b>第二部分</b>
						</td>
					</tr>


					<tr style="height:22px">
						<td align="right">
							毕业去向代码：
						</td>
						<td align="left">
							<bean:write name="rs" property="byqxdm" />
						</td>
						<td align="right">
							生源地区名称：
						</td>
						<td align="left">
							<bean:write name="rs" property="sydq" />
						</td>

					</tr>
					<tr style="height:22px">
						<td align="right">
							信息登记号：
						</td>
						<td align="left">
							<bean:write name="rs" property="xxdjh" />
						</td>
						<td align="right">
							组织机构代码：
						</td>
						<td align="left">
							<bean:write name="rs" property="zzjgdm" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							单位名称：
						</td>
						<td align="left">
							<bean:write name="rs" property="dwmc" />
						</td>
						<td align="right">
							政治面貌代码：
						</td>
						<td align="left">
							<bean:write name="rs" property="zzmmdm" />
						</td>

					</tr>
					<tr style="height:22px">
						<td align="right">
							单位地区名称：
						</td>
						<td align="left">
							<bean:write name="rs" property="dwdq" />
						</td>
						<td align="right">
							主管部门名称：
						</td>
						<td align="left">
							<bean:write name="rs" property="zgbm" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							单位地区代码：
						</td>
						<td align="left">
							<bean:write name="rs" property="dwdqdm" />
						</td>
						<td align="right">
							主管部门代码：
						</td>
						<td align="left">
							<bean:write name="rs" property="zgbmdm" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							未就业标志：
						</td>
						<td align="left">
							<bean:write name="rs" property="wjybz" />
						</td>
						<td align="right">
							自定义1：
						</td>
						<td align="left">
							<bean:write name="rs" property="bz1" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							未就业原因：
						</td>
						<td align="left">
							<bean:write name="rs" property="wjyyy" />
						</td>
						<td align="right">
							自定义2：
						</td>
						<td align="left">
							<bean:write name="rs" property="bz2" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							联系地址：
						</td>
						<td align="left">
							<bean:write name="rs" property="lxdz" />
						</td>
						<td align="right">
							自定义3：
						</td>
						<td align="left">
							<bean:write name="rs" property="bz3" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							邮编：
						</td>
						<td align="left">
							<bean:write name="rs" property="yb" />
						</td>
						<td align="right">
							自定义4：
						</td>
						<td align="left">
							<bean:write name="rs" property="bz4" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							电话：
						</td>
						<td align="left">
							<bean:write name="rs" property="dh" />
						</td>
						<td align="right">
							自定义5：
						</td>
						<td align="left">
							<bean:write name="rs" property="bz5" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							
						</td>
						<td align="left">
							
						</td>
						<td align="right">
							居住证或蓝表标志位：
						</td>
						<td align="left">
							<bean:write name="rs" property="jzzhlbbzwdm" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							生源地主管单位：
						</td>
						<td align="left">
							<bean:write name="rs" property="sydzgbm" />
						</td>
						<td align="right">
							单位性质代码：
						</td>
						<td align="left">
							<bean:write name="rs" property="dwxzdm" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							蓝表批准文号：
						</td>
						<td align="left">
							<bean:write name="rs" property="blueno" />
						</td>
						<td align="right">
							备注：
						</td>
						<td align="left">
							<bean:write name="rs" property="memo" />
						</td>
					</tr>

					<tr style="height:22px">
						<td colspan="4" align="center" bgcolor="DOEOEE">
							<b>第三部分</b>
						</td>
					</tr>


					<tr style="height:22px">
						<td align="right">
							单位性质代码2：
						</td>
						<td align="left">
							<bean:write name="rs" property="dwxzdm2" />
						</td>
						<td align="right">
							档案接收地：
						</td>
						<td align="left">
							<bean:write name="rs" property="dajsd" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							单位地址：
						</td>
						<td align="left">
							<bean:write name="rs" property="dwdz" />
						</td>
						<td align="right">
							档案接收地地址：
						</td>
						<td align="left">
							<bean:write name="rs" property="dajsddz" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							单位电话：
						</td>
						<td align="left">
							<bean:write name="rs" property="dwdh" />
						</td>
						<td align="right">
							档案接收地邮编：
						</td>
						<td align="left">
							<bean:write name="rs" property="dajsdyb" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							单位邮编：
						</td>
						<td align="left">
							<bean:write name="rs" property="dwyb" />
						</td>
						<td align="right">
							地区流向：
						</td>
						<td align="left">
							<bean:write name="rs" property="dqlx" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							违约金：
						</td>
						<td align="left">
							<bean:write name="rs" property="wyj" />
						</td>
						<td align="right">
							行业分类：
						</td>
						<td align="left">
							<bean:write name="rs" property="hyfl" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							第一年月平均工资：
						</td>
						<td align="left">
							<bean:write name="rs" property="dynypjgz" />
						</td>
						<td align="right">
							专业对口：
						</td>
						<td align="left">
							<bean:write name="rs" property="zydk" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							
						<logic:equal value="12061" name="xxdm" scope="session">
							<bean:message key="lable.xsgzyxpzxy" />审核人：
						</logic:equal>
						<logic:notEqual value="12061" name="xxdm" scope="session">
						<logic:equal value="11122" name="xxdm" scope="session">
							<bean:message key="lable.xsgzyxpzxy" />审核人：
						</logic:equal>
							<logic:notEqual value="11122" name="xxdm" scope="session">
								辅导员审核：
							</logic:notEqual>
						</logic:notEqual>
						</td>
						<td align="left" >
							<html:select name="rs" property="fdysh" onchange="">
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
						<logic:equal value="12061" name="xxdm" scope="session">
						<bean:message key="lable.xsgzyxpzxy" />审核人：
						</logic:equal>
						<logic:notEqual value="12061" name="xxdm" scope="session">
							辅导员审核人：
							</logic:notEqual>
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
						<logic:equal value="12061" name="xxdm" scope="session">
							<bean:message key="lable.xsgzyxpzxy" />审核时间：
						</logic:equal>
						<logic:notEqual value="12061" name="xxdm" scope="session">
							辅导员审核时间：
							</logic:notEqual>
							
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
					<tr id="btgtr">
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
