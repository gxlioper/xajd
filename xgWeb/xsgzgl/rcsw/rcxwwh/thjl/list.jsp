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
				url:"rcsw_thjl.do?method=getList",
				colList:[
				   {label:'id',name:'thid', index: 'thid',hidden:true,key:true},
				   {label:'学号',name:'xh', index: 'xh',formatter:function(v,r){
					   return "<a href='javascript:view(\""+r["thid"]+"\")' class='name'>"+v+"</a>";
				   }},
				   {label:'姓名',name:'xm', index: 'xm'},
				   {label:'年级',name:'nj', index: 'nj'},
				   {label:'学院',name:'xymc', index: 'xydm'},
				   {label:'班级',name:'bjmc', index: 'bjdm'},
				   {label:'谈话时间',name:'thsj', index: 'thsj'},
				   {label:'谈话老师',name:'jsxm', index: 'thjs'}
				],
				sortname: "thsj",
			 	sortorder: "desc"
			};

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			function update(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					alertInfo("请选择一条您要修改的记录！");
				} else {
					showDialog('修改',700,500,'rcsw_thjl.do?method=edit&thid='+rows[0]["thid"]);
				}
			}

			function del(){
				var ids = jQuery("#dataTable").getSeletIds();

				if (ids.length == 0){
					alertInfo("请选择您要删除的记录！");
				} else {
					showConfirmDivLayer("您确定要删除该记录吗？",{"okFun":function(){
						jQuery.post("rcsw_thjl.do?method=delete",{ids:ids.toString()},function(data){
							alertInfo(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}
			
			function add(){
				showDialog('增加谈话记录',700,500,'rcsw_thjl.do?method=add');
			}
			
			function view(id){
				showDialog('查看谈话记录',700,390,'rcsw_thjl.do?method=view&thid='+id);
			}
			
			function importData(){
				toImportData("rcsw_thjl");
				return false;
			}
			
			//导出
			function exportConfig(){
				var DCCLBH='rcsw_thjl.do';
				customExport(DCCLBH, exportData);
			}

			function exportData(){
				var DCCLBH='rcsw_thjl.do';
				setSearchTj();//设置高级查询条件
				
				var url = "rcsw_thjl.do?method=export&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
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
		<html:form action="/rcsw_thjl" method="post">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">
					<li><a href="javascript:void(0);" onclick="add()" class="btn_zj">增加</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>						
					<li><a href="javascript:void(0);" onclick="importData();" class="btn_dr">导入</a></li>
					</logic:equal>						
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
				<span> 谈话记录列表 </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
		</html:form>
	</body>
</html>
