<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<script language="javascript" defer="defer">
//��ʾѡ�����Div
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
	
	tipsWindown("ϵͳ��ʾ","id:div_choose_xh","360","350","true","","true","id");
}


//���ù�������
function setChooseTj(){
	jQuery('#njV').val(jQuery('#nj').val());
	jQuery('#xyV').val(jQuery('#xy').val());
	jQuery('#zyV').val(jQuery('#zy').val());
	jQuery('#bjV').val(jQuery('#bj').val());
	jQuery('#xhV').val(jQuery('#xh').val());
	jQuery('#xmV').val(jQuery('#xm').val());
}

//���ѧ����Ϣ
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

	//��������
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

//��һҳ
function prePage(){
	var xsnum = jQuery('#xsnum').val();
		xsnum = parseInt(xsnum)-1;
	
	jQuery('#xsnum').val(xsnum);
	
	getXsInfo();
}

//��һҳ
function nextPage(){
	var xsnum = jQuery('#xsnum').val();
		xsnum = parseInt(xsnum)+1;
	
	jQuery('#xsnum').val(xsnum);
	
	getXsInfo();
}	

//ѡ��ѧ��
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

<!-- ������ -->
<input type="hidden" id="xsnum" value="1"/>

<!-- ѧ��ѡ�񵯳��� -->
<div id="div_choose_xh" style="display:none">
	<div class="open_win01">
		<table align="center" class="formlist">
			<thead>
				<tr>
					<th colspan="2">
						<span>��������</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td width="50%">
						�꼶
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
						רҵ
					</td>
					<td>
						�༶
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
						ѧ��
					</td>
					<td>
						����
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
								ȷ ��
							</button>
							
							<button type="button" onclick="closeWindown();return false;">
								�� ��
							</button>
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</div>
<!-- ѧ��ѡ�񵯳��� end-->