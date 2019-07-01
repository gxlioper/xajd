<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- 头文件 -->
<html:html>
<body>
	<html:form action="/typj" method="post">
		<table width="100%" class="tbstyle">
			<tr>
				<td width="7%"></td>
				<td width="13%"></td>
				<td width="10%"></td>
				<td width="10%"></td>
				<td width="8%"></td>
				<td width="8%"></td>
				<td width="14%"></td>
				<td width="14%"></td>
				<td width="2%"></td>
				<td width="14%"></td>
			</tr>
			<tr height="40px">
				<td align="center" colspan="2">姓&nbsp;&nbsp;&nbsp;&nbsp;名</td>
				<td align="center" colspan="2">${rs.xm }</td>
				<td align="center">性别</td>
				<td align="center">${rs.xb }</td>
				<td align="center">系别、班级</td>
				<td align="center" colspan="3">${rs.zymc }&nbsp;&nbsp;${rs.bjmc }</td>
			</tr>
			<tr height="40px">
				<td align="center" colspan="2">家庭住址</td>
				<td align="center" colspan="5">${rs.jtdz }</td>
				<td align="center">家庭固定电话</td>
				<td align="center" colspan="2">${rs.jtdh }</td>
			</tr>
			<tr height="40px">
				<td align="center" colspan="3">开具贫困证明部门</td>
				<td align="center" colspan="4">${rs.zmbm }</td>
				<td align="center">籍贯（生源地）</td>
				<td align="center"colspan="2">${rs.jg }</td>
			</tr>
			<tr height="40px">
				<td align="center" colspan="3">应缴费用(元)</td>
				<td colspan="7">
					教材代办费：${rs.jcdbf }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					住宿费：${rs.zsf }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					学费：${rs.xf }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
			<tr height="40px">
				<td align="center" colspan="3">拟先缴费用(元)</td>
				<td align="center" colspan="3">${rs.xjfy }</td>
				<td align="center" colspan="3">欠缴费用（申请缓交）（元）</td>
				<td align="center">${rs.qjfy }</td>
			</tr>
			<tr height="40px">
				<td align="center">申请<br/>理由</td>
				<td colspan="9" >
					<p style="height:100px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
					</p>
					<div align="right">
						申请人签名：
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
						本人联系电话：
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						日&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr height="40px">
				<td align="center">院系<br/>意见</td>
				<td colspan="9">
					<p style="height:80px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyshyj }
					</p>
					<div align="right">
						院系资助工作组负责人签名加盖院系章：
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<br/>
						年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						日&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr height="40px">
				<td align="center">备注</td>
				<td colspan="9">
					1、申请人领取本表前，应当提供相关贫困证明材料，如《高等学校学生及家庭情况调查表》。<br/>
					2、申请人应当如实填写本表。经院系批准后，可以凭此表到财务处办理入学缴费手续。<br/>
					3、本表一式两份，第一联院系留存，第二联财务处留存。
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
