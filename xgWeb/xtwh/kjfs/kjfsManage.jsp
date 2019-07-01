<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="/homepage/other/head.jsp"%>
	<script language="javascript" src="/xgxt/dwr/interface/xtwhDAO.js"></script>
	<script type="text/javascript">
	//Ӧ��
	function yy(num){
		//����Ƿ񳬹�������ޣ�10����
		if(checkYy()){
			var yy_id = "yy_"+num;
			var qx_id = "qx_"+num;
			var p_yy_id = "p_yy_"+num;
			var p_qx_id = "p_qx_"+num;
			
			if($(p_yy_id)){
				$(p_yy_id).style.display = "none";
				$(p_qx_id).style.display = "";
			}
			
			var pic_id = "picpath_"+num;
			var mk_id = "showmk_"+num;
			var picpath = $(pic_id).value;//ͼƬ·��
			var mkms = $(mk_id).value;//ģ������
			
			var num = document.getElementsByName("chyh").length;
			
			var maxId = "0";
			
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("chyh")[i];
				var chId = obj.id.replace("ch_","");
				
				if(chId > maxId){
					maxId = chId;
				}
			}
			
			maxId=parseInt(maxId)+1;
			var node = document.getElementById("mkms_ul");    
	    	var li = document.createElement("li");   
	    	var value = "<input name='chyh' type='radio' id='ch_"+maxId+"' value='"+picpath+"' title='"+mkms+"'/><label>&nbsp;"+mkms+"</label>";
	    	li.innerHTML = value;
	    	li.id = "li_"+maxId;
	        node.appendChild(li); 
	        
	        saveKjfsOne(picpath,"yy");
        }
	}
	
	//ȡ��
	function qx(num){
		var yy_id = "yy_"+num;
		var qx_id = "qx_"+num;
		var p_yy_id = "p_yy_"+num;
		var p_qx_id = "p_qx_"+num;
		
		if($(p_qx_id)){
			$(p_yy_id).style.display = "";
			$(p_qx_id).style.display = "none";
		}
		
		var pic_id = "picpath_"+num;
		var mk_id = "showmk_"+num;
		var picpath = $(pic_id).value;//ͼƬ·��
		var mkms = $(mk_id).value;//ģ������
		
		var node = document.getElementById("mkms_ul");
		var num = document.getElementsByName("chyh").length;
		
		for(var i=0;i<num;i++){
		
			var obj = document.getElementsByName("chyh")[i];
			var chId = obj.id.replace("ch_","");
			var chValue = obj.value;
			
			if(chValue == picpath){
				var li_id = "li_"+chId;
				if($(li_id)){
					node.removeChild($(li_id));
					break;
				}
			}
		}
		saveKjfsOne(picpath,"qx");
	}
	
	//����
	function yhup(){
	
		var num = document.getElementsByName("chyh").length;
		var node = document.getElementById("mkms_ul");
		
		var tempArr = new Array();
		var checked_id = "";
		
		for(var i=0;i<num;i++){
		
			var obj = document.getElementsByName("chyh")[i];
			
			tempArr[i] = obj;
			if(obj.checked == true && i>0){
				tempArr[i] = tempArr[i-1];
				tempArr[i-1] = obj;
				checked_id = obj.id;
			}
			
			if(obj.checked == true && i==0){
				alert("��ģ���Ѿ��ڵ�һ������ٽ������Ʋ�����");
				return false;
			}
		}

		if(checked_id == ""){
			
			alert("��ѡ����Ҫ����λ�õ�ģ�飡");
			return false;
		}

		for(var i=0;i<tempArr.length;i++){
			var obj = tempArr[i];
			var chId = obj.id.replace("ch_","");	
			var li_id = "li_"+chId;
			
			node.removeChild($(li_id));
		}
		
		var node = document.getElementById("mkms_ul");    
		
		for(var i=0;i<tempArr.length;i++){
			
			var obj = tempArr[i];
			var chId = obj.id.replace("ch_","");	
	    	var li = document.createElement("li");  	  
	    	var value = "<input name='chyh' type='radio' id='ch_"+chId+"' value='"+obj.value+"' title='"+obj.title+"'/><label>"+obj.title+"</label>";
	    	li.innerHTML = value;
	    	li.id = "li_"+chId;
	        node.appendChild(li); 
		}
		
		$(checked_id).checked = true;
	}
	
	//����
	function yhdown(){

		
		var num = document.getElementsByName("chyh").length;
		var node = document.getElementById("mkms_ul");
		
		var tempArr = new Array();
		var checked_id = "";
		
		var next_flag = "";
		
		for(var i=0;i<num;i++){
		
			var obj = document.getElementsByName("chyh")[i];
			
			if(i != parseInt(next_flag) + 1){
				tempArr[i] = obj;
			}
			
			if(obj.checked == true && i<num-1){
				tempArr[i] = document.getElementsByName("chyh")[i+1];
				tempArr[i+1] = obj;
				checked_id = obj.id;
				next_flag = i;
			}
			
			if(obj.checked == true && i==num-1){
				alert("��ģ���Ѿ������һ������ٽ������Ʋ�����");
				return false;
			}
		}
		
		if(checked_id == ""){
			
			alert("��ѡ����Ҫ����λ�õ�ģ�飡");
			return false;
		}
		
		for(var i=0;i<tempArr.length;i++){
			var obj = tempArr[i];
			var chId = obj.id.replace("ch_","");	
			var li_id = "li_"+chId;
			
			node.removeChild($(li_id));
		}
		
		var node = document.getElementById("mkms_ul");    
		
		for(var i=0;i<tempArr.length;i++){
			
			var obj = tempArr[i];
			var chId = obj.id.replace("ch_","");	
	    	var li = document.createElement("li");  	  
	    	var value = "<input name='chyh' type='radio' id='ch_"+chId+"' value='"+obj.value+"' title='"+obj.title+"'/><label>"+obj.title+"</label>";
	    	li.innerHTML = value;
	    	li.id = "li_"+chId;
	        node.appendChild(li); 
		}
		
		$(checked_id).checked = true;
	}
	
	//����Ƿ���Ϲ淶
	function checkYy(){
		var num = document.getElementsByName("chyh").length;
		var userType=$("userType").value;
		if(userType=="stu" && num == 8){
			alert("����Ӧ��8������ͼ�꣬���Ѵﵽ���ޣ�");
			return false;
		}else if(userType!="stu" && num == 5){
			alert("����Ӧ��5������ͼ�꣬���Ѵﵽ���ޣ�");
			return false;
		}
		
		return true;
	}
	
	//�����ݷ�ʽ
	function saveKjfs(){
	
		var num = document.getElementsByName("chyh").length;
				
		var msg = "�Ƿ񱣴��˳��Ŀ�ݷ�ʽ��";
		if (confirm(msg)) {
			for(var i=0;i<num;i++){
			
				var obj = document.getElementsByName("chyh")[i];
			
				var tmp = document.createElement("input");
				tmp.type = "hidden";
				tmp.name = "pic";
				tmp.value = obj.value;
				document.forms[0].appendChild(tmp);
			}	
			refreshForm('/xgxt/kjfsSet.do?method=kjfsManage&doType=save');
		}
	}
	
	//ɾ����ݷ�ʽ
	function delKjfs(){
	
		var num = document.getElementsByName("chyh").length;

		var flag = false;
		var picpath = "";
		
		for(var i=0;i<num;i++){
			obj = document.getElementsByName("chyh")[i];
			if(obj.checked==true){
				flag = true;
				picpath = obj.value;
			}
		}
		
		if(!flag){
			alert("��ѡ����Ҫɾ���Ŀ�ݷ�ʽ");
			return false;
		}
		
		if (confirm("�Ƿ�ɾ����ѡ�Ŀ�ݷ�ʽ��")) {
		
			var yhm = $("userName").value; 
	
			dwr.engine.setAsync(false);
			
			xtwhDAO.delKjfsOne(yhm,picpath,function(data){
				if(data == true){
					//alert("ɾ���ɹ���");
				}else{
					//alert("ɾ��ʧ�ܣ�");
				}
			});
			
			dwr.engine.setAsync(true);
			
			var btnnum = document.getElementsByName(picpath).length;
			
			for(var i=0;i<btnnum;i++){
				obj = document.getElementsByName(picpath)[i];
				var idnum = obj.id.substring(3,obj.id.length);
				var pId = idnum;
				var p_yy_id = "p_yy_"+idnum;
				var p_qx_id = "p_qx_"+idnum;
				
				if($(p_yy_id)){
					if($(p_yy_id).style.display=="none"){
						$(p_yy_id).style.display = "";
					}else{
						$(p_yy_id).style.display = "none";
					}
				}
				
				if($(p_qx_id)){
					if($(p_qx_id).style.display=="none"){
						$(p_qx_id).style.display = "";
					}else{
						$(p_qx_id).style.display = "none";
					}
				}
				
				break;
			}
		}else {
			return false;
		}
		
		var node = document.getElementById("mkms_ul");
	
		for(var i=0;i<num;i++){
		
			var obj = document.getElementsByName("chyh")[i];
			var chId = obj.id.replace("ch_","");
			var chValue = obj.value;
			
			if(chValue == picpath){
				var li_id = "li_"+chId;
				if($(li_id)){
					node.removeChild($(li_id));
					break;
				}
			}
		}
	}
	
	//�����ݷ�ʽ��������
	function saveKjfsOne(picpath,lx){
	
		dwr.engine.setAsync(false);

		var yhm = $("userName").value; 

		xtwhDAO.saveKjfsOne(yhm,picpath,lx,function(data){
			if(data == true){
				//alert("�����ɹ���");
			}else{
				//alert("����ʧ�ܣ�");
			}
		});
		
		dwr.engine.setAsync(true);
	}
	
	function aa(){
		$("pagesize").disabled="true";
	}
	</script>
	<body onload="aa()">
		<html:form action="/kjfsSet">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="openType" id="openType"
				value="${openType}" />
			<input type="hidden" name="userName" id="userName"
				value="${userName}" />
			<input type="hidden" name="userDep" id="userDep" value="${userDep}" />
			<input type="hidden" name="fdyQx" id="fdyQx" value="${fdyQx}" />
			<input type="hidden" name="userType" id="userType"
				value="${userType}" />
			<input type="hidden" name="jsName" id="jsName" value="${userOnLine}" />
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
			<input type="hidden" name="urls" id="urls" value="/default.jsp" />
			<input type="hidden" name="showShgcKnsTs" id="showShgcKnsTs"
				value="${showShgcKnsTs }" />
			<!-- ������ end-->
			<div class="mainbody">
				<div class="topframe">

					<!-- MENU -->
					<logic:notEqual name="userType" value="stu">
						<!-- TOP -->
						<div class="head">
							<!-- ѧУLOGO -->
							<%@ include file="/homepage/info/logo.jsp"%>
							<!-- ѧУLOGO end-->

							<!-- �û���Ϣ -->
							<%@ include file="/homepage/info/userInfo.jsp"%>
							<!-- �û���Ϣ end-->
						</div>
						<!-- TOP END-->

						<%@ include file="/homepage/info/teaMenu.jsp"%>
					</logic:notEqual>
					<!-- MENU END-->

					<!-- mainframe div -->
					<div class="mainframe">
						<!-- indexmain div -->
						<div class="indexmain">
							   <!-- ��ҳģ�� -->
