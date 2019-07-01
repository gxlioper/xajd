<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function(){
            changeSqkg();
            changeSqkg_nzhb();
            changeSqkg_nzzj();
        });

        //���¸�λ����
        function changeSqkg(){
            var sqkg = jQuery("[name=sqkg]:checked").val();
            if("1"==sqkg){
                jQuery("tr[name=sb] select,tr[name=sb] input:not(input:radio[name=sqkg])").attr("disabled",false);
            }else if("0"==sqkg){
                jQuery("tr[name=sb] select,tr[name=sb] input:not(input:radio[name=sqkg])").attr("disabled","disabled");
            }
            jQuery("#id").attr("disabled",false);
        }
        function changeSqkg_nzhb(){
            var sqkg = jQuery("[name=sqkg_nzhb]:checked").val();
            if("1"==sqkg){
                jQuery("tr[name=nzhb] select,tr[name=nzhb] input:not(input:radio[name=sqkg_nzhb])").attr("disabled",false);
            }else if("0"==sqkg){
                jQuery("tr[name=nzhb] select,tr[name=nzhb] input:not(input:radio[name=sqkg_nzhb])").attr("disabled","disabled");
            }
            jQuery("#id").attr("disabled",false);
        }

        function changeSqkg_nzzj(){
            var sqkg = jQuery("[name=sqkg_nzzj]:checked").val();
            if("1"==sqkg){
                jQuery("tr[name=nzzj] select,tr[name=nzzj] input:not(input:radio[name=sqkg_nzzj])").attr("disabled",false);
            }else if("0"==sqkg){
                jQuery("tr[name=nzzj] select,tr[name=nzzj] input:not(input:radio[name=sqkg_nzzj])").attr("disabled","disabled");
            }
            jQuery("#id").attr("disabled",false);
        }
        function saveJcsz(){
            var sqkglength = jQuery("[name=sqkg]:checked").length;
            var sqkglength_nzhb = jQuery("[name=sqkg_nzhb]:checked").length;
            var sqkglength_nzzj = jQuery("[name=sqkg_nzzj]:checked").length;
            if(sqkglength==0){
                showAlertDivLayer("�������걨���뿪��!");
                return false;
            }
            if(sqkglength_nzhb==0){
                showAlertDivLayer("���������ڻ㱨���뿪��!");
                return false;
            }
            if(sqkglength_nzzj==0){
                showAlertDivLayer("�����������ܽ����뿪��!");
                return false;
            }
            var splc = jQuery("#splc").val();
            var sqkg = jQuery("[name=sqkg]:checked").val();

            var splc_nzhb = jQuery("#splc_nzhb").val();
            var sqkg_nzhb = jQuery("[name=sqkg_nzhb]:checked").val();

            var splc_nzzj = jQuery("#splc_nzzj").val();
            var sqkg_nzzj = jQuery("[name=sqkg_nzzj]:checked").val();

            if ("1"==sqkg && (splc == "" || splc == null)){
                showAlertDivLayer("��ѡ���걨�������!");
                return false;
            }
            if ("1"==sqkg_nzhb && (splc_nzhb == "" || splc_nzhb == null)){
                showAlertDivLayer("��ѡ�����ڻ㱨�������!");
                return false;
            }
            if ("1"==sqkg_nzzj && (splc_nzzj == "" || splc_nzzj == null)){
                showAlertDivLayer("��ѡ�������ܽ��������!");
                return false;
            }
            //			if("1"==sqkg && (jQuery("#sqjssj").val()=="" || jQuery("#sqkssj").val()=="")){
            //				showAlertDivLayer("����ʱ��ͽ���ʱ�������д!");
            //				return false;
            //			}
            var url = "sxzzjy_jcsz.do?method=saveJcsz";
            ajaxSubFormWithFun("BjxfjsjcszForm",url,function(data){
                showAlertDivLayer(data["message"]);
            });
        }
    </script>
