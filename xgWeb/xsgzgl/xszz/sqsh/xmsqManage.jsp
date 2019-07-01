<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/sqsh/js/sqsh.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"资助项目申请列表",
				pager:"pager",
				url:"xszz_sqsh.do?method=xmsqManage&type=query",
				colList:[
				   {label:'key',name:'guid', index: 'guid',key:true ,hidden:true},
				   {label:'学号',name:'xh', index: 'xh',width:'11%',formatter:xhLink},
				   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
				   {label:'性别',name:'xb', index: 'xb',width:'5%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'13%'},
				   {label:'shlc',name:'shlc', index: 'shlc',hidden:true},
                   {label:'书院',name:'symc', index: 'symc',width:'13%'},
				   {label:'行政班级',name:'bjmc', index: 'bjdm',width:'13%'},
                    {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'13%'},
				   {label:'申请学年',name:'xn', index: 'xn',width:'10%'},
				   {label:'申请学期',name:'xqmc', index: 'xq',width:'6%'},
				   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'9%'},
				   {label:'项目名称',name:'xmmc', index: 'xmdm',width:'11%'},
				   {label:'调整后项目',name:'tzhxmmc', index: 'tzhxmmc',width:'11%'},
				   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
				   {label:'审核状态',name:'shztmc', index: 'shzt',width:'6%'},
				   {label:'isopen',name:'isopen',index:'isopen',hidden:true}
				],
				sortname: "sqsj",
			 	sortorder: "desc"
			}
			
			jQuery(function(){
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

						
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xszz_sqsh">
			<input type="hidden" name="tableName" id="tableName" value="view_xg_xszz_new_zzxmsqb"/>
			<input type="hidden" id="SFBJPY_Y" value="${SFBJPY_Y }"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!-- 读写权 -->
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj"
							   onclick="xmsq();return false;" 
							   title="点击该按钮，打开申请表填写页面。"
							>申请</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="updateXmsq();return false;" class="btn_xg"
							   title="选中一条记录，点击该按钮可修改申请表。"
							>修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="xmsqDelete();return false;" class="btn_sc"
							   title="只能取消未审核的记录，已经在审核的不能取消。"
							>删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">提交</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancle();return false;" class="btn_sr">撤销</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="xmsqLcgz();return false;" 
							   title="选中一条记录，点击该按钮可以查看审核流程。"
							   class="btn_cs">流程跟踪</a>
						</li>							
			            <li>
			              <a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a>
			            </li>
				        
							<%--<li><a href="#" class="btn_dc" onclick="exportData();return false;">导出数据</a></li>--%>					
						<!--  
						<li><a href="javascript:void(0);" onclick="printXmsq('xszz_sqsh.do?method=printJsp');return false;" class="btn_dy">打印申请表</a></li>						
						-->
							<%--华东理工个性化 --%>
						<logic:equal value="13431" name="xxdm">
							<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">下载申请审批表</a></li>
						</logic:equal>
						<logic:notEqual value="13431" name="xxdm">
							<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">下载登记表</a></li>
						</logic:notEqual>
						
						<logic:equal name="xxdm" value="10530">	
							<li><a href="javascript:void(0);" onclick="printSqlct();return false;" class="btn_down">申请流程图</a></li>
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="exceptionDataResolve();return false;" class="btn_xg">提交异常数据处理</a></li>
					</ul>
					
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>资助项目申请列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
