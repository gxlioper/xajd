<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/zjdx/cjff/js/cjff.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "�б�",
				pager : "pager",
				url : "cjff_zjdx.do?method=cjffCx&type=query",
				colList : [ {
					label : 'key',
					name : 'id',
					index : 'id',
					key : true,
					hidden : true
				}, {
					label : '���',
					name : 'nd',
					index : 'nd',
					width : '5%'
				}, {
					label : '�·�',
					name : 'yf',
					index : 'yf',
					width : '5%'
				}, {
					label : 'ѧ��',
					name : 'xh',
					index : 'xh',
					width : '10%'
				}, {
					label : '����',
					name : 'xm',
					index : 'xm',
					width : '6%'
				}, {
					label : '���˵�λ',
					name : 'yrdwmc',
					index : 'yrdwmc',
					width : '15%'
				},{
					label : '��ʱ(Сʱ)',
					name : 'gss',
					index : 'gss',
					width : '5%'
				},{
					label : '���Ž��(Ԫ)',
					name : 'bcje',
					index : 'bcje',
					width : '5%'
				},{
					label : '�ύ״̬',
					name : 'tjzt',
					index : 'tjzt',
					width : '5%'
				},{
					label : 'sftj',
					name : 'sftj',
					index : 'sftj',
					hidden : true
				}],
				sortname : "lrsj",
				sortorder : "desc"
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmouseover ="showHelpDiv()">ʹ�ð���</a>
			</p>
		</div>
		<div class="prompt" id="div_help">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				1.${dqnd}����ܾ���Ϊ${tsxx.hbje}Ԫ����ǰ���ύ���${tsxx.tjje}Ԫ��ʣ����${tsxx.syje}Ԫ��δ�ύ���${tsxx.wtjje}Ԫ��<br/>
				2.����ÿ��${jssj}��24��ǰ�ύ�����ϱ����ݣ�����δ�ύ��ϵͳ���Զ��ύ��
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<html:form action="/gygl_xyzsjggl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!-- ��дȨ���ж� -->
						<logic:equal name="writeAble" value="yes">
						
							<!-- �ǹ���Ա�û�  ��𷢷Ű�ť  �ܵ���������ʱ����� -->
							<logic:equal value="false" name="sfqggly">
								<logic:equal value="open" name="sqkg">
									<li>
										<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >��𷢷�</a>
									</li>
									<li>
										<a href="javascript:void(0);" class="btn_tj" onclick="tj();return false;"  >�ύ</a>
									</li>
									<li>
										<a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >ɾ��</a>
									</li>
									<li>
										<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >�޸�</a>
									</li>
									<li>
										<a href="#" class="btn_dr" onclick="dr();return false;">����</a>
									</li>
								</logic:equal>
							</logic:equal>
							
							<!-- ����Ա�û�  ��𷢷Ű�ť  ���ܵ���������ʱ����Ʋ���ӵ�С�ȡ���ύ���Ĺ��� -->
							<logic:equal value="true" name="sfqggly">
								<li>
									<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >��𷢷�</a>
								</li>
								<li>
									<a href="javascript:void(0);" class="btn_tj" onclick="tj();return false;"  >�ύ</a>
								</li>
								<li>
									<a href="javascript:void(0);" class="btn_qxsh" onclick="cancel();return false;"  >ȡ���ύ</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >ɾ��</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >�޸�</a>
								</li>
								<li>
									<a href="#" class="btn_dr" onclick="dr();return false;">����</a>
								</li>
							</logic:equal>
							
						</logic:equal>
						
							<!-- ֻ��Ȩ�ޡ���дȨ�޶�����������ť -->
							<li>
								<a href="javascript:void(0);" onclick="ck();return false;" class="btn_ck" >�鿴��ϸ</a>
							</li>
							<logic:equal value="true" name="sfqggly">
								<li>
									<a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a>
								</li>
							</logic:equal>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>��𷢷��б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
