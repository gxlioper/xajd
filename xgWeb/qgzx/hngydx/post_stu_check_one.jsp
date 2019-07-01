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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/studetailFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	    <script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
	    <script type='text/javascript' src='/xgxt/dwr/interface/hngyStuCj.js'></script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：勤工助学 - 审核 - 学生申请审核 - 单个审核
				</div>
			</div>
			<input type="hidden" name="pkVal"
				value="<bean:write name="rs" property="xh||gwdm||sqsj"/>" />
			<input type="hidden" name="gwdm"
				value="<bean:write name="rs" property="gwdm"/>" />
			<input type="hidden" name="xh"
				value="<bean:write name="rs" property="xh"/>" />
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							单个学生申请审核
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right">
						学号：
					</td>
					<td align="left">
						<bean:write name="rs" property="xh" />
						<input type="hidden" id="xh" value="<bean:write name="rs" property="xh" />" />
						<input type="hidden" id="xxdm" value="<bean:write name="xxdm"/>" />
					</td>
					<td align="right">
						年度：
					</td>
					<td align="left">
						<bean:write name="rs" property="nd" />
					</td>
				</tr>

				<tr style="height:22px">
					<td align="right">
						姓名：
					</td>
					<td align="left">
						<bean:write name="rs" property="xm" />
					</td>
					<td align="right">
						学年：
					</td>
					<td align="left">
						<bean:write name="rs" property="xn" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						性别：
					</td>
					<td align="left">
						<bean:write name="rs" property="xb" />
					</td>
					<td align="right">
						岗位名称：
					</td>
					<td align="left">
						<bean:write name="rs" property="gwdm" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						年级：
					</td>
					<td align="left">
						<bean:write name="rs" property="nj" />
					</td>
					<td align="right">
						申请时间：
					</td>
					<td align="left">
						<bean:write name="rs" property="sqsj" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						<bean:write name="rs" property="xymc" />
					</td>
					<td align="right">
						是否困难生：
					</td>
					<td align="left">
						<bean:write name="rs" property="sfpks" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						专业：
					</td>
					<td align="left">
						<bean:write name="rs" property="zymc" />
					</td>
					<td align="right">
						联系电话：
					</td>
					<td align="left">
						<bean:write name="rs" property="lxdh" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						班级：
					</td>
					<td align="left">
						<bean:write name="rs" property="bjmc" />
					</td>
					<td align="right">
						政治面貌：
					</td>
					<td>
						<bean:write name='map' property="zzmmmc" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						籍贯：
					</td>
					<td align="left" colspan="3">
						<bean:write name="map" property="jg" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						有何特长：
					</td>
					<td align="left" colspan="3">
						<bean:write name="map" property="yhtc" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						岗位记录：
					</td>
					<td align="left" colspan="3">
						<bean:write name="map" property="gzjl" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						申请理由：
					</td>
					<td align="left" colspan="3">
						<bean:write name="map" property="sqly" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						备注：
					</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="bz" />
					</td>
				</tr>
				<tr>
					<td align="right">
						可参加勤工助学时间：
					</td>
					<td colspan="3">
						<logic:notEmpty name="kxList">
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
									<td>
										星期六
									</td>
									<td>
										星期日
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
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}6" value="1" />
											<input type="hidden" name="index${kxsj.sjIndex}6"
												value="${kxsj.sat}" />
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}7" value="1" />
											<input type="hidden" name="index${kxsj.sjIndex}7"
												value="${kxsj.sun}" />
										</td>

									</tr>
								</logic:iterate>
							</table>
								<script>
										for(var i=0;i<7;i++){
											for(var j=1;j<8;j++){
											 	if(document.getElementById("index"+i+j)){
											 		if(document.getElementById("index"+i+j).value==1){
														document.getElementById(i+""+j).checked="checked";
												    }
											 	}
											}
										}
									</script>
						</logic:notEmpty>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<table width="100%" class="tbstyle">
							<tr>
								<td>
									<div id="main3" style="color:blue;cursor:hand"
										onclick="document.all.child3.style.display=(document.all.child3.style.display =='none')?'':'none';getPjpyOfQgzx();">
										<div align="center" class="style1 style3">
											<strong>获奖情况</strong>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<div id="child3" style="display:none">
							<table width="100%" align="center" class="tbstyle">
								<thead>
									<tr>
										<td colspan="9">
											<div align="center" class="style2">
												奖学金记录
											</div>
										</td>
									</tr>
									<tr>
										<td>
											年度
										</td>
										<td>
											学年
										</td>
										<td>
											学号
										</td>
										<td>
											姓名
										</td>
										<td>
											班级
										</td>
										<td>
											奖学金名称
										</td>
										<td>
											<bean:message key="lable.xsgzyxpzxy" />审核
										</td>
										<td>
											学校审核
										</td>
										<td>
											是否达标
										</td>
									</tr>
								</thead>
								<tbody width="100%" class="tbstyle" id="jxj">
								</tbody>
							</table>
							<br></br>
							<table width="100%" align="center" class="tbstyle">
								<thead>
									<tr>
										<td colspan="8">
											<div align="center" class="style2">
												荣誉称号记录
											</div>
										</td>
									</tr>
									<tr>
										<td>
											年度
										</td>
										<td>
											学年
										</td>
										<td>
											学号
										</td>
										<td>
											姓名
										</td>
										<td>
											班级
										</td>
										<td>
											荣誉称号名称
										</td>
										<td>
											<bean:message key="lable.xsgzyxpzxy" />审核
										</td>
										<td>
											学校审核
										</td>
									</tr>
								</thead>
								<tbody width="100%" class="tbstyle" id="rych">
								</tbody>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<table width="100%" class="tbstyle">
							<tr>
								<td>
									<div id="main5" style="color:blue;cursor:hand"
										onclick="document.all.child5.style.display=(document.all.child5.style.display =='none')?'':'none';">
										<div align="center" class="style1 style3">
											<strong>资助情况</strong>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFFFF" colspan="4">
						<div id="child5" style="display:none">
							<table width="100%" align="center" class="tbstyle">
								<logic:notEmpty name="zzrs">
									<input type="hidden" id="maxLength" name="maxLength"
										value="${zzrssize}" />
									<logic:iterate id="rsInfo" name="zzrs" indexId="index">
										<tr>
											<td id="td${index}" align="center" class="style2"
												bgcolor="#FFddcc" colspan="1"
												onclick="document.all.xszz${index}.style.display=(document.all.xszz${index}.style.display =='none')?'':'none';getXszzInfo(${index});">
												${rsInfo.tabCN}
												<input type="hidden" id="tab${index}" name="tab${index}"
													value="${rsInfo.tabEN}" />
											</td>
										</tr>

										<tbody style="display:none" width="100%" class="tbstyle"
											id="xszz${index}">
										</tbody>
									</logic:iterate>
								</logic:notEmpty>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<table width="100%" class="tbstyle">
							<tr>
								<td>
									<div id="main6" style="color:blue;cursor:hand"
										onclick="document.all.child6.style.display=(document.all.child6.style.display =='none')?'':'none';getHngydxBxkcj();">
										<div align="center" class="style1 style3">
											<strong>必修课不及格信息</strong>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<div id="child6" style="display:none">
							<table width="100%" align="center" class="tbstyle">
								<thead>
									<tr>
										<td colspan="9">
											<div align="center" class="style2">
												成绩信息
											</div>
										</td>
									</tr>
									<tr>
										<td>
											年度
										</td>
										<td>
											学年
										</td>
										<td>
											学号
										</td>
										<td>
											姓名
										</td>
										<td>
											班级
										</td>
										<td>
											课程名称
										</td>
										<td>
											补考成绩
										</td>
										<td>
											重新成绩
										</td>
										<td>
											成绩
										</td>
									</tr>
								</thead>
								<tbody width="100%" class="tbstyle" id="bxkcj">
								</tbody>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right">
						审核：
					</td>
					<td align="left" colspan="3">						
						<html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2"
					onclick="refreshForm('/xgxt/postStuChkOne.do?act=save');"
					style="width:80px" id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
		<logic:notEmpty name="flag">
			<logic:equal value="true" name="flag">
				<script language="javascript">
				alert("操作成功！");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:notEqual value="true" name="flag">
				<script language="javascript">
					alert("操作失败！");
				</script>
			</logic:notEqual>
		</logic:notEmpty>
		<logic:equal value="view" name="result">
			<logic:present name="hasSQ">
				<logic:match value="have" name="hasSQ">
					<script language="javascript">
	         	alert("该学生已有申请通过审核！");
	         	Close();
				window.dialogArguments.document.getElementById('search_go').click();
	         	</script>
				</logic:match>
				<logic:match value="notHave" name="hasSQ">
					<script language="javascript">
	         	alert("操作成功！");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
	         	</script>
				</logic:match>
			</logic:present>
		</logic:equal>
		<logic:equal value="full" name="result">
			<script>
				alert("人数已满！");
			</script>
		</logic:equal>
		<logic:equal value="knsfull" name="result">
			<script>
				alert("困难生人数已满！");
			</script>
		</logic:equal>
	</body>
</html>
