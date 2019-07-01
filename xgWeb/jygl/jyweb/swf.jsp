<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" > 
		var pics=""; 
		var links=""; 
		var texts=""; 
		var imgUrl=new Array(); 
		var imgLink=new Array(); 
		var imgText=new Array(); 
		
		imgUrl[0]=""; 
		imgLink[0]=""; 
		imgText[0]=''; 
		
		dwr.engine.setAsync(false);
		jyweb.getNews("2",function(data){
			for (var i = 0 ; i < data.length && i<5; i++){
				imgUrl[i+1]= "batEditor/WEB-INF/upLoad/"+data[i].tplj;
				imgLink[i+1]="jyweb.do?method=newsInfo&pkValue="+data[i].rowid; 
				imgText[i+1] = data[i].wjbt;
			}
		});
		dwr.engine.setAsync(true);
		
		var focus_width=181; 
		var focus_height=143; 
		var text_height=15; 
		var text_align= "left" 
		var swf_height = focus_height+text_height 
		var j=0;
		for (i=1;i<=imgUrl.length-1;i++) { 
			if( (imgUrl[i]!="") && (imgLink[i]!="") ) { 
				if(j !=0){ 
					pics=pics+"|";
					links=links+"|";
					texts=texts+"|";
				 }
				 pics=pics+imgUrl[i];
				 links=links+imgLink[i];
				 texts=texts+imgText[i]; 
				 j++;
			} 
		} 
		texts = replaceChar(texts,'"','¡°');
		links = escape(links);
		document.write('<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="js/swflash.cab" width="'+ focus_width +'" height="'+ swf_height +'">'); 
		document.write('<param name="allowScriptAccess" value="sameDomain"><param name="movie" value="js/pixviewer.swf"><param name="quality" value="high"><param name="bgcolor" value="#D8D8D8">');
		document.write('<param name="menu" value="false"><param name=wmode value="opaque">');
		document.write('<param name="FlashVars" value="pics='+pics+'&links='+links+'&texts='+texts+'&borderwidth='+focus_width+'&borderheight='+focus_height+'&textheight='+text_height+'&text_align='+text_align+'">');
		document.write('<embed src="js/pixviewer.swf" wmode="opaque" FlashVars="pics='+pics+'&links='+links+'&texts='+texts+'&borderwidth='+focus_width+'&borderheight='+focus_height+'&textheight='+text_height+'" menu="false" bgcolor="#C8DC91" quality="high" width="'+ focus_width +'" height="'+ focus_height +'" allowScriptAccess="sameDomain" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />');
		document.write('</object>');
	</script>
</head>