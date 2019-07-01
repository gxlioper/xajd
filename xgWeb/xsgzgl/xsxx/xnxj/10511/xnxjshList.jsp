<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/xnxj/10511/js/xnxj.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"学年小结审核列表",
				pager:"pager",
				url:"xsxx_xnxj_xjtxgl.do?method=viewXnxjshList&type=query&shQryType=D",
				colList:[
				   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
				   {label:'学号',name:'xh', index: 'xh',width:'11%'  , formatter:xhLink1},
				   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'13%'},
				   {label:'班级',name:'bjmc', index: 'bjdm',width:'13%'},
				   {label:'学年',name:'xn', index: 'xn',width:'13%'},
				   {label:'填写时间',name:'txsj', index: 'txsj',width:'9%'},
				   {label:'splid',name:'splid', index: 'splid',hidden:true},
				   {label:'shid',name:'shid', index: 'shid',hidden:true},
				   {label:'xtgwid',name:'xtgwid', index: 'xtgwid',hidden:true},
				   {label:'审核状态',name:'shztmc', index: 'shzt',width:'13%'}
				],
				sortname: "txsj",
			 	sortorder: "desc"
			}


			var gridSetting2 = {
				caption:"学年小结审核列表",
				pager:"pager",
				url:"xsxx_xnxj_xjtxgl.do?method=viewXnxjshList&type=query&shQryType=Y",
				colList:[
				   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
				   {label:'学号',name:'xh', index: 'xh',width:'11%' , formatter:xhLink1},
				   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'13%'},
				   {label:'班级',name:'bjmc', index: 'bjdm',width:'13%'},
				   {label:'学年',name:'xn', index: 'xn',width:'13%'},
				   {label:'填写时间',name:'txsj', index: 'txsj',width:'9%'},
				   {label:'审核时间',name:'shsj', index: 'shsj',width:'15%'},
				   {label:'splid',name:'splid', index: 'splid',hidden:true},
				   {label:'shid',name:'shid', index: 'shid',hidden:true},
				   {label:'xtgwid',name:'xtgwid', index: 'xtgwid',hidden:true},
				   {label:'审核状态',name:'shztmc', index: 'shzt',width:'13%'}
				   
				],
				sortname: "shsj",
			 	sortorder: "desc"
			}
			
			
			jQuery(function(){
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);	
			});

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			/**
			 * 学号链接
			 * @param cellValue
			 * @param rowObject
			 * @return
			 */

			function xhLink1(cellValue,rowObject){
				//return "<a href='javascript:void(0);' class='name' onclick='knsView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
				var onclickfn = "onclick=\"" + "showDialog('学生详细信息' , 720 , 350 , 'xsxx_xnxj_xjtxgl.do?method=xnxjjdck&xh=" + cellValue + "&xn=" + rowObject['xn'] + "')" + "\"";
				
				var href = "href = 'javascript:void(0);'";

				var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue + "</a>";
				
				return el;
			}
		</script>
	</head>

	<body style="min-height: 800px;">
	
		<input type="hidden" value="dsh" id="shzt"/>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xsxx_xnxj_xjtxgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="xnxjSh();return false;" 
							   title="选中您要审核的记录，点击该按钮可以打开审核页面。"
							   class="btn_sh">审核</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelXnxjSh();return false;" 
							   title="选中一条记录，点击该按钮您可以撤消失误的审核操作。"
							   class="btn_sr">撤消</a>
						</li>			
						</logic:equal>			
						<li><a href="javascript:void(0);" onclick="xnxjLcinfo();return false;" 
							   title="选中一条记录，点击该按钮可以查看审核流程。"
							   class="btn_cs">流程跟踪</a></li>	
							   
						<%--<li><a href="javascript:void(0);" onclick="knsrdShqk();return false;" 
							   title="点击查看审核情况汇总统计。"
							   class="btn_tj">审核统计</a></li>--%>						
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
				<span>学年小结申请列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
