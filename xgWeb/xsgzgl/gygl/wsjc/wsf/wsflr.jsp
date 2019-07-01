<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/json.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				pager:"pager",
				url:"wsjcWsflr.do?method=getWsflrList&rcid=${wsfForm.rcid}&jcdx=${wsfForm.jcdx}&fslx=${jcrcInfo.fslx}",
				radioselect:true,
				colList:[
				   {label:'lddm',name:'lddm', index: 'lddm',hidden:true},
				   {label:'楼栋',name:'ldmc', index: 'ldmc'},
				   {label:'寝室',name:'qsh', index: 'qsh'}
				   <logic:equal value="1" name="wsfForm" property="jcdx">
			   		,{label:'床位',name:'cwh', index: 'cwh'}
				   </logic:equal>	
				   <logic:iterate id="r" name="rcxmList" indexId="i">
						,{label:'${r.xmmc }',name:'fs${i}', index: 'fs${i}',formatter:function(v,r){
							<logic:equal value="0" name="jcrcInfo" property="fslx">
								var html = "<input type='text' style='width:50px;' maxlength='5' name='wsf' "; 
								if (v != null){
									html += "value='"+v+"'";
								}
								html +="onblur='saveWsf(\""+r["lddm"]+"\",\""+r["qsh"]+"\",\""+r["cwh"]+"\",\"${r.xmdm}\",this)'/>";
								return html;
							</logic:equal>
							<logic:equal value="1" name="jcrcInfo" property="fslx">
								var obj = jQuery("#djSelect").clone();
								obj.bind("change",function(){
									saveWsf(r["lddm"],r["qsh"],r["cwh"],"${r.xmdm}",this);
								});
								jQuery("option[value="+v+"]",obj).attr("selected","selected");
								return obj.show();
							</logic:equal>
							<logic:equal value="2" name="jcrcInfo" property="fslx">
								var obj = jQuery("#xjSelect").clone();
								obj.bind("change",function(){
									saveWsf(r["lddm"],r["qsh"],r["cwh"],"${r.xmdm}",this);
								});
								jQuery("option[value="+v+"]",obj).attr("selected","selected");
								return obj.show();
							</logic:equal>
						}}
				   </logic:iterate>
				]
			};

			jQuery(function(){
				gridSetting["params"] = getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function saveWsf(lddm,qsh,cwh,xmdm,obj){
				
				var fslx = jQuery("#fslx").val();
				var jcdx = jQuery("#jcdx").val();
				
				var map = {};
				map["rcid"] = "${wsfForm.rcid}";
				map["lddm"] = lddm;
				map["qsh"] = qsh;
				map["jcdx"] = jcdx;
				
				if (jcdx == "1"){
					map["cwh"] = cwh;
				}
				
				map["xmdm"] = xmdm;
				
				if (fslx == "0"){
					map["wsf"] = obj.value;
				} else {
					map["wsdj"] = obj.value;
				}
				
				jQuery.post("wsjcWsflr.do?method=saveWsf",map,function(data){
					if (!data){
						showAlertDivLayer("系统异常，保存失败！");
					}
				},"json");
			}
			
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			
			function importWsf(){
				var map = getSuperSearch();
				var jsonStr = JSON.stringify(map);
				var url = "wsjcWsflr.do?method=importWsf&rcid=${wsfForm.rcid}&jcdx=${wsfForm.jcdx}&fslx=${jcrcInfo.fslx}&jsonStr="+jsonStr;
				showDialog("卫生分导入",550,300,url);
			}
		</script>
	</head>
	
	<body>
		<input type="hidden" name="fslx" id="fslx" value="${jcrcInfo.fslx}"/>
		<input type="hidden" name="jcdx" id="jcdx" value="${wsfForm.jcdx}"/>
	
		<select name="wsf" id="djSelect" style="display:none;">
				<option></option>
			<logic:iterate id="v" name="djList">
				<option value="${v }">${v }</option>
			</logic:iterate>
		</select>
		
		<select name="wsf" id="xjSelect" style="display:none;">
				<option></option>
			<logic:iterate id="v" name="xjList">
				<option value="${v }">${v }</option>
			</logic:iterate>
		</select>
	
		<html:form action="/wsjcWsflr" method="post" styleId="form">
		
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:importWsf();"  class="btn_dr">导入</a></li>
						<li><a href="javascript:history.back(-1);"  class="btn_fh">返回</a></li>
					</ul>
				</div>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		
			<div class="formbox">
				<!--标题start-->
				<h3 class="datetitle_01">
					<span>查询结果列表&nbsp;&nbsp;<font color="blue">检查日程:${jcrcInfo.rcmc }[${jcrcInfo.kssj }至${jcrcInfo.jssj }]</font></span>
				</h3>
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
		</html:form>
	</body>
</html>
