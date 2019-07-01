<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>	 	
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<%--<script type="text/javascript" src='xsgzgl/szdw/thjl/js/thjlManage.js'></script>--%>
		<script type="text/javascript">
			jQuery(function(){
				var gridSetting = {
					caption : "谈话记录列表",
					pager : "pager",
					url : "szdw_thjl.do?method=sqList&doType=query",
					colList : [
						{label : 'sqid',name : 'sqid',index : 'sqid',hidden : true,key : true},
						{label : '学号',name : 'xh',index : 'xh'},
						{label : '姓名',name : 'xm',index : 'xm'},
						{label : '性别',name : 'xb',index : 'xb'},
						{label : '年级',name : 'nj',index : 'nj'},
						{label : '学院',name : 'xymc',index : 'xymc'},
						{label : '书院',name : 'symc',index : 'symc'},
						{label : '专业',name : 'zymc',index : 'zymc'},
						{label : '行政班级',name : 'bjmc',index : 'bjmc'},
						{label : '专业班级',name : 'zybjmc',index : 'zybjmc'},
						{label : '谈话日期',name : 'thsj',index : 'thsj'},
						{label : '谈话教师',name : 'jsxm',index : 'jsxm'},
						{label : '审核状态',name : 'shztmc',index : 'shztmc'},
                        {label : 'shzt',name : 'shzt',index : 'shzt',hidden:'true'},
                        {label : 'splc',name : 'splc',index : 'splc',hidden:'true'}
						],

					sortname : "",
					sortorder : ""
				};
				jQuery("#dataTable").initGrid(gridSetting);
			})
            function addThjl() {
                showDialog("新增谈话记录", 700, 505, "szdw_thjl.do?method=zjsq&doType=add");
            }
            function updateThjl(){
                var rows = jQuery("#dataTable").getSeletRow();
                if(rows.length != 1){
                    showAlertDivLayer("请选择一条您要修改的记录！");
                    return false;
                }
                if(rows[0]["shzt"] != '0' && rows[0]["shzt"] != '3'){
                    showAlertDivLayer("审核中数据不能修改！");
                    return;
                }
                showDialog("修改谈话记录",700,550,"szdw_thjl.do?method=zjsq&doType=update&sqid="+rows[0]["sqid"]);
            }
            function searchRs(){
                var map = getSuperSearch();
                jQuery("#dataTable").reloadGrid(map);
            }
            function submit(){
                var ids = jQuery("#dataTable").getSeletIds();
                if (ids.length != 1){
                    showAlertDivLayer("请选择一条您要提交的记录！");
                }else{
                    var rows = jQuery("#dataTable").getSeletRow();
                    var url = "szdw_thjl.do?method=submit";
                    for(var i=0;i<ids.length;i++){
                        if(rows[i]['shzt']!='0' && rows[i]['shzt']!='3' ){
                            showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
                            return false;
                        }
                    }
                    showConfirmDivLayer("您确定要提交选择的记录吗？",{"okFun":function(){
                        jQuery.post(url,
                            {
                                values:ids.toString(),
                                splc : rows[0]['splc'],
                                shzt : rows[0]['shzt']
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
                        if(rows[i]['shzt']!='5'){
                            showAlertDivLayer("只有审核中的记录才能被撤销！");
                            return false;
                        }
                    }
                    showConfirmDivLayer("您确定要撤销选择的记录吗？",{"okFun":function(){
                        jQuery.post("szdw_thjl.do?method=cancel",
                            {
                                values:ids.toString(),
                                splc : rows[0]['splc']
                            },function(data){
                                showAlertDivLayer(data["message"]);
                                jQuery("#dataTable").reloadGrid();
                            },'json');
                    }});
                }
            }
            function Lcinfo(){
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
                    showDialog("流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
                }
            }
            function deleteThjl() {
                var ids = jQuery("#dataTable").getSeletIds();
                if(ids == 0){
                    showAlertDivLayer("请选择一条您要删除的记录！");
                    return false;
                }
                var rows = jQuery("#dataTable").getSeletRow();
                for(var i=0;i<rows.length;i++){
                    if(rows[i]["shzt"] != '0' && rows[i]["shzt"] != '3'){
                        showAlertDivLayer("审核中数据不能删除，请确认！");
                        return false;
                    }
                }
                confirmInfo("您确定要删除"+ids.length +"条记录吗?",function(ty){
                    if(ty=="ok"){
                        jQuery.post("szdw_thjl.do?method=del",{values:ids.toString()},function(data){
                            alertInfo(data["message"]);
                            jQuery("#dataTable").reloadGrid();
                        },'json');
                    }
                });
            }
            function viewThjl(val){
                var rows = jQuery("#dataTable").getSeletRow();
                if(rows.length != 1){
                    showAlertDivLayer("请选择一条您要修改的记录！");
                    return false;
                }
                showDialog("查看谈话记录申请",700,550,"szdw_thjl.do?method=zjsq&doType=view&sqid="+rows[0]["sqid"]);
            }
		</script>
	</head>


	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/szdw_thjl" styleId="form">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<input type="hidden" id="searchTjstr" value="${searchTjstr}"/>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="addThjl();return false;" class="btn_zj">增加</a>
						</li>
						<li>
							<a href="#" onclick="updateThjl();return false;" class="btn_xg">修改</a>
						</li>
						<li>
							<a href="#" onclick="deleteThjl();return false;" class="btn_sc">删除</a>
						</li>
						<li>
							<a href="#" onclick="viewThjl();return false;" class="btn_ck">查看</a>
						</li>
						<li><a href="javascript:void(0);" onclick="submit();return false;" class="btn_shuc">提交</a></li>
						<li><a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">撤销</a></li>
						<li>
							<a href="#" onclick="Lcinfo();return false;" class="btn_cs">流程跟踪</a>
						</li>
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
				<span> 谈话记录信息列表 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
