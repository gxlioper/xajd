<%@ page language="java" contentType="text/html; charset=GBK"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script language="javascript" src="js/xsgzgl/szdw/dwwh.js"></script>
		<script language="javascript">
		/**
		 * ���������
		 */
		function showAlertDivLayer() {
			var argumentsArr = Array.prototype.slice.call(arguments);
			if(argumentsArr[0] == null) return;
			
			var clickFun = null;
			
			if (argumentsArr.length == 3){
				clickFun = argumentsArr[2]["clkFun"];
			}
			ymPrompt.alert({
				title:"ϵͳ��ʾ",
				useSlide:true,
				maskAlphaColor:"#FFFFFF",
				maskAlpha:0.3,
				message:argumentsArr[0],
				width:340,
				winPos:[180,160],
				height:160,
				//showMask:false,
				handler:clickFun
			});
			//setTimeout(function(){ymPrompt.doHandler();},3000);
		}
			jQuery(function(){
			
			//jQuery("img").attr('src',"/xgxt/teaPic.jsp?zgh=test");
				jQuery("#zhaopian").attr('src','<%=request.getContextPath() %>/teaPic.jsp?zgh=${model.zgh}&t='+new Date());
				// jQuery("#age").val( calculateAges(jQuery("#csrq").val().substr(0,4)+'-'+jQuery("#csrq").val().substr(4,2)+'-'+jQuery("#csrq").val().substr(6,2)));
			});
			
			//��ʾ�ϴ���Ƭ
			function showZpscDiv(){

				var zgh = jQuery("#zgh").val();
				
				if(zgh == ""){
					alertError("������дְ���ţ�");
				}else{
					tipsWindown("�ϴ���Ƭ","id:addPic","380","130","true","","true","id");
					//tipsWindownNew("�ϴ���Ƭ","id:addPic",380,130);
				}
			}
			//�ϴ���Ƭ
			function uploadTeaPic(){
				jQuery.ajaxSetup({async:false,dataType:'text'});
				
				var zgh = jQuery("#zgh").val();
				jQuery.ajaxFileUpload({
					  url:'szdw_teaInfo.do?method=uploadTeaPic&zgh='+zgh,//�������˳���
					  secureuri:false,
					  fileElementId:'teaPic',//input���ID
					  success:function(data,type){
						if (type=='success'){
							jQuery("#jszp").attr("src","szdw_teaInfo.do?method=showTeaPic&zgh="+zgh+"&tt="+new Date());
							jQuery("#zhaopian").attr("src","szdw_teaInfo.do?method=showTeaPic&zgh="+zgh+"&tt="+new Date());
							alertInfo(data);
						}
					  }
					});

				jQuery.ajaxSetup({async:true});
			}
			function calAge(){
				jQuery("#age").val( calculateAges(jQuery("#csrq").val().substr(0,4)+'-'+jQuery("#csrq").val().substr(4,2)+'-'+jQuery("#csrq").val().substr(6,2)));
			}
		</script>
	</head>
	<body>
		
		<html:form action="/data_search" method="post" styleId="dwwh_form">
			<%--<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title}</a>
				</p>
			</div>--%>

					<div class="tab" style="width:100%;height:420px;overflow-x:hidden;overflow-y:auto;">
							<table width="100%"  border="0" class="formlist">
							 <thead>
			    				<tr>
			        				<th colspan="5"><span>��������</span></th>
			        			</tr>
			   				 </thead>
			   				
			   				 <tbody>
								<tr>
									<th align="right" ><font color="red">*</font>����</th>
									<td align="left" style="width: 25%">
										<input type="hidden" />
										<html:hidden property="zgh" name="model" styleId="zgh"  />
										<html:text property="zgh" name="model" styleId="zgh" disabled="true" maxlength="20" ></html:text>
									</td>
									<th align="right"><font color="red">*</font>����</th>
									<td align="left" style="width: 25%">
										<html:text property="xm" name="model" styleId="xm" maxlength="20" ></html:text>
									</td>
									<th align="left" rowspan="5">
										<div align="center">
										<img src="" style="height:133px;width:100px;" border="0"   id="zhaopian"/>
										</div>
										<div align="center">
											<button type="button" onclick="showZpscDiv();" style="width:100px" id="buttonSave">�ϴ���Ƭ</button>
										</div>
									</th>
							</tr>
							<tr>
								<th align="right" width="15%">
									<font color="red">*</font>���ڲ���
								</th>
								<td align="left">
									<logic:equal value="true" name="flag">
										<html:select name='model'  property="bmdm" styleId="bmdm"  disabled="true" style="width:150px;" >
											<html:options collection="bmList" property="bmdm" labelProperty="bmmc"  />
										</html:select>
									</logic:equal>
									<logic:notEqual value="true" name="flag">
										<html:select name='model'  property="bmdm" styleId="bmdm" disabled="${xy}" style="width:150px;" >
											<html:options collection="bmList" property="bmdm" labelProperty="bmmc"  />
										</html:select>
									</logic:notEqual> 
								</td>
								
								<th align="right">
									<font color="red">*</font>�Ա�
								</th>
								<td align="left">
									<html:select property="xb" name="model" styleId="xb"  style="width:150px;">
										<html:option value="1">��</html:option>
										<html:option value="2">Ů</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								
								<th align="right">
									ְ��
								</th>
								<td align="left">
									<html:select name='model'  property="zc" styleId="zc"  style="width:150px;">
										<html:options collection="zcList" property="zcdm" labelProperty="zcmc" />
									</html:select>
								</td>
								<th align="right">
									������ò
								</th>
								<td align="left">
									<html:select name='model'  property="zzmm" styleId="zzmm"  style="width:150px;">
										<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th align="right">
									ְ��
								</th>
								<td align="left">
									<html:select name='model'  property="zw" styleId="zw"  style="width:150px;">
										<html:options collection="zwList" property="zwdm" labelProperty="zwmc" />
									</html:select>
								</td>
								<th align="right">
									��������
								</th>
								<td align="left">
									<html:text property="csrq" name="model" styleId="csrq" onblur="dateFormatChg(this);" onclick="return showCalendar('csrq','y-mm-dd');" readonly="true" style="cursor:hand " ></html:text>
								</td>
							</tr>
							<logic:equal value="10407" name="xxdm">
							<tr>
								
								<th align="right">
									רҵ����ְ��
								</th>
								<td align="left">
									<html:text property="kzzd6" name="model" styleId="kzzd6" maxlength="10" ></html:text>
								</td>
								<th align="right">
									�Կڹ���
								</th>
								<td align="left">
									<html:text property="kzzd7" name="model" styleId="kzzd7" maxlength="10" ></html:text>
								</td>
							</tr>
							</logic:equal>
							<tr>
								<th align="right">
									����
								</th>
								<td align="left" >
									<html:text property="age" name="model" styleId="age"  onclick="return calAge();" readonly="true" style="cursor:hand " ></html:text>
								</td>
								
								<th align="right">
									����
								</th>
								<td align="left">
									<html:select name='model'  property="mz" styleId="mz"  style="width:150px;">
										<html:options collection="mzList" property="mzdm" labelProperty="mzmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th align="right">
									ѧ��
								</th>
								<td align="left">
									<html:select property="xl" name="model"  styleId="xl"  style="width:150px;"> 
										<html:option value=""></html:option>
										<html:option value="��ר">��ר</html:option>
										<html:option value="����">����</html:option>
										<html:option value="�о���">�о���</html:option>
									</html:select>
								</td>
								
								<th align="right">
									ѧλ
								</th>
								<td align="left" colspan="2">
									<html:select property="xw" name="model"  styleId="xw" style="width:150px;"> 
										<html:option value=""></html:option>
										<html:option value="ѧʿ">ѧʿ</html:option>
										<html:option value="˶ʿ">˶ʿ</html:option>
										<html:option value="��ʿ">��ʿ</html:option>
										<html:option value="��ʿ��">��ʿ��</html:option>
									</html:select>
								</td>
								
							</tr>
							<tr>
								<th align="right">
									��ҵԺУ
								</th>
								<td align="left">
									<html:text property="byyx" name="model" styleId="byyx" maxlength="15" ></html:text>
								</td>
								<th align="right">
									���֤��
								</th>
								<td align="left" colspan="2">
									<html:text property="sfzh" name="model" styleId="sfzh" maxlength="18" ></html:text>
								</td>
							</tr>
							         <tr>
								<th align="right">
									��ѧרҵ
								</th>
								<td align="left">
									<html:text property="sxzy" name="model" styleId="sxzy" maxlength="15" ></html:text>
								</td>
								<th align="right">
									����
								</th>
								<td align="left" colspan="2">
										<html:select name='model'  property="jgxs" styleId="jgxs"  style="width:150px;">
											<html:option value=""></html:option>
											<html:options collection="ssList" property="ssdm" labelProperty="ssmc" />
										</html:select>
								</td>
							</tr>  
						    <tr>
								<th align="right">
									��У����ʱ��
								</th>
								<td align="left">
									<html:text property="lxgzsj" name="model" styleId="lxgzsj"  onblur="dateFormatChg(this)" onclick="return showCalendar('lxgzsj','y-mm-dd');" readonly="true" style="cursor:hand " ></html:text>
								</td>
								<th align="right">
									�о�����
								</th>
								<td align="left"  colspan="2">
									<html:text property="xsgzyjfx" name="model" styleId="xsgzyjfx" maxlength="20" ></html:text>
								</td>
								
							</tr>  
							<tr>
								<th align="right">
									��λ���
								</th>
								<td align="left">
									<html:select name='model'  property="gwlbdm" styleId="gwlbdm"  style="width:150px;">
										<html:options collection="gwlbList" property="gwlbdm" labelProperty="gwlbmc" />
									</html:select>
								</td>
								<th align="right">
									ְҵ����֤��
								</th>
								<td align="left"  colspan="2">
									<html:text property="zyjnzs" name="model" styleId="zyjnzs" maxlength="20" >
										
									</html:text>
								</td>
							</tr>
							<tr>
								<th align="right">
									���ʺ�
								</th>
								<td align="left">
									<html:text property="kzzd5" name="model" styleId="kzzd5" maxlength="20" ></html:text>
								</td>
								<th align="right">
									��λ���
								</th>
								<td align="left" colspan="2">
									<html:text property="dwlbdm" name="model" styleId="dwlbdm" maxlength="10" ></html:text>
								</td>
							</tr>
							<tr>
								<th align="right">
								          ��������
								</th>
								<td align="left">
									<html:text property="khyh" name="model" styleId="khyh" maxlength="20" ></html:text>
								</td>
								<th align="right">
									�����˺�
								</th>
								<td align="left"  colspan="2">
									<html:text property="yhzh" name="model" styleId="yhzh" maxlength="20" >
										
									</html:text>
								</td>
							</tr>
							<tr>
								<th align="right">
								         ����״��
								</th>
								<td align="left">
									<html:select name='model'  property="hyzk" styleId="hyzk"  style="width:150px;">
										<html:option value="�ѻ�">�ѻ�</html:option>
										<html:option value="δ��">δ��</html:option>
									</html:select>
								</td>
								<th align="right">
									����Աֵ����
								</th>
								<td align="left"  colspan="2">
									<html:text property="fdyzbs" name="model" styleId="fdyzbs" maxlength="20" >
									</html:text>
								</td>
							</tr>
						    <tr>
								<th align="right">
								         ��ʱ���θ���Ա
								</th>
								<td align="left">
									<html:text property="fdyrzrq" name="model" styleId="fdyrzrq" maxlength="20" onblur="dateFormatChg(this)" onclick="return showCalendar('fdyrzrq','y-mm-dd');" readonly="true" style="cursor:hand " >
									</html:text>
								</td>
								<th align="right">��ͥסַ</th>
								<td align="left"  colspan="2">
									<html:text property="jtzz" name="model" styleId="jtzz" maxlength="50" style="width:250px;"  ></html:text>
								</td>
							</tr>
							<tr>
								<th align="right">��Ҫְ��
								<br/><font color="red">��200����</font>
								</th>
								<td align="left" colspan="4">
									<html:textarea property="zyzz" name="model" styleId="zyzz" rows="3" cols="80" ></html:textarea>
									
								</td>
							</tr>
							<tr>
								<th align="right">�����
								<br/><font color="red">��2000����</font>
								</th>
								<td align="left" colspan="4">
									<html:textarea property="grhjqk" name="model" styleId="grhjqk" rows="3" cols="80" ></html:textarea>
								</td>
							</tr>
							<thead>
			    				<tr>
			        				<th colspan="5"><span>��ϵ��ʽ</span></th>
			        			</tr>
			   				 </thead>
							<tr>
								<th align="right">
									��ϵ�绰
								</th>
								<td align="left">
									<html:text property="lxdh" name="model" styleId="lxdh" maxlength="15" ></html:text>
								</td>
								<th align="right">
									�ƶ��绰
								</th>
								<td align="left" colspan="2">
									<html:text property="yddh" name="model" onkeyup="checkInputData(this)" styleId="yddh" maxlength="15" ></html:text>
								</td>
							</tr>
							<tr>
								<th align="right">
									�칫�绰
								</th>
								<td align="left">
									<html:text property="bgdh" name="model" onblur="checkPhone(this)" styleId="bgdh" maxlength="15" ></html:text>
								</td>
								<th align="right">
									����
								</th>
								<td align="left" colspan="2">
									<html:text property="cz" name="model" styleId="cz" maxlength="20" ></html:text>
								</td>
							</tr>
							<tr>
								<th align="right">
									��������
								</th>
								<td align="left">
									<html:text property="dzyx" name="model" styleId="dzyx" maxlength="25" ></html:text>
								</td>
								<th align="right">
									QQ
								</th>
								<td align="left" colspan="2">
									<html:text property="kzzd3" name="model" onkeyup="value=value.replace(/[^\d]/g,'') " onblur="value=value.replace(/[^\d]/g,'')" styleId="kzzd3" maxlength="15" ></html:text>
								</td>
								
							</tr>
							
							<tr>
								<th align="right">
									΢����
								</th>
								<td align="left">
									<html:text property="kzzd1" name="model" styleId="kzzd1" maxlength="25" ></html:text>
								</td>
								<th align="right">
									΢����
								</th>
								<td align="left" colspan="4">
									<html:text property="kzzd2" name="model" styleId="kzzd2" maxlength="25" ></html:text>
								</td>
							</tr>
							<tr>
								<th align="right">
									�칫�ص�
								</th>
								<td align="left" colspan="4">
									<html:text property="bgdd" name="model" styleId="bgdd" maxlength="25" style="width:575px;" ></html:text>
								</td>
							</tr>
							<tr>
								
								<th align="right">
									ͨѶ��ַ
								</th>
								<td align="left" colspan="4">
									<html:text property="txdz" name="model" styleId="txdz" maxlength="25" style="width:575px;" ></html:text>
								</td>
							</tr>
							
							<thead>
			    				<tr>
			        				<th colspan="5"><span>��������</span></th>
			        			</tr>
			   				 </thead>
							<tr>
								<th align="right">
									�μӹ�������
								</th>
								<td align="left">
									<html:text property="cjgzrq" name="model" styleId="cjgzrq"  onblur="dateFormatChg(this)" onclick="return showCalendar('cjgzrq','y-mm-dd');" readonly="true" style="cursor:hand " ></html:text>
								</td>
								<th align="right">
									˼������ʱ��
								</th>
								<td align="left" colspan="2">
									<html:text property="szgzsj" name="model" styleId="szgzsj"  onblur="dateFormatChg(this)" onclick="return showCalendar('szgzsj','y-mm-dd');" readonly="true" style="cursor:hand " ></html:text>
								</td>
							</tr>
							<tr>
								<th align="right">
									��������
									<br/><font color="red">��2000����</font>
								</th>
								<td align="left"  colspan="4">
									<html:textarea property="gzjl" name="model" styleId="gzjl" rows="3" cols="80" ></html:textarea>
								</td>
							</tr>
							<thead>
			    				<tr>
			        				<th colspan="5"><span>����</span></th>
			        			</tr>
			   				 </thead>
							<tr>
								<th align="right">
									��������
									<br/><font color="red">��2000����</font>
								</th>
								<td align="left"  colspan="4">
									<html:textarea property="kzzd4" name="model" styleId="kzzd4" rows="3" cols="80" ></html:textarea>
								</td>
							</tr>
							<tr>
								<th align="right">
									��������
									<br/><font color="red">��2000����</font>
								</th>
								<td align="left"  colspan="4">
									<html:textarea property="fblw" name="model" styleId="fblw" rows="3" cols="80" ></html:textarea>
								</td>
							</tr>
							<tr>
								<th align="right">
									�μ���ѵ
									<br/><font color="red">��300����</font>
								</th>
								<td align="left"  colspan="4">
									<html:textarea property="pxqk" name="model" styleId="pxqk" rows="3" cols="80" ></html:textarea>
								</td>
							</tr>
							<tr>
								<th align="right">
									��ע
									<br/><font color="red">��2000����</font>
								</th>
								<td align="left"  colspan="4">
									<html:textarea property="bz" name="model" styleId="bz" rows="3" cols="80" ></html:textarea>
								</td>
							</tr>
							</tbody><%--
							 <tfoot>
						      <tr>
						        <td colspan="5"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
						          <div class="btn">
									<button type="button" name="����" onclick="updateDwwh('update');" id="buttonSave">
										�� ��
									</button>
									<logic:empty name="flag">
									<button type="button" name="�ر�" onclick="Close();return false;" id="buttonClose">�� ��</button>
									</logic:empty>
												           
						          </div>
						          </td>
						      </tr>
						    </tfoot>
						--%></table>
						</div>
						
			<!-- ���ƽ�ʦ��Ϣ - �ϴ���Ƭ -->
				<!-- �ϴ���Ƭ -->
			<div id="addPic" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�ϴ���Ƭ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<input type="file" id="teaPic" name="teaPic" style="width:90%"
										   onchange='uploadTeaPic();closeWindown();'
									/>
									<br/><html:hidden property="zgh" name="model" styleId="zgh"  />
									<span style="color:red">ע�����ϴ�jpg��gif��png��bmp ��ʽ���ļ����� 1 M ��</span>
								</td>
							</tr>
						</tbody>
				</table>
				</div>
			</div>
						<table width="100%"  border="0" class="formlist">
				<tfoot>
			      <tr>
			        <td colspan="5"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
						<button type="button" name="����" onclick="updateDwwh('update');" id="buttonSave">
							�� ��
						</button>
						<logic:empty name="flag">
						<button type="button" name="�ر�" onclick="Close();return false;" id="buttonClose">�� ��</button>
						</logic:empty>
									           
			          </div>
			          </td>
			      </tr>
			    </tfoot>
			 </table>
	</html:form>
	</body>
</html>
