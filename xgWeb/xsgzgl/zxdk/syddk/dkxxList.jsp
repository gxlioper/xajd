<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/syddk/js/dkxx.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		
			jQuery(function(){
				var gridSetting = {
					caption:"生源地贷款列表 ",
					pager:"pager",
					url:"zxdkSyddk.do?method=getDkxxList",
					colList:[
					   {label:'key',name:'id', index: 'id',hidden:true,key:true},
					   {label:'学号',name:'xh', index: 'xh',width:'13%',formatter:function(cell,rowObject){
						   return "<a href=\"javascript:ckDkxx('"+rowObject["id"]+"');\" class='name'>"+cell+"</a>";
					   }},
					   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
                        {label:'书院',name:'symc', index: 'sydm',width:'13%'},
                        {label:'行政班级',name:'bjmc', index: 'bjdm',width:'13%'},
                        {label:'专业班级',name:'zybjmc', index: 'zybj',width:'13%'},
					   {label:'学年',name:'xn', index: 'xn',width:'13%'},
					   {label:'贷款总金额',name:'dkje', index: 'dkje',width:'13%'},
					   <logic:notEqual name="xxdm" value="10335">
					   {label:'贷款期限(月)',name:'dkqx', index: 'dkqx',width:'13%'},
					   {label:'贷款开始日期',name:'dkkssj',index:'dkkssj',width:'13%'},
					   </logic:notEqual>
					   {label:'sjly',name:'sjly', index: 'sjly',hidden:true}
					],
					sortname:"lrsj",
					sortorder:"desc"
				};

				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function importSydk(){
				toImportDataNew("ZXDK_SYDDK");
				return false;
			}
			
			function importXnxx(){
				toImportDataNew("ZXDK_SYDDK_XNXX");
				return false;
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
					<div class="buttonbox">
						<ul>
							<logic:equal name="writeAble" value="yes">	
								<li>
									<a href="javascript:void(0);" class="btn_zj"
									   onclick="addDkxx();return false;" 
									>
									增加
									</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="editDkxx();return false;" class="btn_xg"
									>修改</a>
								</li>
							<logic:notEqual value="stu" name="userType">
								<li>
									<a href="javascript:void(0);" onclick="delDkxx();return false;" class="btn_sc"
									>删除</a>
								</li>
								<li><a href="#" class="btn_dr" onclick="importSydk();return false;">导入贷款信息</a></li>
								<li><a href="#" class="btn_dr" onclick="importXnxx();return false;">导入贷款明细</a></li>
							</logic:notEqual>
							</logic:equal>
							
							<logic:equal value="10335" name="xxdm">
								<logic:equal value="zf01" name="userName">
									<li>
										<a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a>
									</li>
								</logic:equal>
							</logic:equal>
							<logic:notEqual value="10335" name="xxdm">
								<li>
									<a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a>
								</li>
							</logic:notEqual>
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
				<span>生源地贷款列表  </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
