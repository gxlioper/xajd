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
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
	</head>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/pjpyFunction.js"></script>
	<script type="text/javascript" src="pjpy/hbsf/hbsfjs.js"></script>
	<script type="text/javascript">
		function refreshJxjinfo() {
			var xh = document.getElementById('xh').value;
			if (xh==''||xh==null) {
				document.getElementById('jxjdm').selectedIndex=0;
				alert('请选择学号再操作!');
				return;
			} else {
				var act = document.getElementById('act').value;
				
				if (act!='modi') {
				refreshForm('/xgxt/prise_apply.do');
				}
			}
		}
	</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/pjpy_zzsf_apply" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：评奖评优 - 奖学金申请 - 填写申请表 
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
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("您输入的学号无效!");
    </script>
				</logic:equal>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/prise_apply.do" />
				
				<input type="hidden" name="pkVal" id="pkValue" value="${pkValue }"/>
				<input type="hidden" name="act" id="act" value="${act }"/>
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="14" align="center">
								<b>填写申请表</b>
								<logic:equal value="yes" name="exis">
								<font color="red"><br/>提示: 本学年您已申请过一次奖学金,不能再次申请!</font>
								</logic:equal>
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td width="25%" align="right">
								<font color="red">*</font>学号：</td>
							<td width="24.8%" align="left">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" style="width:70%"/>
								<button type="button" onClick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
							选择
							</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td width="13%" align="right" nowrap>
								<font color="red">*</font>学号：
							</td>	
							<td width="14%" align="left">
								<bean:write name='rs' property="xh" /><html:hidden name='rs' property="xh" styleId="xh" />
							</td>				
						</logic:equal>
						<td colspan="2" align="right" width="25%">
							学年：						</td>
						<td width="25%" colspan="4" align="left">
							<bean:write name='rs' property="xn" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							姓名：
						</td>
						<td align="left">
							<bean:write name='rs' property="xm" />
						</td>
						<td colspan="2" align="right" >
							学期：
						</td>
						<td align="left" colspan="4">
							<bean:write name='rs' property="xq" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							性别：
						</td>
						<td align="left">
							<bean:write name='rs' property="xb" />
						</td>
						<td colspan="2" align="right" >
							<font color="red">*</font>申请奖学金：
						</td>
						<td align="left" colspan="4">
							<html:select property="xmdm" styleId="jxjdm"
								onchange="refreshJxjinfo()">
								<option value=""></option>
								<html:options collection="jxjList" property="jxjdm"
									labelProperty="jxjmc" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							年级：
						</td>
						<td align="left">
							<bean:write name='rs' property="nj" />
						</td>
						<td colspan="2" align="right" >
							奖学金类别：
						</td>
						<td align="left" colspan="4">
							<bean:write name='rs' property="jxjlb" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							<bean:write name='rs' property="xymc" />
						</td>
						<td colspan="2" align="right" >
							奖励金额：
						</td>
						<td align="left" colspan="4">
							<bean:write name='rs' property='jlje' />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" style="width:11%">
							专业：
						</td>
						<td align="left">
							<bean:write name='rs' property="zymc" />
						</td>
						<td colspan="2" align="right" >
							担任职务：
						</td>
						<td align="left" colspan="4">
							<html:text name='rs' property="drzw" styleId="a" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							班级：
						</td>
						<td align="left">
							<bean:write name='rs' property="bjmc" />
						</td>
						<td colspan="2" align="right" >
							手机号码：
						</td>
						<td align="left" colspan="4">
							<html:text name='rs' property="sjhm" styleId="a"  maxlength="14" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							外语水平：
						</td>
						<td align="left">
							<html:text name='rs' property="wysp" styleId="a"  style="width:80%"/>
						</td>
						<logic:equal value="10878" name="xxdm">
						<td colspan="2" align="right" >
							银行卡号：
						</td>
						<td align="left" colspan="4">
							<html:text name="rs" property="kh" styleId="kh" onkeypress="chkonlynum(this,event)" maxlength="20"></html:text>
						</td>
						</logic:equal>
						<logic:notEqual value="10878" name="xxdm">
							<td colspan="2" align="right" >
							</td>
							<td align="left" colspan="4">
							</td>
						</logic:notEqual>
					</tr>
					<logic:equal value="10402" name="xxdm"><!-- 漳州师范 -->
						<tr align="left" style="height:22px">
					  <td height="30" rowspan="3" align="right">&nbsp;</td>
					  <td colspan="3"><div align="center">学习情况</div></td>
				      <td colspan="6"><div align="center">综合考评情况</div></td>
			      </tr>
					
					<tr align="left" style="height:22px">
					 <td colspan="1" rowspan="2"><div align="center">学习成绩</div></td>
					  <td colspan="2" style="width:45%"><div align="center">排序</div></td>
					  <td width="15%" rowspan="2"><div align="center">综合考评成绩</div></td>
				      <td colspan="2"><div align="center">排序</div></td>
			      </tr>
					<tr align="left" style="height:22px">
					  <td style="width:15%"><div align="center">班级</div></td>
					  <td style="width:15%"><div align="center">专业</div></td>
					  <td colspan="" style="width:15%"><div align="center">班级</div></td>
				      <td colspan="" style="width:15%"><div align="center">专业</div></td>
			      </tr>
					<tr align="left" style="height:22px">
					  <td align="right">第一学年：</td>
					  <td colspan=""><html:text property="pjcj1" maxlength="6" style="width:90%"></html:text></td>
					  <td><html:text property="bjcjpx1" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>
				      <td><html:text property="zycjpx1" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>
				      <td><html:text property="zhkpzf1" maxlength="4"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>
			          <td colspan=""><html:text property="zhkpbjpx1" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>
		              <td colspan=""><html:text property="zhkpzypx1" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>
	              </tr>
					<tr align="left" style="height:22px">
					  <td align="right">第二学年：</td>
					  <td colspan=""><html:text property="pjcj2" maxlength="6" style="width:90%"></html:text></td>
					  <td><html:text property="bjcjpx2" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>
				      <td><html:text property="zycjpx2" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>
				      <td><html:text property="zhkpzf2" maxlength="4"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>
			          <td colspan=""><html:text property="zhkpbjpx2" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>
		              <td colspan=""><html:text property="zhkpzypx2" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>
	              </tr>
					<tr align="left" style="height:22px">
					  <td align="right">第三学年：</td>
					  <td colspan=""><html:text property="pjcj3" maxlength="6" style="width:90%"></html:text></td>
					  <td><html:text property="bjcjpx3" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>
				      <td><html:text property="zycjpx3" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>
				      <td><html:text property="zhkpzf3" maxlength="4"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>
			          <td colspan=""><html:text property="zhkpbjpx3" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>
		              <td colspan=""><html:text property="zhkpzypx3" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>
	              </tr>
					<tr align="left" style="height:22px">
					  <td align="right">第四学年：</td>
					  <td colspan=""><html:text property="pjcj4" maxlength="6" style="width:90%"></html:text></td>
					  <td><html:text property="bjcjpx4" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>
				      <td><html:text property="zycjpx4" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>
				      <td><html:text property="zhkpzf4" maxlength="4"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>
			          <td colspan=""><html:text property="zhkpbjpx4" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>
		              <td colspan=""><html:text property="zhkpzypx4" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>
	              </tr>
					</logic:equal>
					<logic:notEqual value="10402" name="xxdm">
					<tr align="left" style="height:22px">
						<td align="right">
							主要成绩：
						</td>
						<td colspan="9">
							<table class="tbstyle" width="100%">
								<logic:equal name="xxdm" value="10878">
								<tr>
									<td align="center" width="16%">
										学年
									</td>
									<td align="center">
										<bean:write name='rs' property="xn" />
									</td>
									<td align="center" width="16%">
										学期
									</td>
									<td align="center">
										${cxq}
									</td>
									<td align="center" width="16%">
										课程平均分
									</td>
									<td align="center">
										${cpjf}分
									</td>
								</tr>
								<tr>
									<td align="center" width="16%">
										学年
									</td>
									<td align="center">
										<bean:write name='rs' property="xn" />
									</td>
									<td align="center" width="16%">
										学期
									</td>
									<td align="center">
										${qxq}
									</td>
									<td align="center" width="16%">
										课程平均分
									</td>
									<td align="center">
										${qpjf}分
									</td>
								</tr>
								<tr>
								<td align="center" colspan="2">
										课程
									</td>
									<td align="center" colspan="1">
										学期
									</td>
									<td align="center" colspan="3">
										分数
									</td>
								</tr>
										<logic:notEmpty name="cjList">
											<logic:iterate id="s" name="cjList">
											<tr>
											<td align="center" colspan="2">
												<bean:write name="s" property="kcmc"/>
											</td>
											<td align="center"  colspan="1">
												<bean:write name="s" property="xqmc"/>
											</td>
											<td colspan="3" align="center">
												<bean:write name="s" property="cj"/>分<br/>
											</td>
											</tr>
											</logic:iterate> 
										</logic:notEmpty>
								</logic:equal>
								<logic:notEqual name="xxdm" value="10878">
								<tr>
									<td align="center" width="16%">
										学年
									</td>
									<td align="center">
										<bean:write name='rs' property="xn" />
									</td>
									<td align="center" width="16%">
										学期
									</td>
									<td align="center">
										<bean:write name='rs' property="xq" />
									</td>
									<td align="center" width="16%">
										课程平均分
									</td>
									<td align="center">
										${pjf }分
									</td>
								</tr>
								<tr>
								<td align="center" colspan="4">
										课程
									</td>
									<td align="center" colspan="2">
										分数
									</td>
									</tr>
										<logic:notEmpty name="cjList">
											<logic:iterate id="s" name="cjList">
											<tr>
											<td align="center" colspan="4">
												<bean:write name="s" property="kcmc"/>
											</td>
											<td colspan="2" align="center">
												<bean:write name="s" property="cj"/>分<br/>
											</td>
											</tr>
											</logic:iterate> 
										</logic:notEmpty>
								</logic:notEqual>
							</table>
