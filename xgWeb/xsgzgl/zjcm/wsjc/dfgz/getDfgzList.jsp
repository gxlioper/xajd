<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/zjcm/wsjc/dfgz/js/dfgz.js"></script>	
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" >
			var gridSetting = {
					caption : "评分组列表",
					radioselect:true,
					pager : "pager",
					url : "cjWsfDfgz.do?method=getDfgzList&type=query",
					colList : [ 
					   {label : 'dfszid',name : 'dfszid',index : 'dfszid',key:true ,hidden:true},
					   {label : '学年',name : 'xn',index : 'xn',width : '20%'},
					   {label : '学期',name : 'xqdm',index : 'xqdm',width : '20%',hidden:true}, 
					   {label : '学期',name : 'xqmc',index : 'xqmc',width : '10%'},
					   {label : '抽查年月',name : 'ccny',index : 'ccny',width : '20%',formatter:gzszLink},
					   {label : '开放维护时间',name : 'kfwhsj',index : 'kfwhsj',width : '30%'},
					   {label : '已提交寝室数',name : 'ytjqs',index : 'ytjqs',width : '20%',
						formatter:function(cellValue,rowObject){
									var html = jQuery("<a href='javascript:void(0);' class='name'>"+cellValue+"</a>");
									html.bind("click",function(){
										showDialog("查看已提交寝室",800,500,"cjWsfDfgz.do?method=getCcqsList&tjzt=1&dfszid="+rowObject["dfszid"]);
									});
									return html;
								 }
					   },
						   
					   {label : '未提交寝室数',name : 'wtjqs',index : 'wtjqs',width : '20%',
						   formatter:function(cellValue,rowObject){
							var html = jQuery("<a href='javascript:void(0);' class='name'>"+cellValue+"</a>");
							html.bind("click",function(){
								showDialog("查看未提交寝室",800,500,"cjWsfDfgz.do?method=getCcqsList&tjzt=0&dfszid="+rowObject["dfszid"]);
							});
							return html;
						 }
	  					}
					   ],
					sortname : "ccny",
					sortorder : "desc"
				}

			function yhsLink(cellValue, rowObject) {
				return "<a href='javascript:void(0);' class='name' onclick='yhck(\""+rowObject["pfzid"]+"\");'>"+cellValue+"</a>";
			}

			//评分组详情查看
			function yhck(pfzid) {
				showDialog("查看用户", 800, 550, "cjWsfPfz.do?method=viewPfzList&pfzid=" + pfzid);
			}

			jQuery(function() {
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function add() {
				var url = "cjWsfDfgz.do?method=addDfgz";
				var title = "设置";
				showDialog(title, 600, 300, url);
			}
						
		</script>
		
	</head>
	<body>
	<html:form action="/cjWsfDfgz" method="post">
	<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
			<div class="toolbox">
			<!-- 按钮 -->
			<logic:equal name="writeAble" value="yes">	
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">增加</a></li>
					<li><a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">修改</a></li>
					<li><a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">删除</a></li>		
					<li><a href="javascript:void(0);" onclick="gzsd();return false;" class="btn_sz" >规则设定</a></li>					
				</ul>
			</div>
			</logic:equal>
				
			<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
		</div>
			<div class="formbox">
			<!--标题start-->
				<h3 class="datetitle_01">
					<span> 打分规则列表 </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
