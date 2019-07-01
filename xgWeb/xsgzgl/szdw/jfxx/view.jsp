<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
    <style type="text/css">
        #shlccx_table th{text-align: center;}
        #shlccx_table tr{text-align: center;}
    </style>
</head>
<body style="width:100%">
<html:form action="/szdw_jfxx" method="post" styleId="demoForm">
    <div style='width:100%;height:465px;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>������Ϣ</span>
                </th>
            </tr>
            </thead>
            <%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
            <thead>
            <tr>
                <th colspan="4">
                    <span>
                        �ҷö���
                    </span>
                </th>
            </tr>
            </thead>
            <tbody id="�ҷö���">
            <tr>
                <td colspan="4">
                    <table id="shlccx_table" width="100%">
                        <tr>
                            <th>����</th>
                            <th>��ҷ��˹�ϵ</th>
                            <th>��ϵ�绰</th>
                            <th>��ע</th>
                        </tr>
                        <logic:notEmpty name="cyList">
                            <logic:iterate id="i" name="cyList" indexId="index">
                                <tr>
                                    <td>${i.xm}</td>
                                    <td>${i.mc}</td>
                                    <td>${i.lxdh}</td>
                                    <td>${i.bz}</td>
                                </tr>
                            </logic:iterate>
                        </logic:notEmpty>
                    </table>
                </td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="4">
                    <span>�ҷ���Ϣ</span>
                    <html:hidden property="jgid" styleId="jgid"></html:hidden>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>�ҷ�ʱ��</th>
                <td>
                    ${model.jfsj}
                </td>
                <th>�ص�</th>
                <td>
                        ${model.dd}
                </td>
            </tr>
            <tr>
                <th>�ҷ�����</th>
                <td>
                    <logic:equal value="01" name="model" property="jfxz">
                        ʵ��
                    </logic:equal>
                    <logic:equal value="02" name="model" property="jfxz">
                        �绰
                    </logic:equal>
                </td>
                <th>��������</th>
                <td>
                        ${model.rs}
                </td>
            </tr>
            <tr>
                <th>�ҷ�ԭ��</th>
                <td colspan="3">
                        ${model.yy}
                </td>
            </tr>
            <tr>
                <th>�ҷ�����</th>
                <td colspan="3">
                        ${model.nr}
                </td>
            </tr>
            <tr>
                <th>��ʩ����</th>
                <td colspan="3">
                        ${model.csjy}
                </td>
            </tr>
            <tr>
                <th>
                    ����
                </th>
                <td  colspan="3">
                    <div id="commonfileupload-list-0" style="padding: 5px;"></div>
                    <script type="text/javascript">
                        //���ø���
                        jQuery(function(){
                            var gid = "${filepath}";
                            jQuery.MultiUploader_q({
                                gid : gid,
                                targetEl : 'commonfileupload-list-0'
                            });
                        });
                    </script>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div>
        <table width="100%" border="0" class="formlist">
            <tfoot>
            <tr>
                <td colspan="4" >
                    <div class="btn">
                        <button type="button" name="�� ��" onclick="iFClose();">
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

