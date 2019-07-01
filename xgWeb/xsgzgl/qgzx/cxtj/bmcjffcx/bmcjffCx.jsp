<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<%@page import="xsgzgl.qgzx.jcdmwh.QgzxJcdmwhForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script>
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}
		function searchRs(){
			if($("nd") && $("nd").value.trim()==""){
				alertInfo("年度不能为空！",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
				return false;
			}
			var url = "qgzx_cxtj_ajax.do?method=bmcjffCx";
			url+="&nd="+jQuery("#nd").val();
			url+="&yf="+jQuery("#yf").val();
			url+="&bmdm="+jQuery("#bmdm").val();
			var ie = "ie";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}

		//导出
		function Dc(){
			var url = "qgzx_cxtj_ajax.do?method=bmcjffCxDc";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";	
		}
		</script>
	</head>
	<body onload="searchRs();">
		<div class="tab_cur" >
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/qgzx_cxtj" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						
						<li><a href="#" onclick="Dc();return false;" class="btn_dc">导出</a></li>
						
					</ul>
				</div>
				<div style="display: none;">
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
			</div>
			<div class="searchtab">
				<table>
					<tbody>
						<tr>
							<th>年度</th>
							<td>
								<html:select name="rs" property="nd" style="width:180px" styleId="nd">
									<html:options collection="ndList" property="nd" labelProperty="nd"/>
								</html:select>
							</td>
							<th>月份</th>
							<td>
								<html:select name="rs" property="yf" style="width:180px" styleId="yf">
									<html:option value="">全部</html:option>
									<html:options collection="yfList" property="yf" labelProperty="yf"/>
								</html:select>
							</td>
							<th>用人部门</th>
							<td>								
								<logic:equal name="rs" property="dis" value="true">
									<input type="hidden" id="bmdm" name="bmdm" value="${rs.bmdm}"/>
									<html:select name="rs" property="bmdm" style="width:180px" styleId="bmdm" disabled="${rs.dis}">
										<html:option value="">全部</html:option>
										<html:options collection="bmList" property="bmdm" labelProperty="bmmc"/>
									</html:select>
								</logic:equal>
								<logic:notEqual name="rs" property="dis" value="true">
									<html:select name="rs" property="bmdm" style="width:180px" styleId="bmdm" disabled="${rs.dis}">
										<html:option value="">全部</html:option>
										<html:options collection="bmList" property="bmdm" labelProperty="bmmc"/>
									</html:select>
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<div class="btn">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="btn_cx" id="search_go" onclick="searchRs();return false;">
										查询
									</button>
									<button type="button" class="btn_cz" id="btn_cz" onclick="reset()">
										重置
									</button>
								</div>
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
					<span> 统计结果&nbsp;&nbsp;</span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
				
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
