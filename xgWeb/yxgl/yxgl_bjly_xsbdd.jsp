<%@page contentType="application/vnd.ms-excel;charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>


<html xmlns:v="urn:schemas-microsoft-com:vml"
xmlns:o="urn:schemas-microsoft-com:office:office"
xmlns:x="urn:schemas-microsoft-com:office:excel"
xmlns="http://www.w3.org/TR/REC-html40">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta name="ProgId" content="Excel.Sheet">
<meta name=Generator content="Microsoft Excel 11">
<link rel=File-List href="测试.files/filelist.xml">
<link rel=Edit-Time-Data href="测试.files/editdata.mso">
<link rel=OLE-Object-Data href="测试.files/oledata.mso">
<!--[if gte mso 9]><xml>
 <o:DocumentProperties>
  <o:Author>User</o:Author>
  <o:LastAuthor>User</o:LastAuthor>
  <o:LastPrinted>2007-08-31T06:02:40Z</o:LastPrinted>
  <o:Created>2007-08-31T04:05:06Z</o:Created>
  <o:LastSaved>2007-08-31T06:38:22Z</o:LastSaved>
  <o:Company>Microsoft</o:Company>
  <o:Version>11.6568</o:Version>
 </o:DocumentProperties>
</xml><![endif]-->
<style>
<!--table
	{mso-displayed-decimal-separator:"\.";
	mso-displayed-thousand-separator:"\,";}
@page
	{margin:1.0in .75in 1.0in .75in;
	mso-header-margin:.5in;
	mso-footer-margin:.5in;}
.font7
	{color:windowtext;
	font-size:10.5pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;}
tr
	{mso-height-source:auto;
	mso-ruby-visibility:none;}
col
	{mso-width-source:auto;
	mso-ruby-visibility:none;}
br
	{mso-data-placement:same-cell;}
.style0
	{mso-number-format:General;
	text-align:general;
	vertical-align:middle;
	white-space:nowrap;
	mso-rotate:0;
	mso-background-source:auto;
	mso-pattern:auto;
	color:windowtext;
	font-size:12.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	border:none;
	mso-protection:locked visible;
	mso-style-name:常规;
	mso-style-id:0;}
td
	{mso-style-parent:style0;
	padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:12.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:general;
	vertical-align:middle;
	border:none;
	mso-background-source:auto;
	mso-pattern:auto;
	mso-protection:locked visible;
	white-space:nowrap;
	mso-rotate:0;}
.xl24
	{mso-style-parent:style0;
	text-align:left;
	vertical-align:bottom;}
.xl25
	{mso-style-parent:style0;
	text-align:center;}
.xl26
	{mso-style-parent:style0;
	font-size:10.5pt;
	font-family:"Times New Roman", serif;
	mso-font-charset:0;
	text-align:left;
	vertical-align:bottom;}
.xl27
	{mso-style-parent:style0;
	font-size:10.5pt;
	text-align:left;}
.xl28
	{mso-style-parent:style0;
	font-size:10.5pt;
	font-family:"Times New Roman", serif;
	mso-font-charset:0;
	text-align:left;}
.xl29
	{mso-style-parent:style0;
	font-size:10.5pt;
	font-family:"Times New Roman", serif;
	mso-font-charset:0;
	mso-number-format:"\@";}
.xl30
	{mso-style-parent:style0;
	font-size:10.5pt;
	font-family:"Times New Roman", serif;
	mso-font-charset:0;
	mso-number-format:"\@";
	text-align:right;}
.xl31
	{mso-style-parent:style0;
	font-size:10.5pt;
	font-family:"Times New Roman", serif;
	mso-font-charset:0;
	mso-number-format:"\@";
	text-align:center;}
ruby
	{ruby-align:left;}
rt
	{color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-char-type:none;
	display:none;}
-->
</style>
<!--[if gte mso 9]><xml>
 <x:ExcelWorkbook>
  <x:ExcelWorksheets>
   <x:ExcelWorksheet>
    <x:Name>Sheet1</x:Name>
    <x:WorksheetOptions>
     <x:DefaultRowHeight>285</x:DefaultRowHeight>
     <x:Print>
      <x:ValidPrinterInfo/>
      <x:PaperSizeIndex>9</x:PaperSizeIndex>
      <x:HorizontalResolution>600</x:HorizontalResolution>
      <x:VerticalResolution>600</x:VerticalResolution>
     </x:Print>
     <x:Selected/>
     <x:Panes>
      <x:Pane>
       <x:Number>3</x:Number>
       <x:ActiveRow>8</x:ActiveRow>
       <x:ActiveCol>7</x:ActiveCol>
       <x:RangeSelection>$H$9:$I$9</x:RangeSelection>
      </x:Pane>
     </x:Panes>
     <x:ProtectContents>False</x:ProtectContents>
     <x:ProtectObjects>False</x:ProtectObjects>
     <x:ProtectScenarios>False</x:ProtectScenarios>
    </x:WorksheetOptions>
   </x:ExcelWorksheet>
   <x:ExcelWorksheet>
    <x:Name>Sheet2</x:Name>
    <x:WorksheetOptions>
     <x:DefaultRowHeight>285</x:DefaultRowHeight>
     <x:ProtectContents>False</x:ProtectContents>
     <x:ProtectObjects>False</x:ProtectObjects>
     <x:ProtectScenarios>False</x:ProtectScenarios>
    </x:WorksheetOptions>
   </x:ExcelWorksheet>
   <x:ExcelWorksheet>
    <x:Name>Sheet3</x:Name>
    <x:WorksheetOptions>
     <x:DefaultRowHeight>285</x:DefaultRowHeight>
     <x:ProtectContents>False</x:ProtectContents>
     <x:ProtectObjects>False</x:ProtectObjects>
     <x:ProtectScenarios>False</x:ProtectScenarios>
    </x:WorksheetOptions>
   </x:ExcelWorksheet>
  </x:ExcelWorksheets>
  <x:WindowHeight>8445</x:WindowHeight>
  <x:WindowWidth>14955</x:WindowWidth>
  <x:WindowTopX>120</x:WindowTopX>
  <x:WindowTopY>45</x:WindowTopY>
  <x:ProtectStructure>False</x:ProtectStructure>
  <x:ProtectWindows>False</x:ProtectWindows>
 </x:ExcelWorkbook>
