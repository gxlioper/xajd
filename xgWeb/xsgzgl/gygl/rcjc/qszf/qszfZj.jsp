<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/xszz/whtl/ybbx/ybbxDetail.js"></script>
		
		<script	type="text/javascript">
			function checkIs(obj){
				var zflsgh = jQuery("#zflsgh").val();
				if(zflsgh !="" && zflsgh !=null){
					jQuery.getJSON('gyglnew_qszf_ajax.do?method=getFzbjForZflsgh',{zflsgh:jQuery("#zflsgh").val()},function(data){
					jQuery('#fzbj').empty();
					jQuery('#xsszld').empty();
					jQuery('#xsszqsh').empty();
					jQuery('#fzbj').append("<option value=''>--��ѡ��--</option>");
					if(data != null && data.length != 0){
						for(var i=0; i<data.length; i++){
							var option = "<option value=\"" + data[i].bjdm + "\">" + data[i].bjmc + "</option>";
							jQuery('#fzbj').append(option);
						}
					}
				});
				var parameter ={};
				parameter["zflsgh"]=escape(jQuery("#zflsgh").val());
				var url = "gyglnew_qszf_ajax.do?method=getZflsxmForZflsgh";
				jQuery.post(url,parameter,
						function(result){
							if(result == ""){
								alertInfo("�����ڸ��߷���ʦ���ţ�",function(){obj.focus();});
								
							}else{
								jQuery('#zflsxm').html(result);
							}
						}
					);
				}
			}
			
			function getLd(){
				jQuery.getJSON('gyglnew_qszf_ajax.do?method=getLdForFzbj',{fzbj:jQuery("#fzbj").val()},function(data){
					jQuery('#xsszld').empty();
					jQuery('#xsszqsh').empty();
					jQuery('#xsszld').append("<option value=''>--��ѡ��--</option>");
					if(data != null && data.length != 0){
						for(var i=0; i<data.length; i++){
							var option = "<option value=\"" + data[i].lddm + "\">" + data[i].ldmc + "</option>";
							jQuery('#xsszld').append(option);
						}
					}
				});
			}
			
			function getQsh(){
				jQuery.getJSON('gyglnew_qszf_ajax.do?method=getQshForLd',{fzbj:jQuery("#fzbj").val(),xsszld:jQuery("#xsszld").val()},function(data){
					jQuery('#xsszqsh').empty();
					jQuery('#xsszqsh').append("<option value=''>--��ѡ��--</option>");
					if(data != null && data.length != 0){
						for(var i=0; i<data.length; i++){
							var option = "<option value=\"" + data[i].xsszqsh + "\">" + data[i].xsszqsh + "</option>";
							jQuery('#xsszqsh').append(option);
						}
					}
				});
			}
			
			function Save(){
			if($("zflsgh") && $("zflsgh").value==""){
	     		alertInfo("�߷���ʦ���Ų���Ϊ��!",function(tag){
	     			if(tag=="ok"){
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
	     	if($("fzbj") && $("fzbj").value==""){
	     		alertInfo("����༶����Ϊ��!",function(tag){
	     			if(tag=="ok"){
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
	     	if($("xsszld") && $("xsszld").value==""){
	     		alertInfo("ѧ������¥������Ϊ��!",function(tag){
	     			if(tag=="ok"){
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
	     	if($("xsszqsh") && $("xsszqsh").value==""){
	     		alertInfo("ѧ���������ҺŲ���Ϊ��!",function(tag){
	     			if(tag=="ok"){
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
	     	if($("xqsj") && $("xqsj").value==""){
	     		alertInfo("�߷�ʱ�䲻��Ϊ��!",function(tag){
	     			if(tag=="ok"){
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
				confirmInfo("�Ƿ�Ҫ���������ݣ�",saveQszfInfo);	
			}
			
			function saveQszfInfo(tag){
			if(tag=="ok"){
				jQuery.ajaxSetup({async:false});	
				// �õ�JSON����
			    var parameter ={};	
				parameter["zflsgh"]=escape(jQuery("#zflsgh").val());
				parameter["fzbj"]=escape(jQuery("#fzbj").val());
				parameter["xsszld"]=escape(jQuery("#xsszld").val());
				parameter["xsszqsh"]=escape(jQuery("#xsszqsh").val());
				parameter["xqsj"]=escape(jQuery("#xqsj").val());
				parameter["bz"]=escape(jQuery("#bz").val());
				var url = "gyglnew_qszf_ajax.do?method=save";
		        $("divWaiting").style.display="";
				$("divDisable").style.display="";
				jQuery.post(url,parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result);
					}
				);
				jQuery.ajaxSetup({async:true});
				}
			}
			
			function getZfls(){
			var url = 'gyglnew_qszf.do?method=getZfls';	
			showTopWin(url,800,600);
		}
		</script>
	</head>
	<body onload="">
	
		<html:form action="/gyglnew_qszf" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>

			<div style='width:98%;height:360px;overflow:hidden;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="8">
								<span>���������߷�</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr style="height:22px">
							<th width="20%">
								<font color="red">*</font>�߷���ʦ����
							</th>
							
							<td width="40%" colspan="3" >
								<html:text property="zflsgh" styleId="zflsgh" maxlength="50" style="width: 87px" onkeydown="if(event.keyCode==13) {checkIs(this)}">
								</html:text>
								<input  style="display: none" onclick="checkIs();" id="disbutton"/>
								<button type="button" onclick="getZfls();" class="btn_01" id="buttonFindStu">
									ѡ��
								</button>	
							</td>
							<th width="15%">
								�߷���ʦ����
							</th>
							
							<td width="25%" colspan="3" id="zflsxm">
							</td>
						</tr>
						<tr style="height:22px">
							<th width="20%">
								<font color="red">*</font>����༶
							</th>
							<td width="40%" colspan="3" >
								<html:select property="fzbj" styleId="fzbj" onchange="getLd();">
									<option value=''>--��ѡ��--</option>
								</html:select>
							</td>
							<th width="15%">
								<font color="red">*</font>ѧ������¥��
							</th>
							
							<td width="25%" colspan="3" >
								<html:select property="xsszld" styleId="xsszld" onchange="getQsh();">
									<option value=''>--��ѡ��--</option>
								</html:select>
							</td>
						</tr>
						<tr style="height:22px">
						<th width="20%">
								<font color="red">*</font>ѧ���������Һ�
							</th>
							
							<td width="40%" colspan="3" >
								<html:select property="xsszqsh" styleId="xsszqsh">
									<option value=''>--��ѡ��--</option>
								</html:select>
							</td>
							<th width="15%">
								<font color="red">*</font>�߷�ʱ��
							</th>
							
							<td width="25%" colspan="3">
								<html:text property="xqsj" styleId="xqsj"   style="width:90px"
									onclick="return showCalendar('xqsj','y-mm-dd');" 
									onblur="dateFormatChg(this)" readonly="true" />
							</td>
						</tr>
						<tr style="height:90px">
							<th align="right" >
								��ע
							</th>
							<td colspan="11" >
							<html:textarea  property='bz' styleId="bz" style="word-break:break-all;width:97%" onblur="chLeng(this,1000);"
									rows='10' value="${rs.bz }"  />
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="8">
								<div class="btn">
									<button type="button" onclick="Save();return false;">
										�� ��
									</button>
									<button type="button" onclick="Close();return false;">
										�� ��
									</button>

								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>

			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

