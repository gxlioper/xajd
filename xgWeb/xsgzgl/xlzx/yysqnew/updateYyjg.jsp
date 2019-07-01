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
				  // ============= 字段验证 begin ===========
				  var zxfknr=jQuery("#zxfknr").val();
				  if(jQuery.trim(zxfknr)==""){
					  showAlert("中心反馈意见不能为空！");
						return false;
				  }
				  // ============= 字段验证 end ===========
				zxParameter["yyid"]=jQuery("#yyid").val();
	
				zxParameter["zxfknr"]=zxfknr;
			
				showConfirm("确认保存信息？",{"okFun":function(){
					jQuery.ajaxSetup({async:false});
						//咨询信息插入至咨询表	
						jQuery.post(zxurl,zxParameter,function(data){
							if(data == true){
									showAlert("保存成功！",{},{"clkFun":function(){
										frameElement.api.opener.refreshForm("xlzx_yysqnew.do?method=yyjgManage");
										iFClose();
									}
								});
							}else{
								return showAlert("保存失败！");
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
								<span class="red">*</span>中心反馈意见</br><font color="red">(限500字)</font>
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
							<div class="bz">"<span class="red">*</span>"为必填项</div>
							<div class="btn">
								<button type="button" type="button" onclick="saveForm();return false;">
									保 存
								</button>
								<button type="button" type="button" onclick="iFClose();return false;">
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

