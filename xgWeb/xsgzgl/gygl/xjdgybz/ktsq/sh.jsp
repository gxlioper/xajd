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
    <script type="text/javascript" src="xsgzgl/gygl/xjdgybz/ktsq/js/ktsq.js"></script>
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
                var url = "gygl_gybz_wh.do?method=sh&type=save";
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
        });
    </script>
    <style type="text/css">
        #shlccx_table th{text-align: center;}
        #shlccx_table tr{text-align: center;}
    </style>
</head>
<body>
<html:form method="post" styleId="demoForm" action="/gygl_gybz_wh">
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
                        ${model.ldmc}
                </td>
                <th>�����</th>
                <td>
                        ${model.qsh}
                </td>
            </tr>
            <tr>
                <th>��������</th>
                <td>
                        ${model.rs}
                </td>
                <th>���᳤</th>
                <td>
                        ${model.sszmc}
                </td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="4">
                    <span>���ҳ�Ա</span>
                </th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <td colspan="4">
                        <table id="shlccx_table" width="100%">
                            <tr>
                                <th>ѧԺ</th>
                                <th>����</th>
                                <th>�༶</th>
                                <th>ѧ��</th>
                                <th>��ϵ�绰</th>
                                <th>��ַ</th>
                                <th>��ѷ�̯����</th>
                            </tr>
                            <logic:iterate id="i" name="qscyList" indexId="index">
                                <tr>
                                    <td>${i.symc}</td>
                                    <td>${i.xm}</td>
                                    <td>${i.bjmc}</td>
                                    <td>${i.xh}</td>
                                    <td>${i.lxdh}</td>
                                    <td>${i.wz}</td>
                                    <td>${i.ftbl}</td>
                                </tr>
                            </logic:iterate>
                        </table>
                    </td>
                </tr>
                <tr>
                    <th>����</th>
                    <td>
                            ${model.gl}
                    </td>
                    <th>ʹ�÷�ʽ</th>
                    <td>
                            ${model.syfs}
                    </td>
                </tr>
                <tr>
                    <th>ʹ������</th>
                    <td>
                            ${model.synx}
                    </td>
                    <th>��װ����</th>
                    <td>
                            ${model.azrq}
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