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
        var gridSetting = {
            caption:"",
            pager:"pager",
            url:"qgzx_cxtj.do?method=dwgzCx&type=query",
            colList:[
                {label:'用人单位',name:'yrdwmc', index: 'yrdwmc',width:'13%'},
                {label:'单位类别',name:'dwlb', index: 'dwlb',width:'7%',formatter:function(cell,row){
                    if(cell == "01"){
                        return "校内单位";
					} else {
                        return "校外企业";
					}
				}},
                {label:'研究生（个）',name:'yjsrs', index: 'yjsrs',width:'7%'},
                {label:'本科生（个）',name:'bksrs', index: 'bksrs',width:'7%'},
                {label:'年份',name:'nd', index: 'nd',width:'10%'},
                {label:'月份',name:'yf', index: 'yf',width:'10%'},
                {label:'总工时',name:'zgs', index: 'zgs',width:'10%'},
                {label:'合计',name:'ffje', index: 'ffje',width:'10%'}
            ],
            sortname: "",
            sortorder: "desc"
        };
        var gridSetting2 = {
            caption:"学生岗位列表",
            pager:"pager",
            url:"qgzx_cxtj.do?method=dwgzCx&type=query",
            colList:[
                {label:'用人单位',name:'yrdwmc', index: 'yrdwmc',width:'13%'},
                {label:'单位类别',name:'dwlb', index: 'dwlb',width:'7%',formatter:function(cell,row){
                    if(cell == "01"){
                        return "校内单位";
                    } else {
                        return "校外企业";
                    }
                }},
                {label:'研究生（个）',name:'yjsrs', index: 'yjsrs',width:'7%'},
                {label:'本科生（个）',name:'bksrs', index: 'bksrs',width:'7%'},
                {label:'年份',name:'nd', index: 'nd',width:'10%'},
                {label:'总工时',name:'zgs', index: 'zgs',width:'10%'},
                {label:'合计',name:'ffje', index: 'ffje',width:'10%'}
            ],
            sortname: "",
            sortorder: "desc"
        };
        //初始化
        jQuery(document).ready(function(){
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });

        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }

        function yrdwwhExportConfig(type) {
            //DCCLBH导出功能编号,执行导出函数
			if(type == "ygz"){
                customExport("qgzx_cxtj_dwgzCx", yrdwwhExportData);
			} else {
                customExport("qgzx_cxtj_dwgzCx_ngz", yrdwwhExportData2);
			}
        }

        // 导出方法
        function yrdwwhExportData() {
            //setSearchTj();//设置高级查询条件
            var url = "qgzx_cxtj.do?method=dwgzCxExportData&dcclbh="+"qgzx_cxtj_dwgzCx&shzt=ygz";//dcclbh,导出功能编号
            //url = addSuperSearchParams(url);//设置高级查询参数
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
        function yrdwwhExportData2() {
            //setSearchTj();//设置高级查询条件
            var url = "qgzx_cxtj.do?method=dwgzCxExportData&dcclbh=qgzx_cxtj_dwgzCx_ngz&shzt=ngz";//dcclbh,导出功能编号
            //url = addSuperSearchParams(url);//设置高级查询参数
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
        function selectTab(obj, shzt) {
            jQuery("#shzt").val(shzt);
            if (shzt == "ygz") {
                jQuery("#li_sh").css("display", "");
                jQuery("#li_qx").css("display", "none");
                var map = getSuperSearch();
                map["shzt"]="ygz";
                gridSetting["params"] = map;
                jQuery("#dataTable").initGrid(gridSetting);
            } else {
                jQuery("#li_sh").css("display", "none");
                jQuery("#li_qx").css("display", "");
                var map = getSuperSearch();
                map["shzt"]="ngz";
                gridSetting2["params"] = map;
                jQuery("#dataTable").initGrid(gridSetting2);
            }
            jQuery(".ha").removeClass("ha");
            jQuery(obj).parent().addClass("ha");
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
	<!-- 隐藏域 -->
	<div class="toolbox" id="dgncz">
		<!-- 按钮 -->
		<div class="buttonbox">
			<ul>
				<li id="li_sh"><a href="#" onclick="yrdwwhExportConfig('ygz');return false;" class="btn_dc">导出单位月工资</a></li>
				<li id="li_qx" style="display: none;">
					<a href="#" onclick="yrdwwhExportConfig('ngz');return false;" class="btn_dc">导出单位年工资</a>
				</li>
			</ul>
		</div>
		<!-- 过滤条件 -->
		<%@ include file="/comm/search/superSearchArea.jsp"%>
		<div class="comp_title" id="comp_title">
			<ul style="width:90%">
				<li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'ygz');"><span>单位月工资</span></a></li>
				<li><a href="javascript:void(0);" onclick="selectTab(this,'ngz');"><span>单位年工资</span></a></li>
			</ul>
		</div>
	</div>

</html:form>
<div class="main_box">
	<!--标题start-->
	<h3 class="datetitle_01">
		<span> 单位工资列表 </span>
	</h3>

	<table id="dataTable" ></table>
	<div id="pager"></div>

</div>
</body>
</html>
