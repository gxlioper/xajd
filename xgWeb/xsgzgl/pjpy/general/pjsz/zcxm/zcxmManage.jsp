<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<style type="text/css">
			.OrgBox{
				font-size:12px;
				padding:5px 5px 5px 5px;
				clear:left;
				float:left;
				text-align:center;
				position:absolute;
				background:url(/xgxt/pictures/pjpy/zhcp/organization_bg.gif) repeat-x left top;
				width:55px;
				height:106px;
				border:#adc8dc 1px solid;
				border-width:1px 2px 2px 1px;
			}
			.OrgBox img{
				width:60px;
				height:70px;
			}
			.OrgBox div{
				padding:5px 0;
				color:#08487e;
				font-weight:bold;
			}
			.OrgBox div span{
				color:#08487e;
				font-weight:bold;
				}
			.OrgBox input{
				background:none;
				border:1px solid #adc8dc;
				padding:0;
				margin:0;
				text-align:center;
			}
		</style>
		<link rel="stylesheet" type="text/css" href="js/jquery/plugins/ringhtMenu/rightMenu.css" /> 
	       
	    <script type="text/javascript" src="js/jquery/plugins/ringhtMenu/contextMenu.js"></script>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/comm/treeFrame.js"></script>
		<script language="javascript" src="js/xsgzgl/pjpy/pjpyComm.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		function onShow(){
			setTimeout('initZcxm()',100);
			setTimeout('initRight()',1000);
		}
		
		//��ʼ���۲���Ŀ
		function initZcxm(){
			//�ϼ���������
			var sjdmArr=document.getElementsByName("sjdmArr");
			//�۲⼶��
			var zcjb=sjdmArr.length-1;
			//�۲���Ŀ
			var xmdm=document.getElementsByName("xmdm");
			var sjdm=document.getElementsByName("sjdm");
			var xmmc=document.getElementsByName("xmmc");
			var jjf=document.getElementsByName("jjf");
			
			//�۲���Ŀ��ϸ��Ϣ
			var xmdmArr=document.getElementsByName("xmdmArr");
			var bldmArr=document.getElementsByName("bldmArr");
			var blmcArr=document.getElementsByName("blmcArr");
			var blArr=document.getElementsByName("blArr");
			var xmsjdmArr=document.getElementsByName("xmsjdmArr");
			
			var node=new Array();
			for(i=0;i<xmdm.length;i++){
				node[i]=new OrgNode();
			}
			
			var str=new Array();
			for(i=0;i<xmdm.length;i++){
				var xmm=xmmc[i].value;
				//��Ŀ�����޸��ֶ�(�����޸��۲���)
				var text ="<span id='span_xmmc_"+i+"'>";
					text+="<a href=\"#\" name=\"a_zcxm\" id=\"a_"+xmdm[i].value+"\"onclick=\"return false;\">";
					text+="<font color=\"blue\">"
					text+=xmm;
					text+="</font>";
					text+="</a>";
					text+="(";
					text+=jjf[i].value;
					text+=")";
<%--					text+="<br/>";--%>
<%--					text+="�Ӽ��֣�";--%>
<%--					if(jjf[i].value == "-"){--%>
<%--						text+="�ӷ�";--%>
<%--					}else{--%>
<%--						text+="����";--%>
<%--					}--%>
					text+="</span>";
					text+="<input type='text' style='width:80px'";
					text+="name='zcxmmcArr' id='text_xmmc_"+i+"' value='"+xmm+"'";
					text+="style='display:none' onblur=\"checkXmmc('text_xmmc_"+i+"')\"/>";
					
				node[i].customParam.EmpName=text;
				str[i]="";
				for(j=0;j<xmdmArr.length;j++){
					if(xmdm[i].value==xmdmArr[j].value
						&& (xmsjdmArr[j].value==null || xmsjdmArr[j].value=="")){
						var bl=blArr[j].value;			
						str[i]+="<div><span id='span_bl_"+j+"'></span><input type='hidden' style='width:30px' name='zcblArr' id='text_bl_"+j+"' value='' style='display:none'></div>";
					}else if(xmdm[i].value==xmdmArr[j].value){
						var bl=blArr[j].value;
						if(bl==""){
							bl="0";
						}
						str[i]+="<div>"+blmcArr[j].value+"��<span id='span_bl_"+j+"'>"+bl+"</span><input type='text' style='width:30px;display:none' name='zcblArr' id='text_bl_"+j+"' value='"+blArr[j].value+"'  onkeydown=\"return onlyNum(this,3);\" onmousedown=\"return onlyNum(this,3);\">%</div>";
					}
				}
				node[i].customParam.inputV=str[i];
			}
			
			
			for(j=0;j<xmdm.length;j++){
				for(i=0;i<sjdmArr.length;i++){
					if(xmdm[j].value==sjdmArr[i].value){
						
						for(z=0;z<xmdm.length;z++){
							if(sjdm[z].value==xmdm[j].value){
								node[j].Nodes.Add(node[z]);						
								
							}
						}
					}
				}
			}
			
			var OrgShows=null;
			for(i=0;i<sjdm.length;i++){
				
				if(sjdm[i].value==null || sjdm[i].value==""){
					
					OrgShows=new OrgShow(node[i]);
					break;
				}
			}
			
			OrgShows.Top=135;
			OrgShows.Left=85;
			OrgShows.IntervalWidth=10;
			OrgShows.IntervalHeight=20;
		
			OrgShows.ShowType=2;
			OrgShows.BoxHeight=100;
			OrgShows.BoxTemplet="<div id=\"{Id}\" class=\"OrgBox\" ><span>{EmpName}</span><br/><br/><br/>{inputV}</div>"
			OrgShows.Run();
		}

		function initRight(){
			var imageMenuData = [
			    [{
			        text: "�����¼���Ŀ",
			        func:function(){
				    	var id = jQuery(this).attr('id');
			        	var xmdm = id.split("_")[1];
			        	jQuery("#czxm").val(xmdm);
			        	var xmjb = jQuery("#xmjb_"+xmdm).val();
			        	if(xmjb == "3"){
							alertError("����Ŀ�Ѿ���<font color='blue'>������Ŀ</font>�ˣ������ٶ�������¼���Ŀ");
							return false;
			        	}else{
			        		showAddZcxmDiv();
			        	}
			        }
			    }, {
			        text: "�޸ı�����Ŀ",
			        func: function() {
				    	var id = jQuery(this).attr('id');
			        	var xmdm = id.split("_")[1];
			        	jQuery("#czxm").val(xmdm);
			        	showEditZcxmDiv();
			        }
			    }, {
			        text: "ɾ��������Ŀ",
			        func: function() {
				    	var id = jQuery(this).attr('id');
			        	var xmdm = id.split("_")[1];
			        	jQuery("#czxm").val(xmdm);
			        	
			        	var mrxm = jQuery("#mrxm_"+xmdm).val();

			        	if(mrxm == "yes"){
							alertError("����ĿΪ<font color='blue'>Ĭ����Ŀ</font>������ɾ����������������ϵ���������");
							return false;
			        	}else{
			        		confirmInfo('����ȷ���Ƿ�ɾ������Ŀ?<br/>ע���������Ŀӵ������Ŀ�Ļ�������һ��ɾ��',deleteZcxm);
			        	}
			        }
			    }]
			];

			jQuery("a[name=a_zcxm]").smartMenu(imageMenuData, {name: "a"});
		}
		
		jQuery(function(){
			
			jQuery(this).not(jQuery("a[name=a_zcxm]")).mouseup(function(){
				
				jQuery("#smartMenu_a").attr("style","display:none");
			});
			

			onShow();

		})
		</script>
	</head>
	<body >
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>�������� - �������� - �۲���Ŀ����</a>
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
				1.�������Ϊĳ��Ŀ�����¼���Ŀ����ִ��<font color="blue">�����¼���Ŀ</font>��
				2.��������޸�ĳ��Ŀ�������Ϣ����ִ��<font color="blue">�޸ı�����Ŀ</font>��<br/>
				3.�������ɾ��ĳ��Ŀ����ִ��<font color="blue">ɾ��������Ŀ</font>(ϵͳ�ṩ��<font color="blue">Ĭ����Ŀ</font>����ɾ��)��<br/>
				4.<font color="blue">(+)</font>�������Ŀ���ڼӷ��<font color="blue">(-)</font>��������Ŀ�Ǽ����
				5.��Ŀ������ָ����Ŀ������<font color="blue">�ϼ���Ŀ</font>����ռ�ı�����<br/>
				6.���<font color="blue">�Ҽ����</font>��Ŀ���ƣ�����ִ����ز�����<br/>
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<logic:iterate name="zcxmList" id="zcxm">
				<input type="hidden" name="xmdm" id="xmdm_${zcxm.xmdm}" value="${zcxm.xmdm}" />
				<input type="hidden" name="xmmc" id="xmmc_${zcxm.xmdm}" value="${zcxm.xmmc}" />
				<input type="hidden" name="xmjb" id="xmjb_${zcxm.xmdm}" value="${zcxm.xmjb}" />
				<input type="hidden" name="sjdm" id="sjdm_${zcxm.xmdm}" value="${zcxm.sjdm}" />
				<input type="hidden" name="mrxm" id="mrxm_${zcxm.xmdm}" value="${zcxm.mrxm}" />
				<input type="hidden" name="jjf" id="jjf_${zcxm.xmdm}" value="${zcxm.jjf}" />
