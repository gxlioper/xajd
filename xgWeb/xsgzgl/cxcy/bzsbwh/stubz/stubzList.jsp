<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>

    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption : "活动补录列表",
                pager : "pager",
                url : "cxcy_stubz.do?method=getList&type=query",
                colList : [
                    { label : 'id', name : 'id', index : 'id',key : true, hidden : true },
                    { label : 'splc', name : 'splc', index : 'splc',hidden : true },
                    { label : '项目名称', name : 'xmmc', index : 'xmmc', width : '14%' },
                    { label : '补助金额（元）', name : 'bzje', index : 'bzje', width : '9%' },
                    { label : '学年', name : 'xn', index : 'xn', width : '9%' },
                    { label : '学期', name : 'xqmc', index : 'xqmc', width : '7%' },
                    { label : '大队名称', name : 'xymc', index : 'xymc', width : '12%' },
                    { label : '填报人', name : 'tbrmc', index : 'tbrmc', width : '8%' },
                    { label : '记录时间', name : 'tbsj', index : 'tbsj', width : '15%' },
                    { label : '数据来源', name : 'sjly', index : 'sjly', hidden : true},
                    { label : '审核状态', name : 'shztmc', index : 'shztmc', width : '8%' },
                    { label : '审核状态', name : 'shzt', index : 'shzt', hidden : true}

                ]
            };
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });
        function searchRs() {
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        function view(jgid) {
            var rows = jQuery("#dataTable").getSeletRow();
            if (rows.length != 1) {
                showAlertDivLayer("请选择一条您要查看的记录！");
            }else {
                var sjly = rows[0]["sjly"]
                if("1" == sjly){
                    showDialog("创新创业补助申报查看", 700, 450, "cxcy_bzsbwhjg.do?method=bzsbwhjgView&jgid="+rows[0]["id"]);
                }else{
                    showDialog("创新创业补助申报查看", 700, 450, "cxcy_bzsbwhsq.do?method=bzsbwhsqView&sqid="+rows[0]["id"]);
                }
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
<html:form action="/cxcy_stubz">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>
                <li>
                    <a href="javascript:void(0);" onclick="view();return false;" class="btn_ck" >查看</a>
                </li>
                <%--<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>--%>

            </ul>
        </div>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- 过滤条件 end-->
    </div>
</html:form>
<div class="main_box">
    <h3 class=datetitle_01>
        <span>我的创新创业补助列表&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
