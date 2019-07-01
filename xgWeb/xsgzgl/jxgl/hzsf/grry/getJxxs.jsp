<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
		//查询
		function searchRs(){
			var url = "jxgl_grry_ajax.do?method=grryCx";
			var ie = "10.0";
			alert(1);
			var xn = jQuery("#xn").val();
 			var tuandm = jQuery("#tuandm").val();
 			var yingdm = jQuery("#yingdm").val();
 			var liandm = jQuery("#liandm").val();
 			var xh = escape(jQuery("#xh").val());
 			var xm = escape(jQuery("#xm").val());
 	 		var query=xn+"!!splitOne!!"+nj+"!!splitOne!!"+grrydm+"!!splitOne!!"+xydm+"!!splitOne!!"+zydm+"!!splitOne!!";
 	 		query+=bjdm+"!!splitOne!!"+tuandm+"!!splitOne!!"+yingdm+"!!splitOne!!"+liandm+"!!splitOne!!"+xh+"!!splitOne!!"+xm+"!!splitOne!!end";
			var otherValue = [ie,query];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000");
		}
		//初始化
		function onShow(){ 
			searchRs();
		}
		
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}
		
		function searchRs(){
			var url = "jxgl_grry_ajax.do?method=getJxxs";
			var ie = "10.0";
			var xn = jQuery("#xn").val();
 			var tuandm = jQuery("#tuandm").val();
 			var yingdm = jQuery("#yingdm").val();
 			var liandm = jQuery("#liandm").val();
 			var xh = jQuery("#xh").val();
 			var xm = jQuery("#xm").val();
 	 		var query=xn+"!!splitOne!!"+tuandm+"!!splitOne!!"+yingdm+"!!splitOne!!"+liandm+"!!splitOne!!"+xh+"!!splitOne!!"+xm+"!!splitOne!!end";
			var otherValue = [ie,query];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000");
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
		//双击发送学号
		function sendXh(){
			window.dialogArguments.document.getElementById('xh').value = curr_row.getElementsByTagName('input')[0].value;
			Close();
			window.dialogArguments.document.getElementById('disbutton').click();
		}
		
		jQuery(function(){
			onShow();
		})
	</script>
	</head>
	<body >

		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }-学生名单</a>
			</p>
		</div>


		<html:form action="/jxgl_grry_ajax" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xn" id="xn" value="${rs.xn}" />
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
									<button type="button" class="btn_cz" onclick="searchReset();return false;" id="btn_cz">
										重置
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
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
					<span> 查询结果&nbsp;&nbsp; </span>
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