<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/fdyxx/js/fdyxxEdit.js"></script>
		<script language="javascript" src="js/xsgzgl/szdw/dwwh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
    	<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
   		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript">
		function calAge(){
			jQuery("#age").val( calculateAges(jQuery("#csrq").val().substr(0,4)+'-'+jQuery("#csrq").val().substr(5,2)+'-'+jQuery("#csrq").val().substr(8,2)));
		}
		
			jQuery(function() {
				onShow("xg");
				setXgzd("xg");// �����޸��ֶ�
				jQuery("#zgh").attr("readonly","readonly");

				if(jQuery("#xxdm").val() == '11318'){
					jQuery("#zdybdcon_table_szdw_fdyxx_qtxx").css("margin-bottom", "0px");
					jQuery("#gzjl").css("margin-bottom", "0px");
					onChange();
					jQuery("#kzzd16").change(function() {
						onChange();
					});
				}

				if(jQuery("#xxdm").val() == '10704'){
					jQuery("#age").attr('readonly','readonly')
					jQuery("#age").bind('click',function(){
						calAge();
					})
				}

				
				/**������ͨ��ѧ��ѧԺ*/
				if("13431" == jQuery("#xxdm").val()){
					if(jQuery("input:radio[name='zgqk']:checked").val() == '��ְ'){
						jQuery("th[name='zdybdcon_th_zhuanzhi'] ").html("<span class='red'>*</span>רְ");
					}else{
						jQuery("th[name='zdybdcon_th_zhuanzhi']").remove();
						jQuery("td[name='zdybdcon_td_zhuanzhi']").remove();
						jQuery("#zdybdcon_table_szdw_fdyxx_gzxx> tbody").append("<input type='hidden' name='zhuanzhi' value='' id='zhuanzhi'>");
					};
					
				jQuery("input:radio[name='zgqk']").change(function(){
					if(this.value == '��ְ'){
						jQuery("#zhuanzhi").remove();
						jQuery("td[name='zdybdcon_td_zgqk']").parent().append("<th name='zdybdcon_th_zhuanzhi'><span class='red'>*</span>רְ</th>" +
								"<td name='zdybdcon_th_zhuanzhi'>" +
									"<select id='zhuanzhi'>" +
										"<option value='ѧ��������'>ѧ��������</option>" +
										"<option value='ѧ����������'>ѧ����������</option>" +
										"<option value='��ί���'>��ί���</option>" +
										"<option value='ѧ������'>ѧ������</option>" +
										"<option value='�ֹ�ѧ������������'>�ֹ�ѧ������������</option>" +
										"<option value='��Ժѧ������'>��Ժѧ������</option>" +
										"<option value='����Ա'>����Ա</option>" +
									"<select>" +
								"</td>");
					}else{
						jQuery("th[name='zdybdcon_th_zhuanzhi']").remove();
						jQuery("td[name='zdybdcon_td_zhuanzhi']").remove();
						jQuery("#zhuanzhi").remove();
						jQuery("#zdybdcon_table_szdw_fdyxx_gzxx> tbody").append("<input type='hidden' name='zhuanzhi' value='' id='zhuanzhi' class='text_nor'>");
					}
				});
				}

				/*��ȡ������򿪳���ֵ�����޸�ֵ�Ա��Ƿ����޸ģ����ж��Ƿ��ύ*/
				/*�������ֶβ����Զ�������õģ����Զ��ǵ��������*/
				jQuery("#bzbbzBefore").val(jQuery("#bzbbz").val());
                jQuery("#drsjBefore").val(jQuery("#drsj").val());
                jQuery("#lxbmBefore").val(jQuery("#lxbm").val());
			});
		</script>
	</head>
	<body >	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
	
		<logic:equal value="1" name="cssz" property="sqkg">
			<logic:equal value="5" name="shxxRs" property="shjg">
				<div class="prompt" >
					<h3><span>��ʾ��</span> </h3>
					<p>�����޸�������������С�</p>
				</div>
			</logic:equal>
			<logic:equal value="3" name="shxxRs" property="shjg">
				<div class="prompt" >
					<h3><span>��ʾ��</span> </h3>
					<p>�����޸�����<font color='red'>���˻�</font>��<br/>��������${shxxRs.shyj}</p>
				</div>
			</logic:equal>
			<logic:equal value="2" name="shxxRs" property="shjg">
				<div class="prompt" >
					<h3><span>��ʾ��</span> </h3>
					<p>�����޸�����<font color='red'>��ͨ��</font>��<br/>��������${shxxRs.shyj}</p>
				</div>
			</logic:equal>
		</logic:equal>
		
		<logic:notEqual value="1" name="cssz" property="sqkg">
			<div class="prompt" >
				<h3><span>��ʾ��</span> </h3>
				<p>ϵͳδ���Ÿ���Ա��Ϣ�޸ġ�</p>
			</div>
		</logic:notEqual>
		
	
		<html:form action="/szdw_fdyxx" method="post" styleId="form1">
			<html:hidden property="zgh" styleId="zgh" value="${userName }"/>
			<input type="hidden" name="xxdm" id="xxdm"  value="${xxdm }"/>
			<html:hidden property="xgzdJson" styleId="xgzdJson"/>
			<input type="hidden" name="dshSqid" id="dshSqid" value='${dshSqid}'/>
			<input type="hidden" name="shzSqid" id="shzSqid" value='${shzSqid}'/>
			<input type="hidden" name="shjg" id ="shjg" value='${shxxRs.shjg}'/>
			<input type="hidden" name="shyj" id ="shyj" value='${shxxRs.shyj}'/>
			<input type="hidden" name="zpsfbt" id="zpsfbt" />
			<input type="hidden" name="zpsfcz" id="zpsfcz"  value="${zpsfcz }"/>
			<%--����ڱ౸ע������"3+2"����Աʱ�䡢��У�����޸�ǰֵ--%>
			<input type="hidden" id="bzbbzBefore">
			<input type="hidden" id="drsjBefore">
			<input type="hidden" id="lxbmBefore">
			<%--����ڱ౸ע������"3+2"����Աʱ�䡢��У�����޸�ǰֵ--%>
			<div class="demo_fdyxx"  id="content">
			
			</div>
			<logic:equal value="11318" name="xxdm">
				<div id="fjHidDiv" style="display: none;">
					<table width="100%" border="0" style="margin-bottom: 5px;" class="formlist">
					<tbody>
						<tr><th width="15%" >������Ϣ<br/>
							<font color="red">����������صĸ���</font> </th>
							<td width="85%" colspan="3">
							    <html:hidden name = "fdyxxModel"  property="kzzd19" styleId="kzzd19" />
							        <%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
								        <script type="text/javascript">
											//���ø��� 
											jQuery(function(){
												jQuery.MultiUploader({
													maxcount : 5,
													//��׺
													accept : 'png|gif|jpg|zip|rar|doc|docx',
													//����ļ���С ��λM
													maxsize: 10,
													//��Ÿ������������id
													elementid : 'kzzd19'
													});
											});
										</script>
							 </td>
						 </tr>
					 </tbody>
					</table>
				</div>
			</logic:equal>
			<logic:equal value="10704" name="xxdm">
				<div id="fjHidDiv">
					<table width="100%" border="0" style="margin-bottom: 5px;" class="formlist">
					<tbody>
						<tr><th width="15%" >������Ϣ<br/>
							<font color="red">����������صĸ���</font> </th>
							<td width="85%" colspan="3">
							    <html:hidden name = "fdyxxModel"  property="kzzd19" styleId="kzzd19" />
							        <%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
								        <script type="text/javascript">
											//���ø��� 
											jQuery(function(){
												jQuery.MultiUploader({
													maxcount : 5,
													//��׺
													accept : 'png|gif|jpg|zip|rar|doc|docx',
													//����ļ���С ��λM
													maxsize: 10,
													//��Ÿ������������id
													elementid : 'kzzd19'
													});
											});
										</script>
							 </td>
						 </tr>
					 </tbody>
					</table>
				</div>
			</logic:equal>
			<logic:notEqual value="5" name="shxxRs" property="shjg">
				<logic:equal value="1" name="cssz" property="sqkg">		
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="5">
								<div class="bz">"<span class="red">*</span>"Ϊ������  </div>
								<div class="btn">
									<button name="����" id="buttonSave" type="button" onclick="save();">
										����ݸ�
									</button>
									<button name="�ύ" id="buttonSave" type="button" onclick="saveAndSubmit();">
										�ύ����
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				</logic:equal>
			</logic:notEqual>
			<div id="zpHidDiv" style="display: none;">		
				<div align="center">
					<img src="teaPic.jsp?zgh=${userName }" style="height:133px;width:100px;" border="0"   id="zhaopian"/>
				</div>
				<logic:notEqual value="5" name="shxxRs" property="shjg">	
					<logic:equal value="1" name="cssz" property="sqkg">	
						<div align="center">
							<button type="button" onclick="showZpscDiv();" style="width:100px" id="buttonSave">�ϴ���Ƭ</button>
						</div>
					</logic:equal>
				</logic:notEqual>
			</div>	
		</html:form>
	</body>
</html>

