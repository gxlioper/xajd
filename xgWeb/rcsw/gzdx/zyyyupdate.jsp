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
	<meta name="Copyright" content="������� zfsoft" />

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
		   if(lxdh != ""){
		   	if(lxdh.match(/^\d{0,4}\-{0,1}\d{7,8}$/)==null){
			   alert("�绰�����ʽ����ȷ!");
			   document.getElementById("lxdh").focus();
			   return false;
		   }
		   }
		   var yysb = '';
		   if(bm == ''){
		   		alert('ʹ�ò��Ų���Ϊ�գ�');
		   	   	return false;
		   }
		   if(yyrq == ''){
		   		alert('ԤԼ���ڲ���Ϊ�գ�');
		   	   	return false;
		   }
		  
		   if(hour == ''||minute==''||hour2 == ''||minute2==''){
		   		alert('ԤԼʱ����п�ʼ�ͽ�����ʱ���־�����Ϊ�գ�');
		   	   	return false;
		   }
		   if(hour>hour2 || (hour==hour2 && minute>minute2)){
		   		alert('ԤԼʱ����п�ʼʱ����ڽ���ʱ�䣡');
		   	   	return false;
		   }
		   if(hdnr.length > 1000){
		   		alert('����ݲ��ܴ���1000�֣�');
		   	   	return false;
		   }
		   if(bz.length > 1000){
		   		alert('��ע���ܴ���1000�֣�');
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
		   document.forms[0].action = "/xgxt/zysyyygl.do?method=viewYyxx&flag=update&doType=update&yysb="+yysb;
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
	</script>
</head>
<body onload="checkedSb()">
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰ����λ�ã��ճ����� - ��Դʹ��ԤԼ���� - 
				<logic:notPresent name="act">
					��ԴԤԼ�鿴
				</logic:notPresent>
				<logic:present name="act">
					��ԴԤԼ�޸�
				</logic:present>
			</div>
		</div>
		<html:form action="/zysyyygl" method="post">
			<input type="hidden" name="yysbcheck" id="yysbcheck" value="<bean:write name="rs" property="yysb"/>"/>
			<input type="hidden" name="pkVStr" id="pkVStr" value="${pkval}"/>
			<table class="tbstyle" width="100%">
				<tr>
					<td align="right" width="15%">
						<font color="red">*</font>ʹ�ò��ţ�
					</td>
					<td align="left" width="35%">
						<logic:equal value="admin" name="userType">
							<html:select name="rs" property="bm" style="width:200px">
								<html:option value="">--��ѡ��--</html:option>
								<html:options property="bmdm" labelProperty="bmmc" collection="bmList"/>
							</html:select>
						</logic:equal>
						<logic:notEqual value="admin" name="userType">
							<html:select name="rs" property="bm" style="width:200px" disabled="true">
								<html:option value="">--��ѡ��--</html:option>
								<html:options property="bmdm" labelProperty="bmmc" collection="bmList"/>
							</html:select>
						</logic:notEqual>
					</td>
					<td align="right" width="15%">
						<font color="red">*</font>ԤԼ���ڣ�
					</td>
					<td align="left" width="35%">
						<html:text name="rs" property="yyrq" styleId="yyrq" readonly="true"
							onclick="this.value='';return showCalendar('yyrq','y-mm-dd');"
							onblur="getRqVal('yyrq');getWeek(this)"></html:text>
					</td>
				</tr>
				<tr>
					<td align="right">
						ԤԼ���ڣ�
					</td>
					<td align="left">
						<html:text name="rs" property="week" readonly="true"></html:text>
					</td>
					<logic:empty name="cdList">
						<td align="right" width="15%">
							����أ�
						</td>
						<td>
							<html:text property="hdcd" name="rs"></html:text>
						</td>
					</logic:empty>
					<logic:notEmpty name="cdList">
						<td align="right" width="15%">
							<font color="red">*</font>ԤԼ���أ�
						</td>
						<td align="left" width="35%">
							<html:select property="cddm" style="width:200px" name="rs">
								<html:option value="">--��ѡ��--</html:option>
								<html:options property="dm" labelProperty="mc" collection="cdList"/>
							</html:select>
						</td>
					</logic:notEmpty>
				</tr>				
				<tr>
					<td align="right">
						<font color="red">*</font>ԤԼʱ��Σ�
					</td>
					<td align="left" colspan="3">
						<html:select name="rs" property="hour" style="width:60px">
							<html:option value=""></html:option>
							<html:options property="dm" labelProperty="dm" collection="hourList"/>
						</html:select>&nbsp;ʱ
						<html:select name="rs" property="minute" style="width:60px">
							<html:options property="dm" labelProperty="dm" collection="minList"/>
						</html:select>&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<html:select name="rs" property="hour2" style="width:60px">
							<html:option value=""></html:option>
							<html:options property="dm" labelProperty="dm" collection="hourList"/>
						</html:select>&nbsp;ʱ
						<html:select name="rs" property="minute2" style="width:60px">
							<html:options property="dm" labelProperty="dm" collection="minList"/>
						</html:select>&nbsp;��
					</td>
				</tr>	
				<tr>
					<td align="right">
						������(����Ա)��
					</td>
					<td align="left">
						<html:text name="rs" property="fzr" maxlength="30"></html:text>
					</td>
					<td align="right">
						��ϵ�绰��
					</td>
					<td align="left">
						<html:text name="rs" property="lxdh" maxlength="40" onkeyup="value=value.replace(/[^\d]/g,'')" styleId="lxdh"></html:text>
					</td>
				</tr>		
				<logic:equal name="xxdm" value="11078">
					<tr>
					<td align="right" width="15%">
						����ʱ�䣺
					</td>
					<td align="left" width="35%" colspan="3">
						<input type="text" value="${rs.sqsj}" readonly="true"/>
					</td>
				</tr>
				</logic:equal>	
				<logic:notEmpty name="sbList">
					<tr>
					<td align="right" width="15%">
						ԤԼ�豸��
					</td>
					<td align="left" width="35%" colspan="3">
						<logic:iterate id="s" name="sbList">
							<input type="radio" name="sb" value="<bean:write name="s" property="dm"/>"/>
							<bean:write name="s" property="mc"/>&nbsp;&nbsp;
						</logic:iterate>
					</td>
				</tr>
				</logic:notEmpty>		
				<tr>
					<td>
						<div align="center">
							����ݣ�
							<br>
							<font color="red"><��1000����> </font>
						</div>
					</td>
					<td colspan="3">
						<html:textarea  name="rs" property="hdnr" rows="8" styleId="hdnr"
							style="width:100%" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��ע��
							<br>
							<font color="red"><��1000����> </font>
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="bz" rows="8" styleId="aqcs"
							style="width:100%" />
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<logic:equal value="update" name="act">
					<logic:equal value="yes" name="yhqx">
						<button type="button" class="button2" onClick="SaveSq()" id="buttonSave">
							�� ��
						</button>
					</logic:equal>
					<logic:notEqual value="yes" name="yhqx">
						<logic:equal value="δ���" name="rs" property="xxsh">
							<button type="button" class="button2" onClick="SaveSq()" id="buttonSave">
								�� ��
							</button>
						</logic:equal>
					</logic:notEqual>
				</logic:equal>			
				&nbsp;&nbsp;
				<button type="button" class="button2" onClick="window.close()" id="buttonSave">
					�� ��
				</button>
			</div>
		</html:form>
	
	<logic:equal value="true" name="result">
		<script type="text/javascript">
			 alert('�����ɹ���');
			 Close();
			 dialogArgumentsQueryChick();
		</script>
	</logic:equal>
	<logic:equal value="false" name="result">
		<script type="text/javascript">
			    alert('����ʧ�ܣ�');
			  </script>
	</logic:equal>
</body>
</html:html>
