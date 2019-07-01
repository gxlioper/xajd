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
			function saveForm(){
				if (jQuery.trim(jQuery("#ecmm").val()) == "" 
						|| jQuery.trim(jQuery("#cfmm").val()) == "" 
						|| jQuery.trim(jQuery("#zgh").val()) == ""){
					showAlert("请将必填项填写完整！");
					return false;
				}
				
				if (jQuery.trim(jQuery("#ecmm").val()) != jQuery.trim(jQuery("#cfmm").val())){
					showAlert("两次密码输入不一致！");
					return false;
				}
				
				var isUpdate = jQuery("#isUpdate").val();
				
				var url = Boolean(isUpdate) ? "zdxljkEcmm.do?method=edit" : "zdxljkEcmm.do?method=save";
				ajaxSubFormWithFun("form",url,function(data){
					showAlert(data["message"],{},{"clkFun":function(){
						refershParent();
					}});
				});
			}
			
		</script>
	</head>
	<body>
		<html:form action="/zdxljkEcmm" method="post" styleId="form">
				<input type="hidden" value="${update }" id="isUpdate"/>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>教职工信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:18%"><font color="red">*</font>职工号</th>
							<td style="width:32%"><html:text property="zgh" readonly="true" styleId="zgh" style="width:60%" />
							<button class="btn_01" type="button"  
										onclick="showDialog('请选择一个教职工',680,480,'szdw_fdyjtff.do?method=showFdys&goto=${path}');return false;">选择</button>
							</td>
							<th style="width:18%">姓名</th>
							<td style="width:32%">
								${jbxx.xm }
							</td>
						</tr>
						
						<tr>
							<th>性别</th>
							<td>
								${jbxx.xbmc }
							</td>
							<th>联系电话</th>
							<td>
								${jbxx.lxdh }
							</td>
						</tr>
						<tr>
							<th>部门</th>
							<td>
								${jbxx.bmmc }
							</td>
							<th></th>
							<td>
								
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>
									二次密码维护
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>二次密码</th>
							<td>
								<input type="password" name="ecmm" id="ecmm" maxlength="20" autocomplete="off" value=""/>
							</td>
							<th><font color="red">*</font>确认二次密码</th>
							<td>
								<input type="password" name="cfmm" id="cfmm" maxlength="20"/>
							</td>
						</tr>
						<tr>
							<th>
								备注
								<font color="red">(限200字)</font>
							</th>
							<td colspan="3">
								<html:textarea property="bz" styleId="bz" 
											   onblur="checkLen(this,200);"
											   style="width:99%;" rows="5"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
				<table width="100%" border="0" class="formlist">
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

