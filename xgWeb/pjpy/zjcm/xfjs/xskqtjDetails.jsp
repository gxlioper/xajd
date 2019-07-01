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
	<base target="_self" />
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	
	<script language="javascript" src="js/function.js"></script>
	<body>
		<html:form action="/pjpyxfjs" method="post">
			<div class="title">
				<div class="title_img">
					当前所在位置：评奖评优 - 学风建设 - 统计查询 - 学生考勤情况详细信息
				</div>
			</div>
			<table width="90%" class="tbstyle" align="center">
			<thead>
				<tr align="center">
					<td colspan="4">
						学生考勤情况
					</td>
				</tr>
			</thead>
			<logic:notEmpty name="rs">	
			<tr>
				<td colspan="4">
					<table class="tbstyle" align="center" width="100%" id="tTb">
						<tr>
							<td>
								<div class="mid_box">
									<table align="center" style="width:100%" id="rsT" class="tbstyle">
										<thead style="height: 23px">
											<tr>
												<td nowrap="nowrap">
													学年
												</td>
												<td nowrap="nowrap">
													学期
												</td>		
												<td nowrap="nowrap">
													学号
												</td>									
												<td nowrap="nowrap">
													姓名
												</td>
												<td nowrap="nowrap">
													<bean:message key="lable.xsgzyxpzxy" />
												</td>
												<td nowrap="nowrap">
													班级
												</td>	
												<td nowrap="nowrap">
													抽查日期
												</td>
												<td nowrap="nowrap">
													抽查类型
												</td>		
												<td nowrap="nowrap">
													违纪
												</td>	
												<td nowrap="nowrap">
													请假
												</td>	
												<td nowrap="nowrap">
													旷课次数
												</td>																																					
											</tr>
										</thead>
										<tbody class="tbstyle" id="flag">
											<logic:iterate name="rs" id="s">
												<tr>						
													<logic:iterate id="v" name="s" offset="0">
														<td align="left" nowrap="nowrap">
															${v}
														</td>
													</logic:iterate>
												</tr>
											</logic:iterate>
										</tbody>
									</table>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>	
			</logic:notEmpty>
		</table>
		
			<div class="buttontool" align="center">
				<span class="openbutton">
					<button type="button" class="button2" id="buttonClose" onclick="window.close();return false;" style="width: 100px" id="buttonClose">
						关 闭
					</button> 
				</span>
			</div>		
		</html:form>
	</body>
</html>
