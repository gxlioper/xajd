<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/sqsh/js/sqsh.js"></script>
		<script type="text/javascript">

			jQuery(function(){
				//��������ѡ��
				loadMkxxSelectOptions();
				//����radioѡ��
				loadMkxxRadioOptions();

				var api = frameElement.api;
				W = api.opener;
				var grid = W.jQuery("#dataTable");
				var selectBox = grid.getSeletRow();
				
				var tbody = jQuery("#xmInfo");
					tbody.find("tr").remove();
					
				jQuery.each(selectBox,function(i,e){
					var jesfxssq=e['jesfxssq'];
					if(jesfxssq=='1'){
						var trHtml = "<tr>";
						trHtml+="<td style='width:30%'>";
						trHtml+="<input type='hidden' name='xmdmArray' value='"+e['xmdm']+"'/>";
						trHtml+=e['xmmc'];
						trHtml+="</td><td style='width: 30%'>";
						trHtml+=e['lbmc'];
						trHtml+="</td><td style='width: 40%'>";
						trHtml+="<input type='hidden' name='je' value='"+e['je']+"'/>";
						trHtml+="<input type='text' name='ylzd1'  onkeyup='checkInputData(this);checkJesx(this)'/>";
						trHtml+="<font color='blue'>���޽�"+e['je']+"<font/>";
						trHtml+="</td></tr>";
						tbody.append(trHtml);
					}else{
						var trHtml = "<tr>";
						trHtml+="<td style='width: 30%'>";
						trHtml+="<input type='hidden' name='xmdmArray' value='"+e['xmdm']+"'/>";
						trHtml+=e['xmmc'];
						trHtml+="</td><td style='width: 30%'>";
						trHtml+=e['lbmc'];
						trHtml+="</td><td style='width: 40%'>";
						trHtml+="<input type='hidden' name='ylzd1' />";
						trHtml+=e['je'];
						trHtml+="</td></tr>";
						tbody.append(trHtml);
					}
				});

				if ("10351" == jQuery("#xxdm").val()){
					if ("" == jQuery("textarea[name=sqly]").val()) {
						jQuery("textarea[name=sqly]").val("    ����ѧϰ̬�ȶ������ڷܿ̿࣬��λ��һ�Ƚ�ѧ�𣬲�������ĳɼ������ˡ��ݳ��ࡱ��ע�ص���������ȫ�淢չ������������ྺ��������λ񽱣���Ϊһ��ѧ���ɲ��������ɼ����������ͬѧ����ɫ������˸�����������ܺ�����ע��������ʵ�����ϣ����ڴ��£�����ѧ֪ʶ���õ����п���֮�У�����У�����⡶*******������******��2�ʡ�����⡶*********��1��������������ʵ�������Ĺ�����ƻ��������Ϊ*****��");
					}
				}
				
			});
			
		</script>
	</head>
	<body>
	
		<html:form action="/xszz_sqsh" method="post" styleId="xmsqForm" onsubmit="return false;">
			<html:hidden property="guid"/>
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}"/>
			<input type="hidden" name="xh" value="${xh}"/>
			<div style='tab;width:100%;height:395px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">		
					<thead>
						<tr>
							<th colspan="4">
								<span>
									������Ŀ������Ϣ
								</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<td colspan="4">
								<div class="con_overlfow" style="width:100%;height:150px;overflow-x:hidden;overflow-y:auto;vertical-align: top;">
									<table class="dateline" width="100%">
										<thead>
											<tr>
												<td>��Ŀ���� </td>
												<td>��Ŀ��� </td>
												<td>��Ŀ��� </td>
											</tr>
										</thead>
										<tbody id="xmInfo" name="se">
										</tbody>
									</table>
								</div>
							</td>
						</tr>
					</tbody>
					<tbody>
						<tr>
							<th>ѧ��</th>
							<td>${currXn}</td>
							<th>ѧ��</th>
							<td>${currXq}</td>
						</tr>
						<tr>
							<th>
								������Ϣ
							</th>
							<td colspan="3">
								<logic:equal value="10335" name="xxdm">
									<span style="color: blue; line-height:20px;display:block;">
										����ѧԺ(԰)֪ͨҪ������ȷ�����ϴ�������Ҫ���ϴ������޸���Ҫ���������ϴ���
									</span>
									&nbsp;
								</logic:equal>
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
					</tbody>
					<%@ include file="/xsgzgl/xszz/bdpz/mkxxUpdate.jsp" %>
				</table>
			</div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" id="save_button" type="button" onclick="saveXmsq('save');">
										����ݸ�
									</button>
									<button type="button" id="save_button" type="button" onclick="saveXmsq('submit');">
										�ύ����
									</button>
									<button type="button" name="�� ��" onclick="iFClose();">
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