</head>
<body >
<html:form action="/sxzzjy_jcsz" method="post" styleId="BjxfjsjcszForm" >
    <html:hidden property="id" styleId="id"/>
    <div class="tab_cur">
        <p class="location">
            <em>���ĵ�ǰλ��:</em><a>${title }</a>
        </p>
    </div>
    <div class="tab">
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="2"><span>�걨����</span></th>
            </tr>
            </thead>
            <tbody>
            <tr  name="sb">
                <th width="40%"><span class="red">*</span>���뿪��</th>
                <td>
                    <logic:present name="onoffList">
                        <logic:iterate id="o" name="onoffList" >
                            <html:radio property="sqkg"  onclick="changeSqkg();" value="${o.dm}">${o.mc}</html:radio>
                        </logic:iterate>
                    </logic:present>
                </td>
            </tr>
            <tr name="sb">
                <th>���뿪��ʱ��</th>
                <td>
                    <html:text  property="sqkssj" styleId="sqkssj"   size="10"
                                onclick="return showCalendar('sqkssj','y-mm-dd',true,'sqjssj');"
                                onblur="dateFormatChg(this)" readonly="true"></html:text>
                    -
                    <html:text  property="sqjssj" styleId="sqjssj"   size="10"
                                onclick="return showCalendar('sqjssj','y-mm-dd',false,'sqkssj');"
                                onblur="dateFormatChg(this)" readonly="true"></html:text>
                </td>
            </tr>
            <tr name="sb">
                <th><span class="red">*</span>�������</th>
                <td>
                    <html:select property="splc" styleId="splc" disabled="false" >
                        <html:options collection="shlcList" property="splc"
                                      labelProperty="lcxx" />
                    </html:select>
                </td>
            </tr>
            <thead>
            <tr >
                <th colspan="2"><span>���ڻ㱨����</span></th>
            </tr>
            </thead>
            <tr name="nzhb">
                <th width="40%"><span class="red">*</span>���뿪��</th>
                <td>
                    <logic:present name="onoffList">
                        <logic:iterate id="o" name="onoffList" >
                            <html:radio property="sqkg_nzhb"  onclick="changeSqkg_nzhb();" value="${o.dm}">${o.mc}</html:radio>
                        </logic:iterate>
                    </logic:present>
                </td>
            </tr>
            <tr name="nzhb">
                <th>���뿪��ʱ��</th>
                <td>
                    <html:text  property="sqkssj_nzhb" styleId="sqkssj_nzhb"   size="10"
                                onclick="return showCalendar('sqkssj_nzhb','y-mm-dd',true,'sqjssj_nzhb');"
                                onblur="dateFormatChg(this)" readonly="true"></html:text>
                    -
                    <html:text  property="sqjssj_nzhb" styleId="sqjssj_nzhb"   size="10"
                                onclick="return showCalendar('sqjssj_nzhb','y-mm-dd',false,'sqkssj_nzhb');"
                                onblur="dateFormatChg(this)" readonly="true"></html:text>
                </td>
            </tr>
            <tr name="nzhb">
                <th><span class="red">*</span>�������</th>
                <td>
                    <html:select property="splc_nzhb" styleId="splc_nzhb" disabled="false" >
                        <html:options collection="shlcList" property="splc"
                                      labelProperty="lcxx" />
                    </html:select>
                </td>
            </tr>
            <thead>
            <tr>
                <th colspan="2"><span>�����ܽ�����</span></th>
            </tr>
            </thead>
            <tr name="nzzj">
                <th width="40%"><span class="red">*</span>���뿪��</th>
                <td>
                    <logic:present name="onoffList">
                        <logic:iterate id="o" name="onoffList" >
                            <html:radio property="sqkg_nzzj"  onclick="changeSqkg_nzzj();" value="${o.dm}">${o.mc}</html:radio>
                        </logic:iterate>
                    </logic:present>
                </td>
            </tr>
            <tr name="nzzj">
                <th>���뿪��ʱ��</th>
                <td>
                    <html:text  property="sqkssj_nzzj" styleId="sqkssj_nzzj"   size="10"
                                onclick="return showCalendar('sqkssj_nzzj','y-mm-dd',true,'sqjssj_nzzj');"
                                onblur="dateFormatChg(this)" readonly="true"></html:text>
                    -
                    <html:text  property="sqjssj_nzzj" styleId="sqjssj_nzzj"   size="10"
                                onclick="return showCalendar('sqjssj_nzzj','y-mm-dd',false,'sqkssj_nzzj');"
                                onblur="dateFormatChg(this)" readonly="true"></html:text>
                </td>
            </tr>
            <tr name="nzzj">
                <th><span class="red">*</span>�������</th>
                <td>
                    <html:select property="splc_nzzj" styleId="splc_nzzj" disabled="false" >
                        <html:options collection="shlcList" property="splc"
                                      labelProperty="lcxx" />
                    </html:select>
                </td>
            </tr>

            <tfoot>
            <tr>
                <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
                    <div class="btn">
                        <logic:equal name="writeAble" value="yes">
                            <button type="button" class="button2" onclick="saveJcsz();return false;" id="btn_save">
                                �� ��
                            </button>
                        </logic:equal>
                    </div>
                </td>
            </tr>
            </tfoot>
            </tbody>
        </table>
    </div>
</html:form>
</body>
</html>
