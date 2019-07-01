<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script type="text/javascript" src="js/checkUtils.js"></script>
	<script type="text/javascript">
	</script>
</head>

<body>
	<html:form action="/jqlxgl" method="post">
		<input type="hidden" name="message" id="message" value="${message }"/>
		<input type="hidden" id="pkValue" name="pkValue" value="${rs.pk }"/>
		<input type="hidden" id="save_xn" name="save_xn" value="${rs.xn }"/>
		<input type="hidden" id="save_xq" name="save_xq" value="${rs.xq }"/>
		<input type="hidden" id="save_xh" name="save_xh" value="${rs.xh }"/>

		<div class="tab">
		<table class="formlist" width="100%">
			<thead>
		    	<tr>
		        	<th colspan="4"><span>学生注册审核</span></th>
		        </tr>
		    </thead>
			<tbody>
			<tr>
				<th>学年</th>
				<td>
					${rs.xn }
				</td>
				<th>学期</th>
				<td>
					${rs.xq }
				</td>
			</tr>
			<tr>
				<th>学号</th>
				<td>
					${rs.xh }
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
				<th>注册状态</th>
				<td>
					${rs.zczt }
				</td>
				<logic:notEqual value="true" name="isFdy" scope="session">
					<logic:notEqual value="xy" name="userType" scope="session">
						<th>
							审核状态
						</th>
						<td>${rs.fdysh }</td>
					</logic:notEqual>
					<logic:equal value="xy" name="userType" scope="session">
						<th>&nbsp;</th>
						<td>&nbsp;</td>
					</logic:equal>
				</logic:notEqual>
				<logic:equal value="true" name="isFdy" scope="session">
					<th>&nbsp;</th>
					<td>&nbsp;</td>
				</logic:equal>
			</tr>
			<tr>
				<th>原因</th>
				<td colspan="3">
					<html:textarea property="yy" cols="60" rows="5" value="${rs.yy}" readonly="true"></html:textarea>
				</td>
			</tr>
			<logic:equal value="true" name="isFdy" scope="session">
				<tr>
					<th>
						<span class="red">*</span>审核状态
					</th>
					<td colspan="3">
						<html:select property="save_fdysh" style="width:180px" value="${rs.fdysh}">
							<html:options collection="shztList" property="en" labelProperty="cn"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>审核意见</th>
					<td colspan="3">
						<html:textarea property="save_fdyyj" cols="60" rows="5" value="${rs.fdyyj}"></html:textarea>
					</td>
				</tr>
			</logic:equal>
			<logic:notEqual value="true" name="isFdy" scope="session">
				<logic:equal value="xy" name="userType" scope="session">
					<tr>
						<th>
							<span class="red">*</span>审核状态
						</th>
						<td colspan="3">
							<html:select property="save_fdysh" style="width:180px" value="${rs.fdysh}">
								<html:options collection="shztList" property="en" labelProperty="cn"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<th>审核意见</th>
						<td colspan="3">
							<html:textarea property="save_fdyyj" cols="60" rows="5" value="${rs.fdyyj}"></html:textarea>
						</td>
					</tr>
				</logic:equal>
			</logic:notEqual>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4">
		        	<logic:equal value="true" name="isFdy" scope="session"><div class="bz">"<span class="red">*</span>"为必填项</div></logic:equal>
		        	<logic:notEqual value="true" name="isFdy" scope="session">
		        		<logic:equal value="xy" name="userType" scope="session">
		        			<div class="bz">"<span class="red">*</span>"为必填项</div>
		        		</logic:equal>
		        	</logic:notEqual>
		          <div class="btn">
		          <logic:equal value="true" name="isFdy" scope="session">
					<button type="button" class="button2" id="buttonSave" onclick="saveUpdate('/xgxt/xszcgl.do?method=xszcshDetial&doType=save','');">
						保&nbsp;&nbsp;存
					</button>
				 </logic:equal>
				 <logic:notEqual value="true" name="isFdy" scope="session">
				 	<logic:equal value="xy" name="userType" scope="session">
				 		<button type="button" class="button2" id="buttonSave" onclick="saveUpdate('/xgxt/xszcgl.do?method=xszcshDetial&doType=save','');">
							保&nbsp;&nbsp;存
						</button>
				 	</logic:equal>
				 </logic:notEqual>
					<button type="button" class="button2" id="buttonSave" onclick="Close();return false;">
						关&nbsp;&nbsp;闭
					</button>
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
