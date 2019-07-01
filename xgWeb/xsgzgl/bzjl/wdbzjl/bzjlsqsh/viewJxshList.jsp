<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/sqsh/js/jxsh.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.cookie.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript">
		/**
		 * ��ȡ�����������
		 * @returns {___anonymous54_1173}
		 */
		function getDclGird(){
			
			var colList = [
			   {label:'key',name:'sqid', index: 'sqid',hidden:true,key:true},
			   {label:'ѧ��',name:'xh', index: 'xh',width:'12%',formatter:function(cell,rowObject){
				   return "<a href='javascript:void(0);' onclick=\"showDialog('�鿴�����',800,500,'bzjl_sqsh.do?method=viewSqb&sqid="+rowObject["sqid"]+"');\" class='name'>"+cell+"</a>";
			   }},
			   {label:'����',name:'xm', index: 'xm',width:'10%'},
			   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'13%'},
                {label:'�����༶',name:'bjmc', index: 'bjmc',width:'13%'},
			   {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'13%'},
			   <logic:equal name="xxdm" value="10466">
					{label:'�����',name:'qsh',index:'qsh',width:'10%'},
				</logic:equal>
			   {label:'���뽱��',name:'xmmc', index: 'xmdm',width:'12%'},
			   {label:'��������',name:'tzxmmc', index: 'tzxmmc',width:'12%'},
			   {label:'������',name:'xmje', index: 'xmje',width:'10%'},
			   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'19%'}
			];
	 	   if(jQuery('#xxdm').val() == '10264'){//�Ϻ����� ��ʾ����Ǹ�  10264  ����Ǹ���ͨ������ѧ������
	 		  colList.push({label:"����Ǹ���",name:"sfwtgg",index:"sfwtgg",idth:'13%'})
	 	   }
	 	   if(jQuery('#xxdm').val() == '13627'){//�����ʵ��ѧ ���Ի���Ӹ���Ա��
	 		  colList.push({label:"����Ա",name:"fdyxm",index:"fdyxm",idth:'10%'})
	 	   }

			var zcxm = jQuery("[name=zcxm]");
			jQuery.each(zcxm,function(i,n){
				var checked = jQuery(n).prop("checked");
				var xmfsJson = {
						label:jQuery(n).attr("xmmc"),
						name:"fs"+i,
						index:"fs"+i,
						hidden:(!checked)
				};
				var xmpmJson = {
						label:"����",
						name:"pm"+i,
						index:"pm"+i,
						hidden:(!checked)
				};
				colList.push(xmfsJson);
				colList.push(xmpmJson);
				
			});

			if(jQuery("#xxdm").val() == '11527'){
				var zfJson = {
                    label:'�۲��ܷ�',
                    name:'zf',
                    index:'zf'
				};
				var bjpmJson = {
                    label:'�༶����',
                    name:'bjpm',
                    index:'bjpm'
				};
				var zypmJson = {
                    label:'רҵ����',
                    name:'zypm',
                    index:'zypm'
				};
				var xypmJson = {
                    label:'ѧԺ����',
                    name:'xypm',
                    index:'xypm'
				};
                colList.push(zfJson);
                colList.push(bjpmJson);
                colList.push(zypmJson);
                colList.push(xypmJson);
			}


			colList.push({label:'shid',name:'shid', index: 'shid',hidden:true});
			colList.push({label:'splc',name:'splc', index: 'splc',hidden:true});
			colList.push({label:'gwid',name:'gwid', index: 'gwid',hidden:true});
			colList.push({label:'���״̬',name:'shztmc', index: 'shztmc',width:'13%'});
			
			return {
				caption:"����ѧ���б� ",
				pager:"pager",
				url:"bzjl_sqsh.do?method=viewJxshList&type=query&xzdm="+"${xzdm}",
				colList:colList,
				sortname:"nj",
				sortorder:"asc",
				radioselect:false
			};
		}

		 /**
		  * ��ȡ�Ѵ��������
		  * @returns {___anonymous1466_2571}
		  */
		 function getYclGrid(){
		 	
		 	var colList = [
		 	   {label:'key',name:'sqid', index: 'sqid',hidden:true,key:true},
		 	   {label:'ѧ��',name:'xh', index: 'xh',width:'12%',formatter:function(cell,rowObject){
		 		   return "<a href='javascript:void(0);' onclick=\"showDialog('�鿴�����',800,500,'bzjl_sqsh.do?method=viewSqb&sqid="+rowObject["sqid"]+"');\" class='name'>"+cell+"</a>";
		 	   }},
		 	   {label:'����',name:'xm', index: 'xm',width:'10%'},
		 	   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'13%'},
                {label:'�����༶',name:'bjmc', index: 'bjmc',width:'13%'},
                {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'13%'},
		 	    <logic:equal name="xxdm" value="10466">
					{label:'�����',name:'qsh',index:'qsh',width:'10%'},
				</logic:equal>
		 	   {label:'���뽱��',name:'xmmc', index: 'xmdm',width:'13%'},
		 	   {label:'��������',name:'tzxmmc', index: 'tzxmmc',width:'12%'},
		 	   {label:'������',name:'xmje', index: 'xmje',width:'10%'}
		 	];

	 	   if(jQuery('#xxdm').val() == '10264'){//�Ϻ����� ��ʾ����Ǹ� ��ͨ������ѧ�������
	 		  colList.push({label:"����Ǹ���",name:"sfwtgg",index:"sfwtgg",idth:'13%'})
	 	   }
	 	  if(jQuery('#xxdm').val() == '13627'){//�����ʵ��ѧ ���Ի���Ӹ���Ա��
	 		  colList.push({label:"����Ա",name:"fdyxm",index:"fdyxm",idth:'10%'})
	 	   }
		 	var zcxm = jQuery("[name=zcxm]");
		 	jQuery.each(zcxm,function(i,n){
		 		var checked = jQuery(n).prop("checked");
		 		var xmfsJson = {
		 				label:jQuery(n).attr("xmmc"),
		 				name:"fs"+i,
		 				index:"fs"+i,
		 				hidden:(!checked)
		 		};
		 		var xmpmJson = {
		 				label:"����",
		 				name:"pm"+i,
		 				index:"pm"+i,
		 				hidden:(!checked)
		 		};
		 		colList.push(xmfsJson);
		 		colList.push(xmpmJson);
		 		
		 	});

             if(jQuery("#xxdm").val() == '11527'){
                 var zfJson = {
                     label:'�۲��ܷ�',
                     name:'zf',
                     index:'zf'
                 };
                 var bjpmJson = {
                     label:'�༶����',
                     name:'bjpm',
                     index:'bjpm'
                 };
                 var zypmJson = {
                     label:'רҵ����',
                     name:'zypm',
                     index:'zypm'
                 };
                 var xypmJson = {
                     label:'ѧԺ����',
                     name:'xypm',
                     index:'xypm'
                 };
                 colList.push(zfJson);
                 colList.push(bjpmJson);
                 colList.push(zypmJson);
                 colList.push(xypmJson);
             }

		 	colList.push({label:'���ʱ��',name:'shsj', index: 'shsj',width:'19%'});
		 	colList.push({label:'shid',name:'shid', index: 'shid',hidden:true});
		 	colList.push({label:'splc',name:'splc', index: 'splc',hidden:true});
		 	colList.push({label:'gwid',name:'gwid', index: 'gwid',hidden:true});
		 	colList.push({label:'xmdm',name:'xmdm', index: 'xmdm',hidden:true});
		 	colList.push({label:'���״̬',name:'shztmc', index: 'shztmc',width:'13%'});
		 	
		 	return {
		 		caption:"����ѧ���б� ",
		 		pager:"pager",
		 		url:"bzjl_sqsh.do?method=viewJxshList&type=query&xzdm="+"${xzdm}",
		 		colList:colList,
		 		sortname:"nj",
		 		sortorder:"asc",
		 		radioselect:false
		 	};
		 }
			jQuery(function(){
				
				jQuery("[name=zcxm]:not(:disabled)").bind("click",function(){
					var index = jQuery(this).attr("index");
					var liName = jQuery("#tabUl li.ha").attr("clzt");
					
					if ("dcl" == liName){
						var gridSetting = getDclGird();
						jQuery("#dataTable").initGrid(gridSetting);
					} else {
						var gridSetting = getYclGrid();
						jQuery("#dataTable").initGrid(gridSetting);
					}
				});
				
				loadCookie();
				
				var dclGrid = getDclGird();
				var map = getSuperSearch();
					map["shzt"] = "0";
					dclGrid["params"]=map;
				jQuery("#dataTable").initGrid(dclGrid);
			});
			
			//װ��cookie
			function loadCookie(){
				var indexStr = jQuery.cookie("jxshGrid");
				if(indexStr != null && indexStr != undefined){
					var indexArray = indexStr.split("-");
					
					jQuery.each(indexArray,function(i,n){
						
						if (n != ""){
							jQuery("[name=zcxm][index="+n+"]").attr("checked",true);
						}
					});
				}
				
			}
			
			//����cookie
			function setJxshCookie(){
				var chekedZcxm = jQuery("[name=zcxm]:checked:not(:disabled)");
				var indexStr = "";
				
				jQuery.each(chekedZcxm,function(i,n){
					var index = jQuery(n).attr("index");
					indexStr += index+"-";
				});
				jQuery.cookie("jxshGrid",indexStr, { expires: 7});
			}

			//�㽭��ѧ�������
			function zjdxPlsh(){
				
				var ids = jQuery("#dataTable").getSeletIds();
				var map = getSuperSearch();
				var jsonStr = JSON.stringify(map);
				showDialog("�������",750,570,"bzjl_sqsh.do?method=zjdxPlsh&id="+ids.toString()+"&jsonStr="+jsonStr);
			}

			/**
			*	���صǼǱ�
			*/
			function getWord(){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
				 } else if (rows.length > 1){
					var ids = jQuery("#dataTable").getSeletIds();
					var url="xpj_pjxmsq.do?method=getDjbZip&value="+ids + "&actionFrom=sqsh";
					window.open(url);
				 } else {
					var url="xpj_pjxmsq.do?method=getDjbWord&sqid="+rows[0]["sqid"] + "&actionFrom=sqsh";
					window.open(url);
			     }
			}
			
			//�������ܱ�
			function getHzbPrint(){
				var xn_num =  jQuery("a[name=a_name_xn]").length;
				if(xn_num != "1"){
					alertError("��ѡ��һ��ѧ�꣡");
					return false;
				}
				var xmmc_num =  jQuery("a[name=a_name_pjsqxm]").length;
				if(xmmc_num != "1"){
					alertError("��ѡ��һ����Ŀ���ƣ�");
					return false;
				}
				setSearchTj();
				var url="bzjl_sqsh.do?method=getHzbPrint";
				url = addSuperSearchParams(url);
				document.forms[0].action = url;
				document.forms[0].submit();
			}
			
			//�㽭ͬ��ѧ���ɼ����ܵ���
			function xscjhzdc(){
				var url ="bzjl_sqsh.do?method=xscjhzdc";
				
				var xnLength=jQuery("a[name=a_name_xn]").length;
				var xqLength=jQuery("a[name=a_name_xq]").length;
				var xyLength=jQuery("a[name=a_name_xy]").length;
				var bjLength=jQuery("a[name=a_name_bj]").length;
				
				if(xnLength != "1"){
					showAlertDivLayer("��ѡ��һ��ѧ���ѯ������");
					return false;
				}
				if(xqLength != "1"){
					showAlertDivLayer("��ѡ��һ��ѧ�ڲ�ѯ������");
					return false;
				}
				if(xyLength != "1"){
					showAlertDivLayer("��ѡ��һ��ѧԺ��ѯ������");
					return false;
				}
				setSearchTj();
				url = addSuperSearchParams(url);
				document.forms[0].action = url;
				document.forms[0].submit();
			}
			
			//�㽭ͬ��ѧ���ɼ����ܲ鿴
			function xscjhzck(){
				var url ="bzjl_sqsh.do?method=xscjhzck";
				
				var xnLength=jQuery("a[name=a_name_xn]").length;
				var xyLength=jQuery("a[name=a_name_xy]").length;
				var bjLength=jQuery("a[name=a_name_bj]").length;
				
				if(xnLength != "1"){
					showAlertDivLayer("��ѡ��һ��ѧ���ѯ������");
					return false;
				}
				if(xyLength != "1" && bjLength == "0"){
					showAlertDivLayer("��ѡ��һ��ѧԺ��ѯ������");
					return false;
				}
				setSearchTj();
				url = addSuperSearchParams(url);
				document.forms[0].target = '_blank';
				document.forms[0].action = url;
				document.forms[0].submit();
			}

			//�Ͼ��ߵ�ְҵ����ѧУ
			function xsglmddc(){
				setSearchTj();
				var xn_num = jQuery("a[name=a_name_xn]").length;
				var xq_num = jQuery("a[name=a_name_xq]").length;
				var xy_num = jQuery("a[name=a_name_xy]").length;

				if(xn_num != "1"){
					showAlertDivLayer("����ѡ��һ��ѧ�꣬��ֻ��ѡ��һ����");
				}else if (xq_num != "1"){
					showAlertDivLayer("����ѡ��һ��ѧ�ڣ���ֻ��ѡ��һ���� ");
				}else if (xy_num < 1){
					showAlertDivLayer("������ѡ��һ��ѧԺ���е����� ");
				}else{
					var flg = true;
					var yzUrl = 'bzjl_sqsh.do?method=yzxsglmddc';
					
					jQuery.ajaxSetup({async:false});
						ajaxSubFormWithFun("zcxmForm", yzUrl, function(data) {
							if(data["result"] != true){
								flg = false;
							}
						});
						if(!flg){
							showAlertDivLayer("�ޱ����������Ե�����������ѡ��������� ");
							return false;
						}
					jQuery.ajaxSetup({async:true});
					
					var url = "bzjl_sqsh.do?method=xsglmddc";
					url = addSuperSearchParams(url);//���ø߼���ѯ����	
					jQuery("form").eq(0).attr("action", url);
					jQuery("form").eq(0).submit();
				}
			}
		</script>
	</head>

	<body onunload="setJxshCookie();">
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;"	onmousedown ="showHelpDiv()" >ʹ�ð���</a>
			</p>
		</div>
	
		<html:form action="/bzjl_sqsh" method="post" styleId="zcxmForm">
			<input type="hidden" id="xxdm" value="${xxdm }" />
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<input type="hidden" id="shzt" value="0"/>
			<html:hidden styleId="xzdm" property="xzdm" value="${xzdm}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ��ʾ��Ϣ end-->
			<div id="div_help" class="prompt" style="display: none">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					<span>
						������ʹ�ø߼���ѯ����������Ŀ���ƣ����һ�˶�ڣ���������ѡ��ͬһ��λ�ļ�¼����������ˡ�
					</span>
				</p>
				<a class="close" title="����"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- ��ʾ��Ϣ end-->
			
			<div class="toolbox">
					
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<logic:equal value="true" name="cssz" property="kgzt">
								<li id="li_sh">
									<logic:notEqual value="10335" name="xxdm">
										<a href="javascript:void(0);" onclick="viewJxsh();return false;" 
										   title="ѡ����Ҫ��˵ļ�¼������ð�ť���Դ����ҳ�档"
										   class="btn_sh">���</a>
									</logic:notEqual>
									<logic:equal value="10335" name="xxdm">
										<a href="javascript:void(0);" onclick="zjdxPlsh();return false;" 
										   title="����鿴����������ͳ�ơ�"
										   class="btn_sh">�������</a>
									</logic:equal>
								</li>
								<li id="li_qx" style="display: none;">
									<a href="javascript:void(0);" onclick="cancelAuding();return false;" 
									   title="ѡ��һ����¼������ð�ť�����Գ���ʧ�����˲�����"
									   class="btn_qxsh">����</a>
								</li>	
							</logic:equal>
							<li><a href="javascript:void(0);" onclick="viewLcgz();return false;" 
								   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
								   class="btn_cs">���̸���</a></li>
							<logic:equal value="10335" name="xxdm">
								<li><a href="javascript:void(0);" onclick="viewZdtj();return false;" 
									   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
									   class="btn_tj">�������ͳ��</a></li>
							</logic:equal>
							<logic:notEqual value="10335" name="xxdm">
								<li><a href="javascript:void(0);" onclick="viewShqk();return false;" 
									   title="����鿴����������ͳ�ơ�"
									   class="btn_tj">���ͳ��</a></li>
							</logic:notEqual>
						</logic:equal>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
						<logic:equal value="	10279" name="xxdm">
						<li><a href="#" class="btn_dc" onclick="getHzbPrint();return false;">�������ܱ�</a></li>
						</logic:equal>
						<li><a href="javascript:void(0);" class="btn_down" onclick="getWord();return false;">���صǼǱ�</a></li>
						<logic:equal value="12647" name="xxdm">
						<!-- <li><a href="#" class="btn_dc" onclick="xscjhzdc();return false;">ѧ���ɼ����ܵ���</a></li> -->
						<li><a href="#" class="btn_ck" onclick="xscjhzck();return false;">ѧ���ɼ����ܲ鿴</a></li>
						</logic:equal>
						<!-- �Ϻ�Ϸ����Ի� -->
						<logic:equal value="10279" name="xxdm">
							<li><a href="#" class="btn_down" onclick="fjdc();return false;">���ظ���</a></li>
						</logic:equal>
						<!-- �Ͼ��ߵ�ְҵ����ѧУ -->
						<logic:equal value="10874" name="xxdm">
							<li><a href="#" class="btn_down" onclick="xsglmddc();return false;">�����������</a></li>
						</logic:equal>
					</ul>
				</div>
				
			</div>
		
		<!-- �������� -->	
			<%@ include file="/comm/search/superSearchArea.jsp"%>
		<!-- �������� end-->
		<div class="comp_title" id="comp_title">
	      <ul style="width:90%" id="tabUl">
			<li class="ha" clzt="dcl"><a href="javascript:void(0);"  onclick="chageShzt(this,'0');"><span>������</span></a></li>
			<li clzt="ycl"><a href="javascript:void(0);"  onclick="chageShzt(this,'1');"><span>�Ѵ���</span></a></li>
	      </ul>
	    </div>
	    	 
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>���������б� </span>
			</h3>

			<logic:notEqual name="xxdm" value="11527">
				<logic:present name="zcxmList">
					<logic:iterate id="z" name="zcxmList" indexId="i">
						<logic:equal value="N" name="z" property="fjdm">
							<input type="checkbox" xmmc="${z.xmmc }" value="${z.xmdm }" checked="checked" disabled="disabled" name="zcxm"/> ${z.xmmc }
						</logic:equal>

						<logic:notEqual value="N" name="z" property="fjdm">
							<input type="checkbox" xmmc="${z.xmmc }" value="${z.xmdm }" name="zcxm" index="${i }"
							/> ${z.xmmc }
						</logic:notEqual>
					</logic:iterate>
				</logic:present>
			</logic:notEqual>

			<div class="con_overlfow">
			<table id="dataTable"></table>
			<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
