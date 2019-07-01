<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				pager:"pager",
				url:"jddj_jdqk.do?method=getJdqkList",
				colList:[
				   {label:'id',name:'id', index: 'id',hidden:true,key:true},
				   {label:'学号',name:'xh', index: 'xh',formatter:function(v,r){
					   return "<a class='name' href='javascript:cksq(\""+r["id"]+"\")'>"+v+"</a>";
				   }},
				   {label:'姓名',name:'xm', index: 'xm'},
				   {label:'年级',name:'nj', index: 'nj'},
				   {label:'学院',name:'xymc', index: 'xydm'},
				   {label:'班级',name:'bjmc', index: 'bjdm'},
				   {label:'项目',name:'xmmc', index: 'xmmc'},
				   {label:'级别',name:'jbmc', index: 'jbmc'},
				   {label:'sjly',name:'sjly', index: 'sjly',hidden:true},
				   {label:'证书编号',name:'zsbh', index: 'zsbh'}
				],
				sortname: "xh",
			 	sortorder: "asc"
			};

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function cksq(id){
				showDialog('查看',600,330,'jddj_jdqk.do?method=jdqkView&id='+id);
			}

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			function update(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					alertInfo("请选择一条您要修改的记录！");
				} else {
					
					if (rows[0]["sjly"] == "1"){
						alertInfo("流程数据不能修改！");
						return false;
					}
					showDialog('修改',700,380,'jddj_jdqk.do?method=jdqkEdit&id='+rows[0]["id"]);
				}
			}

			function djjdDel(){
				var ids = jQuery("#dataTable").getSeletIds();

				if (ids.length == 0){
					alertInfo("请选择您要删除的记录！");
				} else {
					
					var rows = jQuery("#dataTable").getSeletRow();
					for ( var i = 0; i < ids.length; i++) {
						if (rows[i]['sjly'] == '1') {
							showAlertDivLayer("流程数据不能删除！");
							return false;
						}
					}
					
					showConfirmDivLayer("您确定要删除该记录吗？",{"okFun":function(){
						jQuery.post("jddj_jdqk.do?method=jdqkDel",{ids:ids.toString()},function(data){
							alertInfo(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}
			
			function addDjjd(){
				showDialog('增加等级鉴定情况',700,380,'jddj_jdqk.do?method=jdqkAdd');;
			}
			
			function importJdqk(){
				toImportData("xsxx_ntgm_djjd");
				return false;
			}
			
			//导出
			function exportConfig(){
				var DCCLBH='ntgm_jjdj_jdqk.do';
				customExport(DCCLBH, exportData);
			}

			function exportData(){
				var DCCLBH='ntgm_jjdj_jdqk.do';
				setSearchTj();//设置高级查询条件
				
				var url = "jddj_jdqk.do?method=export&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/jddj_jdqk" method="post">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="addDjjd()" class="btn_zj">增加</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
					<li><a href="javascript:void(0);" onclick="djjdDel();" class="btn_sc">删除</a></li>						
					<li><a href="javascript:void(0);" onclick="importJdqk();" class="btn_dr">导入</a></li>						
					<li><a href="javascript:void(0);" onclick="exportConfig();" class="btn_dc">导出</a></li>						
				</ul>
			</div>
			<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- 过滤条件 end-->
		</div>
	
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 等级鉴定情况列表 </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
		</html:form>
	</body>
</html>
