<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/zbgl/sqsh/js/zbsq.js"></script>
		<script type="text/javascript">
		
			jQuery(function(){
				var gridSetting = {
					pager:"pager",
					url:"zbglSqsh.do?method=getZbshList",
					colList:[
					   {label:'key',name:'id', index: 'id',hidden:true,key:true},
					   {label:'学号',name:'xh', index: 'xh',width:'13%',formatter:function(cell,rowObject){
						   return "<a href=\"javascript:cksq('"+rowObject["id"]+"');\" class='name'>"+cell+"</a>";
					   }},
					   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
					   {label:'班级',name:'bjmc', index: 'bjdm',width:'13%'},
					   {label:'学年',name:'xn', index: 'xn'},
					   {label:'申请时间',name:'sqsj',index:'sqsj',width:'13%'},
					   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
					   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},
					   {label:'xtgwid',name:'xtgwid', index: 'xtgwid',hidden:true},
					   {label:'审核状态',name:'shztmc', index: 'shzt',width:'5%'}
					],
					sortname:"sqsj",
					sortorder:"desc",
					radioselect:true
				};

				var map = getSuperSearch();
				map["shzt"] = "dsh";
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function cancelAuding(){
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				if (ids.length != 1){
					showAlertDivLayer("请选择您要撤消的记录！");
				} else {
					jQuery.post("zbglSqsh.do?method=cancelAudit",{id:ids.toString(),gwid:rows[0]["xtgwid"]},function(data){
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
				}
			}
			
			function cksq(id){
				showDialog("查看申请表",800,480,"zbglSqsh.do?method=cksq&id="+id);
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	
		<html:form action="/zxdkXyddk" method="post" styleId="zxdkXyddkForm">
			<html:hidden property="shzt" styleId="shzt"/>
		
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
			<logic:equal name="writeAble" value="yes">	
				<div class="buttonbox">
					<ul>
						<li id="li_sh"><a href="#" class="btn_sh" onclick="showAuding();return false;">审核</a></li>
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelAuding();return false;" 
							   class="btn_qxsh">撤消</a>
						</li>
						<li><a href="#" class="btn_cs" onclick="viewLcgz();return false;">流程跟踪</a></li>
					</ul>
				</div>
			</logic:equal>
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
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>征兵审核列表 </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
