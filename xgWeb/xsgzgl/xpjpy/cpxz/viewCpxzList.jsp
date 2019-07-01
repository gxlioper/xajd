<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/cpxz/js/cpxz.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"综测排名组",
				pager:"pager",
				url:"xpj_cpxz.do?method=viewCpxzList&type=query",
				colList:[
				   {label:'年级',name:'nj', index: 'nj',width:'7%'},
				   <%--{label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'18%'},--%>
                    {label:'书院',name:'symc', index: 'sydm',width:'18%'},
//				   {label:'专业',name:'zymc', index: 'zydm',width:'22%'},
				   {label:'行政班级',name:'bjdm', index: 'bjdm',key:true,hidden:true},
				   {label:'行政班级',name:'bjmc', index: 'bjdm',width:'25%'},
				   {label:'参评组',name:'cpzmc', index: 'cpzdm',width:'28%'}
				],
//				sortname: "nj,xydm,zydm,bjdm",
                sortname: "nj,sydm,bjdm",
			 	sortorder: "asc"
			};
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			//设置参评组
			function szCpz(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0){
					showAlertDivLayer("请选择您要设置参评组的班级！");
				}else{
					showDialog('设置参评组',390,170,'xpj_cpxz.do?method=szCpz&ids='+ids.toString());
				}
			}
			
			
			
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;"	onmousedown="showHelpDiv()">使用帮助</a>
			</p>
		</div>
		<html:form action="/xpj_cpxz">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<!-- 提示信息 end-->
			<div id="div_help" class="prompt" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					<span >
						系统默认一个班级为一个参评组，若学校需要将多个班级设置为一个参评组，可在此处调整
					</span>
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 提示信息 end-->
			
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">	
				<div class="buttonbox">
					<ul>
						<logic:equal value="true" name="cssz" property="kgzt">
						<li><a href="javascript:void(0);" onclick="szCpz();return false;" class="btn_ccg" >设置参评组</a></li>
						<li><a href="javascript:void(0);" onclick="showDialog('初始化参评组',360,180,'xpj_cpxz.do?method=zdszCpz'); return false;" class="btn_sz">初始化参评组</a></li>
						</logic:equal>
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
				<span><font color="blue">${cssz.zqmc}&nbsp;&nbsp;</font>参评组</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
