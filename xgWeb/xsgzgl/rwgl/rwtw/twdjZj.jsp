<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
				<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script>
		
			//ѡ��
			function sendXx(){
				jQuery.ajaxSetup({async:false});	
				// �õ�JSON����
			    var parameter ={};	
			    parameter["pkValue"]=escape(jQuery("#xh").val());
				url = "rwgl_rwtwgl_ajax.do?method=rwxsCk";
				jQuery.getJSON(url,parameter,
					function(data){
						if(data!=null){
							jQuery("#xm").text(data.xm);
							jQuery("#xb").text(data.xb);
							jQuery("#nj").text(data.nj);
							jQuery("#xymc").text(data.xymc);
							jQuery("#zymc").text(data.zymc);
							jQuery("#bjmc").text(data.bjmc);
							jQuery("#zzmmmc").text(data.zzmmmc);
							jQuery("#mzmc").text(data.mzmc);
							jQuery("#csrq").text(data.csrq);
							jQuery("#sfzh").text(data.sfzh);
							jQuery("#sjhm").text(data.sjhm);
							jQuery("#jtdz").text(data.jtdz);
							jQuery("#jtdh").text(data.jtdh);
							jQuery("#rwsj").text(data.rwsj);
							var xxdm = jQuery("#xxdm").val();
							if("10351" == xxdm){
								jQuery("#rwdwdmc").text(data.rwdwdmc);
							}else{
								jQuery("#rwdwdmc").text(data.rwd);
							}
						}
					}
				);
				jQuery.ajaxSetup({async:true});
			}
			//����
			function rwdjBc(){
				if(jQuery.trim(jQuery("#xh").val())==""){
			 		alertInfo("ѧ�Ų���Ϊ��!",function(tag){
			 			if(tag=="ok"){
			 				return false;
			 			}
			 		});
			 		return false;
				}
				if(jQuery.trim(jQuery("#rwzh").val())==""){
			 		alertInfo("����֤�Ų���Ϊ��!",function(tag){
			 			if(tag=="ok"){
			 				return false;
			 			}
			 		});
			 		return false;
				}
				
				if(jQuery.trim(jQuery("#twsj").val())==""){
			 		alertInfo("����ʱ�䲻��Ϊ��!",function(tag){
			 			if(tag=="ok"){
			 				return false;
			 			}
			 		});
			 		return false;
				}

				var rwsj = jQuery("#rwsj").text();
				var twsj = jQuery("#twsj").val();
				if(rwsj>=twsj){
					alertInfo("����ʱ�䲻ӦС�ڻ��������ʱ��!",function(tag){
			 			if(tag=="ok"){
			 				return false;
			 			}
			 		});
			 		return false;
				}
				
				jQuery.ajaxSetup({async:false});	
				// �õ�JSON����
			    var parameter ={};	
			    parameter["xh"]=escape(jQuery("#xh").val());
				parameter["rwzh"]=escape(jQuery("#rwzh").val());
				parameter["twsj"]=escape(jQuery("#twsj").val());
				parameter["yzy"]=escape(jQuery("#yzy").val());
				parameter["ybj"]=escape(jQuery("#ybj").val());
				parameter["hjgx"]=escape(jQuery("#hjgx").val());
				parameter["hkszd"]=escape(jQuery("#hkszd").val());
				parameter["bz"]=escape(jQuery("#bz").val());
				url = "rwgl_rwtwgl_ajax.do?method=twdjBc&doType=add";
			    $("divWaiting").style.display="";
				$("divDisable").style.display="";
				jQuery.post(url,parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						if("����ɹ�"==result){

							 showAlert(result,{},{"clkFun":function(){
				    				if (parent.window){
				    					refershParent();
				    				}
							}});
						}else{
							alertInfo(result,function(tag){
				     			if(tag=="ok"){
				     				return false;
				     			}
				     		});
				     		return false;
						}
					}
				);
				jQuery.ajaxSetup({async:true});
			}
		</script>
	</head>
	<body>
		<html:form action="/rwgl_rwtwgl" method="post">
			<div style="height:485px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>ѧ��
							</th>
							<td width="34%">
								<html:text property="xh" styleId="xh" maxlength="16" styleClass="text_nor" readonly="true"/>
								<button type="button" onclick="showDialog('',800,570,'rwgl_rwtwgl.do?method=rwxsCx&goto=rwgl_rwtwgl.do?method=twdjZj');return false;" class="btn_01">
									ѡ��
								</button>
								<input type="hidden" id="getStuInfo" onclick="sendXx();"/>
							</td>
							<th width="16%">
								����
							</th>
							<td width="34%" >
								<font id="xm">${rs.xm}</font>
							</td>
						</tr>
						<tr>
							<th width="16%">
								�Ա�
							</th>
							<td width="34%">
								<font id="xb">${rs.xb}</font>
							</td>
							<th width="16%">
								�꼶
							</th>
							<td width="34%">
								<font id="nj">${rs.nj}</font>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<bean:message key="lable.xb" />
							</th>
							<td width="34%">
								<font id="xymc">${rs.xymc}</font>
							</td>
							<th width="16%">
								רҵ
							</th>
							<td width="34%">
								<font id="zymc">${rs.zymc}</font>
							</td>
						</tr>
						<tr>
							<th width="16%">
								�༶
							</th>
							<td width="34%">
								<font id="bjmc">${rs.bjmc}</font>
							</td>
							<th width="16%">
								������ò
							</th>
							<td width="34%">
								<font id="zzmmmc">${rs.zzmmmc}</font>
							</td>
						</tr>
						<tr>
							<th width="16%">
								����
							</th>
							<td width="34%">
								<font id="mzmc">${rs.mzmc}</font>
							</td>
							<th width="16%">
								��������
							</th>
							<td width="34%">
								<font id="csrq">${rs.csrq}</font>
							</td>
						</tr>
						<tr>
							<th width="16%">
								���֤��
							</th>
							<td width="34%">
								<font id="sfzh">${rs.sfzh}</font>
							</td>
							<th width="16%">
								�ֻ�����
							</th>
							<td width="34%">
								<font id="sjhm">${rs.sjhm}</font>
							</td>
						</tr>
						<tr>
							<th width="16%">
								��ͥ��ַ
							</th>
							<td width="34%">
								<font id="jtdz">${rs.jtdz}</font>
							</td>
							<th width="16%">
								��ͥ�绰
							</th>
							<td width="34%">
								<font id="jtdh">${rs.jtdh}</font>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								����ʱ��
							</th>
							<td width="34%">
								<font id="rwsj">${rs.rwsj}</font>
							</td>
							<th width="16%">
								�����
							</th>
							<td width="34%" style="word-break:break-all;width:99%">
								<font id="rwdwdmc">
									<!-- ���ݴ�ѧ -->
									<logic:equal name="xxdm" value="10351">	
										${rs.rwdwdmc }
									</logic:equal>
									<logic:notEqual name="xxdm" value="10351">	
										${rs.rwd }
									</logic:notEqual>
								</font>
							</td>
						</tr>
						<tr>
							<th width="16%">
								���鷽ʽ
							</th>
							<td width="34%" colspan="3">
								<font id="rwfs">${rs.rwfsmc}</font>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>����֤��
							</th>
							<td width="34%">
								<html:text property="rwzh" styleId="rwzh" maxlength="10" styleClass="text_nor" ></html:text>
							</td>
							<th width="16%">
								<font color="red">*</font>����ʱ��
							</th>
							<td width="34%">
								<html:text property="twsj" styleId="twsj" maxlength="10" onclick="return showCalendar('twsj','y-mm-dd');" onblur="dateFormatChg(this)" readonly="true" styleClass="text_nor" ></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								ԭרҵ
							</th>
							<td width="34%">
								<html:text property="yzy" styleId="yzy" maxlength="10" styleClass="text_nor" ></html:text>
							</td>
							<th width="16%">
								ԭ�༶
							</th>
							<td width="34%">
								<html:text property="ybj" styleId="ybj" maxlength="10" styleClass="text_nor" ></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								������ϵ
							</th>
							<td width="34%">
								<html:text property="hjgx" styleId="hjgx" maxlength="10" styleClass="text_nor" ></html:text>
							</td>
							<th width="16%">
								�������ڵ�
							</th>
							<td width="34%">
								<html:text property="hkszd" styleId="hkszd" maxlength="10" styleClass="text_nor" ></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								��ע<br/><font class="red">(��1000��)</font>
							</th>
							<td width="84%" colspan="3">
								<html:textarea property='bz' styleId="bz" style="word-break:break-all;width:99%"
										rows='3' onblur="chLeng(this,1000)"/>
							</td>
						</tr>
					</tbody>
					<%--<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="rwdjBc()">
										�� ��
									</button>
									<button type="button" onclick="Close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				--%></table>
			</div>
			
			<table width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="rwdjBc()">
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
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

