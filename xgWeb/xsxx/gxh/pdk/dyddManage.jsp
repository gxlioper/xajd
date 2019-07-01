<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<!-- 头文件 -->
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/table/uniteCol.js"></script>
		<script type="text/javascript">
			function searchRs(){
				allNotEmpThenGo('moralCard.do?method=dyddManage');
			}
			
			jQuery(function(){
			  	var that;  
			  	jQuery('tr', jQuery('#dataTab')).each(function(row){
		  		jQuery('td:eq(0)', this).filter(':visible').each(function(col) {
		  		   	if (that!=null && jQuery(this).html() == jQuery(that).html()) {
			  		   		rowspan = jQuery(that).attr("rowspan");        
				  		   	if (rowspan == undefined) {
				  		   	 	jQuery(that).attr("rowspan",1);
								rowspan = jQuery(that).attr("rowspan");
							}         
							rowspan = Number(rowspan)+1;
							jQuery(that).attr("rowspan",rowspan); 
							jQuery(this).hide();  
							
								
							jQuery('td:eq(1)',jQuery(that).parents('tr').eq(0)).attr("rowspan",rowspan); 
							jQuery('td:eq(1)',jQuery(this).parents('tr').eq(0)).hide();  
							
							jQuery('td:eq(2)',jQuery(that).parents('tr').eq(0)).attr("rowspan",rowspan); 
							jQuery('td:eq(2)',jQuery(this).parents('tr').eq(0)).hide();  
							
							jQuery('td:eq(3)',jQuery(that).parents('tr').eq(0)).attr("rowspan",rowspan); 
							jQuery('td:eq(3)',jQuery(this).parents('tr').eq(0)).hide();  
							
							jQuery('td:eq(4)',jQuery(that).parents('tr').eq(0)).attr("rowspan",rowspan); 
							jQuery('td:eq(4)',jQuery(this).parents('tr').eq(0)).hide();  
							
							jQuery('td:eq(5)',jQuery(that).parents('tr').eq(0)).attr("rowspan",rowspan); 
							jQuery('td:eq(5)',jQuery(this).parents('tr').eq(0)).hide();  
							
							jQuery('td:eq(6)',jQuery(that).parents('tr').eq(0)).attr("rowspan",rowspan); 
							jQuery('td:eq(6)',jQuery(this).parents('tr').eq(0)).hide();  
							
						} else { 
							that = this; 
						}   
					}); 
				});
			})
		</script>
	</head>
	<body>
		<html:form action="/moralCard" method="post">
			<input type="hidden" id="userName" name="userName"
				value="${userName}" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx}" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" name="realTable" value="xg_xsxx_dyddb" />

			<input type="hidden" name="userDep" id="userDep" value="${userDep }" />
			<input type="hidden" id="path" name="searchModel.path"
				value="dyddgl.do" />

			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title}</a>
				</p>
			</div>

			<div class="toolbox">
				<!--按钮	-->
				<div class="buttonbox">
						<ul>
							<li>
								<a href="#" class="btn_dc" onclick="expData('moralCard.do?method=expDydd')">德育等第导出</a>
							</li>
						</ul>
				</div>
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp;</span>
				</h3>

				<div class="con_overlfow">
					<table class="dateline" width="100%">
						<thead>
							<tr>
								<td style="width:30px">
									<input type="checkbox" disabled="disabled" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="0" scope="request">
									<td>
										${tit}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody id="dataTab">
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr>
										<td>
											<input type="checkbox" id="cbv" name="primarykey_cbv"
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
										</td>
										<logic:iterate id="v" name="s">
											<td>
												${v }
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
						</tbody>
					</table>
				</div>
				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=xsxxCommForm"></jsp:include>
				<!--分页显示-->
			</div>
		</html:form>
	</body>
</html>
