<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>	
		<script type='text/javascript' src='/xgxt/dwr/interface/transferStuinfo.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>	
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script>
	function checkUser()
	{
		var user=document.all['userType'].value;
		if("xy"==user)
		{
			document.getElementById('xy').disabled=true;
		}
		else if("xx"==user)
		{
			document.getElementById('xy').disabled=false;
		}
	}
	
	function checkNullOfStuinfo(){
	var mes = "";
	var mesNull = "";
	var mesExist = "";
	if(confirm('��ȷ��Ҫ���д˲�����\n�˲�������ǰ�꼶Ϊ��ǰ���Ҳ������ڻ�����Ϣ���ѧ��\nת�뵽ѧ��������Ϣ����')){
	transferStuinfo.ckeckNullOfStuinfo(function (data){
		if(data!=null && data.length>0){
			for(var i=0 ; i<4; i++ ){
				if(data[i]!=null && data[i]!=""){
					mesNull += data[i] + "\n";
				}
			}
			if(mesNull!=""){
				mesNull = "�����пյļ�¼��\n" + mesNull;
			}
			for(var i=3; i<6; i++){
				if(data[i]!=null && data[i]!=""){
					mesExist += data[i] + "\n";
				}
			}
			if(mesExist!=""){
				mesExist = "���¼�¼�����ڣ�\n" + mesExist;
			}
			mes = mesNull + mesExist;
			if(mes!=""){
				 alert(mes);
				 return false;
			}else{
				refreshForm('transferStuInfo.do?method=xslsxxToXsjbxx',800,500,false);
				BatAlert.showTips('����ת�룬���Ե�...')
			}
		}
	});
	}
	}
	</script>
	</head>
	
	<body onload="checkUser()">
			<html:form action="/arrangeClass.do?method=arrangeClass" method="post">
			<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm" scope="session"/>"/>
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userSpceType" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable" value="xsjbxxlsb"
							scope="request" />
					<input type="hidden" id="delPk" name="delPk" value="pk" />	
					<input type="hidden" name="zyV" id="zyV" />
					<input type="hidden" name="bjV" id="bjV" />			
				<div class="tab_cur">
					<p class="location" id="title_m">
						<em>���ĵ�ǰλ��:</em><a>
						�ְ��ѧ�� - �������� - �ְࡢ��ѧ��
						</a>
					</p>
				</div>
				 <div class="toolbox">
				 	<logic:equal value="yes" name="writeAble">
						 <div class="buttonbox">
						    <ul>
							<li> <a href="#" onclick="if(confirm('�Ƿ�Ҫ���ɰ༶��Ϣ�����ͬʱ���༶���Ʋ���ѧ��������Ϣ��ʱ���У�')) {refreshForm('arrangeClass.do?method=createClassInfo',600,400); BatAlert.showTips('���ڰ����ù����࣬��ȴ�...');}" class="btn_zj"> �Զ��ְ� </a> </li>
						    <li> <a href="#" onclick="if(confirm('�Ƿ�Ҫ���趨�Ĺ�������ѧ����Ϣ��')) {refreshForm('arrangeClass.do?method=createLearningCode',800,500,false);BatAlert.showTips('���ڰ����ù����ѧ�ţ���ȴ�...');}" class="btn_xg"> ��ѧ�� </a> </li>
							<li> <a href="#" onclick="if(confirm('��ȷ��Ҫ���ѧ����ʱ������Ϣ��')){refreshForm('arrangeClass.do?method=deleteData',800,500,false);BatAlert.showTips('������ձ����Ե�...');}" class="btn_sc"> ��ձ� </a> </li>
						    <li> <a href="#" onclick="checkNullOfStuinfo()" class="btn_sx"> ת��ѧ����Ϣ�� </a> </li>
						    <li> <a href="#" onclick="impAndChkData();" class="btn_dr"> ���� </a> </li>
						    </ul>
						 </div>
					</logic:equal>	
					
				<div class="searchtab">
					<table width="100%" border="0">
					<tfoot>
							<tr>
						 		<td colspan="6" >
									<div class="btn">
				             			<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/arrangeClass.do?method=arrangeClass');">
											��ѯ
										</button>
				                         &nbsp;&nbsp;&nbsp;&nbsp;
				                         <button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
				              	           	 �� ��
				                         </button>
				                    </div>
								</td>
							</tr>
					</tfoot>
							
					<tbody>
							<tr>
								<th align="left">
									�꼶
								</th>
								<td>
									<html:select property="nj" style="width:90px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xymc" style="width:180px" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xymc"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="zymc" style="width:180px" styleId="zy">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zymc"
											labelProperty="zymc" />
									</html:select>									
								</td>
							</tr>
							<tr>							
								<th >								
									ѧ��
								</th>
								<td>
									<html:text property="xh" />
								</td>
								<th>
									 ����
								</th>
								<td>
									<html:text property="xm" />
								</td>
								<td colspan="2">
							</tr>
						</tbody>
					</table>
					</div>
				</div>
				<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	��ѯ���&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">δ�ҵ��κμ�¼��</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs" >
							 <font color="blue">��ʾ��˫��һ�п���ѡ����������ͷ��������</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
				<logic:notEmpty name="rs">
						 <table summary="" id="rsTable" class="dateline" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">									
									<logic:iterate id="tit" name="topTr" offset="0" length="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
									ondblclick="">									
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<bean:write name="v" />
										</logic:iterate>
										<input type="hidden" value="<bean:write name="v" />" />
									</td>
									<td>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
							<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=StudentInfoForm"></jsp:include>
							<script type="text/javascript">
									$('choose').className="hide";
						</script>	
				</logic:notEmpty>
				</div>
				<logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
						alert("�����ɹ���");
						Close();
						document.getElementById('search_go').click();						
					</script>
				</logic:equal>
				<logic:equal name="result" value="false">
					<script>
						alert("����ʧ��!");
					</script>
				</logic:equal>
			</logic:notEmpty>
			</html:form>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
