<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/wjcfFuction.js"></script>
<script type="text/javascript">
<!--
	//保存信息
//-->
</script>
<body>
	<html:form action="/wjcfxmlgwh" method="post">
		<input type="hidden" name="userType" id="userType"
			value="${userType }" />
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />

		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置: 当前位置：违纪处分 - 解除留校察看 - 数据维护 sdfs
			</div>
		</div>
		<table class="tbstyle" width="99%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						修改
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right" width="15%">
					学号：
				</td>
				<td align="left" width="35%">
					${rs.xh }
				</td>
				<td align="right" width="15%">
					学年：
				</td>
				<td align="left" width="35%">
					${rs.xn }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					姓名：
				</td>
				<td align="left">
					${rs.xm }
				</td>
				<td align="right">
					年度：
				</td>
				<td align="left">
					${rs.nd }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					性别：
				</td>
				<td align="left">
					${rs.xb }
				</td>
				<td align="right">
					处分类别：
				</td>
				<td align="left">
					${rs.cflbmc }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					年级：
				</td>
				<td align="left">
					${rs.nj }
				</td>
				<td align="right">
					处分原因：
				</td>
				<td align="left">
					${rs.cfyymc }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />：
				</td>
				<td align="left">
					${rs.xymc }
				</td>
				<td align="right">
					处分学年&nbsp;&nbsp;
					<br />
					年度：
				</td>
				<td align="left">
					${rs.xn }/${rs.nd }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					专业：
				</td>
				<td align="left">
					${rs.zymc }
				</td>
				<td align="right">
					处分文号：
				</td>
				<td align="left">
					${rs.cfwh }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					班级：
				</td>
				<td align="left" colspan="">
					${rs.bjmc }
				</td>
				<td align="right">
					处分时间：
				</td>
				<td align="left">
					${rs.cfsj }
				</td>
			</tr>
			<tr>
				<td align="right">
					留校察看期
				</td>
				<td align="center" colspan="3">
					${rs.lxcfsj } 到 ${rs.lxcksj }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					学生申请
					<br />
					理由要点
				</td>
				<td align="left" colspan="3">
					<font color="red">(说明受处分以来各方面的表现情况，取得的进步，阐述申请理由)</font>
					<br />
					<html:textarea property="xsbx" styleId="xsbx" rows="10"
						style="width:95%"></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
			<logic:notEqual value="view" name="writable">
				<button type="button" class="button2" id="btn_save"
					onclick="saveinfo('wjcf_xmlg_modiLxckxx.do?operType=save','');"
					style="width:80px">
					保 存
				</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notEqual>
			<button type="button" class="button2" id="btn_close" onclick="window.close();return false;"
				style="width:80px">
				关 闭
			</button>
		</div>
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>
