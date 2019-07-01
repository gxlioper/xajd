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
	<script>
		function save(){
			var pkValue = val('xh');
			if(filedNotNull('xh-bdzlx-bdzkwdwmc-dajwdwmc','-')){
				refreshForm('bdzbl.do?method=saveBdzblsq');
			} else {
				alert ('请将带\*号的项目填写完整！');
				return false;
			}
		}
	</script>
	<base target="_self">
	<body>
		<html:form action="/bdzbl.do">
			<input type="hidden" name="url" id="url" value="/bdzbl.do?method=bdzblsq">
			<input type="hidden" value="xh-xm-xb-nj-xymc-zymc-bjmc" id="getStuInfo" name="getStuInfo" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：就业管理 - 报到证办理管理 - 报到证办理审核
				</div>
			</div>
				<table width="100%" class="tbstyle">
					<thead align="center">
						<tr>
							<td align="center" colspan="4">
								报到证办理申请信息
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
								年度：
							</div>
						</td>
						<td>
							${rs.nd}
						</td>
					</tr>
					<tr>
						<td align="right">
							姓名：
						</td>
						<td>
							${rs.xm}
						</td>
						<td>
							<div align="right">
								报到证类型：
							</div>
						</td>
						<td>
							${rs.bdzlx}
						</td>						
					</tr>
					<tr>
						<td align="right">
							性别：
						</td>
						<td>
							${rs.xb}
						</td>
						
						<td>
							<div align="right">
								报到证开至：
							</div>
						</td>
						<td>
							${rs.bdzkwdwmc}
						</td>						
					</tr>
					<tr>
						<td align="right" nowrap="nowrap">
							年级：
						</td>
						<td>
							${rs.nj}
						</td>
						
						<td align="right">
							档案寄至：
						</td>
						<td>
							${rs.dajwdwmc}
							${rs.dajwdwbm}
						</td>						
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td>
							${rs.xymc}
						</td>
						
						<td align="right" >
							本人联系地址：
						</td>
						<td> 
							${rs.lxdz}
						</td>					
					</tr>
					<tr>
						<td align="right">
							专业：
						</td>
						<td>
							${rs.zymc}
						</td>
						<td align="right" nowrap="nowrap">
							本人联系邮编：
						</td>
						<td>
							${rs.lxyb}
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
							本人长期联系方式：
						</td>
						<td>
							${rs.lxfs}
						</td>	
					</tr>
					<tr>		
						<td align="right">
							学制：
						</td>
						<td>
							${rs.xz}
						</td>				
						<td align="right">
							手机号码：
						</td>
						<td>
							${rs.sjhm}
						</td>						
					</tr>
					<tr>
					<logic:equal value="true" name="isFdy">
						<td align="right">
							审核结果：
						</td>
						<td colspan="3">
							<html:select property="fdysh" name="rs">
								<html:option value="通过">通过</html:option>
								<html:option value="不通过">不通过</html:option>
								<html:option value="未审核">未审核</html:option>
							</html:select>
						</td>
					</logic:equal>
					<logic:notEqual value="true" name="isFdy">
						<td align="right">
							审核结果：
						</td>
						<td colspan="3">
							<html:select property="xysh" name="rs">
								<html:option value="通过">通过</html:option>
								<html:option value="不通过">不通过</html:option>
								<html:option value="未审核">未审核</html:option>
							</html:select>
						</td>
					</logic:notEqual>
					</tr>
				</table>
				<center>
					<div class="buttontool" id="btn">
						<button class="button2"
							onclick="refreshForm('bdzbl.do?method=audiBdzblOne');return false;"
							style="width:80px">
							保 存
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="window.close();return false;"
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
