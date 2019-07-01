<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xsxwkh/jcxmwh/js/jcxmwh.js"></script>
		<script type="text/javascript">
		  var gridSetting = {
				caption:"奖励分维护列表",
				pager:"pager",
				url:"xsxwkhJcxmwh.do?method=jlxmManage&type=query",
				colList:[
				   {label:'代码',name:'dm', index: 'dm',key:true,hidden : true},
				   {label:'项目名称',name:'mc', index: 'mc',width:'30%'},
				   {label:'分值(分)',name:'fz', index: 'fz',width:'30%'},
				   {label:'备注',name:'bz', index: 'bz',width:'40%'}
				],
				sortname: "dm",
			 	sortorder: "asc"
			}
			
			function add(){
				var url = "xsxwkhJcxmwh.do?method=addJlxm";
				var title = "增加";
				showDialog(title,550,300,url);
			}
			function update(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer("请选择一条您要修改的记录！");
				} else {
					var url = 'xsxwkhJcxmwh.do?method=updateJlxm&dm='+rows[0]["dm"];
					var title = "修改";
					showDialog(title,550,300,url);
				}
			}
			
			function newChgCode(obj){
				allNotEmpThenGo(obj.id);
			}
		</script>
	</head>
	<body>
	<html:form action="/xsxwkhJcxmwh" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
			<div class="toolbox">
			<!-- 按钮 -->
			<logic:equal name="writeAble" value="yes">	
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">
						<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">增加</a></li>
						<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
						<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>						
					</logic:equal>
				</ul>
			</div>
			</logic:equal>
			<div class="compTab" id="card">
				<div class="comp_title">
					<ul>
						<li class="ha"><a href="#" onclick="newChgCode(this);return false;" id="xsxwkhJcxmwh.do?method=jlxmManage"><span>奖励分</span></a></li>
						<li ><a href="#" onclick="newChgCode(this);return false;" id="xsxwkhJcxmwh.do?method=cfxmManage"><span>处罚分</span></a></li>
					</ul>
				</div>
			</div>	
			<!-- 过滤条件 -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="12%">名称</th>
						<td width="5%">
							<input type="text" id="mc" name="mc" maxleng="20" 
							onkeypress="if(pressEnter(event)){query();return false;}"
							/>
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
					<span>奖励分维护列表 </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
