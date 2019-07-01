<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title><bean:message key="lable.title" /></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
		<style type="text/css">
			/*������������*/
body {
	margin: 0px;
	padding: 0px;
	font-family: verdana, arial, helvetica, sans-serif, ����;
	color: #000;
	background-color: #fff;
	font-size: 12px;
}

body {
	SCROLLBAR-FACE-COLOR: #93B8DD; 
	SCROLLBAR-SHADOW-COLOR:#93B8DD; 
	SCROLLBAR-HIGHLIGHT-COLOR: #B8D1EA; 
	SCROLLBAR-3DLIGHT-COLOR: #5C8DBE; 
	SCROLLBAR-DARKSHADOW-COLOR: #3A6FA3; 
	SCROLLBAR-TRACK-COLOR:#B2CCE7; 
	SCROLLBAR-ARROW-COLOR: #1E5083 

}

td {
	font-size: 12px;
}

th {
	font-size: 12px;
}

strong,b {
	font-weight: bold;
}

img map {
	border: 0;
}

/*--------------------------------*/ /*ȫ�ֲ�������*/
legend {
	font-weight: bold;
	color: #003366
}

fieldset {
	border: #98BCE1 1px solid;
	padding: 4px;
	margin-left:2px;
	margin-right:2px;
}

input {
	background: #F7F9FC;
	border: 1px solid #6699CC;
}

textarea {
	background: #F7F9FC;
	border: 1px solid #6699CC;
}

