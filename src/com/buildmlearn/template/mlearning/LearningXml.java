package com.buildmlearn.template.mlearning;

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
import com.buildmlearn.xml.XmlImplementation;

public class LearningXml implements XmlImplementation<LearningDataTemplate>{

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
		   NodeList nodeList = doc.getElementsByTagName("template");  
		  
		   System.out.println("Root element :"  
		     + doc.getDocumentElement().getNodeName());  
		  
		   for (int temp = 0; temp < nodeList.getLength(); temp++) {  
		    Node node = nodeList.item(temp);  
		  
		    System.out.println("\nElement type :" + node.getNodeName());  
		  
		    if (node.getNodeType() == Node.ELEMENT_NODE) {  
		  
		     Element student = (Element) node;  
		  
		     System.out.println("Data id : "  
		       + student.getAttribute("id"));  
		     System.out.println("Title : "  
		       + student.getElementsByTagName("title").item(0)  
		         .getTextContent());  
		     System.out.println("Description : "  
		       + student.getElementsByTagName("description").item(0)  
		         .getTextContent());  
		     
		  
		    }  
		   }  
	}

	@Override
	public void writeXml(ArrayList<LearningDataTemplate> dataList)
			throws ParserConfigurationException,
			TransformerConfigurationException, TransformerException {
		// TODO Auto-generated method stub
		Log.e("write ", "start");
		try{
		DocumentBuilderFactory documentFactory=DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder=documentFactory.newDocumentBuilder();
		Document document=documentBuilder.newDocument();
		Element rootElement=document.createElement("template");
		document.appendChild(rootElement);
		Element metaData=document.createElement("metadata");
		rootElement.appendChild(metaData);
		Element template_name=document.createElement("type");
		template_name.appendChild(document.createTextNode( ((MyApplication)MyApplication.mApplication.getApplication()).getmModel().getmTemplate().toString()));
		Log.e("type is", ""+template_name.getTextContent());
		metaData.appendChild(template_name);
		Element author_name=document.createElement("author_name");
		author_name.appendChild(document.createTextNode(((MyApplication)MyApplication.mApplication.getApplication()).getmModel().getmAuthorName().toString()));
		Log.e("author name is ", ""+author_name.getTextContent());
		metaData.appendChild(author_name);
		Element app_name=document.createElement("app_name");
		metaData.appendChild(document.createTextNode( ((MyApplication)MyApplication.mApplication.getApplication()).getmModel().getmAppName().toString()));
		Log.e("app name is", ""+app_name.getTextContent());
		metaData.appendChild(app_name);
		for(int i=0;i<dataList.size();i++)
		{
			LearningDataTemplate currentElement=dataList.get(i);
			Element element=document.createElement("data");
			rootElement.appendChild(element);
			//Attr id =document.createAttribute("id");
			//id.setValue(""+i+1);
			//rootElement.setAttributeNodeNS(id);
			Element question=document.createElement("title");
			question.appendChild(document.createTextNode(currentElement.getmTitle()));
			element.appendChild(question);
			Element answer=document.createElement("description");
			answer.appendChild(document.createTextNode(currentElement.getDescription()));
			element.appendChild(answer);
			
			
			
		}
		TransformerFactory transformerFactory=TransformerFactory.newInstance();
		Transformer transformer=transformerFactory.newTransformer();
		DOMSource domSource=new DOMSource(document);
		String root = Environment.getExternalStorageDirectory().toString();
	    File myDir = new File(root + "/buildmlearnFiles");    
	    myDir.mkdirs();
	    File file = new File (myDir, "learning.xml");
	    if (file.exists ()) file.delete (); 
	    try {
	    	
	    }catch(Exception e)
	    {
	    	Log.e("file creation", ""+e);
	    }
		//StreamResult streamResult=new StreamResult(new File("buildmleanFiles/learning.xml"));
		StreamResult streamResult =new StreamResult(file);
	    transformer.transform(domSource, streamResult);
		Log.e("Xml is", ""+streamResult);
		}catch(Exception e)
		{
			Log.e("thorw exception", ""+e);
		}
	}
		

}
