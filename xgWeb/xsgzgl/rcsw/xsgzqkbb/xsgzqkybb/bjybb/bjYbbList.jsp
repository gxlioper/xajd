<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript" src="xsgzgl/rcsw/xsgzqkbb/xsgzqkybb/bjybb/js/bjYbb.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption : "${gnmkmc}列表",
                pager : "pager",
                url : "rcsw_xsgzqkbb_bjybbgl.do?method=getBjYbbListData&xyybbid=${xsgzqkBjYbbForm.xyybbid}",
                colList : [
                    { label : 'key', name : 'id', index : 'id',key : true, hidden : true },
                    { label : 'xyybbid', name : 'xyybbid', index : 'xyybbid',  hidden : true },
                    { label : '辅导员', name : 'dbfdy', index : 'dbfdy', width : '8%' },
                    { label : '班级', name : 'bjmc', index : 'bjdm', width : '8%' },
                    { label : '男', name : 'mxss', index : 'mxss', width : '8%' },
                    { label : '女', name : 'wxss', index : 'wxss', width : '8%' },
                    { label : '召开班会<br/>次数', name : 'zkbhcs', index : 'zkbhcs', width : '8%' },
                    { label : '班级活动<br/>开展次数', name : 'bjhdkzcs', index : 'bjhdkzcs', width : '8%' },
                    { label : '深入宿舍<br/>次数', name : 'srsscs', index : 'srsscs', width : '8%' },
                    { label : '师生谈话<br/>次数', name : 'ssthcs', index : 'ssthcs', width : '8%' },
                    { label : '跟班听课<br/>次数', name : 'gbtkcs', index : 'gbtkcs', width : '8%' },
                    { label : '与家长联系<br/>情况', name : 'yjzlxqk', index : 'yjzlxqk', width : '8%' },
                    { label : '学生违纪<br/>及突发事件处理情况', name : 'tfsjclqk', index : 'tfsjclqk', width : '8%' },
                    { label : '休学人数', name : 'xxrs', index : 'xxrs', width : '8%' },
                    { label : '复学人数', name : 'fxrs', index : 'fxrs', width : '8%' },
                    { label : '退学人数', name : 'txrs', index : 'txrs', width : '8%' },
                    { label : '其他人数', name : 'qtrs', index : 'qtrs', width : '8%' }
                ],
                usedefined:true,
                usercols:16,
                sortname : "bjdm",
                sortorder : "asc" }
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        })
    </script>
</head>

<body>
<div class="tab_cur">
    <p class="location">
        <em>您的当前位置：</em><a>${title }</a>
    </p>
</div>

<html:form action="/rcsw_xsgzqkbb_bjybbgl" method="post" styleId="xsgzqkBjYbbForm">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <input type="hidden" id="gnmkmc" value="${gnmkmc}"/>
    <input type="hidden" id="xyybbid" value="${xsgzqkBjYbbForm.xyybbid}"/>

    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>
                <li>
                    <a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">增加</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="edit();return false;" class="btn_xg" >修改</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >删除</a>
                </li>

                <li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
                <li><a href="#" class="btn_fh" onclick="fhxyYbbList();return false;">返回</a></li>
            </ul>
        </div>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- 过滤条件 end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>${gnmkmc}列表&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" >
            <thead>
            <tr class="nowrap">
                <th width="4%" style="text-align:center" rowspan="2"><input type="checkbox" name="grid_selectAll"></th>
                <th width="12%" style="text-align:center" rowspan="2">辅导员</th>
                <th width="16%" style="text-align:center" rowspan="2">班级</th>
                <th width="10%" style="text-align:center" colspan="2">学生数</th>
                <th width="7%" style="text-align:center" rowspan="2">召开<br/>班会<br/>次数</th>
                <th width="7%" style="text-align:center" rowspan="2">班级活<br/>动开展<br/>次数</th>
                <th width="7%" style="text-align:center" rowspan="2">深入宿<br/>舍次数</th>
                <th width="7%" style="text-align:center" rowspan="2">师生谈<br/>话次数</th>
                <th width="7%" style="text-align:center" rowspan="2">跟班听<br/>课次数</th>
                <th width="7%" style="text-align:center" rowspan="2">与家长联<br/>系情况</th>
                <th width="7%" style="text-align:center" rowspan="2">学生违纪及突发<br/>事件处理情况</th>
                <th width="16%" style="text-align:center" colspan = "4">学籍异动等情况（人数）</th>
            </tr>
            <tr>
                <th width="5%" style="text-align:center">男</th>
                <th width="5%" style="text-align:center">女</th>
                <th width="5%" style="text-align:center">休学</th>
                <th width="5%" style="text-align:center">复学</th>
                <th width="5%" style="text-align:center">退学</th>
                <th width="5%" style="text-align:center">其他</th>
            </tr>
            </thead>
        </table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
