<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				pager:"pager",
				url:"rcswSbglJyjl.do?method=getList",
				colList:[
				   {label:'id',name:'id', index: 'id',hidden:true,key:true},
				   {label:'职工号',name:'zgh', index: 'zgh',formatter:function(v,r){
					   return "<a class='name' href='javascript:view(\""+r["id"]+"\")'>"+v+"</a>";
				   }},
				   {label:'姓名',name:'xm', index: 'xm'},
				   {label:'部门',name:'bmmc', index: 'bmmc'},
				   {label:'设备分类',name:'flmc', index: 'flmc'},
				   {label:'设备名称',name:'sbmc', index: 'sbmc'},
				   {label:'借用时间',name:'jysj', index: 'jysj'},
				   {label:'归还状态',name:'ghzt', index: 'ghzt'},
				   {label:'归还时间',name:'ghsj', index: 'ghsj'}
				],
				sortname: "jysj",
			 	sortorder: "desc"
			};

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			function update(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("请选择一条您要修改的记录！");
				} else {
					showDialog('修改',650,400,'rcswSbglJyjl.do?method=edit&id='+rows[0]["id"]);
				}
			}

			function view(id){
				showDialog('查看',650,400,'rcswSbglJyjl.do?method=view&id='+id);
			}
			
			function del(){
				var ids = jQuery("#dataTable").getSeletIds();

				if (ids.length == 0){
					showAlertDivLayer("请选择您要删除的记录！");
				} else {
					showConfirmDivLayer("您确定要删除该记录吗？",{"okFun":function(){
						jQuery.post("rcswSbglJyjl.do?method=delete",{ids:ids.toString()},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}
			
			function add(){
				showDialog('登记',650,450,'rcswSbglJyjl.do?method=add');;
			}
			
			function ghsb(){
				var ids = jQuery("#dataTable").getSeletIds();
				
				if (ids.length == 0){
					showAlertDivLayer("请选择您要操作的记录！");
				} else {	
					tipsWindownNew("设备归还","id:sbgh",520,200,"",{
						button:[
							{name:"保存",focus: true,callback:function(){
								var ghbz = jQuery("#ghbz",this.content.document).val();
								var ghsj = jQuery("#ghsj",this.content.document).val();
								var ghjbr = jQuery("#ghjbr",this.content.document).val();
								
								jQuery.post("rcswSbglJyjl.do?method=sbgh",{'ghbz':ghbz,'ghsj':ghsj,'ghjbr':ghjbr,'ids':ids.toString()},function(data){
									searchRs();
								});
								
							}},
							{name:"取消",callback:function(){
								
							}}
						]
					});
				}
			}
			
			
			//导出
			function exportConfig(){
				var DCCLBH='rcsw_sbgl.do';
				customExport(DCCLBH, exportData);
			}

			function exportData(){
				var DCCLBH='rcsw_sbgl.do';
				setSearchTj();//设置高级查询条件
				
				var url = "rcswSbglJyjl.do?method=export&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			
			function importJyjl(){
				toImportData("rcsw_sbgl_jyjl");
				return false;
			}
			
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	<html:form action="/rcswSbglJyjl" method="post" styleId="form">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">
					<li><a href="javascript:void(0);" onclick="add()" class="btn_zj">登记</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>						
					<li><a href="javascript:void(0);" onclick="ghsb();" class="btn_gx">归还</a></li>						
					<li><a href="javascript:void(0);" onclick="importJyjl();" class="btn_dr">导入</a></li>	
					</logic:equal>					
					<li><a href="javascript:void(0);" onclick="exportConfig();" class="btn_dc">导出</a></li>						
				</ul>
			</div>
			<!-- 过滤条件 -->	
			<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- 过滤条件 end-->
		</div>
	
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 设备借用记录列表 </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
		</html:form>
		
		<div id="sbgh" style="display:none;">
			<table class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>设备归还</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="20%">
							归还时间
						</th>
						<td width="30%">
							<input type="text" value="${now }" readonly="readonly" name="ghsj" id="ghsj"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})"
							/>
						</td>
						<th align="right" width="20%">
							经办人
						</th>
						<td width="30%">
							${userNameReal }
							<input type="hidden" name="ghjbr" id="ghjbr" value="${userName }"/>
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							归还备注 <br/>
							<font color="red">（限400字）</font>
						</th>
						<td colspan="3">
							<textarea rows="5" style="width:95%;" id="ghbz"
								onblur="checkLen(this,400);"
							></textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>
