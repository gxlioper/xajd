
<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- 头文件 -->
<script type="text/javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
<script type="text/javascript">
<!--
	function saveYxbys() {
			refreshForm('ynys_yxbysmodisave.do');
			document.getElementById('btn_save').disabled = true;
	}	
//-->
</script>
<body onload="">
	<html:form action="/pjpyynyswh" method="post">
		<input type="hidden" name="userType" id="userType"
			value="${userType }" />
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<div class="title">
			<div class="title_img" id="title_m">
				<bean:message key="pjpy_ynys_yxbyssqqry" bundle="pjpyynys" />
			</div>
		</div>
		<table class="tbstyle" width="100%">
			<thead>
				<tr><td colspan="4" align="center">
					优秀毕生生信息单个修改
				</td></tr>
			</thead>
			<tr height="22px">
				<td align="right" style="width:15%">
					学号:
				</td>
				<td align="left">
					<bean:write name="rs" property="xh"/>
					<input type="hidden" name="xh" id="xh" value="<bean:write name="rs" property="xh"/>">
				</td>
				<td align="right" style="width:15%">
					学年:
				</td>
				<td align="left">
					<bean:write name="rs" property="xn"/>
				</td>
			</tr>
			<tr height="22px">
				<td align="right" style="width:15%">
					姓名:
				</td>
				<td align="left">
					<bean:write name="rs" property="xm"/>
				</td>
				<td align="right">
					民族:
				</td>
				<td align="left">
					<html:text property="mz" styleId="mz"></html:text>
				</td>
			</tr>
			<tr height="22px">
				<td align="right" style="width:15%">
					性别:
				</td>
				<td align="left">
					<bean:write name="rs" property="xb"/>
				</td>
				<td align="right" style="width:15%">
					政治面貌:
				</td>
				<td align="left">
					<html:text property="zzmm" styleId="zzmm"></html:text>
				</td>
			</tr>
			<tr height="22px">
				<td align="right" style="width:15%">
					年级:
				</td>
				<td align="left">
					<bean:write name="rs" property="nj"/>
				</td>
				<td align="right" style="width:15%">
					健康状况:
				</td>
				<td align="left">
					<html:text property="jkzk" styleId="jkzk"></html:text>
				</td>
			</tr>
			<tr height="22px">
				<td align="right" style="width:15%">
					院(系):
				</td>
				<td align="left">
					<bean:write name="rs" property="xymc"/>
				</td>
				<td align="right" style="width:15%">
					生源地:
				</td>
				<td align="left">
					<html:text property="syd" styleId="syd" ></html:text>
				</td>
			</tr>
			<tr height="22px">
				<td align="right">
					专业:
				</td>
				<td align="left">
					<bean:write name="rs" property="zymc"/>
				</td>
				<td align="right">
					出生年月
				</td>
				<td align="left">
					<bean:write name="rs" property="csrq"/>
				</td>
			</tr>
			<tr height="22px">
				<td align="right">
					班级:
				</td>
				<td align="left">
					<bean:write name="rs" property="bjmc"/>
				</td>
				<td align="right">
					入学时间:
				</td>
				<td align="left">
					<bean:write name="rs" property="rxrq"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					家庭详细地址:
				</td>
				<td align="left" colspan="3">
					<html:text property="jtdz" styleId="jtdz" style="width:95%"></html:text>
				</td>
			</tr>
			<tr height="22px">
				<td align="right">
					优秀事迹:
				</td>
				<td align="left" colspan="3">
					<html:textarea property="yxsj" styleId="yxsj" rows="5"
						style="width:95%"></html:textarea>
				</td>
			</tr>
			<tr height="22px">
				<td align="right">
					班级意见:
				</td>
				<td align="left" colspan="3">
					<html:textarea property="bjyj" styleId="bjyj" rows="5"
						style="width:95%"></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
			<logic:notPresent name="act">
				<button type="button" class="button2" id="btn_save" onclick="saveYxbys()"
				style="width:80px">
				保 存
			</button>
			</logic:notPresent>
			&nbsp;&nbsp;
			<button type="button" class="button2" id="btn_close"
				onclick="window.close();return false;"
				style="width:80px">
				关闭
			</button>
		</div>
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>
