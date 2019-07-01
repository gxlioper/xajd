<%@ page language="java" contentType="text/html; charset=GBK"%>
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
		<script type="text/javascript" src="xsgzgl/rcsw/bxgl/bxbxsq/js/bxbxsq.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			//字段自定义表单加载 
			zdybdInit("rcsw_bxbx_add");
			//江西航空职业技术学院
			if(jQuery("#xxdm").val() == "13871"){
				jQuery("#bxje").attr("readonly","readonly");
				jQuery("#ylzd1").focus(function(){
					if(jQuery("#xh").val() == ""){
						return showAlert("请先选学生！");
					}
				});
				jQuery("#ylzd1").change(function(){
					jspd(this.value);
				})
				jQuery("#csfysj").removeAttr("onfocus");
				jQuery("#csfysj").bind("focus", function () {
				　　　　　　WdatePicker({
			　　　　　　　　　　dateFmt: 'yyyy-MM-dd',
			　　　　　　　　　　maxDate: '2033-01-01',
			　　　　　　　　　　minDate: '2012-01-01',
			　　　　　　　　　　onpicked: function (dp) { jspd(jQuery("#ylzd1").val()); }
			　　　　　　});
			　　});
			}
		});
		function getZe(xh,csfysj){
			var url = "rcswBxglBxbxsq.do?method=getZe";
			var ze = 0;
			jQuery.ajax({
			type:'post',
			url:url,
			dataType:'text',
			contentType:"application/x-www-form-urlencoded; charset=UTF-8",
			data:{xh:xh,csfysj:csfysj},
			async: false,
			success:function(result){
				ze = result;
			 }
		    });
		    return ze;
		}
		function jspd(ylfy){
			if(ylfy != ""){
				var jsje = Math.round(parseInt(ylfy)*0.8);
				var bxje = jsje;
				if(jQuery("#bxxz").val() == "门诊报销"){
					var csfysj = jQuery("#csfysj").val();
					var xh = jQuery("#xh").val();
					if(csfysj != "" && xh != ""){
						var ze = parseInt(getZe(xh,csfysj)) || 0;
						var sx = parseInt(jQuery("#sx").val());
						if(ze+jsje > sx){
							if(ze < sx){
								bxje = sx - ze;
							}else{
								bxje = 0;
							}
							showAlert("年度门诊报销金额超出上限<font class='red'>"+sx+"</font>");
						}
					}else{
						return;
					}
				
				}
				jQuery("#bxje").val(bxje);
			}else{
				return;
			}
			
		}
		</script>	
	</head>
	<body>
		<html:form method="post" styleId="BxbxSqForm" action="/rcswBxglBxbxsq.do?method=bxbxsqZj"
			enctype="multipart/form-data">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }"/>
			<input type="hidden" name="sx" id="sx" value="${bxsx }"/>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/selectStudent.jsp"%>
					</table>
					<div class=""  id="content" style="margin-top: 5px; overflow-x:hidden;" >
				    </div>
				    <table width="100%" border="0" class="formlist">
					<tr>
							<th align="right" width="15%">
							<logic:equal value="13871" name="xxdm">
							<span class="red">*</span>
							</logic:equal>
								附件信息
							</th>
							<td colspan="3">
							<logic:equal value="13871" name="xxdm">
								<span style="color: red; line-height:20px;display:block;">
									请上传发票图片
								</span>
								&nbsp;
							</logic:equal>
								<html:hidden property="filepath" styleId="filepath"/>
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										jQuery.MultiUploader({
											maxcount : 3,
											//后缀
											accept : 'png|gif|jpg|zip|rar|doc|docx',
											//最大文件大小 单位M
											maxsize: 10,
											//存放附件的隐藏域的id
											elementid : 'filepath'
											});
									});
								</script>  
							</td>
						</tr>
					</table>
				  
			</div>
			<div style="height:35px"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button" onclick="saveForm('save');">
										保存草稿
									</button>
									<button type="button" onclick="saveForm('submit');">
										提交申请
									</button>
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
