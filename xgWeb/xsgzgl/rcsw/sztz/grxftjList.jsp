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
				url:"sztz_grxftj.do?method=getList",
				colList:[
				   {label:'学号',name:'xh', index: 'xh',width:'15%',key:true,formatter:function(cell,rowObject){
					   return "<a href=\"javascript:zxsxxView('"+rowObject["xh"]+"');\" class='name'>"+cell+"</a>";
				   }},
				   {label:'姓名',name:'xm', index: 'xm',width:'15%'},
				   {label:'班级',name:'bjmc', index: 'bjmc',width:'20%'},
				   {label:'A(最低0.5)',name:'azf',index:'azf',formatter:pdxf_a},
				   {label:'B(最低0.5)',name:'bzf',index:'bzf',formatter:pdxf_b},
				   {label:'C(最低2)',name:'czf',index:'czf',formatter:pdxf_c},
				   {label:'D(最低0.5)',name:'dzf',index:'dzf',formatter:pdxf_d},
				   {label:'E(最低0.5)',name:'ezf',index:'ezf',formatter:pdxf_e},
				   {label:'总学分(不低于8)',name:'zxf',index:'zxf',formatter:pdxf_z},
				   {label:'是否合格',name:'zxf',index:'zxf',formatter:pdhg}
				],
				sortname:"xh",
				sortorder:"desc"
			};

			jQuery(function(){
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			function pdxf_a(cell,rowObject){
				if(Number(cell)<0.5){
					return '<font color="red">'+cell+'</font>'
				}else{
					return cell;
				}
			}

			function pdxf_b(cell,rowObject){
				if(Number(cell)<0.5){
					return '<font color="red">'+cell+'</font>'
				}else{
					return cell;
				}
			}

			function pdxf_c(cell,rowObject){
				if(Number(cell)<2){
					return '<font color="red">'+cell+'</font>'
				}else{
					return cell;
				}
			}

			function pdxf_d(cell,rowObject){
				if(Number(cell)<0.5){
					return '<font color="red">'+cell+'</font>'
				}else{
					return cell;
				}
			}

			function pdxf_e(cell,rowObject){
				if(Number(cell)<0.5){
					return '<font color="red">'+cell+'</font>'
				}else{
					return cell;
				}
			}

			function pdxf_z(cell,rowObject){
				if(Number(cell)<8){
					return '<font color="red">'+cell+'</font>'
				}else{
					return cell;
				}
			}

			function pdhg(cell,rowObject){
				if(Number(cell)<8){
					return '<font color="red">不合格</font>';
				}else{
					var azf = rowObject["azf"];
					var bzf = rowObject["bzf"];
					var czf = rowObject["czf"];
					var dzf = rowObject["dzf"];
					var ezf = rowObject["ezf"];
					if(Number(azf)<0.5 || Number(bzf)<0.5 || Number(czf)<2 || Number(dzf)<0.5 || Number(ezf)<0.5){
						return '<font color="red">不合格</font>';
					}else{
						return '合格';
					}					
				}
			}

			function zxsxxView(xh){				
				var url="xsxx_tygl.do?method=ckZxsxx";
				url+="&xh="+xh;
				var width=850;
				//showTopWin(url,850,640);
				showDialog('查看学生详细信息', 850, 500, url)
			}
			
			//导出
			function exportConfig(){
				var DCCLBH='rcsw_sztz_grxftj.do';
				customExport(DCCLBH, exportData);
			}

			function exportData(){
				var DCCLBH='rcsw_sztz_grxftj.do';
				setSearchTj();//设置高级查询条件
				
				var url = "sztz_grxftj.do?method=export&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
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
	
		<html:form action="/sztz_grxftj" method="post" styleId="sztzSzxfForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>						
							<li><a href="javascript:void(0);" onclick="exportConfig();" class="btn_dc">导出</a></li>						
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
				<span>素质拓展学分统计明细</span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
