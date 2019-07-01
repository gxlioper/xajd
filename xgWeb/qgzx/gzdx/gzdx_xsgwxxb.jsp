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
		<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript">
		function stringFormat(){
			var element = ['kcjqgzxsj','sqly','bz'];
			for(var i=0; i<element.length; i++){
				if(ele(element[i])){
					ele(element[i]).innerHTML = formatContentWidth(ele(element[i]).innerText,30);
				}
			}
		}
	</script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>	
	<body onload="stringFormat()">
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow">当前所在位置: 勤工助学 - 申请 - 申请结果查询 - 详细信息</span>
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4" align="center">
								岗位信息
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right">
							学号：
						</td>
						<td align="left">
							${rs.xh}
						</td>
						<td align="right">
							岗位名称：
						</td>
						<td align="left">
							${rs.gwdm}
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							姓名：
						</td>
						<td align="left">
							${rs.xm}
						</td>
						<td align="right">
							年度：
						</td>
						<td align="left">
							${rs.nd}
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							性别：
						</td>
						<td align="left">
							${rs.xb}
						</td>
						<td align="right">
							学年：
						</td>
						<td align="left">
							${rs.xn}
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							年级：
						</td>
						<td align="left">
							${rs.nj}
						</td>
						<td align="right">
							学期：
						</td>
						<td align="left">
							${rs.xqmc}
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right"><bean:message key="lable.xsgzyxpzxy" />: 
						</td>
						<td align="left">
							${rs.xymc}
						</td>
						<td align="right">
							联系电话：
						</td>
						<td align="left">
							${rs.lxdh}
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							专业：
						</td>
						<td align="left">
							${rs.zymc}
						</td>
						<td align="right">
						    是否困难生：
						</td>
						<td align="left">
						    ${rs.sfpks}
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							班级：
						</td>
						<td align="left">
							${rs.bjmc}
						</td>
						<td align="right">
						   有何特长：
						</td>
						<td align="left">
						    ${rs.yhtc}
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />审核：
						</td>
						<td align="left">
							${rs.xyyj}
						</td>
						<td align="right">
						   是否有效：
						</td>
						<td align="left">
						    ${rs.sfyx}
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							学校审核：
						</td>
						<td align="left" colspan="3">
							${rs.xxyj}
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right" nowrap="nowrap">
							可从事工作：
						</td>
						<td colspan="3" id="kcsgz">
							${rs.kcsgz}
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right" nowrap="nowrap">
							家庭经济情况：
						</td>
						<td colspan="3" id="kcsgz">
							${rs.jjqk}
						</td>
					</tr>
					<tr>
						<td align="right">
							可参加勤工助学时间：
						</td>
							<logic:present name="kxList">
							<td colspan="3">
								<table border="0" cellpadding="0" cellspacing="0" align="center">
									<tr>
										<td align="center">
											时间
										</td>
										<td>
											星期一
										</td>
										<td>
											星期二
										</td>
										<td>
											星期三
										</td>
										<td>
											星期四
										</td>
										<td>
											星期五
										</td>
									</tr>
									<logic:iterate id="kxsj" name="kxList">
										<tr>
											<td>
												${kxsj.sj}
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}1" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}1"
													value="${kxsj.mon}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}2" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}2"
													value="${kxsj.tue}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}3" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}3"
													value="${kxsj.wed}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}4" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}4"
													value="${kxsj.thu}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}5" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}5"
													value="${kxsj.fri}" />
											</td>
										</tr>
									</logic:iterate>
								</table>
								</td>
								<script language="javascript">
										for(var i=0;i<8;i++){
											for(var j=1;j<6;j++){
											 	if(document.getElementById("index"+i+j)){
											 		if(document.getElementById("index"+i+j).value==1){
														document.getElementById(i+""+j).checked="checked";
												    }
											 	}
											}
										}
								</script>							
						</logic:present>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right">
							备注：
						</td>
						<td colspan="3" id="bz">
							${rs.bz}
						</td>
					</tr>
				</table>
			</logic:notEmpty>
			<center>
				<div class="buttontool" id="btn">
					<button type="button" class="button2" id="btn_gb"
						onclick="Close();return false;"
						style="width:80px">
						关 闭
					</button>								
				</div>					
			</center>
		</html:form>
	</body>
</html>