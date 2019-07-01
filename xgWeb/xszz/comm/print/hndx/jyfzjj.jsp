<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<html>
<head>
	<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<title>中国教育发展基金会资助计划申请表（一）</title>
</head>
<body> 
<div align="center"> 
 <p align=center ><b><span style='font-size:18.0pt;font-family:宋体;'>中国教育发展基金会资助计划申请表（一）</span></b></p>
  <table class="tbstyle" width="85%"> 
    <tr align="center" height="30px"> 
      <td colspan=11>个人 情 况</td> 
    </tr> 
    <tr height="30px" align="center"> 
      <td align="center" width="15%">姓名</td> 
      <td width="15%">${rs.xm } </td> 
      <td colspan=2 width="10%" align="center">性别</td> 
      <td colspan=2 width="15%" align="center">${rs.xb } </td> 
      <td colspan=2 width="10%" align="center">民族</td> 
      <td colspan=2 width="15%" align="center">${rs.mzmc } </td> 
      <td rowspan=5 width="20%" align="center">照片</td> 
    </tr> 
    <tr align="center" height="30px"> 
      <td>出生日期</td> 
      <td>${rs.csrq } </td> 
      <td colspan=2 align="center">籍贯</td> 
      <td colspan=6 align="center">${rs.jg } </td> 
    </tr> 
    <tr height="30px"> 
      <td align="center">所在学校</td> 
      <td colspan=9 align="center"> 
      &nbsp;&nbsp;&nbsp;${xxsheng }&nbsp;&nbsp;&nbsp;省&nbsp;&nbsp;&nbsp;${xxshi }&nbsp;&nbsp;&nbsp;县（区）</td> 
    </tr> 
    <tr align="center" height="30px"> 
      <td>身份证号</td> 
      <td colspan=4>${rs.sfzh } </td> 
      <td colspan=3> 年级</td> 
      <td colspan=2>${rs.nj } </td> 
    </tr> 
    <tr align="center" height="30px"> 
      <td> 学校地址</td> 
      <td colspan=5>${rs.xxdz } </td> 
      <td colspan=2>邮编</td> 
      <td colspan=2>${rs.xxyb }</td> 
    </tr> 
    <tr align="center" height="30px"> 
      <td> 现学习阶段</td> 
      <td colspan="10"> A<logic:equal value="大学（含高职、大专）" name="rs" property="xxjd">√</logic:equal>&nbsp;&nbsp;&nbsp;大学（含高职、大专）
      	B<logic:equal value="高中（含职高、中专、中技）" name="rs" property="xxjd">√</logic:equal>&nbsp;&nbsp;&nbsp;高中（含职高、中专、中技）
      	C<logic:equal value="初中" name="rs" property="xxjd">√</logic:equal>&nbsp;&nbsp;&nbsp;初中
      	D<logic:equal value="小学" name="rs" property="xxjd">√</logic:equal>&nbsp;&nbsp;&nbsp;小学&nbsp;&nbsp;&nbsp; </td> 
    </tr> 
    <tr align="center" height="30px"> 
      <td> 所在院系专业（大学）</td> 
      <td colspan=10>${rs.xymc }${rs.zymc } </td> 
    </tr> 
    <tr align="center" height="30px"> 
      <td> 本人联系电话或其他联系方式</td> 
      <td colspan=10>${rs.lxdh } </td> 
    </tr> 
    <tr align="center" height="30px"> 
      <td colspan=11> <b><span style='font-size:12.0pt;font-family:宋体;'>家 庭 情 况</span></b></td> 
    </tr> 
    <tr align="center" height="30px"> 
      <td>家庭所在地</td> 
      <td colspan=10> 
               ${szsheng } &nbsp;&nbsp;省/市&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               ${szshi }&nbsp;&nbsp; 地区/县&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               ${szxian }&nbsp;&nbsp; 区/乡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               ${rs.szzhen} &nbsp;&nbsp;街道/村&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               ${rs.szcun } &nbsp;&nbsp;号</td> 
    </tr> 
    <tr align="center" height="30px"> 
      <td>家庭户口</td> 
      <td colspan=6>A<logic:equal value="农村" name="rs" property="jthk">√</logic:equal>&nbsp;&nbsp;&nbsp;农村&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      				 B<logic:equal value="城镇" name="rs" property="jthk">√</logic:equal>&nbsp;&nbsp;&nbsp;城镇</td> 
      <td colspan=2> <p align=center >邮编</p></td> 
      <td colspan=2>${rs.jtyb } </td> 
    </tr> 
    <tr align="center" height="30px"> 
      <td> 家庭年收入</td> 
      <td colspan=2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.jtnzsr }&nbsp;&nbsp;&nbsp;元</td> 
      <td colspan=4>人均年收入</td> 
      <td colspan=4>&nbsp;${rs.jtrjsr } </td> 
    </tr> 
    <tr align="center" height="30px"> 
      <td>家庭总人口</td> 
      <td colspan=2> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.jtrs }&nbsp;&nbsp;&nbsp;&nbsp;人</td> 
      <td colspan=4> <p align=center >收入来源</p></td> 
      <td colspan=4>${rs.srly } </td> 
    </tr> 
    <tr align="center" height="30px"> 
      <td rowspan=2>家庭成员</td> 
      <td colspan=10>
      	姓名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  
       	亲属关系&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
  
       	年龄&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

       	职业和单位&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

       	联系电话
      
      </td> 
    </tr> 
    <tr height="120px" align="center"> 
      <td colspan=10 >
      	<logic:iterate name="cyList" id="cy">
					${cy.cyxm }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					${cy.mc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					${cy.cynl }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					${cy.cygzdw }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					${cy.cydh }&nbsp;&nbsp;
				<br/>
			</logic:iterate>
      
      </td> 
    </tr> 
    <tr align="center" height="120px"> 
      <td width=108> <p align=center >申请理由</p></td> 
      <td width=529 colspan=10 valign="top">
      	<p align="left">
      		<br/>
      		&nbsp;&nbsp;&nbsp;${rs.sqsm }
      	</p>
      </td> 
    </tr> 
    <tr align="center" height="120px"> 
      <td width=108>家庭贫困<br/>原因说明</td> 
      <td width=529 colspan=10 align="left" valign="top">
		<p align="left" >
      		<br/>
      		&nbsp;&nbsp;&nbsp;${rs.pksm }
      	</p>
	  </td> 
    </tr> 
  </table> 
  <br/><br/><br/>
</div>

		<div align="center">
			<p align=center
				style='text-align: center; line-height: 150%; layout-grid-mode: char'>
				<span style='font-size: 18.0pt; line-height: 150%; font-family: 黑体'>中国教育发展基金会资助计划申请表（二）</span>
			</p>
			<table class="tbstyle" width="85%">
				<tr height="30px">
					<td align="center">
						<b><span style='font-size: 12.0pt; font-family: 宋体;'>师长推荐和学校证明</span></b>
					</td>
				</tr>
				<tr height="200px">
					<td align="left" valign="top">
						师长推荐（所在学校校长、教导主任、班主任、任课老师等个人推荐）：
					</td>
				</tr>
				<tr height="30px">
					<td>
						推荐人姓名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						身份：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						电话：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						日期：&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr height="200px">
					<td align="left" valign="top">
						所在学校意见：
					</td>
				</tr>
				<tr height="30px">
					<td>
						联系电话：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						学校领导签名：&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						公章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
						年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						日&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr height="30px" align="center">
					<td>
						<b><span style='font-size: 12.0pt; font-family: 宋体;'>家庭经济困难情况证明</span></b>

					</td>
				</tr>
				<tr height="200px">
					<td align="left" valign="top">
						乡镇或街道办事处对申请人家庭经济困难状况和说明：
					</td>
				</tr>
				<tr height="30px">
					<td>
						<p>
					联系电话：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					公章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;
					年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					日&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
			</table>
			<p>
				<span style='font-family: 宋体;'>说明：申请资助的人员或单位，无论能否获得资助，其所附相关资料不予退回。</span>
			</p>
		</div>
		<br/><br/><br/>
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
