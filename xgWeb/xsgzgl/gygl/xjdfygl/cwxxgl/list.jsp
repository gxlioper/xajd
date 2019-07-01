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
                url : "gygl_fygl_cwxxgl10698.do?method=list&type=query",
                colList : [
                    {label:'pk',name:'pk',index :'pk',key:true,hidden:true,width:'10%'},
                    {label:'楼栋名称',name:'ldmc',index:'ldmc',width:'10%'},
                    {label:'寝室号',name:'qsh',index:'qsh',width:'10%'},
                    {label:'床位号',name:'cwh',index:'cwh',width:'5%'},
                    {label:'所属年级',name:'nj',index:'nj',width:'5%'},
                    {label:'xh',name:'xh',index:'xh',width:'5%',hidden:true},
                    {label:'现住学生',name:'xm',index:'xm',width:'5%'},
                    {label:'性别',name:'xb',index:'xb',width:'5%'},
                    {label:'入住时间',name:'rzsj',index:'rzsj',width:'10%'},
                    {label:'住宿到期时间',name:'zsdqsj',index:'zsdqsj',width:'10%'}
                ],
                sortname: "lddm,qsh,cwh",
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
        function cwsscsh(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length == 0){
                showAlert("请选择床位！");
                return false;
            }
            var ids = jQuery("#dataTable").getSeletIds();
            var url = "gygl_fygl_cwxxgl10698.do?method=szdqsj&pks="+ids.toString();
            showDialog("设置到期时间",650,200,url);
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
                <li><a href="javascript:void(0);" onclick="cwsscsh();" class="btn_xg">设置到期时间</a></li>
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
