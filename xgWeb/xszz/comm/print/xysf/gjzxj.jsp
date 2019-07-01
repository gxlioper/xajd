<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<title>信阳师范学院国家助学金申请表</title>
	<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
</head>
<body> 
<div> 
  <p align=center style='font-size:22.0pt;font-family:宋体'><strong>信阳师范学院国家助学金申请表</strong></p> 
  <p align=center style='font-size:15.0pt;font-family:宋体;font-weight:normal;'><strong>（${rs.xn } 学年）</strong></p> 
  <table class="tbstyle" border="0" align="center" style="width: 90%"> 
    <tr> 
      <td width=7% rowspan=4> <p align=center>本<br/>人<br/>情<br/>况<br/></p></td> 
      <td width=15%> <p align=center>姓名</p></td> 
      <td width=15%>${rs.xm }</td> 
      <td width=8%> <p align=center>性别</p></td> 
      <td width=15% colspan=2>${rs.xb } </td> 
      <td width=18%> <p align=center>出生年月</p></td> 
      <td width=22% colspan=2>${rs.csrq } </td> 
    </tr> 
    <tr> 
      <td> <p align=center>民族</p></td> 
      <td>${rs.mzmc } </td> 
      <td> <p align=center>政治</p> 
        <p align=center>面貌</p></td> 
      <td colspan=2>${rs.zzmmmc }</td> 
      <td > <p align=center>入学时间</p></td> 
      <td colspan=2>${rs.rxrq }</td> 
    </tr> 
    <tr> 
      <td> <p align=center>身份证号码</p></td> 
      <td colspan=4>${rs.sfzh }</td> 
      <td> <p align=center>联系电话</p></td> 
      <td colspan=2>${rs.lxdh } </td> 
    </tr> 
    <tr> 
      <td colspan=5> <p align=center>家庭经济困难认定档次</p></td> 
      <td colspan=3>${rs.knjb } </td> 
    </tr> 
    <tr> 
      <td rowspan=3> <p align=center>家<br/>庭<br/>经<br/>济<br/>情<br/>况<br/></p></td> 
      <td> <p align=center>家庭户口</p></td> 
      <td colspan=5> 
      	<p align=center>
									A、<logic:equal value="城镇" property="jthk" name="rs">
										√
									</logic:equal>城镇
									&nbsp;&nbsp;
									B、<logic:equal value="农村" property="jthk" name="rs">
										√
									</logic:equal>
									农村
		</p></td> 
      <td width="6%"> <p align=center>家庭<br/>人口</p> 
      </td> 
      <td>${rs.jtrs } </td> 
    </tr> 
    <tr> 
      <td> <p align=center>家庭月总收入</p></td> 
      <td>${rs.jtyzsr } </td> 
      <td colspan=3> <p align=center>人均月收入</p></td> 
      <td>${rs.jtrjysr }</td> 
      <td> <p align=center>收入<br/>来源</p> 
      </td> 
      <td>${rs.srly } </td> 
    </tr> 
    <tr> 
      <td> <p align=center>家庭住址</p></td> 
      <td colspan=5>${rs.jtdz }</td> 
      <td> <p align=center>邮政<br/>编码</p> 
      <td>${rs.jtyb } </td> 
    </tr> 
    <tr> 
      <td colspan=9> <p>申请理由：</p> 
      	<p>&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }</p>
        <p align=right >申请人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 年&nbsp;&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日&nbsp;&nbsp;&nbsp;&nbsp;</p></td> 
    </tr> 
    <tr> 
      <td colspan=5> <p><bean:message key="lable.xb" />审核意见：</p> 
        <p><span style='font-size:18.0pt;font-family:华文行楷'>同意发放&nbsp;&nbsp;&nbsp;等国家助学金。</span></p> 
        <p align=right>（公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日</p></td> 
      <td colspan=4> <p>学校审核意见:</p> 
      	<p>&nbsp;&nbsp;&nbsp;</p>
        <p align=right>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 年&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp; 日</p></td> 
    </tr> 
  </table> 
</div> 
	<div align="center" class='noPrin'>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
			页面设置
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
			打印预览
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			直接打印
		</button>
	</div>
</body>
</html>
