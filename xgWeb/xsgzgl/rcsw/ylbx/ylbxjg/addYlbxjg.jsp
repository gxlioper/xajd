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
		
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script	type="text/javascript">
		
		jQuery(function(){
			zdybdInit("rcsw_ylbx_add");
			var xxdm = jQuery("#xxdm").val();
			//�������ù���ְҵѧԺ
			if(xxdm == "14073"){
				jQuery("#zd5").val(jQuery("#dqnd").val());
			}else{
				jQuery("#xn").val(jQuery("#dqxn").val());
			}

			// ========== ���� < ============
			var filepathHiddenHtml = jQuery("#filepathHiddenDiv").html();
			jQuery("td[name='zdybdcon_td_filepathtd']").eq(0).html(filepathHiddenHtml);
			jQuery("#filepathHiddenDiv").html("");
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
			// ========== ���� > ============
			//��������ְҵ����ѧԺ
			if(xxdm == "13871"){
				sfbl();
				jQuery("#zd1").change(function (){
					sfbl();
				});				
			}
		});
		

		function saveForm(){
			//����֤ѧ��
			var xh = jQuery("#xh").val();
			var zd21 = jQuery("#zd21").val();
			var zd22 = jQuery("#zd22").val();
			if (jQuery.trim(xh) == ""){
				showAlert("����ѡ��ѧ����");
				return false;
			}
			if(!zdybdCheck()){
				return false;
			}
			if (zd21 > zd22){
				showAlert("���տ�ʼʱ�䲻�����ڱ��ս���ʱ�䣡");
				return false;
			}
			var	 url = "rcsw_ylbx_ylbxjggl.do?method=addYlbxsqjg&type=save";
		     ajaxSubFormWithFun("ylbxjgForm",url,function(data){
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
		
		
		function sfbl(){
			if(jQuery("#zd1").val()=="��Ը����"){
				jQuery("#zd4").hide();
				jQuery("#zd4").val("");
				jQuery("[name='zdybdcon_th_zd4']").eq(0).parent().hide();
				jQuery("#zd3").show();
				jQuery("[name='zdybdcon_th_zd3']").html("<span class='red'>*</span>���ʱ��");
			}else if(jQuery("#zd1").val()=="������"){
				jQuery("#zd3").hide();
				jQuery("#zd3").val("");
				jQuery("[name='zdybdcon_th_zd3']").text("");
				jQuery("#zd4").show();
				jQuery("[name='zdybdcon_th_zd4']").eq(0).parent().show();
			}
		}
		</script>
		
	</head>
	<body>
		
		<html:form action="/rcsw_ylbx_ylbxjggl" method="post" styleId="ylbxjgForm" onsubmit="return false">
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }"/>
			<div id="filepathHiddenDiv" style="display: none;">
				<html:hidden property="filepath" styleId="filepath" />
				<input type="file" id="filepath_f" name="filepath" />
			</div>
			<logic:notEqual name="xxdm" value="14073">
				<input type="hidden" id="dqxn" value="${dqxn }" />
		   </logic:notEqual>
		   <logic:equal name="xxdm" value="14073">
		   		<input type="hidden" id="dqnd" value="${dqnd }" />
		   </logic:equal>
			<div style='width:100%;height:440px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist" >
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/ylbx/comm/selectStudent.jsp" %>
				</table>
				<div class=""  id="content" style="margin-top: 5px; overflow-x:hidden;" >
				</div>
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

