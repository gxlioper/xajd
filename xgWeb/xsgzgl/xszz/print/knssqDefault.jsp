<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/head.ini"%>
<title>高等学校家庭经济困难学生认定申请表</title>
<style>
<!--
 /* Font Definitions */
 @font-face
	{font-family:Wingdings;
	panose-1:5 0 0 0 0 0 0 0 0 0;}
@font-face
	{font-family:宋体;
	panose-1:2 1 6 0 3 1 1 1 1 1;}
@font-face
	{font-family:黑体;
	panose-1:2 1 6 0 3 1 1 1 1 1;}
@font-face
	{font-family:仿宋_GB2312;
	panose-1:2 1 6 9 3 1 1 1 1 1;}
@font-face
	{font-family:宋体;
	panose-1:2 1 6 9 3 1 1 1 1 1;}
@font-face
	{font-family:"\@宋体";
	panose-1:2 1 6 9 3 1 1 1 1 1;}
@font-face
	{font-family:"\@宋体";
	panose-1:2 1 6 0 3 1 1 1 1 1;}
@font-face
	{font-family:"\@黑体";
	panose-1:2 1 6 0 3 1 1 1 1 1;}
@font-face
	{font-family:"\@仿宋_GB2312";
	panose-1:2 1 6 9 3 1 1 1 1 1;}
 /* Style Definitions */
 p.MsoNormal, li.MsoNormal, div.MsoNormal
	{margin:0cm;
	margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	font-size:10.5pt;
	font-family:"Times New Roman";}
p.MsoBodyText, li.MsoBodyText, div.MsoBodyText
	{margin-top:7.8pt;
	margin-right:0cm;
	margin-bottom:0cm;
	margin-left:0cm;
	margin-bottom:.0001pt;
	text-align:center;
	font-size:10.5pt;
	font-family:宋体;}
 /* Page Definitions */
 @page Section1
	{size:595.3pt 841.9pt;
	margin:72.0pt 2.0cm 42.55pt 2.0cm;
	layout-grid:15.6pt;}
div.Section1
	{page:Section1;}
 /* List Definitions */
 ol
	{margin-bottom:0cm;}
ul
	{margin-bottom:0cm;}
-->
</style>

</head>

<body lang=ZH-CN style='text-justify-trim:punctuation'>



		    
<div align=center>

<html:form action="/xszz_knsrd" method="post" styleId="knsrdForm">
<table border="0" width="652px">
<tr>
	<td>
	<br/><br/>
	<p class="MsoNormal" align="center" style='text-align:center'><b><span
	style='font-size:18.0pt;font-family:黑体'>高等学校家庭经济困难学生认定申请表</span></b></p>
	<br/>
	</td>
</tr>	
  <tr>
  <td width="652px" align="left"><b><span style='font-size:14.0pt;align="center";
font-family:宋体'>&nbsp;学校：</span></b><u><span lang=EN-US style='font-size:14.0pt'>${xxmc}&nbsp;
</span></u></td>
<td></td>
  </tr>
  </table>

