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
		<script type="text/javascript" src="xsgzgl/wjcf/cfjcglnew/cfjcsh/js/cfjcsh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
		var text;
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${cfjcshForm.ywid}&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${cfjcshForm.splcid}&shid=${cfjcshForm.shid}",function(){
				jQuery("#shjg").change(function(){
					if(jQuery(this).val()=='1')
						jQuery("#jcfw_tr1").show();
					else
						jQuery("#jcfw_tr1").hide();
				});
			});
			text=jQuery("#text").val();
			
			var cflbdm ='${map.cflbdm }';
			showCfqxFlag(cflbdm);
		});

		//下载
		function fjxz(){
			var url="wjcf_cfsh.do?method=fjxz";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}

		function save_sh(shzt,message){
			var shzt = jQuery("#shjg").val();
			var jcwh = jQuery("#jcwh").val();
			var cfid = jQuery("#cfid").val();
			jQuery("#shzt").val(shzt);
			if (jQuery("#shyj").val() == ""){
				showAlertDivLayer("请填写审核意见！");
				return false;
			}
			if (jQuery("#shyj").val().length>200){
				showAlertDivLayer("审核意见不能超过200字");
				return false;
			}
			//当最后一级审核通过时，判段是否填写申诉文号及申诉时间
			if((shzt=="1"||shzt==1)){
				if(jQuery("#isZhgw").val()=="true"){
					if("12684"!=jQuery("#xxdm").val()){
						if(jQuery("#jcwh").val()==""){
							showAlertDivLayer("请填写"+text+"文号！");
							return false;
						}
					}
					if(jQuery("#jcsj").val()==""){
						showAlertDivLayer("请填写"+text+"时间！");
						return false;
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
			if("true"==jQuery("#isZhgw").val()&&"12686"==jQuery("#xxdm").val()){
				var flag=true;
				jQuery.ajaxSetup({async:false});
				jQuery.post("wjcf_cfjcsh.do?method=checkExistJcwh", {
					cfid:cfid,
					jcwh:jcwh
				}, function(data) {
					if(data ==null || data){
						flag=false;
					}
				},"json");
				jQuery.ajaxSetup({async:true});
				if(!flag){
					showAlert("该解除文号已存在，请修改！");
					return false;
					}
				}
		
			//提交审核
			showConfirmDivLayer("您确定“"+message+"”该申请吗？",{"okFun":function(){
				var url = "wjcf_cfjcsh.do?method=jcsh&type=save";
				ajaxSubFormWithFun("cfjcshForm",url,function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						//if (parent.window){
							refershParent();
						//}
					}});
				});
			}});
		}
		
		//处分期限显示
		function showCfqxFlag(cflbdm){
			//对于青岛酒店管理职业技术学院屏蔽该功能
			if(${xxdm=='13011'}) return false;
			
			if(cflbdm==""){return false;}
			jQuery.post("wjcf_cflbdmwh.do?method=getCfqx",{cflbdm:cflbdm},function(data){
				jQuery("#showCfqx").html(data["message"]);
			},'json');
		}
		
		</script>
	</head>
	
	<body>
		<html:form method="post" styleId="cfjcshForm" action="/wjcf_cfjcsh" >
		<html:hidden property="ywid" name="cfjcshForm" styleId="ywid"/>
		<html:hidden property="shid" name="cfjcshForm" styleId="shid"/>
		<html:hidden property="gwid" name="cfjcshForm" styleId="gwid"/>
		<html:hidden property="splcid" name="cfjcshForm" styleId="splcid"/>
		<html:hidden property="cfid" name="cfjcshForm" styleId="cfid"/>
		<input name="isZhgw" type="hidden" id="isZhgw" value="${isZhgw }"/>
		<input type="hidden" name="fjmc" id="fjmc" value="${map.fjmc }"/>
		<input type="hidden" name="shzt" id="shzt"/>
		<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}"/>
		<input type="hidden" id="text" value="<bean:message key="wjcf.text" />"/>
			<div
				style='width: 100%; height: 460px; overflow-x: hidden; overflow-y: auto;'>
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
								${map.xn }${map.xqmc }
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
								处分文号
							</th>
							<td align="left">
								${map.cfwh }
							</td>
							<th align="right">
								处分时间
							</th>
							<td align="left">
								${map.cfsj }
							</td>
						</tr>
						<logic:present name="map" property="cfdqsj">
							<tr>
								<th align="right">
									处分到期时间
								</th>
								<td align="left"  colspan="3">
									${map.cfdqsj }
								</td>
							</tr>
						</logic:present>
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
								<span><bean:message key="wjcf.text" />信息</span>
							</th>
							
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right">
								申请时间
							</th>
							<td align="left">
								<bean:write name="jcxx" property="sqsj"/>
							</td>
							<th align="right">
							</th>
							<td align="left">
							</td>
						</tr>
						<logic:notEqual name="xxdm" value="12865">
							<tr>
								<th align="right">
									<bean:message key="wjcf.text" />理由
								</th>
								<td  align="left" colspan="3" style="word-break:break-all;width:100%">
									<%--<bean:write name="jcxx" property="sqly"/>--%>
									${jcxx.sqly}
								</td>
							</tr>
						</logic:notEqual>
						<logic:equal name="xxdm" value="12865">
							<tr>
								<th align="right">
									鉴定信息
								</th>
								<td align="left" colspan="3" style="word-break:break-all;width:100%">
									<bean:write name="jcxx" property="jdxx"/>
								</td>
							</tr>
							<tr>
								<th align="right">
									处分鉴定申请
								</th>
								<td  align="left" colspan="3" style="word-break:break-all;width:100%">
									<%--<bean:write name="jcxx" property="sqly"/>--%>
									${jcxx.sqly}
								</td>
							</tr>
						</logic:equal>
						<tr>
							<th align="right" width="20%">
								跟踪教育记录表
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-5" style="padding: 5px;"></div>
								<script type="text/javascript">
                                    //调用附件
                                    jQuery(function(){
                                        var gid = "${jcxx.filepath}";
                                        jQuery.MultiUploader_q({
                                            gid : gid,
                                            targetEl : 'commonfileupload-list-5'
                                        });
                                    });
								</script>
							</td>
						</tr>
						<tr>
							<th align="right" width="20%">
								成绩单
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-6" style="padding: 5px;"></div>
								<script type="text/javascript">
                                    //调用附件
                                    jQuery(function(){
                                        var gid = "${jcxx.filepath2}";
                                        jQuery.MultiUploader_q({
                                            gid : gid,
                                            targetEl : 'commonfileupload-list-6'
                                        });
                                    });
								</script>
							</td>
						</tr>
					</tbody>
					<logic:equal value="11318" name="xxdm">
					<thead>
						<tr>
							<th colspan="4">
								<span>历年获奖信息
									<a onclick="showLsjl(this);" class="down" 
									   href="javascript:void(0);">
									   <font color="blue">点击展开/收起</font>	
									</a>
								</span>
							</th>
						</tr>
					</thead>
					<tbody id="t_hjjl" style="display: table-row-group;">
						<tr>
							<td colspan="4">
							<table class="dateline" width="100%">
								<thead align="left">
									<tr align="left">
										<td ><b>评奖周期</b></td>
										<td><b>项目名称</b></td>
										<td><b>项目金额</b></td>
										<td ><b>申请时间</b></td>
									</tr>
								</thead>
								<tbody align="left">
							<logic:notEmpty name="hjqkList">
							<logic:iterate name="hjqkList" id="s">
										<tr  style="cursor:hand">
										<td >
												${s.pjzq}
											</td>
											
											<td >
												${s.xmmc}
											</td>
											<td >
												${s.xmje}
											</td>
											<td >
												${s.sqsj}
											</td>
										</tr>
								</logic:iterate>
							</logic:notEmpty>
							</tbody>
							</table>
						</tr>
					</tbody>
					</logic:equal>
					<thead>
						<tr>
							<th colspan="4">
								<span><bean:message key="wjcf.text" />审核情况</span>
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
						<logic:equal value="true" name="isZhgw">
							<tr id="jcfw_tr1">
								<logic:equal value="12684" name="xxdm">
									<th align="right">
										<bean:message key="wjcf.text" />文号
									</th>
								</logic:equal>
								<logic:notEqual value="12684" name="xxdm">
									<th align="right">
										<font color="red">*</font><bean:message key="wjcf.text" />文号
									</th>
								</logic:notEqual>
								<td align="left"  >
									<html:text property='jcwh' styleId="jcwh" maxlength="30"/>
								</td>
								<th align="right">
									<font color="red">*</font><bean:message key="wjcf.text" />时间
								</th>
								<td align="left">
								<html:text property="jcsj" styleId="jcsj"
									style="cursor:hand;"
									onclick="return showCalendar('jcsj','y-mm-dd');" />
								</td>
							</tr>
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
				        		<font color="red">*</font>审核意见
				       		</th>
				     	    <td width="34%" colspan="3">
				     	    	<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=cfjc&id=shyj" />
				        		<textarea rows="5" style="width: 90%;margin-top:5px" id="shyj" name="shyj"></textarea>
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
										<button type="button" onclick="save_sh();">
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