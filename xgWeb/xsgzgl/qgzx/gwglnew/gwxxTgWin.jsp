<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/comm/searchTj.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
			jQuery(document).ready(function(){
				jQuery('#tggw').html("��ǰ��ѡ<font class='red'>${len }</font>����λ");

				// ===== ��ȡ�߼���ѯ���� begin========
				var api = frameElement.api, W = api.opener;
				var html = "<div class='adv_filter' style='display: none;'>" + jQuery("div[class=adv_filter]", W.document).eq(0).html() + "</div>";
				html += "<div id='searchTjDiv' style='display: none;'>" + jQuery("#searchTjDiv", W.document).html() + "</div>";
				jQuery("#cxtjPlHidden").html(html);
				// ===== ��ȡ�߼���ѯ���� end========
			});

			//�����λ�����˸�
			function bcTggwxx(){
				if($("tgyy") && $("tgyy").value==""){
					showAlert("�˸�ԭ����Ϊ�գ�");
			 		return false;
				}
				// ��ȡ�߼���ѯ�����������أ����ڡ����ݹ�ѡ��¼��������������ʹ��
				var api = frameElement.api, W = api.opener;
				var gwdmPlHidden = jQuery("#gwdmPlHidden", W.document).val();
				jQuery("#gwdmPlHidden").val(gwdmPlHidden);
				var url = "qgzx_gwglnew_ajax.do?method=bcTggwxx";
				// ���Ϊ�գ���ô�����ݲ�ѯ�����������������
				if(gwdmPlHidden == ""){
					//setSearchTj();//���ø߼���ѯ����
					url = addSuperSearchParams(url);//���ø߼���ѯ����
				}
				ajaxSubFormWithFun("gwxxTgWinForm",url,function(data){
				   	if(data["message"]=="�����ɹ�"){
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
		</script>
	</head>
	<body>
		<html:form action="/qgzx_gwglnew" method="post" styleId="gwxxTgWinForm">
			<div id="cxtjPlHidden" style="height: 0px;"></div>
			<input type="hidden" name="gwdm" id="gwdmPlHidden" value="" />
			<input type="hidden" id="path" value="${path }" />
			<div style="height:190px;overflow-x:hidden;overflow-y:auto;">
				<table align="center" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>�����˸�</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								��ѡ��λ
							</th>
							<td>
								<font id="tggw"></font>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>�˸�ԭ��<br/><font color="red">(��1000��)</font>
							</th>
							<td>
								<textarea name='tgyy' id="tgyy" style="word-break:break-all;width:97%" onblur="chLeng(this,1000);"
									rows='5'></textarea>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" name="����" onclick="bcTggwxx();">
										����
									</button>
									<button type="button" name="�� ��" onclick="iFClose();">
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

