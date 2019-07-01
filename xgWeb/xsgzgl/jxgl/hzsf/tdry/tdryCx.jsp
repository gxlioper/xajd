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
			var url = "jxgl_tdry_ajax.do?method=tdryCx";
			var ie = "10.0";
			var xn = jQuery("#xn").val();
			var bzjbdm = jQuery("#bzjbdm").val();
 			var tdrydm = jQuery("#tdrydm").val();
 			var bzdm = jQuery("#bzdm").val();
 			if(xn==""){
 	 			alertInfo("学年不能为空",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
				return false;
 			}
 	 		var query=xn+"!!splitOne!!"+bzjbdm+"!!splitOne!!"+tdrydm+"!!splitOne!!"+bzdm+"!!splitOne!!end";
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
			showTopWin('jxgl_tdry.do?method=tdryZj&xn='+xn,450,350);
		}
		//修改查看
		function showModi(type){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len==1){
				var pkValue=jQuery("[name=div_pkValue]:checked").val();
				var url="jxgl_tdry.do?method=tdryXg";	
				url+="&doType="+type;	
				url+="&pkValue="+pkValue;
				showTopWin(url,450,350);
			}else{
				alertInfo("请勾选一条数据操作！",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
			}
		}
		//删除
		function tdrySc(){
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
						var url="jxgl_tdry_ajax.do?method=tdrySc";	
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
		function getBzdmList(){
			if($("bzjbdm") && $("bzjbdm").value==""){
				jQuery('#bzdm').empty();
				jQuery('#bzdm').append("<option value=''></option>");
				jQuery("#tr_bzxx").hide();
				return false;
     		}
     		if($("bzjbdm").value=="1"){
         		jQuery("#bzxx").text("团级");
     		}else if($("bzjbdm").value=="2"){
         		jQuery("#bzxx").text("营级");
     		}else if($("bzjbdm").value=="3"){
         		jQuery("#bzxx").text("连级");
     		}
			jQuery.ajaxSetup({async:false});
			var parameter ={};
		    parameter["xn"]=escape(jQuery("#xn").val());
		    parameter["bzjbdm"]=escape(jQuery("#bzjbdm").val());
			jQuery.getJSON('jxgl_tdry_ajax.do?method=getBzdmList',parameter,function(data){
				jQuery("#tr_bzxx").show();
				jQuery('#bzdm').empty();
				jQuery('#bzdm').append("<option value=''></option>");
				if(data != null && data.length != 0){
					for(var i=0; i<data.length; i++){
						var option = "<option value=\"" + data[i].dm + "\">" + data[i].mc + "</option>";
						jQuery('#bzdm').append(option);
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
		<html:form action="/jxgl_tdry" method="post">
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
						<li><a href="#" onclick="tdrySc();return false;" class="btn_sc">删除</a></li>
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
									<button type="button" class="btn_cz" onclick="reset();jQuery('#tr_bzxx').hide();return false;" id="btn_cz">
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
								<html:select name="rs" property="xn" style="width:180px" styleId="xn" onchange="getBzdmList()">
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
							</td>
							<th>编制级别</th>
							<td>
								<html:select property="bzjbdm" style="width:180px" styleId="bzjbdm" onchange="getBzdmList()">
									<html:option value=""></html:option>
									<html:options collection="zjList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
							<th>团队荣誉</th>
							<td>
								<html:select property="tdrydm" style="width:180px" styleId="tdrydm">
									<html:option value=""></html:option>
									<html:options collection="tdryList" property="tdrydm" labelProperty="tdrymc"/>
								</html:select>
							</td>
						</tr>
						<tr id="tr_bzxx" style="display: none;">
							<th id="bzxx">
							</th>
							<td>
								<html:select property="bzdm" style="width:180px" styleId="bzdm">
									<html:option value=""></html:option>
								</html:select>
							</td>
							<th>
							</th>
							<td>
							</td>
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
					page="/sjcz/turnpage.jsp?form=jxglTdryForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
