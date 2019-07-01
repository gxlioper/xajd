<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			function saveJbsz(){
				if(!jQuery("[name='kgzt']:checked").val()){
					 return showAlert("����״̬���");
				}
				if(jQuery("#jgsbzq").val() == ""){
					 return showAlert("����걨���ڱ��");
				}
				var url = "jskp_lxjg.do?method=saveJbsz";
				ajaxSubFormWithFun("LxjgForm", url, function(data) {
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
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/jskp_lxjg" method="post" styleId="LxjgForm">
			<html:hidden  property="xmid" styleId="xmid"  />
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<tbody>
						<tr>
							<th><span class="red">*</span>�걨����
							</th>
							<td colspan="3">
								<html:radio property="kgzt" value="1"></html:radio>��
								<html:radio property="kgzt" value="0"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>�걨ʱ��</th>
							<td colspan="3">
								<html:text property="sbkssj" styleId="start" onclick="return showCalendar('start','y-mm-dd',true,'end');"  />-
								<html:text property="sbjssj" styleId="end" maxlength="10" onclick="return showCalendar('end','y-mm-dd',false,'start');" />
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>����걨����</th>
							<td colspan="3">
								<html:select property="jgsbzq" styleId="jgsbzq">
									<html:option value="">--��ѡ��--</html:option>
									<html:option value="xn">һѧ��һ��</html:option>
									<html:option value="month">һ��һ��</html:option>
									<html:option value="day">һ��һ��</html:option>
								</html:select>
							</td>
						</tr>
					
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="saveJbsz();">
										�� ��
									</button>
									<button type="button" onclick="iFClose();">
										�ر�
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