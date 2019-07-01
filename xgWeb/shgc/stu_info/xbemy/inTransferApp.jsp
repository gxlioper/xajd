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
    <base target="_self">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/commanFunction.js"></script>
		<script language="javascript" src="js/calendar.js"></script>
		<script language="javascript" src="js/calendar-zh.js"></script>
		<script language="javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/AjaxFunction.js"></script>		
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script>
			function commitInfo(url,param){
				var nullStr = param.split("-");
				for(var i=0; i<nullStr.length; i++){
					if($(nullStr)){
						var Str = document.getElementById(nullStr[i]).value; 
						if(Str==null || Str == ""){
							alert('请将带\*号的项目填写完整!');
							return false;
						}
					}
				}
				refreshForm(url);
			}
			
		function print(url){
			var pk = "!!";
			var xh = document.getElementById("xh").value;
			var xm = document.getElementById("xm").value;
			var xb = document.getElementById("xbm").value;
			var zkzh = document.getElementById("zkzh").value;
			var mz = document.getElementById("mz").value;
			var lxdh = document.getElementById("lxdh").value;
			var rxsj = document.getElementById("rxsj").value;
			var zcxxmc = document.getElementById("zcxxmc").value;
			
			var zczymc = document.getElementById("zczymc").value;
			var zcnj = document.getElementById("zcnj").value;
			var zcxz = document.getElementById("zcxz").value;
			var zcxlcc = document.getElementById("zcxlcc").value;
			var zrxxmc = document.getElementById("zrxxmc").value;
			var zrzymc = document.getElementById("zy").options[document.getElementById("zy").selectedIndex].text;
			var zrnj = document.getElementById("nj").value;
			var zrxz = document.getElementById("zrxz").value;
			var zrxlcc = document.getElementById("zrxlcc").value;
			var sqly = document.getElementById("sqly").value;
			
			url += "&xh=" + xh;
			url += "&xm=" + xm;
			url += "&xb=" + xb;
			url += "&zkzh=" + zkzh;
			url += "&mz=" + mz;
			url += "&lxdh=" + lxdh;
			url += "&rxsj=" + rxsj;
			url += "&zcxxmc=" + zcxxmc;
			
			url += "&zczymc=" + zczymc;
			url += "&zcnj=" + zcnj;
			url += "&zcxz=" + zcxz;
			url += "&zcxlcc=" + zcxlcc;
			
			url += "&zrxxmc=" + zrxxmc;
			url += "&zrzymc=" + zrzymc;
			url += "&zrnj=" + zrnj;
			url += "&zrxz=" + zrxz;
			url += "&zrxlcc=" + zrxlcc;
			
			url += "&sqly=" + sqly;
			
			window.open(url);
			}
		</script>
	<body>
		<html:form action="/xbemyStuStatus.do" method="post">
		<input type="hidden" name="xyV" value=""/>
		<input type="hidden" name="zyV" value=""/>
		<input type="hidden" name="bjV" value=""/>
		<input type="hidden" name="njV" value=""/>
		<html:hidden property="zxlx" name="rs"/>
		<html:hidden property="sqrq" styleId="sqrq"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：学生信息 - 学籍变动申请 - 转学申请
				</div>
			</div>
				<fieldset>
					<legend>
						填写申请表
					</legend>
					<table width="100%" height="541" class="tbstyle" id="rsT">
						<thead>
							<tr style="height:22px">
								<td colspan="5" align="center">
									<b>转入本校申请</b>
								</td>
							</tr>
						</thead>						
						<tr style="height:22px">
							<logic:equal name="userOnLine" value="teacher" scope="session">
								<td align="right">
									<font color="red">*</font>学号：
								</td>
								<td align="left">
								<html:text name="rs" property="xh" styleId="xh"/>									
								</td>
							</logic:equal>
							<logic:equal name="userOnLine" value="student" scope="session">
								<td width="99" align="right">
									<font color="red">*</font>学号：
								</td>
								<td colspan="2" align="left">
									<input type="text" id="xh" name="xh" value="<bean:write name="userName"/>" readonly="readonly" />
								</td>
							</logic:equal>
							<td align="right">
								<font color="red">*</font>准考证号：
							</td>
							<td colspan="2" align="left">								
								<html:text name="rs" property="zkzh" styleId="zkzh"/>
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right" ><font color="red">*</font>姓名：
							</td>
							<td align="left">
								<html:text name="rs"  property="xm" styleId="xm" />
							</td>							
							<td align="right">民族：</td>
							<td colspan="2" align="left">
								<html:select name="rs"  property="mzdm"  style="width:150px" styleId="mz">
								<html:option value=""></html:option>
								<html:options collection="mzList" labelProperty="mzmc" property="mzdm"/>							
								</html:select>
							</td>
						</tr>
						<tr>
						  <td align="right">性别：</td>
						  <td>
						  <html:radio name="rs" property="xb" value="男" styleId="xb" onclick="javascript: document.getElementById('xbm').value=this.value">男
						  </html:radio> 
						  <html:radio name="rs" property="xb" value="女" styleId="xb" onclick="javascript: document.getElementById('xbm').value=this.value">女
						  </html:radio>
                            <input type="hidden" id="xbm" name="xbm" value=""/></td>
						  <td align="right">生源地： </td>
						  <td colspan="2" align="left">
						  	<html:text name="rs" property="syd" styleId="syd"/>
						  </td>
					  </tr>
						<tr>
							<td align="right" style="width:120px">身份证号：</td>
							<td>
								<html:text name="rs" property="sfzh" styleId="sfzh"/>
							</td>
							<td align="right">入学时间：
							</td>
							<td colspan="2" align="left">
								<html:text name="rs" property="rxsj" styleId="rxsj" readonly="true" onclick="return showCalendar('rxsj','y-mm-dd');"/>
							</td>
						</tr>
						<tr style="height:22px">	
							<td align="right">出生日期：</td>
							<td align="left">
								<html:text name="rs" property="csrq" styleId="csrq" readonly="true" onclick="return showCalendar('csrq','y-mm-dd');"/>
							</td>
						    <td align="left"><div align="right">联系电话：</div></td>
						    <td colspan="2" align="left">
							<html:text name="rs" property="lxdh" styleId="lxdh"/>
							</td>
						</tr>
						<thead>
							<tr style="height:22px">
								<td colspan="5" align="center">
									转入转出信息
								</td>
							</tr>
						</thead>
						<tr>
						<td rowspan="3" align="center">
						转<br/>
						出<br/>
						学<br/>
						校<br/>
						</td>
						<td align="right">
							校名：
						</td>
						<td>
						<html:text name="rs" property="zcxxmc" styleId="zcxxmc"/>
						</td>
						<td width="117"><div align="right">转出年级：</div></td>
						<td width="142"><html:text name="rs" property="zcnj" styleId="zcnj"/></td>
						</tr>
						<tr>
						<td align="right">
							专业：
						</td>
						<td>
						<html:text name="rs" property="zczymc" styleId="zczymc"/>
						</td>
						<td><div align="right">学制：</div></td>
						<td><html:text name="rs" property="zcxz" styleId="zcxz"/></td>
						</tr>
						
						<tr>
						<td height="22" align="right">学历层次：
						  </td>
						<td colspan="3">
						<html:text name="rs" property="zcxlcc" styleId="zcxlcc" style="width:100%"/>
						</td>
						</tr>
						
						<tr>
						<td rowspan="18" align="center" >
						转<br/>
						入<br/>
						学<br/>
						校<br/>
						</td>
						<td align="right" width="162">
							校名：
						</td>
						<td>
						<html:text name="rs" property="zrxxmc" styleId="zrxxmc"/>
						</td>
						<td><div align="right">学籍状态：</div></td>
						<td>
						<html:select property="xjztm" name="rs">
						<html:option value=""></html:option>
						<html:option value="有学籍">有学籍</html:option>
						<html:option value="无学籍">无学籍</html:option>
						</html:select>
						</td>
						</tr>
						<tr>
						<td align="right"><font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />：</td>
						<td>
							<html:select property="zrxydm" name="rs" onchange="initZyList();initBjList()" styleId="xy" >
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
							</html:select>
						</td>
						<td align="right">曾用名：</td>
						<td>
							<html:text name="rs" property="cym" styleId="cym"/>
						</td>
						</tr>
						<tr>
						  <td align="right"><font color="red">*</font>专业：</td>
						  <td>
