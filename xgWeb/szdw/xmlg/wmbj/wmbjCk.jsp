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
	function saveHdsq(obj){
		var flg = false;
		var url = '/xgxt/xmlgwmbj.do?method=wmbjCk&doType=save';
		var xn = $("xn").value;
		var xq = $("xq").value;
		
		dwr.engine.setAsync(false);
		getXmlgSzdwDAO.getWmbjZdyZd(xn,xq,function (data){
			if(data != null && data.length>0){
				for(var i=0;i<data.length;i++){
					var id = data[i].zd;
					if($(id)){
						if($(id).value != ""){
							var tmp = document.createElement("input");
							tmp.type = "hidden";
							tmp.name = id;
							tmp.value = $(id).value;
							document.forms[0].appendChild(tmp);
						}
					}
				}
			}else{
				alert("本次文明班级申报还未设定申报内容，请稍候申请！！");
				flg=true;
			}
		});
		dwr.engine.setAsync(true);
		
		if(flg){
			return false;
		}
		if (confirm("确认所修改申报内容？\n点击\"确定\"，提交修改。\n点击\"取消\"，将放弃修改！")) {
			showTips('处理数据中，请等待......');
			refreshForm(url);
			obj.disabled=true;
		}
	}
	
	function gettime(id){
		return showCalendar(id,'y-mm-dd');
	}
	
	function setSbxx(){
		var sbbj = $("bjdm").value;
		var xn = $("xn").value;
		var xq = $("xq").value;
		
		dwr.engine.setAsync(false);
		getXmlgSzdwDAO.getWmbjSbnr(xn,xq,sbbj,function (data){
			var hdnr1 = new Array();
			for(var i=0;i<data.length;i++){
				var zdz="";
				if(data[i].zdz !=""&&data[i].zdz!=null){
					zdz=data[i].zdz;
				}
				if (data[i].zdlx == "003"){
				
					var hdnr2 = new Array();
					
					hdnr2[0]=function(da){
						    return "<div align='right'>"+data[i].zdm+"：</div>";
						    }
						    
					hdnr2[1]=function(da){
						    return "<textarea type='text' style='width:100%' id='"
						    +data[i].zd
						    +"' name='"
						    +data[i].zd
						    +"'rows='5' onblur='chLeng(this,500)'>"
						    +zdz
						    +"</textarea>";	    	
						    }
					
					DWRUtil.addRows("hdnrarea",[''],hdnr2,{escapeHtml:false});
					
				}else{
					if(hdnr1.length == 0){
						if (data[i].zdlx == "002"){
							hdnr1[0]= document.createElement("div");
							hdnr1[0].align="right"
							hdnr1[0].innerHTML=data[i].zdm+"：";
							
							hdnr1[1]= document.createElement("input");
						    hdnr1[1].style.cursor="hand;";
							hdnr1[1].name=data[i].zd;
							hdnr1[1].id=data[i].zd;
							hdnr1[1].onclick=function(){gettime(this.id)};
							hdnr1[1].onblur=function(){dateFormatChg(this)}
							hdnr1[1].value=zdz;
						}else{
							hdnr1[0]= document.createElement("div");
							hdnr1[0].align="right"
							hdnr1[0].innerHTML=data[i].zdm+"：";
							
							hdnr1[1]= document.createElement("input");
							hdnr1[1].maxLength="20";
							hdnr1[1].name=data[i].zd;
							hdnr1[1].id=data[i].zd;
							hdnr1[1].value=zdz;
						}
					continue;
					}else{
						if (data[i].zdlx == "002"){
						
							hdnr1[2]= document.createElement("div");
							hdnr1[2].align="right"
							hdnr1[2].innerHTML=data[i].zdm+"：";
							
							hdnr1[3]= document.createElement("input");
							hdnr1[3].style.cursor="hand;";
							hdnr1[3].name=data[i].zd;
							hdnr1[3].id=data[i].zd;
							hdnr1[3].onclick=function(){gettime(this.id)};
							hdnr1[3].onblur=function(){dateFormatChg(this)}
							hdnr1[3].value=zdz;
						}else{
							hdnr1[2]= document.createElement("div");
							hdnr1[2].align="right"
							hdnr1[2].innerHTML=data[i].zdm+"：";
							
							hdnr1[3]= document.createElement("input");
							hdnr1[3].maxLength="20";
							hdnr1[3].name=data[i].zd;
							hdnr1[3].id=data[i].zd;		
							hdnr1[3].value=zdz;	
						}
						DWRUtil.addRows("hdnrtext",['dd'],hdnr1);
						hdnr1 = new Array();
					}
				}
			}
			if(hdnr1.length != 0){
				DWRUtil.addRows("hdnrtext",['dd'],hdnr1);
			}
		});
		dwr.engine.setAsync(true);
	}
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="setSbxx()">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getXmlgSzdwDAO.js'></script>
		<html:form action="/xmlgwmbj" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：<bean:write name="title" />
				</div>
			</div>
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="msg" name="msg" value="${msg}"/>
				<input type="hidden" id="pk" name="pk" value="${pk}"/>
				<fieldset>
					<legend>
						文明班级申报
					</legend>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								学年：
							</td>
							<td align="left">
								<html:hidden name="rs" property="xn"/>
								<html:select name="rs" property="xn" style="" disabled="true">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>	
							</td>
							<td align="right">
								学期：
							</td>
							<td align="left">
								<html:hidden name="rs" property="xq"/>
								<html:select name="rs" property="xq" style="width:80px" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>	
							</td>
						</tr>
						<tr>
							<td align="right" style="width:15%">
								申报人学号：
							</td>
							<td align="left" style="width:35%">
								<bean:write name="rs" property="xh"/>
								<html:hidden name="rs" property="xh"/>
							</td>
							<td align="right" style="width:15%">
								申报人姓名：
							</td>
							<td align="left">
								<bean:write name="rs" property="xm"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								申报人班级：
							</td>
							<td align="left">
								<bean:write name="rs" property="bjmc"/>
								<html:hidden name="rs" property="bjdm"/>
							</td>
							<td align="right">
								班级人数：
							</td>
							<td align="left">
								<bean:write name="rs" property="bjrs"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								班长：
							</td>
							<td align="left">
								<bean:write name="rs" property="bzxm"/>
							</td>
							<td align="right">
								班主任：
							</td>
							<td align="left">
								<bean:write name="rs" property="bzr"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								团支部书记：
							</td>
							<td align="left">
								<bean:write name="rs" property="tzbsj"/>
							</td>
							<td align="right">
								
							</td>
							<td align="left">
								
							</td>
						</tr>
					</table>
					<table width="100%" class="tbstyle">
						<tbody width="100%" class="tbstyle" id="hdnrtext">
							<tr>
							<td style="width:15%"></td>
							<td style="width:35%"></td>
							<td style="width:15%"></td>
							<td></td>
							</tr>
						</tbody>
					</table>
					<table width="100%" class="tbstyle">
						<tbody width="100%" class="tbstyle" id="hdnrarea">
							<tr>
							<td style="width:15%"></td>						
							<td style="width:85%"></td>
							</tr>
						</tbody>
					</table>			
				</fieldset>
				<div class="buttontool" id="btndiv">
					<logic:notEqual name="doType" value="view">
					<button type="button" class="button2"
						onclick="saveHdsq(this);"
						style="width:80px" id="buttonSave">
						保	存
					</button>
					&nbsp;&nbsp;
					</logic:notEqual>
					<button type="button" class="button2"
						onclick="Close();return false;"
						style="width:80px" id="buttonSave">
						关	闭
					</button>
				</div>
				<logic:present name="result">
					<logic:equal name="result" value="yes">
						<script>
					    alert("操作成功！");
					    dialogArgumentsQueryChick();
						Close();
					    </script>
					</logic:equal>
					<logic:equal name="result" value="no">
						<script>
					    alert("操作失败！");
					    </script>				
					</logic:equal>
				</logic:present>
		</html:form>
	</body>
</html>
