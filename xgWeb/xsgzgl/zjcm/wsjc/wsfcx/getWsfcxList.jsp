<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/zjcm/wsjc/wsfcx/js/wsfcx.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" >
			var gridSetting = {
					caption : "评分组列表",
					pager : "pager",
					url : "cjWsfcx.do?method=getWsfcxList&type=query",
					colList : [					   	 
					   {label : 'wsfid',name : 'wsfid',index : 'wsfid',key:true ,hidden:true},
					   {label : '抽查年月',name : 'ccny',index : 'ccny',width : '9%'},
					   {label : '楼栋名称',name : 'ldmc',index : 'ldmc',width : '12%'},
					   {label : '寝室号',name : 'qsh',index : 'qsh',width : '5%'},
					   {label : '所属年级',name : 'nj',index : 'nj',width : '5%'},
					   {label : '所属学院',name : 'bmmc',index : 'bmmc',width : '20%'},
					   {label : '床位数',name : 'cws',index : 'cws',width : '8%'}, 
					   {label : '入住人数',name : 'rzrs',index : 'rzrs',width : '8%'},					   
					   {label : '分值',name : 'fz',index : 'fz',width : '10%'},
					   {label : '等级',name : 'dj',index : 'dj',width : '7%'},
					   {label:'评分组',name:'pfzmc', index: 'pfzmc',width:'15%'}
					   ],
					sortname : "ccny",
					sortorder : "desc"
				}

			jQuery(function() {
				jQuery("#dataTable").initGrid(gridSetting);
			});

			//高级查询
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
						
		</script>
		
	</head>
	<body>
	<html:form action="/cjWsfcx" method="post">
	<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
			<div class="toolbox">
				
			<div class="buttonbox">
				<ul>
					<!-- 按钮 -->
					<logic:equal name="writeAble" value="yes">
						<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
						<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a>
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false;" 
								   title="选中一条记录，点击该按钮您可以撤消失误的提交操作。"
								   class="btn_qxsh">撤消</a>
						</li>
					</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="ck();return false;" 
								   title="选中一条记录，点击该按钮可以查看信息。"
								   class="btn_ck">查看</a>
						</li>									
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>						
				</ul>					
			</div>							
			<!-- 过滤条件 -->
			<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
		</div>
			<div class="formbox">
			<!--标题start-->
				<h3 class="datetitle_01">
					<span> 卫生分查询列表 </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
