<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xszz/jtcy.js"></script>
		<script language="javascript" src="js/xszz/xszzComm.js"></script>
		<script language="javascript" src="js/xszz/xszzFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getXszzInfo.js'></script>
		<script language="javascript" src="js/xszz/xszzInit.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script language="javascript" src="js/xszz/xszzOptionList.js"></script>	
		<script language="javascript">
		//��ӡ����
		function printBb(url){
			
			var ie = 'ie';

			if(ie == "5.6"){
				confirmInfo("����IE�汾δIE6���汾���ͣ����ܵ��´�ӡ��ʽ���⣬����ʹ��IE8�������Ƿ�Ҫ�ڹ�������������ȡ�����Լ����ñ��������ӡ��",askInfo);
			}else{
				document.forms[0].action = url;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";
				
				var xmdm = $("xmdm").value;
			}
		}
		
		//ѯ����Ϣ
		function askInfo(tag){
			if(tag == "ok"){
				document.forms[0].action = "http://www.microsoft.com/downloads/zh-cn/details.aspx?FamilyID=341c2ad5-8c3d-4347-8c03-08cdecd8852b";
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";
				
				var xmdm = $("xmdm").value;
			}else{
				document.forms[0].action = "commPrint.do?method=printJsp";
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";
				
				var xmdm = $("xmdm").value;
			}
		}
		
		//������Ŀ����
		function saveXmjg(bclx){
			var mklx = $("mklx").value;
			var url = "/xgxt/commXszz.do?method=jgcxUpdate&doType=save&bclx="+bclx;
			var xh = $("xh").value;
			if(xh == ""){
				alert("����ѡ��ϣ�����ӵ�ѧ����");
				return false;
			}
			if (confirm("ȷ����������?")) {	
				saveUpdate(url,'xh');
			}
		}
		
		//���Ƹ�����Ŀ���ܴ�ӡ����
		function disBtn(){
			var xmdm = $("xmdm").value;
			//����ѧ�����������Ϣ
			if(xmdm == "5005"){
				$("printBtn").disabled = true;
			}
		}
		
		//�鿴ѧ���ɼ�
		function showXscjXx(){
			var xh = "";
			var xmdm = "";
			var doType="";
			if($("xh") && $("xh").value !=""){
				xh = $("xh").value;
			}else{
				alert("��ȷ����Ҫ�鿴�ɼ���ѧ����");
				return false;
			}
			
			if($("xmdm") && $("xmdm").value !=""){
				xmdm = $("xmdm").value;
			}else{
				alert("��ȷ����Ҫ�鿴�ɼ�����Ŀ��");
				return false;
			}
			
			if($("doType")){
				doType=$("doType").value;
			}
			
			
			var url = "/xgxt/xszzXszj.do?method=xscjManage";
				url += "&xmdm="+xmdm;	
			if(doType=="update"){
				url += "&sqsj="+$("sqsj").value;
			}	
			url += "&doType="+doType;
			url += "&xh="+xh;
			showTopWin(url,400,300);
		}
		function  upload(url){
			showTopWin(url,600,50);
		}
		function delFile(){
			   var sqsj = $('sqsj').value;
			   var xh = $('xh').value;
			   getXszzInfo.filePathDel(xh,sqsj,function(data){
					if(data){
						document.getElementById("sc").style.display="";
						$('xzfj').style.display="none";
						alert("ɾ�������ɹ�");
					}
				});
			   
		}
				
		</script>
	</head>

	<body onload="onShow_xmjg();xszzInit();disBtn();">
		<!-- ���� -->
		<!-- ���� end-->
		<html:form action="/commXszz">
			<!-- ������ -->
			<html:hidden property="iskns" styleId="iskns"/>
			<input type="hidden" value="${knsdl }" id="knsdl"/>
			<input type="hidden" value="${jtqkdc }" id="jtqkdc"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="url" name="url" value="/xgxt/commXszz.do?method=jgcxUpdate&doType=add&iskns=${xszzTyForm.iskns }"/>
			<input type="hidden" id="xxdm" value="${xxdm }"/>
			<input type="hidden" name="save_xh" id="xh" value="${xmInfo.xh }"/>
			<input type="hidden" name="xmb" id="xmb" value="${xmInfo.xmb }"/>
			<input type="hidden" name="save_sqsj"  id="sqsj" value="${xmInfo.sqsj }"/>
			<input type="hidden" name="save_xmdm" id="xmdm" value="${xmInfo.xmdm }"/>
			<input type="hidden" name="mrxm" id="mrxm" value="${xmInfo.mrxm }"/>
			<input type="hidden" name="doType" id="doType" value="${doType}"/>
			<!-- ��ϵ -->
			<html:select property="gx" styleClass="select" style="display : none" styleId="gx">
				<html:options collection="gxList" property="dm" labelProperty="mc" />
			</html:select>
			<!-- ������ end-->
			<!-- ��Ŀ������� -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<tr style="height: 23px">
					<td align="center" colspan="4">
						<font size="5">
						<logic:equal name="xmInfo" property="sqzq" value="ѧ��">
							${xmInfo.xn }ѧ��
							<input type="hidden" name="save_xn" value="${xmInfo.xn }"/>
						</logic:equal>
						<logic:equal name="xmInfo" property="sqzq" value="���">
							${xmInfo.nd }���
							<input type="hidden" name="save_nd" value="${xmInfo.nd }"/>
						</logic:equal>
						<logic:equal name="xmInfo" property="sqzq" value="ѧ��">
							${xmInfo.xn }ѧ��
							${xmInfo.xqmc }ѧ��
							<input type="hidden" name="save_xq" value="${xmInfo.xq }"/>
							<input type="hidden" name="save_xn" value="${xmInfo.xn }"/>
						</logic:equal>
						${xmInfo.xmmc } ����
						</font>
					</td>
				</tr>
			</table>
			<logic:notEmpty name="xmnrList">
				<logic:iterate name="xmnrList" id="xmnr">
					<!-- ѧ��������Ϣ -->
					<logic:equal name="xmnr" property="mk" value="view_xsjbxx">
						<%@ include file="sqsh/xsInfo.jsp"%>
					</logic:equal>
					<!-- ѧ��������Ϣ end-->
					
					<!-- �������϶���Ϊ����ģ�飬�����������϶������˼�ͥ���������Ŀ -->
					
					<logic:equal name="xmInfo" property="xmb" value="xszz_knsb">
						<logic:equal value="yes" property="iskns" name="xszzTyForm">
							<logic:equal value="true" name="knsdl">
								<logic:equal value="true" name="jtqkdc">
									<logic:equal name="xmnr" property="mk" value="xmxgInfo">
										<%@ include file="sqsh/jtcyInfo.jsp"%>
									</logic:equal>
								</logic:equal>
							</logic:equal>
						</logic:equal>
					</logic:equal>
					
					<!-- ��ͥ��� -->
					<logic:equal name="xmnr" property="mk" value="jtqkdcb">
						<%@ include file="sqsh/jtqkInfo.jsp"%>
					</logic:equal>
					<!-- ��ͥ��� end-->
					<!-- �۲�����ʲ��� -->
					<logic:equal name="xmnr" property="mk" value="zcf">
						<%@ include file="sqsh/zcf.jsp"%>
					</logic:equal>
					<!-- �۲�����ʲ��� end-->
					<!-- ������Ϣ -->
					<logic:equal name="xmnr" property="mk" value="xsqtxxb">
						<%@ include file="sqsh/qtInfo.jsp"%>
					</logic:equal>	
					<!-- ������Ϣ end-->	
					<!-- ��������Ϣ -->
					<logic:equal name="xmnr" property="mk" value="xszz_knsb">
						<%@ include file="sqsh/knInfo.jsp"%>
					</logic:equal>	
					<!-- ��������Ϣ end-->
					<!-- ��Ŀ��� -->
					<logic:equal name="xmnr" property="mk" value="xmxgInfo">
						<%@ include file="sqsh/wsInfo.jsp"%>
					</logic:equal>
					<!-- ��Ŀ��� end-->				
					<!-- ��˽�� -->
					<logic:equal name="xmnr" property="mk" value="shInfo">
						<%@ include file="sqsh/jgInfo.jsp"%>
					</logic:equal>
				</logic:iterate>	
				
				<logic:equal name="xspjpy" value="true">
				<%@ include file="sqsh/pjpyInfo.jsp"%>
				</logic:equal>
				<logic:equal name="xxdm" value="10058">
				<logic:equal name="xmdm" value="5002">
				<input type="hidden" id="scdz" name="save_scdz" readonly="readonly" />
				<input type="hidden" name="scdz" value="${file }" readonly="readonly"/>
			    <input type="hidden" name="scdz" value="${filePath }"/>
				<input type="hidden" id="scdz" name="save_scdz" readonly="readonly" />
			    <table class="formlist" border="0" align="center" style="width: 100%">
					    <tr  id="sc"  <logic:notEmpty name="file">style="display:none"</logic:notEmpty>>
					       <th  width="15%">
					         <a href="#"  onclick="upload('/xgxt/commXszz.do?method=uploadFile')"><u>����ϴ��ļ�</u></a>
					       </th>
					       <td></td>
					    </tr>
					    <tr id="xzfj" <logic:empty name="file">style="display:none" </logic:empty> >
					            <th width="15%" align="left">
									���ظ�����
								</th>
								<td  align="left"> 
									<input type="hidden" name="scdz" value="${filePath }"/>
									<a href="commXszz.do?method=downLoad&dir=${file }" target="_blank" >�������</a>
									<logic:notEqual name="doType" value="view">
									&nbsp;&nbsp;
									<a href="#" onclick="delFile()" >���ɾ��</a>
									</logic:notEqual>
								</td>
						</tr>
				</table>		
				</logic:equal>
				</logic:equal>
				
				
			    </logic:notEmpty>
			<table border="0" class="formlist" align="center" style="width: 100%">
				<tfoot>
					<tr>
						<td align="center">
						<div class="bz"><span class="red">ע������ӡ��ʽ�����⣬����ʹ��IE8���г���</span></div>
							<div class="btn">
								
								<logic:equal name="doType" value="add">
									<button type="button" id="buttonSave" onclick="saveXmjg('add');return false;" style="width: 80px">
										��	��
									</button>
									&nbsp;&nbsp;							
								</logic:equal>
								<logic:equal name="doType" value="update">
									<logic:equal name="canEdit" value="yes">
										<button type="button" id="buttonSave" onclick="saveXmjg('update');return false;" style="width: 80px">
											��	��
										</button>
									</logic:equal>
									&nbsp;&nbsp;							
								</logic:equal>
								<button type="button" id="printBtn" onclick="printBb('commPrint.do?method=printJsp')" >
									<logic:equal name="xxdm" value="12046">
										<logic:equal value="xszz_knsb" name="xmInfo" property="xmb">
											��ӡ�����
										</logic:equal>
										<logic:notEqual value="xszz_knsb" name="xmInfo" property="xmb">
											��   ӡ
										</logic:notEqual>
									</logic:equal>
									<logic:notEqual name="xxdm" value="12046">
										��   ӡ
									</logic:notEqual>
								</button>
								&nbsp;&nbsp;
								<logic:equal name="xxdm" value="12046">
									<logic:equal value="xszz_knsb" name="xmInfo" property="xmb">
										<button type="button" id="printBtn" onclick="printBb('commPrint.do?method=printKnsrd&xh=${xmInfo.xh }');return false;">
											��ӡ�϶���
										</button>
									</logic:equal>
								</logic:equal>
								<button type="button" id="buttonSave" onclick="window.close();return false;" style="width: 80px">
									��	��
								</button> 
								<logic:equal name="xxdm" value="10491">
									&nbsp;&nbsp;
									<button type="button" id="buttonSave" onclick="showXscjXx();return false;" style="width: 80px">
										��  ��
									</button>
								</logic:equal>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="other/tsxx.jsp"%>
		</html:form>
	</body>
</html>