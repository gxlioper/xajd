<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
					caption:"家教注册用户列表",
					pager:"pager",
					radioselect:true,
					rowNum:20,
					url:"jjgl_zcyhgl.do?method=queryZcyhList&type=w",
					colList:[
					   {label:'家长编号',name:'yhm', index: 'yhm',key:true},
					   {label:'姓名',name:'xm', index: 'xm'},
					   {label:'身份证号',name:'sfzh', index: 'sfzh'},
					   {label:'联系电话',name:'lxdh', index: 'lxdh'},
					   {label:'家庭住址',name:'jtzz', index: 'jtzz'},
					   {label:'工作单位',name:'gzdw', index: 'gzdw'},
					   {label:'登记时间',name:'zcsj', index: 'zcsj'}
					]
				};
	
				function searchRs(){
					var map = {};
					map["yhm"] = jQuery("#yhm").val();
					map["xm"] = jQuery("#xm").val();
					map["sfzh"] = jQuery("#sfzh").val();
					jQuery("#dataTable").reloadGrid(map);
				}
	
			/**
			 * 页签切换
			 * @return
			 */
			function selectTab(obj,query_type){
				gridSetting['url'] =  "jjgl_zcyhgl.do?method=queryZcyhList&type=" + query_type;
				
				if(query_type == "w"){
					gridSetting['colList'] = [
					   					   {label:'家长编号',name:'yhm', index: 'yhm',key:true},
										   {label:'姓名',name:'xm', index: 'xm'},
										   {label:'身份证号',name:'sfzh', index: 'sfzh'},
										   {label:'联系电话',name:'lxdh', index: 'lxdh'},
										   {label:'家庭住址',name:'jtzz', index: 'jtzz'},
										   {label:'工作单位',name:'gzdw', index: 'gzdw'},
										   {label:'登记时间',name:'zcsj', index: 'zcsj'}
										];
					jQuery("#zjLinkLi").css("display","");
					jQuery("#xgLinkLi").css("display","");
					jQuery("#scLinkLi").css("display","");
					jQuery("#drLinkLi").css("display","");
					jQuery("#dcLinkLi").css("display","");
					jQuery("#blackLinkLi").css("display","");
					jQuery("#cancelBlackLinkLi").css("display","none");
					jQuery('#listName').text('注册用户列表');
				} else {
					gridSetting['colList'] = [
						   					   {label:'家长编号',name:'yhm', index: 'yhm',key:true},
											   {label:'姓名',name:'xm', index: 'xm'},
											   {label:'身份证号',name:'sfzh', index: 'sfzh'},
											   {label:'联系电话',name:'lxdh', index: 'lxdh'},
											   {label:'家庭住址',name:'jtzz', index: 'jtzz'},
											   {label:'工作单位',name:'gzdw', index: 'gzdw'},
											   {label:'登记时间',name:'zcsj', index: 'zcsj'},
											   {label:'拉黑名单时间',name:'sj', index: 'sj'}
											];
					
					jQuery("#zjLinkLi").css("display","none");
					jQuery("#xgLinkLi").css("display","none");
					jQuery("#scLinkLi").css("display","none");
					jQuery("#drLinkLi").css("display","none");
					jQuery("#dcLinkLi").css("display","none");
					jQuery("#blackLinkLi").css("display","none");
					jQuery("#cancelBlackLinkLi").css("display","");
					jQuery('#listName').text('黑名单列表');
				}
				jQuery("#dataTable").initGrid(gridSetting);
				jQuery(".ha").removeClass("ha");
				jQuery(obj).parent().addClass("ha");
			}

			//拉黑
			function bl(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("请选择一条记录！");
				} else {
					var url = "jjgl_zcyhgl.do?method=hmd&yhm="+jQuery("#dataTable").getSeletIds()[0];
					var title = "黑名单";
					showDialog(title,600,250,url);
				}
			}

			//撤销
			function cbl(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("请选择一条记录！");
				} else {
					showConfirm("您确定要撤销要黑名单？",{"okFun" : function(){
						jQuery.getJSON("jjgl_zcyhgl.do?method=hmdCancelSubmit" , 
								{yhm:jQuery("#dataTable").getSeletIds()[0]} , 
								function(data){
									showAlert(data["message"],{},{"clkFun":function(){
										jQuery("#dataTable").reloadGrid();
									}});
								});
						}});
				}
			}

			/**
			*查看
			**/
			function ck(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("请选择一条记录！");
				} else {
					var url = "jjgl_zcyhgl.do?method=viewZcyh&yhm="+jQuery("#dataTable").getSeletIds()[0];
					var title = "查看用户信息";
					showDialog(title,850,450,url);
				}
			}


			function zj() {
                var url = "jjgl_zcyhgl.do?method=jzxxAdd";
                var title = "家长信息增加";
                showDialog(title, 800, 550, url);
            }
            
            function xg() {
                var rows = jQuery("#dataTable").getSeletRow();
                if (rows.length != 1) {
                    showAlertDivLayer("请选择一条您要修改的记录！");
                } else {
                    var url = 'jjgl_zcyhgl.do?method=jzxxEdit&yhm=' + rows[0]["yhm"];
                    var title = "家长信息修改";
                    showDialog(title, 800, 550, url);
                }
            }
            
            function sc() {
                var ids = jQuery("#dataTable").getSeletIds();
                if (ids.length == 0) {
                    showAlertDivLayer("请选择您要删除的记录！");
                    return false;
                }

                showConfirmDivLayer("您确定要删除选择的记录吗？", {
                    "okFun" : function() {
                        jQuery.post("jjgl_zcyhgl.do?method=jzxxDel", {
                                values : ids.toString()
                            },
                            function(data) {
                                showAlertDivLayer(data["message"]);
                                jQuery("#dataTable").reloadGrid();
                            }, 'json');
                    }
                });
            }
            
            function dr() {
                // 调用通用的导入function，参数是导入功能模块代码
                toImportDataNew("IMPORT_JJLSJG");
                return false;
            }

            /**
             * 导出
             */
            var DCCLBH = "jjgl_jjlsjg.do";//dcclbh,导出功能编号

            //自定义导出 功能
            function dc() {
                //DCCLBH导出功能编号,执行导出函数
                customExport(DCCLBH, exportData);
            }

            //导出方法
            function exportData() {
                setSearchTj();//设置高级查询条件
                var url = "jjgl_jjlsjggl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
                url = addSuperSearchParams(url);//设置高级查询参数
                jQuery("form").eq(0).attr("action", url);
                jQuery("form").eq(0).submit();
            }
			
			/**
			*初始化
			*/
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			/**
			*重新加载数据
			*/
			function reloadWindow(){
				jQuery("#dataTable").reloadGrid();
			}
		</script>
	</head>
	<body>
		<button id="search_go" type="button" style="display:none" onclick="reloadWindow();"></button>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<div class="toolbox">
			<!-- 过滤条件 -->
			<div class="searchtab">
				<html:form action="/jjgl_zcyhgl" method="post" >
					<div class="buttonbox">
						<ul>
							<li id = "zjLinkLi"><a href="javascript:void(0);" onclick="zj();" class="btn_zj" id="zjLink">增加</a></li>
							<li id = "xgLinkLi"><a href="javascript:void(0);" onclick="xg();" class="btn_xg" id="xgLink">修改</a></li>
							<li id = "scLinkLi"><a href="javascript:void(0);" onclick="sc();" class="btn_sc" id="scLink">删除</a></li>

							<li id = "ckLinkLi"><a href="javascript:void(0);" onclick="ck();" class="btn_ck" id="ckLink">查看</a></li>

							<li id = "drLinkLi"><a href="javascript:void(0);" onclick="dr();" class="btn_dr" id="drLink">导入</a></li>
							<li id = "dcLinkLi"><a href="javascript:void(0);" onclick="dc();" class="btn_dc" id="dcLink">导出</a></li>

							<li id = "blackLinkLi"><a href="javascript:void(0);" onclick="bl();" class="btn_sh" id="blackLink">拉黑名单</a></li>
							<li id="cancelBlackLinkLi" style="display:none"><a href="javascript:void(0);" onclick="cbl();" class="btn_qxsh" id="cancelBlackLink">撤销黑名单</a></li>
						</ul>
					</div>
					<div class="comp_title" id="comp_title">
				      <ul style="width:90%" id="tabUl">
				      	<li class="ha" >
				      		<a href="javascript:void(0);" onclick="selectTab(this,'w');"><span>用户列表</span></a>
				      	</li>
						<li ><a href="javascript:void(0);" onclick="selectTab(this,'b');"><span>黑名单</span></a></li>
				      </ul>
				    </div>
					
					<table width="100%" border="0">
						<tr>
							<th width="10%">姓名</th>
							<td>
								<html:text property="xm" styleId="xm" ></html:text>
							</td>
							<th width="10%">身份证号</th>
							<td>
								<html:text property="sfzh" styleId="sfzh" ></html:text>
							</td>
							<td>
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go" onclick="searchRs()">
										查 询
									</button>
								</div>
							</td>
						</tr>					
					</table>
				</html:form>
			</div>
		</div>
		<div class="formbox">
			<div>
				<h3 class="datetitle_01">
					<span id="listName"> 
						注册用户列表
					</span>
				</h3>
			</div>
			<table id="dataTable"></table>
		</div>
		<div id="pager"></div>
		
	</body>
</html>
