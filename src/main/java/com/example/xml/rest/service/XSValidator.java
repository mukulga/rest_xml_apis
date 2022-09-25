package com.example.xml.rest.service;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@Service
public class XSValidator {
	
	@Value("${xsd_version}")
	private String xsdVersion;		
	
	public Map<String, List> validate(Document inpXmlDocument, File xsdFile, 
			                          XmlTxnAPIErrorHandler xmlTxnAPIErrorHandler) throws Exception {				 		
		
		SchemaFactory sf;
		if ("1.1".equals(xsdVersion)) {
		   sf = SchemaFactory.newInstance("http://www.w3.org/XML/XMLSchema/v1.1");
		   sf.setFeature("http://apache.org/xml/features/validation/cta-full-xpath-checking", true);
	    }
	    else {
	       sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	    }
		
		sf.setFeature("http://apache.org/xml/features/validation/schema-full-checking", true);
		sf.setErrorHandler(xmlTxnAPIErrorHandler);
		
		Schema schema = sf.newSchema(new StreamSource(xsdFile));
		Validator validator = schema.newValidator();
		validator.setErrorHandler(xmlTxnAPIErrorHandler);
		validator.validate(new DOMSource(inpXmlDocument));
	    
		return xmlTxnAPIErrorHandler.getXmlParseAndValidationMessages();
		
	}
	
	public Document getXmlDocument(String xmlInpStr, XmlTxnAPIErrorHandler 
			                            xmlTxnAPIErrorHandler) throws ParserConfigurationException, 
	                                                             SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);		
		DocumentBuilder dBuilder = dbf.newDocumentBuilder();
		
		dBuilder.setErrorHandler(xmlTxnAPIErrorHandler);
		InputSource inpSource = new InputSource(new StringReader(xmlInpStr)); 
		Document xmlDocument = dBuilder.parse(inpSource);
		
		return xmlDocument; 
	}

}
