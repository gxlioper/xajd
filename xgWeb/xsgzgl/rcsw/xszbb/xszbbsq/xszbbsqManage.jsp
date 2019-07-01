<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xszbb/xszbbsq/js/xszbbsqManage.js"></script>
		<script type="text/javascript">
		function submitBusi(){
			
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
				return false;
			}
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length != 1){
				if ("false" == isopen){
					showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
					return false;
				}
				showAlertDivLayer("请选择一条您要提交的记录！");
			}else{
				var rows = jQuery("#dataTable").getSeletRow();
				if ('3'!=rows[0]["shzt"] && "false" == isopen){
					showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
					return false;
				}
				var url = "";
				for(var i=0;i<ids.length;i++){
					if(rows[i]["shzt"] != "0" && rows[i]["shzt"] != "3" ){
						showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
						return false;
					}
					if(rows[i]['shzt']!='3'){
						url = "rcsw_xszbb_bbsqgl.do?method=submitXszbbsq&returnflag=back";
					}else{
						url = "rcsw_xszbb_bbsqgl.do?method=submitXszbbsq";
					}
					
				}

				showConfirmDivLayer("您确定要提交选择的记录吗？",{"okFun":function(){
					jQuery.post(url,
						{values:ids.toString(),
						 xh : rows[0]['xh'],
						 splc : rows[0]["splc"],
						 shzt : rows[0]["shzt"],
						 xszbblxdm : rows[0]["xszbblxdm"]
						},function(data){
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
				}});
			}

		}

		function cancel(){
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
				return false;
			}
			if ("false" == isopen){
				showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
				return false;
			}
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length == 0) {
				showAlertDivLayer("请选择您要撤销的记录！");
			} else if (ids.length >1 ) {
				showAlertDivLayer("请选择一条您要撤销的记录！");
			} else {
				var rows = jQuery("#dataTable").getSeletRow();
				for(var i=0;i<ids.length;i++){
					if(rows[i]['shzt']!='5'){
						showAlertDivLayer("只有审核中的记录才能被撤销！");
						return false;
					}
				}
				
				showConfirmDivLayer("您确定要撤销选择的记录吗？",{"okFun":function(){
					jQuery.post("rcsw_xszbb_bbsqgl.do?method=cancelXszbbsq",
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

		function xszbbLcinfo(){
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows == null || rows.length <= 0) {
				showAlertDivLayer("请选择一条流程跟踪记录！");
				return false;
			}
			var shzt = rows[0]["shzt"];
			if (1!=ids.length){
				showAlertDivLayer("请选择一条流程跟踪记录！");
			} else {	
				if ("0" == shzt){
					showAlertDivLayer(jQuery("#lable_wxglcxx").val());
					return false;
				}	
				showDialog("证件补办审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['bbsqid']+"&splc="+rows[0]['splc']);
			}
		}
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<div class="prompt" id="prompt_isopen" style="display:none;">
			<h3>
				<span>提示：</span>
			</h3>
			<p id="prompt_null_isopen" style="display:none;">
				<bean:message key="lable.jcszwcsh_prompt" />
			</p>
			<p id="prompt_false_isopen" style="display:none;">
				<bean:message key="lable.dqwkfsq_prompt" />
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<html:form action="/rcsw_rcxwwh_rcxwxxwhgl">
			<input type="hidden" name="tableName" id="tableName" value="xg_rcsw_rcxwxxwh"/>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >申请</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">提交</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">撤销</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="xszbbLcinfo();return false;" 
								   title="选中一条记录，点击该按钮可以查看审核流程。"
								   class="btn_cs">流程跟踪</a>
						</li>	
						<!-- 读写权 -->
						<logic:equal name="writeAble" value="yes">					
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
						</logic:equal>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>证件补办申请信息维护列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