<%--						</td>--%>
					</tr>
					<logic:equal value="yes" name="zh">
					<tr style="height:22px">
						<td align="right">
							综合成绩：
						</td>
						<td align="left">
							<html:text name="rsMap" property="zhszcpcj"  styleId="zhszcpcj"  readonly="true"></html:text>
						</td>
						<td colspan="2" align="right" >
							综合排名：
						</td>
						<td align="left" colspan="4">
							<html:text name="rsMap" property="zhszcpcjpm"  styleId="zhszcpcjpm"   readonly="true"></html:text>
						</td>
					</tr>
					
					<tr>
					
							<td align="right">
							项目名称：
						</td>
						<td align="left" colspan="7">
							<html:text name="rsMap" property="szmc1" styleId="szmc1" style="width:90px"  readonly="true"></html:text>
							<html:text name="rsMap" property="szmc2" styleId="szmc2" style="width:90px" readonly="true"></html:text>
							<html:text name="rsMap" property="szmc3" styleId="szmc3" style="width:90px"  readonly="true"></html:text>
							<html:text name="rsMap" property="szmc4" styleId="szmc4" style="width:90px"  readonly="true"></html:text>
							<html:text name="rsMap" property="szmc5" styleId="szmc5" style="width:90px"  readonly="true"></html:text>
						</td>
						
					</tr>
					<tr style="height:22px">
						<td align="right">
							项目成绩：
						</td>
						<td align="left" colspan="7">
							<html:text name="rsMap" property="szcj1" styleId="szcj1" style="width:90px"   readonly="true"></html:text>
							<html:text name="rsMap" property="szcj2" styleId="szcj2" style="width:90px"  readonly="true"></html:text>
							<html:text name="rsMap" property="szcj3" styleId="szcj3" style="width:90px"   readonly="true"></html:text>
							<html:text name="rsMap" property="szcj4" styleId="szcj4" style="width:90px"   readonly="true"></html:text>
							<html:text name="rsMap" property="szcj5" styleId="szcj5" style="width:90px"   readonly="true"></html:text>
						</td>
						
					</tr>
					</logic:equal>
					</logic:notEqual>
					<logic:notEqual value="10402" name="xxdm"><!-- 安徽建工 -->
					<logic:notEqual value="10878" name="xxdm">
						<tr align="left" style="height:22px">
					  <td height="30" rowspan="3" align="right">&nbsp;</td>
					 
				      <td colspan="9"><div align="center">综合考评情况</div></td>
			      </tr>
					
					<tr align="left" style="height:22px">
