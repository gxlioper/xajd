<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<%@page import="xsgzgl.qgzx.jcdmwh.QgzxJcdmwhForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		function onShow(){ 
			searchRs();
		}
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}
		//查询
		function searchRs(){
			var url = "jxgl_grry_ajax.do?method=grryCx";
			var ie = "10.0";
			var xn = jQuery("#xn").val();
 			var nj = jQuery("#nj").val();
 			var grrydm = jQuery("#grrydm").val();
 			var xydm = jQuery("#xy").val();
 			var zydm = jQuery("#zy").val();
 			var bjdm =jQuery("#bj").val();
 			var tuandm = jQuery("#tuandm").val();
 			var yingdm = jQuery("#yingdm").val();
 			var liandm = jQuery("#liandm").val();
 			var xh = escape(jQuery("#xh").val());
 			var xm = escape(jQuery("#xm").val());
 			if(xn==""){
 	 			alertInfo("学年不能为空",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
				return false;
 			}
 	 		var query=xn+"!!splitOne!!"+nj+"!!splitOne!!"+grrydm+"!!splitOne!!"+xydm+"!!splitOne!!"+zydm+"!!splitOne!!";
 	 		query+=bjdm+"!!splitOne!!"+tuandm+"!!splitOne!!"+yingdm+"!!splitOne!!"+liandm+"!!splitOne!!"+xh+"!!splitOne!!"+xm+"!!splitOne!!end";
			var otherValue = [ie,query];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000");
		}
		//增加
		function showAdd(){
			var xn = jQuery("#xn").val();
			if(xn==""){
 	 			alertInfo("学年不能为空",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
				return false;
 			}
			
			showTopWin('jxgl_grry.do?method=grryZj&xn='+xn,600,360);
		}
		//修改查看
		function showModi(type){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len==1){
				var pkValue=jQuery("[name=div_pkValue]:checked").val();
				var url="jxgl_grry.do?method=grryXg";	
				url+="&doType="+type;	
				url+="&pkValue="+pkValue;
				showTopWin(url,600,360);
			}else{
				alertInfo("请勾选一条数据操作！",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
			}
		}
		//删除
		function grrySc(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len>=1){
				var array = jQuery("[name=div_pkValue]:checked");
				var str = "";
				for (var i=0;i<array.length;i++) {
					var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
					str += pkValue+"!!splitOne!!";
				}
				confirmInfo("是否确定删除勾选的记录？",function(tag){
					if(tag=="ok"){
						var parameter={}
						var url="jxgl_grry_ajax.do?method=grrySc";	
						parameter["pkValue"]=str;							
						jQuery.ajaxSetup({async:false});	
						jQuery.post(url,
							parameter,
							function(result){
								alertInfo(result,function(tag){
									if(tag=="ok"){
										onShow();
									}
								});
							}
						);
						jQuery.ajaxSetup({async:true});
					}
				});
			}else{
				alertInfo("请勾选需要删除的数据！",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
			}
		}
		//菜单联动
		function initTuanList(){
			jQuery.ajaxSetup({async:false});
			var parameter ={};
		    parameter["xn"]=escape(jQuery("#xn").val());
		    parameter["bzdj"]=escape("1");
		    parameter["sjdm"]=escape("");
			jQuery.getJSON('jxgl_grry_ajax.do?method=getTuanYingLianList',parameter,function(data){
				jQuery('#tuandm').empty();
				jQuery('#tuandm').append("<option value=''></option>");
				if(data != null && data.length != 0){
					for(var i=0; i<data.length; i++){
						var option = "<option value=\"" + data[i].dm + "\">" + data[i].mc + "</option>";
						jQuery('#tuandm').append(option);
					}
				}
			});
			jQuery.ajaxSetup({async:true});
		}
		//初始化营菜单
		function initYingList(){
			jQuery.ajaxSetup({async:false});
			var parameter ={};
			parameter["xn"]=escape(jQuery("#xn").val());
		    parameter["bzdj"]=escape("2");
		    parameter["sjdm"]=escape(jQuery("#tuandm").val());
			jQuery.getJSON('jxgl_grry_ajax.do?method=getTuanYingLianList',parameter,function(data){
				jQuery('#yingdm').empty();
				jQuery('#yingdm').append("<option value=''></option>");
				if(data != null && data.length != 0){
					for(var i=0; i<data.length; i++){
						var option = "<option value=\"" + data[i].dm + "\">" + data[i].mc + "</option>";
						jQuery('#yingdm').append(option);
					}
				}
			});
			jQuery.ajaxSetup({async:true});
		}
		//初始化连菜单
		function initLianList(){
			jQuery.ajaxSetup({async:false});
			var parameter ={};
			parameter["xn"]=escape(jQuery("#xn").val());
		    parameter["bzdj"]=escape("3");
		    parameter["sjdm"]=escape(jQuery("#yingdm").val());
			jQuery.getJSON('jxgl_grry_ajax.do?method=getTuanYingLianList',parameter,function(data){
				jQuery('#liandm').empty();
				jQuery('#liandm').append("<option value=''></option>");
				if(data != null && data.length != 0){
					for(var i=0; i<data.length; i++){
						var option = "<option value=\"" + data[i].dm + "\">" + data[i].mc + "</option>";
						jQuery('#liandm').append(option);
					}
				}
			});
			jQuery.ajaxSetup({async:true});
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body >
		<div class="tab_cur" >
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/jxgl_grry" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
						<li><a href="#" onclick="showAdd();return false;" class="btn_zj">增加</a></li>
						<li><a href="#" onclick="showModi('update');return false;" class="btn_xg">修改</a></li>
						<li><a href="#" onclick="grrySc();return false;" class="btn_sc">删除</a></li>
						<li><a href="#" onclick="impAndChkData();return false;" class="btn_dr">导入数据</a></li>
						</logic:equal>
						<li><a href="#" onclick="showModi('view');return false;" class="btn_ck">查看</a></li>
						<li><a href="#" onclick="choiceFields();return false;" class="btn_sz">导出设置</a></li>
						<li><a href="#" onclick="configureExportData();return false;" class="btn_dc">导出数据</a></li>
					</ul>
				</div>
				<div style="display: none;">
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
				<div class="searchtab">
				<table>
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<button type="button" class="btn_cx" onclick="searchRs();return false;" id="search_go">
										查询
									</button>
									<button type="button" class="btn_cz" onclick="reset();return false;" id="btn_cz">
										重置
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>学年</th>
							<td>
								<html:select name="rs" property="xn" style="width:150px" styleId="xn" onchange="initTuanList();">
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
							</td>
							<th>年级</th>
							<td>
								<html:select property="nj" style="width:150px" styleId="nj">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj" labelProperty="nj"/>
								</html:select>
							</td>
							<th>个人荣誉</th>
							<td>
								<html:select property="grrydm" style="width:150px" styleId="grrydm">
									<html:option value=""></html:option>
									<html:options collection="grryList" property="grrydm" labelProperty="grrymc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xb" />
							</th>
							<td>
								<logic:equal name="userType" value="xy">
									<html:select property="xydm" styleId="xy" disabled="true" value="${userDep }" style="width:150px"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
									</html:select>
									<html:hidden property="xydm" value="${userDep }"/>
								</logic:equal>
								<logic:notEqual name="userType" value="xy">
								<html:select property="xydm" styleId="xy"  style="width:150px"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								</logic:notEqual>
							</td>
							<th>
								专业
							</th>
							<td>
								<html:select property="zydm" styleId="zy"  style="width:150px"
										onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<th>
								班级
							</th>
							<td>
								<html:select property="bjdm" styleId="bj"  style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>	
						<tr>
							<th>
								团级
							</th>
							<td>
								<html:select property="tuandm" style="width:150px" styleId="tuandm" onchange="initYingList();">
										<html:option value=""></html:option>
										<html:options collection="tuanList" property="dm"
											labelProperty="mc" />
								</html:select>
							</td>
							<th>营级</th>
							<td>
								<html:select property="yingdm" styleId="yingdm" style="width:150px" onchange="initLianList();">
									<html:option value=""></html:option>
								</html:select>
							</td>
							<th>连级</th>
							<td>
								<html:select property="liandm" style="width:150px" styleId="liandm">
									<html:option value=""></html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>学号</th>
							<td><html:text property="xh" styleId="xh"></html:text></td>
							<th>姓名</th>
							<td><html:text property="xm" styleId="xm"></html:text></td>
							<th>
							</th>
							<td>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			</div>
				<!-- 内容显示区开始 -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- 查询得到的数据量显示区域 -->
						</p>
					</div>
				</div>
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp;</span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=jxglGrryForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
