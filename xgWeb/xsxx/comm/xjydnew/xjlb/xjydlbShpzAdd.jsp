<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsxx/comm/xjydnew/js/xjydlb.js"></script>
		<script type="text/javascript">
		
		function saveForm(){	
			if(!check("xjlbdm-shlcid")){
				return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
			}
			  
		     var url = "xjyd.do?method=xjydlbShpzAdd&type=save";
		      ajaxSubFormWithFun("xjydForm",url,function(data){
		    	 if(data["message"]=="����ɹ���"){
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

		jQuery(function() {
			jQuery("[name=sftjbj]")[1].checked="true";
			jQuery("[name=lrqzsj]")[1].checked="true";
			jQuery("[name=sfksq]")[1].checked="true";
			jQuery("#sqsj").hide();
			jQuery("[name=sfksq]").bind("click",function(){
				var val=jQuery(this).val();
				if(val=="0"){
					jQuery("#sqsj").show();
				}else{
					jQuery("#sqsj").hide();
				}
			});
		});

		function initShow(){
			var xjlbdm = jQuery("#xjlbdm").val();
			if(xjlbdm == ""){
				jQuery("#sfyxj").html("");
				jQuery("#sfzx").html("");
			}else{
				jQuery.post("xjyd.do?method=xjydlbGet",{values:xjlbdm},function(data){
					if(data != null){
						jQuery("#sfyxj").html(data["sfyxjmc"]);
						jQuery("#sfzx").html(data["sfzxmc"]);
					}
				},'json');
			}
		}
		</script>
	</head>
	<body>
		<html:form action="/xjyd" method="post" styleId="xjydForm" onsubmit="return false;">
			<div class="tab">
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" name="�� ��" onclick="saveForm();return false;">
										�� ��
									</button>
									<button type="button" name="�� ��" onclick="iFClose();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<td colspan="4">
								<span>
									ѧ���춯����������ά��
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<span class="red">*</span>ѧ���춯���
							</th>
							<td colspan="3">
								<html:select property="xjlbdm" styleId="xjlbdm" onchange="initShow();">
									<html:option value=""></html:option>
									<html:options collection="xjlbList" property="xjlbdm"
										labelProperty="xjlbmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th style="width:30%">
								�Ƿ���ѧ��
							</th>
							<td style="width:30%" id="sfyxj">&nbsp;
							</td>
							<th style="width:20%">
								�Ƿ���У
							</th>
							<td id="sfzx" style="width:20%">&nbsp;</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>�������
							</th>
							<td colspan="3">
								<html:select property="shlcid" styleId="shlcid">
									<html:option value=""></html:option>
									<html:options collection="shlcList" property="splc"
										labelProperty="lcxx" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								�����༶
							</th>
							<td colspan="3">
								<html:radio property="sftjbj" value="0" styleId="sftjbj1"><label style='cursor:pointer'  for="sftjbj1">��Ҫ</label></html:radio>		
								<html:radio property="sftjbj" value="1" styleId="sftjbj2"><label style='cursor:pointer'  for="sftjbj2">����Ҫ</label></html:radio>
							</td>
						</tr>
						<tr>
							<th>
								¼����ֹʱ��
							</th>
							<td colspan="3">
								<html:radio property="lrqzsj" value="1" styleId="lrqzsj1"><label style='cursor:pointer'  for="lrqzsj1">��Ҫ</label></html:radio>		
								<html:radio property="lrqzsj" value="0" styleId="lrqzsj2"><label style='cursor:pointer'  for="lrqzsj2">����Ҫ</label></html:radio>
							</td>
						</tr>
						
						<logic:equal name="xxdm" value="10511">
							<tr>
								<th>
									ѧ��
								</th>
								<td colspan="3">
									<html:radio property="xzsfkq" value="1" styleId="xzsfkq1"><label style='cursor:pointer'  for="xzsfkq1">��Ҫ</label></html:radio>		
									<html:radio property="xzsfkq" value="0" styleId="xzsfkq2"><label style='cursor:pointer'  for="xzsfkq2">����Ҫ</label></html:radio>
								</td>
							</tr>	
							<tr>
								<th>
									��ԴѧУ/ȥ��ѧУ
								</th>
								<td colspan="3">
									<html:radio property="xxsfkq" value="1" styleId="xxsfkq1"><label style='cursor:pointer'  for="xxsfkq1">��Ҫ</label></html:radio>		
									<html:radio property="xxsfkq" value="0" styleId="xxsfkq2"><label style='cursor:pointer'  for="xxsfkq2">����Ҫ</label></html:radio>
								</td>
							</tr>
						</logic:equal>
						
						
						<tr>
							<th>
								�����뿪��
							</th>
							<td colspan="3">
								<html:radio property="sfksq" value="0" styleId="sfksq1"><label style='cursor:pointer'  for="sfksq1">����</label></html:radio>		
								<html:radio property="sfksq" value="1" styleId="sfksq2"><label style='cursor:pointer'  for="sfksq2">�ر�</label></html:radio>
							</td>
						</tr>
						<tr id="sqsj">
							<th>
								������ֹʱ��
							</th>
							<td colspan="3">
								<html:text property="sqkssj" styleId="sqkssj" style="width:80px" onfocus="return showCalendar(this.id,'yyyy-MM-dd',true,'sqjssj',130,1);" />
								~
								<html:text property="sqjssj" styleId="sqjssj" style="width:80px" onfocus="return showCalendar(this.id,'yyyy-MM-dd',false,'sqkssj',225,1);" />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>
