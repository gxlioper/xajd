<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/bfjs/fswh/js/jsfs.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/json.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var djList = <%=request.getAttribute("djList")%>;
				var jsonStr = jQuery("#jsonStr").val();
				var map = JSON.parse(jsonStr);
				map["id"] = jQuery("#id").val();
				map["xn"] = jQuery("#xn").val();
				map["xq"] = jQuery("#xq").val();
				
				var gridSetting = {
					caption:"参评学生列表 ",
					pager:"pager",
					url:"xpj_zcfs.do?method=editZcfss&doType=query&editType=edit",
					params:map
				};
					
				var zcxm = jQuery("font[name=zcxm]");
				var colList=[
					   {label:'key',name:'id', index: 'id',hidden:true,key:true},
					   {label:'学号',name:'xh', index: 'xh',width:'15%'},
					   {label:'姓名',name:'xm', index: 'xm',width:'15%'},
					   {label:'班级',name:'bjmc', index: 'bjmc',width:'15%'}
					];
		
				jQuery.each(zcxm,function(i,n){
					var json = {label:jQuery(n).attr("xmmc"),
								name:"fs"+i,
								index:"fs"+i
								
								};
					
					if (jQuery(n).attr("xmlx") != "3" && jQuery(n).attr("jktb") ==""){
						json["formatter"] = function(cell,rowObject){
							
							var html="";
							
							if(jQuery(n).attr("xmlx") == "4"){
								html+="<select onclick=\"saveZcfs(this,'"+jQuery(n).attr("xmdm")+"','"+rowObject["xh"]+"')\">";
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
								html+= "<input onblur=\"checkInputNum(this);saveZcfs(this,'";
								html+=jQuery(n).attr("xmdm");
								html+="','";
								html+=rowObject["xh"];
								html+="')\" type='text' onkeyup='toNext(this,event);checkInputNum(this);'";
								html+=" style='width:50px;' maxlength='10' value='";
								html+=cell==null ? "" : cell;
								html+="' name='";
								html+=jQuery(n).attr("xmmc")+"' max='"+jQuery(n).attr("zdfs")+"' min='"+jQuery(n).attr("zxfs")+"'/>";
							}
							return html;
						};
					}
					
					colList.push(json);
				});
				gridSetting["colList"] = colList;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			var DCCLBH = "pjpy_zcwh_dc.do";

			//导出方法
			function ExportData() {
			//dcclbh,导出功能编号
		    	var jsonStr = jQuery("#jsonStr").val();
		    	var xhxm = jQuery("#xh").val();
				var map = JSON.parse(jsonStr);
				var url = "xpj_zcfs.do?method=exportDataNeW&dcclbh=" + DCCLBH+"&xh="+xhxm+"&xm="+xhxm;
				url = addSuperSearchParams(url,map)
				jQuery("#zcfsForm").attr("target","_blank");
				jQuery("#zcfsForm").attr("action",url);
				jQuery("#zcfsForm").submit();
			}
		</script>
		
		<style>
			/*当终测项目过多时，本页列表会显示不全。增加列表滚动条显示*/
			.formbox {overflow:scroll}
			.formbox table tbody tr td{white-space:nowrap;}
		</style>
		
	</head>

	<body>
	<form action="/xpj_zcfs" method="post" name="xpjZcfsModel" id ="zcfsForm" enctype="multipart/form-data">
		<input type="hidden" value="${cssz.xn }" id="xn" name = "xn"/>
		<input type="hidden" value="${cssz.xq }" id="xq" name = "xq"/>
		<input type="hidden" value="${xpjZcfsModel.id }" id="id" name = "id" />
		<input type="hidden" value='${jsonStr }' id='jsonStr' name ='params'/>
	</form>
		<logic:present name="zcxmList">
			<logic:iterate id="z" name="zcxmList">
				<font style="display:none;" xmdm="${z.xmdm }"  zdfs="${z.zdfs }" zxfs="${z.zxfs }"
				      xmmc="${z.xmmc }" xmlx="${z.xmlx }" jktb="${z.jktb }" name="zcxm"></font>
			</logic:iterate>
		</logic:present>
		
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助1</a>
			</p>
		</div>
		
		<!-- 提示信息 end-->
		<div id="div_help" class="prompt" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
					增加：增加应作为<font color="red">当前周期</font>此班级评奖人员<font color="red">基数</font>的学生<br/>
					删除：删除不能作为<font color="red">当前周期</font>此班级评奖人员<font color="red">基数</font>的学生<br/>
				<%--	提交：人员名单与综测成绩<font color="red">确认无误</font>后，可提交，提交后<font color="red">不可修改</font><br/> --%>
					注：此处维护学生的综测成绩会<font color="red">即时保存生效</font>
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
					
					<logic:equal value="10335" name="xxdm">
					<li><a href="javascript:void(0);" onclick="showDialog('增加参评学生',750,480,'xpj_cpmd.do?method=viewAddCpxsList&bjdm=${bjxxMap.bjdm }');" class="btn_zj"
						   title="在这里，您可以将不在列表中的参评学生找回来。"
						>参评名单调整</a></li>
					</logic:equal>
					<logic:notEqual value="10335" name="xxdm">
					<li><a href="javascript:void(0);" onclick="showDialog('增加参评学生',750,480,'xpj_cpmd.do?method=viewAddCpxsList&bjdm=${bjxxMap.bjdm }');" class="btn_zj"
						   title="在这里，您可以将不在列表中的参评学生找回来。"
						>增加</a></li>
					</logic:notEqual>
					<li><a href="javascript:void(0);" onclick="cpxsDelete();" class="btn_sc"
						   title="在这里，您可以取消不在该班级参评的学生。"
						>删除</a></li>
