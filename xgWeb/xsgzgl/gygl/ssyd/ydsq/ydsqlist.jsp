<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script> 
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/ssyd/js/ydsq.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			jQuery(function(){
			     var gridSetting = {
							caption:"宿舍异动申请列表",
							pager:"pager",
							url:"ydsq.do?method=list&type=query",
							colList:[
							   {label:'宿舍异动id',name:'ssydsqid', index: 'ssydsqid',key:true,hidden:true},
							   {label:'审批流程',name:'splcid', index: 'splcid',hidden:true},
							   {label:'学号',name:'xh', index: 'xh',formatter:dcmcLink},
							   {label:'姓名',name:'xm', index: 'xm'},
							   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'},
							   {label:'班级',name:'bjmc', index: 'bjmc'},
							   {label:'性别',name:'xb', index: 'xb'},
							   {label:'寝室',name:'qsmc', index: 'qsmc'},
							   <logic:equal name="xxdm" value="13627">
							   {label:'辅导员',name:'fdyxm', index: 'fdyxm'},	
							   </logic:equal>
							   {label:'异动类型',name:'ydlxmc', index: 'ydlxmc'},
							   {label:'ssydlx',name:'ssydlx', index: 'ssydlx',hidden:true},
							   {label:'申请时间',name:'sqsj', index: 'sqsj'},
							   {label:'审核状态',name:'shztmc', index: 'shztmc'},
							   {label:'审核',name:'shzt', index: 'shzt',hidden:true}
							],
							sortname: "sqsj",
						 	sortorder: "desc"
							 	
						}			
			    gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			//点击序号跳转
			function dcmcLink(cellValue, rowObject) {
				var ssydsqid = rowObject["ssydsqid"];
				return "<a href='javascript:void(0);' onclick=\"ckxx('" + ssydsqid
						+ "')\" class='name'>" + cellValue + "</a>";
			}
			
		 	//请假状态为1(已经提交)的 不能操作
		 	//jQuery("#dataTable").unSelect("qjzt","1");
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	<html:form action="/ydsq?method=list&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		
        <input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<logic:equal value="yes" name="writeAble">
						<li>
							<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">申请</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">提交</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancle();return false;" class="btn_sr">撤销</a>
						</li>
					</logic:equal>
					<li><a href="javascript:void(0);" onclick="rcxwshLcinfo();return false;" 
						   title="选中一条记录，点击该按钮可以查看审核流程。"
						   class="btn_cs">流程跟踪</a></li>
					<logic:equal value="yes" name="writeAble">	   
					<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
					</logic:equal>
					<logic:equal name="xxdm" value="12865">
							<li>
								<a href="javascript:void(0);" onclick="printTstzd();return false;" class="btn_down">调宿通知单</a>
							</li>
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
				<span>宿舍异动申请列表</span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
