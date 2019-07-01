<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/zzxmjg/js/updateZzxmjg.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){																								
			changeXmmc(false);
			var xxdm = jQuery("#xxdm").val();
			   if(xxdm=="11799"&&jQuery("#sjly").val()=='1'){
					jQuery("#je,#xmmc,#sqly").attr("disabled",true);
					jQuery("select").attr("disabled",true);
			   }
			
		});

		function changeXmmc(flg){
			if(flg){
				// ����
				jQuery("#xmmc").val("");
				jQuery("#je").val("");
			}
			//ȡ�����ݱ�xg_xszz_new_zzxmdmb; �ֶΣ�xmmc										
			var autoSetting = {
				dataTable:"xg_xszz_new_zzxmdmb",
				dataField:"xmmc",
				dataFieldKey:"je",
				dataFieldKeyId:"je",
				sqlTj: getSqlTj,
				scrollHeight:135							
			}
			// ģ��������������Ŀ���ơ�
			jQuery("#xmmc").setAutocomplete(autoSetting);
		}

		function getSqlTj(){
			var xn = jQuery("#xn").val();
			var xq = jQuery("#xq").val();
			var sqlTj;
			if(xn != ''){
				sqlTj = " and sqxn = '"+xn+"' ";
			}
			if(xq != ''){
				sqlTj += " and sqxq = '"+xq+"' ";
			}
			return sqlTj;
		}
		</script>
	</head>
	<body>
		
		<html:form action="/xszz_zzxmjg" method="post" styleId="zzxmjgForm" onsubmit="return false;">
			<html:hidden property="guid"/>
			<input type="hidden" id="nj" name="nj" value="${jbxx.nj}"/>
			<input type="hidden" id="xydm" name="xydm" value="${jbxx.xydm}"/>
			<input type="hidden" id="sjly" name="sjly" value="${mkxxForm.sjly}"/>
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }" />
			<div class="tab" style='width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ${mkxxForm.sjly}</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/selectStudent.jsp" %>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ŀ������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th>����ѧ��</th>
							<td>
								<html:select  property="xn" styleId="xn" style="width:130px" onchange="changeXmmc(true);">
								<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
							<th>����ѧ��</th>
							<td>
								<html:select  property="xq" styleId="xq" style="width:130px" onchange="changeXmmc(true);">
								<html:option value=""></html:option>
								<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
								</html:select>
							</td>
					    </tr>
					    <tr>
							<th><span class="red">*</span>��Ŀ���</th>
							<td>
								<html:select  property="lbdm" styleId="lbdm" style="width:130px">
								<html:options collection="xmlbList" labelProperty="lbmc" property="lbdm"/>
								</html:select>
							</td>
							<th><span class="red">*</span>��Ŀ����</th>
							<td>
								<html:text  property="xmmc" styleId="xmmc"  style="width:180px;" maxlength="20"  styleClass="text_nor"></html:text>
							</td>
					    </tr>
					    <tr>
							<th><span class="red">*</span>��Ŀ����ѧ��</th>
							<td>
								<html:select  property="pdxn" styleId="pdxn" style="width:130px">
								<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
							<th>��Ŀ����ѧ��</th>
							<td>
								<html:select  property="pdxq" styleId="pdxq" style="width:130px">
								<html:option value=""></html:option>
								<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
								</html:select>
							</td>
					    </tr>
					    <tr>
							<th>���</th>
							<td>
								<html:text  property="je" styleId="je"  style="width:120px;" maxlength="10"  styleClass="text_nor" onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>
							</td>
							<th>
							����ʱ��
							</th>
							<td>
								<bean:write property="sqsj" name="zzxmjgForm"/>
								<html:hidden property="sqsj"/>
							</td>
					    </tr>
					    <logic:equal value="11799" name="xxdm">
					    <tr>
							<th>
								�������
							</th>
							<td colspan="3">
								<html:textarea property='ylzd1' style="width:98%" styleId="ylzd1" rows='5'/>
							</td>
			            </tr>
			            </logic:equal>
					    <tr>
							<th>
								������Ϣ
							</th>
							<td colspan="3">
								<html:hidden property="ylzd5" styleId="ylzd5" />
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
								<logic:notEqual value="11799" name="xxdm">
								<script type="text/javascript">
											//���ø��� 
											jQuery(function(){
												jQuery.MultiUploader({
													maxcount : 3,
													//��׺
													accept : 'png|gif|jpg|zip|rar|doc|docx|pdf',
													//����ļ���С ��λM
													maxsize: 10,
													//��Ÿ������������id
													elementid : 'ylzd5'
													});
											});
										</script>
								</logic:notEqual>
								<logic:equal value="11799" name="xxdm">
									<script type="text/javascript">
											//���ø��� 
											jQuery(function(){
												jQuery.MultiUploader({
													maxcount : 3,
													//��׺
													accept : 'png|gif|jpg|zip|rar|doc|docx|pdf|mp4',
													//����ļ���С ��λM
													maxsize: 300,
													//��Ÿ������������id
													elementid : 'ylzd5'
													});
											});
										</script>
								</logic:equal>
							</td>
						</tr>
					    <tr>
							<th>
								��������
								<br /><font color="red">(��200��)</font>
							</th>
							<td colspan="3">
								<html:textarea property='sqly' style="width:98%" styleId="sqly" rows='8' onblur="checkLen(this,200);"/>
							</td>
			      </tr>
					</tbody>
				</table>
			</div>

			<div style="height: 50px"></div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
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

