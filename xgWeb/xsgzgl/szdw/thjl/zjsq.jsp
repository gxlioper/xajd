<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script language="javascript" src="js/check.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript" src="comm/editor/kindeditor-min.js"></script>
    <script type="text/javascript" src="comm/editor/zh_CN.js"></script>
    <script language="javascript" src="xsgzgl/szdw/thjl/js/thjlDetail.js"> </script>
    <script type="text/javascript">
        var editor;
        jQuery(function(){
            //����ͼ
            var simpleOption = {
                name:'simple',
                resizeType : 1,
                themeType : 'simple',//��ʽ���
                items : [
                    'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
                    'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
                    'insertunorderedlist', '|', 'emoticons','link'
                ],
                newlineTag:'br',
                afterBlur:function(){this.sync();}
            };
            editor = KindEditor.create('textarea[name="thnr"]',simpleOption);
        })
        function changeThlx(value){
            jQuery.post("szdw_thlx.do?method=getKhwtListByThlx",{ssthlx:value},function(data){
                if(data["wttg"] != null){
                    editor.html(data["wttg"]);
                } else {
                    editor.html("");
                }
            },'json');
        }
        function submit1() {
            if(jQuery("#thsj").val()=="" || jQuery("#kssj").val()=="" || jQuery("#jssj").val()=="" || jQuery("#thlx").val()==""){
                return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
            }
            var url = "szdw_thjl.do?method=zjsqBc&doType="+jQuery("#doType").val();
            ajaxSubFormWithFun("demoForm",url,function(data){
                showAlertDivLayer(data["message"],{},{"clkFun":function(){
                    if (parent.window){
                        refershParent();
                    }
                    iFClose();
                }});
            });
        }
    </script>
    <style type="text/css">
        #shlccx_table th{text-align: center;}
        #shlccx_table tr{text-align: center;}
    </style>
