<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/sxzzjygl/bjhdgl/bjhdsq/js/bjhdSq.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<link rel="stylesheet" href="js/provicecitylocal.css" type="text/css"/>
		<script type='text/javascript'>
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/bjhdgl_bjhdsq" method="post" styleId="BjhdSqForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�༶�</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<span class="red">*</span>�����
							</th>
							<td width="30%">
								<html:text property="hdmc" styleId="hdmc" maxlength="100"></html:text>
							</td>
							<th><span class="red">*</span>�����</th>
							<td>
								<html:text property="hdzt" styleId="hdzt" maxlength="100"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>�����
							</th>
							<td width="30%">
								<html:text property="hdrq" onclick="return showCalendar('hdrq','yyyy-MM-dd HH:mm');" styleId="hdrq" ></html:text>
							</td>
							<th width="20%">
								<font color="red">*</font>�༶����
							</th>
							<td width="30%">
								<input type="text" name="bjmc" value="" id="bjmc" style="width:150px;" readonly="readonly"
									   class="text_nor"  >
								<input type="hidden" name="bjdm" id="bjdm" value=""/>
								<button class="btn_01" type="button"
										onclick="showDialog('��ѡ��һ���༶',800,500,'bjhdgl_bjhdsq.do?method=getBj');">ѡ��
								</button>
							</td>
						</tr>
						
						<tr>
							<th width="20%">
								<font color="red">*</font>�������
							</th>
							<td >
								<input type="text" name="hdfzrxm" value="" id="hdfzrxm" style="width:120px;" readonly="readonly"
									   class="text_nor">
								<input type="hidden" name="hdfzr" id="hdfzr" value=""/>
								<button class="btn_01" type="button"
										onclick="showDialog('��ѡ��һ��ѧ��',800,500,'bjhdgl_bjhdsq.do?method=getXx');">ѡ��
								</button>
							</td>
							<th width="20%">
								<font color="red">*</font>��������ϵ��ʽ
							</th>
							<td width="30%" >
								<html:text property="hdfzrlxdh" styleId="hdfzrlxdh" maxlength="20"></html:text>
							</td>
							
						</tr>
						<tr>
							<th width="20%">
								��������ʦ
							</th>
							<td width="30%">
								<html:text property="fzls" styleId="fzls" maxlength="20"></html:text>
							</td>
							<th width="20%">
								������ʦ��ϵ��ʽ
							</th>
							<td width="30%">
								<html:text property="fzlslxdh" styleId="fzlslxdh" maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								�Ԥ��
							</th>
							<td width="30%">
								<html:text property="hdys" styleId="hdys" maxlength="20"></html:text>
							</td>

						</tr>
						<tr><th width="20%">�Ԥ�����ݼ���ϸ</th>
							<td colspan="3">
								<html:textarea property="hdysyjmx" styleId="hdysyjmx"
											   onkeypress="checkLen(this,500);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
					</tr>
						<tr><th width="20%">���Ҫ</th>
							<td colspan="3">
								<html:textarea property="hdgy" styleId="hdgy"
											   onkeypress="checkLen(this,500);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
						</tr>
						<tr><th width="20%">�ʵʩ����</th>
							<td colspan="3">
								<html:textarea property="hdssfa" styleId="hdssfa"
											   onkeypress="checkLen(this,500);"
											   style="width:99%;" rows="4"></html:textarea>
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
								<button type="button" onclick="bjhdSqSaveForAdd('save');">
										����ݸ�
									</button>
									<button type="button" onclick="bjhdSqSaveForAdd('submit');">
										�ύ����
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

