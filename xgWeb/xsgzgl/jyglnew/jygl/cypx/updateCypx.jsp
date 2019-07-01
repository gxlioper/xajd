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
				var pxlx = jQuery("#pxlx").val();
				if (jQuery.trim(pxlx) == ""){
					showAlert("��ѡ����ѵ���ͣ�");
					return false;
				}
				var pxsj = jQuery("#pxsj").val();
				if (jQuery.trim(pxsj) == ""){
					showAlert("��ѡ����ѵʱ�䣡");
					return false;
				}
				var sfqdzs = jQuery("#sfqdzs").val();
				if (jQuery.trim(sfqdzs) == ""){
					showAlert("��ѡ���Ƿ�ȡ��֤�飡");
					return false;
				}
				var jyxs = jQuery("#jyxs").val();
				if (jQuery.trim(jyxs) == ""){
					showAlert("��ѡ���ҵ��ʽ��");
					return false;
				}
				var url = "jyglnew_jygl_cypxgl.do?method=updateCypx&type="+type;
		      	ajaxSubFormWithFun("cypxForm",url,function(data){
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
		<html:form action="/jyglnew_jygl_cypxgl" method="post" styleId="cypxForm">
			<html:hidden property="id"/>
			<html:hidden property="xh"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:380px;margin-bottom: 0px;" >
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
								<span>��ҵ��ѵ��Ϣ</span>
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
							<th width=""><font class="red">*</font>��ѵ����</th>
							<td width="">
								<html:select property="pxlx" styleId="pxlx" style="width:150px;">
									<html:options collection="pxlxList" property="lxdm" labelProperty="lxmc" />
								</html:select>
							</td>
							<th width=""><font class="red">*</font>��ѵʱ��</th>
							<td width="">
								<html:text property="pxsj" styleId="pxsj" onclick="return showCalendar('pxsj','yyyy-MM-dd');" readonly="true" ></html:text>
							</td>
					    </tr>
						<tr>
							<th width=""><font class="red">*</font>�Ƿ�ȡ��֤��</th>
							<td width="">
								<html:select property="sfqdzs" styleId="sfqdzs" style="width:150px;" >
									<html:options collection="isnotList" property="dm" labelProperty="mc" />
								</html:select>
							</td>
							<th width=""><font class="red">*</font>��ҵ��ʽ</th>
							<td width="">
								<html:select property="jyxs" styleId="jyxs" style="width:150px;">
									<html:options collection="jyxsList" property="lxdm" labelProperty="lxmc" />
								</html:select>
							</td>
					    </tr>
					    <tr>
							<th width="">��ע<br /><font color="red">&lt;��500��&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='bz' style="width:521px;" styleId="bz" rows='5' onblur="checkLen(this,500);"/>
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

