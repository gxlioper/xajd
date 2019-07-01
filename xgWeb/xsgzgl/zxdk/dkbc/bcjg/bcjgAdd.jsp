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
			function saveForm(){
				var url = "dkbc_bcjg.do?method=save";
                if (!checkNull("xh-dclb-dcje")) {
                    return false;
                }
                if(jQuery("#dclb").val() == "02"){
                    if(!checkNull("yhdm-dkhth")){
                        return false;
                    }
                }
				var xh = jQuery("#xh").val();

				jQuery.post("dkbc_bcjg.do?method=getCountByXhAndXn",{xh:xh},function(data){
					if (Number(data) == 0){
						ajaxSubFormWithFun("bcjgForm",url,function(data){
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
								refershParent();
							}});
						});
					} else {
						showAlertDivLayer("��ѧ���Ѿ����ڽ������,��ȷ�ϣ�");
					}
				},"json");
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
            });
		</script>
	</head>
	<body>
		<html:form action="/dkbc_bcjg" method="post" styleId="bcjgForm">
		<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
			<div style='tab;width:100%;height:460px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
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
							    <input type="hidden" name="xn" value="${xn}"/>
								<span>${xn}</span>
							</td>
							<th><span class="red">*</span>��������</th>
							<td>
								<html:select property="dclb" styleId="dclb" onchange="dclxChange(this);">
									<html:options collection="dclblist" property="dm" labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr id="yhxx">
							<th><span class="red">*</span>��������</th>
							<td>
							   <html:select property="yhdm" styleId="yhdm">
									<html:option value="">��ѡ��</html:option>
									<html:options collection="yhlist" property="dm" labelProperty="mc" />
								</html:select>
							</td>
							<th><span class="red">*</span>�����ͬ��</th>
							<td>
								<html:text property="dkhth"  styleId="dkhth"maxlength="20" ></html:text>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>�������(Ԫ)</th>
							<td colspan="3">
								<html:text property="dcje" styleId="dcje" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							<th>��ҵ��λ����</th>
							<td>
								<html:text property="dwmc" styleId="dwmc" maxlength="25" ></html:text>
							</td>
							<th>������Դ���绰</th>
							<td>
								<html:text property="dwdh"  styleId="dwdh" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'')" ></html:text>
							</td>
						</tr>
						<tr>
							<th>��ҵ��λ��ַ</th>
							<td>
								<html:text property="dwdz" styleId="dwdz" maxlength="50" ></html:text>
							</td>
							<th>��������</th>
							<td>
								<html:text property="fwnx" styleId="fwnx" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							<th>��ҵ����(����)</th>
							<td>
								<html:text property="hyxz" styleId="hyxz" maxlength="50" ></html:text>
							</td>
							<th>ְ������</th>
							<td>
								<html:text property="zwmc" styleId="zwmc" maxlength="50" ></html:text>
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
                                            label: "����Э����ͬ�顢���η���֤����ί����",
                                            //��Ÿ������������id
                                            elementid : 'filepath'
                                        });
                                    });
								</script>
							</td>
					   </tr>
					</tbody>
				</table>
			</div>
			<div style="height: 5px;"></div>
			<table  width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">"<span class="red">*</span>"Ϊ������</div>
							<div class="btn">
								<button type="button" type="button" onclick="saveForm();">
									�� ��
								</button>
								<button type="button" type="button" onclick="iFClose();">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</html:form>
	</body>
</html>

