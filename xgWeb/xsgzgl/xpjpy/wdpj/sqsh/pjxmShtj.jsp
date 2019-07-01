<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript">
		
			jQuery(function(){
				jQuery("li").first().find("a").click();
				setComTitle();
			});
			

			function setComTitle(){
				var ulHeight=jQuery("#comp_title ul").height();
				var len=Math.ceil(ulHeight/26);
				var i=0;
				jQuery(".btn_up").attr('class','btn_up_disable');
				jQuery(".btn_up_down .btn_down").live('click',function(){
					if(!jQuery(this).parents("#comp_title").children("ul").is(":animated")){
					if(i<len-1){
					i++;
					jQuery(this).siblings("span").attr("class","btn_up");
					jQuery(this).parents("#comp_title").children("ul").animate({marginTop:(-26*i)+"px"},200);
					}
					if(i==len-1){
						jQuery(this).attr('class','btn_down_disable');
					}
					}
				});
				jQuery(".btn_up_down .btn_up").live('click',function(){
					if(!jQuery(this).parents("#comp_title").children("ul").is(":animated")){
					if(i>0){
					i--;
					jQuery(this).siblings("span").attr("class","btn_down");
					jQuery(this).parents("#comp_title").children("ul").animate({marginTop:(-26*i)+"px"},200);
					}
					if(i==0){
						jQuery(this).attr('class','btn_up_disable');
					}
					}
				})
			}
		    			
			function showPjpyShqk(obj,xmdm){
				jQuery(".ha").removeClass("ha");
				jQuery(obj).parent().addClass("ha");
				jQuery("#comp_con").load("xpj_sqsh.do?method=viewShqk&xmdm="+xmdm);
			}
			
		</script>
	</head>
	<body>
		<html:form action="/xpj_sqsh">
			
			<div class="comp_title" id="comp_title">
		      <ul style="width: 80%; margin-top: 0px;">
				<logic:present name="pjxmList">
					<logic:iterate id="pjxm" name="pjxmList">
						<li>
							<a href="javascript:void(0);"  onclick="showPjpyShqk(this,'${pjxm.xmdm}');">
								<span>${pjxm.xmmc}</span>
							</a>
						</li>
					</logic:iterate>
				</logic:present>
				
		      </ul>
		      <div class="btn_up_down">
		      	<span class="btn_up_disable"></span>
		      	<span class="btn_down"></span>
		      </div>
		    </div>
			<div class="comp_con" id="comp_con">
			
			</div>
		</html:form>
	</body>
</html>
