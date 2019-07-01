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
					
					/*����ƶ��¼�*/
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
					<!--��ͨ��-->
					<logic:equal value="ͨ��" name="info" property="shzt">
					    <div class="zt" >
					        <div class="tg" id="tg${i }">
					            <a class="more" href="javascript:;"></a>
					            <div class="type"><div class="title">��ͨ��</div></div>
					            <div class="content">
					                <div>
					                	��˸�λ��<span class="green1">${info.gwmc }</span>
					                	����ˣ�<span class="black1">${info.shr }
					                		<logic:notEmpty name="info" property="yhm">
												[${info.yhm }]
											</logic:notEmpty>
					                	</span>
					                	���ʱ�䣺<span class="black1">${info.shsj }</span>
					                </div>
					                <div>��������<span class="green">${info.shyj }</span></div>
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
				    
				    
				    <logic:equal value="��ͨ��" name="info" property="shzt">
					   <div class="zt">
					        <!--δͨ��-->
					        <div class="wtg" id="wtg${i }">
					            <a class="more" href="javascript:;"></a>
					            <div class="type"><div class="title">��ͨ��</div></div>
					            <div class="content">
					                <div>
					                	��˸�λ��<span class="red1">${info.gwmc }</span>
					                	����ˣ�<span class="black1">${info.shr }
					                			<logic:notEmpty name="info" property="yhm">
													[${info.yhm }]
												</logic:notEmpty>
					                	</span>
					                	���ʱ�䣺<span class="black1">${info.shsj }</span>
					                </div>
					                <div>��������<span class="red">${info.shyj }</span></div>
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
				    <logic:equal value="�˻�" name="info" property="shzt">
					   <div class="zt">
					        <!--δͨ��-->
					        <div class="wtg" id="wtg${i }">
					            <a class="more" href="javascript:;"></a>
					            <div class="type"><div class="title">�˻�</div></div>
					            <div class="content">
					                <div>
					                	��˸�λ��<span class="red1">${info.gwmc }</span>
					                	����ˣ�<span class="black1">${info.shr }
					                			<logic:notEmpty name="info" property="yhm">
													[${info.yhm }]
												</logic:notEmpty>
					                	</span>
					                	���ʱ�䣺<span class="black1">${info.shsj }</span>
					                </div>
					                <div>��������<span class="red">${info.shyj }</span></div>
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
				    
				     <logic:equal value="δ���" name="info" property="shzt">
					  	<div class="zt" > 
					        <!--δ��ʼ-->
					        <div class="wks" id="wks${i }">
					            <a class="more" href="javascript:;"></a>
					            <div class="type"><div class="title">δ���</div></div>
					            <div class="content">
					                <div>
					                	��˸�λ��<span class="black1">${info.gwmc }</span>
					                	����ˣ�<span class="black1">${info.shr }</span>
					                	���ʱ�䣺<span class="black1">${info.shsj }</span>
					                </div>
					                <div>��������<span class="black">${info.shyj }</span></div>
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
				  	<logic:equal value="������" name="info" property="shzt">
					  	<div class="zt" > 
					        <!--δ��ʼ-->
					        <div class="wks" id="wks${i }">
					            <a class="more" href="javascript:;"></a>
					            <div class="type"><div class="title">������</div></div>
					            <div class="content">
					                <div>
					                	��˸�λ��<span class="black1">${info.gwmc }</span>
					                	����ˣ�<span class="black1">${info.shr }</span>
					                	���ʱ�䣺<span class="black1">${info.shsj }</span>
					                </div>
					                <div>��������<span class="black">${info.shyj }</span></div>
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
