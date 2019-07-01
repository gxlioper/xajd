<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"发文名单导出列表",
				pager:"pager",
				url:"xpjpy_fwmddc.do?method=getFwmddcList&type=query",
				colList:[
					{label:'key',name:'id', index: 'id',key:true ,hidden:true},
					{label:'评奖周期',name:'pjzq', index: 'xn',width:'8%'},				
					{label:'学号',name:'xh', index: 'xh',width:'10%'},
					{label:'姓名',name:'xm', index: 'xm',width:'8%'},
					{label:'学院名称',name:'xymc', index: 'xymc',width:'10%'},
					{label:'项目类型',name:'xmlxmc', index: 'xmlxmc',width:'5%'},
					{label:'项目名称',name:'xmmc', index: 'xmmc',width:'13%'},
					{label:'获得时间',name:'hdsj', index: 'hdsj',width:'13%'}
				]
			};
			
			jQuery(function(){
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
			});

			//高级查询
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}


			//检验可否查询
			function checkSearch(){
				var flag = true;
				var xn_num =  jQuery("a[name=a_name_xn]").length;
				var xmlx_num = jQuery("a[name=a_name_xxmlx]").length;
				var pjzq = jQuery("#pjzq").val();
				
				if(xn_num != "1"){
					alertError("请选择一个学年！");
					flag = false;
				}else if(xmlx_num < 1){
					alertError("请至少选择一个项目类型！");
					flag = false;
				}
				return flag;
			}

			/*获奖名单导出*/
			function hjmddc(dcfs){
				var pjzq = jQuery("#pjzq").val();
				if(checkSearch()){
					var xn = jQuery("a[name=a_name_xn]").eq(0).attr("id").replace("a_id_","");
					var url = "xpjpy_fwmddc.do?method=expHjmdtj&dcfs="+dcfs;
					url+= "&str_xn="+xn;
					var xmlx = new Array();//项目类型
					var m = 0;
					jQuery("a[name=a_name_xxmlx]").each(function(){
						var id = jQuery(this).attr("id");
						var xmlxmc = id.replace("a_id_","");
						if(xmlxmc !=null && xmlxmc!=""){
							xmlx[m] = xmlxmc;
							m++;
						}
					});

					if(xmlx != null && xmlx.length>0){
						url+= "&array_xmlx="+xmlx.join("!!array!!");
					}
					var xydm = new Array();//学院代码
					
					var n=0;
					
					jQuery("a[name=a_name_xy]").each(function(){
						var id = jQuery(this).attr("id");
						var xy = id.replace("a_id_","");
						if(xy !=null && xy!=""){
							xydm[n] = xy;
							n++;
						}
					});
					
					if(xydm != null && xydm.length>0){
						url+= "&array_xydm="+xydm.join("!!array!!");
					}

					var xmxz = new Array();//项目性质
					var o=0;
					jQuery("a[name=a_name_xxmxz]").each(function(){
						var id = jQuery(this).attr("id");
						var xmxzmc = id.replace("a_id_","");
						if(xmxzmc !=null && xmxzmc!=""){
							xmxz[o] = xmxzmc;
							o++;
						}
					});
					
					if(xmxz != null && xmxz.length>0){
						url+= "&array_xmxz="+xmxz.join("!!array!!");
					}

					var xmmc = new Array();//项目名称
					var q = 0;
					jQuery("a[name=a_name_xmmc]").each(function(){
						var id = jQuery(this).attr("id");
						var xm = id.replace("a_id_","");
						if(xm !=null && xm!=""){
							xmmc[n] = xm;
							q++;
						}
					});
					
					if(xmmc != null && xmmc.length>0){
						url+= "&array_xmmc="+xmmc.join("!!array!!");
					}
					document.forms[0].action = encodeURI(encodeURI(url)); ;
					document.forms[0].target = "_blank";
					document.forms[0].submit();
					document.forms[0].target = "_self";	
					}
				}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xpjpy_fwmddc">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xy" />"/>
			<input type="hidden" name="pjzq" id="pjzq" value="${pjzq}"/>
			<div class="toolbox">
				<!-- 按钮 -->	
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" class="btn_dc" onclick="hjmddc('dc');return false;">导出</a>
						</li>
						<li>
							<a href="#" class="btn_dc" onclick="hjmddc('dcwd');return false;">导出word</a>
						</li>
						<li>
							<a href="#" class="btn_dc" onclick="hjmddc('njdc');return false;">年鉴导出</a>
						</li>
					    <li>
					    	<a href="#" class="btn_dc" onclick="hjmddc('njdcwd');return false;">年鉴导出word</a>
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
				<span>获奖名单统计</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>