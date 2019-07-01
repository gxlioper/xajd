<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript" src="xsgzgl/dtjs/rdjjfzpy/dkcj/js/dkcj.js"></script>
    <script type="text/javascript">
        var gridSetting = {
            caption:"列表",
            pager:"pager",
            url:"dtjs_dkcj.do?method=getList&type=query",
            colList:[
                {label:'id',name:'id', index: 'id',key:true,hidden:true},
                {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
                {label:'姓名',name:'xm', index: 'xm',width:'10%'},
                {label:'性别',name:'xb', index: 'xb',width:'6%'},
                {label:'书院',name:'symc', index: 'symc',width:'10%'},
                {label:'学院',name:'xymc', index: 'xymc',width:'10%'},
                {label:'专业',name:'zymc', index: 'zymc',width:'10%'},
                {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'10%'},
                {label:'行政班级',name:'bjmc', index: 'bjmc',width:'10%'},
                {label:'成绩',name:'dkcj', index: 'dkcj',width:'10%'},
                {label:'考试时间',name:'kssj', index: 'kssj',width:'10%'},
                {label:'录入时间',name:'lrsj', index: 'lrsj',hidden:true}
            ],
            sortname: "lrsj",
            sortorder: "desc"
        };

        jQuery(function(){
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });

    </script>
</head>

<body>

<div class="tab_cur">
    <p class="location">
        <em>您的当前位置：</em><a>${title }</a>
    </p>
</div>
<html:form action="/dtjs_dkcj">
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
                    <li>
                        <a href="javascript:void(0);" onclick="dr1();return false;" class="btn_dr">导入</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc">导出</a>
                    </li>
                </logic:equal>
            </ul>
        </div>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- 过滤条件 end-->
    </div>
</html:form>
<div class="formbox">
    <!--标题start-->
    <h3 class="datetitle_01">
        <span> 列表 </span>
    </h3>

    <table id="dataTable" ></table>
    <div id="pager"></div>

</div>
</body>
</html>
