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
    <style>
        #shlccx_table th{text-align: center;}
        #shlccx_table tr{text-align: center;}
    </style>
    <script type="text/javascript">
        function save(){
            var qss = jQuery(".qss");
            var flag = false;
            for(var i=0;i<qss.length;i++){
                if(qss[i].value == ""){
                    flag = true;
                    break;
                }
            }
            if(flag){
                showAlert("����������Ϊ�գ�");
                return false;
            }
            var cws = jQuery(".qscws");
            for(var i=0;i<cws.length;i++){
                if(cws[i].value == ""){
                    flag = true;
                    break;
                }
            }
            if(flag){
                showAlert("��λ������Ϊ�գ�");
                return false;
            }
            var sfbz = jQuery(".sfbz").val();
            for(var i=0;i<sfbz.length;i++){
                if(sfbz[i].value == ""){
                    flag = true;
                    break;
                }
            }
            if(flag){
                showAlert("�շѱ�׼����Ϊ�գ�");
                return false;
            }
            var url = "gygl_fygl_ldxxgl10698.do?method=qssc&type=save";
            ajaxSubFormWithFun("demoForm", url, function(data) {
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
        /**
         * input��������
         */
        function setInput(target){
            if(jQuery("#mrsz").is(":checked")){
                jQuery("."+target.classList[0]).val(jQuery(target).val());
            }
        }
        function qschChange(){
            var v = jQuery("#qsch").val();
            if(Number(v) > 0){
                jQuery("#sfhlcTh").hide();
                jQuery("#sfhlcTd").hide();
                jQuery(".sfhlc_n").attr("checked","checked");
            } else if(Number(v) == 0){
                jQuery("#sfhlcTh").hide();
                jQuery("#sfhlcTd").hide();
                jQuery(".sfhlc_y").attr("checked","checked");
            } else {
                jQuery("#sfhlcTh").show();
                jQuery("#sfhlcTd").show();
                jQuery(".sfhlc_n").attr("checked","checked");
            }

            addTabletd();
        }
        function szmCheckChange(target){

            if(jQuery(target).is(":checked")){
                jQuery("#szm").show();
            } else {
                jQuery("#szm").hide();
            }
        }
        function addTabletd(){
            jQuery("#lcTd").empty();
            var ldcs = jQuery("#ldcs").val();
            var qscs = jQuery("#qsch").val();
            var ldxb = jQuery("input[name='ldxb']:checked").val();
            var skipZero = false;
            if(Number(qscs) < 0){
                var sfhlc = jQuery("input[name='sfhlc']:checked").val();
                if(sfhlc != "1"){//�������
                    ldcs = Number(ldcs) + 1;
                    skipZero = true;
                }
            }
            var html = "";
            for(var i=0,j=0;i<Number(ldcs);i++,j++){
                var currentLc;//��ǰ¥��
                currentLc = Number(qscs)+j;
                if(skipZero && currentLc == 0){
                    j++;
                    currentLc++;
                }
                html+="<tr>";
                html+="<input type='hidden' name='qsxx["+i+"].ch' value='"+currentLc+"'>";
                html+="<td class='id'>"+ currentLc +"��</td>";
                html+="<td><input name='qsxx["+i+"].qss' class='qss' style='width: 60px;' onblur='checkInt(this);'></div></td>";
                html+="<td><input name='qsxx["+i+"].cws' class='qscws' style='width: 60px;' onblur='checkInt(this);'></td>";
                html+="<td><input name='qsxx["+i+"].sfbz' class='sfbz' style='width: 60px;' onblur='checkMoney(this);'></td>";
                if(ldxb == "2"){
                    html+="<td><input type='hidden' class='xbHiden' name='qsxx["+i+"].qsxb' value='2'><input type='radio' name='qsscxx["+i+"].qsxb1' class='qsxb-nan' disabled='disabled' value='1' onchange='singleXbRadio(this)'>��<input type='radio' name='qsscxx["+i+"].qsxb1' checked='checked' class='qsxb-nv' disabled='disabled' value='2' onchange='singleXbRadio(this)'>Ů</td>";
                } else if(ldxb == "1"){
                    html+="<td><input type='hidden' class='xbHiden' name='qsxx["+i+"].qsxb' value='1'><input type='radio' name='qsscxx["+i+"].qsxb1' checked='checked' class='qsxb-nan' disabled='disabled' value='1' onchange='singleXbRadio(this)'>��<input type='radio' name='qsscxx["+i+"].qsxb1' class='qsxb-nv' disabled='disabled' value='2' onchange='singleXbRadio(this)'>Ů</td>";
                } else {
                    html+="<td><input type='hidden' class='xbHiden' name='qsxx["+i+"].qsxb' value='1'><input type='radio' name='qsscxx["+i+"].qsxb1' checked='checked' class='qsxb-nan' value='1' onchange='singleXbRadio(this)'>��<input type='radio' name='qsscxx["+i+"].qsxb1' class='qsxb-nv' value='2' onchange='singleXbRadio(this)'>Ů</td>";
                }
                html+="<td><input type='radio' name='qsxx["+i+"].sfykt' checked='checked' value='1' class='sfykt-y'>��<input type='radio' name='qsxx["+i+"].sfykt' value='0' class='sfykt-w'>��</td>";
                html+="<td><input type='radio' name='qsxx["+i+"].sfywsj' checked='checked' value='1' class='sfywsj-y'>��<input type='radio' name='qsxx["+i+"].sfywsj' value='0' class='sfywsj-w'>��</td>";
                html+="</tr>";
            }
            if(html != ""){
                jQuery("#lcTd").append(html);
            }
            //�Ե�һ�е�input��������¼�
            jQuery("input[name='qsxx[0].qss']").bind('input',function(){
                setInput(this);
            });
            jQuery("input[name='qsxx[0].cws']").bind('input',function(){
                setInput(this);
            });
            jQuery("input[name='qsxx[0].sfbz']").bind('input',function(){
                setInput(this);
            });
            //�������Ա�radio�󶨶���������ֵ�¼�
            /*for(var i=0;i<Number(ldcs);i++){
                jQuery("input[name='qsxx["+i+"].qsxb1']").change(function(){
                    singleXbRadio(this);
                });
            }*/
            //�Ե�һ��radio��������¼�
            jQuery("input[name='qsxx[0].qsxb1']").change(function(){
                setRadio(this);
            });
            jQuery("input[name='qsxx[0].sfykt']").change(function(){
                setRadio(this);
            });
            jQuery("input[name='qsxx[0].sfywsj']").change(function(){
                setRadio(this);
            });
        }
        /**
         * �Ա𵥸�radio�ı����������ֵ
         */
        function singleXbRadio(target){
            jQuery(target.parentElement.firstElementChild).val(target.value);
        }
        /**
         * radio����
         */
        function setRadio(target){
            if(jQuery("#mrsz").is(":checked")){
                jQuery("."+target.className).attr("checked","checked");
                //���Ա�radio��������ֵ
                if(target.className == 'qsxb-nan'){
                    jQuery(".xbHiden").val("1");
                } else if(target.className == 'qsxb-nv'){
                    jQuery(".xbHiden").val("2");
                }
            }
        }
        /**
         * �ı������Ա�
         */
        function changeQsxb(){
            var val = jQuery("input[name='ldxb']:checked").val();
            if(val == "1"){
                jQuery(".qsxb-nan").attr("checked","checked");

                jQuery(".qsxb-nan").attr("disabled","disabled");
                jQuery(".qsxb-nv").attr("disabled","disabled");

                jQuery(".xbHiden").val("1");
            } else if (val == "2"){
                jQuery(".qsxb-nv").attr("checked","checked");

                jQuery(".qsxb-nan").attr("disabled","disabled");
                jQuery(".qsxb-nv").attr("disabled","disabled");

                jQuery(".xbHiden").val("2");
            } else {
                jQuery(".qsxb-nan").removeAttr("disabled");
                jQuery(".qsxb-nv").removeAttr("disabled");
            }
        }
        jQuery(function(){
            qschChange();
            addTabletd();
        })
    </script>
</head>
<body>
<html:form method="post" styleId="demoForm" action="/gygl_fygl_ldxxgl10698">
    <div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>¥����Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>
                    У��
                </th>
                <td>
                        ${xqmc}
                </td>
                <th>
                    ¥������
                </th>
                <td>
                    <html:hidden property="lddm"/>
                        ${ldmc}
                </td>
            </tr>
            <tr>
                <th>
                    ¥���Ա�
                </th>
                <td >
                    <html:radio property="ldxb" value="1" onchange="changeQsxb()">��</html:radio>
                    <html:radio property="ldxb" value="2" onchange="changeQsxb()">Ů</html:radio>
                    <html:radio property="ldxb" value="3" onchange="changeQsxb()">��ס</html:radio>
                </td>
                <th>
                    ¥������
                </th>
                <td >
                    <html:select property="ldcs" styleId="ldcs" style="width:100px;" onchange="addTabletd()">
                        <logic:iterate id="i" name="cshcs">
                            <html:option value="${i}">${i}��</html:option>
                        </logic:iterate>
                    </html:select>
                </td>
            </tr>
            <tr>
                <th>
                    ¥����ʼ���
                </th>
                <td >
                    <html:select property="qsch" styleId="qsch" style="width:100px;" onchange="qschChange()">
                        <logic:iterate id="i" name="qschList">
                            <html:option value="${i}">${i}��</html:option>
                        </logic:iterate>
                    </html:select>
                </td>
                <th>
                    <span id="sfhlcTh">�Ƿ�0��</span>
                </th>
                <td id="sfhlcTd">
                    <html:radio property="sfhlc" value="1" onchange="addTabletd()" styleClass="sfhlc_y">��</html:radio>
                    <html:radio property="sfhlc" value="0" onchange="addTabletd()" styleClass="sfhlc_n">��</html:radio>
                </td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="4">
                    <span>������Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>���Һ����ɹ���</th>
                <td colspan="4">
                    <input type="checkbox" id="szmcheckbox" onchange="szmCheckChange(this)">&nbsp;����ĸ
                    <html:text property="szm" styleId="szm" style="width:40px;display: none;"/>
                    &nbsp;+&nbsp;
                    <html:radio property="ws" value="3">��λ</html:radio>
                    <html:radio property="ws" value="4">��λ</html:radio>
                </td>
            </tr>
            <tr>
                <th colspan="4" style="text-align: left">
                    <input type="checkbox" id="mrsz" checked="checked">Ĭ�ϰ���ʼ������
                </th>
            </tr>
            <tr>
                <td colspan="4">
                    <div class="con_overlfow">
                        <table id="shlccx_table" width="100%" class="formlist" >
                            <tr>
                                <th>���</th>
                                <th>����������</th>
                                <th>���Ҵ�λ��</th>
                                <th>�շѱ�׼(Ԫ)</th>
                                <th>�����Ա�</th>
                                <th>�Ƿ��пյ�</th>
                                <th>�Ƿ���������</th>
                            </tr>
                            <tbody id="lcTd">
                            </tbody>
                        </table>
                    </div>
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
                    </div>
                    <div class="btn">
                        <button type="button" onclick="save();" id="buttonSave">
                            �� ��
                        </button>
                        <button type="button" onclick="iFClose();"  id="buttonClose">
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