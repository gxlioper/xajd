<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	  <%@ include file="/syscommon/head.ini"%>
	  <script type="text/javascript" src="js/check.js"></script>
	  <script type="text/javascript">
	  	function cancelXsyysqAction(){
	  		var sqid = jQuery("#sqid").val();
	  		var qxyyyy = jQuery('#qxyyyy').val();
	  		if(!checkNotNull("qxyyyy")){
	 			showAlert("�뽫��������д������");
	 			return false;
	 		}
	  		var yyzt = jQuery('#yyzt').val();
	  		if(qxyyyy.length > 500){
	  			showAlertDivLayer("ȡ��ԤԼԭ���������������"+500+",��ȷ�ϣ�");
	  			return false;
	  		}
			jQuery.post("xljk_xsyyzx.do?method=cancelXsyysqAction",{sqid: sqid,qxyyyy:qxyyyy,yyzt:yyzt},function(data){
	 			showAlert(data["message"],{},{"clkFun":function(){
		 				if (frameElement.api){
							var api = frameElement.api,W = api.opener;
							W.reloadXsyysqDataTable();
							iFClose();
						} 
	 				}
	 			});
			},'json');
	  	}
	  </script>
  </head>
  
  <body>
    <table width="100%" border="0" class="formlist">
    	<thead>
			<tr>
				<th colspan="2">
					<span>ȡ��ԤԼ</span>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th width="30%">
					<span class="red">*</span>ȡ��ԤԼԭ��<br/>
					<span class="red">(��500��)</span>
				</th>
				<td>
					<textarea name="qxyyyy" id="qxyyyy" rows="5" style="width: 95%;" onkeypress="checkLen(this,500);"></textarea>
					<input type="hidden" name="sqid" id="sqid" value="${sqid }"/>
					<input type="hidden" name="yyzt" id="yyzt" value="${yyzt }"/>
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
					<div class="bz">"<span class="red">*</span>"Ϊ������</div>
					<div class="btn">
						<button id="submit_button" type="button"  onclick="cancelXsyysqAction();">
							�� ��
						</button>
						<button type="button" name="�� ��" onclick="iFClose();">
							�� ��
						</button>
					</div>
				</td>
			</tr>
		</tfoot>
    </table>
  </body>
</html>
