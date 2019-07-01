<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				pager:"pager",
				url:"ybgzzCywh.do?method=getList",
				colList:[
				   {label:'key',name:'id', index: 'id',hidden:true,key:true},
				   {label:'学号',name:'xh', index: 'xh',width:'13%',formatter:function(cell,rowObject){
					   return "<a href=\"javascript:cksq('"+rowObject["id"]+"');\" class='name'>"+cell+"</a>";
				   }},
				   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
				   {label:'班级',name:'bjmc', index: 'bjdm'},
				   {label:'申请加入日期',name:'sqjrsj',index:'sqjrsj'},
				   {label:'申请时间',name:'sqsj',index:'sqsj'},
				   {label:'是否退出',name:'sftc',index:'sftc',formatter:function(c,r){
					   return c=="是" ? "<a href=\"javascript:cktc('"+r["id"]+"');\" class='name'>是</a>" : "否";
				   }},
				   {label:'sjly',name:'sjly',index:'sjly',hidden:true}
				],
				sortname:"sqsj",
				sortorder:"desc"
			};

			jQuery(function(){
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function cksq(id){
				showDialog('查看',600,330,'ybgzzCywh.do?method=view&id='+id);
			}
			
			function cktc(id){
				showDialog('查看',400,150,'ybgzzCywh.do?method=viewExit&id='+id);
			}

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			function update(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					alertInfo("请选择一条您要修改的记录！");
				} else {
					
					if (rows[0]["sjly"] == "1"){
						alertInfo("流程数据不能修改！");
						return false;
					}
					showDialog('修改',700,380,'ybgzzCywh.do?method=edit&id='+rows[0]["id"]);
				}
			}

			function del(){
				var ids = jQuery("#dataTable").getSeletIds();

				if (ids.length == 0){
					alertInfo("请选择您要删除的记录！");
				} else {
					
					var rows = jQuery("#dataTable").getSeletRow();
					for ( var i = 0; i < ids.length; i++) {
						if (rows[i]['sjly'] == '1') {
							showAlertDivLayer("流程数据不能删除！");
							return false;
						}
					}
					
					showConfirmDivLayer("您确定要删除该记录吗？",{"okFun":function(){
						jQuery.post("ybgzzCywh.do?method=del",{ids:ids.toString()},function(data){
							alertInfo(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}
			
			function add(){
				showDialog('增加易班工作站成员',700,380,'ybgzzCywh.do?method=add');;
			}
			
			function importJdqk(){
				toImportData("ybgzz_jgwh");
				return false;
			}
			
			//导出
			function exportConfig(){
				var DCCLBH='ybgzz_cywh.do';
				customExport(DCCLBH, exportData);
			}

			function exportData(){
				var DCCLBH='ybgzz_cywh.do';
				setSearchTj();//设置高级查询条件
				
				var url = "ybgzzCywh.do?method=export&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			
			function tcgzz(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0){
					alertInfo("请选择您要操作的记录！");
				} else {
					
					var rows = jQuery("#dataTable").getSeletRow();
					for ( var i = 0; i < ids.length; i++) {
						if (rows[i]['sftc'] == '是') {
							showAlertDivLayer("已退出成员不能重复退出！");
							return false;
						}
					}
					
					showDialog('退出易班工作站',500,220,'ybgzzCywh.do?method=exit&ids='+ids.toString());
				}
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	
		<html:form action="/zxdkSyddk" method="post" styleId="zxdkSyddkForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
							<li><a href="javascript:void(0);" onclick="add()" class="btn_zj">增加</a></li>
							<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
							<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>						
<%--							<li><a href="javascript:void(0);" onclick="importJdqk();" class="btn_dr">导入</a></li>						--%>
							<li><a href="javascript:void(0);" onclick="exportConfig();" class="btn_dc">导出</a></li>						
							<li><a href="javascript:void(0);" onclick="tcgzz();" class="btn_gb_shut">退出工作站</a></li>						
						</ul>
					</div>
				</logic:equal>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>易班工作站成员列表  </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
