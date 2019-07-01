<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript">
		jQuery(function() {
			jQuery(".zt").each(function(){
	        	var height=jQuery(this).children().height();
				if(height>60){
					jQuery(this).children().addClass("height60");
					jQuery(this).children().children(".more").show();
					
					/*鼠标移动事件*/
					var ID=jQuery(this).children().attr("id");
					jQuery("#"+ID).hover(function() {
						jQuery("#"+ID).addClass("content1");
						jQuery(this).children(".more").hide();
						jQuery("#"+ID).removeClass("height60");
					},function(){
						jQuery("#"+ID).css("box-shadow","0 0 0 0");
						jQuery(this).children(".more").show();
						jQuery("#"+ID).addClass("height60");
					});
			
				}
			})
	    });
		</script>
	</head>
	<body>
		<div class="splc" style="margin-top: 8px;">
			<logic:present name="infoList">
				<% 
					List<HashMap<String,String>> infoList = (List<HashMap<String,String>>)request.getAttribute("infoList"); 
				%>
				
				<logic:iterate name="infoList" id="info" indexId="i">
					<!--已通过-->
					<logic:equal value="通过" name="info" property="shzt">
					    <div class="zt" >
					        <div class="tg" id="tg${i }">
					            <a class="more" href="javascript:;"></a>
					            <div class="type"><div class="title">已通过</div></div>
					            <div class="content">
					                <div>
					                	审核岗位：<span class="green1">${info.gwmc }</span>
					                	审核人：<span class="black1">${info.shr }
					                		<logic:notEmpty name="info" property="yhm">
												[${info.yhm }]
											</logic:notEmpty>
					                	</span>
					                	审核时间：<span class="black1">${info.shsj }</span>
					                </div>
					                <div>审核意见：<span class="green">${info.shyj }</span></div>
					            </div>
					            <div style="clear:both"></div>
					        </div>
					    </div>
					      <%
					    	if (i != infoList.size()-1){
					      %>
						    <div class="next"><img src="<%=stylePath %>images/knssh_tgnext.gif"></div>
						 <%
					    	}
						 %>
				    </logic:equal>
				    
				    
				    <logic:equal value="不通过" name="info" property="shzt">
					   <div class="zt">
					        <!--未通过-->
					        <div class="wtg" id="wtg${i }">
					            <a class="more" href="javascript:;"></a>
					            <div class="type"><div class="title">不通过</div></div>
					            <div class="content">
					                <div>
					                	审核岗位：<span class="red1">${info.gwmc }</span>
					                	审核人：<span class="black1">${info.shr }
					                			<logic:notEmpty name="info" property="yhm">
													[${info.yhm }]
												</logic:notEmpty>
					                	</span>
					                	审核时间：<span class="black1">${info.shsj }</span>
					                </div>
					                <div>审核意见：<span class="red">${info.shyj }</span></div>
					            </div>
					            <div style="clear:both"></div>
					        </div>
					   </div>
					      <%
					    	if (i != infoList.size()-1){
					      %>
					  	 <div class="next"><img src="<%=stylePath %>images/knssh_wtgnext.gif"></div>
					     <%
					    	}
					     %>
				    </logic:equal>
				    <logic:equal value="退回" name="info" property="shzt">
					   <div class="zt">
					        <!--未通过-->
					        <div class="wtg" id="wtg${i }">
					            <a class="more" href="javascript:;"></a>
					            <div class="type"><div class="title">退回</div></div>
					            <div class="content">
					                <div>
					                	审核岗位：<span class="red1">${info.gwmc }</span>
					                	审核人：<span class="black1">${info.shr }
					                			<logic:notEmpty name="info" property="yhm">
													[${info.yhm }]
												</logic:notEmpty>
					                	</span>
					                	审核时间：<span class="black1">${info.shsj }</span>
					                </div>
					                <div>审核意见：<span class="red">${info.shyj }</span></div>
					            </div>
					            <div style="clear:both"></div>
					        </div>
					   </div>
					      <%
					    	if (i != infoList.size()-1){
					      %>
					  	 <div class="next"><img src="<%=stylePath %>images/knssh_wtgnext.gif"></div>
					     <%
					    	}
					     %>
				    </logic:equal>
				    
				     <logic:equal value="未审核" name="info" property="shzt">
					  	<div class="zt" > 
					        <!--未开始-->
					        <div class="wks" id="wks${i }">
					            <a class="more" href="javascript:;"></a>
					            <div class="type"><div class="title">未审核</div></div>
					            <div class="content">
					                <div>
					                	审核岗位：<span class="black1">${info.gwmc }</span>
					                	审核人：<span class="black1">${info.shr }</span>
					                	审核时间：<span class="black1">${info.shsj }</span>
					                </div>
					                <div>审核意见：<span class="black">${info.shyj }</span></div>
					            </div>
					            <div style="clear:both"></div>
					        </div>
					   </div>
					      <%
					    	if (i != infoList.size()-1){
					      %>
					   		<div class="next"><img src="<%=stylePath %>images/knssh_wksnext.gif"></div>
					   	  <%
					    	}
					   	  %>
				  	</logic:equal>
				  	<logic:equal value="需重审" name="info" property="shzt">
					  	<div class="zt" > 
					        <!--未开始-->
					        <div class="wks" id="wks${i }">
					            <a class="more" href="javascript:;"></a>
					            <div class="type"><div class="title">需重审</div></div>
					            <div class="content">
					                <div>
					                	审核岗位：<span class="black1">${info.gwmc }</span>
					                	审核人：<span class="black1">${info.shr }</span>
					                	审核时间：<span class="black1">${info.shsj }</span>
					                </div>
					                <div>审核意见：<span class="black">${info.shyj }</span></div>
					            </div>
					            <div style="clear:both"></div>
					        </div>
					   </div>
					      <%
					    	if (i != infoList.size()-1){
					      %>
					   		<div class="next"><img src="<%=stylePath %>images/knssh_wksnext.gif"></div>
					   	  <%
					    	}
					   	  %>
				  	</logic:equal>
			</logic:iterate>
		</logic:present>
	</body>
</html>
