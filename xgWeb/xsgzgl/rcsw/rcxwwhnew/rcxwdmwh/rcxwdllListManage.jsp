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

		  if(xxdm == '10704'){
			  gridSetting = {
				  caption:"综合测评大类列表",
					pager:"pager",
					url:"rcsw_rcxwwhnew_rcxwdmwhgl.do?method=rcxwdlManage&type=query",
					colList:[
					   {label:'综合测评大类代码',name:'rcxwlbdldm', index: 'rcxwlbdldm',key:true,width:'14%'},
					   {label:'综合测评大类名称',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'25%'},
					   {label:'所属综合测评类别',name:'rcxwlbmc', index: 'rcxwlbmc',width:'24%'},
					   {label:'审核流程',name:'lcxx', index: 'lcxx',width:'27%'},
					   {label : '申请开关',name : 'sqkg',index : 'sqkg',width : '10%',formatter:setSqkg},
					   {label : '申请开始时间',name : 'sqkssj',index : 'sqkssj',hidden : true},
					   {label : '申请结束时间',name : 'sqjssj',index : 'sqjssj',hidden : true}
					],
					sortname: "rcxwlbdldm",
				 	sortorder: "asc"
			  }
		  }else{
			  gridSetting = {
					caption:"行为大类列表",
					pager:"pager",
					url:"rcsw_rcxwwhnew_rcxwdmwhgl.do?method=rcxwdlManage&type=query",
					colList:[
					   {label:'行为大类代码',name:'rcxwlbdldm', index: 'rcxwlbdldm',key:true,width:'12%'},
					   {label:'行为大类名称',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'21%'},
					   {label:'所属行为类别',name:'rcxwlbmc', index: 'rcxwlbmc',width:'21%'},
					   {label:'审核流程',name:'lcxx', index: 'lcxx',width:'25%'},
                        <logic:equal value="10699" name="xxdm">
                        {label:'所属学院',name:'ssxymc', index: 'ssxymc',width:'20%'},
                        {label:'ssxydm',name:'ssxydm', index: 'ssxydm',hidden : true},
                        </logic:equal>
					   {label : '申请开关',name : 'sqkg',index : 'sqkg',width : '10%',formatter:setSqkg},
					   {label : '申请开始时间',name : 'sqkssj',index : 'sqkssj',hidden : true},
					   {label : '申请结束时间',name : 'sqjssj',index : 'sqjssj',hidden : true}
					],
					sortname: "rcxwlbdldm",
				 	sortorder: "asc"
			}
		  }
		  
		   
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function query(){
				var map = {};
				map["rcxwlbdlmc"] = jQuery("#rcxwlbdlmc").val();
				map["rcxwlbdm"] = jQuery("#rcxwlbdm").val();
                if(xxdm = '10704')
                	map["ssxydm"] = jQuery("#ssxydm").val();
				jQuery("#dataTable").reloadGrid(map);
			}
			function add(){
				var url = "rcsw_rcxwwhnew_rcxwdmwhgl.do?method=addRcxwdl";
				var title; 
				if(xxdm = '10704'){
					title = "增加综合测评大类";
				}else{
					title = "增加行为大类";
				}
				showDialog(title,470,200,url);
			}
			function update(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer("请选择一条您要修改的记录！");
					return false;
				}
                var userDep = jQuery("#userDep").val();
                if(rows[0]["ssxydm"] != userDep){
                    if("qx" == userDep){
                        showAlertDivLayer("只能修改所属学院为全校的记录！");
                    }else{
                        showAlertDivLayer("只能修改所属学院为本学院的记录！");
                    }
                    return false;
                }
                var url = 'rcsw_rcxwwhnew_rcxwdmwhgl.do?method=updateRcxwdl&rcxwlbdldm='+rows[0]["rcxwlbdldm"];
                var title;
                if(xxdm = '10704'){
                    title = "修改综合测评大类";
                }else{
                    title = "修改行为大类";
                }
                showDialog(title,470,250,url);
			}
			function del(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0){
					showAlertDivLayer("请选择您要删除的记录！");
				} else {
					showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
							jQuery.post("rcsw_rcxwwhnew_rcxwdmwhgl.do?method=delRcxwdl",{values:ids.toString()},function(data){
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							},'json');
					}});
				}
			}
			function newChgCode(obj){
				allNotEmpThenGo(obj.id);
			}
			/*
			 *申请开关 
			 */
			function setSqkg(cellValue,rowObject){
				var rcxwlbdldm = rowObject.rcxwlbdldm;
				var value = "未开启";
				var status = '0';
				var color;
				if(cellValue == '1'){
					var currDate = jQuery("#currDate").val();
					if(rowObject.sqkssj != null && currDate < rowObject.sqkssj || rowObject.sqjssj != null && currDate > rowObject.sqjssj ){
					}else{
						value = "已开启";
						status = '1';
					}
				}
				value = setColor(value,status);
				value = "<a  href='javascript:void(0);' onclick='return sjkg(\""+rcxwlbdldm+"\");' >"+value+"</a>";
				return value;
			}
			//由于外层样式影响，颜色必须写在元素上
			function setColor(value,status){
				var color;
				if(status == '1'){
					color = "#004400";
				}else{
					color = "red";
				}
				return value = "<font color='"+color+"'>" + value + "</font>";
			}
			/*
			 * 时间开关
			 */
			function sjkg(rcxwlbdldm) {
				if(rcxwlbdldm == null){//点击按钮
					var rows = jQuery("#dataTable").getSeletRow();
					if (rows.length != 1) {
						showAlertDivLayer("请选择一条您要设置的记录！");
						return false;
					}
					rcxwlbdldm = rows[0]["rcxwlbdldm"];
				}
				var url = 'rcsw_rcxwwhnew_rcxwdmwhgl.do?method=rcxwlbdldmSjkg&rcxwlbdldm=' + rcxwlbdldm;
				var title = "申请时间控制";
				showDialog(title, 600, 230, url);
			}
		</script>
	</head>
	<body>
	<input type="hidden" id="xxdm" value="${xxdm}" />
	<input type="hidden" id="userDep" value="${userDep}"/>
	<html:form action="/rcsw_rcxwwhnew_rcxwdmwhgl" method="post">
		<input type="hidden" name="currDate" id="currDate" value="${currDate}"/>
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
						<li class="ha">
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
						<th width="10%">
							<logic:equal value="10704" name="xxdm">
								所属综合测评类别
							</logic:equal>
							<logic:notEqual value="10704" name="xxdm">
								所属行为类别
							</logic:notEqual>
						</th>
						<td width="5%">
							<select id="rcxwlbdm" style="width: 150px;">
								<option value=""></option>
								<logic:iterate id="rcxwlbByYhsq" name="rcxwlbListByYhsq" >
									<option value="${rcxwlbByYhsq.rcxwlbdm }" title="${rcxwlbByYhsq.rcxwlbmc }">${rcxwlbByYhsq.rcxwlbmc }</option>
								</logic:iterate>
							</select>
						</td>
						<th width="10%">
							<logic:equal value="10704" name="xxdm">
								综合测评大类名称
							</logic:equal>
							<logic:notEqual value="10704" name="xxdm">
								行为大类名称
							</logic:notEqual>							
						</th>
						<td width="5%">
							<input type="text" id="rcxwlbdlmc" name="rcxwlbdlmc" maxleng="20" 
							onkeypress="if(pressEnter(event)){query();return false;}"
							/>
						</td>
						<th width="10%">
							所属学院
						</th>
						<td width="5%">
							<html:select property="ssxydm" style="width:180px" styleId="ssxydm">
								<html:option value=""> </html:option>
								<html:option value="qx">全校</html:option>
								<html:options collection="bmList" property="bmdm" labelProperty="bmmc"/>
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
					<span>
						<logic:equal value="10704" name="xxdm">
							综合测评大类列表
						</logic:equal>
						<logic:notEqual value="10704" name="xxdm">
							行为大类列表
						</logic:notEqual>					
					 </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
