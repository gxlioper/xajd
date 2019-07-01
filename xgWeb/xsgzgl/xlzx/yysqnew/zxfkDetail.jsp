<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="xsgzgl/xlzx/yysqnew/js/yysqnewComm.js"></script>
		<script type="text/javascript">
		function saveyyzxInfo(){
			  var zxurl= "xlzx_zxyyclnew.do?method=updateXlzxInfo";
			  var zxParameter ={};
			  meetFlag();

			  // ============= �ֶ���֤ begin ===========
			  var zxztLen = jQuery("[name=zxzt]:checked").length;
			  var xxdm = "${xxdm}";
			  
			  if(zxztLen == 0){
				showAlert("��ѡ����ѯ״̬��");
				return false;
			  }
			  var zxzt = jQuery("[name=zxzt]:checked").val();
			  var xlcsjg=jQuery("#xlcsjg").val();
			  if(xxdm != "10026" ){
				  if(jQuery.trim(xlcsjg)==""){
					  if(zxzt == "2"){
						  showAlert("������Խ������Ϊ�գ�");
						  return false;
					  } else if(zxzt == "3"){
						  showAlert("��ע����Ϊ�գ�");
						  return false;
					  }
				  }
			  }
			  var ywyw = jQuery("[name=ywyw]:checked").val();
			 
			  if("1"==ywyw){
				  var zyzlls=jQuery("#zyzlls").val();
				  if(jQuery.trim(zyzlls)=="" && zxzt == "2"){
					  if(xxdm != "10026" ){
						  showAlert("סԺ������ʷ����Ϊ�գ�");
							return false;
					  }
				  }
			  }else{
				  jQuery("#zyzlls").val("");
			  }
			  var zxcs=jQuery("#zxcs").val();
			  if(xxdm != "10026" ){
				  if(jQuery.trim(zxcs)=="" && zxzt == "2"){
					  showAlert("��ǰ��ѯ��������Ϊ�գ�");
						return false;
				  }
			  }
			  var sfjaLen = jQuery("[name=sfja]:checked").length;
			  if(xxdm != "10026" ){
				  if(sfjaLen == 0 && zxzt == "2"){
						showAlert("��ѡ���Ƿ�᰸��");
						return false;
				  }
		      }
			  var sfja = jQuery("[name=sfja]:checked").val();
			  var zyzlls = jQuery("#zyzlls").val();
			  var sczxsj = jQuery("#sczxsj").val();
			  var bczxnr = jQuery("#bczxnr").val();
			  var bcjjwt = jQuery("#bcjjwt").val();
			  var zxgsfs = jQuery("#zxgsfs").val();
			  var zxwtlxdm = jQuery("#zxwtlx").val();
			  // ============= �ֶ���֤ end ===========
			zxParameter["zxstatus"]=jQuery("#zxzt").val();//1����ѯ2����ѯ3δ��ѯ
			if(jQuery("#zxzt").val()=="2"){
				zxParameter["xspjzt"]=1;
			}
			zxParameter["yyid"]=jQuery("#yyid").val();

			zxParameter["xlcsjg"]=xlcsjg;
			zxParameter["ywyw"]=ywyw;
			zxParameter["zyzlls"]=zyzlls;
			zxParameter["zxcs"]=zxcs;
			zxParameter["sfja"]=sfja;
			zxParameter["sczxsj"]=sczxsj;
			zxParameter["bczxnr"]=bczxnr;
			zxParameter["bcjjwt"]=bcjjwt;
			zxParameter["zxgsfs"]=zxgsfs;
			zxParameter["zxwtlxdm"]=zxwtlxdm;

			showConfirm("ȷ�ϱ�����Ϣ��",{"okFun":function(){
				jQuery.ajaxSetup({async:false});
					//��ѯ��Ϣ��������ѯ��	
					jQuery.post(zxurl,zxParameter,function(data){
						if(data == true){
								showAlert("����ɹ���",{},{"clkFun":function(){
									frameElement.api.opener.refreshForm("xlzx_yysqnew_yysqnew.do");
									iFClose();
								}
							});
						}else{
							return showAlert("����ʧ�ܣ�");
						}
					},'json');
					jQuery.ajaxSetup({async:true});
			}});
		}
		function init(){
			sfAgree();
			//jQuery("input[type='radio'][name='meet'][value='"+jQuery("#status").val()+"']").attr("checked",true);
			if(jQuery("#status").val()=="2"){
				//jQuery("input[type='radio'][name='meet']").attr("disabled",true);
			}
			meetFlag();
			sfYzx();
			initData('zyzlls', zyzllsMsg);
			jQuery('#ywyw_td').click();
		}

		function sfAgree(){
			if(jQuery("#status").val()=="2"){
				//jQuery("tr[name=yyfkId]").show();
				jQuery("tr[name=yysbyytr]").hide();		
			}else if(jQuery("#status").val()=="5"){
			    jQuery("tr[name=yyfkId]").hide();
			    //jQuery("tr[name=yysbyytr]").show();
			    jQuery("thead[name=zxxgInfo]").hide();	
				jQuery("tbody[name=zxxgInfo]").hide();
			}else{
			 	jQuery("tr[name=yyfkId]").hide();
				jQuery("tr[name=yysbyytr]").hide();		
				jQuery("thead[name=zxxgInfo]").hide();	
				jQuery("tbody[name=zxxgInfo]").hide();
			}
			
			if(jQuery("input[type='radio'][name='sfty'][value='1']").prop("checked")==true){
				jQuery("tr[name=yyfkId]").show();
				jQuery("tr[name=yysbyytr]").hide();
				jQuery("#yystatus").val("2");
			}else if(jQuery("input[type='radio'][name='sfty'][value='2']").prop("checked")==true){
				jQuery("tr[name=yyfkId]").hide();
				jQuery("tr[name=yysbyytr]").show();
				jQuery("#yystatus").val("5");
			}
		}

		function sfYzx(){
			var zxzt = jQuery("[name=zxzt]:checked").val();
			var xxdm = "${xxdm}";
			if("2"==zxzt){
				jQuery("tr[name=zxfkYzxInfo]").show();
				if(xxdm == "10026"){
					jQuery("#zxfkInfo").html("������Խ��");
				}else{
					jQuery("#zxfkInfo").html("<span class='red'>*</span>������Խ��");
				}
				
				changeZyzlls();
			}else if("3"==zxzt){
				jQuery("tr[name=zxfkYzxInfo]").hide();
				if(xxdm == "10026"){
					jQuery("#zxfkInfo").html("��ע");
				}else{
					jQuery("#zxfkInfo").html("<span class='red'>*</span>��ע");
				}
				
				jQuery("#zyzlls_tr").hide();
			}else{
				if(xxdm == "10026"){
					jQuery("#zxfkInfo").html("������Խ��");
				}else{
					jQuery("#zxfkInfo").html("<span class='red'>*</span>������Խ��");
				}
				changeZyzlls();
			}
		}
		function meetFlag(){
			if(jQuery("input[type='radio'][name='zxzt'][value='1']").prop("checked")==true){
				jQuery("#zxzt").val("1");
			}else if(jQuery("input[type='radio'][name='zxzt'][value='2']").prop("checked")==true){
				jQuery("#zxzt").val("2");
			}else if(jQuery("input[type='radio'][name='zxzt'][value='3']").prop("checked")==true){
				jQuery("#zxzt").val("3");
			}
		}
		function showTbody(obj,objTbody){
			if(obj.className=="up"){
				obj.className="down";
				document.getElementById(objTbody).style.display="none";
			}else{
				obj.className="up";
				document.getElementById(objTbody).style.display="";
			}
		}
		</script>
		
	</head>
	<body onload="init();">
		<html:form action="/xlzx_yysqnew" method="post" styleId="form">
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pbdate" id="pbdate" value="${yyzxInfo.yyzxrq}" />
			<input type="hidden" name="zxid" id="zxid" value="${yyzxInfo.zxid}" />
			<input type="hidden" name="id" id="id" value="${yyzxInfo.id}" />
			<input type="hidden" name="yyid" id="yyid" value="${yyzxInfo.id}" />
			<input type="hidden" name="zgh" id="zgh" value="${yyzxInfo.zgh}" />
			<input type="hidden" name="xh" id="xh" value="${yyzxInfo.xh}" />
			<input type="hidden" name="yjdrs" id="yjdrs" value="${yjdrs}" />
			<input type="hidden" name="kjdrs" id="kjdrs" value="${yyzxInfo.kjdrs}" />
			<div style='width:100%;height:405px;overflow-y:auto;overflow-x:hidden'>
				<logic:present name="jzxx">
					<logic:notEmpty name="jzxx">
						<table width="100%" border="0" class="formlist" id="jzxx_tb">
							<thead>
							<tr>
								<th colspan="4">
									<span>�ҳ���д��Ϣ</span>
								</th>
							</tr>
							</thead>
							<tbody>
							<tr>
								<th width="16%">
									<span>�ҳ�����</span>
								</th>
								<td width="34">
										${jzxx.jzxm}
								</td>
								<th width="16%">
									<span>�Ա�</span>
								</th>
								<td width="34">
										${jzxx.jzxbmc}
								</td>
							</tr>
							<tr>
								<th width="16%">
									<span>��ϵ�绰</span>
								</th>
								<td width="34">
										${jzxx.jzlxdh}
								</td>
								<th width="16%">
									<span>��ѧ����ϵ</span>
								</th>
								<td width="34">
										${jzxx.gx}
								</td>
							</tr>
							<tr>
								<th width="16%">
									<span>��������</span>
								</th>
								<td width="34">
										${jzxx.jzdzyx}
								</td>
								<th width="16%">
									<span>��ͥ��ȫ</span>
								</th>
								<td width="34">
										${jzxx.jtjq}
								</td>

							</tr>
							<tr>
								<th width="16%">
									<span>����ְҵ</span>
								</th>
								<td width="34">
										${jzxx.fqzy}
								</td>
								<th width="16%">
									<span>��ѧ��</span>
								</th>
								<td width="34">
										${jzxx.fxl}
								</td>

							</tr>
							<tr>
								<th width="16%">
									<span>ĸ��ְҵ</span>
								</th>
								<td width="34">
										${jzxx.mqzy}
								</td>
								<th width="16%">
									<span>ĸѧ��</span>
								</th>
								<td width="34">
										${jzxx.mxl}
								</td>
							</tr>
							<tr>
								<th width="16%">
									<span>��ͥסַ</span>
								</th>
								<td  colspan="3">
										${jzxx.jtdz}
								</td>
							</tr>
							<tr>
								<th width="16%">
									<span>ѧ���Ƿ�֪��</span>
								</th>
								<td width="34">
										${jzxx.xssfzxmc}
								</td>
								<th width="16%">
									<span>����Ա�Ƿ�֪��</span>
								</th>
								<td width="34">
										${jzxx.fdysfzxmc}
								</td>
							</tr>
							<tr>
								<th width="16%">
									����Ŀ��</span>
								</th>
								<td  colspan="3">
										${jzxx.lfmd}
								</td>
							</tr>
							</tbody>

						</table>
					</logic:notEmpty>
				</logic:present>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr >
							<th colspan="4">
								<span>ѧ����Ϣ</span>
							</th>
						</tr>
					</thead>
						<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<logic:notEmpty name="xstxxx">
						<thead>
						<tr >
							<th colspan="4">
								<span>ѧ����д��Ϣ</span>
							</th>
						</tr>
						</thead>
						<%@ include file="/xsgzgl/xlzx/yysq/viewXstxxx.jsp" %>
					</logic:notEmpty>
					<thead>
						<tr >
							<th colspan="4">
								<span>ԤԼ��ѯʦ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="zxsInfoList">
							<tr style="height:10px">
								<th  width="16%">
									����
								</th>
								<td  width="34%">
									${yyzxInfo.zxsxm}
								</td>
								<th  width="16%">
									ְ����
								</th>
								<td  width="34%">
									${yyzxInfo.zgh}
								</td>
							</tr>
							<tr style="height:10px">
								<th  width="16%">
									�Ա�
								</th>
								<td  width="34%">
									${yyzxInfo.zxsxb }
								</td>
								<th width="16%">
									����
								</th>
								<td  width="34%">
									${yyzxInfo.zxsage}
								</td>
							</tr>
							<tr style="height:10px">
								<th width="16%">
									��ϵ�绰
								</th>
								<td  width="34%">
									${yyzxInfo.lxdh }
									
								</td>
								<th width="16%">
									���ڲ���
								</th>
								<td  width="34%">
									${yyzxInfo.bmmc }
									
								</td>
							</tr>							
					</tbody>
					<logic:notEmpty name="yyzxInfo" property="yyzxzt">
						 <thead>
							<tr >
								<th colspan="4">
									<span>ԤԼ��Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody id="yyzxInfo"> 
							<tr style="height:10px">
								<th>
									ԤԼ��ѯ����
								</th>
								<td >
									<span class="red"><B>${ yyzxInfo.yyzxrq}</B></span>
								</td>
								 <th  width="16%">
									ԤԼ��ѯʱ��
								</th>
								<td  width="34%" >
									<logic:equal value="2" name="pbfs">
										${yyzxInfo.sjdmc}
									</logic:equal>
									<logic:notEqual value="2" name="pbfs">
										${yyzxInfo.yyqssj}&nbsp;
										<logic:notEqual  name="yyzxInfo" property="yyjssj" value="">
												��&nbsp;${yyzxInfo.yyjssj}
										</logic:notEqual>
									</logic:notEqual>
								</td>
							</tr>
							<tr style="height:10px">
								<th>
									Ԥ����ϵ����
								</th>
								<td colspan="3">
									${ yyzxInfo.xstell}
								</td>
							</tr>
							<tr style="height:10px">
								<th>
									��ѯ����
								</th>
								<td colspan="3">
									${ yyzxInfo.yyzxzt}
								</td>
							</tr>
							<tr style="height:30px">
								<th>
									��ѯ��Ҫ<br/>
								</th>
								<td colspan="3">
									${ yyzxInfo.yyzxxq}
								</td>
							</tr>
						</tbody>
					</logic:notEmpty>
					<thead>
						<tr >
							<th colspan="4">
								<span>��ѯ������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="yyInfo">
							<tr style="height:10px">
								<th  width="16%">
									ԤԼ״̬
								</th>
								<td colspan="3" width="34%">
									<input type="hidden" name="status" id="status" value="${yyzxInfo.status}">
									<input type="hidden" name="yystatus" id="yystatus" value="">
										<logic:notEqual name ="yyzxInfo" property="status" value="1">
											${yyzxInfo.statusmc}
										</logic:notEqual>
										<logic:equal name ="yyzxInfo" property="status" value="1">
											<logic:equal name ="doType"  value="view">
											${yyzxInfo.statusmc}
											</logic:equal>
											<logic:notEqual name ="doType"  value="view">
												<input type="radio"  name="sfty"  value="1" checked="true" onclick="sfAgree()"/>ԤԼ�ɹ�
												<input type="radio"  name="sfty"  value="2" onclick="sfAgree()"/>ԤԼʧ��
											</logic:notEqual>
										</logic:equal>
								</td>
								
							</tr>
							<tr style="height:10px" name="yyfkId">
								<th width="16%">
									��ѯ��������
								</th>
								<td  width="34%" >
									<logic:notEqual name ="yyzxInfo" property="status" value="1">
										<span class="red"><B>${yyzxInfo.zxrq}</B></span>
									</logic:notEqual>
									<logic:equal name ="yyzxInfo" property="status" value="1">
										<logic:equal name ="doType"  value="view">
											${yyzxInfo.zxrq}
										</logic:equal>
										<logic:notEqual name ="doType"  value="view">
											<html:text property="zxrq" styleId="zxrq"  value="${yyzxInfo.yyzxrq}"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  onchange="showNewJdrs(this);"/>
										</logic:notEqual>
									</logic:equal>
								</td>
								<th  width="16%">
									��ѯʱ��
								</th>
								<td  width="34%" >
										<logic:equal value="2" name="pbfs">
											${yyzxInfo.sjdmczx}
										</logic:equal>
										<logic:notEqual value="2" name="pbfs">
											<logic:notEqual name ="yyzxInfo" property="status" value="1">
												${yyzxInfo.zxqssj}&nbsp;
												<logic:notEmpty name="yyzxInfo" property="zxjssj">��&nbsp;${yyzxInfo.zxjssj}</logic:notEmpty>
											</logic:notEqual>
											<logic:equal name ="yyzxInfo" property="status" value="1">
												<logic:equal name ="doType"  value="view">
														${yyzxInfo.zxqssj}&nbsp;
														<logic:notEmpty name="yyzxInfo" property="zxjssj">��&nbsp;${yyzxInfo.zxjssj}</logic:notEmpty>
												</logic:equal>
												<logic:notEqual name ="doType"  value="view">
													<html:text property="zxqssj" styleId="zxqssj" style="width:30%"  value="${yyzxInfo.yyqssj}" onfocus="WdatePicker({dateFmt:'HH:mm'})" />&nbsp;��&nbsp;
													<html:text property="zxjssj" styleId="zxjssj" style="width:30%"  value="${yyzxInfo.yyjssj}" onfocus="WdatePicker({dateFmt:'HH:mm'})" />
												</logic:notEqual>
										</logic:equal>
										</logic:notEqual>
										
								</td>
							</tr>
							
							<tr>
								<th>
									��ѯʦ
								</th>
								<td colspan="3">
									 <a href = 'javascript:void(0);' style="color:#0f5dc2;text-decoration:underline;" onclick="showDialog('�鿴��ѯʦ��Ϣ' , 750 , 410 , 'xlzx_zxs.do?method=zxsglDetail&doType=view&zgh=${yyzxInfo.apzxs}')" >${yyzxInfo.apzxsxm}</a>
								</td>
							</tr>
							
							<tr style="height:10px" name="yyfkId">
								<th  width="16%">
									��ϵ�绰
								</th>
								<td  width="34%" colspan="3">
										<logic:notEqual name ="yyzxInfo" property="status" value="1">
											${yyzxInfo.zxtell}
										</logic:notEqual>
										<logic:equal name ="yyzxInfo" property="status" value="1">
											<logic:equal name ="doType"  value="view">
												${yyzxInfo.zxtell}
											</logic:equal>
											<logic:notEqual name ="doType"  value="view">
												<html:text property="zxtell" styleId="zxtell"  value="${yyzxInfo.lxdh}"  maxlength="11" onblur="checkInputData(this);"/>
											</logic:notEqual>
										</logic:equal>
								</td>
							</tr>
							<tr style="height:10px" name="yyfkId" >
								<th  width="16%">
									��ѯ��ַ
								</th>
								<td  width="34%" colspan="3">
										<logic:notEqual name ="yyzxInfo" property="status" value="1">
											${yyzxInfo.zxdz}
										</logic:notEqual>
										<logic:equal name ="yyzxInfo" property="status" value="1">
											<logic:equal name ="doType"  value="view">
												${yyzxInfo.zxdz}
											</logic:equal>
											<logic:notEqual name ="doType"  value="view">
												<html:text property="zxdz" styleId="zxdz" maxlength="50" style="width:400px" value="${yyzxInfo.address}" />
											</logic:notEqual>
										</logic:equal>
								</td>
							</tr>
							<logic:equal name="xxdm" value="10026">
								<tr>
									<th>У��</th>
									<td colspan="3" id="xqdm">${xqmc}</td>
								</tr>
						    </logic:equal>
							<tr style="height:10px" name="yyfkId">
								<th  width="16%">
									��ע
									<logic:equal name ="yyzxInfo" property="status" value="1"><logic:notEqual name="doType" value="view"><br/><font color="red"><B>(��500��)</B></font></logic:notEqual></logic:equal>
								</th>
								<td  width="34%" colspan="3">
										<logic:notEqual name ="yyzxInfo" property="status" value="1">
											${yyzxInfo.zxbz}
										</logic:notEqual>
										<logic:equal name ="yyzxInfo" property="status" value="1">
											<logic:equal name ="doType"  value="view">
												${yyzxInfo.zxbz}
											</logic:equal>
											<logic:notEqual name ="doType"  value="view">
												<html:textarea  property='bz' styleId="bz" value="${yyzxInfo.zxbz}" style="word-break:break-all;width:99%" onblur="chLeng(this,500);" rows='4' />
											</logic:notEqual>
										</logic:equal>	
								</td>
							</tr>
							<tr style="height:10px" name="yysbyytr">
								<th  width="16%">
									ԤԼʧ��ԭ��
									<logic:equal name ="yyzxInfo" property="status" value="1"><logic:notEqual name ="doType"  value="view"><br><font color="red"><B>(��500��)</B></font></logic:notEqual>
									</logic:equal>
								</th>
								<td  width="34%" colspan="3">
										<logic:notEqual name ="yyzxInfo" property="status" value="1">
											${yyzxInfo.yysbyy}
										</logic:notEqual>
										<logic:equal name ="yyzxInfo" property="status" value="1">
											<logic:equal name ="doType"  value="view">
												${yyzxInfo.yysbyy}
											</logic:equal>
											<logic:notEqual name ="doType"  value="view">
												<html:textarea  property='yysbyy' styleId="yysbyy" value="${yyzxInfo.yysbyy}" style="word-break:break-all;width:99%" onblur="chLeng(this,500);" rows='4' />
											</logic:notEqual>
										</logic:equal>
								</td>
							</tr>
						</tbody>
						
						<thead>
							<tr>
								<th colspan="4">
									<span><a href="#" class="down" onclick="showTbody(this,'myTbody');return false">��ѯ��ʷ��Ϣ</a></span>
								</th>
							</tr>
						</thead>
						<tbody id="myTbody" style="display: none;">
							<tr>
								<th colspan="4" style="text-align: left;">
									<logic:notEmpty name="zxlsxxList">
										<table width="100%" border="0" class="formlist">
											<tr>
												<th width="12%" style="text-align: center;">��ѯʱ��</th>
												<th width="10%" style="text-align: center;">��ѯʦ</th>
												<th width="8%" style="text-align: center;">����ҩ<br />������</th>
												<th width="35%" style="text-align: center;">��ѯ����</th>
												<th width="35%" style="text-align: center;">�������</th>
											</tr>
											<logic:iterate name="zxlsxxList" id="zxlsxxMap" indexId="index">
												<tr>
													<td width="12%" style="text-align: center;">${zxlsxxMap.zxrq}</td>
													<td width="10%" style="text-align: center;">${zxlsxxMap.apzxsxm}</td>
													<td width="8%" style="text-align: center;">${zxlsxxMap.ywywmc}</td>
													<td width="35%" style="text-align: left;">${zxlsxxMap.bczxnr}</td>
													<td width="35%" style="text-align: left;">${zxlsxxMap.bcjjwt}</td>
												</tr>
											</logic:iterate>
										</table>
									</logic:notEmpty>
									<logic:empty name="zxlsxxList">
										<span style="color:green; font-weight:bold; font-size:12px;padding-left:10px;">û����ѯ��ʷ��Ϣ</span>
									</logic:empty>
								</th>
							</tr>
						</tbody>
						
						
						<thead name = "zxxgInfo">
							<tr >
								<th colspan="4">
									<span>��ѯ������Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody id="zxInfo" name = "zxxgInfo">
								<tr style="height:10px">
									<th  width="16%">
										<span class="red">*</span>��ѯ״̬
									</th>
									<td  width="34%" colspan="3">
										<input type="hidden" name="xspjzt" id="xspjzt" value="${yyzxInfo.xspjzt}">
										<input type="hidden" name="zxstatus" id="zxstatus" value="${yyzxInfo.zxzt}">
										<input type="hidden" name="zxzt" id="zxzt" value="">
											<html:radio name="yyzxInfo" name="yyzxInfo" property="zxzt" value="2"  onclick="sfYzx()"/>����ѯ
											<html:radio name="yyzxInfo" name="yyzxInfo" property="zxzt" value="3"  onclick="sfYzx()"/>δ��ѯ
									</td>
								</tr>

								<tr style="height:10px">
									<th  width="16%" id="zxfkInfo">
									<logic:notEqual name="xxdm" value="10026">
										<span class="red">*</span>
									</logic:notEqual>	
										������Խ��
									</th>
									<td  width="34%" colspan="3">
										<html:text name="yyzxInfo" property="xlcsjg" styleId="xlcsjg" maxlength="100" style="width:100%;"></html:text>
									</td>
								</tr>

								<logic:equal name="xxdm" value="10704">
									<tr style="height:10px" name="zxfkYzxInfo">
										<th  width="16%" id="">
											��ѯ��������
										</th>
										<td  width="34%" colspan="3">
											<html:select name="yyzxInfo" property="zxwtlxdm" styleId="zxwtlx" style="width:150px">
												<html:option value=""></html:option>
												<html:options collection="zxwtlxList" property="zxwtlxdm" labelProperty="zxwtlxmc" />
											</html:select>
										</td>
									</tr>
								</logic:equal>

								<tr name="zxfkYzxInfo">
									<th>
										����ҩ������
									</th>
									<td id="ywyw_td" colspan="3" onclick="changeZyzlls()">
										<logic:iterate id="o" name="haveList" >
											<label>
												<html:radio name="yyzxInfo" property="ywyw" value="${o.dm}">${o.mc}</html:radio>
											</label>
										</logic:iterate>
									</td>
								</tr>
								<tr id="zyzlls_tr">
								    <th>
										סԺ������ʷ</br><font color="red">(��500��)</font>
									</th>
									<td colspan="3">
										<html:textarea name="yyzxInfo" property="zyzlls" styleId="zyzlls" cols="50" rows="4" style="width:100%" onblur="chLeng(this,500)"></html:textarea>
									</td>
								</tr>
								<tr name="zxfkYzxInfo">
								    <th>
										�״���ѯʱ��
									</th>
									<td>
										<input type="text" id="sczxsj" value="${yyzxInfo.sczxsj }"  name="sczxsj" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
									</td>
								    <th>
								    	<logic:notEqual name="xxdm" value="10026">
										<span class="red">*</span>
										</logic:notEqual>
										��ǰ��ѯ����
									</th>
									<td>
										<input type="text" id="zxcs" value="${yyzxInfo.zxcs }" name="zxcs" maxlength="2" onkeyup="checkInputData(this);"/>
									</td>
								</tr>
								<tr name="zxfkYzxInfo">
								    <th>
										<logic:notEqual name="xxdm" value="10026">
										<span class="red">*</span>
										</logic:notEqual>
										�Ƿ�᰸
									</th>
									<td colspan="3">
										<logic:iterate id="o2" name="isNotList" >
											<label>
												<html:radio name="yyzxInfo" property="sfja" value="${o2.dm}">${o2.mc}</html:radio>
											</label>
										</logic:iterate>
									</td>
								</tr>
								<tr name="zxfkYzxInfo">
								    <th>
										������ѯ���� </br><font color="red">(��500��)</font>
									</th>
									<td colspan="3">
										<html:textarea name="yyzxInfo" property="bczxnr" styleId="bczxnr" cols="50" rows="4" style="width:100%" onblur="chLeng(this,500)"></html:textarea>
									</td>
								</tr>
								<tr name="zxfkYzxInfo">
								    <th>
										���ν������ </br><font color="red">(��500��)</font>
									</th>
									<td colspan="3">
										<html:textarea name="yyzxInfo" property="bcjjwt" styleId="bcjjwt" cols="50" rows="4" style="width:100%" onblur="chLeng(this,500)"></html:textarea>
									</td>
								</tr>
								<tr name="zxfkYzxInfo">
								    <th>
										��ѯ�����뷴˼ </br><font color="red">(��500��)</font>
									</th>
									<td colspan="3">
										<html:textarea name="yyzxInfo" property="zxgsfs" styleId="zxgsfs" cols="50" rows="4" style="width:100%" onblur="chLeng(this,500)"></html:textarea>
									</td>
								</tr>
						</tbody>
					</table>
				</div>
			  <table  border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz" id="btx">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button id="buttonSave" onclick="saveyyzxInfo();return false;">
										�� ��
									</button>
									<button onclick="Close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>

