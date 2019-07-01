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
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/pjpy_zzsf_rychsq" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：评奖评优 - 荣誉称号申请 - 填写申请表
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
				<input type="hidden" id="url" name="url" value="/credit_apply.do" />
				<table width="98%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="8" align="center">
								<b>填写申请表</b>							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td width="8%" colspan="2" align="right">
								<font color="red">*</font>学号：</td>
							<td width="15%" align="left">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" style="width:70%"/>
								<button type="button" onClick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
选择								</button></td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td width="8%" colspan="2" align="right">
								<font color="red">*</font>学号：</td>
							<td width="15%" align="left"><html:hidden name='rs' property="xh" styleId="xh" />
								<bean:write name='rs' property="xh" /></td>
						</logic:equal>
						<td width="16%" align="right" colspan='2'>学年：</td>
						<td width="2%" align="left" colspan='3'>
							<bean:write name='rs' property="xn" /></td>
					</tr>
					<tr style="height:22px">
						<td colspan="2" align="right">
							姓名：</td>
						<td align="left">
							<bean:write name='rs' property="xm" /></td>
						<td colspan="2" align="right">
							学期：</td>
						<td align="left" colspan='3'>
							<bean:write name='rs' property="xq" /></td>
					</tr>
					<tr style="height:22px">
						<td colspan="2" align="right">
							性别：</td>
						<td align="left">
							<bean:write name='rs' property="xb" /></td>
						<td colspan="2" align="right">
							<font color="red">*</font>申请荣誉称号：</td>
						<td align="left" colspan='3'>
							<html:select property="xmdm" styleId="rychdm">
								<option value=""></option>
								<html:options collection="rychList" property="rychdm"
									labelProperty="rychmc" />
							</html:select></td>
					</tr>
					<tr style="height:22px">
						<td colspan="2" align="right">
							年级：</td>
						<td align="left">
							<bean:write name='rs' property="nj" /></td>
						<td colspan="2" align="right">外语水平：</td>
						<td align="left" colspan='3'><html:text name='rs' property="wysp" styleId="a"  style="width:70%"/></td>
					</tr>
					<tr style="height:22px">
						<td colspan="2" align="right">
							<bean:message key="lable.xsgzyxpzxy" />：						</td>
						<td align="left">
							<bean:write name='rs' property="xymc" />						</td>
						<td colspan="2" align="right">手机号码：</td>
						<td align="left" colspan='3'><html:text name='rs' property="sjhm" styleId="a" style="width:70%" /></td>
					</tr>
					<tr style="height:22px">
						<td colspan="2" align="right">
							专业：</td>
						<td align="left">
							<bean:write name='rs' property="zymc" /></td>
						<td colspan="2" align="right">
							担任职务：</td>
						<td align="left" colspan='3'>
							<html:text name='rs' property="drzw" styleId="a" style="width:70%" /></td>
					</tr>
					<tr style="height:22px">
						<td colspan="2" align="right">
							班级：</td>
						<td align="left">
							<bean:write name='rs' property="bjmc" /></td>
						<td colspan="2" align="right">体育达标情况：</td>
						<td align="left" colspan='3'><html:text name='rs' property="tydbqk" style="width:70%" styleId="a" /></td>
					</tr>

					<tr style="height:22px">
						<td colspan="2" align="right">
							生源地：</td>
						<td align="left">
							<html:text name='rs' property="syd" style="width:80%" styleId="a" /></td>
						<td colspan="2" align="right">现任职务：</td>
						<td align="left" colspan='3'><html:text name='rs' property="byzx" style="width:70%" styleId="a" /></td>
					</tr>
					<logic:equal value="10402" name="xxdm">
						<tr style="height:22px">
					  <td width="5%" height="75" rowspan="6" align="center"><p>个<p>人<p>成<p>绩</td>
					  <td width="10%" rowspan="2" align="right">&nbsp;</td>
					  <td colspan="3" align="left"><div align="center">学习情况</div></td>
				      <td colspan="4" align="left"><div align="center">综合考评情况</div></td>
			      </tr>
					<tr style="height:22px">
					  <td align="center">学习成绩</td>
					  <td align="left" width="11%"><div align="center">班级排序</div></td>
				      <td width="11%" align="left"><div align="center">专业排序</div></td>
				      <td width="8%" align="left"><div align="center">考评总分</div></td>
				      <td width="12%" align="left"><div align="center">班级排序</div></td>
				      <td width="12%" colspan="2" align="left"><div align="center">专业排序</div></td>
			      </tr>
				<tr style="height:22px">
					  <td align="right">第一学年：</td>
					  <td align="left"><html:text name="rs" property="pjcj1" maxlength="6" style="width:100px"></html:text></td>
				      <td align="left"><html:text name='rs' property="bjcjpx1" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
				      <td align="left"><html:text  name='rs' property="zycjpx1" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
				      <td align="left"><html:text  name='rs' property="zhkpzf1" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
			          <td align="left"><html:text  name='rs' property="zhkpbjpx1" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
			          <td colspan="2" align="left"><html:text  name='rs' property="zhkpzypx1" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:70%"/></td>
		          </tr>
					<tr style="height:22px">
					  <td align="right">第二学年：</td>
					  <td align="left"><html:text name="rs" property="pjcj2" maxlength="6" style="width:100px"></html:text></td>
				      <td align="left"><html:text  name='rs' property="bjcjpx2" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
				      <td align="left"><html:text  name='rs' property="zycjpx2" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%" /></td>
				      <td align="left"><html:text  name='rs' property="zhkpzf2" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%" /></td>
			          <td align="left"><html:text  name='rs' property="zhkpbjpx2"  maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%" /></td>
			          <td colspan="2" align="left"><html:text name='rs' property="zhkpzypx2" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:70%" /></td>
		          </tr>
					<tr style="height:22px">
					  <td align="right">第三学年：</td>
					  <td align="left"><html:text name="rs" property="pjcj3" maxlength="6" style="width:100px"></html:text></td>
				      <td align="left"><html:text  name='rs' property="bjcjpx3" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
				      <td align="left"><html:text  name='rs' property="zycjpx3" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
				      <td align="left"><html:text  name='rs' property="zhkpzf3" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
			          <td align="left"><html:text  name='rs' property="zhkpbjpx3" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
			          <td colspan="2" align="left"><html:text  name='rs' property="zhkpzypx3" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:70%"/></td>
		          </tr>
					<tr style="height:22px">
					  <td align="right">第四学年：  </td>
					  <td align="left"><html:text name="rs" property="pjcj4" maxlength="6" style="width:100px"></html:text></td>
				      <td align="left"><html:text  name='rs' property="bjcjpx4" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
				      <td align="left"><html:text  name='rs' property="zycjpx4" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
				      <td align="left"><html:text  name='rs' property="zhkpzf4" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
			          <td align="left"><html:text  name='rs' property="zhkpbjpx4" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
			          <td colspan="2" align="left"><html:text  name='rs' property="zhkpzypx4" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:70%" /></td>
		          </tr>
					<tr style="height:22px">
						<td colspan="2" align="right">
							家庭地址：
						</td>
						<td colspan="6" align="left"><html:text name='rs' style="width:98%" property="jtdz" styleId="a" maxlength="50"/></td>
					</tr>
					</logic:equal>
					<logic:notEqual value="10402" name="xxdm">
						<tr style="height:22px">
					  <td width="5%" height="75" rowspan="6" align="center"><p>个<p>人<p>成<p>绩</td>
					  <td width="10%" rowspan="2" align="right">&nbsp;</td>
					  <td colspan="2" align="left"><div align="center">学习情况</div></td>
				      <td colspan="4" align="left"><div align="center">综合考评情况</div></td>
			      </tr>
					<tr style="height:22px">
					  <td align="left"><div align="center">班级排序</div></td>
				      <td width="13%" align="left"><div align="center">专业排序</div></td>
				      <td width="8%" align="left"><div align="center">考评总分</div></td>
				      <td width="12%" align="left"><div align="center">班级排序</div></td>
				      <td width="12%" colspan="2" align="left"><div align="center">专业排序</div></td>
			      </tr>
				<tr style="height:22px">
					  <td align="right">第一学年：</td>
				      <td align="left"><html:text name='rs' property="bjcjpx1" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
				      <td align="left"><html:text  name='rs' property="zycjpx1" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
				      <td align="left"><html:text  name='rs' property="zhkpzf1" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
			          <td align="left"><html:text  name='rs' property="zhkpbjpx1" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
			          <td colspan="2" align="left"><html:text  name='rs' property="zhkpzypx1" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:70%"/></td>
		          </tr>
					<tr style="height:22px">
					  <td align="right">第二学年：</td>
				      <td align="left"><html:text  name='rs' property="bjcjpx2" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
				      <td align="left"><html:text  name='rs' property="zycjpx2" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%" /></td>
				      <td align="left"><html:text  name='rs' property="zhkpzf2" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%" /></td>
			          <td align="left"><html:text  name='rs' property="zhkpbjpx2"  maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%" /></td>
			          <td colspan="2" align="left"><html:text name='rs' property="zhkpzypx2" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:70%" /></td>
		          </tr>
					<tr style="height:22px">
					  <td align="right">第三学年：</td>
				      <td align="left"><html:text  name='rs' property="bjcjpx3" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
				      <td align="left"><html:text  name='rs' property="zycjpx3" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
				      <td align="left"><html:text  name='rs' property="zhkpzf3" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
			          <td align="left"><html:text  name='rs' property="zhkpbjpx3" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
			          <td colspan="2" align="left"><html:text  name='rs' property="zhkpzypx3" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:70%"/></td>
		          </tr>
					<tr style="height:22px">
					  <td align="right">第四学年：  </td>
				      <td align="left"><html:text  name='rs' property="bjcjpx4" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
				      <td align="left"><html:text  name='rs' property="zycjpx4" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
				      <td align="left"><html:text  name='rs' property="zhkpzf4" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
			          <td align="left"><html:text  name='rs' property="zhkpbjpx4" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
			          <td colspan="2" align="left"><html:text  name='rs' property="zhkpzypx4" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:70%" /></td>
		          </tr>
					<tr style="height:22px">
						<td colspan="2" align="right">
							家庭地址：
						</td>
						<td colspan="6" align="left"><html:text name='rs' style="width:98%" property="jtdz" styleId="a" maxlength="50"/></td>
					</tr>
					</logic:notEqual>
					<tr style="height:22px">
						<td colspan="2" align="right">
							主要事迹：</td>
							
							
						<td colspan="6" align="left"><html:textarea rows="5" name='rs' style="width:98%" property="zysj" styleId="a" /></td>
					</tr>
					<tr style="height:22px">
						<td colspan="2" align="right">
							获奖情况：</td>
							
							
						<td colspan="6" align="left"><html:textarea rows="5" name='rs' style="width:98%" property="hjqk" styleId="a" /></td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button type="button" class="button2"
						onclick="dataDoSaveApply1('/xgxt/pjpy_zzsf_rych_save.do','rychdm','rych')">
						提 交 申 请
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"  onclick="window.open('/xgxt/pjpy_zzsf_rychpsdjb.do?xh='+document.getElementById('xh').value+'&rychdm='+document.getElementById('rychdm').value)">
						打 印 报 表
					</button>
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("申请成功！");
    if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
    	window.dialogArguments.document.all("search_go").click();
    	Close();
    }
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("申请失败！");
    if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
    	window.dialogArguments.document.all("search_go").click();
    	Close();
    }
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="nocondi">
					<script>
    alert("您不符合申请条件,申请失败！");
    if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
    	window.dialogArguments.document.all("search_go").click();
    	Close();
    }
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	<div style="display:none;color: red;font-size: 14px;background-color: yellow" id="message" name="message"></div>	
	</body>
</html>
