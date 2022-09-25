package com.example.xml.rest.controller;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;

import com.example.xml.rest.service.XSValidator;
import com.example.xml.rest.service.XmlTxnAPIErrorHandler;

@RestController
public class XMLTxnController {

	@Autowired
	private XSValidator xsValidator;

	@Autowired
	ResourceLoader resourceLoader;
	
	@Value("${show_xml_and_xsd_warnings}")
	private boolean showXmlAndXsdWarnings;

	@PostMapping(path = "/doProcess1", consumes = "application/xml", produces = "application/xml")
	public String doProcess1(@RequestBody String inpXmlStr) throws Exception {

		String resultXmlStr = null;
		
		XmlTxnAPIErrorHandler xmlTxnAPIErrorHandler = new XmlTxnAPIErrorHandler();

		try {
			File xsdFile = (resourceLoader.getResource("classpath:xsd/process1txn.xsd")).getFile();

			Document inpXmlDocument = xsValidator.getXmlDocument(inpXmlStr, xmlTxnAPIErrorHandler);
			Map<String, List> xmlParseAndValidationMessages = xsValidator.validate(inpXmlDocument, 
					                                                   xsdFile, xmlTxnAPIErrorHandler);
			resultXmlStr = constructResultXmlStr(xmlParseAndValidationMessages, inpXmlDocument);
		} catch (Exception ex) {
			resultXmlStr = "<response><status>failure</status><errorMessages><mesg>" + ex.getMessage() + "</mesg>"
	                                      + "</errorMessages></response>";
		}

		return resultXmlStr;
	}

	@PostMapping(path = "/doProcess2", consumes = "application/xml", produces = "application/xml")
	public String doProcess2(@RequestBody String inpXmlStr) throws Exception {

		String resultXmlStr = null;
		
		XmlTxnAPIErrorHandler xmlTxnAPIErrorHandler = new XmlTxnAPIErrorHandler();

		try {
			File xsdFile = (resourceLoader.getResource("classpath:xsd/process2txn.xsd")).getFile();

			Document inpXmlDocument = xsValidator.getXmlDocument(inpXmlStr, xmlTxnAPIErrorHandler);
			Map<String, List> xmlParseAndValidationMessages = xsValidator.validate(inpXmlDocument, 
					                                                  xsdFile, xmlTxnAPIErrorHandler);
			resultXmlStr = constructResultXmlStr(xmlParseAndValidationMessages, inpXmlDocument);
		} catch (Exception ex) {
			resultXmlStr = "<response><status>failure</status><errorMessages><mesg>" + ex.getMessage() + "</mesg>"
					                   + "</errorMessages></response>";
		}

		return resultXmlStr;
	}

	private String constructResultXmlStr(Map<String, List> xmlParseAndValidationMessages,
			                                     Document inpXmlDocument) {
		String resultXmlStr = "";
		String warningXmlFrag = "";
		
		List<String> warningList = xmlParseAndValidationMessages.get("warnings");
		if (showXmlAndXsdWarnings && warningList.size() > 0) {
			warningXmlFrag = "<warningMessages>";
			for (Iterator<String> iter = warningList.iterator(); iter.hasNext();) {
				warningXmlFrag += "<mesg>" + iter.next() + "</mesg>";
			}
			warningXmlFrag += "</warningMessages>";
		}

		if ((xmlParseAndValidationMessages.get("errors")).size() == 0) {
			if (warningXmlFrag.length() > 0) {
				resultXmlStr = "<response><status>success</status>" + warningXmlFrag;
			} else {
				resultXmlStr = "<response><status>success</status>";
			}
		} else {
			List<String> errorList = xmlParseAndValidationMessages.get("errors");
			String errorXmlFrag = "<errorMessages>";
			for (Iterator<String> iter = errorList.iterator(); iter.hasNext();) {
				errorXmlFrag += "<mesg>" + iter.next() + "</mesg>";
			}
			errorXmlFrag += "</errorMessages>";
			resultXmlStr = "<response><status>failure</status>" + errorXmlFrag;
			if (warningXmlFrag.length() > 0) {
				resultXmlStr += warningXmlFrag; 
			}
		}

		return resultXmlStr + "</response>";
	}

}
