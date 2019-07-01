<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
		<script type="text/javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<body onload="checkWinType();">
		<html:form action="/pjpyhhgxyzhwh" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置: 评奖评优 - 信息维护 - 德育操行分维护
				</div>
			</div>
			<input type="hidden" name="pkValue" value="${pkValue }"/>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("您输入的学号无效!");
    </script>
				</logic:equal>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4" align="center">
								单个修改
							</td>
						</tr>
					</thead>
					<tr>
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
							<html:select property="xn" style="width:90px"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							姓名：
						</td>
						<td align="left">
							${rs.xm }
						</td>
						<td align="right">
							<font color="red">*</font>学期：
						</td>
						<td align="left">
							<html:select property="xq" style="width:90px"
								styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							性别：
						</td>
						<td align="left">
							${rs.xb }
						</td>
						<td align="right">
							<font color="red">*</font>项目：
						</td>
						<td align="left">
							<html:select property="dm" id="dm">
								<html:option value=""></html:option>
								<html:options collection="cxList" property="dm" labelProperty="mc"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							年级：
						</td>
						<td align="left">
							${rs.nj}
						</td>
						<td align="right">
							得分：
						</td>
						<td align="left">
							<html:text property="df" styleId="df" maxlength="6" onblur="ckinpdata(this)"></html:text>
						</td>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							${rs.xymc }
						</td>
						<td align="right">
							&nbsp;
						</td>
						<td align="left">
							&nbsp;
						</td>
					</tr><tr>
						<td align="right">
							专业：
						</td>
						<td align="left">
							${rs.zymc }
						</td>
						<td align="right">
							&nbsp;
						</td>
						<td align="left">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td align="right">
							班级：
						</td>
						<td align="left">
							${rs.bjmc }
						</td>
						<td align="right">
							&nbsp;
						</td>
						<td align="left">
							&nbsp;
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
						<logic:notEqual value="view" name="yes">
							<button type="button" class="button2"
							onclick="saveinfo('hhgxy_dycxfview.do','jxjdm');"
							style="width:80px" id="buttonSave">
							保 存
						</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:notEqual>
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
			</logic:notEmpty>
		</html:form>
	</body>
	<logic:present name="inserted">
		<logic:equal value="yes" name="inserted">
			<script>
				alert("操作成功！");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
		<logic:equal value="no" name="inserted">
			<script>
				alert("操作失败！");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
	</logic:present>
