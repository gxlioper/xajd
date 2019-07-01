<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function saveForm(url){
				if (checkNull("xn-xq-tzjb-tzxm-xf")){
					ajaxSubFormWithFun("form",url,function(data){
						showAlert(data["message"],{},{"clkFun":function(){
							refershParent();
						}});
					});
				}
			}
		</script>
	</head>
	<body>
		<html:form action="/ahgf_sztz" method="post" styleId="form">
			<html:hidden property="id" />
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>修改学分明细</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>学年
							</th>
							<td>
								<html:select property="xn" styleId="xn">
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
							</td>
							<th>
								<font color="red">*</font>学期
							</th>
							<td>
								<html:select property="xq" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>素质拓展项目
							</th>
							<td>
								<html:text property="tzxm" maxlength="50" styleId="tzxm"></html:text>
							</td>
							<th>
								<font color="red">*</font>级别
							</th>
							<td>
								<html:text property="tzjb" maxlength="50" styleId="tzjb"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								名次
							</th>
							<td>
								<html:text property="mc" maxlength="5" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
							<th>
								<font color="red">*</font>确认学分
							</th>
							<td>
								<html:text property="xf" styleId="xf" maxlength="5" onkeyup="checkInputNum(this);"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								确认人
							</th>
							<td>
								<html:text property="qrr" maxlength="20"></html:text>
							</td>
							<th>
								确认日期
							</th>
							<td>
								<html:text property="qrsj" maxlength="20" readonly="true"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
								></html:text>
							</td>
						</tr>
						<tr>
							<th>
								审核人
							</th>
							<td>
								<html:text property="shr" maxlength="20"></html:text>
							</td>
							<th>
								录入日期
							</th>
							<td>
								${sztzSzxfForm.lrsj }
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="saveForm('ahgf_sztz.do?method=update');">
										保存
									</button>
									<button type="button" onclick="iFClose();">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>

