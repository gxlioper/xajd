<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/khgl/khpf/js/khpf.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" >
			var gridSetting = {
					caption : "考核对象列表",
					pager : "pager",
					radioselect:false,
					url : "khglKhpf.do?method=bzpf&type=query",
					colList : [
					   {label : 'xmid',name : 'xmid',index : 'xmid' ,hidden:true}, 
					   {label : 'khbid',name : 'khbid',index : 'khbid' ,hidden:true},
					   {label : '学号',name : 'xh',index : 'xh',width : '15%',key:true},
					   {label : '姓名',name : 'xm',index : 'xm',width : '10%'},
					   {label : '班级',name : 'bjmc',index : 'bjmc',width : '20%'},
					   {label : '自评分',name : 'zpzf',index : 'zpzf',width : '8%'},
                       {label : '状态',name : 'zpyp',index : 'zpyp',width : '10%'},
					   {label : '班组评分',name : 'bzzf',index : 'bzzf',width : '8%'},
					   {label : '名次',name : 'pm',index : 'pm',width : '8%'},
					   {label : '状态',name : 'bztjzt',index : 'bztjzt',width : '10%',hidden:true},
					   {label : '审核状态',name : 'bzshzt',index : 'bzshzt',width : '10%',hidden:true},
					   {label : '审核状态',name : 'bzrshzt',index : 'bzrshzt',width : '10%',hidden:true},
					   {label : '审核状态',name : 'bzshztmc',index : 'bzshztmc',width : '10%'},
					   {label : '操作',name : 'cz',index : 'cz',width : '8%',formatter:czFormatter}
					   ]
				};

			jQuery(function() {
				var sftj = jQuery("#sftj").val();
				var map = {xmid:jQuery("#xmid").val() ,khbid:jQuery("#khbid").val()};
				gridSetting["params"] = map;
					jQuery("#dataTable").initGrid(gridSetting);
			});
			function czFormatter(cellValue, rowObject){
			   return "<a href='javascript:void(0);' class='name' onclick='xgpf(\""+rowObject.zpzf+"\",\""+rowObject.xmid+"\",\""+rowObject.khbid+"\",\""+rowObject.xh+"\",\""+rowObject.bztjzt+"\");'>修改</a>";
			}
			//修改评分
			function xgpf(xszpf,xmid,khbid,khdxr,tjzt){
				if(tjzt=="1"){
				 showAlertDivLayer("该学生已审核通过，不允许修改！");
					return false;
				}
					
					var url = "khglKhpf.do?method=xgpf&xmid=";
						url+=xmid+"&khbid="+khbid+"&khdxr="+khdxr+"&xmszid="+jQuery("#xmszid").val()+"&fs="+xszpf+"&pflx="+jQuery("#pflx").val();
						showDialog("修改评分",800,520,url,{close:function(){
							if (jQuery("#search_go")){
								jQuery("#search_go").click();
							}
						}});
			}
			//高级查询
			function searchRs(){
				var map = getSuperSearch();
				map["xmid"] = jQuery("#xmid").val();
				map["khbid"] = jQuery("#khbid").val();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			}
						
		</script>
	</head>
	<body>
	<html:form action="/khglKhpf" method="post">
	<%@ include file="/comm/hiddenValue.jsp"%>
	<input type="hidden" name="xmid" id="xmid" value=${xmInfo.xmid} />
	<input type="hidden" name="khbid" id="khbid" value=${xmInfo.khbid} />
	<input type="hidden" name="xmszid" id="xmszid" value=${xmInfo.xmszid} />
	<input type="hidden" name="pflx" id="pflx" value=${xmInfo.pflx} />
	<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li id="li_ck"><a href="javascript:void(0);" onclick="viewPf();" class="btn_ck">查看</a></li>
						<li id="li_xz"><a href="javascript:void(0);" onclick="getCpcjWord();" class="btn_xg">测评成绩下载</a></li>
						<li id="li_sh"><a href="javascript:void(0);" onclick="bzsh();" class="btn_sh">审核通过</a></li>
						<li id="li_qx"><a href="javascript:void(0);" onclick="cxsh();" class="btn_qxsh">撤消审核</a></li>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
	</html:form>
			<div class="main_box">
					<h3 class=datetitle_01>
						班组评分列表
						<font style="color: blue"> ${xmInfo.xmmc}</font>
					</h3>
					<div class="con_overlfow">
						<table id="dataTable" ></table>
						<div id="pager"></div>
					</div>
				</div>		
	</body>
</html>
