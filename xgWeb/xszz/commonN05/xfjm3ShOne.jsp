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
<html:html>
<base target="_self" />
<head>


	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/xszzCommonN05DWR.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	
	<script language="javascript">
		function yz(){
			var userType = val('userType');
			var xxsh = val("xxsh");
			var xysh = val("xysh");
			var shjb = val('shjb');
			var isFdy = val('isFdy');
			
			if(("未审核" != xxsh ) && (userType == "xy")){
				alert("学校已审核，不能再修改审核结果!");
	          	return false;
			}
			if(("未审核" != xysh ) && (userType == "fdy") && shjb == "3"){
				alert("<bean:message key="lable.xsgzyxpzxy" />已审核，不能再修改审核结果!");
	          	return false;
			}
			//提交
			document.forms[0].action = "/xgxt/n05_xszz.do?method=xfjm3ShOne&type=save";
			document.forms[0].submit();
			
		}
		function toPrintOut(){//输出相应的打印页面
			var pkValue = val('pkValue');
			showOpenWindow("/xgxt/n05_xszz.do?method=xfjm3sqb&pkValue=" + pkValue,700,500);
		}
		
		function operJmxx(){
			if(ele('save_xxsh')){
				var xxsh = val('save_xxsh');
				if(xxsh == "通过"){
					ele('spdc').disabled = false;
					ele('jmje').disabled = false;
				}else{
					ele('spdc').disabled = true;
					ele('jmje').disabled = true;
					setVal('spdc','');
					setVal('jmje','');
				}	
			}	
		}
	</script>
</head>

