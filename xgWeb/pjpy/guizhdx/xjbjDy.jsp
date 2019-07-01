<%@ page language="java" contentType="text/html;charset=GBK"%>

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
<html:html>
<base target="_self">
<head>
	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="Pragma" http-equiv="no-cache" />
	<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
	<meta http-equiv="Expires" http-equiv="0" />
	<meta name="Copyright" content="正方软件 zfsoft" />
</head>
<%
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/xszzFunction.js"></script>
<script language="javascript">
</script>
<body>
	<html:form action="/zzlgdx_rcsw" method="post">
		<table width="100%" border="0" id="theTable">
			<tr height="930px">
				<td scope="col">
					<div align="center">
						&nbsp;<p/>
						&nbsp;<p/>
						<h1>
							先&nbsp;进&nbsp;班&nbsp;集&nbsp;体<p/>登<p/>记<p/>表
						</h1>
						<p/>&nbsp;
						<p/>&nbsp;
						<p/>&nbsp;
						<p/>&nbsp;
						<p/>&nbsp;
							<table border="0">
								<tr>
									<td><h3><bean:message key="lable.xb" /></h3></td>
									<td><h4><u>&nbsp;${rs.xymc }&nbsp;&nbsp; <logic:empty name="rs">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty></u></h4></td>
									<td><h3>专业</h3></td>
									<td><h4><u>&nbsp;&nbsp;${rs.zymc }&nbsp;&nbsp; <logic:empty name="rs">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty></u></h4></td>
								</tr>
								<tr>
									<td><h3>班级</h3></td>
									<td><h4><u>&nbsp;${rs.bjmc }&nbsp;&nbsp; <logic:empty name="rs">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty></u></h4></td>
									<td><h3>年度</h3></td>
									<td><h4><u>&nbsp;${rs.nd }&nbsp;&nbsp; <logic:empty name="rs">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty></u></h4></td>
								</tr>
								<tr>
									<td><h3>年级</h3></td>
									<td><h4><u>&nbsp;${rs.nj }&nbsp;&nbsp; <logic:empty name="rs">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty></u></h4></td>
									<td><h3>申请时间</h3></td>
									<td><h4><u>&nbsp;${rs.sqsj }&nbsp;&nbsp; <logic:empty name="rs">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty></u> </h4></td>
								</tr>
							</table>
						<p/>&nbsp;
						<p/>&nbsp;
						<p/>&nbsp;
						<p/>&nbsp;
						<p/>&nbsp;
						<h2>贵&nbsp;&nbsp;州&nbsp;&nbsp;大&nbsp;&nbsp;学</h2>
						<p/>&nbsp;
						<p/>&nbsp;
						<p/>&nbsp;
						<p/>&nbsp;
						<p/>&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<br />
					<br />
					&nbsp;
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="90%" class="tbstyle" align="center">
						<tr height="60px">
							<td width="23%" align="center">
									班主任姓名
							</td>
							<td width="23%" align="center">
									${rs.bzrxm }
							</td>
							<td width="23%" align="center">
									班级团员数
							</td>
							<td width="23%" align="center">
									${rs.tyrs }
							</td>
						</tr>
						<tr height="60px">
							<td align="center">
									班级人数
							</td>
							<td align="center">
									${rs.bjrs }
							</td>
							<td align="center">
									班级党员数
							</td>
							<td align="center">
								${rs.dyrs }
							</td>
						</tr>
						<tr height="60px">
							<td align="center">
									担任班级以上学生干部人数
							</td>
							<td align="center">
									${rs.gbrs }
							</td>
							<td align="center">
									获得2000元以上资助情况
							</td>
							<td align="center">
								${rs.zzqk }
							</td>
						</tr>
						<tr height="85px">
							<td align="center">
									本学年受处分情况
							</td>
							<td align="center" colspan="3">
									<logic:empty name="cfList">
										&nbsp;
									</logic:empty>
									<logic:notEmpty name="cfList">
										<input type="hidden" id="cfqk" value="0">
										<table width="90%" id="rsTable" class="tbstyle">
											<tr style="cursor:hand">
												<td>学号</td>
												<td>姓名</td>
												<td>学年</td>
												<td>学期</td>
												<td>处分类别</td>
												<td>处分原因</td>
												<td>处分时间</td>
												<td>处分文号</td>
											</tr>
										<logic:iterate id="s" name="cfList">
											<tr style="cursor:hand">
												<logic:iterate id="v" name="s">
													<td>
														<bean:write name="v"/>
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>
										</table>
									</logic:notEmpty>
							</td>
						</tr>
						<tr height="85px">
							<td align="center">
								两学期补考率补考人次
							</td>
							<td colspan="3">
								<logic:empty name="bkxx">
									&nbsp;
								</logic:empty>
								<logic:notEmpty name="bkxx">
									<table width="90%" id="rsTable" class="tbstyle">
										<tr style="cursor:hand">
											<td>学年</td>
											<td>学期</td>
											<td>班级人数</td>
											<td>补考人数</td>
											<td>补考率</td>
										</tr>
										<logic:iterate id="s" name="bkxx">
											<tr style="cursor:hand">
												<td>
													<bean:write property="xn" name="s"/>
												</td>
												<td>
													<bean:write property="xq" name="s"/>
												</td>
												<td>
													<bean:write property="bjrs" name="s"/>
												</td>
												<td>
													<bean:write property="rs" name="s"/>
												</td>
												<td>
													<bean:write property="bkl" name="s"/>
												</td>
											</tr>
										</logic:iterate>
									</table>
								</logic:notEmpty>
							</td>
						</tr>
						<tr height="85px">
							<td align="center">
								校院文明寝室比例
							</td>
							<td colspan="3">
								${rs.wmqs }
							</td>
						</tr>
						<tr height="85px">
							<td align="center">
								欠费学生人数及金额
							</td>
							<td colspan="3">
								${rs.jfqk}
							</td>
						</tr>
						<tr>
							<td align="center">
								入党和入党积极分子的人数、比例
							</td>
							<td colspan="3">
								入党人数：${rs.dyrs }<br/>
								入党人数比例：${rs.dybl }<br/>
								入党积极分子人数：${rs.jjfzrs } <br/>
								入党积极分子比例：${rs.jjfzbl }
							</td>
						</tr>
						<tr height="80px">
							<td align="center">
								体育锻炼及达标情况
							</td>
							<td colspan="3">
								${rs.dyqk}
							</td>
						</tr>
						<tr height="280px">
							<td align="center">
								本学年受各种奖励情况（名称及人数）
							</td>
							<td colspan="3">
								<logic:empty name="pjpyxx">
									&nbsp;
								</logic:empty>
								<logic:notEmpty name="pjpyxx">
									<table width="90%" id="rsTable" class="tbstyle">
											<tr style="cursor:hand">
												<td>学年</td>
												<td>班级</td>
												<td>获奖奖项</td>
												<td>获奖人数</td>
											</tr>
											<logic:iterate id="s" name="pjpyxx">
												<tr style="cursor:hand">
													<logic:iterate id="v" name="s">
														<td>
															<bean:write name="v"/>
														</td>
													</logic:iterate>
												</tr>
											</logic:iterate>
										</table>
								</logic:notEmpty>
							</td>
						</tr>
					</table><br/>
					
					<table width="90%" class="tbstyle" align="center" >
						<tr height="945px">
							<td>
								<div align="center"  style="height:10px" >班级主要事迹</div>
								<logic:equal value="" property="zysj" name="rs">
									<p style="height:900px"/>
								</logic:equal>
								<logic:notEqual value="" property="zysj" name="rs">
								<p style="height:900px" align="left">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.zysj }
								</p>
								</logic:notEqual>
							</td>
						</tr>
					</table>
					<br/>
					<table width="90%" class="tbstyle" align="center">
						<tr height="945px">
							<td>
								<div align="center">班级主要事迹</div>
								<p style="height:900px" align="center"/>
							</td>
						</tr>
					</table>
					<br/>
					<table width="90%" class="tbstyle" align="center">
						<tr height="280px">
							<td colspan="2">&nbsp;</td>
						</tr>
						<tr height="140px">
							<td rowspan="2" width="15%" align="center">班主任<br/><br/>辅导员<br/><br/>推&nbsp;&nbsp;  荐<br/><br/>意 &nbsp;&nbsp; 见</td>
							<td>
								<p style="height:100px">
									&nbsp;&nbsp; &nbsp; &nbsp;${rs.bzryj }
								</p>
								<p align="right">
									 签名 &nbsp;&nbsp; &nbsp; &nbsp;  &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;      年&nbsp;&nbsp; &nbsp; &nbsp;     月 &nbsp;&nbsp; &nbsp; &nbsp;    日&nbsp;&nbsp; &nbsp; &nbsp; 
								</p>
							</td>
						</tr>
						<tr height="140px">
							<td>
								<p style="height:100px">
								&nbsp;
								</p>
								<p align="right">
									 签名 &nbsp;&nbsp; &nbsp; &nbsp;  &nbsp;&nbsp; &nbsp; &nbsp;    &nbsp;&nbsp; &nbsp; &nbsp;   年&nbsp;&nbsp; &nbsp; &nbsp;     月 &nbsp;&nbsp; &nbsp; &nbsp;    日&nbsp;&nbsp; &nbsp; &nbsp; 
								</p>
							</td>
						</tr>
						<tr height="150px">
							<td align="center">学&nbsp;&nbsp;院<br/><br/>党&nbsp;&nbsp;委<br/><br/>推&nbsp;&nbsp;荐<br/><br/>意&nbsp;&nbsp;见</td>
							<td>
								<p style="height:100px">
								&nbsp;&nbsp; &nbsp; &nbsp;${rs.xyyj }
								</p>
								<p align="right">
									 签名 &nbsp;&nbsp; &nbsp; &nbsp;  &nbsp;&nbsp; &nbsp; &nbsp;  &nbsp;&nbsp; &nbsp; &nbsp;（公章） &nbsp;&nbsp; &nbsp; &nbsp;      年&nbsp;&nbsp; &nbsp; &nbsp;     月 &nbsp;&nbsp; &nbsp; &nbsp;    日&nbsp;&nbsp; &nbsp; &nbsp; 
								</p>
							</td>
						</tr>
						<tr height="150px">
							<td align="center">学&nbsp;&nbsp;校<br/><br/>审&nbsp;&nbsp;批<br/><br/>意&nbsp;&nbsp;见</td>
							<td>
								<p style="height:100px">
								&nbsp;&nbsp; &nbsp; &nbsp;${rs.xxyj }
								</p>
								<p align="right">
									 （公章） &nbsp;&nbsp; &nbsp; &nbsp;      年&nbsp;&nbsp; &nbsp; &nbsp;     月 &nbsp;&nbsp; &nbsp; &nbsp;    日&nbsp;&nbsp; &nbsp; &nbsp; 
								</p>
							</td>
						</tr>
					</table>
					<br/>
					<div style="width:95%" align="right">
						贵州大学学生工作处制表
					</div>
					<p/>
					<center>
					<div style="width:95%" align="left">
						注：1、本表一律使用打印文稿。<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、班级主要事迹一栏概括填写突出事迹，同时另附相关证明材料。
					</div>
					</center>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