<table class=MsoNormalTable border=1 cellspacing=0 cellpadding=0 width=643
 style='width:482.3pt;margin-left:9.7pt;border-collapse:collapse;border:none'>
 <tr style='page-break-inside:avoid;height:22.7pt'>
  <td width=44 rowspan=4 style='width:33.3pt;border:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:22.7pt'>
  <p class=MsoNormal align=center style='margin-top:0cm;margin-right:5.65pt;
  margin-bottom:0cm;margin-left:5.65pt;margin-bottom:.0001pt;text-align:center'><b><span
  style='font-family:宋体'>学生本人基本情况</span></b></p>  </td>
  <td width=599 colspan=2 style='width:80.7pt;border:solid windowtext 1.0pt;
  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:22.7pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:宋体'>姓<span lang=EN-US>&nbsp; </span>名</span></p>  </td>
  <td width=599 colspan=2 style='width:62.7pt;border:solid windowtext 1.0pt;
  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:22.7pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:宋体'>${jbxx.xm }</span></p>  </td>
  <td width=599 colspan=3 style='width:48.25pt;border:solid windowtext 1.0pt;
  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:22.7pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:宋体'>性 别</span></p>  </td>
  <td width=599 colspan=2 style='width:62.35pt;border:solid windowtext 1.0pt;
  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:22.7pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:宋体'>${jbxx.xb }</span></p>  </td>
  <td width=599 colspan=2 style='width:53.9pt;border:solid windowtext 1.0pt;
  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:22.7pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:宋体'>出生<br/>年月</span></p>  </td>
  <td width=599 colspan=2 style='width:72.6pt;border:solid windowtext 1.0pt;
  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:22.7pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:宋体'>${jbxx.csrq }</span></p>  </td>
  <td width=599 style='width:44.1pt;border:solid windowtext 1.0pt;border-left:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:22.7pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:宋体'>民 族</span></p>  </td>
  <td width=599 style='width:24.4pt;border:solid windowtext 1.0pt;border-left:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:22.7pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:宋体'>${jbxx.mzmc }</span></p>  </td>
 </tr>
 <tr style='page-break-inside:avoid;height:22.7pt'>
  <td width=599 colspan=2 style='width:80.7pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:22.7pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:宋体'>身份证<br/>号码</span></p>  </td>
  <td width=599 colspan=5 style='width:110.95pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:22.7pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:宋体'>${jbxx.sfzh }</span></p>  </td>
  <td width=599 colspan=2 style='width:62.35pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:22.7pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:宋体'>政治<br/>面貌</span></p>  </td>
  <td width=599 colspan=2 style='width:53.9pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:22.7pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:宋体'>${jbxx.zzmmmc }</span></p>  </td>
  <td width=599 colspan=2 style='width:72.6pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:22.7pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:宋体'>家庭人均</span></p>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:宋体'>年收入</span></p>  </td>
  <td width=599 align="left" colspan=2 style='width:68.5pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:22.7pt'>
  <p class=MsoNormal align="left" style='text-indent:12.0pt'><span style='font-family:宋体'>${jtqk.jtrjsr}</span></p>  </td>
 </tr>
 <tr style='page-break-inside:avoid;height:22.7pt'>
  <td width=599 colspan=2 style='width:80.7pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:22.7pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:宋体'>学<span lang=EN-US>&nbsp; </span>院</span></p>  </td>
  <td width=599 colspan=4 style='width:89.55pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:22.7pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:宋体'>${jbxx.xymc }</span></p>  </td>
  <td width=599 style='width:21.4pt;border-top:none;border-left:none;border-bottom:
  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:22.7pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:宋体'>系</span></p>  </td>
  <td width=599 colspan=4 style='width:116.25pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:22.7pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:宋体'>${jbxx.xymc }</span></p>  </td>
  <td width=599 style='width:49.35pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:22.7pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:宋体'>专 业</span></p>  </td>
  <td width=599 colspan=3 style='width:91.75pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:22.7pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:宋体'>${jbxx.zymc }</span></p>  </td>
 </tr>
 <tr style='page-break-inside:avoid;height:22.7pt'>
  <td width=599 colspan=2 style='width:80.7pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:22.7pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:宋体'>年<span lang=EN-US>&nbsp; </span>级</span></p>  </td>
  <td width=599 style='width:36.45pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:22.7pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:宋体'>${jbxx.nj }</span></p>  </td>
  <td width=599 colspan=2 style='width:37.2pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:22.7pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:宋体'>班</span></p>  </td>
  <td width=599 colspan=2 style='width:37.3pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:22.7pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:宋体'>${jbxx.bjmc }</span></p>  </td>
  <td width=599 colspan=3 style='width:78.5pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:22.7pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:宋体'>在校联系<br/>电话</span></p>  </td>
  <td width=599 colspan=5 style='width:178.85pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:22.7pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:宋体'>${jbxx.lxdh }</span></p>  </td>
 </tr>
 <tr style='page-break-inside:avoid;height:162.15pt'>
  <td width=44 rowspan="2" style='width:33.3pt;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:162.15pt'>
  <p class=MsoNormal align=center style='margin-top:0cm;margin-right:5.65pt;
  margin-bottom:0cm;margin-left:5.65pt;margin-bottom:.0001pt;text-align:center'><b><span
  style='font-family:宋体'>学生陈述申请认定理由</span></b></p>  </td>
  <td width=599 colspan=15 valign="top" align="left" style='border-bottom-style:none;word-wrap: break-word!important; word-break: break-all!important;width:449.0pt;border-top:none;
  border-left:none;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:162.15pt'>
  <p class=MsoNormal><span lang=EN-US style='font-family:宋体'>&nbsp;</span></p>
  &nbsp;&nbsp;&nbsp;&nbsp;${knsrdForm.sqly}
  <p class=MsoNormal><span lang=EN-US style='font-family:宋体'>&nbsp;</span></p>
   </td>
 </tr>
 <tr style='page-break-inside:avoid;height:12.15pt'  >
   <td colspan=15 valign="bottom" style='border-top-style:none;word-wrap: break-word!important; word-break: break-all!important;width:449.0pt;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:12.15pt'><p class=MsoNormal><span lang=EN-US style='font-family:宋体'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span><span style='font-family:宋体'>学生签字：<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></span>年<b><u><span
  lang=EN-US>&nbsp;&nbsp; </span></u></b>月<u><span lang=EN-US>&nbsp;&nbsp; </span></u>日</span></p>
  <p class=MsoNormal ><b><span style='font-family:宋体'>注：可另附详细情况说明。</span></b></p>&nbsp;</td>
 </tr>
 <tr   style='page-break-inside:avoid;height:39.0pt'>
  <td width=44 style='width:33.3pt;border:solid windowtext 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:39.0pt'>
  <p class=MsoNormal align=center style='margin-top:0cm;margin-right:5.65pt;
  margin-bottom:0cm;margin-left:5.65pt;margin-bottom:.0001pt;text-align:center'><b><span
  style='font-family:宋体'>民主评议</span></b></p>  </td>
  <td width=599 style='width:44.9pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:39.0pt'>
  <p class=MsoNormal align=center style='margin-top:0cm;margin-right:5.65pt;
  margin-bottom:0cm;margin-left:5.65pt;margin-bottom:.0001pt;text-align:center'><span
  style='font-family:宋体'>推<br/>荐<br/>档<br/>次</span></p>  </td>
  <td width=599 colspan=5 align="left" style='width:98.5pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:39.0pt'>
	  <logic:present name="knsdc">
	  <logic:iterate id="j" name="knsdc" indexId="i">
	  <p class=MsoBodyText style='text-align:left;margin-top:0cm;text-align:justify;text-justify:
  inter-ideograph'>
	  <logic:equal name="j" property="dcdm" value="${rddc }"><img src="/xgxt/pictures/xszz/gou.jpg"></img></logic:equal><logic:notEqual name="j" property="dcdm" value="${rddc }">□</logic:notEqual>${j.dcmc }
	  </p>
	  </logic:iterate>
	  </logic:present>
