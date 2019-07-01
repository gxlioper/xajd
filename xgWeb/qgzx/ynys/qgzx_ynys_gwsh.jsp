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
	<script language="javascript">
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：勤工助学 - 审核 - 岗位审核 - 单个审核
				</div>
			</div>
			<input type="hidden" name="pkVal"
				value="<bean:write name="rs" property="gwdm||gwsbsj"/>" />
			<input type="hidden" name="xhStr"
				value="<bean:write name="xhStr" />" />
		    <input type="hidden" name="xxyjStr" value="" />	
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							单个岗位审核
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right">
						校区：
					</td>
					<td align="left">
						<bean:write name="rs" property="xqmc" />
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
						岗位名称：
					</td>
					<td align="left">
						<bean:write name="rs" property="gwdm" />
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
						申请单位：
					</td>
					<td align="left">
						<bean:write name="rs" property="yrdwmc" />
					</td>
					<td align="right">
						学期：
					</td>
					<td align="left">
						<bean:write name="rs" property="xueqimc" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						申报时间：
					</td>
					<td align="left">
						<bean:write name="rs" property="gwsbsj" />
					</td>
					<td align="right">
						岗位性质：
					</td>
					<td align="left">
						<bean:write name="rs" property="gwxzmc" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						工作开始时间：
					</td>
					<td align="left">
						<bean:write name="rs" property="gzksrq" />
					</td>
					<td align="right">
						计酬标准：
					</td>
					<td align="left">
						<bean:write name="rs" property="jybcbz" />
						<bean:write name="rs" property="jcfs" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						工作结束时间：
					</td>
					<td align="left">
						<bean:write name="rs" property="gzjsrq" />
					</td>
					<td align="right">
						需要男生人数：
					</td>
					<td align="left">
						<bean:write name="rs" property="xyboy" />
					</td>
				</tr>
				<tr style="height:22px">
					<td height="20" align="right">
						负责人：
					</td>
					<td align="left">
						<bean:write name="rs" property="fzr" />
					</td>
					<td align="right">需要女生人数：
					</td>
					<td align="left">
						<bean:write name="rs" property="xygirl" />
					</td>
				</tr>
				<tr style="height:22px">
				  <td align="right">岗位数量：</td>
				  <td align="left"><bean:write name="rs" property="gwsl" /></td>
				  <td align="right">岗位要求： </td>
				  <td align="left"><bean:write name="rs" property="ryyq" /></td>
			  </tr>
				<tr style="height:22px">
					<td align="right">
						工作时间：
					</td>
					<td align="left">
						<bean:write name="rs" property="gzsj" />
					</td>
					<td align="right">
						总经费：
					</td>
					<td align="left">
						<bean:write name="rs" property="zjf" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">单位地点： </td>
					<td align="left">
						<bean:write name="rs" property="dwdz" />
					</td>
					<td align="right">
						<font color="red">*</font>审批使用人数：
					</td>
					<td align="left">
						<html:text name="rs" property="sqsyrs" style="width:130px"></html:text>人
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right"><font color="red">*</font>审批报酬标准： </td>
					<td align="left">
						<html:text name="rs" property="spbcbz" style="width:130px"/><bean:write name="rs" property="jcfs" /></td>
					<td align="right">
						审核：
					</td>
					<td align="left">
						<html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						工作内容：
					</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="gznr" />
					</td>
				</tr>
				<tr style="height:22px">
				  <td align="right">岗位特殊要求： </td>
				  <td align="left" colspan="3"><bean:write name="rs" property="gwtsyq" /></td>
			  </tr>
				<tr style="height:22px">
					<td align="right">
						用人单位意见：
					</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="sqdwyj" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						勤工办意见：
					</td>
					<td align="left" colspan="3">
						<html:textarea name="rs" property="qgbyj" style="width:90%"
							rows="3"></html:textarea>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						学工处意见：
					</td>
					<td align="left" colspan="3">
						<html:textarea name="rs" property="xgbyj" style="width:90%"
							rows="3"></html:textarea>
					</td>
				</tr>
				<thead>
				　　<tr style="height:22px">
						<td colspan="4" align="center">
							<div id="main2" style="color:blue;cursor:hand"
									onclick="document.all.child2.style.display=(document.all.child2.style.display =='none')?'':'none'">
										<div align="center" class="style1 style3">
										申请该岗位的学生列表
										</div>
							</div>
						</td>
					</tr>
			    </thead>
					<tr>
					    <td colspan="4" align="center">
					    <div id="child2" style="display:none">
					    <logic:empty name="xssqList">
											无申请该岗位的学生记录!
								</logic:empty>
									<logic:notEmpty name="xssqList">
										<fieldset>
											<legend>
												记录数：
												<bean:write name="count" />
											</legend>
											<table width="100%" id="rsTable1" class="tbstyle">
												<thead>
													<tr align="center" style="cursor:hand">
														<td width="7%">序号</td>
														<td width="17%">学号</td>
														<td width="13%">姓名</td>
														<td width="22%">班级</td>
														<td width="13%">是否贫困生</td>
														<td width="18%">联系电话</td>
														<td width="10%">学校审核</td>
													</tr>
												</thead>				
									<logic:iterate name="xssqList" id="xssqList">
										<tr align="center">
											<td>
												<bean:write name="xssqList" property="rownum" />
											</td>
											<td>
												<bean:write name="xssqList" property="xh" />
											</td>
											<td>
												<bean:write name="xssqList" property="xm" />
											</td>
											<td>
												<bean:write name="xssqList" property="bjmc" />
											</td>
											<td>
												<bean:write name="xssqList" property="sfpks" />
											</td>
											<td>
												<bean:write name="xssqList" property="lxdh" />
											</td>
											<td>
											    <input name="<bean:write name="xssqList" property="xh" />" type="checkbox" value="1" <bean:write name="xssqList" property="xxyj" />>
											</td>
										</tr>
									</logic:iterate>
											</table>
										</fieldset>
										</logic:notEmpty>
									</div>
					    </td>
					</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2"
					onclick="refreshForm('/xgxt/postChkOne.do?act=save');"
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
		<logic:equal value="view" name="result">
			<script>
				alert("操作成功!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
	</body>
</html>
