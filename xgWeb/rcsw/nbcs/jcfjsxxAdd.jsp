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
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/xsxfxxgl.js'></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script>
		function save(){
			if(filedNotNull('xh-nd','-')){
				xsxfxxgl.checkJcjsxx({xh:val('xh'),nd:val('nd')},function(data){
					if(data == true){
						alert('您要添加的记录已经存在！');
						return false;
					}else{
						refreshForm('xfxx.do?method=saveJcfjsxx&doType=add');		
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
		<html:form action="/xfxx.do">
			<input type="hidden" name="url" id="url" value="/xfxx.do?method=jcfjsxxAdd">
			<input type="hidden" value="xh" id="getStuInfo" name="getStuInfo" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：日常事务 - 学费催缴 - 教材费结算信息维护 - 增加
				</div>
			</div>
				<table width="100%" class="tbstyle">
					<thead align="center">
						<tr>
							<td align="center" colspan="4">
								学费信息
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
					</tr>
					<tr>
						<td align="right">
							姓名：
						</td>
						<td>							
							${rs.xm}
						</td>				
					</tr>	
					<tr>
						<td align="right">
							性别：
						</td>
						<td>							
							${rs.xb}
						</td>					
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td>							
							${rs.xymc}
						</td>					
					</tr>
					<tr>
						<td align="right">
							专业：
						</td>
						<td>							
							${rs.zymc}
						</td>					
					</tr>
					<tr>
						<td align="right">
							班级：
						</td>
						<td>							
							${rs.bjmc}
						</td>					
					</tr>
					<tr>
						<td>
							<div align="right">
								<font color="red">*</font>年度：
							</div>
						</td>
						<td>
							<html:select property="nd">								
								<html:options collection="ndList" property="nd" labelProperty="nd"/>
							</html:select>
						</td>
					</tr>					
					<tr>
						
						<td align="right">
							结余金额：
						</td>
						<td>							
							<html:text property="jyje" maxlength="10" onkeyup="value=value.replace(/[^\d|.]/g,'') "></html:text>
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
					if(window.dialogArguments){
						window.dialogArguments.document.getElementById('search_go').click();
					}		
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
