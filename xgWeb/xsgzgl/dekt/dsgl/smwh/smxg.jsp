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
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
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


//�޸�--����
function saveForm(){
	var checkId ="nj-ssm-cbs-author-lx";
	if(!check(checkId)){
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
	var url = "dekt_smwhgl.do?method=update&type=update";
	ajaxSubFormWithFun("smwhForm", url, function(data) {
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
	<body>
		<html:form method="post" styleId="smwhForm" action="/dekt_smwhgl"
			enctype="multipart/form-data">
			<html:hidden property="smid"  styleId="smid"/>
			<div style='width: 100%; overflow-x: hidden; overflow-y: auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�鱾��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right">
								<span class="red">*</span>�꼶
							</th>
							<td align="left" >
									<select name="nj" id="nj" style="width:150px;">
									<option value="">--��ѡ��--</option>	
									<option value="��һ" <logic:equal value="��һ" name="nj">selected = "selected" </logic:equal>>��һ</option>
									<option value="���" <logic:equal value="���" name="nj">selected = "selected" </logic:equal>>���</option>
									<option value="����" <logic:equal value="����" name="nj">selected = "selected" </logic:equal>>����</option>
									<option value="����" <logic:equal value="����" name="nj">selected = "selected" </logic:equal>>����</option>
								</select>
							</td>
							<th align="right">
								<span class="red">*</span>����
							</th>
							<td align="left" >
								<html:text styleId="ssm" property="ssm"  maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								<span class="red">*</span>������
							</th>
							<td align="left" >
								<html:text styleId="cbs" property="cbs"  maxlength="50"/>
							</td>
							<th align="right">
								<span class="red">*</span>����
							</th>
							<td align="left" >
								<html:text styleId="author" property="author"  maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								<span class="red">*</span>����
							</th>
							<td align="left" >
								<select name="lx" id="lx" style="width:150px;">
									<option value="">--��ѡ��--</option>	
									<option value="ѡ��" <logic:equal value="ѡ��" name="lx">selected = "selected" </logic:equal>>ѡ��</option>
									<option value="�ض�" <logic:equal value="�ض�" name="lx">selected = "selected" </logic:equal>>�ض�</option>
								</select>
							</td>
							<th align="right">
								����������
							</th>
							<td align="left" >
								<html:text styleId="ebook" property="ebook"  maxlength="500"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								�Ƿ��Ƽ�
							</th>
							<td align="left" >
								<html:radio property="sftj" value="1">��</html:radio>
								<html:radio property="sftj" value="0">��</html:radio>
							</td>
						</tr>
					<tr>
						<th align="right">
							�����ϴ�
						</th>
						<td colspan="3">
							<html:hidden property="stp" styleId="stp" />
							<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
							<script type="text/javascript">
                                //���ø���
                                jQuery(function(){
                                    jQuery.MultiUploader({
                                        maxcount : 1,
                                        //��׺
                                        accept : 'png|gif|jpg',
                                        //����ļ���С ��λM
                                        maxsize: 4,
                                        //��Ÿ������������id
                                        elementid : 'stp'
                                    });
                                });
							</script>
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
									<button type="button" onclick="saveForm();">
										��    ��
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