<%--				<input type="hidden" name="lrly" id="lrly_${zcxm.xmdm}" value="${zcxm.lrly}" />--%>
			</logic:iterate>

			<logic:iterate name="zcblList" id="zcbl">
				<input type="hidden" name="xmdmArr" id="xmdm_${zcbl.xmdm}_${zcbl.bldm}" value="${zcbl.xmdm}" />
				<input type="hidden" name="xmsjdmArr" id="sjdm_${zcbl.xmdm}_${zcbl.bldm}" value="${zcbl.sjdm}" />
				<input type="hidden" name="bldmArr" id="bldm_${zcbl.xmdm}_${zcbl.bldm}" value="${zcbl.bldm}" />
				<input type="hidden" name="blmcArr" id="blmc_${zcbl.xmdm}_${zcbl.bldm}" value="${zcbl.blmc}" />
				<input type="hidden" name="blArr" id="bl_${zcbl.xmdm}_${zcbl.bldm}" value="${zcbl.bl}" />
			</logic:iterate>
			
			<logic:iterate name="sjdmList" id="sjdm">
				<input type="hidden" name="sjdmArr" value="${sjdm.sjdm}" />
			</logic:iterate>
			
			<!-- ������Ŀ -->
			<input type="hidden" id="czxm" />
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!-- ҳ����Դ -->
						<logic:equal name="forward" value="jbsz">
							<li>
								<a href="#" onclick="goPjpyJbsz();return false;" class="btn_fh">
									��������
								</a>
							</li>
						</logic:equal>
						<!-- ҳ����Դend -->
					</ul>
				</div>
				<!-- ��ť end-->
				
			</div>
			
			<!-- �۲���Ŀ���õ����� -->
			<div id="div_zcxm" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�۲���Ŀ����</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									��Ŀ����
								</th>
								<td width="">
									<input type="text" id="input_xmmc" maxlength="5" style="width:80px;"/>
									<input type="hidden" id="hidden_xmmc" maxlength="5"/>
									<font color="blue">(����¼��5��)</font>
								</td>
							</tr>
							<tr>
								<th>
									�Ӽ���
								</th>
								<td>
									<select id="input_jjf" style="width:80px;">
										<option value="+">�ӷ�</option>
										<option value="-">����</option>
									</select>
								</td>
							</tr>
