<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xnwxdkjm/jg/js/xnwxdkjg.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xnwxdkjm/js/util.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				if(jQuery('#xh').val() != null && jQuery.trim(jQuery('#xh').val()) != ""){
					selectXsYzFz(jQuery.trim(jQuery('#xh').val()),"jg");
					jQuery("#jmbl").removeAttr("disabled");
				}else{
					jQuery("#jmbl").attr("disabled","disabled");
				}
				jQuery("#jmbl").change(function(){
					changejmbl(jQuery.trim(jQuery("#jmbl").val()));
				});
				jQuery("textarea").click(function(){
					if(jQuery.trim(jQuery("#xh").val()) == ""){
						showAlert("����ѡ��ѧ����")
					}
				});
			});
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/xnwxdkjm_jg" method="post" styleId="XnwxdkjmJgModel">
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:520px;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/zxdk/xnwxjkhk/comm/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����˾����������ѧ�꣩</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>ѧ��</th>
							<td>
								${xn}
								<html:hidden property="xn" styleId="xn"/>
								
							</td>
							<th>ѧ��</th>
							<td>
							    ${xqmc}
								<html:hidden property="xq" styleId="xq"/>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>���������</th>
							<td>
								<html:select property="jmbl" styleId="jmbl">
									<html:option value=""></html:option>
									  <logic:iterate id="jm"  name="jmllist">
									  	<html:option  value="${jm.jmbl}">${jm.jmbl}</html:option>
									  </logic:iterate>
									<%--<html:options property="jmbl" name="jmllist" labelProperty="jmbl"/>
								--%></html:select>
							</td>
							<th>Ԥ�Ƽ�����</th>
							<td>
								<label id="zje" style="display:none">${zje}</label>
								<label id="yjjmje"></label>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>��������</th>
							<td colspan="3">
							    <label>�ڷ������� �ķ����ڴ�"��"</label>
							    <br/>
							    <div id="jmyjdiv">
								    <%--<logic:iterate id="jm"  name="jmyjlist">
								    	<html:checkbox name="jm" property="jmyj" value="${jm.xssx}">${jm.jmyj}</html:checkbox>
								         <br/>
									</logic:iterate>	
								--%></div>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>��������
								</br><font color="red">(��500��)</font></th>
							<td colspan="3">
								<html:textarea property="sqly" styleId="sqly" 
											   onkeyup="checkzs();"
											   style="width:99%;" rows="5" ></html:textarea>
							</td>
						</tr>
						<tr>
							<th align="right">
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
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" id="bccg" onclick="saveDkjg('save');">
										����
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