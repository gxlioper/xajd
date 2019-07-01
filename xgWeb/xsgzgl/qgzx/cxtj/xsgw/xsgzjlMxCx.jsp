<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type='text/javascript' src="js/uicomm.js"></script>
	<script type='text/javascript' src="js/String.js"></script>
	<script type='text/javascript' src="js/xgutil.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script>
        //初始化
        jQuery(document).ready(function(){
            var gridSetting = {
                caption:"学生岗位列表",
                pager:"pager",
                url:"qgzx_cxtj.do?method=xsgzjlMxCx&type=query&xh="+jQuery("#xh").val(),
                colList:[
                    {label:'gwdm',name:'gwdm', index: 'gwdm',hidden:true},
                    {label:'单位名称',name:'yrdwmc', index: 'yrdwmc',width:'13%'},
                    {label:'投递岗位',name:'gwmc', index: 'gwmc',width:'7%'},
                    {label:'投递时间',name:'sqsj', index: 'sqsj',width:'10%'},
                    {label:'录用时间',name:'sgsj', index: 'sgsj',width:'10%'},
                    {label:'工作时长',name:'gzsc', index: 'gzsc',width:'7%'},
                    {label:'在岗状态',name:'zgzt', index: 'zgzt',width:'7%',formatter:function(cell,row){
                        if(cell == "zg"){
                            return "在岗";
                        } else {
                            return "已离职";
                        }
                    }},
                    {label:'离职时间',name:'tgsj', index: 'tgsj',width:'10%'}
                ],
                sortname: "sqsj",
                sortorder: "desc"
            };
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });

        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }

        function yrdwwhExportConfig() {
            //DCCLBH导出功能编号,执行导出函数
            customExport("qgzx_cxtj_xsgzjlMxCx", yrdwwhExportData);
        }

        // 导出方法
        function yrdwwhExportData() {
            //setSearchTj();//设置高级查询条件
            var url = "qgzx_cxtj.do?method=xsgzjlMxCxExportData&dcclbh=qgzx_cxtj_xsgzjlMxCx?method=xsgzjlMxCx&xh="+jQuery("#xh").val();//dcclbh,导出功能编号
            //url = addSuperSearchParams(url);//设置高级查询参数
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
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
        function back(){
            window.location.href = "qgzx_cxtj.do?method=xsgzjlCx"
		}
	</script>
</head>
<body>
<div class="tab_cur" >
	<p class="location">
		<em>您的当前位置:</em><a>${title }</a>
	</p>
	<p class="help">
		<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
	</p>
</div>

<html:form action="/qgzx_gwglnew" method="post">
	<!-- 隐藏域 -->
	<%@ include file="/comm/hiddenValue.jsp"%>
	<input type="hidden" value="${xh}" id="xh">
	<!-- 隐藏域 -->
	<div class="toolbox" id="dgncz">
		<!-- 按钮 -->
		<div class="buttonbox">
			<ul>
				<li><a href="#" onclick="ckGzsc();return false;" class="btn_ck">查看工作时长</a></li>
				<li><a href="#" onclick="yrdwwhExportConfig();return false;" class="btn_dc">导出</a></li>
				<li><a href="#" onclick="back();return false;" class="btn_fh">返回</a></li>
			</ul>
		</div>
		<!-- 过滤条件 -->
		<%@ include file="/comm/search/superSearchArea.jsp"%>
	</div>

</html:form>
<div class="formbox">
	<!--标题start-->
	<h3 class="datetitle_01">
		<span> 学生岗位信息列表 </span>
	</h3>

	<table id="dataTable" ></table>
	<div id="pager"></div>

</div>
</body>
</html>