</td>
  <td  colspan=1 style='width:8.25pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:39.0pt'>
  <p class=MsoNormal align=center style='margin-top:0cm;margin-right:5.65pt;
  margin-bottom:0cm;margin-left:5.65pt;margin-bottom:.0001pt;text-align:center'><span
  style='font-family:宋体'>陈述理由</span></p>  </td>
  <td width=599 colspan=8 valign="bottom" style='width:257.35pt;
  border-top:none;border-left:none;border-bottom:solid windowtext 1.0pt;
  border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;height:39.0pt'>
  <p class=MsoNormal><span lang=EN-US style='font-family:宋体'>&nbsp;</span></p>
  <p class=MsoNormal><span lang=EN-US style='font-family:宋体'>&nbsp;</span></p>
  <p class=MsoNormal><span lang=EN-US style='font-family:宋体'>&nbsp;</span></p>
  <p class=MsoNormal><span lang=EN-US style='font-family:宋体'>&nbsp;</span></p>
  <p class=MsoNormal><span lang=EN-US style='font-family:宋体'>&nbsp;</span></p>
  <p class=MsoNormal><span lang=EN-US style='font-family:宋体'>&nbsp;</span></p>
  <p class=MsoNormal><span lang=EN-US style='font-family:宋体'>&nbsp;</span></p>
  <p class=MsoNormal><span lang=EN-US style='font-family:宋体'>&nbsp;</span></p>
  <p class=MsoNormal><span lang=EN-US style='font-family:宋体'>&nbsp;</span></p>
  <p class=MsoNormal><span lang=EN-US style='font-family:宋体'>&nbsp;</span></p>
  <p class=MsoNormal style='margin-bottom:7.8pt'><span style='font-family:宋体'>评议小组组长签字：<span
  lang=EN-US>&nbsp;&nbsp; <u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></span>年<u><span
  lang=EN-US>&nbsp;&nbsp; </span></u>月<u><span lang=EN-US>&nbsp;&nbsp; </span></u>日</span></p>  </td>
 </tr>
 
 <tr style='page-break-inside:avoid;height:109.0pt'>
  <td width=44 style='width:33.3pt;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:109.0pt'>
  <p class=MsoNormal align=center style='margin-top:0cm;margin-right:5.65pt;
  margin-bottom:0cm;margin-left:5.65pt;margin-bottom:.0001pt;text-align:center'><b><span
  style='font-family:宋体'>认定决定</span></b></p>  </td>
  <td width=599 style='width:44.9pt;border-top:none;border-left:none;border-bottom:
  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:109.0pt'>
  <p class=MsoNormal><span style='font-family:宋体'>院（系）意见</span></p>  </td>
  <td width=599 colspan=6 align="left" valign=top style='width:146.75pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:109.0pt'>
  <p class=MsoBodyText style='text-align:left;margin-top:0cm;text-align:justify;text-justify:
  inter-ideograph'>经评议小组推荐、本院（系）认真审核后，</p>
  <p class=MsoBodyText style='text-align:left;margin-top:0cm;text-align:justify;text-justify:
  inter-ideograph'>□<span lang=EN-US>&nbsp; </span>同意评议小组意见。</p>
  <p class=MsoBodyText style='text-align:left;margin-top:0cm;text-align:justify;text-justify:
  inter-ideograph'>□<span lang=EN-US>&nbsp; </span>不同意评议小组意见。<br>调整为<u><span
  lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span></u>。</p>
  <p class=MsoBodyText style='margin-bottom:7.8pt;text-align:justify;
  text-justify:inter-ideograph'>工作组组长签字：</p><p class=MsoBodyText style='margin-bottom:7.8pt;text-align:justify;
  text-justify:inter-ideograph;text-indent:21.0pt'><u><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span></u>年<u><span lang=EN-US>&nbsp;&nbsp; </span></u>月<u><span lang=EN-US>&nbsp;&nbsp;
  </span></u>日</p>  </td>
  <td width=599 style='width:44.6pt;border-top:none;border-left:none;border-bottom:
  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:109.0pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:宋体'>学校学生资助管理机构意见</span></p>  </td>
  <td width=599 colspan=7 valign=top style='width:212.75pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:109.0pt'>
  <p class=MsoNormal style='margin-top:7.8pt'><span style='text-align:left;font-family:宋体'>经学生所在院（系）提请，本机构认真核实，</span><span
  style='font-family:宋体'><br>□</span><span lang=EN-US>&nbsp; </span><span
  style='font-family:宋体'>同意工作组和评议小组意见。</span></p>
  <p class=MsoNormal style='text-align:left;margin-bottom:7.8pt'><span style='font-family:宋体'>□</span><span
  lang=EN-US>&nbsp; </span><span style='font-family:宋体'>不</span><span
  style='font-family:宋体'>同意工作组和评议小组意见。调整为：</span></p>
  <p class=MsoNormal style='margin-top:7.8pt;margin-right:0cm;margin-bottom:
  7.8pt;margin-left:0cm'><u><span lang=EN-US style='font-family:宋体'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span></u><span style='font-family:宋体'>。</span></p>
  <p class=MsoNormal style='margin-top:7.8pt;margin-right:0cm;margin-bottom:
  7.8pt;margin-left:0cm'><span style='font-family:宋体'>负责人签字：<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span></span></p>
  <p class=MsoNormal style='margin-top:7.8pt;margin-right:0cm;margin-bottom:
  7.8pt;margin-left:0cm;text-indent:84.0pt'><u><span lang=EN-US
  style='font-family:宋体'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></u><span
  style='font-family:宋体'>年<u><span lang=EN-US>&nbsp;&nbsp; </span></u>月<u><span
  lang=EN-US>&nbsp;&nbsp; </span></u>日</span></p>
  <p class=MsoNormal align=center style='margin-top:7.8pt;margin-right:0cm;
  margin-bottom:7.8pt;margin-left:0cm;text-align:center'><span
  style='font-family:宋体'>（加盖部门公章）</span></p>  </td>
 </tr>
</table>
</html:form>
</div>





</body>

</html>
