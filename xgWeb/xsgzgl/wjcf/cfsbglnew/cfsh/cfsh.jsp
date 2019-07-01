<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src='/xgxt/js/check.js'></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/wjcf/cfsbglnew/cfsh/js/cfsh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${map.cfid}&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${cfshForm.splcid}&shid=${cfshForm.shid}",function(){
					jQuery("#shjg").change(function(){
						if(jQuery(this).val()=='1')
							jQuery("#cffw_tr1,#cffw_tr2,#cffw_tr3").show();
						else
							jQuery("#cffw_tr1,#cffw_tr2,#cffw_tr3").hide();
					});
				});
			var cflbdm ='${map.cflbdm }';
			
			showCfqxFlag(cflbdm);
			showCfqxFlagSh(cflbdm);
			
			defaultCfdqsj();
			
			jQuery("#cflbdm").change(function(){
				defaultCfdqsj();
			});
			
			jQuery("#cfsj").change(function(){
				defaultCfdqsj();
			});
			
		});

		//下载
		function fjxz(){
			var url="wjcf_cfsh.do?method=fjxz";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}

		function save_sh(){
			
			var shzt = jQuery("#shjg").val();
			var cfid = jQuery("#cfid").val();
			var cfwh = jQuery("#cfwh").val();
			jQuery("#shzt").val(shzt);

			if(jQuery("#xxdm").val() == "14008"){
				if (shzt != "1" && jQuery("#shyj").val() == ""){
					showAlertDivLayer("请填写审核意见！");
					return false;
				}
			}else{
				if (jQuery("#shyj").val() == ""){
					showAlertDivLayer("请填写审核意见！");
					return false;
				}
			}
			if (jQuery("#shyj").val().length>200){
				showAlertDivLayer("审核意见不能超过200字");
				return false;
			}
			//当最后一级审核通过时或权限岗位审核通过时，判段是否填写处分文号及处分时间
			if((shzt=="1"||shzt==1)){
				if(jQuery("#isZhgw").val()=="true"||${cffwqxPd==2}){
					if(jQuery("#xxdm").val() != "70002"){
						if(jQuery("#cfwh").val()==""){
							showAlertDivLayer("请填写处分文号！");
							return false;
						}
					}
					if(jQuery("#cfsj").val()==""){
						showAlertDivLayer("请填写处分时间！");
						return false;
					}
					if(jQuery("#cflbdm").val()==""){
						showAlertDivLayer("请选择处分类别！");
						return false;
					}
					
					var cfdqsj = jQuery("#cfdqsj");
					if(cfdqsj.length>0){
						if(cfdqsj.val()==""){
							showAlertDivLayer("请填写处分到期时间！");
							return false;
						}
					}
					
					var wjsj = jQuery("#wjsj").val();
					var cfsj = jQuery("#cfsj").val();
					
					if(""!=wjsj&&cfsj!=""){
						if(new Date(cfsj)<(new Date(wjsj))){
							showAlertDivLayer("处分时间不能小于违纪时间！");
							return false;
						}
					}
					
				}
			}
			var message;
			if(jQuery("#shjg").val() == "1"){
				message = "通过";
			}
			if(jQuery("#shjg").val() == "2"){
				message = "不通过";
			}
			if(jQuery("#shjg").val() == "3"){
				message = "退回";
			}
			if((${cffwqxPd==2}||"true"==jQuery("#isZhgw").val())&&"12686"==jQuery("#xxdm").val()){
				var flag=true;
				jQuery.ajaxSetup({async:false});
				jQuery.post("wjcf_cfsbgl.do?method=checkExistCfwh", {
					cfid:cfid,
					cfwh:cfwh
				}, function(data) {
					if(data ==null || data){
						flag=false;
					}
				},"json");
				jQuery.ajaxSetup({async:true});
				if(!flag){
					showAlert("该处分文号已存在，请修改！");
					return false;
					}
				}
			//提交审核
			showConfirmDivLayer("您确定“"+message+"”该申请吗？",{"okFun":function(){
				// 最后一级审核 并且审核通过时判断
				if((jQuery("#isZhgw").val()=="true"||${cffwqxPd==2}) && jQuery("#shjg").val() == "1"){
					var xh = jQuery("#xh").val();
					var cflbdm = jQuery("#cflbdm").val();
					var wjsj = jQuery("#wjsj").val();
					
					// 验证处分在结果库当中是否存在 （验证条件：学号、处分类别、处分时间
					jQuery.post("wjcf_cfsbgl.do?method=checkExistCfjg", {
						xh:xh,
						cflbdm:cflbdm,
						wjsj:wjsj
					}, function(data) {
						if(data ==null || data){
							showAlert("该学生在"+wjsj+"的违纪已在处分结果中存在！");
							return false;
						}else{
							var url = "wjcf_cfsh.do?method=cfsh&type=save";
							ajaxSubFormWithFun("cfshForm",url,function(data){
								showAlertDivLayer(data["message"],{},{"clkFun":function(){
									refershParent();
								}});
							});
						}
					},"json");
					
				}else{

					var url = "wjcf_cfsh.do?method=cfsh&type=save";
					ajaxSubFormWithFun("cfshForm",url,function(data){
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							refershParent();
						}});
					});
				}
			}});
		}
		
		//显示处分期限
		function showCfqxFlagSh(cflbdm){
			//对于青岛酒店管理职业技术学院屏蔽该功能
			if(${xxdm=='13011'}) return false;
			
			if(cflbdm==""){return false;}
			jQuery.post("wjcf_cflbdmwh.do?method=getCfqx",{cflbdm:cflbdm},function(data){
				jQuery("#showCfqxsh").html(data["message"]);
			},'json');
		}
		
		function showCfqxFlag(cflbdm){
			//对于青岛酒店管理职业技术学院屏蔽该功能
			if(${xxdm=='13011'}) return false;
			
			if(cflbdm==""){return false;}
			jQuery.post("wjcf_cflbdmwh.do?method=getCfqx",{cflbdm:cflbdm},function(data){
				jQuery("#showCfqx").html(data["message"]);
			},'json');
			
		}
		
		//初始化处分到期时间，获得处分到期时间默认值，处分时间+处分期限（如果没有处分期限则不显示处分到期时间）
		function defaultCfdqsj(){
			var cfsj = jQuery("#cfsj").val();
			var cflbdm = jQuery("#cflbdm").val();
			
			jQuery.post("wjcf_cfsh.do?method=defaultCfdqsj",{cfsj:cfsj,cflbdm:cflbdm},function(data){
				//jQuery("#cfdqsj").val(data["message"]);
				if(data["message"]!="hidden"){
					var html = "<th><font color=\"red\">*</font>处分到期时间：</th><td colspan=\"3\"><input name=\"cfdqsj\" id=\"cfdqsj\" "
					+" style=\"cursor:hand;\" onclick=\"return showCalendar('cfdqsj','y-mm-dd',false,'cfsj');\" value=\""+data["message"]+"\"/></td>";
					jQuery("#cffw_tr3").html(html);
				}else{
					jQuery("#cffw_tr3").html("");
				}
				
			},'json');
		}
		</script>
	</head>
	
	<body>
		<html:form method="post" styleId="cfshForm" action="/wjcf_cfsh" >
		<html:hidden property="ywid" name="cfshForm" styleId="ywid"/>
		<html:hidden property="shid" name="cfshForm" styleId="shid"/>
		<html:hidden property="gwid" name="cfshForm" styleId="gwid"/>
		<html:hidden property="splcid" name="cfshForm" styleId="splcid"/>
		<html:hidden property="kzzd1" name="kzzd1" styleId="kzzd1" value="${map.cflbdm }"/>
		<input name="isZhgw" type="hidden" id="isZhgw" value="${isZhgw }"/>
		<input type="hidden" name="fjmc" id="fjmc" value="${map.fjmc }"/>
		<input type="hidden" name="cfid" id="cfid" value="${cfshForm.ywid }"/>
		<input type="hidden" name="wjsj" id="wjsj" value="${map.wjsj }"/>
		<input type="hidden" name="shzt" id="shzt"/>
		<input type="hidden" id="xxdm" value="${xxdm }"/>
			<div
				style=' overflow-x: hidden; overflow-y: auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/wjcf/cfsbglnew/cfsb/selectStudent.jsp"%>
					<thead>
						<tr>
							<th colspan="4">
								<span>处分上报信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="20%">
								学年学期
							</th>
							<td align="left" width="30%">
								${map.xn } ${map.xqmc }
							</td>
							<th align="right" width="20%">
								违纪时间
							</th>
							<td align="left" width="30%">
								${map.wjsj }
							</td>
						</tr>
						<tr>
							<th align="right">
								处分原因
							</th>
							<td align="left">
								${map.cfyymc }
							</td>
							<th align="right">
								处分类别
							</th>
							<td align="left">
								${map.cflbmc }&nbsp;&nbsp;&nbsp;&nbsp;<span id="showCfqx" style="color: red"></span>
							</td>
						</tr>
						<tr>
							<th align="right">
								上报人
							</th>
							<td align="left" colspan="3">
								${map.sbbxm }
							</td>
						</tr>
						<tr>
							<th align="right">
								处分建议
							</th>
							<td align="left" colspan="3">
								${map.cfyj }
							</td>
						</tr>
						<tr>
							<th align="right" width="20%">
								学生检讨书
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//调用附件
									jQuery(function(){
										var gid = "${map.filepath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
											});
									});
								</script>
							</td>
						</tr>
						<tr>
							<th align="right" width="20%">
								考场违纪记录单
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-2" style="padding: 5px;"></div>
								<script type="text/javascript">
                                    //调用附件
                                    jQuery(function(){
                                        var gid = "${map.filepath2}";
                                        jQuery.MultiUploader_q({
                                            gid : gid,
                                            targetEl : 'commonfileupload-list-2'
                                        });
                                    });
								</script>
							</td>
						</tr>
						<tr>
							<th align="right" width="20%">
								夹带纸条
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-3" style="padding: 5px;"></div>
								<script type="text/javascript">
                                    //调用附件
                                    jQuery(function(){
                                        var gid = "${map.filepath3}";
                                        jQuery.MultiUploader_q({
                                            gid : gid,
                                            targetEl : 'commonfileupload-list-3'
                                        });
                                    });
								</script>
							</td>
						</tr>
						<tr>
							<th align="right" width="20%">
								申辩会议记录
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-4" style="padding: 5px;"></div>
								<script type="text/javascript">
                                    //调用附件
                                    jQuery(function(){
                                        var gid = "${map.filepath4}";
                                        jQuery.MultiUploader_q({
                                            gid : gid,
                                            targetEl : 'commonfileupload-list-4'
                                        });
                                    });
								</script>
							</td>
						</tr>
						<tr>
							<th align="right">
								违纪事实经过
							</th>
							<td align="left" colspan="3" style="word-break:break-all;width:100%">
								${map.wjssjg }
							</td>
						</tr>
						<tr>
							<th align="right">
								备注
							</th>
							<td align="left" colspan="3" style="word-break:break-all;width:100%">
								${map.bz }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>处分审核情况</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4" id="shlccx">
							
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>审核信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<!-- 原逻辑 ：在审核到最后一级时显示处分发文信息并可修改，其它不显示-->
						<!-- 现改为 ：在审核时具有处分发文权限的审核级别显示并可修改，较低级别不显示，较高级别显示但不可修改，
						另外情况：早期数据未填处分发文权限字段，则按原逻辑处理-->
						<!-- 如果已经到最后一级，但最后一起的权限因某些原因（如处分类别修改）等级依然低于权限岗位
							   则最后一级可以查看并修改处分发文信息 -->
						<!-- 处分发文权限判断 0：未填写，1：等级低于权限岗位，2：等级等于权限岗位，3等级高于权限岗位 -->
						
						<!-- 0、1两种情况 -->
						<logic:notEqual value="2" name="cffwqxPd">
							<logic:notEqual value="3" name="cffwqxPd">
								<logic:equal value="true" name="isZhgw">
									<tr id="cffw_tr1">
										<th align="right">
											<font color="red">*</font>处分文号：
										</th>
										<td align="left"  >
											<input name="cfwh" id="cfwh" maxlength="30" value="${map.kzzd2}"/>
										<th align="right">
											<font color="red">*</font>处分时间：
										</th>
										<td align="left">
											<input name="cfsj" id="cfsj" style="cursor:hand;" 
											onclick="return showCalendar('cfsj','y-mm-dd','','','','',defaultCfdqsj);" 
											value="${map.kzzd3 }"/>
										</td>
									</tr>
									<tr id="cffw_tr2">
										<th align="right">
											<font color="red">*</font>处分类别：
										</th>
										<td align="left" colspan="3">
											<html:select property="cflbdm" styleId="cflbdm"
												style="width:100px" onchange="showCfqxFlagSh(this.value);">
												<html:option value=""></html:option>
												<html:options collection="cflbList" property="dm"
													labelProperty="mc" />
											</html:select>
											&nbsp;&nbsp;&nbsp;&nbsp;<span id="showCfqxsh" style="color: red"></span>
										</td>
									</tr>
									<tr id="cffw_tr3">
									</tr>
									
								</logic:equal>
							</logic:notEqual>
						</logic:notEqual>
						
						<!-- 2情况 -->
						<logic:equal value="2" name="cffwqxPd">
							<tr id="cffw_tr1">
							
								<th align="right">
									<logic:notEqual value="70002" name="xxdm"><font color="red">*</font></logic:notEqual>处分文号：
								</th>
								<td align="left"  >
									<input name="cfwh" id="cfwh" maxlength="30" value="${map.kzzd2}"/>
								</td>
								<th align="right">
									<font color="red">*</font>处分时间：
								</th>
								<td align="left">
									<input name="cfsj" id="cfsj" style="cursor:hand;" 
									onclick="return showCalendar('cfsj','y-mm-dd','','','','',defaultCfdqsj);" 
									value="${map.kzzd3 }"/>
								</td>
							</tr>
							<tr id="cffw_tr2">
								<th align="right">
									<font color="red">*</font>处分类别：
								</th>
								<td align="left" colspan="3">
									<html:select property="cflbdm" styleId="cflbdm"
										style="width:100px" onchange="showCfqxFlagSh(this.value);">
										<html:option value=""></html:option>
										<html:options collection="cflbList" property="dm"
											labelProperty="mc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;<span id="showCfqxsh" style="color: red"></span>
								</td>
								
							</tr>
							<tr id="cffw_tr3">
							</tr>
						</logic:equal>
						
						<!-- 3情况 -->
						<logic:equal value="3" name="cffwqxPd">
							<tr>
								<th align="right">
									处分文号：
								</th>
								<td align="left"  >
									${map.kzzd2}
									<input type="hidden" name="cfwh"  value="${map.kzzd2}"/>
								</td>
								<th align="right">
									处分时间：
								</th>
								<td align="left">
									${map.kzzd3 }
									<input type="hidden" name="cfsj"  value="${map.kzzd3 }"/>
								</td>
							</tr>
							<tr>
								<th align="right">
									处分类别：
								</th>
								<td align="left" colspan="3">
									${map.cflbmc}
									&nbsp;&nbsp;&nbsp;&nbsp;<span id="showCfqxsh" style="color: red"></span>
									<input type="hidden" name="cflbdm"  value="${map.cflbdm }"/>
								</td>
							</tr>
							<logic:present name="map" property="kzzd5">
								<tr>
									<th align="right">
										处分到期时间：
									</th>
									<td align="left" colspan="3">
										${map.kzzd5 }
										<input type="hidden" name="cfdqsj"  value="${map.kzzd5 }"/>
									</td>
								</tr>
							</logic:present>
						</logic:equal>
						
						
						<tr>
							<tr>
								<th>
									<font color="red">*</font>审核结果
								</th>
								<td colspan="3" id="shjgSpan">
									
								</td>
							</tr>
						</tr>
						<tr>
						
       						<th >
				        		<logic:notEqual name="xxdm" value="14008"><font color="red">*</font></logic:notEqual>审核意见
				        		<br/>
								<font color="red"><B>(限200字)</B>
								</font>
				       		</th>
				     	    <td width="34%" colspan="3">
				     	    	<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=cfsb&id=shyj" />
				        		<textarea rows="5" style="width: 90%;margin-top: 5px" id="shyj" name="shyj" onblur="checkLen(this,200)"></textarea>
				       		</td>
				        </tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>已受处分情况</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table class="formList" width="100%">
									<thead align="center">
										<tr align="center">
											<td>
												<b>学年</b>
											</td>
											<td>
												<b>学期</b>
											</td>
											<td>
												<b>处分类别</b>
											</td>
											<td>
												<b>处分原因</b>
											</td>
											<td>
												<b>处分时间</b>
											</td>
											<td>
												<b>处分文号</b>
											</td>
										</tr>
									</thead>
									<tbody align="center">
										<logic:notEmpty name="yscfqkList">
											<logic:iterate name="yscfqkList" id="s">
												<tr style="cursor: hand">
													<td>
														${s.xn}
													</td>
													<td>
														${s.xqmc}
													</td>
													<td>
														${s.cflbmc}
													</td>
													<td>
														${s.cfyymc}
													</td>
													<td>
														${s.cfsj}
													</td>
													<td>
														${s.cfwh}
													</td>
												</tr>
											</logic:iterate>
										</logic:notEmpty>
									</tbody>
								</table>
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
									<div class="btn">
										<button type="button" name="保存" id="buttonClose" onclick="save_sh();return false;">
											保存
										</button>
										&nbsp;&nbsp;
										<button type="button" name="关 闭" onclick="iFClose();">
											关 闭
										</button>
									</div>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			</html:form>
	</body>
</html>