<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript">
jQuery(function(){
	stzdlsInit();
	//jQuery("#stzdlsxm").val('${zgh}');
    hideNdzzztTd();
});
		//ɾ����
function delRow(){
	var obj = jQuery("[name='chk']:checked").parent().parent();
	if(obj.length == 0){
		showAlert("����ѡ���¼���ٽ���ɾ��������");
		return false;
	}
	jQuery(obj).remove();
	jQuery("[name='chkall']").removeAttr("checked");
}

//��ʦ�������ѧ��
function addRowDialog(){
    var url = "ttgl_stgl.do?method=selectStu";
    var title = "������ѡ��";
	showDialog(title, 770, 550, url);
}

function selectAll(obj){
	if(obj.checked){
		jQuery("[name='chk']").attr("checked","checked");
	}else{
		jQuery("[name='chk']").removeAttr("checked");
	}
}		
function stzdlsInit(){
	var autoSetting = {
		dataTable:"fdyxxb",
		dataField:"xm",
		dataFieldKey:"zgh",
		dataFieldKeyId:"zgh",
		scrollHeight:180										
	}
	// ģ��������������Ŀ���ơ�
	jQuery("#stzdlsxm").setAutocomplete(autoSetting);
}
function saveForm(url){	
	 var checkids ="stlx-stqc-stjc-styx-ywzddw-zgh-stjs";
	 if(!checkNotNull(checkids)){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����!");
		return false;
	 }
	 var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
	 if(!reg.test(jQuery("#styx").val())){
		 showAlert("�����ʽ����ȷ!");
		 return false;
	 }
	if(jQuery("[name='xh']").length == 0){
			showAlert("��������дһλ���Ÿ�����!");
			return false;
	}else{
		if(jQuery("[name='xh']").length >5){
				showAlert("���Ÿ����˲�����5��!");
				return false;
		}
	}
	
	var url = "ttgl_stgl.do?method=updatest&type=update";
	ajaxSubFormWithFun("stglForm", url, function(data) {
		if (data["message"] == "����ɹ���") {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		} else {
			showAlert(data["message"]);
		}
	});
	}
/**
 * �����֯״̬td��������ʾ
 */
