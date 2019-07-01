<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		<script type="text/javascript">
		jQuery(function(){

			var gridSetting = {
				pager:"pager",
				url:"rwfby_dcjg.do?method=getDcjgList",
				colList:[
				   {label:'id',name:'id', index: 'id',hidden:true,key:true},
				   {label:'学号',name:'xh', index: 'xh',formatter:function(cell,rowObject){
					   return "<a href=\"javascript:ckjg('"+rowObject["id"]+"');\" class='name'>"+cell+"</a>";
				   }},
				   {label:'姓名',name:'xm', index: 'xm'},
				   {label:'年级',name:'nj', index: 'nj'},
				   {label:'学院',name:'xymc', index: 'xydm'},
                    {label:'书院',name:'symc', index: 'symc'},
                    {label:'行政班级',name:'bjmc', index: 'bjdm'},
                    {label:'专业班级',name:'zybjmc', index: 'zybj'},
				   {label:'代偿类别',name:'dclbmc', index: 'dclbmc'},
				   {label:'代偿金额',name:'dcje', index: 'dcje'},
//				   {label:'学费金额',name:'xfje', index: 'xfje'},
//				   {label:'贷款本金',name:'dkbj', index: 'dkbj'},
				   {label:'sjly',name:'sjly', index: 'sjly',hidden:true},
				   {label:'申请时间',name:'lrsj',index:'lrsj',width:'13%'}
				],
				sortname: "lrsj",
			 	sortorder: "asc"
			};
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			
			function ckjg(id){
				showDialog('查看',700,500,'rwfby_dcjg.do?method=dcjgView&id='+id);
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
					
					showDialog('修改',700,500,'rwfby_dcjg.do?method=dcjgEdit&id='+rows[0]["id"]);
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
						jQuery.post("rwfby_dcjg.do?method=dcjgDel",{ids:ids.toString()},function(data){
							alertInfo(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}
			
			function addDjjd(){
				showDialog('增加贷款补偿',700,500,'rwfby_dcjg.do?method=dcjgAdd');;
			}
			
			function importJdqk(){
				toImportDataNew("IMPORT_ZXDK_RWFBYDC");
				return false;
			}
			
			//导出
			function exportConfig(){
				var DCCLBH='zxdk_rwfbybc.do';
				customExport(DCCLBH, exportData);
			}

			function exportData(){
				var DCCLBH='zxdk_rwfbybc.do';
				setSearchTj();//设置高级查询条件	
				var url = "rwfby_dcjg.do?method=export&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			
			//浙江大学个性化导入（含发放次数信息）
			function importJdqkForZD(){
				var drmkdm = 'IMPORT_ZXDK_RWFBYDC';
				var url = "rwfby_dcjg.do?method=importForZD" + "&drmkdm=" + drmkdm;
				showDialog('导入',720,580,url,{close:function(){
					if (jQuery("#search_go")){
						jQuery("#search_go").click();
					}
				}});
				return false;
			}
			function getword(){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("请选择您要下载的记录！");
				 } else if (rows.length > 1){
					var ids = jQuery("#dataTable").getSeletIds();
					var url="rwfby_dcjg.do?method=getDjbZip&value="+ids;
					window.open(url);
				 } else {
					var url="rwfby_dcjg.do?method=getDjbWord&id="+rows[0]["id"];
					window.open(url);
			    }
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
					<logic:equal name="writeAble" value="yes">
					<li><a href="javascript:void(0);" onclick="addDjjd()" class="btn_zj">增加</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
					<li><a href="javascript:void(0);" onclick="djjdDel();" class="btn_sc">删除</a></li>	
						<li><a href="javascript:void(0);" onclick="importJdqk();" class="btn_dr">导入</a></li>
					</logic:equal>
					
					<logic:equal value="10335" name="xxdm">
						<logic:equal value="zf01" name="userName">
							<li>
								<a href="javascript:void(0);" onclick="exportConfig();" class="btn_dc">导出</a>
							</li>
						</logic:equal>
					</logic:equal>
					<logic:notEqual value="10335" name="xxdm">
						<li><a href="javascript:void(0);" onclick="exportConfig();" class="btn_dc">导出</a></li>
					</logic:notEqual>
					<li>
						<a href="javascript:void(0);" onclick="getword();return false;" class="btn_dy">登记表下载</a>
					</li>
				</ul>
			</div>
			<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- 过滤条件 end-->
		</div>
	
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<logic:notEqual name="xxdm" value="12688">	
					<span>贷款补偿列表  </span>
				</logic:notEqual>
				<logic:equal name="xxdm" value="12688">	
					<span>退役入学资助列表  </span>
				</logic:equal>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
		</html:form>
	</body>
</html>
