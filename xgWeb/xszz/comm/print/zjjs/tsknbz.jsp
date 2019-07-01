<%@ page language="java" pageEncoding="GB18030"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
	</head>
	<br/><br/>
	<p align="center" style="font-size:23px;font-family:黑体">
		浙江建设职业技术学院学生特殊困难补助申请表
	</p>
	<p align="center">
		&nbsp;
	</p>
	<table cellspacing="0" cellpadding="0" class="printtab" align="center">
		<tr class="nowrap">
			<td width="30" rowspan="4" align="center">
				学生 基本 情况
			</td>
			<td width="90" align="center">
				姓 名
			</td>
			<td width="102" align="center">
				${rs.xm }
			</td>
			<td width="53" align="center">
				性别
			</td>
			<td width="73" colspan="2" align="center">
				${rs.xb }
			</td>
			<td width="102" align="center">
				出生年月
			</td>
			<td width="139" align="center">
				${rs.csrq }
			</td>
			<td width="53" align="center">
				民族
			</td>
			<td width="58" align="center">
				${rs.mzmc }
			</td>
		</tr>
		<tr>
			<td align="center">
					户籍性质
			</td>
			<td align="center" colspan="2">
				<logic:present name="rs" property="jthk">
					<logic:equal value="城镇" property="jthk" name="rs">
						<span class="radic">□<em>&radic;</em></span>城镇&nbsp;&nbsp;&nbsp;&nbsp;□农村
					</logic:equal>
					<logic:equal value="农村" property="jthk" name="rs">
						□城镇&nbsp;&nbsp;&nbsp;&nbsp;<span class="radic">□<em>&radic;</em></span>农村
					</logic:equal>
					<logic:equal value="" property="jthk" name="rs">
						□城镇 □农村
					</logic:equal>
				</logic:present>
				<logic:notPresent name="rs" property="jthk">
					□城镇 □农村
				</logic:notPresent>
			</td>
			<td align="center" colspan="2"  class="nowrap">
					政治面貌
			</td>
			<td align="center">
				${rs.zzmmmc }
			</td>
			<td align="center">
					已享受资助情况
			</td>
			<td align="center" colspan="2">
			</td>
		</tr>
		<tr>
			<td align="center">
					系别
			</td>
			<td align="center">
				${rs.xymc }
			</td>
			<td align="center">
					班级
			</td>
			<td align="center" colspan="3">
				${rs.bjmc }
			</td>
			<td align="center">
					本人联系电话
			</td>
			<td align="center" colspan="2">
				${rs.lxdh }
			</td>
		</tr>
		<tr class="nowrap">
			<td align="center">
					家庭住址
			</td>
			<td align="center" colspan="2">
				${rs.jtdz }
			</td>
			<td align="center" colspan="3">
					家庭人均年收入
			</td>
			<td align="center" colspan="3">
				${rs.jtrjysr }&nbsp;&nbsp;	元
			</td>
		</tr>
		<tr>
			<td width="30">
				<p>
					申请 理由
				</p>
			</td>
			<td colspan="9" valign="bottom">
				<p align="left" style="height:110px">
					&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
				</p>
				<p align="right">
					学生签字 :
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</p>
				<br/>
				<p align="right">
					年&nbsp;&nbsp;&nbsp;&nbsp;
					月&nbsp;&nbsp;&nbsp;&nbsp;
					日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</p>
				<p align="center">
					（可另附详细情况说明）
				</p>
			</td>
		</tr>
		<tr>
			<td colspan="5" valign="top">
				<p align="left">
					辅导员意见：
				</p>
				<p>
					&nbsp;
				</p>
				<p>
					&nbsp;
				</p>
				<p>
					&nbsp;
				</p>
				<p>
					&nbsp;
				</p>
				<p>
					&nbsp;
				</p>
				<p>
					&nbsp;
				</p>
				<p>
					&nbsp;
				</p>
				<p>
					辅导员（签字）：
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					年&nbsp;&nbsp; 月&nbsp;&nbsp; 日&nbsp;&nbsp;
				</p>
				<p>
					&nbsp;
				</p>
			</td>
			<td colspan="5" valign="top">
				<p align="left">
					系审核意见：
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					系领导（签字）：
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
			</td>
		</tr>
		<tr>
			<td colspan="5" valign="top">
				<p align="left">
					学生办事中心审核意见：
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					审核资助金额（大写）：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 元
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					主任（签字）：
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
			</td>
			<td colspan="5" valign="top">
				<p align="left">
					学生处审核意见：
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					处长（签字）：
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;
				</p>
			</td>
		</tr>
		<tr>
			<td colspan="10" rowspan="2" valign="top">
				<p align="left">
					<bean:message key="lable.xb" />分管领导审批意见：
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="right">
					签名：
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					年&nbsp;&nbsp; 月&nbsp;&nbsp; 日
				</p>
				<p align="left">
					&nbsp;
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
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6,6);return false;"
			id="printButton">
			直接打印
		</button>
	</div>

	</body>
</html>
