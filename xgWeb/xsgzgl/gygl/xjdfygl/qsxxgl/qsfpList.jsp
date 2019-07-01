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
                url : "gygl_fygl_qsxxgl10698.do?method=qsfpList&type=query",
                colList : [
                    {label:'pk',name:'pk',index :'pk',key:true,hidden:true,width:'10%'},
                    {label:'楼栋代码',name:'lddm',index :'lddm',hidden:true,width:'10%'},
                    {label:'楼栋名称',name:'ldmc',index:'ldmc',width:'10%'},
                    {label:'寝室号',name:'qsh',index:'qsh',width:'10%'},
                    {label:'层号',name:'ch',index:'ch',width:'5%'},
                    {label:'房间类型',name:'fjlx',index:'fjlx',width:'5%',formatter:function(val,row){
                        if(val == "01"){
                            return "宿舍";
                        } else if(val == "02"){
                            return "值班室";
                        } else if(val == "03"){
                            return "厕所";
                        } else {
                            return val;
                        }
                    }},
                    {label:'房间走向',name:'fjzx',index:'fjzx',width:'5%',formatter:function(val,row){
                        if(val == "1"){
                            return "东";
                        } else if(val == "2"){
                            return "南";
                        } else if(val == "3"){
                            return "西";
                        } else if(val == "4"){
                            return "北";
                        } else {
                            return val;
                        }
                    }},
                    {label:'寝室性别',name:'qsxb',index:'qsxb',width:'5%',formatter:function(val,row){
                        if(val == "1"){
                            return "男";
                        } else if(val == "2"){
                            return "女";
                        } else {
                            return "混住";
                        }
                    }},
                    {label:'床位数',name:'cwss',index:'cwss',width:'5%'},
                    {label:'保留床位',name:'blcws',index:'blcws',width:'5%'},
                    {label:'空闲床位',name:'kxcws',index:'kxcws',width:'5%'},
                    {label:'已入住',name:'ylzcws',index:'ylzcws',width:'5%'},
                    {label:'所属书院/学院',name:'ssbm',index:'ssbm',width:'5%'}
                ],
                sortname: "qsh",
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
        function qsfp(){
            var ids = jQuery("#dataTable").getSeletIds();
            if(ids.length == 0){
                showAlert("请至少选择一个寝室！");
                return false;
            }

            var url = "gygl_fygl_qsxxgl10698.do?method=qsfp&pks="+ids.toString();
            showDialog("分配寝室",650,200,url);
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
                <li><a href="javascript:void(0);" onclick="qsfp();" class="btn_xg">分配书院/学院</a></li>
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
