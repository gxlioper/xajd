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
		
		<script	type="text/javascript">
		jQuery(function(){
			zdybdInit("rcsw_ylbx_add");
			jQuery("#xn").val(jQuery("#dqxn").val());

			// �������Ӳ���˵��
			var czsmHtml = '<TR><TH width="15%">�������Ӳ���˵��</TH>';
			czsmHtml += '<TD width="35%" colspan="3">��ǰ��ѡ��&nbsp;<font color="#ff0000;">'+ jQuery("#xsnum").val() +'</font>&nbsp;λѧ��</TD></TR>';
			var xnTdTemp = jQuery("td[name=zdybdcon_td_zd23]").eq(0);
			xnTdTemp.parent().before(czsmHtml);

			// ===== ��ȡ�߼���ѯ�����������أ����ڡ����ݲ�ѯ��������������ӡ�ʹ�� begin========
			var api = frameElement.api, W = api.opener;
			jQuery("#cxtjPlHidden").html(jQuery("div[class=toolbox]", W.document).eq(0).html());
			jQuery("div[class=search_advanced]").hide();
			jQuery("div[class=more--item_bottom]").hide();
			jQuery("#comp_title").hide(); // ҳǩ��ť����
			jQuery("#searchTjDiv").hide();
			jQuery("div[class=buttonbox]").hide();
			// ===== ��ȡ�߼���ѯ�����������أ����ڡ����ݲ�ѯ��������������ӡ�ʹ�� end========
		});
		
		function saveForm(){
			if(!zdybdCheck()){
				return false;
			}
			
			// ��ȡ�߼���ѯ�����������أ����ڡ����ݹ�ѡ��¼�����������ӡ�ʹ��
			var api = frameElement.api, W = api.opener;
			var xhPlHidden = jQuery("#xhPlHidden", W.document).val();
			jQuery("#xh").val(xhPlHidden);
			var ylbxzt = jQuery("#ylbxzt").val();
			var	url = "rcsw_ylbx_ylbxglgl.do?method=addYlbxglPl&type=save&ylbxzt=" + ylbxzt;
			// ���xhΪ�գ���ô�����ݲ�ѯ��������������ӡ�
			if(xhPlHidden == ""){
				setSearchTj();//���ø߼���ѯ����
				url = addSuperSearchParams(url);//���ø߼���ѯ����
			}
		     ajaxSubFormWithFun("ylbxglForm",url,function(data){
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
		</script>
	</head>
	<body>
		<html:form action="/rcsw_ylbx_ylbxglgl" method="post" styleId="ylbxglForm" onsubmit="return false">
			<div id="cxtjPlHidden" style="height: 0px;"></div>
			<input type="hidden" id="dqxn" value="${dqxn }" />
			<html:hidden property="xh" styleId="xh" />
			<html:hidden property="xsnum" styleId="xsnum" />
			<input type="hidden" value="djz" id="ylbxzt"/>
			<div style='width:100%;height:295px;overflow-x:hidden;overflow-y:auto;'>
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

