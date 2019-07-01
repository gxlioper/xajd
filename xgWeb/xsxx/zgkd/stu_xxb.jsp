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
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<style media='print'>
		.noPrin{
			display:none;
		}
	</style>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	
	<script language="javascript" src="js/function.js"></script>	
	<script language="javascript" src="js/studetailFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<body>		
		<table width="100%" align="center" class="tbstyle" id="rsTab">
			<tr>
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main1" style="color:blue;cursor:hand" 
									onclick="document.all.child1.style.display=(document.all.child1.style.display =='none')?'':'none'">
									<div align="center" class="style1 style3">
										<strong>学生基本信息</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div id="child1">
						<table width="100%" align="center" class="tbstyle">
							<tr>
								<td align="right" width="15%">
									学号：
								</td>
								<td align="left" width="25%">
									<bean:write name="rs" property="xh" />
								</td>
								<td align="right" width="15%">
									年级：
								</td>
								<td align="left" width="30%">
									<bean:write name="rs" property="nj" />
								</td>
								<td align="left" width="15%" rowspan="6">
									<img border="0" style="height:133px;width:100px;"
										src="/xgxt/pictures/<bean:write name="rs" property="xh" />.jpg">
								</td>
							</tr>
							<tr>
								<td align="right">
									姓名：
								</td>
								<td align="left">
									<bean:write name="rs" property="xm" />
								</td>
								<td align="right">
									<bean:message key="lable.xsgzyxpzxy" />：
								</td>
								<td align="left">
									<bean:write name="rs" property="xymc" />
								</td>
							</tr>
							<tr>
								<td align="right">
									性别：
								</td>
								<td align="left">
									<bean:write name="rs" property="xb" />
								</td>
								<td align="right">
									专业：
								</td>
								<td align="left">
									<bean:write name="rs" property="zymc" />
								</td>
							</tr>

							<tr>
								<td align="right">
									出生日期：
								</td>
								<td align="left">
									<bean:write name="rs" property="csrq" />
								</td>
								<td align="right">
									班级：
								</td>
								<td align="left">
									<bean:write name="rs" property="bjmc" />
								</td>
							</tr>
							<tr>
								<td align="right">
									民族：
								</td>
								<td align="left">
									<bean:write name="rs" property="mzmc" />
								</td>
								<td align="right">
									学制：
								</td>
								<td align="left">
									<bean:write name="rs" property="xz" />
								</td>
							</tr>
							<tr>
								<td align="right">
									政治面貌：
								</td>
								<td align="left">
									<bean:write name="rs" property="zzmmmc" />
								</td>
								<td align="right">
									学籍状态：
								</td>
								<td align="left">
									<bean:write name="rs" property="xjztm" />
								</td>
							</tr>
							<tr>
								<td align="right">
									身份证号：
								</td>
								<td align="left">
									<bean:write name="rs" property="sfzh" />
								</td>
								<td align="right">
									电子邮箱：
								</td>
								<td align="left" colspan="2">
									<bean:write name="rs" property="lxdzxx" />
								</td>
							</tr>
							<tr>
								<td align="right">
									来源地区：
								</td>
								<td align="left">
									<bean:write name="rs" property="lydq" />
								</td>
								<td align="right">
									联系电话：
								</td>
								<td align="left" colspan="2">
									<bean:write name="rs" property="lxdh" />
								</td>
							</tr>
							<tr>
								<td align="right">
									籍贯：
								</td>
								<td align="left">
									<bean:write name="rs" property="jg" />
								</td>
								<td align="right">
									手机号码：
								</td>
								<td align="left" colspan="2">
									<bean:write name="rs" property="sjhm" />
								</td>
							</tr>
							<tr>
								<td align="right">
									宿舍号：
								</td>
								<td align="left">
									<bean:write name="rs" property="ssbh" />
								</td>
								<td align="right">
									宿舍电话：
								</td>
								<td align="left" colspan="2">
									<bean:write name="rs" property="qsdh" />
								</td>
							</tr>
							<logic:present name="showksh">
								<tr>
									<td align="right">
										考生号：
									</td>
									<td align="left">
										<bean:write name="rs" property="ksh" />
									</td>
									<td align="right">
										&nbsp;
									</td>
									<td align="left" colspan="2">
										&nbsp;
									</td>
								</tr>
							</logic:present>

						</table>
					</div>
				</td>
			</tr>
			
			<logic:present name="jtxx">
			<tr id="divJtxx" >
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main11" style="color:blue;cursor:hand" onclick="document.all.child11.style.display=(document.all.child11.style.display =='none')?'':'none';">
									<div align="center" class="style1 style3">
										<strong>家庭信息</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF">
					<div id="child11">
						<table width="100%" class="tbstyle">
							<thead>
								<tr>
									<td colspan="8">
										<div align="center" class="style2">
											家庭信息记录
										</div>
									</td>
								</tr>								
							</thead>
							<tr>
							<td align="right" width="20%">
								家庭地址：
							</td>
							<td>
								${jtxxMap.jtdz}
							</td>
							<td align="right" width="20%">
								家庭邮编：
							</td>
							<td>
								${jtxxMap.jtyb}
							</td>
							</tr>
							
							<tr>
							<td align="right">
								家庭经济情况：
							</td>
							<td colspan="3">
								${jtxxMap.jtjjqk}
							</td>
							</tr>
							
							<tr>
								<td colspan="4">
								<fieldset>
									<legend>
										家庭成员1
									</legend>
									<table class="tbstyle" width="100%">
										<tr>
											<td align="right" width="20%">
												姓名：
											</td>
											<td>
												${jtxxMap.jtcy1_xm}
											</td>
											<td align="right" width="20%">
												与本人关系：
											</td>
											<td>
												${jtxxMap.jtcy1_gx}
											</td>
										</tr>
										<tr>
											<td align="right">
												出生日期：
											</td>
											<td>
												${jtxxMap.jtcy1_csrq}
											</td>
											<td align="right">
												身份证号：
											</td>
											<td>
												${jtxxMap.jtcy1_sfzh}
											</td>
										</tr>
										<tr>
											<td align="right">
												民族：
											</td>
											<td>
												${jtxxMap.jtcy1_mzmc}
											</td>
											<td align="right">
												政治面貌：
											</td>
											<td>
												${jtxxMap.jtcy1_zzmmmc}
											</td>
										</tr>
										<tr>
											<td align="right">
												职业：
											</td>
											<td>
												${jtxxMap.jtcy1_zy}
											</td>
											<td align="right">
												职务：
											</td>
											<td>
												${jtxxMap.jtcy1_zw}
											</td>
										</tr>
										
										<tr>
											<td align="right">
												工作单位电话：
											</td>
											<td>
												${jtxxMap.jtcy1_lxdh1}
											</td>
											<td align="right">
												个人电话：
											</td>
											<td>
												${jtxxMap.jtcy1_lxdh2}
											</td>
										</tr>
										
										<tr>
											<td align="right">
												工作地址：
											</td>
											<td colspan="3">
												${jtxxMap.jtcy1_gzdz}
											</td>											
										</tr>
									</table>
								</fieldset>
								</td>
							</tr>
							
							<tr>
								<td colspan="4">
								<fieldset>
									<legend>
										家庭成员2
									</legend>
									<table class="tbstyle" width="100%">
										<tr>
											<td align="right" width="20%">
												姓名：
											</td>
											<td>
												${jtxxMap.jtcy2_xm}
											</td>
											<td align="right" width="20%">
												与本人关系：
											</td>
											<td>
												${jtxxMap.jtcy2_gx}
											</td>
										</tr>
										<tr>
											<td align="right">
												出生日期：
											</td>
											<td>
												${jtxxMap.jtcy2_csrq}
											</td>
											<td align="right">
												身份证号：
											</td>
											<td>
												${jtxxMap.jtcy2_sfzh}
											</td>
										</tr>
										<tr>
											<td align="right">
												民族：
											</td>
											<td>
												${jtxxMap.jtcy2_mzmc}
											</td>
											<td align="right">
												政治面貌：
											</td>
											<td>
												${jtxxMap.jtcy2_zzmmmc}
											</td>
										</tr>
										<tr>
											<td align="right">
												职业：
											</td>
											<td>
												${jtxxMap.jtcy2_zy}
											</td>
											<td align="right">
												职务：
											</td>
											<td>
												${jtxxMap.jtcy2_zw}
											</td>
										</tr>
										
										<tr>
											<td align="right">
												工作单位电话：
											</td>
											<td>
												${jtxxMap.jtcy2_lxdh1}
											</td>
											<td align="right">
												个人电话：
											</td>
											<td>
												${jtxxMap.jtcy2_lxdh2}
											</td>
										</tr>
										
										<tr>
											<td align="right">
												工作地址：
											</td>
											<td colspan="3">
												${jtxxMap.jtcy2_gzdz}
											</td>											
										</tr>
									</table>
								</fieldset>
								</td>
							</tr>
							
							<tr>
								<td colspan="4" width="100%">
								<fieldset>
									<legend>
										家庭成员3
									</legend>
									<table class="tbstyle" style="width:100%">
										<tr>
											<td width="20%" align="right">
												姓名：
											</td>
											<td>
												${jtxxMap.jtcy3_xm}
											</td>
											<td width="20%" align="right">
												与本人关系：
											</td>
											<td>
												${jtxxMap.jtcy3_gx}
											</td>
										</tr>
										<tr>
											<td align="right">
												出生日期：
											</td>
											<td>
												${jtxxMap.jtcy3_csrq}
											</td>
											<td align="right">
												身份证号：
											</td>
											<td>
												${jtxxMap.jtcy3_sfzh}
											</td>
										</tr>
										<tr>
											<td align="right">
												民族：
											</td>
											<td>
												${jtxxMap.jtcy3_mzmc}
											</td>
											<td align="right">
												政治面貌：
											</td>
											<td>
												${jtxxMap.jtcy3_zzmmmc}
											</td>
										</tr>
										<tr>
											<td align="right">
												职业：
											</td>
											<td>
												${jtxxMap.jtcy3_zy}
											</td>
											<td align="right">
												职务：
											</td>
											<td>
												${jtxxMap.jtcy3_zw}
											</td>
										</tr>
										
										<tr>
											<td align="right">
												工作单位电话：
											</td>
											<td>
												${jtxxMap.jtcy3_lxdh1}
											</td>
											<td align="right">
												个人电话：
											</td>
											<td>
												${jtxxMap.jtcy3_lxdh2}
											</td>
										</tr>
										
										<tr>
											<td align="right">
												工作地址：
											</td>
											<td colspan="3">
												${jtxxMap.jtcy3_gzdz}
											</td>											
										</tr>
									</table>
								</fieldset>
								</td>
							</tr>
							
						</table>
					</div>
				</td>
			</tr>
			</logic:present>
			
			<logic:present name="dtjs">
			<tr id="divDtjs">
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main2" style="color:blue;cursor:hand"
									onclick="document.all.child2.style.display=(document.all.child2.style.display =='none')?'':'none';">
									<div align="center" class="style1 style3">
										<strong>党团建设</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			
			<tr>
				<td>
					<div id="child2">
						<logic:present name="dyxx">
						<table width="100%" align="center" class="tbstyle" id="tbDyxx">
							<thead>
								<tr>
									<td colspan="8">
										<div align="center" class="style2">
											党员信息
										</div>
									</td>
								</tr>
								<tr>
									<td>
										学号
									</td>
									<td>
										姓名
									</td>
									<td>
										申请入党时间
									</td>
									<td>
										积极分子时间
									</td>
									<td>
										发展对象时间
									</td>
									<td>
										预备党员时间
									</td>
									<td>
										转正时间
									</td>
									<td>
										目前状态
									</td>
								</tr>
								</thead>
							<logic:iterate id="dyxx" name="dyxxList">
							<tr>
								<logic:iterate id="v" name="dyxx">
								<td>${v}</td>
								</logic:iterate>
							</tr>
							</logic:iterate>							
						</table>
						</logic:present>
						
                        <logic:present name="ybdy">
						<table width="100%" align="center" class="tbstyle" id="tbYbdyjl">
							<thead>
								<tr>
									<td colspan="9">
										<div align="center" class="style2">
											预备党员记录
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
										学期
									</td>
									<td>
										学号
									</td>
									<td>
										姓名
									</td>
									<td>
										年级
									</td>
									<td>
										班级
									</td>
									<td>
										开始时间
									</td>
									<td>
										结束时间
									</td>
								</tr>
							</thead>
							<logic:iterate id="ybdy" name="ybdyList">
							<tr>
								<logic:iterate id="v" name="ybdy">
								<td>${v}</td>
								</logic:iterate>
							</tr>
							</logic:iterate>							
						</table>						
						</logic:present>
						
						<logic:present name="dyjl">
						<table width="100%" align="center" class="tbstyle" id="tbDyjl">
							<thead>
								<tr>
									<td colspan="8">
										<div align="center" class="style2">
											党员记录
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
										学期
									</td>
									<td>
										学号
									</td>
									<td>
										姓名
									</td>
									<td>
										年级
									</td>
									<td>
										班级
									</td>
									<td>
										入党时间
									</td>
								</tr>
							</thead>
							<logic:iterate id="dyjl" name="dyjlList">
							<tr>
								<logic:iterate id="v" name="dyjl">
								<td>${v}</td>
								</logic:iterate>
							</tr>
							</logic:iterate>
						</table>
						</logic:present>
						
					</div>
				</td>
			</tr>
			</logic:present>
			
			<logic:present name="pjpy" scope="request">
				<tr id="divPjpy">
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main3" style="color:blue;cursor:hand"
								onclick="document.all.child3.style.display=(document.all.child3.style.display =='none')?'':'none';">
									<div align="center" class="style1 style3">
										<strong>评奖评优</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div id="child3">
						<logic:present name="jxj">
						<table width="100%" align="center" class="tbstyle" id="tbJxjjl">
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
										奖学金金额
									</td>
									<td>
										<bean:message key="lable.xsgzyxpzxy" />审核
									</td>
									<td>
										学校审核
									</td>
								</tr>
							</thead>
							<logic:iterate id="jxj" name="jxjRs">
							<tr>
								<logic:iterate id="v" name="jxj">
								<td>${v}</td>
								</logic:iterate>
							</tr>
							</logic:iterate>						
						</table>
						</logic:present>
						
						<logic:present name="rych">
						<table width="100%" align="center" class="tbstyle" id="tbRychjl">
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
							<logic:iterate id="rych" name="rychList">
							<tr>
								<logic:iterate id="v" name="rych">
								<td>${v}</td>
								</logic:iterate>
							</tr>
							</logic:iterate>
						</table>		
						</logic:present>
								
						<logic:present name="zhszcp">
						<table width="100%" align="center" class="tbstyle" id="tbZhszcpjl">
							<thead>
								<tr>
									<td colspan="13">
										<div align="center" class="style2">
											综合素质测评记录
										</div>
									</td>
								</tr>

								<tr>									
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
										品德测评得分
									</td>
									<td>
										课程学习成绩测评得分
									</td>
									<td>
										排名
									</td>
									<td>
										身心测评得分
									</td>
									<td>
										基本素质测评得分
									</td>
									<td>
										排名
									</td>
									<td>
										基本素质测评等级
									</td>									
									<td>
										发展素质测评得分
									</td>
									<td>
										排名
									</td>
								</tr>
							</thead>							
							<logic:iterate id="zhszcp" name="zhszcpList">
							<tr>
								<logic:iterate id="v" name="zhszcp">
								<td>${v}</td>
								</logic:iterate>
							</tr>
							</logic:iterate>
						</table>
						</logic:present>		
					</div>
				</td>
			</tr>
			</logic:present>
			<logic:present name="dwjl">
				<tr id="divDwjl">
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main4" style="color:blue;cursor:hand"
									onclick="document.all.child4.style.display=(document.all.child4.style.display =='none')?'':'none';">
									<div align="center" class="style1 style3">
										<strong>对外交流</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div id="child4">
						<table width="100%" align="center" class="tbstyle">
							<thead>
								<tr>
									<td colspan="11">
										<div align="center" class="style2">
											对外交流及交流奖学金记录
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
										学期
									</td>
									<td>
										学号
									</td>
									<td>
										姓名
									</td>
									<td>
										交流项目
									</td>
									<td>
										申请时间
									</td>
										<td>
											<bean:message key="lable.xsgzyxpzxy" />审核
										</td>
										<td>
											学校审核
										</td>
									<td>
										学校终审
									</td>
									<td>
										奖学金
									</td>
								</tr>
							</thead>
							<logic:iterate id="dwjl" name="dwjlList">
							<tr>
								<logic:iterate id="v" name="dwjl">
								<td>${v}</td>
								</logic:iterate>
							</tr>
							</logic:iterate>
						</table>
					</div>
				</td>
			</tr>
			</logic:present>
			
			<logic:present name="xszz">
				<tr id="divXszz">
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main5" style="color:blue;cursor:hand"
									onclick="document.all.child5.style.display=(document.all.child5.style.display =='none')?'':'none';">
									<div align="center" class="style1 style3">
										<strong>学生资助</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			
			<tr id="divXszzxx">
				<td bgcolor="#FFFFFF">
					<div id="child5">
						<table width="100%" align="center" class="tbstyle">
						<thead>
							<tr>
							<td colspan="7" align="center">国家助学贷款</td>
							</tr>
						</thead>
						<logic:iterate id="gjzxdk" name="gjzxdkList">
						<tr>
							<logic:iterate id="v" name="gjzxdk">
								<td>
								${v}
								</td>
							</logic:iterate>
						</tr>
						
						</logic:iterate>
						</table>
						
						<table width="100%" align="center" class="tbstyle">
						<thead>
							<tr>
							<td colspan="9" align="center">资助申请信息</td>
							</tr>
						</thead>
						<logic:iterate id="zzsq" name="zzsqList">
						<tr>
							<logic:iterate id="v" name="zzsq">
								<td>
								${v}
								</td>
							</logic:iterate>
						</tr>
						
						</logic:iterate>
						</table>
						
						<table width="100%" align="center" class="tbstyle">
						<thead>
							<tr>
							<td colspan="7" align="center">困难生信息</td>
							</tr>
						</thead>
						<logic:iterate id="kns" name="knsList">
						<tr>
							<logic:iterate id="v" name="kns">
								<td>
								${v}
								</td>
							</logic:iterate>
						</tr>
						
						</logic:iterate>
						</table>
						
					</div>
				</td>
			</tr>
			</logic:present>
			
			<logic:present name="qgzx">
				<tr id="divQgzx">
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main6" style="color:blue;cursor:hand"
									onclick="document.all.child6.style.display=(document.all.child6.style.display =='none')?'':'none';">
									<div align="center" class="style1 style3">
										<strong>勤工助学</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF">
					<div id="child6">
						<table width="100%" align="center" class="tbstyle" id="tbQgzxjl">
							<thead>
								<tr>
									<td colspan="8">
										<div align="center" class="style2">
											勤工助学记录
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
										学期
									</td>
									<td>
										学号
									</td>
									<td>
										姓名
									</td>
									<td>
										班级名称
									</td>
									<td>
										岗位名称
									</td>
									<td>
										申请时间
									</td>
								</tr>
							</thead>
							<logic:iterate id="xsgw" name="xsgwList">
							<tr>
								<logic:iterate id="v" name="xsgw">
								<td>${v}</td>
								</logic:iterate>
							</tr>
							</logic:iterate>
						</table>
						<table width="100%" align="center" class="tbstyle" id="tbCjffjl">
							<thead>
								<tr>
									<td colspan="10">
										<div align="center" class="style2">
											酬金发放记录
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
										学期
									</td>
									<td>
										月份
									</td>
									<td>
										学号
									</td>
									<td>
										姓名
									</td>
									<td>
										班级名称
									</td>
									<td>
										岗位名称
									</td>
									<td>
										酬金金额
									</td>
									<td>
										发放时间
									</td>
								</tr>
							</thead>
							<logic:iterate id="cjff" name="cjffList">
							<tr>
								<logic:iterate id="v" name="cjff">
								<td>${v}</td>
								</logic:iterate>
							</tr>
							</logic:iterate>
						</table>
					</div>
				</td>
			</tr>
			</logic:present>
			
			<logic:notEqual value="stu" name="userType" scope="session">
				<tr id="divXljk">
					<td>
						<table width="100%" class="tbstyle">
							<tr>
								<td bgcolor="#CCCCCC">
									<div id="main7" style="color:blue;cursor:hand"
										onclick="document.all.child7.style.display=(document.all.child7.style.display =='none')?'':'none';">
										<div align="center" class="style1 style3">
											<strong>心理健康</strong>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFFFF">
						<div id="child7">
						<logic:present name="xlcs">
							<table width="100%" align="center" class="tbstyle" id="tbXlcsjgjl">
								<thead>
									<tr>
										<td colspan="9">
											<div align="center" class="style2">
												心理测试结果记录
											</div>
										</td>
									</tr>
									<tr>
										<td>
											学号
										</td>
										<td>
											姓名
										</td>
										<td>
											测试项目
										</td>
										<td>
											测试结果
										</td>
										<td>
											测试时间
										</td>
									</tr>
								</thead>	
								<logic:iterate id="xlcs" name="xlcsList">
								<tr>
									<logic:iterate id="v" name="xlcs">
									<td>${v}</td>
									</logic:iterate>
								</tr>
								</logic:iterate>							
							</table>							
							</logic:present>
							
							<logic:present name="tsxs">
							<table width="100%" align="center" class="tbstyle" id="tbTsxxjl">
								<thead>
									<tr>
										<td colspan="6">
											<div align="center" class="style2">
												特殊学生记录
											</div>
										</td>
									</tr>
									<tr>
										<td>
											学号
										</td>
										<td>
											姓名
										</td>
										<td>
											需要特别关心类别
										</td>
									</tr>
								</thead>	
								<logic:iterate id="tsxs" name="tsxsList">
								<tr>
									<logic:iterate id="v" name="tsxs">
									<td>${v}</td>
									</logic:iterate>
								</tr>
								</logic:iterate>
							</table>
							</logic:present>
							
							<table width="100%" align="center">
								<thead>
									<tr>
										<td colspan="6">
											<div align="center" class="style2">
												心理约谈情况
											</div>
										</td>
									</tr>
								</thead>
								<tr><td>
								<iframe style="width:100%;height:380px" scrolling="no" frameborder="0" marginwidth="0" marginheight="0" src="/xgxt/xsxxytxx.do?xsxxytxx=xsxxytxx&pkValue=${rs.xh}"></iframe>
								</td></tr>
							</table>
							<table width="100%" align="center">
								<thead>
									<tr>
										<td colspan="6">
											<div align="center" class="style2">
												重点关注情况
											</div>
										</td>
									</tr>
								</thead>
								<tr><td>
								<iframe style="width:100%;height:280px" scrolling="no" frameborder="0" marginwidth="0" marginheight="0" src="/xgxt/xsxxzdgz.do?xsxxzdgz=xsxxzdgz&pkValue=${rs.xh}"></iframe>
								</td></tr>
							</table>
						</div>
					</td>
				</tr>
			</logic:notEqual>
			
			<logic:present name="xsjx">
				<tr id="divXsjx">
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main8" style="color:blue;cursor:hand"
									onclick="document.all.child8.style.display=(document.all.child8.style.display =='none')?'':'none';">
									<div align="center" class="style1 style3">
										<strong>学生军训</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF">
					<div id="child8">
						<logic:present name="jxtdhj">
						<table width="100%" align="center" class="tbstyle" id="tbJxtdhj">
							<thead>
								<tr>
									<td colspan="8">
										<div align="center" class="style2">
											军训团队获奖记录
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
										学期
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
										奖项
									</td>
									<td>
										获奖时间
									</td>
								</tr>
							</thead>
							<logic:iterate id="jxtdhj" name="jxtdhjList">
								<tr>
									<logic:iterate id="v" name="jxtdhj">
									<td>${v}</td>
									</logic:iterate>
								</tr>
								</logic:iterate>
						</table>
						</logic:present>
						
						<logic:present name="jxcj">
						<table width="100%" align="center" class="tbstyle" id="tbJxcjjl">
							<thead>
								<tr>
									<td colspan="10">
										<div align="center" class="style2">
											军训成绩记录
										</div>
									</td>
								</tr>
								<tr>
									<td>
										学号
									</td>
									<td>
										姓名
									</td>
									<td>
										年度
									</td>
									<td>
										班级
									</td>
									<td>
										民族
									</td>
									<td>
										训练成绩
									</td>
									<td>
										考试成绩
									</td>
									<td>
										成绩
									</td>
									<td>
										录入时间
									</td>
									<td>
										录入人
									</td>
								</tr>
							</thead>
							<logic:iterate id="jxcj" name="jxcjList">
								<tr>
									<logic:iterate id="v" name="jxcj">
									<td>${v}</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
						</logic:present>
					</div>
				</td>
			</tr>
			</logic:present>
			
			<logic:present name="wjcf"> 
				<tr id="divWjcf">
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main9" style="color:blue;cursor:hand"
									onclick="document.all.child9.style.display=(document.all.child9.style.display =='none')?'':'none';">
									<div align="center" class="style1 style3">
										<strong>违纪处分</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF">
					<div id="child9">
						<table width="100%" align="center" class="tbstyle">
							<thead>
								<tr>
									<td colspan="10">
										<div align="center" class="style2">
											违纪处分记录
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
										学期
									</td>
									<td>
										学号
									</td>
									<td>
										姓名
									</td>
									<td>
										处分类别
									</td>
									<td>
										处分时间
									</td>
									<td>
										处分文号
									</td>
									<td>
										申诉结果
									</td>
									<td>
										解除时间
									</td>
								</tr>
							</thead>
							<logic:iterate id="wjcf" name="wjcfList">
								<tr>
									<logic:iterate id="v" name="wjcf">
									<td>${v}</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</div>
				</td>
			</tr>
			</logic:present>
			<logic:present name="qtsj">
				<tr id="divQtsj">
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main10" style="color:blue;cursor:hand"
									onclick="document.all.child10.style.display=(document.all.child10.style.display =='none')?'':'none';">
									<div align="center" class="style1 style3">
											<strong>其他数据</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF">
					<div id="child10">
					 <logic:present name="xsbx">
						<table width="100%" align="center" class="tbstyle" id="tbXsbxjl">
							<thead>
								<tr>
									<td colspan="10">
										<div align="center" class="style2">											
											学生保险记录
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
										学期
									</td>
									<td>
										学号
									</td>
									<td>
										姓名
									</td>
									<td>
										保险公司
									</td>
									<td>
										投保险种
									</td>
									<td>
										投保时间
									</td>
									<td>
										退保时间
									</td>
									<td>
										退保标记
									</td>
								</tr>
							</thead>
							<logic:iterate id="xsbx" name="xsbxList">
								<tr>
									<logic:iterate id="v" name="xsbx">
									<td>${v}</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							
						</table>
						</logic:present>
							<table width="100%" align="center" class="tbstyle" id="tbHsxfjl">
								<thead>
									<tr>
										<td colspan="5">
											<div align="center" class="style2">
												伙食消费记录
											</div>
										</td>
									</tr>
									<tr>
										<td>
											学号
										</td>
										<td>
											姓名
										</td>
										<td>
											年份
										</td>
										<td>
											月份
										</td>
										<td>
											消费金额
										</td>
									</tr>
								</thead>
								<logic:iterate id="hsxf" name="hsxfList">
								<tr>
									<logic:iterate id="v" name="hsxf">
									<td>${v}</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							</table>
					</div>
				</td>
			</tr>
			</logic:present>
			
			</table>
		<div class='noPrin' align="center">
		<input type='button' class='button2' value='页面设置' onclick="WebBrowser.ExecWB(8,1);return false;">
		<input type='button' class='button2' value='打印预览' onclick="WebBrowser.ExecWB(7,1);return false;">
		<input type='button' class='button2' value='直接打印' onclick="WebBrowser.ExecWB(6,6);return false;">
		</div>
	</body>
</html>
