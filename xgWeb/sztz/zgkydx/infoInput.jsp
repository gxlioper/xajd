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
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />		
	<base target="_self">
	<script language="javascript">
</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>	
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzDao.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>		
		<html:form action="/zgkydx_sztz" method="post">
			<input type="hidden" name="kmdmstr"
				value="<bean:write name="kmdmstr"/>">
			<input type="hidden" name="kmmcstr"
				value="<bean:write name="kmmcstr"/>">
			<input type="hidden" name="xn" value="<bean:write name="xn"/>" >
			<input type="hidden" name="xq" value="<bean:write name="xq"/>">
			<input type="hidden" name="maxLength" id="maxLength" value="0" >
			<input type="hidden" name="hddm" id="hddm" value="<bean:write name="hddm"/>" >
			<input type="hidden" name="pkV" id="pkV" value="<bean:write name="rs" property="pkV"/>" >
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：素质拓展 - 个人拓展信息 - 录入
				</div>
			</div>
			<logic:equal value="1" name="sqsjTag">
				<br />
				<br />
				<p align="center">
					<font color="red">不在设定时间范围内,暂不开放申报!</font>
				</p>
			</logic:equal>
			<logic:notEqual value="1" name="sqsjTag">
				<logic:notEqual value="stu" name="userType" scope="session">
					<div align="center">
						<br>
						<font color="red">此功能只开放给学生用户!</font>
					</div>
				</logic:notEqual>
				<fieldset>
					<legend>
						素质拓展填写
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr style="height:22px">
								<td colspan="8" align="center">
									<b>素质拓展填写表</b>
								</td>
							</tr>
						</thead>
						<tr>
							<td width="15%" align="right">
								学号：
							</td>
							<td>
								<input type="text" name="xh" readonly="true"
									value="<bean:write name="rs" property="xh"/>">
							</td>
							<td align="right" width="15%">
								学年：
							</td>
							<td>
								<html:select name="rs1" property="xn" disabled="true">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								姓名：
							</td>
							<td>
								<input type="text" name="xm" readonly="true" disabled="true"
									value="<bean:write name="rs" property="xm"/>">
							</td>
							<td align="right">
								学期：
							</td>
							<td>
								<html:select name="rs1" property="xq" disabled="true">
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>

						</tr>
						<tr>
							<td align="right">
								性别：
							</td>
							<td>
								<input type="text" name="xh" readonly="true" disabled="true"
									value="<bean:write name="rs" property="xb"/>">
							</td>
							<td align="right">
								民 族：
							</td>
							<td>
								<input type="text" name="xh" readonly="true" disabled="true"
									value="<bean:write name="rs" property="mzmc"/>">
							</td>
						</tr>
						<tr>
							<td align="right">
								出生年月：
							</td>
							<td>
								<input type="text" name="xh" readonly="true" disabled="true"
									value="<bean:write name="rs" property="csrq"/>">
							</td>
							<td align="right">
								出生地：
							</td>
							<td>
								<input type="text" name="xh" readonly="true" disabled="true"
									value="<bean:write name="rs" property="sydmc"/>">
							</td>
						</tr>
						<tr>
							<td align="right">
								政治面貌：
							</td>
							<td>
								<input type="text" name="xh" readonly="true" disabled="true"
									value="<bean:write name="rs" property="zzmmmc"/>">
							</td>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />：
							</td>
							<td>
								<input type="text" name="xh" readonly="true" disabled="true"
									value="<bean:write name="rs" property="xymc"/>">
							</td>
						</tr>
						<tr>
							<td align="right">
								入校时间：
							</td>
							<td>
								<input type="text" name="xh" readonly="true" disabled="true"
									value="<bean:write name="rs" property="rxrq"/>">
							</td>
							<td align="right">
								专业：
							</td>
							<td>
								<input type="text" name="xh" readonly="true" disabled="true"
									value="<bean:write name="rs" property="zymc"/>">
							</td>
						</tr>
					</table>
					<logic:empty name="kmXmList">

						<br>
						<br>
						<div align="center">
							拓展科目尚未维护，请联系管理员，维护拓展科目，维护路径为:
							<font color="red">系统维护 - 代码维护 - 素质拓展 - 科目设立</font>
						</div>
						<br>
					</logic:empty>
					<logic:notEmpty name="kmXmList">
						<fieldset>
							<legend>
								参加拓展活动&nbsp;&nbsp;&nbsp;
								<font color="blue">提示：单击下面栏目行，显示或隐藏栏目下所有内容,且请如实填写所参加拓展活动相关内容"</font>
							</legend>
							<logic:iterate name="kmXmList" id="s" indexId="index">
								<table width="100%" class="tbstyle" style="cursor: hand">
									<thead onclick="showHd(${index})">
										<tr title="单击该栏目显示或隐藏栏目下所有内容">
											<td colspan="8">
												<b> <span style="font-size: 15px;"><bean:write
															name="s" property="kmm" /> </span> </b>
												<span style="font-size: 11px;">
												<bean:write name="s" property="sm" />
												</span>
												<input type="hidden" id="num" name="num"
													   value="<bean:write name="s" property="num"/>" />
											</td>
										</tr>
									</thead>
									<tr>
									<td>
									<table width="100%" class="tbstyle">
										<tbody id="rsTables${index}" style="display: none">
										<logic:iterate id="b" name="s" property="rsList">	
										<tr>
											<td>
												<bean:write name="b" property="hdnrmc" />  
												<font color="blue"> 
												<input type="button" value="+" 
													title="增加行" style="height:18px;width:20px" id="add<bean:write name="b" property="hdnrdm" />"
													onclick="add('<bean:write name="b" property="hdnrdm" />','<bean:write name='s' property='kmm' />','<bean:write name='b' property='hdnrmc' />')">
												&nbsp; 
												<input type="button" value="-" title="删除行"
														style="height:18px;width:20px"
														onclick="delRow('jj<bean:write name="b" property="hdnrdm" />')">
											</td>
										</tr>
										<tr>
											<td>
												<table width="100%" class="tbstyle">
													<tbody width="100%" class="tbstyle" id="jj<bean:write name="b" property="hdnrdm" />">

													</tbody>	
												</table>
											</td>
										</tr>
										</logic:iterate>
										</tbody>
									</table>
									</td>
									</tr>
										<!--<logic:equal name="isHdnr" value="1">
											<logic:iterate id="b" name="s" property="rsList">	
												<tr>
													<td>
														<bean:write name="b" property="hdnrmc" />
													</td>
												</tr>
											</logic:iterate>
										</logic:equal>-->
								</table>
							</logic:iterate>
						</fieldset>
					</logic:notEmpty>

					<div class="buttontool" id="btn" align="center">
						<button class="button2" onclick="zgkd_infoSave()" style="width:80px"
							id="buttonSave">
							提 交
						</button>
					</div>
				</fieldset>
			</logic:notEqual>
		</html:form>
		<logic:equal value="yes" name="done">
			<script language="javascript">
	         	alert("操作成功！");
	         </script>
		</logic:equal>
	    <logic:equal value="no" name="done">
         	<script language="javascript">
	         	alert("操作失败！");
	        </script>
	   </logic:equal>
	</body>
	<script type="text/javascript">	
	var show_length = "0";
	var allNr ="";
	var tempHddm ="";
	var maxLength = "0";
	
  	function showHd(index){
  		var num = document.getElementById("num").value;
  		var nowRsTables = "rsTables" + index;
  		if(document.getElementById(nowRsTables).style.display==""){
  			document.getElementById(nowRsTables).style.display="none";
  		}
		for(var i=0;i<num;i++){
			var rsTables = "rsTables" + i;
			if(i == index){
				document.getElementById(rsTables).style.display="";
			}else{
				document.getElementById(rsTables).style.display="none";
			}
		}
  	}
  	
  	function add(index,kmm,hdnrmc){
  		var id = "jj"+index;
  		var pk = new Array();
  		var newNr= true;
  		var sxmc = new Array();
  		var objId="";
  		
  		allNr =  allNr+ "-" + index ;
  		pk = allNr.split("-");

		if(tempHddm==""){
			tempHddm=index;
		}
		
  		for(var i=0;i<pk.length-1;i++){
  			if(index ==pk[i]){
  				newNr = false;
  				break;
  			}
		}
  					
		getSztzDao.getHdnrsx(kmm,hdnrmc,function(data1){
			if (data1 != null && typeof data1 == 'object') {
				if(newNr){
					DWRUtil.addRows(id,['dd'],data1);
					show_length = 0;
				}else{
					getSztzDao.getXlsxmc(index,function(data2){
						if (data2 != null && typeof data2 == 'object') {
							var xh = document.createElement("select");	
							var addNr = new Array();
							
							getSztzDao.getSxmc(index,function(data3){
								for(var k=0;k<data3.length;k++){
									sxmc[k]= data3[k];
								}

								for(var j=0;j<data2.length;j++){
										if(data2[j]==null){
											if(sxmc[j].indexOf("时间")!=-1){
												addNr[j]= document.createElement("input");
												addNr[j].style.cursor="hand;";
												addNr[j].style.width="80px";
												addNr[j].id=sxmc[j] + index +show_length;
												addNr[j].name="_"+sxmc[j] + index +show_length;
												addNr[j].onclick=function(){gettime(this.id)};
												addNr[j].onblur=function(){dateFormatChg(this)};
												addNr[j].value= "";
											}else{
												addNr[j]= document.createElement("textarea");
											    addNr[j].onpropertychange=function(){scollChange(this)};
											    addNr[j].onblur=function(){chLeng(this,'500')};											    
											    addNr[j].rows=1;
											    addNr[j].style.width="100px";
												addNr[j].name="_"+sxmc[j] + index +show_length;
												addNr[j].id=sxmc[j] + index +show_length;
												addNr[j].value= "";
											}
										}else{
											addNr[j]= document.createElement("select");
											addNr[j].id=sxmc[j] + index +show_length;
											addNr[j].name="_"+sxmc[j] + index +show_length;
										}
								}
								
								DWRUtil.addRows(id,['dd'],addNr);
								for(var l=0;l<data2.length;l++){
									if(data2[l]!=null){
										objId = sxmc[l] + index+show_length;
										dwr.engine.setAsync(false);
										getSztzDao.getXlsxmc1(index,sxmc[l],function(data4){
											DWRUtil.removeAllOptions(objId);
											DWRUtil.addOptions(objId,data4,"dm","mc");	
										});
										dwr.engine.setAsync(true);
									}
								}	
								show_length ++;
								maxLength ++;
							});		
						}
					});				
				}
			}	
		});
		document.getElementById("maxLength").value=maxLength;
  	}
  	
  	function delRow(the_tab){
    	var tabobj = document.getElementById(the_tab);
    	var length = tabobj.rows.length;   
    	if(length==1){
      		return false;
    	}
   		if(confirm("确定要删除行？")){      
       		tabobj.deleteRow(tabobj.rows.length-1);
			maxLength --;
			show_length --;
			document.getElementById("maxLength").value=maxLength;
    	}
	}
	function scollChange(obj){
	   obj.style.posHeight=obj.scrollHeight;
	}
  	function gettime(id){
		return showCalendar(id,'y-mm-dd');
	}
	
	function zgkd_infoSave(){
		var max = document.getElementById("maxLength").value;
		var hddm = document.getElementById("hddm").value;
		var tempHddm = new Array();
		var pk = "";
		var hdnrdm=""
		var allPk="";
		var flg= true;
		tempHddm=hddm.split("-");
		for(var i = 0;i<=max;i++){	
		dwr.engine.setAsync(false);
			for(var k=0;k<tempHddm.length;k++){
				getSztzDao.getSxmc(tempHddm[k],function(data3){
					for(var j=0;j<data3.length;j++){
							var objId=data3[j]+tempHddm[k]+i;
							if(!flg){ 
					         break;
					        }
							if($(objId)){
								if(pk==""){
									hdnrdm = hdnrdm + tempHddm[k] + "!!SplitSignTrd!!";
								}
								if($(objId).value == ""){
									var row = i+1;
									getSztzDao.dao_getHdnrmc(tempHddm[k],function(data5){
									alert("活动"+data5[0]+"第"+row +"行"+data3[j]+"为空，请确认");
									flg= false;									
									});
									
								}else{
									if($(objId).value.length > 200){
										var row = i+1;
										getSztzDao.dao_getHdnrmc(tempHddm[k],function(data5){
										alert("活动"+data5[0]+"第"+row +"行"+data3[j]+"字数大于200，请确认");
										flg= false;
										
										});
									}
									
								}
								pk=pk+$(objId).value+ "!!@@!!";
							}							
						}
						if(pk!=""){
							allPk = allPk+pk + splitSignTwo
							pk="";
						}
					});
				}
				dwr.engine.setAsync(true);
			}			
		if(flg){
			document.getElementById("pkV").value= allPk;
			refreshForm("/xgxt/zgkydx_sztz.do?method=sztzInfoSave&allhdnrdm="+hdnrdm);
		}
	}
	</script>
</html>

