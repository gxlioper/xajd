<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
<script language="javascript" src="js/xszz/xszzComm.js"></script>
<script language="javascript" src="js/xszz/xszzFunction.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
<script type='text/javascript' src='/xgxt/dwr/interface/getXszzInfo.js'></script>
<script language="javascript">
function xmplsh(shzt){
		
	//��ÿ���
	var jdkz = true;
	var xmdm = jQuery('#xmdm').val();

	var shpk="";
	var checkBoxArr = window.dialogArguments.document.getElementsByName("checkVal");

	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){	
			shpk +=	checkBoxArr[i].value+"!!@@!!";	
		}
	}

	var pkValue = shpk.split('!!@@!!');
	
	jQuery('#shpk').val(shpk);


	
	jQuery.ajaxSetup({async:false});
	if(shzt == "tg"){
		for (var i = 0 ; i < pkValue.length; i++){
			var xh = pkValue[i].substr(0,pkValue[i].length-xmdm.length-8);
			if (xh != ''){
				jQuery.post('xszzAjax.do?method=checkBkjdxm',{xh:xh,xmdm:xmdm},function(data){
					var bkjdxm = data.xmdm;
					
					if (bkjdxm != '' && bkjdxm != null){
						alert('�Ѵ��ڲ��ɼ����Ŀ:{ѧ�ţ�'+xh+',��Ŀ����:'+data.xmdm+',��Ŀ����:'+data.xmmc+'}');
						jdkz = false;
					}
				},'json');
			}
			if (!jdkz){
				break;
			}
		}
	}
	
	if (jdkz && confirm("ȷ�����״̬?")) {
		var url = "/xgxt/commXszz.do?method=xmshUpdate&doType=plsave";
			url+= "&shzt="+shzt;
			
		if(shzt == "tg"){
			shzt = "ͨ��";
		}else{
			shzt = "��ͨ��";
		}
		
		var lx = $("lx").value;
		var shjb = $("shjb").value;
		
		if(shjb == "һ�����"){
			$("shzt1").value = shzt;
		}else if(shjb == "�������"){
		
			var jbdm1 = $("jbdm1").value;
			var jbdm2 = $("jbdm2").value;
			var jb = $("jb").value;

			if(jb == "Lv1"){
				if(jbdm1 != ""){
					var arrJb = jbdm1.split("-");
					for(var i=0;i<arrJb.length;i++){
						var yhsh = lx+"sh";
						if(arrJb[i] == yhsh){
							$("shzt1").value = shzt;
						}else if(lx == "jd" && (arrJb[i] == "fdysh" || arrJb[i] == "bzrsh")){
							$("shzt1").value = shzt;
						}			
					}
				}
			}else{
				if(jbdm2 != ""){
					var arrJb = jbdm2.split("-");
					for(var i=0;i<arrJb.length;i++){
						var yhsh = lx+"sh";
						if(arrJb[i] == yhsh){
							$("shzt2").value = shzt;
						}else if(lx == "jd" && (arrJb[i] == "fdysh" || arrJb[i] == "bzrsh")){
							$("shzt2").value = shzt;
						}			
					}
				}
			}
		}else if(shjb == "�������"){
		
			var jbdm1 = $("jbdm1").value;
			var jbdm2 = $("jbdm2").value;
			var jbdm3 = $("jbdm3").value;
			var jb = $("jb").value;

			if(jb == "Lv1"){
				if(jbdm1 != ""){
					var arrJb = jbdm1.split("-");
					for(var i=0;i<arrJb.length;i++){
						var yhsh = lx+"sh";
						if(arrJb[i] == yhsh){
							$("shzt1").value = shzt;
						}else if(lx == "jd" && (arrJb[i] == "fdysh" || arrJb[i] == "bzrsh")){
							$("shzt1").value = shzt;
						}			
					}
				}
			}else if(jb == "Lv2"){
				if(jbdm2 != ""){
					var arrJb = jbdm2.split("-");
					for(var i=0;i<arrJb.length;i++){
						var yhsh = lx+"sh";
						if(arrJb[i] == yhsh){
							$("shzt2").value = shzt;
						}else if(lx == "jd" && (arrJb[i] == "fdysh" || arrJb[i] == "bzrsh")){
							$("shzt2").value = shzt;
						}			
					}
				}
			}else {
				if(jbdm3 != ""){
					var arrJb = jbdm3.split("-");
					for(var i=0;i<arrJb.length;i++){
						var yhsh = lx+"sh";
						if(arrJb[i] == yhsh){
							$("shzt3").value = shzt;
						}else if(lx == "jd" && (arrJb[i] == "fdysh" || arrJb[i] == "bzrsh")){
							$("shzt3").value = shzt;
						}			
					}
				}
			}
		}
		
		if(lx == "bzr"){
			$("bzrsh").disabled = false;
			$("bzrsh").value = shzt;
		}else if(lx == "fdy"){
			$("fdysh").disabled = false;
			$("fdysh").value = shzt;
		}else if(lx == "jd"){
			$("bzrsh").disabled = false;
			$("fdysh").disabled = false;
			$("bzrsh").value = shzt;
			$("fdysh").value = shzt;
		}else if(lx == "xy"){
			$("xysh").disabled = false;
			$("xysh").value = shzt;
		}else if(lx == "xx"){
			$("xxsh").disabled = false;
			$("xxsh").value = shzt;
		}
		saveUpdate(url,"xmzzjb-xmzzje-fj");
	}
	jQuery.ajaxSetup({async:true});
}



