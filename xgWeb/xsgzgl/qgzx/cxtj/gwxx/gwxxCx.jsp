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
            caption:"岗位发布维护列表",
            pager:"pager",
            url:"qgzx_cxtj.do?method=gwxxCx&type=query",
            colList:[
                {label:'key',name:'gwdm', index: 'gwdm',key:true,hidden:true},
                {label:'学年',name:'xn', index: 'xn',width:'10%'},
                {label:'岗位名称',name:'gwmc', index: 'gwmc',width:'15%'},
                {label:'用人单位',name:'yrdwmc', index: 'yrdwmc',width:'15%'},
                {label:'工作性质',name:'gwxzdm', index: 'gwxzdm',width:'3%',formatter:function(cell,rowObject){
                    if(cell == '0'){
                        return "临时";
                    } else if(cell == '1'){
                        return "正式";
                    } else {
                        return cell;
                    }
                }},
                {label:'岗位类型',name:'gwlx', index: 'gwlx',width:'3%',formatter:function(cell,rowObject){
                    if(cell == '0'){
                        return "临时";
                    } else if(cell == '1'){
                        return "长期";
                    } else {
                        return cell;
                    }
                }},
                {label:'单位类别',name:'dwlb', index: 'dwlb',formatter:function(cell,rowObject){
                    if(cell == "01"){
                        return "校内单位";
                    }
                    return "校外企业";
                }},
                {label:'招聘人数',name:'xqrs', index: 'xqrs',width:'7%'},
                {label:'在岗人数',name:'zgrs', index: 'zgrs',width:'7%'},
                {label:'离职人数',name:'lzrs', index: 'lzrs',width:'7%'}
            ],
            sortname: "fbsj",
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

        function yrdwwhExportConfig() {
            //DCCLBH导出功能编号,执行导出函数
            customExport("qgzx_cxtj_gwxx.do", yrdwwhExportData);
        }

        // 导出方法
        function yrdwwhExportData() {
            //setSearchTj();//设置高级查询条件
            var url = "qgzx_cxtj_ajax.do?method=gwxxcxExportData&dcclbh="+"qgzx_cxtj_gwxx.do";//dcclbh,导出功能编号
            //url = addSuperSearchParams(url);//设置高级查询参数
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }

        function ck(){
            var ids = jQuery("#dataTable").getSeletIds();
            if (ids.length != 1){
                showAlertDivLayer("请选择一条流程跟踪记录！");
            } else {
                showDialog("查看明细",700,500,"qgzx_cxtj.do?method=gwxxCk&gwdm="+ids[0]);
            }
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
				<%--<logic:equal value="yes" name="writeAble">--%>
					<li><a href="#" onclick="ck();return false;" class="btn_ck">查看明细</a></li>
				<%--</logic:equal>--%>

				<li><a href="#" onclick="yrdwwhExportConfig();return false;" class="btn_dc">导出</a></li>
			</ul>
		</div>
		<!-- 过滤条件 -->
		<%@ include file="/comm/search/superSearchArea.jsp"%>
	</div>

</html:form>
<div class="formbox">
	<!--标题start-->
	<h3 class="datetitle_01">
		<span> 岗位信息列表 </span>
	</h3>

	<table id="dataTable" ></table>
	<div id="pager"></div>

</div>
</body>
</html>
