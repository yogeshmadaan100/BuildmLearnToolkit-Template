package com.buildmlearn.template.spellings;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.os.Environment;
import android.util.Log;

import com.buildmlearn.application.MyApplication;
import com.buildmlearn.template.mlearning.LearningDataTemplate;
import com.buildmlearn.xml.XmlImplementation;

public class SpellingXml implements XmlImplementation<SpellingsDataTemplate>{

	@Override
	public void readXml() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeXml() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void readXml(String fileLocation) throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		 File xmlFile = new File(fileLocation);  
		   DocumentBuilderFactory documentFactory = DocumentBuilderFactory  
		     .newInstance();  
		   DocumentBuilder documentBuilder = documentFactory  
		     .newDocumentBuilder();  
		   Document doc = documentBuilder.parse(xmlFile);  
		  
		   doc.getDocumentElement().normalize();  
		   NodeList nodeList = doc.getElementsByTagName("data");  
		  
		   System.out.println("Root element :"  
		     + doc.getDocumentElement().getNodeName());  
		  
		   for (int temp = 0; temp < nodeList.getLength(); temp++) {  
		    Node node = nodeList.item(temp);  
		  
		    System.out.println("\nElement type :" + node.getNodeName());  
		  
		    if (node.getNodeType() == Node.ELEMENT_NODE) {  
		  
		     Element student = (Element) node;  
		  
		     System.out.println("Data id : "  
		       + student.getAttribute("id"));  
		     System.out.println("Word : "  
		       + student.getElementsByTagName("word").item(0)  
		         .getTextContent());  
		     System.out.println("Meaning : "  
		       + student.getElementsByTagName("meaning").item(0)  
		         .getTextContent());  
		     MyApplication.mDataList=new ArrayList<SpellingsDataTemplate>();
		     MyApplication.mDataList.add(new SpellingsDataTemplate( student.getElementsByTagName("word").item(0)  
			         .getTextContent(),student.getElementsByTagName("meaning").item(0)  
			         .getTextContent()));
		  
		    }  
		   }  
	}
	@Override
	public void writeXml(ArrayList<SpellingsDataTemplate> dataList)
			throws ParserConfigurationException,
			TransformerConfigurationException, TransformerException {
		// TODO Auto-generated method stub
		DocumentBuilderFactory documentFactory=DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder=documentFactory.newDocumentBuilder();
		Document document=documentBuilder.newDocument();
		Element rootElement=document.createElement("template");
		document.appendChild(rootElement);
		Element metaData=document.createElement("metadata");
		rootElement.appendChild(metaData);
		Element template_name=document.createElement("type");
		template_name.appendChild(document.createTextNode( ((MyApplication)MyApplication.mApplication.getApplication()).getmModel().getmTemplate().toString()));
		metaData.appendChild(template_name);
		Element app_name=document.createElement("app_name");
		template_name.appendChild(document.createTextNode( ((MyApplication)MyApplication.mApplication.getApplication()).getmModel().getmAppName().toString()));
		metaData.appendChild(app_name);
		Element author_name=document.createElement("author_name");
		author_name.appendChild(document.createTextNode(((MyApplication)MyApplication.mApplication.getApplication()).getmModel().getmAuthorName().toString()));
		metaData.appendChild(author_name);
			for(int i=0;i<dataList.size();i++)
		{
			SpellingsDataTemplate currentElement=dataList.get(i);
			Element element=document.createElement("data");
			rootElement.appendChild(element);
			Attr id =document.createAttribute("id");
			id.setValue(""+i+1);
			rootElement.setAttributeNodeNS(id);
			Element question=document.createElement("word");
			question.appendChild(document.createTextNode(currentElement.getmWord()));
			element.appendChild(question);
			Element answer=document.createElement("meaning");
			answer.appendChild(document.createTextNode(currentElement.getMeaning()));
			element.appendChild(answer);
			
			
			
		}
		String root = Environment.getExternalStorageDirectory().toString();
	    File myDir = new File(root + "/buildmlearnFiles");    
	    myDir.mkdirs();
	    File file = new File (myDir, "spellings.xml");
	    if (file.exists ()) file.delete (); 
	    try {
	    	
	    }catch(Exception e)
	    {
	    	Log.e("file creation", ""+e);
	    }
		TransformerFactory transformerFactory=TransformerFactory.newInstance();
		Transformer transformer=transformerFactory.newTransformer();
		DOMSource domSource=new DOMSource(document);
		StreamResult streamResult=new StreamResult(file);
		transformer.transform(domSource, streamResult);
		
		
		
	}

}
