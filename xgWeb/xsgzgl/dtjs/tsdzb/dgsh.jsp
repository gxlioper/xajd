<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript">
	function saveSh(){
		if (jQuery("#shzt").val() == "" || jQuery("#shzt").val() == null){
			showAlert("�뽫��������д������");
			return false;
		}
		if((jQuery("#shzt").val() == '1' || jQuery("#shzt").val() == 1) && (jQuery("#pf").val() == null || jQuery("#pf").val() == '')){
			showAlert("�뽫��������д������");
			return false;
		}
		var url = "dtjs_tsdzbsh.do?method=saveSh&type=sh";
		ajaxSubFormWithFun("tsdzbForm",url,function(data){
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

	function xs(object){
		if(object.value == '1'){
			jQuery("#pfdiv").css("display","");
			jQuery("#pf").attr("disabled",false);
		}else{
			jQuery("#pf").attr("disabled","disabled");
			jQuery("#pfdiv").css("display","none");
		}
	}	
	</script>
</head>
<body>
	<html:form action="/dtjs_tsdzbsh" method="post" styleId="tsdzbForm">
		<html:hidden property="dzbid"/>
		<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>			
				<table width="100%" border="0" class="formlist">
				<thead>
						<tr>
							<th colspan="4">
								<span>��֧����Ϣ</span>
							</th>
						</tr>
				</thead>
					<tbody>
						<tr>
							<th width="20%">
								��֧������
							</th>
							<td width="30%">
								${tsdzbForm.dzbmc}
							</td>
							</td>
							<th>
								������
							</th>
							<td>
								${tsdzbForm.fzr}
							</td>
						</tr>
						<tr>
							<th width="20%">
								��ϵ��ʽ
							</th>
							<td width="30%">
								${tsdzbForm.lxfs}
							</td>
							</td>
							<th>������</th>
							<td>
								${tsdzbForm.cjr}
							</td>
						</tr>
						<tr>
							<th width="20%">
								����ʱ��
							</th>
							<td colspan="3">
								${tsdzbForm.cjsj}
							</td>
						</tr>
						<tr>
							<th>
								��Ͻ�༶
							</th>							
							<td colspan="3">
								${tsdzbForm.bjxx}
							</td>						
							</tr>
						<tr>
							<th>
								��ע
							</th>
							<td colspan="3">
								${tsdzbForm.bz}
							</td>
			      		</tr>						
				</tbody>
				<tbody>
				<tr>						
					<th>
						<span><font color="red">*</font></span>��˽��
					</th>
					<td colspan="3">
						<select name="shzt" id="shzt" onchange="xs(this);return false;">
							<option>
								---ѡ��---
							</option>
							<option value="1">
								ͨ��
							</option>
							<option value="2">
								��ͨ��
							</option>
						</select>
					</td>	
				</tr>
				<tr style="display: none" id="pfdiv">						
					<th>
						<span><font color="red">*</font></span>����
					</th>
					<td colspan="3">
						<input type="text" name="pf" id="pf" style="width: 300px" onblur="checkZs(this)" disabled="disabled"/>
					</td>	
				</tr>
				<tr>
					<th width="20%">
						������
						<br />
						<font color="red">(��200��)</font>
					</th>
					<td colspan="3">
						<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
					</td>
				</tr>
				</tbody>
				</table>
		</div>
		<div style="height: 30px"></div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="����"  onclick="saveSh();return false;">
									�� ��
								</button>
								<button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
		<!-- ��ʾ��Ϣ -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
</body>
</html>
