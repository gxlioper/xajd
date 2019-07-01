<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/fdypx/js/fdypxsh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//为button注册事件
				jQuery("#xzpxxm").load('szdw_fdypxxmwh.do?method=fdypxxmxzView&xmdm=${model.xmdm}');
				jQuery("#yhdpxxm").load('szdw_fdypxxmsh.do?method=yhdpxxm&sqid=${model.sqid}');
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.sqid}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splc}&shid=${myForm.shid}");
				
			});

			/**
			 * 保存审核操作
			 * @param shzt
			 * @param message
			 * @return
			 */
			function save_sh(shzt,message){
				//jQuery("#shzt").val(shzt);
				var shzt = jQuery("#shjg").val();
				jQuery("#shzt").val(shzt);
				if (jQuery("#shyj").val() == ""){
					showAlertDivLayer("请填写审核意见！");
					return false;
				}
				if (jQuery("#shyj").val().length>200){
					showAlertDivLayer("审核意见不能超过200字");
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
					var url = "szdw_fdypxxmsh.do?method=fdypxxmsh&type=save&tt="+new Date();
					ajaxSubFormWithFun("demoForm",url,function(data){
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							//if (parent.window){
								refershParent();
							//}
						}});
					});
				}});
				
			}

		
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/rcsw_xsxxgl.do?method=zjXxgl" method="post" styleId="demoForm">
			<div style='tab;width:100%;height:460px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
								<html:hidden property="sqid" name="model" styleId="sqid"/>
								<html:hidden property="shzt" name="model" styleId="shzt"/>
								<html:hidden property="splc" name="model" styleId="splc"/>
								<html:hidden property="shid" name="myForm" styleId="shid"/>
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
							<th >
								姓名
							</th>
							<td width="34%">
							${rs.xm}
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td width="34%">
							${rs.xbs}
							</td>
							
							<th>
								所在部门
							</th>
							<td width="34%">
							${rs.bmmc}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>申请培训信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_sqly">
						<tr>
							<th >
								申请时间
							</th>
							<td width="34%" colspan="3">
								${model.sqsj}
							</td>
						</tr>
						<tr>
							<th >
								申请理由
							</th>
							<td width="34%" colspan="3">
								${model.sqly }
							</td>
						</tr>
						
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>申请培训项目信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_sqlys">
						<tr>
							<td colspan="4" id="xzpxxm">
								 
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>已通过培训项目</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_sqjg">
						<tr>
							<td colspan="4" id="yhdpxxm">
								 
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
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=fdypx&id=shyj" />
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
										<%--<button type="button" onclick="save_sh('1','通过');">
											通过
										</button>
										<button type="button" onclick="save_sh('2','不通过');">
											不通过
										</button>
										<button type="button" onclick="save_sh('3','退回');">
											退回
										</button>
										--%>
										<button type="button" onclick="save_sh();">
											保存
										</button>
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

