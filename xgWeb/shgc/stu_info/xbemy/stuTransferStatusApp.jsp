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
		<title><bean:message key="lable.title"/>
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript">
		function checkNull(url){
			var qssj = document.getElementById("qssj").value;
			var jzsj = document.getElementById("jzsj").value;
			var nNull = document.getElementById("notnull").value;
			var param = nNull.split("-");
			for(var i=0; i<param.length; i++){
				var str = document.getElementById(param[i]).value;
				if(str==null || str== ""){
					alert('请将带\*号的项目填写完整!');
					return false;
				}
			}
			
			if(qssj>jzsj){
				alert('截止时间不能晚于起始时间！');
				return false;				
			}
			
			refreshForm(url);			
		}
		
		function print(url){
			var pk = "!!";
			var xh = document.getElementById("xh").value;
			var qssj = document.getElementById("qssj").value;
			var jzsj = document.getElementById("jzsj").value;
			var ydlbdm = document.getElementById("ydlbdm").value;
			var jtdz = document.getElementById("jtdz").value;
			var jtdh = document.getElementById("jtdh").value;
			
			var ydqnj = document.getElementById("ydqnj").value;
			var ydqzydm = document.getElementById("ydqzydm").value;
			var ydqbjdm = document.getElementById("ydqbjdm").value;
			
			var ydhnj = document.getElementById("nj").value;
			var ydhzydm = document.getElementById("zy").value;
			var ydhbjdm = document.getElementById("bj").value;
						
			var sqly = document.getElementById("sqly").value;
			
			url += "&xh=" + xh;
			url += "&qssj=" + qssj;
			url += "&jzsj=" + jzsj;
			url += "&ydlbdm=" + ydlbdm;
			url += "&jtdz=" + jtdz;
			url += "&jtdh=" + jtdh;
			
			url += "&ydqnj=" + ydqnj;
			url += "&ydqzydm=" + ydqzydm;			
			url += "&ydqbjdm=" + ydqbjdm;
			
			url += "&ydhnj=" + ydhnj;
			url += "&ydhzydm=" + ydhzydm;
			url += "&ydhbjdm=" + ydhbjdm;
						
			url += "&sqly=" + sqly;
			
			window.open(url);
			}
		
	</script>
	<body>		
		<html:form action="/xbemyStuStatus.do" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				当前位置:学生信息 - 学籍变动申请 - 申请学籍变动
			</div>
		</div>
			<input type="hidden" name="url" id="url"
				value="/shgc/stu_info/xbemy/stuTransferStatusApp.jsp">
			<input type="hidden" name="variable" id="variable" value="ydinfo">
			<input type="hidden" name="redirect" id="redirect" value="true">
			<input type="hidden" name="realTable" id="realTable" value="xbemy_xjbdsqb">
			<input type="hidden" name="page" id="page" value="stustatus">
			<input type="hidden" id="read" value="" />
			<input type="hidden" value="xh-ydqnj-ydqzydm-ydqbjdm-ydlbdm" id="notnull" />
			<input type="hidden" value="<bean:write name="rs" property="xymc"/>" id="xydm" />
			<input type="hidden" value="<bean:write name="rs" property="zymc"/>" id="zydm" />
			<input type="hidden" value="<bean:write name="rs" property="bjmc"/>" id="bjdm" />
			<input type="hidden" value="<bean:write name="rs" property="ydqnj"/>" id="ydqnj" />
			<input type="hidden" value="<bean:write name="rs" property="ydqxz"/>" id="ydqxz" />
			<input type="hidden" name="zyV" id="zyV" value="zyV"/>
			<input type="hidden" name="bjV" id="bjV" value="bjV"/>	
			<input type="hidden" name="xyV" id="xyV" value="xyV"/>
				
			<table width="100%" class="tbstyle">
				<div class="buttontool" id="btu" width="100%">
					<center>申请学籍变动</center>
				</div>
				<tr style="height:22px">
					<td>
						<div align="right">
							<font color="red">*</font>学号：
						</div>
					</td>
					<td>
						<logic:equal value="student" name="userOnLine">
							<html:text name="rs" styleId="xh" property="xh" readonly="true"
								style="cursor:hand" />
						</logic:equal>
						<logic:equal value="teacher" name="userOnLine">
							<html:text name="rs" property="xh"
								onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />
							<button align="left" class="button2"
								onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								id="buttonFindStu">
								选择
							</button>
						</logic:equal>
					</td>
					<td>
						<div align="right">
							姓名：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xm"/>	
					</td>
				</tr>
				<tr style="height:20px">
					<td>						
						<div align="right">
							性别：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xb"/>
					</td>
					<td>
						<div align="right">
							民族：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="mzmc"/>			
					</td>
				</tr>
				<tr style="height:22px">
					<td>
						<div align="right">
							出生日期：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="csrq"/>
					</td>
					<td>
						<div align="right">
							生源地：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="syd"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td>
						<div align="right">
							起始时间：
						</div>
					</td>
					<td>
						<html:text name="rs" property="qssj" onclick="return showCalendar('qssj','y-mm-dd');" styleId="qssj"/>
					</td>
					<td>
						<div align="right">
							截止时间：
						</div>
					</td>
					<td>
						<html:text name="rs" property="jzsj" onclick="return showCalendar('jzsj','y-mm-dd');" styleId="jzsj"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td>
						<div align="right">
							<font color="red">*</font>异动类别：
						</div>
					</td>
					<td>
						<html:select name="rs" property="ydlbdm" styleId="ydlbdm" style="160px">
							<html:option value=""></html:option>
							<html:options collection="ydlbList" property="ydlbdm"
								labelProperty="ydlbmc" />
						</html:select>
					</td>
					<td>
						<div align="right">
							家庭电话：
						</div>
					</td>
					<td>
						<html:text name="rs" property="jtdh" styleId="jtdh"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						家庭地址：
					</td>
					<td colspan="3">
						<html:text name="rs" property="jtdz" styleId="jtdz"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						申请理由：
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="sqly" style="width:100%;height:45px" styleId="sqly"/>
					</td>
				</tr>
			</table>

			<table class="tbstyle" width="100%" height="220px">
				<tr style="height:22px">
					<td colspan="2" width="100%">
						<div class="buttontool" style="width:100%">
							<center>
								异动信息
							</center>
						</div>
					</td>
				</tr>
				<tr align="top">
					<td>
						<fieldset>
							<legend>
								异动前信息
							</legend>
							<table style="width:310px" class="tbstyle">
								<tr style="height:26px">
									<td align="right" width="15%">
										<div align="right">
											<font color="red">*</font>年级：
										</div>
									</td>
									<td align="left" width="30%">
										<html:select name="rs" property="ydqnj" style="width:180px" styleId="ydqnj">
											<html:option value=""></html:option>
											<html:options  collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>		
									</td>
								</tr>
								<tr style="height:26px">
									<td width="22%">
										<div align="right">
											<font color="red">*</font>专业：
										</div>
									</td>
									<td width="33%">
										<html:select name="rs" property="ydqzydm" style="width:180px" styleId="ydqzydm">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
									</td>
								</tr>
								<tr style="height:26px">
									<td width="22%">
										<div align="right">
											<font color="red">*</font>班级：
										</div>
									</td>
									<td width="33%">
										<html:select name="rs" property="ydqbjdm" style="width:180px" styleId="ydqbjdm">
											<html:option value=""></html:option>
											<html:options  collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>
							</table>
						</fieldset>
					</td>

					<td>
						<fieldset>
							<legend>
								异动后信息
							</legend>
							<table width="310px" class="tbstyle">
								<tr>
									<td width="22%">
										<div align="center">
											年级：
										</div>
									</td>
									<td>
										<html:select name="rs" property="ydhnj"
											styleId="nj" style="width:180px"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options  collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>										
									</td>
								</tr>
								<tr>
									<td width="22%">
										<div align="center">
											专业：
										</div>
									</td>
									<td width="33%">
										<html:select name="rs" property="ydhzydm"
											styleId="zy" style="width:180px"
											onchange="initBjList();">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<td width="22%">
										<div align="center">
											班级：
										</div>
									</td>
									<td width="33%">
										<html:select name="rs" property="ydhbjdm"
											styleId="bj" style="width:180px">
											<html:option value=""></html:option>
											<html:options  collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>
							</table>
						</fieldset>
					</td>
				</tr>
			</table>
			<logic:equal value="yes" name="writeAble">
				<div>
					<div class="buttontool" id="btu" width="100%">
						<button class="button2"
							onclick="checkNull('xbemyStuStatus.do?method=showTransferStuStatus&doType=save');">
							 提 交 申 请 
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="print('xbemyStuStatus.do?method=printTransferStatusApp')">
							打 印 报 表
						</button>
					</div>
				</div>
			</logic:equal>
			<logic:notEmpty name="result" scope="request">			
				<logic:equal value="true" name="result" scope="request">
					<script>
					alert("操作成功！");
				</script>
				</logic:equal>
				<logic:equal value="false" name="result" scope="request">
					<script>
					Close();					
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
