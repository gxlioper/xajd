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
		<script type="text/javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript" src="js/stuinfoFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getXjydInfo.js'></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type="text/javascript" src="js/xsxx/xsxxplczFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/xsxxgl/js/xsxxgl.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/xsxxgl/js/xsxxglXg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
			<script type="text/javascript">
								//���ø��� 
								jQuery(function(){
									jQuery.MultiUploader({
										maxcount : 3,
										//��׺
										accept : 'png|gif|jpg|jpeg|doc|docx|rar|zip',
										//����ļ���С ��λM
										maxsize: 10,
										//��Ÿ������������id
										elementid : 'fjid'
										});
										// �й�����־Ը��  http://www.zgzyz.org.cn/
									if("12303" == jQuery("#xxdm").val()){
										var shgxgx2 = jQuery("#shgxgx2").val();
										jQuery("td").on("change","#shgxgx2",function(){
											if(this.value == '��ע��'){
												jQuery(this).after("<label id='zdybdcon_th_wzlj'>�й�����־Ը����</label>" +
												"<a id='zdybdcon_th_wzlj' href=' http://www.zgzyz.org.cn/' target='_Blank'>"+
												" http://www.zgzyz.org.cn/"+
												"</a>");
											}else{
												jQuery("label[id='zdybdcon_th_wzlj']").remove();
												jQuery("a[id='zdybdcon_th_wzlj']").remove();
												jQuery("#zdybdcon_table_xsxx_update_qtxx> tbody").append("<input type='hidden' name='shgxgx2' value='' id='shgxgx2' class='text_nor'>");
											}
										});
									}
								});
								
								//���������Ƿ���֤������������ñ������ñ�����������true
								var fjbtsfyz = function(){
									return ${fjxx.sfbt == "yes"};
								}
							</script>
	</head>
	<body >	
	<html:form action="/xsxx_xsxxgl" method="post" styleId="form1">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="sfxsgban" id="sfxsgban" value="${sfxsgban} "/>
			<input type="hidden" name="zpsfbt" id="zpsfbt" />
			<input type="hidden" name="zpsfcz" id="zpsfcz"  value="${zpsfcz }"/>
			<input type="hidden" name="xxdm" id="xxdm"  value="${xxdm }"/>
			<input type="hidden" name="xh" id="xh" value='${xh}'/>
			<input type="hidden" name="yyfj" id="yyfj" value="${yyfj}" />
			<!-- ������� -->
			<div style="height: 50px;">
				<div id="navigation" style="top: 0; background: #fff; position: fixed; width: 100%;_position:absolute;_bottom:auto;_top:expression(eval(document.documentElement.scrollTop));">
					<div class="title_xxxx">
						<span class="people_xx"><span id="xmView"></span> ��ѧ�ţ� ${xh }��</span>
						<logic:notEqual value="11458" name="xxdm">
						<span class="wxts">��ܰ���ѣ� <span>
						�����������
								���Կ��ٶ�λ������Ҫ�鿴����Ϣ
						</span>
						</span>
					
						</logic:notEqual>
					</div>
					<logic:equal value="11458" name="xxdm">
					<div style="background-color:#fffedf;height: 95px;line-height: 25px;border-bottom: #4381d1 1px solid;margin-top: 4px;">
						<span style="float:left; color:#ff9600; padding-left:10px;">��ܰ���ѣ� <span style="color:#666666;">
						�Ա�ѧ�ڿ�ʼ�����λͬѧ��ʱͨ��ѧ��������Ϣϵͳ������ѧ���������Ϣ��
						����Ϣ����ѧ��������ѧ�𡢾�ҵ��������������ҵ��ϢϢ��أ���������ӣ�
						�ڿ�չ��ѧ����ѧ���Լ������ܶ๤�����Ǵ�ʹ�ñ�ϵͳ������Ϣ�д�ѧ��д���ֻ������e-mail�������ϵ��
						����ϵ�����Ƿ��ֺܶ�ͬѧ�ֻ�����û�л���ͣ����e-mail��ַ��д����
						���������ϵ���ϴ�ң����������ʹ��ɥʧĳЩ���ϣ���õ��Ļ��ᣬ
						Ϊ���������������������ʱ�����������Ϣ����״̬����������ȷ���ֻ����롢׼ȷ����ĵ����������סַ��
						</span></span>
						</div>
					</logic:equal>
					
				</div>
			</div>
			<logic:notEqual value="11458" name="xxdm">
			<div class="demo_xxxx" style="margin-top: 20px; overflow-x:hidden;" id="content">
			</logic:notEqual>
			<logic:equal value="11458" name="xxdm">
			<div class="demo_xxxx" style="margin-top: 120px; overflow-x:hidden;" id="content">
			</logic:equal>
			</div>
			<logic:equal name="xxdm" value="13943">
			<div
				 style='width: 100%;overflow-x: hidden; overflow-y: auto;'>
				<table width="100%"  border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>Ժϵ���</span>
							</th>
						</tr>
					</thead>
				<tbody>
					<tr>
							<th align="right">
								Ժϵ���
								<br />
								<font color="red"><B>(��100��)</B>
								</font>
							</th>
							<td align="left" colspan="100">
								<html:textarea property='shgxgzdw1' styleId="shgxgzdw1" style="width:700px" rows='7' value="${shgxgzdw1}"
									onblur="checkLen(this,100)" />
							</td>
						</tr>
				</tbody>
				</table>
			</div>
			</logic:equal>
			<div style="height: 15px"></div>			
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="5">
								<div class="bz">"<span class="red">*</span>"Ϊ������  </div>
								<div class="btn">
										<button name="����" id="buttonSave" type="button" onclick="saveForm();">
											�� ��
										</button>
									<button type="button"  name="�ر�" onclick="Close()" id="buttonClose">�� ��</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
						
			<div id="zpHidDiv" style="display: none;">			
				<jsp:include flush="true" page="zpxg.jsp"></jsp:include>
			</div>

			<div id="jtcyxxHidDiv" style="display: none;">
				<jsp:include flush="true" page="jtcyxxxg.jsp"></jsp:include>
			</div>						
				
			</html:form>
		
		<!-- ��ʾ��Ϣ -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
		<script type="text/javascript" src="js/station/station.js"></script>
	</body>
</html>

