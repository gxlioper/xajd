<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.action.Base"%>
<%@page import="common.Globals"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<!-- ͷ�ļ� -->
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/studetailFunction.js"></script>
		<script type="text/javascript" src="js/stuinfoFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/dwjlgl.js'></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript" src="js/prototype-1.6.0.3.js"></script>
		<script>

		function deploy(id){
			document.getElementById(id).style.display = (document.getElementById(id).style.display == 'none') ? '' : 'none';  
		}	
		
		function printOne(){
			var xh = document.getElementById('xh').value;
			var mks = document.getElementsByName('print_mk');

			var print_mk = ""
			for(var i=0; i<mks.length; i++){
				if(mks[i].checked == true){
					print_mk = print_mk + mks[i].value + "split!!";
				}
			}
			
<%--			refreshForm('stu_info_details.do?page=print');--%>
			showOpenWindow("stu_info_details.do?xh="+xh+"&page=print&print_mk="+print_mk,800,600);
		}
		
		function loadchange(){
			var tab = document.getElementById("titName").value;
			if(tab != ""){
				changePage($(tab+'_a'));
			}
	    }
	    
	    function changePage(obj){
	    	var id = obj.parentNode.id;
	    	obj.parentNode.className = 'ha';
	    
	    	var nodeId = obj.id.substring(0,obj.id.length-2);
	    	var elements = ele('ul1').children;
	    	for(var i=0; i<elements.length; i++){
	    		if(elements[i].id!= id){
	    			elements[i].className = '';
	    			var trId = elements[i].id.substring(0,elements[i].id.length-2);
	    			if(document.getElementById(trId)){
	    				document.getElementById(trId).style.display = "none";	    			
	    			}
	    		}
	    	}
	    	elements = ele('dropDownUl').children;
	    	for(var i=0; i<elements.length; i++){
	    		if(elements[i].id!= id){
	    			elements[i].className = '';
	    			var trId = elements[i].id.substring(0,elements[i].id.length-2);
	    			if(document.getElementById(trId)){
	    				document.getElementById(trId).style.display = "none";	    			
	    			}
	    		}
	    	}
	    	
			if(document.getElementById(nodeId)){
				document.getElementById(nodeId).style.display = "";
				loadData(nodeId);
			}    	
	    }
	    
	    function loadData(id){
	    	var xxdm = document.getElementById("xxdm").value;
	    	if(id == "xjyd"){
	    		getXjydxx();
	    	}
	    	if(id == "jtxx"){
	    		getJtxxInfo();
	    	}
	    	if(id == "dtjs"){
	    		getSxjyInfo();
	    	}
	    	if(id == "pjpy"){
	    		getPjpyInfo();
	    	}
	    	if(id == "dwjl"){
	    		getDwjlInfo();
	    	}
	    	if(id == "xszz" && xxdm == "10628#"){
	    		//����ѧԺ
	    		getXcxyXszzInfo();
	    	}
	    	if(id == "qgzx"){
	    		getQgzxInfo();
	    	}
	    	if(id == "xljk"){
	    		getXljkInfo();
	    	}
	    	if(id == "xsjx"){
	    		getXsjxInfoByHzsfxy();
	    	}
	    	if(id == "qtsj"){
	    		getQtsjInfo();
	    	}
	    	if(id == "wjcf"){
	    		getWjcfInfo();
	    	}
	    	if(id == "xsgy"){
	    		getXsgyInfo();
	    	}
	    	if(id == "sztz"){
	    		if (xxdm == '11032'){
	    			getTjzySztz();
	    		} else {
		    		getTzHd();
	    		}
	    		
	    	}
	    	if(id == "bzrfdy"){
	    		getBDinfo();
	    	}
	    	if(id == "xnmzsztz"){
	    		getTzBjJy();
	    	}
	    	if(id == "xycjd"){
	    		getXycjdInfo();
	    	}
	    	if(id == "jsxxsztz"){
	    		getTzRzXx();
	    	}
	    	if(id == "lssfxscj"){
	    		getXskccjInfo();
	    	}
	    	if(id == "zgmyxscj"){
	    		getXskccjInfo();
	    	}
	    	if(id == "xssfxx"){
	    		getChargeInfo();
	    	}
	    	if(id == "xscj"){
	    		//getXscjInfo();
	    		createXscjHtml();
	    	}
	    	if(id == "jfqk"){
	    		getXsjfqkInfo();
	    	}
	    	if(id == "xxjl"){
	    		getXsxxjlInfo();
	    	}
	    	if(id == "jygl"){
	    		getJyglInfo();
		    }
		    if(id == "zcqk"){
	    		getZcqkInfo();
		    }
		    if(id == "bzrpy"){
	    		getBzrpyInfo();
		    }
		    if(id == "xjydcomm"){
	    		getXjydInfo();
		    }
		    if(id == "shgz"){
	    		getShgzInfo();
		    }
		    if(id == "rwgl"){
	    		getRwglInfo();
		    }
	    }
	    
	    //ѧ���ɼ�
	    function createXscjHtml(){
	    	var xh = jQuery("#xh").val();

		    jQuery.ajaxSetup({async:false});
	
			//·��
			var url = "xsxx_ajax.do?method=createXscjHtml";
			//����
		 	var parameter = {
				"xh":xh
			};
			
			jQuery("#xscj").load(
				url,
				parameter,
				function(){
				
				}
			);
	
			jQuery.ajaxSetup({async:true});	
	    }
	</script>
	</head>
	<body onload="loadchange()" onunload="windowClose();">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>ѧ����ϸ��Ϣ</a>
			</p>
		</div>
		<form action="/stu_info_details" method="post">
			<input type="hidden" id="xh" name="xh" value="${xh }" />
			<input type="hidden" name="zzxh" id="zzxh" value="${xh }" />
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}" />
			<input type="hidden" id="titName" name="titName" value="${mrxs }" />
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<%-- ѧ��������Ϣʼ����ʾ --%>
			<table width="97%" align="center" class="formlist breakword">
				<thead>
					<tr>
						<th colspan="5">
							<span>ѧ����Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="15%">
							ѧ��
						</th>
						<td width="25%">
							${rs.xh}
						</td>
						<th width="15%">
							����
						</th>
						<td width="25%">
							${rs.xm}
						</td>
						<td rowspan="6" class="nohover"
							style="vertical-align:top; text-align:center;width: 20%">
							<div class="open_img">
								<!--�㽭��ҵְҵ����ѧԺ-->
								<logic:equal value="12865" name="xxdm">
									<img border="0" style="height:133px;width:100px;"
										src="/xgxt/pictures/${rs.sfzh}.jpg" />
								</logic:equal>
								<!--end�㽭��ҵְҵ����ѧԺ-->
								<%--�㽭����ְҵ����ѧԺ--%>
								<logic:equal value="12861" name="xxdm">
									<img
										src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh}"
										border="0" align="absmiddle" style="width:140px;height:160px" />
								</logic:equal>
								<%--end�㽭����ְҵ����ѧԺ--%>
								<%--������Ϣְҵ����ѧԺ--%>
								<logic:equal value="13108" name="xxdm">
									<img
										src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh}"
										border="0" align="absmiddle" style="width:140px;height:160px" />
								</logic:equal>
								<%--end������Ϣְҵ����ѧԺ--%>

								<!--����ѧУ-->
								<logic:notEqual value="12865" name="xxdm">
									<logic:notEqual value="12861" name="xxdm">
										<logic:notEqual value="13108" name="xxdm">
											<img
												src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}"
												width="96" height="128" />
										</logic:notEqual>
									</logic:notEqual>
								</logic:notEqual>
								<!--end����ѧУ-->
								<!--						<p style="color:#0F5DC1">���ͣ�jpg<br />-->
								<!--       						������С��100k<br />��С��96*128����</p>-->
							</div>
						</td>
					</tr>
					<tr>
						<th>
							�Ա�
						</th>
						<td>
							${rs.xb}
						</td>
						<th>
							��������
						</th>
						<td>
							${rs.csrq}
						</td>
					</tr>
					<tr>
						<th>
							����
						</th>
						<td>
							${rs.mzmc}
						</td>
						<th>
							������ò
						</th>
						<td>
							${rs.zzmmmc}
						</td>
					</tr>
					<tr>
						<th>
							���֤��
						</th>
						<td align="left">
							${rs.sfzh}
						</td>
						<th>
							�������
						</th>
						<td align="left">
							<logic:equal value="10491" name="xxdm">
							${rs.pycc}
						</logic:equal>
							<logic:notEqual value="10491" name="xxdm">
								<logic:equal value="new" name="edition">
								${rs.pyccmc}
							</logic:equal>
								<logic:notEqual value="new" name="edition">
								${rs.pycc}
							</logic:notEqual>
							</logic:notEqual>
						</td>
					</tr>
					<tr>
						<th>
							����
						</th>
						<td colspan="3">
							${rs.jg}
						</td>
					</tr>
					<logic:equal value="11733" name="xxdm">
						<tr>
							<th>
								��Դ����(��Դ��)
							</th>
							<td colspan="3">
								${rs.syds}
							</td>
						</tr>
						<tr>
							<th>
								�������ڵ�
							</th>
							<td colspan="3">
								${rs.hjdz}
							</td>
						</tr>
					</logic:equal>
					<logic:notEqual value="11733" name="xxdm">
						<tr>
							<th>
								��Դ����(��Դ��)
							</th>
							<td colspan="3">
								${rs.lydq}
							</td>
						</tr>
						
					</logic:notEqual>
				</tbody>
			</table>
			<br />

			<logic:present name="mkxx">
				<table width="97%" align="center" class="formlist breakword">
					<thead>
						<tr>
							<th colspan="5">
								<span>ģ����Ϣ</span>
							</th>
						</tr>
					</thead>
				</table>
				<div class="menu" id="nav">
					<div class="compTab" id="card" style="position:relative">
						<div class="comp_title" id="div1">
							<ul id="ul1">
								<logic:notEmpty name="pages">
									<logic:iterate id="card" name="pages" scope="request"
										indexId="s">
										<li id="${card.en}li">
											<a href="#" onclick="changePage(this)" id="${card.en}_a">
												<span>${card.cn}</span> </a>
										</li>
									</logic:iterate>
								</logic:notEmpty>
							</ul>
						</div>
						<div class="morediv" id="morediv"
							style="position:absolute;top:0px;right:3px;text-align:center"
							onmousemove="javascript:document.getElementById('morelist').style.display='block'"
							onmouseout="javascript:document.getElementById('morelist').style.display='none'">
							<p class="more"></p>
							<div class="morelist" id="morelist" style="display:none;">
								<!--[if lte IE 6]><iframe class=navbug></iframe><![endif]-->
								<ul id="dropDownUl">
								</ul>
							</div>
						</div>
					</div>
				</div>

				<script type="text/javascript">
			var sumWidth = 0;
			var splitPositionNo = 0;
			var liDropDownArr = new Array();
			var liDropDownArr2 = new Array();
			var liArr = ele('ul1').children;
			var flag = true;
			
			for(var j=0; j<liArr.length+1; j++){
				if(liArr[j] && $(liArr[j].id)){
					sumWidth += $(liArr[j].id).getWidth();
					if(sumWidth > $('card').getWidth()-300){//ȷ���ֽ�li���
						flag = false;
				         splitPositionNo = j;
				         for(var k=j;;k++){
				            if(liArr[k] && $(liArr[k].id)){//ȷ��
				                liDropDownArr.push($((liArr[k].id)));//����li����
				                
				                $((liArr[k].id)).remove();//ɾ��ԭ����li����
				             }else{
				             	if(liDropDownArr.length>0){
			  	 					for(var p=0;p<liDropDownArr.length;p++){
			     					 $('dropDownUl').insert(liDropDownArr[p]);
			     					}
		 						}
		 						break;
				             }
				         }
					}
				}
			}
			
			if(flag && $("morediv")){
				$("morediv").className="";
			}
		</script>

				<div class="tab">
					<!--ѧ��������Ϣ-->
					<%@ include file="/xsxx/hzsfxy/xsxx_jbxx.jsp"%>
					<!--��ͥ��Ϣ	-->
					<%@ include file="/xsxx/common/xsxx_jtxx.jsp"%>
					<!--���Ž���-->
					<%@ include file="/xsxx/common/xsxx_dtjs.jsp"%>
					<!--��������-->
					<%if(Base.xxdm.equals(Globals.XXDM_SZGYYQZYJSXY)){ %>
					<!--�����������ݹ�ҵ԰��-->
					<%@ include file="/xsxx/common/xsxx_pjpy_szgyyq.jsp"%>
					<%}else{ %>
					<%@ include file="/xsxx/common/xsxx_pjpy_zp.jsp"%>
					<%} %>
					<!--���⽻��-->
					<%@ include file="/xsxx/common/xsxx_dwjl.jsp"%>
					<!--ѧ������-->
					<%@ include file="/xsxx/common/xsxx_xszz.jsp"%>
					<!--�ڹ���ѧ-->
					<%@ include file="/xsxx/common/xsxx_qgzx.jsp"%>
					<!--������-->
					<%if(Base.xxdm.equals("10344")){ %>
					<!--�㽭��ҽҩ��ѧ-->
					<%@ include file="/xsxx/common/xsxx_xljk_zjzy.jsp"%>
					<%}else{ %>
					<%@ include file="/xsxx/common/xsxx_xljk.jsp"%>
					<%} %>
					<!--��ѵ����-->
					<%@ include file="/xsxx/hzsfxy/xsxx_jxgl.jsp"%>
					<!--Υ�ʹ���-->
					<%@ include file="/xsxx/common/xsxx_wjcf.jsp"%>
					<!--ѧ���ɼ�-->
					<%@ include file="/xsxx/common/xsxx_xscj.jsp"%>
					<!--ѧҵ�ɼ�-->
					<%@ include file="/xsxx/common/xsxx_xscjd.jsp"%>
					<!--�շ���Ϣ-->
					<%@ include file="/xsxx/common/xsxx_sfxx.jsp"%>
					<!--��Ԣ����-->
					<%@ include file="/xsxx/common/xsxx_gygl.jsp"%>
					<!--������չ-->
					<%@ include file="/xsxx/common/xsxx_sztz.jsp"%>
					<!--˼������-->
					<%@ include file="/xsxx/common/xsxx_szdw.jsp"%>
					<!--��������-->
					<%@ include file="/xsxx/common/xsxx_qtsj.jsp"%>
					<!--ѧ��ѧϰ����-->
					<%@ include file="/xsxx/common/xsxx_xxjl.jsp"%>
					<!--ѧ����ҵ����-->
					<%@ include file="/xsxx/common/xsxx_jygl.jsp"%>
					<!--ѧ��ע�����-->
					<%@ include file="/xsxx/common/xsxx_zcqk.jsp"%>
					<!--����������-->
					<%@ include file="/xsxx/common/xsxx_bzrpy.jsp"%>
					<!--ѧ���춯-->
					<%@ include file="/xsxx/common/xsxx_comm_xjyd.jsp"%>
					<!--��Ṥ��-->
					<%@ include file="/xsxx/hzsfxy/xsxx_shgz.jsp"%>
					<!--�������-->
					<%@ include file="/xsxx/hzsfxy/xsxx_rwgl.jsp"%>
			</logic:present>

			<div id="tmpDiv" style="display: none">
				<div class="tab">
					<table class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�ɴ�ӡģ��</span>
								</th>
							</tr>
						</thead>
						<tr>
							<logic:iterate id="card" name="pages" indexId="index">
								<td>
									${card.cn }&nbsp;&nbsp;&nbsp;
									<input type="checkbox" name="print_mk" value="${card.en }" />
								</td>
								<%if(index>0 && index%2==1){%>
							
						</tr>
						<%} %>
						</logic:iterate>
						<%if(((List)request.getAttribute("pages")).size() == 0){ %>
						<td colspan="2" align="center">
							<b>�޿ɴ�ӡģ�飡</b>
						</td>
						</tr>
						<%} %>
						<%if(((List)request.getAttribute("pages")).size()%2 != 0 && ((List)request.getAttribute("pages")).size() > 0){%>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						</tr>
						<%} %>
						<tr>
							<td colspan='2' align='right'>
								<button type="button" onclick='printOne()'>
									ȷ��
								</button>
							</td>
						</tr>
					</table>
				</div>
			</div>

			<table class="formlist" border="0" align="center" style="width: 100%">
				<tfoot>
					<tr>
						<td>
							<div class="btn">
								<logic:notEqual value="stu" name="userType">
									<button type="button" id="buttonUp" style="width:80px" onclick="nextOrUp()">
										��һ��
									</button>
									<button type="button" id="buttonNext" style="width:80px"
										onclick="nextOrUp('N')">
										��һ��
									</button>
								</logic:notEqual>
								<button type="button"
									onclick="viewTempDiv('ѡ���ӡģ��','tmpDiv',400,350);window.scroll(0,0);"
									style="width:80px" id="buttonSave">
									�� ӡ
								</button>
								<logic:notEqual value="zjck" name="doType">
									<button type="button" onclick="window.close();return false;" style="width:80px;"
										id="buttonClose">
										�� ��
									</button>
								</logic:notEqual>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
		<logic:notEqual value="stu" name="userType">
			<!-- ��һ������һ�����ٽ��ʱ����Ӧ��ť�ҵ� -->
			<script type="text/javascript" defer>
			if (!window.dialogArguments){
				var rowIndex = opener.curr_row.rowIndex;
				var rowsLength = opener.$("rsTable").rows.length;
				if (1==rowIndex) {
					$("buttonUp").disabled=true;
				} 
				if (rowsLength-1 == rowIndex 
							|| opener.$("rsTable").rows[rowIndex+1].getElementsByTagName('input')[0].value==""){
					$("buttonNext").disabled=true;
				}
			} else {
				$("buttonUp").style.display='none';
				$("buttonNext").style.display='none';
			}
		</script>
		</logic:notEqual>
		<!-- ����ҳ��رպ�Ѳ�ѯҳ���ѡ������ʽȥ�� -->
		<script type="text/javascript">
			function windowClose(){
				if (!window.dialogArguments && "stu" != $('userType').value){
					var row = opener.$("rsTable").rows[rowIndex];
					row.style.backgroundColor = obj_bgc;
					row == null;
				}
			}
		</script>

	</body>
</html>
