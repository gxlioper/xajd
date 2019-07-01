<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script> 
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/ssyd/js/ydjg.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//初始化查询
				var gridSetting = {
						caption:"宿舍异动结果列表",
						pager:"pager",
						url:"ydjg.do?method=list&type=query",
						colList:[
								   {label:'学号',name:'xh', index: 'xh',formatter:xhLink},
								   {label:'姓名',name:'xm', index: 'xm'},
								   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'},
								   {label:'性别',name:'xb', index: 'xb'},
								   {label:'异动类型',name:'ssydlxmc', index: 'ssydlxmc'},
								   {label:'退宿寝室',name:'tsqs', index: 'tsqs',formatter:tscwLink},
								   {label:'入住寝室',name:'rzqs', index: 'rzqs',formatter:rzcwLink},
								   {label:'异动时间',name:'tstzsj', index: 'tstzsj',formatter:rzsjLink},
								   {label:'记录时间',name:'czsj', index: 'czsj',formatter:rzsjLink,hidden:true},
								   {label:'备注',name:'bz', index: 'bz',title:'bz',formatter:bzSubstring},
								   {label:'sjly',name:'sjly', index: 'sjly',hidden:true},	
								   {label:'ydqlddm',name:'ydqlddm', index: 'ydqlddm',hidden:true},	
								   {label:'ydqqsh',name:'ydqqsh', index: 'ydqqsh',hidden:true},	
								   {label:'ydqcwh',name:'ydqcwh', index: 'ydqcwh',hidden:true},	
								   {label:'ydhlddm',name:'ydhlddm', index: 'ydhlddm',hidden:true},	
								   {label:'ydhqsh',name:'ydhqsh', index: 'ydhqsh',hidden:true},	
								   {label:'ydhcwh',name:'ydhcwh', index: 'ydhcwh',hidden:true},	
								   {label:'ssydid',name:'ssydid', index: 'ssydid',key:true,hidden:true}
						],
						sortname: "czsj",
					 	sortorder: "desc"
				}
				
				var gridSetting2 = {
						caption:"宿舍异动结果列表",
						pager:"pager",
						url:"ydjg.do?method=list&type=query",
						colList:[
								   {label:'学号',name:'xh', index: 'xh',formatter:xhLink},
								   {label:'姓名',name:'xm', index: 'xm'},
								   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'},
								   {label:'性别',name:'xb', index: 'xb'},
								   {label:'异动类型',name:'ssydlxmc', index: 'ssydlxmc'},
								   {label:'退宿寝室',name:'tsqs', index: 'tsqs',formatter:tscwLink},
								   {label:'入住寝室',name:'rzqs', index: 'rzqs',formatter:rzcwLink},
								   {label:'异动时间',name:'tstzsj', index: 'tstzsj',formatter:rzsjLink},
								   {label:'记录时间',name:'czsj', index: 'czsj',formatter:rzsjLink,hidden:true},
								   {label:'申请理由',name:'bz', index: 'bz',title:'bz',formatter:bzSubstring},
								   {label:'sjly',name:'sjly', index: 'sjly',hidden:true},	
								   {label:'ydqlddm',name:'ydqlddm', index: 'ydqlddm',hidden:true},	
								   {label:'ydqqsh',name:'ydqqsh', index: 'ydqqsh',hidden:true},	
								   {label:'ydqcwh',name:'ydqcwh', index: 'ydqcwh',hidden:true},	
								   {label:'ydhlddm',name:'ydhlddm', index: 'ydhlddm',hidden:true},	
								   {label:'ydhqsh',name:'ydhqsh', index: 'ydhqsh',hidden:true},	
								   {label:'ydhcwh',name:'ydhcwh', index: 'ydhcwh',hidden:true},	
								   {label:'ssydid',name:'ssydid', index: 'ssydid',key:true,hidden:true}
						],
						sortname: "czsj",
					 	sortorder: "desc"
				}
				if(jQuery("#xxdm").val() == '12303'){				
					jQuery("#dataTable").initGrid(gridSetting2);
				}else{
					jQuery("#dataTable").initGrid(gridSetting);
				}
				//jQuery("#btn_sh").click(go_sh);
				//jQuery("#btn_cs").click(lcgz);
			});
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
				<input type="hidden" name="shlx" id="shlx" value="dsh"/>
			</p>
		</div>
		<html:form action="/szdw_zwsh.do">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="javascript:void(0);" onclick="addYdjg();return false;" class="btn_zj">增加</a>
							</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="ckYdjg();return false;" class="btn_ck">查看</a>
						</li>
						<logic:equal value="yes" name="writeAble">
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
						</logic:equal>
						<logic:notEqual name="xxdm" value="12861">
							<li>
								<a href="javascript:void(0);" onclick="printTstzd();return false;" class="btn_down">调宿通知单</a>
							</li>
						</logic:notEqual>
						<logic:equal name="xxdm" value="12861">
							<li>
								<a href="javascript:void(0);" onclick="printTstzd();return false;" class="btn_down">调宿单</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="printTsd();return false;" class="btn_down">退宿单</a>
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
				<span>宿舍异动结果列表</span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
