<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		<script	type="text/javascript">

		
		
		function saveForm(obj){

			if(!checkNull("xh-lxdm-sqly")){
				return false;
			}
			
			var url = "";
			if(obj == 'update'){
				 url = "rcsw_lstd_sqgl.do?method=updateLstdsq&type=update";
			}
			if(obj == 'submit'){
				 url = "rcsw_lstd_sqgl.do?method=updateLstdsq&type=submit";
			}
			var shzt = jQuery("#shzt").val();
			var isopen = jQuery("#isopen").val();
			
			if(isopen==null||isopen==''){
				showAlertDivLayer('��������δ��ʼ��������ϵ����Ա��');
				return false;
			}
			if ("false" == isopen&&'3'!= shzt){
				showAlertDivLayer("��ǰδ����֤���������룬����ϵ����Ա��");
				return false;
			}
			
			
		      ajaxSubFormWithFun("lstdsqForm",url,function(data){
		    	 if(data["message"]=="����ɹ���" || data["message"]=="�ύ�ɹ���"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		    	 }
				});
		  }

		</script>
		
	</head>
	<body>
		
		<html:form action="/rcsw_lstd_sqgl" method="post" styleId="lstdsqForm">
			<html:hidden property="sqid"  styleId="sqid"/>
			<html:hidden property="shzt" styleId="shzt"/>
			<html:hidden property="splc"/>
			<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/lstd/comm/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th><span class="red">*</span>��������</th>
							<td>
								<html:select  property="lxdm" styleId="lxdm" style="width:130px">
								<option value=''></option>
								<html:options collection="lxwhList" property="lxdm" labelProperty="lxmc" />
								</html:select>
							</td>
							<th>����ʱ��</th>
							<td colspan="3">
								<html:hidden property="sqsj" />
								<bean:write name="lstdsqForm" property="sqsj"/>
							
							</td>
					    </tr>
					    <tr>
							<th>
								<span class="red">*</span>��������
								<br /><font color="red">(��1000��)</font>
							</th>
							<td colspan="3">
								<html:textarea property='sqly' style="width:98%" styleId="sqly" rows='7' onblur="checkLen(this,1000);"/>
							</td>
			      </tr>
					</tbody>
				</table>
			</div>
				<div style="height: 15px"></div>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm('update');">
										����ݸ�
									</button>
									
									<button type="button" type="button" onclick="saveForm('submit');">
										�ύ����
									</button>
									
									<button type="button" type="button" onclick="iFClose();">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>

