<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->	
	<%@ include file="/syscommon/pagehead_V4.ini"%>	
	<script type="text/javascript" src="js/String.js"></script>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getXjydInfo.js'></script>
	<script type="text/javascript">
		function Close() {
			var ua = navigator.userAgent;
			var ie = navigator.appName == "Microsoft Internet Explorer" ? true : false;
			if (ie) {
				var IEversion = parseFloat(ua.substring(ua.indexOf("MSIE ") + 5, ua.indexOf(";", ua.indexOf("MSIE "))));
				if (IEversion < 5.5) {
					var str = "<object id=noTipClose classid=\"clsid:ADB880A6-D8FF-11CF-9377-00AA003B7A11\">";
					str += "<param name=\"Command\" value=\"Close\"></object>";
					document.body.insertAdjacentHTML("beforeEnd", str);
					document.all.noTipClose.Click();
				} else {
					window.opener = null;
					window.close();
				}
			} else {
				window.close();
			}
		}
		
		function send(){	
			stuinfoSave("stu_info_add.do?method=stuInfoSave&oper=");	
		}
	</script>
</head>
	<body>		
		<html:form action="/stu_info_add" method="post">
		
		<div class="title">
			<div class="title_img" id="title_m">
				当前位置：个人信息-学生信息维护
			</div>
		</div>
			
			
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="5" align="center">
							<center>
								学生个人信息
							</center>
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right" width="15%">
						<font color="red">*</font>学号：
					</td>
					<td>
						<logic:equal value="update" name="oper">
							<html:text name="rs" styleId="xh" property="xh" readonly="true"
								style="cursor:hand" />
						</logic:equal>
						<logic:equal value="add" name="oper">
							<html:text name="rs" property="xh" styleId="xh" 
							onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="20"/>
						</logic:equal>
					</td>
					<td align="right" width="15%">
						<font color="red">*</font>年级：
					</td>
					<td align="left" width="30%">
						<html:select name="rs" property="nj" styleId="nj"
							style="width:90px"
							onchange="initZyList();initBjList();">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj"
								labelProperty="nj" />
						</html:select>
						<br />
					</td>
					<logic:equal value="浙江商业职业技术<bean:message key="lable.xsgzyxpzxy" />" name="xxmc" scope="session">
						<td align="left" width="15%" rowspan="6">
							<img border="0" style="height:133px;width:100px;"
								src="/xgxt/pictures/<bean:write name="rs" property="sfzh" />.jpg">
						</td>
					</logic:equal>
					<logic:notEqual value="浙江商业职业技术<bean:message key="lable.xsgzyxpzxy" />" name="xxmc" scope="session">
						<td align="left" width="15%" rowspan="6">
							<img border="0" style="height:133px;width:100px;"
								src="/xgxt/pictures/<bean:write name="rs" property="xh" />.jpg">
						</td>
					</logic:notEqual>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>姓名：
					</td>
					<td align="left">
						<html:text name="rs" property="xm" styleId="xm" maxlength="16"/>
						<br />
					</td>
					<td align="right">
						<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						<html:select name="rs" property="xydm" styleId="xy"
							style="width:180px"
							onchange="initZyList();initBjList()">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
						<input type="hidden" name="xyV" value="<bean:write name="xydm" scope="request"/>"/>
						<br />
					</td>
				</tr>
				<tr>
					<td align="right">
						性别：
					</td>
					<td align="left">
						<html:radio name="rs" property="xb" value="1">男</html:radio>
						<html:radio name="rs" property="xb" value="2">女</html:radio>
						<br />
					</td>
					<td align="right">
						<font color="red">*</font>专业：</td>
					<td align="left">
						<html:select name="rs" property="zydm" style="width:180px"
							styleId="zy"
							onchange="initBjList();">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm"
								labelProperty="zymc" />
						</html:select>
						<input type="hidden" name="zyV" value="<bean:write name="zydm" scope="request"/>"/>
						<br />
					</td>
				</tr>
				<tr>
					<td align="right">
						民族：
					</td>
					<td align="left">
						<html:select name="rs" property="mz" styleId="mz"
							style="width:150px">
							<html:options collection="mzList" property="mzdm"
								labelProperty="mzmc" />
						</html:select>
						<br />
					</td>
					<td align="right">
						<font color="red">*</font>班级：</td>
					<td align="left">
						<html:select name="rs" property="bjdm" style="width:180px"
							styleId="bj">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>
						<input type="hidden" name="bjV" value="<bean:write name="bjdm" scope="request"/>"/>
						<br />
					</td>
				</tr>
				<tr>
					<td align="right">
						政治面貌：
					</td>
					<td align="left">
						<html:select name="rs" property="zzmm" styleId="mz"
							style="width:150px">
							<html:options collection="zzmmList" property="zzmmdm"
								labelProperty="zzmmmc" />
						</html:select>
						<br />
					</td>
					<td align="right">
						学籍状态：</td>
					<td align="left">
						<html:select name="rs" property="xjzt" style="width:150">	
						<html:option value=""></html:option>
						<html:options collection="xjztList" property="zxdmmc" labelProperty="zxdmmc"/>
