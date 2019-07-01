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
		<script	type="text/javascript">
			function saveForm(type){
				var bkid = jQuery("#bkid").val();
				if (jQuery.trim(bkid) == ""){
					showAlert("请选择咨询版块！");
					return false;
				}	
				var sfzdLen = jQuery("[name=sfzd]:checked").length;
				if(sfzdLen==0){
					showAlert("请选择是否置顶！");
					return false;
				}
				var zxzt = jQuery("#zxzt").val();
				if (jQuery.trim(zxzt) == ""){
					showAlert("咨询主题不能为空！");
					return false;
				}
				var zxnr = jQuery("#zxnr").val();
				if (jQuery.trim(zxnr) == ""){
					showAlert("咨询内容不能为空！");
					return false;
				}
				var hfnr = jQuery("#hfnr").val();
				if (jQuery.trim(hfnr) == ""){
					showAlert("回复内容不能为空！");
					return false;
				}
				var url = "rcsw_zxzx_cjwtszgl.do?method=addCjwtsz&type="+type;
		      	ajaxSubFormWithFun("cjwtszForm",url,function(data){
		    	 if(data["message"]=="保存成功！"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    	 }});
		  	}
			jQuery(function(){
			});
		</script>
	</head>
	<body>
		<html:form action="/rcsw_zxzx_cjwtszgl" method="post" styleId="cjwtszForm">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>咨询信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="13%"><font class="red">*</font>咨询版块</th>
							<td width="">
								<html:select property="bkid" styleId="bkid" style="width:250px;">
									<html:options collection="zxbkszList" property="bkid" labelProperty="bkmc" />
								</html:select>
							</td>
					    </tr>
						<tr>
							<th width=""><font class="red">*</font>是否置顶</th>
							<td width="">
								<logic:iterate id="o" name="isnotList" >
									<html:radio property="sfzd" value="${o.dm}">${o.mc}</html:radio>
								</logic:iterate>
							</td>
					    </tr>
						<tr>
							<th width=""><font class="red">*</font>咨询主题</th>
							<td width="">
								<html:text property="zxzt" styleId="zxzt" maxlength="50" style="width:98%"/>
							</td>
					    </tr>
						<tr>
							<th width=""><font class="red">*</font>咨询内容<br /><font color="red">&lt;限500字&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='zxnr' style="width:98%" styleId="zxnr" rows='5' onblur="checkLen(this,500);"/>
							</td>
					    </tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>回复信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width=""><font class="red">*</font>回复内容<br /><font color="red">&lt;限500字&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='hfnr' style="width:98%" styleId="hfnr" rows='5' onblur="checkLen(this,500);"/>
							</td>
					    </tr>
					</tbody>
				 </table>
				</div>
				<div>
					<table width="100%" border="0" class="formlist">
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="bz">"<span class="red">*</span>"为必填项</div>
									<div class="btn">
										<button type="button" type="button" onclick="saveForm('save');">
											保存
										</button>
										<button type="button" type="button" onclick="iFClose();">
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

