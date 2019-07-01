<%@ page language="java" contentType="text/html; charset=gb2312"%>
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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
<title><bean:message key="lable.title" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta http-equiv="Content-Language" content="GBK" />
<meta name="Copyright" content="正方软件 zfsoft" />
<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
</head>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/stuinfoFunction.js"></script>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<style media="print" type="text/css">
  .brk{
	page-break-after:always;
   }
   .noPrin{display:none}
</style>
<body> 
<html:form action="/stu_info_add.do" method="post">
<hr style="width:100%"> 
<center>
    <br /> 
  <br /> 
  <table width="80%" align="center" border="0" class="tbstyle"> 
   <div class=Section1 style='layout-grid:15.6pt'>
  <p align=center style='text-align:center'><h2 align="center">中国地质大学新生入学登记表</h2></p>
  <p align="center">班号:<bean:write name="rs" property="BJMC"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 填表日期:</p>
  <div align="center">
    <table class="tbstyle" border=0 cellspacing=0 cellpadding=0 >
      <tr>
        <td width=57 rowspan=10 class="Normal"><p align=center style='text-align:center'>基</p>
          <p align=center style='text-align:center'>本</p>
          <p align=center style='text-align:center'>信</p>
          <p align=center style='text-align:center'>息</p></td>
        <td width=70 class="Normal"><p align=center style='text-align:center'>姓名</p></td>
        <td width=100 colspan=4 class="Normal"><bean:write name="rs" property="XM"/></td>
        <td width=57 class="Normal"><p align=center style='text-align:center'>性别</p></td>
        <td width=59 colspan=2 class="Normal"><bean:write name="rs" property="XB"/></td>
        <td width=72 colspan=4 class="Normal"><p align=center style='text-align:center'>出生日期</p></td>
        <td width=72 colspan=3 class="Normal"><bean:write name="rs" property="CSRQ"/></td>
        <td width=84 rowspan=6 class="Normal">
			<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}"
							border="0" style="width:140;height:160"/>
		</td>
      </tr>
      <tr>
        <td width=70 class="Normal"><p align=center style='text-align:center'>曾用名</p></td>
        <td width=100 colspan=4 class="Normal"><bean:write name="rs" property="CYM"/></td>
        <td width=57 class="Normal"><p align=center style='text-align:center'>民族</p></td>
        <td width=59 colspan=2 class="Normal"><bean:write name="rs" property="MZ"/></td>
        <td width=72 colspan=4 class="Normal"><p align=center style='text-align:center'>宗教信仰</p></td>
        <td width=72 colspan=3 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=70 class="Normal"><p align=center style='text-align:center'>身份证号</p></td>
        <td width=216 colspan=7 class="Normal"><bean:write name="rs" property="SFZH"/></td>
        <td width=72 colspan=4 class="Normal"><p align=center style='text-align:center'>是否侨眷</p></td>
        <td width=72 colspan=3 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=70 class="Normal"><p align=center style='text-align:center'>身高</p></td>
        <td width=84 colspan=2 class="Normal"><bean:write name="rs" property="SG"/></td>
        <td width=84 colspan=4 class="Normal"><p align=center style='text-align:center'>体重</p></td>
        <td width=48 class="Normal"><bean:write name="rs" property="TZ"/></td>
        <td width=72 colspan=4 class="Normal"><p align=center style='text-align:center'>个人电话</p></td>
        <td width=72 colspan=3 class="Normal"><bean:write name="rs" property="LXDH"/></td>
      </tr>
      <tr>
        <td width=70 class="Normal"><p align=center style='text-align:center'>政治面貌</p></td>
        <td width=84 colspan=2 class="Normal"><bean:write name="rs" property="ZZMM"/></td>
        <td width=84 colspan=4 class="Normal"><p align=center style='text-align:center'>爱好特长</p></td>
        <td width=192 colspan=8 class="Normal"><bean:write name="rs" property="AH"/></td>
      </tr>
      <tr>
        <td width=70 class="Normal"><p align=center style='text-align:center'>入团时间</p></td>
        <td width=84 colspan=2 class="Normal">&nbsp;</td>
        <td width=84 colspan=4 class="Normal"><p align=center style='text-align:center'>入党时间</p></td>
        <td width=48 class="Normal">&nbsp;</td>
        <td width=72 colspan=4 class="Normal"><p align=center style='text-align:center'>转正时间</p></td>
        <td width=72 colspan=3 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=70 class="Normal"><p align=center style='text-align:center'>籍贯</p></td>
        <td width=360 colspan=14 class="Normal"><bean:write name="rs" property="JG"/></td>
        <td width=84 rowspan=14 class="Normal"><p>填写说明:<br>
