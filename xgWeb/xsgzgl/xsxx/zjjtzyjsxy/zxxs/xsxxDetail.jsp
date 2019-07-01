<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/studetailFunction.js"></script>
		<script type="text/javascript" src="js/stuinfoFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getStuDetails.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/dwjlgl.js'></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript" src="js/prototype-1.6.0.3.js"></script>
		<script type="text/javascript">
			function deploy(id){
				document.getElementById(id).style.display = (document.getElementById(id).style.display == 'none') ? '' : 'none';  
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
		    	if(id == "qgzx"){
		    		getQgzxInfo();
		    	}
		    	if(id == "xljk"){
		    		getXljkInfo();
		    	}
		    	if(id == "xsjx"){
		    		getXsjxInfo();
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
			    	getTzHd();
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
		    		getXscjInfo();
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
		    }
		    
		    jQuery(function(){
		    	jQuery('a',jQuery('li',jQuery('#ul1')).eq(0)).click();
		    })
		    
		</script>
	</head>
	<body onload="" >
		<html:form action="/general_xsxx" method="post">
			<input type="hidden" id="xh" name="xh" value="${rs.xh }" />
			<input type="hidden" name="zzxh" id="zzxh" value="${xh }" />
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}" />
			<input type="hidden" id="titName" name="titName" value="${mrxs }" />
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- style="width:100%;height:520px;overflow-x:hidden;overflow-y:auto;" -->
			<div class="tab" >
				<!-- ������Ϣ -->
				<table width="100%" border="0" class="formlist">	
					<thead>
						<tr>
							<th colspan="5" style="cursor:hand" onclick="hiddenMk('tb_jbxx')">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tb_jbxx">
						<tr>
							<th width="16%">
								ѧ��
							</th>
							<td width="24%">
								${rs.xh }
							</td>
							<th width="15%">
								����
							</th>
							<td width="25%">
								${rs.xm }
							</td>
							<td rowspan="6">
								<div id="stuImg">
									<img style="width:120px;height:160px" 
										src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh }"
										border="0">
								</div>
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td>
								${rs.xb }
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
								${rs.mzmc }
							</td>
							<th>
								������ò
							</th>
							<td>
								${rs.zzmmmc }
							</td>
						</tr>
						<tr>
							<th>
								���֤��
							</th>
							<td>
								${rs.sfzh }
							</td>
							<th>
								
							</th>
							<td>
								
							</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td colspan="3">
								${rs.jgmc }
							</td>
						</tr>
						<tr>
							<th>
								��Դ����(��Դ��)
							</th>
							<td colspan="3">
								${rs.sydqmc }
							</td>
						</tr>
					</tbody>
			    </table>
		   		<!-- ������Ϣ  end-->		    
			    
			    <logic:present name="pages">
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
										indexId="s" offset="0" length="6">
										<li id="${card.en}li">
											<a href="#" onclick="changePage(this)" id="${card.en}_a">
												<span>${card.cn}</span> </a>
										</li>
									</logic:iterate>
								</logic:notEmpty>
							</ul>
						</div>
						<div class="morediv" id="morediv"
							style="position:absolute;top:0px;right:30px;text-align:center"
							onmousemove="javascript:document.getElementById('morelist').style.display='block'"
							onmouseout="javascript:document.getElementById('morelist').style.display='none'">
							<p class="more"></p>
							<div class="morelist" id="morelist" style="display:none;">
								<!--[if lte IE 6]><iframe class=navbug></iframe><![endif]-->
								<ul id="dropDownUl">
									<logic:notEmpty name="pages">
										<logic:iterate id="card" name="pages" scope="request"
											indexId="s" offset="6" >
											<li id="${card.en}li">
												<a href="#" onclick="changePage(this)" id="${card.en}_a">
													<span>${card.cn}</span> </a>
											</li>
										</logic:iterate>
									</logic:notEmpty>
								</ul>
							</div>
						</div>
					</div>
				</div>


				<div class="tab" id="tab">
					<!--ѧ��������Ϣ-->
					<%@ include file="/xsgzgl/xsxx/zjjtzyjsxy/zxxs/zjjtzy_xsxx.jsp"%>
					<!--���Ž���-->
					<%@ include file="/xsxx/common/xsxx_dtjs.jsp"%>
					<!--��������-->
					<%@ include file="/xsxx/common/xsxx_pjpy_zp.jsp"%>
					<!--���⽻��-->
					<%@ include file="/xsxx/common/xsxx_dwjl.jsp"%>
					<!--ѧ������-->
					<%@ include file="/xsxx/common/xsxx_xszz.jsp"%>
					<!--�ڹ���ѧ-->
					<%@ include file="/xsxx/common/xsxx_qgzx.jsp"%>
					<!--������-->
					<%@ include file="/xsxx/common/xsxx_xljk.jsp"%>
					<!--��ѵ����-->
					<%@ include file="/xsxx/common/xsxx_jxgl.jsp"%>
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
				</logic:present>
			    
			    
			    
		    </div>
		    <div>
		    	<table width="100%" border="0" class="formlist">
			    	<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
									<button type="button" name="�ر�" onclick="Close();return false;">�� ��</button>
								</div>
							</td>
						</tr>
				    </tfoot>
			    </table>
			</div>
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>