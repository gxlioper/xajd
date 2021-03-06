<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<base target="_self" />
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
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
  document.all.eprint.marginTop=5;
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
.bianhao1{
	font-size:16px;
	font-weight:bold;
	}
.bianhao2{
	font-size:16px;
	font-weight:bold;
	}
.sheng1{
	font-size:16px;
	}
.sheng2{
	font-size:16px;
	}
.qu1{
	font-size:16px;
	}
.qu2{
	font-size:16px;
	}
.zhongxue1{
	font-size:16px;
	}
.zhongxue2{
	font-size:16px;
	}
.xingming1{
	font-size:16px;
	}
.xingming2{
	font-size:16px;
	}
.xi1{
	font-size:16px;
	}
.xi2{
	font-size:16px;
	}
.zhuanye1{
	font-size:16px;
	}
.zhuanye2{
	font-size:16px;
	}
.nianfen1{
	font-size:16px;
	}
.nianfen2{
	font-size:12px;
	font-weight:bold;
	}
.nianfen3{
	font-size:12px;
	font-weight:bold;
	}
.xz1{
	font-size:16px;
}
.xz2{
	font-size:16px;
}
.ksh1{
	font-size:16px;
}
.ksh2{
	font-size:16px;
}
</style>
	<style media="print">
.noprint{
display:none}
.print{
display:block}

