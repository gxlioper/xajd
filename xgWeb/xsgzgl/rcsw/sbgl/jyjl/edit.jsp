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
				if (jQuery("#zgh").val() == "" || jQuery("#fldm").val() == "" || jQuery("#sbdm").val() == ""){
					showAlert("请将必填项填写完整。");
					return false;
				}
				
				var url = "rcswSbglJyjl.do?method=update";
				ajaxSubFormWithFun("form",url,function(data){
					showAlert(data["message"]);
					refershParent();
				});
			}
			
			function getSbxx(obj){
				
				var sbdmSelect = jQuery("#sbdm");
				var fldm = jQuery("option:selected",jQuery(obj)).val();
				
				sbdmSelect.find("option").remove();
				
				if (fldm == ""){
					return false;
				}
				
				jQuery.getJSON("rcswSbglSbxx.do?method=getSbxxByFldm",{fldm:fldm},function(data){
					
					sbdmSelect.append("<option value=''></option>");
					
					jQuery.each(data,function(i,n){
						var option = jQuery("<option value='"+n["dm"]+"'>"+n["mc"]+"</option>");
						sbdmSelect.append(option);
					});
				});
			}
			
		</script>
	</head>
	<body>
		<html:form action="/rcswSbglJyjl" method="post" styleId="form">
			<html:hidden property="id" />
		
			<div style='overflow-x:hidden;overflow-y:auto;height:360px;margin-bottom: 0px;'>
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
							<th style="width:15%">职工号</th>
							<td style="width:35%">
								${jbxx.zgh }
							</td>
							<th style="width:15%">姓名</th>
							<td style="width:35%">
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
								<span>借用设备信息
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>设备分类</th>
							<td>
								<html:select property="fldm" styleId="fldm" onchange="getSbxx(this);">
									<html:option value=""></html:option>
									<html:options collection="sbflList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
							<th>
								<font color="red">*</font>设备名称
							</th>
							<td>
								<html:select property="sbdm" styleId="sbdm">
									<html:option value=""></html:option>
									<html:options collection="sbxxList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>借用时间</th>
							<td colspan="3">
								<html:text property="jysj" readonly="true" styleId="jysj"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})"></html:text>
							</td>
<%--							<th>--%>
<%--								<font color="red">*</font>归还时间--%>
<%--							</th>--%>
<%--							<td>--%>
<%--								<html:text property="ghsj" readonly="true" styleId="ghsj"--%>
<%--										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})"></html:text>--%>
<%--							</td>--%>
						</tr>
						<tr>
							<th>备注
							</th>
							<td colspan="3">
								<html:text property="bz" maxlength="50" style="width:85%;"></html:text>
							</td>
						</tr>
						<tr>
							<th>借用原因<br/>
								<font color="red">（限400字）</font>
							</th>
							<td colspan="3">
								<html:textarea property="jyyy" styleId="jyyy" 
											   onblur="checkLen(this,400);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
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

