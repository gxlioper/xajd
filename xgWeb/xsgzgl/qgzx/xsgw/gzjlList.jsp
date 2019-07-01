<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type='text/javascript' src="js/xgutil.js"></script>
    <script type='text/javascript' src='dwr/engine.js'></script>
    <script type='text/javascript' src='dwr/util.js'></script>
    <script type='text/javascript' src='dwr/interface/exportData.js'></script>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="xsgzgl/qgzx/xsgw/js/xsgwsh.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption:"学生工作记录列表",
                pager:"pager",
                url:"qgzx_xsgwsh.do?method=ckgzjl&type=query&xh="+jQuery("#xh").val(),
                colList:[
                    {label:'用人单位',name:'yrdwmc', index: 'yrdwmc',width:'12%'},
                    {label:'投递名称',name:'gwmc', index: 'gwmc',width:'10%'},
                    {label:'岗位代码',name:'gwdm', index: 'gwdm',width:'15%',hidden:true},
                    {label:'投递时间',name:'sqsj', index: 'sqsj',width:'15%'},
                    {label:'录用时间',name:'sgsj', index: 'sgsj',width:'15%'},
                    {label:'工作时长',name:'gzsc', index: 'gzsc',width:'10%'},
                    {label:'在岗状态',name:'zgzt', index: 'zgzt',width:'10%',formatter:function(value,row){
                        if(value == "zg"){
                            return "在岗";
                        } else {
                            return "已离职";
                        }
                    }},
                    {label:'离职时间',name:'tgsj', index: 'tgsj',width:'15%'}
                ],
                sortname: "sqsj",
                sortorder: "desc"
            };

            jQuery("#dataTable").initGrid(gridSetting);
        });
        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        function back(){
            window.location.href="qgzx_xsgwsh.do?method=zgxsList";
        }
        function ckGzsc(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length != 1){
                showAlertDivLayer("请选择一条记录！");
                return false;
            }
            var url = "qgzx_xsgwsh.do?method=ckgzsc&gwdm="+rows[0]["gwdm"]+"&xh="+jQuery("#xh").val();
            showDialog("查看学生工作时长",765,500,url);
        }
    </script>
</head>

<body>

<div class="tab_cur">
    <p class="location">
        <em>您的当前位置：</em><a>${title }</a>
    </p>
</div>
<html:form action="/qgzx_jfcjgl_cjff.do?method=gjcxCjff">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <input type="hidden" value="${xh}" id="xh">
    <div class="toolbox">
        <div class="buttonbox">
            <ul>
                    <li>
                        <a href="javascript:void(0);" id="btn_sh" onclick="ckGzsc();return false;" class="btn_ck">查看工作时长</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" id="btn_qxsh" onclick="back();return false;" class="btn_fh">返回</a>
                    </li>
            </ul>
        </div>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- 过滤条件 end-->
    </div>
</html:form>
<div class="main_box">
    <!--标题start-->
    <h3 class="datetitle_01">
        <span>学生工作记录列表</span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>

</div>
</body>
</html>