<%--						  	<html:text name="rs" property="zrzydm" styleId="zrzymc"/>--%>
						  	<html:select property="zrzydm" name="rs" styleId="zy" onchange="initBjList()">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm" labelProperty="zymc"/>
							</html:select>
						  	</td>
						  <td><div align="right">考生号：</div></td>
						  <td>
						  	<html:text name="rs" property="ksh" styleId="ksh"/>
						  </td>
					  </tr>
						<tr>
						  <td align="right"><font color="red">*</font>班级：</td>
						  <td>
						  	<html:select property="zrbjdm" name="rs" styleId="bj">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm" labelProperty="bjmc"/>
							</html:select>
						  </td>
						  <td><div align="right">籍贯：</div></td>
						  <td>
						  <html:text name="rs" property="jg" styleId="jg"/>
						  </td>
					  </tr>
						<tr>
						  <td align="right"><font color="red">*</font>年级：</td>
						  <td>
						  <html:select property="zrnj" name="rs" styleId="nj">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj" labelProperty="nj"/>
							</html:select>
						  </td>
						  <td><div align="right">出生地：</div></td>
						  <td>
						  <html:text name="rs" property="csd" styleId="csd"/>
						  </td>
					  </tr>
						<tr>
						<td align="right"><font color="red">*</font>学制：
						  </td>
						<td>
						<html:text name="rs" property="zrxz" styleId="zrxz"/>
						</td>
						<td><div align="right">毕业日期：</div></td>
						<td>
						<html:text name="rs" property="byrq" styleId="byrq"/>
						</td>
						</tr>
						<tr>
						<td align="right">培养层次：
						  </td>
						<td>
						<html:text name="rs" property="zrxlcc" styleId="zrxlcc"/>
						</td>
						<td><div align="right">毕结业结论：</div></td>
						<td>
						<html:text name="rs" property="bjyjl" styleId="bjyjl"/>
						</td>
						</tr>
						<tr>
						  <td align="right">专业类别：</td>
						  <td>
						  <html:text name="rs" property="zylb" styleId="zylb"/>
						  </td>
						  <td><div align="right">证书编号：</div></td>
						  <td>
						  <html:text name="rs" property="zsbh" styleId="zsbh"/>
						  </td>
					  </tr>
						<tr>
						  <td align="right">专业方向：</td>
						  <td>
						  <html:text name="rs" property="zyfx" styleId="zyfx"/>
						  </td>
						  <td><div align="right">证书序列号：</div></td>
						  <td>
						  <html:text name="rs" property="zsxlh" styleId="zsxlh"/>
						  </td>
					  </tr>
						<tr>
						  <td align="right">培养方向：</td>
						  <td>
						  <html:text name="rs" property="pyfx" styleId="pyfx"/>
						  </td>
						  <td><div align="right">学位：</div></td>
						  <td>
						  <html:text name="rs" property="xw" styleId="xw"/>
						  </td>
					  </tr>
						<tr>
						  <td align="right">辅修专业：</td>
						  <td>
						  <html:select property="fxzy" name="rs" styleId="fxzy">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm" labelProperty="zymc"/>
							</html:select>
						  </td>
						  <td><div align="right">学位证书编号：</div></td>
						  <td>
						  <html:text name="rs" property="xwzsbh" styleId="xwzsbh"/>
						  </td>
					  </tr>
						<tr>
						  <td align="right">辅修专业方向：</td>
						  <td>
						  <html:text name="rs" property="fxzyfx" styleId="fxzyfx"/>
						  </td>
						  <td><div align="right">学位证书序列号：</div></td>
						  <td>
						  <html:text name="rs" property="xwzsxlh" styleId="xwzsxlh"/>
						  </td>
					  </tr>
						<tr>
						  <td align="right">办学类型：</td>
						  <td>
						  <html:text name="rs" property="bxlx" styleId="bxlx"/>
						  </td>
						  <td><div align="right">校长姓名：</div></td>
						  <td>
						  <html:text name="rs" property="xzxm" styleId="xzxm"/>
						  </td>
					  </tr>
						<tr>
						  <td align="right">办学形式：</td>
						  <td>
						  <html:text name="rs" property="bxxs" styleId="bxxs"/>
						  </td>
						  <td><div align="right">审核标记：</div></td>
						  <td>
						  <html:text name="rs" property="shbj" styleId="shbj"/>
						  </td>
					  </tr>
						<tr>
						  <td align="right">学习形式：</td>
						  <td>
						  <html:text name="rs" property="xxxs" styleId="xxxs"/>
						  </td>
						  <td><div align="right">打印标记：</div></td>
						  <td>
						  <html:text name="rs" property="dybj" styleId="dybj"/>
						  </td>
					  </tr>
						<tr>
						  <td align="right">招生季节：</td>
						  <td>
						  <html:text name="rs" property="zsjj" styleId="zsjj"/>
						  </td>
						  <td><div align="right">替换标识：</div></td>
						  <td>
						  <html:text name="rs" property="thbs" styleId="thbs"/>
						  </td>
					  </tr>
						<tr>
						  <td align="right">政治面貌：</td>
						  <td >
						  <html:select property="zzmm" name="rs">
						  <html:option value=""></html:option>
						  <html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
						  </html:select>
						  </td>
						  <td align="right">学校所在地：</td>
						  <td >
						  <html:text name="rs" property="xxszd" styleId="xxszd" style="width:100%"/>
						  </td>						  
					  </tr>
						<tr>
						  <td align="right">备注：</td>
						  <td colspan="3">
						  <html:textarea property="bz" name="rs" style="width:100%">
						  
						  </html:textarea>
						  </td>
					  </tr>
						<tr>
						<td align="right">转学申请（理由）：</td>
						<td colspan="4">
						<html:textarea name="rs" property="sqly" styleId="sqly" style="width:100%;height:45px"/>
						</td>
						</tr>
					</table>
					<logic:equal value="yes" name="writeAble">
					<div class="buttontool" align="center">
						<button class="button2"
							onclick="commitInfo('xbemyStuStatus.do?method=showTransferApp&doType=save','xh-xm-zkzh-xy-xy-bj-nj-zrxz')">
							提 交 申 请
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="print('xbemyStuStatus.do?method=printTransferApp')">
							打 印 报 表
						</button>
					</div>
					</logic:equal>
				</fieldset>
				
				<logic:notEmpty name="result">
				<logic:equal value="true" name="result">
				<script>
					alert("申请成功！");
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">
				<script>
					alert("申请失败！");
				</script>
				</logic:equal>
				</logic:notEmpty>
		</html:form>
	</body>
</html>
