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
		var bzMsg = '填写是否寝室长，港澳台，国防生等'; // 备注
		// 初始化提示信息
		function initData(id, msg){	
			jQuery("#" + id).focus( function() {
				var v = jQuery.trim(jQuery(this).val());
				if (v == msg) {
					jQuery(this).val("");
					jQuery(this).css("color", "");
				}
			});
			jQuery("#" + id).blur( function() {
				var v = jQuery.trim(jQuery(this).val());
				if (v == "") {
					jQuery(this).val(msg);
					jQuery(this).css("color", "#999");
				}
			});
			jQuery("#" + id).blur();
		}
		//清空提示信息
		function clearData(id, msg){
			var obj = jQuery("#" + id);	
			var v = jQuery.trim(obj.val());
			if (v == msg) {
				obj.val("");
			}
		}
		jQuery(function(){
			initData('bz', bzMsg);
		});
		
		function saveForm(){
			clearData('bz', bzMsg);

			if (jQuery.trim(jQuery("#jg").val()) == "" ||
				jQuery.trim(jQuery("#lxdh").val()) == "" ||
				jQuery.trim(jQuery("#dh").val()) == "" ||
				jQuery.trim(jQuery("#bzrxm").val()) == "" ||
				jQuery.trim(jQuery("#fdyxm").val()) == "" ||
				jQuery.trim(jQuery("#bzrlxdh").val()) == "" ||
				jQuery.trim(jQuery("#fdylxdh").val()) == "" ||
				jQuery.trim(jQuery("#bzryx").val()) == "" ||
				jQuery.trim(jQuery("#fdyyx").val()) == ""){
				showAlert("请将必填项填写完整。");
				return false;
			}
			var bz = jQuery("#bz").val();
			if(bz.length > 200){
				alertError("备注只能输入200个字");
				return false;
			}
			
			var	 url = "xszyXsxxgl.do?method=updateXszyXsxx&type=update";
		     ajaxSubFormWithFun("xszyXsxxForm",url,function(data){
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
		<html:form action="/xszyXsxxgl" method="post" styleId="xszyXsxxForm">
			<html:hidden property="xh" styleId="xh" />
			<div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>新生基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th width="20%">学号</th>
							<td width="30%">
								${rs.xh }
							</td>
							<th width="20%">姓名</th>
							<td width="30%">
								${rs.xm }
							</td>
						</tr>
					    <tr>
							<th width="20%">性别</th>
							<td width="30%">
								${rs.xb }
							</td>
							<th width="20%">年级</th>
							<td width="30%">
								${rs.nj }
							</td>
						</tr>
					    <tr>
							<th width="20%">大类</th>
							<td width="30%">
								${rs.dl }
							</td>
							<th width="20%">班级</th>
							<td width="30%">
								${rs.bjmc }
							</td>
						</tr>
					    <tr>
							<th width="20%"><span class="red">*</span>籍贯</th>
							<td width="30%" colspan="3" id="td_jg">
								<html:text property="jg" styleId="jg" maxlength="100" style="width:300px"></html:text>
							</td>
						</tr>
					    <tr>
							<th width="20%"><span class="red">*</span>联系电话</th>
							<td width="30%">
								<html:text property="lxdh" styleId="lxdh" maxlength="13" onkeyup="value=value.replace(/[^\d-]/ig,'')"></html:text>
							</td>
							<th width="20%"><span class="red">*</span>短号</th>
							<td width="30%">
								<html:text property="dh" styleId="dh" maxlength="10" onkeyup="value=value.replace(/[^\d]/ig,'')"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%"><span class="red">*</span>民族</th>
							<td width="30%">
								<html:select  property="mzdm" styleId="mzdm" style="width:130px">
									<html:options collection="mzList" labelProperty="mc" property="dm"/>
								</html:select>
							</td>
							<th width="20%">邮箱</th>
							<td width="30%">
								<html:text property="dzyx" styleId="dzyx" maxlength="30"></html:text>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>其他信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th width="20%"><span class="red">*</span>班主任姓名</th>
							<td width="30%">
								<html:text property="bzrxm" styleId="bzrxm" maxlength="10"></html:text>
							</td>
							<th width="20%"><span class="red">*</span>辅导员姓名</th>
							<td width="30%">
								<html:text property="fdyxm" styleId="fdyxm" maxlength="10"></html:text>
							</td>
						</tr>
					    <tr>
							<th width="20%"><span class="red">*</span>班主任联系电话</th>
							<td width="30%">
								<html:text property="bzrlxdh" styleId="bzrlxdh" maxlength="13" onkeyup="value=value.replace(/[^\d-]/ig,'')"></html:text>
							</td>
							<th width="20%"><span class="red">*</span>辅导员联系电话</th>
							<td width="30%">
								<html:text property="fdylxdh" styleId="fdylxdh" maxlength="13" onkeyup="value=value.replace(/[^\d-]/ig,'')"></html:text>
							</td>
						</tr>
					    <tr>
							<th width="20%"><span class="red">*</span>班主任邮箱</th>
							<td width="30%">
								<html:text property="bzryx" styleId="bzryx" maxlength="30"></html:text>
							</td>
							<th width="20%"><span class="red">*</span>辅导员邮箱</th>
							<td width="30%">
								<html:text property="fdyyx" styleId="fdyyx" maxlength="30"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								备注
								<br/><font color="red">限200个字</font>
							</th>
							<td align="left" colspan="3">
								<html:textarea property="bz" styleId="bz" rows="3" cols="65"></html:textarea>
							</td>
			      		</tr>
					</tbody>
				</table>
			</div>
			<div style="height:30px"></div>
					<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
										保 存
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

