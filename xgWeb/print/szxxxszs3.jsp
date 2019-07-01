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
<head>
	<title>λ�õ���</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="styles.css">
	<style>
.weizhi {
	position: absolute;
	left: 1cm;
	top: 1cm;
}

.weizhi ul {
	list-style: none;
	margin: 0px;
	padding: 0px;
}

.weizhi li {
	list-style: none;
	margin: 26px 0px;
	padding: 0px;
}

.weizhi h4 {
	float: left;
	margin: 0px 20px;
	font-size: 16px;
	font-family: ����;
	font-weight: normal;
	break-all: keep-all
}

.youbianxia {
	margin-top: 5px;
}

.yueri {
	margin-left: 5px
}
</style>

</head>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/String.js"></script>
<script language="javascript" src="js/stuinfoFunction.js"></script>
<script language="javascript" src="/xgxt/dwr/interface/getPagePara.js"></script>
<script language="javascript" src="/xgxt/dwr/engine.js"></script>
<script language="javascript" src="js/BatAlert.js"></script>
<script language="javascript" src="js/webPrint.js"></script>

	<object id=eprint classid="clsid:CA03A5A8-9890-49BE-BA4A-8C524EB06441" codebase="images/webprint.cab" viewasext> </object>

<script language="javascript">

/**
function Preview() 
{
	if (document.all.eprint.defaultPrinterName.length==0){
		alert("���Ȱ�װ��ӡ������ִ�д˹��ܣ�");
		return;
	}
  document.all.eprint.InitPrint();
  document.all.eprint.SetMarginMeasure(1);//1mm��default, 2 inch
  document.all.eprint.marginTop=92;
  document.all.eprint.marginLeft=20;
  document.all.eprint.marginRight=20;
  document.all.eprint.marginBottom=10;
  document.all.eprint.header = "";
  document.all.eprint.footer = "";
  document.all.eprint.Preview();//��ӡԤ��
}
*/
function Print() {
	if (document.all.eprint.defaultPrinterName.length==0){
		alert("���Ȱ�װ��ӡ������ִ�д˹��ܣ�");
		return;
	}
  document.all.eprint.InitPrint();
  document.all.eprint.SetMarginMeasure(1);//1mm��default, 2 inch

  document.all.eprint.header = "";
  document.all.eprint.footer = "";
}

    var top= new Array();
	var left = new Array();
    var iex,iey,tempx,tempy;
	var zd = ["xm","xh","mz","bjmc","xymc","xz","sfzhm","jtdz","zymc","xb","jg","csN","csY","csR","rxN","rxY","rxR","fzN","fzY","fzR","bfN","bfY","bfR","yxN","yxY","yxR"];
	var dragapproved=false
   	function initializedragie(obj){
			 //iex=event.clientX;
			 //iey=event.clientY;
			 //tempx=obj.style.pixelLeft;
			 //tempy=obj.style.pixelTop;
			 //dragapproved=true;
		}
		function drag_dropie(obj){}
	function drag_dropie1(obj){
		if (dragapproved==true){
		obj.style.pixelLeft=tempx+event.clientX-iex;
		obj.style.pixelTop=tempy+event.clientY-iey;
		}
	}
	if (document.all){
		document.onmouseup=function(){
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
		getPagePara.savePagePara(topStri,leftStri,'zgdzxsz',function(){});
		return false;
		}
	}
	
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
		BatAlert.MyAlert("��ӡ����!","",function(){
			Close();
		});
		return false;
	}else{
		window.opener.rowOnClick(window.opener.$("tabPri").rows[cu_R]);//������һ��
		var xh = window.opener.curr_row.cells[1].innerText.trim();
		//window.location.href = "noticePrintOne.do?xh="+xh+"&fzrq="+document.getElementById('fzrq').value+"&bfrq="+document.getElementById('bfrq').value+"&yxrq="+document.getElementById('yxrq').value;
		}
	}
