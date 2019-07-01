<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xyddk/js/dksq.js"></script>
		<script type="text/javascript">
		
			jQuery(function(){
				if('${xxdm}' == '10335'){
					var gridSetting = {
						caption:"助学贷款申请列表 ",
						pager:"pager",
						url:"zxdkXyddk.do?method=getDksqList",
						colList:[
						   {label:'key',name:'id', index: 'id',hidden:true,key:true},
						   {label:'学号',name:'xh', index: 'xh',width:'13%',formatter:function(cell,rowObject){
							   return "<a href=\"javascript:ckSqb('"+rowObject["id"]+"');\" class='name'>"+cell+"</a>";
						   }},
						   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
						   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
                            {label:'书院',name:'symc', index: 'sydm',width:'13%'},
						   {label:'行政班级',name:'bjmc', index: 'bjdm',width:'13%'},
                            {label:'专业班级',name:'zybjmc', index: 'zybj',width:'13%'},
						   {label:'贷款学年',name:'xn', index: 'xn',width:'15%'},
						   {label:'贷款总金额',name:'dkje', index: 'dkje',width:'13%'},
						   {label:'贷款年限',name:'xzf', index: 'xzf',width:'13%'},
						   {label:'申请时间',name:'sqsj',index:'sqsj',width:'13%'},
						   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
						   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},
						   {label:'审核状态',name:'shztmc', index: 'shzt',width:'5%'}
						],
						sortname:"sqsj",
						sortorder:"desc",
						radioselect:true
					};
				}else{
					var gridSetting = {
							caption:"助学贷款申请列表 ",
							pager:"pager",
							url:"zxdkXyddk.do?method=getDksqList",
							colList:[
							   {label:'key',name:'id', index: 'id',hidden:true,key:true},
							   {label:'学号',name:'xh', index: 'xh',width:'13%',formatter:function(cell,rowObject){
								   return "<a href=\"javascript:ckSqb('"+rowObject["id"]+"');\" class='name'>"+cell+"</a>";
							   }},
							   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
							   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
                                {label:'书院',name:'symc', index: 'sydm',width:'13%'},
                                {label:'行政班级',name:'bjmc', index: 'bjdm',width:'13%'},
                                {label:'专业班级',name:'zybjmc', index: 'zybj',width:'13%'},
							   {label:'贷款学年',name:'xn', index: 'xn',width:'15%'},
							   {label:'贷款总金额',name:'dkje', index: 'dkje',width:'13%'},
							   {label:'贷款年限',name:'dkqx', index: 'dkqx',width:'13%'},
							   {label:'申请时间',name:'sqsj',index:'sqsj',width:'13%'},
							   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
							   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},
							   {label:'审核状态',name:'shztmc', index: 'shzt',width:'5%'}
							],
							sortname:"sqsj",
							sortorder:"desc",
							radioselect:true
						};
				}
				

				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			
			function printSqb(){
				var url = "zxdkXyddk.do?method=printSqb";
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length <=0) {
					showAlertDivLayer("请选择一条记录！");
				} else {
					if('${xxdm}' == '10335' && rows[0]['shzt'] != '1'){
						showAlertDivLayer("请在审核通过之后打印！");
						return false;
					}
					var guid = jQuery("#dataTable").getSeletIds();
					var url = url + "&ids=" +guid;
					window.open(url);
				}
			}
			function jsdkxxcj(){
				var url = "zxdkXyddk.do?method=printJsxxcj";
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length <=0) {
					showAlertDivLayer("请选择一条记录！");
				} else {
					var guid = jQuery("#dataTable").getSeletIds();
					var url = url + "&id=" +guid;
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
	
		<html:form action="/zxdkXyddk" method="post" styleId="zxdkXyddkForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
							<logic:equal value="1" name="cssz" property="xydKg">
								<li>
									<a href="javascript:void(0);" class="btn_zj"
									   onclick="editSqb();return false;" 
									   title="点击该按钮，打开申请表填写页面。"
									>
									申请
									</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="xgSqb();return false;" class="btn_xg"
									   title="选中一条记录，点击该按钮可修改申请表。"
									>修改</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc"
										title="对于已填写申请表未提交的记录进行提交申请操作。"
									>提交</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="cancelSubmit();return false;" class="btn_sr"
										 title="对于已提交未审核的记录进行撤销操作。"
									>撤销</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="qxsq();return false;" class="btn_sc"
									   title="只能删除未审核或退回的记录，已经在审核的不能删除。"
									>删除</a>
								</li>
							</logic:equal>
							<li>
								<a href="javascript:void(0);" onclick="viewLcgz();return false;" 
								   title="选中一条记录，点击该按钮可以查看审核流程。"
								   class="btn_cs">流程跟踪</a>
							</li>
							
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
							
							<li><a href="javascript:void(0);" onclick="printSqb();" class="btn_dy">打印申请表</a></li>
							<logic:equal value="12688" name="xxdm">
								<li>
									<a href="#" class="btn_dc" onclick="jsdkxxcj();return false;">江苏省生源地信用助学贷款信息采集表</a>
								</li>
							</logic:equal>
							
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
				<span>助学贷款申请列表 </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
