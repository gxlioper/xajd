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
		<script type="text/javascript" src="gygl/sspy/pysh/js/pysh.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				getSscyxx();
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
					var url = "sspysh.do?method=sspyDgsh&type=save";
					ajaxSubFormWithFun("sspyshForm",url,function(data){
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
		<html:form method="post" styleId="sspyshForm" action="/sspysh"
			enctype="multipart/form-data">
		<html:hidden name="model" property="sqid" styleId="sqid"/>
		<html:hidden name="model" property="xh" styleId="xh"/>		
		<html:hidden name="model" property="splc" styleId="splc"/>
		<html:hidden name="model" property="shid" styleId="shid"/>
		<input type="hidden" id="lddm" value="${lddm }"/>
		<input type="hidden" id="qsh" value="${qsh }"/>
			<div style='width: 100%; overflow-x: hidden; overflow-y: auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
					<tr>
						<th colspan="4">
							<span>床位信息</span>
						</th>
					</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right">
								楼栋名称
							</th>
							<td align="left">
								${cwxxData.ldmc}
							</td>
							<th align="right">
								寝室号
							</th>
							<td align="left">
								${cwxxData.qsh}
							</td>
						</tr>
						<tr>
							<th align="right" >
								床位号
							</th>
							<td align="left" id="td_cwh">
								${cwxxData.cwh}
							</td>
							<th align="right">
								寝室电话
							</th>
							<td align="left">
								${cwxxData.qsdh}
							</td>
						</tr>
						<tr>
							<th align="right" >
								收费标准
							</th>
							<td align="left">
								${cwxxData.sfbz}
							</td>
							<th align="right">
								所属年级
							</th>
							<td align="left">
								${cwxxData.nj}
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								所属<bean:message key="lable.xb" />
							</th>
							<td align="left">
								${cwxxData.xymc}
							</td>
							<th align="right">
								所属班级
							</th>
							<td align="left">
								${cwxxData.bjmc}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>宿舍成员</span>
							</th>
						</tr>
					</thead>
					<thead>
						<tr>
							<th  width="20%">
								<div align="center" >学号</div>
							</th>
							<th>
								<div align="center" >姓名</div>
							</th>
							<th>
								<div align="center" >班级</div>
							</th>
							<th>
								<div align="center" >入住床位</div>
							</th>
						</tr>
					</thead>
					<tbody id="xsList">
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>申请评优项目</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="10%">
								学年
							</th>
							<td align="left">
								${dqxn}
							</td>
							<th align="right">
								学期
							</th>
							<td align="left">
								${dqxq}
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								评奖项目
							</th>
							<td align="left" colspan="3">
							${rs.pyxmmc}
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								申请理由
							
							</th>
							<td colspan="5">
								${rs.sqly}
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
						<tr>
							<th align="right" width="10%">
								备注&nbsp;
							
							</th>
							<td colspan="5">
								${rs.bz }
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
