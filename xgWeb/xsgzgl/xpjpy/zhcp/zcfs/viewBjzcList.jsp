<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zhcp/zcfs/js/zcfs.js"></script>
		<script type="text/javascript" src="js/json.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="js/comm/message.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"�۲�༶�б�",
				pager:"pager",
				url:"xpj_zcfs.do?method=viewBjzcList&doType=query",
				colList:[
				   {label:'key',name:'id', index: 'id',hidden:true,key:true},
				   {label:'�꼶',name:'nj', index: 'nj',width:'8%'},
				   <%--{label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'24%'},--%>
                    {label:'��Ժ',name:'symc', index: 'sydm',width:'24%'},
//				   {label:'רҵ',name:'zymc', index: 'zydm',width:'23%'},
				   {label:'�����༶',name:'bjdm', index: 'bjdm',hidden:true},
				   {label:'�����༶',name:'bjmc', index: 'bjdm',width:'17%'},
				   {label:'�༶����',name:'bjrs', index: 'bjrs',width:'8%',
					formatter:function(cellValue,rowObject){
								var html = jQuery("<a href='javascript:void(0);' class='name'>"+cellValue+"</a>");
								html.bind("click",function(){
									showDialog("�鿴�۲��",700,500,"xpj_zcfs.do?method=viewZcfs&id="+rowObject["id"]);
								});
								return html;
							 }
				   }
				   /*xg_pjpy_new_cspzb szyf = 1 �����ύ�ˣ��ύ״̬*/
				   <logic:notEqual name="szyf" value="1">
				   ,{label:'�ύ��',name:'tjrxm', index: 'tjr',width:'10%'},
				   {label:'�ύ״̬',name:'tjzt', index: 'tjzt',hidden:true},
				   {label:'�ύ״̬',name:'tjztmc', index: 'tjzt',width:'10%'}
				   </logic:notEqual>
				  
				],
//				sortname: "tjzt,nj,xydm,zydm",
                sortname: "tjzt,nj,sydm",
			 	sortorder: "desc"
			};
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			//�۲��ύͳ��
			function zctjtj(){
				var url = "xpj_zcfs.do?method=getZcfAyTjTjcxList";
				document.location.href = url;
			}
			function initCpzCpmd(){
				showConfirmDivLayer("�Ƿ�ȷ�ϳ�ʼ����ǰѧ��ѧ�ڲ������������ù��̲����棩",{"okFun":function(){
					var tips = loading();	
					tips.show();
					jQuery.post("xpj_zcfs.do?method=initCpzCpmd",{},function(data){
						tips.close();
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							jQuery("#dataTable").reloadGrid();
						}});
					},"json");
				}});
			}
            function tbxfcj() {
                showConfirmDivLayer("�Ƿ�ͬ��ѧ�ֳɼ�", {
                    "okFun": function () {
                        var url = "xpj_zcfs.do?method=tbXfcj";
                        jQuery.post(url, {}, function (data) {
                            showAlertDivLayer(data["message"], {}, {
                                "okFun": function () {
                                }
                            });
                            jQuery("#dataTable").reloadGrid();
                        }, "json");

                    }
                });
            }
			
		</script>
	</head>

	<body>
		<logic:present name="zcxmList">
			<logic:iterate id="z" name="zcxmList">
				<font style="display:none;" xmdm="${z.xmdm }"  zdfs="${z.zdfs }" zxfs="${z.zxfs }"
				      xmmc="${z.xmmc }" xmlx="${z.xmlx }" jktb="${z.jktb }" name="zcxm"></font>
			</logic:iterate>
		</logic:present>
		<input type="hidden" value="${cssz.xn }" id="xn"/>
		<input type="hidden" value="${cssz.xq }" id="xq"/>
		<input type="hidden" value="${szyf}"  id="szyf"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">				
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
			</p>
		</div>
		<html:form action="/xpj_zcfs" >
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<!-- ��ʾ��Ϣ end-->
			<div id="div_help" class="prompt" style="display: none">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					<span>
						���༶����<font color="red">������Ա����</font>��ά���μ�����ѧ����<font color="red">�۲�ɼ�</font>
					</span>
				</p>
				<a class="close" title="����"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- ��ʾ��Ϣ end-->
			
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
							<logic:equal value="true" name="cssz" property="kgzt">
								<li><a href="javascript:void(0);" onclick="editZcfs();" class="btn_xg"><bean:message key="lable.zcwh" /></a></li>
								<!-- xg_pjpy_new_cspzb szyf = 1 �����ύ��ť	 -->
								<logic:notEqual name="szyf" value="1"> 
									<li><a href="javascript:void(0);" onclick="submitZcfs();" class="btn_up"><bean:message key="lable.zctj" /></a></li>
								</logic:notEqual>
							</logic:equal>
							<li><a href="javascript:void(0);" onclick="viewZcfs();" class="btn_ck">�鿴</a></li>
							<li><a href="javascript:void(0);" onclick="tbxfcj();return false;" class="btn_xg"
							>ͬ��ѧ�ֳɼ�</a></li>
							<logic:equal name="szyf" value="1">
								<li><a href="javascript:void(0);" onclick="zctjtj();return false;" title="����鿴��ѧ�걾ѧ�ڸ��۲��·�ͳ�ơ�" class="btn_tj">�ύͳ��</a></li>				
							</logic:equal>
							<logic:equal name="userName" value="zf01">
							<!--  ���������Ի� ���ӳ�ʼ�������������������  -->
							<logic:equal name="xxdm" value="12309">
								<li><a href="javascript:void(0);" onclick="initCpzCpmd();" class="btn_ck">����������ʼ��</a></li>
							</logic:equal>
							</logic:equal>
						</ul>
					</div>
				</logic:equal>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span><font color="blue">${cssz.zqmc}&nbsp;</font>�۲�༶�б� </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
