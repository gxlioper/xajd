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
    <script type="text/javascript" src="xsgzgl/gygl/sfqs/sfqscj/sfqscjsq/js/sfqscj.js"></script>
    <script type="text/javascript">

        function save(){

            if(jQuery("#shyj").val() == ""){
                showAlertDivLayer("����д��������");
                return false;
            }
            var message;
            if(jQuery("#shjg").val() == "1"){
                message = "ͨ��";
            }
            if(jQuery("#shjg").val() == "2"){
                message = "��ͨ��";
            }
            if(jQuery("#shjg").val() == "3"){
                message = "�˻�";
            }
            showConfirmDivLayer("��ȷ��" + message + "��������",{"okFun":function(){
                var url = "gygl_sfqscj_wh.do?method=sh&type=save";
                ajaxSubFormWithFun("demoForm",url,function(data){
                    showAlertDivLayer(data["message"],{},{"clkFun":function(){
                        if (parent.window){
                            refershParent();
                        }
                    }});
                });
            }});
        }
        jQuery(document).ready(function(){
            jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.sqid}&tt="+new Date().getTime());
            jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splc}&shid=${model.shid}");
            qshChange();
        });
    </script>
    <style type="text/css">
        #shlccx_table th{text-align: center;}
        #shlccx_table tr{text-align: center;}
    </style>
</head>
<body>
<html:form method="post" styleId="demoForm" action="/gygl_sfqscj_wh">
    <input type="hidden" name="sqid" value="${model.sqid}"/>
    <input type="hidden" name="splc" value="${model.splc}"/>
    <input type="hidden" name="xh" value="${model.xh}"/>
    <div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>������Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>
                    ѧ��
                </th>
                <td>
                    ${model.xn}
                </td>
                <th>
                    У��
                </th>
                <td>
                        ${model.xqmc}
                </td>
            </tr>
            <tr>
                <th>
                    ����¥����
                </th>
                <td>
                    <input type="hidden" id="lddm" value="${model.lddm}"/>
                        ${model.ldmc}
                </td>
                <th>���Һ�</th>
                <td>
                    <input type="hidden" id="qsh" value="${model.qsh}"/>
                        ${model.qsh}
                </td>
            </tr>
            <tr>
                <th>������Ժ</th>
                <td id="sssyTd">

                </td>
                <th>��ϵ��ʽ</th>
                <td>
                    ${model.lxfs}
                </td>
            </tr>
            <tr>
                <th>�걨����</th>
                <td colspan="3">
                    ${model.sblx}
                </td>
            </tr>
            <tr>
                <th>���ҳ�Ա</th>
                <td colspan="3">
                    <style type="text/css">
                        #shlccx_table th{text-align: center;}
                        #shlccx_table tr{text-align: center;}
                    </style>
                    <table id="shlccx_table" width="100%">
                        <tr id="xmTr">
                            <th>����</th>
                            <logic:iterate id="i" name="qscyList">
                                <td>${i.xm}</td>
                            </logic:iterate>
                        </tr>
                        <tr id="xhTr">
                            <th>ѧ��</th>
                            <logic:iterate id="i" name="qscyList" indexId="index">
                                <td>${i.xh}</td>
                            </logic:iterate>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <th>�����ں�</th>
                <td colspan="3">
                    ${model.cjkh}
                </td>
            </tr>
            <tr>
                <th>�����ƻ�</th>
                <td colspan="3">
                    ${model.cjjh}
                </td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="4">
                    <span>�����Ϣ</span>
                </th>
            </tr>
            </thead>
            <tr>
                <td colspan="4" id="shlccx">

                </td>
            </tr>
            <tbody>
            <tr>
                <th >
                    ��˽��
                </th>
                <td id="shjgSpan" colspan="3">

                </td>
            </tr>

            <tr>
                <th >
                    <font color="red">*&nbsp;</font> ������
                    <br />
                    <font color="red">(��200��)</font>
                </th>
                <td colspan="3">
                    <jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=cdgl&id=shyj" />
                    <textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
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
                    <div class="btn">
                        <button type="button" id="btn_submit" type="button"
                                onclick="save();">
                            ����
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