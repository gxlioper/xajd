<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xyddk/js/dksq.js"></script>
		
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		
		<script type="text/javascript">
			jQuery(function(){

				onShow("zxdk_query");
				
				//加载下拉选项
				loadViewMkxxSelectOptions();
				//加载radio选项
				loadViewMkxxRadioOptions();

				var xh = jQuery("#xh").val();
				
				if (jQuery.trim(xh) != ""){
					jQuery("#div_jtqk").load("xszz_jtqkdc.do?method=jtqkInfo",{xh:xh},function(){
					});
					
				}
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${zxdkXyddkForm.id}&tt="+new Date().getTime());
			});
		</script>
	</head>
	<body>
		<html:form action="/zxdkXyddk" method="post" styleId="xyddkForm">
			<html:hidden property="id" styleId="id"/>
			<html:hidden property="xh" styleId="xh"/>
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
								<span>家庭情况${zxdkXyddkForm.xn }
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
				</table>
			</div>
			<logic:notEqual value="10511" name="xxdm">
				<div class="tab"  id="content" style="margin-top: 5px; overflow-x:hidden;" ></div>
			</logic:notEqual>
			<logic:equal value="10511" name="xxdm">
					<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>贷款信息<font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;单位（元）</font></span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">贷款期数</th>
							<td width="34%" id='dkqxtd'>
							  ${mkxxForm.dkqx}
							</td>
							<th width="16%">贷款期限（月）</th>
							<td width="34%">
								 ${mkxxForm.jhr1}
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
								 ${mkxxForm.zsfdks}
							</td>
							<th width="16%">学费贷款总额</th>
							<td width="34%">
								 ${mkxxForm.xfdks}
							</td>
						</tr>
						<tr>
							<th width="16%">生活费贷款总额</th>
							<td width="34%">
								${mkxxForm.shfdks}
							</td>
							<th width="16%">贷款总金额</th>
							<td width="34%">
								${mkxxForm.dkje}
							</td>
						</tr>
						<tr>
							<th width="16%">申请理由</th>
							<td width="84%" colspan="3">
								${mkxxForm.sqly}
							</td>
						</tr>
					</tbody>
				</table>
			</logic:equal>
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>附件信息</span>
						</th>
					</tr>
				</thead>
					<tr>
						<th align="right" width="10%">
							附件信息
						</th>
						<td colspan="3">
							<div id="commonfileupload-list-0" style="padding: 5px;"></div>
							<html:hidden property="filepath" styleId="fjid"/>
							<script type="text/javascript">
								//调用附件 
								jQuery(function(){
									var gid = jQuery('#fjid').val();
									jQuery.MultiUploader_q({
										gid : gid,
										targetEl : 'commonfileupload-list-0'
									});
								});
							</script>
						</td>
					</tr>
			</table>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px; margin-top: 5px;">
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
				</table>
			</div>
			<div style="height: 30px"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
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