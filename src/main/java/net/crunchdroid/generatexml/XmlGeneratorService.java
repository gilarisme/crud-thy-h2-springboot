package net.crunchdroid.generatexml;

import java.util.Map;

public interface XmlGeneratorService {

	void generateXML(Map<String, String> param, Map<Object, Object> data);
}
