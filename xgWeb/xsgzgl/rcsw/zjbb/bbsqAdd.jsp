<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	    <script language="javascript" src="js/check.js"></script>
	    <script type='text/javascript' src="js/function.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script language="javascript" defer="defer">
		function saveBbsq(){
			
			if($("xmid") && $("xmid").value==""){
				alertInfo("�������Ʋ���Ϊ�գ�");
				return false;
			}
			showConfirm("�ò�������<font color='blue'>����֤������</font>������Ϣ���Ƿ�ȷ����",{"okFun":function(){
				//����
				var pkValue=new Array();
	
				jQuery.ajaxSetup({async:false});
				
				// �õ�JSON����
		        var parameter ={};
				
				parameter["xmid"]=escape(jQuery("#xmid").val());
				
				parameter["sqly"]=escape(jQuery("#sqly").val());
				
				var url = "rcsw_zjbb_ajax.do?method=save";
	          	
				jQuery.post(url,parameter,
					function(result){
						if(result=="����ɹ�"){
							showAlert(result,{},{"clkFun":function(){
								if (parent.window){
									refershParent();
								}
							}});
						}else{
							alertInfo(result);
						}
						});
				
				jQuery.ajaxSetup({async:true});
				}});
			
	     }
				
    	</script>
  </head>
  
  <body>
  	<html:form action="/rcsw_zjbb_ajax" method="post">
		<!-- ������ -->
		<%@ include file="/comm/hiddenValue.jsp"%>
	<div id="div_bbsq" >
				<table class="formlist">
					<thead>
					<tr>
					<th colspan="2">
					<span>ѧ������</span>
					</th>
					</tr>
					</thead>

					<tbody>
					<tr>
					<th width='25%'>
						<font color="red">*</font>֤������
					</th>
					<td style='word-break:break-all;width:96%' >
					 	<html:select property="xmid" styleId="xmid">
							<option value=""></option>
							<html:options collection="zjbbList" property="id"
								labelProperty="zjmc" />
						</html:select>
					</td>
					</tr>
				
					<tr>
					<th width='25%'>
						��������
					</th>
					<td style='word-break:break-all;width:96%'>
						 <html:textarea  property="sqly" rows="4" 
						 style="word-break:break-all;width:99%" styleId="sqly" 
									onblur="chLeng(this,500);"></html:textarea>
					</td>
					</tr>
					</tbody>
			
					<tfoot>
					<tr>
					<td  colspan="2">
					<div class="btn">
					<button type="button" id="btn_bc" onclick="saveBbsq()">
					�� ��
					</button>
			
					<button type="button" id="btn_gb" onclick="Close();return false;">
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