</script>
<body>
	<input type="hidden" name="fzrq" id="fzrq"
		value="<bean:write name="fzrq" />" />
	<input type="hidden" name="bfrq" id="bfrq"
		value="<bean:write name="bfrq" />" />
	<input type="hidden" name="yxrq" id="yxrq"
		value="<bean:write name="yxrq" />" />
	<html:form action="/noticePrintOne" method="post">
		<input type="hidden" name="topstr" id="topstr"
			value="<bean:write name="topstr" />" />
		<input type="hidden" name="leftstr" id="leftstr"
			value="<bean:write name="leftstr" />" />
		<table width="500" class="weizhi">
			<tr> 
    <td width="50%">
    <ul style="margin-top:10em;"> 
        <li> 
          <h4><div style="position: absolute; word-break:keep-all;left: 168px; top: 245px;" title="����" id="xm" onMouseDown="initializedragie(this)" onMouseMove="drag_dropie(this)"><bean:write name="rsMap" property="xm"/></div></h4> 
        </li> 
        <li> 
          <h4><div style="position: absolute; left: 164px; top: 287px; height: 1px;" title="ѧ��" id="xh" onMouseDown="initializedragie(this)" onMouseMove="drag_dropie(this)"><bean:write name="rsMap" property="xh"/></div></h4> 
        </li> 
        <li> 
          <h4><div style="position: absolute; left: 164px; top: 287px; height: 1px;width: 300" title="����" id="mz" onMouseDown="initializedragie(this)" onMouseMove="drag_dropie(this)"><bean:write name="rsMap" property="mz"/></div></h4> 
        </li>
        <li> 
          <h4><div style="position: absolute; left: 164px; top: 287px; height: 1px;width: 300" title="���" id="bjmc" onMouseDown="initializedragie(this)" onMouseMove="drag_dropie(this)"><bean:write name="rsMap" property="bjmc"/></div></h4> 
        </li>
         <li> 
          <h4><div style="position: absolute; left: 164px; top: 287px; height: 1px;width: 300" title="<bean:message key="lable.xsgzyxpzxy" />" id="xymc" onMouseDown="initializedragie(this)" onMouseMove="drag_dropie(this)"><bean:write name="rsMap" property="xymc"/></div></h4> 
        </li>
        <li> 
          <h4><div style="position: absolute; left: 164px; top: 287px; height: 1px;" title="ѧ��" id="xz" onMouseDown="initializedragie(this)" onMouseMove="drag_dropie(this)"><bean:write name="rsMap" property="xz"/></div></h4> 
        </li>
        <li> 
          <h4><div style="position: absolute; left: 164px; top: 287px; height: 1px;" title="���֤����" id="sfzhm" onMouseDown="initializedragie(this)" onMouseMove="drag_dropie(this)"><bean:write name="rsMap" property="sfzhm"/></div></h4> 
        </li>
        <li> 
          <h4><div style="position: absolute; left: 164px; top: 287px; height: 1px;width: 300" title="��ͥ��ַ" id="jtdz" onMouseDown="initializedragie(this)" onMouseMove="drag_dropie(this)"><bean:write name="rsMap" property="jtdz"/></div></h4> 
        </li>
      </ul>
    </td> 
    <td>
    <ul> 
        <li> 
          <h4><div style="position: absolute;word-break:keep-all; left: 486px; top: 24px;width:300px" title="רҵ����" id="zymc" onMouseDown="initializedragie(this)" onMouseMove="drag_dropie(this)"><bean:write name="rsMap" property="zymc"/></div></h4> 
        </li> 
        <li> 
          <h4><div style="position: absolute; left: 488px; top: 74px; width: 17px; height: 42px;" title="�Ա�" id="xb" onMouseDown="initializedragie(this)" onMouseMove="drag_dropie(this)"><bean:write name="rsMap" property="xb"/></div></h4> 
          <h4><div style="position: absolute;word-break:keep-all; left: 483px; top: 111px; height: 125px;" title="����" id="jg" onMouseDown="initializedragie(this)" onMouseMove="drag_dropie(this)"><bean:write name="rsMap" property="jg"/></div></h4> 
        </li>
        <li class="youbianxia"> 
          <h4><div style="position: absolute; left: 488px; top: 168px;" title="������" id="csN" onMouseDown="initializedragie(this)" onMouseMove="drag_dropie(this)"><bean:write name="csMap" property="csN"/></div></h4> 
          <h4 class="yueri"><div style="position: absolute; left: 623px; top: 171px;" title="������" id="csY" onMouseDown="initializedragie(this)" onMouseMove="drag_dropie(this)"><bean:write name="csMap" property="csY"/></div></h4> 
          <h4><div style="position: absolute; left: 733px; top: 170px;" title="������" id="csR" onMouseDown="initializedragie(this)" onMouseMove="drag_dropie(this)"><bean:write name="csMap" property="csR"/></div></h4> 
        </li> 
        <li> 
          <h4><div style="position: absolute; left: 489px; top: 215px;" title="��ѧ��" id="rxN" onMouseDown="initializedragie(this)" onMouseMove="drag_dropie(this)"><bean:write name="rxMap" property="rxN"/></div></h4> 
          <h4 class="yueri"><div style="position: absolute; left: 620px; top: 217px;" title="��ѧ��" id="rxY" onMouseDown="initializedragie(this)" onMouseMove="drag_dropie(this)"><bean:write name="rxMap" property="rxY"/></div></h4> 
          <h4><div style="position: absolute; left: 737px; top: 217px;" title="��ѧ��" id="rxR" onMouseDown="initializedragie(this)" onMouseMove="drag_dropie(this)"><bean:write name="rxMap" property="rxR"/></div></h4> 
        </li> 
        <li> 
          <h4><div style="position: absolute; left: 488px; top: 255px;" title="��֤��" id="fzN" onMouseDown="initializedragie(this)" onMouseMove="drag_dropie(this)"><bean:write name="fzMap" property="fzN"/></div></h4> 
          <h4 class="yueri"><div style="position: absolute; left: 620px; top: 258px;" title="��֤��" id="fzY" onMouseDown="initializedragie(this)" onMouseMove="drag_dropie(this)"><bean:write name="fzMap" property="fzY"/></div></h4> 
          <h4><div style="position: absolute; left: 739px; top: 263px;" title="��֤��" id="fzR" onMouseDown="initializedragie(this)" onMouseMove="drag_dropie(this)"><bean:write name="fzMap" property="fzR"/></div></h4> 
        </li> 
        <li> 
          <h4><div style="position: absolute; left: 486px; top: 295px;" title="������" id="bfN" onMouseDown="initializedragie(this)" onMouseMove="drag_dropie(this)"><bean:write name="bfMap" property="bfN"/></div></h4> 
          <h4 class="yueri"><div style="position: absolute; left: 621px; top: 296px;" title="������" id="bfY" onMouseDown="initializedragie(this)" onMouseMove="drag_dropie(this)"><bean:write name="bfMap" property="bfY"/></div></h4> 
          <h4><div style="position: absolute; left: 740px; top: 302px;" title="������" id="bfR" onMouseDown="initializedragie(this)" onMouseMove="drag_dropie(this)"><bean:write name="bfMap" property="bfR"/></div></h4> 
        </li> 
        <li> 
          <h4><div style="position: absolute; left: 486px; top: 335px;" title="��Ч��" id="yxN" onMouseDown="initializedragie(this)" onMouseMove="drag_dropie(this)"><bean:write name="yxMap" property="yxN"/></div></h4> 
          <h4 class="yueri"><div style="position: absolute; left: 621px; top: 336px;" title="��Ч��" id="yxY" onMouseDown="initializedragie(this)" onMouseMove="drag_dropie(this)"><bean:write name="yxMap" property="yxY"/></div></h4> 
          <h4><div style="position: absolute; left: 740px; top: 332px;" title="��Ч��" id="yxR" onMouseDown="initializedragie(this)" onMouseMove="drag_dropie(this)"><bean:write name="yxMap" property="yxR"/></div></h4> 
        </li> 
      </ul>
      </td> 
  </tr> 
		</table>
	</html:form>
	<script>
	
</script>
</body>
</html:html>
