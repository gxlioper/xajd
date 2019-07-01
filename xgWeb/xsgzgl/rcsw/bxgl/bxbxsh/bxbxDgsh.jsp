<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		<script type="text/javascript">
		function onShow(gndm) {
			var url = "rcswBxglBxbxsq.do?method=getBxsqxx";
			jQuery.ajax( {
				type : "post",
				async : false,
				url : url,
				data : {
					bxid : jQuery("#sqid").val()
				},
				dataType : "json",
				success : function(data) {
					zdybdInit(gndm, data);
				}
			});
		}
		jQuery(function(){
			onShow("rcsw_bxbx_query");
			var sqid = jQuery("#sqid").val();
			var splc = jQuery("#splc").val();
			var shid = jQuery("#shid").val();
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid="+sqid+"&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid="+splc+"&shid="+shid);
		});
	
		function saveShzt(){
			var shyj = jQuery("#shyj").val();
			if (jQuery.trim(shyj) == ""){
				showAlertDivLayer("请填写审核意见！");
				return false;
			}
			var url = "rcswBxglBxbxsh.do?method=bxDgsh&type=save";
			ajaxSubFormWithFun("BxbxshForm",url,function(data){
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
			});
		}
		
		</script>
	</head>
<body>
	<html:form action="/rcswBxglBxbxsh" method="post" styleId="BxbxshForm">
		<html:hidden property="sqid" styleId="sqid"/>
		<html:hidden property="splc" styleId="splc"/>
		<html:hidden property="shid" styleId="shid"/>
		<html:hidden property="xh" styleId="xh"/>
		
		<div style="height:460px;overflow-x:hidden;overflow-y:auto;">
			<table width="100%" border="0" class="formlist" >
				<thead>
					<tr>
						<th colspan="4">
							<span>学生基本信息</span>
						</th>
					</tr>
				</thead>
				<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp"%>
			</table>
			<div class="" id="content" style="margin-top: 5px; overflow-x:hidden;" >
			</div>
		    <table width="100%" border="0" class="formlist">
				<tr>
					<th align="right" width="15%">
						附件信息
					</th>
					<td colspan="3">
						<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
						<html:hidden property="filepath" styleId="filepath" value="${model.filepath}"/>
							<script type="text/javascript">
								//调用附件 
								jQuery(function(){
									var gid = jQuery('#filepath').val();
									jQuery.MultiUploader_q({
										gid : gid
										});
								});
							</script> 
					</td>
				</tr>
			</table>
			<table width="100%" border="0" class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>审批信息</span>
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
					<font color="red">(限200字)</font>
				</th>
				<td colspan="3">
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=bxbx&id=shyj" />
					<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
				</td>
			</tr>
			</tbody>
			</table>
		</div>
		</td>
		</tr>
		</table>
		</div>
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
		</html:form>
</body>
</html>
