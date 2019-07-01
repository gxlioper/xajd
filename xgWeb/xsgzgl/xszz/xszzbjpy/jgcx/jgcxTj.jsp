<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/sqsh/js/sqsh.js"></script>
		<script type="text/javascript">
			function queryParentDocumentJgcxTj(){
				if(frameElement.api){
					var api = frameElement.api,W = api.opener;
					return W.document;
				}
				return parent.window.document;
			}
			jQuery(function(){
				var parentD = queryParentDocumentJgcxTj();
				jQuery("#pyjgtempmc_td").html(jQuery("#pyjgtempmc_hid",parentD).val());
				jQuery("#pyjgtempdm_hid").val(jQuery("#pyjgtempdm_hid",parentD).val());

				//默认评议结果
				jQuery("#shzt").val(jQuery("#pyjgtempdm_hid").val());

				//加载下拉选项
				loadViewMkxxSelectOptions();
				//加载radio选项
				loadViewMkxxRadioOptions();

				var xh = jQuery("#xh").val();
				if (jQuery.trim(xh) != ""){
					jQuery("#div_jtqk").load("xszz_jtqkdc.do?method=jtqkInfoForStu",{xh:xh},function(){
					});
				}
			});

			function submitBusi(){
				var pyhsj = jQuery('#pyhsj').val();
				if(jQuery.trim(pyhsj) == ''){
					showAlert("评议会时间不能为空！");
					return false;
				}
				var pyhdd = jQuery('#pyhdd').val();
				if(jQuery.trim(pyhdd) == ''){
					showAlert("评议会地点不能为空！");
					return false;
				}
				var pyyj = jQuery('#pyyj').val();
				if(jQuery.trim(pyyj) == ''){
					showAlert("评议意见不能为空！");
					return false;
				}
				if(pyyj.length > 100){
					showAlert("评议意见最多100字！");
					return false;
				}
				var url = "xszz_xszzbjpy_jgcxgl.do?method=jgcxTj&type=save&values=${mkxxForm.xh }";
			      ajaxSubFormWithFun("jgcxTjForm",url,function(data){
		    		  showAlert(data["message"],{},{"clkFun":function(){
		        			if (parent.window){
		     				 refershParent();
		        			}
		      		  }});
			      });
			}
		</script>
	</head>
	<body>
	
		<html:form action="/xszz_xszzbjpy_jgcxgl" method="post" styleId="jgcxTjForm">
		<input type="hidden" id="xh" value="${mkxxForm.xh }" />
		<input type="hidden" name="sqid" value="${mkxxForm.guid }" />
		<div style='tab;width:100%;height:465px;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/viewStudent.jsp" %>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>家庭情况
									<logic:notEqual value="" property="xh" name="mkxxForm">
										<a onclick="showJtqk(this);" class="up" 
										   href="javascript:void(0);">
										   <font color="blue">点击展开/收起</font>	
										</a>
									</logic:notEqual>
								</span>
							</th>
						</tr>
					</thead>
					<tbody id="t_jtqk" style="display: none;">
						<tr>
							<td colspan="4">
								<div id="div_jtqk">
								
								</div>
							</td>
						</tr>
					</tbody>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>困难生认定信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<div class="con_overlfow">
									<table class="dateline" width="100%">
										<thead>
											<tr>
												<td>学年</td>
												<td>学期</td>
												<td>认定档次</td>
											</tr>
										</thead>
										<tbody>
											<logic:present name="rdlsjlList">
												<logic:notEmpty name="rdlsjlList">
													<logic:iterate id="k" name="rdlsjlList">
														<tr>
															<td>${k.xn }</td>
															<td>${k.xqmc }</td>
															<td>${k.dcmc }</td>
														</tr>
													</logic:iterate>
												</logic:notEmpty>
												<logic:empty name="rdlsjlList">
													<tr>
														<td colspan="3" style="text-align:center;">未找到任何记录！</td>
													</tr>
												</logic:empty>
											</logic:present>
											<logic:notPresent name="rdlsjlList">
												<tr>
													<td colspan="3" style="text-align:center;">未找到任何记录！</td>
												</tr>
											</logic:notPresent>
										</tbody>
									</table>
								</div>
							</td>
						</tr>
					</tbody>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>
									资助项目申请信息
								</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<th>申请周期</th>
							<td>${mkxxForm.xn }&nbsp;${mkxxForm.xqmc }</td>
							<th>项目评定周期</th>
							<td>${mkxxForm.pdxn }&nbsp;${mkxxForm.pdxqmc }</td>
						</tr>
						<tr>
							<th>项目名称</th>
							<td>${mkxxForm.xmmc }</td>
							<th>金额</th>
							<td>${mkxxForm.ylzd1 }</td>
						</tr>
						<tr>
							<th>
								附件信息
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden name="mkxxForm" property="ylzd5" styleId="fjid"/>
								<div id="fjidDiv"></div>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										var gid = jQuery('#fjid').val();
										jQuery.MultiUploader_q({
											gid : gid
											});
									});
								</script>
							</td>
						</tr>
					</tbody>
					
					<%@ include file="/xsgzgl/xszz/bdpz/mkxxView.jsp" %>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>班级评议结果</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								评议小组成员
								<input type="hidden" name="bjpyxzcyxms" value="${bjpyxzdbMap.bjpyxzcyxms }"/>
							</th>
							<td colspan="3">
								${bjpyxzdbMap.bjpyxzcyxms }
							</td>
						</tr>
						<tr>
							<th>
								评议小组代表
								<input type="hidden" name="bjpyxzdbxms" value="${bjpyxzdbMap.bjpyxzdbxms }"/>
							</th>
							<td colspan="3">
								${bjpyxzdbMap.bjpyxzdbxms }
							</td>
						</tr>
						<tr>
							<th>
								评议结果
								<input type="hidden" id="pyjgtempdm_hid" name="pyjg" value=""/>
							</th>
							<td colspan="3" id="pyjgtempmc_td">
								
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>最终评议结果
							</th>
							<td colspan="3">
								<html:select property="shzt" styleId="shzt" style="width:155px">
									<html:options collection="pyjgList" labelProperty="mc" property="dm"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>评议会时间
							</th>
							<td colspan="3">
								<html:text styleId="pyhsj" property="pyhsj" name="jgcxForm" onclick="return showCalendar('pyhsj','yyyy-MM-dd HH:mm');"  readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>评议会地点
							</th>
							<td colspan="3">
								<html:text property="pyhdd" styleId="pyhdd" name="jgcxForm" maxlength="100" style="width:95%;"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>评议意见
								<br />
								<font color="red">(限100字)</font>
							</th>
							<td colspan="3" style="word-break: break-all;">
								<html:textarea property="pyyj" styleId="pyyj" name="jgcxForm" style="width:95%;" rows="5"></html:textarea>
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
								<div class="btn">
									<button type="button" id="save_button" type="button"
										onclick="submitBusi();">
										提交
									</button>
									<button type="button" name="关 闭" onclick="iFClose();">
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

