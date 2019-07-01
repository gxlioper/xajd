<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/zjcm/wsjc/wsflr/js/wsflr.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/json.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption:"参评学生列表 ",
				pager:"pager",
				url:"cjWsflr.do?method=addWsflr&type=query&ccny=${ccny}",
				params:getSuperSearch()
			};
				
			var colList=[
				   {label:'key',name:'wsfid', index: 'wsfid',hidden:true,key:true},
				   {label:'楼栋',name:'ldmc', index: 'ldmc',width:'15%'},
				   {label:'寝室号',name:'qsh', index: 'qsh',width:'15%'},
				   {label:'床位数',name:'cws', index: 'cws',width:'15%'},
				   {label:'入住人数',name:'rzrs', index: 'rzrs',width:'15%'},
				   {label:'提交状态',name:'tjzt', index: 'tjzt',hidden:true}
				];
						
				var json = {label:'分值',
							name:"fz",
							index:"fz"							
							};
					json["formatter"] = function(cell,rowObject){					
							var html="";													
							if(rowObject["tjzt"] == '1'){
								return cell;
							}else{
								html+= "<input onblur=\"savefs(this,'";
								html+=rowObject["wsfid"];
								html+="','";
								html+=rowObject["qsh"];
								html+="')\" type='text' onkeyup='toNext(this,event);checkInputNum(this);'";
								html+=" style='width:50px;' maxlength='4' value='";
								html+=cell==null ? "" : cell;
								html+="' max='100' min='0'";
								html+=" />";
								return html;
							}																		
					}				
			colList.push(json);
			var json1 = {label:'分值备注',
					name:"fzbz",
					index:"fzbz"							
					};
			json1["formatter"] = function(cell,rowObject){					
					var htmll="";
					if(rowObject["tjzt"] == '1'){
						return cell;
					}else{
						htmll+= "<input onblur=\"saveBz(this,'";
						htmll+=rowObject["wsfid"];
						htmll+="','";
						htmll+=rowObject["qsh"];
						htmll+="')\" type='text' onkeyup='toNext(this,event);'";
						htmll+=" style='width:100px;' maxlength='50' value='";
						htmll+=cell==null ? "" : cell;
						htmll+="'" 
						htmll+=" />";
						return htmll;
					}										
			}				
			colList.push(json1);
			colList.push({label:'提交状态',name:'tjztmc', index: 'tjztmc',width:'7%'});
			gridSetting["colList"] = colList;
			jQuery("#dataTable").initGrid(gridSetting);
		});
		</script>
		
		<style>
			/*当终测项目过多时，本页列表会显示不全。增加列表滚动条显示*/
			.formbox {overflow:scroll}
			.formbox table tbody tr td{white-space:nowrap;}
		</style>
		
	</head>

	<body>		
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>传媒个性化主菜单-卫生分检查（传媒）-卫生分录入</a>
			</p>
		</div>
		<div class="toolbox">
			<input type="hidden" id="ccny" value="${ccny}" />
			<!-- 按钮 -->
			<html:form action="/cjWsflr">
			<%@ include file="/comm/hiddenValue.jsp"%>				
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="fh();iFClose();" class="btn_fh"
					   title="在这里，您可以提交已确认的学生。"
					>返回</a></li>
					<li>
					<li><a href="javascript:void(0);" onclick="Tj();" class="btn_up"
					   title="在这里，您可以提交已确认的学生。"
					>提交</a></li>
					<li>
						<a href="#" onclick="exportConfig();return false;" class="btn_dc">
							导出
						</a>
					</li>
				</ul>
			</div>
			<!-- 过滤条件 -->	
			<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- 过滤条件 end-->
			</html:form>
		</div>
		<div class="formbox" style="overflow: auto;">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span><font color="blue">${cssz.zqmc}&nbsp;</font>参评学生列表 </span>
			</h3>
			<div style="overflow: auto;">
				<table id="dataTable" ></table>
			</div>
			<div id="pager"></div>
		</div>
	</body>
</html>
