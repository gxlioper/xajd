<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
			<script type="text/javascript">
/**
 * ��֤�Ƿ���ڿ���
 * @param ids Ҫ��֤�Ŀؼ�id "-"�ָ�
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}


//�޸�--����ݸ���ύ
function saveXg(url){
	var checkId ="xh-ssm-xd";
	if(!check(checkId)){
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
	var xd = jQuery("#xd").val();
	if(xd.length < 200||xd.length > 1000){
		return showAlert("�����ĵ�������������ΧΪ200-1000��");
	}
	ajaxSubFormWithFun("dsglsqForm", url, function(data) {
		if (data["message"] == "����ɹ���") {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		} else {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		}
	});
}
		
		</script>
	</head>
	<body onpaste="return false">
		<html:form method="post" styleId="dsglsqForm" action="/dekt_dsglsq"
			enctype="multipart/form-data">
			<html:hidden property="sqid"  styleId="sqid"/>
			<html:hidden property="shzt" styleId="shzt" />
			<html:hidden property="xh" styleId="xh" />
			<html:hidden property="splc"/>
			<div style='width: 100%; overflow-x: hidden; overflow-y: auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
						<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>�鱾��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="10%">
								ѧ��
							</th>
							<td align="left">
								${dqxn}
							</td>
							<th align="right">
								ѧ��
							</th>
							<td align="left">
								${dqxq}
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								<span class="red">*</span>����
							</th>
							<td align="left" colspan="3">
								<html:text styleId="ssm" property="ssm"  maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								�����ϴ�
							</th>
							<td colspan="3">
							<html:hidden property="filepath" styleId="filepath" />
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
												elementid : 'filepath'
												});
										});
									</script>
						</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								<span class="red">*</span>�����ĵ�&nbsp;
								<br />
								<font color="red">(������200��,<br />��1000��)</font>
							</th>
							<td colspan="3">
								<html:textarea rows="7" property="xd" styleId="xd"
									style="width:97%" ></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:50px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button"
										onclick="saveXg('dekt_dsglsq.do?method=update&type=update');return false;"
										id="buttonSave">
										����ݸ�
									</button>
									<button type="button"
										onclick="saveXg('dekt_dsglsq.do?method=update&type=submit');return false;"
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
