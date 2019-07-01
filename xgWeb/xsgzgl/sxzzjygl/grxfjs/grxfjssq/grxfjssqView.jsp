<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script language="javascript" src="js/check.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/sxzzjygl/grxfjs/grxfjssq/js/grxfjssqEdit.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid="+jQuery("#sqid").val()+"&tt="+ new Date().getTime());
        })
    </script>
    <style type="text/css">
        *{font-size: 14px;}
        textarea{width: 98%}
    </style>
</head>
<body>
<html:form action="/sxzzjy_grxfjssq" method="post"  styleId="form">
    <html:hidden property="sqid" styleId="sqid" />
    <div style='width:100%; height:500px; overflow-y:auto;overflow-x:hidden'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>基本信息</span>
                </th>
            </tr>
            </thead>
            <%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
            <tbody id="">
            <tr style="">
                <th colspan="4"  style="text-align: left">
                    个人学风建设
                </th>
            </tr>
            <tr style="">
                <th >
                   名 称
                </th>
                <td colspan="3">
                        ${map.xfjsmc}
                </td>
            </tr>
            <tr style="">
                <th >
                    申报类型
                </th>
                <td >
                        ${map.sblxmc}
                </td>
                <th >
                    学年学期
                </th>
                <td>
                        ${map.xn}${map.xqmc}
                </td>
            </tr>
            <tr style="">
                <th>
                    本学年目标
                </th>
                <td   colspan="3">
                    <table id="bxnmbTable">
                        <tr>
                            <th width="27%">平均学分积</th>
                            <td width="23%">${map.pjxfj}分</td>
                            <th width="27%">平均学分积小班排名</th>
                            <td width="23%">${map.pjxfjpm}名</td>
                        </tr>
                        <tr>
                            <th width="27%">综合测评成绩</th>
                            <td width="23%">${map.zhcpcj}分</td>
                            <th width="27%">综合测评成绩排名</th>
                            <td width="23%">${map.zhcpcjpm}名</td>
                        </tr>
                        <tr>
                            <th width="27%">综合测评成绩年级（专业）百分比</th>
                            <td >${map.njbfb}%</td>
                            <th width="27%">所在宿舍获奖次数</th>
                            <td >${map.sshjcs}次</td>
                        </tr>
                        <tr>
                            <th width="27%">
                                其他<br>
                                <span style="color: red">(限100字)</span>
                            </th>
                            <td colspan="3">
                                    ${map.qt}
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr style="">
                <th>
                    建设思路和计划

                </th>
                <td   colspan="3">${map.jssl}
                </td>
            </tr>
            <tr>
                <th>
                    附件
                </th>
                <td colspan="3">
                    <html:hidden property="fjid" styleId="fjid" value="${map.fjid}"/>
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
        <logic:notEqual value="无需审核" name="shztmc">
            <table width="100%" border="0" class="formlist">
                <thead>
                <tr>
                    <th colspan="4">
                        <span>审核信息</span>
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td colspan="4" id="shlccx">

                    </td>
                </tr>

                </tbody>

            </table>
        </logic:notEqual>
    </div>
</html:form>
    <table width="100%" border="0" class="formlist">
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="btn">

                    <button type="button" type="button" onclick="iFClose();">
                        关 闭
                    </button>
                </div>
            </td>
        </tr>
        </tfoot>
    </table>


</body>
</html>

