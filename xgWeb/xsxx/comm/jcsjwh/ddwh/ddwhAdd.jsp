<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="xsxx/comm/jcsjwh/js/ddwh.js"></script>
		<script type='text/javascript' src="js/AjaxFunction.js"></script>
		<script type="text/javascript">
		function saveForm(){
			 if(!check('dddm-ddmc')){
				 showAlert("�뽫��������д����");
				 return false;
			 }
		     var url = "zjjcddwh.do?method=ddAdd&type=save";
		      ajaxSubFormWithFun("ddwhForm",url,function(data){
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
		</script>
	</head>
	<body>
		<html:form action="/zjjcddwh" method="post" styleId="ddwhForm" onsubmit="return false;">
		<div style="margin-top:8px;">
			 <table width="95%" border="0" class="formlist">
					<tbody>
						<tr>
							<th align="right">
								<span class="red">*</span>��Ӵ���
							</th>
							<td align="left" colspan="3">
								<html:text property="dddm" styleId="dddm" maxlength="8" 
								onkeyup="value=value.replace(/[^\u0000-\u00FF]/g,'')"
								></html:text>
							</td>
						</tr>
						<tr>
							<th align="right">
								<span class="red">*</span>�������
							</th>
							<td align="left" colspan="3">
								<html:text property="ddmc" styleId="ddmc" maxlength="128"></html:text>
							</td>
						</tr>
	
					</tbody>
			</table>
		</div>
		<div>
			<table width="95%" border="0" class="formlist">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button"  onclick="saveForm();return false;" id="buttonSave">
									�� ��
								</button>
								&nbsp;&nbsp;
								<button type="button"  onclick="iFClose();" id="buttonClose">
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
