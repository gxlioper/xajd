<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>		
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="js/comm/dateUtils.js"></script>
		<script type="text/javascript" >
		var gridSetting = {
			caption:"ԤԼ��ѯ�б�",
			pager:"pager",
			url:"xlzx_yysqnew.do?method=yyfkManage&doType=query",
			colList:[
				{label:'id',name:'id',index:'id',hidden:true, key:true  },
			   {label:'ѧ��',name:'xh', index: 'xh',width:'12%', formatter:xhLinkForYyfk},
			   {label:'����',name:'xsxm', index: 'xsxm',width:'9%'},
			   {label:'�Ա�',name:'xsxb', index: 'xsxb',width:'6%'},
			   <logic:equal value="10504" name="xxdm">
			   {label:'Σ��<br/>����',name:'sfxlwjgamc', index: 'sfxlwjgamc',width:'6%'},
			   </logic:equal>
			   {label:'ԤԼ��ѯ����',name:'yyzxrq', index: 'yyzxrq',width:'11%'},
			   {label:'ԤԼ��ѯʦ',name:'zxsxm', index: 'zxsxm',width:'9%',formatter:zxsxmLink},
			   {label:'ԤԼ״̬',name:'status', index: 'status',width:'9%',formatter:statusChange},
			   {label:'ԤԼ����',name:'yyfkzt', index: 'yyfkzt',width:'9%',formatter:yyfkztChange},
			   {label:'��ѯ��������',name:'zxrq', index: 'zxrq',width:'11%'},
			   <logic:equal value="10026" name="xxdm">
			   {label:'��ѯ����ʱ���',name:'sjddmzxmc', index: 'sjddmzxmc'},
			   </logic:equal>
			   {label:'������ѯʦ',name:'apzxsxm', index: 'apzxsxm',width:'9%',formatter:apzxsxmLink},
			   {label:'��ѯ״̬',name:'zxztmc', index: 'zxztmc',width:'9%'},
			   {label:'��ѯ״̬����',name:'zxzt', index: 'zxzt',hidden:true},
			   {label:'����Σ������ѧ������',name:'sfxlwjga', index: 'sfxlwjga',hidden:true},
			   {label:'ԤԼ��ѯʦְ����',name:'zgh', index: 'zgh',hidden:true},
			   {label:'������ѯʦְ����',name:'apzxs', index: 'apzxs',hidden:true}
			],
			sortname: "sfxlwjga,status,xh",
		 	sortorder: "asc"
		};
		jQuery(function(){
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
		});
		function searchRs() {
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}
		// ѧ�����ӣ���ʾԤԼ��ѯ������Ϣ��������ѯ��Ϣ��
		function xhLinkForYyfk(cellValue, rowObject) {
			return "<a href = 'javascript:void(0);' class='name' onclick=\"showDialog('�鿴������ѯ' , 750,440 , 'xlzx_yysqnew.do?method=yyfkDetail&doType=view&id=" + rowObject['id'] + "')\" >" + cellValue + "</a>";
		}
		// ԤԼ״̬
		function statusChange(cellValue, rowObject) {
			var returnText;
			switch (cellValue) {
				case "1":
					returnText="ԤԼ��";
					break;
				case "2":
					returnText="<font color='blue'>ԤԼ�ɹ�</font>";
					break;
				case "3":
					returnText="ԤԼ��<br/>(ѧ��ȡ��)";
					break;
				case "4":
					returnText="ԤԼ�ɹ�<br/>(ѧ��ȡ��)";
					break;
				case "5":
					returnText="ԤԼʧ��";
					break;
				default:
					returnText="";
					break;
			}
			return returnText;
		}
		// ����״̬
		function yyfkztChange(cellValue, rowObject) {
			var returnText;
			var status = rowObject['status'];
			if(status=='2' || status == '4' || status == '5'){
				returnText = "�ѷ���";
			}else if(status=='1'){
				returnText = "<font color='red'>δ����</font>";
			}else{
				returnText = "";
			}
			return returnText;
		}
		// ��ѯʦ��������(����)
		function apzxsxmLink(cellValue, rowObject) {
			return "<a href = 'javascript:void(0);' class='name' onclick=\"showDialog('�鿴��ѯʦ��Ϣ' , 750 , 410 , 'xlzx_zxs.do?method=zxsglDetail&doType=view&zgh=" + rowObject['apzxs'] + "')\" >" + (cellValue==null?" ":cellValue) + "</a>";
		}
		// ��ѯʦ��������(ԤԼ)
		function zxsxmLink(cellValue, rowObject) {
			return "<a href = 'javascript:void(0);' class='name' onclick=\"showDialog('�鿴��ѯʦ��Ϣ' , 750 , 410 , 'xlzx_zxs.do?method=zxsglDetail&doType=view&zgh=" + rowObject['zgh'] + "')\" >" + (cellValue==null?" ":cellValue) + "</a>";
		}
		// ԤԼ����
		function xlzxyyfk(){
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1){
				showAlertDivLayer("��ѡ��һ����ҪԤԼ�����ļ�¼��");
				return false;
			} else{
				var zxzt = rows[0]['zxzt'];
				var status = rows[0]['status'];
				if(status == '3'){
					showAlertDivLayer("�����ܶ�ԤԼ�У�ѧ��ȡ���������ݽ��в�����");
					return false;
				}else if(status == '4'){
					showAlertDivLayer("�����ܶ�ԤԼ�ɹ���ѧ��ȡ���������ݽ��в�����");
					return false;
				}
				if(zxzt == '1' || zxzt == '2'){
					showAlertDivLayer("��ֻ�ܶ�δ���������ݽ��в�����");
					return false;
				}
				showDialog('ԤԼ����',750,440,'xlzx_yysqnew.do?method=yyfkDetail&doType=yyfk&id=' + rows[0]['id'] + '&status=' + rows[0]['status']);
			}
		}
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xlzx_yysqnew" method="post">
			<%@ include file="/comm/hiddenValue.jsp"%>
				<div class="toolbox">
					<!-- ��ť -->
					<div class="buttonbox">
						<ul>
						<logic:equal  name="writeAble" value="yes">
							<li>
								<a href="#" onclick="xlzxyyfk();return false;" class="btn_xg">ԤԼ����</a>
							</li>
						</logic:equal>
						</ul>
					</div> 
				</div>
		  		<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ԤԼ��ѯ�б� </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
