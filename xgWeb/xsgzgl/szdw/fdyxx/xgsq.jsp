<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/fdyxx/js/fdyxxEdit.js"></script>
		<script language="javascript" src="js/xsgzgl/szdw/dwwh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
    	<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
   		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript">
		function calAge(){
			jQuery("#age").val( calculateAges(jQuery("#csrq").val().substr(0,4)+'-'+jQuery("#csrq").val().substr(5,2)+'-'+jQuery("#csrq").val().substr(8,2)));
		}
		
			jQuery(function() {
				onShow("xg");
				setXgzd("xg");// 设置修改字段
				jQuery("#zgh").attr("readonly","readonly");

				if(jQuery("#xxdm").val() == '11318'){
					jQuery("#zdybdcon_table_szdw_fdyxx_qtxx").css("margin-bottom", "0px");
					jQuery("#gzjl").css("margin-bottom", "0px");
					onChange();
					jQuery("#kzzd16").change(function() {
						onChange();
					});
				}

				if(jQuery("#xxdm").val() == '10704'){
					jQuery("#age").attr('readonly','readonly')
					jQuery("#age").bind('click',function(){
						calAge();
					})
				}

				
				/**华东交通大学理工学院*/
				if("13431" == jQuery("#xxdm").val()){
					if(jQuery("input:radio[name='zgqk']:checked").val() == '兼职'){
						jQuery("th[name='zdybdcon_th_zhuanzhi'] ").html("<span class='red'>*</span>专职");
					}else{
						jQuery("th[name='zdybdcon_th_zhuanzhi']").remove();
						jQuery("td[name='zdybdcon_td_zhuanzhi']").remove();
						jQuery("#zdybdcon_table_szdw_fdyxx_gzxx> tbody").append("<input type='hidden' name='zhuanzhi' value='' id='zhuanzhi'>");
					};
					
				jQuery("input:radio[name='zgqk']").change(function(){
					if(this.value == '兼职'){
						jQuery("#zhuanzhi").remove();
						jQuery("td[name='zdybdcon_td_zgqk']").parent().append("<th name='zdybdcon_th_zhuanzhi'><span class='red'>*</span>专职</th>" +
								"<td name='zdybdcon_th_zhuanzhi'>" +
									"<select id='zhuanzhi'>" +
										"<option value='学工处处长'>学工处处长</option>" +
										"<option value='学工处副处长'>学工处副处长</option>" +
										"<option value='团委书记'>团委书记</option>" +
										"<option value='学工干事'>学工干事</option>" +
										"<option value='分管学生工作负责人'>分管学生工作负责人</option>" +
										"<option value='分院学生干事'>分院学生干事</option>" +
										"<option value='辅导员'>辅导员</option>" +
									"<select>" +
								"</td>");
					}else{
						jQuery("th[name='zdybdcon_th_zhuanzhi']").remove();
						jQuery("td[name='zdybdcon_td_zhuanzhi']").remove();
						jQuery("#zhuanzhi").remove();
						jQuery("#zdybdcon_table_szdw_fdyxx_gzxx> tbody").append("<input type='hidden' name='zhuanzhi' value='' id='zhuanzhi' class='text_nor'>");
					}
				});
				}

				/*获取到画面打开初期值，与修改值对比是否有修改，来判断是否提交*/
				/*这三个字段不是自定义表单配置的，所以都是单独处理的*/
				jQuery("#bzbbzBefore").val(jQuery("#bzbbz").val());
                jQuery("#drsjBefore").val(jQuery("#drsj").val());
                jQuery("#lxbmBefore").val(jQuery("#lxbm").val());
			});
		</script>
	</head>
	<body >	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
	
		<logic:equal value="1" name="cssz" property="sqkg">
			<logic:equal value="5" name="shxxRs" property="shjg">
				<div class="prompt" >
					<h3><span>提示：</span> </h3>
					<p>您的修改申请正在审核中。</p>
				</div>
			</logic:equal>
			<logic:equal value="3" name="shxxRs" property="shjg">
				<div class="prompt" >
					<h3><span>提示：</span> </h3>
					<p>您的修改申请<font color='red'>已退回</font>！<br/>审核意见：${shxxRs.shyj}</p>
				</div>
			</logic:equal>
			<logic:equal value="2" name="shxxRs" property="shjg">
				<div class="prompt" >
					<h3><span>提示：</span> </h3>
					<p>您的修改申请<font color='red'>不通过</font>！<br/>审核意见：${shxxRs.shyj}</p>
				</div>
			</logic:equal>
		</logic:equal>
		
		<logic:notEqual value="1" name="cssz" property="sqkg">
			<div class="prompt" >
				<h3><span>提示：</span> </h3>
				<p>系统未开放辅导员信息修改。</p>
			</div>
		</logic:notEqual>
		
	
		<html:form action="/szdw_fdyxx" method="post" styleId="form1">
			<html:hidden property="zgh" styleId="zgh" value="${userName }"/>
			<input type="hidden" name="xxdm" id="xxdm"  value="${xxdm }"/>
			<html:hidden property="xgzdJson" styleId="xgzdJson"/>
			<input type="hidden" name="dshSqid" id="dshSqid" value='${dshSqid}'/>
			<input type="hidden" name="shzSqid" id="shzSqid" value='${shzSqid}'/>
			<input type="hidden" name="shjg" id ="shjg" value='${shxxRs.shjg}'/>
			<input type="hidden" name="shyj" id ="shyj" value='${shxxRs.shyj}'/>
			<input type="hidden" name="zpsfbt" id="zpsfbt" />
			<input type="hidden" name="zpsfcz" id="zpsfcz"  value="${zpsfcz }"/>
			<%--存非在编备注、担任"3+2"辅导员时间、留校部门修改前值--%>
			<input type="hidden" id="bzbbzBefore">
			<input type="hidden" id="drsjBefore">
			<input type="hidden" id="lxbmBefore">
			<%--存非在编备注、担任"3+2"辅导员时间、留校部门修改前值--%>
			<div class="demo_fdyxx"  id="content">
			
			</div>
			<logic:equal value="11318" name="xxdm">
				<div id="fjHidDiv" style="display: none;">
					<table width="100%" border="0" style="margin-bottom: 5px;" class="formlist">
					<tbody>
						<tr><th width="15%" >附件信息<br/>
							<font color="red">与上述项相关的附件</font> </th>
							<td width="85%" colspan="3">
							    <html:hidden name = "fdyxxModel"  property="kzzd19" styleId="kzzd19" />
							        <%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
								        <script type="text/javascript">
											//调用附件 
											jQuery(function(){
												jQuery.MultiUploader({
													maxcount : 5,
													//后缀
													accept : 'png|gif|jpg|zip|rar|doc|docx',
													//最大文件大小 单位M
													maxsize: 10,
													//存放附件的隐藏域的id
													elementid : 'kzzd19'
													});
											});
										</script>
							 </td>
						 </tr>
					 </tbody>
					</table>
				</div>
			</logic:equal>
			<logic:equal value="10704" name="xxdm">
				<div id="fjHidDiv">
					<table width="100%" border="0" style="margin-bottom: 5px;" class="formlist">
					<tbody>
						<tr><th width="15%" >附件信息<br/>
							<font color="red">与上述项相关的附件</font> </th>
							<td width="85%" colspan="3">
							    <html:hidden name = "fdyxxModel"  property="kzzd19" styleId="kzzd19" />
							        <%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
								        <script type="text/javascript">
											//调用附件 
											jQuery(function(){
												jQuery.MultiUploader({
													maxcount : 5,
													//后缀
													accept : 'png|gif|jpg|zip|rar|doc|docx',
													//最大文件大小 单位M
													maxsize: 10,
													//存放附件的隐藏域的id
													elementid : 'kzzd19'
													});
											});
										</script>
							 </td>
						 </tr>
					 </tbody>
					</table>
				</div>
			</logic:equal>
			<logic:notEqual value="5" name="shxxRs" property="shjg">
				<logic:equal value="1" name="cssz" property="sqkg">		
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="5">
								<div class="bz">"<span class="red">*</span>"为必填项  </div>
								<div class="btn">
									<button name="保存" id="buttonSave" type="button" onclick="save();">
										保存草稿
									</button>
									<button name="提交" id="buttonSave" type="button" onclick="saveAndSubmit();">
										提交申请
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				</logic:equal>
			</logic:notEqual>
			<div id="zpHidDiv" style="display: none;">		
				<div align="center">
					<img src="teaPic.jsp?zgh=${userName }" style="height:133px;width:100px;" border="0"   id="zhaopian"/>
				</div>
				<logic:notEqual value="5" name="shxxRs" property="shjg">	
					<logic:equal value="1" name="cssz" property="sqkg">	
						<div align="center">
							<button type="button" onclick="showZpscDiv();" style="width:100px" id="buttonSave">上传照片</button>
						</div>
					</logic:equal>
				</logic:notEqual>
			</div>	
		</html:form>
	</body>
</html>

