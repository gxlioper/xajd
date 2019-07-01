<%@ page language="java" contentType="text/html; charset=GBK"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<title></title>
<style>
<!--
.Section1
	{page:Section1;}
.style1 {
	font-family: "宋体";
	font-size: 16.0pt;
}
-->
</style>
</head>
<s:form action="" theme="simple">
<body bgcolor=#FFFFFF class="Normal" style='text-justify-trim:punctuation' lang=ZH-CN> 
<div class=Section1 style='width:19cm;margin:0 auto;position:relative '> 
  <p align="center" style="font-size:20pt;font-weight:bold;padding:30px 0;">证&nbsp;明</p>
  <p align="center" style="font-size:16pt;line-height:220%;text-align:left;text-indent:2em;">兹证明，<s:if test="bxMap.xm != null">${bxMap.xm}，</s:if><s:if test="bxMap.xb != null">${bxMap.xb}</s:if>，浙江大学<s:if test="bxMap.xymc != null">${bxMap.xymc}</s:if>（<s:if test="bxMap.zymc != null">${bxMap.zymc}</s:if>）<s:if test="bxMap.nj != null">${bxMap.nj}</s:if>级学生，学号<s:if test="bxMap.xh != null">${bxMap.xh}</s:if>，由<s:if test="bxMap.mc != null">${bxMap.zd8mc}</s:if>杭州市分公司承保该学生的学生平安保险、附加意外伤害医疗保险、附加住院医疗保险。现保单原件已遗失，特此证明。
</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
<p style="text-align: center;float:right;font-size:16pt;width:380px;">浙江大学党委学工部<br />${time}</p>
<p style="position: absolute; right: 72px; top:360px;">
					<img src="images/bxz.gif" style="width: 220px;height: 170px" />
				</p>
				
				
			</div> 
</s:form>
</body>
</html>
