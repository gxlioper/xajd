<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script> 
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/xsgbgl/js/zwsq.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"学生干部职务申请",
				pager:"pager",
				url:"szdw_zwsq.do?method=zwsqList&type=query",
				colList:[
				   {label:'申请代码',name:'sqid', index: 'sqid',width:'1%',key:true,hidden:true},
				   {label:'学号',name:'xh', index: 'xh',width:'15%',formatter:xhLink},
				   {label:'姓名',name:'xm', index: 'xm',width:'12%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'15%'},
				   {label:'专业',name:'zymc', index: 'zymc',width:'15%'},
				   {label:'申请职务',name:'zwmc', index: 'zwmc',width:'15%'},
                   {label:'职务类型',name:'lxmc', index: 'lxmc',width:'15%'},
				   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'18%'},
				   {label:'审核状态',name:'shzt', index: 'shzt',width:'15%'},
				   {label : 'shztdm', name : 'shztdm', index : 'shztdm', hidden : true },
				   {label:'审批流程',name:'splc', index: 'splc',width:'1%',hidden:true}
				],
				sortname: "sqsj",
			 	sortorder: "desc"
			};
				
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
				//为button注册事件
				jQuery("#btn_zj").click(add);
				jQuery("#btn_sc").click(qx_sh);
				//jQuery("#search_go").click(query);
				jQuery('#btn_del').click(del);
				jQuery("#btn_xg").click(update);
				jQuery("#btn_cz").click(function(){searchReset()});
				jQuery("#btn_cs").click(lcgz);
			});

			function submitBusi(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0) {
					showAlertDivLayer("请选择您要提交的记录！");
				} else if (ids.length >1 ) {
					showAlertDivLayer("请选择一条您要提交的记录！");
				} else {
					var rows = jQuery("#dataTable").getSeletRow();
					for(var i=0;i<ids.length;i++){
						if(rows[i]['shzt']!='未提交' && rows[i]['shzt']!='退回' ){
							showAlertDivLayer("请选择未提交或者退回的记录！");
							return false;
						}
					}
					showConfirmDivLayer("您确定要提交选择的记录吗？",{"okFun":function(){
						jQuery.post("szdw_zwsq.do?method=submitZwsq",
							{values:ids.toString(),
							 xh : rows[0]['xh'],
							 splcid : rows[0]['splc'],
							 shzt:rows[0]['shztdm']
							},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}

			function cancel(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0) {
					showAlertDivLayer("请选择您要撤销的记录！");
				} else if (ids.length >1 ) {
					showAlertDivLayer("请选择一条您要撤销的记录！");
				} else {
					var rows = jQuery("#dataTable").getSeletRow();
					for(var i=0;i<ids.length;i++){
						if(rows[i]['shzt']!='审核中'){
							showAlertDivLayer("只有审核中的记录才能被撤销！");
							return false;
						}
					}
					showConfirmDivLayer("您确定要撤销选择的记录吗？",{"okFun":function(){
						jQuery.post("szdw_zwsq.do?method=qxsq",
							{
							 values:ids.toString(),
							 splcid : rows[0]['splc'] 
							},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}
        var DCCLBH = "szdw_zwsq.do";//dcclbh,导出功能编号

        //自定义导出 功能
        function exportConfig() {
            //DCCLBH导出功能编号,执行导出函数
            customExport(DCCLBH, ExportDatas);
        }

        //导出方法
        function ExportDatas() {
            setSearchTj();//设置高级查询条件
            var url = "szdw_zwsq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
            url = addSuperSearchParams(url);//设置高级查询参数
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }

		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/szdw_fdypxxmwh.do">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
							  <a href="javascript:void(0);" id="btn_zj" class="btn_zj">申请</a>
							</li>
							<li>
							  <a href="javascript:void(0);"  id="btn_xg" class="btn_xg">修改</a>
							</li>
							<li>
							<a href="javascript:void(0);"  id="btn_del" class="btn_sc" >删除</a>
							</li>
							<li>
							  <a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">提交</a>
							</li>
							<li>
								<a href="javascript:void(0);" id="btn_sr"  class="btn_sr" onclick="cancel();return false;">撤销</a>
							</li>
							<li><a href="javascript:void(0);" id="btn_cs" class="btn_cs">流程跟踪</a></li>	
							<logic:equal value="11067" name="xxdm">
								<li><a href="javascript:void(0);" onclick="printXsgbbab('szdw_zwsq.do?method=printXsgbbab');return false;" class="btn_down">下载备案表</a></li>	
							</logic:equal>
							<logic:equal value="10351" name="xxdm">
								<li><a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_down">导出</a></li>
							</logic:equal>
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
				<span>学生干部职务申请列表</span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
