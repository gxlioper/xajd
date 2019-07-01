<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xyddk/js/dkjg.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xyddk/js/hsdComm.js"></script>
		
		<script type="text/javascript">
		jQuery(function(){
			
			onShow("zxdk_update");
			jQuery("#mnje").attr("readonly","readonly");
			jQuery("#dkje").attr("readonly","readonly");
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
			jQuery("#xzf").keyup(function(){
				getzje(this);
				});
		});
			function saveDksq(url){

				var xxdm = jQuery("#xxdm").val();
				var xh = jQuery("#xh").val();
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
				
				// 检查学号是否存在
				if (xh==""){
					showAlertDivLayer("[学号]不许为空!");
					return false;
				}
				
				if(!zdybdCheck()){ //必填字段验证
					return;
				}
				if("10335"!=xxdm){
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
				
				jQuery.post("zxdkDkjg.do?method=getCountByXhAndXn",{xh:xh,xn:xn,id:jQuery("#id").val()},function(data){
					if (Number(data) == 0){
						ajaxSubFormWithFun("xyddkForm",url,function(data){
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
								refershParent();
							}});
						});
					} else {
						showAlertDivLayer("该学年已经申请过国家助学贷款，请确认！");
					}
				},"json");
			}
			
		</script>
	</head>
	<body>
		<html:form action="/zxdkXyddk" method="post" styleId="xyddkForm">
			<html:hidden property="id" styleId="id"/>
			<html:hidden property="xh" styleId="xh"/>
			<html:hidden property="dkzesx" styleId="dkzesx" value="${cssz.dkzesx }"/>
			<input type="hidden" id='xxdm' name='xxdm' value='${xxdm }'/> 
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
					</table>
			</div>
			<logic:notEqual name="xxdm" value="10511">
				<div class="tab"  id="content" style="margin-top: 5px; margin-bottom: -1px; overflow-x:hidden;"></div>
			</div>
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
			<logic:notEqual value="10335" name="xxdm">
				<table width="100%" border="0" class="formlist" style="margin-bottom: 5px;">
					<logic:equal value="12688" name="xxdm">
						<thead>
							<tr>
								<th colspan="4">
									<span>贷款其他信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
					</logic:equal>
					<tr>
						<th width="15%">贷款合同编号</th>
						<logic:equal value="10511" name="xxdm">
							<td width="35%" colspan="3">
								${mkxxForm.htbh}
							<html:hidden property="htbh" styleId="htbh"   />
						   </td>
						</logic:equal>
						<logic:notEqual value="10511" name="xxdm">
							<!-- 西安科技大学 -->
						<logic:equal value="10704" name="xxdm">
							<td width="35%">
								<html:text property="htbh" styleId="htbh" maxlength="20"  styleClass="text_nor"></html:text>
						    </td>
						    <th width="15%">
						    	办理贷款日期
						    </th>
						    <td width="35%">
						    	<html:text  property="bldkrq" styleId="bldkrq" maxlength="25"  styleClass="text_nor" onfocus="showCalendar('bldkrq','y-mm-dd');"></html:text>
						    </td>
						</logic:equal>
						<logic:notEqual value="10704" name="xxdm">						
							<td width="35%" colspan="3">
								<html:text property="htbh" styleId="htbh" maxlength="20"  styleClass="text_nor"></html:text>
						    </td>
						</logic:notEqual>
						</logic:notEqual>
						
					</tr>
					<tr>
						<th width="15%">贷款银行</th>
						<td width="35%">
							<html:text  property="yhmc" styleId="yhmc" maxlength="25"  styleClass="text_nor"></html:text>
						</td>
						<th width="15%">银行电话</th>
						<td width="35%">
							<html:text  property="lxdh" styleId="lxdh" maxlength="15"  styleClass="text_nor"></html:text>
						</td>
					</tr>
					<!-- 西安科技大学个性化 -->
					<logic:equal value="10704" name="xxdm">
						<tr>
							<th width="15%">合同签订地点</th>
							<td width="35%">
								<html:text  property="htdd" styleId="htdd" maxlength="15"  styleClass="text_nor"></html:text>
							</td>
							<th width="15%">学院经办人</th>
							<td width="35%">
								<html:text property="xyjbr" styleId="xyjbr" maxlength="20" styleClass="text_nor"></html:text>
							</td>
						</tr>
					</logic:equal>
					<logic:equal value="12688" name="xxdm">
						</tbody>
					</logic:equal>
				</table>
			</logic:notEqual>
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
			<div>
				<div style="height: 30px">
				</div>
				<table class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
								<logic:notEqual name="xxdm" value="10511">
									<button type="button" onclick="saveDksq('zxdkDkjg.do?method=update');">
										保    存
									</button>
									<button type="button" onclick="iFClose();">
										关闭
									</button>
								</logic:notEqual>
								<logic:equal  name="xxdm" value="10511">
									 	<button type="button" id="bccg" onclick="saveJgForHsd('zxdkDkjg.do?method=saveUpdateForHsd');">
											保    存
										</button>
										<button type="button" onclick="iFClose();">
											关闭
										</button>
									</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
	
</html>