/*��ť����*/
.button2 {
	border: #002D96 1px solid;
	padding: 2px 0px 0px 2px;
	filter: progid:DXImageTransform.Microsoft.Gradient(GradientType=0,StartColorStr=#FFFFFF,EndColorStr=#9DBCEA);
	cursor: hand;
	color: #003366;
	margin: 0px 2px;
	height: 22px;
}

.buttontool {
	margin: 2px;
	padding: 6px;
	background: #D0E0EE;
	text-align: center;
	border:#A2C7EA 1px solid;
}

.tbstyle {
	border-collapse: collapse;
}

.tbstyle td {
	border: 1px #97B7DB solid;
	padding: 3px;
}
.tbstyle th {
	border: 1px #97B7DB solid;
	padding: 3px;
}
.tbstyle thead {
	background: #D0E0EE;
	color: #003366
}
.printstyle {
	border-collapse: collapse;
}

.printstyle td {
	border: 1px #000 solid;
	padding: 3px;
}
.printstyle th {
	border: 1px #000 solid;
	padding: 3px;
}
.printstyle thead {
	background: #f5f5f5;
	color: #000
}
/*Ĭ�ϵĳ�������ʽ*/
A:link {
	COLOR: #000;
	TEXT-DECORATION: none
}

A:visited {
	COLOR: #003366;
	TEXT-DECORATION: none
}

A:active {
	COLOR: #003366;
	TEXT-DECORATION: none
}

A:hover {
	COLOR: red;
	TEXT-DECORATION: underline
}

/*----------------top����----------*/
.toplogo{
	background:url(../../images/headbg.gif);
	height:45px;
	width:100%;
	line-height:45px;
	}
.toplogo img{
	float:left;}
.toplogo span{
	float:left}
.topframe {
	background: url(../images/top_bg.gif);
	height: 24px;
	width: 100%;
}
.tolefthand {
	cursor: hand;
	background: url(../images/sous_2.gif) no-repeat;
	height: 83px;
	width: 11px;
}

.torighthand {
	cursor: hand;
	background: url(../images/sous_1.gif) no-repeat;
	height: 83px;
	width: 11px;
}

.topframe span{
	background: #80B8E7;
	border: solid #fff;
	border-width: 1px 0px;
	filter: progid : DXImageTransform . Microsoft . Alpha(opacity = 55);
	height: 22px;
	width: 100%;
	text-align:right;}
.tool {
	position: absolute;
	top:47px;
	right:5px;
}

.tool ul {
	list-style: none;
	margin: 0px;
}

.tool li {
	list-style: none;
	float: left;
	margin: 0px 2px;
	cursor: hand;
}

.tool_0 {
	background: url(../images/authorship.gif) no-repeat;
	height: 17px;
	width: 17px;
}
.tool_1 {
	background: url(../images/tool_loginout.gif) no-repeat;
	height: 17px;
	width: 17px;
}

.tool_2 {
	background: url(../images/tool_close.gif) no-repeat;
	height: 17px;
	width: 17px;
}

.tool_3 {
	background: url(../images/tool_help.gif) no-repeat;
	height: 17px;
	width: 17px;
}
.tool_4 {
	background: url(../images/tool_qiehuan.gif) no-repeat;
	height: 17px;
	width: 17px;
}
/*----------------top����----------*/
.leftframe {
	background: url(../images/left_bg.gif);
	width: 100%;
	height: 100%
}

/*========================��߲˵���ʽ=======================*/
	/*.titΪÿ���˵��ı�����ʽ .list Ϊ�˵���Ŀ����ʽ*/
.tit  , .list {
	margin-left: 3px;
}

/*�˵��������ʽ*/
.tit {
	color: #D9E1F6; /*ǰ��ɫ*/
	text-indent: 17px;
	border: solid #98BCE1;
	margin-top: 10px;
	border-width: 0px 0px 1px 0px;
	font: bold 12px/ 14px "����"; /*������ʽ����Ϊ������ ��С/�и� ����*/
}

.tit a  ,.tit a:link  , .tit a hover { /*�˵������е����ӵ���ʽ����ͬ�Ĳ���*/
	color: #003366;
	display: block;
	text-decoration: none;
	width: 100%;
	background-repeat: no-repeat;
	background-position: 2px 0px;
}

/*====================
    �˵������еļ�ͷͼƬ
======================*/
.on {
	background-image: url(../images/arrow_down.gif); /*���ϵļ�ͷ-��ɫ*/
}

.on:hover {
	background-image: url(../images/arrow_down_o.gif); /*���ϵļ�ͷ-��ɫ*/
}

.off {
	background-image: url(../images/arrow_up.gif); /*���µļ�ͷ-��ɫ*/
}

.off:hover {
	background-image: url(../images/arrow_up_o.gif); /*���µļ�ͷ-��ɫ*/
}

.tit a:hover {
	color: #006699; /*��꾭��ʱ����ɫ*/
	text-decoration: none;
}

.list {
	font-size: 12px;
	color: #002280;
	text-align: left;
	voice-family: "\"}\"";
	voice-family: inherit;
	overflow: hidden;
}

html>body .list {
	width: 150px;
}

.list ul {
	list-style-type: none;
	margin: 5px 0px;
	padding: 0;
	padding-left: 20px;
}

.list li {
	margin: 2px;
	padding: 0;
	padding-left: 5px;
}

/*============
�˵����ӵ���ʽ
==============*/
.lipic {
	list-style-image: url("../images/.gif");
}

.list a {
	color: #002280;
	text-decoration: none;
}

.list a:link {
	color: #002280;
}

.list a:hover {
	color: #296DC1;
	text-decoration: underline;
}

.list a:active {
	color: #296DC1;
}

/*============�ұ���������ʽ==============*/
.title {
	border: #7AA5D2 1px solid;
	padding: 2px 0px 0px 2px;
	filter: progid:DXImageTransform.Microsoft.Gradient(GradientType=0,StartColorStr=#7AA5D2,EndColorStr=#CEE3F9);
	color: #003366;
	height: 14px;
	font-weight: bold;
}

.title_img {
	float: left;
	background: url(../images/list-image-5.gif) no-repeat;
	height: 16px;
	padding-left: 25px
}

.rightcontent {
	border: #7AA5D2 solid;
	border-width: 0px 0px 0px 0px;
	padding: 4px;
}

/*============�ײ���ʽ==============*/
.bottomframe {
	width: 100%;
	height: 21px;
	border: #A2C7EA solid 1px;
	background-color: #CEE4F9;
}

.zflogo {
	float: right;
	background: url(../images/zflogo.gif) no-repeat;
	height: 21px;
	width: 67px;
	cursor: hand;
}

/*����Ϊѧ��������ʽ*/
.roll_tip {
	background: url(../images/loading.gif) no-repeat;
	width: 180px;
}

.menu_out_l {
	background: url(../images/menu_more_l.gif) no-repeat;
	width: 15px;
}

.menu_out_r {
	background: url(../images/menu_more_r.gif) no-repeat;
	width: 15px;
}

.menu_contener {
	position: absolute;
	top: 46px;
	font-weight: bold;
	left: 10px;
	color: #fff;
}

.left_title {
	background: url(../images/left_title.gif) no-repeat;
	height: 22px;
	width: 152px;
	text-align: center;
	font-weight: bold;
	color: #fff;
}

.xxk {
	width: 100%;
	height: 36px;
	background: url(../images/xxk_bg.gif);
	margin-top: 9px;
}

/*ѡ�еĲ˵���ʽ*/
.xxk_on_l {
	background: url(../images/xxk_on_l.gif) no-repeat;
	height: 36px;
	width: 5px;
}

.xxk_on_r {
	background: url(../images/xxk_on_r.gif) no-repeat;
	height: 36px;
	width: 5px;
}

.xxk_on_m {
	height: 36px;
	font-weight: bold;
	color: #053E7A;
	filter: glow(color = white, strength = 1);
	background: url(../images/xxk_on_m.gif) repeat-x;
	padding-top: 3px;
}

/*δѡ�еĲ˵���ʽ*/
.xxk_off_l {
	background: url(../images/xxk_off_l.gif) no-repeat;
	height: 36px;
	width: 5px;
}

.xxk_off_r {
	background: url(../images/xxk_off_r.gif) no-repeat;
	height: 36px;
	width: 5px;
}

.xxk_off_m {
	height: 36px;
	font-weight: bold;
	color: #053E7A;
	filter: glow(color = white, strength = 1);
	background: url(../images/xxk_off_m.gif) repeat-x;
	padding-top: 5px;
}

.xxk ul {
	list-style: none;
	margin: 0px 1px;
	float: left;
}

.xxk li {
	list-style: none;
	float: left;
	cursor: hand;
}

.menuIn{
	cursor: hand;
	background: url(../images/menu_bar.gif) no-repeat;
	width: 18px;	
}
.menuOut{
	cursor: hand;
	background: url(../images/menu_bar2.gif) no-repeat;
	width: 18px;	
}
.menu_bg_1{
	cursor: hand;
	background: url(../images/menu_bg1.gif) repeat-x;
	height: 12px;
}
.bktubiao {
	background: url(../images/menu_more_r.gif) no-repeat left center;
	padding-left:10px;
	}
.tubiao {
	background: url(../images/list_arrow.gif) no-repeat left center;
	padding-left:10px;
	}
		</style>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/pjpyFunction.js"></script>	
<script language="javascript" src="js/BatAlert.js"></script>
	<script language="javascript" src="js/webPrint.js"></script>
	<script type="text/javascript" src="js/bbld.js"></script>
	<object id=eprint classid="clsid:CA03A5A8-9890-49BE-BA4A-8C524EB06441" codebase="images/webprint.cab" viewasext> </object>
	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
  </head>
  <body>
      <logic:present name="pks">
<script>
window.onload = function(){
	Print(true);
	var kshs = '<bean:write name="pks"/>';
	if(kshs == ''){
		BatAlert.MyAlert("��ӡ����!","",function(){
			Close();
		});
		return false;
	}else{
		var tempArray = kshs.split(',');
		ksh=tempArray[0];
		tempArray.splice(0,1);
		window.location.href = 'hzyrychbbld.do?pkValue='+ksh+'&pks='+tempArray.join(',');
	}
}
</script>
</logic:present>
    <html:form action="/dxjxjsp">
      <div align="center" style="font-size:18px;font:'����' "><bean:write name='rs' property="title"/></div>
<br/>
<div>


&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<bean:write name='rs' property="xn"/>&nbsp;ѧ��  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="xymc"/> &nbsp;&nbsp;${rs.bjmc }&nbsp;&nbsp;  <bean:write name='rs' property="xq"/></div>
<table width="98%" class="printstyle">
  <tr>
    <th height="30" colspan="2" width="14%" scope="col">��   &nbsp;&nbsp;&nbsp; ��</th>
    <td height="30" colspan="2" style="width:20%" scope="col" align="center">&nbsp;<bean:write name='rs' property="xm"/></td>
    <th height="30" width="11%" scope="col">��  ��</th>
    <td height="30" colspan="2" scope="col" style="width:15%" align="center">&nbsp;<bean:write name='rs' property="xb"/></td>
    <th height="30" width="10%" scope="col">ְ  ��</th>
    <td height="30" colspan="2" scope="col" align="center">&nbsp;<bean:write name='rs' property="drzw"/></td>
  </tr>
  <tr>
    <th height="30" colspan="2" scope="row">������ò</th>
    <td colspan="2" align="center">&nbsp;<bean:write name='rs' property="zzmm"/></td>
    <th><div align="center">��  ��</div></th>
    <td colspan="2" align="center">&nbsp;<bean:write name='rs' property="mz"/></td>
    <th colspan="2"><div align="center">�ۺ���������/�ֳܷɼ�����</div></th>
    <td colspan="" width="13%" align="center">&nbsp;<bean:write name='rs' property="zhpfmc"/>/<bean:write name='rs' property="cjmc"/></td>
  </tr>
  <tr>
    <th height="30" colspan="2" scope="row">��ͥ��ϸ��ַ</th>
    <td colspan="5" align="center">&nbsp;<bean:write name='rs' property="jtdz"/></td>
    
    <th><div align="center">��  ��</div></th>
    <td colspan="2" align="center">&nbsp;<bean:write name='rs' property="sjhm"/></td>
  </tr>
  
  <tr>
    <th scope="row" style="width:6%">
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>��</p>
    <p>Ҫ</p>
    <p>��</p>
    <p>��</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    </th>
    <th colspan="9" scope="row"><p  align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='rs' property="zysj"/></p>
   </th>
  </tr>
  <tr>
  	<th scope="row" colspan="5">
  		<p align="left">&nbsp;&nbsp; �����������</p>
  	
  		
  		<p align="left"><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="fdyyj"/> </p>
  		<p>&nbsp;</p>
  		<p>&nbsp;</p>
  		<p align="center">ǩ����<bean:write name="rs" property="fdyqm"/></p>
  		<p align="right"><bean:write name="rs" property="fdyshyear"/> �� <bean:write name="rs" property="fdyshmon"/> �� <bean:write name="rs" property="fdyshdate"/> �� &nbsp;&nbsp;&nbsp;</p>
  	</th>
  	<th scope="row" colspan="5">
  		<p align="left">&nbsp;&nbsp; ��ϵ���������</p>
  
  		
  		<p align="left"><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="xyyj"/> </p>
  		
  		<p>&nbsp;</p>
  		<p>&nbsp;</p>
  		<p align="center">ǩ����<bean:write name="rs" property="xyqm"/></p>
  		<p align="right"><bean:write name="rs" property="xyshyear"/> �� <bean:write name="rs" property="xyshmon"/> �� <bean:write name="rs" property="xyshdate"/> �� &nbsp;&nbsp;&nbsp;</p>
  	</th>
  </tr>
  <tr>
  	<th scope="row" colspan="10">
  		<p align="left">&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />�����</p>
  		
  		<p align="left"><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="fkyj"/> </p>
  		
  		<p>&nbsp;</p>
  		<p align="right">���£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		</p>
  		<p align="right"><bean:write name="rs" property="xxshyear"/> �� <bean:write name="rs" property="xxshmon"/> �� <bean:write name="rs" property="xxshdate"/> �� &nbsp;&nbsp;&nbsp;</p>
  	</th>
  </tr>
</table>
<!-- ע���˱�һʽ���ݣ�ϵ��Ժ����Ժѧ������һ�� -->
    </html:form>
  </body>
</html:html>
