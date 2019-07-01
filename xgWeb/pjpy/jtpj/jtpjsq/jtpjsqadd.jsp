<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="pjpy/jtpj/jtpjsq/js/jtpjsq.js"></script>
		<script type="text/javascript" src="js/comm/jcsjld.js"></script>
		<script type="text/javascript" src="pjpy/jtpj/js/jtpjyz.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				jQuery("#jxid").bind("change",function(){
					loadJxxx();
					jxidChange();
				});
				jQuery("#jxid").change();
			});
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/jtpjsq.do?method=add&type=save">
			<html:hidden property="splcid" styleId="splcid"/>
			<input type="hidden" id="mrpjjtiid"/> 
			<html:hidden property="type" value="add" styleId="type"/>
			<html:hidden property="sqid" styleId="sqid"/>
			<input type="hidden" id="first" value="0"/>
			<div style='tab;width:100%;height:460px;overflow-x:hidden;overflow-y:auto;'>
				<table width="" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>���影��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								����ѧ��
							</th>
							<td width="30%" id="sqxn">
								${data.sqxn}
							</td>
							<th width="20%">
								����ѧ��
							</th>
							<td width="30%" id="sqxq">
								${xqmc}
							</td>
						</tr>
						<tr>
							<th >
								<span class="red">*</span>���뽱��
							</th>
							<td>
								<html:select property="jxid" styleId="jxid"	style="width:200px;">
									<html:options collection="jxList" property="jxid" labelProperty="jxmc" />
								</html:select>
							</td>
							<th >
								��������
							</th>
							<td id="pdzq">
							</td>
						</tr>
						<tr>
							<th>
								����˵��
							</th>
							<td colspan="3" id="jxsm">
							</td>
						</tr>
					</tbody>
				</table>
				<div id="jtpjxx">
				
				</div>
				<div id="divyzts">
					
				</div>
				<table width="" border="0" class="formlist">
					<tr>
						<th>
							����
						</th>
						<td colspan="3">
							<html:hidden property="filepath" styleId="fjid"/>
							<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
							<script type="text/javascript">
								//���ø��� 
								jQuery(function(){
									jQuery.MultiUploader({
										maxcount : 3,
										//��׺
										accept : 'png|gif|jpg|zip|rar|doc|docx',
										//����ļ���С ��λM
										maxsize: 10,
										//��Ÿ������������id
										elementid : 'fjid'
										});
								});
							</script>						
						</td>
					</tr>
					<tr>
						<th width="20%" style="border-top: 0px;">
							<span class="red">*</span>��������
							<br />
							<span class="red">(��500��)</span>
						</th>
						<td colspan="3" style="border-top: 0px;">
							<html:textarea  property="sqly" rows="4" cols="70" styleId="sqly" onblur="checkLen(this,500);"></html:textarea>
						</td>
					</tr>
				</table>
			</div>
			<div style="hight: 30px"></div>
			<div>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" style="display:none"
										onclick="save('jtpjsq.do?method=add&type=save','jxid-pjjtid-pjjtmc-sqly');return false;"
										id="buttonSave">
										����ݸ�
									</button>
									<button type="button"
										onclick="save('jtpjsq.do?method=tj&type=save','jxid-pjjtid-pjjtmc-sqly');return false;"
										id="buttonSave">
										�ύ����
									</button>
									<button type="button" onclick="iFClose();" id="buttonClose">
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
