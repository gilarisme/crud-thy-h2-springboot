package net.crunchdroid.generatexml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import net.crunchdroid.constant.ParamConstant;

@Service
public class XMLGenerator implements XmlGeneratorService {
	
private final SpringTemplateEngine springTemplateEngine;
	
	@Autowired
	public XMLGenerator(SpringTemplateEngine springTemplateEngine) {
		this.springTemplateEngine = springTemplateEngine;
	}

	@Override
	public void generateXML(Map<String, String> param, Map<Object, Object> data) {
		
		try {
			this.createDirectory(param);
			
			File file = new File(param.get(ParamConstant.PARAM_PATH_OUTPUT) + param.get(ParamConstant.PARAM_FORM_ID)+ "/" + param.get(ParamConstant.PARAM_FILE_NAME) + ".xml");
//			File backup = new File(pathOutputBackup + param.get(ParamConstant.PARAM_JETFORM_ID) + param.get(ParamConstant.PARAM_FILE_NAME) + param.get(ParamConstant.PARAM_POLICY_NO) + "_" + nameCal + ".xml");
			
			this.createFile(file, param, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void createDirectory(Map<String, String> param) {
		Path path = Paths.get(param.get(ParamConstant.PARAM_PATH_OUTPUT) + param.get(ParamConstant.PARAM_FORM_ID));

		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void createFile(File file, Map<String, String> param, Map<Object, Object> data) {
		Context context = new Context();

		try (FileOutputStream fop = new FileOutputStream(file)) {
			context.setVariable(param.get(ParamConstant.PARAM_CONTEXT_NAME), data);

			String content = springTemplateEngine.process(param.get(ParamConstant.PARAM_TEMPLATE_NAME), context);

			byte[] contentBytes = content.getBytes();

			fop.write(contentBytes);
			fop.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
