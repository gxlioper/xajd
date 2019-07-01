<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		<script type="text/javascript">
			var gridSetting = {
				pager:"pager",
				url:"dkbc_bcjg.do?method=getBcjgList",
				colList:[
				   {label:'id',name:'id', index: 'id',hidden:true,key:true},
				   {label:'学号',name:'xh', index: 'xh',formatter:function(cell,rowObject){
					   return "<a href=\"javascript:ckjg('"+rowObject["id"]+"');\" class='name'>"+cell+"</a>";
				   }},
				   {label:'姓名',name:'xm', index: 'xm'},
				   {label:'年级',name:'nj', index: 'nj'},
				   {label:'学院',name:'xymc', index: 'xydm'},
                    {label:'书院',name:'symc', index: 'sydm'},
                    {label:'行政班级',name:'bjmc', index: 'bjdm'},
                    {label:'专业班级',name:'zybjmc', index: 'zybj'},
				   {label:'代偿类别',name:'dclbmc', index: 'dclbmc'},
				   {label:'代偿金额',name:'dcje', index: 'dcje'},
//				   {label:'学费金额',name:'xfje', index: 'xfje'},
//				   {label:'贷款本金',name:'dkbj', index: 'dkbj'},
				   {label:'sjly',name:'sjly', index: 'sjly',hidden:true},
				   {label:'申请时间',name:'sqsj',index:'sqsj',width:'13%'}
				],
				sortname: "sqsj",
			 	sortorder: "asc"
			};

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			
			function ckjg(id){
				showDialog('查看',700,500,'dkbc_bcjg.do?method=bcjgView&id='+id);
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
					
					showDialog('修改',700,500,'dkbc_bcjg.do?method=bcjgEdit&id='+rows[0]["id"]);
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
						jQuery.post("dkbc_bcjg.do?method=bcjgDel",{ids:ids.toString()},function(data){
							alertInfo(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}
			
			function addDjjd(){
				showDialog('增加贷款补偿',700,500,'dkbc_bcjg.do?method=bcjgAdd');;
			}
			
			function importJdqk(){
				toImportDataNew("IMPORT_ZXDK_BYDC");
				return false;
			}
			
			//导出
			function exportConfig(){
				var DCCLBH='zxdk_dkbc.do';
				customExport(DCCLBH, exportData);
			}

			function exportData(){
				var DCCLBH='zxdk_dkbc.do';
				setSearchTj();//设置高级查询条件
				
				var url = "dkbc_bcjg.do?method=export&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			
			function importBfje(){
				toImportDataNew("IMPORT_ZXDK_BYDC_BFQX");
				return false;
			}
			
			function printSqb(){
				var url = "dkbc_bcjg.do?method=printSqb";
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length <=0) {
					showAlertDivLayer("请选择一条记录！");
				} else {
					var guid = jQuery("#dataTable").getSeletIds();
					var url = url + "&ids=" +guid;
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
					<%--<li><a href="javascript:void(0);" onclick="importBfje();" class="btn_dr">导入拨付代偿金额</a></li>--%>
					</logic:equal>
					
					<logic:equal value="10335" name="xxdm">
						<logic:equal value="zf01" name="userName">						
							<li><a href="javascript:void(0);" onclick="exportConfig();" class="btn_dc">导出</a></li>	
						</logic:equal>
					</logic:equal>
					<logic:notEqual value="10335" name="xxdm">
								<li><a href="javascript:void(0);" onclick="exportConfig();" class="btn_dc">导出</a></li>
					</logic:notEqual>
					
					<li><a href="javascript:void(0);" onclick="printSqb();" class="btn_dy">打印申请表</a></li>						
				</ul>
			</div>
			<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- 过滤条件 end-->
		</div>
	
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 贷款补偿列表 </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
		</html:form>
	</body>
</html>
