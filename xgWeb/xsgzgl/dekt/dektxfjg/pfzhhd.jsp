<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function tj(){				
				ajaxSubFormWithFun("dektxfjgForm", 'dekt_xfjg.do?method=pfbc', function(data) {
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
	<body >
		<html:form action="/dekt_xfjg" method="post" styleId="dektxfjgForm" onsubmit="return false;">
			<html:hidden property="jgid" style="jgid"/>
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>����</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>���۽��
							</th>
							<td width="34%" colspan="3">
								<html:select property="pjjg" styleId="pjjg">
									<option value="����">����</option>
									<option value="һ��">һ��</option>
									<option value="������">������</option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="16%">
								��ע
							</th>
							<td width="34%" colspan="3">
								<html:textarea property="pjbz" styleId="pjbz" rows="5" onblur="checkLen(this,500);" style="width:98%;">
									
								</html:textarea>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="tj();return false;">
										����
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

