<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/sqsh/js/bdpz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
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
				var url = "xpjpy_pjpybjpy_jgcxgl.do?method=jgcxTj&type=save&values=${mkxxForm.xh }";
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
	
		<html:form action="/xpjpy_pjpybjpy_jgcxgl" method="post" styleId="jgcxTjForm">
		<input type="hidden" id="xh" value="${mkxxForm.xh }" />
		<input type="hidden" name="sqid" value="${mkxxForm.sqid }" />
		<div style='tab;width:100%;height:465px;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
						<%@ include file="/xsgzgl/xpjpy/wdpj/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>申请奖项</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								评奖周期
							</th colspan="3">
							<td>
								<bean:write property="xn" name="mkxxForm"/>&nbsp;<bean:write property="xqmc" name="mkxxForm"/>
							</td>
						</tr>
						<tr>
							<th>
								项目名称
							</th>
							<td>
								${xmwhModel.xmmc }
							</td>
							<th>
								项目金额
							</th>
							<td>
								${xmwhModel.xmje }
							</td>
						</tr>
						<tr>
							<th>
								附件信息
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden property="ylzd5" name="mkxxForm" styleId="fjid"/>
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
						<tr>
							<th>
								申请理由
							</th>
							<td colspan="3" style="word-break:break-all;">
								<bean:write property="sqly" name="mkxxForm" filter="false"/>
							</td>
						</tr>
						<tr>
							<th>
								限制条件
							</th>
							<td colspan="3" id="checkTd">
								<logic:notEmpty name="checkResult">
									<logic:iterate id="check" name="checkResult" indexId="i">
										<logic:equal value="true" name="check" property="result">
											<img src='images/ico_38.gif' title='符合条件'/> ${i+1 }、${check.sqts }<br/>
										</logic:equal>
										<logic:equal value="false" name="check" property="result">
											<img src='images/ico_39.gif' name='faidImg' title='不符合条件'/> ${i+1 }、${check.sqts }<br/>
										</logic:equal>
									</logic:iterate>
								</logic:notEmpty>
								<logic:empty name="checkResult">
									<font color='green'>无限制条件</font>
								</logic:empty>								
							</td>
						</tr>
					</tbody>
					
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