<%--					<li><a href="javascript:void(0);" onclick="doEditSubmit('${xpjZcfsModel.id}');" class="btn_up"--%>
<%--						   title="您确认参评学生、综测分数无误后请点击这里提交;系统会在您提交后计算出综测分排名。"--%>
<%--						>提交</a></li>--%>
					<li><a href="javascript:void(0);" onclick="toImport();" class="btn_dr"
						   title="在这里，您可以将已经整理好的学生综测分数快速导入到系统当中。"
						>导入</a></li>	
					<logic:notEqual value="13779" name="xxdm">
						<logic:notEqual value="10279" name="xxdm">		
							<logic:present name="zcxmList">
								<logic:iterate id="z" name="zcxmList">
									<logic:notEmpty name="z" property="jktb">
										<li><a href="javascript:void(0);" onclick="getIntefaceData('${z.xmdm}')" class="btn_sx">同步${z.xmmc }</a></li>						
									</logic:notEmpty>
								</logic:iterate>
							</logic:present>
						</logic:notEqual>
					</logic:notEqual>
					<logic:equal value="10279" name="xxdm">
						<li><a href="javascript:void(0);" onclick="getIntefaceDataS()" class="btn_sx">同步综测分</a></li>
					</logic:equal>
					<logic:equal value="13779" name="xxdm">
						<li><a href="javascript:void(0);" onclick="getIntefaceDataS()" class="btn_sx">同步德智体分</a></li>
					</logic:equal>
					<li><a href="javascript:void(0);" onclick="ExportData();" class="btn_dc">导出</a></li>
					<li><a href="javascript:void(0);" onclick="document.location.href='pj_zcflr.do';" class="btn_fh">返回</a></li>	
				</ul>
			</div>
			</logic:equal>
			<!-- 过滤条件 -->	
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="12%">学号 / 姓名</th>
						<td>
							<input type="text" id="xh" name="xh" maxleng="20" onkeypress="if(event.keyCode==13){doQuery();}"/>
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="doQuery();">
									查 询
								</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
			<!-- 过滤条件 end-->
		</div>
		<div class="formbox" style="overflow: auto;">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span><font color="blue">${cssz.zqmc}&nbsp;</font>参评学生列表 </span>
			</h3>
			<div style="overflow: auto;">
				<table id="dataTable" ></table>
			</div>
			<div id="pager"></div>

		</div>
	</body>
</html>