</xml><![endif]--><!--[if gte mso 9]><xml>
 <o:shapedefaults v:ext="edit" spidmax="1030"/>
</xml><![endif]-->
</head>

<body link=blue vlink=purple>
<input type="hidden" id="sfjyFlag" name="sfjyFlag" value="<bean:write name="rs" property="sfjy"/>">
<table x:str border=0 cellpadding=0 cellspacing=0 width=647 style='border-collapse:
 collapse;table-layout:fixed;width:485pt'>
 <col width=72 span=2 style='width:54pt'>
 <col width=75 style='mso-width-source:userset;mso-width-alt:2400;width:56pt'>
 <col width=72 span=4 style='width:54pt'>
 <col width=68 style='mso-width-source:userset;mso-width-alt:2176;width:51pt'>
 <col width=72 style='width:54pt'>
 <tr height=19 style='height:14.25pt'>
  <td height=19 width=72 style='height:14.25pt;width:54pt'></td>
  <td width=72 style='width:54pt'></td>
  <td width=75 style='width:56pt'></td>
  <td width=72 style='width:54pt'></td>
  <td width=72 style='width:54pt'></td>
  <td width=72 style='width:54pt'></td>
  <td width=72 style='width:54pt'></td>
  <td width=68 style='width:51pt'></td>
  <td width=72 style='width:54pt'></td>
 </tr>
 <tr height=38 style='height:28.5pt;mso-xlrowspan:2'>
  <td height=38 colspan=9 style='height:28.5pt;mso-ignore:colspan'></td>
 </tr>
 <tr height=19 style='height:14.25pt'>
  <td height=19 colspan=4 style='height:14.25pt;mso-ignore:colspan'></td>
  <td colspan=4 rowspan=3 class=xl24>
  <img id="txm" width=200 height=60 src="/xgxt/BarcodeServlet?image=3&type=6&data=<bean:write name='rs' property='ksh' />&height=60&fontsize=10&barWidth=0.5" align="right" style="position:absolute;top:48;left:360;width:100px;height:60px"/>
  </td>
  <td></td>
 </tr>
 <tr height=12 style='mso-height-source:userset;height:9.0pt'>
  <td height=12 colspan=4 style='height:9.0pt;mso-ignore:colspan'></td>
  <td></td>
 </tr>
 <tr height=19 style='height:14.25pt'>
  <td colspan=2 height=19 class=xl25 style='height:14.25pt'><bean:write name="rs" property="xm"/></td>
  <td colspan=2 style='mso-ignore:colspan'></td>
  <td></td>
 </tr>
 <tr height=39 style='mso-height-source:userset;height:29.25pt'>
  <td height=39 colspan=4 style='height:29.25pt;mso-ignore:colspan'></td>
  <td colspan=3 class=xl26><span style='mso-spacerun:yes'>&nbsp;</span><font
  class="font7"><bean:write name="rs" property="xymc" /></font></td>
  <td colspan=2 style='mso-ignore:colspan'></td>
 </tr>
 <tr height=15 style='mso-height-source:userset;height:11.25pt'>
  <td height=15 colspan=9 style='height:11.25pt;mso-ignore:colspan'></td>
 </tr>
 <tr height=19 style='height:14.25pt'>
  <td height=19 style='height:14.25pt'></td>
  <td colspan=3 class=xl27><bean:write name="rs" property="zymc" /></td>
  <td colspan=3 style='mso-ignore:colspan'></td>
  <td colspan=2 class=xl28><span lang=EN-US><bean:write name="rs" property="bjmc"/></span></td>
 </tr>
 <tr height=19 style='height:14.25pt'>
  <td height=19 colspan=9 style='height:14.25pt;mso-ignore:colspan'></td>
 </tr>
 <tr height=19 style='height:14.25pt'>
  <td height=19 colspan=2 style='height:14.25pt;mso-ignore:colspan'></td>
  <td class=xl29 ><span lang=EN-US><bean:write name="rs" property="xh"/></span></td>
  <td colspan=2 style='mso-ignore:colspan'></td>
  <td class=xl31><span lang=EN-US><bean:write name="rs" property="ldh"/></span></td>
  <td class=xl30><span lang=EN-US><bean:write name="rs" property="qsh"/></span></td>
  <td colspan=2 style='mso-ignore:colspan'></td>
 </tr>
 <![if supportMisalignedColumns]>
 <tr height=0 style='display:none'>
  <td width=72 style='width:54pt'></td>
  <td width=72 style='width:54pt'></td>
  <td width=75 style='width:56pt'></td>
  <td width=72 style='width:54pt'></td>
  <td width=72 style='width:54pt'></td>
  <td width=72 style='width:54pt'></td>
  <td width=72 style='width:54pt'></td>
  <td width=68 style='width:51pt'></td>
  <td width=72 style='width:54pt'></td>
 </tr>
 <![endif]>
</table>

</body>

</html>
