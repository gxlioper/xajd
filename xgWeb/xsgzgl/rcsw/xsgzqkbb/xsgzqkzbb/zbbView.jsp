<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <%@ include file="/syscommon/autocomplete.ini"%>
    <script type="text/javascript" src="xsgzgl/rcsw/xsgzqkbb/xsgzqkzbb/js/zbb.js"></script>
</head>
<body style="width: 100%">
<html:form action="/rcsw_xsgzqkbb_zbbgl" method="post" styleId="xsgzqkZbbForm" onsubmit="return false;">
    <div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>ѧ����������ܱ�����Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="20%">
                    ������
                </th>
                <td width="30%">
                        ${xsgzqkZbbForm.bsrmc}
                </td>
                <th>
                    ���͵�λ
                </th>
                <td>
                        ${xsgzqkZbbForm.bsdwmc}
                </td>
            </tr>
            <tr>
                <th width="20%">
                    ��ϵ�绰
                </th>
                <td width="30%">
                        ${xsgzqkZbbForm.lxdh}
                </td>
                <th>��λ������</th>
                <td>
                        ${xsgzqkZbbForm.dwfzrmc}
                </td>
            </tr>
            <tr>
                <th width="20%">
                    ѧ��
                </th>
                <td width="30%">
                        ${xsgzqkZbbForm.xn}
                </td>
                <th>ѧ��</th>
                <td>
                        ${xsgzqkZbbForm.xqmc}
                </td>
            </tr>
            <tr>
                <th>�ܴ�</th>
                <td>
                        ${xsgzqkZbbForm.zc}
                </td>
                <th>�ܴ���ֹ����</th>
                <td>${xsgzqkZbbForm.zcksjsrq}</td>
            </tr>
            <tr>
                <th width="20%">
                    ��Ϣ����
                </th>
                <td width="80%" colspan="3">
                        ${xsgzqkZbbForm.xxzt}
                </td>
            </tr>

            <tr>
                <th width="20%">��Ϣ����
                    </th>
                <td colspan="3">
                        ${xsgzqkZbbForm.xxnr}
                </td>
            </tr>

            <tr>
                <th width="20%">�������
                    </th>
                <td colspan="3">
                        ${xsgzqkZbbForm.clqk}
                </td>
            </tr>

            <tr>
                <th width="20%">��ע
                   </th>
                <td colspan="3">
                        ${xsgzqkZbbForm.bz}
                </td>
            </tr>

            <tr>
                <th width="20%">����</th>
                <td colspan="3">
                    <html:hidden property="fjid" styleId="fjid" />
                    <%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
                    <script type="text/javascript">
                        //���ø���
                        jQuery(function(){
                            var gid = jQuery('#fjid').val();
                            jQuery.MultiUploader_q({
                                gid : gid
                            });
                        });
                    </script>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div style="height:35px"></div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="btn">
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

<script type="text/javascript">
    jQuery(function(){
        changeZcksjsrq();
    });
</script>
</body>
</html>

