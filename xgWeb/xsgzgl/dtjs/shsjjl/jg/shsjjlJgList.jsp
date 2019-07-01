<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/dtjs/shsjjl/jg/js/shsjjlJg.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption : "${gnmkmc}列表",
                pager : "pager",
                url : "shsjjl_jg.do?method=getList&type=query",
                colList : [
                    { label : 'key', name : 'jgid', index : 'jgid',key : true, hidden : true },
                    { label : '学号', name : 'xh', index : 'xh', width : '10%', formatter : xhLink},
                    { label : '姓名', name : 'xm', index : 'xm', width : '8%' },
                    { label : '学年', name : 'xn', index : 'xn', width : '8%' },
                    { label : '学期', name : 'xqmc', index : 'xqmc', width : '8%' },
                    { label : '活动名称', name : 'hdmc', index : 'hdmc', width : '12%'},
                    { label : '时间', name : 'sj', index : 'sj', width : '10%' },
                    { label : '地点', name : 'ddxxdz', index : 'ddxxdz', width : '10%' },
                    { label : '举办单位', name : 'zbdw', index : 'zbdw', width : '10%' },
                    { label : '数据来源', name : 'sjly', index : 'sjly', hidden : true},
                    { label : 'lrsj', name : 'lrsj', index : 'lrsj', hidden : true}],
                sortname : "lrsj",
                sortorder : "desc" };
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
<html:form action="/shsjjl_jg">
    <input type="hidden" id="gnmkmc" value="${gnmkmc}"/>
    <%@ include file="/comm/hiddenValue.jsp"%>

    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>
                <logic:equal name="writeAble" value="yes">
                    <li>
                        <a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >增加</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >删除</a>
                    </li>
                </logic:equal>

                <li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>

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
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