</script>
</head>
	<body onload="">
		<html:form action="/commXszz">
			<!-- ������ -->
			<%@ include file="/xszz/hiddenValue.jsp"%>
			<input type="hidden" name="save_xmdm" id="xmdm" value="${xmInfo.xmdm }"/>
			<input type="hidden" name="shpk" id="shpk" value="${shpk }"/>
			<input type="hidden" name="xmb" id="xmb" value="${xmInfo.xmb }"/>
			<!-- ������ end-->
			<!-- ��Ŀ�ּ���� -->
			<logic:notEmpty name="xmfjqkList">
				<table class="formlist" border="0" align="center" style="width: 100%">
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ŀ�������</span>				
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:iterate id="xmjb" name="xmfjqkList">
							<tr style="height: 23px">
								<th align="right" width="15%">
									<logic:equal name="xmjb" property="fjmc" value="��">
										���
									</logic:equal>
									<logic:notEqual name="xmjb" property="fjmc" value="��">
										${xmjb.fjmc }
									</logic:notEqual>
								</th>
								<td align="left" colspan="3">
									<logic:notEqual name="xmjb" property="fjqdje" value="��">
										${xmjb.fjqdje }
									</logic:notEqual>
									<logic:equal name="xmjb" property="fjqdje" value="��">
										<logic:equal name="xmjb" property="fjxxje" value="��">
											<logic:equal name="xmjb" property="fjsxje" value="��">
												���漰���
											</logic:equal>
										</logic:equal>
									</logic:equal>
									<logic:notEqual name="xmjb" property="fjxxje" value="��">
										<logic:equal name="xmjb" property="fjqdje" value="��">
											<logic:notEmpty name="xmjb" property="fjxxje">
												${xmjb.fjxxje }
											</logic:notEmpty>
											<logic:empty name="xmjb" property="fjxxje">
												������
											</logic:empty>
											--
											<logic:notEmpty name="xmjb" property="fjsxje">
												${xmjb.fjsxje }
											</logic:notEmpty>
											<logic:empty name="xmjb" property="fjsxje">
												������
											</logic:empty>
										</logic:equal>
									</logic:notEqual>
								</td>			
							</tr>
						</logic:iterate>
					</tbody>
				</table>
			</logic:notEmpty>
			<!-- ��Ŀ�ּ���� end-->
			<!-- ��Ŀ������ -->
			<logic:notEmpty name="xmnrList">
				<logic:iterate name="xmnrList" id="xmnr">
					<logic:equal name="xmnr" property="mk" value="shInfo">
						<%@ include file="sqsh/plshInfo.jsp"%>
					</logic:equal>
				</logic:iterate>
			</logic:notEmpty>
			<!-- ��Ŀ������ end-->
			<table border="0" class="formlist" align="center" style="width: 100%">
				<tfoot>
					<tr>
						<td align="center">
							<div class="btn">
								<button type="button" id="buttonSave" onclick="xmplsh('tg')" style="width: 80px">
									ͨ��
								</button>
								&nbsp;&nbsp;
								<button type="button" id="buttonSave" onclick="xmplsh('btg')" style="width: 80px">
									��ͨ��
								</button>
								&nbsp;&nbsp;
								<button type="button" id="buttonSave" onclick="window.close();return false;" style="width: 80px">
									��	��
								</button> 
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="other/tsxx.jsp"%>
		</html:form>
	</body>
</html>