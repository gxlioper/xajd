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
					showAlert("��ѡ����ѯ��飡");
					return false;
				}	
				var zxzt = jQuery("#zxzt").val();
				if (jQuery.trim(zxzt) == ""){
					showAlert("��ѯ���ⲻ��Ϊ�գ�");
					return false;
				}
				var zxnr = jQuery("#zxnr").val();
				if (jQuery.trim(zxnr) == ""){
					showAlert("��ѯ���ݲ���Ϊ�գ�");
					return false;
				}
				var url = "rcsw_zxzx_xszxzxgl.do?method=addXszxzx&type="+type;
		      	ajaxSubFormWithFun("xszxzxForm",url,function(data){
		    	 if(data["message"]=="����ɹ���"){
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
		<html:form action="/rcsw_zxzx_xszxzxgl" method="post" styleId="xszxzxForm" onsubmit="return false;">
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:205px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��ѯ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="13%"><font class="red">*</font>��ѯ���</th>
							<td width="">
								<html:select property="bkid" styleId="bkid" style="width:250px;">
									<html:options collection="zxbkszList" property="bkid" labelProperty="bkmc" />
								</html:select>
							</td>
					    </tr>
						<tr>
							<th width=""><font class="red">*</font>��ѯ����</th>
							<td width="">
								<html:text property="zxzt" styleId="zxzt" maxlength="50" style="width:98%"/>
							</td>
					    </tr>
						<tr>
							<th width=""><font class="red">*</font>��ѯ����<br /><font color="red">&lt;��500��&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='zxnr' style="width:98%" styleId="zxnr" rows='5' onblur="checkLen(this,500);"/>
							</td>
					    </tr>
					</tbody>
				</table>
			</div>
		
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
									<div class="bz">"<span class="red">*</span>"Ϊ������</div>
									<div class="btn">
										<button type="button" type="button" onclick="saveForm('save');">
											����
										</button>
										<button type="button" type="button" onclick="iFClose();">
											�� ��
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