1:籍贯、出生地填写到县级。如湖北省黄冈市团风县。<br>
2:宿舍在新峰公寓者请注明”新峰A”字样。乘车区间填写火车或者轮船的终点站名<br>
3:家庭和学校的地址填写能够收到信件的详细地址</p></td>
      </tr>
      <tr>
        <td width=70 class="Normal"><p align=center style='text-align:center'>出生地</p></td>
        <td width=360 colspan=14 class="Normal"><bean:write name="rs" property="SYD"/></td>
      </tr>
      <tr>
        <td width=70 class="Normal"><p align=center style='text-align:center'>家庭住址</p></td>
        <td width=360 colspan=14 class="Normal"><bean:write name="rs" property="JTDZ"/></td>
      </tr>
      <tr>
        <td width=70 class="Normal"><p align=center style='text-align:center'>邮政编码</p></td>
        <td width=360 colspan=14 class="Normal"><bean:write name="rs" property="YZBM"/></td>
      </tr>
      <tr>
        <td width=57 rowspan=6 class="Normal"><p align=center style='text-align:center'>在</p>
          <p align=center style='text-align:center'>校</p>
          <p align=center style='text-align:center'>信</p>
          <p align=center style='text-align:center'>息</p></td>
        <td width=70 class="Normal"><p align=center style='text-align:center'>学号</p></td>
        <td width=157 colspan=5 class="Normal"><bean:write name="rs" property="XH"/></td>
        <td width=71 colspan=3 class="Normal"><p align=center style='text-align:center'>班级序号</p></td>
        <td width=132 colspan=6 class="Normal"><bean:write name="rs" property="BJMC"/></td>
      </tr>
      <tr>
        <td width=70 class="Normal"><p align=center style='text-align:center'><bean:message key="lable.xsgzyxpzxy" />名称</p></td>
        <td width=157 colspan=5 class="Normal"><bean:write name="rs" property="XYMC"/></td>
        <td width=71 colspan=3 class="Normal"><p align=center style='text-align:center'>专业名称</p></td>
        <td width=132 colspan=6 class="Normal"><bean:write name="rs" property="ZYMC"/></td>
      </tr>
      <tr>
        <td width=70 class="Normal"><p align=center style='text-align:center'>入学日期</p></td>
        <td width=157 colspan=5 class="Normal"><bean:write name="rs" property="RXRQ"/></td>
        <td width=71 colspan=3 class="Normal"><p align=center style='text-align:center'>培养层次</p></td>
        <td width=132 colspan=6 class="Normal"><bean:write name="rs" property="PYCC"/></td>
      </tr>
      <tr>
        <td width=70 class="Normal"><p align=center style='text-align:center'>宿舍号码</p></td>
        <td width=157 colspan=5 class="Normal"><bean:write name="rs" property="SSBH"/></td>
        <td width=71 colspan=3 class="Normal"><p align=center style='text-align:center'>宿舍电话</p></td>
        <td width=132 colspan=6 class="Normal"><bean:write name="rs" property="QSDH"/></td>
      </tr>
      <tr>
        <td width=70 class="Normal"><p align=center style='text-align:center'>乘车类型</p></td>
        <td width=157 colspan=5 class="Normal">&nbsp;</td>
        <td width=71 colspan=3 class="Normal"><p align=center style='text-align:center'>乘车区间</p></td>
        <td width=132 colspan=6 class="Normal"><p align=center style='text-align:center'>武汉--</p></td>
      </tr>
      <tr>
        <td width=70 class="Normal"><p align=center style='text-align:center'>一卡通号</p></td>
        <td width=157 colspan=5 class="Normal">&nbsp;</td>
        <td width=71 colspan=3 class="Normal"><p align=center style='text-align:center'>中信银行卡号</p></td>
        <td width=132 colspan=6 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=57 rowspan=5 class="Normal"><p align=center style='text-align:center'>高</p>
          <p align=center style='text-align:center'>考</p>
          <p align=center style='text-align:center'>信</p>
          <p align=center style='text-align:center'>息</p></td>
        <td width=70 class="Normal"><p align=center style='text-align:center'>考生号</p></td>
        <td width=43 class="Normal">&nbsp;</td>
        <td width=53 colspan=2 class="Normal">&nbsp;</td>
        <td width=61 colspan=2 class="Normal">&nbsp;</td>
        <td width=71 colspan=3 class="Normal">&nbsp;</td>
        <td width=48 colspan=2 class="Normal">&nbsp;</td>
        <td width=51 colspan=3 class="Normal">&nbsp;</td>
        <td width=33 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=70 class="Normal"><p align=center style='text-align:center'>来源中学</p></td>
        <td width=43 class="Normal">&nbsp;</td>
        <td width=53 colspan=2 class="Normal">&nbsp;</td>
        <td width=61 colspan=2 class="Normal">&nbsp;</td>
        <td width=71 colspan=3 class="Normal">&nbsp;</td>
        <td width=48 colspan=2 class="Normal">&nbsp;</td>
        <td width=51 colspan=3 class="Normal">&nbsp;</td>
        <td width=33 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=70 class="Normal"><p align=center style='text-align:center'>学校地址</p></td>
        <td width=43 class="Normal">&nbsp;</td>
        <td width=53 colspan=2 class="Normal">&nbsp;</td>
        <td width=61 colspan=2 class="Normal">&nbsp;</td>
        <td width=71 colspan=3 class="Normal">&nbsp;</td>
        <td width=48 colspan=2 class="Normal">&nbsp;</td>
        <td width=51 colspan=3 class="Normal">&nbsp;</td>
        <td width=33 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=70 rowspan=2 class="Normal"><p align=center style='text-align:center'>高考成绩</p></td>
        <td width=43 class="Normal"><p align=center style='text-align:center'>总分</p></td>
        <td width=53 colspan=2 class="Normal"><p align=center style='text-align:center'>语文</p></td>
        <td width=61 colspan=2 class="Normal"><p align=center style='text-align:center'>数学</p></td>
        <td width=71 colspan=3 class="Normal"><p align=center style='text-align:center'>外语</p></td>
        <td width=48 colspan=2 class="Normal">&nbsp;</td>
        <td width=51 colspan=3 class="Normal">&nbsp;</td>
        <td width=33 class="Normal"><p align=center style='text-align:center'>综合</p></td>
      </tr>
      <tr>
        <td width=43 class="Normal">&nbsp;</td>
        <td width=53 colspan=2 class="Normal">&nbsp;</td>
        <td width=61 colspan=2 class="Normal">&nbsp;</td>
        <td width=71 colspan=3 class="Normal">&nbsp;</td>
        <td width=48 colspan=2 class="Normal">&nbsp;</td>
        <td width=51 colspan=3 class="Normal">&nbsp;</td>
        <td width=33 class="Normal">&nbsp;</td>
        <td width=84 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=57 rowspan=5 class="Normal"><p align=center style='text-align:center'>本人</p>
          <p align=center style='text-align:center'>学习</p>
          <p align=center style='text-align:center'>简历</p></td>
        <td width=114 colspan=2 class="Normal"><p align=center style='text-align:center'>由何时至何时</p></td>
        <td width=114 colspan=4 class="Normal"><p align=center style='text-align:center'>在何单位</p></td>
        <td width=107 colspan=4 class="Normal"><p align=center style='text-align:center'>任何职务</p></td>
        <td width=96 colspan=5 class="Normal"><p align=center style='text-align:center'>收到何种奖励或处分</p></td>
        <td width=84 class="Normal"><p align=center style='text-align:center'>证明人</p></td>
      </tr>
      <tr>
        <td width=114 colspan=2 class="Normal">&nbsp;</td>
        <td width=114 colspan=4 class="Normal">&nbsp;</td>
        <td width=107 colspan=4 class="Normal">&nbsp;</td>
        <td width=96 colspan=5 class="Normal">&nbsp;</td>
        <td width=84 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=114 colspan=2 class="Normal">&nbsp;</td>
        <td width=114 colspan=4 class="Normal">&nbsp;</td>
        <td width=107 colspan=4 class="Normal">&nbsp;</td>
        <td width=96 colspan=5 class="Normal">&nbsp;</td>
        <td width=84 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=114 colspan=2 class="Normal">&nbsp;</td>
        <td width=114 colspan=4 class="Normal">&nbsp;</td>
        <td width=107 colspan=4 class="Normal">&nbsp;</td>
        <td width=96 colspan=5 class="Normal">&nbsp;</td>
        <td width=84 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=114 colspan=2 class="Normal">&nbsp;</td>
        <td width=114 colspan=4 class="Normal">&nbsp;</td>
        <td width=107 colspan=4 class="Normal">&nbsp;</td>
        <td width=96 colspan=5 class="Normal">&nbsp;</td>
        <td width=84 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=57 rowspan=5 class="Normal"><p align=center style='text-align:center'>家庭</p>
          <p align=center style='text-align:center'>主要</p>
          <p align=center style='text-align:center'>成员</p></td>
        <td width=70 class="Normal"><p align=center style='text-align:center'>称呼</p></td>
        <td width=43 class="Normal"><p align=center style='text-align:center'>姓名</p></td>
        <td width=53 colspan=2 class="Normal"><p align=center style='text-align:center'>年龄</p></td>
        <td width=61 colspan=2 class="Normal"><p align=center style='text-align:center'>政治面貌</p></td>
        <td width=143 colspan=7 class="Normal"><p align=center style='text-align:center'>工作单位</p></td>
        <td width=60 colspan=2 class="Normal"><p align=center style='text-align:center'>职务</p></td>
        <td width=84 class="Normal"><p align=center style='text-align:center'>联系电话</p></td>
      </tr>
      <tr>
        <td width=70 class="Normal">&nbsp;</td>
        <td width=43 class="Normal">&nbsp;</td>
        <td width=53 colspan=2 class="Normal">&nbsp;</td>
        <td width=61 colspan=2 class="Normal">&nbsp;</td>
        <td width=143 colspan=7 class="Normal">&nbsp;</td>
        <td width=60 colspan=2 class="Normal">&nbsp;</td>
        <td width=84 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=70 class="Normal">&nbsp;</td>
        <td width=43 class="Normal">&nbsp;</td>
        <td width=53 colspan=2 class="Normal">&nbsp;</td>
        <td width=61 colspan=2 class="Normal">&nbsp;</td>
        <td width=143 colspan=7 class="Normal">&nbsp;</td>
        <td width=60 colspan=2 class="Normal">&nbsp;</td>
        <td width=84 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=70 class="Normal">&nbsp;</td>
        <td width=43 class="Normal">&nbsp;</td>
        <td width=53 colspan=2 class="Normal">&nbsp;</td>
        <td width=61 colspan=2 class="Normal">&nbsp;</td>
        <td width=143 colspan=7 class="Normal">&nbsp;</td>
        <td width=60 colspan=2 class="Normal">&nbsp;</td>
        <td width=84 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=70 class="Normal">&nbsp;</td>
        <td width=43 class="Normal">&nbsp;</td>
        <td width=53 colspan=2 class="Normal">&nbsp;</td>
        <td width=61 colspan=2 class="Normal">&nbsp;</td>
        <td width=143 colspan=7 class="Normal">&nbsp;</td>
        <td width=60 colspan=2 class="Normal">&nbsp;</td>
        <td width=84 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=57 rowspan=4 class="Normal"><p align=center style='text-align:center'>主要</p>
          <p align=center style='text-align:center'>社会</p>
          <p align=center style='text-align:center'>关系</p></td>
        <td width=70 class="Normal"><p align=center style='text-align:center'>称呼</p></td>
        <td width=43 class="Normal"><p align=center style='text-align:center'>姓名</p></td>
        <td width=53 colspan=2 class="Normal"><p align=center style='text-align:center'>年龄</p></td>
        <td width=61 colspan=2 class="Normal"><p align=center style='text-align:center'>政治面貌</p></td>
        <td width=143 colspan=7 class="Normal"><p align=center style='text-align:center'>工作单位</p></td>
        <td width=60 colspan=2 class="Normal"><p align=center style='text-align:center'>职务</p></td>
        <td width=84 class="Normal"><p align=center style='text-align:center'>联系电话</p></td>
      </tr>
      <tr>
        <td width=70 class="Normal">&nbsp;</td>
        <td width=43 class="Normal">&nbsp;</td>
        <td width=53 colspan=2 class="Normal">&nbsp;</td>
        <td width=61 colspan=2 class="Normal">&nbsp;</td>
        <td width=143 colspan=7 class="Normal">&nbsp;</td>
        <td width=60 colspan=2 class="Normal">&nbsp;</td>
        <td width=84 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=70 class="Normal">&nbsp;</td>
        <td width=43 class="Normal">&nbsp;</td>
        <td width=53 colspan=2 class="Normal">&nbsp;</td>
        <td width=61 colspan=2 class="Normal">&nbsp;</td>
        <td width=143 colspan=7 class="Normal">&nbsp;</td>
        <td width=60 colspan=2 class="Normal">&nbsp;</td>
        <td width=84 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=70 class="Normal">&nbsp;</td>
        <td width=43 class="Normal">&nbsp;</td>
        <td width=53 colspan=2 class="Normal">&nbsp;</td>
        <td width=61 colspan=2 class="Normal">&nbsp;</td>
        <td width=143 colspan=7 class="Normal">&nbsp;</td>
        <td width=60 colspan=2 class="Normal">&nbsp;</td>
        <td width=84 class="Normal">&nbsp;</td>
      </tr>
      <tr height=0>
        <td width=57 class="Normal"></td>
        <td width=70 class="Normal"></td>
        <td width=43 class="Normal"></td>
        <td width=41 class="Normal"></td>
        <td width=12 class="Normal"></td>
        <td width=4 class="Normal"></td>
        <td width=57 class="Normal"></td>
        <td width=11 class="Normal"></td>
        <td width=48 class="Normal"></td>
        <td width=12 class="Normal"></td>
        <td width=36 class="Normal"></td>
        <td width=12 class="Normal"></td>
        <td width=12 class="Normal"></td>
        <td width=12 class="Normal"></td>
        <td width=27 class="Normal"></td>
        <td width=33 class="Normal"></td>
        <td width=84 class="Normal"></td>
      </tr>
    </table>
  </div>
</div>  
  </table>  
</center> 
<div class='noPrin' align="center">
	 <input type='button' value='页面设置' onclick="try{WebBrowser.ExecWB(8,1)}catch(e){alert('请设置安全级别，启用ActiveX控件和插件！')}">
	 <input type='button' value='打印预览' onclick="try{WebBrowser.ExecWB(7,1)}catch(e){alert('请设置安全级别，启用ActiveX控件和插件！')}">
	 <input type='button' value='直接打印' onclick="try{WebBrowser.ExecWB(6,6)}catch(e){alert('请设置安全级别，启用ActiveX控件和插件！')}">
</div>
</html:form>

</body>
</html>
