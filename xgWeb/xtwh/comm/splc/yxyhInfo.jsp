<%@ page language="java" pageEncoding="GBK"%>
<div style="vertical-align: top; overflow-y:hidden;" id="div_yxyh">
	<script type="text/javascript">
	function turnPage(pages){
	
		$("checkAkxyh").checked=false;
		$("checkAyxyh").checked=false;
		checkAllKxyh();
		checkAllYxyh();
		
		var url="splcAjax.do?method=loadYxyhByDel&zdm="+$("zdm").value+"&spgw="+$("spgw").value+"&yxyhPage="+pages;
		var kxyhArr=document.getElementsByName("kxyhArr");
		var kxyhxmArr=document.getElementsByName("kxyhxmArr");
		
		var yxyhArr=document.getElementsByName("yxyhArr");
		var yxyhxmArr=document.getElementsByName("yxyhxmArr");
		
		var n=0;
				
		var strArr="";
		for(i=0;i<kxyhArr.length;i++){
			if(kxyhArr[i].checked){
				
				hidYxyhArr[yxLen]=kxyhArr[i].value;
				yxLen++;
				
			}
		}
		
		
		for(i=0;i<hidYxyhArr.length;i++){
			strArr+=hidYxyhArr[i]+"!!@@!!";
		}
		reloadYhxxInfo(strArr)
		
		var parameter = {
			"strArr":strArr
		};
		jQuery.ajax({
			type:'post',
			url:url,
			data:parameter,
			dataType:'json',
			async: false,
			success:function(yxyh){
				
				reloadYxyhInfo(yxyh);
			}
		});
	}
	
	function getPages(type){
	
		
		var pages=$("yxyhPages").value;
		var noteLength=$("yxyhNoteLen").value;
		
		if(type=="first"){
			pages="1"
		}else if(type=="ascend"){
			pages=eval(pages)-1;
		}else if(type=="next"){
			pages=eval(pages)+1;
		}else if(type=="last"){
			pages=(noteLength-eval(noteLength)%10)/10+1;
		}
		
		$("yxyhPages").value=pages;
		$("p_yxyhPages").innerHTML=pages;
		turnPage(pages);
		
	}
	</script>
	<div id="div_yxyh">
		<table id="table_selectedUser" 
			class="bodyPerson" 
			style="overflow-y:hidden;height:350px">
		</table>
	</div>
	
	<span id="p_yxyhNoteLen" style="display:none"></span>
</div>
<div class="page_Simple">

	<!-- 全选按钮 -->
	<p class="left_Part">
		<input type="checkbox" name="checkAyxyh" id="checkAyxyh" onclick="checkAllYxyh()"/>全选
		<!-- 页数显示 -->
		<span id="showyxyhPages" style="display:none">Pages:<span id="p_yxyhPages"></span>/<span id="p_yxyhMaxPages"></span></span>
		<input type="hidden" id="initYxyh" />
	</p>
	<p class="right_Part" id="yxyhTurnPage">
<!--		<a href="javascript:getPages('first')" id='yxyhFirst' class="first"></a>-->
		<a href="javascript:previousYxyhPages()" id='yxyhAscend' class="left"></a>
		<a href="javascript:nextYxyhPages()" id='yxyhNext' class="right"></a>
<!--		<a href="javascript:getPages('last')" id='yxyhLast' class="last"></a>-->
	</p>
	<!--如果图片要变灰，可在原来的名字后加_disable，如first变灰后的class名为first_disable-->
</div>
