/**
 * @Title: AbstactServlet.java
 * @Package cn.edu.nenu.www.funs.filter
 * @Description: TODO(用一句话描述该文件做什么)
 * @author qiuxiao
 * @date 2016年7月31日
 * @version V1.0
 */
package cn.edu.nenu.clzc.commons.core;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import cn.edu.nenu.clzc.commons.core.annotation.RequestMapping;
import cn.edu.nenu.clzc.commons.core.annotation.ResponseBody;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.commons.utils.Assert;
import cn.edu.nenu.clzc.commons.utils.BeanToJson;
import cn.edu.nenu.clzc.commons.utils.PageBean;
import cn.itcast.commons.CommonUtils;
/*
 * @类功能说明：
 * @公司名称：蓝旭工作室
 * @作者：qiuxiao
 * @创建时间：2016年7月31日 下午10:17:16
 * @版本：V1.0 
 */
@WebServlet(asyncSupported=true)
public abstract class AbstractServlet extends HttpServlet {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	protected PrintWriter out;

	private BeanToJson jsonStr;

	protected HttpSession session;

	protected static ServletContext context;

	protected ServletConfig config;

	protected HttpServletRequest request;

	protected HttpServletResponse response;

	private String contentType;

	private String actionName;

	private Method action;

	private Object actionReturn;

	protected Integer sizeMax;// 文件最大上限

	protected String uploadPath; // 上传文件的目录

	protected String tmpUploadPath; // 临时文件目录

	protected String filePart = "";// 文件模块

	protected List<String> fileNames = new ArrayList<String>();// 文件名称

	private List<FileItem> fileItems;

	private Map<String, Object> paraMap;
	
	Logger logger;
	
