<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html>
<head>
    <title>绿色通道审核</title>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="xsgzgl/xszz/lstd/js/lstd.js"></script>
    <script type="text/javascript">
        var gridSetting = {};
//        var gridSetting2 = {};
        function initGrid(){
            gridSetting = {
                caption:"绿色通道申请列表",
                pager:"pager",
                url:"xszz_lstd.do?method=lstdshList&type=query",
                colList:[
                    {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
                    {label:'学号',name:'xh', index: 'xh',width:'13%'},
                    {label:'姓名',name:'xm', index: 'xm',width:'8%'},
                    {label:'性别',name:'xb', index: 'xb',width:'5%'},
                    {label:'学院',name:'xymc', index: 'xydm',width:'13%'},
                    {label:'书院',name:'symc', index: 'symc',width:'13%'},
                    {label:'行政班级',name:'bjmc', index: 'bjdm',width:'13%'},
                    {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'13%'},
//                    {label:'学年',name:'xn', index: 'xn',width:'10%'},
//                    {label:'学期',name:'xq', index: 'xq',width:'10%',hidden:true},
                    {label:'申请时间',name:'sqsj', index: 'sqsj',width:'9%'},
                    {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
                    {label:'审核状态',name:'shztmc', index: 'shzt',width:'6%'},
                    {label:'shlc',name:'shlc', index: 'shlc',hidden:true},
                    {label:'shid',name:'shid', index: 'shid',hidden:true},
                    {label:'gwid',name:'gwid', index: 'gwid',hidden:true}
                ],
                sortname: "sqsj",
                sortorder: "desc"
            };
//            gridSetting2 = {
//                caption:"绿色通道申请列表",
//                pager:"pager",
//                url:"xszz_lstd.do?method=lstdshList&type=query",
//                colList:[
//                    {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
//                    {label:'学号',name:'xh', index: 'xh',width:'13%'},
//                    {label:'姓名',name:'xm', index: 'xm',width:'8%'},
//                    {label:'性别',name:'xb', index: 'xb',width:'5%'},
//                    {label:'学院',name:'xymc', index: 'xydm',width:'13%'},
//                    {label:'书院',name:'symc', index: 'symc',width:'13%'},
//                    {label:'行政班级',name:'bjmc', index: 'bjdm',width:'13%'},
//                    {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'13%'},
////                    {label:'学年',name:'xn', index: 'xn',width:'10%'},
////                    {label:'学期',name:'xq', index: 'xq',width:'10%',hidden:true},
//                    {label:'申请时间',name:'sqsj', index: 'sqsj',width:'9%'},
//                    {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
//                    {label:'审核状态',name:'shztmc', index: 'shzt',width:'6%'},
//                    {label:'shlc',name:'shlc', index: 'shlc',hidden:true},
//                    {label:'shid',name:'shid', index: 'shid',hidden:true},
//                    {label:'gwid',name:'gwid', index: 'gwid',hidden:true},
//                ],
//                sortname: "sqsj",
//                sortorder: "desc"
//            };
        };
        function searchRs(){
            var map = getSuperSearch();
            var shzt = jQuery("#shzt").val();

            if (shzt != ""){
                map["shzt"] = shzt;
            }
            jQuery("#dataTable").reloadGrid(map);
        }
        jQuery(function(){
            initGrid();
            var map = getSuperSearch();
            map["shzt"]="dsh";
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        })
        //批量审核
        function savePlshx(shzt,shyj){
            var rows = jQuery("#dataTable").getSeletRow();
            var guid = new Array();
            var gwid = new Array();
            var xhs = new Array();
            var splc = new Array();

            jQuery.each(rows,function(i,row){
                guid.push(row["sqid"]);
                gwid.push(row["gwid"]);
                xhs.push(row["xh"]);
                splc.push(row["shlc"]);
            });

            jQuery.post(
                "xszz_lstd.do?method=lstdplsh&type=save",
                {
                    shzt:shzt,
                    id:guid,
                    gwids:gwid,
                    xhs:xhs,
                    shyj:shyj,
                    shlcs:splc
                },function(data){

                    showAlertDivLayer(data["message"],{},{"clkFun":function(){
                        jQuery("#dataTable").reloadGrid();
                    }});
                },
                'json'
            );
        }
        //导出
        function exportConfig(){
            var DCCLBH='xszz_lstd_sh.do';
            customExport(DCCLBH, exportData);
        }

        function exportData(){
            var DCCLBH='xszz_lstd_sh.do';
            setSearchTj();//设置高级查询条件
            var shzt = jQuery("#shzt").val();
            var url = "xszz_lstd.do?method=shExport&dcclbh=" + DCCLBH + "&shzt="+shzt;//dcclbh,导出功能编号
            url = addSuperSearchParams(url);//设置高级查询参数
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
    </script>
</head>
<body>
<html:form action="/xszz_lstd">
    <input type="hidden" value="${isopean}" id="isopean">
    <input type="hidden" value="dsh" id="shzt"/>
    <%@ include file="/comm/hiddenValue.jsp"%>

    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>
                    <%--<logic:equal name="writeAble" value="yes">--%>
                        <li id="li_sh">
                            <a href="javascript:void(0);" onclick="lstdsh();return false;"
                               title="选中您要审核的记录，点击该按钮可以打开审核页面。"
                               class="btn_sh">审核</a>
                        </li>
                        <li id="li_qx" style="display: none;">
                            <a href="javascript:void(0);" onclick="cancelSh();return false;"
                               title="选中一条记录，点击该按钮您可以撤消失误的审核操作。"
                               class="btn_qxsh">撤消</a>
                        </li>
                        <li>
                            <a href="javascript:void(0);" onclick="exportConfig();return false;"
                               class="btn_dc">导出</a>
                        </li>
                        <li>
                            <a href="javascript:void(0);" onclick="print();return false;"
                               class="btn_dy">打印</a>
                        </li
                    <%--</logic:equal>--%>

            </ul>
        </div>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- 过滤条件 end-->
        <div class="comp_title" id="comp_title">
            <ul style="width:90%">
                <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>待处理</span></a></li>
                <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>已处理</span></a></li>
            </ul>
        </div>
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>绿色通道审核列表&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
