<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/knsrdnew/knsrdsq/js/knsrdsqManage.js"></script>
		<script type="text/javascript">
		jQuery(function(){

			var gridSetting = {
					caption:"困难生认定申请列表",
					pager:"pager",
					url:"xg_xszz_knsrd_knsqgl.do?method=knsrdsqManage&type=query",
					colList:[
					   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
					   {label:'zbid',name:'zbid', index: 'zbid',hidden:true},
					   {label:'学号',name:'xh', index: 'xh',width:'11%',formatter:xhLink},
					   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
					   {label:'性别',name:'xb', index: 'xb',width:'5%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'13%'},
					   {label:'splc',name:'splc', index: 'splc',hidden:true},
					   {label:'班级',name:'bjmc', index: 'bjdm',width:'13%'},
					   {label:'学年',name:'xn', index: 'xn',width:'10%'},
					   {label:'学期',name:'xqmc', index: 'xq',width:'6%'},
					   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'9%'},
					   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
					   {label:'审核状态',name:'shztmc', index: 'shzt',width:'6%'},
					   {label:'splc',name:'splc', index: 'splc',hidden:true}
					   
					],
					sortname: "sqsj",
				 	sortorder: "desc"
				};
				
			jQuery("#dataTable").initGrid(gridSetting);

			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				jQuery("#prompt_isopen").show();
				jQuery("#prompt_null_isopen").show();
				return false;
			}
			if ("false" == isopen){
				jQuery("#prompt_isopen").show();
				jQuery("#prompt_false_isopen").show();
				return false;
			}
		});

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
				showAlertDivLayer(jQuery("#lable_one_tj").val());
			}else{
				var rows = jQuery("#dataTable").getSeletRow();
				if ('3'!=rows[0]["shzt"] && "false" == isopen){
					showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
					return false;
				}
				
				var url = "";
				for(var i=0;i<ids.length;i++){
					if(rows[i]['shzt']!='0' && rows[i]['shzt']!='3' ){
						showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
						return false;
					}
				   url = "xg_xszz_knsrd_knsqgl.do?method=submitKnsrdsq";
				}
				showConfirmDivLayer("您确定要提交选择的记录吗？",{"okFun":function(){
					jQuery.post(url,
						{values:ids.toString(),
						 xh : rows[0]['xh'], splc : rows[0]['splc']  
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
					jQuery.post("xg_xszz_knsrd_knsqgl.do?method=cancelKnsrdsq",
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

		function knsrdsqLcinfo(){
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			if (ids.length != 1){
				showAlertDivLayer("请选择一条流程跟踪记录！");
			} else {	
				var shzt = rows[0]["shzt"];
				if ("0" == shzt){
					showAlertDivLayer(jQuery("#lable_wxglcxx").val());
					return false;
				}	
				showDialog("困难生认定申请审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
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
		
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					<logic:equal value="yes" name="writeAble">
						<li>
							<a href="javascript:void(0);" class="btn_zj"
							   onclick="add();return false;" 
							>申请</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg"
							>修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc"
							>删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">提交</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">撤销</a>
						</li>
					</logic:equal>	
						<li>
							<a href="javascript:void(0);" onclick="knsrdsqLcinfo();return false;" 
								   title="选中一条记录，点击该按钮可以查看审核流程。"
								   class="btn_cs">流程跟踪</a>
						</li>
										
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
							
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>困难生认定申请信息维护列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
