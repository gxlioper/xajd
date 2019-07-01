<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/zjcm/wsjc/pfz/js/pfz.js"></script>	
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting1 = {
				caption:"查询结果",
				pager:"pager",
				rowNum:10,
				url:"cjWsfPfz.do?method=showPfcyList&type=query&pfzt=2&pfzid="+'${pfzid}',
				colList:[
						   {label:'用户名',name:'zgh', index: 'zgh',width:'10%',key:true},
						   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
						   {label:'性别',name:'xbmc', index: 'xbmc',width:'5%'},
						   {label:'所属部门',name:'bmmc', index: 'bmmc',width:'20%'}
						],
				sortname: "zgh",
			 	sortorder: "asc",
			}
			var gridSetting2 = {
					caption:"查询结果",
					pager:"pager",
					rowNum:10,
					url:"cjWsfPfz.do?method=showPfcyList&type=query&pfzt=1&pfzid="+'${pfzid}',
					colList:[
							   {label:'用户名',name:'zgh', index: 'zgh',width:'10%',key:true},
							   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
							   {label:'性别',name:'xbmc', index: 'xbmc',width:'5%'},
							   {label:'所属部门',name:'bmmc', index: 'bmmc',width:'20%'}
							],
					sortname: "zgh",
				 	sortorder: "asc",
				}
		jQuery(function(){
			var map = getSuperSearch();
			map["fpzt"]="2";
			gridSetting1["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting1);
		});

		//高级查询
		function searchRs(){
			var fpzt = jQuery("#fpzt").val();
			if (fpzt == "2") {
				var map = getSuperSearch();
				map["fpzt"]=fpzt;
				gridSetting1["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting1);
			}else{
				var map = getSuperSearch();
				map["fpzt"]="1";
				gridSetting2["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting2);
			}
		}
			
		</script>
	</head>

	<body>
		<html:form method="post" styleId="form" action="/cjWsfPfz">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id ="pfzid" value="${pfzid }"/>
			<input type="hidden" id="fpzt" value="2"/>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
				<ul>	
						<li><a href="#" onclick="reBack();return false;" class="btn_fh">返回</a></li>
						<li id="li_fp">
							<a href="javascript:void(0);" onclick="pfzFp();return false;" class="btn_zj">分配</a>
						</li>
						<li id="li_qxfp" style="display: none;">
							<a href="javascript:void(0);" onclick="pfzQxFp();return false;" class="btn_sr">取消分配</a>
						</li>
				</ul>
				</div>
				<table style="" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>组信息</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<th width="15%">组名称</th>
							<td width="85%" >
								${zxxMap.pfzmc }
							</td>
						</tr><tr height="2px"></tr>
					</tbody>		
				</table>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'2');"><span>可分配</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'1');"><span>已分配</span></a></li>
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
