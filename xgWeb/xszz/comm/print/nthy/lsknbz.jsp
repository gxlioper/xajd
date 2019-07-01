<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- 头文件 -->
<html:html>
<!-- 打印控件begin -->
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media='print'>
		.noPrin{display:none;}
	</style>
<!-- end -->
<body>
	<table width="95%" border="0" id="theTable" align="center">
		<tr>
			<td align="center">
				<h2>
					${xxmc }学生困难补助申请表
				</h2>
			</td>
		</tr>
		<tr>
			<td align="center">
				<table width="100%" class="tbstyle">
					<tr>
						<td width="7%"></td>
						<td width="8%"></td>
						<td width="6%"></td>
						<td width="8"></td>
						<td width="5%"></td>
						<td width="5%"></td>
						<td width="9%"></td>
						<td width="5%"></td>
						<td width="5%"></td>
						<td width="5%"></td>
						<td width="5%"></td>
						<td width="5%"></td>
						<td width="11%"></td>
						<td width="5%"></td>
						<td width="11%"></td>
					</tr>
				  <tr height="40px">
				    <td colspan="2" align="center">姓 名</td>
				    <td colspan="2" align="center">${rs.xm }</td>
				    <td colspan="2" align="center">性别 </td>
				    <td align="center">${rs.xb }</td>
				    <td colspan="3" align="center">学 院 </td>
				    <td colspan="2" align="center">${rs.xymc }</td>
				    <td align="center">专业年级 </td>
				    <td align="center" colspan="2">${rs.zymc } &nbsp;&nbsp;${rs.nj }</td>
				  </tr>
				  <tr height="40px"> 
				    <td align="center" colspan="2"><p align="center">学 号 </p></td>
				    <td align="center" colspan="2"><p align="center">${rs.xh } </p></td>
				    <td align="center" colspan="2"><p align="center">学制 </p></td>
				    <td align="center" ><p>${rs.xz } </p></td>
				    <td align="center" colspan="3"><p align="center">毕业时间 </p></td>
				    <td align="center" colspan="2"><p>${rs.bysj } </p></td>
				    <td align="center" ><p align="center">联系电话 </p></td>
				    <td align="center" colspan="2"><p>${rs.lxdh } </p></td>
				  </tr>
				  <tr height="40px">
				    <td colspan="3"><p align="center">家庭详细地址 </p></td>
				    <td colspan="12"><p align="center">${rs.jtdz } </p></td>
				  </tr>
				  <tr height="40px">
				    <td colspan="3"><p align="center">思想品德现实表现 </p></td>
				    <td colspan="2"><p align="center">${rs.sxpdbx } </p></td>
				    <td colspan="7"><p align="center">学习总体情况 </p></td>
				    <td colspan="3"><p align="center">${rs.xxztbx } </p></td>
				  </tr>
				  <tr height="40px">
				    <td colspan="5"><p align="center">受到过何违纪处分 </p></td>
				    <td colspan="10"> ${rs.wjcfqk }</td>
				  </tr>
				  <tr height="40px">
				    <td align="center" colspan="3">本学年<br/>有否贷款</td>
				    <td align="center" colspan="2">${rs.bxnsfdk } </td>
				    <td align="center" colspan="4">本学年有否<br/>学费减免</td>
				    <td align="center" colspan="2">${rs.bxnsfjm }</td>
				    <td align="center" colspan="3">本学年有否<br/>参加勤工助学</td>
				    <td align="center">${rs.bxnsfqg }</td>
				  </tr>
				  <tr height="40px">
				    <td align="center" colspan="3">本学年是否获<br/>得奖学金 </td>
				    <td align="center" colspan="2">${rs.bxnsfjxj } </td>
				    <td align="center" colspan="4">本学年是否已<br/>获得困难补助</td>
				    <td align="center" colspan="2">${rs.bxnsfbz } </td>
				    <td align="center" colspan="3">本学年是否受<br/>过社会资助</td>
				    <td align="center">${rs.bxnsfszz }</td>
				  </tr>
				  <tr>
				    <td align="center">
				    	<br/><br/>申<br/><br/>请<br/><br/>理<br/><br/>由<br/><br/>
				    </td>
				    <td width="598" colspan="14">
				    	<p style="height:180px">
				        &nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
				       	</p>
				    </td>
				  </tr>
				  <tr>
				    <td width="283" colspan="8"><p>班级意见 </p>
				        <p>&nbsp; </p>
				        <p>&nbsp; </p>
				        <p>&nbsp; </p>
				        <p>&nbsp; </p>
				        <p>建议补助______________元 </p>
				        <p align="center">班主任签名 </p>
				        <p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				        				 月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				        				 日 </p></td>
				    <td width="358" colspan="7"><p><bean:message key="lable.xb" />意见 </p>
				        <p>&nbsp; </p>
				        <p align="left">&nbsp; </p>
				        <p align="left">同意补助______________元 </p>
				        <p align="left">&nbsp; </p>
				        <p align="right">
				        	盖章&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				        		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				        		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				        </p>
				        <p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				        				 月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				        				 日 </p></td>
				  </tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				注：请提供相关证明或详细材料
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
</body>
</html:html>
