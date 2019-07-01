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
<html>
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="Copyright" content="正方软件 zfsoft">
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/xsxxgl.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script>
		function save(){
			var pkValue = val('xn') + val('xq') + val('xh');
			if(filedNotNull('xh-xn-xq','-')){
				//判断记录是否存在
				xsxxgl.checkExists('view_hzzy_cjbgdxxb','xn||xq||xh',pkValue,function(data){
					if(data == true){
						alert("您添加的记录已经存在！");
						return false;
					}else{
						refreshForm('xsxxgl.do?method=addXscjbgdxx');
					}
				});
			} else {
				alert ('请将带\*号的项目填写完整！');
				return false;
			}
		}
	</script>
	<base target="_self">
	<body>
		<html:form action="/xsxxgl.do">
			<input type="hidden" name="url" id="url" value="/xsxxgl.do?method=xscjbgdxxAdd">
			<input type="hidden" value="xh-xm-xb-nj-xymc-zymc-bjmc" id="getStuInfo" name="getStuInfo" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生信息 - 学生信息 - 成绩报告单信息维护 - 添加信息
				</div>
			</div>
				<table width="100%" class="tbstyle">
					<thead align="center">
						<tr>
							<td align="center" colspan="4">
								成绩报告单信息
							</td>
						</tr>
					</thead>

					<tr>
						<td align="right">
							<font color="red">*</font>学号：
						</td>
						<td>
							<html:text name="rs" property="xh" styleId="xh" onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />
							<button type="button" class="button2"
								onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								style="width:20px" id="buttonFindStu">
								选择
							</button>
						</td>
						<td>
							<div align="right">
								<font color="red">*</font>学年：
							</div>
						</td>
						<td>
							<html:select property="xn" styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							姓名：
						</td>
						<td>
							<bean:write name="rs" property="xm" />
						</td>
						<td align="right">
							<font color="red">*</font>学期：
						</td>
						<td>
							<html:select property="xq" styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							性别：
						</td>
						<td>
							<bean:write name="rs" property="xb" />
						</td>
						<td align="right" >
							应上课天数：
						</td>
						<td>
							<html:text property="yskts" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'')" ></html:text>
						</td>
					</tr>
					<tr>
						<td align="right" nowrap="nowrap">
							年级：
						</td>
						<td>
							<bean:write name="rs" property="nj" />
						</td>
						<td align="right" nowrap="nowrap">
							实上课天数：
						</td>
						<td>
							<html:text property="sskts" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'')" ></html:text>
						</td>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td>
							<bean:write name="rs" property="xymc" />
						</td>
						<td align="right">
							病假：
						</td>
						<td>
							<html:text property="bingjia" maxlength="4" onkeyup="value=value.replace(/[^\d|.]/g,'')" ></html:text>次
						</td>
					</tr>
					<tr>
						<td align="right">
							专业：
						</td>
						<td>
							<bean:write name="rs" property="zymc" />
						</td>
						<td align="right">
							事假：
						</td>
						<td>
							<html:text property="shijia" maxlength="4" onkeyup="value=value.replace(/[^\d|.]/g,'')" ></html:text>次
						</td>
					</tr>
					<tr>
						<td align="right">
							班级：
						</td>
						<td>
							<bean:write name="rs" property="bjmc" />
						</td>
						<td align="right">
							迟到(早退)：
						</td>
						<td>
							<html:text property="chidao" maxlength="4" onkeyup="value=value.replace(/[^\d|.]/g,'')" ></html:text>次
						</td>
					</tr>
					<tr>
						<td align="right">
							旷课：
						</td>
						<td>
							<html:text property="kuangke" maxlength="4" onkeyup="value=value.replace(/[^\d|.]/g,'')"></html:text>次
						</td>
						<td align="right">
							其它：
						</td>
						<td>
							<html:text property="qita" maxlength="4" onkeyup="value=value.replace(/[^\d|.]/g,'')" ></html:text>次
						</td>
					</tr>
					<tr>
						<td align="right">
							班主任：
						</td>
						<td>
							<html:text property="bzr" maxlength="15"></html:text>
						</td>
						<td align="right" nowrap="nowrap">
							班主任联系电话：
						</td>
						<td>
							<html:text property="bzrlxdh" maxlength="12" onkeyup="value=value.replace(/[^\d|.]/g,'')"></html:text>
						</td>
					</tr>
					<tr>
						<td align="right">
							综合素质评定：
						</td>
						<td colspan="3">
							<html:textarea property="zhszpd" styleId="zhszpd" cols="40" rows="4" onblur="chLeng(this,'300')"></html:textarea>
						</td>
					</tr>
				</table>
				<center>
					<div class="buttontool" id="btn">
						<button type="button" class="button2"
							onclick="save();return false;"
							style="width:80px">
							保 存
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="window.close();return false;"
							style="width:80px">
							关 闭
						</button>
					</div>
				</center>

			 <logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
					alert("操作成功！");
					Close();
					window.dialogArguments.document.getElementById('search_go').click();		
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
					alert("操作失败！");
					Close();
				</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
