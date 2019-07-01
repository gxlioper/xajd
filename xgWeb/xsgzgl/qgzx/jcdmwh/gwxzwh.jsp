<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script>
            var gridSetting = {
                caption:"岗位类别维护列表",
                pager:"pager",
                url:"qgzx_jcdmwh_ajax.do?method=gwxzWh",
                colList:[
                    {label:'岗位类别代码',name:'gwxzdm', index:'gwxzdm',width:'8%',key:true},
                    {label:'岗位性质名称',name:'gwxzmc', index: 'gwxzmc',width:'13%'},
                    {label:'薪酬上限',name:'gwxcsx', index: 'gwxcsx',width:'10%'},
                    {label:'工时上限',name:'gssx', index: 'gssx',width:'7%'},
                    {label:'时薪',name:'sx', index: 'sx',width:'7%'},
                    {label:'说明',name:'label', index: 'label',width:'20%'}
                ],
                sortname: "gwxzdm",
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
		
		function gwxzwhExportConfig() {
			//DCCLBH导出功能编号,执行导出函数 
			customExport("qgzx_jcdmwh_gwxzwh.do", gwxzwhExportData);
			}
			
		
			
		// 导出方法
		function gwxzwhExportData() {
			//setSearchTj();//设置高级查询条件
			var url = "qgzx_jcdmwh_ajax.do?method=gwxzwhExportData&dcclbh=" + "qgzx_jcdmwh_gwxzwh.do";//dcclbh,导出功能编号
			//url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}


		function gwxzDiv1(type,topMsg){
			 var url = "";
            var doType = type;
			if(type=="update"){
                var row = jQuery("#dataTable").getSeletRow();
                if(row.length != 1){
                    showAlertDivLayer("请选择一条您要修改的数据！");
                    return;
                }
				url="qgzx_jcdmwh_ajax.do?method=gwxzZjxg&doType="+doType+"&gwxzdm=" + row[0]["gwxzdm"];

			}
			
				var title = topMsg;
				if(type=="add"){
					url="qgzx_jcdmwh_ajax.do?method=gwxzZjxg&doType="+doType;
				}
				showDialog(title, 500, 270, url);
		}
		function gwxzSc(){
            var row = jQuery("#dataTable").getSeletRow();
            if(row.length == 0){
                showAlertDivLayer("请选择您要啥删除的数据！");
                return;
            }
            var ids = jQuery("#dataTable").getSeletIds();
            var url = "qgzx_jcdmwh_ajax.do?method=gwxzSc";
            showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
                jQuery.post(url,{gwxzdm:ids.toString()},function(data){
                    showAlert(data["message"],{},{"clkFun" :function(){
                        jQuery("#dataTable").reloadGrid();
					}});
                },'json');
			}});

		}
		</script>
	</head>
	<body>
		<div class="tab_cur" >
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/qgzx_jcdmwh" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
						<li><a href="#" onclick="gwxzDiv1('add','增加岗位类别');return false;" class="btn_zj">增加</a></li>
						<li><a href="#" onclick="gwxzDiv1('update','修改岗位类别');return false;" class="btn_xg">修改</a></li>
						<li><a href="#" onclick="gwxzSc();return false;" class="btn_sc">删除</a></li>
						</logic:equal>
						
						<li><a href="#" onclick="gwxzwhExportConfig();return false;" class="btn_dc">导出</a></li>
					</ul>
				</div>
				<div style="display: none;">
					<!-- 过滤条件 -->
					<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>申请列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
