<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/dyxj/dyzp/js/dyzp.js"></script>
		<script type="text/javascript">
			function saveForm(url){
				
				if (jQuery("#xn").val() == "" || jQuery("#xq").val() == "" || jQuery("#djdm").val() == "" ){
					showAlert("请将必填项填写完整。");
					return false;
				}
				
				var _fun = function(){
					ajaxSubFormWithFun("jdqkForm",url,function(data){
						showAlert(data["message"],{},{clkFun:function(){
							if(data["message"] != '提交成功！'){
								  return false;
							}
							refershParent();
						}});
					});
				};
				if(!checklenbyzpnr()){
					return false;
				}
				if (jQuery("#yxn").val() != jQuery("#xn").val() && jQuery("#yxq").val() != jQuery("#xq").val()){
					jQuery.post("xsxxDyxjDyzp.do?method=getCount",{xh:jQuery("#xh").val(),xn:jQuery("#xn").val(),xq:jQuery("#xq").val()},function(data){
						if (Number(data) == 0){
							_fun();
						} else {
							showAlert("您在当前学年、学期已申请过德育自评。");
						}
					},"json");
				} else {
					_fun();
				}
			}
		</script>
	</head>
	<body>
		<html:form action="/xsxxDyxjDyzp" method="post" styleId="jdqkForm">
			<html:hidden property="id" />
			<input type="hidden" id="yxn" valueu="${dyxjDyzpModel.xn }"/>
			<input type="hidden" id="yxq" valueu="${dyxjDyzpModel.xq }"/>
			
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
								<span>自评信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><span class="red">*</span>学年</th>
							<td>
								<html:hidden property="xn" styleId="xn"/>
								<label>${xn}</label>
							</td>
							<th><span class="red">*</span>学期</th>
							<td>
								<html:hidden property="xq" styleId="xq"/>
								<label>${xq}</label>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>自评等级</th>
							<td colspan="3">
								<html:select property="djdm" styleId="djdm">
									<html:option value=""></html:option>
									<html:options collection="zpdjList" property="djdm" labelProperty="djmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>自评内容
							<br/><font color="red">(限400-1000字)</font>
							</th>
							<td colspan="3">
								<html:textarea property="zpnr" styleId="zpnr" 
											   onblur="checklenbyzpnr();"
											   style="width:99%;" rows="8"></html:textarea>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
								<!--  
									<button type="button" onclick="saveForm('xsxxDyxjDyzp.do?method=update');">
										保存草稿
									</button>
									-->
									<button type="button" onclick="saveForm('xsxxDyxjDyzp.do?method=saveAndSubmit');">
										提交申请
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

