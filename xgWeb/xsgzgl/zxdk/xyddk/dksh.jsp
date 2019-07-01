<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xyddk/js/dksq.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//加载下拉选项
				loadViewMkxxSelectOptions();
				//加载radio选项
				loadViewMkxxRadioOptions();

				var xh = jQuery("#xh").val();
				
				if (jQuery.trim(xh) != ""){
				
					jQuery("#div_jtqk").load("xszz_jtqkdc.do?method=jtqkInfo",{xh:xh},function(){
					})
				}
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${zxdkXyddkForm.id}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${zxdkXyddkForm.splcid}&shid=${zxdkXyddkForm.id}");
			});
			function saveAudit(){
				var shzt=jQuery("#shjg").val();
				jQuery("#shzt").val(shzt);
				if (jQuery("#shyj").val() == ""){
					showAlert("请填写审核意见！");
					return false;
				}
				if (jQuery("#shyj").val().length>200){
					showAlert("审核意见不能超过200字");
					return false;
				}
				if("${xxdm}" == "10511"){
					if(jQuery.trim(jQuery("#zd3").val()) == ""){
						showAlert("合同编号不能为空！");
					     return false;
					}
				}
				var text=jQuery("#shjg").find("option:selected").text();
				//提交审核
				zx(shzt,text);
			}
			
			function zx(shzt,text){
			
				if("${xxdm}" == "10511"){
					var message = "";
				    var data = {shzt:jQuery("#shjg").val(),htbh:jQuery("#zd3").val()}
					var url = "zxdkXyddk.do?method=checkHtbhIsExists";
					jQuery.ajax({
					type:'post',
					url:url,
					dataType:'json',
					contentType:"application/x-www-form-urlencoded; charset=UTF-8",
					data:data,
					async: false,
					success:function(result){
						if(result==null||result=="null"){
							message = "未知错误！";
						}else{
							message = result["message"];
						}
					 }
					
				     });
				     if(message != "true"){
				     	showAlert(message);
				     	return false;
				     }
				}
				
				var url = "zxdkXyddk.do?method=submitAudit&tt="+new Date();
				ajaxSubFormWithFun("xyddkForm",url,function(data){
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
		<html:form action="/zxdkXyddk" method="post" styleId="xyddkForm">
			<html:hidden property="id"/>
			<html:hidden property="shzt"/>
			<html:hidden property="xh" styleId="xh"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:460px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>贷款申请</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>家庭情况
									<logic:notEqual value="" property="xh" name="zxdkXyddkForm">
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
								<span>贷款信息<font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;单位（元）</font></span>
							</th>
						</tr>
					</thead>
					<logic:notEqual name="xxdm" value="10511">
						<tbody>			
						<tr>
							<th>贷款学年</th>
							<td>
								${zxdkXyddkForm.xn }
							</td>
							<th>贷款总金额（元）</th>
							<td>
								${zxdkXyddkForm.dkje }
							</td>
						</tr>
						<tr>
							<th>住宿费应收数（元）</th>
							<td>
								${zxdkXyddkForm.zsf }
							</td>
							<th>学费应收数（元）</th>
							<td>
								${zxdkXyddkForm.xzf }
							</td>
						</tr>
						<tr>
							<th>
								<logic:equal value="10704" name="xxdm">
									住宿费月贷款数（元）
								</logic:equal>
								<logic:notEqual value="10704" name="xxdm">
									住宿费年贷款数（元）
								</logic:notEqual>
							</th>
							<td>
								${zxdkXyddkForm.zsfdks }
							</td>
							<th>
								<logic:equal value="10704" name="xxdm">
									学费月贷款数（元）
								</logic:equal>
								<logic:notEqual value="10704" name="xxdm">
									学费年贷款数（元）
								</logic:notEqual>							
							</th>
							<td>
								${zxdkXyddkForm.xfdks }
							</td>
						</tr>
						<tr>
							<th>
								<logic:equal value="10704" name="xxdm">
									生活费月贷款数（元）
								</logic:equal>
								<logic:notEqual value="10704" name="xxdm">
									生活费年贷款数（元）
								</logic:notEqual>
							</th>
							<td>
								${zxdkXyddkForm.shfdks }
							</td>
							<th>
								<logic:equal value="10704" name="xxdm">
									贷款期限（月）
								</logic:equal>
								<logic:notEqual value="10704" name="xxdm">
									贷款期限（年）
								</logic:notEqual>								
							</th>
							<td>
								${zxdkXyddkForm.dkqx }
							</td>
						</tr>
						<tr>
							<th>每年贷款总额（元）</th>
							<td>
								${zxdkXyddkForm.mnje }
							</td>
						</tr>
						<tr>
							<th>申请理由</th>
							<td colspan="3">
								${zxdkXyddkForm.sqly }
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								附件信息
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										var gid = "${zxdkXyddkForm.filepath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
										});
									});
								</script>
							</td>
						</tr>
					</tbody>				
					<logic:equal value="12688" name="xxdm">
						<thead>
							<tr>
								<th colspan="4">
									<span>共同借款人信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>姓名</th>
								<td>
									${zxdkXyddkForm.gtjkrxm }
								</td>
								<th>家庭电话</th>
								<td>
									${zxdkXyddkForm.gtjkrjtdh }
								</td>
							</tr>
							<tr>
								<th>与学生关系</th>
								<td>
									${zxdkXyddkForm.gtjkrgx }
								</td>
								<th>身份证号码</th>
								<td>
									${zxdkXyddkForm.gtjkrsfzh }
								</td>
							</tr>
							<tr>
								<th>手机号码</th>
								<td>
									${zxdkXyddkForm.gtjkrsjhm }
								</td>
								<th>健康状况</th>
								<td>
									${zxdkXyddkForm.gtjkrjkzk }
								</td>
							</tr>
							<tr>
								<th>邮政编码</th>
								<td>
									${zxdkXyddkForm.gtjkryb }
								</td>
								<th></th>
								<td></td>
							</tr>
							<tr>
								<th>家庭详细地址</th>
								<td colspan="3">
									${zxdkXyddkForm.gtjkrjtdz }
								</td>
							</tr>
						</tbody>
					</logic:equal>
					</logic:notEqual>
					<logic:equal name="xxdm" value="10511">
						<tbody>
						<tr>
							<th width="16%">贷款期数</th>
							<td width="34%" id='dkqxtd'>
							  ${zxdkXyddkForm.dkqx}
							</td>
							<th width="16%">贷款期限（月）</th>
							<td width="34%">
								 ${zxdkXyddkForm.jhr1}
							</td>
						</tr>
						<tr  id = "tableshow" >
							<td colspan="4"  width="100%">
								<table id="table" width="100%">
									<tr width="100%">
										<th style="text-align:center" width="15%" >学年</th>
										<th style="text-align:center" >住宿费贷款额</th>
										<th style="text-align:center" >学费贷款额</th>
										<th style="text-align:center" >生活费贷款额</th>
										<th style="text-align:center" >年住宿费应缴额</th>
										<th style="text-align:center" >年学费应缴额</th>
									</tr>
									<logic:iterate id="i" name="nddkList">
									<tr class ='showtr'>
										<td>
											${i.xn}
										</td>
										 <td>
										 	${i.nzsfdk}
										 </td>
										 <td>
										 	${i.nxfdk}
										 </td>
										 <td>
										 	${i.nshfdk}
										 </td>
										 <td>
										 	${i.nzsfyje}
										 </td>
										 <td>
										 	${i.nxfyje}
										  </td>
										</tr>
									</logic:iterate>
									
								</table>
							</td>
						</tr>
						<tr>
							<th width="16%">住宿费贷款总额</th>
							<td width="34%">
								 ${zxdkXyddkForm.zsfdks}
							</td>
							<th width="16%">学费贷款总额</th>
							<td width="34%">
								 ${zxdkXyddkForm.xfdks}
							</td>
						</tr>
						<tr>
							<th width="16%">生活费贷款总额</th>
							<td width="34%">
								${zxdkXyddkForm.shfdks}
							</td>
							<th width="16%">贷款总金额</th>
							<td width="34%">
								${zxdkXyddkForm.dkje}
							</td>
						</tr>
						<tr>
							<th width="16%">申请理由</th>
							<td width="84%" colspan="3">
								${zxdkXyddkForm.sqly}
							</td>
						</tr>
						<tr>
							<th align="right" width="16%">
								附件信息
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										var gid = "${zxdkXyddkForm.filepath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
										});
									});
								</script>
							</td>
						</tr>
					</tbody>
					</logic:equal>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>审核信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4" id="shlccx">
							
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>审核结果
							</th>
							<td id="shjgSpan">
								 
							</td>
							<th><logic:equal name="xxdm" value="10511"><font color="red">*</font></logic:equal>贷款合同编号</th>
							<td>
								<input type="hidden" name="zd1" value="贷款合同编号"/>
								<input type="text" name="zd3" id="zd3" maxlength="20" value = "${sjxx.zd3}"/>
							</td>
						</tr>
						<!-- 西安科技大学个性化 -->
						<logic:equal value="10704" name="xxdm">
							<tr>
								<th>
									贷款金额
								</th>
								<td>
									<input type="hidden" name="dkje" value="${zxdkXyddkForm.dkje}"/>
									<logic:present name="sjxx" property="zd7">									
										<logic:empty name="sjxx" property="zd7">
											<input type="text" name="zd7" id="zd7" maxlength="10" value="${zxdkXyddkForm.dkje}" onblur="checkMoneyForBlur(this);return false;" />
										</logic:empty>
										<logic:notEmpty name="sjxx" property="zd7">								
											<input type="text" name="zd7" id="zd7" maxlength="10" value="${sjxx.zd7}" onblur="checkMoneyForBlur(this);return false;" /> 
										</logic:notEmpty>
									</logic:present>
									<logic:notPresent name="sjxx" property="zd7">
										<input type="text" name="zd7" id="zd7" maxlength="10" value="${zxdkXyddkForm.dkje}" onblur="checkMoneyForBlur(this);return false;" />
									</logic:notPresent>
								</td>
								<th>
									办理贷款日期
								</th>
								<td>								
									<input type="text" name="zd8" id="zd8" maxlength="10" value="${sjxx.zd8}" onfocus="showCalendar('zd8','y-mm-dd');" />  
								</td>
							</tr>
							<tr>
								<th>
									贷款合同期限（月）
								</th>
								<td>
									<input type="hidden" name="dkqx" value="${zxdkXyddkForm.dkqx}" />
									<logic:present name="sjxx" property="zd9">
										<logic:empty name="sjxx" property="zd9">
											<input type="text" name="zd9" id="zd9" maxlength="2" value="${zxdkXyddkForm.dkqx}" onblur="checkZs(this);return false;"/>
										</logic:empty>
										<logic:notEmpty name="sjxx" property="zd9">								
											<input type="text" name="zd9" id="zd9" maxlength="2" value="${sjxx.zd9}" onblur="checkZs(this);return false;"/> 
										</logic:notEmpty>
									</logic:present>
									<logic:notPresent name="sjxx" property="zd9">
											<input type="text" name="zd9" id="zd9" maxlength="2" value="${zxdkXyddkForm.dkqx}" onblur="checkZs(this);return false;"/>
									</logic:notPresent>
								</td>
								<th>
									合同签订地点
								</th>
								<td>
									<input type="text" name="zd10" id="zd10" maxlength="20" value="${sjxx.zd10}" />   
								</td>
							</tr>
						</logic:equal>
						
						<tr>
							<th>贷款银行</th>
							<td>
								<input type="hidden" name="zd1" value="贷款银行"/>
								<input type="text" name="zd5" maxlength="25" value = "${sjxx.zd5}"/>
							</td>
							<th>银行电话</th>
							<td>
								<input type="hidden" name="zd1" value="银行电话"/>
								<input type="text" name="zd6" maxlength="15" value = "${sjxx.zd6}"/>
							</td>
						</tr>
						
						<!-- 西安科技大学个性化 -->
						<logic:equal value="10704" name="xxdm">							
							<tr>
								<th>
									学院经办人
								</th>
								<td>
									<input type="text" name="zd2" maxlength="10" value = "${sjxx.zd2}"/>
								</td>
							</tr>
						</logic:equal>
						
						<tr>
							<th width="20%">
								<font color="red">*&nbsp;</font> 审核意见&nbsp;
								<br />
								<font color="red">(限200字)</font>
							</th>
							<td colspan="3">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=gjzxdk&id=shyj" />
								<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);">${rs.shyj}</textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<div style="height: 15px"></div>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" id="btqd" onclick="saveAudit();">
										保 存
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