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
		<input type="hidden" name="save_xn" value="${rs.xn }"/>
		<input type="hidden" name="save_xq" value="${rs.xq }"/>
		<input type="hidden" id="pkValue" name="pkValue" value="${rs.pk }"/>

		<div class="tab">
		<table class="formlist" width="100%">
			<thead>
		    	<tr>
		        	<th colspan="4"><span>假期留校申请</span></th>
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
					${map.xh }
					<input type="hidden" id="save_xh" name="save_xh" value="${map.xh }"/>
				</td>
				<th>姓名</th>
				<td>
					${map.xm }
				</td>
			</tr>
			<tr>
				<th>性别</th>
				<td>${map.xb }</td>
				<th>辅导员</th>
				<td>
					${map.fdyxm }
				</td>
			</tr>
			<tr>
				<th><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>${map.xymc }</td>
				<th>专业</th>
				<td>${map.zymc }</td>
			</tr>
			<tr>
				<th>班级</th>
				<td>${map.bjmc }</td>
				<th>&nbsp;</th>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<th>留校开始时间</th>
				<td>
					<html:text property="save_lxkssj"
							onclick="return showCalendar('save_lxkssj','y-mm-dd');" styleClass="text_nor" styleId="save_lxkssj"
							readonly="true" value="${rs.lxkssj}"/>
				</td>
				<th>留校结束时间</th>
				<td>
					<html:text property="save_lxjssj"
							onclick="return showCalendar('save_lxjssj','y-mm-dd');" styleClass="text_nor" styleId="save_lxjssj"
							readonly="true" value="${rs.lxjssj}"/>
				</td>
			</tr>
			<tr>
				<th><logic:equal value="ok" name="buttonCtrl"><span class="red">*</span></logic:equal>联系电话</th>
				<td>
					<html:text property="save_lxdh" maxlength="20" value="${rs.lxdh}"></html:text>
				</td>
				<th>宿舍编号</th>
				<td>
					<html:text property="save_ssbh" maxlength="20" value="${rs.ssbh}"></html:text>
				</td>
			</tr>
			<tr>
				<th><logic:equal value="ok" name="buttonCtrl"><span class="red">*</span></logic:equal>申请理由<br/><font color="red">(限制字数500个)</font></th>
				<td colspan="3">
					<html:textarea property="save_sqly" cols="60" rows="5" onblur="checkLeng(this,500)" value="${rs.sqly}"></html:textarea>
				</td>
			</tr>
			<tr>
				<th>备注<br/><font color="red">(限制字数500个)</font></th>
				<td colspan="3">
					<html:textarea property="save_bz" cols="60" rows="5" onblur="checkLeng(this,500)" value="${rs.bz}"></html:textarea>
				</td>
			</tr>
			<logic:notEqual value="ok" name="buttonCtrl">
				<tr>
					<th>
						辅导员审核状态
					</th>
					<td colspan="3">
						${rs.fdysh }
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
						${rs.xysh }
					</td>
				</tr>
				<tr>
					<th><bean:message key="lable.xb" />审核意见</th>
					<td colspan="3">
						<html:textarea property="xyshyj" cols="60" rows="5" value="${rs.xyshyj}" readonly="true"></html:textarea>
					</td>
				</tr>
			</logic:notEqual>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4"><logic:equal value="ok" name="buttonCtrl"><div class="bz">"<span class="red">*</span>"为必填项</div></logic:equal>
		          <div class="btn">
		            <logic:equal value="ok" name="buttonCtrl">
						<button type="button" class="button2" id="buttonSave" onclick="saveUpdate('/xgxt/jqlxgl.do?method=jqlxsqDetial&doType=save','save_xh-save_lxdh-save_sqly');">
							保&nbsp;&nbsp;存
						</button>
					</logic:equal>
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
