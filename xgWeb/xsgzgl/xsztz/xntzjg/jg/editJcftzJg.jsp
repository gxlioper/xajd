<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/xntzjg/jg/js/jcftzjg.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var xmdm = jQuery("#xmdm").val();
			var xxdm = jQuery("#xxdm").val();
			var jxids = [];
			var jxmcs = [];
			var fjfss = [];
			jQuery.post("jxgl_xnjxsq.do?method=getjxxx", {
				xmdm:xmdm
			}, function(data) {
				var jxhtml = "";      		
	            	for(var i=0;i<data.length;i++){           		
	            		jxhtml+="<tr><input type='hidden' name='jxId' value='"+data[i].jgid+"'/><td>"+data[i].jxmc+"</td><td>"+data[i].fjxf+"</td><td>"+data[i].xssx+"</td><tr>";
	            		jxids.push(data[i].jgid);
						jxmcs.push(data[i].jxmc);
						fjfss.push(data[i].fjxf);
	            	}
	            	jQuery("#tbody_xmjxxx").html(jxhtml); 
				}, 'json');
			jQuery.post("jcftz_jg.do?method=getXsListForUpdateForAjax", {
				xmdm:xmdm
			}, function(data) {
				var rdhtml = "";				
	            	for(var i=0;i<data.length;i++){           		
	            		rdhtml+="<tr><td><input type='checkbox'/><input type='hidden' name='jgidss' id='jgidss' value='"+data[i].jgid+"' /></td><td>"+data[i].xh+"<input name= 'xh' type='hidden' value='"+data[i].xh+"'/></td><td>"+data[i].xm+"</td><td>"+data[i].bjmc+"</td><td>";
	            		rdhtml+= "<input onblur=\"savefs(this,'";
	            		rdhtml+=data[i].jgid;
	            		rdhtml+="','";
	            		rdhtml+=data[i].jcxf;
	            		rdhtml+="')\" type='text' name='tzhjcf' onkeyup='checkNumberinput(this);'";
	            		rdhtml+=" style='width:100%;' maxlength='4' value='";
	            		rdhtml+=data[i].tzhjcf==null ? data[i].jcxf : data[i].tzhjcf;
	            		rdhtml+="' min='0' max='";
	            		rdhtml+=data[i].jcxf;
	            		rdhtml+="' /><td>";
	            		rdhtml+= "<select name='jxdm' onblur=\"saveJx(this,'";
	            		rdhtml+=data[i].jgid;
	            		rdhtml+="')\"";
	            		rdhtml+=" style='width:100%;' value='";
	            		rdhtml+=data[i].jxdm==null ? "" : data[i].jxdm;
	            		rdhtml+="'" 
	            		rdhtml+=" >";
	            		rdhtml+="<option value=''></option>"
						for(var j = 0;j<jxids.length;j++){
							rdhtml+="<option value ='";
							rdhtml+=jxids[j];
							rdhtml+="' ";
							if(data[i].jxdm==jxids[j]){
								rdhtml+="selected='selected'"
							}
							rdhtml+=">";
							rdhtml+=jxmcs[j];
							rdhtml+="</option>";
						}
						rdhtml+="</select></td><td>";
						rdhtml+= "<select name='sfqq' onblur=\"saveQq(this,'";
						rdhtml+=data[i].jgid;
						rdhtml+="')\"";
						rdhtml+=" style='width:40px;' value='";
						rdhtml+=data[i].sfqq==null ? "0" : data[i].sfqq;
						rdhtml+="'" 
						rdhtml+=" >";
						if(data[i].sfqq == '1'){
							rdhtml+="<option value ='0'>��</option>";
							rdhtml+="<option value ='1' selected='selected'>��</option>";
						}else{
							rdhtml+="<option value ='0' selected='selected'>��</option>";
							rdhtml+="<option value ='1'>��</option>";
						}			
	            		rdhtml+="</td><td>"+data[i].zf+"</td>";      
	            		
	            		
	            		//��ע1-5
	            		if("13627"==xxdm){
	            		rdhtml+= "<td><input onblur=\"savebz(this,'"+data[i].jgid+"','bz1')\" type='text' name='bz1' ";
	            		rdhtml+=" style='width:50px;' maxlength='20' value='";
	            		rdhtml+=data[i].bz1==null?"":data[i].bz1;
	            		rdhtml+="' /></td>";
	            		rdhtml+= "<td><input onblur=\"savebz(this,'"+data[i].jgid+"','bz2')\" type='text' name='bz2' ";
	            		rdhtml+=" style='width:50px;' maxlength='20' value='";
	            		rdhtml+=data[i].bz2==null?"":data[i].bz2;
	            		rdhtml+="' /></td>";
	            		rdhtml+= "<td><input onblur=\"savebz(this,'"+data[i].jgid+"','bz3')\" type='text' name='bz3' ";
	            		rdhtml+=" style='width:50px;' maxlength='20' value='";
	            		rdhtml+=data[i].bz3==null?"":data[i].bz3;
	            		rdhtml+="' /></td>";
	            		rdhtml+= "<td><input onblur=\"savebz(this,'"+data[i].jgid+"','bz4')\" type='text' name='bz4' ";
	            		rdhtml+=" style='width:50px;' maxlength='20' value='";
	            		rdhtml+=data[i].bz4==null?"":data[i].bz4;
	            		rdhtml+="' /></td>";
	            		rdhtml+= "<td><input onblur=\"savebz(this,'"+data[i].jgid+"','bz5')\" type='text' name='bz5' ";
	            		rdhtml+=" style='width:50px;' maxlength='20' value='";
	            		rdhtml+=data[i].bz5==null?"":data[i].bz5;
	            		rdhtml+="' /></td>";
	            		}
	            		rdhtml+="</tr>";      
	            	}
	            	jQuery("#tbody_dhryxx").html(rdhtml);
				}, 'json');	
		});
		</script>
		<style type = "text/css">
			#xxdz{width:250px;}
			.fontstyl{float:left}
			.fontstyl1{margin-left:5px}
		</style>
	</head>
	<body>
		<html:form action="/jcftz_jg" method="post" styleId="jcftzjgForm">
		<input type="hidden" id="xxdm" name="xxdm" value="${xxdm }"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ŀ��Ϣ</span>
								<span> 
										<a onclick="showxmxx(this);" class="up"
											href="javascript:void(0);"> <font color="blue">��ϸ��Ϣ</font>
										</a>									
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width: 20%"><font color="red">*</font>��Ŀ����</th>
							<td style="width: 30%">
							<input type="hidden" name="jgids" id="jgids"/>
							<input type="hidden" name="xhs" id="xhs"/>
							<input type="hidden" name="jxdms" id="jxdms"/>
							<input type="hidden" name="sfqqs" id="sfqqs"/>
							<input type="hidden" name="tzhjcfs" id="tzhjcfs"/>
								<input type="hidden" name="jgid" id="jgid"/>
								<html:hidden property="xmdm" styleId="xmdm"/>
								${rs.xmmc}
							</td>
							<th style="width: 20%">��Ŀ����</th>
							<td id="xmjbmc" style="width: 30%">
							  ${rs.xmjbmc}
                             </td>
						</tr>
						<tr>
							<th>�걨����</th>
							<td id="sbbmmc" >
								${rs.sbbmmc} 
							</td>
							<th>������Ŀ</th>
							<td id="sskmmc" name="sskmmc">
								${rs.sskmmc}
							</td>
						</tr>
						<tr>
							<th>�걨��</th>
							<td id="sbr" name="sbr">
								${rs.sbrxm}
							</td>
							<th>�걨����ϵ��ʽ</th>
							<td id="lxdh" name="lxdh">
								${rs.lxdh}
							</td>						
						</tr>
						<tbody id="t_xmxx" style="display: none;">
						<tr>
							<th>�ɲ�������</th>
							<td id="kcyrs" name="kcyrs">
								${rs.kcyrs}
							</td>
							<th>��Ŀ��ʼʱ��</th>
							<td id="xmkssj" name="xmkssj">
								${rs.xmkssj}
							</td>
						</tr>
						<tr>
							<th>
								�Ƿ���������
							</th>
							<td id="sfsljxmc">
								${rs.sfsljxmc}
							</td>
							<th>
								����ѧ��
							</th>
							<td id="jcxf">
								${rs.jcxf}
							</td>
						</tr>
						<tr>
							<th>ѧ��</th>
							<td id="xn" >
								${rs.xn}
							</td>
							<th>ѧ��</th>
							<td id="xq" >
								${rs.xqmc}
							</td>
						</tr>
						<tr>
							<th>��Ŀ����</th>
							<td id="xmlx">
								<logic:equal value="1" name="rs" property="csms">
									����
								</logic:equal>
								<logic:equal value="2" name="rs" property="csms">
									����
								</logic:equal>
							</td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th width="20%">
								��Ŀ������Ϣ
							</th>
							<td width="30%"  colspan="3">
								
								 <div style="overflow-y:auto;" id="jxDiv">
								 <table width="100%" border="0" class="formlist">
									<thead>
										<tr>
											<td width='15%'>��������</td>
											<td width='15%'>����ѧ��</td>
											<td width='15%'>����˳��</td>
										</tr>
									</thead>
									<tbody id="tbody_xmjxxx">
									
								</tbody>
								</table>
								</div>								
							</td>
						</tr>
						</tbody>
						<thead>
							<tr>
								<th colspan="4">
									<span>ѧ���϶�</span>
								</th>
							</tr>
					   </thead>
					</tbody>
				</table>
				</div>
				<div style="overflow-y:auto;height:250px">
					<tr>						
						<table width="100%" border="0" class="formlist">
								<tr>
									<td width='5%'><input type="checkbox" name="th" onclick="selectAll(this);" /></td>
									<td width='10%'>ѧ��</td>
									<td width='7%'>����</td>
									<td width='10%'>�༶</td>
									<td width='7%'>������</br>������<font color='red'>*</font></td>
									<td width='10%'>��ý���</td>
									<td width='5%'>�Ƿ�</br>ȱ��</td>
									<td width='6%'>���</br>��ѧ��</td>
									<logic:equal name="xxdm" value="13627">
									<td width='8%'>��ע1</td>
									<td width='8%'>��ע2</td>
									<td width='8%'>��ע3</td>
									<td width='8%'>��ע4</td>
									<td width='8%'>��ע5</td>
									</logic:equal>
								</tr>
								<tbody id="tbody_dhryxx">
									
								</tbody>									
						</table>
				</div>
				<div style="height:30px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" id="bc" onclick="saveSqjg('update');">
										����
									</button>
									<button type="button" onclick="iFClose();">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
	
</html>