</head>
<body onload="init();">
<input type="hidden" name="khhwt_inp" id="khhwt_inp" value="${thjlInfo.khhwt}" />
<input type="hidden" name="wtms_inp" id="wtms_inp" value="${thjlInfo.wtms}" />
<html:form action="/szdw_thjl" method="post" styleId="demoForm">
    <input type="hidden" name="zgh" id="zgh" value="${zgh}" />
    <input type="hidden" name="sqid" id="sqid" value="${thjlInfo.id}" />
    <input type="hidden" name="knlxdm" id="knlxdm" value="${thjlInfo.knlxdm}" />
    <input type="hidden" name="doType" id="doType" value="${doType}" />
    <input type="hidden" name="path" id="path" value="${path}" />
    <input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
    <input type="hidden" id = "sfzdgzmc" value = "${thjlInfo.sfzdgzmc }"/>
    <input type="hidden" id = "sfsdktmc" value = "${thjlInfo.sfsdktmc }"/>
    <logic:equal name="doType" value="view">
        <input type="hidden" value="${xh}" id="xh">
    </logic:equal>
    <logic:equal name="doType" value="update">
        <input type="hidden" value="${xh}" id="xh">
    </logic:equal>
    <div style="tab;overflow-x:hidden;overflow-y:auto;height:450px;margin-bottom: 0px;" >
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>ѧ��������Ϣ</span>
                </th>
            </tr>
            </thead>
            <logic:equal name="doType" value="view">
                <%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
            </logic:equal>
            <logic:notEqual name="doType" value="view">
                <logic:equal name="doType" value="update">
                    <%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
                </logic:equal>
                <logic:notEqual name="doType" value="update">
                    <%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
                </logic:notEqual>
            </logic:notEqual>
            <thead>
                <tr>
                    <th colspan="4">
                        <span>������ɼ�</span>
                    </th>
                </tr>
            </thead>
            <tbody id="bjgcj">
            </tbody>
            <thead>
            <tr >
                <th colspan="4">
                    <span>ѧ���춯��Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody id="xjydInfo">
            </tbody>
            <thead>
            <tr >
                <th colspan="4">
                    <span≯����ʦ��Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody id="thjlInfo">
            <tr style="height:10px">
                <th  width="16%">
                    ̸����ʦ
                </th>
                <td  width="34%">
                        ${thjlInfo.jsxm}
                </td>
                <th width="16%">
                    ְ����
                </th>
                <td width="34%">
                        ${thjlInfo.zgh}
                </td>
            </tr>
            <tr>
                <th width="16%">
                    �Ա�
                </th>
                <td width="34%">
                        ${thjlInfo.jsxb}
                </td>
                <th width="16%">
                    ����
                </th>
                <td width="34%">
                        ${thjlInfo.jsbmmc}
                </td>
            </tr>
            <thead>
            <tr >
                <th colspan="4">
                    <span≯����¼��Ϣ</span>
                </th>
            </tr>
            </thead>
            <tr>
                <th width="16%">
                    <logic:notEqual name="doType" value="view"><span class="red">*</span></logic:notEqual≯������
                </th>
                <td width="34%" colspan="3">
                    <logic:equal name="doType" value="view">
                        ${thjlInfo.thsj }
                    </logic:equal>
                    <logic:notEqual name="doType" value="view">
                        <html:text property="thsj" styleId="thsj"  value="${thjlInfo.thsj }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true"/>
                    </logic:notEqual>
                </td>
            </tr>
            <tr>
                <th width="16%">
                    <logic:notEqual name="doType" value="view"><span class="red">*</span></logic:notEqual≯��ʱ��
                </th>
                <td width="34%">
                    <logic:equal name="doType" value="view">
                        <logic:notEqual  name="thjlInfo" property="kssj" value="">
                            ${thjlInfo.kssj }&nbsp;��&nbsp;${thjlInfo.jssj }
                        </logic:notEqual>
                    </logic:equal>
                    <logic:notEqual name="doType" value="view">
                        <html:text property="kssj" styleId="kssj" style="width:21%" value="${thjlInfo.kssj }" onfocus="WdatePicker({dateFmt:'HH:mm',maxDate:'#F{$dp.$D(\\'jssj\\')}'})" readonly="true" onchange="changeThsc();"/>&nbsp;��
                        <html:text property="jssj" styleId="jssj" style="width:21%" value="${thjlInfo.jssj }" onfocus="WdatePicker({dateFmt:'HH:mm',minDate:'#F{$dp.$D(\\'kssj\\')}'})" readonly="true" onchange="changeThsc();"/>
                    </logic:notEqual>
                </td>
                <th width="16%">
                    ̸��ʱ��
                    <input type="hidden" name="thsc" id="thsc" value="${thjlInfo.thsc}">
                </th>
                <td width="34%" id="thscTd">
                    <logic:equal name="doType" value="view">
                        <logic:notEqual  name="thjlInfo" property="thsc" value="">
                            ${thjlInfo.thsc }��
                        </logic:notEqual>
                    </logic:equal>
                </td>
            </tr>
            <tr>
                <th width="16%">
                    <logic:notEqual name="doType" value="view">
                        <span class="red">*</span>
                    </logic:notEqual≯������
                </th>
                <td width="34%" colspan="3">
                    <logic:equal name="doType" value="view">
                        ${thjlInfo.thlxmc }
                    </logic:equal>
                    <logic:notEqual name="doType" value="view">
                        <html:select property="thlx" styleId="thlx"  style="width: 200px" value="${thjlInfo.thlx}" onchange="changeThlx(this.value);">
                            <html:option value=""></html:option>
                            <html:options collection="thlxList" property="lxdm"
                                          labelProperty="lxmc" />
                        </html:select>
                    </logic:notEqual>
                </td>
            </tr>
            <tr>
                <th width="16%">
                    ̸����¼<br>
                    <logic:notEqual name="doType" value="view"><font color="red"><B>(��2000��)</B></font></logic:notEqual>
                </th>
                <td width="34%" colspan="3">
                    <logic:equal name="doType" value="view">
                        ${thjlInfo.thnr }
                    </logic:equal>
                    <logic:notEqual name="doType" value="view">
                        <html:textarea property="thnr" styleId="thnr"  value="${thjlInfo.thnr }" style="word-break:break-all;width:99%" onblur="chLeng(this,1000);" rows='6'/>
                    </logic:notEqual>
                </td>
            </tr>
            <tr>
                <th width="16%">�ϴ�����</th>
                <td width="34%" colspan = "3">
                    <logic:notEqual value="view" name="doType">
                        <html:hidden property="filepath" styleId="filepath" value="${thjlInfo.filepath}"/>
                        <input type="file" id="filepath_f" name="filepath" />
                        <script type="text/javascript">
                            //���ø���
                            jQuery(function(){
                                jQuery('#filepath_f').multiUploader({
                                    maxcount : 3,
                                    //��׺
                                    accept : 'png|gif|jpg|zip|rar|doc|docx',
                                    //����ļ���С ��λM
                                    maxsize: 10,
                                    //��Ÿ������������id
                                    elementid : 'filepath',
                                    label : '̸��ģ����Ӱ����Ƭ',
                                    eid : 'filepath_f'
                                });
                            });
                        </script>
                    </logic:notEqual>
                    <logic:equal value="view" name="doType">
                        <%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
                        <html:hidden property="filepath" styleId="filepath" value="${thjlInfo.filepath}"/>
                        <script type="text/javascript">
                            //���ø���
                            jQuery(function(){
                                var gid = jQuery('#filepath').val();
                                jQuery.MultiUploader_q({
                                    gid : gid
                                });
                            });
                        </script>
                    </logic:equal>
                </td>
            </tr>
            <tr>
                <th width="16%">
                    <logic:notEqual name="doType" value="view"><span class="red">*</span></logic:notEqual>�Ƿ��ص��ע
                </th>
                <td width="34%" colspan = "3">
                    <logic:equal name="doType" value="view">
                        ${thjlInfo.sfzdgzmc }
                    </logic:equal>
                    <logic:notEqual name="doType" value="view">
                        <logic:present name="isnotList">
                            <logic:iterate id="o" name="isnotList">
                                <label>
                                    <html:radio property="sfzdgz" name="thjlInfo" value="${o.dm}">${o.mc}</html:radio>
                                </label>
                            </logic:iterate>
                        </logic:present>
                    </logic:notEqual>
                </td>
            </tr>
            <tr>
                <th width="16%">
                    <logic:notEqual name="doType" value="view"><span class="red">*</span></logic:notEqual>�Ƿ���Ҫ��ȿ�̸
                </th>
                <td width="34%" onclick="initsdktsj();">
                    <logic:equal name="doType" value="view">
                        ${thjlInfo.sfsdktmc }
                    </logic:equal>
                    <logic:notEqual name="doType" value="view">
                        <logic:iterate id="o" name="isnotList">
                            <label>
                                <html:radio property="sfsdkt" name="thjlInfo" value="${o.dm}">${o.mc}</html:radio>
                            </label>
                        </logic:iterate>
                    </logic:notEqual>
                </td>
                <th width="16%" id="sdktsjTh" style="display: none">
                    ��ʱ��֯��ȿ�̸
                </th>
                <td width="34%" id="sdktsjTd" style="display: none">
                    <logic:equal name="doType" value="view">
                        ${thjlInfo.sdktsj }
                    </logic:equal>
                    <logic:notEqual name="doType" value="view">
                        <html:text property="sdktsj" styleId="sdktsj"  value="${thjlInfo.sdktsj }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true"/>
                    </logic:notEqual>
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <div>
        <table class="formlist">
            <tfoot>
            <tr>
                <td colspan="4">
                    <logic:notEqual name="doType" value="view"><div class="bz">
                        "<span class="red">*</span>"Ϊ������
                    </div></logic:notEqual>
                    <div class="btn">
                        <button type="button" onclick="submit1();return false;">
                            �ύ
                        </button>
                        <button onclick="Close();return false;">
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

