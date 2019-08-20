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
                url:"xyfd_fdkcjg.do?method=fdkcjgList&type=query",
                colList:[
                    { label : 'jgid', name : 'jgid', index : 'jgid',key : true,hidden : true },
                    { label : '登记号', name : 'fdjs', index : 'fdjs', width : '8%'},
                    { label : '用户名', name : 'yhm', index : 'yhm', width : '10%',hidden:true},
                    { label : '辅导教师', name : 'xm', index : 'xm', width : '10%' },
                    { label : '课程名称', name : 'kcmc', index : 'kcmc', width : '15%' },
                    { label : '开课单位', name : 'kkdw', index : 'kkdw', width : '15%' },
                    { label : '辅导室', name : 'fdsmc', index : 'fdsmc', width : '15%' },
                    { label : '辅导室地点', name : 'fdsdd', index : 'fdsdd', width : '15%' },
                    { label : '数据来源', name : 'sjly', index : 'sjly', hidden : true},
                    { label : 'lrsj', name : 'lrsj', index : 'lrsj', hidden : true},
                    { label : '操作', name : '', index : '', formatter:czLink}
                ],
                sortname: "lrsj",
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
        function czLink(cellValue, rowObject){
            return "<label class='btn_01' onclick=\"select('"+rowObject["jgid"]+"');\">选择</label>";
        }
        function select(jgid){
            var gotoPath = jQuery("#gotoPath").val();
            if (gotoPath.split("?").length > 1){
                gotoPath = gotoPath+"&fdkc="+ jgid;
            } else {
                gotoPath = gotoPath+"?fdkc="+ jgid;
            }
            var api = frameElement.api;

            if (api){
                if (api.get('childDialog')){
                    api.reload(api.get('parentDialog') ,gotoPath);
                } else {
                    var W = api.opener;
                    W.location=gotoPath;
                }
            } else if (parent.window){
                parent.window.document.location=gotoPath;
            }
            api.close();

        }
    </script>
</head>

<body>
<html:form action="/xyfd_fqyy">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- 按钮 -->

        <!-- 过滤条件 -->
        <%@ include file="/xsgzgl/xyfd/jzppwh/fdyywh/superSearchAreaforFdyy.jsp"%>
        <!-- 过滤条件 end-->
    </div>
</html:form>
<input type="hidden" value="${gotoPath}" id="gotoPath"/>
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
