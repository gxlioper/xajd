<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD bjdmTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/bjdmtml1/DTD/bjdmtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/bjdmtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/bfjs/fswh/js/jsfs.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/json.js"></script>
		<script type="text/javascript">
		jQuery(function(){
		var gridSetting = {
				caption:"竞赛班级列表 ",
				pager:"pager",
				url:"xpjpyBfjsFswh.do?method=viewBjjsList&doType=query&editType=edit",
				params:getSuperSearch()
			};
				
			var jsxm = jQuery("font[name=jsxm]");
			var colList=[
				   {label:'key',name:'id', index: 'id',hidden:true,key:true},
				   {label:'班级代码',name:'bjdm', index: 'bjdm',width:'15%',hidden:true},
				   {label:'班级名称',name:'bjmc', index: 'bjmc',width:'15%'},
				   {label:'提交状态',name:'tjzt', index: 'tjzt',hidden:true}
				];

			jQuery.each(jsxm,function(i,n){
				var json = {label:jQuery(n).attr("xmmc"),
							name:"fs"+i,
							index:"fs"+i
							
							};
				
				if (jQuery(n).attr("xmlx") != "3"){
					json["formatter"] = function(cell,rowObject){
						
						var html="";

						if(rowObject["tjzt"]=="1"){
							if(jQuery(n).attr("xmlx") == "4"){
								jQuery.each(djList,function(i,dj){
									if(cell == dj['dm']&&jQuery(n).attr("xmmc")==dj['xmmc']){
										html+=dj['mc'];
									}
								});
							}else{
								html+=cell==null ? "" : cell;						
							}
						}else if(jQuery(n).attr("xmlx") == "4"){
							html+="<select onclick=\"saveBfjsFswh(this,'"+jQuery(n).attr("xmdm")+"','"+rowObject["bjdm"]+"')\">";
							html+="<option value=''></option>";
							jQuery.each(djList,function(i,dj){
								if(jQuery(n).attr("xmmc")==dj['xmmc']){
									var option = "<option value='" + dj['dm'] + "'";
									if(cell == dj['dm']){
										option+=" selected ";
									}
									option+= ">" + dj['mc'] + "</option>";
									html+=option;
								}
							});
					   		html+="</select>";	

							
						}else{
							html+= "<input onblur=\"checkInputNum(this);saveBfjsFswh(this,'";
							html+=jQuery(n).attr("xmdm");
							html+="','";
							html+=rowObject["bjdm"];
							html+="')\" type='text' onkeyup='toNext(this,event);checkInputNum(this);'";
							html+=" style='width:50px;' maxlength='10' value='";
							html+=cell==null ? "" : cell;
							html+="' name='";
							html+=jQuery(n).attr("xmmc")+"' max='"+jQuery(n).attr("zdfz")+"' min='"+jQuery(n).attr("zxfz")+"'/>";
						}
						return html;
					};
				}
				
				colList.push(json);
			});
			colList.push({label:'提交状态',name:'tjztmc', index: 'tjztmc',width:'7%'});
			gridSetting["colList"] = colList;
			jQuery("#dataTable").initGrid(gridSetting);
		});
		</script>
		<style>
		</style>
	</head>

	<body>
		<input type="hidden" value="${cssz.xn }" id="xn"/>
		<input type="hidden" value="${cssz.xq }" id="xq"/>
		<logic:present name="jsxmList">
			<logic:iterate id="z" name="jsxmList">
				<font style="display:none;" xmdm="${z.xmdm }"  zdfz="${z.zdfz }" zxfz="${z.zxfz }"
				      xmmc="${z.xmmc }" xmlx="${z.xmlx }" name="jsxm"></font>
			</logic:iterate>
		</logic:present>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">				
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
			</p>
		</div>
		<html:form action="/xpjpyBfjsFswh" >
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
							<logic:equal value="true" name="cssz" property="kgzt">
								<li><a href="javascript:void(0);" onclick="submitJsfs();" class="btn_up">提交</a></li>
								<li><a href="javascript:void(0);" onclick="toImport();" class="btn_dr" >导入</a></li>	
							</logic:equal>
							<li><a href="javascript:void(0);" onclick="ExportData();" class="btn_dc">导出</a></li>						
						</ul>
					</div>
				</logic:equal>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span><font color="blue">${cssz.zqmc}&nbsp;</font>竞赛班级列表 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
