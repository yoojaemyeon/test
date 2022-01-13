package test.test02;

import java.util.HashMap;

public class ParamMap extends HashMap<String, Object>{
	
	private ParamMap() {}
	public void ParamMap init() {
		return new ParamMap();
		
	}
	
	public String getString(String key) {
		Object object = this.get(key);
		if(object == null) {
			return null;
		}else {
			return String.valueOf(object);
		}
	}
	
	public <T> T GeneralTransform3D(String key, Class<T> clazz) {
		Object object = this.get(key);
		if(object == null) {
			return null;
		}else {
			return (T) object; 
		}
	}
	
	public static void main(String[] args) {
		/** key : String, valye : object */
		ParamMap param = ParamMap.init();
		param.put("key1", "value1");
		String value = param.getString("key1");
		
		StringBuffer sb = new StringBuffer();
		sb.append("test");
		param.put("key2",sb);
		StringBuffer stringBuffer = param.put.get("key2", StringBuffer.class);
		
		
		
	}
}
