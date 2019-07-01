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
		        	<th colspan="4"><span>假期留校申请审核</span></th>
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
					${rs.xqmc }
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
				<th>辅导员</th>
				<td>
					${rs.fdyxm }
				</td>
			</tr>
			<tr>
				<th><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>${rs.xymc }</td>
				<th>专业</th>
				<td>${rs.zymc }</td>
			</tr>
			<tr>
				<th>班级</th>
				<td>${rs.bjmc }</td>
				<th>&nbsp;</th>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<th>留校开始时间</th>
				<td>
					${rs.lxkssj}
				</td>
				<th>留校结束时间</th>
				<td>
					${rs.lxjssj}
				</td>
			</tr>
			<tr>
				<th>联系电话</th>
				<td>
					${rs.lxdh}
				</td>
				<th>宿舍编号</th>
				<td>
					${rs.ssbh}
				</td>
			</tr>
			<tr>
				<th>申请理由</th>
				<td colspan="3">
					<html:textarea property="sqly" cols="60" rows="5" onblur="checkLeng(this,500)" value="${rs.sqly}" readonly="true"></html:textarea>
				</td>
			</tr>
			<tr>
				<th>备注</th>
				<td colspan="3">
					<html:textarea property="bz" cols="60" rows="5" onblur="checkLeng(this,500)" value="${rs.bz}" readonly="true"></html:textarea>
				</td>
			</tr>
			<logic:equal value="true" name="isFdy" scope="session">
				<tr>
					<th>
						<span class="red">*</span>辅导员审核状态
					</th>
					<td colspan="3">
						<html:select property="save_fdysh" style="width:180px" value="${rs.fdysh}">
							<html:options collection="shztList" property="en" labelProperty="cn"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>辅导员审核意见</th>
					<td colspan="3">
						<html:textarea property="save_fdyshyj" cols="60" rows="5" value="${rs.fdyshyj}"></html:textarea>
					</td>
				</tr>
			</logic:equal>
			<logic:notEqual value="true" name="isFdy" scope="session">
				<logic:equal value="xy" name="userType">
				<tr>
					<th>
						辅导员审核状态
					</th>
					<td colspan="3">
						${rs.fdysh}
					</td>
				</tr>
				<tr>
					<th>辅导员审核意见</th>
					<td colspan="3">
						<html:textarea property="fdyshyj" cols="60" rows="5" value="${rs.fdyshyj}" readonly="true"></html:textarea>
					</td>
				</tr>
				<tr>
					<th>
						<span class="red">*</span><bean:message key="lable.xb" />审核状态
					</th>
					<td colspan="3">
						<html:select property="save_xysh" style="width:180px" value="${rs.xysh}">
							<html:options collection="shztList" property="en" labelProperty="cn"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th><bean:message key="lable.xb" />审核意见</th>
					<td colspan="3">
						<html:textarea property="save_xyshyj" cols="60" rows="5" value="${rs.xyshyj}"></html:textarea>
					</td>
				</tr>
				</logic:equal>
				<logic:notEqual value="xy" name="userType">
				<tr>
					<th>
						辅导员审核状态
					</th>
					<td colspan="3">
						${rs.fdysh}
					</td>
				</tr>
				<tr>
					<th>辅导员审核意见</th>
					<td colspan="3">
						<html:textarea property="fdyshyj" cols="60" rows="5" value="${rs.fdyshyj}" readonly="true"></html:textarea>
					</td>
				</tr>
				<tr>
					<th>
						<bean:message key="lable.xb" />审核状态
					</th>
					<td colspan="3">
						${rs.xysh}
					</td>
				</tr>
				<tr>
					<th><bean:message key="lable.xb" />审核意见</th>
					<td colspan="3">
						<html:textarea property="save_xyshyj" cols="60" rows="5" value="${rs.xyshyj}" readonly="true"></html:textarea>
					</td>
				</tr>
				</logic:notEqual>
			</logic:notEqual>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
		          <div class="btn">
		          	<logic:equal value="true" name="isFdy" scope="session">
						<button type="button" class="button2" id="buttonSave" onclick="saveUpdate('/xgxt/jqlxgl.do?method=jqlxshDetial&doType=save','');">
							保&nbsp;&nbsp;存
						</button>
					</logic:equal>
					<logic:notEqual value="true" name="isFdy" scope="session">
						<logic:equal value="xy" name="userType">
							<button type="button" class="button2" id="buttonSave" onclick="saveUpdate('/xgxt/jqlxgl.do?method=jqlxshDetial&doType=save','');">
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
