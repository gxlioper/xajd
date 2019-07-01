<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		
		<script type="text/javascript">
		
			jQuery(function(){
				var gridSetting = {
					<logic:notEqual value="12688" name="xxdm" >
					caption:"学费资助申请列表 ",
					</logic:notEqual>
					<logic:equal value="12688" name="xxdm" >
					caption:"征兵补偿资助申请列表 ",
					</logic:equal>
					pager:"pager",
					url:"tyxs_zzsq.do?method=getZzsqList",
					colList:[
					   {label:'key',name:'id', index: 'id',hidden:true,key:true},
					   {label:'学号',name:'xh', index: 'xh',width:'13%',formatter:function(cell,rowObject){
						   return "<a href=\"javascript:ckSqbs('"+rowObject["id"]+"');\" class='name'>"+cell+"</a>";}},
					   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
                        {label:'书院',name:'symc', index: 'sydm',width:'13%'},
					   {label:'行政班级',name:'bjmc', index: 'bjmc',width:'13%'},
                        {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'13%'},
					   {label:'申请学年',name:'xn',index:'xn',width:'13%'},	
					    {label:'申请总金额',name:'sqxfzj',index:'sqxfzj',width:'13%'},					   				
					   {label:'申请时间',name:'sqsj',index:'sqsj',width:'13%'},					   
					   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
					   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},
					   {label:'审核状态',name:'shztmc', index: 'shztmc',width:'5%'},
					   {label:'审核Id',name:'shid', index: 'shid',hidden:true},
					{label:'岗位Id',name:'xtgwid', index: 'xtgwid',hidden:true}
					],
					sortname:"sqsj",
					sortorder:"desc",
					radioselect:false
				};
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
				
			});
			/**
			 * 查看申请表
			 * @param id
			 */
			function ckSqbs(id){
				showDialog("查看申请表",800,590,"tyxs_zzsq.do?method=ckZzsq&id="+id);
			}
			
			function printSqbs(){
				var url = "tyxs_zzsq.do?method=printSqb";
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
	
		<html:form action="/tyxs_zzsq" method="post" styleId="tyxsZzsqForm">
			 <%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="splc" styleId="splc" value=""/>
			<html:hidden property="xxdm" styleId="xxdm" value="${xxdm}"/>
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
						
							<logic:equal value="1" name="cssz" property="xfzzsqkg">
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
									<a href="javascript:void(0);" onclick="cancelsq();return false;" class="btn_sr"
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
						
							<li><a href="javascript:void(0);" onclick="printSqbs();" class="btn_dy">下载登记表</a></li>						
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
				<logic:notEqual value="12688" name="xxdm" >
					<span>资助申请列表 </span>
				</logic:notEqual>
				<logic:equal value="12688" name="xxdm" >
					<span>补偿申请列表 </span>
				</logic:equal>
				
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
	
	
	<script type="text/javascript" src="xsgzgl/zxdk/tyxs/sqsh/js/zzsq.js"></script>
</html>
