<%@ page language="java" contentType="text/html; charset=GBK"%>
<script type="text/javascript">

	jQuery(function(){
		var url = "xtwh_wdgz.do?method=queryWdgz";
		
		jQuery.post(url,{},function(data){
			for (var i = 0 ; i < data.length && i < 6; i++){
				if (!jQuery.isEmptyObject(data[i])){
					var gnmkpathV = data[i]["gnmkpath"];
					var liHtml = "<a class='list-group-item' href='";
						liHtml += gnmkpathV;
						liHtml +="'>��";
						liHtml +=data[i]["gznr"];
						liHtml +="���У���<font color='red'> "
						liHtml += data[i]["dbs"];
						if(gnmkpathV == "szdw_thjl_zdgzxsk.do"){
							liHtml +=" </font>&nbsp;����Ҫ����ע��";
						}else{
							liHtml +=" </font>&nbsp;����Ҫ����ˡ�";
						}
						
						liHtml +="</a>";
					
					jQuery("#wdgzList").append(liHtml);
				}
			}
		},"json");
	});
	
</script>
<div class="col-md-6 col-sm-6" id="div_wdgz">
	<div class="panel panel-default index_list margin_t15 to-do-item">
		<div class="panel-heading">
			<span class="pull-left"><img src="images/to_do_icon.png">��������</span>
			<a href="xtwh_wdgz.do?method=getMoreJobs" class="pull-right" title="����">more>></a>
		</div>
		<div class="panel-body">
			<ul class="list-group" id="wdgzList" style="height: 235px; overflow: hidden;">
			</ul>
		</div>
	</div>
</div>