<body onload="operJmxx();">
	<div class="title">
		<div class="title_img" id="title_m"> 
			前所在位置：学生资助 - 学费减免
		</div>
	</div>
		<html:form action="n05_xszz.do" method="post">
			<input type="hidden" id="shjb" name="shjb" value="${shjb}" />
			<input type="hidden" id="isFdy" name="isFdy" value="${isFdy}" />
			<input type="hidden" id="xysh" name="xysh" value="${rs.xysh}" />
			<input type="hidden" id="xxsh" name="xxsh" value="${rs.xxsh}" />
			<input type="hidden" id="userType" name="userType" value="${userType}" />
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
							<font color="red">*</font>学号：
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
					<td>
						<div align="center">
							学年：
						</div>
					</td>
					<td>
						${rs.xn}
					</td>	
					<td>
						<div align="center">
							申请时间：
						</div>
					</td>
					<td>
						${rs.sqsj}
					</td>				
				</tr>
				<tr>
					<td>
						<div align="center">
							申请学费减免档次：
						</div>
					</td>
					<td>
						${rs.sqdc}
					</td>
					<td>
						<div align="center">
							品德等第：
						</div>
					</td>
					<td>
						${rs.pddd}
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							参加勤工助学时间：
						</div>
					</td>
					<td>
						${rs.save_cjqgzxsj}
					</td>
					<td>
						<div align="center">
							申请助学贷款时间：
						</div>
					</td>
					<td>
						${rs.save_sqzxdksj}
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap">
						<div align="center">
							何学年获何种资助：
							<br/>（写明受助金额）
						</div>
					</td>
					<td colspan="3">
						${rs.szqk}
					</td>					
				</tr>
				<tr>
					<td>
						<div align="center">
							家庭经济困难证明：
						</div>
					</td>
					<td colspan="3">
						${rs.gczm}
						${rs.ssmzzm}
						${rs.lsznzm}
						${rs.yfjtzm}
						${rs.qtzm}						
					</td>					
				</tr>
				<tr>
					<td>
						<div align="center">
							申请理由：
						</div>
					</td>
					<td colspan="3">
						${rs.sqyy}
					</td>					
				</tr>
				<tr>
					<td>
						<div align="center">
							入档编号：
						</div>
					</td>
					<td colspan="3">
						<html:text property="save_rdbh" name="rs" maxlength="30" style="width:100%"></html:text>
					</td>					
				</tr>
				
				<logic:equal value="3" name="shjb">					
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
							<html:textarea property="save_fdyshyj" onblur="chLeng(this,50)" name="rs"></html:textarea>
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
								班级评议：
							</div>
						</td>
						<td colspan="3">
							<!--辅导员审核-->
							<logic:equal value="true" name="fdyQx">
							<html:textarea property="save_bjpy" onblur="chLeng(this,300)" name="rs"></html:textarea>
							</logic:equal>
							<!--end辅导员审核-->
							<logic:notEqual value="true" name="fdyQx">
								${rs.save_bjpy}
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
								公示结果：
							</div>
						</td>
						<td colspan="3">
							<logic:notEqual value="true" name="fdyQx">
							<logic:equal value="xy" name="userType">
								<html:textarea property="save_xygsjg" onblur="chLeng(this,300)" name="rs" style="width:100%"></html:textarea>
							</logic:equal>
							</logic:notEqual>
							
							<logic:equal value="true" name="fdyQx">
							${rs.save_xygsjg}
							</logic:equal>

							<logic:notEqual value="true" name="fdyQx">
								<logic:notEqual value="xy" name="userType">
									${rs.save_xygsjg}
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
								<html:select property="save_xxsh" name="rs" styleId="save_xxsh" onchange="operJmxx()">
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
						<tr>
						<td>
							<div align="center">
								学费减免档次：
							</div>
						</td>
						<td>
							<logic:notEqual value="true" name="fdyQx">
							<logic:notEqual value="xy" name="userType">
								<html:select property="save_spdc" name="rs">
									<html:option value=""></html:option>
									<html:option value="全免">全免</html:option>
									<html:option value="半免">半免</html:option>
									<html:option value="部分减免">部分减免</html:option>
								</html:select>								
							</logic:notEqual>
							</logic:notEqual>
							
							<logic:equal value="true" name="fdyQx">
							${rs.save_spdc}
							</logic:equal>

							<logic:notEqual value="true" name="fdyQx">
								<logic:equal value="xy" name="userType">
									${rs.save_spdc}
								</logic:equal>
							</logic:notEqual>
						</td>
						<td>
							<div align="center">
								学费减免金额：
							</div>
						</td>
						<td>
							<logic:notEqual value="true" name="fdyQx">
							<logic:notEqual value="xy" name="userType">
								<html:text property="save_jmje" name="rs" styleId="jmje" maxlength="10" onkeyup="value=value.replace(/[^\d|.]/g,'') "></html:text>
							</logic:notEqual>
							</logic:notEqual>
							
							<logic:equal value="true" name="fdyQx">
							${rs.save_jmje}
							</logic:equal>

							<logic:notEqual value="true" name="fdyQx">
								<logic:equal value="xy" name="userType">
									${rs.save_jmje}
								</logic:equal>
							</logic:notEqual>
						</td>
						</tr>
				</logic:equal>
				
				<logic:notEqual value="3" name="shjb">	
						<tr>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />审核：
							</div>
						</td>
						<td colspan="3">
							<logic:equal value="xy" name="userType">
								<html:select property="save_xysh" name="rs" onchange="operJmxx();">
								<html:option value=""></html:option>
								<html:options collection="chkList" property="en" labelProperty="cn"/>
								</html:select>
							<input type="hidden" id="shsj" name="save_xyshsj" value="${sj}"/>
							</logic:equal>
							<logic:notEqual value="xy" name="userType">
								${rs.save_xysh}
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
							<logic:equal value="xy" name="userType">
								<html:textarea property="save_xyshyj" onblur="chLeng(this,50)" name="rs" style="width:100%"></html:textarea>
							</logic:equal>
							<logic:notEqual value="xy" name="userType">
								${rs.save_xyshyj}
							</logic:notEqual>
						</td>
						</tr>
						
						<tr>
						<td>
							<div align="center">
								公示结果：
							</div>
						</td>
						<td colspan="3">
							<logic:equal value="xy" name="userType">
								<html:textarea property="save_xygsjg" onblur="chLeng(this,300)" name="rs" style="width:100%"></html:textarea>
							</logic:equal>
							<logic:notEqual value="xy" name="userType">
								${rs.save_xygsjg}
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
							<logic:notEqual value="xy" name="userType">
								<html:select property="save_xxsh" name="rs" onchange="operJmxx()" styleId="save_xxsh">
								<html:option value=""></html:option>
								<html:options collection="chkList" property="en" labelProperty="cn"/>
							</html:select>
							<input type="hidden" id="shsj" name="save_xxshsj" value="${sj}"/>
							</logic:notEqual>
							<logic:equal value="xy" name="userType">
								${rs.save_xxsh}
							</logic:equal>
						</td>
						</tr>
						<tr>
						<td nowrap="nowrap">
							<div align="center">
								学校审核意见：
							</div>
						</td>
						<td colspan="3">
							<logic:notEqual value="xy" name="userType">
								<html:textarea property="save_xxshyj" onblur="chLeng(this,50)" name="rs" style="width:100%"></html:textarea>
							</logic:notEqual>
							<logic:equal value="xy" name="userType">
								${rs.save_xxshyj}
							</logic:equal>
						</td>
						</tr>
						<tr>
						<td>
							<div align="center">
								学费减免档次：
							</div>
						</td>
						<td>
							<logic:notEqual value="xy" name="userType">
								<html:select property="save_spdc" name="rs" styleId="spdc">
									<html:option value=""></html:option>
									<html:option value="全免">全免</html:option>
									<html:option value="半免">半免</html:option>
									<html:option value="部分减免">部分减免</html:option>
								</html:select>								
							</logic:notEqual>
							<logic:equal value="xy" name="userType">
								${rs.save_spdc}
							</logic:equal>
						</td>
						<td>
							<div align="center">
								学费减免金额：
							</div>
						</td>
						<td>
							<logic:notEqual value="xy" name="userType">
								<html:text property="save_jmje" name="rs" styleId="jmje" maxlength="10" onkeyup="value=value.replace(/[^\d|.]/g,'') "></html:text>
							</logic:notEqual>
							<logic:equal value="xy" name="userType">
								${rs.save_jmje}
							</logic:equal>
						</td>
						</tr>
					</logic:notEqual>	
			</table>
			<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<button class="button2" id="buttonSave"
					onClick="yz();">
					保&nbsp;&nbsp;&nbsp;&nbsp;存
				</button>
				&nbsp;&nbsp;
				<button class="button2"
					onClick="toPrintOut()">
					打&nbsp;&nbsp;&nbsp;&nbsp;印
				</button>
			</div>
		</html:form>
	</body>	
</html:html>
