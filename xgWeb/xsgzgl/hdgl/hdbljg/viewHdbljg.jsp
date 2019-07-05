<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/hdgl/js/hdbljg.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type='text/javascript'>
			jQuery(function(){
				var hdxs = '${hdbljgForm.hdxs}';
                if("�γ�" == hdxs){
                    jQuery("#jzlxTr").show();
                    jQuery("tr[name='zjrxx_tr']").hide();
                }else if("����" == hdxs){

                    jQuery("tr[name='zjrxx_tr']").show();
                    jQuery("#lx_span").html("��������");
                    jQuery("#con_span").html("��������");

                    jQuery("#jzlxTr").hide();
                }else{
                    jQuery("tr[name='zjrxx_tr']").hide();
                    jQuery("#jzlxTr").hide();
                    var hdlx = '${hdbljgForm.hdlx}';
                    if ("�"==hdxs && "4"==hdlx){
                        jQuery("#zysc").show();
                    }else {
                        jQuery("#zysc").hide();
                    }
                }
                kcjbChange();
			});
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/hdgl_hdbljg" method="post" styleId="hdbljgForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;height: 500px' >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>���¼������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="15%">
								ѧ��
							</th>
							<td width="35%">
								${hdbljgForm.xn}
							</td>
							<th>ѧ��</th>
							<td>
								${hdbljgForm.xqmc}
							</td>
						</tr>
						<tr>
							<th width="15%">
								�����
							</th>
							<td width="35%">
								${hdbljgForm.hdmc}
							</td>
							<th>�ʱ��</th>
							<td>
								${hdbljgForm.hdsj}
							</td>
						</tr>
						<tr>
							<th>
								���췽
							</th>
							<td colspan="3">
									${hdbljgForm.zbf}
							</td>
						</tr>
						<tr>
							<th width="15%">
								����or���»
							</th>
							<td width="35%">
									${hdbljgForm.xsxxlx}
							</td>
							<th>���ʽ</th>
							<td>
								${hdbljgForm.hdkclx}
							</td>
						</tr>
						<tr>
							<th width="15%">
								�����
							</th>
							<td width="35%">
								${hdbljgForm.hdxs}
							</td>
							<th>�����</th>
							<td>
								${hdbljgForm.hdlxmc}
							</td>
						</tr>

						<tr name="zjrxx_tr">
							<th>
								����������
							</th>
							<td>
									${hdbljgForm.zjrxm}
							</td>
							<th >
								�����˵�λ
							</th>
							<td >
									${hdbljgForm.zjrdw}
							</td>
						</tr>
						<tr name="zjrxx_tr">
							<th>
								������ְ��
							</th>
							<td>
									${hdbljgForm.zjrzc}
							</td>
							<th >
								������ְ��
							</th>
							<td >
									${hdbljgForm.zjrzw}
							</td>
						</tr>
						<tr name="zjrxx_tr">
							<th>
								��������
							</th>
							<td colspan="3">
									${hdbljgForm.jzjb}
							</td>

						</tr>
						<tr name="zjrxx_tr">
							<th>
								�����˽���
							</th>
							<td colspan="3">
									${hdbljgForm.zjrjs}
							</td>

						</tr>

						<tr id="jzlxTr">
							<th width="15%">
								�γ̼���
							</th>
							<td width="35%">
								<html:hidden property="jzlx" styleId="jzlx"/>
								${hdbljgForm.jzlxmc}
							</td>
							<th width="15%" id="zxkclxTh" style="display: none">
								��ѡ�γ�����
							</th>
							<td width="35%" id="zxkclxTd" style="display: none">
									${hdbljgForm.zxkclxmc}
							</td>
						</tr>
						<tr>
							<th>
								���ǩ
							</th>
							<td colspan="3">
								${hdbljgForm.hdbqmc}
							</td>
						</tr>
						<tr>
							<th>
								������ǩ
							</th>
							<td colspan="3">
								${hdbljgForm.nlbqmc}
							</td>
						</tr>
						<tr>
							<th width="15%">
								��ص�
							</th>
							<td width="35%">
								${hdbljgForm.hddd}
							</td>
							<th>�μ�����</th>
							<td>
								${hdbljgForm.cjlx}
							</td>
						</tr>
						<tr>
							<th width="15%">
								���ְ��
							</th>
							<td width="35%">
								${hdbljgForm.zdzw}
							</td>
							<th>�ְ��</th>
							<td>
								${hdbljgForm.hdzw}
							</td>
						</tr>
						<tr>
							<th width="15%">
								��ý���
							</th>
							<td width="35%">
								${hdbljgForm.hdjx}
							</td>
							<th>������ѧ��</th>
							<td>
								${hdbljgForm.hdxf}
							</td>
						</tr>
						<tr id="zysc">
							<th width="15%">
								־Ըʱ��
							</th>
							<td colspan="3">
								${hdbljgForm.zyxss}
							</td>
						</tr>
			      		<tr>
							<th>
								����
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden property="fjpath" styleId="fjid"/>
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										var gid = jQuery('#fjid').val();
										jQuery.MultiUploader_q({
											gid : gid
											});
									});
								</script>						
							</td>
						</tr>
						<tr>
							<th>
								<span id="con_span">����ݼ��ĵ�</span>
							</th>
							<td colspan="3">
								${hdbljgForm.bz}
							</td>
			      		</tr>						
					</tbody>
				 </table>			
			</div>
			<div style="height:35px"></div>   
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
									<button type="button" onclick="iFClose();">
										�ر�
									</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

