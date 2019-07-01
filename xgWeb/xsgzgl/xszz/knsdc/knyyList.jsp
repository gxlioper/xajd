<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		
		<script type="text/javascript">
        var gridSetting = {
				caption:"困难原因列表",
				pager:"pager",
				url:"xszz_knsdc.do?method=knyyList&type=query",
				colList:[
				   {label:'代码',name:'yydm', index: 'yydm',hidden:true,key:true},
				   {label:'序号',name:'xh', index: 'xh',width:'10%'},
				   {label:'名称',name:'yymc', index: 'yymc',width:'90%'}
				  
				],
				sortname: "to_number(xh)",
			 	sortorder: "asc"
			};

			function yymcLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name'>"+cellValue+"</a>";
			}


			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function query(){
				var map = {};
				map["yymc"] = jQuery("#yymc").val();
				jQuery("#dataTable").reloadGrid(map);
			}

			function add(){
				var url = "xszz_knsdc.do?method=addKnyy";
				var title = "增加困难原因";
				showDialog(title,400,200,url);
			}
			function update(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("请选择一条您要修改的记录！");
				} else {
					var url = 'xszz_knsdc.do?method=updateKnyy&yydm='+rows[0]["yydm"];
					var title = "修改困难原因";
					showDialog(title,400,200,url);
				}
			}

			function del(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0){
					showAlertDivLayer("请选择您要删除的记录！");
				} else {
					showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
							jQuery.post("xszz_knsdc.do?method=delKnyy",{values:ids.toString()},function(data){
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							},'json');
					}});
					
				}
			}
		
			function newChgCode(obj){
				allNotEmpThenGo(obj.id);
			}
		</script>
	</head>
	
	<body>
	<html:form action="/xszz_knsdc" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	
		<div class="toolbox">
			<!-- 按钮 -->
			
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">	
					<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">增加</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>		
					</logic:equal>				
				</ul>
			</div>
			<!-- 上海体育个性化-->
			<logic:equal value="10277" name="xxdm">
			<div class="compTab" id="card">
				<div class="comp_title">
					<ul>
						<li><a href="#" onclick="newChgCode(this);return false;" id="xszz_knsdc.do?method=dcwhList"><span>困难档次</span></a></li>
						<li class="ha"><a href="#" onclick="newChgCode(this);return false;" id="xszz_knsdc.do?method=knyyList"><span>困难原因</span></a></li>
						
					</ul>
				</div>
			</div>	
			</logic:equal>
			<!-- 过滤条件 -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="10%">名称</th>
						<td>
							<input type="text" id="yymc" name="yymc" maxleng="20" 
							onkeypress="if(pressEnter(event)){query();return false;}"/>
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
				<span> 档次维护列表 </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
		</html:form>
	</body>
</html>
