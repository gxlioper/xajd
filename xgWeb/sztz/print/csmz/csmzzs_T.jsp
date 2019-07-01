<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="Pragma" http-equiv="no-cache" />
	<meta http-equiv="Cache-Control" http-equiv="no-cache" />
	<meta http-equiv="Expires" http-equiv="0" />
	<meta name="Copyright" content="正方软件 zfsoft" />
	
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
	<style media="print">
	.noPrin{display:none}
	</style>
<style>
.weizhi{
	position:absolute;
	left:1cm;
	top:1cm;
	}
.weizhi ul{
	list-style:none;
	margin:0px;
	padding:0px;
	
	}
.weizhi li{
	list-style:none;
	margin:13px 0px;
	padding:0px; 
	}
.weizhi h4{	
	margin:10px 0px;
	font-size:12px;
	font-weight:normal;
	break-all:keep-all
	}	

.htit{ 
	font-size:14px;
	font-style: oblique;
	font-family:黑体;	
	padding: 0px;
	}
.toxm{  
   font-size:11px;
   margin:0px 0px;
}	
div{
   cursor:hand;
   position: absolute; 
   word-break:keep-all
}
.clin{
   font-family:cursive;
   font-size:12px;
   color: red;
}	
</style>
<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
</head>
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/String.js"></script>
<script language="javascript" src="js/webPrint.js"></script>
<script language="javascript">
var flag = 0;
var dragswitch=0
var nsx
function drag_dropns(name){
temp=eval(name)
temp.captureEvents(Event.MOUSEDOWN | Event.MOUSEUP)
temp.onmousedown=gons
temp.onmousemove=dragns
temp.onmouseup=stopns
}
function gons(e){
temp.captureEvents(Event.MOUSEMOVE)
nsx=e.x
nsy=e.y
}
function dragns(e){
if (dragswitch==1){
temp.moveBy(e.x-nsx,e.y-nsy)
return false
}
}
function stopns(){
temp.releaseEvents(Event.MOUSEMOVE)
}
var dragapproved=false

function drag_dropie(obj){
if (dragapproved==true){
obj.style.pixelLeft=tempx+event.clientX-iex
obj.style.pixelTop=tempy+event.clientY-iey
return false
}
}
function initializedragie(obj){
iex=event.clientX
iey=event.clientY
tempx=obj.style.pixelLeft
tempy=obj.style.pixelTop
dragapproved=true
}
if (document.all){
document.onmouseup=new Function("dragapproved=false")
}

function clear_theone(obj){
   obj.style.display = "none";
}

function toPrint(xqRz){
    var xh = document.getElementById("xh").value;
    var fzrq = document.getElementById("fzrq").value;
    var xn = document.getElementById("xn").value;
    var mb = document.getElementById("mb").value;   
    var url = "/xgxt/csmz_sztzprintOne.do?mb="+mb+"&fzrq="+fzrq+"&xh="+xh+"&xn="+xn+"&xqRz="+xqRz;
    refreshForm(url);
}
function clinDes(){
   var xqRzAarry = ["one","tow","three"];
   var xqRz = document.getElementById("xqRz").value;
   for(i=0;i<3;i++){;
       if(xqRz==xqRzAarry[i]){
          document.getElementById(xqRzAarry[i]).style.display = "";
   }else{
          document.getElementById(xqRzAarry[i]).style.display = "none";
      }
   }
  }
