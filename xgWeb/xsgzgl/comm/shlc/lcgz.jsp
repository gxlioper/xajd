<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript">
	jQuery(document).ready(function() {
		setTimeout(show,100);
		
    });
	function show(){

		/**
		jQuery(".zt1").each(function(){
        	var height=jQuery(this).children().height();
			if(height>60){
				jQuery(this).children().addClass("height60");
				jQuery(this).children().children(".more").show();
				
				var ID=jQuery(this).children().attr("id");
				jQuery("#"+ID).hover(function() {
					jQuery("#"+ID).addClass("content1");
					jQuery(this).children(".more").hide();
					jQuery("#"+ID).removeClass("height60");
				},function(){
					jQuery("#"+ID).removeClass("content1");
					jQuery(this).children(".more").show();
					jQuery("#"+ID).addClass("height60");
				});
			}
		})
		**/
		
		jQuery(".step ul li").each(function(){
			var classname	=	jQuery(this).attr("class");
			if(classname	==	"yzz"){
				jQuery(".step .text").html("����[��ͨ��]");
				return false;
			}else if(classname	==	"shz"){
				jQuery(".step .text").html("�����");
				return false;
			}else if(classname	==	"wsh"){
				jQuery(".step .text").html("���˻�������");
				return false;
			}else{
				jQuery(".step .text").html("����[ͨ��]");
			}
		})
		
		/*��content��ֵ���*/
		var widthall	=	jQuery(".zt1").width();
		jQuery(".zt1 .content").css("width",widthall-57);
		
		/*��li��ֵ���*/
		var len	=	jQuery(".step").width()-11;
		/*���һ��li��ʵ�ʿ��*/
		var len1	=	jQuery(".step ul li").last().width()+1;
		/*��ǰ״̬��ʵ�ʿ��*/
		var len2	=	jQuery(".step .text").width()+30;
		var len_ul	=	len	-	len1	-	len2;
		var num	=	jQuery(".step ul li").length-1;
		var li_width	=	len_ul/num;
		jQuery(".step ul li").css("width",li_width);
		
		jQuery(".step ul li").last().children(".num").css("width","19px");
		jQuery(".step ul li").last().css("width",len1);
		jQuery(".step ul .shz").prevAll("li").addClass("ysh");
		jQuery(".step ul .yzz").prevAll("li").addClass("ysh");
		jQuery(".step ul .shz").nextAll("li").addClass("wsh");
		jQuery(".step ul .yzz").nextAll("li").addClass("wsh");

		/**
		//��������������ʾ����������
		//�Ų�·[982]
		var len=30;//�Զ����г���
		jQuery(".content").each(function(){
			var div =jQuery(this).find("div").last();
			var span=div.find("span");
			var cla=span.attr("class");
			var shyj=span.text();
			if(shyj.length>len){
				//��ȡ��ʾ
				span.text(shyj.substring(0,len));
			}
			//����������
			var ccStr=shyj.substring(len,shyj.length);
			var ccHtml=format(ccStr,len,cla);
			jQuery(this).append(ccHtml);
		});
		**/
	}
	//��ʽ����������Ϊhtml
	//�Ų�·[982]
	function format(str,len,cla){
		var strHtml="<div style=\"padding-left: 60px;\"><span class=\""+cla+"\">";
		if(str.length>len){
			strHtml+=str.substring(0,len);
			strHtml+="</span></div>";
			var syStr=str.substring(len,str.length);
			strHtml+=format(syStr,len,cla);
		}else{
			strHtml+=str;
			strHtml+="</span></div>";
		}
		return strHtml;
	}
    
    </script>

	</head>
	<body>
			<div class="splc1">
		            
		            <div class="step" >
		            	<ul>
				            <logic:iterate id="gw" name="gwList" indexId="ss">
				            	<logic:greaterThan value="${dqgw.xh}" name="gw"  property="xh">
				            		<li class="wsh"><p class="user">${gw.mc}</p><p class="num">${ss+1}</p></li>
				            	</logic:greaterThan>
				            	<logic:lessThan value="${dqgw.xh}" name="gw"  property="xh">
				            		<li class="ysh"><p class="user">${gw.mc}</p><p class="num">${ss+1}</p></li>
				            	</logic:lessThan>
				            	<logic:equal value="${dqgw.xh}" name="gw"  property="xh">
				            		<logic:equal value="0" name="dqgw"  property="shzt">
				            			<li class="shz"><p class="user">${gw.mc}</p><p class="num">${ss+1}</p></li>
				            		</logic:equal>
				            		<logic:equal value="1" name="dqgw"  property="shzt">
				            			<li class="ysh"><p class="user">${gw.mc}</p><p class="num">${ss+1}</p></li>
				            		</logic:equal>
				            		<logic:equal value="2" name="dqgw"  property="shzt">
				            			<li class="yzz"><p class="user">${gw.mc}</p><p class="num">${ss+1}</p></li>
				            		</logic:equal>
				            		<logic:equal value="" name="dqgw"  property="shzt">
				            			<li class="wsh"><p class="user">${gw.mc}</p><p class="num">${ss+1}</p></li>
				            		</logic:equal>
				            	</logic:equal>
			                </logic:iterate>
		                </ul>
			            <div class="text"></div>
			        </div>
		            
			    <div class="clearall"  style="overflow-x:hidden;overflow-y:hidden;"></div>    
				<!--��ͨ��-->
				<logic:iterate id="sp" name="infoList" indexId="idx" length="len" >
					<logic:equal value="0" name="sp" property="shzt">
					   <div class="zt1"  style="overflow-x:hidden;overflow-y:hidden;"> 
					        <div class="wks" id="dsh">
					            <a class="more" href="javascript:;"></a>
					            <div class="type"><div class="title">�����</div></div>
					            <div class="content">
					                <div>��˸�λ��<span class="black1">${sp.mc}</span><logic:notEqual name="xxdm" value="10956">����ˣ�<span class="black1">${sp.shr }</span></logic:notEqual>���ʱ�䣺<span class="black1">${sp.shsj }</span></div>
					                <div>��������<span class="black">${sp.shyj }</span></div>
					            </div>
					            <div style="clear:both"></div>
					        </div>
					   </div>
					   <logic:notEqual value="${idx}" name="gwListSize">
					   <div class="next"><img src="<%=stylePath %>images/knssh_wksnext.gif" /></div>
					   </logic:notEqual>
				    </logic:equal>
					<logic:equal value="1" name="sp" property="shzt">
					    <div class="zt1">
					        <div class="tg" id="tg${sp.guid}"  style="overflow-x:hidden;overflow-y:hidden;">
					            <a class="more" href="javascript:;"></a>
					            <div class="type"><div class="title">��ͨ��</div></div>
					            <div class="content">
					                <div>��˸�λ��<span class="green1">${sp.mc }</span><logic:notEqual name="xxdm" value="10956">����ˣ�<span class="black1">${sp.shr }</span></logic:notEqual>���ʱ�䣺<span class="black1">${sp.shsj }</span></div>
					                <div>��������<span class="green">${sp.shyj }</span></div>
					                <logic:notEmpty name="sp" property="zd1">
					                	<div>${sp.zd1}��<span class="green">${sp.zd3 }</span></div>
					                </logic:notEmpty>
					                <logic:notEmpty name="sp" property="zd10">
					                	<div>��˵�����λ��<span class="green">${sp.zd8 }_${sp.zd9 }_${sp.zd10 }</span></div>
					                </logic:notEmpty>
					            </div>
					            <div style="clear:both"></div>
					        </div>
					    </div>
					    <logic:notEqual value="${idx}" name="gwListSize">
					    <div class="next"><img src="<%=stylePath %>images/knssh_tgnext.gif" /></div>
					    </logic:notEqual>
				    </logic:equal>
				    <logic:equal value="2" name="sp" property="shzt">
					    <div class="zt1"  style="overflow-x:hidden;overflow-y:hidden;">
					        <!--δͨ��-->
					        <div class="wtg" id="btg${sp.guid}">
					            <a class="more" href="javascript:;"></a>
					            <div class="type"><div class="title">��ͨ��</div></div>
					            <div class="content">
					                <div>��˸�λ��<span class="red1">${sp.mc }</span><logic:notEqual name="xxdm" value="10956">����ˣ�<span class="black1">${sp.shr }</span></logic:notEqual>���ʱ�䣺<span class="black1">${sp.shsj }</span></div>
					                <div>��������<span class="red">${sp.shyj }</span></div>
					            </div>
					            <div style="clear:both"></div>
					        </div>
					   </div>
					   <logic:notEqual value="${idx}" name="gwListSize">
					   <div class="next"><img src="<%=stylePath %>images/knssh_wtgnext.gif" /></div>
					   </logic:notEqual>
				   </logic:equal>
				   <logic:equal value="3" name="sp" property="shzt">
					    <div class="zt1"  style="overflow-x:hidden;overflow-y:hidden;">
					        <!--δͨ��-->
					        <div class="wtg" id="yth${sp.guid}">
					            <a class="more" href="javascript:;"></a>
					            <div class="type"><div class="title">���˻�</div></div>
					            <div class="content">
					                <div>��˸�λ��<span class="red1">${sp.mc }</span><logic:notEqual name="xxdm" value="10956">����ˣ�<span class="black1">${sp.shr }</span></logic:notEqual>���ʱ�䣺<span class="black1">${sp.shsj }</span></div>
					                <div>��������<span class="red">${sp.shyj }</span></div>
					            </div>
					            <div style="clear:both"></div>
					        </div>
					   </div>
					   <logic:notEqual value="${idx}" name="gwListSize">
					   <div class="next"><img src="<%=stylePath %>images/knssh_wtgnext.gif" /></div>
					   </logic:notEqual>
				   </logic:equal>
				   <logic:equal value="4" name="sp" property="shzt">
					    <div class="zt1"  style="overflow-x:hidden;overflow-y:hidden;">
					        <!--δͨ��-->
					        <div class="wtg" id="xcs${sp.guid}">
					            <a class="more" href="javascript:;"></a>
					            <div class="type"><div class="title">������</div></div>
					            <div class="content">
					                <div>��˸�λ��<span class="red1">${sp.mc }</span><logic:notEqual name="xxdm" value="10956">����ˣ�<span class="black1">${sp.shr }</span></logic:notEqual>���ʱ�䣺<span class="black1">${sp.shsj }</span></div>
					                <div>��������<span class="red">${sp.shyj }</span></div>
					            </div>
					            <div style="clear:both"></div>
					        </div>
					   </div>
					   <logic:notEqual value="${idx}" name="gwListSize">
					   <div class="next"><img src="<%=stylePath %>images/knssh_wtgnext.gif" /></div>
					   </logic:notEqual> 
				   </logic:equal>
			    </logic:iterate>
			</div>

	</body>
</html>
