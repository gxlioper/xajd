<%@ page language="java" contentType="text/html; charset=GBK"%>
<script type="text/javascript">
jQuery(function(){
	var xmmc = encodeURI('${xmxx.xmmc}');
	var xh = '${stuJbxx.xh }';
	
	jQuery.post('pjpy_ty_ajax.do?method=getOtherInfo',{xmmc:xmmc,xh:xh},function(data){
		if (data.length == 0){
			jQuery('#mk_qtxx').attr('style','display:none');
			jQuery('#qtxx').attr('style','display:none');
		}
	
		var tdArr = [];
		var tdLongArr = [];
	
		//先把th td 加好放在数组里
		for (var i = 0 ; i < data.length ; i++){
			var html = "";
			var zdz = data[i].zdz==null ? '' : data[i].zdz;
			
			if ('long'==data[i].xxlx){
				html+= "<th>"+data[i].zdmc+"</th>";
				html+= "<td colspan='3' >"+zdz+"</td>";
				tdLongArr[tdLongArr.length] = html;
			} else if ('textarea'==data[i].xxlx){
				html+= "<th width='15%'>"+data[i].zdmc;
				html+= "<br/><font color='red'>&lt;限400字&gt;</font>"+"</th>";
				html+= "<td colspan='3' style='word-break:break-all;'><textarea rows='4' onblur='chLeng(this,400)' style='width:80%' name='"+data[i].zdm+"'>"+zdz+"</textarea></td>";
				tdLongArr[tdLongArr.length] = html;
			} else if ('text' == data[i].xxlx){
				html+= "<th width='15%'>"+data[i].zdmc+"</th>";
				html+= "<td width='25%'><input type='text' maxlength='10' style='width:80%' name='"+data[i].zdm+"' value='"+zdz+"'></td>";
				tdArr[tdArr.length] = html;
			} else {
				html+= "<th>"+data[i].zdmc+"</th>";
				html+= "<td>"+zdz+"</td>";
				tdArr[tdArr.length] = html;
			}
		}
		
		//把short字段放在行里再加到tbody
		if (tdArr.length > 0){
			var trHtml = "";
			for (var i = 0 ; i <tdArr.length ; i++){
				if (i%2 == 0 && i != 0){
					trHtml +="</tr>";
				}
				if (i == 0)	{
					trHtml +="<tr>";
				}
				trHtml+=tdArr[i];	
			}
		
			if (tdArr.length%2 != 0){
				trHtml += "<th></th><td></td>";
			}
			trHtml += "</tr>";
			jQuery('#mk_qtxx').append(trHtml);
		}
		
		//把long textarea字段放在行里再加到tbody
		if (tdLongArr.length > 0){
			var trHtml = "";
			for (var i = 0 ; i <tdLongArr.length ; i++){
				
				trHtml += "<tr>";
				trHtml += tdLongArr[i];
				trHtml += "</tr>";
			}
		
			jQuery('#mk_qtxx').append(trHtml);
		}
		
	},'json');
})
</script>

<thead onclick="hiddenMk('mk_qtxx')" id="qtxx">
	<tr><th colspan="4" style="cursor:hand"><span>其它信息</span></th></tr>
</thead>
<tbody id="mk_qtxx">
</tbody>
