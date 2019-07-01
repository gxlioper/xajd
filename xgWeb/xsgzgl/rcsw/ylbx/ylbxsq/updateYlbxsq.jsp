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
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script	type="text/javascript">
			function saveForm(type){
				if(!zdybdCheck()){
					return false;
				}

				var zd21 = jQuery("#zd21").val();
				var zd22 = jQuery("#zd22").val();

				if (zd21 > zd22){
					showAlert("保险开始时间不能晚于保险结束时间！");
					return false;
				}
				var url = "rcsw_ylbx_ylbxsqgl.do?method=updateYlbxsq&type="+type;
		      	ajaxSubFormWithFun("ylbxsqForm",url,function(data){
		    	 if(data["message"]=="保存成功！" || data["message"]=="提交成功！"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    	 }});
		  	}
			function onShow(gndm) {
				var url = "rcsw_ylbx_ylbxsqgl.do?method=ylbxsq";
				jQuery.ajax( {
					type : "post",
					async : false,
					url : url,
					data : {
						sqid : jQuery("#sqid").val()
					},
					dataType : "json",
					success : function(data) {
						zdybdInit(gndm, data);
					}
				});
			}
			jQuery(function(){
				onShow("rcsw_ylbx_update");
				// ========== 附件 < ============
				var filepathtdV = jQuery("#filepathtd").val();
				jQuery("#filepath").val(filepathtdV);
				var filepathHiddenHtml = jQuery("#filepathHiddenDiv").html();
				jQuery("td[name='zdybdcon_td_filepathtd']").eq(0).html(filepathHiddenHtml);
				jQuery("#filepathHiddenDiv").html("");
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
				// ========== 附件 > ============
				var xxdm = jQuery("#xxdm").val();
				//江西航空职业技术学院
				if(xxdm == "13871"){
					sfbl();
					jQuery("#zd1").change(function (){
						sfbl();
					});
				}
			});
			
			function sfbl(){
				if(jQuery("#zd1").val()=="自愿办理"){
					jQuery("#zd4").hide();
					jQuery("#zd4").val("");
					jQuery("[name='zdybdcon_th_zd4']").eq(0).parent().hide();
					jQuery("#zd3").show();
					jQuery("[name='zdybdcon_th_zd3']").html("<span class='red'>*</span>申办时间");
				}else if(jQuery("#zd1").val()=="不办理"){
					jQuery("#zd3").hide();
					jQuery("#zd3").val("");
					jQuery("[name='zdybdcon_th_zd3']").text("");
					jQuery("#zd4").show();
					jQuery("[name='zdybdcon_th_zd4']").eq(0).parent().show();
				}
			}
		</script>
	</head>
	<body>
		<html:form action="/rcsw_ylbx_ylbxsqgl" method="post" styleId="ylbxsqForm">
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }"/>
			<div id="filepathHiddenDiv" style="display: none;">
				<html:hidden property="filepath" styleId="filepath" />
				<input type="file" id="filepath_f" name="filepath" />
			</div>
			<html:hidden property="sqid"  styleId="sqid"/>
			<html:hidden property="xh" styleId="xh" />
			<div style='width:100%;height:434px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist" >
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/ylbx/comm/viewStudent.jsp" %>
				</table>
				<div class=""  id="content" style="margin-top: 5px; overflow-x:hidden;" >
				</div>
			</div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm('save');">
										保存草稿
									</button>
									
									<button type="button" type="button" onclick="saveForm('submit');">
										提交申请
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

