<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function cshMm(){
				if(jQuery("#mm1").val() != jQuery("#mm2").val()){
					showAlert("ȷ�����벻һ�£�");
			    	return false;
				}
				
				if(!checkPsw(jQuery("#mm1").val()))
					return false;
					
				var url = "xsxx_tygl.do?method=yhmmCsh";
				var parameter = {};
				var array = new Array();
				var api = frameElement.api, W = api.opener;
				jQuery(W.document).find("input[name=primarykey_checkVal]:checked").each(function(i,n){	
					array.push(jQuery(n).val());
				});
				
				parameter["array_primarykey_checkVal"]=array.join('!!array!!');
				parameter["str_kl"]=jQuery("#mm1").val();
				confirmInfo("ȷ��Ҫ��ѡ�е��û��������ʼ����?",function(ok){
					if(ok=="ok"){
						jQuery.post(url,parameter,function(result){
							showAlert(result,{},{"clkFun":function(){
								refershParent();
							}});
						});					
					}
				});
			}
		</script>
	</head>

	<body>
		<table class="formlist">
			<thead>
				<tr>
					<th colspan="2">
						<span>�����ʼ��</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr height='30'>
					<th>
						<font color="red">*</font>������
					</th>
					<td>
						<input type="password" name="mm1" id="mm1" class="text_nor"
							maxlength="20" />
					</td>
				</tr>
				<tr height='30'>
					<th>
						<font color="red">*</font>ȷ������
					</th>
					<td>
						<input type="password" name="mm2" id="mm2" class="text_nor"
							maxlength="20" />
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2">
						"
						<span class="red">*</span>"Ϊ������
						<br />
						<span class="red">���볤�Ȳ���С��6λ�Ҳ���Ϊ�������ֻ���ͬ�ַ�</span>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="btn">
							<button type="button" onclick="cshMm()">
								ȷ��
							</button>
							&nbsp;&nbsp;
							<button type="button"
								onclick="closeDialog();">
								�ر�
							</button>
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
	</body>
</html>
