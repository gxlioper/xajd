<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
		  
		 	String.prototype.replaceAll = function(s1,s2) { 
			    return this.replace(new RegExp(s1,"gm"),s2); 
			}
			function format(cellValue,rowObject){
				if(cellValue==null){
					return cellValue;
				}else{
					return cellValue.replaceAll('\\n','<br>');
				}
			}

			jQuery(function(){
				  var xxdm = jQuery("#xxdm").val();
				
				  var gridSetting;

				  if(xxdm == '10704'){
					gridSetting = {
						caption:"综合测评小类列表",
						pager:"pager",
						url:"rcsw_rcxwwhnew_rcxwdmwhgl.do?method=rcxwxlManage&type=query",
						colList:[
						   {label:'综合测评小类代码',name:'rcxwlbxldm', index: 'rcxwlbxldm',key:true,width:'15%'},
						   {label:'综合测评小类名称',name:'rcxwlbxlmc', index: 'rcxwlbxlmc',width:'18%'},
						   {label:'所属综合测评大类',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'18%'},
						   {label:'所属综合测评类别',name:'rcxwlbmc', index: 'rcxwlbmc',width:'18%'},
						   {label:'分值类型',name:'fzlxmc', index: 'fzlxmc',width:'7%'},
						   {label:'分值',name:'fzqj', index: 'fzqj',width:'12%'},
						   {label:'评分说明',name:'rcxwlbbz', index: 'rcxwlbbz',width:'30%',formatter:format},
						   {label:'所属综合测评大类',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
						   {label:'启用状态',name:'sfqymc', index: 'sfqymc',width:'10%'}
						],
						sortname: "rcxwlbxldm",
					 	sortorder: "asc"
					 }
				  }else{
				    gridSetting = {
						caption:"行为小类列表",
						pager:"pager",
						url:"rcsw_rcxwwhnew_rcxwdmwhgl.do?method=rcxwxlManage&type=query",
						colList:[
						   {label:'行为小类代码',name:'rcxwlbxldm', index: 'rcxwlbxldm',key:true,width:'10%'},
						   {label:'行为小类名称',name:'rcxwlbxlmc', index: 'rcxwlbxlmc',width:'14%'},
						   {label:'所属行为大类',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'14%'},
						   {label:'所属行为类别',name:'rcxwlbmc', index: 'rcxwlbmc',width:'14%'},
                           {label:'所属学院',name:'ssxymc', index: 'ssxymc',width:'14%'},
						   {label:'分值类型',name:'fzlxmc', index: 'fzlxmc',width:'6%'},
						   {label:'分值',name:'fzqj', index: 'fzqj',width:'6%'},
						   {label:'评分说明',name:'rcxwlbbz', index: 'rcxwlbbz',width:'15%',formatter:format},
						   {label:'所属行为大类',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
                           {label:'所属学院代码',name:'ssxydm', index: 'ssxydm',hidden:true},
						   {label:'启用状态',name:'sfqymc', index: 'sfqymc',width:'7%'}
						],
						sortname: "rcxwlbxldm",
					 	sortorder: "asc"
					} 
				  }				
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function query(){
				var map = {};
				map["rcxwlbdldm"] = jQuery("#rcxwlbdldm").val();
				map["rcxwlbxlmc"] = jQuery("#rcxwlbxlmc").val();
				map["sfqy"] = jQuery("#sfqy").val();
                map["ssxydm"] = jQuery("#ssxydm").val();
				jQuery("#dataTable").reloadGrid(map);
			}
			
			function add(){
				var url = "rcsw_rcxwwhnew_rcxwdmwhgl.do?method=addRcxwxl";
				var title;
				if(jQuery("#xxdm").val() == '10704'){
					title = "增加综合测评小类";
				}else{
					title = "增加行为小类";
				}
				showDialog(title,700,310,url);
			}
			
			function update(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("请选择一条您要修改的记录！");
					return false;
				}
                if(!checkSsxydm(rows[0]["ssxydm"])) return false;
                jQuery.post("rcsw_rcxwwhnew_rcxwdmwhgl.do?method=checkRcxwxl",{rcxwlbxldm:rows[0]["rcxwlbxldm"]},function(data){
                    if(data["message"] == ""){
                        var url = 'rcsw_rcxwwhnew_rcxwdmwhgl.do?method=updateRcxwxl&rcxwlbxldm='+rows[0]["rcxwlbxldm"];
                        var title;
                        if(jQuery("#xxdm").val() == '10704'){
                            title = "修改综合测评小类";
                        }else{
                            title = "修改行为小类";
                        }
                        showDialog(title,700,310,url);
                    }else{
                        showAlertDivLayer(data["message"]);
                        jQuery("#dataTable").reloadGrid();
                    }
                },'json');
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
                    var rows = jQuery("#dataTable").getSeletRow();
                    if(!checkSsxydm(rows[0]["ssxydm"])) return false;
					showConfirmDivLayer("您确定要"+msg+"选择的记录吗？",{"okFun":function(){
							jQuery.post("rcsw_rcxwwhnew_rcxwdmwhgl.do?method=sfqyRcxwxl",{values:ids.toString(),sfqy:sfqy},function(data){
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							},'json');
					}});
				}
			}
			
			function del(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0){
					showAlertDivLayer("请选择您要删除的记录！");
				} else {
					showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
							jQuery.post("rcsw_rcxwwhnew_rcxwdmwhgl.do?method=delRcxwxl",{values:ids.toString()},function(data){
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							},'json');
					}});
				}
			}
			function newChgCode(obj){
				allNotEmpThenGo(obj.id);
			}
			function  checkSsxydm(ssxydm) {
                var userDep = jQuery("#userDep").val();
                if(ssxydm != userDep){
                    if("qx" == userDep){
                        showAlertDivLayer("只选择所属学院为全校的记录！");
                    }else{
                        showAlertDivLayer("只能选择所属学院为本学院的记录！");
                    }
                    return false;
                }
                return true;
            }
		</script>
	</head>
	<body>
	<input type="hidden" id="xxdm" value="${xxdm}" />
	<input type="hidden" name="userDep" id="userDep" value="${userDep}"/>
	<html:form action="/rcsw_rcxwwhnew_rcxwdmwhgl" method="post">
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
						<li>
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
						<li class="ha">
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
						<th width="12%">
							<logic:equal value="10704" name="xxdm">
								所属综合测评大类
							</logic:equal>
							<logic:notEqual value="10704" name="xxdm">									
								所属行为大类
							</logic:notEqual>							
						</th>
						<td width="12%">
							<select id="rcxwlbdldm" style="width: 150px;">
								<option value=""></option>
								<logic:iterate id="rcxwlbdlByYhsq" name="rcxwlbdlListByYhsq" >
									<option value="${rcxwlbdlByYhsq.rcxwlbdldm }" title="${rcxwlbdlByYhsq.rcxwlbdlmc }">${rcxwlbdlByYhsq.rcxwlbdlmc }</option>
								</logic:iterate>
							</select>
						</td>
						<th width="12%">
							<logic:equal value="10704" name="xxdm">
								综合测评小类名称
							</logic:equal>
							<logic:notEqual value="10704" name="xxdm">									
								行为小类名称
							</logic:notEqual>							
						</th>
						<td width="12%">
							<input type="text" id="rcxwlbxlmc" onkeypress="if(pressEnter(event)){query();return false;}"
							/>
						</td>
						<td>
							<div style="margin-left: 50px;">
								<button type="button" class="btn_cx" id="search_go" onclick="query()">
									查 询
								</button>
							</div>
						</td>
					</tr>
					<tr>
						<th width="10%">
							所属学院
						</th>
						<td width="12%">
							<html:select property="ssxydm" style="width:150px" styleId="ssxydm">
								<html:option value=""> </html:option>
								<html:option value="qx">全校</html:option>
								<html:options collection="bmList" property="bmdm" labelProperty="bmmc"/>
							</html:select>
						</td>
						<th width="10%">
							启用状态
						</th>
						<td width="12%">
							<html:select property="sfqy" style="width:150px" styleId="sfqy">
								<html:option value="">--请选择--</html:option>
								<html:option value="1">启用</html:option>
								<html:option value="0">停用</html:option>
							</html:select>
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
							综合测评小类列表
						</logic:equal>
						<logic:notEqual value="10704" name="xxdm">
							行为小类列表 
						</logic:notEqual>
					</span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
