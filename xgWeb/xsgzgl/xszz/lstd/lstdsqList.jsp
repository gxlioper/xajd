<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html>
<head>
    <title>绿色通道申请</title>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="xsgzgl/xszz/lstd/js/lstd.js"></script>
    <script type="text/javascript">
        function initGrid(){
            var gridSetting = {
                caption:"绿色通道申请列表",
                pager:"pager",
                url:"xszz_lstd.do?method=lstdsqList&type=query",
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
                    {label:'shlc',name:'shlc', index: 'shlc',hidden:true}
                ],
                sortname: "sqsj",
                sortorder: "desc"
            };
            gridSetting["params"]=getSuperSearch();
            jQuery("#dataTable").initGrid(gridSetting);
        };
        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        jQuery(function(){
            initGrid();
        })
    </script>
</head>
<body>
<html:form action="/xszz_lstd">
    <%@ include file="/comm/hiddenValue.jsp"%>

    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>
                <logic:equal name="isOpean" value="1">
                    <li>
                        <a href="javascript:void(0);" class="btn_zj" onclick="lstdsq();return false;"  title="点击该按钮，打开申请表填写页面。">申请</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="lstdxg();return false;" class="btn_xg" title="选中一条记录，点击该按钮可修改申请表。">修改</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="lstdsqDelete();return false;" class="btn_sc" title="只能取消未审核的记录，已经在审核的不能取消。" >删除</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="submit();return false;" class="btn_shuc">提交</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">撤销</a>
                    </li>
                </logic:equal>
                <li>
                    <a href="javascript:void(0);" onclick="Lcinfo();return false;"
                       title="选中一条记录，点击该按钮可以查看审核流程。"
                       class="btn_cs">流程跟踪</a>
                </li>
            </ul>
        </div>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- 过滤条件 end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>绿色通道申请列表&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
