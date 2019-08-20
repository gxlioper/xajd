<%@ page language="java" contentType="text/html; charset=GBK"%>
<script type="text/javascript" defer="defer">
	jQuery(function(){
		var url = "hdgl_hdxx.do?method=zxHdxxList";
		
		jQuery.post(url,{},function(data){
			for (var i = 0 ; i < data.length && i < 6; i++){
				if (!jQuery.isEmptyObject(data[i])){
					var liHtml = "<li style='width: 20%;' title='" + data[i]["hdmc"] + "'><div style='width: 100%;'><a href='hdgl_hdxx.do?method=ckHdxx&hdid=" + data[i]["hdid"] + "'>";
					liHtml += "<img src='" + data[i]["hb"] + "' style='width:97px;height:127px;'/>";
					liHtml += "<p class='text-center'>" + data[i]["hdmc"] + "【" + data[i]["hdztmc"] + "】</p>";
					liHtml += "</a></div></li>";
					
					jQuery("#zxhdxxList").append(liHtml);
				}
			}
		},"json");
	});
</script>
<div class="col-md-6 col-sm-6" id="div_wdgz">
	<div class="panel panel-default index_list margin_t15 to-do-item">
		<div class="panel-heading">
			<span class="pull-left"><img src="images/to_do_icon.png">最新活动</span>
			<a href="hdgl_hdgl_hdxx.do" class="pull-right" title="更多">more>></a>
		</div>
		<div class="panel-body">
			<ul class="info-search" id="zxhdxxList" style="height: 265px; overflow: hidden;">
			</ul>
		</div>
	</div>
</div>