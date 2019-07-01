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
</head>

<body>
	<html:form action="/bdzcgl" method="post">
		<input type="hidden" name="pk" value="${pk }"/>
		<input type="hidden" id="url" name="url" value="/bdzcgl.do?method=hzcsq" />
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-bjmc" />
		<input type="hidden" name="message" id="message" value="${message }"/>
		<input type="hidden" name="save_xn" value="${xn }"/>
		<input type="hidden" name="save_nd" value="${nd }"/>
		<input type="hidden" name="save_xq" value="${xq }"/>
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
					<logic:notEqual value="stu" name="userType">
						<button type="button" onclick="showTopWin('/xgxt/stu_info.do',800,600);" class="btn_01" id="buttonFindStu">
							选择
						</button>
					</logic:notEqual>
				</td>
				<th>姓名</th>
				<td width="30%">
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
				<th><span class="red">*</span>申请原因</th>
				<td>
					<html:text property="save_sqyy" maxlength="50" ></html:text>
				</td>
				<th></th>
				<td></td>
			</tr>
			<tr>
				<th><span class="red">*</span>申请理由</th>
				<td colspan="3">
					<html:textarea property="save_sqly" cols="60" rows="5" onblur="checkLeng(this,500)"></html:textarea>
				</td>
			</tr>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
		          <div class="btn">
		            <logic:equal value="true" name="isHzcsj">
						<button type="button" class="button2" id="buttonSave" onclick="saveUpdate('/xgxt/bdzcgl.do?method=hzcsq&doType=save','save_xh-save_sqyy-save_sqly');">
							保&nbsp;&nbsp;存
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