<%--					  <td colspan="2" style="width:45%"><div align="center">学习成绩排序</div></td>--%>
					  <td width="15%" rowspan="2"><div align="center">综合考评成绩</div></td>
				      <td colspan="6"><div align="center">排序</div></td>
			      </tr>
					<tr align="left" style="height:22px">
<%--					  <td style="width:15%"><div align="center">班级</div></td>--%>
<%--					  <td style="width:15%"><div align="center">专业</div></td>--%>
					  <td colspan="2" style="width:15%"><div align="center">班级</div></td>
				      <td colspan="4" style="width:15%"><div align="center">专业</div></td>
			      </tr>
					<tr align="left" style="height:22px">
					  <td align="right">第一学年：</td>
<%--					  <td><html:text property="bjcjpx1" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>--%>
<%--				      <td><html:text property="zycjpx1" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>--%>
				      <td><html:text property="zhkpzf1" maxlength="4"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>
			          <td colspan="2"><html:text property="zhkpbjpx1" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>
		              <td colspan="2"><html:text property="zhkpzypx1" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>
	              </tr>
					<tr align="left" style="height:22px">
					  <td align="right">第二学年：</td>
<%--					  <td><html:text property="bjcjpx2" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>--%>
<%--				      <td><html:text property="zycjpx2" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>--%>
				      <td><html:text property="zhkpzf2" maxlength="4"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>
			          <td colspan="2"><html:text property="zhkpbjpx2" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>
		              <td colspan="2"><html:text property="zhkpzypx2" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>
	              </tr>
					<tr align="left" style="height:22px">
					  <td align="right">第三学年：</td>
