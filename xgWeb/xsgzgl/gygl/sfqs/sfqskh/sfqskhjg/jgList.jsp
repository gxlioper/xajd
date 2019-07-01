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
                url : "gygl_sfqskh_jgwh.do?method=jgList&type=query",
                colList : [
                    {label:'jgid',name:'jgid',index :'jgid',key:true,hidden:true,width:'10%'},
                    {label:'学年',name:'xn',index:'xn',width:'10%',formatter:function(val,row){
                        return "<a href='javascript:void(0);' onclick='view(\""+row["sqid"]+"\")'>"+val+"</a>";
                    }},
                    {label:'楼栋名称',name:'ldmc',index:'ldmc',width:'10%'},
                    {label:'寝室号',name:'qsh',index:'qsh',width:'10%'},
                    {label:'申请人',name:'xm',index:'xm',width:'5%'},
                    {label:'申请时间',name:'sqsj',index:'sqsj',width:'10%'},
                    {label:'sjly',name:'sjly',index:'sjly',width:'10%',hidden:true},
                    {label:'sqid',name:'sqid',index:'sqid',width:'10%',hidden:true}
                ],
                sortname: "xn,qsh",
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

        function view(sqid){
            var url="gygl_sfqskh_wh.do?method=view&sqid="+sqid;
            showDialog("查看", 700, 600, url);
        }

        //导出方法
        function exprotData() {
            setSearchTj();//设置高级查询条件
            var url = "gygl_sfqskh_jgwh.do?method=exportData";//dcglbh,导出功能编号
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
                <li><a href="javascript:void(0);" onclick="exprotData();return false;" class="btn_dc">导出</a></li>
            </ul>
        </div>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- 过滤条件 end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>文明寝室学生名单</span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
