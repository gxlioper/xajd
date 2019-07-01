<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/jskp/xspjjg/js/xspjjg.js"></script>
		<script type="text/javascript">
			var gridSetting1 = {
					caption:"学生评价自主申请",
					pager:"pager",
					url:"xspj_xspjjg.do?method=seachForXspjjg&type=nlsy",
					colList:[
					   {label:'guid',name:'guid', index: 'guid',key:true,hidden:true},
					   {label:'数据来源',name:'sjly', index: 'sjly',hidden:true},
					   {label:'数据录入时间',name:'sjlrsj', index: 'sjlrsj',hidden:true},
					   {label:'学号',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
					   {label:'姓名',name:'xm', index: 'xm',width:'6%'},
					   {label:'学院',name:'xymc', index: 'xymc',width:'10%'},
					   {label:'学年',name:'xn', index: 'xn',width:'6%'},
					   {label:'项目名称',name:'xmmc', index: 'xmmc',width:'10%'},
					   {label:'项目类别',name:'xmlbmc', index: 'xmlbmc',width:'8%'},
					   {label:'短学期',name:'dxqmc', index: 'dxqmc',width:'5%'},
					   {label:'分值',name:'fz', index: 'fz',width:'4%'},
					   {label:'参与时间',name:'cysj', index: 'cysj',width:'4%'}
					],
					sortname: "cysj",
				 	sortorder: "desc"
			}

			var gridSetting2 = {
					caption:"学生评价自主申请",
					pager:"pager",
					url:"xspj_xspjjg.do?method=seachForXspjjg&type=szsz",
					colList:[
					   {label:'guid',name:'guid', index: 'guid',key:true,hidden:true},
					   {label:'数据来源',name:'sjly', index: 'sjly',hidden:true},
					   {label:'数据录入时间',name:'sjlrsj', index: 'sjlrsj',hidden:true},
					   {label:'学号',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
					   {label:'姓名',name:'xm', index: 'xm',width:'6%'},
					   {label:'学院',name:'xymc', index: 'xymc',width:'10%'},
					   {label:'学年',name:'xn', index: 'xn',width:'6%'},
					   {label:'项目名称',name:'xmmc', index: 'xmmc',width:'10%'},
					   {label:'项目类别',name:'xmlbmc', index: 'xmlbmc',width:'8%'},
					   {label:'短学期',name:'dxqmc', index: 'dxqmc',width:'5%'},
					   {label:'分值',name:'fz', index: 'fz',width:'4%'},
					   {label:'参与时间',name:'cysj', index: 'cysj',width:'4%'}
					],
					sortname: "cysj",
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
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title}</a>
			</p>
		</div>
		<html:form action="/xspj_xspjsq">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li id="zj" style="display:none;">
								<a href="javascript:void(0);" class="btn_zj" onclick="xspjjgAdd();return false;">增加</a>
							</li>
							<li id="xg" style="display:none;">
								<a href="javascript:void(0);" class="btn_xg" onclick="xspjjgUpdate();return false;">修改</a>
							</li>
							<li id="sc" style="display:none;">
								<a href="javascript:void(0);" class="btn_sc" onclick="xspjjgDelete();return false;">删除</a>
							</li>
							<li id="dr">
								<a href="javascript:void(0);" class="btn_dr" onclick="xspjjgImport();return false">导入</a>
							</li>
						</logic:equal>
							<!-- 导出按钮的变换BEGIN -->
							<li id="dc_Nlsy">
								<a href="javascript:void(0);" class="btn_dc" onclick="xspjjgExportNlsy();return false">导出</a>
							</li>
							<li id="dc_Szsz" style="display:none;">
								<a href="javascript:void(0);" class="btn_dc" onclick="xspjjgExportSzsz();return false">导出</a>
							</li>
							<!-- 导出按钮的变换END -->
						<logic:notEqual value="stu" name="userType">
							<li>
								<a href="javascript:void(0);" class="btn_dc" onclick="DataStatistics();return false;">纪实考评分统计</a>
							</li>
						</logic:notEqual>
					</ul>
				</div>
				
				<div class="comp_title" id="comp_title">
					<ul style="width:90%" id="tabUl">
						<li class="ha" tab="nlsy"><a href="javascript:void(0);" onclick="changeTab(this,'1');"><span>能力素养</span></a></li>
						<li tab="szsz"><a href="javascript:void(0);" onclick="changeTab(this,'2');"><span>思想政治素质</span></a></li>
					</ul>
				</div>	
				
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>评价结果列表&nbsp;&nbsp;</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>