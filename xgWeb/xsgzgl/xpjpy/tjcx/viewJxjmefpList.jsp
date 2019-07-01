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
				searchRs();
			});
			
			function getGridSettiong(){
				var gridSetting = {
					caption:"奖学金名额分配一览表",
					pager:"pager",
					url:"pj_jxjmefp.do?method=viewJxjmefpList&type=query",
					params:getSuperSearch(),
					multiselect:false
				};

				var colList=[
					   {label:'部门名称',name:'bmmc', index: 'bmmc',width:'15%'}
					];				
					
				var pjxm = jQuery("font[name=pjxm]");
				jQuery.each(pjxm,function(i,n){
					var pjxmJson = {
							label:"&nbsp;&nbsp;"+jQuery(n).attr("xmmc")+"<br/>(总人数/调整人数)",
							name:"jx"+i,
							index:"jx"+i
					};
					colList.push(pjxmJson);
				});

				var jjeList = {label:'奖金额',name:'jje', index: 'jje',width:'15%',key:true};
				var bmtzjeList = {label:'部门调整金额',name:'bmtzje', index: 'bmtzje',width:'15%',key:true};
				var zrsList = {label:'部门学生总数',name:'zrs', index: 'zrs',width:'15%',key:true};
				var ytjrsList = {label:'部门综测已提交人数',name:'ytjrs', index: 'ytjrs',width:'15%',key:true};
				colList.push(jjeList);
				colList.push(bmtzjeList);
				colList.push(zrsList);
				colList.push(ytjrsList);
				
				gridSetting["colList"] = colList;
				
				return gridSetting;				
			}

	
			function searchRs(){
				var xn_num = document.getElementsByName("a_name_xn").length;
				var notFirst = jQuery("#notFirst").val();
				if("yes"==notFirst&&0==xn_num){
					showAlertDivLayer("请先选择一个学年！");
					return false;
				}
				jQuery.ajaxSetup({async:false});
				initPjxm();
				var gridSetting = getGridSettiong();
				jQuery("#dataTable").initGrid(gridSetting);
				jQuery.ajaxSetup({async:true});
			}

			//初始化需统计评奖项目
			function initPjxm(){
				var url="xpj_tjcx.do?method=initPjxm";
				var xn = jQuery("[name=tj_xn][class=selectedValue]").attr("id");
				if(undefined!=xn){
					xn=xn.split("_")[2];
				}
				jQuery.post(url,{"xn":xn},function(data){
					dataObj=data;
					createPjxmDiv();
					},'json');
				
				}

			function createPjxmDiv(){
				var pjDiv=jQuery("#pjxmDiv");
				jQuery("font",pjDiv).remove();
				var pjxmHtml = "";
				for ( var i = 0; i < dataObj.length; i++) {
					var o = dataObj[i];
					pjxmHtml+="<font style='display:none;' xmdm="+o.xmdm+" xmmc="+o.xmmc+" name='pjxm'></font>";
				}
				pjDiv.html(pjxmHtml);
				jQuery("#notFirst").val("yes")
				}	

			//综测分导出
			function exportZcf(){
				
				var xn_num =  jQuery("a[name=a_name_xn]").length;
				if(xn_num != "1"){
					showAlertDivLayer("学年条件不可为空，且只能选择一个！");
					return false;
				}
				
				setSearchTj();//设置高级查询条件
				var url = "xpj_tjcx.do?method=exportViewTjjg"
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}

			//发放汇总导出
			function exportFfhz(){
				
				var xn_num =  jQuery("a[name=a_name_xn]").length;
				if(xn_num != "1"){
					showAlertDivLayer("学年条件不可为空，且只能选择一个！");
					return false;
				}
				
				setSearchTj();//设置高级查询条件
				var url = "xpj_tjcx.do?method=exportFfhz"
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}

			//发放汇总导出
			function exportGjjxjhz(){
				
				var xn_num =  jQuery("a[name=a_name_xn]").length;
				if(xn_num != "1"){
					showAlertDivLayer("学年条件不可为空，且只能选择一个！");
					return false;
				}
				
				setSearchTj();//设置高级查询条件
				var url = "xpj_tjcx.do?method=exportGjjxjhz"
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}

		</script>
		<style>
			.con_overlfow { overflow-x: auto;}
		</style>
	</head>

	<body style="min-height: 800px;">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		
		<logic:present name="pjxmList">
		<div id="pjxmDiv">
			<logic:iterate id="p" name="pjxmList">
				<font style="display:none;" xmdm="${p.xmdm }" xmmc="${p.xmmc }" name="pjxm"></font>
			</logic:iterate>
		</logic:present>
		</div>
		<html:form action="/xpj_tjcx">
			<input type="hidden" id="notFirst" value=""/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<!-- 按钮 -->	
				<div class="buttonbox">
					<ul>
						<li id="li_sh">
							<li><a href="#" class="btn_dc" onclick="exportZcf(); return false;">导出</a></li>
							<li><a href="#" class="btn_dc" onclick="exportFfhz(); return false;">发放汇总导出</a></li>
							<li><a href="#" class="btn_dc" onclick="exportGjjxjhz(); return false;">国家奖学金汇总导出</a></li>
						</li>						
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>奖学金名额分配一览表 </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
