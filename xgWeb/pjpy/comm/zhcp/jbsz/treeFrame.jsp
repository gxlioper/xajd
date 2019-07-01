<%@ page language="java" contentType="text/html; charset=GBK"%>
<script type='text/javascript' src='/xgxt/dwr/interface/getCommGygl.js'></script>

<style type="text/css">
.OrgBox{
	font-size:12px;
	padding:5px 5px 5px 5px;
	clear:left;
	float:left;
	text-align:center;
	position:absolute;
	background:url(/xgxt/pictures/pjpy/zhcp/organization_bg.gif) repeat-x left top;
	width:90px;
	height:106px;
	border:#adc8dc 1px solid;
	border-width:1px 2px 2px 1px;
}
.OrgBox img{
	width:60px;
	height:70px;
}
.OrgBox div{
	padding:5px 0;
	color:#08487e;
	font-weight:bold;
}
.OrgBox div span{
	color:#08487e;
	font-weight:bold;
	}
.OrgBox input{
	background:none;
	border:1px solid #adc8dc;
	padding:0;
	margin:0;
	text-align:center;
}
</style>

<script language="javascript" defer="defer">	
		function jztree(){
			//上级代码数组
			var sjdmArr=document.getElementsByName("sjdmArr");
			//综测级别
			var zcjb=sjdmArr.length-1;
			//综测项目
			var xmdm=document.getElementsByName("xmdm");
			var sjdm=document.getElementsByName("sjdm");
			var xmmc=document.getElementsByName("xmmc");
			
			//综测项目详细信息
			var xmdmArr=document.getElementsByName("xmdmArr");
			var bldmArr=document.getElementsByName("bldmArr");
			var blmcArr=document.getElementsByName("blmcArr");
			var blArr=document.getElementsByName("blArr");
			var xmsjdmArr=document.getElementsByName("xmsjdmArr");
			
			var node=new Array();
			for(i=0;i<xmdm.length;i++){
				node[i]=new OrgNode();
			}
			
			var str=new Array();
			for(i=0;i<xmdm.length;i++){
				var xmm=xmmc[i].value;
				//项目名称修改字段(用于修改综测名)
				var text="<span id='span_xmmc_"+i+"'>"+xmm+"</span><input type='text' style='width:80px' name='zcxmmcArr' id='text_xmmc_"+i+"' value='"+xmm+"' style='display:none' onblur=\"checkXmmc('text_xmmc_"+i+"')\"/>"
				//项目代码字段(用于修改综测名)
				text+="<input type='hidden'  name='zcxmdmArr' value='"+xmdm[i].value+"' >";
				node[i].customParam.EmpName=text;
				str[i]="";
				for(j=0;j<xmdmArr.length;j++){
					if(xmdm[i].value==xmdmArr[j].value
						&& (xmsjdmArr[j].value==null || xmsjdmArr[j].value=="")){
						var bl=blArr[j].value;
						
						var xmdmbc="<input type='hidden'  name='zcdmblArr'  value='"+xmdmArr[j].value+"' >";
						xmdmbc+="<input type='hidden'  name='zcbldmArr'  value='"+bldmArr[j].value+"' >"
						xmdmbc+="<input type='hidden'  name='zcsjdmArr' id='hid_sjdm_"+j+"' value='"+xmsjdmArr[j].value+"' >"
						str[i]+="<div><span id='span_bl_"+j+"'></span><input type='hidden' style='width:30px' name='zcblArr' id='text_bl_"+j+"' value='' style='display:none'></div>";
						str[i]+=xmdmbc;
					}else if(xmdm[i].value==xmdmArr[j].value){
						var bl=blArr[j].value;
						if(bl==""){
							bl="未设置";
						}
						var xmdmbc="<input type='hidden'  name='zcdmblArr'  value='"+xmdmArr[j].value+"' >";
						xmdmbc+="<input type='hidden'  name='zcbldmArr'  value='"+bldmArr[j].value+"' >"
						xmdmbc+="<input type='hidden'  name='zcsjdmArr' id='hid_sjdm_"+j+"'  value='"+xmsjdmArr[j].value+"' >"
						str[i]+="<div>"+blmcArr[j].value+"：<span id='span_bl_"+j+"'>"+bl+"</span><input type='text' style='width:30px;display:none' name='zcblArr' id='text_bl_"+j+"' value='"+blArr[j].value+"'  onkeydown=\"return onlyNum(this,3);\" onmousedown=\"return onlyNum(this,3);\">%</div>";
						str[i]+=xmdmbc;
					}
				}
				node[i].customParam.inputV=str[i];
			}
			
			
			for(j=0;j<xmdm.length;j++){
				for(i=0;i<sjdmArr.length;i++){
					if(xmdm[j].value==sjdmArr[i].value){
						
						for(z=0;z<xmdm.length;z++){
							if(sjdm[z].value==xmdm[j].value){
								node[j].Nodes.Add(node[z]);						
								
							}
						}
					}
				}
			}
			
			var OrgShows=null;
			for(i=0;i<sjdm.length;i++){
				
				if(sjdm[i].value==null || sjdm[i].value==""){
					
					OrgShows=new OrgShow(node[i]);
					break;
				}
			}
			
			OrgShows.Top=135;
			OrgShows.Left=85;
			OrgShows.IntervalWidth=10;
			OrgShows.IntervalHeight=20;
		
			OrgShows.ShowType=2;
			OrgShows.BoxHeight=100;
			OrgShows.BoxTemplet="<div id=\"{Id}\" class=\"OrgBox\"><span>{EmpName}</span><br/><br/><br/>{inputV}</div>"
			OrgShows.Run();
			
		}
		
		setTimeout('jztree()',100);
		
		function checkXmmc(id){
			
			var obj=$(id);
			//综测项目名称数组
			var xmmcArr=document.getElementsByName("zcxmmcArr");
			for(i=0;i<xmmcArr.length;i++){
				if(xmmcArr[i].value==obj.value && xmmcArr[i].id!=obj.id){
					alert("综测项目名称不能相等!");
					obj.focus();
					return false;
				}
			}
		}
		</script>
