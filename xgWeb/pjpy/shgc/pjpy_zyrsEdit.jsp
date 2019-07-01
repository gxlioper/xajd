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
	<style>
	 .szrs{}
	 .xymcI{}
	 .xyrs{}
	</style>
	
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/prototype-1.6.0.3.js"></script>
	<script language="javascript">
		function yz(){
			var szrsArr = $$('input.szrs');	
			var zymcIArr = $$('input.zymcI');
			var zyrsArr = $$('input.zyrs');
			var zrsObj = $('zrs').value;
			var sumSzrs	= 0;
			for(var i=0;i<szrsArr.length;i++){
				if(toInt(szrsArr[i].value) > toInt(zyrsArr[i].value)){
					alert(zymcIArr[i].value+" 人数超出了限制！");
					return false;
				} else {
				  	sumSzrs += toInt(szrsArr[i].value);
				}
			}
			if(sumSzrs > zrsObj){
			   alert("已设置人"+sumSzrs+"人，超出限制！");
			   return false;
			}
			document.forms[0].action = "/xgxt/shgc_pjpy_new.do?method=shgc_pjpy_zyrsEdit&act=save";
			document.forms[0].submit();
		}
		
		function sx(){
			document.forms[0].action = "/xgxt/shgc_pjpy_new.do?method=shgc_pjpy_zyrsEdit";
			document.forms[0].submit();
		}
		
		function fp(){
			var bfb = $('bfb').value;
			var zyrsArr = $$('input.zyrs');
			var szrsArr = $$('input.szrs');
			var zrs = 0;
			
			if (bfb == null || bfb == ""){
				$('bfb').value = 0;
				bfb = 0;
			}
			
			if (toInt(bfb) > 100){
				$('bfb').value = 100;
				bfb = 100;
			}
			
			for(var i=0;i<szrsArr.length;i++){
				szrsArr[i].value = Math.round(toInt(zyrsArr[i].value)*toInt(bfb)/100);
				zrs += Math.round(toInt(zyrsArr[i].value)*toInt(bfb)/100);
			}
			document.getElementById('yfprs').value = zrs;
		}
		
		function jsrs(){
			var szrsArr = $$('input.szrs');	
			var sumSzrs	= 0;
			
			for(var i=0;i<szrsArr.length;i++){
				sumSzrs += toInt(szrsArr[i].value);
			}
			document.getElementById('yfprs').value = sumSzrs;
		}
		
		function szbjrs(bbdm,zydm){
			var url = "/xgxt/shgc_pjpy_new.do?method=shgc_pjpy_bjrsEdit";
			url += "&bbdm="+bbdm;
			url += "&zydm="+zydm;
			return showTopWin(url,620,800);
		}
	</script>
</head>

<body onload="jsrs();">
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：评奖评优 - 基础数据维护 - 专业具体人数维护
		</div>
	</div>
	<html:form action="/shgc_pjpy_new.do?method=shgc_pjpy_zyrsEdit" method="post">

		<input type="hidden" id="bbdm" name="bbdm"
				value="<bean:write name="bbdm" />">
		<input type="hidden" id="xydm" name="xydm"
				value="<bean:write name="xydm" />">
		<logic:present name="end">
			<logic:match value="end" name="end">
				<script language="javascript">
	         	alert("设置完成！");
	         	</script>
			</logic:match>
		</logic:present>
		<table class="tbstyle"  width="100%">
			<tr>
				<td width="50%">
					<div align="center">
						项目/<bean:message key="lable.xsgzyxpzxy" />
					</div>
				</td>
				<td colspan="2">
					<bean:write name="bbmc" />&nbsp;
					<bean:write name="xymc" />
				</td>
			</tr>
			<tr>
				<td width="50%">
					<div align="center">
						总人数
					</div>
				</td>
				<td colspan="2">
					<bean:write name="zrs" />
					<input type="hidden" id="zrs" name="zrs" value="<bean:write name="zrs" />" />
				</td>
			</tr>
			<tr>
				<td width="50%">
					<div align="center">
						已分配总人数
					</div>
				</td>
				<td colspan="2">
					<input type="text" id="yfprs" name="yfprs" readonly="readonly"
						style="width:100%;heigh:100%"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<td width="50%">
					<div align="center">
						全部分配
					</div>
				</td>
				<td width="20%">
					<input type="text" id="bfb" name="bfb" maxlength="3"
						style="width:50%;heigh:100%"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					%
				</td>
				<td width="30%">
					<button class="button2" onClick="fp();">
						自动分配
					</button>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td width="40%" align="center">
									专业名称
								</td>
								<td width="15%" align="center">
									专业人数
								</td>
								<td width="10%" align="center">
									设置人数
								</td>
								<td width="15%" align="center">
									已分配人数
								</td>
								<td width="20%" align="center">
									设置班级人数
								</td>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr>
								<td align="center">
									<input type="hidden" name="pks" value="${s.pks}" />
									<input type="hidden" class="zymcI" name="zymcI" value="${s.zymc}" />
									${s.zymc}
								</td>
								<td align="center">
									<input type="hidden" class="zyrs" name="zyrs" value="${s.zyrs}" />
									${s.zyrs}
								</td>
								<td align="center">
									<input type="text" class="szrs" id="szrs" name="szrs" maxlength="5" onblur="jsrs();"
										style="width:100%;heigh:100%" value="${s.szrs}"
										onkeyup="value=value.replace(/[^\d]/g,'') "
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
								</td>
								<td align="center">
									${s.yfprs}
								</td>
								<td align="center">
									<button class="button2" onClick="szbjrs('${s.bbdm}','${s.zydm}');">
										设置
									</button>
								</td>
							</tr>
						</logic:iterate>
					</table>
				</td>
			</tr>
		</table>
		<logic:equal name="writeAble" value="yes">
			<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<button class="button2" onClick="yz();" id="bt" name="bt">
					保&nbsp;&nbsp;&nbsp;&nbsp;存
				</button>
				<button class="button2" onClick="sx();" id="search_go_zy" name="search_go_zy">
					刷&nbsp;&nbsp;&nbsp;&nbsp;新
				</button>
				<button class="button2"
					onClick="Close();window.dialogArguments.document.getElementById('search_go_xy').click();">
					关&nbsp;&nbsp;&nbsp;&nbsp;闭
				</button>
			</div>
		</logic:equal>
	</html:form>
</body>
</html:html>
