<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/rwdjsh/js/rwdjsh.js"></script>
		<script type="text/javascript">
		var gridSetting = {
		
				            pager:"pager",
							url:"rwdjsh.do?method=getRwdjShList&type=query",
							colList:[
							   {label:'rwdjid',name:'rwdjid', index: 'rwdjid',key:true,hidden:true},
							   {label:'学号 ',name:'xh', index: 'xh',formatter:dcmcLink},
							   {label:'姓名',name:'xm', index: 'xm'},
							   {label:'性别',name:'xb', index: 'xb'},
							   {label:'年级',name:'nj', index: 'nj'},
							   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'},
							   {label:'专业',name:'zymc', index: 'zymc'},
							   {label:'班级',name:'bjmc', index: 'bjmc'},
							   {label:'入伍时间',name:'rwsj', index: 'rwsj'},
							   {label:'服役部队',name:'fybd', index: 'fybd'},
							   {label:'审核状态',name:'shztmc', index: 'shztmc'},
							   {label:'shzt',name:'shzt',index:'shzt',hidden:true},
							   {label:'splc',name:'splc',index:'splc',hidden:true},
							   {label:'审核Id',name:'shid', index: 'shid',hidden:true},
					            {label:'岗位Id',name:'gwid', index: 'gwid',hidden:true}
							],
						 	sortorder: "desc",
						 	params:{shzt:"dsh"},
						 	radioselect:false
			 	
		}

		var gridSetting2 = {
				    pager:"pager",
							url:"rwdjsh.do?method=getRwdjShList&type=query",
							colList:[
							   {label:'rwdjid',name:'rwdjid', index: 'rwdjid',key:true,hidden:true},
							   {label:'学号 ',name:'xh', index: 'xh',formatter:dcmcLink},
							   {label:'姓名',name:'xm', index: 'xm'},
							   {label:'性别',name:'xb', index: 'xb'},
							   {label:'年级',name:'nj', index: 'nj'},
							   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'},
							   {label:'专业',name:'zymc', index: 'zymc'},
							   {label:'班级',name:'bjmc', index: 'bjmc'},
							   {label:'入伍时间',name:'rwsj', index: 'rwsj'},
							   {label:'服役部队',name:'fybd', index: 'fybd'},
							   {label:'审核状态',name:'shztmc', index: 'shztmc'},
							   {label:'shzt',name:'shzt',index:'shzt',hidden:true},
							   {label:'splc',name:'splc',index:'splc',hidden:true},
							   {label:'审核Id',name:'shid', index: 'shid',hidden:true},
					           {label:'岗位Id',name:'gwid', index: 'gwid',hidden:true}
							],
						 	sortorder: "desc",
						 	params:{shzt:"ysh"},
						 	radioselect:true
		}
			
		jQuery(function(){
			var map = getSuperSearch();
			map["shzt"]="dsh";
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
		<html:form action="/gygl_xyzsshgl">
			<input type="hidden" name="shkg" id="shkg" value="${shkg}"/>
			<input type="hidden" id="shzt" value="dsh"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="khsh();return false;" 
							   title="选中您要审核的记录，点击该按钮可以打开审核页面。"
							   class="btn_sh">审核</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelSh();return false;" 
							   title="选中一条记录，点击该按钮您可以撤消失误的审核操作。"
							   class="btn_qxsh">撤消</a>
						</li>	
						</logic:equal>					
						<li><a href="javascript:void(0);" onclick="splcInfo();return false;" 
							   title="选中一条记录，点击该按钮可以查看审核流程。"
							   class="btn_cs">流程跟踪</a></li>	
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>待处理</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>已处理</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>入伍登记审核列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
