<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
		//��ѯ����
		function searchRs(){
			if(yzshgw()){
				var url = "wjcfCfssgl_cfsssh.do?method=cfssshCxSj";

				var ie = "ie";
				var stylePath = "<%=stylePath %>";
				var otherValue = [ie,stylePath];
	 			var xtgwid=jQuery("#xtgwid").val();
	 			if("" != xtgwid){
	 				otherValue=[ie,stylePath,xtgwid];
	 	 		}

				searchRsByAjax(url,otherValue);

				setTimeout("setDivHeight()","1000")
			}else{
				setPage();
			}
		}

		//�������
		function ssshDg(){
			var objs = jQuery("input[type=checkbox][name=checkVal]:checked");
			var pkValues="";
			if(objs.length!=1){
				alertError("��ѡ��һ����¼��");
				return false;
			}else{
				pkValues+=objs[0].value;
			}
			var xtgwid=jQuery("#xtgwid").val();
			var url="wjcfCfssgl_cfsssh.do?method=cfssshSh&pkValue="+pkValues+"&xtgwid="+xtgwid;
			//showTopWin(url,780,660);
			showDialog("", 800, 500, url);
		}

		//�������
		function ssshPl(){
			var checkBoxArr = document.getElementsByName("checkVal");
			var pkValue="";
			var len=0;
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					if(pkValue==""){
						pkValue=checkBoxArr[i].value;
					}else{
						pkValue=pkValue+"@@"+checkBoxArr[i].value;
					}
					len++;
				}
			}
			if(len==0){
				alertError("�빴ѡϣ����˵ļ�¼��");
				return false;
				}
			var xtgwid=jQuery("#xtgwid").val();
			var url="wjcfCfssgl_cfsssh.do?method=cfssshPlsh&pkValue="+pkValue+"&xtgwid="+xtgwid;
			//showTopWin(url,800,600);
			showDialog("", 800, 210, url);
		}

		//��ʼ��ҳ��
		function setPage(){

			var xtgwid=jQuery("#xtgwid").val();

			if (xtgwid == ""){
				jzSpgw();
			} else {
				searchRs();
			}
		}

		//����������λ
		function jzSpgw(){
			var url="wjcfCfssgl_cfsssh.do?method=spgwCx";


			jQuery("#div_spgw").load(url,parameter,function(){
				var qhshgw=jQuery("#qhshgw");
				var len=jQuery("[name=spgws]").length;
				if(len==1 || qhshgw.length==0){
					$("xtgwid").value=jQuery("[name=spgws]:checked").val();
					searchRs();
					
				}else{
					tipsWindown("ϵͳ��ʾ","id:div_spgw","300","170","true","","true","id");
				}
			});
			
		}

		//���ȷ�����ѯ
		function checkSpgw(){
			jQuery("#xtgwid").val(jQuery("[name=spgws]:checked").val());
			searchRs();
			closeWindown();
		}

		//�л���˸�λ
		function qhgw(){
			tipsWindown("ϵͳ��ʾ","id:div_spgw","300","170","true","","true","id");
		}

		//�����ύ
		function sstj(){
			var url="wjcfCfssgl_cfsssh.do?method=cfssshTj";
			//showTopWin(url,400,250);
			
			showDialog("", 500, 350, url);
		}
		
		//��֤��˸�λ
		function yzshgw(){
			if(jQuery("#xtgwid").val()==""){
				return false;
			}
			return true;
		}
		</script>
	</head>
	<body onload="setPage();">
		<html:form action="/wjcfCfssgl_cfsssh.do?method=cfssshCx" method="post">
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
				<html:hidden property="xtgwid" styleId="xtgwid"/>
			</div>
			
			<!-- ��ʾ��Ϣ end-->	
			<!-- ģ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>						
						<li><a href="#" onclick="ssshDg();return false;" class="btn_sh"> ��� </a></li>
						<li><a href="#" onclick="ssshPl();return false;" class="btn_sh"> ������� </a></li>
						<logic:present name="spgwList">
							<logic:notEmpty name="spgwList">
								<logic:iterate id="spgw" name="spgwList" offset="1" length="1">
									<li><a href="#" onclick="jzSpgw();return false;" id="qhshgw" class="btn_sz"> �л���˸�λ</a></li>
								</logic:iterate>
							</logic:notEmpty>
						</logic:present>
						
						<logic:equal name="isZgjyh" value="true">
							<li><a href="#" onclick="sstj();return false;" class="btn_shtg"> �ύ </a></li>
						</logic:equal>
						
					</ul>
				</div>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
			<div class="formbox" id="cxjg">
				<h3 class="datetitle_01">
					<span>
						��ѯ���&nbsp;&nbsp;<font color="red">���߳��������ݲ������޸ġ�</font>
					</span>
				</h3>
				<div id="div_rs"
					style="width:100%;height:380px;overflow-x:auto;overflow-y:hidden;">
				</div>
				
				<!--��ҳ��ʾ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=wjcfCfssglForm"></jsp:include>
				<!--��ҳ��ʾ-->
				
			</div>
			<!-- ������λ-->
			<div id="div_spgw" style="display:none"></div>
			<!-- ������λ-->
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
		<div id="tmpdiv1" style="display:none">
				<iframe name='mainFrame' style='height:250px; width:400px; '
					marginwidth='0' marginheight='0' framespacing='0' frameborder='0'
					scrolling='yes'
					src=''></iframe>
		</div>
		<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					alertInfo("����ʧ�ܣ�");
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					alertInfo("�����ɹ���");
					</script>
				</logic:equal>
		</logic:notEmpty>
		
	</body>
</html>
