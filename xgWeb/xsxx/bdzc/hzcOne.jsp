<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
	<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
	<script type="text/javascript" src="js/checkUtils.js"></script>
		<script type="text/javascript">
			function sh(url) {
				if ($('xxsh')) {
					if (' ' == $('xxsh').value) {
						alert('带*项不能为空！');
						return false;
					}
				}
				if ($('xysh')) {
					if (' ' == $('xysh').value) {
						alert('带*项不能为空！');
						return false;
					}
				}
				saveUpdate(url,'');
				
			}
		</script>
</head>
<body>
	<html:form action="/bdzcgl" method="post">
		<input type="hidden" name="message" id="message" value="${message }"/>
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<input type="hidden" name="save_xn" value="${rs.xn }"/>
		<input type="hidden" name="save_nd" value="${rs.nd }"/>
		<input type="hidden" name="save_xq" value="${rs.xq }"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		
		<div class="tab">
		<table class="formlist" width="100%">
			<tbody>
			<tr>
				<th><span class="red">*</span>学号</th>
				<td>
					<html:text  property="save_xh" styleId="xh" readonly="true" value="${rs.xh}"/>
				</td>
				<th>姓名</th>
				<td>
					${rs.xm }
				</td>
			</tr>
			<tr>
				<th>性别</th>
				<td>${rs.xb }</td>
				<th><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>${rs.xymc }</td>
			</tr>
			<tr>
				<th>专业</th>
				<td>${rs.zymc }</td>
				<th>班级</th>
				<td>${rs.bjmc }</td>
			</tr>
			<tr>
				<th>申请原因</th>
				<td colspan="3">
					<logic:equal value="sh" name="doType">
						<html:text property="save_sqyy" maxlength="50" value="${rs.sqyy}" readonly="true"></html:text>
					</logic:equal>
					<logic:notEqual value="sh" name="doType">
						<html:text property="save_sqyy" maxlength="50" value="${rs.sqyy}" ></html:text>
					</logic:notEqual>
					
				</td>
			</tr>
			<tr>
				<th>申请理由</th>
				<td colspan="3">
					<logic:equal value="sh" name="doType">
						<html:textarea property="save_sqly" cols="60" rows="5" value="${rs.sqly}" readonly="true"></html:textarea>
					</logic:equal>
					<logic:notEqual value="sh" name="doType">
						<html:textarea property="save_sqly" cols="60" rows="5" value="${rs.sqly}" onblur="checkLeng(this,500)"></html:textarea>
					</logic:notEqual>
					
				</td>
			</tr>
			<logic:equal value="sh" name="doType">
				<logic:equal value="xy" name="userType">
					<tr>
						<th>
							<span class="red">*</span><bean:message key="lable.xsgzyxpzxy" />审核
						</th>
						<td colspan="3">
							<html:select property="save_xysh" styleId="xysh" name="rs">
								<html:option value=" "></html:option>
								<html:options collection="shztList" property="en" labelProperty="cn"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<th><bean:message key="lable.xsgzyxpzxy" />意见</th>
						<td colspan="3">
							<html:textarea property="save_xyyj" cols="60" rows="5" onblur="checkLeng(this,500)" name="rs"></html:textarea>
						</td>
					</tr>
				</logic:equal>
				<logic:notEqual value="xy" name="userType">
						<tr>
							<th><span class="red">*</span>学校审核</th>
							<td colspan="3">
								<html:select property="save_xxsh" styleId="xxsh" name="rs">
									<html:option value=" "></html:option>
									<html:options collection="shztList" property="en" labelProperty="cn"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>学校意见</th>
							<td colspan="3">
								<html:textarea property="save_xxyj" cols="60" rows="5" onblur="checkLeng(this,500)" name="rs"></html:textarea>
							</td>
						</tr>
				</logic:notEqual>
			</logic:equal>
			<logic:equal value="view" name="doType">
				<tr>
					<th><bean:message key="lable.xsgzyxpzxy" />审核</th>
					<td colspan="3">
						<html:text property="save_xysh" name="rs" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<th><bean:message key="lable.xsgzyxpzxy" />意见</th>
					<td colspan="3">
						<html:textarea property="save_xyyj" cols="60" rows="5" name="rs" readonly="true"></html:textarea>
					</td>
				</tr>
				<tr>
					<th>学校审核</th>
					<td colspan="3">
						<html:text property="save_xxsh" name="rs" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<th>学校意见</th>
					<td colspan="3">
						<html:textarea property="save_xxyj" cols="60" rows="5" name="rs" readonly="true"></html:textarea>
					</td>
				</tr>
			</logic:equal>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
		          <div class="btn">
		            <logic:equal value="view" name="doType">
						<button type="button" class="button2" id="buttonSave" onclick="window.close();return false;">
							关&nbsp;&nbsp;闭
						</button>
					</logic:equal>
					<logic:equal value="sh" name="doType">
						<button type="button" class="button2" id="buttonSave" onClick="sh('/xgxt/bdzcgl.do?method=hzcOne&doType=modify');">
								保&nbsp;&nbsp;存
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" id="buttonSave" onclick="window.close();return false;">
							关&nbsp;&nbsp;闭
						</button>
					</logic:equal>
					<logic:equal value="modi" name="doType">
						<button type="button" class="button2" id="buttonSave" onclick="saveUpdate('/xgxt/bdzcgl.do?method=hzcOne&doType=modify','save_xh-save_sqyy_save_sqly');">
						修&nbsp;&nbsp;改
						</button>
					</logic:equal>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
		</table>
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
</html>