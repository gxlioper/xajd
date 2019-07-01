<%@ page language="java" pageEncoding="GB2312" contentType="text/html;charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><head>
    <title><bean:message key="lable.title" /></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	</head>

<html:html>
  <style media="print" type="text/css">
.brk{
	page-break-after:always;
}
</style>
  <body> 
    <html:form action="specialprise.do" method="post" >
<center>
<h3><bean:write name="map" property="xxmc"/> <bean:write name="map" property="xn"/>学年<bean:write name="map" property="xq"/>学期 <bean:write name="map" property="jxjmc"/>评审登记表</h3>
<br>
<table width="90%" class="tbstyle">
  <tr>
    <td height="35" colspan="2" width="15%" align="center">学号</td>
    <td width="89" align="center"><bean:write name="map" property="xh"/></td>
    <td width="50" align="center">姓名</td>
    <td colspan="2" align="center" ><bean:write name="map" property="xm"/></td>
    <td width="77" align="center">性别</td>
    <td colspan="2" align="center"><bean:write name="map" property="xb"/></td>
    <td width="87" align="center">民族</td>
    <td width="58" align="center"><bean:write name="map" property="mzmc"/></td>
    <td width="136" align="center">党团员</td>
    <td colspan="2" width="173" align="center"><bean:write name="map" property="zzmmmc"/></td>
    </tr>
  <tr>
    <td height="35" colspan="2" align="center"><bean:message key="lable.xsgzyxpzxy" />、系<br>
    专业、班级</td>
    <td colspan="7"><bean:write name="map" property="xymc"/><bean:write name="map" property="zymc"/><br><bean:write name="map" property="bjmc"/></td>
    <td align="center">外语水平</td>
    <td colspan="4"><bean:write name="map" property="wysp"/></td>
  </tr>
  <tr>
    <td height="35" colspan="2" align="center">宿舍电话</td>
    <td colspan="7"></td>
    <td align="center">手机</td>
    <td colspan="4"><bean:write name="map" property="sjhm"/></td>
  </tr>
  <tr>
    <td height="35" colspan="2" align="right">担任职务:</td>
    <td colspan="2"><bean:write name="map" property="drzw"/></td>
    <td colspan="5">专业年级总人数：</td>
    <td colspan="2" height="35" align="right"><br><bean:write name='map' property="zyrsNum" /> 人</td>
    <td colspan="3"></td>
  </tr>
  <tr>
    <td width="25" height="200" rowspan="7"><p>个</p>
      <p>人</p>
      <p>学</p>
      <p>习</p>
      <p>简</p>
      <p>历</p></td>
    <td width="91">&nbsp;</td>
    <td colspan="7"><div align="center">学习情况</div></td>
    <td colspan="5"><div align="center">综合考评情况</div></td>
    </tr>
  <tr>
    <td width="91">&nbsp;</td>
	<td height="18" colspan="1" rowspan="2"><div align="center">学习成绩</div></td>
    <td height="18" colspan="6"><div align="center">排序</div></td>
    <td colspan="2" rowspan="2"><div align="center">综合考评成绩</div></td>
    <td colspan="3"><div align="center">排序</div></td>
    </tr>
  <tr>
    <td width="91">&nbsp;</td>
    <td height="20" colspan="3"><div align="center">班级</div></td>
    <td height="20" colspan="3"><div align="center">专业</div></td>
    <td colspan="2" width="100px"><div align="center">班级</div></td>
    <td width="100px"><div align="center">专业</div></td>
    </tr>
  
  <tr>
    <td width="91"><div align="center">第一学年</div></td>
    <td></td>
    <td height="20" colspan="3"><bean:write name="map" property="bjcjpx1"/></td>
    <td height="20" colspan="3"><bean:write name="map" property="zycjpx1"/></td>
    <td colspan="2"><bean:write name="map" property="zhkpzf1"/></td>
    <td colspan="2"><bean:write name="map" property="zhkpbjpx1"/></td>
    <td><bean:write name="map" property="zhkpzypx1"/></td>
    </tr>
  <tr>
  
    <td width="91"><div align="center">第二学年</div></td>
    <td></td>
    <td height="19" colspan="3"><bean:write name="map" property="bjcjpx2"/></td>
    <td height="19" colspan="3"><bean:write name="map" property="zycjpx2"/></td>
    <td colspan="2"><bean:write name="map" property="zhkpzf2"/></td>
    <td colspan="2"><bean:write name="map" property="zhkpbjpx2"/></td>
    <td><bean:write name="map" property="zhkpzypx2"/></td>
    </tr>
  <tr>
  
    <td width="91"><div align="center">第三学年</div></td>
    <td></td>
    <td height="23" colspan="3"><bean:write name="map" property="bjcjpx3"/></td>
    <td height="23" colspan="3"><bean:write name="map" property="zycjpx3"/></td>
    <td colspan="2"><bean:write name="map" property="zhkpzf3"/></td>
    <td colspan="2"><bean:write name="map" property="zhkpbjpx3"/></td>
    <td><bean:write name="map" property="zhkpzypx3"/></td>
    </tr>
  <tr>
 
    <td width="91"><div align="center">第四学年</div></td>
    <td></td>
    <td height="23" colspan="3"><bean:write name="map" property="bjcjpx4"/></td>
    <td height="23" colspan="3"><bean:write name="map" property="zycjpx4"/></td>
    <td colspan="2"><bean:write name="map" property="zhkpzf4"/></td>
    <td colspan="2"><bean:write name="map" property="zhkpbjpx4"/></td>
    <td><bean:write name="map" property="zhkpzypx4"/></td>
    </tr>
    <logic:present name="isAHJG">
    <tr>
    
	    <td width="91"><div align="center">第五学年</div></td>
	    <td></td>
	    <td height="23" colspan="3"><bean:write name="map" property="bjcjpx5"/></td>
	    <td height="23" colspan="3"><bean:write name="map" property="zycjpx5"/></td>
	    <td colspan="2"><bean:write name="map" property="zhkpzf5"/></td>
	    <td colspan="2"><bean:write name="map" property="zhkpbjpx5"/></td>
	    <td><bean:write name="map" property="zhkpzypx5"/></td>
    </tr>
    </logic:present>
  <tr>
    <td height="105" colspan="2"><div align="center">主要成绩</div></td>
    <td colspan="12"><bean:write name="map" property="xxjl"/></td>
  </tr>
  <tr>
    <td height="119" colspan="2"><div align="center">申请理由</div></td>
    <td colspan="12">&nbsp;<bean:write name="map" property="sqly"/></td>
  </tr>
</table>
<!--<div class="brk"></div>-->
<table width="90%" class="tbstyle">
       <tr>
         <td width="15%" height="107">
          <p align="center">系意见</p></td>
         <td width="775" height="107" align="right" valign="bottom"><bean:write name="map" property="fdyyj"/><p>签名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <p>年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日</p></td>
       </tr>
       <tr>
         <td height="133"><div align="center">学校意见</div></td>
         <td height="133" align="right" valign="bottom"><bean:write name="map" property="xxshyj"/><p>学校盖章&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <p>年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日</p></td>
       </tr >
</table>
</center>
</html:form>
</body>
</html:html>
