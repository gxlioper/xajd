<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/cxcy/bzsbwh/bzsbwhsh/js/bzsbwhsh.js"></script>
    <script type="text/javascript">
        var gridSetting = {
            caption:"活动补录审核列表",
            pager:"pager",
            url:"cxcy_bzsbwhsh.do?method=getList&type=query",
            colList : [
                { label : 'sqid', name : 'sqid', index : 'sqid',key : true, hidden : true },
                { label : 'splc', name : 'splc', index : 'splc',hidden : true },
                { label : '学号', name : 'xh', index : 'xh', width : '10%',formatter:xhLink },
                { label : '姓名', name : 'xm', index : 'xm', width : '8%' },
                { label : '项目名称', name : 'xmmc', index : 'xmmc', width : '14%' },
                { label : '补助金额（元）', name : 'bzje', index : 'bzje', width : '9%' },
                { label : '学年', name : 'xn', index : 'xn', width : '9%' },
                { label : '学期', name : 'xqmc', index : 'xqmc', width : '7%' },
                { label : '大队名称', name : 'xymc', index : 'xymc', width : '12%' },
                { label : '填报人', name : 'tbrmc', index : 'tbrmc', width : '8%' },
                { label : '记录时间', name : 'sqsj', index : 'sqsj', width : '15%' },
                { label : '审核状态', name : 'shztmc', index : 'shztmc', width : '18%' },
                { label : '审核状态', name : 'shzt', index : 'shzt', hidden : true},
                {label:'审核Id',name:'shid', index: 'shid',hidden:true},
                {label:'岗位Id',name:'gwid', index: 'gwid',hidden:true}
            ],
            params:{shzt:"dsh"},
            radioselect:false
        };

        var gridSetting2 = {
            caption:"活动补录审核列表",
            pager:"pager",
            url:"cxcy_bzsbwhsh.do?method=getList&type=query",
            colList : [
                { label : 'sqid', name : 'sqid', index : 'sqid',key : true, hidden : true },
                { label : 'splc', name : 'splc', index : 'splc',hidden : true },
                { label : '学号', name : 'xh', index : 'xh', width : '10%',formatter:xhLink },
                { label : '姓名', name : 'xm', index : 'xm', width : '8%' },
                { label : '项目名称', name : 'xmmc', index : 'xmmc', width : '14%' },
                { label : '补助金额（元）', name : 'bzje', index : 'bzje', width : '9%' },
                { label : '学年', name : 'xn', index : 'xn', width : '9%' },
                { label : '学期', name : 'xqmc', index : 'xqmc', width : '7%' },
                { label : '大队名称', name : 'xymc', index : 'xymc', width : '12%' },
                { label : '填报人', name : 'tbrmc', index : 'tbrmc', width : '8%' },
                { label : '记录时间', name : 'sqsj', index : 'sqsj', width : '15%' },
                { label : '审核状态', name : 'shztmc', index : 'shztmc', width : '18%' },
                { label : '审核状态', name : 'shzt', index : 'shzt', hidden : true},
                {label:'审核Id',name:'shid', index: 'shid',hidden:true},
                {label:'岗位Id',name:'gwid', index: 'gwid',hidden:true}
            ],
            params:{shzt:"ysh"},
            radioselect:true
        };

        jQuery(function(){
            var map = getSuperSearch();
            map["shzt"]="dsh";
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });
        function searchRs(){
            var map = getSuperSearch();
            var shzt = jQuery("#shzt").val();
            if (shzt != ""){
                map["shzt"] = shzt;
            }
            jQuery("#dataTable").reloadGrid(map);
        }
        function selectTab(obj,shzt){
            jQuery("#shzt").val(shzt);
            if (shzt == "dsh"){
                jQuery("#li_sh").css("display","");
                jQuery("#li_qx").css("display","none");
                jQuery("#dataTable").initGrid(gridSetting);
            } else {
                jQuery("#li_sh").css("display","none");
                jQuery("#li_qx").css("display","");
                jQuery("#dataTable").initGrid(gridSetting2);
            }
            jQuery(".ha").removeClass("ha");
            jQuery(obj).parent().addClass("ha");
            searchRs();
        }
        function xhLink(cellValue, rowObject) {
            return "<a href='javascript:void(0);' class='name' onclick='view(\""
                + rowObject["sqid"]+"\");'>" + cellValue
                + "</a>";
        }
    </script>
</head>

<body>
    <div class="tab_cur">
        <p class="location">
            <em>您的当前位置：</em><a>${title }</a>
        </p>
    </div>
    <html:form action="/cxcy_bzsbwhsh">
        <input type="hidden" id="shzt" value="dsh"/>
        <%@ include file="/comm/hiddenValue.jsp"%>
        <div class="toolbox">
            <!-- 按钮 -->
            <div class="buttonbox">
                <ul>
                    <logic:equal name="writeAble" value="yes">
                        <li id="li_sh">
                            <a href="javascript:void(0);" onclick="sh();return false;"
                               title="选中您要审核的记录，点击该按钮可以打开审核页面。"
                               class="btn_sh">审核</a>
                        </li>
                        <li id="li_qx" style="display: none;">
                            <a href="javascript:void(0);" onclick="cancelSh();return false;"
                               title="选中一条记录，点击该按钮您可以撤消失误的审核操作。"
                               class="btn_qxsh">撤消</a>
                        </li>
                    </logic:equal>
                    <li>
                        <a href="javascript:void(0);" onclick="lcgz();return false;"
                           title="选中一条记录，点击该按钮可以查看审核流程。"
                           class="btn_cs">流程跟踪</a>
                    </li>
                    <li>
                        <a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a>
                    </li>
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
            <span>审核列表&nbsp;&nbsp; </span>
        </h3>
        <div class="con_overlfow">
            <table id="dataTable" ></table>
            <div id="pager"></div>
        </div>
    </div>
</body>
</html>