function printField(){
  var obj = $("printField");
  obj.style.top = "50px";  
  if(flag==1){
     flag =0;
     $("flagBt").value="<==";
     $("flagBt").title="向左调整";
     obj.style.left = "350px";
  }else{
     flag =1;
     $("flagBt").value="==>";
     $("flagBt").title="向右调整";
     obj.style.left = "20px";
  }   
}  
</script>
<body onload="clinDes();printField();">
	<html:form action="/csmz_sztzprintOne" method="post">
		<input type="hidden" name="go" id="go" value="go" />
		<input type="hidden" name="fzrq" id="fzrq"
			value="<bean:write name="fzrq" />" />
		<input type="hidden" name="mb" id="mb"
			value="<bean:write name="mb" />" />
		<input type="hidden" name="xh" id="xh"
			value="<bean:write name="xh" />" />
		<input type="hidden" name="xn" id="xn"
			value="<bean:write name="xn" />" />
		<input type="hidden" name="xqRz" id="xqRz"
			value="<bean:write name="xqRz" />" />	
		<button class="button2" style="height:40px" id="search_go" style="display:none"
			onclick="refreshForm('xcxy_sztzprintOne.do?mb='+document.forms[0].mb.value+'&fzrq='+document.forms[0].fzrq.value+'&xh='+document.forms[0].xh.value)">
		</button>
		<div id="printField" style="position: absolute;" >		
			<table width="500px"  class="weizhi" >
				<tr style="cursor:hand;">
					<td align="center">
						<table width="100%">
						<tr>
							<td>
							<ul>
								<logic:iterate name="rs1" id="s">									
									<li style="margin-top:0.7em;">
	                           <div class="toxm" onmousedown="initializedragie(this)" 
	                           onMousemove="drag_dropie(this)" ondblclick="clear_theone(this)">										
											<logic:iterate id="v" name="s" offset="0">
												<bean:write name="v" />
											</logic:iterate>
										</div>											
									</li>
								</logic:iterate>
							</ul>
							</td>
							</tr>
						</table>
						<br>												
					</td>
				</tr>
				<tr style="cursor:hand;">
					<td align="center">
						<table width="100%">
							<tr>
							<td>
							<ul>
								<logic:iterate name="rs2" id="s">									
									<li style="margin-top:0.5em;">
										<div class="toxm" onmousedown="initializedragie(this)"
									onMousemove="drag_dropie(this)" ondblclick="clear_theone(this)">
											<logic:iterate id="v" name="s" offset="0">
												<bean:write name="v" />
											</logic:iterate>
										</div>
									</li>
								</logic:iterate>
							</ul>
							</td>
							</tr>
						</table>
					</td>
				</tr>				
			</table>
			</div>
			<div class='noPrin'>
			<TABLE width="99%" id="rsTable" class="formlist">	
			<thead>					
			<tr>
				<td align="left">
					证书具体事项及成绩:&nbsp;&nbsp;
					<bean:write name="xn" />
					学年&nbsp;&nbsp;&nbsp;&nbsp;
					<button  onclick="toPrint('one')">
						第一学期
					</button>
					<button  onclick="toPrint('tow')">
						第二学期
					</button>
					<button  onclick="toPrint('three')">
						第三学期
					</button>
					<div align="right" id="one" style="display: none">
						思想政治与道德素养，社会实践与志愿服务
					</div>
				
					<div align="right" id="tow" style="display: none">
						科技学术与创新创业，文化艺术与身心发展
					</div>
					<div align="right" id="three" style="display: none">
						社团活动与社会工作，技能培训及其它
					</div>				
				</td>				
			</tr>
			</thead>
			<tr>
			<td class="clin">
			注：1、双击某行记录可将该行记录删除；2、在某行记录上，按下鼠标左键的同时，可以拖动该记录并改变其显示位置;3、单击右边"<=="或"==>"可进行左右页位置调换;		
			<br>
			<button class='btn' onclick="printField()" id="flagBt"></button>
			<button   onclick="WebBrowser.ExecWB(8,1);return false;">
				页面设置
			</button>
			<button  onclick="WebBrowser.ExecWB(7,1);return false;">
				打印预览
			</button>
			<button  onclick="WebBrowser.ExecWB(6,6);return false;">
				直接打印
			</button>
			</td>
			</tr>
			
		</TABLE>
		</div>
		
			
	</html:form>
</body>
</html:html>
