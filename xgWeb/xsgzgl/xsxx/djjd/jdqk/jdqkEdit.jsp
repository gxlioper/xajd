<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript">
			function saveForm(){
				
				if (jQuery("#xn").val() == "" || jQuery("#xq").val() == "" 
					|| jQuery("#xmdm").val() == "" || jQuery("#jbdm").val() == ""){
					showAlert("请将必填项填写完整。");
					return false;
				}
				
				var url = "jddj_jdqk.do?method=update";
				ajaxSubFormWithFun("jdqkForm",url,function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						refershParent();
					}});
				});
			}
		</script>
	</head>
	<body>
		<html:form action="/jddj_jdqk" method="post" styleId="jdqkForm">
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
								<span>等级鉴定情况</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><span class="red">*</span>学年</th>
							<td>
								<html:select property="xn" styleId="xn">
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
							</td>
							<th><span class="red">*</span>学期</th>
							<td>
								<html:select property="xq" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>项目</th>
							<td>
								<html:select property="xmdm" styleId="xmdm">
									<html:option value=""></html:option>
									<html:options collection="xmdmList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
							<th><span class="red">*</span>级别</th>
							<td>
								<html:select property="jbdm" styleId="jbdm">
									<html:option value=""></html:option>
									<html:options collection="xmjbList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>证书编号</th>
							<td colspan="3">
								<html:text property="zsbh" maxlength="20" styleId="zsbh"></html:text>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
										保 存
									</button>
									<button type="button" type="button" onclick="iFClose();">
										关 闭
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

