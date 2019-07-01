<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		<script type="text/javascript">	
		
		jQuery(function(){
			var gridSetting = {
					caption:"困难生取消记录查询",
					pager:"pager",
					url:"xszz_qxknsjlgl.do?method=qxKnsjlManage&type=query",
					colList:[
						{label:'key',name:'jgguid', index: 'jgguid',key:true ,hidden:true},												 						 
						{label:'学号',name:'xh', index: 'xh',width:'10%'},
						{label:'姓名 ',name:'xm', index: 'xm',width:'7%'},						
						{label:'学院',name:'xymc', index: 'xymc',width:'21%'},
						{label:'班级',name:'bjmc', index: 'bjmc',width:'21%'},
						{label:'取消档次 ',name:'dcmc', index: 'rddc',width:'9%'},
						{label:'操作人',name:'czrxm', index: 'czrxm',width:'21%'},
						{label:'操作时间',name:'czsj', index: 'czsj',width:'13%'}
					],
					sortname: "czsj",
				 	sortorder: "desc"
			};
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);			
		});

		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		function ckqx(){
							
			var rows = jQuery("#dataTable").getSeletRow();				
			if(rows.length == 0){}																																							  
			//var url = 'xszz_qxknszggl.do?method=qxknsrd&xh='+rows[0]["xh"];	
			var url = 'xszz_qxknszggl.do?method=qxknsrd';			    				 				
			var title = "取消认定";
			showDialog(title, 459,258, url);															
			
		}

		function viewQxKnszgjl() {
			
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1){
				showAlertDivLayer("请选择一条您要查看的记录！");
				
			} else {
				showDialog("查看困难生取消认定信息", 720, 520, "xszz_qxknsjlgl.do?method=viewQxKnszgjl&jgguid=" + rows[0]['jgguid']
						+ "&xh=" + rows[0]['xh']);
			}
			
		}

		//导出
		function exportConfig(){
			var DCCLBH='xszz_qxknsjl_cx.do';
			customExport(DCCLBH, exportData);
		}
		function exportData(){
			var DCCLBH='xszz_qxknsjl_cx.do';
			setSearchTj();//设置高级查询条件
			var url = "xszz_qxknsjlgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
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
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
			</p>
		</div>
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
					学生或老师提交的困难生认定，经过审核最终通过的申请结果会进入此菜单<br/>
					用户也可在此处直接维护困难生名单					
				</span>
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		<html:form action="/xszz_qxknsjlgl" >			
			<input type="hidden" name="tableName" id="tableName" value="xg_xszz_new_knsqxjl"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="viewQxKnszgjl();return false;" class="btn_ck">查看</a>
						</li>				
						
		                <logic:equal value="zf01" name="userName">
               				<li>
               					<a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a>
               				</li>
              			</logic:equal>
              			
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 取消困难生记录查询 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
