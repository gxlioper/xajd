<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/dtjs/llxxjl/sq/js/llxxjlSq.js"></script>
    <script type="text/javascript" src="xsgzgl/xstgl/comm/js/comm.js"></script>
    <script type='text/javascript' src="js/check.js"></script>
    <script type="text/javascript" src="js/provicecitylocal.js"></script>
    <link rel="stylesheet" href="js/provicecitylocal.css" type="text/css"/>
    <script type='text/javascript'>
        jQuery(function(){
            proviceCiyyLocalMain({type:"add",id:"ddssx",flag:"yxxdz"});
        })
    </script>
</head>
<body style="width: 100%">
<html:form action="/llxxjl_sq" method="post" styleId="form" onsubmit="return false;">
    <div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
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
                    <span><font id="gnmkmc_prompt_span"></font></span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="20%">
                    学年
                </th>
                <td width="30%">
                        ${xn}
                    <input type="hidden" value="${xn}" id="xn" name="xn">
                </td>
                <th>学期</th>
                <td>
                        ${xqmc}
                    <input type="hidden" value="${xq}" id="xq" name="xq">
                </td>
            </tr>
            <tr>
                <th>
                    <span class="red">*</span>活动名称
                </th>
                <td colspan="3">
                    <html:text property="hdmc" styleId="hdmc" style="width:85%" onblur="checkLen(this,50);"/>
                </td>

            </tr>
            <tr>
                <th ><span class="red">*</span>时间</th>
                <td >
                    <input type="hidden" id="nowDate" name="nowDate" value="${nowDate}">
                    <html:text property="sj"  styleId="sj"
                               onclick="return showCalendar('sj','yyyy-MM-dd HH:mm',true,'nowDate');" />
                </td>
                <th>
                    <font color="red">*</font>主办单位
                </th>
                <td>
                    <html:text property="zbdw" styleId="zbdw" style="width:95%" onblur="checkLen(this,50);"/>
                </td>

            </tr>
            <tr>
                <th>
                    <font color="red">*</font>地点
                </th>
                <td colspan="3">
                    <html:hidden  property="ddssx" styleId="ddssx"/>
                    <html:text property="dd" styleId="dd"  onblur="checkLen(this,50);"/>
                </td>
            </tr>
            <tr>
                <th>附件</th>
                <td colspan="3">
                    <html:hidden property="fjid" styleId="fjid" />
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
                                elementid : 'fjid'
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
                    <div class="bz">
                        "<span class="red">*</span>"为必填项
                    </div>
                    <div class="btn">
                        <button type="button" onclick="saveAdd('save');">
                            保存草稿
                        </button>
                        <button type="button" onclick="saveAdd('submit');">
                            提交申请
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
    <%@ include file="/comm/other/tsxx.jsp"%>
</html:form>
</body>
</html>

