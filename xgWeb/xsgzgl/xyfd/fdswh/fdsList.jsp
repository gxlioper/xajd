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
                caption:"",
                pager:"pager",
                url:"xyfd_fdswh.do?method=fdsList&type=query",
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
                    }}
                ]
            }
            gridSetting["params"]=getSuperSearch();
            jQuery("#dataTable").initGrid(gridSetting);
        });

        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }

        function add(){
            showDialog("新增辅导室",700,350,"xyfd_fdswh.do?method=addfds");
        }
        function update(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length!=1){
                showAlertDivLayer("请选择一条您要修改的记录");
                return false;
            }

            showDialog("修改辅导室",700,350,"xyfd_fdswh.do?method=updatefds&id="+rows[0].id );

        }
        function deleteFds(){
            var ids = jQuery("#dataTable").getSeletIds();
            if (ids.length == 0) {
                showAlertDivLayer("请选择您要删除的记录！");
            } else {
                var rows = jQuery("#dataTable").getSeletRow();
                showConfirmDivLayer("您确定要删除选择的记录吗？", {
                    "okFun" : function() {
                        jQuery.post("xyfd_fdswh.do?method=deleteFds", {
                            values : ids.toString()
                        }, function(data) {
                            var mes="成功删除了<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>条数据";
                            mes+="</br>";
                            if(data["nodel"]!="-1"){
                                mes+="<font color='red'>"+data["nodel"]+"</font>";
                                mes+="正常运行中不能删除!";
                            }
                            showAlertDivLayer(mes);
                            jQuery("#dataTable").reloadGrid();
                        }, 'json');
                    }
                });
            }
        }
    </script>
</head>
<body>
<div class="tab_cur">
    <p class="location">
        <em>您的当前位置：</em><a>${title }</a>
    </p>
</div>
<html:form action="/hdgl_hdgl_tj">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>
                <li><a href="#" class="btn_zj" onclick="add();return false;">增加</a></li>
                <li><a href="#" class="btn_xg" onclick="update();return false;">修改</a></li>
                <li><a href="#" class="btn_sc" onclick="deleteFds();return false;">删除</a></li>
                <%--<li><a href="#" class="btn_dr" onclick="importConfig();return false;">导入</a></li>--%>
                <%--<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>--%>
                    <%--<li><a href="#" class="btn_dc" onclick="return false;">推送报名数据</a></li>--%>
            </ul>
        </div>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- 过滤条件 end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>活动签到&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
