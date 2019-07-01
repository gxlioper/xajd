<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/gwglnew/js/gwgl.js"></script>
		<script language="javascript" defer="defer">
		function getGridSettiong(){
			var colList = [
	                {label:'key',name:'gwdm', index: 'gwdm',hidden:true,key:true},
					{label:'行号',name:'r', index: 'r',width:'6%'},
					{label:'用人部门',name:'yrdwmc', index: 'yrdwmc',width:'19%'},
					{label:'岗位名称',name:'gwmc', index: 'gwmc',width:'14%'},
					{label:'岗位性质',name:'gwxzmc', index: 'gwxzmc',width:'12%'},
					{label:'岗位类别',name:'bmlb', index: 'bmlb',width:'7%'},
					{label:'需求人数',name:'xqrs', index: 'xqrs',width:'8%'},
					{label:'在岗人数',name:'zgrs', index: 'zgrs',width:'8%'},
					{label:'退岗人数',name:'tgrs', index: 'tgrs',width:'8%'},
					{label:'岗位有效时',name:'gwyxs', index: 'gwyxs',width:'16%'},
					{label:'岗位开始时间',name:'gwkssj', index: 'gwkssj',hidden:true},
					{label:'岗位结束时间',name:'gwjssj', index: 'gwjssj',hidden:true},
					{label:'是否有效岗位',name:'sfyxgw', index: 'sfyxgw',hidden:true},
					{label:'学年',name:'xn', index: 'xn',hidden:true},
					{label:'学期',name:'xq', index: 'xq',hidden:true},
					{label:'sjly',name:'sjly', index: 'sjly',hidden:true}
				];
			var xxdm = jQuery("#xxdm").val();
			if(xxdm == "10052"){
				colList = [
					{label:'key',name:'gwdm', index: 'gwdm',hidden:true,key:true},
					{label:'行号',name:'r', index: 'r',width:'6%'},
					{label:'用人部门',name:'yrdwmc', index: 'yrdwmc',width:'16%'},
					{label:'岗位序号',name:'gwxh', index: 'gwxh',width:'8%'},
					{label:'岗位名称',name:'gwmc', index: 'gwmc',width:'14%'},
					{label:'岗位性质',name:'gwxzmc', index: 'gwxzmc',width:'12%'},
					{label:'需求人数',name:'xqrs', index: 'xqrs',width:'8%'},
					{label:'在岗人数',name:'zgrs', index: 'zgrs',width:'8%'},
					{label:'退岗人数',name:'tgrs', index: 'tgrs',width:'8%'},
					{label:'岗位有效时',name:'gwyxs', index: 'gwyxs',width:'19%'},
					{label:'岗位开始时间',name:'gwkssj', index: 'gwkssj',hidden:true},
					{label:'岗位结束时间',name:'gwjssj', index: 'gwjssj',hidden:true},
					{label:'是否有效岗位',name:'sfyxgw', index: 'sfyxgw',hidden:true},
					{label:'学年',name:'xn', index: 'xn',hidden:true},
					{label:'学期',name:'xq', index: 'xq',hidden:true},
					{label:'sjly',name:'sjly', index: 'sjly',hidden:true}
					
				];
			}else if(xxdm=="10351"){
				colList = [
					{label:'key',name:'gwdm', index: 'gwdm',hidden:true,key:true},
					{label:'行号',name:'r', index: 'r',width:'6%'},
					{label:'用人部门',name:'yrdwmc', index: 'yrdwmc',width:'19%'},
					{label:'岗位名称',name:'gwmc', index: 'gwmc',width:'14%'},
					{label:'岗位性质',name:'gwxzmc', index: 'gwxzmc',width:'12%'},
					{label:'需求人数',name:'xqrs', index: 'xqrs',width:'8%'},
					{label:'在岗人数',name:'zgrs', index: 'zgrs',width:'8%'},
					{label:'困难生</br>需求人数',name:'knsrs', index: 'knsrs',width:'8%'},
					{label:'困难生</br>在岗人数',name:'knszgrs', index: 'knszgrs',width:'8%'},
					{label:'岗位有效时',name:'gwyxs', index: 'gwyxs',width:'16%'},
					{label:'岗位开始时间',name:'gwkssj', index: 'gwkssj',hidden:true},
					{label:'岗位结束时间',name:'gwjssj', index: 'gwjssj',hidden:true},
					{label:'是否有效岗位',name:'sfyxgw', index: 'sfyxgw',hidden:true},
					{label:'学年',name:'xn', index: 'xn',hidden:true},
					{label:'学期',name:'xq', index: 'xq',hidden:true},
					{label:'sjly',name:'sjly', index: 'sjly',hidden:true}
					
				];
			}
			
			var gridSetting = {
				caption:"结果列表",
				pager:"pager",
				url:"qgzx_gwglnew.do?method=gwxxCx&type=query",
				sortname: "r",
			 	sortorder: "asc"
			}
			gridSetting["colList"] = colList;
			return gridSetting;				
		}
		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}
		//初始化
		jQuery(document).ready(function(){ 
			var gridSetting = getGridSettiong();
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
		});

		//复制
		function copyGwxx(){
			var ids = jQuery("#dataTable").getSeletIds();
			var len = ids.length;
			if(len>=1){
				var num = 0;
				var str = "";
				var idList = "";
				var rows = jQuery("#dataTable").getSeletRow();
				for(var i=0;i<ids.length;i++){
					var xn = rows[i]["xn"];
					var qgzq = jQuery("#qgzq").val();
					if(qgzq == "xq" ){
						xn = xn+ rows[i]["xq"];
					}
					if(str.indexOf(xn)==-1){
						str += xn+"!!@@!!";
						num++;
					}
					var gwdm = rows[i]["gwdm"];
					idList += gwdm + "!!@@!!";
				}
				url = "qgzx_gwglnew_ajax.do?method=gwxxFz&str="+str+"&num="+num+"&len="+len +"&idList="+idList;
				showDialog('复制岗位', 380, 390, url)
			}else{
				showAlertDivLayer("请选择您要复制的记录！");
			}
		}
		</script>
	</head>
	<body>

		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/qgzx_gwglnew" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="gwdmPlHidden" value="" />
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
            <input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
            <input type="hidden"  id="qgzq" name="qgzq" value="${qgzq}" />
            
			<!-- 隐藏域 -->
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<logic:equal name="sfQggly" value="1">
						<li> 
							<a href="javascript:void(0);" onclick="showDialog('增加岗位', 760, 520, 'qgzx_gwglnew.do?method=gwxxZj');return false;" class="btn_zj">增加岗位</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="showModi('gwxxXg','修改岗位');return false;" class="btn_xg">修改岗位</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="deleteGwxx();return false;" class="btn_sc">删除岗位</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="copyGwxx();return false;" class="btn_fz">复制岗位</a>
						</li>
						</logic:equal>
						<logic:equal value="10407" name="xxdm">
                        <li>
                            <a href="javascript:;" onclick="gwxxDr();return false;" id="btn_dr" class="btn_dr">岗位导入</a>
                       </li>
						 <li>
                            <a href="javascript:;" onclick="gwryDr();return false;" id="btn_dr" class="btn_dr">岗位人员导入</a>
                       </li>
						</logic:equal>
						</logic:equal>
						<logic:equal name="xxdm" value="10022">
						<li>
							<a href="javascript:void(0);" onclick="showModi('ryxxZj','人员增减');return false;" class="btn_zj">人员增减</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="showModi('ryxxTg','人员退岗');return false;" class="btn_sc">人员退岗</a>
						</li>
						</logic:equal>
						<logic:equal name="xxdm" value="10052">
						<li>
							<a href="javascript:void(0);" onclick="showModi('ryxxZj','人员增减');return false;" class="btn_zj">人员增减</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="showModi('ryxxTg','人员退岗');return false;" class="btn_sc">人员退岗</a>
						</li>
						</logic:equal>
						<logic:notEqual name="xxdm" value="10022">
						<logic:notEqual name="xxdm" value="10052">
						<logic:notEqual name="xxdm" value="10351">
						<logic:equal name="writeAble" value="yes">
						<logic:equal name="sfQggly" value="1">
						<logic:notEqual name="xxdm" value="11799">
						<li>
							<a href="javascript:void(0);" onclick="showModi('ryxxZj','人员增减');return false;" class="btn_zj">人员增减</a>
						</li>
						</logic:notEqual>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="showModi('ryxxTg','人员退岗');return false;" class="btn_sc">人员退岗</a>
						</li>
						</logic:equal>
						</logic:notEqual>
						</logic:notEqual>
						</logic:notEqual>
						<logic:equal name="xxdm" value="10351">
							<logic:equal name="writeAble" value="yes">
								<logic:equal name="sfQggly" value="1">
									<li>
										<a href="javascript:void(0);" onclick="showModi('ryxxZj','人员增减');return false;" class="btn_zj">人员增减</a>
									</li>
								</logic:equal>
							</logic:equal>
							<li>
								<a href="javascript:void(0);" onclick="showModi('ryxxTg','人员退岗');return false;" class="btn_sc">人员退岗</a>
							</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" class="btn_ck" onclick="showModi('gwxxCk','查看明细');return false;">查看明细</a>
						</li>
					</ul>
				</div>
				
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
			</html:form>
			<!-- 多功能操作区 end-->

			<div class="main_box">
				<h3 class=datetitle_01>
					<span>结果列表&nbsp;&nbsp; </span>
				</h3>
				<div class="con_overlfow">
					<table id="dataTable" ></table>
					<div id="pager"></div>
				</div>
			</div>
	</body>
</html>