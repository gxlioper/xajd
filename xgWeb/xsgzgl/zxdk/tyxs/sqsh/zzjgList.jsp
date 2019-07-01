<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>		
		<script type="text/javascript" src="xsgzgl/zxdk/tyxs/sqsh/js/zzjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		
		<script type="text/javascript">
		
			jQuery(function(){
				var gridSetting = {
					caption:"资助结果列表 ",
					pager:"pager",
					url:"tyxs_zzjg.do?method=getZzjgList",
					colList:[
					   {label:'key',name:'id', index: 'id',hidden:true,key:true},
					   {label:'学号',name:'xh', index: 'xh',width:'13%',formatter:function(cell,rowObject){
						   return "<a href=\"javascript:ckZzjg('"+rowObject["id"]+"');\" class='name'>"+cell+"</a>";}},
					   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
					   {label:'学院',name:'xymc', index: 'xydm',width:'15%'},
                        {label:'书院',name:'symc', index: 'sydm',width:'13%'},
                        {label:'行政班级',name:'bjmc', index: 'bjmc',width:'13%'},
                        {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'13%'},
					   {label:'申请学年',name:'xn',index:'xn',width:'13%'},	
					   {label:'申请总金额',name:'sqxfzj',index:'sqxfzj',width:'13%'},				  				 	
					   {label:'申请时间',name:'sqsj',index:'sqsj',width:'13%'},
					   {label:'资助总金额',name:'zzzje',index:'zzzje',width:'13%'},
					   {label:'申请ID',name:'sqid',index:'sqid',width:'13%',hidden:true}
					  
					],
					sortname:"sqsj",
					sortorder:"desc",
					radioselect:false
				};
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
				
			});
			
			function importDkxx(){
				toImportDataNew("IMPORT_N9005_TYXSJGB");
				return false;
			}
			
			function printSqb(){
				var url = "tyxs_zzjg.do?method=printSqb";
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
	
		<html:form action="/tyxs_zzjg" method="post" styleId="TyxsZzjgForm">
			 <%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
						
							
								<li>
									<a href="javascript:void(0);" class="btn_zj"
									   onclick="addZzjg();return false;" 
									   title="点击该按钮，打开申请表填写页面。"
									>
									增加
									</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="editZzjg();return false;" class="btn_xg"
									   title="选中一条记录，点击该按钮可修改申请表。"
									>修改</a>
								</li>								
								<li>
									<a href="javascript:void(0);" onclick="delZzjg();return false;" class="btn_sc"
									   title="只能删除未提交的记录！"
									>删除</a>
								</li>
							<logic:equal value="10335" name="xxdm">
								<logic:equal value="zf01" name="userName">
									<li>
										<a href="javascript:void(0);"  onclick="exportConfig();return false;" class="btn_dc" >导出</a>
									</li>
								</logic:equal>
							</logic:equal>
							<logic:notEqual value="10335" name="xxdm">
								<li>
									<a href="javascript:void(0);"  onclick="exportConfig();return false;" class="btn_dc" >导出</a>
								</li>
							</logic:notEqual>
							
							<li><a href="#"  onclick="importDkxx();return false;" class="btn_dr">导入</a></li>							
							<li><a href="javascript:void(0);" onclick="printSqb();" class="btn_dy">下载登记表</a></li>						
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
				<span>审核结果列表 </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
	
</html>
