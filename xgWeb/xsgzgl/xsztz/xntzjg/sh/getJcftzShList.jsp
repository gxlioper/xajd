<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/xntzjg/sh/js/jcftzsh.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"校内拓展结果审核列表",
				pager:"pager",
				url:"jcftz_sh.do?method=getJcftzShList&type=query",
				colList : [
							{ label : 'rdsqid', name : 'rdsqid', index : 'rdsqid',key : true, hidden : true },
							{ label : 'jgid', name : 'jgid', index : 'jgid',key : true, hidden : true },
							{ label : 'rdsplc', name : 'rdsplc', index : 'rdsplc',hidden : true },
							{ label : 'xmdm', name : 'xmdm', index : 'xmdm',hidden : true },
							{ label : '学年', name : 'xn', index : 'xn', width : '10%'},
							{ label : '学期', name : 'xqmc', index : 'xqmc' ,width : '7%' },
							{ label : '学期', name : 'xq', index : 'xq',hidden : true },
							{ label : '项目名称', name : 'xmmc', index : 'xmmc', width : '15%',formatter : xmmcLink },
							{ label : '项目类型',name : 'csmsmc',index : 'csmsmc',width : '5%'},
							{ label : '项目级别', name : 'xmjbmc', index : 'xmjbmc', width : '5%' },
							{ label : '所属科目', name : 'sskmmc', index : 'sskmmc', width : '15%' },
							{ label : '项目开始时间', name : 'xmkssj', index : 'xmkssj', width : '15%' },
							{ label : '团/人数', name : 'ybjrs', index : 'ybjrs', width : '10%',formatter : rsLink },
							{ label : '审核状态', name : 'shztmc', index : 'shztmc', width : '18%' },
							{ label : '审核状态', name : 'shzt', index : 'shzt', hidden : true},
							{label : '项目类型',name : 'csms',index : 'csms',hidden:true},
							{label:'审核Id',name:'shid', index: 'shid',hidden:true},
							{label:'岗位Id',name:'gwid', index: 'gwid',hidden:true}
							],
				params:{shzt:"dsh"},
				sortname: "xmkssj",
			 	sortorder: "desc",
			 	radioselect:false
		}

		var gridSetting2 = {
				caption:"校内拓展结果审核列表",
				pager:"pager",
				url:"jcftz_sh.do?method=getJcftzShList&type=query",
				colList : [
							{ label : 'rdsqid', name : 'rdsqid', index : 'rdsqid',key : true, hidden : true },
							{ label : 'jgid', name : 'jgid', index : 'jgid',key : true, hidden : true },
							{ label : 'rdsplc', name : 'rdsplc', index : 'rdsplc',hidden : true },
							{ label : 'xmdm', name : 'xmdm', index : 'xmdm',hidden : true },
							{ label : '学年', name : 'xn', index : 'xn', width : '10%'},
							{ label : '学期', name : 'xqmc', index : 'xqmc' ,width : '7%' },
							{ label : '学期', name : 'xq', index : 'xq',hidden : true },
							{ label : '项目名称', name : 'xmmc', index : 'xmmc', width : '15%',formatter : xmmcLink },
							{ label : '项目类型',name : 'csmsmc',index : 'csmsmc',width : '5%'},
							{label : '项目类型',name : 'csms',index : 'csms',hidden:true},
							{ label : '项目级别', name : 'xmjbmc', index : 'xmjbmc', width : '5%' },
							{ label : '所属科目', name : 'sskmmc', index : 'sskmmc', width : '15%' },
							{ label : '项目开始时间', name : 'xmkssj', index : 'xmkssj', width : '15%' },
							{ label : '团/人数', name : 'ybjrs', index : 'ybjrs', width : '10%',formatter : rsLink },
							{ label : '审核状态', name : 'shztmc', index : 'shztmc', width : '18%' },
							{ label : '审核状态', name : 'shzt', index : 'shzt', hidden : true},
							{label:'审核Id',name:'shid', index: 'shid',hidden:true},
							{label:'岗位Id',name:'gwid', index: 'gwid',hidden:true}
							],
				params:{shzt:"ysh"},
				sortname: "xmkssj",
			 	sortorder: "desc",
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
		<html:form action="/jcftz_sh">
			<input type="hidden" id="shzt" value="dsh"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="sbsh();return false;" 
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
<%--						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>--%>
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
				<span>校内拓展结果审核列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