function hideNdzzztTd(){
    jQuery("#stlx").change(function(e){
        var b = jQuery("#stlx").val();
        var ndzzzt = jQuery("#ndzzzt");
        if(b === "ѧ������"){
            ndzzzt.removeAttr('style')
        } else {
            ndzzzt.attr('style','display:none');
        }
    })
    jQuery("#stlx").trigger('change');
}
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/ttgl_stgl" method="post" styleId="stglForm" onsubmit="return false;">
		<html:hidden property="jgid"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
					<tr>
						<th colspan="4">
							<span>ѧ����֯��Ϣ</span>
						</th>
					</tr>
					</thead>
					<tbody>
					<tr>
						<th width="20%"><font color="red">*</font>ѧ����֯ȫ��</th>
						<td width="30%">
							<html:text styleId="stqc" property="stqc"  maxlength="50"/>
						</td>
						<th width="20%"><font color="red">*</font>ѧ����֯���</th>
						<td width="30%">
							<html:text styleId="stjc" property="stjc"  maxlength="50"/>
						</td>
					</tr>
					<tr>
						<th width="20%"><font color="red">*</font>����ʱ��</th>
						<td width="30%">
							<html:text  property="clsj" styleId="clsj"   size="10"
										onclick="return showCalendar('clsj','y-mm-dd',true,'clsj');"
										onblur="dateFormatChg(this)" readonly="true"></html:text>
						</td>
						<th width="20%"><font color="red">*</font>��֯����</th>
						<td width="30%">
							<html:text styleId="strs" property="strs"  maxlength="50"/>
						</td>
					</tr>
					<tr>
						<th><font color="red">*</font>ѧ����֯����</th>
						<td>
							<html:text styleId="styx" property="styx"  maxlength="50"/>
						</td>
						<th>ѧ����֯���ں�</th>
						<td>
							<html:text styleId="gzh" property="gzh"  maxlength="50"/>
						</td>
					</tr>
					<tr>
						<th><font color="red">*</font>ָ����ʦ</th>
						<td>
							<input type='hidden' id="zgh" name="stzdls" value="${zgh}" />
							<input type="text" class="form-control" id="stzdlsxm" value="${zdls}">
						</td>
						<th><font color="red">*</font>ָ����λ</th>
						<td>
							<html:select property="ywzddw" style="width:150px" styleId="ywzddw">
								<html:option value="">--��ѡ��--</html:option>
								<html:options collection="bmList" property="bmdm" labelProperty="bmqc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>�칫�ҵ�ַ</th>
						<td colspan="3">
							<html:text styleId="bgsdz" property="bgsdz" style="width:200px" maxlength="50"/>
						</td>
					</tr>
					<tr>
						<th>ѧ����֯������Դ</th>
						<td colspan="3">
							<logic:iterate id="item" collection="${xszzjflyList}">
								<html:checkbox property="jflyArray" value="${item.dm}">${item.mc}</html:checkbox>
							</logic:iterate>
								<%--��̨��������checkboxѡ��--%>
							<script type="text/javascript">
                                jQuery(function(){
                                    var r = '${stglForm.jfly}';
                                    var result = r.split(",");
                                    for(var i=0;i<result.length;i++){
                                        jQuery("input[value='"+result[i]+"'").attr("checked","checked");
                                    }
                                })
							</script>
						</td>
					</tr>
					<tr>
						<th>��֯����</th>
						<td colspan="3">
							<html:select property="stlx" style="width:150px" styleId="stlx">
								<html:option value="У��ѧ������֯"></html:option>
								<html:option value="Ժ��ѧ������֯"></html:option>
								<html:option value="ѧ������"></html:option>
							</html:select>
						</td>
					</tr>
					<tr id="ndzzzt" style="display:none">
						<th>�����֯״̬</th>
						<td colspan="3">
							<html:select property="xn" style="width:150px" styleId="xn">
								<html:option value="${currXn}"></html:option>
							</html:select>
							<html:select property="ndzzzt" style="width:150px" styleId="ndzzzt">
								<html:option value="">--��ѡ��--</html:option>
								<html:options collection="ndzztList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>��֯���</th>
						<td colspan="3">
							<html:select property="zzlb" style="width:150px" styleId="zzlb">
								<html:options collection="zzlbList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th><font color="red">*</font>ѧ����֯��ּ<br><font color="red">����������200��</font><br/></th>
						<td colspan="3">
							<html:textarea property="stjs" styleId="stjs" onblur="chLengs(this,200);"
										   style="width:90%;" rows="5"></html:textarea>
						</td>
					</tr>
					</tbody>
					<thead>
					<tr class="h">
						<th colspan="4">
							<span><font color="red">*</font>ѧ����֯��һ������</span>
							<div class="btn">
								<button type="button" onclick="addRow('tablebody1');return false;" style="float:left">����</button>
								<button type="button" onclick="delRow();return false;" style="float:left">ɾ��</button>
							</div>
						</th>
					</tr>
					</thead>
					<tbody>
						<tr class="h">
							<td colspan="7">
								<table width="100%" >
									<thead>
										<tr>
											<th width='5%'><input name='chkAll' onclick="selectAll(this)" type="checkbox" /></th>
											<th width='10%' style="text-align:center">ѧ��</th>
											<th width='10%' style="text-align:center">����</th>
											<th width='10%' style="text-align:center">��Ժ</th>
											<th width='10%' style="text-align:center">ѧԺ</th>
											<th width='10%' style="text-align:center">רҵ</th>
											<th width='10%' style="text-align:center">�༶</th>
											<th width='10%' style="text-align:center">����</th>
											<th width='10%' style="text-align:center">�绰</th>
										</tr>
									</thead>
									<tbody id="tablebody">
									<logic:iterate id="i" name="fzrxxInfo" indexId="index">
											<tr name='deltr'>
												<td style='text-align:center'><input type='checkbox' name='chk'></td>
												<td style='text-align:center'><input name='xh' type='hidden'  value='${i.xh}'/><label name = 'xhname'>${i.xh}</label></td>
												<td style='text-align:center'><label name = 'xm'>${i.xm}</label></td>
												<td style='text-align:center'><label name = 'symc'>${i.symc}</label></td>
												<td style='text-align:center'><label name = 'xymc'>${i.xymc}</label></td>
												<td style='text-align:center'><label name = 'zymc'>${i.zymc}</label></td>
												<td style='text-align:center'><label name = 'bjmc'>${i.bjmc}</label></td>
												<td style='text-align:center'><label name = 'fz'>������</label></td>
												<td style='text-align:center'><label name = 'sjhm'>${i.sjhm}</label></td>
											</tr>
									</logic:iterate>
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
					<thead>
					<tr class="h">
						<th colspan="4">
							<span><font color="red">*</font>��֧�������Ϣ</span>
							<div class="btn">
								<button type="button" onclick="addRowDialog('tablebody2');return false;" style="float:left">����</button>
								<button type="button" onclick="delRow();return false;" style="float:left">ɾ��</button>
							</div>
						</th>
					</tr>
					</thead>
                    <tbody>
                    <tr class="h">
                        <td colspan="7">
                            <table width="100%" >
                                <thead>
                                <tr>
                                    <th width='5%'><input name='chkAll' onclick="selectAll(this)" type="checkbox" /></th>
                                    <th width='10%' style="text-align:center">ѧ��</th>
                                    <th width='10%' style="text-align:center">����</th>
                                    <th width='10%' style="text-align:center">��Ժ</th>
                                    <th width='10%' style="text-align:center">ѧԺ</th>
                                    <th width='10%' style="text-align:center">רҵ</th>
                                    <th width='10%' style="text-align:center">�༶</th>
                                    <th width='10%' style="text-align:center">����</th>
                                    <th width='10%' style="text-align:center">�绰</th>
                                </tr>
                                </thead>
                                <tbody id="tablebody2">
                                <logic:iterate id="i" name="tzsxxInfo" indexId="index">
                                    <tr name='deltr'>
                                        <td style='text-align:center'><input type='checkbox' name='chk'></td>
                                        <td style='text-align:center'><input name='tzsxh' type='hidden'  value='${i.xh}'/><label name = 'xhname'>${i.xh}</label></td>
                                        <td style='text-align:center'><label name = 'xm'>${i.xm}</label></td>
                                        <td style='text-align:center'><label name = 'symc'>${i.symc}</label></td>
                                        <td style='text-align:center'><label name = 'xymc'>${i.xymc}</label></td>
                                        <td style='text-align:center'><label name = 'zymc'>${i.zymc}</label></td>
                                        <td style='text-align:center'><label name = 'bjmc'>${i.bjmc}</label></td>
                                        <td style='text-align:center'><label name = 'fz'>��֧��</label></td>
                                        <td style='text-align:center'><label name = 'sjhm'>${i.sjhm}</label></td>
                                    </tr>
                                </logic:iterate>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <th align="right" width="20%">
                            �ϴ�����
                        </th>
                        <td colspan="3">
                            <html:hidden property="filepath" styleId="filepath" />
                            <%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
                            <script type="text/javascript">
                                //���ø���
                                jQuery(function(){
                                    jQuery.MultiUploader({
                                        maxcount : 4,
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
                        <th>˵��</th>
                        <td colspan="3">
                            <span>����1 ��ҵ��ָ����λȷ�Ϻ���������ǩ�� ��λ���£�<br/></span>
                            <span>����2 ��ָ����ʦȷ�Ϻ���ָ����ʦǩ�� ��ָ����ʦ���������<br/></span>
                            <span>����3 ��ѧ����֯�ⶨƷ�ƻ����<br/></span>
                            <span>����4 ��ѧ����֯�³�</span>
                        </td>
                    </tr>
                    </tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
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