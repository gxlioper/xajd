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
        jQuery(document).ready(function(){
            jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.sqid}&tt="+new Date().getTime());
        });
    </script>
    <style type="text/css">
        #shlccx_table th{text-align: center;}
        #shlccx_table tr{text-align: center;}
    </style>
</head>
<body>
<html:form method="post" styleId="demoForm" action="/gygl_gybz_wh">
    <div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>寝室信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>
                    学年
                </th>
                <td>
                    ${model.xn}
                </td>
                <th>
                    校区
                </th>
                <td>
                        ${model.xqmc}
                </td>
            </tr>
            <tr>
                <th>
                    宿舍楼栋号
                </th>
                <td>
                        ${model.ldmc}
                </td>
                <th>房间号</th>
                <td>
                        ${model.qsh}
                </td>
            </tr>
            <tr>
                <th>宿舍人数</th>
                <td>
                        ${model.rs}
                </td>
                <th>宿舍长</th>
                <td>
                        ${model.sszmc}
                </td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="4">
                    <span>寝室成员</span>
                </th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <td colspan="4">
                        <table id="shlccx_table" width="100%">
                            <tr>
                                <th>学院</th>
                                <th>姓名</th>
                                <th>班级</th>
                                <th>学号</th>
                                <th>联系电话</th>
                                <th>网址</th>
                                <th>电费分摊比例</th>
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
                    <th>功率</th>
                    <td>
                            ${model.gl}
                    </td>
                    <th>使用方式</th>
                    <td>
                            ${model.syfs}
                    </td>
                </tr>
                <tr>
                    <th>使用年限</th>
                    <td>
                            ${model.synx}
                    </td>
                    <th>安装日期</th>
                    <td>
                            ${model.azrq}
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
            <tr>
                <td colspan="4" id="shlccx">

                </td>
            </tr>
        </table>
    </div>
    <div style="height:30px;"></div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="btn">
                        <button type="button" onclick="iFClose();"  id="buttonClose">
                            关 闭
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