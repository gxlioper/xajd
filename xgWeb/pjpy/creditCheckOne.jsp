<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
<script type="text/javascript" src="js/check.js"></script>
	<body>
		<html:form action="/typj" method="post">
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
			
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：${title } - 单个荣誉称号审核
				</div>
			</div>
			<table class="tbstyle" width="100%">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							单个奖学金审核
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right">
						学号：
					</td>
					<td align="left"  id="selxh">
						${rs.xh }
						<html:hidden property="save_xh" name="rs"/>
					</td>
					<td align="right">
						年度：
					</td>
					<td align="left">
						${rs.nd }
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						姓名：
					</td>
					<td align="left">
						${rs.xm }
					</td>
					<td align="right">
						学年：
					</td>
					<td align="left">
						${rs.xn }
						<html:hidden property="save_xn" name="rs"/>
						<html:hidden property="save_xq" name="rs"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						性别：
					</td>
					<td align="left">
						${rs.xb }
					</td>
					<td align="right">
						荣誉称号：
					</td>
					<td align="left">
						${rs.rychmc }
						<html:hidden property="save_rychdm" name="rs"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						年级：
					</td>
					<td align="left">
						${rs.nj }
					</td>
					<td align="right">
						德成绩：
					</td>
					<td align="left">
						${rs.dcj }
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						${rs.xymc }
					</td>
					<td align="right">
						智成绩：
					</td>
					<td align="left">
						${rs.zcj }
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						专业：
					</td>
					<td align="left">
						${rs.zymc }
					</td>
					<td align="right">
						体成绩：
					</td>
					<td align="left">
						${rs.tcj }
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						班级：
					</td>
					<td align="left">
						${rs.bjmc }
					</td>
					<td align="right">
						审核：
					</td>
					<td align="left">
						<logic:equal value="xy" name="userType" scope="session">
							<logic:equal value="true" name="isFdy" scope="session">
								<html:select property="save_fdysh" name="rs" styleId="sh">
							 		<html:options collection="shztList" property="en" labelProperty="cn"/>
							 	</html:select>
							</logic:equal>
							<logic:equal value="false" name="isFdy" scope="session">
								<html:select property="save_xysh" name="rs" styleId="sh">
							 		<html:options collection="shztList" property="en" labelProperty="cn"/>
							 	</html:select>
							</logic:equal>
						</logic:equal>
						<logic:notEqual value="xy" name="userType" scope="session">
							<html:select property="save_xxsh" name="rs" styleId="sh">
							 	<html:options collection="shztList" property="en" labelProperty="cn"/>
							</html:select>
						</logic:notEqual>
					</td>
				</tr>
					<tr>
						<td align="right">外语等级：</td>
						<td align="left" colspan="3">${wydj }</td>
					</tr>
					<tr>
						<td align="right">主要事迹：</td>
						<td align="left" colspan="3">${rs.zysj }</td>
					</tr>
				</tr>
				<logic:equal value="xy" name="userType">
					<logic:equal value="true" name="isFdy" scope="session">
						<tr>
							<td align="right">
								辅导员意见：
								<br/>
								<font color="red">(限制在400字内)</font>
							</td>
							<td align="left" colspan="3">
								<html:textarea property="save_fdyyj" name="rs" style="width:100%" rows="3" onblur="checkLen(this,400)"></html:textarea>
							</td>
						</tr>
					</logic:equal>
				
					<logic:equal value="false" name="isFdy" scope="session">
						<tr>
							<td align="right">
								辅导员意见：
							</td>
							<td align="left" colspan="3">
								${rs.fdyyj }
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />审核意见：
								<br/>
								<font color="red">(限制在400字内)</font>
							</td>
							<td align="left" colspan="3">
								<html:textarea property="save_xyyj" name="rs" style="width:100%" rows="3" onblur="checkLen(this,400)"></html:textarea>
							</td>
						</tr>
					</logic:equal>
				</logic:equal>
				<logic:notEqual value="xy" name="userType">
				<tr>
					<td align="right">
						辅导员意见：
					</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="fdyyj" />
					</td>
				</tr>				
				<tr>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />审核意见：
					</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="xxyj"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						学校审核意见：
						<br/>
						<font color="red">(限制在400字内)</font>
					</td>
					<td align="left" colspan="3">
						<html:textarea property="save_xxyj" name="rs" style="width:100%" rows="3" onblur="checkLen(this,400)"></html:textarea>
					</td>
				</tr>
				
				</logic:notEqual>
			</table>
			<div class="buttontool" align="center">
				<logic:notEqual value="view" name="doType">
					<button class="button2"
						onclick="saveUpdate('/xgxt/typj.do?method=creditCheckOne&doType=modify','');"
						style="width:90px" id="buttonSave">
						保 存
					</button>
				</logic:notEqual>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:90px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
		<logic:present name="result">
			<script>
				alert(''+$('message').value);
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
	</logic:present>
	</body>
