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
    <script type="text/javascript">
    </script>
</head>
<body style="width:100%">
<html:form action="/szdw_fdy_ywxxypxjg" method="post" styleId="demoForm">
    <div style='width:100%;height:465px;overflow-x:hidden;overflow-y:auto;'>
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
                    <th>ְ����</th>
                    <input type="hidden" name="zgh" value="${fdyxx.zgh}">
                    <td>${fdyxx.zgh}</td>
                    <th>����</th>
                    <td>${fdyxx.xm}</td>
                </tr>
                <tr>
                    <th width="30%">�Ա�</th>
                    <td width="30%">${fdyxx.xb}</td>
                    <th>����</th>
                    <td>${fdyxx.mzmc}</td>
                </tr>
                <tr>
                    <th>���ڲ���</th>
                    <td>${fdyxx.bmmc}</td>
                    <th>������Ժ</th>
                    <td>${fdyxx.symc}</td>
                </tr>
                <tr>
                    <th>������ò</th>
                    <td>${fdyxx.zzmmmc}</td>
                    <th>��ϵ�绰</th>
                    <td>${fdyxx.lxdh}</td>
                </tr>
                <tr>
                    <th>��У����ʱ��</th>
                    <td colspan="3">${fdyxx.rxgzsj}</td>
                </tr>
            </tbody>
            <thead>
                <tr>
                    <th colspan="4">
                        <span>��ѵ��Ŀ��Ϣ</span>
                    </th>
                </tr>
            </thead>
            <tbody>
            <tr>
                <th>��ѵ����</th>
                <td>
                    ${model.xmmc}
                </td>
                <th>��ѵʱ��</th>
                <td>
                    ${model.pxsj}
                </td>
            </tr>
            <tr>
                <th>��֯����</th>
                <td>
                    ${model.zzbmmc}
                </td>
                <th>ѧʱ</th>
                <td>
                    ${model.pxxs}
                </td>
            </tr>
            <tr>
                <th>��ѵ����</th>
                <td colspan="3">
                    ${model.pxjj}
                </td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="4">
                    <span>��ѵ�����Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>�������</th>
                <td colspan="3">
                        ${model.kqqk}
                </td>
            </tr>
            <tr>
                <th>��ѵ�ĵ�</th>
                <td colspan="3">
                    ${model.pxxd}
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

