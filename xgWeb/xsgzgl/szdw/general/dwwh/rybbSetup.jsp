<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� --> 
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/szdw/dwwh.js"></script>
		<script language="javascript" defer="defer">
		
		//��ʼ��
		function onShow(zgh){ 
		
			jQuery.ajaxSetup({async: false});
			
			createJzgOption(zgh);
			searchRs();
			titleNjxyzybj();
			
			jQuery.ajaxSetup({async: true});
		}
		
		//������ְ��Option
		function createJzgOption(zgh){ 
		
			var url = "szdw_dwwh.do?method=createJzgOption";			
			var parameter={
				"str_zgh":jQuery("#text_zgh").val(),
				"str_xm":escape(jQuery("#text_xm").val()),
				"str_bmdm":jQuery("#select_bmdm").val(),
				"str_dbqk":jQuery("#select_dbqk").val(),
				"str_lx":jQuery("#hidden_lx").val()
			};
			
			jQuery.ajaxSetup({async:false});
			
			jQuery.post(url,
				parameter,
				function(result){
					jQuery("#select_jzg").html("");
					jQuery("#select_jzg").append(result);
					
					if(zgh != "" && zgh != null){
						jQuery("#select_jzg").val(zgh)
					}
				}
			);
		
			jQuery.ajaxSetup({async:true});
		}
		
		//��ѯ
		function searchRs(){
			
			var zgh = jQuery("#select_jzg").val();
			
			if(zgh == "" || zgh == null){
				alertError("��������ְ���б���ѡ��һλϣ��������ʦ");
				return false;
			}
			
			jQuery.ajaxSetup({async:false});
			
			var url = "szdw_dwwh.do?method=searchSetup";
			
			var parameter={
				"str_zgh":jQuery("#select_jzg").val(),
				"str_njV":jQuery("#njV").val(),
				"str_xyV":jQuery("#xy").val(),
				"str_zyV":jQuery("#zyV").val(),
				"str_bjV":jQuery("#bjV").val(),
				"str_ywjs":jQuery("#select_ywjs").val(),
				"str_brdb":jQuery("#select_brdb").val(),
				"str_lx":jQuery("#hidden_lx").val()
			};
		
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			// ��ѯ����
			searchGo(url,parameter);
		
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}
		
		// ִ�в�ѯ����
		function searchGo(url,parameter){
			
			// ��ҳ
			var currentPage = "1";
			if($("currentPage")){
				currentPage = $("currentPage").value;
			}
			
			var editPageSize = "";
			if($("editPageSize")){
				editPageSize = $("editPageSize").value;	
			}
				
			var pagesize = "";
			if($("pagesize")){
				pagesize = $("pagesize").value;
			}
			
			parameter["currentPage"]=currentPage;
			parameter["editPageSize"]=editPageSize;
			parameter["pagesize"]=pagesize;
		
		 	$("divWaiting").style.display="";
			$("divDisable").style.display="";

			jQuery("#div_rs").load(url,parameter,function(){
				setTimeout("setPageInfo()",500);
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				
				jQuery("#table_rs td").each(function(){
						// �и߿���
						jQuery(this).attr("height","10");
						jQuery(this).css("text-align","center");
				});

				if($("span_note")){
					var html="";
					// ��������
					var hid_lx=$("hidden_lx").value;
					var hid_dbs="0";
					
					if($("hid_dbs").value!=""){
						hid_dbs=$("hid_dbs").value;
					}	
					
					if(hid_lx=="fdy"){
						if(jQuery("#xxdm").val()=='12834'){
							html+="����ӳ������༶<font color='blue'>"+hid_dbs+"</font>��,��<font color='blue'>��ɫ</font>�ļ�¼Ϊ�Ѿ�����༶��"
						}else{
							html+="������Ա�����༶<font color='blue'>"+hid_dbs+"</font>��,��<font color='blue'>��ɫ</font>�ļ�¼Ϊ�Ѿ�����༶��"
						}
					}else if(hid_lx=="bzr"){
					if(jQuery("#xxdm").val()=='12834'){
							html+="���жӳ������༶<font color='blue'>"+hid_dbs+"</font>��,��<font color='blue'>��ɫ</font>�ļ�¼Ϊ�Ѿ�����༶��"	
						}else{
							html+="�������ι����༶<font color='blue'>"+hid_dbs+"</font>��,��<font color='blue'>��ɫ</font>�ļ�¼Ϊ�Ѿ�����༶��"	
						}
					}
					
					$("span_note").innerHTML = "��ѯ���&nbsp;&nbsp;"+html;
				}
			});
		}
		
		function resetFdytj(){
			
			jQuery("input,select",jQuery("#tab_fdy")).each(function(){
				
				if(jQuery(this).attr("disabled")!=true
					&& jQuery(this).css("display")!="none"){
					jQuery(this).val("");
				}
			});
		}
		
		function resetBbtj(){
			
			jQuery("input,select",jQuery("#tab_bbxx")).each(function(){
				
				if(jQuery(this).attr("disabled")!=true
					&& jQuery(this).css("display")!="none"){
					jQuery(this).val("");
				}
			});
		}
		
		
		function titleLoad(id){
			
			if(jQuery("#"+id)){
			
				jQuery("#"+id).children("option").each(function(){
					jQuery(this).attr("title",jQuery(this).text());
				});
			}
		}
		
		function titleNjxyzybj(){
		
			titleLoad('nj');
			setTimeout("titleLoad('xy')",500);
			setTimeout("titleLoad('zy')",500);
			setTimeout("titleLoad('bj')",500);
		}
		</script>
	</head>
	<body onload="onShow('')" ondrag="return false">
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p><%--
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
			</p>
		--%></div>
		<!-- ���� end-->
		
		<html:form action="/general_szdw" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="hidden_lx" value="${lx }"/>
			<input type="hidden" id="hidden_zgh" value="${zgh }"/>
			<input type="hidden" id="hidden_nj" value="${nj }"/>
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}" />
	
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" class="btn_fh" onclick="goDwwh();return false;">
								����
							</a>
						</li>
						<li>
							<a href="#" class="btn_ccg" onclick="saveFpbj();return false;">
								������
							</a>
						</li>
						<li>
							<a href="#" class="btn_sc" onclick="disfrockFpbj();return false;">
								�������
							</a>
						</li>
					</ul>
				</div>
				<!-- ��ť end-->
			</div>
				
			<!-- ��Ŀ������� -->
			<table class="searchtab" border="0" align="center" style="width: 100%">
				<tr>
					<td style="vertical-align: top">
						<!-- ��Ա�������� begin -->
						
						<table width="100%" border="0" class="formlist">
							<thead>
								<tr>
									<th>
										<span>��ְ����������</span>
									</th>
								</tr>
							</thead>
						</table>
						
						<table id="tab_fdy">
							<tr>
								<td>
									ְ����
								</td>
								<td>
									<input type="text" id="text_zgh" value="${rs.zgh }" style="width:120px"/>
								</td>
							</tr>
							<tr>
								<td>
									����
								</td>
								<td>
									<input type="text" id="text_xm" value="${rs.xm }" style="width:120px"/>
								</td>
							</tr>
							<tr>
								<td>
									��������
								</td>
								<td>
									<logic:equal name="userType" value="xy"  >
									<select style="width:120px" disabled="disabled" value="${userDep }">
										<logic:notEmpty name="bmpyList">
											<option value=""></option>
											<logic:iterate name="bmpyList" id="bm">
												<logic:equal name="bm" property="bmdm" value="${rs.bmdm}">
													<option value="${bm.bmdm }" title="${bm.bmmc }" selected="selected">${bm.bmpy }${bm.bmsx }</option>
												</logic:equal>
												<logic:notEqual name="bm" property="bmdm" value="${rs.bmdm}">
												<option value="${bm.bmdm }" title="${bm.bmmc }">${bm.bmpy }${bm.bmsx }</option>
												</logic:notEqual>				
											</logic:iterate>
										</logic:notEmpty>
									</select>
									<input type="hidden" name="select_bmdm"  id="select_bmdm" value="${userDep }" style="display: none" />
									</logic:equal>
									
									<logic:notEqual name="userType" value="xy">
									<select id="select_bmdm" style="width:120px">
										<logic:notEmpty name="bmpyList">
											<option value=""></option>
											<logic:iterate name="bmpyList" id="bm">
												<logic:equal name="bm" property="bmdm" value="${rs.bmdm}">
													<option value="${bm.bmdm }" title="${bm.bmmc }" selected="selected">${bm.bmpy }${bm.bmsx }</option>
												</logic:equal>
												<logic:notEqual name="bm" property="bmdm" value="${rs.bmdm}">
													<option value="${bm.bmdm }" title="${bm.bmmc }">${bm.bmpy }${bm.bmsx }</option>
												</logic:notEqual>				
											</logic:iterate>
										</logic:notEmpty>
									</select>
									</logic:notEqual>
								</td>
							</tr>
							<tr>
								<td>
									�������
								</td>
								<td>
									<select id="select_dbqk" style="width:120px">
										<option></option>
										<option value="yes">�Ѵ���</option>
										<option value="no">δ����</option>
									</select>
								</td>
							</tr>
							<tr>
								<td colspan="2">								
									<div class="btn">
										<button type="button" onclick="createJzgOption()">
											�� ѯ
										</button>
										<button type="button" onclick="resetFdytj();createJzgOption()">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</table>
						<!-- ��Ա�������� end -->
						
						<table>
							<tr>
								<td>
									<select id="select_jzg" size="25" style="width:100%" onchange="searchRs();">
										<option></option>
									</select>
								</td>
							</tr>
						</table>
					</td>
					<td style="vertical-align: top">
					
						<!-- ���Ź������� begin  -->
						<table width="100%" border="0" class="formlist">
							<thead>
								<tr>
									<th>
										<span>�༶��������</span>
									</th>
								</tr>
							</thead>
						</table>
									
						<table align="center" class="searchtab" id="tab_bbxx">
							<thead>
								<tr>
									<th>�꼶</th>
									<td>
										<html:select property="nj" style="width: 120px" 
											onchange="initZyList();initBjList();$('njV').value=this.value;$('zyV').value='';$('bjV').value='';titleNjxyzybj()" styleId="nj">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj" labelProperty="nj" />
										</html:select>
									</td>
									<th>Ժϵ</th>
									<td>
										<logic:equal name="userType" value="xy">
											<html:select property="xydm" style="width: 120px" disabled="true" value="${userDep }"
												onchange="initZyList();initBjList();$('xyV').value=this.value;$('zyV').value='';$('bjV').value='';titleNjxyzybj()">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
											<input type="text"  property="xydm" id="xy" value="${userDep }" style="display:none"/>
										</logic:equal>
										<logic:notEqual name="userType" value="xy">
											<html:select property="xydm" style="width: 120px" styleId="xy" 
												onchange="initZyList();initBjList();$('xyV').value=this.value;$('zyV').value='';$('bjV').value='';">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
										</logic:notEqual>
									</td>
									<th>רҵ</th>
									<td>
										<html:select property="zydm" style="width: 120px" styleId="zy" 
											onchange="initBjList();$('zyV').value=this.value;$('bjV').value='';titleNjxyzybj()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>�༶</th>
									<td>
										<html:select property="bjdm" style="width:120px" styleId="bj"
											onchange="$('bjV').value=this.value">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
									<th>����${lxmc }</th>
									<td>
										<select id="select_ywjs" style="width:120px">
											<option value=""></option>
											<option value="yes">��</option>
											<option value="no">��</option>
										</select>
									</td>
									<th>�Ƿ��д���</th>
									<td>
										<select id="select_brdb" style="width:120px">
											<option value=""></option>
											<option value="yes">��</option>
											<option value="no">��</option>
										</select>
									</td>
								</tr>
								<tr>
									<td colspan="6">
										<div class="btn">
						              		<button type="button" id="search_go"  onclick="searchRs();return false;">�� ѯ</button>
						              		&nbsp;&nbsp;&nbsp;&nbsp;
											<button type="button" class="btn_cz" id="btn_cz" onclick="resetBbtj()">
												�� ��
											</button>
						            	</div>
									</td>
								</tr>
							</thead>
						</table>
						<!-- ���Ź�������  end  -->
						
						<!-- ����� begin -->
						<div id="div_rs" style="height:400px;width:100%;overflow-x:hidden;overflow-y:auto;">
						</div>
						<!-- ����� end -->			
						<!--��ҳ��ʾ-->
						<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=generalSzdwGeneralForm"/>
						<script type="text/javascript">
								$('choose').className="hide";
						</script>
					</td>
				</tr>
			</table>
			
			<!-- ��ְ����ϢDiv begin -->
			<div id="div_jzgxx" style="display:none">
				
			</div>
			<!-- jiaozhigong��ϢDiv end -->
					
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>