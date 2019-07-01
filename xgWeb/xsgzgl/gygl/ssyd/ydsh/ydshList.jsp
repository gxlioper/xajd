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
		<script type="text/javascript" src="xsgzgl/gygl/ssyd/js/ydsh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
		<script type="text/javascript">
		//初始化查询
		function getDclGird(){
			return {
				caption:"宿舍异动审核列表",
				pager:"pager",
				url:"ydsh.do?method=list&type=query",
				colList:[
						   {label:'学号',name:'xh', index: 'xh'},//,formatter:xhLink
						   {label:'姓名',name:'xm', index: 'xm'},
						   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'},
						   {label:'性别',name:'xb', index: 'xb'},
						   {label:'寝室',name:'ssxx', index: 'ssxx'},
						   {label:'异动类型',name:'ssydlxmc', index: 'ssydlxmc'},
						   {label:'申请时间',name:'sqsj', index: 'sqsj'},
						   {label:'审核状态',name:'shzt', index: 'shzt'},
						   {label:'shid',name:'shid', index: 'shid',hidden:true},
						   {label:'gwid',name:'gwid', index: 'gwid',hidden:true},
						   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},	
						   {label:'异动类型代码',name:'ssydlx', index: 'ssydlx',hidden:true},
						   {label:'最新床位信息',name:'sqshHideCwxx', index: 'sqshHideCwxx',hidden:true},
						   {label:'ssydsqid',name:'ssydsqid', index: 'ssydsqid',key:true,hidden:true}
				],
				params:{shlx:"dsh"},
				sortname: "sqsj",
			 	sortorder: "asc",
			 	radioselect:false
			}
		}
		function getYclGrid(){
			return {
				caption:"宿舍异动审核列表",
				pager:"pager",
				url:"ydsh.do?method=list&type=query",
				colList:[
						   {label:'学号',name:'xh', index: 'xh'},//,formatter:xhLink
						   {label:'姓名',name:'xm', index: 'xm'},
						   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'},
						   {label:'性别',name:'xb', index: 'xb'},
						   {label:'寝室',name:'ssxx', index: 'ssxx'},
						   {label:'异动类型',name:'ssydlxmc', index: 'ssydlxmc'},
						   {label:'申请时间',name:'sqsj', index: 'sqsj'},
						   {label:'审核状态',name:'shzt', index: 'shzt'},
						   {label:'shid',name:'shid', index: 'shid',hidden:true},
						   {label:'gwid',name:'gwid', index: 'gwid',hidden:true},
						   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},	
						   {label:'异动类型代码',name:'ssydlx', index: 'ssydlx',hidden:true},
						   {label:'ssydsqid',name:'ssydsqid', index: 'ssydsqid',key:true,hidden:true}
				],
				params:{shlx:"ysh"},
				sortname: "shsj",
			 	sortorder: "desc",
			 	radioselect:true
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
					splc_cx_new(rows[0]["splcid"],rows[0]["shid"]);
				}
			}
			/*
			 * 审批流程撤销
			 * shid 审核id
			 * splc 审批流程id 
			 */
			function splc_cx_new(splc,shid){
				//最后一级撤销审核后调用的路径
				var cancelPath = jQuery("#cancelPath").val();
				confirmInfo("您确定要撤销操作吗?",function(ty){
					if(ty=="ok"){
						jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
								// 判断是否最后一级撤销(1:最后一级撤销成功）
								if("1" == data["cancelFlg"]){
									jQuery.post(cancelPath,{splcid:splc,shid:shid},function(result){
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
					}
				});
			}
			
			/*
			 * 审批流程撤销
			 * shid 审核id
			 * splc 审批流程id 
			 */
			function splc_cx(splcid,shid){
				confirmInfo("您确定要撤销操作吗?",function(ty){
					if(ty=="ok"){
						jQuery.post("ydsh.do?method=cxsh",{splcid:splcid,shid:shid},function(data){
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
								jQuery("#dataTable").reloadGrid();
							}});
						},'json');
					}
				});
			}
			/*
			 * 撤销[最后一级不可撤销]
			 */
			function cxsh(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					alertInfo("请选择一条您要审核的记录！");
				} else {
					splc_cx(rows[0]["splcid"],rows[0]["shid"]);
				}
			}
			jQuery(function(){
				var dclGrid = getDclGird();
				var map = getSuperSearch();
					map["shlx"] = "dsh";
					dclGrid["params"]=map;
				jQuery("#dataTable").initGrid(dclGrid);
				
				jQuery("#btn_sh").click(go_sh);
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
							<a href="javascript:void(0);" id="btn_qxsh" class="btn_sr">撤销</a>
							</li>
						</logic:equal>
						<li><a href="javascript:void(0);" id="btn_cs" class="btn_cs">流程跟踪</a></li>
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
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>宿舍异动审核列表</span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
