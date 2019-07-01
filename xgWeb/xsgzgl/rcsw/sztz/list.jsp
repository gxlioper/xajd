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
				url:"ahgf_sztz.do?method=getList",
				colList:[
				   {label:'key',name:'id', index: 'id',hidden:true,key:true},
				   {label:'学号',name:'xh', index: 'xh',width:'13%',formatter:function(cell,rowObject){
					   return "<a href=\"javascript:cksq('"+rowObject["id"]+"');\" class='name'>"+cell+"</a>";
				   }},
				   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
				   {label:'学年',name:'xn', index: 'xn'},
				   {label:'学期',name:'xqmc',index:'xqmc'},
				   {label:'素质拓展项目',name:'tzxm',index:'tzxm'},
				   {label:'级别',name:'tzjb',index:'tzjb'},
				   {label:'名次',name:'mc',index:'mc'},
				   {label:'确认学分',name:'xf',index:'xf'},
				   {label:'审核人',name:'shr',index:'shr'}
				],
				sortname:"lrsj",
				sortorder:"desc"
			};

			jQuery(function(){
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function cksq(id){
				showDialog('查看',700,400,'ahgf_sztz.do?method=view&id='+id);
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
					showDialog('修改学分明细',700,450,'ahgf_sztz.do?method=edit&id='+rows[0]["id"]);
				}
			}

			function del(){
				var ids = jQuery("#dataTable").getSeletIds();

				if (ids.length == 0){
					alertInfo("请选择您要删除的记录！");
				} else {
					var rows = jQuery("#dataTable").getSeletRow();
					showConfirmDivLayer("您确定要删除该记录吗？",{"okFun":function(){
						jQuery.post("ahgf_sztz.do?method=delete",{ids:ids.toString()},function(data){
							alertInfo(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}
			
			function add(){
				showDialog('增加学分明细',700,450,'ahgf_sztz.do?method=add');;
			}
			
			function importJdqk(){
				toImportData("ybgzz_jgwh");
				return false;
			}
			
			//导出
			function exportConfig(){
				var DCCLBH='nhgf_sztz.do';
				customExport(DCCLBH, exportData);
			}

			function exportData(){
				var DCCLBH='nhgf_sztz.do';
				setSearchTj();//设置高级查询条件
				
				var url = "ahgf_sztz.do?method=export&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			
			function importXfmx(){
				toImportData("nhgf_sztz");
				return false;
			}
			
			function xfhz(){
				document.location.href="ahgf_sztz.do?method=xfhz";
			}
			
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	
		<html:form action="/ahgf_sztz" method="post" styleId="zxdkSyddkForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
							<li><a href="javascript:void(0);" onclick="add()" class="btn_zj">增加</a></li>
							<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
							<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>						
							<li><a href="javascript:void(0);" onclick="importXfmx();" class="btn_dr">导入</a></li>						
							<li><a href="javascript:void(0);" onclick="exportConfig();" class="btn_dc">导出</a></li>						
							<li><a href="javascript:void(0);" onclick="xfhz();" class="btn_cx">学分汇总</a></li>						
						</ul>
					</div>
				</logic:equal>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>素质拓展学分明细</span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
