<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="xsxx/comm/xjydnew/js/xjydlb.js"></script>
		<script language="javascript" src="xsgzgl/comm/bbdmpz/js/commBbdm.js"></script>
		<script type="text/javascript">

 	   var gridSetting = {
			caption:"学籍异动类别审核配置列表",
			pager:"pager",
			url:"xjyd.do?method=xjydlbShpzList&type=query",
			colList:[
			   {label:'xjlbdm',name:'xjlbdm', index: 'xjlbdm',key:true,hidden:true},
			   {label:'dybb',name:'dybb', index: 'dybb',hidden:true},
			   {label:'学籍异动<br>类别名称',name:'xjlbmc', index: 'xjlbmc'},
			   {label:'是否有学籍',name:'sfyxjmc', index: 'sfyxj'},
			   {label:'是否在校',name:'sfzxmc', index: 'sfzx'},
			   {label:'调整班级',name:'sftjbjmc', index: 'sftjbjmc'},
			   {label:'录入起止<br>时间',name:'lrqzsjmc', index: 'lrqzsjmc'},
			   {label:'审核流程',name:'shlcmc', index: 'shlcmc'},
			   {label:'可申请<br>开关',name:'sfksqmc', index: 'sfksq',width:'5%'},
			   {label:'可申请起止时间',name:'sqqzsj', index: 'sqqzsj'}
			],
			sortname: "xjlbdm",
		 	sortorder: "asc"
		}


		jQuery(function(){
			jQuery("#dataTable").initGrid(gridSetting);
		});

		function query(){
			var map = {};
			map["xjlbmc"] = jQuery("#xjlbmc").val();
			jQuery("#dataTable").reloadGrid(map);
		}

		
		function bbsz(){
			var rows = jQuery("#dataTable").getSeletRow();
			
			if (rows.length != 1) {
				showAlertDivLayer("请选择您要设置登记表的项目！");
			} else {
				var type = rows[0]['xjlbmc'];
				commBbdm({
					ywid:rows[0]['xjlbdm'], //设置报表字段的表的主键 ，可以是组合的
					h_title: type + ' 登记表配置', //向选择报表页面传参
					mkdm:'xjydshlb.do', //模块代码，唯一
					thlj: jQuery('#hidden_path').val()
				});
			}
		}

					
		
		</script>
	</head>
	<body>
		<input type="hidden" value="${path}" id="hidden_path"/>
		<%@ include file="/xsgzgl/comm/bbdmpz/csszForm.jsp" %>
		
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
					  <logic:equal value="yes" name="writeAble">
						<li><a href="javascript:void(0);" onclick="addShpz();" class="btn_zj">增加</a></li>
						<li><a href="javascript:void(0);" onclick="updateShpz();" class="btn_xg">修改</a></li>
						<li><a href="javascript:void(0);" onclick="delShpz();" class="btn_sc">删除</a></li>		
						<li><a href="javascript:void(0);" onclick="bbsz();" class="btn_sz">登记表设置</a></li>		
					  </logic:equal>
					</ul>
				</div>
			</logic:equal>
			<!-- 过滤条件 -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="20%">学籍异动类别名称</th>
						<td>
							<input type="text" id="xjlbmc" name="xjlbmc" maxleng="25" 
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
				<span> 学籍异动类别审核配置列表 </span>
			</h3>	
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>	
	</body>
</html>
