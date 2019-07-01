<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/bfjs/fswh/js/jsfs.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"已提交竞赛分班级列表",
				pager:"pager",
				url:"xpjpyBfjsFswh.do?method=viewQxjlList&doType=query",
				colList:[
				   {label:'key',name:'id', index: 'id',hidden:true,key:true},
				   {label:'年级',name:'nj', index: 'nj',width:'8%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
				   {label:'专业',name:'zymc', index: 'zydm',width:'20%'},
				   {label:'班级',name:'bjdm', index: 'bjdm',hidden:true},
				   {label:'班级',name:'bjmc', index: 'bjdm',width:'23%'},
				   {label:'提交人',name:'tjr', index: 'tjr',width:'10',hidden:true},
				   {label:'操作人',name:'tjrxm', index: 'tjrxm',width:'12%'},
				   {label:'操作时间',name:'qxsj', index: 'qxsj',width:'18%'},
				   {label:'取消原因',name:'qxyy',index:'qxyy',width:'15%',hidden:true}
				],
				sortname: "qxsj",
			 	sortorder: "desc"
			};
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			//前往取消调整记录
			function goYtjbj(){
				var url = "pj_zcfqx.do";
				refreshForm(url);
			}

			//查看取消记录
			
			function qxjlView(){
				
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer("请选择一条您要查看的记录！");
					return false;
				} 
				
				var id = rows[0]["id"];
				showDialog("查看竞赛分",510,295,"xpjpyBfjsFswh.do?method=qxjlView&id="+id);
			}
			
			
			//竞赛分取消提交记录导出
			function exportConfig(){
				var DCCLBH="pj_qxjl.do";
				customExport(DCCLBH, exportData);
			}
			function exportData(){
				var DCCLBH="pj_qxjl.do";
				setSearchTj();//设置高级查询条件
				var url = "xpjpyBfjsFswh.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
		</script>
	</head>

	<body>
	
		<input type="hidden" value="${cssz.xn }" id="xn"/>
		<input type="hidden" value="${cssz.xq }" id="xq"/>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xpjpyBfjsFswh" >
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->	
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li><a href="javascript:void(0);" onclick="qxjlView();" class="btn_ck">查看</a></li>
						</logic:equal>	
						
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
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
				<span><font color="blue">${cssz.zqmc}&nbsp;</font>提交状态取消记录 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
