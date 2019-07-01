<%@ page language="java" pageEncoding="GBK"%>
<div style="vertical-align: top; overflow-y:hidden;" id="div_kxyh">
	<script type="text/javascript">
	function turnKxyhPage(pages){
		
		$("checkAkxyh").checked=false;
		$("checkAyxyh").checked=false;
		checkAllKxyh();
		checkAllYxyh();
		
		var url="splcAjax.do?method=loadKxyhInfo&zdm="+$("zdm").value+"&spgw="+$("spgw").value+"&kxyhPage="+pages;
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
			success:function(kxyh){
				
				reloadKxyhInfo(kxyh);
			}
		});
	}
	
	function getKxyhPages(type){
	
		
		var pages=$("kxyhPages").value;
		var noteLength=$("kxyhNoteLen").value;
		
		if(type=="first"){
			pages="1"
		}else if(type=="ascend"){
			pages=eval(pages)-1;
		}else if(type=="next"){
			pages=eval(pages)+1;
		}else if(type=="last"){
			pages=(noteLength-eval(noteLength)%10)/10+1;
		}
		
		$("kxyhPages").value=pages;
		$("p_kxyhPages").innerHTML=pages;
		turnKxyhPage(pages);
		
	}
	</script>
	<div id="div_kxyh">
		<table id="ul_eligiblyUser" class="bodyPerson" 
			 style="overflow-y:hidden;height:350px">
		
		</table>
	</div>
	
	
<!--	 ���ҳ -->
<!--	<input type="hidden" name="kxyhMaxPage" id="kxyhMaxPage">-->
	<!-- ��Сҳ -->
<!--	<input type="hidden" name="kxyhMinPage" id="kxyhMinPage">-->
	<!-- first -->
<!--	<input type="hidden" name="kxyhFirstPage" id="kxyhFirstPage">-->
	<!-- last -->
<!--	<input type="hidden" name="kxyhLastPage" id="kxyhLastPage">-->
	<!-- ��ǰҳ -->
<!--	<input type="hidden" name="kxyhPages" id="kxyhPages">-->
	<!-- ��¼�� -->
<!--	<input type="hidden" name="kxyhNoteLen" id="kxyhNoteLen">-->
	
		
	<span id="p_kxyhNoteLen" style="display:none"></span> 
</div>

<div class="page_Simple">
	<p class="left_Part">
		<!-- ȫѡ��ť -->
		<input type="checkbox" name="checkAkxyh" id="checkAkxyh" onclick="checkAllKxyh()"/>ȫѡ
		<!-- ҳ����ʾ -->
		<span id="showkxyhPages" style="display:none">Pages:<span id="p_kxyhPages"></span>/<span id="p_kxyhMaxPages"></span></span>
		<input type="hidden" id="initKxyh" />
	</p>
	<p class="right_Part" id="kxyhTurnPage">
<!--		<a href="javascript:getKxyhPages('first')" id='kxyhFirst' class="first"></a>-->
		<a href="javascript:previousKxyhPages()" id='kxyhAscend' class="left"></a>
		<a href="javascript:nextKxyhPages()" id='kxyhNext' class="right"></a>
<!--		<a href="javascript:getKxyhPages('last')" id='kxyhLast' class="last"></a>-->
	</p>
	<!--���ͼƬҪ��ң�����ԭ�������ֺ��_disable����first��Һ��class��Ϊfirst_disable-->
</div>
