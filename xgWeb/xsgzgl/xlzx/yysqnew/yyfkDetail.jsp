<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript">
		function saveyyzxInfo(){
				if(!jQuery("tr[name=yyfkId]").is(":hidden")  && (jQuery("#zxrq").val()=='' || jQuery("[name=apzxs]:checked").length == 0)){
					return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
				}
				
				if(!jQuery("tr[name=yysbyytr]").is(":hidden") && jQuery("#yysbyy").val()==''){
					return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
				}
				//��ԤԼ�ɹ��ġ���ѡ���ԤԼ����ԤԼ�ɹ��ģ��ѽӴ��������ܴ��ڿɽӴ�������
				if('${pbfs}' != '2'){
					if(jQuery("#status").val()!="2" && jQuery("#yystatus").val()=="2" && parseInt(jQuery("#yjdrs").val())>=parseInt(jQuery("#kjdrs").val())){
						return showAlert("�Ѵﵽ��ԤԼ���ޣ�");
					}
				}else{
			        if(jQuery("#sjddm").val() == ""){
			        	return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
					}
				}
				var zxurl='';
				var yyurl='';
				var zxParameter ={};
				var yyParameter ={};
				//ԤԼ����
				if(jQuery("#doType").val()=='yyfk'){
					yyurl = "xlzx_yysqnew.do?method=updateYysqInfo";
					zxurl = "xlzx_zxyyclnew.do?method=saveXlzxInfo";
					var zxUpurl = "xlzx_zxyyclnew.do?method=updateXlzxInfo";
					//��ֹundifined
					var sjddm = (!jQuery("#sjddm").val()) ? "" : jQuery("#sjddm").val();
					var qssj = (!jQuery("#zxqssj").val()) ? "" : jQuery("#zxqssj").val();
					var jssj = (!jQuery("#zxjssj").val()) ? "" : jQuery("#zxjssj").val();
					zxParameter ={
				    	yyid:jQuery("#yyid").val(),
						zxrq:jQuery("#zxrq").val(),
						qssj:qssj,
						jssj:jssj,
						sjddm:sjddm,
						zxtell:jQuery("#zxtell").val(),
						//zxdz:jQuery("#zxdz").val(),
						zxdz:encodeURI(encodeURI(jQuery("#zxdz").val())),
						bz:encodeURI(encodeURI(jQuery("#bz").val())),
						apzxs:jQuery("[name=apzxs]:checked").val(),
						zxstatus: 1,//������ѯ��Ϣ����״̬��Ϊ1����ѯ
						//bz:jQuery("#bz").val(),
						//xspjzt:1//ѡ����ѯ��Ϣ��ѧ������״̬1������
						id:jQuery("#zxid").val()//�JԃID
					};
					
					showConfirm("ȷ�ϱ�����Ϣ��",{"okFun":function(){
						jQuery.ajaxSetup({async:false});
						//ԤԼ�ɹ�
						if(jQuery("#yystatus").val()=="2"){
							yyParameter={id:jQuery("#yyid").val(),status:jQuery("#yystatus").val()}; //,zgh:jQuery("[name=apzxs]:checked").val()
							//ԤԼ״̬����
							jQuery.post(yyurl,yyParameter,function(result){
								if(result=="true"){
									if(jQuery("#zxid").val()!=""){

										//��ѯ��Ϣ��������ѯ��
										jQuery.post(zxUpurl,zxParameter,function(data){
											if(data == true){
														showAlert("����ɹ���",{},{"clkFun":function(){
															frameElement.api.opener.refreshForm("xlzx_yysqnew.do?method=yyfkManage");
															iFClose();
														}
													});
											}else{
												return showAlert("����ʧ�ܣ�");
											}
										},'json');
									}else{
										//��ѯ��Ϣ��������ѯ��
										jQuery.post(zxurl,zxParameter,function(data){
											if(data == true){
														showAlert("����ɹ���",{},{"clkFun":function(){
															frameElement.api.opener.refreshForm("xlzx_yysqnew.do?method=yyfkManage");
															iFClose();
														}
													});
											}else{
												return showAlert("����ʧ�ܣ�");
											}
										},'json');
									}
								}else{
									return showAlert("����ʧ�ܣ�");						
								}
							});

						//ԤԼʧ��
						}else if(jQuery("#yystatus").val()=="5"){
							//ԤԼʧ�ܣ���ɾ����ѯ��Ϣ
							jQuery.post("xlzx_zxyyclnew.do?method=delZxInfoByYyid",{yyid:jQuery("#yyid").val()},function(result){
												});
							yyParameter={id:jQuery("#yyid").val(),status:jQuery("#yystatus").val(),yysbyy:jQuery("#yysbyy").val()};
							//ԤԼ״̬����
							jQuery.post(yyurl,yyParameter,function(data){
								if(data == "true"){
									showAlert("����ɹ���",{},{"clkFun":function(){
										frameElement.api.opener.refreshForm("xlzx_yysqnew.do?method=yyfkManage");
										iFClose();
									}});
								}else{
									return showAlert("����ʧ�ܣ�");						
								}
							});
						}
						jQuery.ajaxSetup({async:true});
					}});
				}

				//��ѯ����
				if(jQuery("#doType").val()=='zxfk'){
					
					zxParameter={};
					meetFlag();
					if(jQuery("#zxzt").val()==""||jQuery("#zxzt").val()=="1"){
						return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
					}
					//��ѯ״̬ȡ���ж�
					if(jQuery("#xspjzt").val()=="2"){
						return showAlert("����ѯѧ���Ѿ����ۡ��޷��޸���ѯ������");
					}

					zxParameter["zxstatus"]=jQuery("#zxzt").val();//1����ѯ2����ѯ3δ��ѯ
					if(jQuery("#zxzt").val()=="2"){
						zxParameter["xspjzt"]=1;
					}
					
					zxParameter["yyid"]=jQuery("#yyid").val();
					//zxParameter["zxsfk"]=jQuery("#zxsfk").val();
				
					zxurl = "xlzx_zxyyclnew.do?method=updateXlzxInfo";
					
					showConfirm("ȷ�ϱ�����Ϣ��",{"okFun":function(){
						jQuery.ajaxSetup({async:false});
							//��ѯ��Ϣ��������ѯ��	
							jQuery.post(zxurl,zxParameter,function(data){
								if(data == true){
											showAlert("����ɹ���",{},{"clkFun":function(){
												frameElement.api.opener.refreshForm("xlzx_yysqnew.do?method=yyfkManage");
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
		}

		function showNewJdrs(){
			var flag = false;
			var xh = jQuery("#xh").val();
			var id = jQuery("#id").val();
			var date = jQuery("#zxrq").val();
			//var pbdate = jQuery("#pbdate").val();
			jQuery.ajaxSetup({async:false});
			jQuery.post("xlzx_zxspb.do?method=getOkZxsList&xh="+xh+"&t="+Math.random(),{pbdate:date,id:id},function(data){
				jQuery("#zxsShowTd").html("");
				if(data["message"]){
					showAlert(data["message"],{},{"clkFun":function(){
					}});
				}else{
		  			var zxsxx = '<div style="overflow-y: scroll; height: 120px;">';
		  			jQuery.each( data , function(i, node){
						zxsxx += '<div style="margin: 5px 0px 5px 0px;">';
						zxsxx += '<input type="radio" name="apzxs" value="'+node.zgh+'" onclick="checkZxs(this)" ';
						var zgh = jQuery("#zgh").val();
						if(zgh == node.zgh){
							zxsxx += ' checked="checked" ';
						}
						zxsxx += ' >';
						zxsxx += '<span onclick="jQuery(this).parent().find(\':radio\').attr(\'checked\',true);checkZxs(this);" style="">';
						zxsxx += node.xm+'&nbsp;['+node.xb+']'+ ((node.bmmc == null)?'':('['+node.bmmc+']')) +'['+node.zgh+']';
						zxsxx += '<font color="#0000ff">['+node.kjdrsms+']</font><font color="#ff0000">[��ԤԼ'+node.yaprs+'��]</font>';
						if('${pbfs}' == '2' && '${xxdm}' == '10026'){
							zxsxx += "<font color='blue'>["+node.xqmc+"]</font>";
						}
						zxsxx += '</span>';
						zxsxx += '<span class="lxdh" style="display: none;">'+node.lxdh +'</span>';
						zxsxx += '<span class="address" style="display: none;">'+node.address +'</span>';
						zxsxx += '<span class="kjdrs" style="display: none;">'+node.kjdrs +'</span>';
						zxsxx += '<span class="yaprs" style="display: none;">'+node.yaprs +'</span>';
						zxsxx += '</div>';
		  			});
					zxsxx += '</div>';
		  			jQuery("#zxsShowTd").html(zxsxx);
				}
				var zxsChecked = jQuery("[name=apzxs]:checked").val();
				if(zxsChecked){
					jQuery("[name=apzxs]:checked").click();
				}else{
					jQuery("#zxtell").val("");
					jQuery("#zxdz").val("");
				}
				//jQuery("#zxrq").val(pbdate);
			},'json');
			jQuery.ajaxSetup({async:true});
		}
		function checkZxs(node){
			var lxdh = jQuery(node).parent().find(".lxdh").html();
			var address = jQuery(node).parent().find(".address").html();
			jQuery("#zxtell").val(lxdh=="null"?"":lxdh);
			jQuery("#zxdz").val(address=="null"?"":address);
			jQuery("#cnt").val(parseInt(jQuery("#cnt").val())+1);
			if('${pbfs}' == '2' && jQuery("#cnt").val() > 1 ){
				changeSjdSelect();
			}
		}

		function getYjdrs(){
			var count=0;
			var zgh = jQuery("#zgh").val();
		    var date =jQuery("#pbdate").val();
			var url = "xlzx_yysqnew.do?method=getZxsYjdrsByDate&zgh="+zgh+"&date="+date;
			jQuery.ajaxSetup({async:false});
			jQuery.post(url,{},function(data){
				count = data;
			});
			jQuery.ajaxSetup({async:true});
			return count;
		}


		function addYyInfo(zgh){
			var pbdate = jQuery("#pbdate").val();
			showDialog("ԤԼ��Ϣ",640,260,"xlzx_yysqnew.do?method=addyyzxInfo&yyzxrq="+pbdate+"&zgh="+zgh);
		}

		function disabledView(){
			
			if(jQuery("#doType").val()=="view"){
				jQuery("input,select,text,textarea").each(function(){
					jQuery(this).attr("readOnly","true");
				});
			}
		}


		function init(){
			sfAgree();
			
			//jQuery("input[type='radio'][name='meet'][value='"+jQuery("#status").val()+"']").attr("checked",true);
			if(jQuery("#status").val()=="2"){
				//jQuery("input[type='radio'][name='meet']").attr("disabled",true);
			}
			meetFlag();
			if(jQuery("#doType").val()=="view"){

				jQuery("#btx").hide();
				jQuery("#buttonSave").hide();
			}else{
				<logic:equal name ="doType" value="yyfk">
				showNewJdrs();
				</logic:equal>
			}
		}

		function sfAgree(){
			if(jQuery("#status").val()=="2"){
				//jQuery("tr[name=yyfkId]").show();
				jQuery("tr[name=yysbyytr]").hide();		
			}
			else if(jQuery("#status").val()=="5"){
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

		function meetFlag(){
			if(jQuery("input[type='radio'][name='zxzt'][value='1']").prop("checked")==true){
				jQuery("#zxzt").val("1");
			}else if(jQuery("input[type='radio'][name='zxzt'][value='2']").prop("checked")==true){
				jQuery("#zxzt").val("2");
			}else if(jQuery("input[type='radio'][name='zxzt'][value='3']").prop("checked")==true){
				jQuery("#zxzt").val("3");
			}
		}

		//��д��ѯ���ڴ����¼�
		function changezxrq(){
			showNewJdrs();
			if('${pbfs}' == '2' ){
				changeSjdSelect();
			}
		}

		//�ı�ʱ���������optionȡֵ
		function changeSjdSelect(){
			var date = jQuery("#zxrq").val();
			var xh = jQuery("#xh").val();
			var zgh = jQuery("[name='apzxs']:checked").val();
			var json = {date:date,xh:xh,zgh:zgh};
			var url = "xlzx_zxspb.do?method=changeSjdSelect";
			jQuery.ajax({
				type:'post',
				url:url,
				dataType:'json',
				contentType:"application/x-www-form-urlencoded; charset=UTF-8",
				data:json,
				async: false,
				success:function(data){
					jQuery("#sjddm").empty();
					var optionhtml = "<option></option>";
					if(data.length > 0){
						jQuery(data).each(function(i,o){
							optionhtml += "<option value='"+o.sjddm+"'>"+o.sjdmc+"</option>";
						})
					}
					jQuery("#sjddm").html(optionhtml);
					jQuery("#sjddm").val("");
				}
			});
		}

		function showqtxx(obj){
			var className = jQuery(obj).attr("class");
			var newClass = className == "up" ? "down" : "up";

			jQuery(obj).attr("class",newClass);
			jQuery("#qtxx").toggle();
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
			<input type="hidden" id="cnt" value="0"/>
			<input type="hidden" name="kjdrs" id="kjdrs" value="${yyzxInfo.kjdrs}" />
			<div style='width:100%;height:405px;overflow-y:auto;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr >
							<th colspan="4">
								<span>ѧ����Ϣ</span>
							</th>
						</tr>
					</thead>
						<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr >
							<th colspan="4">
								<span>ѧ����д��Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xlzx/yysq/viewXstxxx.jsp" %>
				</table>
				<%--�������Щ��Ǳ���ģ�����ѡ��һ��Ϊ���򶼲���--%>
				<logic:notEmpty  name="yyzxInfo" property="qxztztmc">
					<table width="100%" border="0" class="formlist" id="yytxxx_tb">
						<thead>
						<tr>
							<th colspan="2">
								<span>ԤԼ��д��Ϣ</span>
							</th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<th rowspan="3" width="20%">һ�ܵ�����״̬</th>
							<td width="80%">
								���壺${yyzxInfo.qxztztmc}
							</td>
						</tr>
						<tr>
							<td>
								���ǣ�${yyzxInfo.qxztjlmc}
							</td>
						</tr>
						<tr>
							<td>
								������${yyzxInfo.qxztyymc}
							</td>
						</tr>
						<tr>
							<th >�ϴ���ѯ��ĸı�</th>
							<td>
									${yyzxInfo.sczxhgbmc}
							</td>
						</tr>
						<tr>
							<th >���Լ������״̬</th>
							<td>
									${yyzxInfo.zjztmc}
							</td>
						</tr>
						<tr>
							<th>
								������ѯ������
							</th>
							<td>
									${yyzxInfo.bczxwt}
							</td>
						</tr>
						<tr>
							<th>
								�ϴ���ѯ�����������״̬����Ṧ��
							</th>
							<td>
									${yyzxInfo.zxhzt}
							</td>
						</tr>
						</tbody>
					</table>
				</logic:notEmpty>
				<table width="100%" border="0" class="formlist">
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
									<logic:equal name ="doType" value="yyfk">
										<input type="radio"  name="sfty"  value="1" id="yyfk_yycg"
										<logic:notEqual name ="yyzxInfo" property="status"  value="5">
											checked="true"
										</logic:notEqual>
											onclick="sfAgree()"/><label style='cursor:pointer' for="yyfk_yycg">ԤԼ�ɹ�</label>
										<input type="radio"  name="sfty"  value="2" id="yyfk_yysb"
										<logic:equal name ="yyzxInfo" property="status"  value="5">
											checked="true"
										</logic:equal>
										onclick="sfAgree()"/><label style='cursor:pointer' for="yyfk_yysb">ԤԼʧ��</label>
									</logic:equal>
									<logic:notEqual name ="doType" value="yyfk">
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
									</logic:notEqual>
									
								</td>
								
							</tr>
							<tr style="height:10px" name="yyfkId">
								<th width="16%">
									<logic:equal name ="doType" value="yyfk"><span class="red">*</span></logic:equal>��ѯ��������
								</th>
								<td  width="34%" >
									<logic:equal name ="doType" value="yyfk">
										<input type="text" id="zxrq"  name="zxrq" 
										<logic:notEqual name ="yyzxInfo" property="zxrq"  value="">
											value="${yyzxInfo.zxrq}"
										</logic:notEqual>
										<logic:equal name ="yyzxInfo" property="zxrq"  value="">
											value="${yyzxInfo.yyzxrq}"
										</logic:equal> onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'${today}'})"  onchange="changezxrq();"/>
									</logic:equal>
									
									<logic:notEqual name ="doType" value="yyfk">
										<logic:notEqual name ="yyzxInfo" property="status" value="1">
											<span class="red"><B>${yyzxInfo.zxrq}</B></span>
										</logic:notEqual>
										<logic:equal name ="yyzxInfo" property="status" value="1">
											<logic:equal name ="doType"  value="view">
												${yyzxInfo.zxrq}
											</logic:equal>
											<logic:notEqual name ="doType"  value="view">
												<html:text property="zxrq" styleId="zxrq"  value="${yyzxInfo.yyzxrq}"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  onchange="changezxrq();"/>
											</logic:notEqual>
										</logic:equal>
									</logic:notEqual>
								</td>
								<th  width="16%">
									<logic:equal name ="doType" value="yyfk"><logic:equal value="2" name="pbfs"><span class="red">*</span></logic:equal></logic:equal>��ѯʱ��
								</th>
								<td  width="34%" >
									<logic:equal name ="doType" value="yyfk">
											<logic:equal value="2" name="pbfs">
												
												<logic:notEmpty name="yyzxInfo" property="sjddmzx" >
													<html:select styleId="sjddm" property="sjddm"  value="${yyzxInfo.sjddmzx}">
														<html:option value=""></html:option>
														<html:options collection="sjddmList" labelProperty="sjdmc" property="sjddm"/>
											        </html:select>
												</logic:notEmpty>
												<logic:empty name="yyzxInfo" property="sjddmzx" >
													<html:select styleId="sjddm" property="sjddm"  value="${yyzxInfo.sjddm}">
													        <html:option value=""></html:option>
															<html:options collection="sjddmList" labelProperty="sjdmc" property="sjddm"/>
												     </html:select>
												</logic:empty>
											</logic:equal>
											<logic:notEqual value="2" name="pbfs">
												 <input type="text" id="zxqssj"  name="zxqssj"  style="width:30%"  
												<logic:notEqual name ="yyzxInfo" property="zxqssj"  value="">
													value="${yyzxInfo.zxqssj}"
												</logic:notEqual>
												<logic:equal name ="yyzxInfo" property="zxqssj"  value="">
													value="${yyzxInfo.yyqssj}"
													</logic:equal>
												 onfocus="WdatePicker({dateFmt:'HH:mm'})" />&nbsp;��&nbsp;
											    <input type="text" id="zxjssj"  name="zxjssj" style="width:30%" 
												<logic:notEqual name ="yyzxInfo" property="zxjssj"  value="">
													value="${yyzxInfo.zxjssj}"
												</logic:notEqual>
												<logic:equal name ="yyzxInfo" property="zxjssj"  value="">
													value="${yyzxInfo.yyjssj}"
													</logic:equal>					
												 onfocus="WdatePicker({dateFmt:'HH:mm'})" />
											</logic:notEqual>
										  
									</logic:equal>
									<logic:notEqual name ="doType" value="yyfk">
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
										<logic:equal value="2" name="pbfs">
											${yyzxInfo.sjdmczx}
										</logic:equal>
									</logic:notEqual>
								</td>
							</tr>
							
							<tr>
								<logic:equal name ="doType" value="yyfk">
									<logic:equal name ="doType"  value="view">
										<th>
											��ѯʦ
										</th>
										<td colspan="3">
											 <a href = 'javascript:void(0);' style="color:#0f5dc2;text-decoration:underline;" onclick="showDialog('�鿴��ѯʦ��Ϣ' , 750 , 410 , 'xlzx_zxs.do?method=zxsglDetail&doType=view&zgh=${yyzxInfo.apzxs}')" >${yyzxInfo.apzxsxm}</a>
										</td>
									</logic:equal>
									<logic:notEqual name ="doType"  value="view">
										<th>
											<span class="red">*</span>��ѯʦ
										</th>
										<td colspan="3" id="zxsShowTd">
											 
										</td>
									</logic:notEqual>
								</logic:equal>
								<logic:notEqual name ="doType" value="yyfk">
									<th>
										��ѯʦ
									</th>
									<td colspan="3">
										 <a href = 'javascript:void(0);' style="color:#0f5dc2;text-decoration:underline;" onclick="showDialog('�鿴��ѯʦ��Ϣ' , 750 , 410 , 'xlzx_zxs.do?method=zxsglDetail&doType=view&zgh=${yyzxInfo.apzxs}')" >${yyzxInfo.apzxsxm}</a>
									</td>
								</logic:notEqual>
							</tr>
							
							<tr style="height:10px" name="yyfkId">
								<th  width="16%">
									��ϵ�绰
								</th>
								<td  width="34%" colspan="3">
									<logic:equal name ="doType" value="yyfk">
										<input type="text" id="zxtell"  name="zxtell"  
											<logic:notEqual name ="yyzxInfo" property="zxtell"  value="">
												value="${yyzxInfo.zxtell}"
											</logic:notEqual>
											<logic:equal name ="yyzxInfo" property="zxtell"  value="">
												value="${yyzxInfo.lxdh}"
												</logic:equal>
											maxlength="11" onkeyup="checkInputData(this);"/>
									</logic:equal>
									<logic:notEqual name ="doType" value="yyfk">
										<logic:notEqual name ="yyzxInfo" property="status" value="1">
											${yyzxInfo.zxtell}
										</logic:notEqual>
										<logic:equal name ="yyzxInfo" property="status" value="1">
											<logic:equal name ="doType"  value="view">
												${yyzxInfo.zxtell}
											</logic:equal>
											<logic:notEqual name ="doType"  value="view">
												<html:text property="zxtell" styleId="zxtell"  value="${yyzxInfo.lxdh}"  maxlength="11" onkeyup="checkInputData(this);"/>
											</logic:notEqual>
										</logic:equal>
									</logic:notEqual>
								</td>
							</tr>
							<tr style="height:10px" name="yyfkId" >
								<th  width="16%">
									��ѯ��ַ
								</th>
								<td  width="34%" colspan="3">
									<logic:equal name ="doType" value="yyfk">
										<input type="text" id="zxdz"  name="zxdz" style="width:400px" maxlength="50"
											<logic:notEqual name ="yyzxInfo" property="zxdz"  value="">
												value="${yyzxInfo.zxdz}"
											</logic:notEqual>
											<logic:equal name ="yyzxInfo" property="zxdz"  value="">
												value="${yyzxInfo.address}"
												</logic:equal>
										  />
									</logic:equal>
									<logic:notEqual name ="doType" value="yyfk">
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
									</logic:notEqual>
								</td>
							</tr>
							<tr style="height:10px" name="yyfkId">
								<th  width="16%">
									��ע
									<logic:equal name ="doType" value="yyfk"><br/><font color="red"><B>(��500��)</B></font></logic:equal>
									<logic:notEqual name ="doType" value="yyfk"><logic:equal name ="yyzxInfo" property="status" value="1"><logic:notEqual name="doType" value="view"><br/><font color="red"><B>(��500��)</B></font></logic:notEqual></logic:equal></logic:notEqual>
								</th>
								<td  width="34%" colspan="3">
									<logic:equal name ="doType" value="yyfk">
										<html:textarea  property='bz' styleId="bz" 												
											value="${yyzxInfo.zxbz}" style="word-break:break-all;width:99%" onblur="chLeng(this,500);" rows='4' />
									</logic:equal>
									<logic:notEqual name ="doType" value="yyfk">
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
									</logic:notEqual>
								</td>
							</tr>
							<tr style="height:10px" name="yysbyytr">
								<th  width="16%">
									<logic:equal name ="doType" value="yyfk"><span class="red">*</span></logic:equal>ԤԼʧ��ԭ��
									<logic:equal name ="doType" value="yyfk"><br><font color="red"><B>(��500��)</B></font></logic:equal>
									<logic:notEqual name ="doType" value="yyfk"><logic:equal name ="yyzxInfo" property="status" value="1"><logic:notEqual name ="doType"  value="view"><br><font color="red"><B>(��500��)</B></font></logic:notEqual></logic:equal></logic:notEqual>
								</th>
								<td  width="34%" colspan="3">
									<logic:equal name ="doType" value="yyfk">
										<html:textarea  property='yysbyy' styleId="yysbyy" value="${yyzxInfo.yysbyy}" style="word-break:break-all;width:99%" onblur="chLeng(this,500);" rows='4' />
									</logic:equal>
									<logic:notEqual name ="doType" value="yyfk">
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
									</logic:notEqual>
								</td>
							</tr>
							
					</tbody>
						<thead name = "zxxgInfo">
							<tr >
								<th colspan="4">
									<span>��ѯ�����Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody id="zxInfo" name = "zxxgInfo">
								<tr style="height:10px">
									<th  width="16%">
										<logic:equal name ="doType" value="zxfk"><span class="red">*</span></logic:equal>��ѯ״̬
									</th>
									<td  width="34%" colspan="3">
										<input type="hidden" name="xspjzt" id="xspjzt" value="${yyzxInfo.xspjzt}">
										<input type="hidden" name="zxstatus" id="zxstatus" value="${yyzxInfo.zxzt}">
										<input type="hidden" name="zxzt" id="zxzt" value="">
										<logic:equal name ="doType" value="zxfk">
											<html:radio name="yyzxInfo" property="zxzt" value="2"/>����ѯ
											<html:radio name="yyzxInfo" property="zxzt" value="3"/>δ��ѯ</logic:equal>
										<logic:notEqual name ="doType" value="zxfk">
											${yyzxInfo.zxztmc}
										</logic:notEqual>
									</td>
								</tr>
								<tr style="height:40px">
									<th  width="16%">
										ѧ����ѯ����
									</th>
									<td  width="34%" colspan="3">
											${yyzxInfo.xspj}
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

