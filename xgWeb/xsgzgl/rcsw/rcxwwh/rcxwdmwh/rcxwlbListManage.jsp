<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/rcsw/rcxwwh/rcxwdmwh/js/rcxwlbManage.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
            var xxdm = "${xxdm}";
		function add(){
			var url = "rcsw_rcxwwh_rcxwdmwhgl.do?method=addRcxwlb";
			var title = "增加行为类别";
            if("13431" == xxdm) title="增加加分类别";
			showDialog(title,700,250,url);
		}
		
		function update(){
			var rows = jQuery("#dataTable").getSeletRow();

			if (rows.length != 1){
				showAlertDivLayer("请选择一条您要修改的记录！");
			} else {
				jQuery.post("rcsw_rcxwwh_rcxwdmwhgl.do?method=checkRcxwlb",{rcxwlbdm:rows[0]["rcxwlbdm"]},function(data){
					if(data["message"] == ""){
						var url = 'rcsw_rcxwwh_rcxwdmwhgl.do?method=updateRcxwlb&rcxwlbdm='+rows[0]["rcxwlbdm"];
						var title = "修改行为类别";
                        if("13431" == xxdm) title="修改加分类别";
						showDialog(title,700,250,url);
					}else{
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					}
				},'json');
			}
		}
		
		jQuery(function(){
			titleXwdlmc();
		})
		
		function titleLoad(id){
			if(jQuery("#"+id)){
			jQuery("#"+id).children("option").each(function(){
				jQuery(this).attr("title",jQuery(this).text());
			});
			}
		}

		function titleXwdlmc(){

			setTimeout("titleLoad('rcxwlbdlmc')",500);
		}

		function updateSfqy(sfqy){
			var msg = "启用";
			if(sfqy == '0'){
				msg = "停用";
			}
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length == 0){
				showAlertDivLayer("请选择您要"+msg+"的记录！");
			} else {
				showConfirmDivLayer("您确定要"+msg+"选择的记录吗？",{"okFun":function(){
						jQuery.post("rcsw_rcxwwh_rcxwdmwhgl.do?method=sfqyRcxwlb",{values:ids.toString(),sfqy:sfqy},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
				}});
				
			}
		}
		
		</script>
	</head>
	<body>
	<html:form action="/rcsw_rcxwwh_rcxwdmwhgl" method="post">
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
					<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">增加</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>						
					<li><a href="javascript:void(0);" onclick="updateSfqy('1');" class="btn_shtg">启用</a></li>						
					<li><a href="javascript:void(0);" onclick="updateSfqy('0');" class="btn_shbtg">停用</a></li>
				</ul>
			</div>
			</logic:equal>
			<div class="compTab" id="card">
				<div class="comp_title">
					<ul>
						<li >
							<a href="#" onclick="newChgCode(this);return false;" id="rcsw_rcxwwh_rcxwdmwhgl.do?method=rcxwdlManage">
								<logic:notEqual name="xxdm" value="13431">
									<span>行为大类</span>
								</logic:notEqual>
								<logic:equal name="xxdm" value="13431">
									<span>加分大类</span>
								</logic:equal>
							</a>
						</li>
						<li class="ha">
							<a href="#" onclick="newChgCode(this);return false;" id="rcsw_rcxwwh_rcxwdmwhgl.do?method=rcxwlbManage">
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
						<th width="10%">
							<logic:notEqual name="xxdm" value="13431">
								<span>所属行为大类</span>
							</logic:notEqual>
							<logic:equal name="xxdm" value="13431">
								<span>所属加分大类</span>
							</logic:equal>
						</th>
						<td width="8%">
							<html:select property="rcxwlbdlmc" styleId="rcxwlbdlmc" style="width:130px" >
									<html:option value=""></html:option>
									<html:options collection="xwdlList" property="rcxwlbdlmc" labelProperty="rcxwlbdlmc" />
							</html:select>
						</td>
						<th width="10%" >
							<logic:notEqual name="xxdm" value="13431">
								<span>行为类别名称</span>
							</logic:notEqual>
							<logic:equal name="xxdm" value="13431">
								<span>加分类别名称</span>
							</logic:equal>
						</th>
						<td width="8%">
							<input type="text" id="rcxwlbmc" name="rcxwlbmc" maxleng="20" 
							   onkeypress="if(pressEnter(event)){query();return false;}"
							/>
						</td>
						<th width="10%">
							启用状态
						</th>
						<td width="8%">
							<html:select property="sfqy" style="width:130px" styleId="sfqy">
								<html:option value="">--请选择--</html:option>
								<html:option value="1">启用</html:option>
								<html:option value="0">停用</html:option>
							</html:select>
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
