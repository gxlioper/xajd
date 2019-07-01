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
		<script type="text/javascript" src="xsgzgl/wjcf/cfsbglnew/cfsh/js/cfsh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
		<script type="text/javascript">
		if("13011" == ${xxdm}){
			var gridSetting = {
					caption:"违纪处分审核列表",
					pager:"pager",
					url:"wjcf_cfsh.do?method=cxCfshList&type=query",
					colList:[
							   {label:'学号',name:'xh', index: 'xh'},//,formatter:xhLink
							   {label:'姓名',name:'xm', index: 'xm'},
							   {label:'专业',name:'zymc', index: 'zymc'},
							   {label:'学年',name:'xn', index: 'xn'},
							   {label:'学期',name:'xqmc', index: 'xqmc'},
							   {label:'处分类别',name:'cflbmc', index: 'cflbmc'},
							   {label:'处分原因',name:'cfyymc', index: 'cfyymc'},
							   {label:'处分时间',name:'cfsj', index: 'cfsj'},
							   {label:'审核状态',name:'shzt', index: 'shzt'},
							   {label:'ywid',name:'ywid', index: 'ywid',hidden:true},
							   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},
							   {label:'kzzd1',name:'kzzd1', index: 'kzzd1',hidden:true},
							   {label:'cffwqx',name:'cffwqx', index: 'cffwqx',hidden:true},
							   {label:'gwid',name:'gwid', index: 'gwid',hidden:true},
							   {label:'shid',name:'shid', index: 'shid',hidden:true},
							   {label:'cflbdm',name:'cflbdm', index: 'cflbdm',hidden:true}
					],
					params:{shlx:"dsh"}
			}
		}else{
			var gridSetting = {
					caption:"违纪处分审核列表",
					pager:"pager",
					url:"wjcf_cfsh.do?method=cxCfshList&type=query",
					colList:[
							   {label:'学号',name:'xh', index: 'xh'},//,formatter:xhLink
							   {label:'姓名',name:'xm', index: 'xm'},
                        		{label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'15%'},
                        		{label:'书院',name:'symc', index: 'symc',width:'15%'},
							   {label:'学年',name:'xn', index: 'xn'},
							   {label:'学期',name:'xqmc', index: 'xqmc'},
							   {label:'处分类别',name:'cflbmc', index: 'cflbmc'},
							   {label:'处分原因',name:'cfyymc', index: 'cfyymc'},
							   {label:'处分时间',name:'cfsj', index: 'cfsj'},
							   {label:'审核状态',name:'shzt', index: 'shzt'},
							   {label:'ywid',name:'ywid', index: 'ywid',hidden:true},
							   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},
							   {label:'kzzd1',name:'kzzd1', index: 'kzzd1',hidden:true},
							   {label:'cffwqx',name:'cffwqx', index: 'cffwqx',hidden:true},
							   {label:'gwid',name:'gwid', index: 'gwid',hidden:true},
							   {label:'shid',name:'shid', index: 'shid',hidden:true},
							   {label:'cflbdm',name:'cflbdm', index: 'cflbdm',hidden:true}
					],
					params:{shlx:"dsh"}
			}
		}
		if("13011" == ${xxdm}){
			var gridSetting2 = {
					caption:"违纪处分审核列表",
					pager:"pager",
					url:"wjcf_cfsh.do?method=cxCfshList&type=query",
					colList:[
							   {label:'学号',name:'xh', index: 'xh'},//,formatter:xhLink
							   {label:'姓名',name:'xm', index: 'xm'},
							   {label:'专业',name:'zymc', index: 'zymc'},
							   {label:'学年',name:'xn', index: 'xn'},
							   {label:'学期',name:'xqmc', index: 'xqmc'},
							   {label:'处分类别',name:'cflbmc', index: 'cflbmc'},
							   {label:'处分原因',name:'cfyymc', index: 'cfyymc'},
							   {label:'处分时间',name:'cfsj', index: 'cfsj'},
							   {label:'审核状态',name:'shzt', index: 'shzt'},
                        	   {label:'审核时间',name:'shsj', index: 'shsj'},
							   {label:'ywid',name:'ywid', index: 'ywid',hidden:true},
							   {label:'kzzd1',name:'kzzd1', index: 'kzzd1',hidden:true},
							   {label:'cffwqx',name:'cffwqx', index: 'cffwqx',hidden:true},
							   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},
							   {label:'gwid',name:'gwid', index: 'gwid',hidden:true},
							   {label:'shid',name:'shid', index: 'shid',hidden:true},
							   {label:'cflbdm',name:'cflbdm', index: 'cflbdm',hidden:true}
					],
                sortname : "shsj",
                sortorder : "desc",
                params:{shlx:"ysh"}
			}
		}else{
			var gridSetting2 = {
					caption:"违纪处分审核列表",
					pager:"pager",
					url:"wjcf_cfsh.do?method=cxCfshList&type=query",
					colList:[
							   {label:'学号',name:'xh', index: 'xh'},//,formatter:xhLink
							   {label:'姓名',name:'xm', index: 'xm'},
							   {label:'学年',name:'xn', index: 'xn'},
							   {label:'学期',name:'xqmc', index: 'xqmc'},
                        		{label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'15%'},
                        		{label:'书院',name:'symc', index: 'symc',width:'15%'},
							   {label:'处分类别',name:'cflbmc', index: 'cflbmc'},
							   {label:'处分原因',name:'cfyymc', index: 'cfyymc'},
							   {label:'处分时间',name:'cfsj', index: 'cfsj'},
							   {label:'审核状态',name:'shzt', index: 'shzt'},
							   {label:'ywid',name:'ywid', index: 'ywid',hidden:true},
							   {label:'kzzd1',name:'kzzd1', index: 'kzzd1',hidden:true},
							   {label:'cffwqx',name:'cffwqx', index: 'cffwqx',hidden:true},
							   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},
							   {label:'gwid',name:'gwid', index: 'gwid',hidden:true},
							   {label:'shid',name:'shid', index: 'shid',hidden:true},
							   {label:'cflbdm',name:'cflbdm', index: 'cflbdm',hidden:true}
					],
					params:{shlx:"ysh"}
			}
		}
			/*
			 * 撤销
			 */
			function cxshnew(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					alertInfo("请选择一条您要撤销审核的记录！");
				} else {
					splc_cx_new(rows[0]["splcid"],rows[0]["shid"],rows[0]["kzzd1"],rows[0]["cflbdm"]);
				}
			}

			/*
			 * 审批流程撤销[最后一级可撤销]
			 * shid 审核id
			 * splc 审批流程id 
			 */
			function splc_cx_new(splc,shid,kzzd1,cflbdm){
				//最后一级撤销审核后调用的路径
				var cancelPath = jQuery("#cancelPath").val();
				confirmInfo("您确定要撤销操作吗?",function(ty){
					if(ty=="ok"){
						//判断是否可撤销
						jQuery.post("wjcf_cfsh.do?method=canCancel",{splcid:splc,shid:shid},function(data){
							//可撤销
							if(data["message"]==""){
								jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
										// 判断是否最后一级撤销(1:最后一级撤销成功）
										// 判断是否处分类别代码是否有改动（由于现在权限岗位可以修改处分类别，撤销应该还原到上一次）
										if("1" == data["cancelFlg"]||kzzd1!=cflbdm){
											jQuery.post(cancelPath,{splc:splc,shid:shid,kzzd1:kzzd1},function(result){
												showAlertDivLayer(result["message"],{},{"clkFun":function(){
													jQuery("#dataTable").reloadGrid();
												}});
											},'json');
										}else{
											showAlertDivLayer(data["message"],{},{"clkFun":function(){
												jQuery("#dataTable").reloadGrid();
											}});
										}
									
								},'json');
							}else{
								//不可撤销
								showAlertDivLayer(data["message"],{},{"clkFun":function(){
									jQuery("#dataTable").reloadGrid();
								}});
							}
						},'json');
					}
				});
			}
			function showView() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length == 1) {
					var ywid = rows[0]["ywid"];
					var url = "wjcf_cfsh.do?method=cfshCk&ywid=";
					url += ywid;
					showDialog("查看审核信息", 820, 500, url);
				} else {
					showAlertDivLayer("请勾选一条需要查看的记录！");
					return false;
				}
			}
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
				if("12930" == jQuery("#xxdm").val()){
					jQuery("#btn_sh").click(go_sh_12930);	
				}else{
					jQuery("#btn_sh").click(go_sh);
				}
				jQuery("#btn_cs").click(lcgz);
				jQuery("#btn_qxsh").click(cxshnew);
				jQuery("#btn_qxsh").hide();
			});
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
				<input type="hidden" name="shlx" id="shlx" value="dsh"/>
			</p>
		</div>
		<html:form action="/szdw_zwsh.do">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="cancelPath" id="cancelPath" value="${cancelPath}"/>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
						<li><a href="javascript:void(0);" id="btn_sh" class="btn_sh">审核</a>
						<a href="javascript:void(0);" id="btn_qxsh" class="btn_qxsh">撤销</a>
						</li>
						</logic:equal>
						<li><a href="javascript:void(0);" id="btn_cs" class="btn_cs">流程跟踪</a></li>
						<li>
							<a href="#" onclick="showView(); return false;" class="btn_ck">查看</a>
						</li>
						<logic:equal value="yes" name="getCfjdWord">
						<li>
							<a href="#" onclick="getCfjdWord(); return false;" class="btn_down">处分决定书下载</a>
						</li>
						</logic:equal>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="query(this,'dsh');"><span>待处理</span></a></li>
			        <li><a href="javascript:void(0);" onclick="query(this,'ysh');"><span>已处理</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>处分上报审核列表</span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
