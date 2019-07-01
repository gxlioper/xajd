<%@ page language="java" contentType="text/html; charset=GBK"%>
<script type="text/javascript" defer="defer">
	jQuery(function(){
		var url = "xtwh_wdgz.do?method=queryWdsq";
		
		jQuery.post(url,{},function(data){
			for (var i = 0 ; i < data.length && i < 6; i++){
				if (!jQuery.isEmptyObject(data[i])){
					var liHtml = "<a class='list-group-item' href='";;
						liHtml += data[i]["gnmkpath"];
						liHtml += "' >【";
						liHtml +=data[i]["gznr"];
						liHtml +="】";
						liHtml +=data[i]["shztmc"];
						liHtml +="。</a></li>";
					
					jQuery("#wdsqList").append(liHtml);
				}
			}
		},"json");
	});
</script>
<div class="col-md-6 col-sm-6" id="div_wdgz">
	<div class="panel panel-default index_list margin_t15 to-do-item">
		<div class="panel-heading">
			<span class="pull-left"><img src="images/to_do_icon.png">办结申请</span>
			<a href="xtwh_wdgz.do?method=getMoreJobs" class="pull-right" title="更多">more>></a>
		</div>
		<div class="panel-body">
			<ul class="list-group" id="wdsqList" style="height: 235px; overflow: hidden;">
			</ul>
		</div>
	</div>
</div>