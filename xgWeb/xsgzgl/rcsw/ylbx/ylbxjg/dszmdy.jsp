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
	font-family: "����";
	font-size: 16.0pt;
}
-->
</style>
</head>
<s:form action="" theme="simple">
<body bgcolor=#FFFFFF class="Normal" style='text-justify-trim:punctuation' lang=ZH-CN> 
<div class=Section1 style='width:19cm;margin:0 auto;position:relative '> 
  <p align="center" style="font-size:20pt;font-weight:bold;padding:30px 0;">֤&nbsp;��</p>
  <p align="center" style="font-size:16pt;line-height:220%;text-align:left;text-indent:2em;">��֤����<s:if test="bxMap.xm != null">${bxMap.xm}��</s:if><s:if test="bxMap.xb != null">${bxMap.xb}</s:if>���㽭��ѧ<s:if test="bxMap.xymc != null">${bxMap.xymc}</s:if>��<s:if test="bxMap.zymc != null">${bxMap.zymc}</s:if>��<s:if test="bxMap.nj != null">${bxMap.nj}</s:if>��ѧ����ѧ��<s:if test="bxMap.xh != null">${bxMap.xh}</s:if>����<s:if test="bxMap.mc != null">${bxMap.zd8mc}</s:if>�����зֹ�˾�б���ѧ����ѧ��ƽ�����ա����������˺�ҽ�Ʊ��ա�����סԺҽ�Ʊ��ա��ֱ���ԭ������ʧ���ش�֤����
</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
<p style="text-align: center;float:right;font-size:16pt;width:380px;">�㽭��ѧ��ίѧ����<br />${time}</p>
<p style="position: absolute; right: 72px; top:360px;">
					<img src="images/bxz.gif" style="width: 220px;height: 170px" />
				</p>
				
				
			</div> 
</s:form>
</body>
</html>
