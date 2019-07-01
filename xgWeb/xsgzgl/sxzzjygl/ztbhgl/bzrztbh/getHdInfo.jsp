<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/sxzzjygl/ztbhgl/bzrztbh/js/ztbhJg.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/provicecitylocal.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<link rel="stylesheet" href="js/provicecitylocal.css" type="text/css"/>
		<script type='text/javascript'>
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/ztbhgl_ztbhsq" method="post" styleId="ZtbhSqForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th width="20%">
							ѧ��
						</th>
						<td width="30%">
								${xn}
						</td>
						<th width="20%">
							�����
						</th>
						<td width="30%">
							${model.hdrq}
						</td>
					</tr>
					<tr>
						<th width="20%">
							ѧ��
						</th>
						<td width="30%">
								${xqmc}
						</td>
						<th>�������</th>
						<td>
							${model.hdzt}
						</td>
					</tr>
					<tr>
						<th width="20%">
							���ʱ��
						</th>
						<td width="30%">
								${model.bhsj}
						</td>
						<th>�ص�</th>
						<td>
								${model.dd}
						</td>
					</tr>
					<%--<tr>
						<th width="20%">
							�������Ƿ�μ�
						</th>
						<td colspan="3">
							${model.bzrsfcj}
						</td>
					</tr>--%>
					</tbody>
					<thead>
					<tr>
						<th colspan="4"><span>�༶��Ϣ</span></th>
					</tr>
					</thead>
					<tbody>
					<style type="text/css">
						#shlccx_table th{text-align: center;}
						#shlccx_table tr{text-align: center;}
					</style>
					<tr>
						<td colspan="4">
							<table id="shlccx_table" width="100%">
								<tr>
									<th>רҵ�༶</th>
									<th>Ӧ������</th>
									<th>ʵ������</th>
									<th>ȱ������</th>
									<th>ѧԺ</th>
									<th>������</th>
								</tr>
								<logic:notEmpty name="bjList">
									<logic:iterate id="i" name="bjList">
										<tr>
											<td>${i.bjmc}</td>
											<td>${i.ydrs}</td>
											<td>${i.sdrs}</td>
											<td>${i.qqrs}</td>
											<td>${i.xymc}</td>
											<td>${i.fdyxm}</td>
										</tr>
									</logic:iterate>
								</logic:notEmpty>
							</table>
						</td>
					</tr>
					</tbody>
					<thead>
					<tr>
						<th colspan="4">
							<span>�������</span>
						</th>
					</tr>
					</thead>
					<tbody>
						<tr><th width="20%">�������
								</br><font color="red">(��500��)</font></th>
							<td colspan="3">
								<textarea style="width:99%;" readonly="true" rows="4">${model.bhmd}</textarea>
							</td>
					</tr>
						<tr><th width="20%">����ܽ�
							</br><font color="red">(��500��)</font></th>
							<td colspan="3">
								<textarea style="width:99%;"  readonly="true" rows="4">${model.bhyc}</textarea>
							</td>
						</tr>
						<tr>
							<th align="right">
								����
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
                                    //���ø���
                                    jQuery(function(){
                                        var gid = "${fj}";
                                        jQuery.MultiUploader_q({
                                            gid : gid,
                                            targetEl : 'commonfileupload-list-0'
                                        });
                                    });
								</script>
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
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
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
		</html:form>
	</body>
</html>

