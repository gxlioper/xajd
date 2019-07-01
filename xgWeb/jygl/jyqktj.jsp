<%@ page language="java" contentType="text/html; charset=gb2312"%>
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
		<title>就业管理信息系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
    <script type="text/javascript" src="js/BatAlert.js"></script>
	<script language="javascript">
    function querygo(type){
       var tjxm = document.getElementById("tjxm").value;
       var nd  = document.getElementById("nd").value;
       if(tjxm==""){
       	 	alert("统计项目不能为空！");
       		return false;
       }
       
       if(nd==""){
       	 	alert("毕业年度不能为空！");
       		return false;
       }
       if(type == 'tj'){
       		document.forms[0].action = "/xgxt/jyqktj.do?type="+type;
			document.forms[0].submit();
	   }else{
			window.open('/xgxt/jyqktj.do?tjxm='+tjxm+'&nd='+nd+'&type='+type);
	   }
    }
	</script>

	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<html:form action="/jyqktj" method="post">

			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：就业管理 - 统计分析 - 就业情况统计
				</div>
			</div>
			<fieldset>
				<legend>
					统计查询
				</legend>
				<div>
					<br>
					&nbsp;&nbsp;毕业年度：
					<html:select property="nd" style="width:80px" styleId="nd">
						<html:options collection="ndList" labelProperty="nd" property="nd"/>
					</html:select>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					统计项目：
					<html:select property="tjxm" style="width:200px" styleId="tjxm">
						<html:option value=""></html:option>
						<html:option value="xjsp">毕业生就业薪金水平</html:option>
						<html:option value="zydkl">毕业生就业专业对口率</html:option>
						<html:option value="dyfb">毕业生就业地域分布</html:option>
						<html:option value="dwxzqk">毕业生就业单位性质情况</html:option>
						<html:option value="hyfb">毕业生就业行业分布</html:option>
						<html:option value="jyphb">就业排行榜</html:option>
					</html:select>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="querygo('tj');" style="width:80px">
						统 计
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="querygo('dc');"
						style="width:80px">
						导 出
					</button>
					<br>
					&nbsp;
				</div>
			</fieldset>

			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					未找到任何记录！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal value="jyphb" name="tjxm">
				<fieldset>
					<legend align="center">
						<font color="blue">
							<span id="xm"></span>
						</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<bean:write name="tabtop" property="xy" />
									</td>
									<td>
										<bean:write name="tabtop" property="zy" />
									</td>
									<td>
										<bean:write name="tabtop" property="byrs" />
									</td>
									<td>
										<bean:write name="tabtop" property="qyrs" />
									</td>
									<td>
										<bean:write name="tabtop" property="qyl" />
									</td>
									<td>
										<bean:write name="tabtop" property="qylpx" />
									</td>
									<td>
										<bean:write name="tabtop" property="jyrs" />
									</td>
									<td>
										<bean:write name="tabtop" property="jyl" />
									</td>
									<td>
										<bean:write name="tabtop" property="jylpx" />
									</td>
								</tr>
							</thead>
							<logic:iterate id="s" name="rs">
								<% int i = 0; %>
								<logic:iterate id="n" name="s" property="xyzymap">
									<tr style="cursor:hand">
										<% if(i == 0){ %>
											<td rowspan="<bean:write name="s" property="count"/>"><bean:write name="s" property="xymc"/></td>
										<%} %>
											 <logic:iterate id="b" name="s" property="xyzymap">
											 	  <td><bean:write name="b" property="zymc"/></td>
												  <td><bean:write name="b" property="zyrs"/></td>
												  <td><bean:write name="b" property="qyrs"/></td>
												  <td><bean:write name="b" property="zyqyl"/></td>
											 </logic:iterate>
										<% if(i == 0){ %>
											<td rowspan="<bean:write name="s" property="count"/>"><bean:write name="s" property="qylmc"/></td>
										<%} %>
										<logic:iterate id="c" name="s" property="xyzymap">
											 <td><bean:write name="c" property="jyrs"/></td>
											 <td><bean:write name="c" property="zyjyl"/></td>
										</logic:iterate>
										<% if(i == 0){ %>
											<td rowspan="<bean:write name="s" property="count"/>"><bean:write name="s" property="jylmc"/></td>
										<%
												i++;
											} 										
										%>
									</tr>
								</logic:iterate>
								<tr style="cursor:hand">
										<td><font style="font: bolder;">小计</font></td>
										<td name="xyrs"><font style="font: bolder;"><bean:write name="s" property="xyrs"/></font></td>
										<td name="xyqyrs"><font style="font: bolder;"><bean:write name="s" property="xyqyrs"/></font></td>
										<td><font style="font: bolder;"><bean:write name="s" property="xyqyl"/></font></td>
										<td name="xyjyrs"><font style="font: bolder;"><bean:write name="s" property="xyjyrs"/></font></td>
										<td><font style="font: bolder;"><bean:write name="s" property="xyjyl"/></font></td>
								</tr>
							</logic:iterate>
								<tr style="cursor:hand">
										<td colspan="2" align="center"><font style="font: bolder;color :red">总计</font></td>
										<td><font style="font: bolder;color :red"><bean:write name="totalmap" property="xxrs"/></font></td>
										<td><font style="font: bolder;color :red"><bean:write name="totalmap" property="xxqyrs"/></font></td>
										<td><font style="font: bolder;color :red"><bean:write name="totalmap" property="xxqyl"/></font></td>
										<td></td>
										<td><font style="font: bolder;color :red"><bean:write name="totalmap" property="xxjyrs"/></font></td>
										<td><font style="font: bolder;color :red"><bean:write name="totalmap" property="xxjyl"/></font></td>
										<td></td>									
								</tr>
					</table>
				</fieldset>
				</logic:equal>
				
				<logic:equal value="xjsp" name="tjxm">
				<fieldset>
					<legend align="center">
						<font color="blue">
							<span id="xm"></span>
						</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td rowspan="2">
										<bean:write name="tabtop" property="xy" />
									</td>
									<td rowspan="2">
										<bean:write name="tabtop" property="zy" />
									</td>
									<td rowspan="2">
										<bean:write name="tabtop" property="cyrs" />
									</td>
									<td colspan="6">
										<bean:write name="tabtop" property="xjspfb" />
									</td>
									<td rowspan="2">
										<bean:write name="tabtop" property="pjgz" />
									</td>
								</tr>
								<tr>
									<td>
										<bean:write name="tabtop" property="xj1" />
									</td>
									<td>
										<bean:write name="tabtop" property="xj2" />
									</td>
									<td>
										<bean:write name="tabtop" property="xj3" />
									</td>
									<td>
										<bean:write name="tabtop" property="xj4" />
									</td>
									<td>
										<bean:write name="tabtop" property="xj5" />
									</td>
									<td>
										<bean:write name="tabtop" property="xj6" />
									</td>
								</tr>
							</thead>
							<logic:iterate id="s" name="rs">
								<% int i = 0; %>
								<logic:iterate id="n" name="s" property="xyzymap">
									<tr style="cursor:hand">
										<% if(i == 0){ %>
											<td rowspan="<bean:write name="s" property="count"/>"><bean:write name="s" property="xymc"/></td>
										<%
											i++;
											} 
										%>
										<td><bean:write name="n" property="zymc"/></td>
										<td><bean:write name="n" property="zyrs"/></td>
										<td><bean:write name="n" property="xj1"/></td>
										<td><bean:write name="n" property="xj2"/></td>
										<td><bean:write name="n" property="xj3"/></td>
										<td><bean:write name="n" property="xj4"/></td>
										<td><bean:write name="n" property="xj5"/></td>
										<td><bean:write name="n" property="xj6"/></td>
										<td><bean:write name="n" property="zydynypjgz"/></td>
									</tr>
								</logic:iterate>
								<tr style="cursor:hand">
										<td><font style="font: bolder;">小计</font></td>
										<td><font style="font: bolder;"><bean:write name="s" property="xyrs"/></font></td>
										<td><font style="font: bolder;"><bean:write name="s" property="xj1"/></font></td>
										<td><font style="font: bolder;"><bean:write name="s" property="xj2"/></font></td>
										<td><font style="font: bolder;"><bean:write name="s" property="xj3"/></font></td>
										<td><font style="font: bolder;"><bean:write name="s" property="xj4"/></font></td>
										<td><font style="font: bolder;"><bean:write name="s" property="xj5"/></font></td>
										<td><font style="font: bolder;"><bean:write name="s" property="xj6"/></font></td>
										<td><font style="font: bolder;"><bean:write name="s" property="xydynypjgz"/></font></td>
								</tr>
							</logic:iterate>
							<tr style="cursor:hand">
										<td colspan="2" align="center"><font style="font: bolder;color :red">总计</font></td>
										<td><font style="font: bolder;color :red"><bean:write name="totalmap" property="xxrs"/></font></td>
										<td><font style="font: bolder;color :red"><bean:write name="totalmap" property="zxj1"/></font></td>
										<td><font style="font: bolder;color :red"><bean:write name="totalmap" property="zxj2"/></font></td>
										<td><font style="font: bolder;color :red"><bean:write name="totalmap" property="zxj3"/></font></td>
										<td><font style="font: bolder;color :red"><bean:write name="totalmap" property="zxj4"/></font></td>	
										<td><font style="font: bolder;color :red"><bean:write name="totalmap" property="zxj5"/></font></td>	
										<td><font style="font: bolder;color :red"><bean:write name="totalmap" property="zxj6"/></font></td>	
										<td><font style="font: bolder;color :red"><bean:write name="totalmap" property="xxdynypjgz"/></font></td>									
							</tr>
					</table>
				</fieldset>
				</logic:equal>
				<logic:equal value="zydkl" name="tjxm">
				<fieldset>
					<legend align="center">
						<font color="blue">
							<span id="xm"></span>
						</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td rowspan="2">
										<bean:write name="tabtop" property="xy" />
									</td>
									<td rowspan="2">
										<bean:write name="tabtop" property="zy" />
									</td>
									<td rowspan="2">
										<bean:write name="tabtop" property="cyrs" />
									</td>
									<td colspan="2">
										<bean:write name="tabtop" property="byqx" />
									</td>
									<td rowspan="2">
										<bean:write name="tabtop" property="zydkl" />
									</td>
								</tr>
								<tr>
									<td>
										<bean:write name="tabtop" property="bzy" />
									</td>
									<td>
										<bean:write name="tabtop" property="kzy" />
									</td>
								</tr>
							</thead>
							<logic:iterate id="s" name="rs">
								<% int i = 0; %>
								<logic:iterate id="n" name="s" property="xyzymap">
									<tr style="cursor:hand">
										<% if(i == 0){ %>
											<td rowspan="<bean:write name="s" property="count"/>"><bean:write name="s" property="xymc"/></td>
										<%
											i++;
											} 
										%>
										<td><bean:write name="n" property="zymc"/></td>
										<td><bean:write name="n" property="zyrs"/></td>
										<td><bean:write name="n" property="bzyrs"/></td>
										<td><bean:write name="n" property="kzyrs"/></td>
										<td><bean:write name="n" property="zydkl"/></td>
									</tr>
								</logic:iterate>
								<tr style="cursor:hand">
										<td><font style="font: bolder;">小计</font></td>
										<td><font style="font: bolder;"><bean:write name="s" property="xyrs"/></font></td>
										<td><font style="font: bolder;"><bean:write name="s" property="xybzyrs"/></font></td>
										<td><font style="font: bolder;"><bean:write name="s" property="xykzyrs"/></font></td>
										<td><font style="font: bolder;"><bean:write name="s" property="xyzydkl"/></font></td>
								</tr>
							</logic:iterate>
							<tr style="cursor:hand">
										<td colspan="2" align="center"><font style="font: bolder;color :red">总计</font></td>
										<td><font style="font: bolder;color :red"><bean:write name="totalmap" property="xxrs"/></font></td>
										<td><font style="font: bolder;color :red"><bean:write name="totalmap" property="xxbzyrs"/></font></td>
										<td><font style="font: bolder;color :red"><bean:write name="totalmap" property="xxkzyrs"/></font></td>	
										<td><font style="font: bolder;color :red"><bean:write name="totalmap" property="xxzylkl"/></font></td>									
							</tr>
					</table>
				</fieldset>
				</logic:equal>
				<logic:equal value="dyfb" name="tjxm">
				<fieldset>
					<legend align="center">
						<font color="blue">
							<span id="xm"></span>
						</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td rowspan="2">
										<bean:write name="tabtop" property="xy" />
									</td>
									<td rowspan="2">
										<bean:write name="tabtop" property="byrs" />
									</td>
									<td rowspan="2">
										<bean:write name="tabtop" property="qyrs" />
									</td>
									<td rowspan="2">
										<bean:write name="tabtop" property="tjjswqyrs" />
									</td>
									<td colspan="3" >
										<bean:write name="tabtop" property="dwszdq" />
									</td>
								</tr>
								<tr>
									<td>
										<bean:write name="tabtop" property="sq" />
									</td>
									<td>
										<bean:write name="tabtop" property="jq" />
									</td>
									<td>
										<bean:write name="tabtop" property="wd" />
									</td>
								</tr>
							</thead>
							<logic:iterate id="s" name="rs">					
								<tr style="cursor:hand">
										<td rowspan="2"><bean:write name="s" property="xymc"/></td>
										<td rowspan="2"><bean:write name="s" property="byrs"/></td>
										<td rowspan="2"><bean:write name="s" property="jyrs"/></td>
										<td>人数</td>
										<td><bean:write name="s" property="sq"/></td>
										<td><bean:write name="s" property="jq"/></td>
										<td><bean:write name="s" property="wd"/></td>
								</tr>
								<tr style="cursor:hand">
										<td>百分比</td>
										<td><bean:write name="s" property="sqbfb"/></td>
										<td><bean:write name="s" property="jqbfb"/></td>
										<td><bean:write name="s" property="wdbfb"/></td>
								</tr>
							</logic:iterate>
					</table>
				</fieldset>
				</logic:equal>
				<logic:equal value="dwxzqk" name="tjxm">
				<fieldset>
					<legend align="center">
						<font color="blue">
							<span id="xm"></span>
						</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td rowspan="2">
										<bean:write name="tabtop" property="xy" />
									</td>
									<td rowspan="2">
										<bean:write name="tabtop" property="byrs" />
									</td>
									<td rowspan="2">
										<bean:write name="tabtop" property="qyrs" />
									</td>
									<td rowspan="2">
										<bean:write name="tabtop" property="tjjswqyrs" />
									</td>
									<td colspan="7" >
										<bean:write name="tabtop" property="dwxz" />
									</td>
								</tr>
								<tr>
									<td>
										<bean:write name="tabtop" property="gy" />
									</td>
									<td>
										<bean:write name="tabtop" property="jt" />
									</td>
									<td>
										<bean:write name="tabtop" property="my" />
									</td>
									<td>
										<bean:write name="tabtop" property="sy" />
									</td>
									<td>
										<bean:write name="tabtop" property="gf" />
									</td>
									<td>
										<bean:write name="tabtop" property="hz" />
									</td>
									<td>
										<bean:write name="tabtop" property="dz" />
									</td>
								</tr>
							</thead>
							<logic:iterate id="s" name="rs">					
								<tr style="cursor:hand">
										<td rowspan="2"><bean:write name="s" property="xymc"/></td>
										<td rowspan="2"><bean:write name="s" property="byrs"/></td>
										<td rowspan="2"><bean:write name="s" property="jyrs"/></td>
										<td>人数</td>
										<td><bean:write name="s" property="gy"/></td>
										<td><bean:write name="s" property="jt"/></td>
										<td><bean:write name="s" property="my"/></td>
										<td><bean:write name="s" property="sy"/></td>
										<td><bean:write name="s" property="gf"/></td>
										<td><bean:write name="s" property="hz"/></td>
										<td><bean:write name="s" property="dz"/></td>
								</tr>
								<tr style="cursor:hand">
										<td>百分比</td>
										<td><bean:write name="s" property="gybfb"/></td>
										<td><bean:write name="s" property="jtbfb"/></td>
										<td><bean:write name="s" property="mybfb"/></td>
										<td><bean:write name="s" property="sybfb"/></td>
										<td><bean:write name="s" property="gfzbfb"/></td>
										<td><bean:write name="s" property="hzbfb"/></td>
										<td><bean:write name="s" property="dzbfb"/></td>
								</tr>
							</logic:iterate>
					</table>
				</fieldset>
				</logic:equal>
				<logic:equal value="hyfb" name="tjxm">
				<fieldset>
					<legend align="center">
						<font color="blue">
							<span id="xm"></span>
						</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td rowspan="2">
										<bean:write name="tabtop" property="xy" />
									</td>
									<td rowspan="2">
										<bean:write name="tabtop" property="byrs" />
									</td>
									<td rowspan="2">
										<bean:write name="tabtop" property="qyrs" />
									</td>
									<td rowspan="2">
										<bean:write name="tabtop" property="tjjswqyrs" />
									</td>
									<td colspan="10" >
										<bean:write name="tabtop" property="dwszhy" />
									</td>
								</tr>
								<tr>
									<td>
										<bean:write name="tabtop" property="zzy" />
									</td>
									<td>
										<bean:write name="tabtop" property="fwy" />
									</td>
									<td>
										<bean:write name="tabtop" property="ydtxjsjxxy" />
									</td>
									<td>
										<bean:write name="tabtop" property="jrbxfdc" />
									</td>
									<td>
										<bean:write name="tabtop" property="jykywgtyfl" />
									</td>
									<td>
										<bean:write name="tabtop" property="pflsmycy" />
									</td>
									<td>
										<bean:write name="tabtop" property="jzy" />
									</td>
									<td>
										<bean:write name="tabtop" property="jtyscc" />
									</td>
									<td>
										<bean:write name="tabtop" property="gjdzjgshtt" />
									</td>
									<td>
										<bean:write name="tabtop" property="qt" />
									</td>
								</tr>
							</thead>
							<logic:iterate id="s" name="rs">					
								<tr style="cursor:hand">
										<td rowspan="2"><bean:write name="s" property="xymc"/></td>
										<td rowspan="2"><bean:write name="s" property="byrs"/></td>
										<td rowspan="2"><bean:write name="s" property="jyrs"/></td>
										<td>人数</td>
										<td><bean:write name="s" property="zzy"/></td>
										<td><bean:write name="s" property="shfw"/></td>
										<td><bean:write name="s" property="ydtx"/></td>
										<td><bean:write name="s" property="jrbx"/></td>
										<td><bean:write name="s" property="jykj"/></td>
										<td><bean:write name="s" property="pfls"/></td>
										<td><bean:write name="s" property="jzy"/></td>
										<td><bean:write name="s" property="jtys"/></td>
										<td><bean:write name="s" property="gjjg"/></td>
										<td><bean:write name="s" property="qt"/></td>
								</tr>
								<tr style="cursor:hand">
										<td>百分比</td>
										<td><bean:write name="s" property="zzybfb"/></td>
										<td><bean:write name="s" property="shfwbfb"/></td>
										<td><bean:write name="s" property="ydtxbfb"/></td>
										<td><bean:write name="s" property="jrbxbfb"/></td>
										<td><bean:write name="s" property="jykjbfb"/></td>
										<td><bean:write name="s" property="pflsbfb"/></td>
										<td><bean:write name="s" property="jzybfb"/></td>
										<td><bean:write name="s" property="jtysbfb"/></td>
										<td><bean:write name="s" property="gjjgbfb"/></td>
										<td><bean:write name="s" property="qtbfb"/></td>
								</tr>
							</logic:iterate>
					</table>
				</fieldset>
				</logic:equal>
				<script language="javascript">
					$('xm').innerText = $('nd').options[$('nd').selectedIndex].text+'届'+$('tjxm').options[$('tjxm').selectedIndex].text;
				</script>
			</logic:notEmpty>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>