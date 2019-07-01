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
				var jydw = jQuery("#jydw").val();
				if (jQuery.trim(jydw) == ""){
					showAlert("��ҵ��λ����Ϊ�գ�");
					return false;
				}
				var jydwxz = jQuery("#jydwxz").val();
				if (jQuery.trim(jydwxz) == ""){
					showAlert("��ѡ���ҵ��λ���ʣ�");
					return false;
				}
				var pqdw = jQuery("#pqdw").val();
				if (jQuery.trim(pqdw) == ""){
					showAlert("��ǲ��λ����Ϊ�գ�");
					return false;
				}
				var jyxs = jQuery("#jyxs").val();
				if (jQuery.trim(jyxs) == ""){
					showAlert("��ѡ���ҵ��ʽ��");
					return false;
				}
				var url = "jyglnew_jygl_byqxgl.do?method=updateByqx&type="+type;
		      	ajaxSubFormWithFun("byqxForm",url,function(data){
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
		<html:form action="/jyglnew_jygl_byqxgl" method="post" styleId="byqxForm">
			<html:hidden property="xh"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:430px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/xszbb/comm/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>��ҵȥ����Ϣ</span>
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
							<th width=""><font class="red">*</font>
							<logic:equal value="70002" name="xxdm">
								ʵ�ʹ�����λ
							</logic:equal>
							<logic:notEqual value="70002" name="xxdm">							
								��ҵ��λ
							</logic:notEqual>
							</th>
							<td width="">
								<html:text property="jydw" styleId="jydw" maxlength="80" style="" />
							</td>
							<th width=""><font class="red">*</font>��ҵ��λ����</th>
							<td width="">
								<html:select property="jydwxz" styleId="jydwxz" style="width:150px;">
									<html:options collection="jydwxzList" property="lxdm" labelProperty="lxmc" />
								</html:select>
							</td>
					    </tr>
						<tr>
							<th width=""><font class="red">*</font>��ǲ��λ</th>
							<td width="">
								<html:text property="pqdw" styleId="pqdw" maxlength="80" style="" />
							</td>
							<th width=""><font class="red">*</font>��ҵ��ʽ</th>
							<td width="">
								<html:select property="jyxs" styleId="jyxs" style="width:150px;">
									<html:options collection="jyxsList" property="lxdm" labelProperty="lxmc" />
								</html:select>
							</td>
					    </tr>
					    <!-- �Ͼ��ߵ�ְҵ����ѧУ -->
					    <logic:equal value="10874" name="xxdm">					    
						    <tr>
						    	<th>ѧ��</th>
								<td>
									<html:text property="xl" styleId="xl" maxlength="20" style="" />
								</td>
								<th>ѧλ</th>
								<td>
									<html:text property="xw" styleId="xw" maxlength="20" style="" />
								</td>
						    </tr>
					    </logic:equal>
					    
					     <!-- ����ҽҩ�ߵ�ְҵѧУ -->
					    <logic:equal value="70002" name="xxdm">					    
						    <tr>
						    	<th>��ҵȥ��</th>
								<td>
									<html:select property="byqx" styleId="byqx" style="width:150px;">
										<html:options collection="byqxList" property="lxdm" labelProperty="lxmc" />
									</html:select>
								</td>
								<th>��ҵ���</th>
								<td>
									<html:select property="jylb" styleId="jylb" style="width:150px;">
										<html:options collection="jylbList" property="lxdm" labelProperty="lxmc" />
									</html:select>
								</td>
						    </tr>
						    <tr>
						    	<th>��ҵ״��</th>
						    	<td>
						    		<html:select property="jyzk" styleId="jyzk" style="width:150px;">
										<html:options collection="jyzkList" property="lxdm" labelProperty="lxmc" />
									</html:select>
						    	</td>
						    	<th>����֤��</th>
						    	<td>
						    		<html:text property="bdzh" styleId="bdzh" maxlength="20"></html:text>
						    	</td>
						    </tr>
						    <tr>
						    	<th>��ǲʱ��</th>
						    	<td>
						    		<html:text property="pqsj" styleId="pqsj" onfocus="showCalendar('pqsj','y-mm-dd');"></html:text>
						    	</td>
						    	<th>ѧ�������ʵݵ�λ</th>
						    	<td>
						    		<html:text property="tddw" styleId="tddw" maxlength="20"></html:text>
						    	</td>
						    </tr>
						     <tr>
						    	<th>����ת��ʱ��</th>
						    	<td>
						    		<html:text property="zdsj" styleId="zdsj" onfocus="showCalendar('zdsj','y-mm-dd');"></html:text>
						    	</td>
						    	<th></th>
						    	<td></td>
						    </tr>
						    <tr>
						    	<th>��ע</th>
						    	<td colspan="3">
						    		<html:textarea property="bz" styleId="bz" rows="8" style="width:99%"></html:textarea>
						    	</td>
						    </tr>
					    </logic:equal>
					</tbody>
				 </table>
				</div>
				<div style="height: 20px"></div>
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