</style>
	<body id="noticeprint">
		<script type="text/javascript">
			var top= new Array();
			var left = new Array();
			var font = new Array();
		    var x,y;
		    
			function getResult()
		    {
		    	var topstring = "";
				var leftstring = "";
				var fontstring = "";
			    var tag = 0;
		    	var obj = $(tag.toString());
		    	while(obj != null){
					topstring = topstring + obj.offsetTop + "#";
					leftstring = leftstring + obj.offsetLeft + "#";
					fontstring = fontstring + document.getElementById(tag).style.fontSize + "#";
					tag++;
					obj = $(tag.toString());
		    	}
				$("topstr").value = topstring;
				$("leftstr").value = leftstring;
				$("fontstr").value = fontstring;
			}
			
			function clickEven(obj){
				if(document.getElementById("zt")){
					var div = document.getElementById("zt");
					div.parentNode.removeChild(div);
					}
				if(focusObj){
					focusObj.style.color = 'black';
				}
				focusObj = obj;
				obj.style.color = 'red';
			}
			
			if (document.all){
				var focusObj = null;
				document.onkeydown = function(){
					if(focusObj){
						tempx = focusObj.style.pixelLeft
						tempy = focusObj.style.pixelTop
						switch(event.keyCode){
							case 37:
								focusObj.style.pixelLeft = tempx - 1;
								break;
							case 38:
								focusObj.style.pixelTop = tempy - 1;
								break;
							case 39:
								focusObj.style.pixelLeft = tempx + 1;
								break;	
							case 40:
								focusObj.style.pixelTop = tempy + 1;
								break;		
						}
					}
				}
			}
			
			function setFont(data){
				if(document.getElementById("zt")){
					var div = document.getElementById("zt");
					div.parentNode.removeChild(div);
					}
				var id=data;
				var div = document.createElement("div");
				var divString = "<div id='zt' style='position: absolute;top:50px;left:700px;'>\n"
				divString += "<table>\n";
				divString += "<tr>\n";
				divString += "<td>\n";
				divString += "字体大小:\n";
				divString += "</td>\n";
				divString += "<td>\n";
				divString += "<select id='fontsize' name='fontsize' style='width:50px'>\n";
				divString += "<option value='12px'>12</option>\n";
				divString += "<option value='13px'>13</option>\n";
				divString += "<option value='14px'>14</option>\n";
				divString += "<option value='15px'>15</option>\n";
				divString += "<option value='16px'>16</option>\n";
				divString += "<option value='17px'>17</option>\n";
				divString += "<option value='18px'>18</option>\n";
				divString += "<option value='19px'>19</option>\n";
				divString += "<option value='20px'>20</option>\n";
				divString += "<option value='21px'>21</option>\n";
				divString += "<option value='22px'>22</option>\n";
				divString += "<option value='23px'>23</option>\n";
				divString += "<option value='24px'>24</option>\n";
				divString += "</select><input type='button' class='button2' value='确定' onclick='sentback("+id+")'/>\n";
				divString += "</td>\n";
				divString += "</tr>\n";
				divString += "</table>\n";
				div.innerHTML = divString;
				var obj=document.getElementById("noticeprint");
   				obj.appendChild(div);
				$("fontsize").value = document.getElementById(id).style.fontSize;
			}
			function sentback(data){
				var id=data;
				var tmp = $("fontsize").value;
				if(tmp != null){
					document.getElementById(id).style.fontSize=tmp;
					var div = document.getElementById("zt");   
  					div.parentNode.removeChild(div);   //删除
					return true;
					
				}
				alert("输入的值必须大于0");
				return false;
			}
			
			window.onload = function(){
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
				//alert(i+'-'+obj.style.top+'|'+obj.style.left);
					}
				}
			}
		</script>
		<logic:notPresent name="back">
			<form action="/errMsg" method="post">
				<input type="hidden" name="topstr" id="topstr"
					value="<bean:write name="rsMap" property="topstr" />" />
				<input type="hidden" name="leftstr" id="leftstr"
					value="<bean:write name="rsMap" property="leftstr" />" />
				<input type="hidden" name="fontstr" id="fontstr"
					value="<bean:write name="rsMap" property="fontstr" />" />
				<div class="dayin">
					<div class="xingming1"
						style="position: absolute;top:59px;left:60px;" id="0"
						onclick="clickEven(this);setFont(this.id)">
						{姓名}
					</div>
					<div class="xingming2"
						style="position: absolute;top:456px;left:175px;" id="1"
						onclick="clickEven(this);setFont(this.id)">
						{合同号}
					</div>
					<div class="xi1" id="2"
						style="position: absolute;top:457px;left:395px;"
						onclick="clickEven(this);setFont(this.id)">
						{期数}
					</div>
					<div class="xi1" id="3"
						style="position: absolute;top:457px;left:395px;"
						onclick="clickEven(this);setFont(this.id)">
						{年}
					</div>
					<div class="xi1" id="4"
						style="position: absolute;top:457px;left:395px;"
						onclick="clickEven(this);setFont(this.id)">
						{月}
					</div>
					<div class="xi1" id="5"
						style="position: absolute;top:457px;left:395px;"
						onclick="clickEven(this);setFont(this.id)">
						{日}
					</div>
					<div class="xi2" id="6"
						style="position: absolute;top:180px;left:278px;"
						onclick="clickEven(this);setFont(this.id)">
						{扣款帐号}
					</div>
					<div class="zhuanye1"
						style="position: absolute;top:457px;left:606px;" id="7"
						onclick="clickEven(this);setFont(this.id)" >
						{贷款账号}
					</div>
					<div class="zhuanye2"
						style="position: absolute;top:180px;left:425px;" id="8"
						onclick="clickEven(this);setFont(this.id)">
						{千(万)}
					</div>
					<div class="zhuanye2"
						style="position: absolute;top:180px;left:425px;" id="9"
						onclick="clickEven(this);setFont(this.id)">
						{百(万)}
					</div>
					<div class="zhuanye2"
						style="position: absolute;top:180px;left:425px;" id="10"
						onclick="clickEven(this);setFont(this.id)">
						{十(万)}
					</div>
					<div class="zhuanye2"
						style="position: absolute;top:180px;left:425px;" id="11"
						onclick="clickEven(this);setFont(this.id)">
						{万}
					</div>
					<div class="zhuanye2"
						style="position: absolute;top:180px;left:425px;" id="12"
						onclick="clickEven(this);setFont(this.id)">
						{千}
					</div>
					<div class="zhuanye2"
						style="position: absolute;top:180px;left:425px;" id="13"
						onclick="clickEven(this);setFont(this.id)">
						{百}
					</div>
					<div class="zhuanye2"
						style="position: absolute;top:180px;left:425px;" id="14"
						onclick="clickEven(this);setFont(this.id)">
						{十}
					</div>
					<div class="zhuanye2"
						style="position: absolute;top:180px;left:425px;" id="15"
						onclick="clickEven(this);setFont(this.id)">
						{元}
					</div>
					<div class="zhuanye2"
						style="position: absolute;top:180px;left:425px;" id="16"
						onclick="clickEven(this);setFont(this.id)">
						{角}
					</div>
					<div class="zhuanye2"
						style="position: absolute;top:180px;left:425px;" id="17"
						onclick="clickEven(this);setFont(this.id)">
						{分}
					</div>
					<div class="xz1" style="position:absolute;top:508px;left:188px;"
						id="18" onclick="clickEven(this);setFont(this.id)">
						{借款金额（大写）}
					</div>
					<div class="xz2" style="position:absolute;top:218px;left:101px;"
						id="19" onclick="clickEven(this);setFont(this.id)">
						{年(首1)}
					</div>
					<div class="xz2" style="position:absolute;top:218px;left:101px;"
						id="20" onclick="clickEven(this);setFont(this.id)">
						{月(首1)}
					</div>
					<div class="xz2" style="position:absolute;top:218px;left:101px;"
						id="21" onclick="clickEven(this);setFont(this.id)">
						{日(首1)}
					</div>
					<div class="xz2" style="position:absolute;top:218px;left:101px;"
						id="22" onclick="clickEven(this);setFont(this.id)">
						{年(首2)}
					</div>
					<div class="xz2" style="position:absolute;top:218px;left:101px;"
						id="23" onclick="clickEven(this);setFont(this.id)">
						{月(首2)}
					</div>
					<div class="xz2" style="position:absolute;top:218px;left:101px;"
						id="24" onclick="clickEven(this);setFont(this.id)">
						{日(首2)}
					</div>
					<div class="nianfen1"
						style="position: absolute;top:217px;left:398px;" id="25"
						onclick="clickEven(this);setFont(this.id)">
						{年(本1)}
					</div>
					<div class="nianfen1"
						style="position: absolute;top:217px;left:398px;" id="26"
						onclick="clickEven(this);setFont(this.id)">
						{月(本1)}
					</div>
					<div class="nianfen1"
						style="position: absolute;top:217px;left:398px;" id="27"
						onclick="clickEven(this);setFont(this.id)">
						{日(本1)}
					</div>
					<div class="nianfen1"
						style="position: absolute;top:217px;left:398px;" id="28"
						onclick="clickEven(this);setFont(this.id)">
						{年(本2)}
					</div>
					<div class="nianfen1"
						style="position: absolute;top:217px;left:398px;" id="29"
						onclick="clickEven(this);setFont(this.id)">
						{月(本2)}
					</div>
					<div class="nianfen1"
						style="position: absolute;top:217px;left:398px;" id="30"
						onclick="clickEven(this);setFont(this.id)">
						{日(本2)}
					</div>
					<div class="nianfen2"
						style="position: absolute;top:511px;left:398px;" id="31"
						onclick="clickEven(this);setFont(this.id)">
						{借款用途}
					</div>
					<div class="nianfen3" style="position: absolute;top:355px;left:512px;" id="32"
						onclick="clickEven(this);setFont(this.id)">
						{月利率}
					</div>
					<div class="ksh1" style="position:absolute;top:504px;left:608px;"
						id="33" onclick="clickEven(this);setFont(this.id)">
						{户名}
					</div>
					<div class="ksh2" style="position:absolute;top:354px;left:129px;"
						id="34" onclick="clickEven(this);setFont(this.id)">
						{开户行}
					</div>
					<div class="bianhao3" style="position:absolute;top:69px;left:494px;"
						id="35" onclick="clickEven(this);setFont(this.id)">
						{帐号}
					</div>
					<br />
					<center>
						<div class="noPrin noprint"
							style="position:absolute;top:500px;left:350px;">
							<input type='button' class='button2' value='打印预览'
								onclick="Preview();">
							<input type='button' class='button2' value='直接打印'
								onclick="Print(true);">
							<input type='button' class='button2' value='保    存'
								onclick="getResult();refreshForm('/xgxt/zgdzdx_xszz.do?method=gjzxdkgs&act=save');">
						</div>
					</center>
				</div>
			</form>
		</logic:notPresent>
		<logic:present name="back">
			<script type="text/javascript">
				window.onunload = function(){
				}
				window.onload = function(){
/*					BatAlert.MyAlert("<bean:write name="back" scope="request" />","",function(){
						window.close();
					});*/
					alert("<bean:write name="back" scope="request" />");
					window.close();
					
				}
			</script>
		</logic:present>
	</body>
</html>
