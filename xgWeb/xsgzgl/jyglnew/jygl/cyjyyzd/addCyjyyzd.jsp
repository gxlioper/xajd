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
		<script	type="text/javascript">
			function saveForm(type){
				var xh = jQuery("#xh").val();
				if (jQuery.trim(xh) == ""){
					showAlert("����ѡ��һ��ѧ����");
					return false;
				}	
				var gsmc = jQuery("#gsmc").val();
				if (jQuery.trim(gsmc) == ""){
					showAlert("��˾���Ʋ���Ϊ�գ�");
					return false;
				}
				var gslx = jQuery("#gslx").val();
				if (jQuery.trim(gslx) == ""){
					showAlert("��ѡ��˾���ͣ�");
					return false;
				}
				var zcsj = jQuery("#zcsj").val();
				if (jQuery.trim(zcsj) == ""){
					showAlert("��ѡ��ע��ʱ�䣡");
					return false;
				}
				var zczb = jQuery("#zczb").val();
				if (jQuery.trim(zczb) == ""){
					showAlert("ע���ʱ�����Ϊ�գ�");
					return false;
				}
				var sshy = jQuery("#sshy").val();
				if (jQuery.trim(sshy) == ""){
					showAlert("��ѡ��������ҵ��");
					return false;
				}
				var url = "jyglnew_jygl_cyjyyzdgl.do?method=addCyjyyzd&type="+type;
		      	ajaxSubFormWithFun("cyjyyzdForm",url,function(data){
		    	 if(data["message"]=="����ɹ���"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    	 }});
		  	}
			jQuery(function(){
			});
		</script>
	</head>
	<body>
		<html:form action="/jyglnew_jygl_cyjyyzdgl" method="post" styleId="cyjyyzdForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:330px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsxx/comm/selectStudent/selectStudentAll.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>��ҵ��ҵ������ָ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th width="20%">�ֻ�����</th>
							<td width="30%">
								<html:text property="sjhm" styleId="sjhm" maxlength="11" style="" onkeyup="checkInputData(this);"/>
							</td>
							<th width="20%">QQ����</th>
							<td width="30%">
								<html:text property="qqhm" styleId="qqhm" maxlength="12" style="" onkeyup="checkInputData(this);"/>
							</td>
						</tr>
						<tr>
							<th width=""><font class="red">*</font>��˾����</th>
							<td width="" colspan="3">
								<html:text property="gsmc" styleId="gsmc" maxlength="80" style="width:523px;" />
							</td>
					    </tr>
						<tr>
							<th width=""><font class="red">*</font>��˾����</th>
							<td width="">
								<html:select property="gslx" styleId="gslx" style="width:150px;">
									<html:options collection="gslxList" property="dm" labelProperty="mc" />
								</html:select>
							</td>
							<th width=""><font class="red">*</font>ע��ʱ��</th>
							<td width="">
								<html:text property="zcsj" styleId="zcsj" onclick="return showCalendar('zcsj','yyyy-MM-dd');" readonly="true" ></html:text>
							</td>
					    </tr>
						<tr>
							<th width=""><font class="red">*</font>ע���ʱ�</th>
							<td width="">
								<html:text property="zczb" styleId="zczb" maxlength="10" style="" onkeyup="checkInputData(this);"/>
							</td>
							<th width="">��ҵ����</th>
							<td width="">
								<html:text property="jyrs" styleId="jyrs" maxlength="10" style="" onkeyup="checkInputData(this);"/>
							</td>
					    </tr>
					    <tr>
					    	<th width=""><font class="red">*</font>������ҵ</th>
							<td width="" colspan="3">
								<html:select property="sshy" styleId="sshy" style="width:150px;">
									<html:options collection="sshyList" property="lxdm" labelProperty="lxmc" />
								</html:select>
							</td>
					    </tr>
					</tbody>
				 </table>
				</div>
				<div>
					<table width="100%" border="0" class="formlist">
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="bz">"<span class="red">*</span>"Ϊ������</div>
									<div class="btn">
										<button type="button" type="button" onclick="saveForm('save');">
											����
										</button>
										<button type="button" type="button" onclick="iFClose();">
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

