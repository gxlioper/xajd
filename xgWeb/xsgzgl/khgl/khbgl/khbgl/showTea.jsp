<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/khgl/khdxgl/khdxgl/js/khdxwh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting1 = {
				caption:"查询结果",
				pager:"pager",
				rowNum:10,
				url:"khglKhdxgl.do?method=showKhdxFp&type=query&khlx=2&khdxid="+'${khdxid}',
				colList:[
						   {label:'用户名',name:'yhm', index: 'yhm',width:'10%',key:true},
						   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
						   {label:'性别',name:'xb', index: 'xb',width:'5%'},
						   {label:'所属部门',name:'bmmc', index: 'bmmc',width:'20%'},
						   {label:'用户身份',name:'', index: '',width:'10%'}
						],
				params:{fpzt:"kfp"},
				sortname: "yhm",
			 	sortorder: "asc",
			}
			var gridSetting2 = {
					caption:"查询结果",
					pager:"pager",
					rowNum:10,
					url:"khglKhdxgl.do?method=showKhdxFp&type=query&khlx=2&khdxid="+'${khdxid}',
					colList:[
					   {label:'用户名',name:'yhm', index: 'yhm',width:'10%',key:true},
					   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
					   {label:'性别',name:'xb', index: 'xb',width:'5%'},
					   {label:'所属部门',name:'bmmc', index: 'bmmc',width:'20%'},
					   {label:'用户身份',name:'', index: '',width:'10%'}
					],
					params:{fpzt:"yfp"},
					sortname: "yhm",
				 	sortorder: "asc",
				}
		jQuery(function(){
			var map = getSuperSearch();
			map["fpzt"]="kfp";
			gridSetting1["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting1);
			
			jQuery("#btn_fh").bind("click",function(){
				document.location.href="khglKhdxgl.do?method=getKhdxglList";
			});
		});
			
		</script>
	</head>

	<body>
		<html:form method="post" styleId="form" action="/khglKhdxgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id ="khdxid" value="${khdxid }"/>
			<input type="hidden" id="fpzt" value="kfp"/>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
				<ul>
						<li>
							<a id="btn_fh" class="btn_fh"> 返 回 </a>
						</li>
						<li id="li_fp">
							<a href="javascript:void(0);" onclick="khdxFp('2');return false;" class="btn_zj">分配</a>
						</li>
						<li id="li_dr" style="display: none;">
							<a href="javascript:void(0);" onclick="khdxDr('2');return false;" class="btn_dr">导入</a>
						</li>
						<li id="li_qxfp" style="display: none;">
							<a href="javascript:void(0);" onclick="khdxQxfp('2');return false;" class="btn_sr">取消分配</a>
						</li>
				</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'kfp');"><span>可分配</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'yfp');"><span>已分配</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="toolbox">
					<!--标题start-->
			<h3 class="datetitle_01">
				<span> 查询结果
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
