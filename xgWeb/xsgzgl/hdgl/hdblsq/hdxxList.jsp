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
                caption:"活动列表",
                pager:"pager",
                url:"hdgl_hdblsq.do?method=getHdxxList&type=query",
                colList:[
                    {label:'活动id',name:'hdid', index: 'hdid',key:true,hidden:true},
                    {label:'活动名称',name:'hdmc', index: 'hdmc',width:'30%'},
                    {label:'活动时间',name:'hdsj', index: 'hdsj',width:'10%'},
                    {label:'活动性质',name:'hdxs', index: 'hdxs',width:'10%'},
                    {label:'活动类型',name:'hdlxmc', index: 'hdlxmc',width:'10%'},
                    {label:'活动地点',name:'hddd', index: 'hddd',width:'15%'},
                    {label:'主办方',name:'zbf', index: 'zbf',width:'15%'},
                    {label:'活动来源',name:'ly', index: 'ly',width:'10%'}
                ],
                sortname: "hdsj",
                sortorder: "desc"
            }
            gridSetting["params"]=getSuperSearch();
            jQuery("#dataTable").initGrid(gridSetting);
        });

        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        function select(){
            var rows = jQuery("#dataTable").getSeletRow();
            if (rows.length != 1) {
                showAlertDivLayer("请选择一条记录！");
                return false;
            }
            if(rows[0]["hdjxzt"] == "已下架" ){
                showAlertDivLayer("请选择有效活动！");
                return false;
            }
			var gotoPath = jQuery("#gotoPath").val();
			if (gotoPath.split("?").length > 1){
				gotoPath = gotoPath+"&hdid="+rows[0].hdid+"&ly=dt";
			} else {
				gotoPath = gotoPath+"?hdid="+rows[0].hdid+"&ly=dt";
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
            // var W = api.get('parentDialog');
//            var rowData = JSON.stringify();
            // W.selectCallBack(rows[0]);
            api.close();
        }
    </script>
</head>
<body>
	
<div class="tab_cur">
    <p class="location">
        <em>您的当前位置：</em><a>${title }</a>
    </p>
</div>
<input type="hidden" value="${gotoPath}" id="gotoPath"/>
<html:form action="/hdgl_hdgl_tj">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>
                <li><a href="#" class="btn_sz" onclick="select();return false;">选择</a></li>
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
