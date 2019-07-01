<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
            //��������
            function saveForm(){
                var checkId = 'xmdm-xmmc-cjhgfsx';
                if(!checkNotNull(checkId)){
                    showAlertDivLayer("�뽫��������д������");
                    return false;
                }
                var xmdm =jQuery("#xmdm").val();
                if(xmdm.length>4){
                    showAlertDivLayer("��Ŀ���볤�ȹ�����");
                    return false;
                }
                var cjhgfsx =jQuery("#cjhgfsx").val();
                if(cjhgfsx.length>2){
                    showAlertDivLayer("�ɼ��ϸ�����ж��ֶγ��ȹ�����");
                    return false;
                }
                var xsxh =jQuery("#xsxh").val();
                if(xsxh.length>2){
                    showAlertDivLayer("��ʾ����ֶγ��ȹ�����");
                    return false;
                }
                var url = "dycjgl_dmwh.do?method=saveDmwh";
                ajaxSubFormWithFun("DydmwhForm",url,function(data){
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


	<body >
		<html:form action="/dycjgl_dmwh" method="post" styleId="DydmwhForm" onsubmit="return false;">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>���ӵ����ɼ���Ŀ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>�����ɼ����ʹ���
							</th>
							<td width="34%">
								<html:text property="xmdm" styleId="xmdm" maxlength="10" styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>�����ɼ���������
							</th>
							<td width="34%">
								<html:text property="xmmc" styleId="xmmc" maxlength="30" styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>	�ɼ��ϸ�������ж�
							</th>
							<td width="34%">
								<html:text property="cjhgfsx" styleId="cjhgfsx" maxlength="10" onblur="checkInputNum(this)" styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th width="16%">
								��ʾ���
							</th>
							<td width="34%">
								<html:text property="xsxh" styleId="xsxh" maxlength="10" onblur="checkInputNum(this)" styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th>����˵��<br/><span class="red">����500�֣�</span></th>
							<td colspan="3">
								<textarea name="xmsm" id="xmsm" rows="4" cols="3" style="width:99%" onblur="checkLen(this,500);"></textarea>
							</td>
						</tr>

						
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();return false;">
										�� ��
									</button>
									<button type="button" type="button" onclick="iFClose();return false;">
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

