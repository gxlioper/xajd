<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xthd/xmsqjs/js/xmsqjs.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"申请列表 ",
				pager:"pager",
				url:"rcsw_txhd_xmsq.do?method=viewJssqList&type=query",
				colList:[
				   {label:'key',name:'sqid', index: 'sqid',hidden:true,key:true},
				   {label:'学号',name:'xh', index: 'xh',width:'13%',formatter:function(cell,rowObject){
					   return "<a href=\"javascript:ckSqb('"+rowObject["sqid"]+"');\" class='name'>"+cell+"</a>";
				   }},
				   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
				   {label:'性别',name:'xb', index: 'xb',width:'5%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
				   {label:'行政班级',name:'bjmc', index: 'bjdm',width:'13%'},
                    {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'13%'},
				   {label:'申请项目',name:'xmmc', index: 'xmdm',width:'13%',formatter:show},
				   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'13%'},
				   {label:'项目代码',name:'xmdm', index: 'xmdm',width:'13%',hidden:true},
				   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
				   {label:'splc',name:'splc', index: 'splc',hidden:true},
				   {label:'审核状态',name:'shztmc', index: 'shzt',width:'5%'},
				   {label:'审核流程NEW',name:'shlc', index: 'shlc',width:'5%',hidden:true}
				   
				],
				sortname:"sqsj",
				sortorder:"desc",
				radioselect:true
			};
			jQuery(function(){
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	
		<html:form action="/rcsw_txhd_xmsq" method="post" styleId="TxhdXmsqJsForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
								<li>
									<a href="javascript:void(0);" class="btn_zj" onclick="editSqb();return false;" 
									   title="点击该按钮，打开申请表填写页面。"
									>
									申请
									</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="updateXmsq();return false;" class="btn_xg"
									   title="选中一条记录，点击该按钮可修改申请表。"
									>修改</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="xmsqDelete();return false;" class="btn_sc"
									   title="只能删除未审核或退回的记录，已经在审核的不能删除。"
									>删除</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc"
										title="对于已填写申请表未提交的记录进行提交申请操作。"
									>提交</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="cancle();return false;" class="btn_sr"
										 title="对于已提交未审核的记录进行撤销操作。"
									>撤销</a>
								</li>
							<li>
								<a href="javascript:void(0);" onclick="xmsqLcgz();return false;" 
								   title="选中一条记录，点击该按钮可以查看审核流程。"
								   class="btn_cs">流程跟踪</a>
							</li>
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
				<span>申请列表 </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
