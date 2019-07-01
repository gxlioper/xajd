<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
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
                showAlert("����ѡ���Ա��Ϣ���ٽ���ɾ��������");
                return false;
            }
            jQuery(obj).remove();
            jQuery("[name='chkall']").removeAttr("checked");
        }
        //�õ�����ӵ�ѧ���ַ���
        function getYtjxhs(obj){
            var xhs = "";
            var xhobj = jQuery("[name='xh']").not(obj);
            jQuery(xhobj).each(function(i){
                xhs +=this.value;
                if(xhobj.length-1 != i){
                    xhs +=",";
                }
            });
            return xhs;
        }
        //ѧ�������ӳ�Աʱblur�¼�����������֤һ��ֵ������ʾ��Ϣ
        function inputBlur(obj){
            var xhs = getYtjxhs(obj);//ȡ���Ѿ���ӵ�ѧ�ţ������ظ���ӣ���֤��
            var xh = obj.value;
            var jsonPara = {xh:xh,xhs:xhs};
            var url = "ttgl_stglsq.do?method=getxsInfo";
            var jsonResult = null;
            jQuery.ajax({
                type:'post',
                url:url,
                dataType:'json',
                contentType:"application/x-www-form-urlencoded; charset=UTF-8",
                data:jsonPara,
                async: false,
                success:function(result){
                    jsonResult = result;
                }
            });
            var parentObj = jQuery(obj).parent().parent();
            if(jsonResult['xh']){
                jQuery(parentObj).find("[name='xm']").text(jsonResult['xm']);
                jQuery(parentObj).find("[name='symc']").text(jsonResult['symc']==null?'':jsonResult['symc']);
                jQuery(parentObj).find("[name='xymc']").text(jsonResult['xymc']==null?'':jsonResult['xymc']);
                jQuery(parentObj).find("[name='zymc']").text(jsonResult['zymc']==null?'':jsonResult['zymc']);
                jQuery(parentObj).find("[name='bjmc']").text(jsonResult['bjmc']==null?'':jsonResult['bjmc']);
                jQuery(parentObj).find("[name='sjhm']").text(jsonResult['sjhm']==null?'':jsonResult['sjhm']);
            }else{
                jQuery(parentObj).find("[name='xm']").text("");
                jQuery(parentObj).find("[name='symc']").text("");
                jQuery(parentObj).find("[name='xymc']").text("");
                jQuery(parentObj).find("[name='zymc']").text("");
                jQuery(parentObj).find("[name='bjmc']").text("");
                jQuery(parentObj).find("[name='sjhm']").text("");
                showAlert("ѧ�Ų����ڻ�ѧ���ظ�!");
                return false;
            }
        }
        //������
        function addRow(tableId){
            var html = "";
            html += "<tr name='deltr'>";
            html += "<td><input type='checkbox' name='chk'></td>"
            if(tableId == "tablebody1"){
                html += "<td><input name='xh' style='width:90%' onblur='inputBlur(this)'/></td>";
            } else{
                html += "<td><input name='tzsxh' style='width:90%' onblur='inputBlur(this)'/></td>";
            }
            html += "<td style='text-align:center'><label name = 'xm'></label></td>";
            html += "<td style='text-align:center'><label name = 'symc'></label></td>";
            html += "<td style='text-align:center'><label name = 'xymc'></label></td>";
            html += "<td style='text-align:center'><label name = 'zymc'></label></td>";
            html += "<td style='text-align:center'><label name = 'bjmc'></label></td>";
            if(tableId == "tablebody1"){
                html += "<td style='text-align:center'><label name = 'fz'>������</label></td>";
            } else {
                html += "<td style='text-align:center'><label name = 'fz'>��֧��</label></td>";
            }
            html += "<td style='text-align:center'><label name = 'sjhm'></label></td>";
            html += "</tr>";
            jQuery("#"+tableId).append(html);
        }

        //ѡ��������
        function selectAll(obj){
            if(obj.checked){
                jQuery("[name='chk']").attr("checked","checked");
            }else{
                jQuery("[name='chk']").removeAttr("checked");
            }
        }
        function fzrIsNull(){
            var flag = true;
            jQuery("[name='xm']").each(function(){
                if(jQuery(this).text() == ""){
                    flag = false;
                    return false;
                }
            });
            return flag;
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
        function saveXg(url){
            var checkids ="stlx-stqc-stjc-styx-ywzddw-zgh-stjs";
            if(!checkNotNull(checkids)){
                showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����!");
                return false;
            }
            if(!validateNUM(jQuery("#strs").val())){
                showAlert("��֯�������벻��ȷ!");
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

            ajaxSubFormWithFun("stglsqForm", url, function(data) {
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
<html:form action="/ttgl_stglsq" method="post" styleId="stglsqForm" onsubmit="return false;">
	<html:hidden property="sqid"/>
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
                    <html:text  property="sqsj" styleId="sqsj"   size="10"
                                onclick="return showCalendar('sqsj','y-mm-dd',true,'sqsj');"
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
                            var r = '${stglsqForm.jfly}';
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
							<th width='12%' style="text-align:center">ѧ��</th>
							<th width='10%' style="text-align:center">����</th>
							<th width='10%' style="text-align:center">��Ժ</th>
							<th width='10%' style="text-align:center">ѧԺ</th>
							<th width='10%' style="text-align:center">רҵ</th>
							<th width='10%' style="text-align:center">�༶</th>
							<th width='10%' style="text-align:center">����</th>
							<th width='10%' style="text-align:center">�绰</th>
						</tr>
						</thead>
						<tbody id="tablebody1">
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
						<button type="button" onclick="addRow('tablebody2');return false;" style="float:left">����</button>
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
	<div style="height:50px;"></div>
	<div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
			<tfoot>
			<tr>
				<td colspan="4">
					<div class="bz">
						"<span class="red">*</span>"Ϊ������
					</div>
                    <div class="btn">
                        <button type="button"
                                onclick="saveXg('ttgl_stglsq.do?method=update&type=update');return false;"
                                id="buttonSave">
                            ����ݸ�
                        </button>
                        <button type="button"
                                onclick="saveXg('ttgl_stglsq.do?method=update&type=submit');return false;"
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