<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"我的家教列表",
				pager:"pager",
				rowNum:10,
				url:"jjgl_jjxq.do?method=getWdjjList",
				colList:[
				   {label:'ID',name:'sqid', index: 'sqid',hidden:true,key:true},
				   {label:'家教编号',name:'xqid', index: 'xqid',formatter:xqidLink},
				   {label:'家教年级',name:'jjnj', index: 'jjnj'},
				   {label:'家教科目',name:'jjxk', index: 'jjxk'},
				   {label:'家教地点',name:'jjdd', index: 'jjdd'},
				   {label:'申请时间',name:'sqsj', index: 'sqsj'},
				   {label:'家教开始时间',name:'kssj', index: 'kssj'},
				   {label:'家教结束时间',name:'jssj', index: 'jssj'},
				   {label:'家教状态',name:'jjzt', index: 'jjzt',hidden:true},
				   {label:'家教状态',name:'jjztmc', index: 'jjzt'},
				   {label:'jjczshzt',name:'jjczshzt', index: 'jjczshzt',hidden:true},
				   {label:'jjczsqid',name:'jjczsqid', index: 'jjczsqid',hidden:true},
				   {label:'tjjcz',name:'tjjcz', index: 'tjjcz',hidden:true},
				   {label:'家教操作状态',name:'jjczshztmc', index: 'jjczshzt'}
				]
			};

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
	
			function jsjj(){
                //jjczshzt = 空或3（退回）才能申请
			    //未交协议书不能结束家教 已结束（关闭）家教不能结束家教
                var rows = jQuery("#dataTable").getSeletRow();
                if (rows.length != 1){
                    showAlertDivLayer("请选择一条您要操作的记录！");
                    return false;
                }
                if(rows[0]["jjzt"] == '4'){
                    showAlertDivLayer("家教已结束！");
                    return false;
                }
                //有操作在审核中不能再申请
				if(rows[0]["jjczshzt"] == '5'){
                    showAlertDivLayer("有操作正在审核中，暂不能进行该操作！");
                    return false;
				}
                //相同的操作通过或不通过都不能再申请
                if(rows[0]["tjjcz"] == '4'&&(rows[0]["jjczshzt"] == '1' || rows[0]["jjczshzt"] == '2')){
                    showAlertDivLayer("该操作已完成，不能再次操作！");
                    return false;
                }
                //已退家教不能执行关闭操作
                if(rows[0]["tjjcz"] == '0'&&(rows[0]["jjczshzt"] == '1')){
                    showAlertDivLayer("已退家教，不能执行该操作！");
                    return false;
                }


				showDialog("结束家教",700,480,"jjgl_jjxq.do?method=jsjj&jjcz=4&xqid="+rows[0]["xqid"]+"&sqid="+rows[0]["sqid"]);
			}

            function tjj(){
			    //已交协议书不能退家教 已结束（关闭）家教不能结束家教
                var rows = jQuery("#dataTable").getSeletRow();
                if (rows.length != 1){
                    showAlertDivLayer("请选择一条您要操作的记录！");
                    return false;
                }
                if(rows[0]["jjzt"] == '4'){
                    showAlertDivLayer("家教已结束！");
                    return false;
                }
                if(rows[0]["jjzt"] == '3'){
                    showAlertDivLayer("已交协议书不能退家教！");
                    return false;
                }
                //有操作在审核中不能再申请
                if(rows[0]["jjczshzt"] == '5'){
                    showAlertDivLayer("有操作正在审核中，暂不能进行该操作！");
                    return false;
                }
                //相同的操作通过或不通过都不能再申请
                if(rows[0]["tjjcz"] == '0'&&(rows[0]["jjczshzt"] == '1' || rows[0]["jjczshzt"] == '2')){
                    showAlertDivLayer("该操作已完成，不能再次操作！");
                    return false;
                }

                showDialog("退家教",700,480,"jjgl_jjxq.do?method=jsjj&jjcz=0&xqid="+rows[0]["xqid"]+"&sqid="+rows[0]["sqid"]);
            }
			
			function ckjj(sqid){
				showDialog("查看",500,350,"jjgl_jjxq.do?method=cksq&sqid="+sqid);
			}
			
			function searchRs(){
				var map = {};
				map["jjxk"] = jQuery("#jjxk").val();
				map["jjnj"] = jQuery("#jjnj").val();
				map["jjzt"] = jQuery("#jjzt").val();
				jQuery("#dataTable").reloadGrid(map);
			}

            function xqidLink(v,r){

                return "<a class='name' href='javascript:void(0);' onclick='ck("+v+")'>"+v+"</a>";
            }

            function ck(value){

                var url = "jjgl_xqwhgl.do?method=ck&xqid="+value;
                var title = "查看需求信息";
                showDialog(title,800,400,url);
            }

            /**
			 * 家教时长维护
             */
            function jjscwh() {

                var rows = jQuery("#dataTable").getSeletRow();
                if (rows.length != 1){
                    showAlertDivLayer("请选择一条您要操作的记录！");
                    return false;
                }
                showDialog("家教时长维护",560,450,"jjgl_jjxq.do?method=jjgswh&xqid="+rows[0]["xqid"]);
            }

            /**
			 * 家教评价
			 * 是家教老师对家长的评价
             */
            function jjpj() {

                var rows = jQuery("#dataTable").getSeletRow();
                if (rows.length != 1){
                    showAlertDivLayer("请选择一条您要操作的记录！");
                    return false;
                }
                showDialog("家教评价",560,300,"jjgl_jjxq.do?method=jjpj&xqid="+rows[0]["xqid"]);
            }
		</script>
	</head>

	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 标题 end-->
	
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<li>
						<a href="javascript:void(0);" onclick="jsjj();return false;"  class="btn_xg">结束家教</a>
					</li>
					<li>
						<a href="javascript:void(0);" onclick="tjj();return false;"  class="btn_xg">退家教</a>
					</li>
					<li>
						<a href="javascript:void(0);" onclick="jjscwh();return false;"  class="btn_xg">家教时长维护</a>
					</li>
					<li>
						<a href="javascript:void(0);" onclick="jjpj();return false;"  class="btn_xg">家教评价</a>
					</li>
				</ul>
			</div>
			<!-- 过滤条件 -->
			<div class="searchtab">
				<html:form action="/jjgl_jjxq" method="post" >
					<table width="100%" border="0">
						<tr>
							<th width="10%">家教科目</th>
							<td>
								<html:select property="jjxk" styleId="jjxk" style="width:173px">
					    			<html:option value=""></html:option>
					    			<html:options collection="jjxkList" property="jjxk" labelProperty="jjxk"/>
					    		</html:select>
							</td>
							<th width="10%">年级</th>
							<td>
								<html:select property="jjnj" styleId="jjnj" style="width:173px">
					    			<html:option value=""></html:option>
					    			<html:options collection="jjnjList" property="jjnj" labelProperty="jjnj"/>
					    		</html:select>
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
	
		<div>
			<h3 class="datetitle_01">
				<span>我的家教列表</span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
		<div id="pager"></div>
	</body>
</html>
