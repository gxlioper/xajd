<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.sqid}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splc}&shid=${model.shid}");
			});
			function saveShzt(){
				var shzt = jQuery("#shjg").val();
				var shyj = jQuery("#shyj").val();
				
				if(jQuery("#shjg").val() == "0"){
					showAlertDivLayer("请选择审核状态！");
					return false;
				}
			
				if (jQuery.trim(shyj) == ""){
					showAlertDivLayer("请填写审核意见！");
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
				showConfirmDivLayer("您确定" + message + "该申请吗？",{"okFun":function(){
					var url = "gfjyqk_sh.do?method=gfqkDgsh&type=save";
					ajaxSubFormWithFun("gfjyshForm",url,function(data){
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
					});
				}});
		
			}
		</script>
	</head>
	<body>
		<html:form method="post" styleId="gfjyshForm" action="/gfjyqk_sh"
			enctype="multipart/form-data">
		<html:hidden name="model" property="sqid" styleId="sqid"/>
		<html:hidden name="model" property="xh" styleId="xh"/>		
		<html:hidden name="model" property="splc" styleId="splc"/>
		<html:hidden name="model" property="shid" styleId="shid"/>
			<div style='width: 100%; overflow-x: hidden; overflow-y: auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/xszbb/comm/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>国防教育情况</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="10%">
								学年
							</th>
							<td align="left">
								${rs.xn}
							</td>
							<th align="right">
								学期
							</th>
							<td align="left">
									${rs.xqmc}
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								国防情况分类
							</th>
							<td align="left" colspan="3">
								${rs.gfqkflmc}
							</td>
						</tr>
						<tr id="bydj" <logic:notEqual value="1" name="gfqkfl">style="display:none"</logic:notEqual>>
							<th align="right" width="10%">
								兵役登记时间
							</th>
							    <td align="left">
									${rs.bydjsj}
								</td>
							
							<th align="right">
								兵役登记地点
							</th>
								<td align="left">
									${rs.bydjdd}
								</td>
						</tr>
						
						<tr id="cjrw" <logic:notEqual value="2" name="gfqkfl">style="display:none"</logic:notEqual>>
							<th align="right" width="10%">
								参军入伍时间
							</th>
							    <td align="left">
										${rs.cjrwsj}
								</td>
							
							<th align="right">
								入伍批准单位
							</th>
								<td align="left">
									${rs.rwpzdw}
								</td>
						</tr>
						<tr id="twfx"  <logic:notEqual value="3" name="gfqkfl">style="display:none"</logic:notEqual>>
							<th align="right" width="10%">
								退伍复学时间
							</th>
							    <td align="left">
							    ${rs.twfxsj}
								</td>
							
							<th align="right">
								退伍批准单位
							</th>
								<td align="left">
								    ${rs.twpzdw}
								</td>
						</tr>
						<tr id="pjpy"  <logic:notEqual value="4" name="gfqkfl">style="display:none"</logic:notEqual>>
							<th align="right" width="10%">
								评奖评优时间
							</th>
							    <td align="left">
							       ${rs.pjpysj}
								</td>
							
							<th align="right">
								评奖评优单位
							</th>
								<td align="left">
								 ${rs.pjpydw}
								</td>
						</tr>
						
						<tr id="jc"  <logic:notEqual value="5" name="gfqkfl">style="display:none"</logic:notEqual>>
							<th align="right" width="10%">
								奖惩时间
							</th>
							    <td align="left">
							     ${rs.jcsj}
								</td>
							
							<th align="right">
								奖惩单位
							</th>
								<td align="left">
								 ${rs.jcdw}
								</td>
						</tr>
						<tr id="cjhd"  <logic:notEqual value="6" name="gfqkfl">style="display:none"</logic:notEqual>>
							<th align="right" width="10%">
								参加活动时间
							</th>
							    <td align="left">
							     ${rs.cjhdsj}
								</td>
							
							<th align="right">
								参加活动地点
							</th>
								<td align="left">
								 ${rs.cjhddd}
								</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								备注&nbsp;
								<br />
							</th>
							<td colspan="3">
							 ${rs.bz}
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								附件信息
							</th>
							<td colspan="3">
							<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
							<input type="hidden" id="fjid" value="${filepath}"/>
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
				</table>
					<table width="100%" border="0" class="formlist">
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
					<tr>
						<th>
							<font color="red">*</font>审核结果
						</th>
						<td colspan="3" id="shjgSpan">
							
						</td>
					</tr>
			</tr>
			<tr>
				<th width="20%">
					<font color="red">*&nbsp;</font> 审核意见
					<br />
					<font color="red">限200字</font>
				</th>
				<td colspan="3">
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=xsxxxg&id=shyj" />
					<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top:5px" onblur="checkLen(this,200);"></textarea>
				</td>
			</tr>
			</tbody>
					</table>
			</div>
			<div style="height:50px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="保存"  onclick="saveShzt();return false;">
									保 存
								</button>
								<button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">
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
