<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	
	<script language="javascript">
		function yz(){
			var userType = val('userType');
			var xxsh = val("xxsh");
			var xysh = val("xysh");
			var isFdy = val('isFdy');
			
			if(("通过" == xysh ) && isFdy == "true"){
				alert("<bean:message key="lable.xsgzyxpzxy" />已审核，不能再修改审核结果!");
	          	return false;
			}
			if(("通过" == xxsh ) && (userType == "xy")){
				alert("学校已审核，不能再修改审核结果!");
	          	return false;
			}			
			//提交
			document.forms[0].action = "/xgxt/gzdxQgzx.do?method=jjfzShOne&type=save";
			document.forms[0].submit();
			
		}
		function toPrintOut(){//输出相应的打印页面
			var pkValue = val("pkValue");
			showOpenWindow( "/xgxt/gzdxQgzx.do?method=qgzxjjfzsqb&pkValue" + pkValue, 700,500);
		}
	</script>
<body>
	<div class="title">
		<div class="title_img" id="title_m"> 
			前所在位置：勤工助学 - 勤工助学评奖评优 - 积极分子审核	
		</div>
	</div>
		<html:form action="/gzdxQgzx.do" method="post">
			<input type="hidden" id="shjb" name="shjb" value="${shjb}" />
			<input type="hidden" id="isFdy" name="isFdy" value="${isFdy}" />			
			<input type="hidden" id="xysh" name="xysh" value="${rs.xysh}" />
			<input type="hidden" id="xxsh" name="xxsh" value="${rs.xxsh}" />
			<input type="hidden" id="userType" name="userType" value="${userType}" />
			<input type="hidden" id="userName" name="userName" value="${userName}" />
			<input type="hidden" id="pkValue" name="pkValue" value="${rs.pkValue}" />
			<logic:present name="result">
				<input type="hidden" id="message" value="${message}"/>
				<script>
					alert(document.getElementById('message').value);
				</script>
			</logic:present>
			<table class="tbstyle" width="100%">
				<tr>
					<td align="center" width="16%">
							学号：
						</td>
						<td align="left" width="34%">
							${rs.xh}						
						</td>
					<td width="16%">
						<div align="center">
							姓名：
						</div>
					</td>
					<td width="34%">
						${rs.xm}
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							性别：
						</div>
					</td>
					<td>
						${rs.xb}
					</td>
					<td>
						<div align="center">
							身份证号：
						</div>
					</td>
					<td>
						${rs.sfzh}
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							民族：
						</div>
					</td>
					<td>
						${rs.mzmc}
					</td>
					<td>
						<div align="center">
							政治面貌：
						</div>
					</td>
					<td>
						${rs.zzmmmc}
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							年级：
						</div>
					</td>
					<td>
						${rs.nj}
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />名称：
						</div>
					</td>
					<td>
						${rs.xymc}
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							专业名称：
						</div>
					</td>
					<td>
						${rs.zymc}
					</td>
					<td>
						<div align="center">
							班级名称：
						</div>
					</td>
					<td>
						${rs.bjmc}
					</td>
				</tr>
				<tr>
					<!-- 广州大学 -->
					<logic:equal name="xxdm" value="11078">
					<td >
						<div align="center">
							学年：
						</div>
					</td>
					<td>
						${rs.xn}
					</td>
					</logic:equal>
					<!-- 非广州大学 -->
					<logic:notEqual name="xxdm" value="11078">
					<td >
						<div align="center">
							学年：
						</div>
					</td>
					<td colspan="3">
						${rs.xn}
					</td>
					</logic:notEqual>
					
					<logic:equal name="xxdm" value="11078">
					<td>
						<div align="center">
							综合测评排名：
						</div>
					</td>
					<td colspan="3">
						${zcpm }
					</td>
					</logic:equal>				
				</tr>
				<tr>
					<!-- 广州大学 -->
					<logic:equal name="xxdm" value="11078">
					<td>
						<div align="center">
							年度：
						</div>
					</td>
					<td>
						${rs.nd}
					</td>
					</logic:equal>
					<!-- 非广州大学 -->
					<logic:notEqual name="xxdm" value="11078">
					<td>
						<div align="center">
							年度：
						</div>
					</td>
					<td colspan="3">
						${rs.nd}
					</td>	
					</logic:notEqual>
					<logic:equal name="xxdm" value="11078">
					<td>
						<div align="center">
							挂科：
						</div>
					</td>
					<td colspan="3">
						${bjgkms }科
					</td>
					</logic:equal>								
				</tr>				
				<tr>
					<logic:equal name="xxdm" value="11078">
					<td>
						<div align="center">
							学期：
						</div>
					</td>
					<td >
						${rs.xqmc}
					</td>	
					</logic:equal>
					<logic:notEqual name="xxdm" value="11078">
					<td>
						<div align="center">
							学期：
						</div>
					</td>
					<td colspan="3">
						${rs.xqmc}
					</td>	
					</logic:notEqual>
					<logic:equal name="xxdm" value="11078">
					<td>
						<div align="center">
							处分次数：
						</div>
					</td>
					<td colspan="3">
						${wjcs }次
					</td>
					</logic:equal>				
				</tr>	
				<logic:equal name="xxdm" value="11078">						
				<tr>
					<td>
						<div align="center">
							勤工助学岗位：
						</div>
					</td>
					<td colspan="3">
							${qgzxgw}
					</td>
				</tr>
				</logic:equal>
				<tr>
					<td>
						<div align="center">
							辅导员审核：
						</div>
					</td>
					<td colspan="3">
						<!--辅导员审核-->
						<logic:equal value="true" name="fdyQx">
						<html:select property="save_fdysh" name="rs">
							<html:option value=""></html:option>
							<html:options collection="chkList" property="en" labelProperty="cn"/>
						</html:select>
						<input type="hidden" id="shsj" name="save_fdyshsj" value="${sj}"/>
						</logic:equal>
						<!--end辅导员审核-->
						<logic:notEqual value="true" name="fdyQx">
							${rs.save_fdysh}
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							辅导员审核意见：
						</div>
					</td>
					<td colspan="3">
						<!--辅导员审核-->
						<logic:equal value="true" name="fdyQx">
						<html:textarea property="save_fdyshyj" onblur="chLeng(this,50)" name="rs" style="width:100%"></html:textarea>
						</logic:equal>
						<!--end辅导员审核-->
						<logic:notEqual value="true" name="fdyQx">
							${rs.save_fdyshyj}
						</logic:notEqual>
					</td>
				</tr>

				<tr>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />审核：
						</div>
					</td>
					<td colspan="3">
						<logic:notEqual value="true" name="fdyQx">
						<logic:equal value="xy" name="userType">
							<html:select property="save_xysh" name="rs">
							<html:option value=""></html:option>
							<html:options collection="chkList" property="en" labelProperty="cn"/>
						</html:select>
						<input type="hidden" id="shsj" name="save_xyshsj" value="${sj}"/>
						</logic:equal>
						</logic:notEqual>
						
						<logic:equal value="true" name="fdyQx">
						${rs.save_xysh}
						</logic:equal>

						<logic:notEqual value="true" name="fdyQx">
							<logic:notEqual value="xy" name="userType">
								${rs.save_xysh}
							</logic:notEqual>
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />审核意见：
						</div>
					</td>
					<td colspan="3">
						<logic:notEqual value="true" name="fdyQx">
						<logic:equal value="xy" name="userType">
							<html:textarea property="save_xyshyj" onblur="chLeng(this,50)" name="rs" style="width:100%"></html:textarea>
						</logic:equal>
						</logic:notEqual>
						
						<logic:equal value="true" name="fdyQx">
						${rs.save_xyshyj}
						</logic:equal>

						<logic:notEqual value="true" name="fdyQx">
							<logic:notEqual value="xy" name="userType">
								${rs.save_xyshyj}
							</logic:notEqual>
						</logic:notEqual>
					</td>
				</tr>

				<tr>
					<td>
						<div align="center">
							学校审核：
						</div>
					</td>
					<td colspan="3">
						<logic:notEqual value="true" name="fdyQx">
						<logic:notEqual value="xy" name="userType">
							<html:select property="save_xxsh" name="rs">
							<html:option value=""></html:option>
							<html:options collection="chkList" property="en" labelProperty="cn"/>
						</html:select>
						<input type="hidden" id="shsj" name="save_xxshsj" value="${sj}"/>
						</logic:notEqual>
						</logic:notEqual>
						
						<logic:equal value="true" name="fdyQx">
						${rs.save_xxsh}
						</logic:equal>

						<logic:notEqual value="true" name="fdyQx">
							<logic:equal value="xy" name="userType">
								${rs.save_xxsh}
							</logic:equal>
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							学校审核意见：
						</div>
					</td>
					<td colspan="3">
						<logic:notEqual value="true" name="fdyQx">
						<logic:notEqual value="xy" name="userType">
							<html:textarea property="save_xxshyj" onblur="chLeng(this,50)" name="rs" style="width:100%"></html:textarea>
						</logic:notEqual>
						</logic:notEqual>
						
						<logic:equal value="true" name="fdyQx">
						${rs.save_xxshyj}
						</logic:equal>

						<logic:notEqual value="true" name="fdyQx">
							<logic:equal value="xy" name="userType">
								${rs.save_xxshyj}
							</logic:equal>
						</logic:notEqual>
					</td>
				</tr>	
			</table>
			<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<logic:notEqual value="view" name="type">
				<button type="button" class="button2" id="buttonSave"
					onClick="yz();">
					保&nbsp;&nbsp;&nbsp;&nbsp;存
				</button>
				&nbsp;&nbsp;
				</logic:notEqual>
				<button type="button" class="button2"
					onClick="Close()">
					关&nbsp;&nbsp;&nbsp;&nbsp;闭
				</button>
			</div>
		</html:form>
	</body>	
