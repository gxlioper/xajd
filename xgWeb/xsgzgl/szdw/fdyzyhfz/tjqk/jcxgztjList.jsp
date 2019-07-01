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
                url:"szdw_fdyjcxgztj.do?method=jcxgztjList&type=query",
                colList:[
                    {label:'学年',name:'xn', index: 'xn',width:'10%'},
                    {label:'学期',name:'xq', index: 'xq',width:'10%'},
                    {label:'部门代码',name:'bmdm', index: 'bmdm',width:'10%',hidden:'true'},
                    {label:'部门',name:'bmmc', index: 'bmmc',width:'10%'},
                    {label:'职工号',name:'zgh', index: 'zgh',width:'10%'},
//                        ,formatter:function(cell,row){
//                        return "<a href='javascript:void(0);' onclick='view(\""+row["sqid"]+"\")'>"+cell+"</a>";
//                    }},
                    {label:'姓名',name:'xm', index: 'xm',width:'10%'},
                    {label:'学生总人数',name:'xsrs', index: 'xsrs',width:'10%'},
                    {label:'重点关注学生数',name:'zdgzrs', index: 'zdgzrs',width:'10%'},
                    {label:'谈话人数',name:'thrs', index: 'thrs',width:'10%'},
                    {label:'重点关注学生谈话数',name:'zdgzthcs', index: 'zdgzthcs',width:'10%'},
                    {label:'谈话总次数',name:'thcs', index: 'thcs',width:'10%'},
                    {label:'家访人数',name:'jfrs', index: 'jfrs',width:'10%'},
                    {label:'家访总次数',name:'jfcs', index: 'jfcs',width:'10%'},
                    {label:'主题班会总次数',name:'ztbhcs', index: 'ztbhcs',width:'10%'}
                ],
                sortname: "xn,xq",
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
        var DCGLBH = "szdw_fdy_jcxgztj.do";

        //自定义导出 功能
        function exportConfig() {
            //DCCLBH导出功能编号,执行导出函数
            customExport(DCGLBH, exprotData);
        }

        //导出方法
        function exprotData() {
            setSearchTj();//设置高级查询条件
            var url = "szdw_fdyjcxgztj.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,导出功能编号
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
<html:form action="/szdw_fdyjcxgztj">
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
