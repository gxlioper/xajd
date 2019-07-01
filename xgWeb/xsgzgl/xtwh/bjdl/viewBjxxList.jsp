<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"班级信息列表",
				pager:"pager",
				url:"xtwh_bjdl.do?method=viewBjxxList&type=query",
				colList:[
				   {label:'年级',name:'nj', index: 'nj',width:'10%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'20%'},
				   {label:'专业',name:'zymc', index: 'zydm',width:'20%'},
				   {label:'班级',name:'bjmc', index: 'bjdm',width:'20%'},
				   {label:'班级大类',name:'lbmc', index: 'lbdm',width:'20%'},
				   {label:'bjdm',name:'bjdm', index: 'bjdm',width:'20%',key:true,hidden:true}
				],
				sortname: "bjdm",
			 	sortorder: "asc"
			};

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			function szBjdl(){
				
				var rows = jQuery("#dataTable").getSeletRow();
				
				if (rows.length == 0){
					if(jQuery("#dataTable").getRowCount() == '0'){
							showAlertDivLayer("没有班级可设置，请重新查询！");
							return ;
						}

					//showAlertDivLayer("请选择您要设置的班级！");
					var url = "xtwh_bjdl.do?method=szBjdl&szType=szdl_all&path=xg_bjdl.do";
					var map = getSuperSearch();
					//高级查询
					url +="&searchTj=";
					url +=map["searchTj"];
					url +="&searchTjz=";
					url +=map["searchTjz"];
					url +="&mhcx_lx=";
					url +=map["mhcx_lx"];
					url +="&searchLx=";
					url +=map["searchLx"];

					//模糊查询
					url +="&input_mhcx=";
					url +=map["input_mhcx"];
					url +="&mhcx_lx=";
					url +=map["mhcx_lx"];
					
					showDialog("设置班级大类",500,300,url);
				} else {
					var ids = jQuery("#dataTable").getSeletIds();
					showDialog("设置班级大类",500,300,"xtwh_bjdl.do?method=szBjdl&bjdm="+ids);
				}
				
			}

			//取消班级大类
			function qxBjdl(){
				
				var rows = jQuery("#dataTable").getSeletRow();
				
				if(jQuery("#dataTable").getRowCount() == '0'){
					showAlertDivLayer("没有班级可撤销，请重新查询！");
					return ;
				}

				var ids = jQuery("#dataTable").getSeletIds();
				var map = getSuperSearch(); 
				var values = ids.toString();
				
				var show = "";
				if(rows.length == 0){
					show="您确定要撤销查询结果集当中的班级大类吗？"
				}else{
					show="您确定要撤销选择的班级大类吗？"
				}
				showConfirmDivLayer(show,{"okFun":function(){
					jQuery.post("xtwh_bjdl.do?method=delBjdl&values="+values,map,function(data){
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
				}});
				
			}
			
			// 导出方法
			var DCCLBH = "xg_bjdl.do";//dcclbh,导出功能编号
			function exportConfig() {
				customExport(DCCLBH, exportData);
			}
			function exportData() {
				setSearchTj();//设置高级查询条件
				var url = "xtwh_bjdl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
		</script>
	</head>

	<body>
	<html:form action="/xtwh_bjdl">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">				
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
			</p>
		</div>
		
		<!-- 提示信息 end-->
		<div id="div_help" class="prompt" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
					此处可对班级大类进行划分，主要作用于评奖与资助的条件设置，控制某些条件的启用范围
				</span>
			</p>
			<a class="close" title="隐藏"
			   onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<%@ include file="/comm/hiddenValue.jsp"%>
	
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" 
						   onclick="szBjdl();" 
						   class="btn_xg">设置班级大类</a></li>
					<li><a href="javascript:void(0);" 
						   onclick="qxBjdl();" 
						   class="btn_sc">撤销班级大类</a></li>
					<li><a href="#" class="btn_dc" onclick="exportConfig();return false;" >导出</a></li>	
				</ul>
			</div>
			<!-- 过滤条件 -->
			<%@ include file="/comm/search/superSearchArea.jsp"%>
		</div>
		
	
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 班级信息列表 </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>
			<input type="hidden" value="0" name="hiddenRowCount" id="hiddenRowCountID"/>
		</div>
		</html:form>
	</body>
</html>
