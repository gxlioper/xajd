<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/ssyd/js/plyd.js"></script>
		<script type="text/javascript">
		var yrzGrid = {
				pager:"pager",
				url:"gy_plyd.do?method=getYrzList",
				colList:[
				   {label:'学号',name:'xh', index: 'xh',key:true,formatter:xhLink},
				   {label:'姓名',name:'xm', index: 'xm'},
				   {label:'性别',name:'xb', index: 'xb'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm'},
				   {label:'班级',name:'bjmc', index: 'bjdm'},
				   {label:'楼栋',name:'ldmc', index: 'ldmc'},
				   {label:'寝室',name:'qsh', index: 'qsh'},
				   {label:'床位',name:'cwh', index: 'cwh'}
				],
				sortname: "xh",
			 	sortorder: "asc"
			};

			var dtzGrid = {
				pager:"pager",
				url:"gy_plyd.do?method=getDtzList",
				colList:[
				   {label:'学号',name:'xh', index: 'xh',key:true,formatter:xhLink},
				   {label:'姓名',name:'xm', index: 'xm'},
				   {label:'性别',name:'xb', index: 'xb'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm'},
				   {label:'班级',name:'bjmc', index: 'bjdm'},
				   {label:'原楼栋',name:'ldmc', index: 'ldmc'},
				   {label:'原寝室',name:'yss', index: 'yss'},
				   {label:'原床位',name:'ycw', index: 'ycw'}
				],
				sortname: "xh",
			 	sortorder: "asc"
			};

			var qrtzGrid = {
				pager:"pager",
				url:"gy_plyd.do?method=getQrtzList",
				colList:[
				   {label:'学号',name:'xh', index: 'xh',key:true,formatter:function(c,r){
					   
					   var html = kftj = r["kftj"]=="1" ? "<a href='javascript:void(0);' class='name' onclick='zxsxxView(\"" + c  + "\");'>"+c +"</a>" : "<a href='javascript:void(0);' class='name' onclick='zxsxxView(\"" + c  + "\");'>"+c +"</a><input type='hidden' name='bktj'/>";
					   
					   return html;
				   }},
				   {label:'姓名',name:'xm', index: 'xm'},
				   {label:'性别',name:'xb', index: 'xb'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm'},
				   {label:'班级',name:'bjmc', index: 'bjdm'},
				   {label:'原楼栋',name:'ldmc', index: 'ldmc'},
				   {label:'原寝室',name:'yss', index: 'yss'},
				   {label:'原床位',name:'ycw', index: 'ycw'},
				   {label:'新楼栋',name:'xldmc', index: 'xldmc'},
				   {label:'新寝室',name:'xss', index: 'xss'},
				   {label:'新床位',name:'xcw', index: 'xcw'},
				   {label:'可否提交',name:'kftj', index: 'kftj',hidden:true}
				],
				sortname: "kftj",
			 	sortorder: "asc"
			};
			jQuery(function(){
				jQuery("#dataTable").initGrid(yrzGrid);
			});
	
 			function searchRs(){
 				var map = getSuperSearch();
 				jQuery("#dataTable").reloadGrid(map);
 			}

 			//查看学生链接
 			function xhLink(cellValue, rowObject) {
 				return "<a href='javascript:void(0);' class='name' onclick='zxsxxView(\"" + cellValue + "\");'>" + cellValue
 						+ "</a>";
 			}
			
 			//单击学号查看
 			function zxsxxView(xh){
 				
 					var pkValue=xh;
 					var url="xsxx_tygl.do?method=ckZxsxx";
 					url+="&xh="+pkValue;
 					var width=850;
 					//showTopWin(url,850,640);
 					showDialog('查看学生详细信息',850,640,url);
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
		<!-- 标题 end-->
	
		<html:form action="/xsxx_xsgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
				<div class="buttonbox">
					<ul>
							<li id="btn_tzrz">
								<a href="javascript:void(0);" onclick="tzrz();return false;"  class="btn_zj">调整入住</a>
							</li>
							<li id="btn_dtz">
								<a href="javascript:void(0);" onclick="szDtz();return false;"  class="btn_xg">待调整</a>
							</li>
							<li id="btn_sc" style="display:none;">
								<a href="javascript:void(0);" onclick="qxDtz();return false;"  class="btn_sc">删除</a>
							</li>
							
							<li id="btn_qxtz" style="display:none;">
								<a href="javascript:void(0);" onclick="qxtz();return false;" class="btn_sc">取消调整</a>
							</li>
							<li id="btn_qrtz" style="display:none;">
								<a href="javascript:void(0);" onclick="qrtz();return false;" class="btn_shtg">确认调整</a>
							</li>
					</ul>
				</div>
				</logic:equal>
			</div>
		</html:form>
		<!-- 过滤条件 -->	
 				<%@ include file="/comm/search/superSearchArea.jsp"%> 
		<!-- 过滤条件 end-->
		<div class="comp_title" id="comp_title">
	      <ul style="width:90%;" id="tabUl">
			<li class="ha"><a href="javascript:void(0);" onclick="changeTab(0);"><span>已入住</span></a></li>
			<li><a href="javascript:void(0);" onclick="changeTab(1);"><span>待调整</span></a></li>
			<li><a href="javascript:void(0);" onclick="changeTab(2);"><span>确认调整</span></a></li>
	      </ul>
	    </div>
		
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 学生信息列表 </span> 
			</h3>
			
			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
