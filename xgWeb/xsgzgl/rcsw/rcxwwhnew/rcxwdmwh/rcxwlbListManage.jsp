<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
		var xxdm = jQuery("#xxdm").val();
		
		var gridSetting;
		//西安科技大学个性化
		if(xxdm = '10704'){
			gridSetting = {
				caption:"综合测评类别列表",
				pager:"pager",
				url:"rcsw_rcxwwhnew_rcxwdmwhgl.do?method=rcxwlbManage&type=query",
				colList:[
				   {label:'综合测评类别代码',name:'rcxwlbdm', index: 'rcxwlbdm',key:true,width:'15%'},
				   {label:'综合测评类别名称',name:'rcxwlbmc', index: 'rcxwlbmc',width:'85%'}
				 ],
				sortname: "rcxwlbdm",
				sortorder: "asc"
			}
		}else{
			gridSetting = {
				caption:"行为类别列表",
				pager:"pager",
				url:"rcsw_rcxwwhnew_rcxwdmwhgl.do?method=rcxwlbManage&type=query",
				colList:[
				   {label:'行为类别代码',name:'rcxwlbdm', index: 'rcxwlbdm',key:true,width:'15%'},
				   {label:'行为类别名称',name:'rcxwlbmc', index: 'rcxwlbmc',width:'85%'}
				],
				sortname: "rcxwlbdm",
			 	sortorder: "asc"
			}
		}
		
		 
		jQuery(function(){
			jQuery("#dataTable").initGrid(gridSetting);
		});
		function query(){
			var map = {};
			map["rcxwlbmc"] = jQuery("#rcxwlbmc").val();
			jQuery("#dataTable").reloadGrid(map);
		}
		function del(){
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length == 0){
				showAlertDivLayer("请选择您要删除的记录！");
			} else {
				showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
						jQuery.post("rcsw_rcxwwhnew_rcxwdmwhgl.do?method=delRcxwlb",{values:ids.toString()},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
				}});
				
			}
		}
		function newChgCode(obj){
			allNotEmpThenGo(obj.id);
		}
		function add(){
			var url = "rcsw_rcxwwhnew_rcxwdmwhgl.do?method=addRcxwlb";
			var title;
			//西安科技大学个性化
			if(xxdm = '10704'){
				title = "增加综合测评类别";
			}else{
				title = "增加行为类别";
			}
			showDialog(title,370,175,url);
		}
		function update(){
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1){
				showAlertDivLayer("请选择一条您要修改的记录！");
			} else {
				/*jQuery.post("rcsw_rcxwwhnew_rcxwdmwhgl.do?method=checkRcxwlb",{rcxwlbdm:rows[0]["rcxwlbdm"]},function(data){
					if(data["message"] == ""){*/
						var url = 'rcsw_rcxwwhnew_rcxwdmwhgl.do?method=updateRcxwlb&rcxwlbdm='+rows[0]["rcxwlbdm"];
						var title;
						//西安科技大学个性化
						if(xxdm = '10704'){
							title = "修改综合测评类别";
						}else{
							title = "修改行为类别";
						}
						showDialog(title,370,175,url);
					/*}else{
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					}
				},'json');*/
			}
		}
		// 用户授权
		function yhsq(){
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1){
				showAlertDivLayer("请选择一条您要授权的记录！");
			} else {
				var url = "/xgxt/splcNew.do?method=splcYhsz&spgw="+rows[0]["rcxwlbdm"]+"&yhszlx=rcxwwh";
				var title = "用户授权";
				showDialog(title,720,400,url);
			}
		}
		</script>
	</head>
	<body>
	<input type="hidden" id="xxdm" value="${xxdm}"/>
	<html:form action="/rcsw_rcxwwhnew_rcxwdmwhgl" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
			<div class="toolbox">
			<!-- 按钮 -->
			<logic:equal name="writeAble" value="yes">	
			<logic:equal name="userType" value="admin">	
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">增加</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>						
					<li><a href="javascript:void(0);" onclick="yhsq();" class="btn_sq">用户授权</a></li>						
				</ul>
			</div>
			</logic:equal>
			</logic:equal>
			<div class="compTab" id="card">
				<div class="comp_title">
					<ul>
						<li class="ha">
							<a href="#" onclick="newChgCode(this);return false;" id="rcsw_rcxwwhnew_rcxwdmwhgl.do?method=rcxwlbManage">
								<span>
								<logic:equal value="10704" name="xxdm">
									综合测评类别
								</logic:equal>
								<logic:notEqual value="10704" name="xxdm">								
									行为类别
								</logic:notEqual>
								</span>
							</a>
						</li>
						<li>
							<a href="#" onclick="newChgCode(this);return false;" id="rcsw_rcxwwhnew_rcxwdmwhgl.do?method=rcxwdlManage">
								<span>
									<logic:equal value="10704" name="xxdm">
										综合测评大类
									</logic:equal>
									<logic:notEqual value="10704" name="xxdm">								
										行为大类
									</logic:notEqual>									
								</span>
							</a>
						</li>
						<li>
							<a href="#" onclick="newChgCode(this);return false;" id="rcsw_rcxwwhnew_rcxwdmwhgl.do?method=rcxwxlManage">
								<span>
									<logic:equal value="10704" name="xxdm">
										综合测评小类
									</logic:equal>
									<logic:notEqual value="10704" name="xxdm">								
										行为小类
									</logic:notEqual>									
								</span>
							</a>
						</li>
					</ul>
				</div>
			</div>	
			<!-- 过滤条件 -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="12%" >
							<logic:equal value="10704" name="xxdm">
								综合测评类别名称
							</logic:equal>
							<logic:notEqual value="10704" name="xxdm">								
								行为类别名称
							</logic:notEqual>							
						</th>
						<td>
							<input type="text" id="rcxwlbmc" name="rcxwlbmc" maxleng="20" onkeypress="if(pressEnter(event)){query();return false;}" />
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="query()">
									查 询
								</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
		</div>
			<div class="formbox">
			<!--标题start-->
				<h3 class="datetitle_01">
					<span>
						<logic:equal value="10704" name="xxdm">
							综合测评类别列表
						</logic:equal>
						<logic:notEqual value="10704" name="xxdm">								
							行为类别列表
						</logic:notEqual>						
					</span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
