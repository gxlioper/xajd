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
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript'
			src='/xgxt/dwr/interface/dateUtil.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript">
		function SaveSq(){
		   var bm = document.getElementById('bm').value;
		   var yyrq = document.getElementById('yyrq').value;
		   var hdnr = document.getElementById('hdnr').value;
		   var bz = document.getElementById('bz').value;
		   var hour = document.getElementById('hour').value;
		   var minute = document.getElementById('minute').value;
		   var hour2 = document.getElementById('hour2').value;
		   var minute2 = document.getElementById('minute2').value;
		   var sb = document.getElementsByName('sb');
		   var lxdh = document.getElementById("lxdh").value;
		   var xxdm=$("xxdm").value;
		   var flg= $("flg").value;
		   if(lxdh != ""){
		   	if(lxdh.match(/^\d{0,4}\-{0,1}\d{7,8}$/)==null){
			   alert("电话号码格式不正确!");
			   document.getElementById("lxdh").focus();
			   return false;
		   }
		   }
		   var yysb = '';
		   if(bm == ''){
		   		alert('使用部门不能为空！');
		   	   	return false;
		   }
		   if(yyrq == ''){
		   		alert('预约日期不能为空！');
		   	   	return false;
		   }
		   if(xxdm=="11078"){
		   		if(hdnr== ''){
		   			alert('活动内容不能为空！');
		   			return false;
		   		}
		   }
		   if(hour == ''||minute==''||hour2 == ''||minute2==''){
		   		alert('预约时间段中开始和结束的时、分均不能为空！');
		   	   	return false;
		   }
		   if(hour>hour2 || (hour==hour2 && minute>minute2)){
		   		alert('预约时间段中开始时间大于结束时间！');
		   	   	return false;
		   }
		
		   if(hdnr.length > 1000){
		   		alert('活动内容不能大于1000字！');
		   	   	return false;
		   }
		   if(bz.length > 1000){
		   		alert('备注不能大于1000字！');
		   	   	return false;
		   }
		   for(var i=0;i<sb.length;i++){
		   		if(sb[i].checked==true){
		   			yysb += sb[i].value+'-';
		   		}		   		
		   }
		   if(yysb.indexOf('-')>0){
		   	   yysb = yysb.substring(0,yysb.length-1);
		   }
		   document.forms[0].action = "/xgxt/zysyyygl.do?method=zyyysq&doType=save&act=add&yysb="+yysb+"&flg="+flg;
		   document.forms[0].submit();
		}	
		function getRqVal(name){
			var rq=document.getElementById(name).value;
			if (rq!=""){
				rqs=rq.split("-");
				rq="";
				for (var i=0;i<rqs.length;i++){
					rq+=rqs[i];
				}
				document.getElementById(name).value=rq;
			}
		}
		function getWeek(obj){
			dateUtil.getDayOfWeek_CN(obj.value,function viewWeek(data){
				if(data != null){
					$('week').value=data;
				}     
         	})
		}
		function checkedSb(){
			if($('yysbcheck')){
				var yysb = $('yysbcheck').value;
				var sb = document.getElementsByName('sb');
				for(var i=0;i<sb.length;i++){
		   			if(yysb.indexOf(sb[i].value)>=0){
		   				sb[i].checked=true;
		   			}		   		
		   		}
			}		
		}
		function toPrint(){
			document.forms[0].action = "/xgxt/zysyyygl.do?method=zyyysqPrint";
			document.forms[0].target = "_blank";
	        document.forms[0].submit();
	        document.forms[0].target = "_self";
		}
		function change(title){
			if(title.id=="cd"){
				document.forms[0].action = "/xgxt/zysyyygl.do?method=zyyysq&flg=cd";
			}else{
				document.forms[0].action = "/xgxt/zysyyygl.do?method=zyyysq&flg=sb";
			}
			document.forms[0].submit();
		}
	</script>
