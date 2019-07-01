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
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
		<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<object id=eprint classid="clsid:CA03A5A8-9890-49BE-BA4A-8C524EB06441" codebase="images/webprint.cab" viewasext> </object>
		<base target="_self">
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="js/stuinfoFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript" src="js/webPrint.js"></script>
		<script language="javascript" src="js/BatAlert.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script type="text/javascript">
			var top= new Array();
			var left = new Array();
		    var x,y;
		    var zd = ["xm","hjqsxn","hjjsxn","hjxq1","hjxjdj1","fzxn","fzy"];
			
			var dragapproved=false			
			window.onload = function(){
			//直接打印	
			Print(true);
			setTimeout("",2000);
			
			//var cu_R = window.opener.curr_row.rowIndex;
			var curr_R = window.opener.curr_row.rowIndex;	
			curr_R = curr_R+1;
			if(curr_R >= window.opener.$("rsTable").rows.length){
				BatAlert.MyAlert("打印结束!","",function(){
				});
			}else{
				var xh1="";
				var xh2="";
				var xn = val('xn');
				var xq = val('xq');
							
				if(xh1 == "" && curr_R < window.opener.$("rsTable").rows.length){
					window.opener.rowOnClick(window.opener.$("rsTable").rows[curr_R]);//单击下一行
					//alert(window.opener.curr_row.cells[1].innerText);
					xh1 = window.opener.curr_row.cells[1].innerText.trim();
					curr_R = curr_R+1;
				}
				if(xh2=="" && curr_R < window.opener.$("rsTable").rows.length){
					window.opener.rowOnClick(window.opener.$("rsTable").rows[curr_R]);//单击下一行
					xh2 = window.opener.curr_row.cells[1].innerText.trim();				
				}
				window.location.href="xsxxgl.do?method=cjbgdlxdy&xh1="+xh1+"&xh2="+xh2+"&xn="+xn+"&xq="+xq;
				BatAlert.closeTips();
						
			}
			}
		</script>
		<style media="print">
			.noprint{
				display:none
			}
			.print{
				display:block
			}
		</style>
		
	</head>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
	<body>
	
		<center>
			<html:form action="/xsxxgl" method="post">
				<input type="hidden" id="xn" value="${xn}"/>
				<input type="hidden" id="xq" value="${xq}"/>
				<br>
				<div align="center" style=" font-size:23px; ">
					杭州职业技术<bean:message key="lable.xsgzyxpzxy" />（成绩报告单）
				</div>
				<logic:notEmpty name="stuInfoOne">
					<p align="left">
						<u>&nbsp;${xn}&nbsp;</u> 学年
						 第&nbsp;&nbsp;
						<u>&nbsp;${xqmc}&nbsp;</u> 学期
						&nbsp;&nbsp;班级
						<u>&nbsp;${stuInfoOne.bjmc}&nbsp;</u> 姓名
						<u>&nbsp;${stuInfoOne.xm}&nbsp;</u> &nbsp;&nbsp;
						<u id="year1_1">&nbsp;${rs.ffY}&nbsp;</u> 年
						<u id="month1_1">&nbsp;${rs.ffM}&nbsp;</u>月
						<u id="day1_1">&nbsp;${rs.ffD}&nbsp;</u>日发下学期定于
						<u id='year2_1'>&nbsp;${rs.kxY}&nbsp;</u>年
						<u id='month2_1'>&nbsp;${rs.kxM}&nbsp;</u>月
						<u id="day2_1">&nbsp;${rs.kxD}&nbsp;</u>日开学，学生必须在
						<u id="year3_1">&nbsp;${rs.bdY}&nbsp;</u>年
						<u id="month3_1">&nbsp;${rs.bdM}&nbsp;</u>月
						<u id="day3_1">&nbsp;${rs.bdD}&nbsp;</u>日来校报到并携带学杂费等合计：
						<u id="yuan1">&nbsp;${stuInfoOne.xfje}&nbsp;</u>元。 有成绩不合格的请于
						<u id="year4_1">&nbsp;${rs.bkksY}&nbsp;</u>年
						<u id="month4_1">&nbsp;${rs.bkksM}&nbsp;</u>月
						<u id="day4_1">&nbsp;${rs.bkksD}&nbsp;</u>日至
						<u id="year5_1">&nbsp;${rs.bkjsY}&nbsp;</u>年
						<u id="month5_1">&nbsp;${rs.bkjsM}&nbsp;</u>月
						<u id="day5_1">&nbsp;${rs.bkjsD}&nbsp;</u>日回校参加补考。
					</p>
					<div align="center">
						各科成绩及考勤情况
					</div>
					<table width="100%" class="tbstyle">
						<tr>
							<td width="5%" align="center">
								序号
							</td>
							<td align="center">
								科目
							</td>
							<td width="12%" align="center">
								成绩
							</td >
							<td width="5%" align="center">
								序号
							</td>
							<td align="center">
								科目
							</td>
							<td width="12%" align="center">
								成绩
							</td>
						</tr>
						<logic:iterate id="s" name="listOne">
						<tr>
						
							<td align="center">
								<bean:write name="s" property="rowOneA"/>
							</td>
							
							<td align="center">
								<bean:write name="s" property="kcOneA"/>
							</td>
							<td align="center">
								<bean:write name="s" property="cjOneA"/>
							</td>
							<td align="center">
								<bean:write name="s" property="rowOneB"/>
							</td>
							<td align="center">
								<bean:write name="s" property="kcOneB"/>
							</td>
							<td align="center">
								<bean:write name="s" property="cjOneB"/>
							</td>
						</tr>
						</logic:iterate>
					</table>
					<br>
					<table width="100%" class="tbstyle">
						<tr>
							<td  align="center">
								应上课天数
							</td>
							<td  align="center">
								实上课天数
							</td>
							<td  align="center">
								病假
							</td>
							<td  align="center">
								事假
							</td>
							<td  align="center">
								旷课
							</td>
							<td  align="center">
								迟到（早退）
							</td>
							<td  align="center">
								其他
							</td>
						</tr>
						<tr height="20px">
							<td>
								<div id="yskts1">${stuInfoOne.yskts}</div>
							</td>
							<td>
								<div id="sskts1">${stuInfoOne.sskts}</div>
							</td>
							<td>
								<div id="bj1">${stuInfoOne.bingjia}</div>
							</td>
							<td>
								<div id="sj1">${stuInfoOne.shijia}</div>
							</td>
							<td>
								<div id="kk1">${stuInfoOne.kuangke}</div>
							</td>
							<td>
								<div id="cd1">${stuInfoOne.chidao}</div>
							</td>
							<td>
								<div id="qt1">${stuInfoOne.qita}</div>
							</td>
						</tr>
					</table>
					<div align="center">
						综合素质评定
					</div>
					<table width="100%" class="tbstyle">
						<tr height="80px">
							<td>
								<div id="zhszcp1">${stuInfoOne.zhszpd}</div>
							</td>
							
						</tr>
					</table>
					<div align="right">
						班主任：
						<u id="bzrU1">&nbsp;${stuInfoOne.bzr}&nbsp;</u>联系电话：
						<u id="lxdhU1">&nbsp;${stuInfoOne.bzrlxdh}&nbsp;</u>
					</div>
				</logic:notEmpty>
				<logic:notEmpty name="stuInfoTow">
					<br>
					<br>
					<br>
					<div align="center" style=" font-size:23px; ">
						杭州职业技术<bean:message key="lable.xsgzyxpzxy" />（成绩报告单）
					</div>
					<p align="left">
						<u>&nbsp;${xn}&nbsp;</u> 学年
						 第&nbsp;&nbsp;
						<u>&nbsp;${xqmc}&nbsp;</u> 学期
						&nbsp;&nbsp;班级
						<u>&nbsp;${stuInfoTow.bjmc}&nbsp;</u> 姓名
						<u>&nbsp;${stuInfoTow.xm}&nbsp;</u> &nbsp;&nbsp;
						<u id="year1_2">&nbsp;${rs.ffY}&nbsp;</u> 年
						<u id="month1_2">&nbsp;${rs.ffM}&nbsp;</u>月
						<u id="day1_2">&nbsp;${rs.ffD}&nbsp;</u>日发下学期定于
						<u id="year2_2">&nbsp;${rs.kxY}&nbsp;</u>年
						<u id="month2_2">&nbsp;${rs.kxM}&nbsp;</u>月
						<u id="day2_2">&nbsp;${rs.kxD}&nbsp;</u> 日开学，学生必须在
						<u id="year3_2">&nbsp;${rs.bdY}&nbsp;</u>年
						<u id="month3_2">&nbsp;${rs.bdM}&nbsp;</u>月
						<u id="day3_2">&nbsp;${rs.bdD}&nbsp;</u> 日来校报到并携带学杂费等合计：
						<u id="yuan2">&nbsp;${stuInfoTow.xfje}&nbsp;</u>元。 有成绩不合格的请于
						<u id="year4_2">&nbsp;${rs.bkksY}&nbsp;</u>年
						<u id="month4_2">&nbsp;${rs.bkksM}&nbsp;</u>月
						<u id="day4_2">&nbsp;${rs.bkksD}&nbsp;</u>日至
						<u id="year5_2">&nbsp;${rs.bkjsY}&nbsp;</u>年
						<u id="month5_2">&nbsp;${rs.bkjsM}&nbsp;</u>月
						<u id="day5_2">&nbsp;${rs.bkjsD}&nbsp;</u>日回校参加补考。
					</p>
					<div align="center">
						各科成绩及考勤情况
					</div>
					<table width="100%" class="tbstyle">
						<tr>
							<td width="5%" align="center">
								序号
							</td>
							<td align="center">
								科目
							</td>
							<td width="12%" align="center">
								成绩
							</td>
							<td width="5%" align="center">
								序号
							</td>
							<td align="center">
								科目
							</td>
							<td width="12%" align="center">
								成绩
							</td>
						</tr>
						<logic:iterate id="s" name="listTow">
						<tr>
						
							<td align="center">
								<bean:write name="s" property="rowOneA"/>
							</td>
							
							<td align="center">
								<bean:write name="s" property="kcOneA"/>
							</td>
							<td align="center">
								<bean:write name="s" property="cjOneA"/>
							</td>
							<td align="center">
								<bean:write name="s" property="rowOneB"/>
							</td>
							<td align="center">
								<bean:write name="s" property="kcOneB"/>
							</td>
							<td align="center">
								<bean:write name="s" property="cjOneB"/>
							</td>
						</tr>
						</logic:iterate>
					</table>
					<br>
					<table width="100%" class="tbstyle">
						<tr>
							<td  align="center">
								应上课天数
							</td>
							<td  align="center">
								实上课天数
							</td>
							<td  align="center">
								病假
							</td>
							<td  align="center">
								事假
							</td>
							<td  align="center">
								旷课
							</td>
							<td  align="center">
								迟到（早退）
							</td>
							<td  align="center">
								其他
							</td>
						</tr>
						<tr height="20px">
							<td>
								<div id="yskts2">${stuInfoTow.yskts}
							</td>
							<td>
								<div id="sskts2">${stuInfoTow.sskts}
							</td>
							<td>
								<div id="bj2">${stuInfoTow.bingjia}
							</td>
							<td>
								<div id="sj2">${stuInfoTow.shijia}</div>
							</td>
							<td>
								<div id="kk2">${stuInfoTow.kuangke}</div>
							</td>
							<td>
								<div id="cd2">${stuInfoTow.chidao}</div>
							</td>
							<td>
								<div id="qt2">${stuInfoTow.qita}</div>
							</td>
						</tr>
					</table>
					<div align="center" >
						综合素质评定
					</div>
					<table width="100%" class="tbstyle">
						<tr height="80px">
							<td>
								<div id="zhszcp2">${stuInfoTow.zhszpd}</div>								
							</td>
						</tr>
					</table>
					<div align="right">
						班主任：
						<u id="bzr2">&nbsp;${stuInfoTow.bzr}&nbsp;</u>联系电话：
						<u id="lxdh2">&nbsp;${stuInfoTow.bzrlxdh}&nbsp;</u>
					</div>
				</logic:notEmpty>
			</html:form>
		</center>
	</body>
</html>
