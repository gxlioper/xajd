<%@ page language="java" contentType="text/html; charset=GBK"%>
<script type='text/javascript' src='/xgxt/dwr/interface/commDAO.js'></script>
<script type="text/javascript">

//增加学生信息字段
function getTableZd(){
	//dwr.engine.setAsync(false);
	setZdList("xsxx");
	setZdList("jtxx");
	setZdList("xmxx");
	//dwr.engine.setAsync(true);
}
//增加学生信息字段
function setZdList(lx){
	dwr.engine.setAsync(false);	
	var id = "";
	var tableName = "";
	var xmdm = $("xmdm").value;
	if(lx == "xsxx"){
		id = "xsxx";
		tableName = "view_xsjbxx";
	}else if(lx == "jtxx"){
		id = "jtxx";
		tableName = "jtqkdcb";
	}else if(lx == "xmxx"){
		id = "xmxx";
		getOtherData.getOneValue("xszz_zzxmb","xmb","xmdm",xmdm,function(table){
			if(table != null){
				tableName = table;
			}
		});
	}
		
	commDAO.getTableZdList(tableName,function(data){
		if(data != null){
			for(var i=0;i<data.length;i++){
				
				if(data[i].mc !=null && data[i].mc != ""){
				var zd= document.createElement("input");
					zd.type="checkbox";
					zd.style.width="";
					zd.id=data[i].dm;
					//zd[j].name="_"+sxmc[j] + index +show_length;
					zd.value= data[i].dm;
					
				var value = data[i].mc;
			
				DWRUtil.addRows(id,['dd'],[zd,value]);	
				}
			}
		}
	});
	dwr.engine.setAsync(true);
}

function closeDiv(){

	dwr.engine.setAsync(false);	
	
	var id = ["xsxx","jtxx","xmxx"];
	
	for(var i=0;i<id.length;i++){
	
		var tabobj = $(id[i]);
    	var length = tabobj.rows.length*2;  
    	
    	for(var j=0;j<=length;j++){
			length--;
			if(tabobj.rows.length){
				tabobj.deleteRow(tabobj.rows.length-1);
			}
		}
	}
	
   dwr.engine.setAsync(true);

	//hiddenExpDiv(true,true);
}
</script>
<div id="expEiv" style="display:none;height:100px" align="center">
	<table class="formlist" border="0" align="center" style="width: 100%;">
		<thead>
			<tr>
				<th colspan="6">
					<span>导出字段列选</span>
				</th>
			</tr>
		</thead>
		<tbody>		
			<tr>
				<td align='right'width="1%" height="400px">
					学<br/>生<br/>基<br/>本<br/>信<br/>息
				</td>
				<td align='left' width="25%" valign="top" height="400px">
					 <div style="width:100%;height:400px;overflow:auto;"> 
						<table align="center" style="width: 80%" id="rsT" class="tbstyle">
							<tbody width="100%" id="xsxx">
			
							</tbody>	
						</table>
					</div>
				</td>
				<td align='right'  width="1%">
					家<br/>庭<br/>情<br/>况<br/>信<br/>息
				</td>
				<td align='left'  width="25%" valign="top">
					<div style="width:100%;height:400px;overflow:auto;"> 
						<table align="center" style="width: 80%" id="rsT" class="tbstyle">
							<tbody width="100%" id="jtxx">
			
							</tbody>	
						</table>
					</div>
				</td>
				<td align='right' width="1%">
					项<br/>目<br/>相<br/>关<br/>信<br/>息
				</td>
				<td align='left' width="" valign="top">
 					<div style="width:100%;height:400px;overflow:auto;"> 
						<table align="center" style="width: 80%" id="rsT" class="tbstyle">
							<tbody width="100%" id="xmxx">
			
							</tbody>	
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="6">
					
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan='6' align="center">
					<button type="button" id="kfbtnSave" onclick=''>
						导出
					</button>
					&nbsp;&nbsp;
					<button type="button" id="kfbtnClose" onclick='closeDiv();'>
						关闭
					</button>
				</td>
			</tr>
		</tfoot>
	</table>
<div>