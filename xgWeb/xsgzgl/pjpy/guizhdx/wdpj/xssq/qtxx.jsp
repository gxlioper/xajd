<%@ page language="java" contentType="text/html; charset=GBK"%>
<script type="text/javascript">
jQuery(function(){
	var xmmc = encodeURI('${wdpjXssqInfo.xmInfo.xmmc}');
	var xh = '${wdpjXssqInfo.stuInfo.xh }';
	
	jQuery.post('pjpy_ty_ajax.do?method=getOtherInfo',{xmmc:xmmc,xh:xh},function(data){
	
		if (data.length == 0){
			jQuery('#mk_qtxx').attr('style','display:none');
			jQuery('#qtxx').attr('style','display:none');
		}
		
		var tdArr = [];
		var tdLongArr = [];
	
		//�Ȱ�th td �Ӻ÷���������
		for (var i = 0 ; i < data.length ; i++){
			var html = "";
			var zdz = data[i].zdz==null ? '' : data[i].zdz;
			
			if ('long'==data[i].xxlx){
				html+= "<th>"+data[i].zdmc+"</th>";
				html+= "<td colspan='3' >"+zdz+"</td>";
				tdLongArr[tdLongArr.length] = html;
			} else if ('textarea'==data[i].xxlx){
				html+= "<th>"+data[i].zdmc;
				html+= "<br/><font color='red'>&lt;��400��&gt;</font>"+"</th>";
				html+= "<td colspan='3' style='word-break:break-all;'><textArea rows='4' onblur='chLeng(this,400)' style='width:95%' name='"+data[i].zdm+"' ";
				
				if($("doType") && $("doType").value=="view"){
					html+=(" readOnly='true'");	
				}
				html+=" >"+zdz+"</textArea></td>"
				tdLongArr[tdLongArr.length] = html;
			} else {
				html+= "<th>"+data[i].zdmc+"</th>";
				html+= "<td>"+zdz+"</td>";
				tdArr[tdArr.length] = html;
			}
		}
		
		//��short�ֶη��������ټӵ�tbody
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
		
		//��long textarea�ֶη��������ټӵ�tbody
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
	<tr><th colspan="4" style="cursor:hand"><span>������Ϣ</span></th></tr>
</thead>
<tbody id="mk_qtxx">
</tbody>
