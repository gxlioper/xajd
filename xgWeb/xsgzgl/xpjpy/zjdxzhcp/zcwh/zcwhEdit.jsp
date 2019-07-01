<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxzhcp/zcwh/js/zcwh.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/json.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var djList = <%=request.getAttribute("djList")%>;
			var jsonStr = jQuery("#jsonStr").val();
			var map = JSON.parse(jsonStr);
			map["id"] = jQuery("#id").val();
			map["xn"] = jQuery("#xn").val();
			
			var gridSetting = {
				caption:"参评学生列表 ",
				pager:"pager",
				url:"xpjpy_zcwh.do?method=zcwhEdit&doType=query&editType=edit&id="+jQuery("#id").val() + "&zcxmdmForTop=" + jQuery("#zcxmdmForTop").val(),
				params:map
			};
				
			var zcxm = jQuery("font[name=zcxm]");
			
			var colList=[
				   {label:'key',name:'id', index: 'id',hidden:true,key:true},
				   {label:'学号',name:'xh', index: 'xh',width:'11%'},
				   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
				   {label:'班级',name:'bjmc', index: 'bjmc',width:'12%'},
				   {label:'提交状态',name:'tjzt', index: 'tjzt',hidden:true}
				];

			jQuery.each(zcxm,function(i,n){
				var json = {label:jQuery(n).attr("xmmc"),
							name:"fs"+i,
							index:"fs"+i
							};
				/*原评奖中综测项目项目类型  1：加分、2：减分、3：默认分、4：等级*/
				/*原评奖中综测项目项目类型  0：等级、1：分值*/
				/*最外层判断不要也行，实际没什么用*/
				if (jQuery(n).attr("xmlx") != "3" && jQuery(n).attr("jktb") ==""){
					
					json["formatter"] = function(cell,rowObject){
						var html="";
						
						if(rowObject["tjzt"]=="1"){
							if(jQuery(n).attr("xmlx") == "等级"){
								jQuery.each(djList,function(i,dj){
									if(cell == dj['dm']&&jQuery(n).attr("xmmc")==dj['xmmc']){
										html+=dj['mc'];
									}
								});
							}else{
								html+=cell==null ? "" : cell;						
							}
						}else if(jQuery(n).attr("xmlx") == "等级"){
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
							html+=jQuery(n).attr("xmmc")+"' max='"+jQuery(n).attr("zdfz")+"' min='"+jQuery(n).attr("zxfz")+"'/>";
						}
						return html;
					};
				}
				
				colList.push(json);
			});
			/*colList.push({label:'提交状态',name:'tjztmc', index: 'tjztmc',width:'7%',hidden:true});*/
			gridSetting["colList"] = colList;
			jQuery("#dataTable").initGrid(gridSetting);
		});
		</script>
		
		<style>
			/*当终测项目过多时，本页列表会显示不全。增加列表滚动条显示*/
			.formbox {overflow:scroll}
			.formbox table tbody tr td{white-space:nowrap;}
		</style>
		
	</head>

	<body>
		<form action="/xpjpy_zcwh" method="post" name="zcwhForm" id ="zcwhForm" enctype="multipart/form-data">
			<input type="hidden" value="${cssz.xn}" id="xn" name = "xn"/>
			<input type="hidden" value="${zcwhForm.id}" id="id" name = "id" />
			<input type="hidden" value='${jsonStr }' id='jsonStr' name ='params'/>
			<input type="hidden" value='${zcxmdmForTop}' id='zcxmdmForTop' name ='zcxmdmForTop'/>
		</form>
		<logic:present name="zcxmList">
			<logic:iterate id="z" name="zcxmList">
				<font style="display:none;" xmdm="${z.xmdm }"  zdfz="${z.zdfz }" zxfz="${z.zxfz }"
				      xmmc="${z.xmmc }" xmlx="${z.xmlx }" jktb="${z.jktb }" name="zcxm"></font>
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
		
		<!-- 提示信息 end-->
		<div id="div_help" class="prompt" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
					增加：增加应作为<font color="red">当前周期</font>此学院评奖人员<font color="red">基数</font>的学生<br/>
					删除：删除不能作为<font color="red">当前周期</font>此学院评奖人员<font color="red">基数</font>的学生<br/>
					提交：人员名单与综测成绩<font color="red">确认无误</font>后，可提交，提交后<font color="red">不可修改</font><br/>
					注：此处维护学生的综测成绩会<font color="red">即时保存生效</font>
				</span>
			</p>
			<a class="close" title="隐藏"
			   onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<!-- 在这里，您可以将不在列表中的参评学生找回来。 -->
					<li>
						<a href="javascript:void(0);" class="btn_zj" onclick="cpxsAdd();">参评名单调整</a>
					</li>
					
					<!-- 在这里，您可以取消不在该班级参评的学生。 -->
					<li>
						<a href="javascript:void(0);" class="btn_sc" onclick="cpxsDelete();">删除</a>
					</li>
					
					<li>
						<a href="javascript:void(0);" class="btn_dr" onclick="cpxsImport();">导入</a>
					</li>
					
					<li>
						<a href="javascript:void(0);" class="btn_dc" onclick="cpxsExport();">导出</a>
					</li>
					
					<li>
						<a href="javascript:void(0);" class="btn_fh" onclick="document.location.href='xpjpy_zhcp_zcwh.do';">返回</a>
					</li>
				</ul>
			</div>
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
		</div>
		<div class="formbox" style="overflow: auto;">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span><font color="blue">${cssz.xn}&nbsp;</font>参评学生列表 </span>
			</h3>
			<div style="overflow: auto;">
				<table id="dataTable" ></table>
			</div>
			<div id="pager"></div>
		</div>
	</body>
</html>