<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<meta name=ProgId content=Excel.Sheet>
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
	<div align="center" style="font-size:28px;font:'黑体' "><b>国家助学贷款申请审批表</b></div>
		<br>
		<div align="center" style="font-size:15px;">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;
		院校名称：&nbsp;&nbsp;${rs.xymc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  编号：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
  <table class="tbstyle" width="80%" height="850px"> 
    <tr align="center"> 
      <td width="6%" rowspan=6>申<br/>请<br/>人<br/>情<br/>况</td> 
      <td width="10%">姓名</td> 
      <td width="10%">${rs.xm }</td> 
      <td width="10%">性别</td> 
      <td width="10%">${rs.xb }</td> 
      <td width="12%">出生年月</td> 
      <td width="15%">${rs.csrq }</td> 
      <td width="12%">身份证号</td> 
      <td width="15%">${rs.sfzh }</td> 
    </tr> 
    <tr align="center"> 
      <td rowspan=2>院系</td> 
      <td colspan=3 rowspan=2>${rs.xymc }</td> 
      <td rowspan=2>学制</td> 
      <td rowspan=2>${rs.xz }</td> 
      <td>学号</td> 
      <td>${rs.xh }</td> 
    </tr> 
    <tr align="center"> 
      <td>信用卡号</td> 
      <td>${rs.yhkh }</td> 
    </tr> 
    <tr align="center"> 
      <td rowspan=2>家庭住址</td> 
      <td colspan=5 rowspan=2>　</td> 
      <td>邮政编码</td> 
      <td>${rs.jtyb }</td> 
    </tr> 
    <tr align="center"> 
      <td>联系电话</td> 
      <td>${rs.jtdh }</td> 
    </tr> 
    <tr align="center"> 
      <td>备注</td> 
      <td colspan=7></td> 
    </tr> 
    <tr align="center"> 
      <td rowspan="${cyNum+3 }">家<br/>庭<br/>情<br/>况</td> 
      <td>关系</td> 
      <td>姓名</td> 
      <td colspan=4>工作单位</td> 
      <td>每月收入</td> 
      <td>联系电话</td> 
    </tr> 
    <logic:iterate name="cyList" id="cy">
							<tr height="20px" align="center">
								<td>
										${cy.mc }&nbsp;&nbsp;
										
								</td>
								<td>
										${cy.cyxm }&nbsp;&nbsp;
								</td>
								<td colspan=4>
										${cy.cygzdw }
								</td>
								<td>
										${cy.cyysr }&nbsp;&nbsp;
								</td>
								<td>
										${cy.cydh }&nbsp;&nbsp;
								</td>								
							</tr>
						</logic:iterate>
    <tr align="center"> 
      <td>　</td> 
      <td>　</td> 
      <td colspan=4>　</td> 
      <td>　</td> 
      <td>　</td> 
    </tr> 
    <tr align="center"> 
      <td>　</td> 
      <td>　</td> 
      <td colspan=4>　</td> 
      <td>　</td> 
      <td>　</td> 
    </tr> 
    <tr align="center"> 
      <td rowspan=4>贷<br/>款<br/>事<br/>项</td> 
      <td rowspan=3>申请贷款总额</td> 
      <td rowspan=3>${rs.zje }　</td> 
      <td rowspan=3>贷款利率</td> 
      <td rowspan=3>　</td> 
      <td rowspan=3>发放方式</td> 
      <td colspan=3>一次性发放<font
  class="font68574">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </font><font class="font08574">元</font><font class="font68574">&nbsp;&nbsp;&nbsp;</font></td> 
    </tr> 
    <tr align="center"> 
      <td colspan=3>按学年发放，每学年<font class="font68574">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </font><font class="font08574">元</font></td> 
    </tr> 
    <tr align="center"> 
      <td colspan=3>　</td> 
    </tr>
				<tr>
					<td colspan=8 rowspan="1" height="80px" valign="bottom" align="right">
						<p>
						借款人签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						
						 年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
						</p>
					</td>
				</tr>
				<tr align="center">
					<td rowspan=3>
						见
						<br />
						证
						<br />
						人
					</td>
					<td>
						姓名
					</td>
					<td colspan=2>
						工作单位
					</td>
					<td colspan=3>
						与借款人关系
					</td>
					<td>
						联系电话
					</td>
					<td>
						签名
					</td>
				</tr>
				<tr align="center"> 
      <td>　</td> 
      <td colspan=2>　</td> 
      <td colspan=3>　</td> 
      <td>　</td> 
      <td>　</td> 
    </tr> 
    <tr align="center"> 
      <td>　</td> 
      <td colspan=2>　</td> 
      <td colspan=3>　</td> 
      <td>　</td> 
      <td>　</td> 
    </tr> 
    <tr align="center"> 
      <td>联<br/>系<br/>人</td> 
      <td>邵小卿</td> 
      <td colspan=2>浙江科技学院</td> 
      <td colspan=3>师生</td> 
      <td>85070162</td> 
      <td>　</td> 
    </tr> 
    <tr align="center"> 
      <td>二<br/>级<br/>学<br/>院<br/>意<br/>见</td> 
      <td colspan="4"></td>
      <td>学<br/>生<br/>处 <br/>意<br/>见</td> 
      <td colspan="3">
      
      </td> 
    </tr> 
    
    <tr align="center" height="120px"> 
      <td>贷<br/>款<br/>人<br/>意<br/>见</td> 
      <td colspan=3 align="left">
      	调查意见
		<br/><br/><br/><br/>      
      	<p align="right">
      		调查人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      		<br/>
      		年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
      	</p>
      </td> 
      <td colspan=3 align="left">
      	审查报告
		<br/><br/><br/><br/>      
      	<p align="right">
      		审查人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      		<br/>
      		年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
      	</p>
      </td> 
       <td colspan=3 align="left">
      	审批意见
		<br/><br/><br/><br/>      
      	<p align="right">
      		审批人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      		<br/>
      		年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
      	</p>
      </td> 
    </tr> 
  </table> 
</div>
	<br/>
	<br/>
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
