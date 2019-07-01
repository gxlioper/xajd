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
		<script type="text/javascript" src="xsgzgl/wjcf/cfjcglnew/cfjcsh/js/cfjcsh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
		<script type="text/javascript">

			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
				jQuery("#btn_sh").click(go_sh);
				jQuery("#btn_cs").click(lcgz);
				jQuery("#btn_qxsh").click(cxshnew);
				jQuery("#btn_qxsh").hide();
			});

			function cxshnew(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					alertInfo("请选择一条您要撤销审核的记录！");
				} else {
					splc_cx_new(rows[0]["splcid"],rows[0]["shid"],rows[0]["cfid"]);
				}
			}
			/*
			 * 审批流程撤销[最后一级可撤销]
			 * shid 审核id
			 * splc 审批流程id 
			 */
			function splc_cx_new(splc,shid,cfid){
				//最后一级撤销审核后调用的路径
				var cancelPath = jQuery("#cancelPath").val();
				confirmInfo("您确定要撤销操作吗?",function(ty){
					if(ty=="ok"){
						jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
								// 判断是否最后一级撤销(1:最后一级撤销成功）
								if("1" == data["cancelFlg"]){
									jQuery.post(cancelPath,{splcid:splc,shid:shid,cfid:cfid},function(result){
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
			function showView() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length == 1) {
					var ywid = rows[0]["ywid"];
					var url = 'wjcf_cfjcsh.do?method=jcshCk&ywid='+rows[0]["ywid"]+"&shid="+rows[0]["shid"]+"&gwid="+rows[0]["gwid"]+"&splcid="+rows[0]["splcid"]+"&cfid="+rows[0]["cfid"];
					showDialog("查看审核信息", 820, 500, url);
				} else {
					showAlertDivLayer("请勾选一条需要查看的记录！");
					return false;
				}
			}
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
				<input type="hidden" name="shlx" id="shlx" value="dsh"/>
				<input type="hidden" id="xbmc" value="解除" />
			</p>
		</div>
		<html:form action="/szdw_zwsh.do">
			<input type="hidden" id="text" value="<bean:message key="wjcf.text" />"/>
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
				<span>处分<bean:message key="wjcf.text" />审核列表</span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
