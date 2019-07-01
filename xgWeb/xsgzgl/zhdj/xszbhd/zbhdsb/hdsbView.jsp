<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/xszbhd/zbhdsb/js/hdsbEdit.js"></script>

    <script type="text/javascript">

    </script>
    <style type="text/css">
        textarea{width: 98%}
    </style>
</head>
<body>
<html:form action="/xszbhd_hdsb"  method="post"  styleId="form">
    <%--<html:hidden property="dzbid" styleId="dzbid"  />
    <html:hidden property="type" styleId="type"  />
    <html:hidden property="hdid" styleId="hdid"  />
    <html:hidden property="xn" styleId="xqdm"  />
    <html:hidden property="xqdm" styleId="xqdm"  />
    <html:hidden property="hdsbid" styleId="hdsbid"  />--%>
    <div style='width:100%; height:360px; overflow-y:auto;overflow-x:hidden'>
        <table width="100%" border="0" class="formlist">
            <tbody id="">
            <tr style="">
                <th width="16%">
                    学年
                </th>
                <td width="34%">
                        ${map.xn}
                </td>
                <th  width="16%">
                    支部
                </th>
                <td width="34%">
                        ${map.dzbmc}
                </td>
            </tr>
            <tr style="">
                <th >
                    学期
                </th>
                <td >
                        ${map.xqmc}
                </td>
                <th >
                    所属学院
                </th>
                <td >
                        ${map.xymc}
                </td>
            </tr>
            <tr style="">
                <th >
                    时间
                </th>
                <td >
                        ${map.hdsj}
                </td>
                <th >
                     地点
                </th>
                <td >
                        ${map.hddd}
                </td>
            </tr>
            <tr style="">
                <th >
                     应到人数
                </th>
                <td >
                        ${map.ydrs}
                </td>
                <th >
                    实到人数
                </th>
                <td >
                        ${map.sdrs}
                </td>
            </tr>
            <tr style="">
                <th >
                    请假人数
                </th>
                <td >
                        ${map.qjrs}
                </td>
                <th >
                     无故不到人数
                </th>
                <td >
                        ${map.wgbdrs}
                </td>
            </tr>
            <tr style="">
                <th >
                     三会一课/党日活动
                </th>
                <td >
                        ${map.shykdrhdmc}
                </td>
                <th >
                     活动类型
                </th>
                <td >
                        ${map.hdlxmc}
                </td>
            </tr>

            <tr style="">
                <th >
                        活动主题
                </th>
                <td >
                        ${map.hdzt}
                </td>
                <th >
                     时长
                </th>
                <td >
                        ${map.hdsc}
                </td>
            </tr>
            <tr style="">
                <th >
                    提交思想报告人员数
                </th>
                <td   colspan="3">
                        ${map.tjsxbgrs}
                </td>
            </tr>
            <tr style="">
                <th>
                    会议记录
                </th>
                <td    colspan="3">
                        ${map.hyjl}
                </td>
            </tr>

            <tr>
                <th>
                    附件
                </th>
                <td  colspan="3">
                    <div id="commonfileupload-list-0" style="padding: 5px;"></div>
                    <script type="text/javascript">
                        //调用附件
                        jQuery(function(){
                            var gid = "${map.fjid}";
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
    <table width="100%" border="0" class="formlist">
        <tfoot>
        <tr>
            <td colspan="4">
                <%--<div class="bz">
                    "<span class="red">*</span>"为必填项
                </div>--%>
                <div class="btn">
                    <%--<button id="buttonSave" onclick="save('update');return false;">
                        保 存
                    </button>--%>
                    <button onclick="Close();return false;">
                        关 闭
                    </button>

                </div>
            </td>
        </tr>
        </tfoot>
    </table>

</html:form>
</body>
</html>

