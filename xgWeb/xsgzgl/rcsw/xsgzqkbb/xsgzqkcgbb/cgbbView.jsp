<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="xsgzgl/rcsw/xsgzqkbb/xsgzqkcgbb/js/cgbb.js"></script>
</head>
<body style="width: 100%">
<html:form action="/rcsw_xsgzqkbb_cgbbgl" method="post" styleId="xsgzqkCgbbForm" onsubmit="return false;">
    <div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>学生工作情况常规报表信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="20%">
                    报送人
                </th>
                <td width="30%">
                        ${xsgzqkCgbbForm.bsrmc}
                </td>
                <th>
                    报送单位
                </th>
                <td>
                        ${xsgzqkCgbbForm.bsdwmc}
                </td>
            </tr>
            <tr>
                <th width="20%">
                    学年
                </th>
                <td width="30%">
                        ${xsgzqkCgbbForm.xn}
                </td>
                <th>学期</th>
                <td>
                        ${xsgzqkCgbbForm.xqmc}
                </td>
            </tr>
            <tr>
                <th width="20%">
                    报送主题
                </th>
                <td width="80%" colspan="3">
                        ${xsgzqkCgbbForm.bszt}
                </td>
            </tr>

            <tr>
                <th width="20%">报送内容
                </th>
                <td colspan="3">
                        ${xsgzqkCgbbForm.bsnr}
                </td>
            </tr>

            <tr>
                <th width="20%">附件</th>
                <td colspan="3">
                    <html:hidden property="fjid" styleId="fjid" />
                    <%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
                    <script type="text/javascript">
                        //调用附件
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
                            关闭
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

