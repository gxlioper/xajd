<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/jjgl/xqsh/script/xqsh.js"></script>
		<script type="text/javascript">
			var gridSetting = {
					caption:"家教需求列表",
					pager:"pager",
					multiselect:true,
					rowNum:10,
					url:"jjgl_xqshgl.do?method=queryXqList&type=dsh",
					colList:[
					   {label:'ID',name:'xqid', index: 'xqid',hidden:true,key:true},
					   {label:'家教科目',name:'jjxkmc', index: 'jjxkmc'},
					   {label:'家教年级',name:'jjnjmc', index: 'jjnjmc'},
					   {label:'家教地点',name:'jjdd', index: 'jjdd'},
					   {label:'家教时间',name:'jjsj', index: 'jjsj'},
					   {label:'家教老师要求',name:'jjlsyq', index: 'jjlsyq'},
					   {label:'发布人',name:'sqr', index: 'sqr'},
					   {label:'发布时间',name:'sqsj', index: 'sqsj'},
					   {label:'审核状态',name:'shztmc', index: 'shztmc'}
					]
				};
	
				function searchRs(){
					var map = {};
					map["sqr"] = jQuery("#sqr").val();
					jQuery("#dataTable").reloadGrid(map);
				}
	
			/**
			 * 页签切换
			 * @return
			 */
			function selectTab(obj,query_type){
				gridSetting['url'] =  "jjgl_xqshgl.do?method=queryXqList&type=" + query_type;
				
				if(query_type == "dsh"){
					jQuery("#xqshLinkLi").css("display","");
				} else {
					jQuery("#xqshLinkLi").css("display","none");
				}
				jQuery("#dataTable").initGrid(gridSetting);
				jQuery(".ha").removeClass("ha");
				jQuery(obj).parent().addClass("ha");
			}

			//审核
			function xqsh(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("请选择一条您要审核的记录！");
				} else {
					var type = jQuery('#hiddenQryType').val();
					var url = "jjgl_xqshgl.do?method=xqDgsh&xqid="+jQuery("#dataTable").getSeletIds()[0];
					var title = "审核";
					showDialog(title,700,430,url);
				}
			}


			//查看
			function xqck(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("请选择一条您要查看的记录！");
				} else {
					var url = "jjgl_xqshgl.do?method=xqshck&xqid="+jQuery("#dataTable").getSeletIds()[0];
					var title = "查看";
					showDialog(title,700,430,url);
				}
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
				<html:form action="/jjgl_xqshgl" method="post" >
					<!-- 按钮 -->
					<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
							<li id="xqshLinkLi"><a href="javascript:void(0);" onclick="xqsh();" class="btn_sh" id="xqshLink">审核</a></li>
							<li id="ckLinkLi"><a href="javascript:void(0);" onclick="xqck();" class="btn_ck" id="xqckLink">查看</a></li>				
						</ul>
					</div>
					</logic:equal>
					<table width="100%" border="0">
						<tr>
							<th width="10%">审核状态</th>
							<td>
								<html:select property="shzt" styleId="shzt">
									<html:option value="">--请选择--</html:option>
									<html:option value="0">未审核</html:option>
									<html:option value="1">通过</html:option>
									<html:option value="2">不通过</html:option>
								</html:select>
							</td>
							<th width="10%">发布人</th>
							<td>
								<html:text property="sqr" styleId="sqr" ></html:text>
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
					<div class="comp_title" id="comp_title">
				      <ul style="width:90%" id="tabUl">
				      	<li class="ha" >
				      		<a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>待审核</span></a>
				      	</li>
						<li ><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>已审核</span></a></li>
				      </ul>
				    </div>
				</html:form>
			</div>
		</div>
		<div class="formbox">
			<div>
				<h3 class="datetitle_01">
					<span> 
						需求列表
					</span>
				</h3>
			</div>
			<table id="dataTable"></table>
		</div>
		<div id="pager"></div>
		
	</body>
</html>
