@echo off

call mvn install:install-file -Dfile="d:\eclipseWorkspaces\spring_boot_rest_xml_apis\rest_xml_apis\xerceslib\cupv10k-runtime.jar" -DgroupId=java_cup -DartifactId=java_cup_impl -Dversion=1.0 -Dpackaging=jar

call mvn install:install-file -Dfile="d:\eclipseWorkspaces\spring_boot_rest_xml_apis\rest_xml_apis\xerceslib\icu4j.jar" -DgroupId=org.ibm.icu -DartifactId=ibm_icu4j -Dversion=4.2 -Dpackaging=jar

call mvn install:install-file -Dfile="d:\eclipseWorkspaces\spring_boot_rest_xml_apis\rest_xml_apis\xerceslib\org.eclipse.wst.xml.xpath2.processor_1.2.1.jar" -DgroupId=org.eclipse.wst.xml -DartifactId=xpath2 -Dversion=1.2.1 -Dpackaging=jar

call mvn install:install-file -Dfile="d:\eclipseWorkspaces\spring_boot_rest_xml_apis\rest_xml_apis\xerceslib\resolver.jar" -DgroupId=org.apache.xerces -DartifactId=xml_resolver -Dversion=1.2 -Dpackaging=jar

call mvn install:install-file -Dfile="d:\eclipseWorkspaces\spring_boot_rest_xml_apis\rest_xml_apis\xerceslib\serializer.jar" -DgroupId=org.apache.xerces -DartifactId=xml_serializer -Dversion=2.7.2 -Dpackaging=jar

call mvn install:install-file -Dfile="d:\eclipseWorkspaces\spring_boot_rest_xml_apis\rest_xml_apis\xerceslib\xercesImpl.jar" -DgroupId=org.apache.xerces -DartifactId=xercesImpl -Dversion=2.12.2 -Dpackaging=jar

call mvn install:install-file -Dfile="d:\eclipseWorkspaces\spring_boot_rest_xml_apis\rest_xml_apis\xerceslib\xml-apis.jar" -DgroupId=org.apache.xerces -DartifactId=xml-apis -Dversion=1.4.02 -Dpackaging=jar