</head>
<body onload="checkedSb()">
	<logic:equal value="no" name="view">
		<br>
		<br>
		<br>
		<p align="center">
			<font color="red" size="2">该页面只允许教师访问！</font>
		</p>
	</logic:equal>
	<logic:notEqual value="no" name="view">
		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置：日常事务 - 资源使用预约管理 - 资源预约申请
			</div>
		</div>
		<div class="xxk">
				<ul>
					<li id="" class="xxk_off_l"></li>
					<li id="cd" onclick="change(this)" class="xxk_off_m">
							&nbsp;
							场地预约
							&nbsp;
					</li>
					<li id="" class="xxk_off_r"></li>
				</ul>
				<ul>
					<li id=""	class="xxk_off_l"></li>
					<li id="sb"	onclick="change(this)" class="xxk_off_m">
							&nbsp;
							设备预约
							&nbsp;
					</li>
					<li id="" class="xxk_off_r"></li>
				</ul>
				</div>
		<html:form action="/zysyyygl" method="post">
			<input type="hidden" name="yysbcheck" id="yysbcheck" value="<bean:write name="yysb"/>"/>
			<input type="hidden" name="act" id="act" value="${act}"/>
			<input type="hidden" name="flg" id="flg" value="${flg}" styleId="flg"/>
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }" styleId="xxdm"/>
			<table class="tbstyle" width="100%">
				<tr>
					<td align="right" width="15%">
						<font color="red">*</font>使用部门：
					</td>
					<td align="left" width="35%">
						<logic:equal value="admin" name="userType">
							<html:select property="bm" style="width:200px">
								<html:option value="">--请选择--</html:option>
								<html:options property="bmdm" labelProperty="bmmc" collection="bmList"/>
							</html:select>
						</logic:equal>
						<logic:notEqual value="admin" name="userType">
							<html:select property="bm" style="width:200px" disabled="true">
								<html:option value="">--请选择--</html:option>
								<html:options property="bmdm" labelProperty="bmmc" collection="bmList"/>
							</html:select>
						</logic:notEqual>
						
					</td>
					<td align="right" width="15%">
						<font color="red">*</font>预约日期：
					</td>
					<td align="left" width="35%">
						<html:text property="yyrq" styleId="yyrq" readonly="true"
							onclick="this.value='';return showCalendar('yyrq','y-mm-dd');"
							onblur="getRqVal('yyrq');getWeek(this)"></html:text>
					</td>
				</tr>
				<tr>
					<td align="right">
						预约星期：
					</td>
					<td align="left">
						<html:text property="week" readonly="true"></html:text>
					</td>
					<logic:empty name="cdList">
						<td align="right" width="15%">
							活动场地：
						</td>
						<td>
							<html:text property="hdcd"></html:text>
						</td>
					</logic:empty>
					<logic:notEmpty name="cdList">
						<td align="right" width="15%">
							<font color="red">*</font>预约场地：
						</td>
						<td align="left" width="35%">
							<html:select property="cddm" style="width:200px" styleId="cddm">
								<html:options property="dm" labelProperty="mc" collection="cdList"/>
							</html:select>
						</td>
					</logic:notEmpty>
				</tr>				
				<tr>
					<td align="right">
						<font color="red">*</font>预约时间段：
					</td>
					<td align="left" colspan="3">
						<html:select property="hour" style="width:60px">
							<html:option value=""></html:option>
							<html:options property="dm" labelProperty="dm" collection="hourList"/>
						</html:select>&nbsp;时
						<html:select property="minute" style="width:60px">
							<html:options property="dm" labelProperty="dm" collection="minList"/>
						</html:select>&nbsp;分
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						至&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<html:select property="hour2" style="width:60px">
							<html:option value=""></html:option>
							<html:options property="dm" labelProperty="dm" collection="hourList"/>
						</html:select>&nbsp;时
						<html:select property="minute2" style="width:60px">
							<html:options property="dm" labelProperty="dm" collection="minList"/>
						</html:select>&nbsp;分
					</td>
				</tr>	
				<tr>
					<td align="right">
						负责人(辅导员)：
					</td>
					<td align="left">
						<html:text property="fzr" maxlength="30"></html:text>
					</td>
					<td align="right">
						联系电话：
					</td>
					<td align="left">
						<html:text property="lxdh" styleId="lxdh" maxlength="40" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
					</td>
				</tr>	
				<logic:notEmpty name="sbList">
					<tr>
					<td align="right" width="15%">
						<font color="red">*</font>预约设备：
					</td>
					<td align="left" width="35%" colspan="3">
						<logic:iterate id="s" name="sbList">
							<input type="radio" name="sb" id="sb" value="<bean:write name="s" property="dm"/>" checked="checked"/>
							<bean:write name="s" property="mc"/>&nbsp;&nbsp;
						</logic:iterate>
					</td>
				</tr>
				</logic:notEmpty>		
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>活动内容：
							<br>
							<font color="red"><限1000字内> </font>
						</div>
					</td>
					<td colspan="3">
						<html:textarea  property="hdnr" rows="8" styleId="hdnr"
							style="width:100%" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							备注：
							<br>
							<font color="red"><限1000字内> </font>
						</div>
					</td>
					<td colspan="3">
						<html:textarea property="bz" rows="8" styleId="aqcs"
							style="width:100%" />
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<button type="button" class="button2" onClick="SaveSq()" id="buttonSave">
					提 交
				</button>
				<logic:equal value="add" name="act">
					&nbsp;&nbsp;
					<button type="button" class="button2" onClick="Close()" id="buttonSave">
						关 闭
					</button>
				</logic:equal>
			</div>
		</html:form>
	<logic:equal value="add" name="act">
		<logic:equal value="true" name="result">
			<script type="text/javascript">
				alert('操作成功！');
				if(window.dialogArguments){
					Close();
					dialogArgumentsQueryChick();
				}
			</script>
		</logic:equal>
	</logic:equal>
	<logic:notEqual value="add" name="act">
		<logic:equal value="true" name="result">
			<script type="text/javascript">
				alert('操作成功！');
			</script>
		</logic:equal>
	</logic:notEqual>
	<logic:equal value="false" name="result">
		<script type="text/javascript">
			 alert('您预约的时间段与已有的申请有冲突！');
		</script>
	</logic:equal>
	<logic:equal value="true" name="bnjc">
		<logic:equal name="flg" value="cd">
		<script type="text/javascript">
			 alert('该场地暂时不能借出！');
		</script>
		</logic:equal>
		<logic:equal name="flg" value="sb">
		<script type="text/javascript">
			 alert('该设备暂时不能借出！');
		</script>
		</logic:equal>
	</logic:equal>
	</logic:notEqual>
</body>
</html:html>
