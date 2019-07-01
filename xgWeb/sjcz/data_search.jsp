<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/chgRychlist.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/chgJxjlist.js'></script>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<title><bean:message key="lable.title" /></title>

		<script language="javascript">
		function queryOne(value) {
			var xh = "";
			if(value == null || value == ""){
				if((curr_row == null) || (curr_row == "")){
					return false;
				}
				xh = curr_row.getElementsByTagName("input")[2].value;
			}else{
				xh = value;
			}
			var url = "/xgxt/stu_info_details.do?xh="+xh;
			showTopWin(url, 800, 600);
		}
			
			var Rows=new Array();	//����ѡ�е��ж���
			var ShiftStartRow="";		//Shift��ѡʱ�洢��ʼ�ж���
			var cur_bgc = "#ffdead";//ѡ���б������ַ�����
			
			function rowOver(objTr) {//
				curr_row = objTr;
			}
			
			function rowOut(objTr) {//
				curr_row = null;
			}
			
			function xz_viewMore(curr_row)
			{
				var xxdm=document.all['xxdm2'].value;
				var xg_xxdm = document.getElementById("xxdm").value;
				var tn = '';
				if ($('tableName')) {
					tn = document.getElementById('tableName').value;
				}
				if(xg_xxdm=="10402"){//����ʦ��
					viewMore('modi');
				} else if("no"==xxdm)
				{	
					if (xg_xxdm=='13108' && tn=='view_zhszcp') {
						var url = "/xgxt/stu_cj_details.do?xh=" + curr_row.cells[3].innerText;
						var xn=curr_row.cells[1].innerText;
						var xq=curr_row.cells[2].innerText;
						url +='&xn='+xn;
						url +='&xq='+xq;
						showTopWin(url, 800, 600);
					}else {
						viewMore('view');
					}				
				} else if("10110"==xxdm)
				{
					if("zhszcp"==document.all['realTable'].value)
					{
					var xn=curr_row.cells[1].innerText;
					var nd=curr_row.cells[0].innerText;
					var xh=curr_row.cells[3].innerText;
				    var url='/xgxt/pjpy_zbdx_weihu_one.do?doType=view';
				    url=url+"&xn="+xn+"&nd="+nd+"&xh="+xh;
				    showTopWin(url,'550','500');
				} else {
				    viewMore('view');
				}
			}
			}
			function getRqVal(name)
			{
			var rq=document.getElementById(name).value;
			if (rq!="")
			{
				rqs=rq.split("-");
				rq="";
				for (var i=0;i<rqs.length;i++)
				{
					rq+=rqs[i];
				}
				document.getElementById(name).value=rq;
			}
			}
			function chgrychlists(xydms) {
				var xydm = document.getElementById(xydms).value;
				chgRychlist.xyRychList(xydm,function(data) {
							DWRUtil.removeAllOptions('rychdm');			
							var o = [{id:'',labelText:''}];
							DWRUtil.addOptions('rychdm',o,'id','labelText');
							for(var i=0;i<data.length;i++){
								o = [{id:data[i].rychdm,labelText:data[i].rychmc}];
							DWRUtil.addOptions('rychdm',o,'id','labelText');
							}
				});
			}  		
		function chgJxjlists(xydms) {
			var xydm = document.getElementById(xydms).value;
			chgJxjlist.xyJxjList(xydm,function(data) {
						DWRUtil.removeAllOptions('jxjdm');			
						var o = [{id:'',labelText:''}];
						DWRUtil.addOptions('jxjdm',o,'id','labelText');
						for(var i=0;i<data.length;i++){
							o = [{id:data[i].jxjdm,labelText:data[i].jxjmc}];
						DWRUtil.addOptions('jxjdm',o,'id','labelText');
						}
			});
		}
		function bgdgl() {
			if (curr_row==null) {
				alert('��ѡ��Ҫ������������!');
				return;
			} else {
				var count=0;
				var chklist =document.getElementsByName("cbv");
				for (var i=0;i<chklist.length;i++) {
					if (chklist[i].checked) {
						count = count+1;
					}
				}
				if (count>1) {
					alert('ֻ�ܲ�����������!');
					return;
				} else {
					var xh = curr_row.cells[0].innerText;
					chgJxjlist.chkStuIsBys(xh,function(data) {
						if (data) {
							showTopWin('wjcf_BgdLoad.do?tz=true&pk='+curr_row.cells[0].getElementsByTagName("input")[0].value,620,540)	
						} else {
							alert('���������ϲ��鵵��������,ֻ�б�ҵ���ŷ��ϴ�����,�����º˶�!');
							return;
						}
					});
				}
			}
		}
		
		function chSj(){
			if($("gzkssj") && $("gzjssj")){
				var kssj = $("gzkssj").value;
				var jssj = $("gzjssj").value;
				if(kssj !="" && jssj !=""){
					if(kssj > jssj){
						alert("��ʼʱ����ڽ���ʱ�䣬��ȷ��");
						return false;
					}
				}
			}
			return true;		
		}
		function jsxxzcPrint() {
			var xn = document.getElementById('xn').value;
			var xq = document.getElementById('xq').value;
			var bjdm = document.getElementById('bjdm').value;
			if (xn=='' ||xq==''||bjdm=='') {
				alert("�ۺ����ʻ��ܱ���ѧ��,ѧ��,�༶Ϊ��λ���л������,��ѡ����Ӧ����!");
				return false;
			}
			window.open('zhszcp_print.do?xydm=' + document.getElementById('xy').value + '&bjdm=' + document.getElementById('bj').value + '&xn=' + document.getElementById('xn').value + '&xq=' + document.getElementById('xq').value, '', '');
		}
		function jsxxzcCount() {
			var xn = document.getElementById('xn').value;
			var xq = document.getElementById('xq').value;
			var bjdm = document.getElementById('bjdm').value;
			if (xn=='' ||xq==''||bjdm=='') {
				alert("�ۺ������Զ����㽫��ѧ��,ѧ��,�༶Ϊ��λ���л���,��ѡ����Ӧ����!");
				return false;
			}
			AutoAccountCj('/xgxt/AutoAccount.do')
		}
		function refreshSubForm() {
			if ($('wjcfflag') && $('wjcfflag').value=='true') {
				alert("�����ɹ�!");
				document.getElementById('search_go').click();
			}	
		}
		function hhgxyTj(){
			if($("bjdm").value==""){
				alert("��ѡ��༶��");
				return false;
			}
			if($("xn").value==""){
				alert("��ѡ��ѧ�꣡");
				return false;
			}
			if($("xq").value==""){
				alert("��ѡ��ѧ�ڣ�");
				return false;
			}
			var url="/xgxt/pjpyHhgxyPjtj.do?method=pjtj";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		
		function wjcfjj(){
			if (curr_row==null || curr_row=='') {
	     		alert('��ѡ��Ҫ������������.');
	     		return;
	     	} else {
	     		var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
	     		var url = "wjcf_bjqn_wjcfjj.do?pkValue=" + pkValue; 
	  			showTopWin(url,800,600);
	     	}
		}
		
		<!-- Υ�ʹ��ֻ��� -->
		function wjcfhz(){
			showOpenWindow("/xgxt/wjcfHztj.do?method=showWindow",400,200);
		}
		
		//td����������ɵ�������
		jQuery(function(){
		
			jQuery(".searchtab").children("table").find("tr").each(function(){
				
				var len=jQuery(this).find("td").length;
				var nulls=false;
				var sum=0;
				if(len<6 && len!=0){
					
					
					jQuery(this).find("td").each(function(){
						
						var colspan=0;
						
						if(jQuery(this).html()!=""){
						
							nulls=true;
						}
						colspan=eval(jQuery(this).attr("colspan"));
						sum+=colspan;
						sum++;
						
					});
					
					
					for(i=0;i<6-sum;i++){
						jQuery(this).append("<td></td>")
					}
				
				}
				
				if(len==0){
					
					jQuery(this).attr("style","display:none");
				
				}
				
				
				if(!nulls){
					
					jQuery(this).attr("style","display:none");
				
				}
				
			});
			
		});

		//ͬ�ô�ѧ�㽭��Ժ���Ի���������
		function bbdcExport(){
			
			var url = "bbdcExport.do?";

			var nj = jQuery("select[id=nj]").val();//�꼶
			if(nj != null && nj.length>0){
				url+= "&nj="+nj;
			}
			var xh = jQuery("input[name=xh]").val(); //ѧ��
			if(xh != null && xh.length>0){
				url+= "&xh="+xh
			}
			var xm = jQuery("input[name=xm]").val(); //����
			if(xm != null && xm.length>0){
				url+= "&xm="+xm
			}
			var xn = jQuery("select[id=xn]").val(); //ѧ��
			if(xn != null && xn.length>0){
				url+= "&xn="+xn
			}
			var nd = jQuery("select[id=nd]").val(); //���
			if(nd != null && nd.length>0){
				url+= "&nd="+nd
			}
			var xq = jQuery("select[id=xq]").val(); //ѧ��
			if(xq != null && xq.length>0){
				url+= "&xq="+xq
			}
			var xy = jQuery("select[id=xy]").val(); //ѧԺ
			if(xy != null && xy.length>0){
				url+= "&xydm="+xy
			}
			var zy = jQuery("select[id=zy]").val(); //רҵ
			if(zy != null && zy.length>0){
				url+= "&zydm="+zy
			}
			var bj = jQuery("select[id=bj]").val(); //�༶
			if(bj != null && bj.length>0){
				url+= "&bjdm="+bj
			}
			var lydm = jQuery("select[id=lydm]").val(); //�ر�ѧ����Դ
			if(lydm != null && lydm.length>0){
				url+= "&lydm="+lydm
			}

			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}

		</script>


	</head>
	<body
		onload="xyDisabled('xy');removeXnXq();bzrLoad();refreshSubForm();">

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a id="title_m">${tips}</a>
			</p>
		</div>

		<html:form action="/data_search" method="post">
			<input type="hidden" id="tzurl" name="tzurl"
				onclick="refreshForm('wjcf_BgdGl.do?go=go');" />


			<logic:equal value="view_xslxfszsxx" name="tableName">
				<logic:equal value="stu" name="userType">
					��ҳ��ֻ��ѧУ��<bean:message key="lable.xsgzyxpzxy" />�û����Է���
				</logic:equal>
			</logic:equal>

			<logic:notEqual value="stu" name="userType">
				<div class="rightcontent">
					<jsp:include page="for_data_searchli.jsp"></jsp:include>

					<logic:present name="showhzyjx">
						<table width="100%" class="tbstyle">
							<html:radio property="grhj" value="grhj" styleId="grhj"
								onclick="refreshForm('/xgxt/data_search.do?act=trainPrise')">���˻�</html:radio>
							<html:radio property="grhj" value="bjhj" styleId="bjhj"
								onclick="refreshForm('/xgxt/data_search.do?act=trainPrise')">�༶��</html:radio>
							<html:radio property="grhj" value="yxhj" styleId="yxhj"
								onclick="refreshForm('/xgxt/data_search.do?act=trainPrise')">
								<bean:message key="lable.xsgzyxpzxy" />��</html:radio>
						</table>
					</logic:present>
					<input type="hidden" name="zyV" id="zyV" />
					<input type="hidden" name="bjV" id="bjV" />
					<input type="hidden" id="userType" name="userType"
						value="<bean:write name="userType" scope="request"/>" />
					<input type="hidden" id="tableName" name="tableName"
						value="<bean:write name="tableName" scope="request"/>" />
					<input type="hidden" id="act" name="act"
						value="<bean:write name="act" scope="request"/>" />
					<input type="hidden" id="realTable" name="realTable"
						value="<bean:write name="realTable" scope="request"/>" />
					<input type="hidden" id="pk" name="pk"
						value="<bean:write name="pk" scope="request"/>" />
					<input type="hidden" id="xxdm" name="xxdm"
						value="<bean:write name="xxdm" scope="session"/>" />
					<input type="hidden" id="dxq" name="dxq"
						value="<bean:write name="writeAble" scope="request"/>" />
					<input type="hidden" id="isBzr" name="isBzr"
						value="<bean:write name="isBzr" scope="request"/>" />
					<input type="hidden" id="stab" name="stab" value="stab" />
					<input type="hidden" id="userName" name="userName"
						value="<bean:write name="userName" scope="session"/>" />
					<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />

					<div class="searchtab">
						<table width="100%" border="0">
							<logic:present name="showzbdx_xx">
								<input type="hidden" id="xxdm2" name="xxdm2"
									value="<bean:write name="xxdm" scope="request"/>" />
							</logic:present>
							<logic:notPresent name="showzbdx_xx">
								<input type="hidden" id="xxdm2" name="xxdm2" value="no" />
							</logic:notPresent>
							<logic:present name="sfxfzrx">
								<input type="hidden" id="sfxfzrx" name="sfxfzrx"
									value="<bean:write name="sfxfzrx" scope="request"/>" />
							</logic:present>

							<tfoot>
								<tr>
									<td colspan="6">
										<div class="btn">
											<input type="hidden" name="go" value="a" />
											<input type="hidden" name="tab" id="tab" value="qtjxj" />
											<button type="button" id="search_go"
												onclick="if(chSj()){allNotEmpThenGo('/xgxt/data_search.do');}">
												��ѯ
											</button>
											<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
												����
											</button>
											<logic:present name="showblsz">
												<button type="button" onclick="showTopWin('/xgxt/zhszcpblsz.do',750,250)">
													����
													<br />
													����
												</button>
											</logic:present>
										</div>
									</td>
								</tr>
							</tfoot>

							<tbody>
								<logic:notEqual value="bjhj" name="bjhjType">
									<tr>
										<th>
											�꼶
										</th>
										<td>
											<html:select property="nj"
												onchange="initZyList();initBjList()" styleId="nj">
												<html:option value=""></html:option>
												<html:options collection="njList" property="nj"
													labelProperty="nj" />
											</html:select>
										</td>
										<th>
											ѧ��
										</th>
										<td>
											<html:text property="xh" style="width:120px" maxlength="20"></html:text>
										</td>
										<th>
											����
										</th>
										<td>
											<html:text property="xm" style="width:85px" maxlength="20"></html:text>
										</td>
									</tr>
								</logic:notEqual>
								<tr>
									<logic:equal value="bjhj" name="bjhjType">
										<th>
											�꼶
										</th>
										<td colspan="5">
											<html:select property="nj"
												onchange="initZyList();initBjList()" styleId="nj">
												<html:option value=""></html:option>
												<html:options collection="njList" property="nj"
													labelProperty="nj" />
											</html:select>
										</td>
									</logic:equal>
								</tr>
								<logic:equal value="showXbemy" name="showXbemy">
									<tr>
										<th>
											����ʱ��&nbsp;��ʼ&nbsp;
										</th>
										<td>
											<input type="text" name="qssj" id="qssj"
												onclick="return showCalendar('qssj','y-mm-dd');"
												onblur="getRqVal('qssj')" />
										</td>
										<th>
											��ֹ&nbsp;
										</th>
										<td colspan="3">
											<input type="text" name="zzsj" id="zzsj"
												onclick="return showCalendar('zzsj','y-mm-dd');"
												onblur="getRqVal('zzsj')" />
										</td>
									</tr>
								</logic:equal>

								<logic:equal value="xshsxfb" name="realTable">
									<tr>
										<th>
											���
										</th>
										<td colspan="5">
											<html:select property="nf" style="width:70px" styleId="nf">
												<html:option value=""></html:option>
												<html:options collection="njList" property="nj"
													labelProperty="nj" />
											</html:select>
										</td>
									</tr>
								</logic:equal>
								
								<tr>
									<logic:notEqual value="view_xslxfszsxx" name="tableName">
										<logic:notEqual value="xshsxfb" name="realTable">
											<!--��Ͷ����Ϣ��ѯ-->
											<logic:notEqual value="xsbxb" name="realTable">
												<!--�����ʵ����ѯ-->
												<logic:notEqual name="tableName" value="view_knsshhdxx">
													<th>
														ѧ��
													</th>
													<td>
														<html:select property="xn" style="width:90px" styleId="xn"
															onchange="">
															<html:options collection="xnList" property="xn"
																labelProperty="xn" />
														</html:select>
													</td>
												</logic:notEqual>
											</logic:notEqual>
											<!--end��Ͷ����Ϣ��ѯ-->
										</logic:notEqual>
									</logic:notEqual>

									<%--ѧ����Ϣά���������--%>
									<logic:notEqual value="view_xslxfszsxx" name="tableName">
										<logic:notEqual value="13108" name="xxdm">
											<!--�����ʵ����ѯ-->
											<logic:notEqual name="tableName" value="view_knsshhdxx">
												<th>
													���
												</th>
												<td>
													<html:select property="nd" styleId="nd" style="width:70px">
														<html:options collection="ndList" property="nd"
															labelProperty="nd" />
													</html:select>
												</td>

											</logic:notEqual>
										</logic:notEqual>
									</logic:notEqual>
									<!--Ͷ����Ϣ��ѯ-->
									<logic:equal value="view_xsbxxx" name="tableName">
										<th>
											�Ƿ��ѱ�ҵ
										</th>
										<td>
											<html:select property="sfyby">
												<html:option value=""></html:option>
												<html:option value="��">��</html:option>
												<html:option value="��">��</html:option>
											</html:select>
										</td>
									</logic:equal>
									<logic:notPresent name="zjujxjrych">
										<logic:equal value="xshsxfb" name="realTable">
											<th>
												�·�
											</th>
											<td>
												<html:select property="yf" style="width:40px" styleId="yf">
													<html:option value=""></html:option>
													<html:options collection="yfList" property="yf"
														labelProperty="yf" />
												</html:select>
											</td>
										</logic:equal>

										<logic:notEqual value="view_xslxfszsxx" name="tableName">
											<logic:notEqual value="xshsxfb" name="realTable">
												<!--��Ͷ����Ϣ��ѯ-->
												<logic:notEqual value="xsbxb" name="realTable">
													<logic:notEqual value="no" name="view">
														<!--�����ʵ����ѯ-->
														<logic:notEqual name="tableName" value="view_knsshhdxx">
															<th>
																ѧ��
															</th>
															<td>
																<html:select property="xq" style="width:60px"
																	styleId="xq">
																	<html:option value=""></html:option>
																	<html:options collection="xqList" property="xqdm"
																		labelProperty="xqmc" />
																</html:select>
															</td>
														</logic:notEqual>
													</logic:notEqual>
												</logic:notEqual>
												<!--end��Ͷ����Ϣ��ѯ-->
											</logic:notEqual>
										</logic:notEqual>
									</logic:notPresent>
								</tr>
								<%--										<logic:present name="zjujxjrych">--%>
								<tr>
									<!--Ͷ����Ϣ��ѯ-->
									<logic:equal value="10290" name="xxdm">
										<logic:equal value="view_xlcsjg" name="tableName">
											<th>
												���Խ��
											</th>
											<td>
												<html:select name="cxrs" property="csjg" style="width:90px">
													<html:option value=""></html:option>
													<html:option value="����"></html:option>
													<html:option value="����"></html:option>
												</html:select>
											</td>

											<th>
												����ʱ��
											</th>
											<td>
												<html:select name="cxrs" property="csnj">
													<html:option value=""></html:option>
													<html:option value="һ�꼶"></html:option>
													<html:option value="���꼶"></html:option>
													<html:option value="���꼶"></html:option>
													<html:option value="���꼶"></html:option>
													<html:option value="���꼶"></html:option>
													<html:option value="���꼶"></html:option>
												</html:select>
											</td>
										</logic:equal>
									</logic:equal>
								</tr>
								<tr>
									<logic:equal value="10827" name="xxdm">
										<logic:equal value="xsjxjb" name="realTable">
											<th>
												���״̬
											</th>
											<td>
												<html:select property="shzt" styleId="shzt">
													<html:option value=""></html:option>
													<html:option value="0">ͨ��</html:option>
													<html:option value="1">��ͨ��</html:option>
													<html:option value="2">δ���</html:option>
												</html:select>
											</td>
										</logic:equal>
										<logic:equal value="xsrychb" name="realTable">
											<th>
												���״̬
											</th>
											<td>
												<html:select property="shzt" styleId="shzt">
													<html:option value=""></html:option>
													<html:option value="0">ͨ��</html:option>
													<html:option value="1">��ͨ��</html:option>
													<html:option value="2">δ���</html:option>
												</html:select>
											</td>
										</logic:equal>
									</logic:equal>
								</tr>
								<tr>
									<logic:equal value="wjcf" name="wjcf">
										<logic:notEqual value="11078" name="xxdm">
											<th>
												���֤��
											</th>
											<td>
												<html:text property="sfzh" style="width:110px"></html:text>
											</td>
										</logic:notEqual>
										<th>
											�������
										</th>
										<td>
											<html:select property="cflb" style="width:150px"
												styleId="cflb">
												<html:option value=""></html:option>
												<html:options collection="cflbList" property="cflbdm"
													labelProperty="cflbmc" />
											</html:select>
										</td>
										<th>
											����ԭ��
										</th>
										<td>
											<html:select property="cfyy" style="width:150px"
												styleId="cfyy">
												<html:option value=""></html:option>
												<html:options collection="cfyyList" property="cfyydm"
													labelProperty="cfyymc" />
											</html:select>
										</td>


										<!-- ���ݴ�ѧ���� -->
										<logic:equal value="11078" name="xxdm">
											<th>
												�Ƿ��������
											</th>
											<td>
												<html:select property="sfjw" styleId="sfjw"
													style="width:50px">
													<html:option value=""></html:option>
													<html:option value="��">��</html:option>
													<html:option value="��">��</html:option>
												</html:select>
											</td>
										</logic:equal>
										<!-- END -->
									</logic:equal>
								</tr>
								<tr>
									<!-- liang : �㽭����ְҵ����<bean:message key="lable.xsgzyxpzxy" /> -->
									<logic:equal value="12861" name="xxdm">
										<logic:equal value="view_xsgwxx" name="tableName">
											<th>
												���˵�λ
											</th>
											<td>
												<html:select property="yrdwdm" style="width:90px">
													<html:option value=""></html:option>
													<html:options collection="yrdwList" property="yrdwdm"
														labelProperty="yrdwmc" />
												</html:select>
											</td>
										</logic:equal>
									</logic:equal>
									<!-- ���Ž���ѧ����� -->
									<logic:present name="xsccList">
										<th>
											ѧ�����
										</th>
										<td>
											<html:select property="xsccdm" style="width:90px">
												<html:option value=""></html:option>
												<html:options collection="xsccList" property="xsccdm"
													labelProperty="xsccmc" />
											</html:select>
										</td>
									</logic:present>
									<logic:equal name="tableName" value="view_rdjjfzxx">
										<logic:equal name="xxdm" value="12061">
											<th>
												���ʱ���
											</th>
											<td>
												<html:text property="gzkssj" styleId="gzkssj"
													onblur="dateFormatChg(this);" style="cursor:hand;width:10%"
													onclick="return showCalendar('gzkssj','y-mm-dd');" />
												--
												<html:text property="gzjssj" styleId="gzjssj"
													onblur="dateFormatChg(this);" style="cursor:hand;width:10%"
													onclick="return showCalendar('gzjssj','y-mm-dd');" />
											</td>
										</logic:equal>
									</logic:equal>
								</tr>

								<tr>
									<th>
										<bean:message key="lable.xsgzyxpzxy" />
									</th>
									<logic:equal value="10827" name="xxdm">
										<logic:equal value="xsjxjb" name="realTable">
											<td>
												<html:select property="xydm" style="width:200px"
													styleId="xy"
													onchange="initZyList();initBjList();chgJxjlists('xy');">
													<html:option value=""></html:option>
													<html:options collection="xyList" property="xydm"
														labelProperty="xymc" />
												</html:select>
											</td>
										</logic:equal>
										<logic:notEqual value="xsjxjb" name="realTable">
											<logic:equal value="xsrychb" name="realTable">
												<td>
													<html:select property="xydm" style="width:200px"
														styleId="xy"
														onchange="initZyList();initBjList();chgrychlists('xy');">
														<html:option value=""></html:option>
														<html:options collection="xyList" property="xydm"
															labelProperty="xymc" />
													</html:select>
												</td>
											</logic:equal>
											<logic:notEqual value="xsrychb" name="realTable">
												<td>
													<html:select property="xydm" style="width:200px"
														styleId="xy" onchange="initZyList();initBjList();">
														<html:option value=""></html:option>
														<html:options collection="xyList" property="xydm"
															labelProperty="xymc" />
													</html:select>
												</td>
											</logic:notEqual>
										</logic:notEqual>
									</logic:equal>
									<logic:notEqual value="10827" name="xxdm">
										<td>
											<html:select property="xydm" style="width:150px" styleId="xy"
												onchange="initZyList();initBjList()">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
										</td>
									</logic:notEqual>
									<logic:notEqual value="xyhj" name="xyhjType">
										<th>
											רҵ
										</th>
										<td>
											<html:select property="zydm" style="width:150px" styleId="zy"
												onchange="initBjList()">
												<html:option value=""></html:option>
												<html:options collection="zyList" property="zydm"
													labelProperty="zymc" />
											</html:select>
										</td>
										<th>
											�༶
										</th>
										<td>
											<html:select property="bjdm" style="width:150px" styleId="bj">
												<logic:notEqual value="yes" name="isBzr">
													<html:option value=""></html:option>
												</logic:notEqual>
												<html:options collection="bjList" property="bjdm"
													labelProperty="bjmc" />
											</html:select>
										</td>

									</logic:notEqual>
								</tr>
								<%--								<logic:equal value="10338" name="xxdm" scope="session">--%>
								<%--									<tr>--%>
								<%--										<th>--%>
								<%--											����������--%>
								<%--										</th>--%>
								<%--										<td>--%>
								<%--											<html:text property="xlcslb"></html:text>--%>
								<%--										</td>--%>
								<%--										<th>--%>
								<%--											������������--%>
								<%--										</th>--%>
								<%--										<td>--%>
								<%--											<html:text property="xlwtlx"></html:text>--%>
								<%--										</td>--%>
								<%--										<th>--%>
								<%--											�Ƿ�������--%>
								<%--										</th>--%>
								<%--										<td>--%>
								<%--											<html:select property="sfkns">--%>
								<%--												<html:option value=""></html:option>--%>
								<%--												<html:option value="��">��</html:option>--%>
								<%--												<html:option value="��">��</html:option>--%>
								<%--											</html:select>--%>
								<%--										</td>--%>
								<%--									</tr>--%>
								<%--									<tr>--%>
								<%--										<th>--%>
								<%--											�Ƿ���--%>
								<%--										</th>--%>
								<%--										<td>--%>
								<%--											<html:select property="sfdq">--%>
								<%--												<html:option value=""></html:option>--%>
								<%--												<html:option value="��">��</html:option>--%>
								<%--												<html:option value="��">��</html:option>--%>
								<%--											</html:select>--%>
								<%--										</td>--%>
								<%--									</tr>--%>
								<%--								</logic:equal>--%>
								<tr>
									<logic:equal value="10827" name="xxdm">
										<logic:equal value="xy" name="userType">
											<th>
												�����
											</th>
											<td>
												<html:select property="jxjlb" styleId="jxjlb"
													onchange="refreshForm('/xgxt/data_search.do')">
													<html:option value="1">Ժ��</html:option>
													<html:option value="2">ϵ��</html:option>
												</html:select>
											</td>
										</logic:equal>
									</logic:equal>
									<logic:equal value="10827" name="xxdm">
										<logic:present name="xsjxjb">
											<th>
												��ѧ��
											</th>

											<td>
												<html:select property="jxjdm" style="width:170px"
													styleId="jxjdm">
													<html:option value=""></html:option>
													<html:options collection="xmlist" property="jxjdm"
														labelProperty="jxjmc" />
												</html:select>
											</td>
										</logic:present>
									</logic:equal>
									<logic:notEqual value="10827" name="xxdm">
										<logic:present name="xsjxjb">
											<th>
												��ѧ��
											</th>
											<td>
												<html:select property="jxjdm" style="width:170px"
													styleId="jxjdm">
													<html:option value=""></html:option>
													<html:options collection="xmlist" property="jxjdm"
														labelProperty="jxjmc" />
												</html:select>
											</td>
											<th>
												Ժϵ���
											</th>
											<td>
												<html:select property="xysh" style="width:170px"
													styleId="xysh">
													<html:option value=""></html:option>
													<html:options collection="shList" property="en"
														labelProperty="cn" />
												</html:select>
											</td>
											<th>
												ѧУ���
											</th>
											<td>
												<html:select property="xxsh" style="width:170px"
													styleId="xxsh">
													<html:option value=""></html:option>
													<html:options collection="shList" property="en"
														labelProperty="cn" />
												</html:select>
											</td>
										</logic:present>
									</logic:notEqual>
									<logic:present name="xsrychb">
										<th>
											�����ƺ�
										</th>
										<td>
											<html:select property="rychdm" style="width:160px"
												styleId="rychdm">
												<html:option value=""></html:option>
												<html:options collection="xmlist" property="rychdm"
													labelProperty="rychmc" />
											</html:select>
										</td>
										<th>
											Ժϵ���
										</th>
										<td>
											<html:select property="xysh" style="width:170px"
												styleId="xysh">
												<html:option value=""></html:option>
												<html:options collection="shList" property="en"
													labelProperty="cn" />
											</html:select>
										</td>
										<th>
											ѧУ���
										</th>
										<td>
											<html:select property="xxsh" style="width:170px"
												styleId="xxsh">
												<html:option value=""></html:option>
												<html:options collection="shList" property="en"
													labelProperty="cn" />
											</html:select>
										</td>
									</logic:present>
									<%--										</logic:present>--%>
								</tr>
								<tr>
									<!-- �������е��� -->
									<logic:equal value="12645" name="xxdm">
										<logic:equal value="wjcfb" name="realTable">
											<th>
												��������
											</th>
											<td>
												<html:text property="cfnx" styleId="cfnx" maxlength="20"></html:text>
											</td>
										</logic:equal>
									</logic:equal>

									<logic:equal value="wjcfb" name="realTable">
										<th>
											ѧ������ȷ��
										</th>
										<td>
											<html:select property="xsqr" styleId="xsqr">
												<html:option value=""></html:option>
												<html:option value="δȷ��">δȷ��</html:option>
												<html:option value="��ȷ��">��ȷ��</html:option>
											</html:select>
										</td>
										<th>
											���ִ���(������)
										</th>
										<td>
											<html:text property="cs" styleId="cs" maxlength="2"
												style="width:50px" onkeyup="chkIsNum(this)"></html:text>
										</td>
										<th>
											Υ��ʱ��
										</th>
										<td>
											<html:text property="wjkssj" style="width:80px"
												onclick="return showCalendar('wjkssj','ymmdd');"
												styleId="wjkssj" />
											-
											<html:text property="wjjssj" style="width:80px"
												onclick="return showCalendar('wjjssj','ymmdd');"
												styleId="wjjssj" />
										</td>
									</logic:equal>

									<logic:equal value="13022" name="xxdm">
										<logic:equal name="wjcf" value="wjcf">
											<th>
												�������ߴ���
											</th>
											<td>
												<html:select property="ywss" style="width:50px"
													styleId="ywss">
													<html:option value=""></html:option>
													<html:option value="0">��</html:option>
													<html:option value="1">��</html:option>
												</html:select>
											</td>
										</logic:equal>
									</logic:equal>
									<logic:notEqual name="xxdm" value="13022">
										<logic:notEqual name="wjcf" value="wjcf">
											<th></th>
											<td></td>
										</logic:notEqual>
									</logic:notEqual>
								</tr>
								<logic:equal value="13022" name="xxdm">
									<logic:equal name="wjcf" value="wjcf">
										<tr>
											<th>
												���޽������
											</th>
											<td>
												<html:select property="ywcx" style="width:50px"
													styleId="ywcx">
													<html:option value=""></html:option>
													<html:option value="0">��</html:option>
													<html:option value="1">��</html:option>
												</html:select>
											</td>
											<th></th>
											<td></td>
											<th></th>
											<td></td>
										</tr>
									</logic:equal>
								</logic:equal>
								<logic:equal value="wjcfb" name="realTable">
									<th>
										����ʱ��
									</th>
									<td>
										<html:text property="cfkssj" style="width:80px"
											onclick="return showCalendar('cfkssj','ymmdd');"
											styleId="cfkssj" />
										-
										<html:text property="cfjssj" style="width:80px"
											onclick="return showCalendar('cfjssj','ymmdd');"
											styleId="cfjssj" />
									</td>
									
									<logic:equal value="12759" name="xxdm">
									<th>
										�Ƿ���
									</th>
									<td>
										<select name="sfcx" id="sfcx" style="width:50px">
											<option value=""></option>
											<option value="��" <logic:equal name="sfcx" value="��">selected="selected"</logic:equal>>��</option>
											<option value="��" <logic:equal name="sfcx" value="��">selected="selected"</logic:equal>>��</option>
										</select>
									</td>
									</logic:equal>
									<logic:notEqual value="12759" name="xxdm">
									
									<th>

									</th>
									<td>

									</td>
									</logic:notEqual>
									<th>

									</th>
									<td>

									</td>
								</logic:equal>
								<logic:present name="specialStu">
									<th>
										�ر�ѧ����Դ
									</th>
									<td>
										<html:select  property="lydm" style="width:90px"
											styleId="lydm">
											<html:option value=""></html:option>
											<html:options collection="tsxslyList" property="lydm"
												labelProperty="lymc" />
										</html:select>
									</td>
									<th>

									</th>
									<td>

									</td>
									<th>

									</th>
									<td>

									</td>
								</logic:present>
							</tbody>
						</table>
					</div>

					<font color="blue"></font>

					<div class="formbox">
						<logic:empty name="rs">
							<h3 class="datetitle_01">
								<span> ��ѯ���&nbsp;&nbsp; <font color="red">δ�ҵ��κμ�¼��</font>
								</span>
							</h3>
						</logic:empty>

						<logic:notEmpty name="rs">
							<h3 class="datetitle_01">
								<span> ��ѯ���&nbsp;&nbsp; <font color="blue"><logic:present
											name="qssj">(<bean:write name="qssj" />--</logic:present> <logic:present
											name="zzsj">
											<bean:write name="zzsj" /> Υ������)</logic:present>
										��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������;��סCtrl���Զ�ѡ</font> </span>
							</h3>
							<table width="100%" id="rsTable" class="dateline">
								<thead>
									<tr style="cursor: hand">
										<logic:notPresent name="xsjxjb">
											<logic:notEqual value="10290" name="xxdm">
											
												<logic:equal name="realTable" value="wjcfb">
												<logic:iterate id="tit" name="topTr" offset="2">
													<td id="<bean:write name="tit" property="en"/>"
														onclick="tableSort(this)">
														<bean:write name="tit" property="cn" />
													</td>
												</logic:iterate>
												</logic:equal>
												<logic:notEqual name="realTable" value="wjcfb">
												<logic:iterate id="tit" name="topTr" offset="1">
													<td id="<bean:write name="tit" property="en"/>"
														onclick="tableSort(this)">
														<bean:write name="tit" property="cn" />
													</td>
												</logic:iterate>
												</logic:notEqual>
												
											</logic:notEqual>
											<logic:equal value="10290" name="xxdm">
												<logic:iterate id="tit" name="topTr" offset="1" length="2">
													<td id="<bean:write name="tit" property="en"/>"
														onclick="tableSort(this)">
														<bean:write name="tit" property="cn" />
													</td>
												</logic:iterate>
												<logic:iterate id="tit" name="topTr" offset="3" length="4">
													<td id="<bean:write name="tit" property="en"/>"
														onclick="tableSort(this)">
														<bean:write name="tit" property="cn" />
													</td>
												</logic:iterate>

												<logic:iterate id="tit" name="topTr" offset="8" length="1">
													<td id="<bean:write name="tit" property="en"/>"
														onclick="tableSort(this)">
														<bean:write name="tit" property="cn" />
													</td>
												</logic:iterate>

											</logic:equal>
										</logic:notPresent>
										<logic:present name="xsjxjb">
											<logic:iterate id="tit" name="topTr" offset="2">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)">
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
										</logic:present>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<logic:notPresent name="xsjxjb">
										<logic:equal value="12872" name="xxdm">
											<logic:equal value="xsrychb" name="realTable">
												<tr onclick="rowMoreClick(this,'',event);" style="cursor: hand"
													ondblclick="hzzyrychmodi()">
													<td>
														<logic:iterate id="v" name="s" offset="0" length="1">
															<input type="hidden" value="<bean:write name="v"/>" />
														</logic:iterate>
														<logic:iterate id="v" name="s" offset="1" length="1">
															<bean:write name="v" />
														</logic:iterate>
													</td>
													<logic:iterate id="v" name="s" offset="2">
														<td>
															<bean:write name="v" />
														</td>
													</logic:iterate>
												</tr>
											</logic:equal>
											<logic:notEqual value="xsrychb" name="realTable">
												<tr align="center" onclick="rowMoreClick(this,'',event);"
													style="cursor: hand" ondblclick="xz_viewMore(this)">
													<td>
														<logic:iterate id="v" name="s" offset="0" length="1">
															<input type="hidden" value="<bean:write name="v"/>" />
														</logic:iterate>
														<logic:iterate id="v" name="s" offset="1" length="1">
															<bean:write name="v" />
														</logic:iterate>
													</td>
													<logic:iterate id="v" name="s" offset="2">
														<td>
															<bean:write name="v" />
														</td>
													</logic:iterate>
												</tr>
											</logic:notEqual>
										</logic:equal>

										<logic:notEqual value="12872" name="xxdm">
											<logic:notEqual value="10290" name="xxdm">

												<tr onclick="rowMoreClick(this,'',event);" style="cursor: hand"
													ondblclick="xz_viewMore(this)">

													<logic:equal name="realTable" value="wjcfb">
														<td>
															<logic:iterate id="v" name="s" offset="0" length="1">
																<input type="hidden" value="<bean:write name="v"/>" />
															</logic:iterate>
															<logic:iterate id="v" name="s" offset="1" length="1">
																<input type="hidden" value="<bean:write name="v"/>" />
															</logic:iterate>
															<logic:iterate id="v" name="s" offset="2" length="1">
																<bean:write name="v" />
															</logic:iterate>
														</td>
														<logic:iterate id="v" name="s" offset="3">
															<td>
																<bean:write name="v" />
															</td>
														</logic:iterate>
													</logic:equal>
													<logic:notEqual name="realTable" value="wjcfb">
													<td>
														<logic:iterate id="v" name="s" offset="0" length="1">
															<input type="hidden" value="<bean:write name="v"/>" />
														</logic:iterate>
														<logic:iterate id="v" name="s" offset="1" length="1">
															<bean:write name="v" />
														</logic:iterate>
													</td>
													<logic:iterate id="v" name="s" offset="2">
														<td>
															<bean:write name="v" />
														</td>
													</logic:iterate>
													</logic:notEqual>
												</tr>
											</logic:notEqual>

											<logic:equal value="10290" name="xxdm">
												<tr onclick="rowMoreClick(this,'',event);" style="cursor: hand"
													ondblclick="xz_viewMore(this)">
													<td>
														<logic:iterate id="v" name="s" offset="0" length="1">
															<input type="hidden" value="<bean:write name="v"/>" />
														</logic:iterate>
														<logic:iterate id="v" name="s" offset="1" length="1">
															<bean:write name="v" />
														</logic:iterate>
													</td>
													<logic:iterate id="v" name="s" offset="2" length="5">
														<td>
															<bean:write name="v" />
														</td>
													</logic:iterate>
													<logic:iterate id="v" name="s" offset="8" length="1">
														<td>
															<bean:write name="v" />
														</td>
													</logic:iterate>
												</tr>
											</logic:equal>
										</logic:notEqual>
									</logic:notPresent>

									<%--									</logic:notPresent>--%>
									<!-- �ǽ�����Ϣ END -->
									<!-- ��ѧ�� -->
									<logic:present name="xsjxjb">
										<logic:equal value="10290" name="xxdm">
											<tr onclick="rowMoreClick(this,'',event);"
												onmouseover="rowOver(this);" onmouseout="rowOut();"
												ondblclick="<logic:equal value="12872" name="xxdm">hzyjxjmodi()</logic:equal><logic:notEqual value="12872" name="xxdm">viewMore2('modi')</logic:notEqual>">

												<td>
													<logic:iterate id="v" name="s" offset="0" length="1">
														<input type="hidden" value="<bean:write name="v"/>" />
													</logic:iterate>
													<logic:iterate id="v" name="s" offset="1" length="1">
														<input type="hidden" value="<bean:write name="v"/>" />
													</logic:iterate>
													<a href="#" onclick="queryOne()"> <logic:iterate id="v"
															name="s" offset="2" length="1">
															<bean:write name="v" />
														</logic:iterate> <input type="hidden" value="<bean:write name="v"/>" /> </a>
												</td>
												<logic:iterate id="v" name="s" offset="3">
													<td>
														<bean:write name="v" />
													</td>
												</logic:iterate>
											</tr>
										</logic:equal>
										<logic:notEqual value="10290" name="xxdm">
											<tr onclick="rowMoreClick(this,'',event);" style="cursor: hand"
												ondblclick="<logic:equal value="12872" name="xxdm">hzyjxjmodi()</logic:equal><logic:notEqual value="12872" name="xxdm">viewMore2('modi')</logic:notEqual>">

												<td>
													<logic:iterate id="v" name="s" offset="0" length="1">
														<input type="hidden" value="<bean:write name="v"/>" />
													</logic:iterate>
													<logic:iterate id="v" name="s" offset="1" length="1">
														<input type="hidden" value="<bean:write name="v"/>" />
													</logic:iterate>

													<logic:iterate id="v" name="s" offset="2" length="1">
														<bean:write name="v" />
													</logic:iterate>

												</td>
												<logic:iterate id="v" name="s" offset="3" length="1">
													<td>
														<bean:write name="v" />
													</td>
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="4" length="1">
													<td>
														<a href="#" onclick="queryOne('<bean:write name="v" />')"><bean:write
																name="v" /> </a>
													</td>
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="5">
													<td>
														<bean:write name="v" />
													</td>
												</logic:iterate>
											</tr>
										</logic:notEqual>
									</logic:present>
								</logic:iterate>
							</table>
					</div>
					<logic:notEqual value="12872" name="xxdm">
						<table width="99%" id="rsTable1" class="tbstyle">
							<tr>
								<td height=3></td>
							</tr>
							<tr>
								<td>
									<jsp:include flush="true"
										page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>

									<script type="text/javascript">
														$('choose').className="hide";
												</script>
								</td>
							</tr>
							<tr>
								<td height=3></td>
							</tr>
						</table>
						<br />
						<br />
					</logic:notEqual>
					<logic:equal value="12872" name="xxdm">
						<logic:notEqual value="xsjxjb" name="realTable">
							<logic:notEqual value="xsrychb" name="realTable">
								<table width="99%" id="rsTable1" class="tbstyle">
									<tr>
										<td height=3></td>
									</tr>
									<tr>
										<td>
											<jsp:include flush="true"
												page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
										</td>
									</tr>
									<tr>
										<td height=3></td>
									</tr>
								</table>
							</logic:notEqual>
						</logic:notEqual>
					</logic:equal>
					</logic:notEmpty>
				</div>
				<br />
				<br />

				<div id="toolTipLayer"
					style="position: absolute; visibility: hidden">
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute; left: 1%; top: 100px">
						</div>
					</center>
				</div>
			</logic:notEqual>

			<div id="tmpdiv"></div>
			<%--�㽭��ý<bean:message key="lable.xsgzyxpzxy" />--%>
			<logic:present name="dydate">
				<logic:equal name="dydate" value="no">
					<script language="javascript">
      					alert("��ѧ��δ��Ԥ����");
	  				</script>
				</logic:equal>
				<logic:equal name="dydate" value="yes">
					<script language="javascript">
      					alert("ת���ɹ�");
	  				</script>
				</logic:equal>
			</logic:present>
			<%--end�㽭��ý<bean:message key="lable.xsgzyxpzxy" />--%>
			<%--����ְҵ����<bean:message key="lable.xsgzyxpzxy" />--%>
			<logic:present name="autoCj">
				<logic:equal name="autoCj" value="ok">
					<script language="javascript">
      						alert("�Զ�������ɣ�");
	  					</script>
				</logic:equal>
				<logic:equal name="autoCj" value="no">
					<script language="javascript">
	  						alert("�Զ�����ʧ��! ");
	  					</script>
				</logic:equal>
			</logic:present>
		</html:form>
		<logic:equal name="result" value="ok">
			<script language="javascript">
      				alert("�����ɹ���");
      				document.getElementById('search_go').click();
	  		</script>
		</logic:equal>
		<logic:equal name="result" value="no">
			<script language="javascript">
	  				alert("����ʧ��! ");
	  		</script>
		</logic:equal>
		<logic:equal name="result" value="NoStu">
			<script language="javascript">
	  				alert("�˲���ֻ���ѧ�����˻�!");
	  		</script>
		</logic:equal>
		<!-- 	<script type="text/javascript" src="js/bottomButton.js"></script>  -->
		<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "96%";
					window.setInterval("initBTNTool('btn')",1);

					function hzyrychprint(){
						if (curr_row==null || curr_row=='') {
							alert('��ѡ��Ҫ��ӡ�������У�');
							return;
						} else 
							window.open('hzyrychprint.do?pkValue='+curr_row.cells[0].getElementsByTagName("input")[0].value);
					}
					function hzyprint() {
				     	if (curr_row==null || curr_row=='') 
				     	{
				     		alert('��ѡ��Ҫ��ӡ�������ݣ�����һ�м���!');
				     		return;
				     	}
				     	 else {
				     	 	var url = 'dxjxjsp.do?method=dxjxjsp&pk=';
				     	 	url += curr_row.cells[0].getElementsByTagName("input")[0].value;
				     	 	window.open(url);
				     	 }
				     }
				     function hzyjxjmodi(){
				     	if (curr_row==null || curr_row=='') {
				     		alert('��ѡ��Ҫ������������.');
				     		return;
				     	} else {
				     		var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
				     		
				     		showTopWin('hzzyjxjmodi.do?pkValue='+pkValue,'670','550');
				     	}
				     } 
				     function hzzyrychmodi(){
				     	if (curr_row==null || curr_row=='') {
				     		alert('��ѡ��Ҫ������������.');
				     		return;
				     	} else {
				     		var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
				     		
				     		showTopWin('hzzyrychmodi.do?pkValue='+pkValue,'610','510');
				     	}
				     }
				     function wjsjzy(url) {
				     	var RowsStr="!!SplitOneSplit!!";   
				     	if (Rows.length==0) {
				     		alert('��ѡ��Ҫ������������,��סCtrl�����Զ�ѡ!');
				     		return;
				     	}
				     	if (confirm('ȷ��Ҫ��ѡ�������ת����ʷ��Ϣ����?')) {
				     		for (i=0; i<Rows.length; i++){ 										//�����ַ���
    							RowsStr+=Rows[i].getElementsByTagName("input")[0].value+"!!SplitOneSplit!!";
							}
							showTips('���������У���ȴ�......');
							refreshForm(url+"?pkValue="+RowsStr);
							
				     	}
				     	return;
				     }
				     
				     function kftj(url){
				     	var xn = document.getElementById('xn').value;
				     	var xq = document.getElementById('xq').value;
				     	
				     	url += "?xn=" + xn;
				     	url += "&xq=" + xq;
				     	
				     	if(xn == "" && xq == ""){
				     		if(confirm('��ѡ��ѧ��ѧ�ڽ�ͳ��ȫ������Ϣ,�Ƿ�ȷ����')){
				     			window.open(url);
				     			return true;
				     		}
				     	}
				     	window.open(url);
				     }
		</script>
		<!--		<logic:equal value="10290" name="xxdm">-->
		<!--			<SCRIPT LANGUAGE="JavaScript">-->
		<!--				setPageSize();-->
		<!--			</SCRIPT>-->
		<!--		</logic:equal>-->


	</body>
</html>