<%--					  <td><html:text property="bjcjpx3" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>--%>
<%--				      <td><html:text property="zycjpx3" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>--%>
				      <td><html:text property="zhkpzf3" maxlength="4"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>
			          <td colspan="2"><html:text property="zhkpbjpx3" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>
		              <td colspan="2"><html:text property="zhkpzypx3" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>
	              </tr>
					<tr align="left" style="height:22px">
					  <td align="right">第四学年：</td>
<%--					  <td><html:text property="bjcjpx4" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>--%>
<%--				      <td><html:text property="zycjpx4" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>--%>
				      <td><html:text property="zhkpzf4" maxlength="4"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>
			          <td colspan="2"><html:text property="zhkpbjpx4" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>
		              <td colspan="2"><html:text property="zhkpzypx4" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>
	              </tr>
					</logic:notEqual>
					</logic:notEqual>
<%--	              <logic:present name="isAHJG">--%>
<%--					<tr align="left" style="height:22px">--%>
<%--					  <td align="right">第五学年：</td>--%>
<!-- 					  <td><html:text property="bjcjpx5" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>
				      <td><html:text property="zycjpx5" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td> -->
<%--				      <td><html:text property="zhkpzf5" maxlength="4"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>--%>
<%--			          <td colspan="2"><html:text property="zhkpbjpx5" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>--%>
<%--		              <td colspan="2"><html:text property="zhkpzypx5" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:90%"/></td>--%>
<%--	              	</tr>--%>
<%--	              </logic:present>--%>
	              
	              <logic:equal value="10402" name="xxdm">
	              	<tr align="left" style="height:22px">
						<td align="right">
							主要成绩：
							<br/>
							<span class="style1">(限制800汉字)</span>
						</td>
						<td colspan="9">
							<html:textarea name='rs' property='xxjl' style="width:99%" rows='5' />
						</td>
					</tr>
	              </logic:equal>
					<tr align="left" style="height:22px">
						<logic:present name="showhzy">
							<td align="right">
								奖罚情况：
								<br/>
							<span class="style1">(限制800汉字)</span>
							</td>
							<td colspan="9">
								<html:textarea name='rs' property='jfqk' style="width:99%" rows='5' />
							</td>
						</logic:present>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right">
							申请理由：
							<br/>
							<span class="style1">(限制800汉字)</span>
						</td>
						<td colspan="9">
							<html:textarea name='rs' property='sqly' style="width:99%"
								rows='5' /></td>
					</tr>
					<logic:equal value="10878" name="xxdm">
						<tr align="left" style="height:22px">
						<td align="right">
							备注：
							<br/>
							<span class="style1">(可注明是否贫特困生，<br/>当前学年是否获得国家励志、<br/>国家奖学金)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						</td>
						<td colspan="9">
							<html:textarea name='rs' property='bz' style="width:99%"
								rows='5' /></td>
					</tr>
					</logic:equal>
				</table>
				<div class="buttontool" align="center">
					<button type="button" class="button2" <logic:equal value="yes" name="exis">disabled</logic:equal>
						onclick="dataDoSaveApply1('/xgxt/pjpy_zzsf_apply_save.do','jxjdm','jxj','xh-jxjdm')">
						提 交 申 请
					</button>
					&nbsp;&nbsp;
<%--					<logic:equal value="10878" name="xxdm">--%>
<%--						<button type="button" id="btn_cj" class="button2" onclick="showTopWin('ahjg_xscjb.do?xh='+document.getElementById('xh').value,'500','400')">--%>
<%--						学 生 成 绩--%>
<%--					</button>--%>
<%--					&nbsp;&nbsp;--%>
<%--					</logic:equal>--%>
					<button type="button" class="button2" onClick="window.open('/xgxt/pjpy_zzsf_jxjpsdjb.do?xh='+document.getElementById('xh').value+'&jxjdm='+document.getElementById('jxjdm').value)">
						打 印 报 表
					</button>
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("申请成功！");
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("申请失败！本学年您已申请过一次奖学金,不能再次申请.");
    </script>
				</logic:equal>
				<logic:equal value="bhg" name="inserted">
					<script>
						alert('提示：该项奖学金的申请基本条件是无必修课成绩不及格,\n      无必修课成绩三门以上低于70分,\n      无任何违纪处分!');
					</script>
				</logic:equal>
				<logic:equal value="nocj" name="inserted">
					<script>
						alert('提示：成绩表中无该生信息！');
					</script>
				</logic:equal>
				<logic:equal value="notj" name="inserted">
					<script>
						alert('该生平均成绩未达到奖学金申请条件！');
					</script>
				</logic:equal>
				<script language="javascript">
				if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
    				window.dialogArguments.document.all("search_go").click();
    				Close();
    			}
		</script>
			</logic:notEmpty>
		</html:form>
		
		<div style="display:none;color: red;font-size: 14px;background-color: yellow" id="message" name="message"></div>
	</body>
</html>
