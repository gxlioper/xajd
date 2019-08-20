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
                multiselect:false,
                url:"xyfd_xyzyzxjl.do?method=selectFdjs&type=query",
                colList:[
                    { label : '登记号', name : 'djh', index : 'djh', width : '8%',key : true},
                    { label : '用户名', name : 'yhm', index : 'yhm', width : '10%', hidden : true},
                    { label : '姓名', name : 'xm', index : 'xm', width : '10%' },
                    { label : '辅导人员类型', name : 'fdjslx', index : 'fdjslx',width : '10%'},
                    {label:'操作',name:'', index: '',width:'10%',formatter:function(cell,rowObject){
                        return "<label class='btn_01' onclick=\"select('"+rowObject["djh"]+"','"+rowObject["xm"]+"','"+rowObject["fdjslx"]+"');\">选择</label>";
                    }}
                ],
                sortname: "djh",
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
        function select(djh,xm,fdjslx){
            var api = frameElement.api;
            var parent = api.get('parentDialog');
            parent.jQuery("#fdjs").val(djh);
            parent.jQuery("#fdjsxm").val(xm == 'null' ? '' : xm);
            parent.jQuery("#fdjslx").html(fdjslx);
            iFClose();
        }
    </script>
</head>

<body>
<html:form action="/xyfd_xyzyzxjl">
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
