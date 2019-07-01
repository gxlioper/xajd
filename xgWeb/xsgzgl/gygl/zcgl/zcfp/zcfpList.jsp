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
        var gridSetting = {
            caption : "楼栋资产数",
            pager : "pager",
            url : "gygl_zcgl_zcfpgl.do?method=zcfpList&type=query",
            colList : [
                {label:'lddm',name:'lddm',index :'lddm',key:true,hidden:true,width:'5%'},
                {label:'楼栋名称',name:'ldmc',index :'ldmc',width:'10%'},
                {label:'资产类型',name:'lxmc',index:'lxmc',width:'10%'},
                {label:'资产名称',name:'mc',index:'mc',width:'10%'},
                {label:'数量',name:'num',index:'num',width:'10%'}
            ],
            sortname: "lddm",
            sortorder: "asc"
        }
        var gridSetting2 = {
            caption : "楼层资产数",
            pager : "pager",
            url : "gygl_zcgl_zcfpgl.do?method=zcfpList&type=query",
            colList : [
                {label:'pk',name:'pk',index :'pk',key:true,hidden:true,width:'5%'},
                {label:'楼栋名称',name:'ldmc',index :'ldmc',width:'10%'},
                {label:'层号',name:'ch',index :'ch',width:'10%'},
                {label:'资产类型',name:'lxmc',index:'lxmc',width:'10%'},
                {label:'资产名称',name:'mc',index:'mc',width:'10%'},
                {label:'数量',name:'num',index:'num',width:'10%'}
            ],
            sortname: "lddm,ch",
            sortorder: "asc"
        }
        var gridSetting3 = {
            caption : "寝室资产数",
            pager : "pager",
            url : "gygl_zcgl_zcfpgl.do?method=zcfpList&type=query",
            colList : [
                {label:'pk',name:'pk',index :'pk',key:true,hidden:true,width:'5%'},
                {label:'楼栋名称',name:'ldmc',index :'ldmc',width:'10%'},
                {label:'层号',name:'ch',index :'ch',width:'10%'},
                {label:'寝室号',name:'qsh',index :'qsh',width:'10%'},
                {label:'资产类型',name:'lxmc',index:'lxmc',width:'10%'},
                {label:'资产名称',name:'mc',index:'mc',width:'10%'},
                {label:'数量',name:'num',index:'num',width:'10%'}
            ],
            sortname: "lddm,ch",
            sortorder: "asc"
        }
        jQuery(function(){
            var map = getSuperSearch();
            map["cxlx"] = "qs";
            gridSetting3["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting3);
        });
        function searchRs() {
            var map = getSuperSearch();
            map["cxlx"] = jQuery("#cxlx").val();
            jQuery("#dataTable").reloadGrid(map);
        }

        function add() {
            showDialog("分配", 700, 600, "gygl_zcgl_zcfpgl.do?method=fp");
        }
        function update() {
            var ids = jQuery("#dataTable").getSeletIds();
            if(ids.length != 1){
                showAlert("请选择一条记录！");
                return false;
            }
            showDialog("修改", 700, 600, "gygl_zcgl_zcfpgl.do?method=update&pk="+ids[0]);
        }
        function del(){
            var ids = jQuery("#dataTable").getSeletIds();
            if(ids.length == 0){
                showAlert("请至少选择一条记录！");
                return false;
            }
            var url = "gygl_zcgl_zcfpgl.do?method=del&ids="+ids.toString();
            showConfirmDivLayer("您确定要删除选择的记录吗？", {
                "okFun" : function() {
                    jQuery.post(url,{},function(data){
                        showAlertDivLayer(data["message"]);
                        jQuery("#dataTable").reloadGrid();
                    },'json');
                }
            });
        }
        function selectTab(obj,fpfs){
            jQuery("#cxlx").val(fpfs);
            jQuery(".ha").removeClass("ha");
            jQuery(obj).parent().addClass("ha");
            var map = getSuperSearch();
            map["cxlx"] = fpfs;
            if("ld" == fpfs){
                jQuery("#btn_ul").attr("style","display:none");
                gridSetting["params"] = map;
                jQuery("#dataTable").initGrid(gridSetting);
            } else if("lc" == fpfs){
                jQuery("#btn_ul").attr("style","display:none");
                gridSetting2["params"] = map;
                jQuery("#dataTable").initGrid(gridSetting2);
            } else if("qs" == fpfs){
                jQuery("#btn_ul").removeAttr("style");
                gridSetting3["params"] = map;
                jQuery("#dataTable").initGrid(gridSetting3);
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
<html:form action="/gyglnew_wmqsxsmd_12688">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <input type="hidden" value="qs" id="cxlx">
    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul id="btn_ul">
                <li><a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">增加</a></li>
                <li><a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">修改</a></li>
                <li><a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">删除</a></li>
            </ul>
        </div>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- 过滤条件 end-->
        <%--<div class="comp_title" id="comp_title">
            <ul style="width:90%">
                <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'qs');"><span>寝室资产数</span></a></li>
                <li><a href="javascript:void(0);" onclick="selectTab(this,'ld');"><span>楼栋资产数</span></a></li>
                <li><a href="javascript:void(0);" onclick="selectTab(this,'lc');"><span>楼层资产数</span></a></li>
            </ul>
        </div>--%>
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
