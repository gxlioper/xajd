<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/xstgl/comm/js/comm.js"></script>
    <script type="text/javascript" src="js/provicecitylocal.js"></script>
    <script type='text/javascript'>
        jQuery(function(){
            proviceCiyyLocalMain({type:"view",id:"ddssx",flag:"yxxdz"});
        })
    </script>
</head>
<body style="width: 100%">
<html:form action="/shsjjl_jg" method="post" styleId="form" onsubmit="return false;">
    <div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>学生基本信息</span>
                </th>
            </tr>
            </thead>
            <%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
            <thead>
            <tr>
                <th colspan="4">
                    <span><font id="gnmkmc_prompt_span"></font></span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>
                    学年
                </th>
                <td>
                        ${rs.xn}
                </td>
                <th>
                    学期
                </th>
                <td>
                        ${rs.xqmc}
                </td>
            </tr>
            <tr>
                <th>
                    活动名称
                </th>
                <td  colspan="3">
                        ${rs.hdmc}
                </td>

            </tr>
            <tr>
                <th >时间</th>
                <td >
                        ${rs.sj}
                </td>
                <th>
                    主办单位
                </th>
                <td>
                        ${rs.zbdw}
                </td>
            </tr>
            <tr>
                <th >
                    地点
                </th>
                <td colspan="3">
                    <html:hidden  property="ddssx" styleId="ddssx"/>
                        ${rs.dd}
                </td>
            </tr>
            <tr>
            <tr>
                <th>附件</th>
                <td colspan="3">
                    <%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
                    <html:hidden property="fjid" styleId="fjid" value="${rs.fjid}"/>
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
                        <button type="button" onclick="Close();return false;">
                            关 闭
                        </button>
                    </div>
                </td>
            </tr>
            </tfoot>
        </table>

    </div>
    <%@ include file="/comm/other/tsxx.jsp"%>
</html:form>
</body>
</html>

