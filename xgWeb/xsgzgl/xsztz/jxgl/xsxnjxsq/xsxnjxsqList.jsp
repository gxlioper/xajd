<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/jxgl/xsxnjxsq/js/xsjxsh.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"δ�걨������Ŀ",
				pager:"pager",
				url:"jxgl_xsxnjxsq.do?method=xsxnjxsqList&type=query",
				colList : [
							{ label : 'key', name : 'jgid', index : 'jgid',key : true, hidden : true },
							{ label : 'shlc', name : 'shlc', index : 'shlc',hidden : true },
							{ label : 'ѧ��', name : 'xn', index : 'xn', width : '10%' },
							{ label : 'ѧ��', name : 'xqmc', index : 'xqmc', width : '8%' },
							{ label : '��Ŀ����', name : 'xmmc1', index : 'xmmc1', width : '15%'  },
							{ label : '��Ŀ����', name : 'xmjbmc', index : 'xmjbmc', width : '10%' },
							{ label : '�걨����', name : 'sbbmmc', index : 'sbbmdm', width : '10%' },
							{ label : '����ѧ��', name : 'jcxf', index : 'jcxf', width : '10%' },
							{ label : 'ѧ��', name : 'xh', index : 'xh', width : '10%',hidden:true },
							{ label : '����', name : 'xm', index : 'xm', width : '10%',hidden:true },
							{ label : '�༶', name : 'bjmc', index : 'bjdm', width : '10%' },
							{ label : '����״̬', name : 'shztmc', index : 'shztmc', width : '8%' },
							{ label : '���״̬', name : 'shzt', index : 'shzt', hidden : true},
							{label:'�ܷ�',name:'zf', index: 'zf',hidden:true},
							{label:'����Id',name:'jxid', index: 'jxid',hidden:true},
							{label:'��Ŀ����',name:'xmdm', index: 'xmdm',hidden:true},
							{label:'����id',name:'id', index: 'id',hidden:true}					
							],
				params:{sqzt:"wsq"},
				sortname: "xmkssj",
			 	sortorder: "desc",
			 	radioselect:false
		}

		var gridSetting2 = {
				caption:"���걨������Ŀ",
				pager:"pager",
				url:"jxgl_xsxnjxsq.do?method=xsxnjxsqList&type=query",
				colList : [
							{ label : 'key', name : 'jgid', index : 'jgid',key : true, hidden : true },
							{ label : 'shlc', name : 'shlc', index : 'shlc',hidden : true },
							{ label : 'ѧ��', name : 'xn', index : 'xn', width : '10%' },
							{ label : 'ѧ��', name : 'xqmc', index : 'xqmc', width : '8%' },
							{ label : '��Ŀ����', name : 'xmmc1', index : 'xmmc1', width : '15%'  },
							{ label : '��Ŀ����', name : 'xmjbmc', index : 'xmjbmc', width : '10%' },
							{ label : '�걨����', name : 'sbbmmc', index : 'sbbmdm', width : '10%' },
							{ label : '����ѧ��', name : 'jcxf', index : 'jcxf', width : '10%' },
							{ label : 'ѧ��', name : 'xh', index : 'xh', width : '10%',hidden:true },
							{ label : '����', name : 'xm', index : 'xm', width : '10%',hidden:true },
							{ label : '�༶', name : 'bjmc', index : 'bjdm', width : '10%' },
							{ label : '���״̬', name : 'shztmc', index : 'shztmc', width : '8%' },
							{ label : '���״̬', name : 'shzt', index : 'shzt', hidden : true},
							{label:'�ܷ�',name:'zf', index: 'zf',hidden:true},
							{label:'����Id',name:'jxid', index: 'jxid',hidden:true},
							{label:'��Ŀ����',name:'xmdm', index: 'xmdm',hidden:true},
							{label:'����id',name:'id', index: 'id',hidden:true}
							],
				params:{sqzt:"ysq"},
				sortname: "xmkssj",
			 	sortorder: "desc",
			 	radioselect:false
		}
			
		jQuery(function(){
			var map = getSuperSearch();
			map["sqzt"]="wsq";
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});

		function query(){
			var map = {};
			map["xmjbdm"] = jQuery("#xmjbdm").val();
			map["sqzt"]= jQuery("#sqzt").val();
			jQuery("#dataTable").reloadGrid(map);
		}
			
		
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/jxgl_xsxnjxsq">
			<input type="hidden" id="sqzt" value="wsq"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_sq">
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >����</a>
						</li>
						<li id="li_xg">
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >�޸�</a>
						</li>
						<li id="li_sc">
							<a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >ɾ��</a>
						</li>
						<li id="li_tj">
							<a href="javascript:void(0);" onclick="submitBusi();return false" class="btn_shuc" >�ύ</a>
						</li>
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancel();return false;" 
							   title="ѡ��һ����¼������ð�ť�����Գ���ʧ�����˲�����"
							   class="btn_sr">����</a>
						</li>	
						</logic:equal>					
						<li id="li_gz" style="display: none;"><a href="javascript:void(0);" onclick="splcInfo();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a></li>	
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'wsq');"><span>δ�걨������Ŀ</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysq');"><span>���걨������Ŀ</span></a></li>
			      </ul>
			    </div>
				<div class="searchtab">
				<table width="100%" border="0">
					<tbody>
						<tr>
							<th width="15%" style="margin-left:20px">
								��Ŀ����
							</th>
							<td>
								<html:select property="xmjbdm" styleId="xmjbdm" style="width:130px">
									<html:option value=""></html:option>
									<html:options collection="xmjbList" property="xmjbdm"
										labelProperty="xmjbmc" />
								</html:select>
							</td>
							<td>
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go"
										onclick="query()">
										�� ѯ
									</button>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>δ�걨�����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
