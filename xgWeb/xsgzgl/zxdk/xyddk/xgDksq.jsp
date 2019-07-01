<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
			<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xyddk/js/dksq.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xyddk/js/hsdComm.js"></script>
		
		<script type="text/javascript" defer="defer">

		jQuery(function(){

			onShow("zxdk_update");
			
			//加载下拉选项
			loadMkxxSelectOptions();
			//加载radio选项
			loadMkxxRadioOptions();
			jQuery("#mnje").attr("readonly","readonly");
			jQuery("#dkje").attr("readonly","readonly");
			jQuery("#xn").val(jQuery("#dqxn").val());
			if("12861" == jQuery("#xxdm").val()){
				var afterxn = jQuery("#afterxn").val();
				var zdybdcon_td_xn = jQuery("td[name='zdybdcon_td_xn']").eq(0);
				var zdybdcon_td_xn_html = "<input type='hidden' name='xn' id='xn' value='"+afterxn+"' />";
				zdybdcon_td_xn_html += afterxn;
				zdybdcon_td_xn.html(zdybdcon_td_xn_html);
			}
			var xh = jQuery("#xh").val();
			if (jQuery.trim(xh) != ""){
				jQuery("#div_jtqk").load("xszz_jtqkdc.do?method=jtqkInfo",{xh:xh});
			}
			jQuery("#zsfdks").keyup(function(){
				getzje(this);
				});
			jQuery("#xfdks").keyup(function(){
				getzje(this);
				});
			jQuery("#shfdks").keyup(function(){
				getzje(this);
				});
			jQuery("#dkqx").keyup(function(){
				getzje(this);
				});
		});

		function lock(){
			jQuery(".btn").find("button").each(function(){
				jQuery(this).attr("disabled","disabled");
				jQuery(this).css({color:"#cccccc"});
			});
		}
			function saveDksq(url){
				
				var xn = jQuery("#xn").val();
				var dkje = jQuery("#dkje").val();
				var xzf = jQuery("#xzf").val();
				var zsf = jQuery("#zsf").val();
				var dkqx = jQuery("#dkqx").val();
				var sqly = jQuery("#sqly").val();
				var mnje = jQuery("#mnje").val();
				var zsfdks = jQuery("#zsfdks").val();
				var xfdks =  jQuery("#xfdks").val();
				var shfdks = jQuery("#shfdks").val();
				var xz = jQuery("#xz").val();
				var nj = jQuery("#nj").val();

				// 检查学号是否存在
				if (xh==""){
					showAlertDivLayer("[学号]不许为空!");
					return false;
				}
				
				if(!zdybdCheck()){ //必填字段验证
					return;
				}
				
				if("10335" != jQuery("#xxdm").val()){
					if(parseInt(zsfdks)>parseInt(zsf)){
						showAlertDivLayer("住宿费年贷款数不能大于住宿费应收数！");
						return false;
					}
					if(parseInt(xfdks)>parseInt(xzf)){
						showAlertDivLayer("学费年贷款数不能大于学费应收数！");
						return false;
					}
				}
				if(parseInt(mnje)>parseInt(jQuery("#dkzesx").val())){
					showAlertDivLayer("每年贷款总额超过"+jQuery("#dkzesx").val()+"元!");
					return false;
				}
				
				//是否弹出家庭情况调查填写页面
				var openJtqk = jQuery("#openJtqk").val();

				if ("true" == openJtqk){
					var xh = jQuery("#xh").val();

					showAlertDivLayer("请先填写家庭情况调查表！",{},{"clkFun":function(){
						editJtqk();
					}});
					return false;
				}
				
				jQuery.post("zxdkXyddk.do?method=checkDksq",{xh:xh,xn:xn,xz:xz,dkqx:dkqx,nj:nj,id:jQuery("#id").val()},function(data){
					if (data["success"] == "true"){
						ajaxSubFormWithFun("xyddkForm",url,function(data){
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
								refershParent();
							}});
						});
					} else {
						showAlertDivLayer(data["message"]);
					}
				},"json");
			}
		</script>
	</head>
	<body>
		<html:form action="/zxdkXyddk" method="post" styleId="xyddkForm">
			<html:hidden property="id" styleId="id"/>
			<html:hidden property="xh" styleId="xh"/>
			<input type="hidden" id="xxdm" value="${xxdm }" />
			<input type="hidden" id="dqxn" value="${dqxn }" />
			<input type="hidden" id="afterxn" value="${afterxn }" />
			<input type="hidden" id="xz" value="${jbxx.xz }" />
			<input type="hidden" id="nj" value="${jbxx.nj }" />
			<html:hidden property="dkzesx" styleId="dkzesx" value="${cssz.dkzesx }"/>
			<input type="hidden" id="jesx" value="${jesx}" />
			
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
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
								<span>家庭情况 <logic:notEqual value="" property="xh"
										name="zxdkXyddkForm">
										<a onclick="showJtqk(this);" class="up"
											href="javascript:void(0);"> <font color="blue">点击展开/收起</font>
										</a>
										|
										<a onclick="editJtqk();" class="btn_xg"
											href="javascript:void(0);"> <font color="blue">编辑家庭情况</font>
										</a>
									</logic:notEqual> </span>
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
				</table>
				<logic:notEqual name="xxdm" value="10511">
					<div class="tab"  id="content" style="margin-top: 5px; overflow-x:hidden;"></div>
				</logic:notEqual>
				<logic:equal name="xxdm" value="10511">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>贷款信息<font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;单位（元）</font>&nbsp;&nbsp;&nbsp;&nbsp;<font color="blue">每年贷款总金额不能超过金额上限</font><font color="red" id="tip">${jesx}</font><font color="red" >元</font></span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">贷款期数</th>
							<td id='dkqxtd' width="34%">
							  ${mkxxForm.dkqx}
								<html:hidden property="dkqx" styleId="dkqx"/>
							</td>
							<th width="16%">贷款期限（月）</th>
							<td width="34%">
								 ${mkxxForm.jhr1}
								<html:hidden property="jhr1" styleId="jhr1"/>
							</td>
						</tr>
						<tr  id = "tableshow" >
							<td colspan="4"  width="100%">
								<table id="table">
									<tr>
										<th style="text-align:center" width="15%" >学年</th>
										<th style="text-align:center" ><font class="red">*</font>住宿费贷款额</th>
										<th style="text-align:center" ><font class="red">*</font>学费贷款额</th>
										<th style="text-align:center" ><font class="red">*</font>生活费贷款额</th>
										<th style="text-align:center" ><font class="red">*</font>年住宿费应缴额</th>
										<th style="text-align:center" ><font class="red">*</font>年学费应缴额</th>
									</tr>
									<logic:iterate id="i" name="nddkList">
									<tr class ='showtr'>
										<td>
											${i.xn}
											<input type="hidden" name="xn" value="${i.xn}"/>		
										</td>
										 <td>
										 <input name='nzsfdk' class='dk' value='${i.nzsfdk}'  style='width:98%' maxlength='5' onkeyup='checkNullInput(this);checkInput(this);calEverykx(this)'  />
										 </td>
										 <td>
										 <input name='nxfdk'  class='dk' value='${i.nxfdk}'   style='width:98%' maxlength='5' onkeyup='checkNullInput(this);checkInput(this);calEverykx(this)'  />
										 </td>
										 <td>
										 <input name='nshfdk' class='dk' value='${i.nshfdk}'  style='width:98%' maxlength='5' onkeyup='checkNullInput(this);checkInput(this);calEverykx(this)' />
										 </td>
										 <td>
										 <input name='nzsfyje'  value='${i.nzsfyje}'  style='width:98%' maxlength='5' onkeyup='checkInput(this);' />
										 </td>
										 <td>
										 <input name='nxfyje'   value='${i.nxfyje}'  style='width:98%' maxlength='5' onkeyup='checkInput(this);' />
										  </td>
										</tr>
									</logic:iterate>
									
								</table>
							</td>
						</tr>
						<tr>
							<th width="16%"><font class='red'>*</font>住宿费贷款总额</th>
							<td width="34%">
							<html:text property="zsfdks" styleId="zsfdks" readonly="true" styleClass="text_nor"/>
							</td>
							<th width="16%"><font class='red'>*</font>学费贷款总额</th>
							<td width="34%">
							<html:text property="xfdks" styleId="xfdks" readonly="true" styleClass="text_nor"/>
							</td>
						</tr>
						<tr>
							<th width="16%"><font class='red'>*</font>生活费贷款总额</th>
							<td width="34%">
							<html:text property="shfdks" styleId="shfdks" readonly="true" styleClass="text_nor"/>
							</td>
							<th width="16%"><font class='red'>*</font>贷款总金额</th>
							<td width="34%">
							<html:text property="dkje" styleId="dkje" readonly="true" styleClass="text_nor"/>
							</td>
						</tr>
						<tr>
							<th width="16%"><font class='red'>*</font>申请理由</th>
							<td colspan="3" width="84%">
								<html:textarea rows="4" property="sqly" styleId="sqly" style="width:98%"/>
							</td>
						</tr>
					</tbody>
				</table>
				</logic:equal>
				</div>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>附件信息</span>
							</th>
						</tr>
					</thead>
					<tr>
						<th align="right" width="16%">
							附件信息
						</th>
							<logic:equal value="10511" name="xxdm">
							<td colspan="3">
								<html:hidden property="filepath" styleId="filepath" />
								<input type="file" id="filepath_f" name="filepath" />
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										jQuery('#filepath_f').multiUploader({
											maxcount : 3,
											//后缀
											accept : 'pdf',
											//最大文件大小 单位M
											maxsize: 10,
											//存放附件的隐藏域的id
											elementid : 'filepath',
			
											eid : 'filepath_f'
										});
									});
								</script>
						   </td>	
						</logic:equal>
						<logic:notEqual value="10511" name="xxdm">
						  <td colspan="3">
								<html:hidden property="filepath" styleId="filepath" />
								<input type="file" id="filepath_f" name="filepath" />
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										jQuery('#filepath_f').multiUploader({
											maxcount : 3,
											//后缀
											accept : 'png|gif|jpg|zip|rar|doc|docx',
											//最大文件大小 单位M
											maxsize: 10,
											//存放附件的隐藏域的id
											elementid : 'filepath',
			
											eid : 'filepath_f'
										});
									});
								</script>
						   </td>
						</logic:notEqual>
					</tr>
				</table>
				<div style="height:25px;"></div>
				<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
								<logic:notEqual name="xxdm" value="10511">
									<button type="button" onclick="saveDksq('zxdkXyddk.do?method=update');">
										保存草稿
									</button>
									<button type="button" onclick="saveDksq('zxdkXyddk.do?method=saveAndSubmit');">
										提交申请
									</button>
								</logic:notEqual>
									
									<logic:equal name="xxdm" value="10511">
										<button type="button" id="bccg" onclick="saveSqForHsdupdate('update')">
										        保存草稿
									    </button>
									    <button type="button" id="tjsq" onclick="saveSqForHsdupdate('submit')">
										      提交申请
									    </button>									
									</logic:equal>
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