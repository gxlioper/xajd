<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/wjcf/cfsbglnew/cfsb/js/cfsb.js"></script>
		
		<script type="text/javascript">
		if("13011" == ${xxdm}){
				var gridSetting = {
					caption:"违纪信息列表",
					pager:"pager",
					url:"wjcf_cfsbgl.do?method=cxCfsbList&type=query",
					colList:[
					   {label:'pk',name:'cfid', index: 'cfid',hidden:true,key:true},
					   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},
					   {label:'sbjg',name:'sbjg', index: 'sbjg',hidden:true},
					   {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
					   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
					   {label:'专业',name:'zymc', index: 'zymc',width:'15%'},
					   {label:'学年',name:'xn', index: 'xn',width:'15%'},
					   {label:'学期',name:'xqmc', index: 'xqmc',width:'10%'},
					   {label:'处分原因',name:'cfyymc', index: 'cfyymc',width:'15%'},
					   {label:'处分类别',name:'cflbmc', index: 'cflbmc',width:'15%'},
					   {label:'审核状态',name:'shjg', index: 'shjg',width:'10%'},
					   {label:'sbjg',name:'sbjg',index:'sbjg',hidden:true},
					   {label:'wjsj',name:'wjsj',index:'wjsj',hidden:true},
					   {label:'cflbdm',name:'cflbdm',index:'cflbdm',hidden:true}
					],
					sortname: "xh",
				 	sortorder: "asc"
				}
			}else{
				var gridSetting = {
					caption:"违纪信息列表",
					pager:"pager",
					url:"wjcf_cfsbgl.do?method=cxCfsbList&type=query",
					colList:[
					   {label:'pk',name:'cfid', index: 'cfid',hidden:true,key:true},
					   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},
					   {label:'sbjg',name:'sbjg', index: 'sbjg',hidden:true},
					   {label:'学号',name:'xh', index: 'xh',width:'15%',formatter:xhLink},
					   {label:'姓名',name:'xm', index: 'xm',width:'15%'},
                        {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'15%'},
                        {label:'书院',name:'symc', index: 'symc',width:'15%'},
					   {label:'学年',name:'xn', index: 'xn',width:'15%'},
					   {label:'学期',name:'xqmc', index: 'xqmc',width:'15%'},
					   {label:'处分原因',name:'cfyymc', index: 'cfyymc',width:'15%'},
					   {label:'处分类别',name:'cflbmc', index: 'cflbmc',width:'15%'},
					   {label:'审核状态',name:'shjg', index: 'shjg',width:'10%'},
					   {label:'sbjg',name:'sbjg',index:'sbjg',hidden:true},
					   {label:'wjsj',name:'wjsj',index:'wjsj',hidden:true},
					   {label:'cflbdm',name:'cflbdm',index:'cflbdm',hidden:true}
					],
					sortname: "xh",
				 	sortorder: "asc"
				}
			}

			function xhLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name' onclick='cfsbView(\""+rowObject["cfid"]+"\");'>"+cellValue+"</a>";
			}
			
			jQuery(function(){
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();

				jQuery("#dataTable").reloadGrid(map);
			}


			function submitBusi(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length != 1) {
					showAlertDivLayer("请选择一条您要提交的记录！");
				} else {
					var rows = jQuery("#dataTable").getSeletRow();
					for(var i=0;i<ids.length;i++){
						if(rows[i]['shjg']!='未提交' && rows[i]['shjg']!='退回' ){
							showAlertDivLayer("请选择未提交或者退回的记录！");
							return false;
						}
					}
					var xh = rows[0]['xh'];
					var cflbdm =rows[0]['cflbdm'];
					var wjsj = rows[0]['wjsj'];
					var cfid = ids.toString();
					
					// 验证处分在结果库当中是否存在 （验证条件：学号、处分类别、处分时间
					jQuery.post("wjcf_cfsbgl.do?method=checkExistCfsbjg", {
						xh:xh,
						cflbdm:cflbdm,
						wjsj:wjsj,
						cfid:cfid
					}, function(data) {
						if(data ==null || data){
							showAlert("该学生在"+wjsj+"的违纪已在处分结果中存在！");
							return false;
						}else{
							// 提交
							showConfirmDivLayer("您确定要提交选择的记录吗？",{"okFun":function(){
								jQuery.post("wjcf_cfsbgl.do?method=submitCfsb",
									{values:ids.toString(),
									 xh : rows[0]['xh']
									},function(data){
									showAlertDivLayer(data["message"]);
									jQuery("#dataTable").reloadGrid();
								},'json');
							}});
						}
					},"json");
					
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
						if(rows[i]['shjg']!='审核中'){
							showAlertDivLayer("只有审核中的记录才能被撤销！");
							return false;
						}
					}
					showConfirmDivLayer("您确定要撤销选择的记录吗？",{"okFun":function(){
						jQuery.post("wjcf_cfsbgl.do?method=cancelCfsb",
							{
							 values:ids.toString(),
							 splcid : rows[0]['splcid'] 
							},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
				
			}
			// 通用打印方法，后台xxdm判断
			function getWordWjcfComman(type){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("请选择您要下载的记录！");
				 } else if (rows.length > 1){
					if(type == 'wjcftzs'){ 
						var flag = false;
						for(var i = 0; i < rows.length; i++){
							if(rows[i]["sbjg"] != '1'){
								 flag = true;
							 }
						}
						if(flag){
							showAlertDivLayer("请选择审核通过的记录！");
							return false;
						}
					}
					
					var ids = jQuery("#dataTable").getSeletIds();
					var url="wjcf_cfsbgl.do?method=getDjbZip&value="+ids+"&type="+type;
					window.open(url);
				 } else {
					 if(type == 'wjcftzs' && rows[0]["sbjg"] != '1'){
						 showAlertDivLayer("请选择审核通过的记录！");
						 return false;
					 }
					var url="wjcf_cfsbgl.do?method=getDjbWord&cfid="+rows[0]["cfid"]+"&type="+type;
					window.open(url);
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
		<html:form action="/wjcf_cfsbgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
						<li><a href="javascript:void(0);" onclick="add()" class="btn_zj">增加</a></li>
						<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
						<li><a href="javascript:void(0);" onclick="del()" class="btn_sc">删除</a></li>	
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">提交</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">撤销</a>
						</li>
						</logic:equal>
						<li><a href="javascript:void(0);" id="btn_cs" onclick="lcgz()" class="btn_cs">流程跟踪</a></li>	
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>				
						<!-- 南通工贸技师学院 begin -->
						<logic:equal name="xxdm" value="5002">	
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcfsq');return false;" class="btn_down">下载处分申报表</a></li>
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcftzs');return false;" class="btn_down">下载处分通知书</a></li>
						</logic:equal>
						<!-- 南通工贸技师学院 end -->
						<!-- 黑龙江农垦职业学院 begin -->
						<logic:equal name="xxdm" value="12727">	
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcfsq');return false;" class="btn_down">下载违纪处分审批表</a></li>
						</logic:equal>
						<!-- 黑龙江农垦职业学院 end -->
						<!-- 徐州医药高等职业学校 begin -->
						<logic:equal name="xxdm"  value="70002">
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcfsq');return false;" class="btn_down">下载违纪处分审批表</a></li>
						</logic:equal>
						<!-- 徐州医药高等职业学校 end -->
						<!-- 广西职业技术学院 begin -->
						<logic:equal name="xxdm"  value="11773">
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcfsq');return false;" class="btn_down">下载违纪处分审批表</a></li>
						</logic:equal>
						<!-- 广西职业技术学院end -->
						<!-- 成都体育学院begin -->
						<logic:equal name="xxdm" value="10653">	
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcfsq');return false;" class="btn_down">纪律处理登记表</a></li>
						</logic:equal>	
						<!-- 成都体育学院end -->
						<!-- 四川职业技术学院begin -->
						<logic:equal name="xxdm" value="12970">	
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcfsq');return false;" class="btn_down">纪律处理登记表</a></li>
						</logic:equal>	
						<!-- 四川职业技术学院end -->	
						<!-- 北京经济管理职业学院begin -->
						<logic:equal name="xxdm" value="14073">	
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcfsq');return false;" class="btn_down">违纪处分登记表</a></li>
						</logic:equal>	
						<!-- 北京经济管理职业学院end -->
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
				<span> 违纪信息列表 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
