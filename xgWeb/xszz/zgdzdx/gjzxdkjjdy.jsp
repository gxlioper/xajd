<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	   <style media='print'>
		.noPrin{display:none;}
	</style>
	
	</head>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript" src="/xgxt/js/AjaxFunction.js"></script>
	<object id=eprint classid="clsid:CA03A5A8-9890-49BE-BA4A-8C524EB06441"
		codebase="/xgxt/images/webprint.cab" viewasext>
	</object>
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<script language="javascript">
function Preview() 
{
	if (document.all.eprint.defaultPrinterName.length==0){
		alert("请先安装打印机，再执行此功能！");
		return;
	}
  document.all.eprint.InitPrint();
  document.all.eprint.SetMarginMeasure(1);//1mm是default, 2 inch
  document.all.eprint.orientation=1; //1:纵向，2：横向
  document.all.eprint.marginTop=132;
  document.all.eprint.marginLeft=5;
  document.all.eprint.marginRight=5;
  document.all.eprint.marginBottom=15;
  document.all.eprint.header = "";
  document.all.eprint.footer = "";
  document.all.eprint.Preview();//打印预览
}

function Print() {  
	if (document.all.eprint.defaultPrinterName.length==0){
		alert("请先安装打印机，再执行此功能！");
		return;
	}
  document.all.eprint.InitPrint();
  document.all.eprint.SetMarginMeasure(1);//1mm是default, 2 inch
  document.all.eprint.orientation=1; //1:纵向，2：横向
  document.all.eprint.marginTop=5;
  document.all.eprint.marginLeft=5;
  document.all.eprint.marginRight=5;
  document.all.eprint.marginBottom=15;
  document.all.eprint.header = "";
  document.all.eprint.footer = "";
  document.all.eprint.Print(true);//直接打印
}
</script>
<style>
body{
	text-align:center}
.dayin{
	font-size:2em;
	font-weight:bold;
	}
.bianhao{
	position: absolute;
	top:58px;
	left:779px;
	font-size:16px;
	font-weight:bold;
	}
.sheng{
	position: absolute;
	top:189px;
	left:208px;
	}
.qu{
	position: absolute;
	top:189px;
	left:511px;
	}
.zhongxue{
	position: absolute;
	top:242px;
	left:204px;
	}
.xingming{
	position: absolute;
	font-size:16px;
	top:297px;
	left:215px;
	}
.xi{
	position: absolute;
	font-size:16px;
	top:354px;
	left:536px;
	}
.zhuanye1{
	position: absolute;
	
	top:409px;
	left:217px;
	}
.zhuanye2{
	position: absolute;
	
	top:409px;
	left:217px;
	}
.nianfen{
	position: absolute;
	font-size:16px;
	top:474px;
	left:354px;
	}
.nianfen2{
	position: absolute;
	top:647px;
	left:766px;
	font-size:16px;
	font-weight:bold;
	}
.xz{
	position: absolute;
	font-size:16px;
	top:523px;
	left:276px;
	}
.nianfen3{
	position: absolute;
	font-size:16px;
	top:523px;
	left:276px;
	}
.ksh{
	position: absolute;
	font-size:16px;
	top:523px;
	left:779px;
	}
.sfzh{
	position: absolute;
	font-size:16px;
	top:69px;
	left:735px;
	}
</style>
<style media="print">
.noprint{
display:none}
.print{
display:block}
</style>
<body onload="printtype()">
 <script language ="javascript" type ="text/javascript">
  function printtype(){
		var topstr = new Array();
		var leftstr = new Array();
		var fontstr = new Array();
		if($("topstr").value != "" && $("leftstr").value != "" && $("fontstr").value != ""){
			topstr = ($("topstr").value).split("#");
			leftstr = ($("leftstr").value).split("#");
			fontstr = ($("fontstr").value).split("#");
			for(var i=0;i<topstr.length;i++){
				var obj = $(i.toString());
				if(obj == null) break;
				obj.style.top = topstr[i];
				obj.style.left = leftstr[i];
				document.getElementById(i).style.fontSize = fontstr[i];
			}
		}
	}
		</script>
		<input type="hidden" name="topstr" id="topstr" value="<bean:write name="rsMaptmp" property="topstr" />"/>
