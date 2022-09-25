package com.example.xml.rest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XmlTxnAPIErrorHandler implements ErrorHandler {
	
	private List<String> errorList = new ArrayList<String>();
	private List<String> warningList = new ArrayList<String>();
	private Map<String, List> xmlParseAndValidationMessages = new HashMap<String, List>();

	@Override
	public void warning(SAXParseException ex) throws SAXException {		   
	   String warningStr = "warning : " + (ex.getMessage()).trim();
	   warningList.add(warningStr);
	}

	@Override
	public void error(SAXParseException ex) throws SAXException {
		String errorStr = "error : " + (ex.getMessage()).trim();
		errorList.add(errorStr);
	}

	@Override
	public void fatalError(SAXParseException ex) throws SAXException {
		String errorStr = "fatalError : " + (ex.getMessage()).trim();
		errorList.add(errorStr);
	}
	
	public Map<String, List> getXmlParseAndValidationMessages() {
		xmlParseAndValidationMessages.put("errors", errorList);
		xmlParseAndValidationMessages.put("warnings", warningList);
		return xmlParseAndValidationMessages;
	}
	
}
