<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<%@page import="xsgzgl.qgzx.jcdmwh.QgzxJcdmwhForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript">
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
			var url = "jxgl_dmwh_ajax.do?method=grryWh";
			var ie = "10.0";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}
		//岗位性质div操作 增加、修改
		var doType;
		function grryDiv(type,topMsg){
			var len=jQuery("[name=div_pkValue]:checked").length;
			$("grrydm").value="";
			$("grrymc").value="";
			doType=type;
			if(type=="update"){
				if(len==1){
					var pkValue=jQuery("[name=div_pkValue]:checked").val();
					var message = checkGrry(pkValue);
					if("true"!=message){
						alertInfo(message,function(tag){
							if(tag=="ok"){
								return false;
							}
						});
						return false;
					}
					$("grrydm").value=jQuery("[name=div_pkValue]:checked").val();
					$("grrymc").value=jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(3).text();
				}else{
					alertInfo("请勾选一条记录进行修改！");
					return false;
				}
			}
			tipsWindown(topMsg,"id:tempDiv","350","120","true","","true","id");
			jQuery("#grrymc").focus();
		}
		//div保存
		function DivSave(){
			if($("grrymc").value.trim()==""){
				alertInfo("请输入个人荣誉名称！");
				return false;
			}
			var parameter={}
			var url="jxgl_dmwh_ajax.do?method=grryBc&doType="+doType;
			parameter["grrydm"]=escape(jQuery("#grrydm").val());
			parameter["grrymc"]=escape(jQuery("#grrymc").val());						
			jQuery.ajaxSetup({async:false});	
			jQuery.post(url,
				parameter,
				function(result){
					if("保存成功"==result){
						alertInfo(result,function(tag){
							if(tag=="ok"){
								closeWindown();
								onShow();
							}
						});
					}else{
						alertInfo(result);
					}
				}
			);
			jQuery.ajaxSetup({async:true});
		}

		//删除
		function grrySc(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len>=1){
				var array = jQuery("[name=div_pkValue]:checked");
				var str = "";
				for (var i=0;i<array.length;i++) {
					var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
					str += pkValue+"!!@@!!";
				}
				var message = checkGrry(str);
				if("true"!=message){
					alertInfo(message,function(tag){
						if(tag=="ok"){
							return false;
						}
					});
					return false;
				}
				confirmInfo("是否确定删除勾选的记录？",function(tag){
					if(tag=="ok"){
						var parameter={}
						var url="jxgl_dmwh_ajax.do?method=grrySc";	
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
		//验证修改删除信息
		function checkGrry(str){
			var data = "true";
			var parameter={}
			var url="jxgl_dmwh_ajax.do?method=checkGrry";	
			parameter["pkValue"]=str;							
			jQuery.ajaxSetup({async:false});	
			jQuery.post(url,
				parameter,
				function(result){
					data = result;
				}
			);
			jQuery.ajaxSetup({async:true});
			return data;
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
		<html:form action="/jxgl_dmwh" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
						<li><a href="#" onclick="grryDiv('add','增加个人荣誉');return false;" class="btn_zj">增加</a></li>
						<li><a href="#" onclick="grryDiv('update','修改个人荣誉');return false;" class="btn_xg">修改</a></li>
						<li><a href="#" onclick="grrySc();return false;" class="btn_sc">删除</a></li>
						</logic:equal>
						<li><a href="#" onclick="configureExportData();return false;" class="btn_dc">导出数据</a></li>
					</ul>
				</div>
				<div class="comp_title">
					<ul>
			      		<li class="ha"><a href="#"><span>个人荣誉代码</span></a></li>
			       	 	<li><a href="jxgl_dmwh.do?method=tdryWh"><span>团队荣誉代码</span></a></li>
			     	 </ul>
		     	 </div>
				<div style="display: none;">
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
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
					page="/sjcz/turnpage.jsp?form=jxglDmwhForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>			
			<div id="tempDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>个人荣誉代码维护</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<span class="red">*</span>个人荣誉名称
								</th>
								<td>
									<input type="hidden" id="grrydm" name="grrydm"/>
									<input type="text" id="grrymc" name="grrymc" maxlength="50"/>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<button type="button" name="保存" onclick="DivSave()">
											保 存
										</button>
										<button type="button" name="取消" onclick="closeWindown();return false;">
											取 消
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
