package com.buildmlearn.xml;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;


public interface XmlImplementation<T> {
	public void readXml() throws ParserConfigurationException, SAXException, IOException;
	public void writeXml() throws ParserConfigurationException;
	void writeXml(ArrayList<T> dataList,String filename)
			throws ParserConfigurationException, TransformerConfigurationException, TransformerException;
	void readXml(String fileLocation) throws ParserConfigurationException,
			SAXException, IOException;

}
