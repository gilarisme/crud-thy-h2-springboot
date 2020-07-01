package net.crunchdroid.generatexml;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import net.crunchdroid.constant.ParamConstant;

@Service
public class TestGenerate implements TestGenerateInterface {
	
	@Autowired
	XmlGeneratorService xmlGenerator;
	
	@Value("${dir.pathOutput}")
	private String pathOutput;
	
	public static final String UW_POS_CONTEXT_NAME = "context";
    public static final String UW_POS_TEMPLATE_NAME = "TEMPLATE";

	@Override
	public void generateXML(boolean parameter) {
		
		Map<String, String> param = new HashMap<>();
        Map<Object, Object> data = new HashMap<>();
        
        param.put(ParamConstant.PARAM_FILE_NAME, "AWEU");
        param.put(ParamConstant.PARAM_CONTEXT_NAME, UW_POS_CONTEXT_NAME);
        param.put(ParamConstant.PARAM_TEMPLATE_NAME, UW_POS_TEMPLATE_NAME);
        param.put(ParamConstant.PARAM_PATH_OUTPUT, pathOutput);
        param.put(ParamConstant.PARAM_FORM_ID, "Pernikahan");

        data.put("id", "ID");
        data.put("process", "PROCESS");
        data.put("tranNo", "01");
        
        xmlGenerator.generateXML(param, data);
		
	}

}
