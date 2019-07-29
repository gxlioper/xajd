<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript">
        var gridSetting;
        jQuery(function(){
            gridSetting = {
                caption:"",
                pager:"pager",
                url:"xyfd_fdjswh.do?method=selectFds&type=query",
                colList:[
                    {label:'id',name:'id', index: 'id',key:true,hidden:true },
                    {label:'辅导室名称',name:'fdsmc', index: 'fdsmc',width:'10%'},
                    {label:'辅导室地点',name:'fdsdd', index: 'fdsdd',width:'20%'},
                    {label:'使用日期',name:'syksrq', index: 'syksrq',width:'10%',formatter:function (cell,rowObject) {
                            return rowObject["syksrq"] + "-" + rowObject["syjsrq"];
                        }},
                    {label:'使用结束日期',name:'syjsrq', index: 'syjsrq',width:'1%',hidden:true},
                    {label:'使用时间',name:'sykssj', index: 'sykssj',width:'10%',formatter:function(cell,rowObject){
                            return rowObject["sykssj"] + "-" + rowObject["syjssj"];
                        }},
                    {label:'使用结束时间',name:'syjssj', index: 'syjssj',width:'1%',hidden:true},
                    {label:'情况描述',name:'qkms', index: 'qkms',width:'1%',hidden:true},
                    {label:'运行情况',name:'yxzt', index: 'yxzt',width:'10%',formatter:function(cell,rowObject){
                            if(rowObject["yxzt"]=="1"){
                                return "正常运行";
                            }else if(rowObject["yxzt"]=="2"){
                                return "停止运行";
                            }else {
                                return rowObject["qkms"];
                            }
                        }},
                    {label:'操作',name:'', index: '',width:'10%',formatter:function(cell,rowObject){
                        return "<label class='btn_01' onclick=\"select('"+rowObject["id"]+"','"+rowObject["fdsmc"]+"');\">选择</label>";
                    }}
                ],
                sortname: "id",
                sortorder: "desc",
                radioselect:false
            }

            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });

        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        function select(id,fdsmc){
            var api = frameElement.api;
            var parent = api.get('parentDialog');
            parent.jQuery("#fds").val(id);
            parent.jQuery("#fdsmc").val(fdsmc == 'null' ? '' : fdsmc);
            iFClose();
        }
    </script>
</head>

<body>
<html:form action="/qgzx_jcdmwh_ajax">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- 按钮 -->
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- 过滤条件 end-->
    </div>
</html:form>
<div class="main_box">
    <h3 class=datetitle_01>
        <span>查询结果&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
