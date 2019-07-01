<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">


		function limFn(cellVal , rowObj){
						if(cellVal == null)
							return "<font color='green'>无限制条件</font>";
						else{
							var html = "";
							for(i = 0 ; i < cellVal.length ; i++ ){
								var item = cellVal[i];
								var result = item.result;
								var msg = item.sqts;
								if(result == 'true'){
									var htmli = "<img src='images/ico_38.gif' title='符合条件' /> " + (i + 1) + "、" + msg + "<br/>";
									html += htmli;
								}else{
									var htmli = "<img src='images/ico_39.gif'name='faidImg'  title='不符合条件' /> " + (i + 1) + "、" + msg + "<br/>";
									html += htmli;
								}
							}
							return html;
						}

				}
			var gridSetting1 = {
					caption:"评奖项目申请列表 ",
					pager:"pager",
					url:"xpj_pjxmsq.do?method=viewPjxmsqList&type=query&queryType=wsq&xzdm=${xzdm}",
					colList:[
					   {label:'key',name:'xmdm', index: 'xmdm',hidden:true,key:true},
					   {label:'xmdm',name:'xmdm', index: 'xmdm',width:'13%',hidden:true,formatter:hideXmdm},
					   {label:'项目名称',name:'xmmc', index: 'xmmc',width:'15%',formatter:setXmsm},
					   {label:'项目说明',name:'xmsm', index: 'xmsm',width:'15%',hidden:true},
					   {label:'项目类别',name:'xmlx', index: 'xmlx',width:'15%'},
					   {label:'金额',name:'xmje', index: 'xmje',width:'10%'},
					   {label:'是否可选', name:'checkable', index: 'checkable',hidden:true},
					   {label:'限制条件',name:'conditionCheckResult', index: 'conditionCheckResult',width:'39%' , formatter:limFn},
					   {label:'申请开始时间',name:'sqkssj', index: 'sqkssj',hidden:true},
					   {label:'申请结束时间',name:'sqjssj', index: 'sqjssj',hidden:true},
					   {label:'开关备注',name:'kgbz', index: 'kgbz',hidden:true},
					   {label:'开关状态',name:'sqkg', index: 'sqkg',width:'10%',formatter:setSqkg},
					   {label:'申请状态',name:'shztmc', index: 'shztmc',width:'10%'},
					   {label:'sqid',name:'sqid', index: 'sqid',hidden:true},
					   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
					   {label:'splc',name:'splc', index: 'splc',hidden:true}
					],
					params:{shzt:"wsq"},//未申请
					sortname:"sqkg",
					sortorder:"desc",
					checkboxFormatter:function(rowObj){
						var check = rowObj['checkable'];
						return check == 'false' ? false : true;
						},
					radioselect:true
				};
			function hideXmdm(cellVal , rowObj){
				return "<input name='"+rowObj['xmdm']+"' value='"+rowObj['xmdm']+"' type='text' />";
			}
			var gridSetting2 = {
					caption:"评奖项目申请列表 ",
					pager:"pager",
					url:"xpj_pjxmsq.do?method=viewPjxmsqList&type=query&queryType=ysq&xzdm=${xzdm}",
					colList:[
					   {label:'key',name:'sqid', index: 'sqid',hidden:true,key:true},
					   {label:'xmdm',name:'xmdm', index: 'xmdm',width:'13%',hidden:true},
					   {label:'评奖周期',name:'pjzq', index: 'xn',width:'13%'},
					   {label:'申请奖项',name:'xmmc', index: 'xmdm',width:'18%',formatter:setXmsm},
					   {label:'项目说明',name:'xmsm', index: 'xmsm',width:'15%',hidden:true},
					   {label:'项目类别',name:'xmlxmc', index: 'xmlxmc',width:'15%'},
					   {label:'金额',name:'xmje', index: 'xmje',width:'10%'},
					   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'25%'},
					   {label:'最终获得奖项',name:'tzhxmmc',index:'tzhxmdm',width:'18%'},
					   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
					   {label:'splc',name:'splc', index: 'splc',hidden:true},
					   {label:'审核状态',name:'shztmc', index: 'shzt',width:'5%'}
					   //{label:'删除开关',name:'ff', index:'ff',hidden:true}
					],
					params:{shzt:"ysq"},//已申请
					sortname:"sqsj",
					sortorder:"desc",
					radioselect:true
				};

			/*
			 *申请开关 
			 */
			function setSqkg(cellValue,rowObject){
				var xmdm = rowObject.xmdm;
				var value = "关闭申请";
				var status = '0';
				var color;
				if(cellValue == '1'){
					var currDate = jQuery("#currDate").val();
					if(rowObject.sqkssj != null && currDate < rowObject.sqkssj || rowObject.sqjssj != null && currDate > rowObject.sqjssj ){
					}else{
						value = "开放申请";
						status = '1';
					}
				}
				rowObject.sqkg=status;
				return value;
			}

			/**
			 *奖项项目说明
			 */
			function setXmsm(cellValue,rowObject){

				if(rowObject['xmsm'] == null) {
					var value = "<a title=''>"+cellValue+"</a>";
				}else {
					var xmsm = rowObject["xmsm"];
					xmsm = xmsm.replaceAll("<br/>","\n");
					var value = "<a title='"+xmsm+"'>"+cellValue+"</a>";
				}
		
				return value;	
				
			}
			
			/**
			 * 切换tab页
			 * @param obj
			 * @param shzt
			 * @return
			 */
			function selectTab(obj,shzt){
				jQuery("#shzt").val(shzt);

				if (shzt == "wsq"){
					jQuery("#li_sq").css("display","");
					jQuery("#li_xg").css("display","");
					jQuery("#li_sc").css("display","");
					jQuery("#li_tj").css("display","");
					jQuery("#li_ts").css("display","");

					jQuery("#li_cx").css("display","none");
					jQuery("#li_lc").css("display","none");
					jQuery("#li_xz").css("display","none");
					
					jQuery("#dataTable").initGrid(gridSetting1);
				} else {
					jQuery("#li_sq").css("display","none");
					jQuery("#li_xg").css("display","none");
					jQuery("#li_sc").css("display","none");
					jQuery("#li_tj").css("display","none");
					jQuery("#li_ts").css("display","none");
					
					jQuery("#li_cx").css("display","");
					jQuery("#li_lc").css("display","");
					jQuery("#li_xz").css("display","");
					
					jQuery("#dataTable").initGrid(gridSetting2);
				}
				
				jQuery(".ha").removeClass("ha");
				jQuery(obj).parent().addClass("ha");
			}


			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting1);
			});

        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
			/**
			 * 修改申请表
			 * @returns {Boolean}
			 */
			function xgSqb(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("请选择一条您要取消的记录！");
				} else {
					if(rows[0]["sqkg"]!="1"){
						showAlertDivLayer("所选项目申请状态未开放，请重新选择");
						return false;
					}
					if(rows[0]["sqid"] == '' || rows[0]["sqid"] == null){
						showAlertDivLayer("该项目未填写申请，不能修改，请先填写！");
						return false;
						}
					showDialog("修改申请表",600,380,"xpj_pjxmsq.do?method=updateSqb&sqid="+rows[0]["sqid"]);
				}
			}

			/**
			 * 填写申请表
			 */
			function xmsq(){

				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length == 0){
					showAlertDivLayer("请至少选择一个申请奖项！");
					return false;
				}

				for ( var i = 0; i < rows.length; i++) {
					if(rows[i]["sqkg"]!="1"){
						showAlertDivLayer("所选项目申请状态未开放，请重新选择");
						return false;
					}
					if(rows[i]["sqid"] != '' && rows[i]["sqid"] != null){
						showAlertDivLayer("所选项目存在已填写未提交或已退回记录，请确认！");
						return false;
					}
				}

				jQuery.post("xpj_xmwh_jdsz.do?method=getJdysq",{xmdm:rows[0]["xmdm"]},function(data){

					if(data!=""&&data!=null){
						showAlertDivLayer("您已<font color='red'>申请</font>或<font color='red'>保存</font>“"+data[0]["xmmc"]+"”，不能再申请当前奖项，请确认！");
						return false;
					}
					
					showDialog("填写申请表",600,400,"xpj_pjxmsq.do?method=viewXmsq");
					
				},"json");
				
				
			}


			/**
			 * 流程跟踪
			 * @return
			 */
			function viewLcgz(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer("请选择一条记录！");
				} else {
					if(rows[0]["shzt"]=="0" || rows[0]["shzt"]=="6" || rows[0]["shzt"]=="7"){
						showAlertDivLayer("无相关流程信息！");
						return false;
					}
					//已退回开放流程跟踪功能，屏蔽其他审核状态的流程跟踪功能
					if(rows[0]["shzt"]==""||rows[0]["shzt"]==null){
						showAlertDivLayer("无相关流程信息！");
						return false;
					}
					showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
				}
			}
			
			/**
			 * 流程跟踪
			 */
			//function viewLcgz(){
			//	var rows = jQuery("#dataTable").getSeletRow();

			//	if (rows.length != 1){
			//		showAlertDivLayer("请选择一条记录！");
			//	} else {
			//		var url = "xpj_sqsh.do?method=viewLcgz&sqid="+rows[0]["sqid"];
			//		showDialog("流程跟踪",580,450,url,{max:false,min:false});
			//	}
			//}	

			/**
			*	下载登记表
			*/
			function getWord(){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("请选择您要下载的记录！");
				 } else if (rows.length > 1){
					var ids = jQuery("#dataTable").getSeletIds();
					var url="xpj_pjxmsq.do?method=getDjbZip&value="+ids + "&actionFrom=sqsh";
					window.open(url);
				 } else {
					var url="xpj_pjxmsq.do?method=getDjbWord&sqid="+rows[0]["sqid"] + "&actionFrom=sqsh";
					window.open(url);
			     }
			}		

			//撤销
			function cxSqb(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0) {
					showAlertDivLayer("请选择您要撤销的记录！");
				} else if (ids.length >1 ) {
					showAlertDivLayer("请选择一条您要撤销的记录！");
				} else {
					var rows = jQuery("#dataTable").getSeletRow();
					var SFBJPY_Y = jQuery("#SFBJPY_Y").val();
					for(var i=0;i<ids.length;i++){
						if(SFBJPY_Y == 'true'){
							if(rows[i]['shzt']!='6'){
								showAlertDivLayer("只有班级评议中的记录才能被撤销！");
								return false;
							}
						}else{
							if(rows[i]['shzt']!='5'){
								showAlertDivLayer("只有审核中的记录才能被撤销！");
								return false;
							}
						}
					}
					
					showConfirmDivLayer("您确定要撤销选择的记录吗？", {
						"okFun" : function() {
							jQuery.post("xpj_sqsh.do?method=cancle", {
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
				
				var rows = jQuery("#dataTable").getSeletRow();

				var ids = [];
				for ( var int = 0; int < rows.length; int++) {
					ids.push(rows[int]['sqid'])
				}
				
				if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
					showAlertDivLayer("请选择未提交或者已退回的记录！");
					return false;
				}
				
				if (ids.length == 0){
					showAlertDivLayer("请选择未提交或者已退回的记录！！");
				} else if (ids.length >1 ) {
					showAlertDivLayer("请选择一条您要提交的记录！");
				} else {
					var xmdm=rows[0]["xmdm"];
					showConfirmDivLayer("您确定要提交选择的记录吗？",{"okFun":function(){
						jQuery.post("xpj_sqsh.do?method=saveUpdateSqb&type=tj&xh=" + jQuery("input[name='userName']").val(),{values:ids.toString(),xmdm:xmdm},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
				
			}
			
			/**
			 * 删除申请
			 */
			function delSqb(){
				var rows = jQuery("#dataTable").getSeletRow();
				var ids = [];
				for ( var int = 0; int < rows.length; int++) {
					ids.push(rows[int]['sqid'])
				}
				if (ids.length == 0){
					showAlertDivLayer("请选择您要取消申请的记录！");
				} else if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
					showAlertDivLayer("只能删除未提交或者已退回的记录！");
					return false;
				}else {
					showConfirmDivLayer("您确定要取消该申请吗？",{"okFun":function(){
						jQuery.post("xpj_sqsh.do?method=cancelXmsq",{values:ids.toString()},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}

<%--			jQuery(function($){--%>
<%--				//兼得--%>
<%--				$("#dataTable").find("tbody>tr").live("click",function(){--%>
<%--					if($(this).find(":checkbox").is(":checked")){--%>
<%--						var obj=$(this);--%>
<%--						var xmdm=$(this).find("td")[1];--%>
<%----%>
<%--						//--%>
<%--						$.post("xpj_xmwh_jdsz.do?method=getJdysq",{xmdm:$(xmdm).text()},function(data){--%>
<%----%>
<%--							if(data!=""&&data!=null){--%>
<%--								showAlertDivLayer("您已申请或保存“"+data[0]["xmmc"]+"”，不能再申请当前奖项，请确认！");--%>
<%--								$(obj).removeAttr("checked");--%>
<%--							}--%>
<%--							--%>
<%--						},"json");--%>
<%----%>
<%--						//--%>
<%--						$.post("xpj_xmwh_jdsz.do?method=getBjdxm",{xmdm:$(xmdm).text()},function(data){--%>
<%--							$("#dataTable").find("tbody>tr").each(function(){--%>
<%--								for (var i = 0; i < data.length ; i++){--%>
<%--									var xmobj;--%>
<%--									if($(this).find(":checkbox").is(":checked")){--%>
<%--										xmobj=$(this).find("input[name='"+data[i]["xmdm"]+"']").val();--%>
<%--									}--%>
<%--									if (xmobj){--%>
<%--										showAlertDivLayer("该奖项与“ "+$($(this).find("td")[3]).text()+"”不能同时申请，请确认！");--%>
<%--										$(obj).find(":checkbox").removeAttr("checked");--%>
<%--										break;--%>
<%--									}									--%>
<%--								}--%>
<%--							});--%>
<%--						},"json");	--%>
<%--					}--%>
<%--				});--%>
<%--					--%>
<%--			});--%>

			
		</script>
	</head>

	<body>
		<input type="hidden" name="currDate" id="currDate" value="${currDate}"/>
		<input type="hidden" id="SFBJPY_Y" value="${SFBJPY_Y }"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	
		<html:form action="/xpj_sqsh" method="post" styleId="zcxmForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
								
							<logic:equal value="true" name="cssz" property="kgzt">
								<li id="li_sq">
									<a href="javascript:void(0);" class="btn_zj"
									   onclick="xmsq();return false;" 
									   title="点击该按钮，打开申请表填写页面。"
									>
									申请
									</a>
								</li>
								
								<li id="li_xg" >
									<a href="javascript:void(0);" onclick="xgSqb();return false;" class="btn_xg"
							   			title="只能修改未审核或退回的记录，已被在审核的不能删除。"
										>修改</a>
								</li>
								
								<li id="li_sc" >
									<a href="javascript:void(0);" onclick="delSqb();return false;" class="btn_sc"
							   			title="只能取消未审核或退回的记录，已被在审核的不能取消。"
										>删除</a>
								</li>
								
								<li id="li_tj" >
									<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc"
							   			title="只能取消未审核或退回的记录，已被在审核的不能取消。"
										>提交</a>
								</li>
								<li id="li_cx" style="display: none;">
									<a href="javascript:void(0);" onclick="cxSqb();return false;" class="btn_xg"
							   			title="只能取消未审核或退回的记录，已被在审核的不能取消。"
										>撤销</a>
								</li>
							</logic:equal>
								<li id="" style="">
									<a href="javascript:void(0);" onclick="viewLcgz();return false;" 
							   			title="选中一条记录，点击该按钮可以查看审核流程。"
							   			class="btn_cs">流程跟踪</a>
								</li>	
								<li id="li_xz" style="display: none;">
									<a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">下载登记表</a>
								</li>
						</ul>
					</div>
				</logic:equal>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchAreaStu.jsp"%>
				<!-- 过滤条件 end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'wsq');"><span>未申请项目</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysq');"><span>已申请项目</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>
				<li id="li_ts" ><font color="blue">${pjzqmc}&nbsp;</font></li>评奖项目申请列表
				 </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>

		</div>
	</body>
</html>
