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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
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
	<script language="javascript">
    </script>
	<script language="javascript" src="js/function.js"></script>
	<body>
		<center>
			<html:form action="/fdywork_result" method="post">
				<div class="title">
					<div class="title_img" id="title_m">
						当前所在位置：公寓管理 - 宿舍分配 - 床位分配 - 分配情况统计
					</div>
				</div>
				
				<logic:empty name="rs">
					<p align="center">
						未找到任何记录！
					</p>
				</logic:empty>
				<logic:notEmpty name="rs">										
					<table width="99%" class="tbstyle" id="rsTable">
						<tr>
							<td align="center">
								<B><font size="5">入住情况统计 </font> </B>
								<div align="right">

								</div>
							</td>
						</tr>
						<tr>
							<td align="center">
							</td>
						</tr>
						<tr>
							<td align="center">
								<table width="100%" class="tbstyle">
									<thead>
										<tr>
											<td  width="15%" align="center">
												<bean:message key="lable.xsgzyxpzxy" />
											</td>
											<td  width="10%" align="center">
												年级
											</td>
											<td  width="20%" align="center">
												专业
											</td>
											<td  width="15%" align="center">
												班级
											</td>
											<td width="5%" align="center">
												总人数
											</td>
											<td width="6%" align="center">
												入住人数
											</td>
											<td width="7%" align="center">
												未入住人数
											</td>
											<td width="15%" align="center">
												未入住比例
											</td>
										</tr>
									</thead>
									<logic:iterate name="rs" id="s">
										<tr>
											<td  align="center" rowspan="<bean:write name="s" property="bjcout"/>"
												>
												<bean:write name="s" property="xymc" />
												</td>
												<logic:notEqual value="0" name="s" property="bjcout">
													<logic:iterate id="n" name="s" property="coutinfo">														
														<td >
															<bean:write name="n" property="nj" />
														</td>
														<td >
															<bean:write name="n" property="zymc" />
														</td>
														<td >
															<bean:write name="n" property="bjmc" />
														</td>
														<td >
															<bean:write name="n" property="zcout" />
														</td>
														<td >
															<bean:write name="n" property="yzcout" />
														</td>
														<td >
															<bean:write name="n" property="wzcout" />
														</td>
														<td >
															<img src="fdygl/fdygzdc/total.jpg" width="<bean:write name="n" property="wzbl"/>px" height="10px"/>
															<bean:write name="n" property="wzbl" />%
														</td>
														<%
														out.print("</tr>");
														%>
													</logic:iterate>
												</logic:notEqual>
												<logic:equal value="0" name="s" property="bjcout">
													<td >
												
											</td>
											
											<td>
												
											</td>
											<td>
												
											</td>
											<td>
												
											</td>
											<td>
												
											</td>
											<td>
												
											</td>
											<td>
												
											</td>
											
												</logic:equal>
											</tr>	
											<tr style="height:30 px" onmouseover=this.style.backgroundColor="#D7E6F0" onmouseout=this.style.backgroundColor="#FFFFFF">
											<td align="center" colspan="4">
											合计
											</td>
																				
											<td>
												<bean:write name="s" property="xyzcout" />
											</td>
											<td>
												<bean:write name="s" property="xyyzcout" />
											</td>
											<td>
												<bean:write name="s" property="xywzcout" />
											</td>
											<td>
												<img src="fdygl/fdygzdc/total.jpg" width="<bean:write name="s" property="zwzbl"/>px" height="10px"/>
															<bean:write name="s" property="zwzbl" />%
											</td>
											</tr>
									</logic:iterate>									
								</table>								
							</td>
						</tr>						
					</table>
					
					<div class="buttontool" align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="expTab('rsTable','','')">
							打 印 报 表
						</button>
					</div>
				</logic:notEmpty>
			</html:form>
		</center>
	</body>
</html>