<%--					      	  <div class="notice" >--%>
<%--					          <h3><span>��ҳģ��	</span></h3>--%>
<%--						     	  <div>--%>
<%--						            <a href="#" target="_self">���ް����ļ�</a>--%>
<%--						          </div>--%>
<%--					          	  <a class="close" title="����"></a>--%>
<%--					      	  </div>--%>
					      	  <!-- ��ҳģ��end -->
					     
					      	<!-- ��ݷ�ʽ���� -->  
							<div class="functionapply">
							
							<!-- ���� -->
							<h2 align="left">
								<span>���Ӧ��</span><font style="font-weight:normal; color:red;">
								<logic:equal name="userType" value="stu">
								(����Ӧ��8������ͼ��)</font>
								</logic:equal>
								<logic:notEqual name="userType" value="stu">
								(����Ӧ��5������ͼ��)</font>
								</logic:notEqual>
							</h2>
							<!-- ������ -->
							<div class="demo_list" >
								<div class="search" align="left">
										<html:select property="fwlb" styleId="fwlb"
														style="width:150px">
											<html:option value="">----�������----</html:option>
											<html:options collection="fwlbList" property="dm"
												labelProperty="mc" />
										</html:select>
									<html:text property="kjfs" styleId="kjfs" />
									<button type="button" class="btn_cx" id="search_go"  
										onclick="allNotEmpThenGo('/xgxt/kjfsSet.do?method=kjfsManage')">
										�� ��
									</button>
								</div>
								<!-- ��ݷ�ʽ ͼƬ����ť -->
								<ul>
								<logic:iterate name="rsList" id="pic" indexId="num">
										<li class="cencel">
											<logic:notEmpty name="pic" property="picpath">
											<p class="pic" title="${pic.bz }">
												<img src="<%=stylePath%>images/blue/54/${pic.picpath }" />
											</p>
											</logic:notEmpty>
											<logic:empty name="pic" property="picpath">
											<p class="pic" title="${pic.bz }">
												<img src="<%=stylePath%>images/blue/54/moren.png" />
											</p>
											</logic:empty>
											<p class="con" title="${pic.mkms }">
												<em>${pic.showmk }</em><span>${pic.showbz }</span>
											</p>
											
											<logic:equal name="pic" property="iskjfs" value="yes">
												<p class="btn" id="p_yy_${num }" style="display: none">
													<button type="button" id="yy_${num }" name="${pic.picpath }"
														onclick="yy(${num })">
														Ӧ ��
													</button>
												</p>
												<p class="btn" id="p_qx_${num }">
													<button type="button" id="qx_${num }" name="${pic.picpath }"
														onclick="qx(${num })">
														ȡ ��
													</button>
												</p>
											</logic:equal>
											<logic:notEqual name="pic" property="iskjfs" value="yes">
												<logic:notEqual name="pic" property="iskjfs" value="none">
												<p class="btn" id="p_yy_${num }">
													<button type="button" id="yy_${num }" name="${pic.picpath }"
														onclick="yy(${num })">
														Ӧ ��
													</button>
												</p>
												<p class="btn" id="p_qx_${num }" style="display: none">
													<button type="button" id="qx_${num }" name="${pic.picpath }"
														onclick="qx(${num })">
														ȡ ��
													</button>
												</p>
												</logic:notEqual>
											</logic:notEqual>
										</li>
									
									<input type="hidden" id="picpath_${num }"
										value="${pic.picpath }" />
									<input type="hidden" id="showmk_${num }" value="${pic.showmk }" />
								</logic:iterate>
								</ul>
								<!-- ��ݷ�ʽ ͼƬ����ť end-->
							</div>
							<!--�����б�-->
							<div class="chooselist" align="left">
								<h3>
									<span>�����б�</span>
								</h3>
								<div class="choosebtn">
								<button type="button" class="chooseup" value="����" onclick="yhup()">
									����
								</button>
								<button type="button" class="choosedown" value="����" onclick="yhdown()">
									����
								</button>
								<button type="button" class="delete" onclick="delKjfs()">
									ɾ��
								</button>
								</div>
								<ul id="mkms_ul" >
									<logic:notEmpty name="yhqxList">
										<logic:iterate name="yhqxList" id="yh" indexId="yhnum">
											<li id="li_${yhnum}">
												<input name="chyh" id="ch_${yhnum}" type="radio"
													value="${yh.picpath }" title="${yh.mkms }" />
												<label>
													${yh.showmk }
												</label>
											</li>
										</logic:iterate>
									</logic:notEmpty>
								</ul>
							</div>
							<!--�����б� end -->
							
							<!--��ҳ-->
							<input type="hidden" name="go" value="a" />
							<button type="button" class="btn_cx" id="search_go" style="display: none"
								onclick="allNotEmpThenGo('/xgxt/kjfsSet.do?method=kjfsManage')">
								�� ѯ
							</button>
							<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=kjfsForm"></jsp:include>
							<script type="text/javascript">
								$('choose').className="hide";
							</script>
							<!-- ѧ���û� -->
							<p class="btn_02">
							<logic:equal name="userType" value="stu">
								<button type="button" class="return" onclick="returnHomPage('')">
									�� ��
								</button>
								<button type="button" onclick="saveKjfs()">
									�� ��
								</button>
							</logic:equal>
							<!-- ��ѧ���û� -->
							<logic:notEqual name="userType" value="stu">
								<button type="button" onclick="saveKjfs()">
									�� ��
								</button>
								<button type="button" class="return" onclick="returnHomPage('ben')">
									�� ��
								</button>
							</logic:notEqual>
							</p>
							<!--��ҳend-->
						</div>
						
						<!-- MENU -->

						<logic:notEqual name="userType" value="stu">
							<!-- BOTTOM-->
							<%@ include file="/homepage/info/companyInfo.jsp"%>
							<!-- BOTTOM end-->
						</logic:notEqual>
					</div>
					<!-- MAIN END -->
					</div>
				</div>
				<!-- ��ʾ��Ϣ -->
				<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
		<%@ include file="/homepage/other/bottom.jsp"%>
	</body>
</html>
