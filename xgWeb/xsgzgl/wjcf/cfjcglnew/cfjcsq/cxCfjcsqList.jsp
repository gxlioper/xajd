<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/wjcf/cfjcglnew/cfjcsq/js/cfjcsq.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"处分<bean:message key="wjcf.text" />信息列表",
				pager:"pager",
				url:"wjcf_cfjcsq.do?method=cxCfjcsqList&type=query",
				colList:[
				   {label:'jcid',name:'jcid', index: 'jcid',hidden:true,key:true},
				   {label:'cfid',name:'cfid', index: 'cfid',hidden:true},
				   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},
				   {label:'学号',name:'xh', index: 'xh'},
				   {label:'姓名',name:'xm', index: 'xm'},
                    {label:'专业班级',name:'zybjmc', index: 'zybjmc'},
                    {label:'书院',name:'symc', index: 'symc'},
				   {label:'学年',name:'xn', index: 'xn'},
				   {label:'学期',name:'xqmc', index: 'xqmc'},
				   {label:'处分类别',name:'cflbmc', index: 'cflbmc'},
				   {label:'处分原因',name:'cfyymc', index: 'cfyymc'},
				   {label:'<bean:message key="wjcf.text" />文号',name:'jcwh', index: 'jcwh'},
				   {label:'<bean:message key="wjcf.text" />时间',name:'jcsj', index: 'jcsj'},
				   {label:'<bean:message key="wjcf.text" />状态',name:'shztmc', index: 'shztmc'},
				   {label:'shzt',name:'shzt', index: 'shzt',hidden:true}
				],
				sortname: "xh",
			 	sortorder: "asc"
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

			function cancel(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0) {
					showAlertDivLayer("请选择您要撤销的记录！");
				} else if (ids.length >1 ) {
					showAlertDivLayer("请选择一条您要撤销的记录！");
				} else {
					var rows = jQuery("#dataTable").getSeletRow();
					for(var i=0;i<ids.length;i++){
						if(rows[i]['shztmc']!='审核中'){
							showAlertDivLayer("只有审核中的记录才能被撤销！");
							return false;
						}
					}
					showConfirmDivLayer("您确定要撤销选择的记录吗？",{"okFun":function(){
						jQuery.post("wjcf_cfjcsq.do?method=cancelCfjcsq",
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

			function submitBusi(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length != 1) {
					showAlertDivLayer("请选择一条您要提交的记录！");
				} else {
					var rows = jQuery("#dataTable").getSeletRow();
					for(var i=0;i<ids.length;i++){
						if(rows[i]['shztmc']!='未提交' && rows[i]['shztmc']!='退回' ){
							showAlertDivLayer("请选择未提交或者退回的记录！");
							return false;
						}
					}

					showConfirmDivLayer("您确定要提交选择的记录吗？",{"okFun":function(){
						jQuery.post("wjcf_cfjcsq.do?method=submitCfjcsq",
							{values:ids.toString(),
							 xh : rows[0]['xh']
							},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}
			
			function printJccfwjb() {
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				var len = ids.length;
				if (len == 1) {
					var url = "wjcf_cfjcsq.do?method=getJccfwjb";
					url += "&xh=" + ids+"&cfid="+rows[0]["cfid"];
					window.open(url);
				} else {
					showAlertDivLayer("请<font color='blue'>勾选一条</font>您希望下载的记录！");
					return false;
				}
			}
			// 通用打印方法，后台xxdm判断
			function getWordWjcfComman(type){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("请选择您要下载的记录！");
				 } else if (rows.length > 1){
					var flag = false;
					var ids = "";
					for(var i = 0; i < rows.length; i++){
						ids +=  rows[i]["cfid"];
						if(i < rows.length - 1){
							ids += ",";
						}
						if(rows[i]["shzt"] != '1'){
							 flag = true;
						 }
					}
					if(type == 'cfjctzs' && flag){
						showAlertDivLayer("请选择审核通过的记录！");
						return false;
					}
					
					var url="wjcf_cfsbgl.do?method=getDjbZip&value="+ids+"&type="+type;
					window.open(url);
				 } else {
					 if(type == 'cfjctzs' && rows[0]["shzt"] != '1'){
						 showAlertDivLayer("请选择审核通过的记录！");
						 return false;
					 }
					var url="wjcf_cfsbgl.do?method=getDjbWord&cfid="+rows[0]["cfid"]+"&type="+type;
					window.open(url);
			     }
			}

            function getCfjcWord() {
                var rows = jQuery("#dataTable").getSeletRow();

                if (rows.length == 0) {
                    showAlertDivLayer("请选择您要下载的记录！");
                } else if (rows.length > 1) {
                    var flag = false;
                    var ids = "";
                    var xhs = "";
                    var jcids="";
                    for (var i = 0; i < rows.length; i++) {
                        ids += rows[i]["cfid"];
                        xhs += rows[i]["xh"];
                        jcids += rows[i]["jcid"];
                        if (i < rows.length - 1) {
                            ids += ",";
                            xhs += ",";
                            jcids += ",";
                        }
                        if (rows[i]["shzt"] != '1') {
                            flag = true;
                        }
                    }
                    if (flag) {
                        showAlertDivLayer("请选择审核通过的记录！");
                        return false;
                    }

                    var url = "wjcf_cfsbgl.do?method=getCfjcZip&value="+ids+"&xhs="+xhs+"&jcids="+jcids;
                    window.open(url);
                } else {
                    if (rows[0]["shzt"] != '1') {
                        showAlertDivLayer("请选择审核通过的记录！");
                        return false;
                    }
                    var url = "wjcf_cfsbgl.do?method=getCfjcWord&cfid=" + rows[0]["cfid"] + "&xh=" + rows[0]["xh"]+"&jcid="+rows[0]["jcid"];
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
		<html:form action="/wjcf_cfsssq">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="text" value="<bean:message key="wjcf.text" />"/>
			<input type="hidden" id="xbmc" value="解除" />
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
						<li><a href="javascript:void(0);" onclick="add()" class="btn_zj"><bean:message key="wjcf.text" />处分申请</a></li>
						<li><a href="javascript:void(0);" onclick="update();" class="btn_xg"><bean:message key="wjcf.text" />处分修改</a></li>
						<li><a href="javascript:void(0);" onclick="del()" class="btn_sc">删除</a></li>
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">撤销</a>
						</li>	
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_sr">提交</a>
						</li>
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="lcgz()" id="btn_cs" class="btn_cs">流程跟踪</a></li>
									
						<logic:equal name="xxdm" value="10606">							
							<li>
								<a href="javascript:void(0);" onclick="printJccfwjb();return false;"
									class="btn_dy"><bean:message key="wjcf.text" />处分文件下载</a>
							</li>
						</logic:equal>
						<!-- 南通工贸技师学院 begin -->
						<logic:equal name="xxdm" value="5002">	
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('cfjcsq');return false;" class="btn_down">下载撤销处分申请表</a></li>
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('cfjctzs');return false;" class="btn_down">下载撤销处分通知书</a></li>
						</logic:equal>
						<!-- 南通工贸技师学院 end -->
						<!-- 黑龙江农垦职业学院 begin -->
						<logic:equal name="xxdm" value="12727">	
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('cfjcsq');return false;" class="btn_down">下载解除处分审批表</a></li>
						</logic:equal>
						<!-- 黑龙江农垦职业学院 end -->
						<!-- 重庆三峡医药高等专科学校 begin -->
						<logic:equal name="xxdm" value="14008">	
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('cfjcsq');return false;" class="btn_down">下载处分撤销申请表</a></li>
						</logic:equal>
						<!-- 重庆三峡医药高等专科学校 end -->
						<!-- 成都体育学院 begin -->
						<logic:equal name="xxdm" value="10653">	
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjjcsq');return false;" class="btn_down">下载处分撤销申请表</a></li>
						</logic:equal>
						<!-- 成都体育学院 end-->
						<!-- 徐州医药高等专科学校 begin -->
						<logic:equal value="70002" name="xxdm">
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('cfjgdy');return false;" class="btn_down">下载处分撤销申请表</a></li>
						</logic:equal>
						<!-- 徐州医药高等专科学校 end -->
						<!-- 河北民族师范学院 begin -->
						<logic:equal name="xxdm" value="10098">
							<li><a href="javascript:void(0);" onclick="getCfjcWord();return false;" class="btn_down">解除处分审批表下载</a></li>
						</logic:equal>
						<!-- 河北民族师范学院 end -->
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
				<span><bean:message key="wjcf.text" />信息列表 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