	private Map<String, ?> urlParam;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		logger=Logger.getLogger(this.getClass());
		setContentType("text/html;charset=UTF-8");// 初始化响应编码
		AbstractServlet.context = this.getServletContext();// 创建servletContext对象
		this.uploadPath = context.getRealPath( context.getInitParameter("upload")).replace("\\", "/");
		this.tmpUploadPath = context.getRealPath( context.getInitParameter("tmp-upload")).replace("\\", "/");
		this.sizeMax = 10;
		logger.info("Dispatcher init end!");
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		try {
			createAction(request, response);
			doAfter();
		} catch (Exception e) {
			if (e instanceof ServletException || e instanceof IOException) 
				handleException(e);
			else 
				throw new ServletException(e);
		}
		
	}


	/**
	 * 调用action方法之前进行一系列的初始化操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 */
	protected void doBefore(HttpServletRequest request, HttpServletResponse response) throws  ServletException {
		this.config = this.getServletConfig();// 初始化servletconfig对象

		this.session = request.getSession();// 初始化httpsession对象

		setRequest(request);// 初始化request

		setResponse(response);// 初始化response

		response.setCharacterEncoding("UTF-8");// 一定要放在out对象创建之前

		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new ServletException("illegal charactor encoding  "+"UTF-8", e);
		}

		this.paraMap = new HashMap<String, Object>();

		this.jsonStr = new BeanToJson();// 创建一个处理json字符串的对象

	}
	
	/*处理ServletException和IOexception异常在controller类扩展类中一定要记得实现它*/
	protected abstract void handleException(Exception e);
	
	protected void doAfter() throws ServletException {
		if (Assert.isNotNull(this.actionReturn) && (this.actionReturn instanceof String)) {
			/*获取方法返回值*/
			String actionReturnString = (String) actionReturn;
			
			if (actionReturnString != null && !actionReturnString.trim().isEmpty()) {// 如果请求处理方法返回不为空
				try {/* "r:xxx","f:xxx","xxx" */
					int index = actionReturnString.indexOf(":");// 获取第一个冒号的位置
					/*是否已经包含前缀*/
					boolean containsBase = actionReturnString.contains(SystemConstant.JSP_BASE);
					/*请求的是一个jsp页面的话加上jsp页面前缀*/
					boolean containsJsp = actionReturnString.contains(".jsp");
					if (index == -1) {
						// 如果没有冒号，使用转发
						if(containsJsp && !containsBase);
							String url=SystemConstant.JSP_BASE+actionReturnString;
						
						/*获取将要传递请求的参数*/
						String param = getUrlParam();
						if (Assert.isNotNull(param))
							url += url+"?"+param;
						
						request.getRequestDispatcher(param) .forward(request, response);
					} else {
						// 如果存在冒号
						String start = actionReturnString.substring(0, index);// 分割出前缀
						String path = actionReturnString.substring(index + 1);// 分割出路径
						
						if(containsJsp && !containsBase)
							path = SystemConstant.JSP_BASE + path;
						
						String url=path;
						/*获取将要传递请求的参数*/
						String param = getUrlParam();
						if (Assert.isNotNull(param))
							url += url+"?"+param;
						
						if (start.equals("f")) {// 前缀为r表示重定向
							// 前缀为f表示转发
							request.getRequestDispatcher(param).forward( request, response);
						} else if (start.equals("r")) {
							// 前缀为r表示重定向
							response.sendRedirect(request.getContextPath() + param);
						}
					}
				} catch (ServletException e) {
					logger.error("address： " + actionReturnString + " request faild！", e);
					throw new ServletException("address： " + actionReturnString + " request faild！", e);
				} catch (IOException e) {
					logger.error("faild ： " + actionReturnString + " request faild！", e);
					throw new ServletException("faild ： " + actionReturnString + " request faild！", e);
				}
			}
		} else if (Assert.isNull(this.actionReturn)) {
			// 为空的时候说明直接使用了转发和重定向，不用处理也行。以后可以在进行相应的封装（返回其他的类型）
		}
	}

	protected Double getDouble(String name) throws ServletException {
		String valueString = getString(name);
		
		if (!Assert.isEmpty(valueString)) {
			return Double.parseDouble(valueString);
		} else {
			return null;
		}
	}

	protected Double getDoubleDefault(String name, double defaultDouble) throws ServletException {
		Double returnDouble = getDouble(name);
		if (Assert.isNull(returnDouble)) {
			return defaultDouble;
		} else {
			return returnDouble;
		}
	}
	
	protected String getString(String name) throws ServletException {
		String value = null;
		if (ServletFileUpload.isMultipartContent(request)) {
			getFormFiledMap();
			if (this.paraMap.containsKey(name)) {
				value = (String) this.paraMap.get(name);
			}
		} else {
			value = request.getParameter(name);
		}
		if (!Assert.isEmpty(value)) {
			return value;
		} else {
			return null;
		}
	}

	protected String getStringDefault(String name, String defaultString) throws ServletException {
		String returnsString = getString(name);
		if (Assert.isNull(returnsString)) {
			return defaultString;
		} else {
			return returnsString;
		}

	}

	protected Integer getInt(String name) throws ServletException {
		String valueString = getString(name);
		if (!Assert.isEmpty(valueString)) {
			return Integer.parseInt(valueString);
		} else {
			return null;
		}
	}

	protected Integer getIntDefault(String name, Integer defaultInteger) throws ServletException {
		Integer returnInteger = getInt(name);
		if (Assert.isNull(returnInteger)) {
			return defaultInteger;
		} else {
			return returnInteger;
		}
	}

	protected <T> T getBean(Class<T> clz) throws ServletException {
		T t = null;
		if (ServletFileUpload.isMultipartContent(request)) {
			t = CommonUtils.toBean(getFormFiledMap(), clz);
		} else {
			t = CommonUtils.toBean(request.getParameterMap(), clz);
		}
		return t;
	}
	protected void to500(String message,String path500) throws ServletException, IOException {
		request.setAttribute("error", message);
		
		/*是否已经包含前缀*/
		boolean containsBase = path500.contains(SystemConstant.JSP_BASE);
		/*请求的是一个jsp页面的话加上jsp页面前缀*/
		boolean containsJsp = path500.contains(".jsp");
		if (containsJsp && !containsBase) 
			path500 = SystemConstant.JSP_BASE+path500;
		request.getRequestDispatcher(path500).forward(request, response);
	}
	
	/**
	 * @throws ServletException 
	 * 获取指定名称写入磁盘后的文件全路径
	 * 
	 * @Title: getFilePath
	 * @Description: TODO
	 * @param @param name
	 * @param @return
	 * @param @throws ContextException 参数
	 * @return String 返回类型
	 * @throws
	 */
	protected String getFilePath(String name) throws  ServletException {
		Map<String, Object> map = getFormFiledMap();
		if (Assert.isNotNull(map) && map.size() > 0) {
			if (map.containsKey(name.trim())) {
				return (String) map.get(name);
			} else {
				logger.error("file is not found!");
				throw new ServletException("file is not found!");
			}
		} else {
			logger.error(" resolve file stream faild!");
			throw new ServletException(" resolve file stream faild!");
		}
	}

	protected void requestDispatcher(String path) throws ServletException, IOException {
		String params = getUrlParam();
		if(path.contains(".jsp") && !path.contains(SystemConstant.JSP_BASE))
			path=SystemConstant.JSP_BASE+path;
		
		String url=path;
		if (Assert.isNotNull(params) && !url.contains("?")) 
			url=url+"?"+params;
		else if (Assert.isNotNull(params) && url.contains("?"))
			url = url+"&"+params;
		
		this.request.getRequestDispatcher(url).forward(this.request, this.response);
	}
	protected void requestDispatcher(String controller,String method) throws ServletException, IOException {
		requestDispatcher(controller+"?to="+method);
	}

	protected void sendRedirect(String location) throws IOException {
		String params = getUrlParam();
		if (location.contains("jsp") && !location.contains(SystemConstant.JSP_BASE)) 
			location = SystemConstant.JSP_BASE+location;
		
		String url=this.getServletContext().getContextPath()+location;
		if (Assert.isNotNull(params) && !url.contains("?")) 
			url=url+"?"+params;
		else if (Assert.isNotNull(params) && url.contains("?"))
			url = url+"&"+params;
		
		this.response.sendRedirect(url);
	}
	protected void sendRedirect(String controller,String method) throws IOException {
		sendRedirect(controller+"?to="+method);
	}

	protected void setAttribute(String name, Object obj) {
		this.request.setAttribute(name, obj);
	}

	protected Object getAttribute(String name) {
		return this.request.getAttribute(name);
	}

	protected <T> void  putJson(String key, T t) {
		this.jsonStr.put(key, t);
	}

	protected void sendJson() {
		try {
			out.println(this.jsonStr.toJsonString());
		} catch (ContextException e) {
			e.printStackTrace();
		}
	}
	protected void sendJson(PageBean bean) throws ServletException  {
		if (bean==null)
			throw new NullPointerException();
		
		int size = bean.getSize();
		List<?> list = bean.getList();
		
		if (list==null) 
			throw new NullPointerException();
		
		this.jsonStr.put("size", size);
		this.jsonStr.put("list", list);
		
		try {
			out.println(this.jsonStr.toJsonString());
		} catch (ContextException e) {
			logger.error("error occur in sending json!",e);
			throw new ServletException("error occur in sending json!",e);
		}
	}

	private void createAction( HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		String methodName=null;
		try {
			Class<? extends AbstractServlet> controllerClazz = this .getClass();
			
			WebServlet webServlet = controllerClazz .getAnnotation(WebServlet.class);
			
			String[] servletMapping = webServlet.value();
			
			Method[] declaredMethods = this.getClass().getDeclaredMethods();
			
			boolean ismapped=false;
			
			for (int i = 0; i < declaredMethods.length; i++) {
				if (ismapped) 
					break;
				Method method = declaredMethods[i];
				
				methodName=method.getName();
				
				RequestMapping requestMapping = method .getAnnotation(RequestMapping.class);
				
				if (requestMapping==null)
					continue;
				
				String value = requestMapping.value();
				
				if(Assert.isEmpty(value)){
					NullPointerException nullPointerException = new NullPointerException("mapping url can`t be null");
					logger.error("mapping url can`t be null",nullPointerException);
					throw new ServletException(nullPointerException);
				}
					
				
				for (int j = 0; j < servletMapping.length; j++) {
					String mapping = servletMapping[j];
					
					if (value.contains(mapping.trim())) {
						int length = mapping.length();
						
						if (length == value.length()){
							IllegalArgumentException illegalArgumentException = new IllegalArgumentException( "illegal RequestMapping parameter！-> WebServlet value ：" + mapping + " RequestMapping value ： " + value);
							
							logger.error("error parameter! ", illegalArgumentException);
							throw new ServletException( "illegal RequestMapping parameter！-> WebServlet value ：" + mapping + " RequestMapping value ： " + value,illegalArgumentException);
						}
						value = value.substring(length+1); // 去除RequestMapping中WebServlet前缀
					}
					
					String requestMethod = request.getMethod();
					
					String allowedMethod = requestMapping.method();
					
					doBefore(request, response);
					actionName = getString("to");// 获取action参数
						
					if (actionName != null && actionName.equals(value.trim())) {
						if (requestMethod != null && allowedMethod.trim().contains(requestMethod)) {
							ismapped=true;
						
							out = response.getWriter();// 初始化out
							
							if (method .isAnnotationPresent(ResponseBody.class))
								setContentType("text/json;charset=UTF-8");/* 设置json响应 */
							
							Object returnObject = method.invoke(this);// 反射调用方法
							
							Class<?> returnType = method .getReturnType();
							this.actionReturn = returnType .cast(returnObject);
						
						} else {
							IllegalAccessException illegalAccessException = new IllegalAccessException("the method  handle " + allowedMethod + " request!");
							logger.error("error request method! ",illegalAccessException);
							throw new ServletException("the method  handle " + allowedMethod + " request!",illegalAccessException);
						}
					} 
				}
				if(i == declaredMethods.length-1 && ismapped == false){
					IllegalArgumentException t = new IllegalArgumentException(this.getClass().getName()+" not find available method handle request");
					logger.error("error request method! ", t);
					throw new ServletException("not find available method handle request",t);
				}
			}
		} catch (SecurityException e) {
			
			logger.error("unuseful method "+methodName,e);
			throw new ServletException("unuseful method "+methodName, e);
			
		} catch (IllegalAccessException e) {
			
			logger.error("unuseful method "+methodName,e);
			throw new ServletException("unuseful method "+methodName, e);
			
		} catch (IllegalArgumentException e) {
			
			logger.error("method argument illegal!",e);
			throw new ServletException("method argument illegal!", e);
			
		} catch (InvocationTargetException e) {
			
			logger.error("target method invoke error", e.getTargetException());
			throw new ServletException("target method invoke error",e.getTargetException());
			
		} catch (IOException e) {
			
			logger.error("io exception", e);
			throw e;
			
		}
		
	}

	/**
	 * 设置响应数据的格式
	 * 
	 * @Title: setContentType
	 * @Description: TODO
	 * @param @param contentType 参数
	 * @return void 返回类型
	 * @throws
	 */
	protected void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @return contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @return out
	 */
	public PrintWriter getOut() {
		return out;
	}

	/**
	 * @param out
	 *            the out to set
	 */
	public void setOut(PrintWriter out) {
		this.out = out;
	}

	/**
	 * @return jsonStr
	 */
	public BeanToJson getJsonStr() {
		return jsonStr;
	}

	/**
	 * @param jsonStr
	 *            the jsonStr to set
	 */
	public void setJsonStr(BeanToJson jsonStr) {
		this.jsonStr = jsonStr;
	}

	/**
	 * @return sizeMax
	 */
	public Integer getSizeMax() {
		return sizeMax;
	}

	/**
	 * @param sizeMax
	 *            the sizeMax to set
	 */
	public void setSizeMax(Integer sizeMax) {
		this.sizeMax = sizeMax;
	}

	/**
	 * @return session
	 */
	public HttpSession getSession() {
		return session;
	}

	/**
	 * @param session
	 *            the session to set
	 */
	public void setSession(HttpSession session) {
		this.session = session;
	}

	/**
	 * @return context
	 */
	public ServletContext getContext() {
		return context;
	}

	/**
	 * @return config
	 */
	public ServletConfig getConfig() {
		return config;
	}

	/**
	 * @param config
	 *            the config to set
	 */
	public void setConfig(ServletConfig config) {
		this.config = config;
	}
	private String getUrlParam(){
		if (urlParam !=null) {
			Set<String> keySet = urlParam.keySet();
			Iterator<String> iterator = keySet.iterator();
			String param=null;
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				Object value = urlParam.get(key);
				param += (key+"="+value.toString()+"&");
			}
			if (Assert.isNotNull(param))
				param=param.substring(0,param.length()-1);
			return param;
		}else {
			
			return "";
		}
	}
	/**
	 * @return request
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * @param request
	 *            the request to set
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * @return response
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

	/**
	 * @param response
	 *            the response to set
	 */
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * @return actionName
	 */
	public String getActionName() {
		return actionName;
	}

	/**
	 * @param actionName
	 *            the actionName to set
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	/**
	 * @return action
	 */
	public Method getAction() {
		return action;
	}

	/**
	 * @param action
	 *            the action to set
	 */
	public void setAction(Method action) {
		this.action = action;
	}

	/**
	 * @return actionReturn
	 */
	public Object getActionReturn() {
		return actionReturn;
	}

	/**
	 * @param actionReturn
	 *            the actionReturn to set
	 */
	public void setActionReturn(Object actionReturn) {
		this.actionReturn = actionReturn;
	}

	/**
	 * @return serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return uploadPath
	 */
	protected String getUploadPath() {
		return uploadPath;
	}

	/**
	 * @param uploadPath
	 *            the uploadPath to set
	 */
	protected void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	/**
	 * @return tmpUploadPath
	 */
	protected String getTmpUploadPath() {
		return tmpUploadPath;
	}

	/**
	 * @param tmpUploadPath
	 *            the tmpUploadPath to set
	 */
	protected void setTmpUploadPath(String tmpUploadPath) {
		this.tmpUploadPath = tmpUploadPath;
	}

	/**
	 * @return filePart
	 */
	protected String getFilePart() {
		return filePart;
	}

	/**
	 * @param filePart
	 *            the filePart to set
	 */
	protected void setFilePart(String filePart) {
		this.filePart = filePart;
	}

	/**
	 * @return fileNames
	 */
	protected List<String> getFileNames() {
		return fileNames;
	}

	/**
	 * @param fileNames
	 *            the fileNames to set
	 * @return
	 */
	protected void addFileNames(String fileName) {
		if (!Assert.isEmpty(fileName)) {
			this.fileNames.add(fileName.trim());
		}
	}

	/**
	 * 上传文件并且返回所有的参数信息
	 */
	private Map<String, Object> getFormFiledMap() throws ServletException {
		if (this.paraMap.size() < 1) {// 防止文件被重复写入内存
			createUploadDir();
			createFileItems();

			Map<String, Object> map = new HashMap<String, Object>();
			int fileIndex = 0;
			for (int i = 0; i < this.fileItems.size(); i++) {
				String filedName = null;
				FileItem item = this.fileItems.get(i);
				if (item.isFormField()) {
					String filedValueString = null;
					try {
						filedValueString = new String(item.getString("UTF-8")) .trim();// 防止中文乱码
					} catch (UnsupportedEncodingException e) {
						logger.error("Unsupported  encoding UTF-8",e);
						throw new ServletException("Unsupported  encoding UTF-8",e);
					}
					filedName = item.getFieldName().trim();
					map.put(filedName, filedValueString);
				} else {

					String filePath = null;
					// 文件名
					String fileName = item.getName().toLowerCase();
					// input框name属性值
					filedName = "file" + fileIndex;// 无法直接获取type=file的name值，构造file0,1,2,3...
					fileIndex++;
					String fileSuffix = fileName.substring(fileName .indexOf('.'));
					String uuid = UUID.randomUUID().toString();
					if (fileNames.size() == 0) {
						filePath = uploadPath +System.getProperty("file.separator")+ uuid + fileSuffix;
					} else {
						if (i < fileNames.size()) {
							filePath = uploadPath + System.getProperty("file.separator") + fileNames.get(i) + uuid
									+ fileSuffix;
						} else {
							filePath = uploadPath + System.getProperty("file.separator") + uuid + fileSuffix;
						}
					}
					filePath.trim();
					try {
						item.write(new File(filePath));
					} catch (Exception e) {
						logger.error("upload file write to disc error!",e);
						throw new ServletException("upload file write to disc error!",e);
					}
					map.put(filedName, filePath);
				}
			}
			this.paraMap = map;
		}
		return this.paraMap;
	}

	/**
	 * @throws ServletException 
	 * 获取所有数据的fileItem对象列表
	 * 
	 * @Title: getFileItems
	 * @Description: TODO
	 * @param @return
	 * @param @throws ContextException 参数
	 * @return List<FileItem> 返回类型
	 * @throws
	 */
	public List<FileItem> createFileItems() throws  ServletException {
		if (Assert.isNotNull(this.fileItems) && this.fileItems.size() > 0) {
			return this.fileItems;
		} else {
			ServletFileUpload servletFileUpload = createServletFileUpload();
			
			servletFileUpload.setFileSizeMax(sizeMax * 1024 * 1024);// 表单传递的单个文件的最大限制
			servletFileUpload.setSizeMax(1024 * 1024 * 1024);// 文件最大上限整个表单传过来的所有文件的总大小
			
			try {
				this.fileItems = servletFileUpload.parseRequest(request);
			} catch (FileUploadException e) {
				logger.error("resolve file faild！", e);
				throw new ServletException("resolve file faild！", e);
			}
		}
		return this.fileItems;
	}

	/**
	 * 获取ServletFileUpload对象
	 * 
	 * @Title: createServletFileUpload
	 * @Description: TODO
	 * @param @return 参数
	 * @return ServletFileUpload 返回类型
	 * @throws
	 */
	public ServletFileUpload createServletFileUpload() {
		DiskFileItemFactory factory = new DiskFileItemFactory();// 获取磁盘文件对象工厂
		if (!new File(tmpUploadPath).isDirectory()) {
			new File(tmpUploadPath).mkdirs();
		}
		factory.setSizeThreshold(1024 * 1024); // 最大缓存
		factory.setRepository(new File(tmpUploadPath));// 临时文件目录

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		return upload;
	}

	/**
	 * 创建文件上传目录
	 * 
	 * @Title: createUploadDir
	 * @Description: TODO
	 * @param 参数
	 * @return void 返回类型
	 * @throws
	 */
	private void createUploadDir() {
		if (!Assert.isEmpty(this.filePart)) {
			this.uploadPath = this.uploadPath + "/" + this.filePart + "/";
		}
		if (!new File(uploadPath).isDirectory()) {
			new File(uploadPath).mkdirs();
		}
	}
}
