<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "gyjc_jcjglr.do?method=getJcjgLrcxList&type=query",
				colList : [ {
					label : 'key',
					name : 'mxid',
					index : 'mxid',
					key : true,
					hidden : true
				}, {
					label : 'key',
					name : 'rcid',
					index : 'rcid',
					hidden : true
				}, {
					label : '学院',
					name : 'xymc',
					index : 'xymc',
					width : '10%'
				}, {
					label : '楼栋',
					name : 'ldmc',
					index : 'ldmc',
					width : '10%'
				}, {
					label : '寝室',
					name : 'qsh',
					index : 'qsh',
					width : '10%'
				}, {
					label : '类别',
					name : 'jjlxmc',
					index : 'jjlxmc',
					width : '10%'
				}
				<logic:notEqual value="wtjscx" name="JcjglrForm" property="flag">
				, {
					label : '文明寝室等级',
					name : 'dj',
					index : 'dj',
					width : '10%'
				},
				{
					label : '不达标说明',
					name : 'xh',
					index : 'xh',
					width : '10%'
				},
				<logic:equal value="lr" name="JcjglrForm" property="flag">
				{
					label : '提交状态',
					name : 'tjztmc',
					index : 'tjztmc',
					width : '10%'
				},
				</logic:equal>
				{
					label : 'lddm',
					name : 'lddm',
					index : 'lddm',
					hidden : true
				},
				{
					label : 'tjzt',
					name : 'tjzt',
					index : 'tjzt',
					hidden : true
				},
				{
					label : 'xydm',
					name : 'xydm',
					index : 'xydm',
					hidden : true
				}
				</logic:notEqual>
				<logic:equal value="ytjscx" name="JcjglrForm" property="flag">
				, {
					label : '检查人',
					name : 'tjrxm',
					index : 'tjrxm',
					width : '10%'
				}, {
					label : '检查时间',
					name : 'tjsj',
					index : 'tjsj',
					width : '10%'
				}
				
				</logic:equal>
				<logic:equal value="jgcx" name="JcjglrForm" property="flag">
				, {
					label : '检查人',
					name : 'tjrxm',
					index : 'tjrxm',
					width : '10%'
				}, {
					label : '检查时间',
					name : 'tjsj',
					index : 'tjsj',
					width : '10%'
				}
				
				</logic:equal>
				],
				radioselect:false
			}
			var map = getSuperSearch();
			map['rcid'] = jQuery("#rcid").val();
			map['tjzt'] = jQuery("#tjzt").val();
			map['flag'] = jQuery("#flag").val();
			map['tjr'] = jQuery("#tjr").val();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
		var DCCLBH = "gyjc_jcjglr.do?method=getJcjgLrcxList";//dcclbh,导出功能编号
		//导出
		function dc(){
			customExport(DCCLBH, lrjgExportData);
		}
		//导出方法
		function lrjgExportData() {
			setSearchTj();//设置高级查询条件
			var url = "gyjc_jcjglr.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		//录入
		function lr(){
			var rows = jQuery("#dataTable").getSeletRow();
			if(rows.length != 1){
				return showAlertDivLayer("请选择一条记录！");
			}
			if(rows[0]["tjzt"] != "0"){
				return showAlertDivLayer("已提交的状态不允许修改！");
			}
			var url = 'gyjc_jcjglr.do?method=addJcJgLr&rcid='+rows[0]["rcid"]+"&xydm="+rows[0]["xydm"]+"&qsh="+rows[0]["qsh"]+"&lddm="+rows[0]["lddm"]+"&js="+jQuery("#js").val(); 
            var title = "检查结果录入";
            showDialog(title, 770, 552, url);
		}

		/**
		 * 查看检查结果
		 * @return
		 */
		function jcjglrck(){
			var rows = jQuery("#dataTable").getSeletRow();
			if(rows.length != 1){
				return showAlertDivLayer("请选择一条记录！");
			}
			var url = 'gyjc_jcjglr.do?method=ckJcJgLr&rcid='+rows[0]["rcid"]+"&xydm="+rows[0]["xydm"]+"&qsh="+rows[0]["qsh"]+"&lddm="+rows[0]["lddm"]+"&js="+jQuery("#js").val()+"&flag="+jQuery("#flag").val(); 
            var title = "检查结果录入";
            showDialog(title, 770, 552, url);
		}

		//提交
		function tj(){
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length == 0){
				return showAlertDivLayer("请选择至少一条您要提交的记录！");
			}
			showConfirmDivLayer("您确定要提交选择的记录吗？",{"okFun":function(){
				jQuery.post("gyjc_jcjglr.do?method=submitRecord",{mxids:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}});
		}

		//撤销
		function cancel(){
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length == 0){
				return showAlertDivLayer("请选择至少一条您要撤销的记录！");
			}
			showConfirmDivLayer("您确定要撤销选择的记录吗？",{"okFun":function(){
				jQuery.post("gyjc_jcjglr.do?method=cxRecord",{mxids:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}});
		}

		//返回
		function fh(){
			document.location.href = 'xg_gyjc_jcjglr.do';
		}

		function searchRs() {
			var map = getSuperSearch();
			map['rcid'] = jQuery("#rcid").val();
			map['tjzt'] = jQuery("#tjzt").val();
			map['flag'] = jQuery("#flag").val();
			map['tjr'] = jQuery("#tjr").val();
			jQuery("#dataTable").reloadGrid(map);
		}
		</script>
	</head>

	<body>
	<logic:equal value="jgcx" name="JcjglrForm" property="flag"> 
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	</logic:equal>
		<html:form action="/gyjc_jcjglr">
			<html:hidden property="rcid" styleId="rcid" />
			<html:hidden property="tjzt" styleId="tjzt" />
			<html:hidden property="flag" styleId="flag" />
			<html:hidden property="tjr" styleId="tjr" />
			<html:hidden property="js" styleId="js"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal value="lr" name="JcjglrForm" property="flag"> 
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" class="btn_fh" onclick="fh();return false;"  >返回</a>
						</li>
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="lr();return false;"  >检查项录入</a>
						</li>
						<li>
							<a href="javascript:void(0);" class="btn_ck" onclick="jcjglrck();return false;"  >查看</a>
						</li>
						<li>
							<a href="javascript:void(0);" class="btn_tj" onclick="tj();return false;"  >提交</a>
						</li>
						<li>
							<a href="javascript:void(0);" class="btn_dc" onclick="dc();return false;"  >导出</a>
						</li>
						
					</ul>
				</div>
				</logic:equal>
				<logic:equal value="jgcx" name="JcjglrForm" property="flag"> 
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_qxsh" onclick="cancel();return false;"  >撤销</a>
						</li>
					</logic:equal>
						<li>
							<a href="javascript:void(0);" class="btn_ck" onclick="jcjglrck();return false;"  >查看</a>
						</li>
						<li>
							<a href="javascript:void(0);" class="btn_dc" onclick="dc();return false;"  >导出</a>
						</li>
					</ul>
				</div>
				</logic:equal>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>检查日程列表&nbsp;&nbsp;<font color="blue">用于卫生检查，安全检查，纪律检查</font> </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
