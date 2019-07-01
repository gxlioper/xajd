<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/tqhk/js/hkjg.js"></script>
		<script type="text/javascript">
		
			jQuery(function(){
				var gridSetting = {
					pager:"pager",
					url:"zxdkHkjg.do?method=getHkjgList",
					colList:[
					   {label:'key',name:'id', index: 'id',hidden:true,key:true},
					   {label:'学号',name:'xh', index: 'xh',width:'13%',formatter:function(cell,rowObject){
						   return "<a href=\"javascript:ckHkjg('"+rowObject["id"]+"');\" class='name'>"+cell+"</a>";
					   }},
					   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
					   {label:'班级',name:'bjmc', index: 'bjdm',width:'13%'},
					   {label:'还款账号',name:'hkzh', index: 'hkzh',width:'13%'},
					   {label:'还款金额',name:'hkje', index: 'hkje',width:'13%'},
					   {label:'还款标记',name:'hkbj',index:'hkbj',width:'13%'},
					   {label:'申请时间',name:'sqsj',index:'sqsj',width:'13%'}
					],
					sortname:"sqsj",
					sortorder:"desc",
					radioselect:true
				};

				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function importTqhk(){
				toImportData("ZXDK_TQHK");
				return false;
			}
			
			function exportData(){
				var DCCLBH='zxdk_tqhk.do';
				setSearchTj();//设置高级查询条件
				
				var url = "zxdkHkjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			
			//导出
			function exportConfig(){
				var DCCLBH='zxdk_tqhk.do';
				customExport(DCCLBH, exportData);
			}
			
			function ckHkjg(id){
				showDialog("查看申请表",800,520,"zxdkHkjg.do?method=viewTqhk&id="+id);
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	
		<html:form action="/zxdkSyddk" method="post" styleId="zxdkSyddkForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
							<li>
								<a href="javascript:void(0);" class="btn_zj"
								   onclick="addTqhk();return false;" 
								>
								增加
								</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="editTqhk();return false;" class="btn_xg"
								>修改</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="delTqhk();return false;" class="btn_sc"
								>删除</a>
							</li>
							<li><a href="#" class="btn_dr" onclick="importTqhk();return false;">导入</a></li>
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
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
				<span>提前还款列表  </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
