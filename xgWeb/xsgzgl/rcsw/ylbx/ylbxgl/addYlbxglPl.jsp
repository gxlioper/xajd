<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		
		<script	type="text/javascript">
		jQuery(function(){
			zdybdInit("rcsw_ylbx_add");
			jQuery("#xn").val(jQuery("#dqxn").val());

			// 批量增加操作说明
			var czsmHtml = '<TR><TH width="15%">批量增加操作说明</TH>';
			czsmHtml += '<TD width="35%" colspan="3">当前共选中&nbsp;<font color="#ff0000;">'+ jQuery("#xsnum").val() +'</font>&nbsp;位学生</TD></TR>';
			var xnTdTemp = jQuery("td[name=zdybdcon_td_zd23]").eq(0);
			xnTdTemp.parent().before(czsmHtml);

			// ===== 获取高级查询条件，并隐藏，便于【根据查询结果进行批量增加】使用 begin========
			var api = frameElement.api, W = api.opener;
			jQuery("#cxtjPlHidden").html(jQuery("div[class=toolbox]", W.document).eq(0).html());
			jQuery("div[class=search_advanced]").hide();
			jQuery("div[class=more--item_bottom]").hide();
			jQuery("#comp_title").hide(); // 页签按钮隐藏
			jQuery("#searchTjDiv").hide();
			jQuery("div[class=buttonbox]").hide();
			// ===== 获取高级查询条件，并隐藏，便于【根据查询结果进行批量增加】使用 end========
		});
		
		function saveForm(){
			if(!zdybdCheck()){
				return false;
			}
			
			// 获取高级查询条件，并隐藏，便于【根据勾选记录进行批量增加】使用
			var api = frameElement.api, W = api.opener;
			var xhPlHidden = jQuery("#xhPlHidden", W.document).val();
			jQuery("#xh").val(xhPlHidden);
			var ylbxzt = jQuery("#ylbxzt").val();
			var	url = "rcsw_ylbx_ylbxglgl.do?method=addYlbxglPl&type=save&ylbxzt=" + ylbxzt;
			// 如果xh为空，那么【根据查询结果进行批量增加】
			if(xhPlHidden == ""){
				setSearchTj();//设置高级查询条件
				url = addSuperSearchParams(url);//设置高级查询参数
			}
		     ajaxSubFormWithFun("ylbxglForm",url,function(data){
		    	 if(data["message"]=="保存成功！"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    	 }
				});
		  }
		</script>
	</head>
	<body>
		<html:form action="/rcsw_ylbx_ylbxglgl" method="post" styleId="ylbxglForm" onsubmit="return false">
			<div id="cxtjPlHidden" style="height: 0px;"></div>
			<input type="hidden" id="dqxn" value="${dqxn }" />
			<html:hidden property="xh" styleId="xh" />
			<html:hidden property="xsnum" styleId="xsnum" />
			<input type="hidden" value="djz" id="ylbxzt"/>
			<div style='width:100%;height:295px;overflow-x:hidden;overflow-y:auto;'>
				<div class=""  id="content" style="margin-top: 5px; overflow-x:hidden;" >
				</div>
			</div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
										保  存
									</button>
									<button type="button" type="button" onclick="iFClose();">
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

