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
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/tbxxFunc.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript">
			function tbxxAdd(){
				var disNull = "xh-nd-tbgsdm-tbsj-bxnx";
				var bxxzNum = document.getElementById("bxxzNum").value;
				var xh = document.getElementById("xh").value;
				var tbgsdm = document.getElementById("tbgsdm").value;
				var nd = document.getElementById("nd").value;
				var bxxzdm = "";
				var tbxzdm = "";
				
				var eles = disNull.split("-");
					for (i = 0; i < eles.length; i++) {
						if (document.getElementById(eles[i]).value == "") {
							alert("请将带\"*\"号的项目输入完整！");
							return false;
						}
					}
				for(var i =0 ; i<parseInt(bxxzNum); i++){
					bxxzdm += document.getElementById("bxxzdm"+i).value;
					tbxzdm = document.getElementById("bxxzdm" + i).value;						
					var bxmc = document.getElementById("bxxzdm" + i).options[document.getElementById("bxxzdm" + i).selectedIndex].text;										
					if(tbxzdm!=""){
						var bxpzh = document.getElementById("bxpzh" + i).value;
						if(bxpzh==""){
							alert(bxmc+"的保险凭证号不能为空！");
							return false;
						}
					}
				}
				tbxxFunc.isExists(xh+nd,function(data){
						if(data){
							alert("该学生已经存在投保信息！");
							return false;
						}else{
							if(bxxzdm==""){
								alert('请至少选择一种保险险种！');
								return false;
							}
							if(checkRule()){
								refreshForm('shgc_tbxxwh.do?method=addTbxx&doType=add');
							}
						}
					});
				
			}
			
			function checkRule(){
				var num = parseInt(document.getElementById("bxxzNum").value);	
				for(var i=0;i<num;i++){		
					if(i==0){
						for(var j=1;j<num;j++)	{
						if($("bxxzdm"+j)){
						 if(document.getElementById("bxxzdm"+i).value== document.getElementById("bxxzdm"+j).value){	
						 	alert("投保险种重复！");
						 	return false;
						 }
						 }
						}
					}else if(i==num){
						for(var j=num;j>0;j--){
							if($("bxxzdm"+j)){
							if(document.getElementById("bxxzdm"+i).value==document.getElementById("bxxzdm"+j).value){
								alert("投保险种重复！");
								return false;
							}
							}
						}
					}else{
						for(var j=i;j<num-1;j++){
						if(j-1!=i){
							if($("bxxzdm"+parseInt(j-1))){
							if(document.getElementById("bxxzdm"+i).value=="" || document.getElementById("bxxzdm"+i).value==null){
							}else{
							if(document.getElementById("bxxzdm"+i).value==document.getElementById("bxxzdm"+parseInt(j-1)).value){
								alert("投保险种重复！");
								return false;
							}
							}
							}
						}
						}
						for(var m=i;m>=0;m--){
							if(m-1!=i){
							if($("bxxzdm"+parseInt(m-1))){
							if(document.getElementById("bxxzdm"+i).value=="" || document.getElementById("bxxzdm"+i).value==null){
							}else{				
							if(document.getElementById("bxxzdm"+i).value==document.getElementById("bxxzdm"+parseInt(m-1)).value){
								alert("投保险种重复！");
								return false;
							}			
							}
							}				
							}				
						}//end else			
					}	//end for						
				}//end fucntion
				return true;
			}
			
			function changeBf(obj){
				var index = parseInt(obj);
				var xh = document.getElementById("xh").value;
				var bxxzdm = document.getElementById("bxxzdm"+index).value;
				var bxnx = document.getElementById("bxnx").value;
				getInsureInfo.getBf(bxxzdm,bxnx,xh,function(date){
					var result = date.split("!!");
					document.getElementById("bf"+index).value=result[0];
					document.getElementById("bxnx").value=result[1];
				});
			}
		</script>
		<html:form action="/shgc_tbxxwh.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>	
				<input type="hidden" id="disableEle" name="disableEle" value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/qtsj/shgc/tbxxAdd.jsp" />
				<input type="hidden" id="tabName" name="tabName" value="xsbxb" />
				<input type="hidden" id="isSchool" name="isSchool" value="" />
				<input type="hidden" id="bxxzNum" name="bxxzNum" value="<bean:write name="bxxzNum"/>" />
				<fieldset>
					<legend>
						学生保险信息维护
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td colspan="4" align="center">
									学生保险信息维护
								</td>
							</tr>
						</thead>
						<tr>
							<td align="right">
								<font color="red">*</font>学号：
							</td>
							<td align="left">
								<html:text name='rs' property="xh" readonly="readonly"
									styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
							</td>	
							<td align="right">
								<font color="red">*</font>年度：
							</td>
							<td align="left">
								<html:select name="rs" property="nd" style="width:90px"
									styleId="nd" onchange="getInsureInfoExist();">
									<html:options collection="ndList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>						
						</tr>
						<tr>
							<td align="right">
								姓名：
							</td>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" disabled="true"/>
							</td>
							<td align="right">
								<font color="red">*</font>保险公司：
							</td>
							<td align="left">
								<html:select name="rs" property="tbgsdm" style="width:150px" styleId="tbgsdm">
									<html:option value=""></html:option>
									<html:options collection="tbgsdmList" property="bxgsdm"
										labelProperty="bxgsmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								性别：
							</td>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" disabled="true"/>
							</td>
							<td align="right">
								<font color="red">*</font>投保日期：
							</td>
							<td align="left">
								<html:text name='rs' property="tbsj" styleId="tbsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('tbsj','y-mm-dd');" />
							</td>							
						</tr>
						<tr>
							<td align="right">
								年级：
							</td>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj" disabled="true"/>
							</td>
							<td align="right">
								<font color="red">*</font>保险期限：
							</td>
							<td align="left">
								<html:text name="rs" property="bxnx" styleId="bxnx"
								onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="5"/>(年)					
							</td>								
						</tr>
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />：
							</td>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" disabled="true"/>
							</td>
							<td align="right">
								退保日期：
							</td>
							<td align="left">
								<html:text name='rs' property="tuibsj" styleId="tuibsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('tuibsj','y-mm-dd');" />
							</td>	
						</tr>
						<tr>
							<td align="right">
								专业：
							</td>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" disabled="true"/>
							</td>
							<td align="right">
								退保标记：
							</td>
							<td align="left">
								<html:select name="rs" property="tbbj" style="width:90px"
									styleId="tbbj">
									<html:option value=""></html:option>
									<html:option value="未退">未退</html:option>
									<html:option value="已退">已退</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								班级：
							</td>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" disabled="true"/>
							</td>
							<td align="right">
								缴费标记：
							</td>
							<td align="left">
								<html:select name="rs" property="jfbj" style="width:90px" styleId="jfbj">
								    <html:option value="否">否</html:option>
								    <html:option value="是">是</html:option>
								</html:select>
							</td>							
						</tr>
						<tr >
						<td colspan="4">
						<table class="tbstyle" width="100%">						
						<logic:iterate id="bxxz" name="bxxzList" indexId="index">
							<tr>											
							<td align="right">
								<font color="red">*</font>投保险种：
							</td>
							<td align="left" width="160px">
								<select id="bxxzdm${index}" name="bxxzdm${index}" style="width:160px" onchange="changeBf(${index})">	
								<option></option>
								 <logic:iterate id="bxxz" name="bxxzList">
								 	<option value="${bxxz.bxxzdm}">${bxxz.bxxzmc}</option>								 	
								 </logic:iterate>			
								</select>
							</td>
							<td align="right">
								保险凭证号：
							</td>
							<td align="left">
								<input type="text" id="bxpzh${index}" name="bxpzh${index}"/>
							</td>							
							<td align="right">
								保费：
							</td>
							<td align="left">
							<input type="text" id="bf${index}" name="bf${index}" onkeyup="value=value.replace(/[^\d]/g,'') " />(元)							
							</td>
						</tr>
						</logic:iterate>
						<html:hidden property="xxsh"/>
						</table>
						</td>
						</tr>	
						<tr align="left">
							<td align="right">
								备注：
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='bz' style="width:99%"
									rows='5' />
							</td>
						</tr>
					</table>
					<div class="buttontool" align="center">						
						<button class="button2" onclick="tbxxAdd()"
							style="width:80px" id="buttonSave">
							保 存
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							关 闭
						</button>
					</div>
				</fieldset>
				<logic:present name="result">
				<logic:equal value="true" name="result">
				<script>
				alert('操作成功！');
				Close();
				window.dialogArguments.document.getElementById('search_go').click();	
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">
				<script>
				alert('操作失败！');
				Close();
				</script>
				</logic:equal>
				</logic:present>
		</html:form>		
	</body>
</html>
