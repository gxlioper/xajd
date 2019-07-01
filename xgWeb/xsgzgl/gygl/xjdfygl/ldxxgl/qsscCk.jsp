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

            var url = "gygl_fygl_ldxxgl10698.do?method=qsscBc";
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
         * �Ա𵥸�radio�ı����������ֵ
         */
        function singleXbRadio(target){
            jQuery(target.parentElement.firstElementChild).val(target.value);
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
            changeQsxb()
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
                    <input type="radio" name="ldxb" value="1" onchange="changeQsxb()" <logic:equal value="1" name="mmark">disabled</logic:equal> <logic:equal value="1" name="ldxb">checked</logic:equal>/>��
                    <input type="radio" name="ldxb" value="2" onchange="changeQsxb()" <logic:equal value="1" name="mmark">disabled</logic:equal> <logic:equal value="2" name="ldxb">checked</logic:equal>/>Ů
                    <input type="radio" name="ldxb" value="3" onchange="changeQsxb()" <logic:equal value="3" name="ldxb">checked</logic:equal>/>��ס
                </td>
                <th>
                    ¥������
                </th>
                <td >
                    ${ldcs}
                </td>
            </tr>
            <tr>
                <th>
                    ¥����ʼ���
                </th>
                <td >
                    ${qsch}
                </td>
                <th>
                    <span id="sfhlcTh">�Ƿ�0��</span>
                </th>
                <td id="sfhlcTd">
                    <logic:equal value="1" name="sfhlc">
                        ��
                    </logic:equal>
                    <logic:notEqual value="0" name="sfhlc">
                        ��
                    </logic:notEqual>
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
                <td colspan="4">
                    <div class="con_overlfow">
                        <table id="shlccx_table" width="100%" class="formlist" >
                            <tr>
                                <th>���</th>
                                <th>����������</th>
                                <th>�ܴ�λ��</th>
                                <th>�����Ա�</th>
                            </tr>
                            <tbody id="lcTd">
                                <logic:iterate id="i" name="lcqsxx" indexId="index">
                                    <tr>
                                        <input type="hidden" value="${i.ch}" name="qsxx[<%=index%>].ch">
                                        <td>${i.ch}��</td>
                                        <td>${i.qss}</td>
                                        <td>${i.cws}</td>
                                        <td>
                                            <input type="hidden" class="xbHiden" name="qsxx[<%=index%>].qsxb" value="${i.qsxb}">
                                            <logic:notEqual value="0" name="i" property="mark" >
                                                <logic:equal value="1" name="i" property="qsxb">
                                                    ��
                                                </logic:equal>
                                                <logic:equal value="2" name="i" property="qsxb">
                                                    Ů
                                                </logic:equal>
                                                <logic:equal value="3" name="i" property="qsxb">
                                                    ��ס
                                                </logic:equal>
                                            </logic:notEqual>
                                            <logic:equal value="0" name="i" property="mark" >
                                                <input type="radio" value="1" name="qsxx[<%=index%>].qsxb1"
                                                       onchange="singleXbRadio(this)" class='qsxb-nan'
                                                       <logic:equal value="1" name="i" property="qsxb">checked</logic:equal>
                                                >��
                                                <input type="radio" value="2" name="qsxx[<%=index%>].qsxb1"
                                                       onchange="singleXbRadio(this)" class='qsxb-nv'
                                                       <logic:equal value="2" name="i" property="qsxb">checked</logic:equal>
                                                >Ů
                                            </logic:equal>
                                        </td>
                                    </tr>
                                </logic:iterate>
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