<input type="hidden" name="leftstr" id="leftstr" value="<bean:write name="rsMaptmp" property="leftstr" />"/>
<input type="hidden" name="fontstr" id="fontstr" value="<bean:write name="rsMaptmp" property="fontstr" />"/>
<div class="tab">
<div class="dayin">
<div class="xingming" id="0"><bean:write name="rsMap" property="xm" scope="request"/></div>
<div class="xingming" id="1"><bean:write name="rsMap" property="khh" scope="request"/></div>
<div class="xi" id="2"><bean:write name="rsMap" property="qs" scope="request"/></div>
<div class="xi" id="3"><bean:write name="rsMap" property="year" scope="request"/></div>
<div class="zhuanye1" id="4"><bean:write name="rsMap" property="mon" scope="request"/></div>
<div class="zhuanye2" id="5"><bean:write name="rsMap" property="day" scope="request"/></div>
<div class="xz" id="6"><bean:write name="rsMap" property="kkzh" scope="request" /></div>
<div class="xz" id="7"><bean:write name="rsMap" property="htbh" scope="request" /></div>
<div class="xz" id="8"><bean:write name="rsMap" property="qianW" scope="request" /></div>
<div class="xz" id="9"><bean:write name="rsMap" property="baiW" scope="request" /></div>
<div class="xz" id="10"><bean:write name="rsMap" property="shiW" scope="request" /></div>
<div class="xz" id="11"><bean:write name="rsMap" property="wan" scope="request" /></div>
<div class="xz" id="12"><bean:write name="rsMap" property="qian" scope="request" /></div>
<div class="xz" id="13"><bean:write name="rsMap" property="bai" scope="request" /></div>
<div class="xz" id="14"><bean:write name="rsMap" property="shi" scope="request" /></div>
<div class="xz" id="15"><bean:write name="rsMap" property="yuan" scope="request" /></div>
<div class="xz" id="16"><bean:write name="rsMap" property="jiao" scope="request" /></div>
<div class="xz" id="17"><bean:write name="rsMap" property="fen" scope="request" /></div>
<div class="xz" id="18"><bean:write name="rsMap" property="dkje_dx" scope="request" /></div>
<div class="xz" id="19"><bean:write name="rsMap" property="yearS1" scope="request" /></div>
<div class="xz" id="20"><bean:write name="rsMap" property="monS1" scope="request" /></div>
<div class="xz" id="21"><bean:write name="rsMap" property="dayS1" scope="request" /></div>
<div class="xz" id="22"><bean:write name="rsMap" property="yearS2" scope="request" /></div>
<div class="xz" id="23"><bean:write name="rsMap" property="monS2" scope="request" /></div>
<div class="xz" id="24"><bean:write name="rsMap" property="dayS2" scope="request" /></div>
<div class="xz" id="25"><bean:write name="rsMap" property="yearB1" scope="request" /></div>
<div class="xz" id="26"><bean:write name="rsMap" property="monB1" scope="request" /></div>
<div class="xz" id="27"><bean:write name="rsMap" property="dayB1" scope="request" /></div>
<div class="xz" id="28"><bean:write name="rsMap" property="yearB2" scope="request" /></div>
<div class="xz" id="29"><bean:write name="rsMap" property="monB2" scope="request" /></div>
<div class="xz" id="30"><bean:write name="rsMap" property="dayB2" scope="request" /></div>
<div class="xz" id="31"><bean:write name="rsMap" property="jkyt" scope="request" /></div>
<div class="xz" id="32"><bean:write name="rsMap" property="dkll" scope="request" /></div>
<div class="xz" id="33"><bean:write name="rsMap" property="hm" scope="request" /></div>
<div class="xz" id="34"><bean:write name="rsMap" property="khyh" scope="request" /></div>
<div class="xz" id="35"><bean:write name="rsMap" property="zh" scope="request" /></div>
<br/>
</div>
           <div align="center" class='noPrin'>
         	<button type="button" class=''  onclick="WebBrowser.ExecWB(8,1);return false;">页面设置</button>
    <button type="button" class=''  onclick="WebBrowser.ExecWB(7,1);return false;">打印预览</button>
    <button type="button" class=''  onclick="WebBrowser.ExecWB(6,6);return false;">直接打印</button> 
		</div>
</body>
</html>