package com.buildmlearn.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.content.Intent;
import android.sax.StartElementListener;

import com.buildmlearn.application.MyApplication;
import com.buildmlearn.models.Template;
import com.buildmlearn.template.flashcard.FlashCardXml;
import com.buildmlearn.template.mlearning.LearningXml;
import com.buildmlearn.template.quiz.QuizXml;
import com.buildmlearn.template.spellings.SpellingXml;
import com.example.buildmlearntoolkit.ContentActivity;

public class XmlReader {

	public static void readXml(String fileLocation) throws ParserConfigurationException, SAXException, IOException
	{
		File fXmlFile = new File(fileLocation);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
	 
		//optional, but recommended
		//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();
	 
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	 
		NodeList nList = doc.getElementsByTagName("metadata");
	 
		System.out.println("----------------------------");
	 
		for (int temp = 0; temp < nList.getLength(); temp++) {
	 
			Node nNode = nList.item(temp);
	 
			System.out.println("\nCurrent Element :" + nNode.getNodeName());
	 
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	 
				Element eElement = (Element) nNode;
	 
				System.out.println("First Name : " + eElement.getElementsByTagName("type").item(0).getTextContent());
				System.out.println("Last Name : " + eElement.getElementsByTagName("app_name").item(0).getTextContent());
				System.out.println("Nick Name : " + eElement.getElementsByTagName("author_name").item(0).getTextContent());
				String type =eElement.getElementsByTagName("type").item(0).getTextContent();
				Object xml;
				 ((MyApplication)MyApplication.mApplication.getApplication()).getmModel().setmAppName(eElement.getElementsByTagName("app_name").item(0).getTextContent());
				 ((MyApplication)MyApplication.mApplication.getApplication()).getmModel().setmAuthorName(eElement.getElementsByTagName("author_name").item(0).getTextContent());
					
				 switch (type) {
				
				case "FLASHCARD":
					 ((MyApplication)MyApplication.mApplication.getApplication()).getmModel().setmTemplate(Template.FLASHCARD);
					xml=new FlashCardXml();
					 ((FlashCardXml) xml).readXml(fileLocation);
					 break;
				case "QUIZ":
					 ((MyApplication)MyApplication.mApplication.getApplication()).getmModel().setmTemplate(Template.QUIZ);
						 xml=new QuizXml();
						((QuizXml) xml).readXml(fileLocation);
					break;
				case "SPELLING":
					 ((MyApplication)MyApplication.mApplication.getApplication()).getmModel().setmTemplate(Template.SPELLLING);
						xml=new SpellingXml();
						((SpellingXml)xml).readXml(fileLocation);
				break;
				
				case "LEARNING":
					 ((MyApplication)MyApplication.mApplication.getApplication()).getmModel().setmTemplate(Template.LEARNING);
						xml=new LearningXml();
						((LearningXml)xml).readXml(fileLocation);
					break;

				default:
					break;
				}
				Intent i =new Intent(MyApplication.mApplication,ContentActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				MyApplication.mApplication.startActivity(i);
			}
		}
	}
}
