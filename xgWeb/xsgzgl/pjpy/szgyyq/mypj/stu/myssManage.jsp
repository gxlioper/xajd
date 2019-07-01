<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <script language="javascript" src="js/check.js"></script>
	    <script language="javascript" src="js/pjpy/szgyyq/pjpy_szgyyq.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		
		//ǰ���ҵ�����
		function goMypj(){
			var url = "pjpy_szgyyq_mypj.do";
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		
		//��ʾѧ��Ͷ��Div
		function showXsssDiv(ssnr,sssj,clr,clyj,clsj){
			
			$("ssnr").value = ssnr;
			$("clyj").value = clyj;
			$("p_sssj").innerHTML = sssj;
			$("p_clr").innerHTML = clr;
			$("p_clsj").innerHTML = clsj;
			
			tipsWindown("ϵͳ��ʾ","id:div_xsss","350","380","true","","true","id");
		}
		
		//��ѯ�����
		function searchRs(){
			var url = "szgyyq_mypj_stu.do?method=getMyssInfoList";

			//��ѯ����
			var mhcx_lx = $("mhcx_lx").value;
			//ģ����ѯ
			var input_mhcx = $("input_mhcx").value;
			if(input_mhcx == ""){
				input_mhcx = " ";
			}else{
				input_mhcx=escape(input_mhcx);
			}

			//��Ŀ
			var xmdm = new Array();
			var xmdm_num = jQuery("a[name=a_name_xmdm]").length;
		
			var num = 0;
			
			if(xmdm_num != 0){
				for(var i=0;i<xmdm_num;i++){
					var id = jQuery("a[name=a_name_xmdm]")[i].id;
					xmdm[num] = id.replace("a_id_","");
					num++;
				}
			}else{
				xmdm = [" "];
			}
			
			//ѧ��
			var xn = new Array();
			var xn_num = jQuery("a[name=a_name_xn]").length;
		
			num = 0;
			
			if(xn_num != 0){
				for(var i=0;i<xn_num;i++){
					var id = jQuery("a[name=a_name_xn]")[i].id;
					xn[num] = id.replace("a_id_","");
					num++;
				}
			}else{
				xn = [" "];
			}
			
			//ѧ��
			var xq = new Array();
			var xq_num = jQuery("a[name=a_name_xq]").length;
		
			num = 0;
			
			if(xq_num != 0){
				for(var i=0;i<xq_num;i++){
					var id = jQuery("a[name=a_name_xq]")[i].id;
					xq[num] = id.replace("a_id_","");
					num++;
				}
			}else{
				xq = [" "];
			}
			
			//����
			var lx = new Array();
			var lx_num = jQuery("a[name=a_name_lx]").length;
		
			num = 0;
			
			if(lx_num != 0){
				for(var i=0;i<lx_num;i++){
					var id = jQuery("a[name=a_name_lx]")[i].id;
					lx[num] = escape(id.replace("a_id_",""));
					num++;
				}
			}else{
				lx = [" "];
			}
			
			var otherValue = [
				xmdm.join("!!##!!"),
				xn.join("!!##!!"),
				xq.join("!!##!!"),
				lx.join("!!##!!"),
				mhcx_lx,
				input_mhcx
			];

			if(checkSearch()){
				searchRsByAjax(url,otherValue);
			}
		}
		
		//����ɷ��ѯ
		function checkSearch(){
		
			var flag = true;
			var is_default = $("is_default").value;
			
			if(is_default!=""){
				var xn_num =  jQuery("a[name=a_name_xn]").length;
				var xq_num =  jQuery("a[name=a_name_xq]").length;
				
				if(xn_num == "0"){
					alertError("������ѡ��һ��ѧ�꣡");
					flag = false;
				}else if( xq_num == "0"){
					alertError("������ѡ��һ��ѧ�ڣ�");
					flag = false;
				}
			}
			return flag;
		}
		</script>
	</head>
	<body onload="searchRs()" >
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>�ۺ��������ɿ� - �ҵĹ��� - �ҵ�����</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>
		<!-- ���� end-->
		
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
				1.�������в������ǻ���<font color="blue">${xn}</font>ѧ�꣬<font color="blue">${xqmc}</font>ѧ�� չ���ġ�</br>
				2.����״̬Ϊ<font color="blue">�Ѵ���</font>�ģ��ɵ������״̬��ѯ���������
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/szgyyq_mypj_stu" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="is_default" id="is_default" value=""/>
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<li>
								<a href="#" onclick="goMypj();return false;" class="btn_fh">
									����
								</a>
							</li>		
						</li>
					</ul>
				</div>
				<!-- ��ť end-->
				<!-- �������� -->
				
				<!-- ģ����ѯ -->
				<div class="search_advanced">
					<div class="adv_filter">
						<table border="0" width="100%">
							<tbody>
								<tr>
									<td style="padding-left:68px;">
										��ѯ������
								        <input type="text" name="searchModel.input_mhcx"
											id="input_mhcx" value="${searchTj.input_mhcx }" size="50"
											onkeypress="if(pressEnter(event)){searchRs();return false;}"
											onfocus="setTsMsg();displayMsgDiv('input_mhcx_msg');"
											onblur="hideMsgDiv('input_mhcx_msg')" />
			
										<div id="input_mhcx_msg" class="hide"
											style="left: 140px;top: 122px;">
											<div class="prompcon" style="width: 250px">
												<p id="tsxx_span"></p>
											</div>
										</div>
										
										<button type="button" class="btn_cx" id="search_go"
											onclick="searchRs();return false;">
											�� ѯ
										</button>
			
										<button type="button" class="btn_cz" onclick="czSuperSearch()">
											�� ��
										</button>
										<br>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<html:radio property="searchModel.mhcx_lx" value="all" onclick="$('mhcx_lx').value = this.value"/>
										ȫ��
										<!-- ģ��ѭ�� -->
										<html:radio property="searchModel.mhcx_lx" value="ssnr" onclick="$('mhcx_lx').value = this.value"/>��������
										<html:radio property="searchModel.mhcx_lx" value="clrxm" onclick="$('mhcx_lx').value = this.value"/>������
										<input type="hidden" id="mhcx_lx" value="all"/>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					
					<!-- ��ѡ����(���Ի�) -->
					<div class="selected-attr" id="yxtj_gxh_div" style="display: none">
						<h3>
							��ѡ������
						</h3>
						<dl id="yxtj_gxh_dl">
				
						</dl>
					</div>
					<!-- ��ѡ���� end-->
					
					<!-- �߼���ѯ(���Ի�)-->
					<div class="prop-item" id="gjcx_gxh_div" style="display: none">
						<!-- ѧ�� -->
						<dl>
							<dt>ѧ�꣺</dt>
							<dd>
								<ul>
									<logic:iterate name="xnTjList" id="xnMap" indexId="list_num">
										<logic:notEqual name="list_num" value="0">
											<li>
												<a href="#" class=""
													onclick="clickTj('xn','${xnMap.xn }');creatGxhClickedTj('xn','ѧ��','${xnMap.xn }','${xnMap.xn }',this);return false;" 
													id="tj_xn_${xnMap.xn }" name="tj_xn">
													${xnMap.xn }
												</a>
											</li>
										</logic:notEqual>
									</logic:iterate>	
								</ul>
							</dd>
						</dl>
						<!-- ѧ�� end-->
						
						<!-- ѧ�� -->
						<dl>
							<dt>ѧ�ڣ�</dt>
							<dd>
								<ul>
									<logic:iterate name="xqTjList" id="xqMap" indexId="list_num">
										<li>
											<a href="#" class=""
												onclick="clickTj('xq','${xqMap.xqdm }');creatGxhClickedTj('xq','ѧ��','${xqMap.xqdm }','${xqMap.xqmc }',this);return false;" 
												id="tj_xq_${xqMap.xqdm }" name="tj_xq">
												${xqMap.xqmc }
											</a>
										</li>
									</logic:iterate>	
								</ul>
							</dd>
						</dl>
						<!-- ѧ�� end-->
						
						<!-- ����״̬ -->
						<dl>
							<dt>����״̬��</dt>
							<dd>
								<ul>
									<logic:iterate name="lxTjList" id="lxMap">
										<li>
											<a href="#" class=""
												onclick="clickTj('lx','${lxMap.en }');creatGxhClickedTj('lx','����״̬','${lxMap.en }','${lxMap.cn }',this);return false;" 
												id="tj_lx_${lxMap.en }" name="tj_lx">
												${lxMap.cn }
											</a>
										</li>
									</logic:iterate>	
								</ul>
							</dd>
						</dl>
						<!-- ����״̬ end-->
						
						<!-- ������Ŀ -->
						<dl>
							<dt>������Ŀ��</dt>
							<dd>
								<ul>
									<logic:iterate name="xmdmTjList" id="xmdmMap">
										<li>
											<a href="#" class=""
												onclick="clickTj('xmdm','${xmdmMap.en }');creatGxhClickedTj('xmdm','������Ŀ','${xmdmMap.en }','${xmdmMap.cn }',this);return false;" 
												id="tj_xmdm_${xmdmMap.en }" name="tj_xmdm">
												${xmdmMap.cn }
											</a>
										</li>
									</logic:iterate>	
								</ul>
							</dd>
						</dl>
						<!-- ������Ŀ end-->
						
					</div>
					<!-- �߼���ѯ end-->
				</div>

				<!-- ������ť -->
				<div class="more--item_bottom">
					<p>
						<a href="#" class="down"
							onclick="showGxhTbody(this,'up','down','�� ��','�� ��');return false">
							�� ��
						</a>
					</p>
				</div>
				<!-- ������ť end-->
				
				<!-- �������� -->
				<div id="searchGxhTjDiv" style="display:none">
					<logic:present name="searchTj">
						<!-- ������� -->
						<logic:notEmpty name="searchTj" property="search_tj_xn">
							<!-- ѧ�� -->
							<logic:iterate name="searchTj" property="search_tj_xn" id="queryValue">
								<input type="hidden" title="xn" name="search_tj_xn" value="${queryValue }" />
							</logic:iterate>
							<!-- ѧ�� -->
							<logic:iterate name="searchTj" property="search_tj_xq" id="queryValue">
								<input type="hidden" title="xq" name="searchModel.search_tj_xq" value="${queryValue }" />
							</logic:iterate>
						</logic:notEmpty>	

						<!-- ������ѡ���� -->
						<script language="javascript" defer="defer">
							setTimeout("creatClickedTjByGxhSearch()",500);
						</script>
					</logic:present>
				</div>
				<!-- ��������end-->
				
				<div style="display:none">
					<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
				<!-- �������� end-->
			</div>
			
			<!-- ������ʾ����ʼ -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- ��ѯ�õ�����������ʾ���� -->
						</p>
					</div>
				</div>
				<!-- From���� -->
				<div id="div_rs" style="height:360px;overflow-x:auto;overflow-y:auto;">
				</div>
				
				<!--��ҳ��ʾ-->
				<div style="clear:both;">
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpySzgyyqStuForm"></jsp:include>
					 <script type="text/javascript">
				      $('choose').className="hide";
				     </script>
				</div>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ����ʼ end-->	
			
			<!-- ѧ�����ߵ����� -->
			<div id="div_xsss" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>������Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									��������
								</th>
								<td>
									<textarea id="ssnr" rows="5" cols="" 
										style="word-break:break-all;width:100%"></textarea>
								</td>
							</tr>
							<tr>
								<th width="30%">
									����ʱ��
								</th>
								<td>
									<p id="p_sssj"></p>
								</td>
							</tr>
							<tr>
								<th width="30%">
									������
								</th>
								<td>
									<p id="p_clr"></p>
								</td>
							</tr>
							<tr>
								<th width="30%">
									�������
								</th>
								<td>
									<textarea id="clyj" rows="5" cols="" 
										style="word-break:break-all;width:100%"></textarea>
								</td>
							</tr>
							<tr>
								<th width="30%">
									����ʱ��
								</th>
								<td>
									<p id="p_clsj"></p>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<button type="button" id="btn_gb" onclick="closeWindown();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>