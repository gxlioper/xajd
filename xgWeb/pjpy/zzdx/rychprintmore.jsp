<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title>szxxxszs.jsp</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
	margin:26px 0px;
	padding:0px;
	}
.weizhi h4{
	float:left;
	margin:0px 20px;
	font-size:16px;
	font-family:黑体;
	font-weight:normal;
	break-all:keep-all
	}	
.youbianxia{
	margin-top:5px;
	}
.yueri{
	margin-left:5px}
</style>
  </head>
  	<script language="javascript" src="js/function.js"></script>
  	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="/xgxt/dwr/interface/getPagePara.js"></script>
	<script language="javascript" src="/xgxt/dwr/engine.js"></script>
	<script language="javascript" src="js/BatAlert.js"></script>
	<script language="javascript" src="js/webPrint.js"></script>
	<object id=eprint classid="clsid:CA03A5A8-9890-49BE-BA4A-8C524EB06441" codebase="images/webprint.cab" viewasext> </object>
<script language="javascript">
function Preview() 
{
	if (document.all.eprint.defaultPrinterName.length==0){
		alert("请先安装打印机，再执行此功能！");
		return;
	}
  document.all.eprint.InitPrint();
  document.all.eprint.SetMarginMeasure(1);//1mm是default, 2 inch
  document.all.eprint.marginTop=92;
  document.all.eprint.marginLeft=20;
  document.all.eprint.marginRight=20;
  document.all.eprint.marginBottom=10;
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
  document.all.eprint.header = "";
  document.all.eprint.footer = "";
  document.all.eprint.Print(true);//直接打印
}

var top= new Array();
	var left = new Array();
    var x,y;
    var zd = ["xm","hjqsxn","hjjsxn","zhi","hjxjmc1","fzxn","fzy"];
	
	var dragapproved=false
   	//function initializedragie(obj){
			//iex=event.clientX
			//iey=event.clientY
			//tempx=obj.style.pixelLeft
			//tempy=obj.style.pixelTop
			//dragapproved=true
		//}
	//function drag_dropie(obj){
		//if (dragapproved==true){
		//obj.style.pixelLeft=tempx+event.clientX-iex;
		//obj.style.pixelTop=tempy+event.clientY-iey;
		//var topStri = "";
		//var leftStri = "";
		//for(var i=0;i<zd.length;i++){
			//var tempzd = document.getElementById(zd[i]);
			//topStri += tempzd.style.pixelTop+"#";
			//leftStri += tempzd.style.pixelLeft+"#";
		//}
		//getPagePara.savePagePara(topStri,leftStri,'szxxxszs',function(){});
		//return false;
		//}
	//}
	//if (document.all){
		//document.onmouseup=new Function("dragapproved=false")
	//}
	
	window.onload = function(){
		var topstr = new Array();
		var leftstr = new Array();
		if(document.getElementById("topstr").value != "" && document.getElementById("leftstr").value != ""){
			topstr = (document.getElementById("topstr").value).split("#");
			leftstr = (document.getElementById("leftstr").value).split("#");			
			for(var i=0;i<topstr.length;i++){
				var obj = document.getElementById(zd[i]);
				if(obj == null) break;
				obj.style.top = topstr[i];
				obj.style.left = leftstr[i];
			}
		}
		
	Print(true);
	setTimeout("",1000);
	var cu_R = window.opener.curr_row.rowIndex;
	if(cu_R >= window.opener.$("tabPri").rows.length){
		BatAlert.MyAlert("打印结束!","",function(){
			Close();
		});
		return false;
	}else{
		window.opener.rowOnClick(window.opener.$("tabPri").rows[cu_R]);//单击下一行
		var xm = window.opener.curr_row.cells[2].innerText.trim();
		window.location.href = "rychprintmore.do?xm="+xm+'&hjxn='+document.getElementById('hjxn').value+'&hjxjmc='+document.getElementById('hjxjmc').value+'&hjny='+document.getElementById('hjny').value;
	}
	}
	
	function savePosition(){
		dragapproved=false;
		var topStri = "";
		var leftStri = "";
		for(var i=0;i<zd.length;i++){		  
			var tempzd = document.getElementById(zd[i]);
			if(i==zd.length-1){
				topStri += tempzd.style.pixelTop;
				leftStri += tempzd.style.pixelLeft;
				continue;
			}
			topStri += tempzd.style.pixelTop+"#";
			leftStri += tempzd.style.pixelLeft+"#";
		}
		
		getPagePara.savePagePara(topStri,leftStri,'rych',function(){});
		return false;
		}
</script>
 <body>
 <input type="hidden" name="hjxn" id="hjxn" value="${hjxn }"/>
   <input type="hidden" name="hjxjmc" id="hjxjmc" value="${hjxjmc }"/>
    <input type="hidden" name="hjny" id="hjny" value="${hjny }"/>
		<input type="hidden" name="topstr" id="topstr" value="${topstr }"/>
  		<input type="hidden" name="leftstr" id="leftstr" value="${leftstr }"/>
  <html:form action="/pjpyzzdxwh.do" method="post">
   <table width="500" class="weizhi"> 
  <tr> 
    <td width="50%">
    <ul style="margin-top:10em;"> 
        <li> 
          <h4><div  style="position: absolute; word-break:keep-all;left: 168px; top: 245px;" title="姓名" id="xm" onMouseDown="initializedragie(this)" onMouseMove="drag_dropie(this)" onmouseup="savePosition()">${xm }</div></h4> 
        </li> 
        <li> 
          
        </li> 
      </ul></td> 
    <td>
    <ul> 
        <li class="youbianxia"> 
          <h4><div  style="position: absolute; left: 488px; top: 168px;" title="获奖起始学年度" id="hjqsxn" onMouseDown="initializedragie(this)" onMouseMove="drag_dropie(this)" onmouseup="savePosition()">${hjqsxn }</div></h4> 
          <h4 class="yueri"><div   style="position: absolute; left: 623px; top: 171px;" title="获奖结束学年度" id="hjjsxn" onMouseDown="initializedragie(this)" onMouseMove="drag_dropie(this)" onmouseup="savePosition()">${hjjsxn }</div></h4> 
          
        </li> 
        <li> 
          <h4 class="yueri"><div style="position: absolute; left: 620px; top: 217px;" title="学年度连接线" id="zhi" onMouseDown="initializedragie(this)" onMouseMove="drag_dropie(this)" onmouseup="savePosition()">${zhi }</div></h4>  
          <h4 class="yueri"><div style="position: absolute; left: 620px; top: 217px;" title="发证年份" id="fzxn" onMouseDown="initializedragie(this)" onMouseMove="drag_dropie(this)" onmouseup="savePosition()">${fzxn }</div></h4> 
          <h4><div  style="position: absolute; left: 737px; top: 217px;" title="发证月份" id="fzy" onMouseDown="initializedragie(this)" onMouseMove="drag_dropie(this)" onmouseup="savePosition()">${fzy }</div></h4> 
        	<h4><div style="position: absolute; left: 733px; top: 170px;" title="评奖名称" id="hjxjmc1" onMouseDown="initializedragie(this)" onMouseMove="drag_dropie(this)" onmouseup="savePosition()" nowrap>${hjxjmc }</div></h4> 
        </li> 
     </ul></td> 
  </tr> 
</table> 
</html:form>
<script>
</script>
  </body>
</html:html>
