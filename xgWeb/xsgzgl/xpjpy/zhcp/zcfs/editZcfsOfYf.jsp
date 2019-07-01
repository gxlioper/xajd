<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zhcp/zcfs/js/zcfs.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/pjmd/js/pjmd.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/json.js"></script>
		<script type="text/javascript">
		
		function getGrid(){
			var zcyf="";
			var yfHidden = true;
			var zczfHid=false;
			
				var djList = <%=request.getAttribute("djList")%>;
				var jsonStr = jQuery("#jsonStr").val();
				
				 zcyf = jQuery("#zcyf").val();//选择总分
				 fsWriteable = true;//
				if("all"!=zcyf){
					zczfHid=true;
					yfHidden=false;
					}
				else{//月综测分：总分合计只读
					fsWriteable=false;
					}
				var para = jQuery("#tjzt").find("[value='"+zcyf+"']").text();
				if(para == '1'){
					fsWriteable=false;
				}
				var map = JSON.parse(jsonStr);
				map["id"] = jQuery("#id").val();
				map["xn"] = jQuery("#xn").val();
				map["xq"] = jQuery("#xq").val();
				
				var gridSetting = {
					caption:"参评学生列表 ",
					pager:"pager",
					url:"xpj_zcfs_yf.do?method=editZcfss&doType=query&editType=edit&zcyf="+zcyf,
					params:map
				};
					
				var zcxm = jQuery("font[name=zcxm]");
				var colList=[
					   {label:'key',name:'id', index: 'id',hidden:true,key:true},
					   {label:'学号',name:'xh', index: 'xh',width:'15%'},
					   {label:'姓名',name:'xm', index: 'xm',width:'15%'},
					   {label:'班级',name:'bjmc', index: 'bjmc',width:'15%'},
					   {label:'月份',name:'zcyf', index: 'zcyf',width:'15%',hidden:yfHidden}
					];
				
				jQuery.each(zcxm,function(i,n){
					var json = {label:jQuery(n).attr("xmmc"),
								name:"fs"+i,
								index:"fs"+i
								
								};
				if(fsWriteable){
					if (jQuery(n).attr("xmlx") != "3" && jQuery(n).attr("jktb") ==""){
						json["formatter"] = function(cell,rowObject){
							
							var html="";
							
							if(jQuery(n).attr("xmlx") == "4"){
								html+="<select onchange=\"saveZcfsYf(this,'"+jQuery(n).attr("xmdm")+"','"+rowObject["xh"]+"')\">";
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
								html+= "<input onblur=\"checkInputNum(this);saveZcfsYf(this,'";
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
						else{
							json["hidden"]=zczfHid;
							}
						
				}
					colList.push(json);
				});
				
				gridSetting["colList"] = colList;
				return gridSetting;
				
			}
			jQuery(function(){
				jQuery("#zcyf").change(function(){
					changeYf();
					var gridSetting = getGrid();
					jQuery("#dataTable").initGrid(gridSetting);
					});
				changeYf();
				var gridSetting = getGrid();
				jQuery("#dataTable").initGrid(gridSetting);
				});
			//切换月份
			function changeYf() {
				 zcyf = jQuery("#zcyf").val();
				if (zcyf == "all") {
					jQuery("#li_dr").css("display", "none");
					jQuery("#li_zj").css("display", "none");
					jQuery("#li_tj").css("display", "none");
					jQuery("#li_sc").css("display", "none");
					jQuery("#li_tb").css("display", "");
				} else {
				    var	para = jQuery("#tjzt").find("[value='"+zcyf+"']").text();
				    if(para == '0' || para == "2"){
				    	jQuery("#li_dr").css("display", "");
						jQuery("#li_zj").css("display", "");
						jQuery("#li_tj").css("display", "");
						jQuery("#li_sc").css("display", "");
				    }else{
				    	jQuery("#li_dr").css("display", "none");
						jQuery("#li_zj").css("display", "none");
						jQuery("#li_tj").css("display", "none");
						jQuery("#li_sc").css("display", "none");
				    }
					jQuery("#li_tb").css("display", "none");
				}
				
				
			}
						
		</script>
		
		<style>
			/*当终测项目过多时，本页列表会显示不全。增加列表滚动条显示*/
			.formbox {overflow:scroll}
			.formbox table tbody tr td{white-space:nowrap;}
		</style>
		
	</head>

	<body>
	<html:form action="xpj_zcfs.do">
		<input type="hidden" value="${cssz.xn }" id="xn"/>
		<input type="hidden" value="${cssz.xq }" id="xq"/>
		<input type="hidden" value="${xpjZcfsModel.id }" id="id"/>
		<input type="hidden" value="${bjdm}" id="bjdm"/>
		<input type="hidden" value='${jsonStr }' id="jsonStr" />
		<input type="hidden" value='${szyf}' id="szyf" />
		<div style="display:none">
		 	<select id="tjzt" name="tjzt">
		 		<logic:iterate id="i" name="yftjztlist">
		 			<option value="${i.yf}">${i.tjzt}</option>
		 		</logic:iterate>
		 	</select>
		</div>
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
					<li id="li_zj"><a  href="javascript:void(0);" onclick="showDialog('增加参评学生',750,480,'xpj_cpmd.do?method=viewAddCpxsList&bjdm=${bjxxMap.bjdm }');" class="btn_zj"
						   title="在这里，您可以将不在列表中的参评学生找回来。"
						>增加</a></li>
					</logic:notEqual>
					<li id="li_sc"><a href="javascript:void(0);"  onclick="cpxsDelete();" class="btn_sc"
						   title="在这里，您可以取消不在该班级参评的学生。"
						>删除</a></li>
<%--					<li><a href="javascript:void(0);" onclick="doEditSubmit('${xpjZcfsModel.id}');" class="btn_up"--%>
<%--						   title="您确认参评学生、综测分数无误后请点击这里提交;系统会在您提交后计算出综测分排名。"--%>
<%--						>提交</a></li>--%>
					<li id="li_dr"><a  href="javascript:void(0);" onclick="toImport();" class="btn_dr"
						   title="在这里，您可以将已经整理好的学生综测分数快速导入到系统当中。"
						>导出/导入</a></li>			
					<logic:present name="zcxmList">
						<logic:iterate id="z" name="zcxmList">
							<logic:notEmpty name="z" property="jktb">
								<li id="li_tb"><a href="javascript:void(0);" onclick="getIntefaceData('${z.xmdm}')" class="btn_sx">同步${z.xmmc }</a></li>						
							</logic:notEmpty>
						</logic:iterate>
					</logic:present>
					<li id="li_tj"><a href="javascript:void(0);" onclick="submitZcfsyf();" class="btn_up"><bean:message key="lable.zctj" /></a></li>
					
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
						<logic:equal value="1" name="szyf">
						<th width="12%">综测月份</th>
						<td>
							<html:select property="zcyf" styleId="zcyf" style="width:150px">
							<html:option value="all">总分</html:option>
							<html:options collection="zcyfList" property="zcyf"
								labelProperty="zcyfmc" />
							</html:select>
						</td>
						</logic:equal>
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
		</html:form>
	</body>
</html>
