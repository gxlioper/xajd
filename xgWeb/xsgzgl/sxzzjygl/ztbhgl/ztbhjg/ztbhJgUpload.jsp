<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/sxzzjygl/ztbhgl/ztbhjg/js/ztbhJg.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/provicecitylocal.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<link rel="stylesheet" href="js/provicecitylocal.css" type="text/css"/>
		<script type='text/javascript'>
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/ztbhgl_ztbhjg" method="post" styleId="ZtbhJgForm" onsubmit="return false;">
			<html:hidden property="jgid" styleId="jgid"></html:hidden>
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
								�����
							</th>
							<td width="30%">
								${model.hdmc}
							</td>
							<th>�����</th>
							<td>
									${model.hdzt}
							</td>
						</tr>
						<tr>
							<th width="20%">
								�����
							</th>
							<td width="30%">
									${model.hdrq}
							</td>
							<th width="20%">
								�༶����
							</th>
							<td width="30%">
								${bjmc}
							</td>
						</tr>
						
						<tr>
							<th width="20%">
								�������
							</th>
							<td >
								${hdfzr}
							</td>
							<th width="20%">
							��������ϵ��ʽ
							</th>
							<td width="30%">
									${model.hdfzrlxdh}
							</td>
							
						</tr>
						<tr>
							<th width="20%">
								��������ʦ
							</th>
							<td width="30%">
									${model.fzls}
							</td>
							<th width="20%">
								������ʦ��ϵ��ʽ
							</th>
							<td width="30%">
									${model.fzlslxdh}
							</td>
						</tr>
						<tr><th width="20%">���Ŀ��
								</br><font color="red">(��500��)</font></th>
							<td colspan="3">
								<html:textarea property="bhmd" styleId="bhmd"
											   onkeypress="checkLen(this,500);"
											   style="width:99%;" readonly="true" rows="4">${model.bhmd}</html:textarea>
							</td>
					</tr>
						<tr><th width="20%">�����̣����⣩
							</br><font color="red">(��500��)</font></th>
							<td colspan="3">
								<html:textarea property="bhyc" styleId="bhyc"
											   onkeypress="checkLen(this,500);"
											   style="width:99%;"  readonly="true" rows="4">${model.bhyc}</html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td  colspan="3">
								<html:hidden property="fj" styleId="fj" />
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
                                            elementid : 'fj',

                                            eid : 'filepath_f'
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
								<button type="button" onclick="ztbhJgSaveForupload();">
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

