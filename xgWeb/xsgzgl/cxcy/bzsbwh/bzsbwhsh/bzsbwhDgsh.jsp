<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
    <script type="text/javascript" src="xsgzgl/cxcy/bzsbwh/bzsbwhsh/js/bzsbwhsh.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var sqid = jQuery("#sqid").val();
            var splc = jQuery("#splc").val();
            var shid = jQuery("#shid").val();
            jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid="+sqid+"&tt="+new Date().getTime());
            jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid="+splc+"&shid="+shid);
        });
    </script>
</head>
<body>
<html:form action="/cxcy_bzsbwhsh" method="post" styleId="form">
    <div id="filepathHiddenDiv" style="display: none;">
        <div id="commonfileupload-list-0" style="padding: 5px;"></div>
    </div>
    <html:hidden property="sqid" styleId="sqid"/>
    <html:hidden property="splc" styleId="splc"/>
    <html:hidden property="shid" styleId="shid"/>
    <div style="height:460px;overflow-x:hidden;overflow-y:auto;">
        <div class="tab"  id="content" style="margin-top: 5px; overflow-x:hidden;" >

        </div>
        <table width="100%" border="0" class="formlist">
            <thead>
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
                    <span>
                        申请信息
                    </span>
                </th>
            </tr>
            </thead>
            <tbody>
                <tr style="">
                    <th width="14%">
                        项目名称
                    </th>
                    <td width="36%">
                            ${map.xmmc}
                    </td>
                    <th width="14%">
                        补助金额
                    </th>
                    <td width="36%">
                            ${map.bzje}
                    </td>
                </tr>
                <tr style="">
                    <th width="14%">
                        学年
                    </th>
                    <td width="36%">
                            ${map.xn}
                    </td>
                    <th width="14%">
                        学期
                    </th>
                    <td width="36%">
                            ${xqmc}
                    </td>
                </tr>
                <tr style="">
                    <th width="14%">
                        填报人
                    </th>
                    <td width="36%">
                            ${tbrmc}
                    </td>
                    <th width="14%">
                       填报时间
                    </th>
                    <td width="36%">
                            ${map.sqsj}
                    </td>
                </tr>
                <tr style="">
                    <th>
                        申请理由
                    </th>
                    <td   colspan="3">
                            ${map.sqly}
                    </td>
                </tr>
                <tr>
                    <th>
                        附件
                    </th>
                    <td colspan="3">
                        <html:hidden property="fj" styleId="fj" value="${map.fj}"/>
                        <%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
                        <script type="text/javascript">
                            //调用附件
                            jQuery(function(){
                                var gid = jQuery('#fj').val();
                                jQuery.MultiUploader_q({
                                    gid : gid
                                });
                            });
                        </script>
                    </td>
                </tr>
            </tbody>
        </table>

        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>审核历史</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td colspan="4" id="shlccx">

                </td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="4">
                    <span>审核信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>
                    <font color="red">*</font>审核结果
                </th>
                <td colspan="3" id="shjgSpan">

                </td>
            </tr>
            <tr>
                <th width="20%">
                    <font color="red">*&nbsp;</font> 审核意见
                    <br />
                    <font color="red">(限200字)</font>
                </th>
                <td colspan="3">
                    <jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=ylbx&id=shyj" />
                    <textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <table width="100%" border="0" class="formlist">
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="btn">
                    <button type="button" name="保存"  onclick="saveSh();return false;">
                        保 存
                    </button>
                    <button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">
                        关 闭
                    </button>
                </div>
            </td>
        </tr>
        </tfoot>
    </table>
    <!-- 提示信息 -->
    <%@ include file="/comm/other/tsxxNew.jsp"%>
</html:form>
</body>
</html>
