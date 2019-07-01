<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption : "楼栋信息列表",
                pager : "pager",
                url : "gygl_fygl_fsbjwh.do?method=list&type=query",
                colList : [
                    {label:'pk',name:'pk',index :'pk',key:true,hidden:true,width:'10%'},
                    {label:'校区名称',name:'xqmc',index:'xqmc',width:'10%'},
                    {label:'楼栋名称',name:'ldmc',index:'ldmc',width:'10%'},
                    {label:'寝室号',name:'qsh',index:'qsh',width:'10%'},
                    {label:'床位数',name:'cws',index:'cws',width:'5%'},
//                    {label:'年度',name:'xn',index:'xn',width:'5%'},
                    {label:'粉刷标记',name:'fsbj',index:'fsbj',width:'7%',formatter:function(val,row){
                        if(val == "已粉刷"){
                            return "已粉刷";
                        } else {
                            return "未粉刷";
                        }
                    }},
                    {label:'最后粉刷日期',name:'fsrq',index:'fsrq',width:'10%'}
                ],
                sortname: "lddm,qsh",
                sortorder: "asc"
            }
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });
        function searchRs() {
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        function fsbj(){
            var row = jQuery("#dataTable").getSeletRow();
            var url = "gygl_fygl_fsbjwh.do?method=add";
            if(row.length != 0){
                var ids = jQuery("#dataTable").getSeletIds();
                url += "&pks="+ids.toString();
            }

            showDialog("粉刷标记",600,300,url);
        }
        var DCCLBH = "gygl_fygl_fsbj.do";
        //自定义导出 功能
        function exportConfig() {
            //DCCLBH导出功能编号,执行导出函数
            customExport(DCCLBH, exportData);
        }

        //导出方法
        function exportData() {
            setSearchTj();//设置高级查询条件
            var url = "gygl_fygl_fsbjwh.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
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
<html:form action="/gyglnew_wmqsxsmd_12688">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>
                <%--<logic:equal name="writeAble" value="yes">--%>
                    <li><a href="javascript:void(0);" onclick="fsbj();" class="btn_xg">粉刷标记</a></li>
                <%--</logic:equal>--%>
                <li><a href="#" class="btn_dc" onclick="exportData();return false;">导出</a></li>
            </ul>
        </div>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- 过滤条件 end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span></span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
