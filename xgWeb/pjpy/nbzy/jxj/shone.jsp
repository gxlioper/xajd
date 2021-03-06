<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<body>
	<html:form action="/pjpynbzywh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
	<input type="hidden" name="pkValue" id="pkVlaue" value="${pkValue }"/>
		<div class="title">
			<div class="title_img" id="title_m">
				当前位置：评奖评优 － 审核 － 奖学金审核
			</div>
		</div>
		<table class="tbstyle" width="99%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						单个审核
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right">
					<font color="red">*</font>学号：
				</td>
				<td align="left">
						${rs.xh }
				</td>
				<td align="right">
					<font color="red">*</font>学年：
				</td>
				<td align="left">
					<bean:write name="rs" property="xn" />
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					姓名：
				</td>
				<td align="left">
					<bean:write name="rs" property="xm" />
				</td>
				<td align="right">
					<font color="red">*</font>申报奖学金等级:
				</td>
				<td align="left">
					${rs.jxjmc }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					性别：
				</td>
				<td align="left">
					<bean:write name="rs" property="xb" />
				</td>
				<td align="right">
					寝室号:
				</td>
				<td align="left">
					${rs.qsh }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					年级：
				</td>
				<td align="left">
					<bean:write name="rs" property="nj" />
				</td>
				<td align="right">
					职业技能素养测评分:
				</td>
				<td align="left">
					${rs.zyjnsyf }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />：
				</td>
				<td align="left">
					<bean:write name="rs" property="xymc" />
				</td>
				<td align="right">
					可持续发展素质测评分:
				</td>
				<td align="left">
					${rs.kcxfzsyf }
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
					职业素养测评分:
				</td>
				<td align="left">
					${rs.zysyf }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					班级：
				</td>
				<td align="left">
					${rs.bjmc }
				</td>
				<td align="right">
					名次:
				</td>
				<td align="left">
					${rs.zypm }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					综合测评总分:
				</td>
				<td align="left">
					${rs.zhcpzf }
				</td>
				<td align="right">
					名次:
				</td>
				<td align="left">
					${rs.zhpm }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					<logic:equal value="xy" name="userType"><bean:message key="lable.xsgzyxpzxy" /></logic:equal><logic:notEqual value="xy" name="userType">学校</logic:notEqual>审核:
				</td>
				<td align="left" colspan="3">
					<html:select property="yesNo" styleId="yesNo">
						<html:option value=""></html:option>
						<html:options collection="chkList" property="en" labelProperty="cn"/>
					</html:select>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					申请理由:
				</td>
				<td align="left" colspan="3">
					<html:textarea property="sqly" rows="3" readonly="true" styleId="sqly" style="width:95%"></html:textarea>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					班级测评&nbsp;<br/>小组意见:
				</td>
				<td align="left" colspan="3">
					<html:textarea property="fdyyj" styleId="fdyyj" style="width:95%" readonly="true" rows="3"></html:textarea>
				</td>
			</tr>
			<logic:equal value="xy" name="userType">
				<tr style="height:23px">
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />综合测&nbsp;<br/>评小组意见:
				</td>
				<td align="left" colspan="3">
					<html:textarea property="yj" styleId="yj"  style="width:95%" rows="5"></html:textarea>
				</td>
			</tr>
			</logic:equal>
			<logic:notEqual value="xy" name="userType">
				<tr style="height:23px">
				<td align="right">
					学工部<br/>意见:
				</td>
				<td align="left" colspan="3">
					<html:textarea property="yj" styleId="yj" style="width:95%" rows="3"></html:textarea>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />意见:
				</td>
				<td align="left" colspan="3">
					<html:textarea property="xyshyj" styleId="xyshyj" style="width:95%" rows="3"></html:textarea>
				</td>
			</tr>
			</logic:notEqual>
		</table>
		<div class="buttontool" align="center">
					<logic:notPresent name="act">
						<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('pjpy_nbzy_shone.do?act=save','');"
						style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notPresent>
					<button type="button" class="button2" id="btn_close" onclick="window.close();return false;" style="width:80px">
						关闭
					</button>
				</div>
				<!-- 保存后提示页面 -->	
		<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert('操作成功!');
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert('操作失败!');
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
		</logic:present>
	</html:form>
</body>
