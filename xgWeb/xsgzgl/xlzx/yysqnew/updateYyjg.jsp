<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">
			function saveForm(){	
				  var zxurl= "xlzx_zxyyclnew.do?method=updateXlzxInfo";
				  var zxParameter ={};
				  // ============= �ֶ���֤ begin ===========
				  var zxfknr=jQuery("#zxfknr").val();
				  if(jQuery.trim(zxfknr)==""){
					  showAlert("���ķ����������Ϊ�գ�");
						return false;
				  }
				  // ============= �ֶ���֤ end ===========
				zxParameter["yyid"]=jQuery("#yyid").val();
	
				zxParameter["zxfknr"]=zxfknr;
			
				showConfirm("ȷ�ϱ�����Ϣ��",{"okFun":function(){
					jQuery.ajaxSetup({async:false});
						//��ѯ��Ϣ��������ѯ��	
						jQuery.post(zxurl,zxParameter,function(data){
							if(data == true){
									showAlert("����ɹ���",{},{"clkFun":function(){
										frameElement.api.opener.refreshForm("xlzx_yysqnew.do?method=yyjgManage");
										iFClose();
									}
								});
							}else{
								return showAlert("����ʧ�ܣ�");
							}
						},'json');
						jQuery.ajaxSetup({async:true});
				}});
			}
			jQuery(function(){
			})
		</script>
	</head>
	<body >
		<html:form action="/xlzx_yysqnew" method="post">
			<input type="hidden" name="yyid" id="yyid" value="${yyzxInfo.id}" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<tbody>
						<tr>
						    <th>
								<span class="red">*</span>���ķ������</br><font color="red">(��500��)</font>
							</th>
							<td colspan="3">
								<html:textarea name="yyzxInfo" property="zxfknr" styleId="zxfknr" cols="50" rows="9" style="width:100%" onblur="chLeng(this,500)"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		
			<div>
				<table class="formlist">
					<tfoot>
						<tr>
						<td colspan="4">
							<div class="bz">"<span class="red">*</span>"Ϊ������</div>
							<div class="btn">
								<button type="button" type="button" onclick="saveForm();return false;">
									�� ��
								</button>
								<button type="button" type="button" onclick="iFClose();return false;">
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

