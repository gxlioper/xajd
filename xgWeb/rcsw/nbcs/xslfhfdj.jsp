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
	<script type='text/javascript' src='/xgxt/dwr/interface/xsfwzdzx.js'></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script>
		function save(){
			if(filedNotNull('hfr','-')){
				refreshForm('xsfwzdzx.do?method=saveXslfhf');
			}else {
				alert ('请将带\*号的项目填写完整！');
				return false;
			}
		}
	</script>
	
	<base target="_self">
	<body onload="">
		<html:form action="/xsfwzdzx.do">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：日常事务 - 学生指导服务中心 - 受理、回访登记 - 回访登记
				</div>
			</div>
				<table width="100%" class="tbstyle">
					<thead align="center">
						<tr>
							<td align="center" colspan="4">
								回访登记
							</td>
						</tr>
					</thead>
					
					<tr>
						<td align="right">
							学号：
						</td>
						<td>							
							${rs.xh}
							<html:hidden property="xh" name="rs"/>
						</td>
						<td>
							<div align="right">
								受理人部门：
							</div>
						</td>
						<td>
							${rs.slbmmc}
						</td>
					</tr>
					<tr>
						<td align="right">
							姓名：
						</td>
						<td>							
							${rs.xm}
						</td>	
						<td align="right">
							受理人：
						</td>
						<td>					
							${rs.slrxm}
						</td>						
					</tr>	
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td>							
							${rs.xymc}
						</td>	
						<td align="right">
							来访方式：
						</td>
						<td>							
							${rs.lffs}
						</td>						
					</tr>
					<tr>
						<td align="right">
							班级：
						</td>
						<td>							
							${rs.bjmc}
						</td>	
						<td align="right">
							来访日期：
						</td>
						<td>	
							${rs.lfrq}
							<html:hidden property="lfrq" name="rs"/>						
						</td>						
					</tr>
					<tr>
						<td align="right">
							年度：
						</td>
						<td>							
							${rs.nd}
						</td>	
						<td align="right">
							联系电话：
						</td>
						<td>							
							${rs.lxdh}
						</td>						
					</tr>
					<tr>
						<td align="right">
							月份：
						</td>
						<td>							
							${rs.yf}
						</td>	
						<td align="right">
							来访事由：
						</td>
						<td>							
							${rs.lfsy}
						</td>						
					</tr>
					<tr>
						<td align="right">
							<font color="red">*</font>回访人：
						</td>
						<td>							
							<html:text property="hfr" name="rs" maxlength="15"></html:text>
						</td>	
						<td align="right">
							办结时间：
						</td>
						<td>							
							<html:select property="bjsj" name="rs" >
								<html:option value="及时">及时</html:option>
								<html:option value="不及时">不及时</html:option>
								<html:option value="未办">未办</html:option>
							</html:select>
						</td>						
					</tr>
					<tr>
						<td align="right">
							回访时间：
						</td>
						<td>							
							<html:text property="hfsj" name="rs" readonly="true" onclick="return showCalendar('hfsj','y-mm-dd');" ></html:text>
						</td>	
						<td align="right">
							服务质量：
						</td>
						<td>							
							<html:select property="fwzl" name="rs">
								<html:option value="满意">满意</html:option>
								<html:option value="基本满意">基本满意</html:option>
								<html:option value="不满意">不满意</html:option>
							</html:select>
						</td>						
					</tr>
					<tr>
						<td align="right">
							备注：
						</td>
						<td colspan="3">							
							<html:textarea property="bz" name="rs" onblur="chLeng(this,300)" cols="60"></html:textarea>
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