<%--							<tr>--%>
<%--								<th>--%>
<%--									�Ƿ���Ҫά������--%>
<%--								</th>--%>
<%--								<td width="">--%>
<%--									<select id="input_lrly">--%>
<%--										<option value="no">��</option>--%>
<%--										<option value="yes">��</option>--%>
<%--									</select>--%>
<%--								</td>--%>
<%--							</tr>--%>
							<!-- �۲���Ŀ -->
							<logic:iterate name="bldmList" id="blMap">
								<tr>
									<th>
										${blMap.blmc}
									</th>
									<td>
										<input type="hidden" name="bldm" id="input_bldm_${blMap.bldm}" value="${blMap.bldm}"/>
										<input type="text" name="bl" id="input_bl_${blMap.bldm}"
											onkeydown="return onlyNum(this,5)"
											onmousedown="return onlyNum(this,5)"
											maxlength="5"
											style="width:80px;ime-mode:disabled"
										/>%<font color="blue">(������������)</font>
									</td>
								</tr>
							</logic:iterate>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<button type="button"  id="btn_add_save" onclick="checkSaveNextZcxm()">
											�� ��
										</button>
										
										<button type="button"  id="btn_edit_save" onclick="checkSaveEditZcxm()">
											�� ��
										</button>
										
										<button type="button"  onclick="closeWindown();return false;">
											��  ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- ����С�����õ����� end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>		
		</html:form>
	</body>
</html>