<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript">
			function saveForm(url){
				 var xxdm = jQuery("#xxdm").val();
                var xh = jQuery("#xh").val();
                if (!checkNull("xh-dclb-dcje")) {
                    return false;
                }
                if(jQuery("#dclb").val() == "02"){
                    if(!checkNull("yhdm-dkhth")){
                        return false;
                    }
                }
				 
				/*var dcje = jQuery("#dcje").val();
				if(dcje>32000){
					showAlertDivLayer("�����������Ϊ32000");
					return false;
				}*/
				ajaxSubFormWithFun("rwfbysqshForm",url,function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						refershParent();
					}});
				});
			}
            function dclxChange(target){
                if(target.value == "01"){
                    jQuery("#yhxx").hide();
                } else {
                    jQuery("#yhxx").show();
                }
            }
            jQuery(function(){
                dclxChange(document.getElementById("dclb"));
            })
		</script>
	</head>
	<body>
		<html:form action="/rwfby_sqsh" method="post" styleId="rwfbysqshForm">
			<html:hidden property="id" />
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
			<div style='tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;'>
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
								<span>�����</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>����ѧ��</th>
							<td>
							    <html:hidden property="xn" styleId="xn"/>
								<span>${rwfbysqshForm.xn}</span>
							</td>
							<th><span class="red">*</span>��������</th>
							<td>
								<html:select property="dclb" styleId="dclb" onchange="dclxChange(this);">
									<html:options collection="dclblist" property="dm" labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr id="yhxx">
							<th>��������</th>
							<td>
							   <html:select property="yhdm" styleId="yhdm">
									<html:option value="">��ѡ��</html:option>
									<html:options collection="yhlist" property="dm" labelProperty="mc" />
								</html:select>
							</td>
							<th>�����ͬ��</th>
							<td>
								 <html:text property="dkhth"  styleId="dkhth" maxlength="20" ></html:text>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>���벹�����(Ԫ)</th>
							<td colspan="3">
								<html:text property="dcje" styleId="dcje" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								֤������
							</th>
							<td colspan="3">
								<html:hidden property="filepath" styleId="filepath" />
								<input type="file" id="filepath_f" name="filepath" />
								<script type="text/javascript">
                                    //���ø���
                                    jQuery(function(){
                                        jQuery("#filepath_f").multiUploader({
                                            maxcount : 3,
                                            //��׺
                                            accept : 'png|gif|jpg|zip|rar|doc|docx',
                                            //����ļ���С ��λM
                                            maxsize: 10,
                                            label: "���ϴ�����֤����¼ȡ֪ͨ�鸴ӡ������Ϣ",
                                            //��Ÿ������������id
                                            elementid : 'filepath'
                                        });
                                    });
								</script>
							</td>
					   </tr>
					</tbody>
					</table>
					<div class="tab"  id="content" style="margin-top: 5px; overflow-x:hidden;" >
				</div>
				<div style="height: 20px">
				</div>
			</div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="saveForm('rwfby_sqsh.do?method=update');">
										����ݸ�
									</button>
									<button type="button" onclick="saveForm('rwfby_sqsh.do?method=saveAndSubmit');">
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

