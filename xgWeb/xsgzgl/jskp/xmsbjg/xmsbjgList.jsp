<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/jskp/xmsbjg/js/xmsbjg.js"></script>
		<script type="text/javascript">
			var gridSetting1 = {
				caption:"项目申报结果列表",
				pager:"pager",
				url:"jskpXmjg.do?method=getXmsbjgList&type=query",
				colList:[
					   {label:'申请id',name:'jgid', index: 'jgid',key:true,hidden:true},
				       {label:'项目id',name:'xmid', index: 'xmid',hidden:true},
				       {label:'sbly',name:'sbly', index: 'sbly',hidden:true},
				       {label:'xn',name:'xn', index: 'xn',hidden:true},
				       { label : '年级', name : 'nj', index : 'nj', width : '4%' },
					   { label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xymc', width : '15%' },
					   {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
					   {label:'姓名',name:'xm', index: 'xm',width:'12%'},
					   {label:'申报项目',name:'xmmc', index: 'xmmc',width:'12%'},
					   {label:'项目大类',name:'xmdlmc', index: 'xmdlmc',width:'8%'},
					   {label:'获奖时间',name:'hjsj', index: 'hjsj',width:'8%'},
					   {label:'得分',name:'fs', index: 'fs',width:'4%'},
					   {label:'申报时间',name:'sbsj', index: 'sbsj',width:'12%'}
				],
				sortname: "sbsj",
			 	sortorder: "desc"
			}

			var gridSetting2 = {
					caption:"思政素质结果列表",
					pager:"pager",
					url:"jskpXmjg.do?method=getSzszList",
					colList:[
						   {label:'jgid',name:'jgid', index: 'jgid',key:true,hidden:true},
					       {label:'年级',name:'nj',index:'nj',width:'4%' },
						   {label:'学院',name:'xymc',index:'xymc',width:'10%' },
						   {label:'学号',name:'xh',index:'xh',width:'8%'},
						   {label:'姓名',name:'xm',index:'xm',width:'8%'},
						   {label:'申报项目',name:'xmmc',index:'xmmc',width:'10%'},
						   {label:'项目类别',name:'xmlbmc',index:'xmlbmc',width:'12%'},
						   {label:'获奖时间',name:'hjsj',index:'hjsj',width:'6%'},
						   {label:'得分',name:'fs', index: 'fs',width:'2%'},
						   {label:'短学期',name:'dxqmc',index:'dxqmc',width:'8%'}
					],
					sortname: "jgid",
				 	sortorder: "desc"
				}
			
			jQuery(function(){
				var map = getSuperSearch();
				gridSetting1["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting1);
			});
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<input type="hidden" name="sfsh" id="sfsh" value="${sfsh}" />
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/jskpXmjg">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="1" name="sfsh">
							<li>
								<a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a>
							</li>
						</logic:equal>
						
						<logic:equal value="0" name="sfsh">
							<li id="li_exportOne">
								<a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a>
							</li>
							<li id="li_exportTwo" style="display:none;">
								<a href="#" class="btn_dc" onclick="szszExport();return false;">导出</a>
							</li>
							<logic:notEqual value="stu" name="userType">
								<li>
									<a href="#" class="btn_dc" onclick="DataStatistics();return false;">纪实考评分统计</a>
								</li>
								<li id="li_dr" style="display:none;">
									<a href="#" class="btn_dr" onclick="szszDataImport();return false;">结果导入</a>
								</li>
							</logic:notEqual>
						</logic:equal>
						
					</ul>
				</div>
				<logic:equal value="0" name="sfsh">
					<div class="comp_title" id="comp_title">
						<ul style="width:90%" id="tabUl">
							<li class="ha" tab="nlsy"><a href="javascript:void(0);" onclick="changeTab(this,'1');"><span>能力素养</span></a></li>
								<li tab="szsz"><a href="javascript:void(0);" onclick="changeTab(this,'2');"><span>思想政治素质</span></a></li>
						</ul>
					</div>					
				</logic:equal>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>项目申报结果列表&nbsp;&nbsp;</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
