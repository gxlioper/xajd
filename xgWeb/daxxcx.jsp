<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
 	<title>������Ϣ��ѯ</title>
 	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" /> 
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript">
	jQuery(function(){
		jQuery(".search_da_sfz").keydown(function(event){
			if(event.keyCode == 13){
				getXsDaxx();
			}
		});
		jQuery(".search_da_uname").keydown(function(event){
			if(event.keyCode == 13){
				getXsDaxx();
			}
		});
	})
	function clearInputValue(node){
		if(jQuery(node).attr("id") == "search_da_sfz" && jQuery(node).val() == "���������֤��"){
			jQuery(node).val("");
			jQuery(node).css("color","black");
		}else if(jQuery(node).attr("id") == "search_da_uname" && jQuery(node).val() == "����������"){
			jQuery(node).val("");
			jQuery(node).css("color","black");
		}
	}
	function shake(classstr){
	    var $panel = jQuery("."+classstr);
	    box_left = (jQuery(window).width() -  jQuery(".search_da_form").width()) / 2 + 40;
	    $panel.css({'left': box_left,'position':'absolute'});
	    for(var i=1; 4>=i; i++){
	        $panel.animate({left:box_left-(40-10*i)},50);
	        $panel.animate({left:box_left+2*(40-10*i)},50);
	    }
	}
	function getXsDaxx(){
		var xm = jQuery("#search_da_uname").val();
		var sfzh = jQuery("#search_da_sfz").val();
		
		jQuery.post("xsDaxxCx.do?method=queryXsDaxxAction",{xm: xm,sfzh:sfzh},function(json){
			var temphtml;
			if(!jQuery.isEmptyObject(json)){
				jQuery(".search_da_con1").css("display","none");
				jQuery(".search_da_con2").css("display","");
				jQuery.each( json, function(key, value){
					temphtml = jQuery("#"+key).clone();
					jQuery("#"+key).parent().empty().append(temphtml).append(value);
				});
			}else{
				jQuery(".search_da_con2").css("display","none");
				jQuery(".search_da_con1").css("display","");
				shake("search_da_con1");
				jQuery.each( jQuery(".search_da_con2 span"), function(i, node){
					temphtml = jQuery(node).clone();
					jQuery(node).parent().empty().append(temphtml);
				});
				

			}
		},'json');
	}
	</script>
  </head>
  
  <body>
  	<div align="center">
  		<div class="search_da">
	    	<div class="search_da_form">
	        	<p><input type="text" value="���������֤��" class="search_da_sfz" id="search_da_sfz" onclick="clearInputValue(this)"/></p>
	        	<p><input type="text" value="����������" class="search_da_uname" id="search_da_uname" onclick="clearInputValue(this)"/></p>
	        	<p><input type="button" value="" class="search_da_input" onclick="getXsDaxx()"/></p>
	        </div>
	          
	        <!--û�м�¼��ʱ��-->
	        <div class="search_da_con1" style="display: none;width: 200px;">
	        	<img src="./images/search_da_t.gif" />�޼�¼��
	        </div>
	        
	        <!--�м�¼��ʱ��-->
	        <div class="search_da_con2" style="display: none;">
	        	<table width="100%" cellpadding="0" cellspacing="0">
	        		<tr>
	        			<td colspan="2">
	        				<p><span id="xm">������</span></p>
	        			</td>
	        		</tr>
	        		<tr>
	        			<td width="50%">
	        				<p><span id="dazjdw">�������յ�λ��</span></p>
	        			</td>
	        			<td width="50%">
	        				<p><span id="dazcsj">����ת��ʱ�䣺</span></p>
	        			</td>
	        		</tr>
	        		<tr>
	        			<td>
	        				<p><span id="dazjyb">���������ʱࣺ</span></p>
	        			</td>
	        			<td>
	        				<p><span id="dazcfs">����ת����ʽ��</span></p>
	        			</td>
	        		</tr>
	        		<tr>
	        			<td>
	        				<p><span id="dazjdz">�������յ�ַ��</span></p>
	        			</td>
	        			<td>
	        				<p><span id="zcfsbh">ת����ʽ��ţ�</span></p>
	        			</td>
	        		</tr>
	        		<tr>
	        			<td>
	        				<p><span id="dazjdwlxr">����ת�ĵ�λ��ϵ�ˣ�</span></p>
	        			</td>
	        			<td>
	        				<p><span id="dazjdwdh">����ת�ĵ�λ�绰��</span></p>
	        			</td>
	        		</tr>
	        		<tr>
	        			<td colspan="2">
	        				<p><span id="bz">��ע��</span></p>
	        			</td>
	        		</tr>
	        	</table>
	        </div>
	        
	        <div style="width:90%; margin:0 auto; height:5px; line-height:5px; border-bottom:1px dashed #d0d0d0"></div>
	    </div>
  	</div>
  </body>
</html>
