<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/comm/searchTj.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				// ===== ��ȡ�߼���ѯ���� begin========
				var api = frameElement.api, W = api.opener;
				var html = "<div class='adv_filter' style='display: none;'>" + jQuery("div[class=adv_filter]", W.document).eq(0).html() + "</div>";
				html += "<div id='searchTjDiv' style='display: none;'>" + jQuery("#searchTjDiv", W.document).html() + "</div>";
				jQuery("#cxtjPlHidden").html(html);
				// ===== ��ȡ�߼���ѯ���� end========
			});
			
			function saveForm(){
				var zcsj = jQuery("#zcsj").val();
				if(zcsj == ""){
					showAlert("ע��ʱ�䲻��Ϊ�գ�");
					return false;
				}
				// ��ȡ�߼���ѯ�����������أ����ڡ����ݹ�ѡ��¼��������������ʹ��
				var api = frameElement.api, W = api.opener;
				var pksPlHidden = jQuery("#pksPlHidden", W.document).val();
				jQuery("#pksPlHidden").val(pksPlHidden);
				var url = "dtjs_tyzc.do?method=tyzcPlzc&type=save";
				// ���Ϊ�գ���ô�����ݲ�ѯ�����������������
				if(pksPlHidden == ""){
					//setSearchTj();//���ø߼���ѯ����
					url = addSuperSearchParams(url);//���ø߼���ѯ����
				}
				ajaxSubFormWithFun("tyzcForm",url,function(data){
					if (data["message"]=="����ɹ���"){
			    		  showAlert("ע��ɹ���",{},{"clkFun":function(){
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
		<html:form action="/dtjs_tyzc" method="post" styleId="tyzcForm">
			<div id="cxtjPlHidden" style="height: 0px;"></div>
			<input type="hidden" name="pks" id="pksPlHidden" value="" />
			<input type="hidden" id="path" value="${path }" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:219px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<tbody>
						<tr>
							<th width="30%">
								��ʾ��
							</th>
							<td width="70%">
								��ǰ��ѡ<font class='red'>${len }</font>��ѧ��
							</td>
						</tr>
						<tr>
							<th width="30%">
								<span class="red">*</span>ע��ʱ��
							</th>
							<td width="70%">
								<input type="text" name="zcsj" value="${zcsj}" id="zcsj"  style="width:150px" 
								onclick="return showCalendar('zcsj','yyyy-MM-dd',true);" 
								readonly="true"/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		
			<div>
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
										ȷ�� 
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


