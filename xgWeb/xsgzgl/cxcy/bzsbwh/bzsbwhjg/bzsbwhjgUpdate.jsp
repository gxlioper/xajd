<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/cxcy/bzsbwh/bzsbwhjg/js/bzsbwhjgEdit.js"></script>
    <script type='text/javascript' src="js/check.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript">
    </script>
</head>
<body style="width: 100%">
<html:form action="/cxcy_bzsbwhjg" method="post" styleId="form" onsubmit="return false;">
    <html:hidden property="jgid" styleId="xq" value="${map.jgid}"/>
    <html:hidden property="xn" styleId="xn" value="${map.xn}"/>
    <html:hidden property="xq" styleId="xq" value="${map.xq}"/>
    <div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;height: 500px' >
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>学生基本信息</span>
                </th>
            </tr>
            </thead>
            <%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
            <thead>
            <tr>
                <th colspan="4">
                    <span>创新创业补助信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="15%">
                    学年
                </th>
                <td width="35%">
                        ${map.xn}
                </td>
                <th width="15%">学期</th>
                <td width="35%">
                        ${map.xqmc}
                </td>
            </tr>
            <tr>
                <th width="15%">
                    <span>填报人</span>
                </th>
                <td >
                        ${map.tbrmc}
                </td>
                <th>
                    填报时间
                </th>
                <td>
                        ${map.lrsj}
                </td>
            </tr>
            <tr>
                <th width="15%">
                    <span><font color="red">*</font>项目名称</span>
                </th>
                <td >
                    <html:text property="xmmc" styleId="xmmc" maxlength="50" style="width:80%;" value="${map.xmmc}"/>
                </td>
                <th>
                    <span><font color="red">*</font>补助金额（元）</span>
                </th>
                <td>
                    <html:text property="bzje" styleId="bzje" style="width:100px;"
                               onblur="checkMoney(this);"  value="${map.bzje}"/>
                </td>
            </tr>
            <tr>
                <th>
                    <span>附件</span>
                </th>
                <td colspan="3">
                    <html:hidden property="fj" styleId="fj"  value="${map.fj}"/>
                    <%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
                    <script type="text/javascript">
                        //调用附件
                        jQuery(function(){
                            jQuery.MultiUploader({
                                maxcount : 3,
                                //后缀
                                accept : 'png|gif|jpg|zip|rar|doc|docx',
                                //最大文件大小 单位M
                                maxsize: 10,
                                //存放附件的隐藏域的id
                                elementid : 'fj'
                            });
                        });
                    </script>
                </td>
            </tr>
            <tr>
                <th>
                    <font color="red">*</font>申请理由
                    <br /><font color="red">&lt;限500字&gt;</font>
                </th>
                <td colspan="3">
                    <html:textarea property='sqly' style="width:98%" styleId="sqly" rows='4'
                                   onblur="checkLen(this,500);"  value="${map.sqly}"/>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div style="height:25px"></div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="bz">
                        "<span class="red">*</span>"为必填项
                    </div>
                    <div class="btn">
                        <button type="button" onclick="save('update');">
                            保存
                        </button>
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