<!--							<html:option value="有学籍">有学籍</html:option>-->
<!--							<html:option value="无学籍">无学籍</html:option>-->
						</html:select>
						<br />
					</td>

				</tr>
				<tr>
					<td align="right">
						姓名拼音：
					</td>
					<td align="left">
						<html:text name="rs" property="xmpy" maxlength="64"/>
						<br />
					</td>
					<td align="right">
						考生号：</td>
					<td align="left">
					  <html:text name="rs" property="ksh" />
					   <br />
				     </td>
				</tr>
				<tr>
					<td align="right">
						曾用名：
					</td>
					<td align="left">
						<html:text name="rs" property="cym" maxlength="16"/>
						<br />
					</td>
					<td align="right">
						招生季节：</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="zsjj" />
						<br />				  </td>

				</tr>
				<tr>
					<td align="right">
						出生日期：
					</td>
					<td align="left">
						<html:text name="rs" property="csrq"
							onclick="return showCalendar('csrq','y-mm-dd');" styleId="csrq" readonly="true"/>
						<br />
					</td>
					<td align="right">
						当前所在级：</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="dqszj" maxlength="32"/>
						<br />
					</td>
				</tr>
				<tr>
					<td align="right">
						培养方式：
					</td>
					<td align="left">
						<html:text name="rs" property="pyfs"/>
						<br />
					</td>
					<td align="right">
						培养层次：</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="pycc"/>
						<br />
					</td>
				</tr>
				<tr>
				  <td align="right">培养方向：</td>
				  <td align="left"><html:text name="rs" property="pyfx"/></td>
				  <td align="right">出生地：</td>
				  <td align="left" colspan="2"><html:text name="rs" property="csd"/></td>
			  </tr>
				<tr>
					<td align="right">
						入学方式：
					</td>
					<td align="left">
						<html:text name="rs" property="rxfs" maxlength="32"/>
						<br />
					</td>
					<td align="right">
						考生类别：
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="kslb" maxlength="32"/>
						<br />
					</td>
				</tr>
				<tr>
					<td align="right">
						身份证号：
					</td>
					<td align="left">
						<html:text name="rs" property="sfzh" styleId="sfzh"/>
						<br />
					</td>
					<td align="right">
						电子邮箱：				  </td>
					<td align="left" colspan="2">
						<html:text name="rs" property="dzyx" styleId="dzyx"/>
						<br />
					</td>
				</tr>
				<tr>
				
					<td align="right">
						来源地区：
					</td>
					<td align="left">
						<html:text name="rs" property="syd" maxlength="25"/>
						<br />
					</td>
					<td align="right">
						联系电话：
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="lxdh" maxlength="13" onkeyup="value=value.replace(/[^\d]/g,'') "/>
						<br />
					</td>			
				</tr>
				<tr>				
				
					<td align="right">
						籍贯：
					</td>
					<td align="left">
						<html:text name="rs" property="jg" maxlength="10"/>
						<br />
					</td>
					<td align="right">
						手机号码：
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="sjhm" maxlength="11"
						onkeyup="value=value.replace(/[^\d]/g,'') "/>
						<br />
					</td>
				
				</tr>
				<tr>
					<td align="right">
						学制：
					</td>
					<td >
						<html:text name="rs" property="xz" onkeyup="value=value.replace(/[^\d]/g,'') "
						maxlength="1"/>年						
					</td>
						<td><div align="right">专业方向：								 
						</div></td>
						<td colspan="2">
						<html:text name="rs" property="zyfx"/>
						</td>	
				</tr>	
				<tr>
				  <td align="right">入学日期：</td>
				  <td ><html:text name="rs" property="rxrq" styleId="rxrq" 
				  onclick="return showCalendar('rxrq','y-mm-dd');" readonly="true"/></td>
				  <td><div align="right">专业类别：</div></td>
				  <td colspan="2"><html:text name="rs" property="zylb"/></td>
			  </tr>
				<tr>
				  <td align="right">辅修专业：</td>
				  <td ><html:text name="rs" property="fxzy"/></td>
				  <td><div align="right">辅修专业方向：</div></td>
				  <td colspan="2"><html:text name="rs" property="fxzyfx"/></td>
			  </tr>
				<tr>
				  <td align="right">办学形式：</td>
				  <td ><html:text name="rs" property="bxxs"/></td>
				  <td><div align="right">办学类型：</div></td>
				  <td colspan="2"><html:text name="rs" property="bxlx"/></td>
			  </tr>
				<tr>
				  <td align="right">学习形式：</td>
				  <td ><html:text name="rs" property="xxxs"/></td>
				  <td><div align="right">毕业日期：</div></td>
				  <td colspan="2"><html:text name="rs" property="byny" styleId="byrq" 
				  onclick="return showCalendar('byrq','y-mm-dd');" readonly="true"/></td>
			  </tr>
				<tr>
				  <td align="right">证书编号：</td>
				  <td ><html:text name="rs" property="zsbh"/></td>
				  <td><div align="right">证书序列号：</div></td>
				  <td colspan="2"><html:text name="rs" property="zsxlh"/></td>
			  </tr>
				<tr>
				  <td align="right">学位：</td>
				  <td ><html:text name="rs" property="xw"/></td>
				  <td><div align="right">学位证书编号：</div></td>
				  <td colspan="2"><html:text name="rs" property="xwzsbh"/></td>
			  </tr>
				<tr>
				  <td align="right">学位证书序列号：</td>
				  <td ><html:text name="rs" property="xwzsxlh"/></td>
				  <td><div align="right">毕结业结论：</div></td>
				  <td colspan="2"><html:text name="rs" property="bjyjl"/></td>
			  </tr>
				<tr>
				  <td align="right">学校地址：</td>
				  <td ><html:text name="rs" property="xxszd"/></td>
				  <td><div align="right">校长姓名：</div></td>
				  <td colspan="2"><html:text name="rs" property="xzxm"/></td>
			  </tr>
				<tr>
				  <td align="right">审核标记：</td>
				  <td ><html:text name="rs" property="shbj"/></td>
				  <td><div align="right">打印标记：</div></td>
				  <td colspan="2"><html:text name="rs" property="dybj"/></td>
			  </tr>
				<tr>
					<td align="right">替换标识：</td>
					<td ><html:text name="rs" property="thbs"/></td>
						<td>&nbsp;</td>
						<td colspan="2">&nbsp;</td>	
				</tr>							
				<tr height="80">
					<td align="right">
						备注：
					</td>
					<td align="left" colspan="4">
						<html:textarea name="rs" property="bz"
							style="width:100%;height:100%"></html:textarea>
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn" width="100%">
			<logic:notPresent name="details">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" style="height:20px;width:80px"
					onclick="send();">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
			</logic:notPresent>
				<button class="button2" style="height:20px;width:80px"
					onclick="Close();return false;">
					关 闭
				</button>
			</div>
			<logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
						alert("操作成功！");
						Close();
						window.dialogArguments.document.getElementById('search_go').click();						
					</script>
				</logic:equal>
				<logic:equal name="result" value="false">
					<script>
						alert("操作失败!");
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
