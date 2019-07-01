<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			var gridSetting1 = {
					caption:"岗位选择列表",
					radioselect:true,
					pager:"pager",
					url:"qgzx_wdgwsq.do?method=wdgwsqjlCx&type=query",
					colList:[
                        {label:'申请编号',name:'sqbh', index: 'sqbh',width:'10%',hidden:true,key:true},
					   {label:'岗位代码',name:'gwdm', index: 'gwdm',hidden:true},
                        {label:'岗位名称',name:'gwmc', index: 'gwmc',width:'10%'},
					   {label:'用人单位',name:'yrdwmc', index: 'yrdwmc',width:'15%'},
					   {label:'单位类别',name:'dwlb', index: 'dwlb',width:'8%',formatter:function(value,row){
					       if(value=="01"){
					           return "校内单位";
						   } else {
                               return "校外单位";
						   }
					   }},
                        {label:'申请时间',name:'sqsj', index: 'sqsj',width:'12%'},
					   {label:'招聘人数',name:'xqrs', index: 'xqrs',width:'8%'},
					   {label:'已录用人数',name:'ylyrs', index: 'ylyrs',width:'8%'},
					   {label:'申请状态',name:'shztmc', index: 'shztmc',width:'8%'},
                        {label:'附件id',name:'fid', index: 'fid',width:'10%',hidden:true},
                        {label:'审批状态',name:'shzt', index: 'shzt',width:'5%',hidden:true},
                        {label:'审批流程',name:'splc', index: 'splc',hidden:true}
					],
					//dblclick:function(rowObject){
						//选择岗位
					//	xzGw(rowObject);
					//},
					sortname: "sqsj",
				 	sortorder: "desc",
				 	/*checkboxFormatter:function(rowObj){
						var check = rowObj['sfksq'];
						return check == 'N' ? false : true;
						}*/
					};

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting1);
				/*var isopen = jQuery("#xssqkg").val();
				if(isopen==null||isopen==''){
					jQuery("#prompt_isopen").show();
					jQuery("#prompt_null_isopen").show();
					return false;
				}
				if ("false" == isopen){
					jQuery("#prompt_isopen").show();
					jQuery("#prompt_false_isopen").show();
					return false;
				}*/
			});

			/**
			 * 流程跟踪
			 * @return
			 */
			function viewLcgz(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer("请选择一条记录！");
				} else {
					if(rows[0]["shzt"]=="0"){
						showAlertDivLayer(jQuery("#lable_wxglcxx").val());
						return false;
					}
					showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqbh']+"&splc="+rows[0]['splc']);
				}
			}	

			//撤销
			function cxSqb(){
				/*var isopen = jQuery("#xssqkg").val();
				if(isopen==null||isopen==''){
					showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
					return false;
				}
				if ("false" == isopen){
					showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
					return false;
				}*/
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
					
					showConfirmDivLayer("您确定要撤销选择的记录吗？", {
						"okFun" : function() {
						
							jQuery.post("qgzx_wdgwsq.do?method=cancle", {
								values : ids.toString(),
								lcid : rows[0]['splc']
							}, function(data) {
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							}, 'json');
							
						}
					});
				}
			}


			//提交
			function submitBusi(){
				/*var isopen = jQuery("#xssqkg").val();
				if(isopen==null||isopen==''){
					showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
					return false;
				}*/
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length != 1){
					/*if ("false" == isopen){
						showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
						return false;
					}*/
					showAlertDivLayer("请选择一条您要提交的记录！");
				}else{
					var rows = jQuery("#dataTable").getSeletRow();
					/*if ('3'!=rows[0]["shzt"] && "false" == isopen){
						showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
						return false;
					}*/
					var xh = jQuery('#userName').val();
					for(var i=0;i<ids.length;i++){
						if(rows[i]['shzt']!='0'&&rows[i]['shzt']!='3'){
							showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
							return false;
						}
					}
					showConfirmDivLayer("您确定要提交选择的记录吗？", {
						"okFun" : function() {
							/*jQuery.post("qgzx_wdgwsq.do?method=yzgwsq",{xh:xh,gwdm:rows[0]["gwdm"]},function(data){
								var message = data["message"];
								if(message=="true"){*/
									jQuery.post("qgzx_wdgwsq.do"+"?method=subBusi", {
										values : rows[0]['sqbh'],
										lcid : rows[0]['splc'],
										xh :xh,
										gwdm:rows[0]["gwdm"]
									}, function(data) {
										showAlertDivLayer(data["message"]);
										jQuery("#dataTable").reloadGrid();
									}, 'json');
							/*	}else{
									showAlertDivLayer(message);
								}
							},'json');*/
						}
					});
				}
				
			}

			
			/**
			 * 删除申请
			 */
			function delSqb(){
				var rows = jQuery("#dataTable").getSeletRow();
				var ids = [];
				for ( var i = 0; i < rows.length; i++) {
					ids.push(rows[i]['sqbh'])
				}
				if (ids.length == 0){
					showAlertDivLayer("请选择您要取消申请的记录！");
				} else if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
					showAlertDivLayer(jQuery("#lable_wjt_sc").val());
					return false;
				}else {
					showConfirmDivLayer("您确定要取消该申请吗？",{"okFun":function(){
						jQuery.post("qgzx_wdgwsq.do?method=del",{values:ids.toString()},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}
			
			function xzxys(){
                var rows = jQuery("#dataTable").getSeletRow();
                if(rows.length != 1){
                    showAlertDivLayer("请选择一条记录！");
                    return false;
				}
                window.open('common_upload.do?method=asyncDownload&fid='+rows[0]["fid"]);
			}
		</script>
	</head>

	<body>
		<input type="hidden" name="curXn" id="curXn" value="${curXn}"/>
		<input type="hidden" name="isQgzxStu" id="isQgzxStu" value="${isQgzxStu}"/>
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
		
		<input type="hidden" id="xssqkg" value="${cssz.xssqkg }" />
		<html:form action="/qgzx_wdgwsq" method="post" styleId="wdgwsqForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
					<div class="buttonbox">
						<ul>
							<logic:equal name="writeAble" value="yes">
							<logic:equal name="sfkfsq" value="1">
								<li><a href="javascript:void(0);" id="btn_xzxys" class="btn_xg" onclick="xzxys();return false;">下载协议书</a></li>
								<li><a href="javascript:void(0);" id="btn_sc" onclick="delSqb();return false;" class="btn_sc">删除</a></li>
								<li><a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">提交</a></li>
								<li><a href="javascript:void(0);" id="btn_cx" onclick="cxSqb();return false;" class="btn_sr">撤销</a></li>
								<li><a href="javascript:void(0);" id="btn_cs" onclick="viewLcgz();return false;" class="btn_cs">流程跟踪</a></li>
							</logic:equal>
							</logic:equal>
						</ul>

					</div>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchAreaStu.jsp"%>
				<!-- 过滤条件 end-->

			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>岗位申请信息</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>

		</div>
	</body>
</html>
