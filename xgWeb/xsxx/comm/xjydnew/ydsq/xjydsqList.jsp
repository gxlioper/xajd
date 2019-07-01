<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="xsxx/comm/xjydnew/js/xjydsq.js"></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		
 	   var gridSetting = {
			caption:"学籍异动申请列表",
			pager:"pager",
			url:"xjydsq.do?method=xjydsqList&type=query",
			colList:[
			   {label:'xjydsqid',name:'xjydsqid', index: 'xjydsqid',key:true,hidden:true},
			   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
			   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},
			   {label:'ydlbdm',name:'ydlbdm', index: 'ydlbdm',hidden:true},
			   {label:'学号',name:'xh', index: 'xh',formatter:xhLink},
			   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
			   {label:'原年级',name:'ydqnj', index: 'ydqnj'},
			   {label:'原<bean:message key="lable.xb" />',name:'ydqxymc', index: 'ydqxymc'},
			   {label:'原班级',name:'ydqbjmc', index: 'ydqbjmc'},
			   {label:'异动类别',name:'ydlbmc', index: 'ydlbmc'},
			   {label:'申请时间',name:'sqsj', index: 'sqsj'},
			   {label:'审核状态',name:'shztmc', index: 'shztmc'}
			],
			sortname: "sqsj",
		 	sortorder: "desc"
		}

 	  function getWord(){
			var rows = jQuery("#dataTable").getSeletRow();

			var ids = jQuery("#dataTable").getSeletIds();
			
			 if (rows.length == 0){
				showAlertDivLayer("请选择您要下载的记录！");
				return;
			 }
			var url="xjydsq.do?method=doPrint&value="+ids ;
			window.open(url);
		}

		jQuery(function(){
			gridSetting.params = getSuperSearch();
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
		<html:form action="/xjydsq.do?method=xjydsqList&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
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
							<a href="javascript:void(0);" onclick="pltj();return false;" class="btn_shuc">提交</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="plqxtj();return false;" class="btn_sr">撤销</a>
						</li>
					</logic:equal>
					<li><a href="javascript:void(0);" onclick="rcxwshLcinfo();return false;" 
						   title="选中一条记录，点击该按钮可以查看审核流程。"
						   class="btn_cs">流程跟踪</a></li>
					<logic:equal name="writeAble" value="yes">		   
					<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
					</logic:equal>
					<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">下载登记表</a></li>
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
				<span> 学籍异动申请列表 </span>
			</h3>	
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>	
	</body>
</html>
