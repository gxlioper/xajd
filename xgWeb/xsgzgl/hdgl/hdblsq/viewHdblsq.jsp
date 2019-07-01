<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/hdgl/js/hdblsq.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type='text/javascript'>
			jQuery(function(){
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${rs.sqid}&tt="+ new Date().getTime());
				var hdxs = '${rs.hdxs}';
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
                }
                kcjbChange();
			});
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/hdgl_hdblsq" method="post" styleId="hdblsqshForm" onsubmit="return false;">
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
								${rs.xn}
							</td>
							<th>ѧ��</th>
							<td>
								${rs.xqmc}
							</td>
						</tr>
						<tr>
							<th width="15%">
								�����
							</th>
							<td width="35%">
								${rs.hdmc}
							</td>
							<th>�ʱ��</th>
							<td>
								${rs.hdsj}
							</td>
						</tr>
						<tr>
							<th>
								���췽
							</th>
							<td colspan="3">
									${rs.zbf}
							</td>
						</tr>
						<tr>
							<th width="15%">
								����or���»
							</th>
							<td width="35%">
									${rs.xsxxlx}
							</td>
							<th>���ʽ</th>
							<td>
									${rs.hdkclx}
							</td>
						</tr>
						<tr>
							<th width="15%">
								�����
							</th>
							<td width="35%">
									${rs.hdxs}
							</td>
							<th>�����</th>
							<td>
									${rs.hdlxmc}
							</td>
						</tr>

						<tr name="zjrxx_tr">
							<th>
								����������
							</th>
							<td>
									${rs.zjrxm}
							</td>
							<th >
								�����˵�λ
							</th>
							<td >
									${rs.zjrdw}
							</td>
						</tr>
						<tr name="zjrxx_tr">
							<th>
								������ְ��
							</th>
							<td>
									${rs.zjrzc}
							</td>
							<th >
								������ְ��
							</th>
							<td >
									${rs.zjrzw}
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td colspan="3">
									${rs.jzjb}
							</td>

						</tr>
						<tr name="zjrxx_tr">
							<th>
								�����˽���
							</th>
							<td colspan="3">
									${rs.zjrjs}
							</td>

						</tr>

						<tr id="jzlxTr">
							<th width="15%">
								�γ̼���
							</th>
							<td width="35%">
								<html:hidden property="jzlx" styleId="jzlx"/>
									${rs.jzlxmc}
							</td>
							<th width="15%" id="zxkclxTh" style="display: none">
								��ѡ�γ�����
							</th>
							<td width="35%" id="zxkclxTd" style="display: none">
									${rs.zxkclxmc}
							</td>
						</tr>
						<tr>
							<th>
								���ǩ
							</th>
							<td colspan="3">
									${rs.hdbqmc}
							</td>
						</tr>
						<tr>
							<th>
								������ǩ
							</th>
							<td colspan="3">
									${rs.nlbqmc}
							</td>
						</tr>
						<tr>
							<th width="15%">
								��ص�
							</th>
							<td width="35%">
								${rs.hddd}
							</td>
							<th>�μ�����</th>
							<td>
								${rs.cjlx}
							</td>
						</tr>
						<tr>
							<th width="15%">
								���ְ��
							</th>
							<td width="35%">
								${rs.zdzw}
							</td>
							<th>�ְ��</th>
							<td>
								${rs.hdzw}
							</td>
						</tr>
						<tr>
							<th width="15%">
								��ý���
							</th>
							<td width="35%">
								${rs.hdjx}
							</td>
							<th>������ѧ��</th>
							<td>
								${rs.hdxf}
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
								${rs.bz}
							</td>
			      		</tr>						
					</tbody>
				 </table>			
			 <logic:notEqual value="�������" name="shztmc">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>�����Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="4" id="shlccx">

								</td>
							</tr>

						</tbody>

					</table>
				</logic:notEqual>
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

