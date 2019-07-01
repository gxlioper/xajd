<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<script language="javascript" defer="defer">
//显示选择过滤Div
function showChooseDiv(){
	jQuery('#xsnum').val("1");
	
	var userStatus = jQuery('#userStatus').val();
	var userDep = jQuery('#userDep').val()
	
	if(userStatus == "xy"){
		jQuery("#xy").val(userDep);
		jQuery("#xy").attr("disabled",true);
	}else{
		jQuery("#xy").val("");
		jQuery("#xy").attr("disabled",false);
	}
	
	tipsWindown("系统提示","id:div_choose_xh","360","350","true","","true","id");
}


//设置过滤条件
function setChooseTj(){
	jQuery('#njV').val(jQuery('#nj').val());
	jQuery('#xyV').val(jQuery('#xy').val());
	jQuery('#zyV').val(jQuery('#zy').val());
	jQuery('#bjV').val(jQuery('#bj').val());
	jQuery('#xhV').val(jQuery('#xh').val());
	jQuery('#xmV').val(jQuery('#xm').val());
}

//获得学生信息
function getXsInfo(){

	var url="customGnmk.do?method=getXsInfo";
	
	var nj = jQuery('#njV').val();
	var xydm = jQuery('#xyV').val();
	var zydm = jQuery('#zyV').val();
	var bjdm = jQuery('#bjV').val();
	var xh = jQuery('#xhV').val();
	var xm = jQuery('#xmV').val();
	var xsnum = jQuery('#xsnum').val();
	var ryfw = jQuery('#ryfw').val();

	//其他数据
 	var parameter = {
		"nj":nj,
		"xydm":xydm,
		"zydm":zydm,
		"bjdm":bjdm,
		"xh":xh,
		"xm":escape(xm),
		"xsnum":xsnum,
		"ryfw":ryfw
	};
  
  	jQuery.ajaxSetup({async:false});
  	
	jQuery("#windown-content").load(url,parameter,function(){
		setOtherXsxx(xh);
	});
	
	jQuery.ajaxSetup({async:true});
}

//上一页
function prePage(){
	var xsnum = jQuery('#xsnum').val();
		xsnum = parseInt(xsnum)-1;
	
	jQuery('#xsnum').val(xsnum);
	
	getXsInfo();
}

//下一页
function nextPage(){
	var xsnum = jQuery('#xsnum').val();
		xsnum = parseInt(xsnum)+1;
	
	jQuery('#xsnum').val(xsnum);
	
	getXsInfo();
}	

//选择学生
function chooseXs(xh){

	var url = "customGnmk.do?method=getXsInfoDetail";
			url+= "&xh="+xh;
		
	jQuery.ajaxSetup({async:false});
		
	jQuery.ajax({
		type:'post',
		url:url,
		dataType:'json',
		async: false,
		success:function(result){
			if(result != null && result.length>0){
			
				var xh = result[0].xh;
				var xm = result[0].xm;
				var xb = result[0].xb;
				var nj = result[0].nj;
				var xymc = result[0].xymc;
				var zymc = result[0].zymc;
				var bjmc = result[0].bjmc;
				
				jQuery("#input_xh").val(xh);
				jQuery("#span_xm").html(xm);
				jQuery("#span_xb").html(xb);
				jQuery("#span_nj").html(nj);
				jQuery("#span_xymc").html(xymc);
				jQuery("#span_zymc").html(zymc);
				jQuery("#span_bjmc").html(bjmc);

				setOtherXsxx(xh);
			}else{
			
			}
		}
	});
	
	closeWindown();
	
	jQuery.ajaxSetup({async:true});
}
</script>

<!-- 隐藏域 -->
<input type="hidden" id="xsnum" value="1"/>

<!-- 学生选择弹出层 -->
<div id="div_choose_xh" style="display:none">
	<div class="open_win01">
		<table align="center" class="formlist">
			<thead>
				<tr>
					<th colspan="2">
						<span>过滤条件</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td width="50%">
						年级
					</td>
					<td width="50%">
						<bean:message key="lable.xsgzyxpzxy" />
					</td>
				</tr>
				<tr>
					<td>
						<html:select property="nj" style="width: 150px" onchange="initZyList();initBjList();" styleId="nj">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj" labelProperty="nj" />
						</html:select>
					</td>
					<td>
						<html:select property="xydm" style="width: 150px" styleId="xy" onchange="initZyList();initBjList();">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm" labelProperty="xymc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						专业
					</td>
					<td>
						班级
					</td>
				</tr>
				<tr>
					<td>
						<html:select property="zydm" style="width: 150px" styleId="zy" onchange="initBjList();">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm" labelProperty="zymc" />
						</html:select>
					</td>
					<td>
						<html:select property="bjdm" style="width:150px" styleId="bj" onchange="">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						学号
					</td>
					<td>
						姓名
					</td>
				</tr>
				<tr>
					<td>
						<html:text property="xh" style="width: 150px" styleId="xh"/>
					</td>
					<td>
						<html:text property="xm" style="width: 150px" styleId="xm"/>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2">
						<div class="bz">
							
						</div>
						<div class="btn">
							<button type="button" onclick="setChooseTj();getXsInfo()">
								确 定
							</button>
							
							<button type="button" onclick="closeWindown();return false;">
								关 闭
							</button>
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</div>
<!-- 学生选择弹出层 end-->