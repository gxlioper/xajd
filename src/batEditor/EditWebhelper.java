package batEditor;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import xgxt.action.Base;
import xgxt.base.Configuration;

public class EditWebhelper {

    private String[] aButtonCode;

    private String[] aButtonHTML;

    private Document dStyle;

    private Document dButton;

    private int size;

    public EditWebhelper() {
	size = 255;
	aButtonCode = new String[size];
	aButtonHTML = new String[size];
    }

    public static void init() {
	EditWebhelper editor = new EditWebhelper();
	editor.InitPara();
    }

    public String Code2HTML(String s_Code) {
	String CodeToHTML = "";
	for (int i = 0; i < size; i++) {
	    if (aButtonCode[i].equals(s_Code)) {
		CodeToHTML = aButtonHTML[i];
		return CodeToHTML;
	    }
	}
	return CodeToHTML;
    }

    public void InitButtonArray() {
	List list = this.dButton.selectNodes("Edit_Button/bcode");
	List list2 = this.dButton.selectNodes("Edit_Button/bcode/@name");
	Iterator it = list.iterator();
	Iterator it2 = list2.iterator();

	int i = 0;
	int option = 0;
	try {
	    while (it.hasNext()) {
		Attribute attribute = (Attribute) it2.next();
		Element button = (Element) it.next();
		this.aButtonCode[i] = attribute.getValue();
		option = Integer.parseInt(memoString(button, "btype").getTextTrim());
		
		switch (option) {
		case 0: // '\0'
		    this.aButtonHTML[i] = "<div class=\""
			    + memoString(button, "bclass").getTextTrim()
			    + "\" title=\""
			    + memoString(button, "btitle").getTextTrim()
			    + "\" onclick=\""
			    + memoString(button, "bevent").getTextTrim()
			    + "\"><img class=\"Ico\" SRC=\"batEditor/buttonimage/standard/"
			    + memoString(button, "bimage").getTextTrim()
			    + "\" style=\"cursor:hand\"></div>\n";
		    break;

		case 1: // '\001'
		    this.aButtonHTML[i] = "<select class=\""
			    + memoString(button, "bclass").getTextTrim()
			    + "\" onchange=\""
			    + memoString(button, "bevent").getTextTrim()
			    + "\">\n"
			    +  memoString(button, "bhtml").getTextTrim()
			    + "\n</select>\n";
		    break;

		case 2: // '\002'
		    this.aButtonHTML[i] = "<div class=\""
			    + memoString(button, "bclass").getTextTrim()
			    + "\">"
			    + notNull(memoString(button, "bhtml").getTextTrim())
			    + "</div>\n";
		    break;
		}
		i++;
	    }
	    this.size = i;
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
	return;
    }

    public void InitPara() {
	SAXReader saxReader = new SAXReader();
	
	try {	
	    this.dStyle = saxReader.read(new File(Configuration.PRO_PATH
		    + "/css/style.xml"));
	    this.dButton = saxReader.read(new File(Configuration.PRO_PATH
		    + "/css/button.xml"));
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	String sToolBar = "";
	List list = this.dStyle.selectNodes("Edit_Style/standard");
	InitButtonArray();
	int tot = 0;
	String aButton[] = getNodeValue(list, "stoolbar1").split("\\|");
	sToolBar = "<table border=0 cellpadding=0 cellspacing=0 width='100%'"
		+ " class='Toolbar' id='eWebEditor_Toolbar'>";
	sToolBar = sToolBar + "<tr><td><div class='yToolbar'>";
	for (int i = 0; i < aButton.length; i++) {
	    if (aButton[i].equalsIgnoreCase("MAXIMIZE")) {
		aButton[i] = "Minimize";
	    }
	    tot++;
	    sToolBar = sToolBar + Code2HTML(aButton[i]);
	}
	sToolBar = sToolBar + "</div></td></tr>";
	String aButton2[] = getNodeValue(list, "stoolbar2").split("\\|");
	sToolBar = sToolBar + "<tr><td><div class='yToolbar'>";
	for (int j = 0; j < aButton2.length; j++) {
	    if (aButton2[j].equalsIgnoreCase("MAXIMIZE")) {
		aButton2[j] = "Minimize";
	    }
	    tot++;
	    sToolBar = sToolBar + Code2HTML(aButton2[j]);
	}
	sToolBar = sToolBar + "</div></td></tr>";
	String aButton3[] = getNodeValue(list, "stoolbar3").split("\\|");
	sToolBar = sToolBar + "<tr><td><div class='yToolbar'>";
	for (int j = 0; j < aButton3.length; j++) {
	    if (aButton3[j].equalsIgnoreCase("MAXIMIZE")) {
		aButton3[j] = "Minimize";
	    }
	    tot++;
	    sToolBar = sToolBar + Code2HTML(aButton3[j]);
	}
	sToolBar = sToolBar + "</div></td></tr></table>";
	try {
	    Base.sButton = sToolBar;
	} catch (Exception e) {
	    this.dStyle = null;
	    this.dButton = null;
	    System.out.println(e.getMessage());
	}
    }

    public String getNodeValue(List list, String Node) {
	Iterator it = list.iterator();
	if (it.hasNext()) {
	    Element styleElement = (Element) it.next();
	    Iterator memo = styleElement.elementIterator(Node);
	    if (memo.hasNext()) {
		Element memostring = (Element) memo.next();
		return memostring.getTextTrim();
	    }
	    return "";

	}
	return "";

    }

    public Element memoString(Element button, String Node) {
	Iterator memo = button.elementIterator(Node);
	if (memo.hasNext()) {
	    Element memostring = (Element) memo.next();
	    return memostring;
	}
	return null;

    }

    public String notNull(String str) {
	String s = str;
	if (str == null) {
	    return "";
	}
	return s;

    }

    public void test() {

    }
}
