<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
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
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">

			jQuery(function(){
				onShow("rcsw_ylbx_query");
				// ========== ���� < ============
				var filepathtd = jQuery("td[name='zdybdcon_td_filepathtd']").eq(0);
				if(filepathtd.html() != null) {
					var filepathtdV = jQuery.trim(filepathtd.html().replace('&nbsp;','')); // �ȱ���
					var filepathHiddenHtml = jQuery("#filepathHiddenDiv").html();
					filepathtd.html(filepathHiddenHtml);
					jQuery("#filepathHiddenDiv").html("");
					jQuery.MultiUploader_q({
						gid : filepathtdV,
						targetEl : 'commonfileupload-list-0'
					});
				}
				// ========== ���� > ============
				var xxdm = jQuery("#xxdm").val();
				//��������ְҵ����ѧԺ
				if(xxdm == "13871"){
					sfblview();
				}
			});

			//ҽ�Ʊ��ս����Ϣ
			function onShow(gndm) {
				var url = "rcsw_ylbx_ylbxjggl.do?method=ylbxXx";
				jQuery.ajax( {
					type : "post",
					async : false,
					url : url,
					data : {
						id : jQuery("#jgid").val()
					},
					dataType : "json",
					success : function(data) {
						zdybdInit(gndm, data);
					}
				});
			}
		
			function sfblview(){
				var sfbl=jQuery("[name='zdybdcon_td_zd1']").html().trim().replace(/&nbsp;/gi,'');
				if(sfbl=="��Ը����"){
					jQuery("[name='zdybdcon_th_zd4']").eq(0).parent().hide();
				}else	{
					jQuery("[name='zdybdcon_th_zd3']").text("");
					jQuery("[name='zdybdcon_th_zd4']").eq(0).parent().show();
				}
			}
			
		</script>

	</head>
	<body>

		<html:form action="/rcsw_ylbx_ylbxjggl" method="post" styleId="ylbxjgForm">
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }"/>
			<div id="filepathHiddenDiv" style="display: none;">
				<div id="commonfileupload-list-0" style="padding: 5px;"></div>
			</div>
			<html:hidden property="jgid"  styleId="jgid"/>
			<div style='width:100%;height:365px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/ylbx/comm/viewStudent.jsp" %>
				</table>
				<div class=""  id="content" style="margin-top: 5px; overflow-x:hidden;" >
				</div>
			</div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" type="button" onclick="iFClose();">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</table>
		</html:form>
	</body>
</html>

