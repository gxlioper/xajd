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
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
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
			onShow("rcsw_ylbx_query");
			var sqid = jQuery("#sqid").val();
			var splc = jQuery("#splc").val();
			var shid = jQuery("#shid").val();
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid="+sqid+"&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid="+splc+"&shid="+shid);
			// ========== 附件 < ============
			var filepathtd = jQuery("td[name='zdybdcon_td_filepathtd']").eq(0);
			if(filepathtd.html() != null) {
				var filepathtdV = jQuery.trim(filepathtd.html().replace('&nbsp;','')); // 先保存
				var filepathHiddenHtml = jQuery("#filepathHiddenDiv").html();
				filepathtd.html(filepathHiddenHtml);
				jQuery("#filepathHiddenDiv").html("");
				jQuery.MultiUploader_q({
					gid : filepathtdV,
					targetEl : 'commonfileupload-list-0'
				});	
			}
			// ========== 附件 > ============
			var xxdm = jQuery("#xxdm").val();
			//江西航空职业技术学院
			if(xxdm == "13871"){
				sfblview();
			}
		});
	
		function saveShzt(){
			var shyj = jQuery("#shyj").val();
			var ybh = jQuery("#zd30").val();

			if("13573" == jQuery("#xxdm").val()) {	
				if (jQuery.trim(shyj) == "" || jQuery.trim(ybh) == ""){
					showAlertDivLayer("必填项不能为空！");
					return false;
				}
			}else {
				if (jQuery.trim(shyj) == ""){
					showAlertDivLayer("请填写审核意见！");
					return false;
				}
			}
	
			var url = "rcsw_ylbx_ylbxshgl.do?method=ylbxDgsh&type=save";
			ajaxSubFormWithFun("ylbxshForm",url,function(data){
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
			});
		}
		
		function sfblview(){
			var sfbl=jQuery("[name='zdybdcon_td_zd1']").html().trim().replace(/&nbsp;/gi,'');
			if(sfbl=="自愿办理"){
				jQuery("[name='zdybdcon_th_zd4']").eq(0).parent().hide();
			}else	{
				jQuery("[name='zdybdcon_th_zd3']").text("");
				jQuery("[name='zdybdcon_th_zd4']").eq(0).parent().show();
			}
		}
		
		</script>
	</head>
<body>
	<html:form action="/rcsw_ylbx_ylbxshgl" method="post" styleId="ylbxshForm">
		<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }"/>
		<div id="filepathHiddenDiv" style="display: none;">
			<div id="commonfileupload-list-0" style="padding: 5px;"></div>
		</div>
		<html:hidden property="sqid" styleId="sqid"/>
		<html:hidden property="splc" styleId="splc"/>
		<html:hidden property="shid" styleId="shid"/>
		<html:hidden property="xh" styleId="xh"/>
		<html:hidden property="xn" styleId="xn"/>
		<input type="hidden" id="xxdm" value="${xxdm}" />
		
		<div style="height:460px;overflow-x:hidden;overflow-y:auto;">
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
			<div class="tab"  id="content" style="margin-top: 5px; overflow-x:hidden;" >
			</div>
			<table width="100%" border="0" class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>审核历史</span>
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
			<logic:equal name="xxdm" value="13573">
				<tr>
					<tr>
						<th>
							<font color="red">*</font>医保号
						</th>
						<td colspan="3">
							<html:text property="zd30" styleId="zd30" maxlength="30" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
						</td>
					</tr>
				</tr>
			</logic:equal>
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
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=ylbx&id=shyj" />
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
		<table width="100%" border="0" class="formlist">	
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
		<!-- 提示信息 -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
</body>
</html>
