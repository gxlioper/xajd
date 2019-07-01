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
            caption : "",
            pager : "pager",
            url : "gygl_zsgl_cwgl.do?method=cwfpList&type=query",
            colList : [
                {label:'pk',name:'pk',index :'pk',key:true,hidden:true,width:'10%'},
                {label:'年级',name:'nj',index:'nj',width:'10%'},
                {label:'学院',name:'xymc',index:'xymc',width:'10%'},
                {label:'专业',name:'zymc',index:'zymc',width:'10%'},
                {label:'班级',name:'bjmc',index:'bjmc',width:'10%'},
                {label:'班级人数',name:'rs',index:'rs',width:'10%'},
                {label:'已分配床位数',name:'yfpcws',index:'yfpcws',width:'5%'},
                {label:'已分配寝室数',name:'yfpqss',index:'yfpqss',width:'5%'},
                {label:'已分配楼栋数',name:'yfplds',index:'yfplds',width:'5%'}
            ],
            sortname: "nj",
            sortorder: "asc"
        }

        var gridSetting2 = {
            caption : "",
            pager : "pager",
            url : "gygl_zsgl_cwgl.do?method=cwfpList&type=query",
            colList : [
                {label:'pk',name:'pk',index :'pk',key:true,hidden:true,width:'10%'},
                {label:'年级',name:'nj',index:'nj',width:'10%'},
                {label:'书院',name:'symc',index:'symc',width:'10%'},
                {label:'班级',name:'bjmc',index:'bjmc',width:'10%'},
                {label:'班级人数',name:'rs',index:'rs',width:'10%'},
                {label:'已分配床位数',name:'yfpcws',index:'yfpcws',width:'5%'},
                {label:'已分配寝室数',name:'yfpqss',index:'yfpqss',width:'5%'},
                {label:'已分配楼栋数',name:'yfplds',index:'yfplds',width:'5%'}
            ],
            sortname: "nj",
            sortorder: "asc"
        }
        jQuery(function(){
            var map = getSuperSearch();
            map["fpfs"] = "xy";
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });
        function searchRs() {
            var map = getSuperSearch();
            map["fpfs"] = jQuery("#fpfs").val();
            jQuery("#dataTable").reloadGrid(map);
        }
        function qsfp(){
            var ids = jQuery("#dataTable").getSeletIds();
            if(ids.length > 1){
                showAlert("只能选择一条记录！");
                return false;
            }
            debugger
            var url = "gygl_zsgl_cwgl.do?method=cwfp&pks="+ids.toString();
            var fpfs = jQuery("#fpfs").val();
            if("sy" == fpfs){
                url = "gygl_zsgl_cwgl.do?method=sycwfp&pks="+ids.toString();
            }
            window.open(url,'_self');
        }
        function selectTab(obj,fpfs){
            jQuery("#fpfs").val(fpfs);
            jQuery(".ha").removeClass("ha");
            jQuery(obj).parent().addClass("ha");
            var map = getSuperSearch();
            map["fpfs"] = fpfs;
            if("xy" == fpfs){
                gridSetting["params"] = map;
                jQuery("#dataTable").initGrid(gridSetting);
            } else {
                gridSetting2["params"] = map;
                jQuery("#dataTable").initGrid(gridSetting2);
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
    <input type="hidden" value="xy" id="fpfs"/>
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
        <div class="comp_title" id="comp_title">
            <ul style="width:90%">
                <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'xy');"><span>学院（研究生）</span></a></li>
                <li><a href="javascript:void(0);" onclick="selectTab(this,'sy');"><span>书院（本科生）</span></a></li>
            </ul>
        </div>
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
