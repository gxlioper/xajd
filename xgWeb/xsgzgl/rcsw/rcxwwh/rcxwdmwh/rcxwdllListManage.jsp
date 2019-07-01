<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/rcxwwh/rcxwdmwh/js/rcxwdlManage.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>

		<script type="text/javascript">
         var xxdm = "${xxdm}";
		function add(){
			var url = "rcsw_rcxwwh_rcxwdmwhgl.do?method=addRcxwdl";
			var title = "增加行为大类";

			if("13431" == xxdm) title="增加加分大类";
			showDialog(title,470,180,url);
		}
		
		function update(){
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1){
				showAlertDivLayer("请选择一条您要修改的记录！");
			} else {
				jQuery.post("rcsw_rcxwwh_rcxwdmwhgl.do?method=checkRcxwdl",{rcxwlbdldm:rows[0]["rcxwlbdldm"]},function(data){
					if(data["message"] == ""){
						var url = 'rcsw_rcxwwh_rcxwdmwhgl.do?method=updateRcxwdl&rcxwlbdldm='+rows[0]["rcxwlbdldm"];
						var title = "修改行为大类";
                        if("13431" == xxdm) title="修改加分大类";
						showDialog(title,470,180,url);
					}else{
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					}
				},'json');
				
			}
		}
		</script>
		
	</head>
	<body>
	<html:form action="/rcsw_rcxwwh_rcxwdmwhgl" method="post">
		<input type="hidden" name="currDate" id="currDate" value="${currDate}"/>
		<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}"/>
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
						<li class="ha">
							<a href="#" onclick="newChgCode(this);return false;"
							   		id="rcsw_rcxwwh_rcxwdmwhgl.do?method=rcxwdlManage">
								<logic:notEqual name="xxdm" value="13431">
									<span>行为大类</span>
								</logic:notEqual>
								<logic:equal name="xxdm" value="13431">
									<span>加分大类</span>
								</logic:equal>
							</a>
						</li>
						<li>
							<a href="#" onclick="newChgCode(this);return false;"
							   		id="rcsw_rcxwwh_rcxwdmwhgl.do?method=rcxwlbManage">
								<logic:notEqual name="xxdm" value="13431">
									<span>行为类别</span>
								</logic:notEqual>
								<logic:equal name="xxdm" value="13431">
									<span>加分类别</span>
								</logic:equal>
							</a>
						</li>
					</ul>
				</div>
			</div>	
			<!-- 过滤条件 -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="12%">
							<logic:notEqual name="xxdm" value="13431">
								行为大类名称
							</logic:notEqual>
							<logic:equal name="xxdm" value="13431">
								加分大类名称
							</logic:equal>
						</th>
						<td width="5%">
							<input type="text" id="rcxwlbdlmc" name="rcxwlbdlmc" maxleng="20" 
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
					<logic:equal name="xxdm" value="13815">	
						<span>素质学分代码维护列表&nbsp;&nbsp; </span>
					</logic:equal>
					<logic:notEqual name="xxdm" value="13815">
						<logic:notEqual name="xxdm" value="13431">
							<span>日常行为代码维护列表&nbsp;&nbsp; </span>
						</logic:notEqual>
					</logic:notEqual>
					<logic:equal name="xxdm" value="13431">
						<span>加分申请代码维护列表&nbsp;&nbsp; </span>
					</logic:equal>
				</h3>
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
