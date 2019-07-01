<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/xmsbgl/xmsbjg/js/xmsbjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "项目申报列表",
				pager : "pager",
				url : "xmsbXmsbjg.do?method=getXmsbjgList&type=query",
				colList : [
							{ label : 'key', name : 'jgid', index : 'jgid',key:true,hidden : true },
							{ label : 'key', name : 'xmdm', index : 'xmdm',hidden : true },
							{ label : 'splc', name : 'splc', index : 'splc',hidden : true },
							{ label : 'sfkxg', name : 'sfkxg', index : 'sfkxg',hidden : true },
							{ label : 'sfrm', name : 'sfrm', index : 'sfrm',hidden : true },
							{ label : 'sqkssj', name : 'sqkssj', index : 'sqkssj',hidden : true },
							{ label : 'sqjssj', name : 'sqjssj', index : 'sqjssj',hidden : true },
							{ label : 'sjly', name : 'sjly', index : 'sjly',hidden : true },
							{ label : 'sqkg', name : 'sqkg', index : 'sqkg',hidden : true },
							/*
							{ label : '学年', name : 'xn', index : 'xn', width : '10%' },
							{ label : '学期', name : 'xqmc', index : 'xqmc', width : '8%' },*/
							{ label : '项目名称', name : 'xmmc', index : 'xmmc', width : '15%',formatter : xmmcLink  },
							{ label : '项目类型', name : 'csmsmc', index : 'csms', width : '10%'},
							{ label : '项目级别', name : 'xmjbmc', index : 'xmjbmc', width : '10%' },
							{ label : '所属科目', name : 'sskmmc', index : 'xmjbmc', width : '10%' },
							{ label : '项目开始时间', name : 'xmkssj', index : 'xmkssj', width : '12%' },
							{ label : '基础学分', name : 'jcxf', index : 'jcxf', width : '5%' },
							{ label : '申报人', name : 'sbrxm', index : 'sbrxm', width : '5%' },
							{ label : '申报部门', name : 'sbbmmc', index : 'sbbmmc', width : '15%' },
							{ label : '项目设置', name : 'sqkg', index : 'sqkg', width : '15%' ,formatter : setXmsz},
							{ label : '项目阶段', name : 'jdsz', index : 'jdsz', width : '15%' ,formatter : setXmjd}
						]
					 }
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xmsbXmsbjg">
		<input type="hidden" name="currDate" id="currDate" value="${currDate}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="dr();return false;" class="btn_dr">导入</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="setRmxm('1');return false;" class="btn_sz">设定热门</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="setRmxm('0');return false;" class="btn_sz">取消热门</a>
						</li>
						</logic:equal>
										
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
						
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>项目申报列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
