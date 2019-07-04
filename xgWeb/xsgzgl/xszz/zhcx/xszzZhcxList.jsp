<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type='text/javascript' src="js/xgutil.js"></script>
    <script type='text/javascript' src='dwr/engine.js'></script>
    <script type='text/javascript' src='dwr/util.js'></script>
    <script type='text/javascript' src='dwr/interface/exportData.js'></script>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption:"",
                pager:"pager",
                url:"xszz_newZhcx.do?method=zhcxList&type=query",
                colList:[
                    {label:'学号',name:'xh', index: 'xh',width:'10%'},
                    {label:'姓名',name:'xm', index: 'xm',width:'10%'},
                    {label:'学年',name:'xn', index: 'xn',width:'10%'},
                    {label:'学院代码',name:'xydm', index: 'xydm',width:'10%',hidden:'true'},
                    {label:'学院',name:'xymc', index: 'xymc',width:'10%'},
                    {label:'专业代码',name:'zydm', index: 'zydm',width:'10%',hidden:'true'},
                    {label:'专业',name:'zymc', index: 'zymc',width:'10%'},
                    {label:'专业班级代码',name:'zybj', index: 'zybj',width:'10%',hidden:'true'},
                    {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'10%'},
                    {label:'书院代码',name:'sydm', index: 'sydm',width:'10%',hidden:'true'},
                    {label:'书院',name:'symc', index: 'symc',width:'10%'},
                    {label:'行政班级代码',name:'bjdm', index: 'bjdm',width:'10%',hidden:'true'},
                    {label:'行政班级',name:'bjmc', index: 'bjmc',width:'10%'},

                    {label:'奖学金',name:'jxj', index: 'jxj',width:'10%'},
                    {label:'助学金',name:'zxj', index: 'zxj',width:'10%'},
                    {label:'勤工助学工时',name:'qggs', index: 'qggs',width:'10%'},
                    {label:'勤工助学工资',name:'qgje', index: 'qgje',width:'10%'},
                    {label:'困难程度',name:'rddc', index: 'rddc',width:'10%',hidden:'true'},
                    {label:'经济困难程度',name:'kncd', index: 'kncd',width:'10%'},
                    {label:'生源地贷款',name:'syddkje', index: 'syddkje',width:'10%'},
                    {label:'国家贷款',name:'gjdkje', index: 'gjdkje',width:'10%'},
                    {label:'是否建档立卡',name:'sfjdlk', index: 'sfjdlk',width:'10%'}
                ],
                sortname: "xn",
                sortorder: "asc"
            }
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });
        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }


        //dcglbh,导出功能编号
        var DCGLBH = "xszz_new_zhcx.do";

        //自定义导出 功能
        function exportConfig() {
            //DCCLBH导出功能编号,执行导出函数
            customExport(DCGLBH, exprotData);
        }

        //导出方法
        function exprotData() {
            setSearchTj();//设置高级查询条件
            var url = "xszz_newZhcx.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,导出功能编号
            url = addSuperSearchParams(url);//设置高级查询参数
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
    </script>
</head>

<body>

<div class="tab_cur">
    <p class="location">
        <em>您的当前位置：</em><a>${title }</a>
    </p>
</div>
<html:form action="/xszz_newZhcx">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
            <%--<logic:equal value="yes" name="writeAble">--%>
        <div class="buttonbox">
            <ul>
                <li><a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc">导出</a></li>
            </ul>
        </div>
            <%--</logic:equal>--%>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- 过滤条件 end-->
    </div>
</html:form>
<div class="formbox">
    <!--标题start-->
    <h3 class="datetitle_01">
        <span></span>
    </h3>
    <div style="overflow-x:scroll;">
    <table id="dataTable" ></table>
    </div>
    <div id="pager"></div>

</div>
</body>
</html>
