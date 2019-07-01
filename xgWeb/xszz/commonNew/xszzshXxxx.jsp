<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.Iterator" />
<jsp:directive.page import="java.util.ArrayList" />

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
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			var xyshyj = document.getElementById('xyshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			var isXX = document.getElementById('isXX').value;
			var xxsh = document.getElementById('xxsh').value;
			if((("通过" == xxsh)) && (isXX != "is")){
				alert("学校审核已通过，不能再修改审核结果!");
	          	return false;
			}
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />审核意见不能大于1000个字符");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("学校审核意见不能大于1000个字符");
	          		 return false;
	       		 }
	       	}
		
			refreshForm('/xgxt/new_common_xszz.do?method=xszzshXxxx&actDo=save');
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/data_search" method="post">
			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("保存成功！");
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("保存失败！");
	         	</script>
				</logic:match>
			</logic:present>
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 审核 - 资助审核 - 单个审核
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pk"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" name="tName" value="<bean:write name="tName" />" />
			<input type="hidden" name="xxsh" value="<bean:write name='rs' property='xxsh' />" />
			<input type="hidden" name="isXX" value="<bean:write name="isXX" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="9" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							资助项目
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xmmc" />
					</td>
					<td>
						<div align="center">
							申请时间
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sqsj" />
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						学号
					</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="xh" />
					</td>
					<td width="11%" scope="col">
						<div align="center">
							姓名
						</div>
					</td>
					<td colspan="3" scope="col">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							性别
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xb" />
					</td>
					<td>
						<div align="center">
							年级
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="nj" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							身份证号
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sfzh" />
					</td>
					<td>
						<div align="center">
							联系电话
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="lxdh" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xymc" />
					</td>
					<td>
						<div align="center">
							专业
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="zymc" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							班级
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="bjmc" />
					</td>
					<td>
						<div align="center">
							生源地
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="syd" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							宿舍编号
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="ssbh" />
					</td>
					<td>
						<div align="center">
							寝室电话
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="qsdh" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							政治面貌
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="zzmmmc" />
					</td>
					<td>
						<div align="center">
							民族
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="mzmc" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							入学年月
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="rxny" />
					</td>
					<td>
						<div align="center">
							出生日期
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="csrq" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							专业成绩排名
						</div>
					</td>
					<td colspan="3">
						<bean:write name='rs' property="zycjpm" />
					</td>
					<td>
						<div align="center">
							专业人数
						</div>
					</td>
					<td colspan="3">
						<bean:write name='rs' property="zyrs" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							不及格科目数
						</div>
					</td>
					<td colspan="3">
						<bean:write name='rs' property="bjgmc" />
					</td>
					<td scope="row">
						<div align="center">
							&nbsp;
						</div>
					</td>
					<td colspan="3">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							卡号
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="kh" />
					</td>
					<td>
						<div align="center">
							入学前户口
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="rxqhk" />
					</td>
				</tr>
				<tr>
					<td colspan="4" scope="row">
						<div align="left">
							是否愿意参加慈善或志愿活动
						</div>
					</td>
					<td width="12%">
						<bean:write name="rs" property="sfyycjcshzyhd" />
					</td>
					<td colspan="3">
						<div align="left">
							是否愿意申请国家助学贷款或勤工助学
						</div>
					</td>
					<td width="12%">
						<bean:write name="rs" property="sfyysqgjzxdkhqgzx" />
					</td>
				</tr>
				<tr>
					<td colspan="9" scope="row">
						<div align="center">
							家庭信息
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							家庭类型
						</div>
					</td>
					<td colspan="7">
						&nbsp;&nbsp;&nbsp;是否健全：
						<bean:write name="rs" property="sfjq" />
						&nbsp;&nbsp;&nbsp;&nbsp; 是否孤儿：
						<bean:write name="rs" property="sfge" />
						&nbsp;&nbsp;&nbsp;&nbsp; 是否单亲：
						<bean:write name="rs" property="sfdq" />
						&nbsp;&nbsp;&nbsp;&nbsp; 是否残疾：
						<bean:write name="rs" property="sfcj" />
						<br />
						是否军烈属：
						<bean:write name="rs" property="sfjls" />
						&nbsp;&nbsp;&nbsp;&nbsp; 是否离异：
						<bean:write name="rs" property="sfly" />
						&nbsp;&nbsp;&nbsp;&nbsp; 是否重病：
						<bean:write name="rs" property="sfzb" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							是否困难生
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sfkns" />
					</td>
					<td>
						<div align="center">
							邮政编码
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="yzbm" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭住址
						</div>
					</td>
					<td colspan="7">
						<bean:write name="rs" property="jtzz" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							家庭联系电话
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtlxdh" />
					</td>
					<td>
						<div align="center">
							家庭人均
							<br />
							年收入
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtrjnsr" />
					</td>
				</tr>
				<tr>
					<td width="4%" rowspan="6" scope="row">
						<div align="center">
							家
							<br />
							庭
							<br />
							成
							<br />
							员
							<br />
							信
							<br />
							息
						</div>
					</td>
					<td width="12%">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="12%">
						<div align="center">
							年龄
						</div>
					</td>
					<td width="10%">
						<div align="center">
							与本人
							<br />
							关系
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							工作(学习)单位
						</div>
					</td>
					<td width="12%">
						<div align="center">
							职业
						</div>
					</td>
					<td width="10%">
						<div align="center">
							年收入
							<br />
							(元)
						</div>
					</td>
					<td width="12%">
						<div align="center">
							健康状况
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							&nbsp;
							<bean:write name="rs" property="jtcy1_nl" />
							&nbsp;
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_gx" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy1_gzdw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_zy" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_nsr" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_jkzk" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							&nbsp;
							<bean:write name="rs" property="jtcy2_nl" />
							&nbsp;
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_gx" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy2_gzdw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_zy" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_nsr" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_jkzk" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							&nbsp;
							<bean:write name="rs" property="jtcy3_nl" />
							&nbsp;
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_gx" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy3_gzdw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_zy" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_nsr" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_jkzk" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							&nbsp;
							<bean:write name="rs" property="jtcy4_nl" />
							&nbsp;
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_gx" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy4_gzdw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_zy" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_nsr" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_jkzk" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							&nbsp;
							<bean:write name="rs" property="jtcy5_nl" />
							&nbsp;
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_gx" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy5_gzdw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_zy" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_nsr" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_jkzk" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							学生在本地
							<br />
							受助情况
						</div>
					</td>
					<td colspan="7">
						<bean:write name="rs" property="xszbdszqk" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							家庭遭受
							<br />
							自然灾害情况
						</div>
					</td>
					<td colspan="7">
						<bean:write name="rs" property="jtzszrzhqk" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							家庭遭受
							<br />
							突发意外事件
						</div>
					</td>
					<td colspan="7">
						<bean:write name="rs" property="jtzstfywsj" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							其他情况
						</div>
					</td>
					<td colspan="7">
						<bean:write name="rs" property="qtqk"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							民政部门通讯地址
						</div>
					</td>
					<td colspan="7">
						<bean:write name="rs" property="mzbm_txdz" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							民政部门邮政编码
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="mzbm_yzbm" />
					</td>
					<td>
						<div align="center">
							民政部门
							<br />
							联系电话
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="mzbm_lxdh" />
					</td>
				</tr>
				<tr>
					<td colspan="9">
						<div align="center">
							申请信息
						</div>
					</td>
				</tr>
				<logic:equal name="isNULL" value="no">
					<%
								ArrayList zdyzdList = (ArrayList) request
								.getAttribute("zdyzdList");

								for (Iterator it = zdyzdList.iterator(); it
								.hasNext();) {
							String[] temp = (String[])it.next();
							String zdydm = "zdy" + temp[0];
					%>
					<tr>
						<td colspan="2">
							<div align="center">
								<%=temp[1]%>
							</div>
						</td>
						<td colspan="7">
							<bean:write name="rs" property="<%=zdydm%>" />
						</td>
					</tr>
					<%
					}
					%>
				</logic:equal>
				<tr>
					<td colspan="2">
						<div align="center">
							申请理由
						</div>
					</td>
					<td colspan="7">
						<bean:write name="rs" property="sqly" />
					</td>
				</tr>
				<%String[] je = (String[])request.getAttribute("zjeList"); %>
				<tr>
					<td colspan="9">
						<div align="center">
							个人记录
						</div>
					</td>
				</tr>
				<tr>
					<td rowspan="2">
						<div align="center">
							奖<br />学<br />金<br />记<br />录
						</div>
					</td>
					<td>
						<div align="center">
							获得金额
						</div>
					</td>
					<td colspan="7">
						<%=je[0]%>&nbsp;元
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<%
							ArrayList jxjList = (ArrayList)request.getAttribute("xsJxjjlList");
							if(jxjList.size() == 0){
						%>
						&nbsp;&nbsp;&nbsp;&nbsp;无记录
						<%	
							} else {
						%>
						<br />
						<%
								for(Iterator it = jxjList.iterator(); it.hasNext();){
						%>
						 	&nbsp;<%=it.next() %><br />
						 <%} %>
						 <br />
						 <%} %>
					</td>
				</tr>
				<tr>
					<td rowspan="2">
						<div align="center">
							勤<br />工<br />助<br />学<br />记<br />录
						</div>
					</td>
					<td>
						<div align="center">
							获得金额
						</div>
					</td>
					<td colspan="7">
						<%=je[1]%>&nbsp;元
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<%
							ArrayList qgzuList = (ArrayList)request.getAttribute("xsQgzuCjjlList");
							if(qgzuList.size() == 0){
						%>
						&nbsp;&nbsp;&nbsp;&nbsp;无记录
						<%	
							} else {
						%>
						<br />
						<%
								for(Iterator it = qgzuList.iterator(); it.hasNext();){
						%>
						 	&nbsp;<%=it.next() %><br />
						 <%} %>
						 <br />
						 <%} %>
					</td>
				</tr>
				<tr>
					<td rowspan="2">
						<div align="center">
							资<br />助<br />记<br />录
						</div>
					</td>
					<td>
						<div align="center">
							获得金额
						</div>
					</td>
					<td colspan="7">
						<%=je[2]%>&nbsp;元
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<%
							ArrayList zzList = (ArrayList)request.getAttribute("xszzHdjeList");
							if(zzList.size() == 0){
						%>
						&nbsp;&nbsp;&nbsp;&nbsp;无记录
						<%	
							} else {
						%>
						<br />
						<%
								for(Iterator it = zzList.iterator(); it.hasNext();){
						%>
						 	&nbsp;<%=it.next() %><br />
						 <%} %>
						 <br />
						 <%} %>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							助学贷款金额
						</div>
					</td>
					<td colspan="3">
						<%=je[3] %>&nbsp;元
					</td>
					<td>
						<div align="center">
							获得总金额
						</div>
					</td>
					<td colspan="3">
						<%=je[4] %>&nbsp;元
					</td>
				</tr>
				<logic:equal name="isXX" value="is">
					<tr>
						<td colspan="2">
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />审核
							</div>
						</td>
						<td colspan="3">
							<bean:write name="rs" property="xysh" />
						</td>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />批准金额
							</div>
						</td>
						<td colspan="3">
							<bean:write name="rs" property="xypzje" />
						</td>
					</tr>
				</logic:equal>
				<tr>
					<td colspan="2">
						<div align="center">
							审核结果
						</div>
					</td>
					<td colspan="3">
						<html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
					<td>
						<div align="center">
							批准金额
						</div>
					</td>
					<td colspan="3">
						<html:select name="rs" property="zzje">
							<html:option value="0"></html:option>
							<html:options collection="zzjeList" property="zzje"
								labelProperty="zzje" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="left">
							<bean:message key="lable.xsgzyxpzxy" />审核意见
						</div>
					</td>
					<td colspan="6">
						<html:textarea name="rs" property="xyshyj" rows='5' style="width:100%" onblur="yzdx(this,'xyshyj')"/>
						<input type="hidden" id="xyshyj" name="xyshyj" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="left">
							学校审核意见
						</div>
					</td>
					<td colspan="6">
						<html:textarea name="rs" property="xxshyj" rows='5' style="width:100%" onblur="yzdx(this,'xxshyj')"/>
						<input type="hidden" id="xxshyj" name="xxshyj" value="">
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button class="button2" onclick="yz()" style="width:80px"
					id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
	</body>
</html>
