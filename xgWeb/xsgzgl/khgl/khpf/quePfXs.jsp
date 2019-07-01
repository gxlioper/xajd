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
					radioselect:true,
					url : "khglKhpf.do?method=quePf&type=query",
					colList : [
					   {label : 'xmid',name : 'xmid',index : 'xmid' ,hidden:true}, 
					   {label : 'khbid',name : 'khbid',index : 'khbid' ,hidden:true},
					   {label : 'khdxr',name : 'khdxr',index : 'khdxr' ,hidden:true}, 
					   {label : 'xmszid',name : 'xmszid',index : 'xmszid' ,hidden:true},
					   {label : '学号',name : 'xh',index : 'xh',width : '20%'}, 
					   {label : '姓名',name : 'xm',index : 'xm',width : '15%'}, 
					   {label : '性别',name : 'xb',index : 'xb',width : '15%'}, 
					   {label : '年级',name : 'nj',index : 'nj',width : '20%'}, 
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'20%'},
					   {label : '班级',name : 'bjmc',index : 'bjmc',width : '20%'}
					   ],
					sortname : "xh",
					sortorder : "desc"
				}

			var gridSetting1 = {
					caption : "考核对象列表",
					pager : "pager",
					radioselect:false,
					url : "khglKhpf.do?method=quePf&type=query",
					colList : [
					   {label : 'xmid',name : 'xmid',index : 'xmid' ,hidden:true}, 
					   {label : 'khbid',name : 'khbid',index : 'khbid' ,hidden:true},
					   {label : 'khdxr',name : 'khdxr',index : 'khdxr' ,hidden:true}, 
					   {label : 'xmszid',name : 'xmszid',index : 'xmszid' ,hidden:true},
					   {label : '学号',name : 'xh',index : 'xh',width : '20%'}, 
					   {label : '姓名',name : 'xm',index : 'xm',width : '15%'}, 
					   {label : '性别',name : 'xb',index : 'xb',width : '15%'}, 
					   {label : '年级',name : 'nj',index : 'nj',width : '20%'}, 
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'20%'},
					   {label : '班级',name : 'bjmc',index : 'bjmc',width : '20%'},
					   {label : '总分',name : 'zf',index : 'zf',width : '20%'}
					   ],
					sortname : "xh",
					sortorder : "desc"
				}

			jQuery(function() {
				var sftj = jQuery("#sftj").val();
				var map = {xmid:jQuery("#xmid").val() ,khbid:jQuery("#khbid").val()};
				gridSetting["params"] = map;
				if(""==sftj||"2"==sftj){
					jQuery("#dataTable").initGrid(gridSetting);
				}else{
					jQuery("#dataTable").initGrid(gridSetting1);
				}
			});

			//高级查询
			function searchRs(){
				var sftj = jQuery("#sftj").val();
				var map = getSuperSearch();
				map["xmid"] = jQuery("#xmid").val();
				map["khbid"] = jQuery("#khbid").val();
				map["sftj"] = sftj;
				gridSetting["params"] = map;
				
				if(""==sftj||"2"==sftj){
					jQuery("#dataTable").initGrid(gridSetting);
				}else{
					jQuery("#dataTable").initGrid(gridSetting1);
				}
			}
						
		</script>
	</head>
	<body>
	<html:form action="/khglKhpf" method="post">
	<%@ include file="/comm/hiddenValue.jsp"%>
	<input type="hidden" name="xmid" id="xmid" value=${xmInfo.xmid} />
	<input type="hidden" name="khbid" id="khbid" value=${xmInfo.khbid} />
	<input type="hidden" name="sftj" id="sftj" value="2" />
	<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					<logic:notEqual value="11527" name="xxdm">
						<li><a href="javascript:void(0);" onclick="document.location.href='khgl_khpf.do';" class="btn_fh">返回</a></li>
					</logic:notEqual>
						<li id="li_pf"><a href="javascript:void(0);" onclick="addPf();" class="btn_xg">评分</a></li>
						<li id="li_ck" style="display: none;"><a href="javascript:void(0);" onclick="viewPf();" class="btn_ck">查看</a></li>
						<logic:equal value="11527" name="xxdm">
						<li id="li_xz" style="display: none;"><a href="javascript:void(0);" onclick="getCpcjWord();" class="btn_xg">测评成绩下载</a></li>
						</logic:equal>
						<logic:equal value="13022" name="xxdm">
							<li id="li_qx" style="display:none;"> 
								<a href="javascript:void(0);" onclick="cancelTjRecord();" class="btn_qxsh">取消提交</a>
							</li>
						</logic:equal>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%" id="tabUl">
					<li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'2');"><span>待评人员</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'1');"><span>已评人员</span></a></li>
			      </ul>
			    </div>
			</div>
	</html:form>
			<div class="main_box">
					<h3 class=datetitle_01>
						<span>项目：<font style="color: blue"> ${xmInfo.xmmc}</font> 
						考核表：<font style="color: blue"> ${xmInfo.khbmc}</font></span>
					</h3>
					<div class="con_overlfow">
						<table id="dataTable" ></table>
						<div id="pager"></div>
					</div>
				</div>		
	</body>
</html>
