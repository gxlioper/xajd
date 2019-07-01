<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/jqlx/js/jqlxjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"学生假期留校结果列表",
				pager:"pager",
				url:"rcsw_jqlxjg.do?&type=query",
				colList:[
				   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
				   {label:'sjlx',name:'sjlx', index: 'sjlx',hidden:true},
				   {label:'学号',name:'xh', index: 'xh',width:'13%',formatter:xhLink},
				   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
				   {label:'性别',name:'xb', index: 'xb',width:'6%'},
				   {label:'民族',name:'mzmc', index: 'mz',width:'6%'},
				   {label:'行政班级',name:'bjmc', index: 'bjdm',width:'14%'},
                    {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'14%'},
				   {label:'学年',name:'xn', index: 'xn',width:'11%'},
				   {label:'学期',name:'xqmc', index: 'xqmc',width:'6%'},
				   {label:'留校开始时间',name:'lxkssj', index: 'lxkssj',width:'12%'},
				   {label:'留校截止时间',name:'lxjzsj', index: 'lxjzsj',width:'12%'},

				<logic:equal value="10026" name="xxdm">
					{label:'校区名称',name:'xxxqmc', index: 'lsxq',width:'6%'},
				</logic:equal>

			    <logic:equal value="10344" name="xxdm">
			    	{label:'留宿园区',name:'lxxqmc', index: 'lxxqmc',width:'6%'},
			    	{label:'留宿宿舍楼号',name:'lxldmc', index: 'lxldmc',width:'6%'},
			    	{label:'留宿寝室号',name:'lxqs', index: 'lxqs',width:'6%'},
			    </logic:equal>

				<logic:notEqual value="10344" name="xxdm">
				   {label:'楼栋名称',name:'ldmc', index: 'ldmc',width:'6%'},
				   {label:'寝室号',name:'qsh', index: 'qsh',width:'6%'},
				</logic:notEqual>

				<logic:notEqual value="10026" name="xxdm">
				<logic:notEqual value="10344" name="xxdm">
				   {label:'床位号',name:'cwh', index: 'cwh',width:'6%'},
				</logic:notEqual>
				</logic:notEqual>

				   {label:'数据来源',name:'sjlymc', index: 'sjlymc',hidden:true},
				   {label:'楼栋代码',name:'lddm', index: 'lddm',hidden:true}
				],
				sortname: "sqsj",
			 	sortorder: "desc"
			}

			jQuery(function(){
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function drjqlx(){
				var realTable = "";
				var tableName = "";
				var sty = "toolbar=no,location=no,directories=no,status=yes";
				sty += ",menubar=no,scrollbars=yes,resizable=yes,width=600,height=400,top=100";
				sty += ",left=200";
				if($("realTable")) realTable = document.getElementById("realTable").value;
				if($("tableName")) tableName = document.getElementById("tableName").value;
				url = 'rcsw_jqlx.do?method=importData';
				url += "&realTable=" + realTable;
				url += "&tableName=" + tableName;
				//window.open(url,'',sty);
				showDialog('导入数据', 600, 300, url);
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/rcsw_rcxwwh_rcxwxxwhgl">		
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">删除</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc">导出</a>
						</li>
						<logic:equal name="writeAble" value="yes">	
							<li><a href="javascript:void(0);" onclick="drjqlx();return false;" id="btn_dr" class="btn_dr">留校寝室导入</a></li>
						</logic:equal>
						<!-- 四川职业技术学院打印申请表 begin-->
						<logic:equal value="12970" name="xxdm">
							<li>
								<a href="javascript:void(0);" onclick="printjqlxsqb('rcsw_jqlx.do?method=printjqlxsqb');return false;" class="btn_down">下载申请表</a>
							</li>
						</logic:equal>
						<!-- 四川职业技术学院打印申请表 end-->
						<logic:equal value="12309" name="xxdm">
							<li><a href="javascript:void(0);" onclick="printjqlxsqb('rcsw_jqlx.do?method=printLstxz');return false;" class="btn_down">下载临时通行证</a></li>
						</logic:equal>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>学生假期留校结果列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
