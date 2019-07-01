<%@ page language="java" contentType="text/html; charset=GBK"%>
<script type="text/javascript" src="js/echarts/echarts.js"></script>
<script type="text/javascript" src="js/echarts/initData.js"></script>
<input type='hidden' value="${man}" id="man"/>
<input type='hidden' value="${woman}" id="woman"/>
<input type='hidden' value="${fzxman}" id="fzxman"/>
<input type='hidden' value="${fzxwoman}" id="fzxwoman"/>

<script type="text/javascript" >
			var njxsDataList = '${njxsData}';
			var njxsJson=JSON.parse(njxsDataList);
			var njData = [];
			for(var i=0;i<njxsJson.length;i++){
			njData.push(njxsJson[i].name);
			}
			
			var zzmmDataList = '${zzmmData}';
			var zzmmJson=JSON.parse(zzmmDataList);
			var zmData = [];
			for(var i=0;i<zzmmJson.length;i++){
			zmData.push(zzmmJson[i].name);
			}
			
</script>
<div class="col-md-4 col-sm-4 padding_r0">
		<div class="panel panel-default index_list margin_t15">
			<div class="panel-heading">
				<span class="pull-left"><img src="images/statistical_icon01.png">学生人数总览</span>
			</div>
			<div class="panel-body">
			<div class="row">
				<div class="col-lg-12">
					<div id="xsrs" style="height:325px;border:1px solid #ccc;padding:10px;"></div>
				</div>
			</div>
			</div>
		</div>
</div>
	<div class="col-md-4 col-sm-4 padding_r0">
		<div class="panel panel-default index_list margin_t15">
			<div class="panel-heading">
				<span class="pull-left"><img src="images/statistical_icon02.png">各年级学生数</span>
			</div>
			<div class="panel-body">
			<div class="row">
				<div class="col-lg-12">
					<div id="njxs" style="height:325px;border:1px solid #ccc;padding:10px;"></div>
				</div>
			</div>
			</div>
		</div>
	</div>
	<div class="col-md-4 col-sm-4">
		<div class="panel panel-default index_list margin_t15">
			<div class="panel-heading">
				<span class="pull-left"><img src="images/statistical_icon03.png">学生政治面貌分类</span>
			</div>
			<div class="panel-body">
				<div class="row">
				<div class="col-lg-12">
					<div id="zzmm" style="height:325px;border:1px solid #ccc;padding:10px;"></div>
				</div>
			</div>
			</div>
		</div>
	</div>
</div>
