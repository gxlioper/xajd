<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript" src="xsgzgl/jjgl/jjlsjg/js/jjlsjg.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption : "${gnmkmc}列表",
                pager : "pager",
                url : "jjgl_jjlsjggl.do?method=getJjlsjgListData",
                colList : [
                    { label : '学号', name : 'xh', index : 'xh', width : '10%', key:true , formatter : xhLink},
                    { label : '姓名', name : 'xm', index : 'xm', width : '10%' },
                    { label : '性别', name : 'xb', index : 'xb', width : '5%' },
                    { label : '年级', name : 'nj', index : 'nj', width : '8%' },
                    { label : '书院', name : 'symc', index : 'sydm', width : '12%' },
                    { label : '班级', name : 'bjmc', index : 'bjdm', width : '15%' },
                    { label : '擅长科目', name : 'sckmmcs', index : 'sckmmcs', width : '20%' },
                    { label : '登记人', name : 'djrxm', index : 'djr', width : '10%' },
                    { label : '登记时间', name : 'djsj', index : 'djsj', width : '10%' }],
                sortname : "djsj",
                sortorder : "desc" }
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

<html:form action="/jjgl_jjlsjggl">
    <input type="hidden" id="gnmkmc" value="${gnmkmc}"/>
    <%@ include file="/comm/hiddenValue.jsp"%>

    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>
                <logic:equal name="writeAble" value="yes">
                    <li>
                        <a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >增加</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="edit();return false;" class="btn_xg" >修改</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >删除</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr">导入</a>
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
