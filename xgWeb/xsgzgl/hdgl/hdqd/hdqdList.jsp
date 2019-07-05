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
                caption:"",
                pager:"pager",
                url:"hdgl_hdgl_hdqd_wh.do?method=hdqdList&type=query",
                colList:[
                    {label:'id',name:'id', index: 'id',key:true,hidden:true },
                    {label:'学号',name:'xh', index: 'xh',width:'15%'},
                    {label:'姓名',name:'xm', index: 'xm',width:'10%'},
                    {label:'活动id',name:'hdid', index: 'hdid',hidden:true},
                    {label:'活动名称',name:'hdmc', index: 'hdmc',width:'8%'},
                    {label:'活动地点',name:'hddd', index: 'hddd',width:'8%'},
                    {label:'活动开始时间',name:'hdkssj', index: 'hdkssj',width:'8%'},
                    {label:'活动结束时间',name:'hdjssj', index: 'hdjssj',width:'8%'},
                    {label:'签到状态',name:'qdztmc', index: 'qdztmc',width:'8%',formatter:function(cell,rowObject){
						if(rowObject["qdsj"]!=null&&rowObject["qdztmc"]=="未签到"){
							return "签到无效";
						}
					   return rowObject["qdztmc"];
					}},
                    {label:'签到时间',name:'qdsj', index: 'qdsj',width:'8%'},
                    {label:'签退状态',name:'qtztmc', index: 'qtztmc',width:'8%',formatter:function(cell,rowObject){
						if(rowObject["qtsj"]!=null&&rowObject["qtztmc"]=="未签退"){
							return "签退无效";
						}
					   return rowObject["qtztmc"];
					}},
                    {label:'签退时间',name:'qtsj', index: 'qtsj',width:'8%'},
                    {label:'志愿时长',name:'zyxss', index: 'zyxss',width:'8%'}
                ]
            }
            gridSetting["params"]=getSuperSearch();
            jQuery("#dataTable").initGrid(gridSetting);
        });

        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        //导入
        function importConfig(){
            toImportDataNew("IMPORT_HDQDXX");
            return false;
        }


        var DCCLBH = "hdgl_hdgl_hdqd.do";//dcclbh,导出功能编号

        //自定义导出 功能
        function exportConfig() {
            //DCCLBH导出功能编号,执行导出函数
            customExport(DCCLBH, ExportData);
        }

        // 导出方法
        function ExportData() {
            setSearchTj();//设置高级查询条件
            var url = "hdgl_hdgl_hdqd_wh.do?method=export&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
            url = addSuperSearchParams(url);//设置高级查询参数
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
        function add(){
            showDialog("增加活动学生",800,520,"hdgl_hdgl_hdqd_wh.do?method=add");
        }
        function update(){
        	var rows = jQuery("#dataTable").getSeletRow();
        	if(rows.length!=1){
        		showAlertDivLayer("请选择一条您要修改的记录");
				return false;
        	}
		
        	showDialog("修改活动学生",800,500,"hdgl_hdgl_hdqd_wh.do?method=updateqdView&hdid="+rows[0].hdid+
			"&xh="+rows[0].xh + "&id=" + rows[0].id);
        	
        }
		function deleteQd(){
			var rows = jQuery("#dataTable").getSeletRow();
			var qdxxs = new Array();
			if(rows.length<1){
				showAlertDivLayer("请选择您要删除的记录");
				return false;
			}
			for(var i=0;i<rows.length;i++){
				qdxxs.push(rows[i].xh+"_"+rows[i].hdid);
			}
			showConfirmDivLayer("是否确定删除勾选的"+rows.length+"条记录？", {
					"okFun" : function() {
					var url = "hdgl_hdgl_hdqd_wh.do?method=deleteQd";
					jQuery.post(url, {
						qdxxlist : qdxxs.toString()
					}, function(data) {
						if (data["success"] == false) {
							showAlertDivLayer(data["message"]);
						} else {
							showAlertDivLayer(data["message"], {}, {
								"clkFun" : function(tag) {
									jQuery("#dataTable").reloadGrid();
								}
							});
						}
					}, 'json');
				
			}});
		}
    </script>
</head>
<body>
<div class="tab_cur">
    <p class="location">
        <em>您的当前位置：</em><a>${title }</a>
    </p>
</div>
<html:form action="/hdgl_hdgl_tj">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>
                <li><a href="#" class="btn_zj" onclick="add();return false;">增加</a></li>
                <li><a href="#" class="btn_xg" onclick="update();return false;">修改</a></li>
                <li><a href="#" class="btn_sc" onclick="deleteQd();return false;">删除</a></li>
                <li><a href="#" class="btn_dr" onclick="importConfig();return false;">导入</a></li>
                <li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
                <%--<li><a href="#" class="btn_dc" onclick="return false;">推送报名数据</a></li>--%>
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
