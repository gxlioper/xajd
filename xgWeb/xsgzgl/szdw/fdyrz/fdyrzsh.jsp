<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/fdyrz/js/fdyrz_sh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//为button注册事件
				jQuery("#xzbj").load("szdw_fdyrz_sq.do?method=fdyrzsqbj");
				jQuery("#bjlist").load("xsxx_bjgl.do?method=bjList");
				jQuery("#bjlist").hide();
				jQuery("#but_save").click(fydrz_sh_save);
				jQuery("#but_close").click(btn_close);
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.sqid}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splc}&shid=${model.shid}");
			});

			function save_sh(){
				var shzt = jQuery("#shjg").val();
				jQuery("#shzt").val(shzt);
				if(jQuery("#shjg").val() == "0"){
					showAlertDivLayer("请选择审核状态！");
					return false;
				}
				if (jQuery("#shyj").val() == ""){
					showConfirmDivLayer("请填写审核意见！",{"okFun":function(){}});
					return false;
				}
				if (jQuery("#shyj").val().length>200){
					showConfirmDivLayer("审核意见不能超过200字",{"okFun":function(){}});
					return false;
				}
				if (jQuery("#splc").val() == "" || jQuery("#sqbh").val() == ""||  jQuery("#gwid").val() == ""){
					showConfirmDivLayer("系统异常请稍后",{"okFun":function(){}});
					return false;
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
				//提交审核
				showConfirmDivLayer("您确定“"+message+"”该申请吗？",{"okFun":function(){
					var url = "szdw_fdyrz_sh.do?method=fdyrzsh&type=save&tt="+new Date();
					ajaxSubFormWithFun("demoForm",url,function(data){
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
								refershParent();
						}});
					});
				}});
				
			}
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/rcsw_xsxxgl.do?method=zjXxgl" method="post" styleId="demoForm">
			<div style='tab;width:100%;height:469px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
								<html:hidden property="sqid" name="model" styleId="sqid"/>
								<html:hidden property="shid" name="model" styleId="shid"/>
								<html:hidden property="shzt" name="model" styleId="shzt"/>
								<html:hidden property="splc" name="model" styleId="splc"/>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							
							<th width="20%">
								职工号
							</th>
							<td>
							${rs.zgh }
							</td>
							<th width="20%">
								<font color="red">*</font>专兼职
							</th>
							<td>
								${rs.zjz }
							</td>
							
						</tr>
						<tr>
							<th width="16%">
								姓名
							</th>
							<td width="34%">
							${rs.xm}
							</td>
							<th width="16%">
								性别
							</th>
							<td width="34%" >
								${rs.xbs }
							</td>
						</tr>
						<tr>
							<th width="16%">
								民族
							</th>
							<td width="34%">
							${rs.mzmc}
							</td>
							<th width="16%">
								政治面貌
							</th>
							<td width="34%" >
								${rs.zzmmmc}
							</td>
						</tr>
						<tr>
							<th width="16%">
								联系电话
							</th>
							<td width="34%">
							${rs.yddh}
							</td>
							<th width="16%">
								电子邮箱
							</th>
							<td width="34%" >
								${rs.dzyx}
							</td>
						</tr>
						<tr>
							<th>
								家庭地址
							</th>
							<td colspan="3">
							${rs.jtzz}
							</td>
						</tr>
						<tr>
							<th width="16%">
								思政工作时间
							</th>
							<td width="34%">
							${rs.szgzsj}
							</td>
							<th width="16%">
								到校工作时间
							</th>
							<td width="34%" >
								${rs.lxgzsj}
							</td>
						</tr>
						<tr>
							<th width="16%">
								学历
							</th>
							<td width="34%">
							${rs.xl}
							</td>
							<th width="16%">
								专业
							</th>
							<td width="34%" >
								${rs.sxzy}
							</td>
						</tr>
						<tr>
							<th>
								毕业院校
							</th>
							<td colspan="3">
							${rs.byyx}
							</td>
						</tr>
						
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>申请班级</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_sqly">
						<tr>
							<th >
								申请时间：
							</th>
							<td width="34%" colspan="3">
								${model.sqsj}
							</td>
						</tr>
						<tr>
							<th >
								申请带班数：
							</th>
							<td width="34%" colspan="3">
								${model.sqdbgs}
							</td>
						</tr>
						<tr>
							<th >
								申请理由：
							</th>
							<td width="34%" colspan="3">
								${model.sqly }
								<input type="hidden" id="splc" value="${model.splc }" />
								<input type="hidden" id="go_path" value="szdw_fdyrz_sh.do?method=fdyrzsh&type=save" />
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
						<tr>
							<td colspan="4" id="shlccx">
							
							</td>
						</tr>
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
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=fdyrz&id=shyj" />
								<textarea rows="5" style="width: 90%;margin-top: 5px" id="shyj" name="shyj"></textarea>
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
									<div class="btn"><%--
										<button type="button" onclick="save_sh('1','通过');">
											通过
										</button>
										<button type="button" onclick="save_sh('2','不通过');">
											不通过
										</button>
										<button type="button" onclick="save_sh('3','退回');">
											退回
										</button>
										--%>
										<button type="button" name="保存" onclick="save_sh();return false;">
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

