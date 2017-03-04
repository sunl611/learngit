/**
 * 
 */
package cn.edu.nenu.clzc.commons.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.alibaba.fastjson.JSONObject;

import cn.edu.nenu.clzc.commons.exception.ContextException;

public class BeanToJson {
	JSONObject jsonObject;
	private static final ObjectMapper mapper=new ObjectMapper();
	public BeanToJson() {
		this.jsonObject=new JSONObject();
	}
	/**
	 * 
	* 创建一个新的带map的实例 JSONStr.
	*
	* @param map
	 */
	public BeanToJson(JSONObject jsonObject) {
		this.jsonObject=jsonObject;
	}
	
	
	/**
	 * @throws IOException 
	* @Title: objectToJSONString
	* @Description: TODO(将一个对象封装到map里面为他指定键key，最后将这个map转化为json字符串，方便传输到前台是获取方便)
	* @param @param key
	* @param @param t
	* @param @return
	* @param @throws JsonProcessingException    设定文件
	* @return String    返回类型
	* @throws
	*/ 
	public  static String objectToJSONString(String key,Object obj) throws IOException{
		Map<String, Object> objMap=new HashMap<String, Object>();
		if(key!=null && !"".equals(key.trim()) && obj!=null){
			objMap.put(key, obj);
			return mapper.writeValueAsString(objMap);
		}else{
		return null;
		}
	}
	public BeanToJson put(String key,Object value){
		this.jsonObject.put(key, value);
		return this;
	}
	
	public BeanToJson remove(String key) throws ContextException{
		if (this.jsonObject.containsKey(key)) {
			this.jsonObject.remove(key);
		}else {
			throw new ContextException("map中对应对象不存在！");
		}
		return this;
	}
	public BeanToJson replace(String key,String value) throws ContextException{
		if (this.jsonObject.containsKey(key)) {
			this.jsonObject.put(key, value);
		}else {
			throw new ContextException("map中对应对象不存在！");
		}
		return this;
	}
	public String toJsonString()throws ContextException{
		String returnString ="";
		try {
			if (this.jsonObject.size()==0) {
				throw new ContextException("传递的json对象为空！");
			}else {
				returnString= mapper.writeValueAsString(this.jsonObject);
			}
		} catch (JsonGenerationException e) {
			throw new ContextException("对象转化为json字符串错误！",e);
		} catch (JsonMappingException e) {
			throw new ContextException("对象转化为json字符串错误！",e);
		} catch (IOException e) {
			throw new ContextException("对象转化为json字符串错误！",e);
		}
		return returnString.trim();
	}
	
}
