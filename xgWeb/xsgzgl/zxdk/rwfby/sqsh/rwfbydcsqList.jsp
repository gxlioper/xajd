<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/rwfby/sqsh/js/rwfbydcsq.js"></script>
		<script type="text/javascript">
		
			jQuery(function(){
				var gridSetting = {
					pager:"pager",
					url:"rwfby_sqsh.do?method=getDcsqList",
					colList:[
					   {label:'key',name:'id', index: 'id',hidden:true,key:true},
					   {label:'学号',name:'xh', index: 'xh',width:'13%',formatter:function(cell,rowObject){
						   return "<a href=\"javascript:cksq('"+rowObject["id"]+"');\" class='name'>"+cell+"</a>";
					   }},
					   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
                        {label:'书院',name:'symc', index: 'symc'},
					   {label:'行政班级',name:'bjmc', index: 'bjdm'},
                        {label:'专业班级',name:'zybjmc', index: 'zybj'},
					   {label:'申请金额',name:'dcje', index: 'dcje'},
					   {label:'申请时间',name:'sqsj',index:'sqsj'},
					   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},
					   {label:'shzt',name:'shzt',index:'shzt',hidden:true},
					   {label:'审核状态',name:'shztmc',index:'shztmc'}
					],
					sortname:"sqsj",
					sortorder:"desc",
					radioselect:true
				};

				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function cksq(id){
				showDialog("查看申请表",800,480,"rwfby_sqsh.do?method=cksq&id="+id);
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	
		<html:form action="/rwfby_sqsh" method="post" styleId="rwfbysqshForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
							<logic:equal value="1" name="cssz" property="sqkg">
								<li>
									<a href="javascript:void(0);" class="btn_zj"
									   onclick="bcsq();return false;" 
									>
									增加
									</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="xgsq();return false;" class="btn_xg"
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
								<li><a href="#" class="btn_cs" onclick="viewLcgz();return false;">流程跟踪</a></li>
								<li>
									<a href="javascript:void(0);" onclick="qxsq();return false;" class="btn_sc"
									>删除</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="getword();return false;" class="btn_dy">登记表下载</a>
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
			<logic:notEqual name="xxdm" value="12688">	
				<span>贷款补偿申请列表  </span>
			</logic:notEqual>
			<logic:equal name="xxdm" value="12688">	
				<span>退役入学资助申请列表  </span>
			</logic:equal>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
