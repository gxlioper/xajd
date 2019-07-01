<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<title>申请贷款学生基本情况调查表</title>
		<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
</head>
<body> 
<div align="center"> 
  <div align="center" style="font-size:28px;font:'黑体' "><b>申请贷款学生基本情况调查表</b></div>
			<table border="0" width="80%">
				<tr>
					<td>
						<p style="font-size: 15px;">
							学&nbsp; 院：${rs.xymc}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							学 号：${rs.xh}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							身份证号：${rs.sfzh }
						</p>
					</td>
				</tr>
			</table>

			<table class="printstyle" width="80%" > 
    <tr align="center"> 
      <td width="14%">姓&nbsp;&nbsp;名</td> 
      <td width="12%">${rs.xm }</td> 
      <td colspan=1 width="14%">籍 &nbsp;&nbsp;贯</td> 
      <td colspan=3 width="20%">${rs.jg } </td> 
      <td width="13%">性&nbsp;&nbsp; 别</td> 
      <td width="10%">${rs.xb } </td> 
      <td width="10%">学&nbsp;&nbsp;制</td> 
      <td width="7%">${rs.xz }</td> 
    </tr> 
    <tr align="center"> 
      <td>民&nbsp;&nbsp; 族</td> 
      <td>${rs.mzmc } </td> 
      <td colspan=1>专&nbsp;&nbsp; 业</td> 
      <td colspan=3>${rs.zymc } </td> 
      <td> 入学日期</td> 
      <td colspan=3>${rs.rxrq } </td> 
    </tr> 
    <tr align="center"> 
      <td> QQ 号</td> 
      <td> ${rs.qqhm }</td> 
      <td colspan=1>E-MIAL</td> 
      <td colspan=3>${rs.lxdzxx } </td> 
      <td> 健康状况</td> 
      <td colspan=3>
      	<logic:equal value="" name="rs" property="jkzk">
      	□良好 &nbsp;&nbsp;□一般
      	</logic:equal>
      	
      	<logic:equal value="良好" name="rs" property="jkzk">
      	■良好 &nbsp;&nbsp;□一般
      	</logic:equal>
      	
      	<logic:equal value="一般" name="rs" property="jkzk">
      	□良好 &nbsp;&nbsp;■一般
      	</logic:equal>
      	
      </td> 
    </tr> 
    <tr align="center"> 
      <td> <p align=center>家庭地址</p></td> 
      <td colspan=5>${rs.jtdz }</td> 
      <td> <p align=center>家庭电话</p></td> 
      <td colspan=3>${rs.jtdh }</td> 
    </tr> 
    <tr align="center"> 
      <td>家庭邮编</td> 
      <td>${rs.jtyb }</td> 
      <td colspan="3">是否曾获得助学贷款</td> 
      <td>${rs.sfdk } </td> 
      <td>补考科数</td> 
      <td colspan=3>${rs.bkkm } </td> 
    </tr> 
    <tr height="80px"> 
      <td align="center">申请理由<br/>（描述清楚）</td> 
      <td colspan=9>
      <p>&nbsp;&nbsp;${rs.sqly }</p>
      	<p></p>
		<p align="center">
      	申请人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日
      	</p>
      </td>
    </tr> 
    <tr> 
      <td colspan=10> 
      	<p align="left">
      	        &nbsp;&nbsp;（1）&nbsp;&nbsp;&nbsp;以上所填内容真实，不小存在虚假成分。<br/>
      	        &nbsp;&nbsp;（2）&nbsp;&nbsp;&nbsp;如本人所填内容与事实不符，将承担由此带来的相应责任。
        </p> 
        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签&nbsp;&nbsp;&nbsp; 名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; （须在签定合同时现场签名）</p> 
        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 年&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp; 日</p></td> 
    </tr> 
    <tr align="center"> 
      <td> 学<br/>费<br/>证<br/>明</td> 
      <td colspan=9>
      	<p>该生在校期间学费标准为<u>&nbsp;&nbsp;&nbsp;&nbsp; </u>元/学年，住宿费标准为<u>&nbsp;&nbsp;&nbsp;&nbsp; </u>元/学年，合计<u>&nbsp;&nbsp;&nbsp;&nbsp; </u>元/学年。</p> 
        <p>特此证明</p> 
        <p align=center><bean:message key="lable.xb" />盖章：</p></td> 
    </tr> 
    <tr align="center"> 
      <td rowspan=3>贷<br/>款<br/>申<br/>请</td> 
      <td colspan=2>申请贷款总额</td> 
      <td colspan=3>${rs.zje }&nbsp;&nbsp;元（小写）</td> 
      <td colspan=1> <p align=center>申请贷款年限</p></td> 
      <td colspan=3> <p align=center>${rs.dknx }&nbsp;&nbsp; 年</p></td> 
    </tr> 
    <tr align="center"> 
      <td colspan=9> <p align=center>用款计划</p></td> 
    </tr> 
    <tr align="center"> 
      <td colspan=9> <p align=center>20&nbsp;&nbsp; -20&nbsp;&nbsp;&nbsp; 学年:&nbsp;&nbsp;&nbsp; 用款金额：____________元</p> 
        <p align=center>20&nbsp;&nbsp; -20&nbsp;&nbsp;&nbsp; 学年:&nbsp;&nbsp;&nbsp; 用款金额：____________元</p> 
        <p align=center>20&nbsp;&nbsp; -20&nbsp;&nbsp;&nbsp; 学年:&nbsp;&nbsp;&nbsp; 用款金额：____________元</p> 
        <p align=center>20&nbsp;&nbsp; -20&nbsp;&nbsp;&nbsp; 学年:&nbsp;&nbsp;&nbsp; 用款金额：____________元</p> 
        <p align=center>借款人（签字）：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 年&nbsp;&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日</p></td> 
    </tr> 
    <tr height="120px"> 
      <td> <p align=center>备注</p></td> 
      <td colspan=9 align="left"> 
      	<p>
      	&nbsp;&nbsp;${rs.bz }
      	</p>
      </td> 
    </tr> 
  </table>
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
</div>
</body>
</html>
