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
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xszbb/comm/js/comm.js"></script>
		<script type="text/javascript" src="js/station/station.js"></script>
		<script type="text/javascript" src="js/station/city_name.js"></script>
		<script	type="text/javascript">
		function saveForm(){
			var sqly = jQuery("#sqly").val();
			if(jQuery.trim(jQuery("#xh").val()) == "" ||
					jQuery.trim(jQuery("#xszbblxdm").val()) == ""
						|| jQuery.trim(sqly) == ""||
						("y"==jQuery("input[name='sfbbhcyhk']:checked").val()&&((jQuery("#sj").length>0&&jQuery("#sj").val()=="")
						||(jQuery("#dd").length>0&&jQuery.trim(jQuery("#dd").val())=="")))){
				showAlertDivLayer("�뽫��<font color='red'>*</font>����Ŀ��д������");
				return false;
			}
			if(jQuery("#xxdm").val() == '13011' || jQuery("#xxdm").val() == '10695'){
				if(jQuery("[name=sfbbhcyhk]:first").attr('checked') == true){
					if(jQuery("#ccqdz").val().trim() == null || jQuery("#cczdz").val().trim() == null || jQuery("#ccqdz").val().trim() == "" || jQuery("#cczdz").val().trim() == ""){
						showAlertDivLayer("�뽫��<font color='red'>*</font>����Ŀ��д������");
						return false;
					}
				}
			}
			if(sqly.length > 1000){
				showAlertDivLayer("���벹�����ɲ��ܳ���1000�֣�");
				return false;
			}
			var	 url = "rcsw_xszbb_bbjggl.do?method=addXszbbsqjg&type=save";
		     ajaxSubFormWithFun("xszbbjgForm",url,function(data){
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

		function displayBbyhk(){
			var bbyhk = jQuery("#xszbblxdm").val();
			//�����Żݿ�����
			if (bbyhk == "001"){
				jQuery('.bbyhk').css("display","");
				if(jQuery("#xxdm").val() == '13011' || jQuery("#xxdm").val() == '10695'){
					showQz('y');
				}
			} else {
				//�ر�
				jQuery('.bbyhk').css("display","none");
				if(jQuery("#xxdm").val() == '13011' || jQuery("#xxdm").val() == '10695'){//�ൺ�Ƶ���Ի�
					showQz('n');
				}
			}
		}

		function showQz(val){
			nr = '<th><span class="red">*</span>�˳�����</th><td colspan="3"><input type="text" name="ccqdz" maxlength="15" value="����" id="ccqdz">��<input type="text" name="cczdz" maxlength="15" value="" id="cczdz"></td>';
			if(val == 'y'){
					jQuery("#qj").html(nr);
					citySelect("ccqdz");
					citySelect("cczdz");
					var xh = jQuery("#xh").val();
				if(xh != null){
					getHcqjxx(xh);
				}
			}else{
				jQuery("#qj").empty();
			}						
		}

		function getHcqjxx(xh){
			jQuery.ajaxSetup({async:false});
			jQuery.post("rcsw_xszbb_bbsqgl.do?method=getHcqjxx", {xh:xh}, function(data) {			
				jQuery("#ccqdz").val(data["ccqdz"]);
				jQuery("#cczdz").val(data["cczdz"]);					
<%--				if(data["ccqdz"] == null || data["ccqdz"] == ""){--%>
<%--					jQuery("#ccqdz").val("�ൺ");--%>
<%--				}	      		--%>
			}, 'json');
			jQuery.ajaxSetup({async:true});
		}

			
		jQuery(function(){
			changeHcyhk();
		});
		
		</script>
		
	</head>
	<body>
		
		<body>
		
		<html:form action="/rcsw_xszbb_bbjggl" method="post" styleId="xszbbjgForm" onsubmit="return false">
			<input type="hidden" id="xxdm" value="${xxdm}" />
			
			<div style='tab;width:100%;height:490px;overflow-x:hidden;overflow-y:auto;'>
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
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th><span class="red">*</span>����֤��</th>
							<td>
								<html:select  property="xszbblxdm" styleId="xszbblxdm" onclick="displayBbyhk();" style="width:130px">
								<option value=''></option>
								<html:options collection="bblxwhList" property="xszbblxdm" labelProperty="xszbblxmc" />
								</html:select>
							</td>
							<logic:equal value="11400" name="xxdm">
								<th>
									
								</th>
								<td >
									
								</td>
							</logic:equal>
							<logic:notEqual value="11400" name="xxdm">
								<th>
									<font class="bbyhk" style="display: none" color="red">*</font>
									<font class="bbyhk" style="display: none">�Ƿ񲹰���Żݿ�</font>
							   </th>
								<td >
									<html:radio styleClass="bbyhk" style="display: none" property="sfbbhcyhk" value="y" styleId="sfbbhcyhkY" onclick="showQz('y');">
										<label for="sfbbhcyhkY" style='cursor:pointer'><font class="bbyhk" style="display: none">��</font></label>
									</html:radio>
									<html:radio styleClass="bbyhk" style="display: none" property="sfbbhcyhk"  value="n" styleId="sfbbhcyhkN" onclick="showQz('n');">
										<label for="sfbbhcyhkN" style='cursor:pointer'><font class="bbyhk" style="display: none">��</font></label>
									</html:radio>
								</td>
							</logic:notEqual>
					    </tr>
					    <logic:equal value="12684" name="xxdm">
					     <tr class="bbyhk bbyhk_sjdd" style="display:none">
					     	<th><span class="red">*</span>ʱ��</th>
							<td>
								<html:text property="sj" styleId="sj"
									onfocus="showCalendar('sj','y-mm-dd');" />
							</td>
							<th><span class="red">*</span>�ص�</th>
							<td>
							<html:text property="dd" styleId="dd" maxlength="10" ></html:text>	
							</td>
					     </tr>
					     </logic:equal>
					     
					     <logic:equal value="13011" name="xxdm">					     
						     <tr id="qj">
						     
						     </tr>
					     </logic:equal>
					     
					     <logic:equal value="10695" name="xxdm">					     
						     <tr id="qj">
						     
						     </tr>
					     </logic:equal>
					     
					    <tr>
							<th><span class="red">*</span>���벹��ʱ��</th>
							<td colspan="3">
								<html:hidden property="sqsj" />
								<bean:write name="xszbbjgForm" property="sqsj"/>
							
							</td>
					    </tr>
					    <tr>
							<th>
								<span class="red">*</span>���벹������
								<br /><font color="red">(��1000��)</font>
							</th>
							<td colspan="3">
								<html:textarea property='sqly' style="width:98%" styleId="sqly" rows='5' onblur="checkLen(this,100);"/>
							</td>
			      		</tr>
			      		<tr>
							<th align="right" width="16%">
								������Ϣ
							</th>
							<td colspan="3">
								<html:hidden property="filepath" styleId="filepath" />
								<input type="file" id="filepath_f" name="filepath" />
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										jQuery('#filepath_f').multiUploader({
											maxcount : 3,
											//��׺
											accept : 'png|gif|jpg|zip|rar|doc|docx',
											//����ļ���С ��λM
											maxsize: 10,
											//��Ÿ������������id
											elementid : 'filepath',

											eid : 'filepath_f'
											});
									});
								</script>
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
										��  ��
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

