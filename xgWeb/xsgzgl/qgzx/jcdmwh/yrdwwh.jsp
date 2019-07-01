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
                caption:"用人单位维护列表",
                pager:"pager",
                url:"qgzx_jcdmwh_ajax.do?method=yrdwWh",
                colList:[
                    {label:'key',name:'id', index: 'id',key:true,hidden:true},
                    {label:'单位名称',name:'yrdwmc', index: 'yrdwmc',formatter:function(cell,rowObject){
                        return "<a href='javascript:void(0);' class='name' onclick='view(\""
                            + rowObject["id"] + "\");'>" + cell
                            + "</a>";
					}},
                    {label:'负责人',name:'xm', index: 'xm',width:'10%'},
                    {label:'单位类别',name:'dwlb', index: 'dwlb',formatter:function(cell,rowObject){
                        if(cell == "01"){
                            return "校内单位";
						}
						return "校外企业";
					}},
                    {label:'岗位数量',name:'gws', index: 'gws',width:'7%'},
                    {label:'工作人数',name:'gzrs', index: 'gzrs',width:'13%'}
                    /*{label:'启用状态',name:'qyzt', index: 'qyzt',width:'11%',formatter:function(cell,rowObject){
                        if(cell == "1"){
                            return "启用";
						}
                        return "停用";
					}}*/
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

		function yrdwwhExportConfig() {
			//DCCLBH导出功能编号,执行导出函数
			customExport("qgzx_jcdmwh_yrdwwh.do", yrdwwhExportData);
			}



		// 导出方法
		function yrdwwhExportData() {
			//setSearchTj();//设置高级查询条件
			var url = "qgzx_jcdmwh_ajax.do?method=yrdwwhExportData&dcclbh=" + "qgzx_jcdmwh_yrdwwh.do";//dcclbh,导出功能编号
			//url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}


		function zjyrdw(){
			var url="qgzx_jcdmwh_ajax.do?method=yrdwZj";
			showDialog("用人单位增加", 800, 600, url);
		}

		function xgyrdw(){
			var row = jQuery("#dataTable").getSeletRow();
		    if(row.length != 1){
                showAlertDivLayer("请选择一条您要修改的数据！");
                return;
			}
			var url="qgzx_jcdmwh_ajax.do?method=yrdwXg&id="+row[0]["id"];
			showDialog("用人单位修改", 800, 600, url);
		}
		function view(id){
            var url="qgzx_jcdmwh_ajax.do?method=yrdwCk&id="+id;
            showDialog("用人单位查看", 800, 400, url);
		}
		function mmcsh(){
            var row = jQuery("#dataTable").getSeletRow();
            if(row.length != 1){
                showAlertDivLayer("请选择一条数据！");
                return;
            }
            if(row[0]["dwlb"] == "01"){
                showAlertDivLayer("请选择校外企业！");
                return;
			}
            var url="qgzx_jcdmwh_ajax.do?method=mmcsh&id="+row[0]["id"];
            showDialog("用人单位查看", 400, 200, url);
		}
		function yrdwSc(){
            var row = jQuery("#dataTable").getSeletRow();
            if(row.length == 0){
                showAlertDivLayer("请选择您要删除的数据！");
                return;
            }
            var url="qgzx_jcdmwh_ajax.do?method=blsc&id="+row[0]["id"];
            jQuery.post(url,{},function(data){
                if (data["message"] == "删除成功！") {
                    showAlert(data["message"], {}, {
                        "clkFun" : function() {
                            jQuery("#dataTable").reloadGrid();
                        }
                    });
                } else {
                    showAlert(data["message"]);
                }
			},'json')
		}
		</script>
	</head>
	<body>
		<html:form action="/qgzx_jcdmwh" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
						<li><a href="#" onclick="zjyrdw();return false;" class="btn_zj">新增单位</a></li>
						<li><a href="#" onclick="xgyrdw();return false;" class="btn_xg">修改单位</a></li>
						<li><a href="#" onclick="yrdwSc();return false;" class="btn_sc">删除</a></li>
						<%--<li><a href="#" onclick="" class="btn_xg">工作名单</a></li>--%>
						<%--<li><a href="#" onclick="" class="btn_xg">状态设置</a></li>--%>
						<%--<li><a href="#" onclick="mmcsh();return false;" class="btn_csh">密码初始化</a></li>--%>
						</logic:equal>

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
				<span> 评奖项目汇总列表 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
