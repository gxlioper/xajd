<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
		var DCCLBH="rcsw_cwsj.do";
		var gridSetting = {
				caption:"财务信息",
				pager:"pager",
				url:"rcsw_cwsj.do?method=getCwsjList&type=query",
				colList:[
				   {label:'学号',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
				   {label:'姓名',name:'xm', index: 'xm',width:'7%'},
				   {label:'学年',name:'xn', index: 'xn',width:'10%'},
				   {label:'学期',name:'xqmc', index: 'xq',width:'3%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
				   {label:'专业',name:'zymc', index: 'zydm',width:'15%'},
				   {label:'班级',name:'bjmc', index: 'bjdm',width:'20%'},
				   {label:'应缴',name:'zd2', index: 'zd2',width:'5%'},
				   {label:'实缴',name:'zd3', index: 'zd3',width:'5%'},
				   {label:'欠缴',name:'zd1', index: 'zd1',width:'8%'}
				],
				sortname: "xh",
			 	sortorder: "asc"
			}
		function xhLink(cellValue,rowObject){
			return "<a href='javascript:void(0);' class='name' onclick='zxsxxView(\""+rowObject["xh"]+"\");'>"+cellValue+"</a>";
		}
		function zxsxxView(xh){
			
			var pkValue=xh;
			var url="xsxx_tygl.do?method=ckZxsxx";
			url+="&xh="+pkValue;
			var width=850;
			showDialog('',850,640,url);
	}

			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();

				jQuery("#dataTable").reloadGrid(map);
			}
			function exportConfig(){
				customExport('rcsw_cwsj.do', exportData);
			}
			function exportData(){
				setSearchTj();//设置高级查询条件
				var url = "rcsw_cwsj.do?method=exportData&dcclbh="+DCCLBH;//dcclbh,导出功能编号
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
		<html:form action="/rcsw_cwsj">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
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
				<span> 学生财务数据列表 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
