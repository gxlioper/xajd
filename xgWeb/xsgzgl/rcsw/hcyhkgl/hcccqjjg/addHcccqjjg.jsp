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
		<script type="text/javascript" src="js/station/station.js"></script>
		<script type="text/javascript" src="js/station/city_name.js"></script>
		<script	type="text/javascript">
		function saveForm(){
			//����֤ѧ��
			var xh = jQuery("#xh").val();
			var xn = jQuery("#xn").val();
			var xq = jQuery("#xq").val();
			var ccqdz = jQuery("#ccqdz").val();
			var cczdz = jQuery("#cczdz").val();
			//var bz = jQuery("#bz").val();
			if (jQuery.trim(xh) == ""){
				showAlert("����ѡ��ѧ����");
				return false;
			}	
			if (jQuery.trim(xn) == ""){
				showAlert("����ѡ��ѧ�꣡");
				return false;
			}
			if (jQuery.trim(xq) == ""){
				showAlert("����ѡ��ѧ�ڣ�");
				return false;
			}
			if (jQuery.trim(ccqdz) == ""){
				showAlert("��������˳����վ��");
				return false;
			}
			if (jQuery.trim(cczdz) == ""){
				showAlert("��������˳��յ�վ��");
				return false;
			}
			if(jQuery("#xxdm").val() == "10351"){
				if (jQuery("#hcyhklx").val() == ""){
					showAlert("��ѡ������ԭ��");
					return false;
				}
			}
			/*if (jQuery.trim(bz) == ""){
				showAlert("�������뱸ע��");
				return false;
			}*/
			var url = "rcsw_hcyhk_hcccqjjggl.do?method=addHcccqjjg&type=save";
	      	ajaxSubFormWithFun("hcccqjjgForm",url,function(data){
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
		jQuery(function(){
			citySelect("ccqdz");
			citySelect("cczdz");
		});
		</script>
		
	</head>
	<body>
		
		<body>
		
		<html:form action="/rcsw_hcyhk_hcccqjjggl" method="post" styleId="hcccqjjgForm">	
			<div style='tab;width:100%;height:425px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/xszbb/comm/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>�𳵳˳�������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th><span class="red">*</span>ѧ��</th>
							<td>
								<html:select  property="xn" styleId="xn" style="width:130px">
									<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
							<th><span class="red">*</span>ѧ��</th>
							<td>
								<html:select  property="xq" styleId="xq" style="width:130px">
								<html:option value=""></html:option>
									<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
								</html:select>
							</td>
					    </tr>
					    <tr>
							<th><span class="red">*</span>�˳�����</th>
							<td colspan="3">
								<html:text property="ccqdz" styleId="ccqdz" maxlength="15" value="${ccqdz}"> </html:text>
								��
								<html:text property="cczdz" styleId="cczdz" maxlength="15" > </html:text>
							</td>
					    </tr>
					    <logic:equal name="xxdm" value="10351">
					    	<th><span class="red">*</span>����ԭ��</th>
							<td colspan="3">
						    	<html:select property="hcyhklx" styleId="hcyhklx"  style="width: 200px" value="${hcyhklx}">
									<html:options collection="hcyhklxList" property="lxdm"
										labelProperty="lxmc" />
								</html:select>
							</td>
					    </logic:equal>
					    <tr>
							<th><span class="red">*</span>��дʱ��</th>
							<td colspan="3">
								<html:hidden property="txsj" />
								<bean:write name="hcccqjjgForm" property="txsj"/>
							
							</td>
					    </tr>
					    <tr>
							<th>
								��ע
								<br /><font color="red">&lt;��500��&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='bz' style="width:98%" styleId="bz" rows='5' onblur="checkLen(this,100);"/>
							</td>
			      		</tr>
					</tbody>
				</table>
			</div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
										�� ��
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

